package com.enba.integrate.preventretry.exception;

public class PreventRetryException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private static final int ERR_CODE = 500;

  private int code;

  private String message;

  public PreventRetryException() {
    super("禁止重复提交");
    this.message = "禁止重复提交";
  }

  public PreventRetryException(String message) {
    this(ERR_CODE, "禁止重复提交");
  }

  public PreventRetryException(int code, String message) {
    super(message);
    this.code = code;
    this.message = message;
  }

  public PreventRetryException(int code, String message, Throwable cause) {
    super(message, cause);
    this.code = code;
    this.message = message;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  @Override
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
