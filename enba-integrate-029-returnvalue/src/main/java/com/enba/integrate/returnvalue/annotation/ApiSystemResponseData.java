package com.enba.integrate.returnvalue.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target({TYPE, METHOD})
public @interface ApiSystemResponseData {

  /** 返回值类型 */
  Class<? extends ApiSystemReturnResult> resultClass();
}
