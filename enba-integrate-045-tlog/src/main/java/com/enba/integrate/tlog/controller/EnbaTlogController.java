package com.enba.integrate.tlog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enba-tlog")
public class EnbaTlogController {

  Logger log = LoggerFactory.getLogger(EnbaTlogController.class);

  @RequestMapping("/hello")
  public String hello() {
    log.info("EnbaTlogController.hello####{}", "INFO级别日志");
    return "hello world";
  }
}
