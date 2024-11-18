package com.enba.integrate.swagger.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enba-swagger")
@Api(tags = "恩爸编程接口")
public class EnbaSwaggerController {

  @ApiOperation(value = "测试接口", notes = "测试接口")
  @GetMapping("/hello")
  public String hello() {
    return "hello world";
  }
}
