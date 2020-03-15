package org.suifeng.baseframework.api.result;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "api.result", name = "enabled", havingValue = "true")
public class ResultAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(ResultHandlerInterceptor.class)
    public ResultHandlerInterceptor resultHandlerInterceptor() {
        return new ResultHandlerInterceptor();
    }

    @Bean
    @ConditionalOnMissingBean(ResultWebMvcConfigurer.class)
    public ResultWebMvcConfigurer resultWebMvcConfigurer() {
        return new ResultWebMvcConfigurer();
    }

    @Bean
    @ConditionalOnMissingBean(ResultResponseBodyAdvice.class)
    public ResultResponseBodyAdvice resultResponseBodyAdvice() {
        return new ResultResponseBodyAdvice();
    }

}
