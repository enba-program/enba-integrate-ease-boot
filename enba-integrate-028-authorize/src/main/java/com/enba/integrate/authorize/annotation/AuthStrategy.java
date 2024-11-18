package com.enba.integrate.authorize.annotation;

import com.enba.integrate.authorize.exception.AuthException;
import java.util.Map;

public interface AuthStrategy<T extends AuthException> {

  /**
   * 权限校验
   *
   * @param args
   * @return
   */
  boolean access(Map<String, Object> args);

  /**
   * 未通过时，抛出自定义异常
   *
   * @param args
   * @return
   */
  T accessDeniedException(Map<String, Object> args);
}
