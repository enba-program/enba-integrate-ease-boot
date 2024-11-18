package com.enba.integrate.log.controller;

import com.enba.integrate.log.annotation.Log;
import com.enba.integrate.log.param.TestParam;
import com.enba.integrate.log.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enba-log")
public class EnbaLogController {

  @Autowired private ApplicationContext applicationContext;

  @Log(func = "testGet", module = "测试模块")
  @GetMapping("/testGet")
  public void testGet() {

    String[] beanNamesForType = applicationContext.getBeanNamesForType(LogService.class);

    for (int i = 0; i < beanNamesForType.length; i++) {
      System.out.println(beanNamesForType[i]);
    }

    // throw new RuntimeException("测试");
  }

  @Log(func = "testPost", module = "post请求体传参测试")
  @PostMapping("/testPost")
  public String testPost(@RequestBody TestParam param) {
    System.out.println(param);
    return "success";
  }
}
