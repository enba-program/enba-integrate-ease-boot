package com.enba.integrate.event.controller;

import com.enba.integrate.event.publisher.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enba-event")
public class EnbaEventController {

  @Autowired private UserService userService;

  @RequestMapping("/hello")
  public String hello() {
    return "hello";
  }

  @GetMapping("/register-user")
  public void registerUser() {
    userService.registerUser("enba");
  }
}
