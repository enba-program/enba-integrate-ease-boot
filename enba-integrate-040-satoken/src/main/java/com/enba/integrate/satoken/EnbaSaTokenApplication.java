package com.enba.integrate.satoken;

import cn.dev33.satoken.SaManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EnbaSaTokenApplication {

  public static void main(String[] args) {
    SpringApplication.run(EnbaSaTokenApplication.class, args);
    System.out.println("\n启动成功：Sa-Token配置如下：" + SaManager.getConfig());
  }
}
