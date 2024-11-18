package com.enba.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EnbaOssApplication {

  /**
   * 此模块演示上传文件到云厂商（阿里云，腾讯云，七牛云，华为云等）的场景
   **/

  public static void main(String[] args) {
    SpringApplication.run(EnbaOssApplication.class, args);
  }

}
