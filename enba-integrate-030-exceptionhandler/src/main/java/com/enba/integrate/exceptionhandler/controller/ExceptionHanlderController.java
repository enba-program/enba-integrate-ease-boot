package com.enba.integrate.exceptionhandler.controller;

import com.enba.integrate.exceptionhandler.exception.EnbaBizException;
import java.sql.SQLException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enba-exceptionhandler")
public class ExceptionHanlderController {

  @RequestMapping("/test")
  public String test() {
    throw new RuntimeException("抛出运行时异常");
  }

  @RequestMapping("/test2")
  public String test2() throws SQLException {
    throw new SQLException("抛出检查型异常");
  }

  @RequestMapping("/test3")
  public String test3() throws SQLException {
    throw new EnbaBizException(501, "抛出业务异常", null);
  }
}
