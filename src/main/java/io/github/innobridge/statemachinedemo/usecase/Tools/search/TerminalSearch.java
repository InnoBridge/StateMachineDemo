package io.github.innobridge.statemachinedemo.usecase.Tools.search;

import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.databind.JsonNode;

import io.github.innobridge.statemachine.state.implementation.AbstractTerminalState;

public class TerminalSearch extends AbstractTerminalState {
    
    @Override
    public void action(Optional<JsonNode> input) {
        System.out.println("Terminating search");
    }

    @Override
    public Optional<Map<String, Object>> getPayload() {
        return Optional.empty();
    }
    
}
