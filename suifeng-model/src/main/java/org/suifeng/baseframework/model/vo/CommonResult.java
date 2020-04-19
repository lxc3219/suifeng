package org.suifeng.baseframework.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

/**
 * 响应消息,controller中处理后，返回此对象，响应请求结果给客户端。
 * @param <T>
 */
@Setter
@Getter
@ApiModel(value = "响应结果")
public class CommonResult<T> implements Serializable {

    @ApiModelProperty(value = "是否成功", required =  true)
    private Boolean success;

    @ApiModelProperty(value = "状态码", required = true)
    private Integer status;

    @ApiModelProperty(value = "业务代码，失败时返回具体错误码")
    private Integer code;

    @ApiModelProperty(value = "成功时返回null，失败时返回具体错误消息")
    private String message;

    @ApiModelProperty(value = "成功时具体返回值，失败时为null")
    private T data;

    @ApiModelProperty(value = "时间戳", required = true, dataType = "Long")
    private Long timestamp;

    public CommonResult() {}

    public CommonResult<T> success(Boolean success) {
        this.success = success;
        return this;
    }

    public CommonResult<T> status(Integer status) {
        this.status = status;
        return this;
    }

    public CommonResult<T> code(Integer code) {
        this.code = code;
        return this;
    }

    public CommonResult<T> data(T data) {
        this.data = data;
        return this;
    }

    public CommonResult<T> message(String message) {
        this.message = message;
        return this;
    }

    public CommonResult<T> putTimeStamp() {
        this.timestamp = System.currentTimeMillis();
        return this;
    }


}