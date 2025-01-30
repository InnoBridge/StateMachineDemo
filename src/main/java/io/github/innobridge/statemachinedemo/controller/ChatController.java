package io.github.innobridge.statemachinedemo.controller;

import io.github.innobridge.llmtools.client.OllamaClient;
import io.github.innobridge.llmtools.function.BraveSearchService;
import io.github.innobridge.llmtools.function.WeatherService;
import io.github.innobridge.llmtools.models.Message;
import io.github.innobridge.llmtools.models.request.ChatRequest;
import io.github.innobridge.llmtools.models.response.ChatResponse;
import io.github.innobridge.llmtools.models.request.Tool;
import io.github.innobridge.llmtools.tools.Tools;
import io.github.innobridge.statemachine.service.ChatService;
import io.github.innobridge.statemachinedemo.usecase.Tools.InitialTools;
import io.github.innobridge.llmtools.tools.FunctionConverter;
import io.github.innobridge.llmtools.tools.FunctionsExecutor;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static io.github.innobridge.llmtools.constants.OllamaConstants.FUNCTION;

@RestController
@RequestMapping("/chat")
public class ChatController {
    
    @Autowired
    private OllamaClient ollamaClient;

    @Autowired
    private Tools ollamaTools;

    // @Autowired
    // private StateMachineService stateMachineService;

    @Autowired
    private ChatService chatService;

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

        FunctionsExecutor result = ollamaTools.functionCall(chatRequest, List.of(
            new Tool(FUNCTION, FunctionConverter.convertToToolFunction(WeatherService.class)),
            new Tool(FUNCTION, FunctionConverter.convertToToolFunction(BraveSearchService.class))
        ));

        result.getFunctionCalls().forEach(
            functionCall -> {
                System.out.println(functionCall.getName() + " : " + functionCall.getArguments());
            }
        );

        chatService.toolUse(new InitialTools(), result.getFunctionCalls());

        ChatResponse response = ollamaClient.chat(chatRequest).block();
        return Map.of("response",   response);
    }
}
