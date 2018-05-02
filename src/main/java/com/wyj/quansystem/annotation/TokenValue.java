package com.wyj.quansystem.annotation;

import java.lang.annotation.*;

/**
 * @author wyj
 * @date 2018/4/23 13:25
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TokenValue {
    String value() default "token";
}
