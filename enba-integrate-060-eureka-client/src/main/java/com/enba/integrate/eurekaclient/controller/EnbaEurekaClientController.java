package com.enba.integrate.eurekaclient.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enba-eureka-client")
public class EnbaEurekaClientController {

  @RequestMapping("/hello")
  public String hello() {
    return "hello world";
  }
}
