package com.enba.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EnbaRabbitmqProducerApplication {

  // 本地安装访问可视化界面 http://localhost:15672/#/

  public static void main(String[] args) {
    SpringApplication.run(EnbaRabbitmqProducerApplication.class, args);
  }
}
