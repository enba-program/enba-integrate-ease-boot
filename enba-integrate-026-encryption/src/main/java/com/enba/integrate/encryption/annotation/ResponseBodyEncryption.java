package com.enba.integrate.encryption.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({TYPE, METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ResponseBodyEncryption {

  /** 是否开启加密 */
  boolean encryption() default true;

  /** 加密策略 */
  Class<? extends ResponseBodyEncryptionAlgorithm> encryptionClazz() default
      ResponseBodyEncryptionAlgorithm.class;
}
