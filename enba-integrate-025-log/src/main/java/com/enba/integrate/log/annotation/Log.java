package com.enba.integrate.log.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Log {

  /** 模块名称 */
  String module() default "";

  /** 方法名称 */
  String func() default "";
}
