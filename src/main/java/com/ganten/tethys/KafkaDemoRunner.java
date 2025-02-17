package com.ganten.tethys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class KafkaDemoRunner implements CommandLineRunner {

    private final KafkaProducerService kafkaProducerService;

    @Autowired
    public KafkaDemoRunner(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @Override
    public void run(String... args) throws Exception {
        // 发送测试消息
        kafkaProducerService.sendMessage("test-topic", "Hello, Kafka!");
        kafkaProducerService.sendMessage("test-topic", "key1", "Hello with key!");
        System.out.println("Messages sent to Kafka.");
    }
}
