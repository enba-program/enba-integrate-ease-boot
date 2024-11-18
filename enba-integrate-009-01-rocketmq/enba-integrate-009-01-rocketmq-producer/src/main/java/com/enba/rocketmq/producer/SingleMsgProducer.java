package com.enba.rocketmq.producer;

import com.enba.rocketmq.constants.msg.EnbaUser;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class SingleMsgProducer extends BaseProducer {

  // 单个消息同步推送
  public void sendMessage(String topic, EnbaUser message) {
    rocketMQTemplate.convertAndSend(topic, message);
  }

  //  单个消息同步推送,带消息key，区别于message id (Message Key：用户自定义，用于业务标识和消息过滤。Message ID：系统自动生成，用于唯一标识一条消息，主要用于消息的追踪和状态查询)
  public void sendMessageWithMessageKey(String topic, EnbaUser msg) {
    Message<EnbaUser> message = MessageBuilder.withPayload(msg)
        // Message Key 主要用于消息的业务标识和消息过滤。它是一种可选的、用户自定义的字段，通常用来携带业务上的一些关键信息
        .setHeader(RocketMQHeaders.KEYS, msg.getId())
        .build();
    rocketMQTemplate.convertAndSend(topic, message);
  }


  //单个消息异步推送
  public void sendMessageAsync(String topic, EnbaUser message) {
    rocketMQTemplate.asyncSend(topic, message, null);
  }

  //单个消息异步推送加上回调
  public void sendMessageAsyncSendCallback(String topic, EnbaUser message, SendCallback callback) {
    rocketMQTemplate.asyncSend(topic, message, callback);
  }

}
