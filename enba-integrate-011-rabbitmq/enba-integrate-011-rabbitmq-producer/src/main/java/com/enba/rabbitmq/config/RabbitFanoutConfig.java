package com.enba.rabbitmq.config;

import com.enba.rabbitmq.common.EnbaRabbitDefine;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 通配符交换机
 *
 * @author ding
 */
@Configuration
public class RabbitFanoutConfig {

    /**
     * 创建Fanout Exchange交换机也叫通配符交换机
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return ExchangeBuilder
                .fanoutExchange(EnbaRabbitDefine.FANOUT_EXCHANGE)
                // 开启持久化
                .durable(true)
                .build();
    }

    /**
     * 创建队列
     */
    @Bean
    public Queue fanoutQueue() {
        return QueueBuilder
                .durable(EnbaRabbitDefine.FANOUT_QUEUE)
                .build();
    }

    /**
     * 确定绑定关系,队列和交换机绑定
     */
    @Bean
    public Binding fanoutBinding() {
        return BindingBuilder.bind(fanoutQueue()).to(fanoutExchange());
    }

}