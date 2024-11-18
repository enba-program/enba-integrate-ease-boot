package com.enba.rocketmq.producer;

import com.enba.rocketmq.constants.msg.EnbaUser;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.stereotype.Component;

/**
 * <p>消息过滤:消息过滤功能指消息生产者向Topic中发送消息时，设置消息属性对消息进行分类，消费者订阅Topic时，根据消息属性设置过滤条件对消息进行过滤，只有符合过滤条件的消息才会被投递到消费端进行消费。消费者订阅Topic时若未设置过滤条件，无论消息发送时是否有设置过滤属性，Topic中的所有消息都将被投递到消费端进行消费。</p>
 */
@Component
public class TagMsgProducer extends BaseProducer {

  /**
   * 同步发送消息 destination包含冒号，冒号后面就是tag，可以理解成消息的两级分类，topic理解为消息的一级分类
   *
   * @param destination formats: `topicame:tags`
   * @param payload     the Object to use as payload
   * @return {@link SendResult}
   */
  public void syncSendMessageTag(String destination, EnbaUser payload) {
    rocketMQTemplate.syncSend(destination, payload);
  }

}
