package io.github.innobridge.statemachinedemo.usecase.Tools;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.databind.JsonNode;

import io.github.innobridge.llmtools.function.BraveSearchService;
import io.github.innobridge.llmtools.function.WeatherService;
import io.github.innobridge.llmtools.tools.FunctionConverter;
import io.github.innobridge.statemachine.state.definition.InitialState;
import io.github.innobridge.statemachine.state.implementation.AbstractChildToolState;
import io.github.innobridge.statemachinedemo.usecase.Tools.search.InitialSearch;
import io.github.innobridge.statemachinedemo.usecase.Tools.weather.InitialWeatherService;

public class ChildTools extends AbstractChildToolState {

    private List<String> messages;

    public ChildTools() {
        super();
        this.messages = List.of();
    }

    public List<String> getMessages() {
        return messages;
    }

    @Override
    public Map<String, Class<? extends InitialState>> registerChildInstanceMap() {
        return Map.of(FunctionConverter.getAnnotatedName(WeatherService.class), InitialWeatherService.class,
                FunctionConverter.getAnnotatedName(BraveSearchService.class), InitialSearch.class);
    }

    @Override
    public void action(Map<String, Object> payload) {
        messages.add(payload.get("message").toString());
    }

}
