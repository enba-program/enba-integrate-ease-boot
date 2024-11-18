package com.enba.integrate.decryption.algorithm;

import com.enba.integrate.decryption.annotation.RequestBodyDecryptionAlgorithm;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

/** 自定义解密算法A */
@Component
public class A implements RequestBodyDecryptionAlgorithm {

  @Override
  public String decrypt(String requestBodyStr, HttpHeaders headers) {
    // TODO 自定义解密算法A
    System.out.println("A算法解密");

    return "A算法解密获得的入参";
  }
}
