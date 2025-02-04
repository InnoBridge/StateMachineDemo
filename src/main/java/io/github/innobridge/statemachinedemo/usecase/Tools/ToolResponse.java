package io.github.innobridge.statemachinedemo.usecase.Tools;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.JsonNode;

import io.github.innobridge.statemachine.state.implementation.AbstractChatResponseState;

public class ToolResponse extends AbstractChatResponseState {

    private List<String> responses;

    public ToolResponse(List<String> responses) {
        super();
        this.responses = responses;
    }
    
    @Override
    public List<String> getResponses() {
        return responses;
    }

    @Override
    public void action(Optional<JsonNode> input) {
    }
}
