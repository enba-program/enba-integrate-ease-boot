package com.enba.rocketmq.consumer;

import com.alibaba.fastjson.JSON;
import com.enba.rocketmq.constants.RocketMqTopicConstant;
import com.enba.rocketmq.constants.msg.EnbaUser;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 事务消息
 */
@Component
@RocketMQMessageListener(
    topic = RocketMqTopicConstant.ENBA_TRANSACTION_MSG_TOPIC,
    consumerGroup = "enba-consumer-group-" + RocketMqTopicConstant.ENBA_TRANSACTION_MSG_TOPIC
)
public class EnbaTransactionConsumer implements RocketMQListener<EnbaUser> {

  Logger log = LoggerFactory.getLogger(EnbaTransactionConsumer.class);

  // 定义方法消费消息
  @Override
  public void onMessage(EnbaUser enbaUser) {

    log.info("EnbaTransactionConsumer.onMessage###{}", JSON.toJSONString(enbaUser));
  }
}
