package com.ganten.tethys.cron;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.ganten.tethys.KafkaProducerService;

@Component
public class ScheduledKafkaTask {

    private final KafkaProducerService kafkaProducerService;

    @Autowired
    public ScheduledKafkaTask(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @Scheduled(fixedRate = 5000) // 每5秒执行一次
    public void sendScheduledMessage() {
        String topic = "calculate-input";
        String message = "Scheduled message at " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        kafkaProducerService.sendMessage(topic, message);
        System.out.println("Sent scheduled message: " + message);
    }
}
