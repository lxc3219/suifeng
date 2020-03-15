package org.suifeng.baseframework.model.base;

/**
 * 异常基类
 * @author luoxc
 */
public class BaseException extends RuntimeException {

    private Integer code;

    public BaseException(BaseExceptionEnum baseExceptionEnum) {
        this(baseExceptionEnum.getCode(), baseExceptionEnum.getMessage());
    }

    public BaseException() {
        super();
    }

    public BaseException(String message) {
        this(null, message);
    }

    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BaseException(Throwable cause) {
        this(null, cause);
    }

    public BaseException(String message, Throwable cause) {
        this(null, message, cause);
    }

    public BaseException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
