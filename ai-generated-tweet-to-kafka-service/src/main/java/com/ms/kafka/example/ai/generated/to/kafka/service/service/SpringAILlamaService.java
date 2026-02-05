package com.ms.kafka.example.ai.generated.to.kafka.service.service;

import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.ms.kafka.example.ai.generated.to.kafka.service.exception.AIGeneratedTweetToKafkaServiceException;
import com.ms.kafka.example.ai.generated.to.kafka.service.service.model.TweetResponse;
import com.ms.kafka.example.config.AIGeneratedTweetToKafkaServiceConfigData;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SpringAILlamaService implements AIService {

    private final ChatClient chatClient;
    private final AIGeneratedTweetToKafkaServiceConfigData configData;

    @Value("classpath:/templates/tweet-prompt.st")
    private Resource tweetPrompt;

    @Override
    public String generateTweet() throws AIGeneratedTweetToKafkaServiceException {

        BeanOutputConverter<TweetResponse> converter = new BeanOutputConverter<>(TweetResponse.class);

        PromptTemplate promptTemplate = new PromptTemplate(tweetPrompt);
        Prompt prompt = promptTemplate.create(Map.of(
                configData.getKeywordsPlaceholder().replace("{", "").replace("}", ""),
                String.join(",", configData.getStreamingDataKeywords()),
                "format",
                converter.getFormat()));

        ChatClientResponse response = chatClient.prompt(prompt)
                .call()
                .chatClientResponse();

        String result = response.chatResponse()
                .getResult()
                .getOutput()
                .getText();

        log.info("Model result with model {}: {}",
                response.chatResponse().getMetadata().getModel(),
                result);

        return result.trim();
    }

}
