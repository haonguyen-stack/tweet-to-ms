package com.ms.kafka.example.ai.generated.to.kafka.service.transformer;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.stereotype.Component;

import com.ms.kafka.example.ai.generated.to.kafka.service.dto.TweetDto;
import com.ms.kafka.example.avro.model.XAvroModel;

@Component
public class XToAvroTransformer {

        private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy",
                        Locale.ENGLISH);

        public XAvroModel getXAvroModelFromDto(TweetDto tweetDto) {

                String createdAtStr = tweetDto.getCreatedAt();

                ZonedDateTime zonedDateTime;

                if ("CREATED_AT".equals(createdAtStr)) {
                        zonedDateTime = ZonedDateTime.now(ZoneOffset.UTC);
                } else {
                        zonedDateTime = ZonedDateTime.parse(createdAtStr, FORMATTER);
                }

                long createdAtMillis = zonedDateTime
                                .toInstant()
                                .toEpochMilli();

                return XAvroModel
                                .newBuilder()
                                .setId(tweetDto.getId())
                                .setUserId(tweetDto.getUser().getId())
                                .setText(tweetDto.getText())
                                .setCreatedAt(createdAtMillis)
                                .build();
        }
}
