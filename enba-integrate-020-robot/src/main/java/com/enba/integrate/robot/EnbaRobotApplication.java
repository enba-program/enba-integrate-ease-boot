package com.enba.integrate.robot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EnbaRobotApplication {

  /**
   * 本案例：钉钉机器人发消息，企业微信发消息,本案例只是简单演示发送普通消息，更加丰富的功能，请根据官方文档自行扩展
   * <p>先去钉钉或者企微上创建群机器人</p>
   */

  public static void main(String[] args) {

    SpringApplication.run(EnbaRobotApplication.class, args);
  }
}
