package com.enba.integrate.nacosdiscovery.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enba-nacos-discovery")
public class EnbaNacosDiscoveryController {

  @GetMapping("/hello")
  public String hello() {
    return "hello world";
  }
}
