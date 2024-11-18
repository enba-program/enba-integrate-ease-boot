package com.enba.rocketmq.constants;

/**
 * 定义主题
 */
public interface RocketMqTopicConstant {

  // topic是对消息的分类

  // 单个消息topic
  String ENBA_SINGLE_MSG_TOPIC = "enba_single_msg_topic";

  // 批量消息topic
  String ENBA_BATCH_MSG_TOPIC = "enba_batch_msg_topic";

  // 延迟消息topic
  String ENBA_DELAY_MSG_TOPIC = "enba_delay_msg_topic";

  // 重试消息topic
  String ENBA_RETRY_MSG_TOPIC = "enba_retry_msg_topic";

  // 顺序消息topic
  String ENBA_ORDERLY_MSG_TOPIC = "enba_orderly_msg_topic";

  // tag消息topic
  String ENBA_TAG_MSG_TOPIC = "enba_tag_msg_topic";

  // 事务消息topic
  String ENBA_TRANSACTION_MSG_TOPIC = "enba_transaction_msg_topic";

}
