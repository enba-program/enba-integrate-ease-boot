package com.enba.integrate.authorize.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target({TYPE, METHOD})
public @interface PreAuthorize {

  /** 是否校验权限 */
  boolean checkAuth() default true;

  /** 鉴权策略 */
  Class<? extends AuthStrategy> checkAuthClass() default AuthStrategy.class;
}
