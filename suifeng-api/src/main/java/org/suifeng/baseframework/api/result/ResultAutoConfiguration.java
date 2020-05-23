package org.suifeng.baseframework.api.result;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.suifeng.baseframework.api.common.ApiPropertiesPrefix;

/**
 * 返回体统一包装自动配置类
 * @author luoxc
 * @since 1.0.0
 */
@Configuration
@ConditionalOnProperty(prefix = ApiPropertiesPrefix.API_RESULT_PROP_PREFIX,
        name = ApiPropertiesPrefix.BASE_PROP_ENABLED_KEY, havingValue = ApiPropertiesPrefix.BASE_PROP_ENABLED_VALUE)
@EnableConfigurationProperties(ResultProperties.class)
public class ResultAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(ResultResponseBodyAdvice.class)
    public ResultResponseBodyAdvice resultResponseBodyAdvice() {
        return new ResultResponseBodyAdvice();
    }

}
