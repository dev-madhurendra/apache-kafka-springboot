package com.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(org.apache.kafka.clients.consumer.KafkaConsumer.class);

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}", concurrency = "3")
    public void consume(String message) {
        LOGGER.info("Message received -> {}", message);
    }
}
