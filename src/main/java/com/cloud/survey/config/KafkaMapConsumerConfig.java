package com.cloud.survey.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaMapConsumerConfig {

    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public Map<String,Object> Configs() {
        return CommonJsonDeserializer.getStringObjectMap(bootstrapServers);
    }
    @Bean
    public ConsumerFactory<String, Map<String,Object>> factory() {
        return new DefaultKafkaConsumerFactory<>(Configs());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Map<String,Object>> listener() {
        ConcurrentKafkaListenerContainerFactory<String, Map<String,Object>> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(factory());
        return factory;
    }

    @Bean
    public StringJsonMessageConverter jsonConverter() {
        return new StringJsonMessageConverter();
    }
}