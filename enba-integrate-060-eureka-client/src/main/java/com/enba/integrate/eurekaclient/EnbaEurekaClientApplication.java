package com.enba.integrate.eurekaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EnbaEurekaClientApplication {

  public static void main(String[] args) {
    SpringApplication.run(EnbaEurekaClientApplication.class, args);
  }
}
