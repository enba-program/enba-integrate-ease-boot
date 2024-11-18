package com.enba.rocketmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 简单演示，此服务既作为消息发送者，也作为消息接受者
 */
@SpringBootApplication
public class RocketmqApplication {


  public static void main(String[] args) {

    SpringApplication.run(RocketmqApplication.class, args);
  }

}
