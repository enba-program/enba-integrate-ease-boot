package com.enba.integrate.encryption.annotation;

import org.springframework.http.HttpHeaders;

public interface ResponseBodyEncryptionAlgorithm {

  Object encrypt(Object result, HttpHeaders requestHeaders, HttpHeaders responseHeaders);
}
