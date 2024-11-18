package com.enba.starter.usedemo;

import com.enba.log.core.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EnbaStarterApplication implements ApplicationRunner {

  @Autowired private LogService logService;

  public static void main(String[] args) {

    SpringApplication.run(EnbaStarterApplication.class, args);
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    logService.log("使用自定义starter 基础版");
  }
}
