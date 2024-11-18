package com.enba.integrate.exceptionhandler.exception;

import lombok.Data;

/** 自定义业务异常 */
@Data
public class EnbaBizException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private Integer code;
  private String message;

  public EnbaBizException(Integer code, String message, Throwable cause) {
    super(message, cause);
    this.code = code;
    this.message = message;
  }
}
