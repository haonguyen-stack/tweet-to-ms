package com.ms.kafka.example.ai.generated.to.kafka.service.service.model;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public record TweetResponse(
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "EEE MMM dd HH:mm:ss zzz yyyy")
    ZonedDateTime createdAt,
    Long id,
    String text,
    User user
) {

}
