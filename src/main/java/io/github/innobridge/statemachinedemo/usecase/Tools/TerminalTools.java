package io.github.innobridge.statemachinedemo.usecase.Tools;

import java.util.Optional;

import com.fasterxml.jackson.databind.JsonNode;

import io.github.innobridge.statemachine.state.implementation.AbstractTerminalState;

public class TerminalTools extends AbstractTerminalState {

    @Override
    public void action(Optional<JsonNode> input) {
        System.out.println("Terminating Tools");
    }
    
}
