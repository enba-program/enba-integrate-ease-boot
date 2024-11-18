package com.enba.scheduler.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyJob {

  /**
   * <p>在Spring Boot中使用Spring框架内建的@Scheduled注解来实现定时任务是一种简单且高效的方法。SpringBoot自动提供了对@Scheduled的支持，无需额外配置即可使用。</p>
   * <p>如果服务集群部署多个实例，可以采用加分布式锁的方式，防止多个实例同时执行定时任务。</p>
   */

  // 每隔5秒执行一次
  @Scheduled(fixedRate = 5000)
  public void fixedRateTask() {
    // 分布式环境下，可以先去获取锁，拿到锁之后再执行下面业务逻辑
    System.out.println("Fixed rate task executed at " + new java.util.Date());
  }

  // 使用Cron表达式，每10秒执行一次
  @Scheduled(cron = "0/10 * * * * ?")
  public void cronTask() {
    System.out.println("Cron task executed at " + new java.util.Date());
  }

}
