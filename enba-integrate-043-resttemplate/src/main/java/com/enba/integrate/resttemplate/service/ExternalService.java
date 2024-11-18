package com.enba.integrate.resttemplate.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalService {

  // @Resource(name = "advancedRestTemplate") 这个是高级的
  @Resource(name = "defaultRestTemplate")
  private RestTemplate defaultRestTemplate;

  public String fetchExternalResource(String url) {
    try {
      return defaultRestTemplate.getForObject(url, String.class);
    } catch (Exception e) {
      // 处理异常
      e.printStackTrace();
      return "Error fetching resource";
    }
  }
}
