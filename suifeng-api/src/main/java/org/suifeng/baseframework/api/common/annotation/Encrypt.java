package org.suifeng.baseframework.api.common.annotation;

import java.lang.annotation.*;


/**
 * 加密注解，加了此注解的接口将进行数据加密操作
 * @author luoxc
 * @since 1.0.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Encrypt {

}
