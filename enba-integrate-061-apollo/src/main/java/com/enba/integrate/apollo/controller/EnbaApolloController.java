package com.enba.integrate.apollo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enba-apollo")
public class EnbaApolloController {

  @RequestMapping("/hello")
  public String hello() {
    return "hello world";
  }
}
