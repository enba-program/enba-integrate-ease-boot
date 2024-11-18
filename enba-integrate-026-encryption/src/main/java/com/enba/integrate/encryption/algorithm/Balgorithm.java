package com.enba.integrate.encryption.algorithm;

import com.enba.integrate.encryption.annotation.ResponseBodyEncryptionAlgorithm;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

/** 自定义加密算法 Balgorithm */
@Component
public class Balgorithm implements ResponseBodyEncryptionAlgorithm {

  @Override
  public Object encrypt(Object result, HttpHeaders requestHeaders, HttpHeaders responseHeaders) {
    // TODO 自定义加密算法

    return "返回加密之后的结果B";
  }
}
