package org.suifeng.baseframework.api.common.helper;

import org.suifeng.baseframework.model.vo.CommonResult;
import org.suifeng.baseframework.model.base.BaseExceptionEnum;
import org.springframework.http.HttpStatus;
import javax.servlet.http.HttpServletRequest;

public class RestHelper {

    /**
     * 请求处理成功
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> ok() {
        return ok(null);
    }

    /**
     * 请求处理成功
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> ok(T data) {
        return new CommonResult<T>()
                .success(Boolean.TRUE)
                .status(HttpStatus.OK.value())
                .data(data)
                .putTimeStamp();
    }

    /**
     * 请求处理失败（默认500错误）
     * @param message 调用结果消息
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> error(String message) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }

    /**
     * 请求处理失败
     * @param status 状态码
     * @param message 调用结果消息
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> error(Integer status, String message) {
        return error(status, null, message);
    }

    /**
     * 业务处理失败（默认500错误）
     * @param code 业务代码
     * @param message 调用结果消息
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> bizError(Integer code, String message) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), code, message);
    }

    /**
     * 业务处理失败（默认500错误）
     * @param baseExceptionEnum
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> bizError(BaseExceptionEnum baseExceptionEnum) {
        return bizError(baseExceptionEnum.getCode(), baseExceptionEnum.getMessage());
    }

    /**
     * 请求处理失败
     * @param status 状态码
     * @param code 业务代码
     * @param message 调用结果消息
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> error(Integer status, Integer code, String message) {
        return new CommonResult<T>()
                .success(Boolean.FALSE)
                .status(status)
                .code(code)
                .message(message)
                .putTimeStamp();
    }

    /**
     * 判断是否是ajax请求
     */
    public static boolean isAjax(HttpServletRequest request) {
        return (request.getHeader("X-Requested-With") != null && "XMLHttpRequest".equals(request.getHeader("X-Requested-With")));
    }
}
