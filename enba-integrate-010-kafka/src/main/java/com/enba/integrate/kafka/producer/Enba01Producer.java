package com.enba.integrate.kafka.producer;

import com.enba.integrate.kafka.message.Enba01Message;
import java.util.concurrent.ExecutionException;
import javax.annotation.Resource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
public class Enba01Producer {

    @Resource
    private KafkaTemplate<Object, Object> kafkaTemplate;

    public SendResult syncSend(Integer id) throws ExecutionException, InterruptedException {
        // 创建一个Enba01Message实例
        Enba01Message message = new Enba01Message();
        message.setId(id);

        // 使用Kafka模板发送消息到指定主题，并获取发送结果
        return kafkaTemplate.send(Enba01Message.TOPIC, message).get();
    }

    public ListenableFuture<SendResult<Object, Object>> asyncSend(Integer id) {
        Enba01Message message = new Enba01Message();
        message.setId(id);

        return kafkaTemplate.send(Enba01Message.TOPIC, message);
    }

}
