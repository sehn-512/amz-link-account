package com.sehn.amazonsaasintegration.controller;

import com.sehn.amazonsaasintegration.dto.request.GetUserIdRequest;
import com.sehn.amazonsaasintegration.dto.response.GetUserIdResponse;
import com.sehn.amazonsaasintegration.service.UserService;
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
 * Amazon Account Linking Controller
 * Amazon에서 사용자 계정 연결을 위해 호출하는 엔드포인트
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/linkAccount")
public class AccountLinkingController {

    private final UserService userService;

    /**
     * Account Linking Challenge
     * Amazon Developer Portal 테스트용
     */
    @GetMapping("/amazonlinkingchallenge")
    public ResponseEntity<String> getLinkingChallenge() {
        try {
            ClassPathResource resource = new ClassPathResource("challenges/amazonlinkingchallenge");
            String content = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

            log.info("Served linking challenge");
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(content);
        } catch (IOException e) {
            log.error("Error reading linking challenge file", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Account Linking Endpoint
     * Amazon에서 사용자 ID를 요청할 때 호출
     */
    @PostMapping
    public ResponseEntity<GetUserIdResponse> linkAccount(
            @RequestBody GetUserIdRequest request,
            HttpServletRequest httpRequest) {

        log.info("=== Account Linking Request ===");
        log.info("Operation: {}", request.getOperation());
        log.info("InfoField1: {}", request.getInfoField1());
        log.info("InfoField2: {}", request.getInfoField2());
        log.info("InfoField3: {}", request.getInfoField3());

        // Request headers 로깅 (디버깅용)
        logRequestHeaders(httpRequest);

        GetUserIdResponse response;

        // getUserId 오퍼레이션 처리
        if ("GetUserId".equals(request.getOperation())) {
            response = userService.getUserId(request);
        } else {
            // 지원하지 않는 오퍼레이션
            log.warn("Unsupported operation: {}", request.getOperation());
            response = new GetUserIdResponse();
            response.setResponse(GetUserIdResponse.ResponseValue.FAIL_ACCOUNT_INVALID);
        }

        log.info("=== Account Linking Response ===");
        log.info("Response: {}", response.getResponse());
        log.info("UserId: {}", response.getUserId());

        return ResponseEntity.ok(response);
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
