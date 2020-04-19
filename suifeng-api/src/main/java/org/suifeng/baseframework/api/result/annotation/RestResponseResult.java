package org.suifeng.baseframework.api.result.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Controller;
import java.lang.annotation.*;

/**
 * 自定义标识注解，用于标识该接口需要被 CommonResult 包装
 * 常标记于类上，代替 @Controller，@ResponseBody，@ResponseResult
 * @author luoxc
 * @since 1.0.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Controller
@ResponseResult
public @interface RestResponseResult {
    @AliasFor(
            annotation = Controller.class
    )
    String value() default "";
}
