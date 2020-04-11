package org.suifeng.baseframework.api.cors;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web配置类
 * @author luoxc
 */
@Configuration
public class CorsWebMvcConfigurer implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean CorsFilterRegistration() {
        CorsFilter corsFilter = new CorsFilter();
        FilterRegistrationBean registration = new FilterRegistrationBean(corsFilter);
        registration.addUrlPatterns("/*");
        return registration;
    }

}
