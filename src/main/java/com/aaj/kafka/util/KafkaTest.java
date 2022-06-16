package com.aaj.kafka.util;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;
@Controller("test")
public class KafkaTest {
    private final static String TOPIC_NAME = "iotToExhaust";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/sendTest")
    public String sendTest() {
        kafkaTemplate.send(TOPIC_NAME, UUID.randomUUID().toString(), "test message send~");
        return "ok";
    }

    @KafkaListener(topics = "iotToExhaust",id = "CID-iotToExhaust")
    public void listen(ConsumerRecord<String, String> record) {
        System.out.println(record.value());
    }
}