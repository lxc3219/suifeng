package org.suifeng.baseframework.api.result;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 返回体统一包装配置类
 * @author luoxc
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "api.result")
public class ResponseResultProperties {

    /**
     * 是否启用返回体自动包装
     */
    private boolean enabled;

    /**
     * 启动注解配置：true，启用注解；false，禁用注解
     */
    private boolean ann;
}
