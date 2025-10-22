package com.ganten.tethys;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class KafkaConsumerService {

    @KafkaListener(topics = "calculate-input", groupId = "tethys-group")
    public void listenTestTopic(String message) {
        log.info("Received from calculate-input: {}", message);
    }

    @KafkaListener(topics = "calculate-output", groupId = "tethys-group")
    public void listenScheduledTopic(String message) {
        log.info("Received from scheduled-output: {}", message);
    }
}
