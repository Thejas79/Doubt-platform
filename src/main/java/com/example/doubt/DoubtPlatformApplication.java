package com.example.doubt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DoubtPlatformApplication {
    public static void main(String[] args) {
        SpringApplication.run(DoubtPlatformApplication.class, args);
    }
}
