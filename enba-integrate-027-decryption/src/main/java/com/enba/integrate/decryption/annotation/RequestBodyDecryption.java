package com.enba.integrate.decryption.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({TYPE, METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestBodyDecryption {

  /** 是否开启解密 */
  boolean decryption() default true;

  /** 解密策略 */
  Class<? extends RequestBodyDecryptionAlgorithm> decryptionClazz();
}
