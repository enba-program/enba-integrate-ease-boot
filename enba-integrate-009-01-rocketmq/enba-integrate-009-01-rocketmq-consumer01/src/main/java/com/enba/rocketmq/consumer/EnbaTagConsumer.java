package com.enba.rocketmq.consumer;

import com.alibaba.fastjson.JSON;
import com.enba.rocketmq.constants.RocketMqTopicConstant;
import com.enba.rocketmq.constants.msg.EnbaUser;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * tag消息
 */
@Component
@RocketMQMessageListener(
    topic = RocketMqTopicConstant.ENBA_TAG_MSG_TOPIC,
    selectorExpression = "tag1",
    consumerGroup = "enba-consumer-group-" + RocketMqTopicConstant.ENBA_TAG_MSG_TOPIC,
    messageModel = MessageModel.BROADCASTING
)
public class EnbaTagConsumer implements RocketMQListener<EnbaUser> {

  Logger log = LoggerFactory.getLogger(EnbaTagConsumer.class);

  // 定义方法消费消息
  @Override
  public void onMessage(EnbaUser enbaUser) {

    log.info("EnbaTagConsumer.onMessage###{}", JSON.toJSONString(enbaUser));
  }
}
