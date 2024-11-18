package com.enba.integrate.decryption.annotation;

import org.springframework.http.HttpHeaders;

public interface RequestBodyDecryptionAlgorithm {

  String decrypt(String requestBodyStr, HttpHeaders headers);
}
