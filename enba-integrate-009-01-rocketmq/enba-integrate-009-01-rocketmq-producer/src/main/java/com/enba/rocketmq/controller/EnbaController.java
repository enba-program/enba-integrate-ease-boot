package com.enba.rocketmq.controller;

import com.enba.rocketmq.constants.RocketMqTopicConstant;
import com.enba.rocketmq.constants.msg.EnbaUser;
import com.enba.rocketmq.producer.BatchMsgProducer;
import com.enba.rocketmq.producer.DelayMsgProducer;
import com.enba.rocketmq.producer.OrderlyMsgProducer;
import com.enba.rocketmq.producer.RetryMsgProducer;
import com.enba.rocketmq.producer.SingleMsgProducer;
import com.enba.rocketmq.producer.TagMsgProducer;
import com.enba.rocketmq.producer.TransactionMsgProducer;
import com.google.common.collect.Lists;
import java.util.Date;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enbaUser")
public class EnbaController {

  private final SingleMsgProducer singleMsgProducer;
  private final BatchMsgProducer batchMsgProducer;
  private final DelayMsgProducer delayMsgProducer;
  private final RetryMsgProducer retryMsgProducer;
  private final OrderlyMsgProducer orderlyMsgProducer;
  private final TagMsgProducer tagMsgProducer;
  private final TransactionMsgProducer transactionMsgProducer;

  public EnbaController(
      SingleMsgProducer singleMsgProducer, BatchMsgProducer batchMsgProducer,
      DelayMsgProducer delayMsgProducer, RetryMsgProducer retryMsgProducer,
      OrderlyMsgProducer orderlyMsgProducer, TagMsgProducer tagMsgProducer,
      TransactionMsgProducer transactionMsgProducer) {
    this.singleMsgProducer = singleMsgProducer;
    this.batchMsgProducer = batchMsgProducer;
    this.delayMsgProducer = delayMsgProducer;
    this.retryMsgProducer = retryMsgProducer;
    this.orderlyMsgProducer = orderlyMsgProducer;
    this.tagMsgProducer = tagMsgProducer;
    this.transactionMsgProducer = transactionMsgProducer;
  }

  /*推送单个消息*/

  /**
   * 获取用户信息
   *
   * @return EnbaUser
   */
  private EnbaUser getEnbaUser() {
    EnbaUser enbaUser = new EnbaUser();
    enbaUser.setId(111L);
    enbaUser.setUserName("恩爸编程");
    enbaUser.setAge(20);
    enbaUser.setCreateTime(new Date());
    enbaUser.setUpdateTime(new Date());
    enbaUser.setDeleted(0);
    return enbaUser;
  }

  /**
   * 单个消息同步推送
   *
   * @return s
   */

  @GetMapping("/sendMessage")
  public String sendMessage() {
    EnbaUser enbaUser = getEnbaUser();
    enbaUser.setRemark("sendMessage");

    singleMsgProducer.sendMessage(RocketMqTopicConstant.ENBA_SINGLE_MSG_TOPIC, enbaUser);

    return "rocketmq sendMessage success...";
  }

  @GetMapping("/sendMessageWithMessageKey")
  public String sendMessageWithMessageKey() {
    EnbaUser enbaUser = getEnbaUser();
    enbaUser.setRemark("sendMessageWithMessageKey");

    singleMsgProducer
        .sendMessageWithMessageKey(RocketMqTopicConstant.ENBA_SINGLE_MSG_TOPIC, enbaUser);

    return "rocketmq sendMessageWithMessageKey success...";
  }

  /**
   * 单个消息异步推送
   *
   * @return s
   */
  @GetMapping("/sendMessageAsync")
  public String sendMessageAsync() {
    EnbaUser enbaUser = getEnbaUser();
    enbaUser.setRemark("sendMessageAsync");

    singleMsgProducer.sendMessageAsync(RocketMqTopicConstant.ENBA_SINGLE_MSG_TOPIC, enbaUser);

    return "rocketmq sendMessageAsync success...";
  }

