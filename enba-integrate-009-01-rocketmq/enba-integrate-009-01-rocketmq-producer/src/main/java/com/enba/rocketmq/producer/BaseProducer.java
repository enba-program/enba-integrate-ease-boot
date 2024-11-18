package com.enba.rocketmq.producer;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseProducer {

  Logger log = LoggerFactory.getLogger(this.getClass());

  @Autowired
  protected RocketMQTemplate rocketMQTemplate;
}
