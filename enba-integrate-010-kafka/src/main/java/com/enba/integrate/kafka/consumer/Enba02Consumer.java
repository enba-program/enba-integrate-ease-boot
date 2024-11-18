package com.enba.integrate.kafka.consumer;

import com.enba.integrate.kafka.message.Enba02Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Enba02Consumer {

  private Logger log = LoggerFactory.getLogger(getClass());

  @KafkaListener(topics = Enba02Message.TOPIC,
      groupId = "enba02-consumer-group-" + Enba02Message.TOPIC)
  public void onMessage(Enba02Message message) {

    log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    // 模拟消费失败
    throw new RuntimeException("出错了...");
  }

}
