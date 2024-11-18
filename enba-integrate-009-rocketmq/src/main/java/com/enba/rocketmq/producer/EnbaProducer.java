package com.enba.rocketmq.producer;

import com.enba.rocketmq.msg.EnbaUser;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnbaProducer {

  @Autowired
  private RocketMQTemplate rocketMQTemplate;

  /**
   * 发送消息到指定的主题
   *
   * @param topic   主题名称，用于确定消息的接收者
   * @param message 消息内容，包含需要发送的信息
   *                <p>
   *                为什么使用RocketMQ模板发送消息： 1. RocketMQ是阿里云的分布式消息中间件，可以处理高并发和大规模消息传递 2.
   *                通过模板方式简化了消息的发送过程，无需手动管理消息的序列化和发送细节
   */
  public void sendMessage(String topic, EnbaUser message) {
    rocketMQTemplate.convertAndSend(topic, message);
  }




}
