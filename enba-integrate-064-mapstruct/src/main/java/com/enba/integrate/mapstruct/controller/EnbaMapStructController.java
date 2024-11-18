package com.enba.integrate.mapstruct.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/enba-mapstruct")
public class EnbaMapStructController {

  @RequestMapping(value = "/hello")
  public String hello() {
    return "hello world";
  }
}
