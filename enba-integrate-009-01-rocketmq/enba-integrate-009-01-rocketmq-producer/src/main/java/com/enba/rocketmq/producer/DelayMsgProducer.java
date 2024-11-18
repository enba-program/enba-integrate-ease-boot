package com.enba.rocketmq.producer;

import com.enba.rocketmq.constants.msg.EnbaUser;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * <p>定时消息(延迟消息)，生产者发消息到Broker，消息不会立刻被消费者消费，需要等到特定的时间后才能被消费者消费</p>
 * <p>延时消息：Producer将消息发送到云消息队列 RocketMQ 版服务端，但并不期望立马投递这条消息，而是延迟一定时间后才投递到Consumer进行消费，该消息即延时消息。</p>
 * <p>参考地址：https://www.alibabacloud.com/help/zh/apsaramq-for-rocketmq/cloud-message-queue-rocketmq-4-x-series/developer-reference/scheduled-messages-and-delayed-messages?spm=a2c63.p38356.0.0.6d991aa3OIGvMT</p>
 */
@Component
public class DelayMsgProducer extends BaseProducer {

  /**
   * 5.0版本前 RocketMQ不支持任意的时间精度的延迟，目前预设了18个延迟级别
   * 延迟级别    时间
   * 1          1s
   * 2          5s
   * 3          10s
   * 4          30s
   * 5          1m
   * 6          2m
   * 7          3m
   * 8          4m
   * 9          5m
   * 10         6m
   * 11        	7m
   * 12        	8m
   * 13         9m
   * 14        	10m
   * 15        	20m
   * 16       	30m
   * 17    	    1h
   * 18	        2h
   *
   * 5.0 版本后，延迟消息支持任意的时间精度，通过设置消息的延时时间属性来实现
   */

  /**
   * 同步发送延迟消息
   *
   * @param destination formats: `topicName:tags`
   * @param payload     the Object to use as payload
   * @param delayTime   delay time in seconds for message
   * @return {@link SendResult}
   */
  public SendResult syncSendDelayTimeSeconds(String destination, EnbaUser payload, long delayTime) {
    return rocketMQTemplate.syncSendDelayTimeSeconds(destination, payload, delayTime);
  }

  /**
   * 异步发送延迟消息
   *
   * @param destination  formats: `topicName:tags`
   * @param message      {@link org.springframework.messaging.Message}
   * @param sendCallback {@link SendCallback}
   * @param timeout      send timeout with millis
   * @param delayLevel   level for the delay message
   */
  public void asyncSendDelay(String destination, Message<?> message, SendCallback sendCallback,
      long timeout,
      int delayLevel) {
    rocketMQTemplate.asyncSend(destination, message, sendCallback, timeout, delayLevel);
  }


}
