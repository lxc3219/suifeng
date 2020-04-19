package org.suifeng.baseframework.security.authenticate.annotation;

import java.lang.annotation.*;

/**
 * 权限注解
 * @author luoxc
 * @since 1.0.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Permission {

    String[] value();
}