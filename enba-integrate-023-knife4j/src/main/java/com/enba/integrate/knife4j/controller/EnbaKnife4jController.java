package com.enba.integrate.knife4j.controller;

import com.enba.integrate.knife4j.req.TestReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "示例")
@RestController
@RequestMapping("/knife4j-api")
public class EnbaKnife4jController {

  @Value("${app.version}")
  private String version;

  @ApiOperation("测试get请求")
  @GetMapping("/get")
  public String get() {

    return "测试get请求：" + version;
  }

  @ApiOperation("测试post请求")
  @PostMapping("/post")
  public String post(@RequestBody TestReq req) {

    return "测试post请求：" + req.getName();
  }



}
