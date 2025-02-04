package io.github.innobridge.statemachinedemo.usecase.Tools;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import com.fasterxml.jackson.databind.JsonNode;

import io.github.innobridge.statemachine.state.definition.State;
import io.github.innobridge.statemachine.state.implementation.AbstractInitialState;

public class InitialTools extends AbstractInitialState {

    @Override
    public void setTransitions() {
        Map<State, Function<State, State>> transitions = new HashMap<>();
        transitions.put(this,  state -> new ChildTools());
        transitions.put(new ChildTools(),  state -> {
            ChildTools childTools = (ChildTools) state;
            return new ToolResponse(childTools.getMessages());
        });
        transitions.put(new ToolResponse(null), state -> new TerminalTools());
        this.transitions = transitions;
    }

    @Override
    public void action(Optional<JsonNode> input) {
        System.out.println("Initializing Tools");
    }
    
}
