package com.enba.integrate.preventretry.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enba-preventretry")
public class EnbaPreventRetryController {

  @RequestMapping("/hello")
  public String hello() {
    return "hello";
  }

  @PostMapping("/test-prevent")
  public String hello2() {
    return "测试防止重复提交";
  }
}
