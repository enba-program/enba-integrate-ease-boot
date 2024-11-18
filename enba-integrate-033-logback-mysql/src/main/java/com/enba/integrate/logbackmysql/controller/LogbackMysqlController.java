package com.enba.integrate.logbackmysql.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logback-mysql")
public class LogbackMysqlController {

  private static final Logger log = LoggerFactory.getLogger(LogbackMysqlController.class);

  @RequestMapping("/print-log")
  public String test() {
    log.trace("TRACE 级别日志:{}", "TRACE");
    log.debug("DEBUG 级别日志:{}", "DEBUG");
    log.info("INFO 级别日志:{}", "INFO");
    log.warn("WARN 级别日志:{}", "WARN");
    log.error("ERROR 级别日志:{}", "ERROR");

    return "输出日志到mysql";
  }
}