  /**
   * 单个消息异步推送加上回调
   *
   * @return s
   */
  @GetMapping("/sendMessageAsyncSendCallback")
  public String sendMessageAsyncSendCallback() {
    EnbaUser enbaUser = getEnbaUser();
    enbaUser.setRemark("sendMessageAsyncSendCallback");

    singleMsgProducer.sendMessageAsyncSendCallback(RocketMqTopicConstant.ENBA_SINGLE_MSG_TOPIC,
        enbaUser, new SendCallback() {
          @Override
          public void onSuccess(SendResult sendResult) {
            // 成功回调  do something
            System.out.println("发送成功");
          }

          @Override
          public void onException(Throwable throwable) {
            // 失败回调  do something，如：消息落库，异常通知等
            System.out.println("发送失败");
          }
        });

    return "rocketmq sendMessageAsyncSendCallback success...";
  }

  /* 批量推送操作*/

  /**
   * 定义方法，同步，批量消息推送
   *
   * @return s
   */
  @GetMapping("/batchSendMessage")
  public String batchSendMessage() {
    EnbaUser enbaUser = getEnbaUser();
    enbaUser.setRemark("batchSendMessage");

    batchMsgProducer.batchSendMessage(RocketMqTopicConstant.ENBA_BATCH_MSG_TOPIC,
        Lists.newArrayList(enbaUser, enbaUser));

    return "rocketmq batchSendMessage success...";
  }

  /**
   * 定义方法，同步，批量消息推送,超时时间
   *
   * @return s
   */
  @GetMapping("/batchSendMessageTimeout")
  public String batchSendMessageTimeout() {
    EnbaUser enbaUser = getEnbaUser();
    enbaUser.setRemark("batchSendMessageTimeout");

    batchMsgProducer.batchSendMessageTimeout(RocketMqTopicConstant.ENBA_BATCH_MSG_TOPIC,
        Lists.newArrayList(enbaUser, enbaUser), 3000L);

    return "rocketmq batchSendMessageTimeout success...";
  }

  /**
   * 定义方法，异步，批量消息推送
   *
   * @return s
   */
  @GetMapping("/batchSendMessageAsync")
  public String batchSendMessageAsync() {
    EnbaUser enbaUser = getEnbaUser();
    enbaUser.setRemark("batchSendMessageAsync");

    batchMsgProducer.batchSendMessageAsync(RocketMqTopicConstant.ENBA_BATCH_MSG_TOPIC,
        Lists.newArrayList(enbaUser, enbaUser), new SendCallback() {
          @Override
          public void onSuccess(SendResult sendResult) {
            // 成功回调  do something
            System.out.println("发送成功");
          }

          @Override
          public void onException(Throwable throwable) {
            // 失败回调  do something，如：消息落库，异常通知等
            System.out.println("发送失败");
          }
        });

    return "rocketmq batchSendMessageAsync success...";
  }

  /**
   * 定义方法，异步，批量消息推送,超时时间
   *
   * @return s
   */
  @GetMapping("/batchSendMessageAsyncTimeout")
  public String batchSendMessageAsyncTimeout() {
    EnbaUser enbaUser = getEnbaUser();
    enbaUser.setRemark("batchSendMessageAsyncTimeout");

    batchMsgProducer.batchSendMessageAsyncTimeout(RocketMqTopicConstant.ENBA_BATCH_MSG_TOPIC,
        Lists.newArrayList(enbaUser, enbaUser), new SendCallback() {
          @Override
          public void onSuccess(SendResult sendResult) {
            // 成功回调  do something
            System.out.println("发送成功");
          }

          @Override
          public void onException(Throwable throwable) {
            // 失败回调  do something，如：消息落库，异常通知等
            System.out.println("发送失败");
          }
        }, 3000L);

    return "rocketmq batchSendMessageAsyncTimeout success...";
  }

