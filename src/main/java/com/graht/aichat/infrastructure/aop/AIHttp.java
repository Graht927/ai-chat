package com.graht.aichat.infrastructure.aop;

import java.lang.annotation.*;

/**
 * @author GRAHT
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AIHttp {
}
