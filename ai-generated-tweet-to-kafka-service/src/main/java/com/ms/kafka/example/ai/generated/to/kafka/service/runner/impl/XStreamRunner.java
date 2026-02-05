package com.ms.kafka.example.ai.generated.to.kafka.service.runner.impl;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

import com.ms.kafka.example.ai.generated.to.kafka.service.runner.AIStreamRunner;
import com.ms.kafka.example.ai.generated.to.kafka.service.runner.StreamRunner;
import com.ms.kafka.example.config.AIGeneratedTweetToKafkaServiceConfigData;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class XStreamRunner implements StreamRunner {

    private final TaskScheduler taskScheduler;
    private final AIGeneratedTweetToKafkaServiceConfigData configData;
    private final AIStreamRunner aiStreamRunner;

    @Override
    public void start() {
        log.info("Starting AI Stream Runner with fixed rate {} seconds!", configData.getSchedulerDurationSec());
        taskScheduler.scheduleAtFixedRate(aiStreamRunner,
                    Duration.of(configData.getSchedulerDurationSec(), ChronoUnit.SECONDS));
    }

}
