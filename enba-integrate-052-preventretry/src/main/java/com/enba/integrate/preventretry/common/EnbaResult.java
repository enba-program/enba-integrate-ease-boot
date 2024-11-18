package com.enba.integrate.preventretry.common;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class EnbaResult {

  private static final String SUCCESS = "成功";

  private static final Integer SUCCESS_CODE = 200;

  /** 响应是否成功 */
  private Boolean success;

  /** 业务码值 */
  private Integer code;

  /** 响应文本或错误信息 */
  private String message;

  /** 返回的业务数据 */
  private Object result;

  public static EnbaResult success(Object result) {
    return new EnbaResult()
        .setSuccess(Boolean.TRUE)
        .setCode(SUCCESS_CODE)
        .setMessage(SUCCESS)
        .setResult(result);
  }

  public static EnbaResult err(Integer code, String message) {
    return new EnbaResult().setCode(code).setMessage(message).setSuccess(Boolean.FALSE);
  }
}
