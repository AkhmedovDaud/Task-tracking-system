package com.example.tasktrackingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
public class TaskTrackingSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskTrackingSystemApplication.class, args);
    }

}
