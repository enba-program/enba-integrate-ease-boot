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
 * 延迟队列
 *
 * @author ding
 */
@Configuration
public class RabbitDelayConfig {


    /**
     * 创建延时交换机
     */
    @Bean
    public DirectExchange delayExchange() {
        return ExchangeBuilder
                .directExchange(EnbaRabbitDefine.DELAY_EXCHANGE)
                // 开启延时
                .delayed()
                // 开启持久化
                .durable(true)
                .build();
    }

    /**
     * 队列
     */
    @Bean
    public Queue delayQueue() {
        return QueueBuilder
                .durable(EnbaRabbitDefine.DELAY_QUEUE)
                .build();
    }

    /**
     * 交换机绑定队列
     */
    @Bean
    public Binding delayBinding() {
        return BindingBuilder.bind(delayQueue()).to(delayExchange()).with("delay");
    }

}