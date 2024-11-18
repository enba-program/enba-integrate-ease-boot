package com.enba.integrate.captcha.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enba-captcha")
public class EnbaCaptchaController {

  // 生成验证码
  @GetMapping("/get-captcha")
  public String getCaptcha() {
    return "1234";
  }

}
