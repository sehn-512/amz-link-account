package com.example.amazoninstantaccess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class AmazonInstantAccessApplication {
    public static void main(String[] args) {
        SpringApplication.run(AmazonInstantAccessApplication.class, args);
    }
}
