package com.enba.integrate.jwt.jwt;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/** 密码加密工具类 */
public class PasswordEncoder {

  /**
   * 生成BCryptPasswordEncoder密码
   *
   * @param password 密码
   * @return 加密字符串
   */
  public static String encode(String password) {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    return passwordEncoder.encode(password);
  }

  /**
   * 判断密码是否相同
   *
   * @param rawPassword 真实密码
   * @param encodedPassword 加密后字符
   * @return 结果
   */
  public static boolean matches(String rawPassword, String encodedPassword) {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    return passwordEncoder.matches(rawPassword, encodedPassword);
  }

  public static void main(String[] args) {
    System.out.println(encode("123456"));
  }
}
