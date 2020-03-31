package org.suifeng.baseframework.api.result;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 返回体统一包装自动配置类
 * @author luoxc
 */
@Configuration
@ConditionalOnProperty(prefix = "api.result", name = "enabled", havingValue = "true")
@EnableConfigurationProperties(ResponseResultProperties.class)
public class ResultAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(ResultResponseBodyAdvice.class)
    public ResultResponseBodyAdvice resultResponseBodyAdvice() {
        return new ResultResponseBodyAdvice();
    }

}
