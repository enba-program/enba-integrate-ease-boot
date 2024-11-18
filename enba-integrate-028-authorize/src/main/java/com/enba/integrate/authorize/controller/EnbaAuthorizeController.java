package com.enba.integrate.authorize.controller;

import com.enba.integrate.authorize.annotation.PreAuthorize;
import com.enba.integrate.authorize.strategy.DemoAuthStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enba-authorize")
@PreAuthorize(checkAuthClass = DemoAuthStrategy.class, checkAuth = true)
public class EnbaAuthorizeController {

  @PreAuthorize(checkAuthClass = DemoAuthStrategy.class, checkAuth = true)
  @GetMapping("/test")
  public String test() {
    return "test";
  }
}
