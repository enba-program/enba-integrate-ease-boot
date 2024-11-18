package com.enba.scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // 这里是加在了启动类上，也可以加个某个配置类上,结合配置决定是否开启定时任务
public class EnbaSchedulerApplication {

  /**
   * 注意事项
   *
   * <p>确保你的定时任务不会产生死锁或长时间阻塞，因为它们运行在一个有限的线程池中。</p>
   * <p>如果你的应用部署在集群中，可能需要考虑分布式调度的解决方案，以避免多个节点同时执行相同的定时任务。</p>
   */

  public static void main(String[] args) {
    SpringApplication.run(EnbaSchedulerApplication.class, args);
  }

}
