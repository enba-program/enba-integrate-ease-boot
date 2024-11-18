package com.enba.integrate.i18n.controller;

import com.enba.integrate.i18n.content.I18nHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enba-i18n")
public class EnbaI18nController {

  @GetMapping("/text")
  public String test() {
    String msg = I18nHolder.source().getResourceBundle().getString("text");
    return msg;
  }
}
