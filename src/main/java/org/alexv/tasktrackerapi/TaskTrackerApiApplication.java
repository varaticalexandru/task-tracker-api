package org.alexv.tasktrackerapi;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskTrackerApiApplication {

    public static void main(String[] args) {
        laodEnvVars();
        SpringApplication.run(TaskTrackerApiApplication.class, args);
    }

    public static void laodEnvVars() {
        Dotenv dotenv = Dotenv.load();
        dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
    }

}
