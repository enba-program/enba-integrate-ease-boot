package com.enba.integrate.nacosconfig;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@NacosPropertySource(dataId = "enba-nacos-config", autoRefreshed = true)
public class EnbaNacosConfigApplication {

  public static void main(String[] args) {
    SpringApplication.run(EnbaNacosConfigApplication.class, args);
  }
}
