package com.enba.integrate.nacosconfig.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enba-nacos-config")
public class EnbaNacosConfigController {

  @NacosValue(value = "${text}", autoRefreshed = true)
  private String text;

  @RequestMapping("/hello-text")
  public String hello() {
    return "hello world " + text;
  }
}
