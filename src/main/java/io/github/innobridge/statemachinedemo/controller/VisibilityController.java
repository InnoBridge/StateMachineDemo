package io.github.innobridge.statemachinedemo.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import io.github.innobridge.statemachine.service.VisibilityService;
import io.github.innobridge.statemachine.state.definition.ExecutionThread;

@RestController("/visibility")
public class VisibilityController {
    
    @Autowired
    private VisibilityService visibilityService;
    
    @GetMapping("/instances")
    public Set<String> getActiveInstances() {
        return visibilityService.getActiveInstances();
    }

    @GetMapping("/thread")
    public ExecutionThread getExecutionThread(String instanceId) {
        return visibilityService.getExecutionThread(instanceId);
    }

    @GetMapping("/states")
    public Set<JsonNode> getStates(String instanceId) {
        return visibilityService.getStates(instanceId);
    }
    

}
