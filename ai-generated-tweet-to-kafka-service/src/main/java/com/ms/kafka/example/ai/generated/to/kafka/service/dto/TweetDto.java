package com.ms.kafka.example.ai.generated.to.kafka.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TweetDto {

    private Long id;
    private String text;
    private String createdAt;
    private User user;

    @Data
    public static class User {
        private Long id;
    }
}
