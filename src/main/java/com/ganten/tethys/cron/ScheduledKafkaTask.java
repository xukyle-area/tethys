package com.ganten.tethys.cron;

import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.ganten.tethys.kafka.KafkaProducerService;
import com.ganten.tethys.kafka.model.CalculateInput;
import com.ganten.tethys.utils.JsonUtils;
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
        CalculateInput calculateInput = new CalculateInput();
        calculateInput.setNumberFirst(new Random().nextInt(100));
        calculateInput.setNumberSecond(new Random().nextInt(100));

        kafkaProducerService.sendMessage("calculate-input", JsonUtils.toJson(calculateInput));
    }
}
