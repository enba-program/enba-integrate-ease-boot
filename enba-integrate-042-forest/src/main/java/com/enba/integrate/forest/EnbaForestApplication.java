package com.enba.integrate.forest;

import com.dtflys.forest.springboot.annotation.ForestScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@ForestScan(basePackages = "com.enba.integrate.forest.client")
@SpringBootApplication
public class EnbaForestApplication {

  public static void main(String[] args) {
    SpringApplication.run(EnbaForestApplication.class, args);
  }
}
