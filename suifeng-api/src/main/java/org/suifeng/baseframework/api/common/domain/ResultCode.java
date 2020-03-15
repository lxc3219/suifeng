package org.suifeng.baseframework.api.common.domain;

import org.suifeng.baseframework.model.base.BaseExceptionEnum;


public class ResultCode implements BaseExceptionEnum {

    private Integer code;
    private String message;

    public ResultCode() {};

    public ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
