package org.suifeng.baseframework.api.common.exception;

import org.suifeng.baseframework.api.common.enums.ResultEnum;
import org.suifeng.baseframework.model.base.BaseException;

/**
 * 参数异常
 * @author luoxc
 * @since 1.0.0
 */
public class ParamException extends BaseException {

    public ParamException(String message) {
        super(ResultEnum.PARAM_EXCEPTION.getCode(), message);
    }
}
