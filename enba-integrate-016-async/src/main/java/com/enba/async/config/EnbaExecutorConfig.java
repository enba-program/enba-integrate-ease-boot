package com.enba.async.config;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 自定义线程池
 */
@Configuration
@EnableAsync
public class EnbaExecutorConfig {

  Logger log = LoggerFactory.getLogger(getClass());


  /**
   * 核心线程数
   */
  private static final int CORE_POOL_SIZE = 10;

  /**
   * 最大线程数，缓冲队列满了之后才会申请超过核心线程数的线程
   */
  private static final int MAX_POOL_SIZE = 20;


  /**
   * 设置线程的最大空闲时间，超过了核心线程数之外的线程，在空闲时间到达之后会被销毁
   */
  private static final int KEEP_ALIVE_SECONDS = 10;

  /**
   * 任务阻塞队列
   */
  private static final int QUEUE_CAPACITY = 50;


  /**
   * 设置线程名字的前缀，设置好了之后可以方便我们定位处理任务所在的线程池
   */
  private static final String NAME_PREFIX = "enba-thread-prefix";

  @Bean(name = "enbaExecutor")
  public Executor enbaExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(CORE_POOL_SIZE);
    executor.setMaxPoolSize(MAX_POOL_SIZE);
    executor.setKeepAliveSeconds(KEEP_ALIVE_SECONDS);
    executor.setQueueCapacity(QUEUE_CAPACITY);
    executor.setThreadNamePrefix(NAME_PREFIX);
    executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    executor.initialize();
    return executor;
  }

}


