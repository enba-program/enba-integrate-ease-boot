package com.enba.integrate.undertow.controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@RequestMapping("/enba-undertow")
public class EnbaUndertowController {

  @RequestMapping("/hello")
  public String hello() {
    return "hello undertow";
  }
}
