package io.github.innobridge.statemachinedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "io.github.innobridge.statemachine.repository")
@ComponentScan(basePackages = {
    "io.github.innobridge.statemachinedemo",
    "io.github.innobridge.statemachine"
})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
