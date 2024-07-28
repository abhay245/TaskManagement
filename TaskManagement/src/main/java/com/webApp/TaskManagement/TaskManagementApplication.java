package com.webApp.TaskManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.webApp.TaskManagement.Repository")
public class TaskManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskManagementApplication.class, args);
    }

}