package com.enba.integrate.exceptionhandler.exception;

import com.enba.integrate.exceptionhandler.common.EnbaResult;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = {"com.enba.integrate.exceptionhandler"})
@Slf4j
public class EnbaExceptionHandler {

  /**
   * 这个异常通常在使用Spring框架时出现。当一个带有@Valid或@Validated注解的方法参数未能通过javax.validation提供的验证约束时，Spring
   * MVC会抛出MethodArgumentNotValidException。这个异常通常用于Web层，确保传递给控制器方法的参数是有效的。
   *
   * @param ex 异常信息
   * @return 返回结果
   */
  @ExceptionHandler({MethodArgumentNotValidException.class})
  @ResponseStatus(HttpStatus.OK)
  public EnbaResult handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
    log.error("参数校验异常:{}", ex.getBindingResult().getAllErrors().get(0).getDefaultMessage(), ex);

    return EnbaResult.err(400, ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
  }

  /**
   * 这个异常是由javax.validation
   * API定义的，在任何非Spring环境中都可能发生，或者在Spring环境中使用Validator接口进行验证时发生。当你直接使用Validator类来验证对象并且该对象未能通过验证时，会抛出ConstraintViolationException。
   *
   * @param ex
   * @return
   */
  @ExceptionHandler({ConstraintViolationException.class})
  @ResponseStatus(HttpStatus.OK)
  public EnbaResult handleConstraintViolationException(ConstraintViolationException ex) {
    log.error("参数校验异常:{}", ex.getConstraintViolations().iterator().next().getMessage(), ex);

    return EnbaResult.err(400, ex.getConstraintViolations().iterator().next().getMessage());
  }

  /**
   * 处理自定义异常
   *
   * @param ex
   * @return
   */
  @ExceptionHandler(EnbaBizException.class)
  @ResponseStatus(HttpStatus.OK)
  public EnbaResult handleBizException(EnbaBizException ex) {
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
