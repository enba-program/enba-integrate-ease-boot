package com.enba.integrate.easyes;

import org.dromara.easyes.starter.register.EsMapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EsMapperScan("com.enba.integrate.easyes.mapper")
public class EnbaEasyesApplication {

  public static void main(String[] args) {
    SpringApplication.run(EnbaEasyesApplication.class, args);
  }

}
