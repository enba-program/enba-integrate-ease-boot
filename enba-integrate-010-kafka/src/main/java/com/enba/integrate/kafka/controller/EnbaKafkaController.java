package com.enba.integrate.kafka.controller;

import com.enba.integrate.kafka.producer.Enba01Producer;
import com.enba.integrate.kafka.producer.Enba02Producer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/es")
public class EnbaKafkaController {

  Logger log = LoggerFactory.getLogger(getClass());

  @Autowired
  private Enba01Producer producer;


  /**
   * 测试同步发送消息的方法 该方法通过同步方式发送消息，并等待消息的发送结果 使用@GetMapping注解指定访问路径为"/testSyncSend"，且仅响应GET请求
   * 调用此方法可能会抛出ExecutionException和InterruptedException异常
   */
  @GetMapping("testSyncSend")
  public void testSyncSend() throws ExecutionException, InterruptedException {
    // 根据当前时间戳生成唯一的消息ID
    int id = (int) (System.currentTimeMillis() / 1000);

    // 调用生产者同步发送消息，并获取发送结果
    SendResult result = producer.syncSend(id);

    // 记录日志，包括发送消息的ID和发送结果
    log.info("[testSyncSend][发送编号：[{}] 发送结果：[{}]]", id, result);

    // 使用CountDownLatch使当前线程阻塞等待，确保消息被消费
    // 这里创建了一个计数为1的CountDownLatch实例，调用await方法会使当前线程阻塞，直到其他线程调用该CountDownLatch的countDown方法
    new CountDownLatch(1).await();
  }


  /**
   * 测试异步发送消息 该方法演示了如何使用异步方式发送消息，并记录发送结果 为了确保消息发送后有足够的时间用于后续处理或测试观察，该方法会进行短暂的阻塞
   *
   * @throws InterruptedException 如果线程在等待过程中被中断
   */
  @GetMapping("testASyncSend")
  public void testASyncSend() throws InterruptedException {
    // 生成一个基于当前时间戳的唯一ID，作为消息的标识
    int id = (int) (System.currentTimeMillis() / 1000);

    // 异步发送消息，并注册回调处理发送结果
    producer.asyncSend(id).addCallback(new ListenableFutureCallback<SendResult<Object, Object>>() {

      // 发送失败时调用，记录错误日志
      @Override
      public void onFailure(Throwable e) {
        log.info("[testASyncSend][发送编号：[{}] 发送异常]]", id, e);
      }

      // 发送成功时调用，记录成功日志和发送结果
      @Override
      public void onSuccess(SendResult<Object, Object> result) {
        log.info("[testASyncSend][发送编号：[{}] 发送成功，结果为：[{}]]", id, result);
      }

    });

    // 阻塞等待，保证消费
    // 这里使用CountDownLatch进行阻塞，确保主线程等待直到消息发送后的处理完成
    // 实际应用中，根据具体需求决定是否需要这样的阻塞等待
    new CountDownLatch(1).await();
  }

  // =========================Enba02Producer=========================

  @Autowired
  private Enba02Producer producer2;


  /**
   * 测试同步发送消息到RocketMQ的第二个方法
   * 此方法演示了如何使用同步方式发送消息，并记录发送结果
   * 注意：此方法包含阻塞代码，确保消息消费完成后再返回
   *
   * @throws ExecutionException 如果执行发送操作时发生错误
   * @throws InterruptedException 如果线程在等待过程中被中断
   */
  @GetMapping("testSyncSend2")
  public void testSyncSend2() throws ExecutionException, InterruptedException {
    // 生成一个唯一的ID，基于当前时间戳，用于标识此次消息发送
    int id = (int) (System.currentTimeMillis() / 1000);

    // 调用生产者2的同步发送方法，发送消息并获取发送结果
    SendResult result = producer2.syncSend(id);

    // 记录消息发送日志，包括发送的唯一ID和发送结果
    log.info("[testSyncSend][发送编号：[{}] 发送结果：[{}]]", id, result);

    // 阻塞等待，保证消费
    // 通过CountDownLatch使当前线程等待，确保消息被消费
    // 这里使用1表示只有一个操作需要完成才能继续
    new CountDownLatch(1).await();
  }


  /**
   * 使用同步方式发送消息测试，每次发送后等待10秒，共发送100次
   * 此方法演示了在同步发送消息的场景下，如何等待消息消费的示例
   * 通过CountDownLatch保证所有消息消费完成后再结束方法
   */
  @GetMapping("testSyncSendX2")
  public void testSyncSendX2() throws ExecutionException, InterruptedException {
    for (int i = 0; i < 100; i++) {
      // 以当前时间戳作为消息ID，确保每次发送的消息ID唯一
      int id = (int) (System.currentTimeMillis() / 1000);
      // 调用生产者2的同步发送方法，发送消息，并获取发送结果
      SendResult result = producer2.syncSend(id);
      // 日志记录发送消息的ID和发送结果
      log.info("[testSyncSend][发送编号：[{}] 发送结果：[{}]]", id, result);
      // 每次发送后等待10秒，以便观察和调试
      Thread.sleep(10 * 1000L);
    }

    // 阻塞等待，保证消费
    new CountDownLatch(1).await();
  }



}