  /* 延迟消息,定时消息*/

  /**
   * 同步发送延迟消息，设置消息延迟20秒发送
   *
   * @return 返回发送结果信息
   */
  @GetMapping("/syncSendDelayTimeSeconds")
  public String syncSendDelayTimeSeconds() {
    EnbaUser enbaUser = getEnbaUser();
    enbaUser.setRemark("syncSendDelayTimeSeconds");

    delayMsgProducer
        .syncSendDelayTimeSeconds(RocketMqTopicConstant.ENBA_DELAY_MSG_TOPIC, enbaUser, 20);

    return "rocketmq syncSendDelayTimeSeconds success...";
  }


  /**
   * 异步发送延迟消息
   *
   * @return s
   */
  @GetMapping("/asyncSendDelay")
  public String asyncSendDelay() {
    EnbaUser enbaUser = getEnbaUser();
    enbaUser.setRemark("asyncSendDelay");

    delayMsgProducer
        .asyncSendDelay(RocketMqTopicConstant.ENBA_DELAY_MSG_TOPIC, new Message<EnbaUser>() {
          @Override
          public EnbaUser getPayload() {
            return enbaUser;
          }

          @Override
          public MessageHeaders getHeaders() {
            return null;
          }
        }, new SendCallback() {
          @Override
          public void onSuccess(SendResult sendResult) {
            // 成功回调  do something
            System.out.println("发送成功");
          }

          @Override
          public void onException(Throwable throwable) {
            // 失败回调  do something，如：消息落库，异常通知等
            System.out.println("发送失败");
          }
        }, 3000L, 2);

    return "rocketmq asyncSendDelay success...";
  }

  /* 重试消息*/

  /**
   * 同步发送消息   演示重试消息（消费者消费消息异常情况） 默认情况下，重试16次
   *
   * @return s
   */
  @GetMapping("/syncSendMessageRetry")
  public String syncSendMessageRetry() {
    EnbaUser enbaUser = getEnbaUser();
    enbaUser.setRemark("syncSendMessageRetry");

    retryMsgProducer.syncSendMessageRetry(RocketMqTopicConstant.ENBA_RETRY_MSG_TOPIC, enbaUser);

    return "rocketmq syncSendMessageRetry success...";
  }

  /* 普通顺序消息 */

  /**
   * 同步发送顺序消息
   *
   * @return s
   */
  @GetMapping("/syncSendOrderly")
  public String syncSendOrderly() {
    EnbaUser enbaUser = getEnbaUser();
    enbaUser.setRemark("syncSendOrderly");

    String hashKey = enbaUser.getId() + "";

    orderlyMsgProducer.syncSendOrderly(RocketMqTopicConstant.ENBA_ORDERLY_MSG_TOPIC, enbaUser,
        hashKey);

    return "rocketmq syncSendOrderly success...";
  }

  /* tag消息 */

  /**
   * 同步发送tag消息
   *
   * @return s
   */
  @GetMapping("/syncSendMessageTag")
  public String syncSendMessageTag() {
    EnbaUser enbaUser = getEnbaUser();
    enbaUser.setRemark("syncSendMessageTag");

    String tag = "tag1";

    tagMsgProducer
        .syncSendMessageTag(RocketMqTopicConstant.ENBA_TAG_MSG_TOPIC + ":" + tag, enbaUser);

    return "rocketmq syncSendMessageTag success...";
  }

  /* 事务消息 */

  /**
   * 发送事务消息
   *
   * @return s
   */
  @GetMapping("/sendTransactionMessage")
  public String sendTransactionMessage() {
    EnbaUser enbaUser = getEnbaUser();
    enbaUser.setRemark("sendTransactionMessage");

    transactionMsgProducer.sendTransactionMessage(enbaUser);

    return "rocketmq sendTransactionMessage success...";
  }

}
