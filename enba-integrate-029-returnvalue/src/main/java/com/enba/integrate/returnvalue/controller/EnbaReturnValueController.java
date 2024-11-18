package com.enba.integrate.returnvalue.controller;

import com.enba.integrate.returnvalue.Result;
import com.enba.integrate.returnvalue.annotation.ApiSystemResponseData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enba-returnvalue")
// 这个注解注释了看下效果
@ApiSystemResponseData(resultClass = Result.class)
public class EnbaReturnValueController {

  // 生成一个测试接口
  @RequestMapping("/test")
  public String test() {
    return "测试返回值";
  }
}
