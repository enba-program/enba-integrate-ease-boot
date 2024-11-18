package com.enba.rocketmq.producer;

import com.enba.rocketmq.constants.msg.EnbaUser;
import org.springframework.stereotype.Component;

/**
 * <p>重试消息，消费者消费消息的时候，broker会重新投递该消息给消费者</p>
 * <p>云消息队列 RocketMQ 版消息收发过程中，若Consumer消费某条消息失败或消费超时，则云消息队列 RocketMQ
 * 版会在重试间隔时间后，将消息重新投递给Consumer消费，若达到最大重试次数后消息还没有成功被消费，则消息将被投递至死信队列。您可以通过消费死信队列中的死信消息来恢复业务异常。</p>
 * <p>参考地址：https://www.alibabacloud.com/help/zh/apsaramq-for-rocketmq/cloud-message-queue-rocketmq-4-x-series/developer-reference/message-retry?spm=a2c63.p38356.0.0.30953a267kH3bN</p>
 */
@Component
public class RetryMsgProducer extends BaseProducer {

  /*注意事项*/

  /**
   * 同步发送消息
   * <p>一条消息无论重试多少次，这些重试消息的Message ID都不会改变。</p>
   * <p>消息重试只针对集群消费模式生效；广播消费模式不提供失败重试特性，即消费失败后，失败消息不再重试，继续消费新的消息。</p>
   *
   * @param destination d
   * @param payload     p
   */
  public void syncSendMessageRetry(String destination, EnbaUser payload) {
    rocketMQTemplate.syncSend(destination, payload);
  }

}
