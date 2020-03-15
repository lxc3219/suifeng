package org.suifeng.baseframework.api.common.annotation;

import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.*;

/**
 * 自定义标识注解，用于标识该类或方法需要被CommonResult包装，
 * 常标记于方法上，可代替@ResponseBody使用
 * FIXME 返回类型为String时，会使用StringHttpMessageConverter处理消息，导致报错
 * TODO 这个注解要不要废弃，直接使用 @ResponseBody 去判断？？？存在的唯一理由就是要开关控制是否统一返回体包装
 * @author luoxc
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ResponseBody
public @interface ResponseResult {

}
