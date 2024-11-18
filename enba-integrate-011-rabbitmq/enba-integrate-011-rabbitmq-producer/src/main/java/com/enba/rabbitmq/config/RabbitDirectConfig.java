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
 * direct消息队列配置
 * <p>
 * Direct 类型的交换器由路由规则很简单，它会把消息路由到那些 BindingKey 和 RoutingKey 完全匹配的队列中。
 * Direct Exchange 是 RabbitMQ 默认的交换器模式，也是最简单的模式。它根据 RoutingKey 完全匹配去寻找队列。
 * </p>
 *
 * @author ding
 */
@Configuration
public class RabbitDirectConfig {

    /**
     * 创建direct Exchange交换机也叫完全匹配交换机
     */
    @Bean
    public DirectExchange directExchange() {
        return ExchangeBuilder
                .directExchange(EnbaRabbitDefine.DIRECT_EXCHANGE)
                // 开启持久化
                .durable(true)
                .build();
    }

    /**
     * 普通队列
     */
    @Bean
    public Queue directQueue() {
        return QueueBuilder
                .durable(EnbaRabbitDefine.DIRECT_QUEUE)
                .build();
    }

    /**
     * 交换机绑定队列
     */
    @Bean
    public Binding directBinding() {
        return BindingBuilder.bind(directQueue()).to(directExchange()).with("direct");
    }


}