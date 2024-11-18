package com.enba.rabbitmq.config;

import com.enba.rabbitmq.common.EnbaRabbitDefine;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 临时消息队列配置
 *
 * @author ding
 */
@Configuration
public class RabbitTtlDirectConfig {

    /**
     * 创建direct Exchange交换机也叫完全匹配交换机
     */
    @Bean
    public DirectExchange directTtlExchange() {
        return ExchangeBuilder
                .directExchange(EnbaRabbitDefine.TTL_EXCHANGE)
                // 开启持久化
                .durable(true)
                // 所有消费者都解除订阅此队列，autoDelete=true时，此交换机会自动删除
                //.autoDelete()
                .build();
    }

    /**
     * 会过期的队列
     */
    @Bean
    public Queue directTtlQueue() {

        return QueueBuilder.
                durable(EnbaRabbitDefine.TTL_QUEUE)
                // .ttl()设置队列的过期时间为10秒
                .ttl(10000)
                // 配置消息过期后的处理者（死信队列交换机）
                .deadLetterExchange(EnbaRabbitDefine.DEAD_EXCHANGE)
                // 死信队列路由
                .deadLetterRoutingKey("dead")
                // 所有消费者都解除订阅此队列，autoDelete=true时，此交换机会自动删除
                // .autoDelete()
                .build();
    }

    @Bean
    public Binding directTtlBinding() {
        return BindingBuilder.bind(directTtlQueue()).to(directTtlExchange()).with("test");
    }

}