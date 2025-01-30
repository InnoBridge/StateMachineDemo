package io.github.innobridge.statemachinedemo.usecase.Tools.search;

import java.util.Map;
import java.util.Optional;

import org.springframework.core.env.Environment;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;

import io.github.innobridge.llmtools.function.BraveSearchService;
import io.github.innobridge.llmtools.function.BraveSearchService.Response;
import io.github.innobridge.statemachine.state.implementation.AbstractNonBlockingTransitionState;
import io.github.innobridge.statemachinedemo.Application;

public class BraveSearch extends AbstractNonBlockingTransitionState {

    private Map<String, Object> arguments;

    public BraveSearch(Map<String, Object> arguments) {
        super();
        this.arguments = arguments;
    }

    @Override
    public void action(Optional<JsonNode> input) {
        // TODO Auto-generated method stub
        System.out.println("Brave Search");
        Environment env = Application.getEnvironment();
        String apiKey = env.getProperty("bravesearch.api.key");
        String baseUrl = env.getProperty("bravesearch.api.baseurl");

        WebClient braveSearchClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
        BraveSearchService braveSearchService = new BraveSearchService(apiKey, braveSearchClient);
        Response result = braveSearchService.apply(arguments);

        System.out.println(result);
    }
    
}
