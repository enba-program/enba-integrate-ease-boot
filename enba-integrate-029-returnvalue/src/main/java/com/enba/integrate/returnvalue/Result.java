package com.enba.integrate.returnvalue;

import com.enba.integrate.returnvalue.annotation.ApiSystemReturnResult;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class Result implements ApiSystemReturnResult {

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

  @Override
  public ApiSystemReturnResult success(Object result) {
    return new Result()
        .setSuccess(Boolean.TRUE)
        .setCode(SUCCESS_CODE)
        .setMessage(SUCCESS)
        .setResult(result);
  }
}
