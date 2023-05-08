package com.cloud.survey.service.kafka.producer;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class KafkaProducer{

    private final KafkaTemplate<String, Map<String,Object>> kafkaTemplateObjectMap;

    public void sendObjetMap(String topic, Map<String,Object> map) {
        this.kafkaTemplateObjectMap.send(topic, map);
        System.out.println(String.format("Produce message : %s", map));
    }
}
