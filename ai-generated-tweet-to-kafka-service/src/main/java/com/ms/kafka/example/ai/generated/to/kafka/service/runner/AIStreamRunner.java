package com.ms.kafka.example.ai.generated.to.kafka.service.runner;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.kafka.example.ai.generated.to.kafka.service.dto.TweetDto;
import com.ms.kafka.example.ai.generated.to.kafka.service.service.AIService;
import com.ms.kafka.example.ai.generated.to.kafka.service.transformer.XToAvroTransformer;
import com.ms.kafka.example.avro.model.XAvroModel;
import com.ms.kafka.example.config.KafkaConfigData;
import com.ms.kafka.example.config.service.KafkaProducer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class AIStreamRunner implements Runnable{

    private final AIService aiService;
    private final XToAvroTransformer transformer;
    private final KafkaProducer<Long, XAvroModel> kafkaProducer;
    private final KafkaConfigData kafkaConfigData;
    private final ObjectMapper objectMapper;

    @Override
    public void run() {
        try {
            // 1. Generate tweet from Ollama
            String generatedTweet = aiService.generateTweet();

            // 2. JSON → Avro
            TweetDto tweet = objectMapper.readValue(generatedTweet, TweetDto.class);
            XAvroModel avroModel = transformer.getXAvroModelFromDto(tweet);

            // 3. Send to Kafka
            kafkaProducer.send(
                    kafkaConfigData.getTopicName(),
                    avroModel.getUserId(),
                    avroModel
            );
            log.info("X sent to Kafka: {}", avroModel.getText());

        } catch (Exception e) {
            log.error("Failed to generate/send X", e);
        }
        
        
    }

}
