package org.suifeng.baseframework.api.common.exception;

import org.suifeng.baseframework.api.common.enums.ResultEnum;
import org.suifeng.baseframework.model.base.BaseException;

/**
 * 服务器异常
 * @author luoxc
 * @since 1.0.0
 */
public class ServerException extends BaseException {

    public ServerException(String message) {
        super(ResultEnum.SERVICE_EXCEPTION.getCode(), message);
    }

}
