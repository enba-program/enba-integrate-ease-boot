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
 * 重试消息
 */
@Component
@RocketMQMessageListener(
    topic = RocketMqTopicConstant.ENBA_RETRY_MSG_TOPIC,
    consumerGroup = "enba-consumer-group-" + RocketMqTopicConstant.ENBA_RETRY_MSG_TOPIC
)
public class EnbaRetryConsumer implements RocketMQListener<EnbaUser> {

  Logger log = LoggerFactory.getLogger(EnbaRetryConsumer.class);

  // 定义方法消费消息
  @Override
  public void onMessage(EnbaUser enbaUser) {

    // 抛出异常，broker默认进行重试，作为消息消费者，这里要注意做幂等处理，比如这里是扣款操作，不能因为异常，重试了多次，导致扣款多次
    // 幂等操作可以根据messageId做幂等 或者业务对象 “enbaUser” 唯一标识

    log.info("EnbaRetryConsumer.onMessage###{}", JSON.toJSONString(enbaUser));
    throw new RuntimeException("演示消费者抛出异常，broker推送消息场景");
  }
}
