package com.enba.async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class EnbaAsyncApplication {

  public static void main(String[] args) {
    SpringApplication.run(EnbaAsyncApplication.class, args);
  }

}
