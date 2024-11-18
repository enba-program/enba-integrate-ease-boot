package com.enba.integrate.pmd.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enba-pmd")
public class EnbaPmdController {

  @RequestMapping("/hello")
  public String hello() {
    return "hello world";
  }
}
