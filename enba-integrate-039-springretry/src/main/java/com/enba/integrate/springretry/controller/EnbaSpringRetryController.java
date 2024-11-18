package com.enba.integrate.springretry.controller;

import com.enba.integrate.springretry.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enba-spring-retry")
public class EnbaSpringRetryController {

  @Autowired private MyService myService;

  @GetMapping("/test")
  public String test() throws Exception {
    myService.doSomething();

    return "测试spring retry...";
  }
}
