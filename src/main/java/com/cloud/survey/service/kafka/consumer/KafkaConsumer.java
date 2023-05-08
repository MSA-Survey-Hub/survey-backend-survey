package com.cloud.survey.service.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class KafkaConsumer {

//    @KafkaListener(topics = "test", groupId = "survey_platform")
    public void consume(String message) throws IOException {
        System.out.println(String.format("Consumed message : %s", message));
    }
}