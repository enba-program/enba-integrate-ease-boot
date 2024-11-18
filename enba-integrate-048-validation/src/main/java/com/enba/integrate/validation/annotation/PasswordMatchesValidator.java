package com.enba.integrate.validation.annotation;

import com.enba.integrate.validation.param.UserParam;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/** 自定义校验器 */
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, UserParam> {

  @Override
  public void initialize(PasswordMatches constraintAnnotation) {}

  @Override
  public boolean isValid(UserParam userParam, ConstraintValidatorContext context) {
    return userParam.getPassword().equals(userParam.getConfirmPassword());
  }
}
