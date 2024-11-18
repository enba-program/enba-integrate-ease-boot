package com.enba.rabbitmq.common;

/**
 * 队列定义
 *
 * @author ding
 */
public class EnbaRabbitDefine {

    /**
     * 普通交换机
     */
    public final static String DIRECT_EXCHANGE = "enba.direct.exchange";

    /**
     * 普通队列
     */
    public final static String DIRECT_QUEUE = "enba.direct.queue";

    /**
     * 延迟队列交换机
     */
    public final static String DELAY_EXCHANGE = "enba.delay.exchange";

    /**
     * 延迟队列
     */
    public final static String DELAY_QUEUE = "enba.delay.queue";

    /**
     * 通配符交换机
     */
    public final static String FANOUT_EXCHANGE = "enba.fanout.exchange";

    /**
     * 通配符队列
     */
    public final static String FANOUT_QUEUE = "enba.fanout.queue";

    /**
     * topic 交换器
     */
    public final static String TOPIC_EXCHANGE = "enba.topic.exchange";

    /**
     * topic队列1
     */
    public final static String TOPIC_QUEUE_ONE = "enba.topic.queue.one";

    /**
     * topic队列2
     */
    public final static String TOPIC_QUEUE_TWO = "enba.topic.queue.two";

    /**
     * 临时队列交换器
     */
    public final static String TTL_EXCHANGE = "enba.ttl.exchange";

    /**
     * 临时队列
     */
    public final static String TTL_QUEUE = "enba.ttl.queue";

    /**
     * 死信交换机
     */
    public final static String DEAD_EXCHANGE = "enba.dead.exchange";

    /**
     * 死信队列
     */
    public final static String DEAD_QUEUE = "enba.dead.queue";
}
