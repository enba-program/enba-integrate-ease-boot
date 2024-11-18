package com.enba.integrate.thymeleaf.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/enba-thymeleaf")
public class EnbaThymeleafController {

  @GetMapping("/index")
  public String index() {
    return "index";
  }

  @RequestMapping("/demo")
  public ModelAndView users() {
    Map<String, Object> map = new HashMap<>(1);

    map.put("users", "恩爸编程");
    return new ModelAndView("demo", map);
  }
}
