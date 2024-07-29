package com.webApp.taskManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@ComponentScan(basePackages = "com.webApp.taskManagement")
@EnableMongoRepositories(basePackages = "com.webApp.taskManagement.repository")
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class TaskManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskManagementApplication.class, args);
    }

}
