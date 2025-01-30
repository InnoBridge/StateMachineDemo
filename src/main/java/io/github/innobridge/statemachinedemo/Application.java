package io.github.innobridge.statemachinedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "io.github.innobridge.statemachine.repository")
@ComponentScan(basePackages = {
    "io.github.innobridge.statemachinedemo",
    "io.github.innobridge.statemachine"
})
public class Application {
    private static ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext = SpringApplication.run(Application.class, args);
    }

    public static Environment getEnvironment() {
        return applicationContext.getEnvironment();
    }
}
