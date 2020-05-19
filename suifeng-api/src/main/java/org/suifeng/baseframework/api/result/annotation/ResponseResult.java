package org.suifeng.baseframework.api.result.annotation;

import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.*;

/**
 * 自定义标识注解，用于标识该类或方法需要被 CommonResult 包装
 * 常标记于方法上，可代替 @ResponseBody 使用
 * @author luoxc
 * @since 1.0.0
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ResponseBody
public @interface ResponseResult {

}
