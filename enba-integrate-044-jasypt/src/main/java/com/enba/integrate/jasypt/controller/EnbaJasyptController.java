package com.enba.integrate.jasypt.controller;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enba-jasypt")
public class EnbaJasyptController {

  @Autowired private StringEncryptor stringEncryptor;

  @GetMapping("/encrypt")
  public String encrypt() {
    String url = stringEncryptor.encrypt("jdbc:mysql://localhost:3306/demo");
    String username = stringEncryptor.encrypt("enba");
    String password = stringEncryptor.encrypt("123456");
    System.out.println("url=" + url);
    System.out.println("username=" + username);
    System.out.println("password=" + password);

    return "加密";
  }

  @GetMapping("/decrypt")
  public String decrypt() {
    String url =
        stringEncryptor.decrypt(
            "AYwMucPmOqGnJFOxSugA2H6otY/KbgnT1TTLCPcgwblTxnGbgGwuodbn8bMnbeZsDXUTNiO3edEm/+i3wOxEgtRaGiaUaQF+6N+wk3b2BYI=");
    String username =
        stringEncryptor.decrypt("X7BhOgHbVvSodJswbfL6v4N+xNNIP4CrnRYWxluauTHqXXjgqgHLmusCa29HU099");
    String password =
        stringEncryptor.decrypt("tij7RPkPEuduP7LlerSWlga4l89RLpWQIwy/HQkexW5TFs0ExChyt/F/1Aawx5T3");
    System.out.println("url=" + url);
    System.out.println("username=" + username);
    System.out.println("password=" + password);

    return "解密";
  }

  /** 虽然application.yml中配置了加密的url、username、password，但是这里还是可以获取到明文 */
  @Value("${spring.datasource.url}")
  private String url;

  @Value("${spring.datasource.username}")
  private String username;

  @Value("${spring.datasource.password}")
  private String password;

  @RequestMapping("/hello")
  public String hello() {
    System.out.println("url==>" + url);
    System.out.println("username==>" + username);
    System.out.println("password==>" + password);
    return "Hello, Enba Jasypt";
  }
}
