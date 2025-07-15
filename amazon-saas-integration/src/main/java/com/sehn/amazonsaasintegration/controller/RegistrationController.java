package com.sehn.amazonsaasintegration.controller;

import com.sehn.amazonsaasintegration.entity.User;
import com.sehn.amazonsaasintegration.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Registration Controller
 * Amazon에서 리다이렉트되는 회원가입/로그인 페이지 처리
 */
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/register/amazon")
public class RegistrationController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * Registration Challenge
     * Amazon Developer Portal 테스트용
     */
    @GetMapping("/amazonregistrationchallenge")
    @ResponseBody
    public ResponseEntity<String> getRegistrationChallenge() {
        try {
            ClassPathResource resource = new ClassPathResource("challenges/amazonregistrationchallenge");
            String content = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

            log.info("Served registration challenge");
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(content);
        } catch (IOException e) {
            log.error("Error reading registration challenge file", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * 회원가입/로그인 페이지 표시
     * Amazon에서 팝업으로 열림 (600x500)
     */
    @GetMapping
    public String showRegistrationPage(@RequestParam(required = false) String redirectUrl, Model model) {
        log.info("Registration page requested with redirectUrl: {}", redirectUrl);
        model.addAttribute("redirectUrl", redirectUrl);
        return "registration";
    }

    /**
     * 회원가입 처리
     */
    @PostMapping("/signup")
    @ResponseBody
    public Map<String, Object> signup(@RequestBody SignupRequest request) {
        Map<String, Object> response = new HashMap<>();

        try {
            log.info("Signup request for email: {}", request.getEmail());

            // 이메일 중복 확인
            if (userRepository.existsByEmail(request.getEmail())) {
                response.put("success", false);
                response.put("message", "Email already exists");
                return response;
            }

            // 사용자 생성
            User user = User.builder()
                    .email(request.getEmail())
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .build();

            userRepository.save(user);

            response.put("success", true);
            response.put("email", user.getEmail());
            response.put("username", user.getUsername());

            log.info("User created successfully: {}", user.getEmail());

        } catch (Exception e) {
            log.error("Error during signup", e);
            response.put("success", false);
            response.put("message", "An error occurred during signup");
        }

        return response;
    }

    /**
     * 로그인 처리
     */
    @PostMapping("/signin")
    @ResponseBody
    public Map<String, Object> signin(@RequestBody SigninRequest request) {
        Map<String, Object> response = new HashMap<>();

        try {
            log.info("Signin request for email: {}", request.getEmail());

            // 사용자 찾기
            User user = userRepository.findByEmail(request.getEmail()).orElse(null);

            if (user == null) {
                response.put("success", false);
                response.put("message", "Invalid email or password");
                return response;
            }

            // 비밀번호 확인
            if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                response.put("success", false);
                response.put("message", "Invalid email or password");
                return response;
            }

            response.put("success", true);
            response.put("email", user.getEmail());
            response.put("username", user.getUsername());

            log.info("User signed in successfully: {}", user.getEmail());

        } catch (Exception e) {
            log.error("Error during signin", e);
            response.put("success", false);
            response.put("message", "An error occurred during signin");
        }

        return response;
    }

    @Data
    static class SignupRequest {
        private String email;
        private String username;
        private String password;
    }

    @Data
    static class SigninRequest {
        private String email;
        private String password;
    }
}
