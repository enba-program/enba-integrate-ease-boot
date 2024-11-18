package com.enba.rabbitmq.config;

import com.enba.rabbitmq.common.EnbaRabbitDefine;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 死信队列，当消息过期时，消息进入死信队列
 *
 * @author ding
 */
@Configuration
public class RabbitDeadConfig {

    /**
     * 死信交换机
     *
     * @return FanoutExchange
     */
    @Bean
    public DirectExchange deadExchange() {
        return ExchangeBuilder.directExchange(EnbaRabbitDefine.DEAD_EXCHANGE)
                // 开启持久化
                .durable(true)
                // 所有消费者都解除订阅此队列，autoDelete=true时，此队列会自动删除
                .autoDelete()
                .build();
    }

    /**
     * 死信队列
     *
     * @return Queue
     */
    @Bean
    public Queue deadQueue() {
        return QueueBuilder.durable(EnbaRabbitDefine.DEAD_QUEUE).build();
    }

    /**
     * 绑定
     *
     * @return Binding
     */
    @Bean
    public Binding deadBinding(@Qualifier("deadExchange") DirectExchange deadExchange, @Qualifier("deadQueue") Queue deadQueue) {
        return BindingBuilder.bind(deadQueue).to(deadExchange).with("dead");
    }

}
