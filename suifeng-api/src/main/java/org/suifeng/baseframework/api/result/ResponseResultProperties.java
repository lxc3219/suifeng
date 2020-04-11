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
     * 启用返回体自动包装开关，默认开启
     */
    private boolean enabled = true;

    /**
     * 启动注解配置：true，启用注解；false，禁用注解
     */
    private boolean ann;
}
