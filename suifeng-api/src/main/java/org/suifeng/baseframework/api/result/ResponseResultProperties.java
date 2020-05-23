package org.suifeng.baseframework.api.result;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.suifeng.baseframework.api.common.ApiPropertiesPrefix;

/**
 * 返回体统一包装配置类
 * @author luoxc
 * @since 1.0.0
 */
@Setter
@Getter
@ConfigurationProperties(prefix = ApiPropertiesPrefix.API_RESULT_PROP_PREFIX)
public class ResponseResultProperties {

    /**
     * 启用返回体自动包装开关，默认开启
     */
    private boolean enabled;

    /**
     * 启用注解配置：true，启用注解；false，禁用注解
     */
    private boolean ann;

}
