package org.suifeng.baseframework.model.base;

import org.suifeng.baseframework.model.constant.PunctuationMarkConstants;

/**
 * 基础属性前缀
 * @author luoxc
 * @since 1.0.0
 */
public class BasePropertiesPrefix {

    /**
     * 基础属性APP前缀
     */
    private static final String BASE_PROP_APPL_PREFIX = "suifeng";

    /**
     * 基础属性公共前缀
     */
    protected static final String BASE_PROP_COMMON_PREFIX = BASE_PROP_APPL_PREFIX + PunctuationMarkConstants.INTERVAL_NUMBER;

    /**
     * 基础属性启用KEY值
     */
    public static final String BASE_PROP_ENABLED_KEY = "enabled";

    /**
     * 基础属性启用属性值
     */
    public static final String BASE_PROP_ENABLED_VALUE = "true";

    /**
     * 基础属性禁用属性值
     */
    public static final String BASE_PROP_DISABLED_VALUE = "false";
}
