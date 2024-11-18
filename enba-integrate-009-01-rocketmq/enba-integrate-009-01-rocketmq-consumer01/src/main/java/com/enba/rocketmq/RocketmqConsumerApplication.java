package com.enba.rocketmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>此服务作为消息消费者<p/>
 */
@SpringBootApplication
public class RocketmqConsumerApplication {

  public static void main(String[] args) {
    SpringApplication.run(RocketmqConsumerApplication.class, args);
  }

}
