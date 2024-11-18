package com.enba.integrate.decryption.controller;

import com.enba.integrate.decryption.algorithm.A;
import com.enba.integrate.decryption.algorithm.B;
import com.enba.integrate.decryption.annotation.RequestBodyDecryption;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestBodyDecryption(decryptionClazz = A.class)
@RestController
@RequestMapping("/enba-decryption3")
public class EnbaDecryptionController3 {

  /** 演示不使用加密算法返回结果 */
  @GetMapping("/noAlgorithm")
  public String noAlgorithm() {

    return "不使用解密算法直接返回";
  }

  /** 演示使用自定义解密算法A 返回结果 */
  @RequestBodyDecryption(decryptionClazz = B.class)
  @PostMapping("/test-Aalgorithm")
  public String testAalgorithm(@RequestBody String str) {

    System.out.println("演示使用自定义解密算法A:" + str);

    return "演示使用自定义解密算法A";
  }

  /** 演示使用自定义解密算法B 返回结果 */
  @RequestBodyDecryption(decryptionClazz = B.class)
  @PostMapping("/test-Balgorithm")
  public String testBalgorithm(@RequestBody String str) {

    return "演示使用自定义解密算法B:" + str;
  }
}
