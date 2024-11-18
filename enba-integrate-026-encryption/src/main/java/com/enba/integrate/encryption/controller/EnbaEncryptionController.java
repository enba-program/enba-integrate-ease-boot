package com.enba.integrate.encryption.controller;

import com.enba.integrate.encryption.algorithm.Aalgorithm;
import com.enba.integrate.encryption.algorithm.Balgorithm;
import com.enba.integrate.encryption.annotation.ResponseBodyEncryption;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 演示：类上不加注解,只在方法上加注解
 */
@RestController
@RequestMapping("/enba-encryption")
public class EnbaEncryptionController {

  /** 演示不使用加密算法返回结果 */
  @GetMapping("/noAlgorithm")
  public String noAlgorithm() {

    return "不使用加密算法直接返回";
  }

  /** 演示使用自定义加密算法A 返回结果 */
  @GetMapping("/test-Aalgorithm")
  @ResponseBodyEncryption(encryptionClazz = Aalgorithm.class, encryption = true)
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
