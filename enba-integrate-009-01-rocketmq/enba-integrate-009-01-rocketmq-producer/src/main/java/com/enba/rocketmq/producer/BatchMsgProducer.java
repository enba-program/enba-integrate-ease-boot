package com.enba.rocketmq.producer;

import com.enba.rocketmq.constants.msg.EnbaUser;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.rocketmq.client.producer.SendCallback;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class BatchMsgProducer extends BaseProducer {

  // 定义方法，同步，批量消息推送
  public void batchSendMessage(String topic, Collection<EnbaUser> message) {

    List<Message<EnbaUser>> collect = message.stream().map(e -> {
      return MessageBuilder.withPayload(e).build();
    }).collect(Collectors.toList());

    // 批量消息推送
    rocketMQTemplate.syncSend(topic, collect);
  }

  // 定义方法，同步，批量消息推送,超时时间
  public void batchSendMessageTimeout(String topic, Collection<EnbaUser> message, long timeout) {
    // 批量消息推送
    rocketMQTemplate.syncSend(topic, message.stream().map(e -> {
      return MessageBuilder.withPayload(e).build();
    }).collect(Collectors.toList()), timeout);
  }


  // 定义方法，异步，批量消息推送
  public void batchSendMessageAsync(String topic, Collection<EnbaUser> message,
      SendCallback sendCallback) {
    // 批量消息推送
    rocketMQTemplate.asyncSend(topic, message.stream().map(e -> {
      return MessageBuilder.withPayload(e).build();
    }).collect(Collectors.toList()), sendCallback);
  }

  // 定义方法，异步，批量消息推送,超时时间
  public void batchSendMessageAsyncTimeout(String topic, Collection<EnbaUser> message,
      SendCallback sendCallback, long timeout) {
    // 批量消息推送
    rocketMQTemplate.asyncSend(topic, message.stream().map(e -> {
      return MessageBuilder.withPayload(e).build();
    }).collect(Collectors.toList()), sendCallback, timeout);
  }

}
