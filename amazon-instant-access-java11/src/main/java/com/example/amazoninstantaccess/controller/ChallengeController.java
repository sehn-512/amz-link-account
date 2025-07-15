package com.example.amazoninstantaccess.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
public class ChallengeController {

    @GetMapping(value = "/linkAccount/amazonlinkingchallenge", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getAccountLinkingChallenge() {
        try {
            ClassPathResource resource = new ClassPathResource("challenges/amazonlinkingchallenge");
            String content = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
            return ResponseEntity.ok(content);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error reading challenge file: " + e.getMessage());
        }
    }

    @GetMapping(value = "/purchaseAccount/amazonservicechallenge", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getFulfillmentChallenge() {
        try {
            ClassPathResource resource = new ClassPathResource("challenges/amazonservicechallenge");
            String content = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
            return ResponseEntity.ok(content);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error reading challenge file: " + e.getMessage());
        }
    }

    @GetMapping(value = "/register/amazon/amazonregistrationchallenge", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getRegistrationChallenge() {
        try {
            ClassPathResource resource = new ClassPathResource("challenges/amazonregistrationchallenge");
            String content = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
            return ResponseEntity.ok(content);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error reading challenge file: " + e.getMessage());
        }
    }
}
