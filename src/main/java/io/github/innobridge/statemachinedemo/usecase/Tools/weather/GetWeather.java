package io.github.innobridge.statemachinedemo.usecase.Tools.weather;

import java.util.Map;
import java.util.Optional;

import org.springframework.core.env.Environment;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;

import io.github.innobridge.llmtools.function.WeatherService;
import io.github.innobridge.llmtools.function.WeatherService.Response;
import io.github.innobridge.statemachine.state.implementation.AbstractNonBlockingTransitionState;
import io.github.innobridge.statemachinedemo.Application;

public class GetWeather extends AbstractNonBlockingTransitionState {

    private Map<String, Object> arguments;
    private String weather;

    public GetWeather(Map<String, Object> arguments) {
        super();
        this.arguments = arguments;
    }
    
    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    @Override
    public void action(Optional<JsonNode> input) {
        Environment env = Application.getEnvironment();
        String apiKey = env.getProperty("weather.api.key");
        String baseUrl = env.getProperty("weather.api.baseurl");

        WebClient weatherClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
        WeatherService weatherService = new WeatherService(weatherClient, apiKey);
        Response result = weatherService.apply(arguments);

        System.out.println(result.toString()); 
        setWeather(result.toString());        
    }

}
