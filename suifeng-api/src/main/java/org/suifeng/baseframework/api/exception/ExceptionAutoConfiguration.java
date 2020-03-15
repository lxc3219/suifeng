package org.suifeng.baseframework.api.exception;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ErrorViewProperties.class)
public class ExceptionAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(AdminExceptionHandler.class)
    @ConditionalOnProperty(prefix = "api.exception", name = "type", havingValue = "admin")
    public AdminExceptionHandler adminExceptionHandler() {
        return new AdminExceptionHandler();
    }

    @Bean
    @ConditionalOnMissingBean(RestExceptionHandler.class)
    @ConditionalOnProperty(prefix = "api.exception", name = "type", havingValue = "rest")
    public RestExceptionHandler restExceptionHandler() {
        return new RestExceptionHandler();
    }

}
