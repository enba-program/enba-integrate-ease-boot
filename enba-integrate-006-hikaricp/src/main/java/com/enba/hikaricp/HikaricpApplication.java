package com.enba.hikaricp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.enba.hikaricp.dao")
@SpringBootApplication
public class HikaricpApplication {

  public static void main(String[] args) {
    SpringApplication.run(HikaricpApplication.class, args);
  }

}
