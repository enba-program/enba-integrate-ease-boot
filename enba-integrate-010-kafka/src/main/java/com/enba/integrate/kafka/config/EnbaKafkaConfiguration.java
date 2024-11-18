package com.enba.integrate.kafka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ConsumerRecordRecoverer;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.ErrorHandler;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.util.backoff.BackOff;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class EnbaKafkaConfiguration {

  /**
   * 配置Kafka错误处理策略 本方法旨在统一配置Kafka消费过程中遇到无法处理的消息时的错误处理策略 错误处理策略包括尝试重新处理消息、跳转到当前offset以及将消息发布到死信队列
   *
   * @param template KafkaTemplate实例，用于将无法处理的消息发送到死信队列
   * @return 返回一个ErrorHandler实例，用于处理Kafka消费过程中出现的错误
   */
  @Bean
  @Primary
  public ErrorHandler kafkaErrorHandler(KafkaTemplate<?, ?> template) {
    // 创建一个ConsumerRecordRecoverer实例，用于恢复无法处理的消费者记录
    // 使用DeadLetterPublishingRecoverer实现，将无法处理的消息重新发送到配置的死信主题
    ConsumerRecordRecoverer recoverer = new DeadLetterPublishingRecoverer(template);

    // 创建一个BackOff实例，定义重试的策略
    // 使用FixedBackOff实现，设置固定的重试间隔时间和最大重试次数
    BackOff backOff = new FixedBackOff(10 * 1000L, 3L);

    // 返回一个SeekToCurrentErrorHandler实例，用于处理无法处理的消费者记录
    // 在处理失败时，会根据设置的重试策略进行重试，超过重试次数后跳转到当前offset
    // 并将无法处理的消息委托给ConsumerRecordRecoverer进行恢复处理
    return new SeekToCurrentErrorHandler(recoverer, backOff);
  }

}
