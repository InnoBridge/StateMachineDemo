package io.github.innobridge.statemachinedemo.usecase.Tools.weather;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import com.fasterxml.jackson.databind.JsonNode;

import io.github.innobridge.statemachine.state.definition.State;
import io.github.innobridge.statemachine.state.implementation.AbstractInitialState;

public class InitialWeatherService extends AbstractInitialState {

    private Map<String, Object> arguments;

    public InitialWeatherService(Map<String, Object> arguments) {
        super();
        this.arguments = arguments;
    }

    @Override
    public void setTransitions() {
        Map<State, Function<State, State>> transitions = new HashMap<>();
        transitions.put(this, state -> new GetWeather(getArguments()));
        transitions.put(new GetWeather(null), state -> {
            GetWeather getWeather = (GetWeather) state;
            return new TerminalWeather(getWeather.getWeather());
        });
        this.transitions = transitions;
    }

    @Override
    public void action(Optional<JsonNode> input) {
        System.out.println("Initializing Weather Service");
    }

    public Map<String, Object> getArguments() {
        return arguments;
    }
    
}
