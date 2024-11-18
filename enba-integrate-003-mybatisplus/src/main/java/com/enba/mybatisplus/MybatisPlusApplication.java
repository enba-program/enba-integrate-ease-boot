package com.enba.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.enba.mybatisplus.dao")
@SpringBootApplication
public class MybatisPlusApplication {

  public static void main(String[] args) {
    SpringApplication.run(MybatisPlusApplication.class, args);
  }

}
