package io.github.innobridge.statemachinedemo.usecase.Tools.search;

import io.github.innobridge.statemachine.state.definition.State;
import io.github.innobridge.statemachine.state.implementation.AbstractInitialState;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import com.fasterxml.jackson.databind.JsonNode;

public class InitialSearch extends AbstractInitialState {

    private Map<String, Object> arguments;

    public InitialSearch(Map<String, Object> arguments) {
        super();
        this.arguments = arguments;
    }

    @Override
    public void setTransitions() {
        // TODO Auto-generated method stub
        Map<State, Function<State, State>> transitions = new HashMap<>();
        transitions.put(this, state -> new BraveSearch(getArguments()));
        transitions.put(new BraveSearch(null), state -> new TerminalSearch());
        this.transitions = transitions;
    }

    @Override
    public void action(Optional<JsonNode> input) {
        System.out.println("Initializing Search");
    }
    
    public Map<String, Object> getArguments() {
        return arguments;
    }
    
}
