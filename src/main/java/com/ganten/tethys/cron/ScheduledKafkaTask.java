package com.ganten.tethys.cron;

import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.ganten.tethys.CalculateInput;
import com.ganten.tethys.JsonUtils;
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
        CalculateInput calculateInput = new CalculateInput();
        calculateInput.setNumberFirst(new Random().nextInt(100));
        calculateInput.setNumberSecond(new Random().nextInt(100));

        kafkaProducerService.sendMessage("calculate-input", JsonUtils.toJson(calculateInput));
        log.info("Sent message: {} to {}", JsonUtils.toJson(calculateInput), "calculate-input");
    }
}
