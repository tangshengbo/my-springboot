package com.tangshengbo.core;

import java.lang.annotation.*;


@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PurgeJsonEscape {

    /**
     * 需要清除转义的属性
     * @return
     */
    String value() default "request";

    String encoding() default "UTF-8";
}
