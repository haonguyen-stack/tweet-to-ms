package com.ms.kafka.example.ai.generated.to.kafka.service.transformer;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import org.springframework.stereotype.Component;

import com.ms.kafka.example.ai.generated.to.kafka.service.dto.TweetDto;
import com.ms.kafka.example.avro.model.XAvroModel;

@Component
public class XToAvroTransformer {

        private static final DateTimeFormatter STRICT_FORMATTER = DateTimeFormatter
                        .ofPattern("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

        private static final DateTimeFormatter LENIENT_FORMATTER = new DateTimeFormatterBuilder()
                        .parseCaseInsensitive()
                        .parseLenient()
                        .appendPattern("EEE MMM dd HH:mm:ss zzz yyyy")
                        .toFormatter(Locale.ENGLISH);

        public XAvroModel getXAvroModelFromDto(TweetDto tweetDto) {

                if (tweetDto == null) {
                        throw new IllegalArgumentException("TweetDto is null");
                }

                // -------- CREATED AT --------
                ZonedDateTime zonedDateTime = resolveCreatedAt(tweetDto.getCreatedAt());
                long createdAtMillis = zonedDateTime.toInstant().toEpochMilli();

                // -------- USER ID --------
                Long userId = resolveUserId(tweetDto);

                // -------- TEXT --------
                String text = resolveText(tweetDto.getText());

                return XAvroModel.newBuilder()
                                .setId(defaultIfNull(tweetDto.getId(), 0L))
                                .setUserId(userId)
                                .setText(text)
                                .setCreatedAt(createdAtMillis)
                                .build();
        }

        private ZonedDateTime resolveCreatedAt(String createdAtStr) {

                if (createdAtStr == null || createdAtStr.isBlank()) {
                        return ZonedDateTime.now(ZoneOffset.UTC);
                }

                if ("CREATED_AT".equals(createdAtStr)) {
                        return ZonedDateTime.now(ZoneOffset.UTC);
                }

                try {
                        return ZonedDateTime.parse(createdAtStr, STRICT_FORMATTER);
                } catch (DateTimeParseException e) {
                        try {
                                return ZonedDateTime.parse(createdAtStr, LENIENT_FORMATTER);
                        } catch (Exception ex) {
                                return ZonedDateTime.now(ZoneOffset.UTC);
                        }
                }
        }

        private Long resolveUserId(TweetDto dto) {
                if (dto.getUser() == null || dto.getUser().getId() == null) {
                        return 0L;
                }
                return dto.getUser().getId();
        }

        private String resolveText(String text) {
                if (text == null || text.isBlank()) {
                        return "AI_GENERATED_EMPTY_TEXT";
                }
                return text;
        }

        private Long defaultIfNull(Long value, Long defaultVal) {
                return value == null ? defaultVal : value;
        }
}
