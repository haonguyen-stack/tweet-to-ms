package com.ms.kafka.example.config.service.impl;

import java.util.concurrent.CompletableFuture;

import javax.annotation.PreDestroy;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.ms.kafka.example.avro.model.XAvroModel;
import com.ms.kafka.example.config.service.KafkaProducer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class XKafkaProducer implements KafkaProducer<Long, XAvroModel> {

    private final KafkaTemplate<Long, XAvroModel> kafkaTemplate;

    @Override
    public void send(String topicName, Long key, XAvroModel message) {
        log.info("Sending message='{}' to topic='{}'", message, topicName);

        CompletableFuture<SendResult<Long, XAvroModel>> future = kafkaTemplate.send(topicName, key, message);

        addCallback(topicName, message, future);
    }

    @PreDestroy
    public void close() {
        if (kafkaTemplate != null) {
            log.info("Closing kafka producer!");
            kafkaTemplate.destroy();
        }
    }

    private void addCallback(String topicName, XAvroModel message,
            CompletableFuture<SendResult<Long, XAvroModel>> future) {
        future.whenComplete((result, ex) -> {

            if (ex != null) {
                log.error("Failed to send message {} to topic {}", message, topicName, ex);
            } else {
                log.info("Message sent successfully. Topic: {}; Partition {}; Offset {}; Timestamp {}, at time {}",
                        result.getRecordMetadata().topic(),
                        result.getRecordMetadata().partition(),
                        result.getRecordMetadata().offset(),
                        result.getRecordMetadata().timestamp(),
                        System.nanoTime());
            }
        });
    }

}
