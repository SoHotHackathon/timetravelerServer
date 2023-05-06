package com.example.Back.service;


import com.example.Back.controller.dto.chatGPTRequest;
import com.example.Back.controller.dto.chatGPTResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class chatGPTService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final WebClient webClient;

    private final ObjectMapper objectMapper;

    private final String openaiApiKey;

    public chatGPTService(WebClient.Builder webClientBuilder, ObjectMapper objectMapper,
                      @Value("${hackaton.openai-api-key}") String openaiApiKey) {
        this.webClient = webClientBuilder.baseUrl("https://api.openai.com").build();
        this.objectMapper = objectMapper;
        this.openaiApiKey = openaiApiKey;
    }

    public String completeChat(String prompt) {

        List<chatGPTRequest.Message> messages = new ArrayList<>();
        messages.add(new chatGPTRequest.Message("user", prompt));
        chatGPTRequest request = new chatGPTRequest(
                "gpt-3.5-turbo-0301",
                0.9,
                messages
        );
        try {
            chatGPTResponse response = webClient.post()
                    .uri("/v1/chat/completions")
                    .header("Authorization", "Bearer " + openaiApiKey)
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(chatGPTResponse.class)
                    .blockOptional()
                    .orElseThrow(() -> new IllegalStateException("Error occurred while completing chat"));

            return response.getChoices().get(0).getMessage().getContent();
        } catch (WebClientResponseException e) {
            log.error("Error occurred while completing chat - " + e.getResponseBodyAsString(), e);
            throw new IllegalStateException("Error occurred while completing chat", e);
        }
    }
}
