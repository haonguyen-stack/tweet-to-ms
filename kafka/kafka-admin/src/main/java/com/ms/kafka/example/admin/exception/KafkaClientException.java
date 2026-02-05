package com.ms.kafka.example.admin.exception;

public class KafkaClientException extends RuntimeException{

    public KafkaClientException() {

    }

    public KafkaClientException(String message) {
        super(message);
    }

    public KafkaClientException(String message, Throwable cause) {
        super(message, cause );
    }
}
