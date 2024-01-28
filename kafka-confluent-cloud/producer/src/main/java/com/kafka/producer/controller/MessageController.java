package com.kafka.producer.controller;

import com.kafka.producer.service.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/kafka")
@RequiredArgsConstructor
public class MessageController  {

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
    private final KafkaProducer kafkaProducer;

    @GetMapping("/publish")
    public ResponseEntity<String> publish(@RequestParam("message") String message) {
        try {
            kafkaProducer.sendMessage(message);
            logger.info("Message sent to Kafka topic: {}", message);
            return ResponseEntity.accepted().body("Message sent to the topic");
        } catch (Exception e) {
            logger.error("Error sending message to Kafka: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending message");
        }
    }
}
