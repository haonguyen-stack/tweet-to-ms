package com.ms.kafka.example.ai.generated.to.kafka.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.ms.kafka.example.ai.generated.to.kafka.service.init.StreamInitializer;
import com.ms.kafka.example.ai.generated.to.kafka.service.runner.StreamRunner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@Slf4j
@EnableScheduling
@ComponentScan(basePackages = "com.ms.kafka.example")
@RequiredArgsConstructor
@SpringBootApplication
public class AIGeneratedTweetToKafkaServiceApplication implements CommandLineRunner  {

    
    private final StreamInitializer streamInitializer;
    private final StreamRunner streamRunner;
    

    public static void main(String[] args) {
        SpringApplication.run(AIGeneratedTweetToKafkaServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Application is starting...");
        streamInitializer.init();
        streamRunner.start();
    }
}