package com.enba.druid;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.enba.druid.dao")
@SpringBootApplication
public class DruidApplication {

  public static void main(String[] args) {
    SpringApplication.run(DruidApplication.class, args);
  }

}
