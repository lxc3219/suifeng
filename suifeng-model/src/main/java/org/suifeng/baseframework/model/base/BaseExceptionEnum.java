package org.suifeng.baseframework.model.base;

/**
 * 异常枚举基类
 * @author luoxc
 * @since 1.0.0
 */
public interface BaseExceptionEnum {

    /**
     * 获取异常编码
     * @return code
     */
    Integer getCode();

    /**
     * 获取异常信息
     * @return message
     */
    String getMessage();

}
