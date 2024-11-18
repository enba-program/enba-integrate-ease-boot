package com.enba.log.demo;

import com.enba.log.core.LogStorage;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LogDemoApplication implements InitializingBean {

  @Autowired private LogStorage logStorage;

  public static void main(String[] args) {
    SpringApplication.run(LogDemoApplication.class, args);
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    logStorage.storeLog("测试使用starter");
  }
}
