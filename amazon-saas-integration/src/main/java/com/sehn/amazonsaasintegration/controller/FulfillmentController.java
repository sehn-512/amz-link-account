package com.sehn.amazonsaasintegration.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sehn.amazonsaasintegration.dto.request.*;
import com.sehn.amazonsaasintegration.dto.response.SubscriptionGetResponse;
import com.sehn.amazonsaasintegration.dto.response.SubscriptionResponse;
import com.sehn.amazonsaasintegration.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Amazon Fulfillment Controller
 * Amazon에서 구독 관련 작업을 처리하기 위해 호출하는 엔드포인트
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/purchaseAccount")
public class FulfillmentController {

    private final SubscriptionService subscriptionService;
    private final ObjectMapper objectMapper;

    /**
     * Fulfillment Challenge
     * Amazon Developer Portal 테스트용
     */
    @GetMapping("/amazonservicechallenge")
    public ResponseEntity<String> getServiceChallenge() {
        try {
            ClassPathResource resource = new ClassPathResource("challenges/amazonservicechallenge");
            String content = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

            log.info("Served service challenge");
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(content);
        } catch (IOException e) {
            log.error("Error reading service challenge file", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Fulfillment Endpoint
     * 구독 활성화, 비활성화, 업데이트, 조회 등을 처리
     */
    @PostMapping
    public ResponseEntity<Object> processFulfillment(
            @RequestBody JsonNode requestBody,
            HttpServletRequest httpRequest) {

        try {
            String operation = requestBody.get("operation").asText();
            log.info("=== Fulfillment Request ===");
            log.info("Operation: {}", operation);
            log.info("Request Body: {}", requestBody.toString());

            // Request headers 로깅 (디버깅용)
            logRequestHeaders(httpRequest);

            ResponseEntity<Object> response;
            switch (operation) {
                case "SubscriptionActivate":
                    response = handleSubscriptionActivate(requestBody);
                    break;

                case "SubscriptionDeactivate":
                    response = handleSubscriptionDeactivate(requestBody);
                    break;

                case "SubscriptionGet":
                    response = handleSubscriptionGet(requestBody);
                    break;

                case "SubscriptionUpdate":
                    response = handleSubscriptionUpdate(requestBody);
                    break;

                default:
                    log.warn("Unsupported operation: {}", operation);
                    SubscriptionResponse errorResponse = new SubscriptionResponse();
                    errorResponse.setResponse(SubscriptionResponse.ResponseValue.FAIL_OTHER);
                    response = ResponseEntity.ok(errorResponse);
            }

            log.info("=== Fulfillment Response ===");
            log.info("Response: {}", response.getBody());
            return response;

        } catch (Exception e) {
            log.error("Error processing fulfillment request", e);
            SubscriptionResponse errorResponse = new SubscriptionResponse();
            errorResponse.setResponse(SubscriptionResponse.ResponseValue.FAIL_OTHER);
            log.info("=== Fulfillment Error Response ===");
            log.info("Response: {}", errorResponse);
            return ResponseEntity.ok(errorResponse);
        }
    }

    private ResponseEntity<Object> handleSubscriptionActivate(JsonNode requestBody) {
        try {
            SubscriptionActivateRequest request = objectMapper.treeToValue(requestBody, SubscriptionActivateRequest.class);
            SubscriptionResponse response = subscriptionService.activateSubscription(request);
            log.info("Subscription activation response: {}", response.getResponse());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error in subscription activation", e);
            SubscriptionResponse errorResponse = new SubscriptionResponse();
            errorResponse.setResponse(SubscriptionResponse.ResponseValue.FAIL_OTHER);
            return ResponseEntity.ok(errorResponse);
        }
    }

    private ResponseEntity<Object> handleSubscriptionDeactivate(JsonNode requestBody) {
        try {
            SubscriptionDeactivateRequest request = objectMapper.treeToValue(requestBody, SubscriptionDeactivateRequest.class);
            SubscriptionResponse response = subscriptionService.deactivateSubscription(request);
            log.info("Subscription deactivation response: {}", response.getResponse());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error in subscription deactivation", e);
            SubscriptionResponse errorResponse = new SubscriptionResponse();
            errorResponse.setResponse(SubscriptionResponse.ResponseValue.FAIL_OTHER);
            return ResponseEntity.ok(errorResponse);
        }
    }

    private ResponseEntity<Object> handleSubscriptionGet(JsonNode requestBody) {
        try {
            SubscriptionGetRequest request = objectMapper.treeToValue(requestBody, SubscriptionGetRequest.class);
            SubscriptionGetResponse response = subscriptionService.getSubscription(request);
            log.info("Subscription get response: {}", response.getResponse());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error in subscription get", e);
            SubscriptionGetResponse errorResponse = new SubscriptionGetResponse();
            errorResponse.setResponse(SubscriptionGetResponse.ResponseValue.FAIL_OTHER);
            return ResponseEntity.ok(errorResponse);
        }
    }

    private ResponseEntity<Object> handleSubscriptionUpdate(JsonNode requestBody) {
        try {
            SubscriptionUpdateRequest request = objectMapper.treeToValue(requestBody, SubscriptionUpdateRequest.class);
            SubscriptionResponse response = subscriptionService.updateSubscription(request);
            log.info("Subscription update response: {}", response.getResponse());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error in subscription update", e);
            SubscriptionResponse errorResponse = new SubscriptionResponse();
            errorResponse.setResponse(SubscriptionResponse.ResponseValue.FAIL_OTHER);
            return ResponseEntity.ok(errorResponse);
        }
    }

    private void logRequestHeaders(HttpServletRequest request) {
        log.debug("=== Request Headers ===");
        request.getHeaderNames().asIterator().forEachRemaining(headerName -> {
            String headerValue = request.getHeader(headerName);
            if (headerName.toLowerCase().contains("authorization") ||
                    headerName.toLowerCase().startsWith("x-amz-")) {
                log.debug("{}: {}", headerName, headerValue);
            }
        });
        log.debug("=====================");
    }
}
