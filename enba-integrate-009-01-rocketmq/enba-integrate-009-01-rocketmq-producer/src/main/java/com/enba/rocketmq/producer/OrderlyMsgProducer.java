package com.enba.rocketmq.producer;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.stereotype.Component;

/**
 * <p>普通顺序消息:生产者将关联的消息发送相同的消息队列</p>
 * <p>严格顺序消息:生产者将关联消息发送到相同的消息队列，并且消费者也顺序消费（一般只启动一个消费者服务，单线程消费消息）</p>
 * <p>顺序消息是云消息队列 RocketMQ 版提供的一种对消息发送和消费顺序有严格要求的消息。对于一个指定的Topic，消息严格按照先进先出（FIFO）的原则进行消息发布和消费，即先发布的消息先消费，后发布的消息后消费。</p>
 * <p>参考地址：https://www.alibabacloud.com/help/zh/apsaramq-for-rocketmq/cloud-message-queue-rocketmq-4-x-series/developer-reference/ordered-messages?spm=a2c63.p38356.0.0.2db61aa3hMMH7m</p>
 */
@Component
public class OrderlyMsgProducer extends BaseProducer {


  /**
   * 同步发送顺序消息
   *
   * @param destination formats: `topicName:tags`
   * @param payload     the Object to use as payload
   * @param hashKey     use this key to select queue. for example: orderId, productId ...
   * @return {@link SendResult}
   */
  public SendResult syncSendOrderly(String destination, Object payload, String hashKey) {
    SendResult sendResult = rocketMQTemplate.syncSendOrderly(destination, payload, hashKey);

    log.info("OrderlyMsgProducer.syncSendOrderly###:{}", JSON.toJSONString(sendResult));
    return sendResult;
  }

}
