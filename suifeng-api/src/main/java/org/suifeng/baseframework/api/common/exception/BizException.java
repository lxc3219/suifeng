package org.suifeng.baseframework.api.common.exception;

import org.suifeng.baseframework.model.base.BaseException;
import org.suifeng.baseframework.model.base.BaseExceptionEnum;

/**
 * 业务异常
 * @author luoxc
 * @since 1.0.0
 */
public class BizException extends BaseException {

    public BizException(BaseExceptionEnum baseExceptionEnum) {
        super(baseExceptionEnum);
    }

    public BizException() {
        super();
    }

    public BizException(String msg) {
        super(msg);
    }

    public BizException(Integer code, String msg) {
        super(code, msg);
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    public BizException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public BizException(Integer code, String msg, Throwable cause) {
        super(code, msg, cause);
    }
}
