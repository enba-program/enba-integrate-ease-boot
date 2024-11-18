package com.enba.integrate.profiles.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enba-profiles")
public class EnbaProfilesController {

  @Value("${text}")
  private String text;

  @GetMapping("/get-text")
  public String getText() {
    return text;
  }

  @RequestMapping("/hello")
  public String hello() {
    return "hello world";
  }
}
