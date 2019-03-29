package com.tangshengbo.core;

import java.lang.annotation.*;


@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JsonEscape {

    /**
     * 需要转义的属性
     * @return
     */
    String value() default "response";

    String encoding() default "UTF-8";
}
