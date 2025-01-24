package io.github.innobridge.statemachinedemo.controller;

import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.JsonNode;

import org.springframework.http.ResponseEntity;

import io.github.innobridge.llmtools.client.OllamaClient;
import io.github.innobridge.llmtools.models.Message;
import io.github.innobridge.llmtools.models.request.ChatRequest;
import io.github.innobridge.llmtools.models.response.ChatResponse;
import io.github.innobridge.statemachine.service.StateMachineService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/chat")
public class ChatController {
    
    @Autowired
    private OllamaClient ollamaClient;

    @Autowired
    private StateMachineService stateMachineService;

    @PostMapping("/conversation")
    public Map<String, Object> createChat(
                    @RequestParam String prompt,
                    @RequestParam String model
    ) {
        ChatRequest chatRequest = ChatRequest.builder()
                .messages(
                    List.of(
                        Message.builder()
                            .role("user")
                            .content(prompt)
                            .build()
                    )
                )
                .model(model)
                .stream(false)
                .build();
        ChatResponse response = ollamaClient.chat(chatRequest).block();
        return Map.of("response",   response);
    }
}
