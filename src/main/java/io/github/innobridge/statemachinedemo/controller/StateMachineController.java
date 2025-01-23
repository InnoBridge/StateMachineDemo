package io.github.innobridge.statemachinedemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.databind.JsonNode;

import io.github.innobridge.statemachine.service.StateMachineService;
import io.github.innobridge.statemachine.state.usecases.InitialHelloWorld;
import io.github.innobridge.statemachine.state.usecases.childinstances.InitialMeal;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

@RestController("/statemachine")
public class StateMachineController {

    @Autowired
    private StateMachineService stateMachineService;

    @PostMapping("/create/helloworld")
    public String createHelloWorld(
            @RequestBody(required = false) JsonNode input
    ) {
        return stateMachineService.createStateMachine(new InitialHelloWorld(), Optional.ofNullable(input), Optional.empty());
    }

    @PostMapping("/create/meal")
    public String createMeal(
            @RequestBody(required = false) JsonNode input
    ) {
        return stateMachineService.createStateMachine(new InitialMeal(), Optional.ofNullable(input), Optional.empty());
    }

    @PostMapping("/process")
    public String processStateMachine(@RequestParam String instanceId,
            @RequestBody(required = false) JsonNode input) {
        return stateMachineService.processStateMachine(instanceId, Optional.ofNullable(input));
    }
}
