package com.enba.rocketmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>此服务作为消息发送者<p/>
 * <p>演示：单个消息推送，批量消息推送，集群消息,广播消息，延迟消息,消息重试，消息过滤，消息幂等，事务消息<p/>
 */
@SpringBootApplication
public class RocketmqProducerApplication {

  public static void main(String[] args) {
    SpringApplication.run(RocketmqProducerApplication.class, args);
  }
}
