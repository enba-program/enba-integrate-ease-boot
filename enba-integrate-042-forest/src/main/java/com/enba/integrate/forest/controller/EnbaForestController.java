package com.enba.integrate.forest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enba-forest")
public class EnbaForestController {

  @RequestMapping("/hello")
  public String hello() {
    return "Hello from Enba Forest!";
  }
}
