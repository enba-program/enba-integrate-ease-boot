package com.enba.integrate.kafka.consumer;

import com.enba.integrate.kafka.message.Enba01Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Enba01Consumer {

  Logger log = LoggerFactory.getLogger(getClass());

  @KafkaListener(topics = Enba01Message.TOPIC,
      groupId = "enba01-consumer-group-" + Enba01Message.TOPIC)
  public void onMessage(Enba01Message message) {

    log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
  }

}
