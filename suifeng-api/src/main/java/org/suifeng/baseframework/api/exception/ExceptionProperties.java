package org.suifeng.baseframework.api.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.suifeng.baseframework.api.common.ApiPropertiesPrefix;

/**
 * 异常属性类
 * @author luoxc
 */
@Setter
@Getter
@ConfigurationProperties(prefix = ApiPropertiesPrefix.API_EXCEPTION_PROP_PREFIX)
public class ExceptionProperties {

    /**
     * 异常类型：admin，rest
     */
    private String type;

    /**
     * 异常页面
     */
    private ExceptionPage page;
}
