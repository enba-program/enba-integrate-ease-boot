package com.enba.integrate.kafka.producer;

import com.enba.integrate.kafka.message.Enba02Message;
import java.util.concurrent.ExecutionException;
import javax.annotation.Resource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

@Component
public class Enba02Producer {

    @Resource
    private KafkaTemplate<Object, Object> kafkaTemplate;

    public SendResult syncSend(Integer id) throws ExecutionException, InterruptedException {
        Enba02Message message = new Enba02Message();
        message.setId(id);

        return kafkaTemplate.send(Enba02Message.TOPIC, message).get();
    }

}
