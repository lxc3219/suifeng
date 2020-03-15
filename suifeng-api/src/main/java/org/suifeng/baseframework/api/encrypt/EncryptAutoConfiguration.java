package org.suifeng.baseframework.api.encrypt;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(EncryptProperties.class)
@ConditionalOnProperty(prefix = "api.encrypt", name = "enabled", havingValue = "true")
public class EncryptAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(EncryptRequestBodyAdvice.class)
    public EncryptRequestBodyAdvice encryptRequestBodyAdvice() {
        return new EncryptRequestBodyAdvice();
    }

    @Bean
    @ConditionalOnMissingBean(EncryptResponseBodyAdvice.class)
    public EncryptResponseBodyAdvice encryptResponseBodyAdvice() {
        return new EncryptResponseBodyAdvice();
    }
}
