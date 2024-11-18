package com.enba.integrate.i18n;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EnbaI18nApplication {

  /**
   * 程序的入口点
   * <p>
   * 这个方法是应用程序的起点，它的主要作用是启动Spring Boot应用程序
   * 它通过SpringApplication类的run方法来启动应用程序
   * <p>
   * @param args 命令行参数，可以在应用启动时传递配置信息
   */
  public static void main(String[] args) {
      SpringApplication.run(EnbaI18nApplication.class, args);
  }
}
