package com.enba.integrate.flyway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enba-flyway")
public class EnbaFlywayController {

  @RequestMapping("/hello")
  public String hello() {
    return "hello flyway";
  }
}
