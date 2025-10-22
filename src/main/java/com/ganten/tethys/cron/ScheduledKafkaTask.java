package com.ganten.tethys.cron;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.ganten.tethys.KafkaProducerService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ScheduledKafkaTask {

    private final KafkaProducerService kafkaProducerService;

    @Autowired
    public ScheduledKafkaTask(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @Scheduled(fixedRate = 5000)
    public void sendScheduledMessage() {
        String message = "hello!";
        kafkaProducerService.sendMessage("calculate-input", message);
        log.info("Sent message: {} to {}", message, "calculate-input");
    }
}
