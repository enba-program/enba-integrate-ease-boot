package com.enba.rabbitmq.producer;

import com.enba.rabbitmq.common.EnbaRabbitDefine;
import java.util.UUID;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EnbaProducer {

  @Autowired
  private RabbitTemplate rabbitTemplate;

  /**
   * 直接模式发送消息
   *
   * @param message 发送的信息
   */
  public void sendDirect(Object message) {
    rabbitTemplate.convertAndSend(EnbaRabbitDefine.DIRECT_QUEUE, message, this.getCorrelationData());
  }

  /**
   * 分裂模式发送消息
   *
   * @param message 发送的信息
   */
  public void sendFanout(Object message) {
    rabbitTemplate.convertAndSend(EnbaRabbitDefine.FANOUT_EXCHANGE, "", message);
  }


  /**
   * 主题模式发送消息
   *
   * @param message    发送的信息
   * @param routingKey 匹配的队列名
   */
  public void sendTopic(Object message, String routingKey) {
    rabbitTemplate.convertAndSend(EnbaRabbitDefine.TOPIC_EXCHANGE, routingKey, message);
  }


  /**
   * 发送延迟消息
   *
   * @param message 发送的信息
   * @param delay   延迟时间
   */
  public void sendDelay(String message, int delay) {
    rabbitTemplate.convertAndSend(EnbaRabbitDefine.DELAY_EXCHANGE, "delay", message, msg -> {
      msg.getMessageProperties().setDelay(delay);
      return msg;
    });
  }

  /**
   * 发送临时消息
   */
  public void sendAndExpire(Object message) {
    rabbitTemplate.convertAndSend(EnbaRabbitDefine.TTL_QUEUE, message, this.getCorrelationData());
  }

  /**
   * 生成消息标识
   */
  private CorrelationData getCorrelationData() {
    String messageId = UUID.randomUUID().toString();
    CorrelationData correlationData = new CorrelationData();
    correlationData.setId(messageId);
    return correlationData;
  }

}
