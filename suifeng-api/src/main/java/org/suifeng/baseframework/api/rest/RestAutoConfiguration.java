package org.suifeng.baseframework.api.rest;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ConditionalOnProperty(prefix = "api.rest", name = "enabled", havingValue = "true")
@Import(RestTemplateConfiguration.class)
@EnableConfigurationProperties(RestProperties.class)
public class RestAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(RestResponseErrorHandler.class)
    public RestResponseErrorHandler restResponseErrorHandler() {
        return new RestResponseErrorHandler();
    }

    @Bean
    @ConditionalOnMissingBean(HeaderRequestInterceptor.class)
    public HeaderRequestInterceptor headerRequestInterceptor() {
        return new HeaderRequestInterceptor();
    }

//    @Bean
//    @ConditionalOnClass(value = {RestTemplate.class, HttpClient.class})
//    @ConditionalOnMissingBean(RestTemplate.class)
//    public RestTemplate restTemplate() {
//        return new RestTemplateConfiguration().getRestTemplate();
//    }

    @Bean
    @ConditionalOnMissingBean(RestClientHelper.class)
    public RestClientHelper restClientHelper() {
        return new RestClientHelper();
    }
}
