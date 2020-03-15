package org.suifeng.baseframework.api.common.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Controller;
import java.lang.annotation.*;

/**
 * 自定义标识注解，用于标识该接口需要被CommonResult包装
 * 常标记于类上，代替@Controller，@ResponseBody，@ResponseResult
 * TODO 这个注解要不要废弃，直接使用 @RestController 去判断？？？存在的唯一理由就是要开关控制是否统一返回体包装
 * @author luoxc
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
