package org.suifeng.baseframework.api.common;

import org.suifeng.baseframework.model.base.BasePropertiesPrefix;

/**
 * 接口相关属性前缀
 * @author luoxc
 * @since 1.0.0
 */
public final class ApiPropertiesPrefix extends BasePropertiesPrefix {

    public static final String API_PROP_COMMON_PREFIX = BASE_PROP_COMMON_PREFIX + "api.";

    /**
     * 返回体统一包装配置属性前缀
     */
    public static final String API_RESULT_PROP_PREFIX = API_PROP_COMMON_PREFIX + "result";

    /**
     * 接口加解密配置属性前缀
     */
    public static final String API_ENCRYPT_PROP_PREFIX = API_PROP_COMMON_PREFIX + "encrypt";

    /**
     * 接口 REST 配置属性前缀
     */
    public static final String API_REST_PROP_PREFIX = API_PROP_COMMON_PREFIX + "rest";

    /**
     * 接口异常配置属性前缀
     */
    public static final String API_EXCEPTION_PROP_PREFIX = API_PROP_COMMON_PREFIX + "exception";
}
