package com.enba.integrate.preventretry.exception;

import com.enba.integrate.preventretry.common.EnbaResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = {"com.enba.integrate.preventretry"})
@Slf4j
public class EnbaExceptionHandler {

  /**
   * 处理自定义异常
   *
   * @param ex
   * @return
   */
  @ExceptionHandler(PreventRetryException.class)
  @ResponseStatus(HttpStatus.OK)
  public EnbaResult handleBizException(PreventRetryException ex) {
    log.error("业务异常:{}", ex.getMessage(), ex);

    return EnbaResult.err(ex.getCode(), ex.getMessage());
  }

  /**
   * 拖低异常，其它ExceptionHandler处理不了的异常，交给托底异常处理，比如数据库异常等
   *
   * @param ex
   * @return
   */
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.OK)
  public EnbaResult handleException(Exception ex) {
    // TODO 这里可以做一些业务级的处理，比如：推送钉钉消息通知等
    log.error("系统异常:{}", ex.getMessage(), ex);

    return EnbaResult.err(500, ex.getMessage());
  }
}
