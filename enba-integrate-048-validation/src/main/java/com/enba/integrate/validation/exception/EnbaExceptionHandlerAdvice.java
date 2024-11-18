package com.enba.integrate.validation.exception;

import com.enba.integrate.validation.common.EnbaValidationResult;
import java.util.Objects;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/** 全局统一异常处理 */
@RestControllerAdvice
@ResponseStatus(HttpStatus.OK)
public class EnbaExceptionHandlerAdvice {

  Logger log = LoggerFactory.getLogger(EnbaExceptionHandlerAdvice.class);

  /**
   * 处理参数绑定异常 当方法参数绑定失败时，会抛出BindException异常，该方法用于处理此类异常并返回验证结果
   *
   * @param e 绑定异常对象，包含错误信息和绑定结果
   * @return 返回一个EnbaValidationResult对象，根据错误情况携带错误信息
   */
  @ExceptionHandler(value = {BindException.class})
  public EnbaValidationResult handleMethodArgumentNotValidException(BindException e) {
    // 记录异常信息，便于问题追踪
    log.info("handleMethodArgumentNotValidException###:{}", e.getMessage(), e);

    // 获取默认错误信息，确保错误信息非空
    String errorMsg =
        Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();

    // 返回携带错误信息的验证结果对象
    return EnbaValidationResult.err(errorMsg);
  }

  /**
   * 处理验证异常并返回验证结果
   *
   * @param e 验证异常对象
   * @return EnbaValidationResult类型的验证结果
   */
  @ExceptionHandler(value = {ValidationException.class})
  public EnbaValidationResult constraintViolationException(ValidationException e) {
    // 记录验证异常信息
    log.info("constraintViolationException###:{}", e.getMessage(), e);

    // 检查是否为约束违反异常
    if (e instanceof ConstraintViolationException) {
      // 强制类型转换为约束违反异常
      ConstraintViolationException err = (ConstraintViolationException) e;
      // 获取第一个约束违反
      ConstraintViolation<?> constraintViolation =
          err.getConstraintViolations().stream().findFirst().get();
      // 获取约束违反的消息模板
      String messageTemplate = constraintViolation.getMessageTemplate();
      // 如果消息模板非空
      if (StringUtils.hasText(messageTemplate)) {
        // 返回错误的验证结果，携带消息模板内容
        return EnbaValidationResult.err(messageTemplate);
      }
    }
    // 如果不是约束违反异常或消息模板为空，返回错误的验证结果，携带原始异常信息
    return EnbaValidationResult.err(e.getMessage());
  }

  /** 异常处理 */
  @ExceptionHandler(value = Exception.class)
  public EnbaValidationResult finalException(Exception e) {
    log.info("finalException###:{}", e.getMessage(), e);

    return EnbaValidationResult.err("服务端异常");
  }
}
