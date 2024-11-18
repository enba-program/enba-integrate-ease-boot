package com.enba.integrate.redisson.controller;

import com.enba.integrate.redisson.service.MyService;
import javax.annotation.Resource;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enba-redisson")
public class EnbaRedissonController {

  @Autowired
  private MyService myService;

  @GetMapping("/process")
  public String process() {
    myService.process();

    return "tryLock(long waitTime, long leaseTime, TimeUnit unit)";
  }

  @RequestMapping("/hello")
  public String hello() {
    return "hello world";
  }
}
