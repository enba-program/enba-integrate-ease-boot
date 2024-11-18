package com.enba.integrate.easyretry.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enba-easyretry")
public class EnbaEsyRetryController {

  @RequestMapping("/hello")
  public String hello() {
    return "hello world";
  }
}
