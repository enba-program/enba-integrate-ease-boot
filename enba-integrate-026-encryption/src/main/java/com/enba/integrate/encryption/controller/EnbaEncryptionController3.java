package com.enba.integrate.encryption.controller;

import com.enba.integrate.encryption.algorithm.Aalgorithm;
import com.enba.integrate.encryption.algorithm.Balgorithm;
import com.enba.integrate.encryption.annotation.ResponseBodyEncryption;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** 演示：同时在类上加注解,在方法上也加注解 */
@ResponseBodyEncryption(encryptionClazz = Aalgorithm.class, encryption = true)
@RestController
@RequestMapping("/enba-encryption3")
public class EnbaEncryptionController3 {

  /** 类上加注解，方法上也加注解，方法上的注解的优先级高（类上的注解不生效） */

  /** 演示使用自定义加密算法A 返回结果 */
  @GetMapping("/test-Aalgorithm")
  @ResponseBodyEncryption(encryptionClazz = Balgorithm.class, encryption = true)
  public String testAalgorithm() {

    return "测试加密算法Aalgorithm";
  }

  /** 演示使用自定义加密算法B 返回结果 */
  @GetMapping("/test-Balgorithm")
  @ResponseBodyEncryption(encryptionClazz = Balgorithm.class, encryption = true)
  public String testBalgorithm() {

    return "测试加密算法Balgorithm";
  }
}
