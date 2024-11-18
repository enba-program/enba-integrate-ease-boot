package com.enba.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/web")
@RestController
public class WebController {

  @GetMapping("/test")
  public String web() {
    return "hello web...";
  }

}
