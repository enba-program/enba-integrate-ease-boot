package com.enba.integrate.validation.controller;

import com.enba.integrate.validation.param.UserParam;
import com.enba.integrate.validation.param.UserParam.SecondValidation;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enba-validation")
@Validated
public class EnbaValidationController {

  @RequestMapping("/hello")
  public String hello() {
    return "hello world";
  }

  @GetMapping("/test01")
  public String test01(
      @NotBlank(message = "用户名不能为空") String name, @NotNull(message = "用户名id不能为空") Long userId) {

    return "简单校验";
  }

  @PostMapping("/test02")
  public String test02(@RequestBody @Validated UserParam userParam) {

    return "复杂校验";
  }

  @PostMapping("/test03")
  public String test03(@RequestBody @Validated(SecondValidation.class) UserParam userParam) {

    return "分组校验";
  }
}
