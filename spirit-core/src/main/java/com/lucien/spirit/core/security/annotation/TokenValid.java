package com.lucien.spirit.core.security.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记此注解则会验证TOKEN.
 * <p>User: lijunf
 * <p>Date: 2016年2月24日 下午4:31:28
 * <p>Version: 1.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TokenValid {

}
