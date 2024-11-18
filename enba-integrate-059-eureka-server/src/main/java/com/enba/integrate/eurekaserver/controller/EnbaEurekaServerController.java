package com.enba.integrate.eurekaserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enba-eureka-server")
public class EnbaEurekaServerController {

  @RequestMapping("/hello")
  public String hello() {
    return "hello world";
  }
}
