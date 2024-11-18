package com.enba.rocketmq.consumer;

import com.alibaba.fastjson.JSON;
import com.enba.rocketmq.constants.RocketMqTopicConstant;
import com.enba.rocketmq.msg.EnbaUser;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(
    topic = RocketMqTopicConstant.TOPIC_TEST,
    consumerGroup = "enba-consumer-group-" + RocketMqTopicConstant.TOPIC_TEST
)
public class EnbaConsumer implements RocketMQListener<EnbaUser> {

  Logger log = LoggerFactory.getLogger(EnbaConsumer.class);

  // 定义方法消费消息
  @Override
  public void onMessage(EnbaUser enbaUser) {

    log.info("EnbaConsumer.onMessage###{}", JSON.toJSONString(enbaUser));
  }
}
