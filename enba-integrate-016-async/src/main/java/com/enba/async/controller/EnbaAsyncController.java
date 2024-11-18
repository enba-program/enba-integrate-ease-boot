package com.enba.async.controller;

import com.enba.async.springasync.EnbaSpringAsync;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enba-async")
public class EnbaAsyncController {

  @Autowired
  private EnbaSpringAsync enbaSpringAsync;

  @GetMapping("/async")
  public String asyncMethod() {
    enbaSpringAsync.asyncMethod();
    return "success";
  }

  @GetMapping("/async2")
  public String asyncMethod2() {
    enbaSpringAsync.asyncMethod2();
    return "success2";
  }


}
