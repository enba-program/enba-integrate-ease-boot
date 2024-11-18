package com.enba.integrate.guavaevent.controller;

import com.enba.integrate.guavaevent.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enba-guava")
public class EnbaGuavaController {

  @Autowired
  private LoginService loginService;

  @RequestMapping("/login")
  public String login() {
    loginService.loginUser("enba");
    return "login";
  }

  @RequestMapping("/hello")
  public String hello() {
    return "hello";
  }
}
