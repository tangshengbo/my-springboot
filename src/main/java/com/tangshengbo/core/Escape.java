package com.tangshengbo.core;

import java.lang.annotation.*;

/**
 * Created by Tangshengbo on 2019/3/29
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@JsonEscape
@PurgeJsonEscape
public @interface Escape {
}
