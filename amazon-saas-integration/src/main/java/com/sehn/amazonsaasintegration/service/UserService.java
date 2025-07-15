package com.sehn.amazonsaasintegration.service;

import com.sehn.amazonsaasintegration.dto.request.GetUserIdRequest;
import com.sehn.amazonsaasintegration.dto.response.GetUserIdResponse;
import com.sehn.amazonsaasintegration.entity.User;
import com.sehn.amazonsaasintegration.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    /**
     * Amazon Account Linking을 위한 사용자 ID 조회/생성
     */
    public GetUserIdResponse getUserId(GetUserIdRequest request) {
        GetUserIdResponse response = new GetUserIdResponse();

        try {
            log.info("Processing getUserId request - operation: {}, infoField1: {}, infoField2: {}",
                    request.getOperation(), request.getInfoField1(), request.getInfoField2());

            // Health check 요청 처리
            if ("TESTVALUE".equals(request.getInfoField1())) {
                log.info("Health check request received");
                response.setResponse(GetUserIdResponse.ResponseValue.OK);
                response.setUserId("test-user-id");
                return response;
            }

            String email = request.getInfoField1(); // 첫 번째 필드는 이메일
            String username = request.getInfoField2(); // 두 번째 필드는 사용자명 (선택사항)

            if (email == null || email.trim().isEmpty()) {
                log.warn("Empty or null email provided");
                response.setResponse(GetUserIdResponse.ResponseValue.FAIL_ACCOUNT_INVALID);
                return response;
            }

            // 이메일로 사용자 찾기
            User user = userRepository.findByEmail(email).orElse(null);

            if (user == null) {
                log.warn("User not found for email: {}", email);
                response.setResponse(GetUserIdResponse.ResponseValue.FAIL_ACCOUNT_INVALID);
                return response;
            }

            // Amazon Integration 테스트를 위해 이메일을 userId로 반환
            // 실제 운영에서는 UUID를 사용하는 것이 좋습니다
            String userId = email; // 또는 user.getEmail()

            // Amazon User ID 설정 (나중에 Fulfillment에서 사용)
            if (user.getAmazonUserId() == null) {
                user.setAmazonUserId(userId);
                userRepository.save(user);
                log.info("Set Amazon User ID for user: {}", email);
            }

            response.setResponse(GetUserIdResponse.ResponseValue.OK);
            response.setUserId(userId);

            log.info("Successfully retrieved user ID: {} for email: {}", userId, email);

        } catch (Exception e) {
            log.error("Error processing getUserId request for infoField1: " + request.getInfoField1(), e);
            response.setResponse(GetUserIdResponse.ResponseValue.FAIL_ACCOUNT_INVALID);
        }

        return response;
    }
}
