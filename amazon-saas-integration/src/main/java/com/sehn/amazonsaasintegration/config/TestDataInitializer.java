package com.sehn.amazonsaasintegration.config;

import com.sehn.amazonsaasintegration.entity.User;
import com.sehn.amazonsaasintegration.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Amazon Integration 테스트를 위한 테스트 데이터 초기화
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class TestDataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        initializeTestUsers();
    }

    private void initializeTestUsers() {
        // Amazon 테스트에서 사용할 테스트 사용자들 생성
        String[] testEmails = {
                "isehn1219@gmail.com",
                "test@example.com",
                "admin@test.com"
        };

        for (String email : testEmails) {
            if (!userRepository.existsByEmail(email)) {
                User testUser = User.builder()
                        .email(email)
                        .username(extractUsernameFromEmail(email))
                        .password(passwordEncoder.encode("password123"))
                        .amazonUserId(email) // 테스트용으로 이메일을 amazonUserId로 사용
                        .build();

                userRepository.save(testUser);
                log.info("Created test user: {}", email);
            } else {
                // 기존 사용자의 amazonUserId 설정
                User existingUser = userRepository.findByEmail(email).get();
                if (existingUser.getAmazonUserId() == null) {
                    existingUser.setAmazonUserId(email);
                    userRepository.save(existingUser);
                    log.info("Updated amazonUserId for existing user: {}", email);
                }
            }
        }
    }

    private String extractUsernameFromEmail(String email) {
        return email.substring(0, email.indexOf('@'));
    }
}
