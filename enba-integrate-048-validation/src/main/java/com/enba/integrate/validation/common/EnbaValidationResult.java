package com.enba.integrate.validation.common;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class EnbaValidationResult {

  private static final String SUCCESS = "成功";

  private static final Integer SUCCESS_CODE = 200;

  private static final Integer ERR_CODE = 500;

  /** 响应是否成功 */
  private Boolean success;

  /** 业务码值 */
  private Integer code;

  /** 响应文本或错误信息 */
  private String message;

  /** 返回的业务数据 */
  private Object result;

  public static EnbaValidationResult success(Object result) {
    return new EnbaValidationResult()
        .setSuccess(Boolean.TRUE)
        .setCode(SUCCESS_CODE)
        .setMessage(SUCCESS)
        .setResult(result);
  }

  public static EnbaValidationResult err(Integer code, String message) {
    return new EnbaValidationResult().setCode(code).setMessage(message).setSuccess(Boolean.FALSE);
  }

  public static EnbaValidationResult err(String message) {
    return err(ERR_CODE, message);
  }

  public static EnbaValidationResult err() {
    return err(ERR_CODE, "出错啦！！！");
  }
}
