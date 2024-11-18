package com.enba.starter.usedemo;

import com.enba.log.upgrade.core.LogService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EnbaStarterApplication implements InitializingBean {

  @Autowired private LogService logService;

  public static void main(String[] args) {
    SpringApplication.run(EnbaStarterApplication.class, args);
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    logService.log("使用自定义starter 升级版");
  }
}
