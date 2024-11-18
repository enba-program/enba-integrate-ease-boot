package com.enba.integrate.decryption.algorithm;

import com.enba.integrate.decryption.annotation.RequestBodyDecryptionAlgorithm;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

/** 自定义解密算法B */
@Component
public class B implements RequestBodyDecryptionAlgorithm {

  @Override
  public String decrypt(String requestBodyStr, HttpHeaders headers) {
    // TODO 自定义解密算法B
    System.out.println("B算法解密");

    return "B算法解密获得的入参";
  }
}
