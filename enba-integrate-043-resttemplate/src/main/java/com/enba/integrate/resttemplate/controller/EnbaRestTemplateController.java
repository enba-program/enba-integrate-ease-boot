package com.enba.integrate.resttemplate.controller;

import com.enba.integrate.resttemplate.service.ExternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enba-rest-template")
public class EnbaRestTemplateController {

  private final ExternalService externalService;

  @Autowired
  public EnbaRestTemplateController(ExternalService externalService) {
    this.externalService = externalService;
  }

  @GetMapping("/external-resource")
  public String getExternalResource(@RequestParam("url") String url) {
    return externalService.fetchExternalResource(url);
  }
}
