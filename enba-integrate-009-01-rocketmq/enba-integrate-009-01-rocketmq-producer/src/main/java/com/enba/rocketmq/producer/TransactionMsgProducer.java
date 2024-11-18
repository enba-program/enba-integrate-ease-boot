package com.enba.rocketmq.producer;

import com.alibaba.fastjson.JSON;
import com.enba.rocketmq.constants.RocketMqTopicConstant;
import com.enba.rocketmq.constants.msg.EnbaUser;
import java.util.concurrent.TimeUnit;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * <p>事务消息:事务消息能够保证消息的发送与本地事务的提交或回滚保持一致，从而在微服务架构中实现跨服务的事务一致性</p>
 * <p>参考地址：https://www.alibabacloud.com/help/zh/apsaramq-for-rocketmq/cloud-message-queue-rocketmq-4-x-series/developer-reference/transactional-messages?spm=a2c63.p38356.0.0.157863d7WK1CvC</p>
 */
@Component
public class TransactionMsgProducer extends BaseProducer {

  /**
   * 事务消息：云消息队列 RocketMQ 版提供类似XA或Open XA的分布式事务功能，通过云消息队列 RocketMQ 版事务消息能达到分布式事务的最终一致。
   * 半事务消息：暂不能投递的消息，生产者已经成功地将消息发送到了云消息队列 RocketMQ 版服务端，但是云消息队列 RocketMQ
   * 版服务端未收到生产者对该消息的二次确认，此时该消息被标记成“暂不能投递”状态，处于该种状态下的消息即半事务消息。 消息回查：由于网络闪断、生产者应用重启等原因，导致某条事务消息的二次确认丢失，云消息队列
   * RocketMQ 版服务端通过扫描发现某条消息长期处于“半事务消息”时，需要主动向消息生产者询问该消息的最终状态（Commit或是Rollback），该询问过程即消息回查。
   */

//  /**
//   * 分布式事务消息的优势: 云消息队列 RocketMQ 版分布式事务消息不仅可以实现应用之间的解耦，又能保证数据的最终一致性。同时，传统的大事务可以被拆分为小事务，不仅能提升效率，还不会因为某一个关联应用的不可用导致整体回滚，从而最大限度保证核心系统的可用性。在极端情况下，如果关联的某一个应用始终无法处理成功，也只需对当前应用进行补偿或数据订正处理，而无需对整体业务进行回滚。
//   */
  public void sendTransactionMessage(EnbaUser msg) {
    Message<EnbaUser> message = MessageBuilder.withPayload(msg)
        .setHeader("userId", msg.getId())
        .build();

    // 发送事务消息
    try {
      TransactionSendResult ret = rocketMQTemplate
          .sendMessageInTransaction(RocketMqTopicConstant.ENBA_TRANSACTION_MSG_TOPIC, message,
              "ext arg");

      log.info("TransactionMsgProducer.sendTransactionMessage###ret:{}", JSON.toJSONString(ret));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  @RocketMQTransactionListener
  public class EnbaTransactionListener implements RocketMQLocalTransactionListener {

    /**
     * 执行本地事务 该方法在事务消息发送到Broker之前被调用，用于执行本地事务逻辑 如果本地事务执行成功，返回COMMIT状态，表示可以提交事务
     * 如果本地事务执行失败，返回ROLLBACK状态，表示需要回滚事务
     *
     * @param message 消息对象，包含事务的相关信息
     * @param o       事务的额外参数，具体类型和用途依赖于业务场景
     * @return 返回本地事务的状态，可以是COMMIT、ROLLBACK或UNKNOWN
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
      try {
        log.info("EnbaTransactionListener.executeLocalTransaction###message:{},o:{}",
            JSON.toJSONString(message), JSON.toJSONString(o));

        // 执行本地事务逻辑，模拟程序执行
        System.out.println("Executing local transaction...");
        TimeUnit.SECONDS.sleep(5);

        throw new RuntimeException("模拟抛出异常，返给broker ROLLBACK，半消息回滚，broker不会把消息投递给消费者");

//        return RocketMQLocalTransactionState.COMMIT;
      } catch (Exception e) {
        log.error("TransactionMsgProducer.executeLocalTransaction err:{}", e.getMessage(), e);
        return RocketMQLocalTransactionState.ROLLBACK;
      }
    }

    /**
     * 检查本地事务的状态
     *
     * @param message 消息对象，包含事务的相关信息
     * @return 返回事务的状态，这里直接假设事务总是成功的，因此返回提交状态
     * <p>
     * 注意：这里假设事务总是成功的，实际使用时需要根据事务的实际状态返回相应的状态
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
      // 检查本地事务状态，这里假设事务总是成功的
      System.out.println("Checking local transaction...");
      return RocketMQLocalTransactionState.COMMIT;
    }
  }


}
