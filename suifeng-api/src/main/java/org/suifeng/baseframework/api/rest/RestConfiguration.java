package org.suifeng.baseframework.api.rest;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.util.ClassUtils;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;


@Configuration
@ConditionalOnClass(value = {RestTemplate.class, HttpClient.class})
@ConditionalOnProperty(prefix = "api.rest", name = "enabled", havingValue = "true")
public class RestConfiguration {

    @Autowired
    private HttpRequestProperties httpRequestProperties;

    @Autowired
    private RestResponseErrorHandler restResponseErrorHandler;

    /**
     * 使用RestTemplateBuilder来实例化RestTemplate对象
     * @createTime 2019/6/1 22:34
     * @author luoxc
     */
    @Bean
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = getRestTemplate();
        // 添加自定义请求头拦截器
//        restTemplate.setInterceptors(Collections.singletonList(headerRequestInterceptor));
        // 捕捉自定义的restException
        restTemplate.setErrorHandler(restResponseErrorHandler);
        return restTemplate;
    }

    /**
     * 创建HTTP客户端工厂
     * @createTime 2019/6/1 22:34
     * @author luoxc
     */
    private ClientHttpRequestFactory createFactory() {
        if (httpRequestProperties.getMaxConnTotal() <= 0) {
            SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
            factory.setConnectTimeout(httpRequestProperties.getConnectTimeout());
            factory.setReadTimeout(httpRequestProperties.getReadTimeout());
            return factory;
        }
        HttpClient httpClient = HttpClientBuilder.create().setMaxConnTotal(httpRequestProperties.getMaxConnTotal())
                .setMaxConnPerRoute(httpRequestProperties.getMaxConnPerRoute()).build();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
        factory.setConnectTimeout(httpRequestProperties.getConnectTimeout());
        factory.setReadTimeout(httpRequestProperties.getReadTimeout());
        return factory;
    }

    /**
     * 重新初始化MessageConverter
     * @createTime 2019/6/1 22:34
     * @author luoxc
     */
    private void reInitMessageConverter(RestTemplate restTemplate) {
        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
        Iterator<HttpMessageConverter<?>> iterator = messageConverters.iterator();
        while (iterator.hasNext()) {
            HttpMessageConverter<?> converter = iterator.next();
            /*
             * RestTemplate会默认添加HttpMessageConverter，
             * 而添加的StringHttpMessageConverter默认使用的字符集是ISO-8859-1，
             * 在遇到中文的时候会有乱码，所以需要将StringHttpMessageConverter使用的字符集修改为为UTF-8后重新设置。
             */
            if (converter instanceof StringHttpMessageConverter) {
                ((StringHttpMessageConverter) converter).setDefaultCharset(StandardCharsets.UTF_8);
            }
            /*
             * 由于MessageConverter消息转换器机制会判断"com.fasterxml.jackson.dataformat.xml.XmlMapper"是否存在，
             * 若存在则引入MappingJackson2XmlHttpMessageConverter，该消息转换器会优先于MappingJackson2HttpMessageConverter转换消息，
             * 导致调用接口默认返回xml格式，故要将其移除(或者降低优先级)以返回json
             * 此处是降低优先级（先移除，再根据需要引入MappingJackson2XmlHttpMessageConverter）
             */
            if (converter instanceof MappingJackson2XmlHttpMessageConverter) {
                iterator.remove();
            }
        }
        // 判断XmlMapper是否存在，若存在则添加MappingJackson2XmlHttpMessageConverter
        ClassLoader classLoader = RestConfiguration.class.getClassLoader();
        boolean jackson2XmlPresent = ClassUtils.isPresent("com.fasterxml.jackson.dataformat.xml.XmlMapper", classLoader);
        if (jackson2XmlPresent) {
            messageConverters.add(new MappingJackson2XmlHttpMessageConverter());
        }
    }

    /**
     * 初始化RestTemplate,并加入spring的Bean工厂，由spring统一管理
     * @createTime 2019/6/1 22:34
     * @author luoxc
     */
    public RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate(this.createFactory());
        reInitMessageConverter(restTemplate);
        return restTemplate;
    }
}
