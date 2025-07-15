package com.sehn.amazonsaasintegration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AmazonSaasIntegrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmazonSaasIntegrationApplication.class, args);
    }
}
