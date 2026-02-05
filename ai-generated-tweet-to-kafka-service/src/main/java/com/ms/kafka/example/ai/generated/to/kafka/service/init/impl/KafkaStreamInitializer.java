package com.ms.kafka.example.ai.generated.to.kafka.service.init.impl;

import org.springframework.stereotype.Component;

import com.ms.kafka.example.admin.client.KafkaAdminClient;
import com.ms.kafka.example.ai.generated.to.kafka.service.init.StreamInitializer;
import com.ms.kafka.example.config.KafkaConfigData;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaStreamInitializer implements StreamInitializer{

    private final KafkaConfigData kafkaConfigData;
    private final KafkaAdminClient kafkaAdminClient;

    @Override
    public void init() {
        kafkaAdminClient.createTopics();
        log.info("Topics with name {} is ready for operations!", kafkaConfigData.getTopicNameToCreate().toArray());
    }

}
