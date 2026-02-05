package com.ms.kafka.example.admin.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;

import com.ms.kafka.example.config.KafkaConfigData;

import lombok.RequiredArgsConstructor;

@EnableRetry
@Configuration
@RequiredArgsConstructor
public class KafkaAdminConfig {

    private final KafkaConfigData kafkaConfigData;

    @Bean
    public AdminClient adminClient() {
        Map<String, Object> config = new HashMap<>();

        config.put(
            AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,
            kafkaConfigData.getBootstrapServers()
        );

        return AdminClient.create(config);
    }
}
