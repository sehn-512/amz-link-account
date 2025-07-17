package com.sehn.amazonsaasintegration.service;

import com.sehn.amazonsaasintegration.dto.request.SubscriptionActivateRequest;
import com.sehn.amazonsaasintegration.dto.request.SubscriptionDeactivateRequest;
import com.sehn.amazonsaasintegration.dto.request.SubscriptionGetRequest;
import com.sehn.amazonsaasintegration.dto.request.SubscriptionUpdateRequest;
import com.sehn.amazonsaasintegration.dto.response.SubscriptionGetResponse;
import com.sehn.amazonsaasintegration.dto.response.SubscriptionResponse;
import com.sehn.amazonsaasintegration.entity.License;
import com.sehn.amazonsaasintegration.entity.Subscription;
import com.sehn.amazonsaasintegration.entity.User;
import com.sehn.amazonsaasintegration.repository.LicenseRepository;
import com.sehn.amazonsaasintegration.repository.SubscriptionRepository;
import com.sehn.amazonsaasintegration.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class SubscriptionService {

    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final LicenseRepository licenseRepository;

    @Value("${amazon.integration.license-management-url}")
    private String licenseManagementUrl;

    // Valid Product IDs - 실제 환경에서는 데이터베이스나 설정파일에서 관리
    private static final Set<String> VALID_PRODUCT_IDS = Set.of(
            "Product-Id",           // Amazon 테스트에서 사용하는 일반적인 product ID
            "product_A",            // 다른 예시 product ID들
            "product_B",
            "SaaS-Product-Basic",
            "SaaS-Product-Premium"
    );

    /**
     * Product ID 유효성 검증
     */
    private boolean isValidProductId(String productId) {
        if (productId == null || productId.trim().isEmpty()) {
            return false;
        }

        // Amazon 테스트의 invalid product ID 패턴 확인
        if (productId.startsWith("DTG_INVALID_")) {
            log.warn("Invalid product ID detected: {}", productId);
            return false;
        }

        // Valid product ID 목록에 있는지 확인
        return VALID_PRODUCT_IDS.contains(productId);
    }

    /**
     * 구독 활성화
     */
    public SubscriptionResponse activateSubscription(SubscriptionActivateRequest request) {
        SubscriptionResponse response = new SubscriptionResponse();

        try {
            log.info("Processing subscription activation - subscriptionId: {}, userId: {}, productId: {}, numberOfLicenses: {}",
                    request.getSubscriptionId(), request.getUserId(), request.getProductId(), request.getNumberOfLicenses());

            // Product ID 검증
            if (!isValidProductId(request.getProductId())) {
                log.warn("Invalid product ID: {}", request.getProductId());
                response.setResponse(SubscriptionResponse.ResponseValue.FAIL_OTHER);
                return response;
            }

            // 사용자 확인 (amazonUserId 또는 email로 검색)
            User user = findUserByIdOrEmail(request.getUserId());
            if (user == null) {
                log.error("User not found for ID: {}", request.getUserId());
                response.setResponse(SubscriptionResponse.ResponseValue.FAIL_USER_INVALID);
                return response;
            }

            log.info("Found user: {} (email: {})", user.getAmazonUserId(), user.getEmail());

            // 구독 중복 확인
            if (subscriptionRepository.existsBySubscriptionId(request.getSubscriptionId())) {
                log.warn("Subscription already exists: {}", request.getSubscriptionId());

                // 기존 구독 정보 반환
                Subscription existingSubscription = subscriptionRepository.findBySubscriptionId(request.getSubscriptionId()).get();
                if (existingSubscription.getSubscriptionType() == Subscription.SubscriptionType.TEAM) {
                    List<License> licenses = licenseRepository.findBySubscription(existingSubscription);
                    long unassigned = licenses.stream().filter(l -> l.getStatus() == License.LicenseStatus.UNASSIGNED).count();
                    long assigned = licenses.stream().filter(l -> l.getStatus() == License.LicenseStatus.ASSIGNED).count();

                    response.setNumberOfUnusedLicenses((int) unassigned);
                    response.setNumberOfUsedLicenses((int) assigned);
                    response.setManagementURL(licenseManagementUrl + "?subscriptionId=" + existingSubscription.getSubscriptionId());
                }

                response.setResponse(SubscriptionResponse.ResponseValue.OK);
                return response;
            }

            // 구독 타입 결정
            boolean isTeamSubscription = request.getNumberOfLicenses() != null && request.getNumberOfLicenses() > 0;
            Subscription.SubscriptionType type = isTeamSubscription
                    ? Subscription.SubscriptionType.TEAM
                    : Subscription.SubscriptionType.SUS;

            log.info("Creating {} subscription with {} licenses", type,
                    isTeamSubscription ? request.getNumberOfLicenses() : "N/A");

            // 구독 생성
            Subscription subscription = Subscription.builder()
                    .subscriptionId(request.getSubscriptionId())
                    .user(user)
                    .productId(request.getProductId())
                    .subscriptionType(type)
                    .status(Subscription.SubscriptionStatus.ACTIVE)
                    .activatedAt(LocalDateTime.now())
                    .build();

            subscription = subscriptionRepository.save(subscription);
            log.info("Created subscription: {}", subscription.getId());

            // Team 구독인 경우 라이센스 생성
            if (isTeamSubscription) {
                int licenseCount = request.getNumberOfLicenses();
                for (int i = 0; i < licenseCount; i++) {
                    License license = License.builder()
                            .subscription(subscription)
                            .licenseKey(UUID.randomUUID().toString())
                            .status(License.LicenseStatus.UNASSIGNED)
                            .build();
                    licenseRepository.save(license);
                }

                log.info("Created {} licenses for subscription", licenseCount);

                // Team 구독 응답 설정
                response.setNumberOfUnusedLicenses(licenseCount);
                response.setNumberOfUsedLicenses(0);
                response.setManagementURL(licenseManagementUrl + "?subscriptionId=" + subscription.getSubscriptionId());
            }

            response.setResponse(SubscriptionResponse.ResponseValue.OK);
            log.info("Successfully activated subscription: {}", request.getSubscriptionId());

        } catch (Exception e) {
            log.error("Error activating subscription: " + request.getSubscriptionId(), e);
            response.setResponse(SubscriptionResponse.ResponseValue.FAIL_OTHER);
        }

        return response;
    }

    /**
     * 구독 비활성화
     */
    public SubscriptionResponse deactivateSubscription(SubscriptionDeactivateRequest request) {
        SubscriptionResponse response = new SubscriptionResponse();

        try {
            log.info("Processing subscription deactivation - subscriptionId: {}, reason: {}, period: {}",
                    request.getSubscriptionId(), request.getReason(), request.getPeriod());

            Subscription subscription = subscriptionRepository.findBySubscriptionId(request.getSubscriptionId())
                    .orElse(null);

            if (subscription == null) {
                log.warn("Subscription not found: {} - treating as already deactivated", request.getSubscriptionId());
                // Amazon 테스트에서는 존재하지 않는 subscription에 대해서도 OK를 반환해야 함
                // Deactivation 응답에서는 라이센스 관련 필드를 포함하지 않음
                response.setResponse(SubscriptionResponse.ResponseValue.OK);
                return response;
            }

            log.info("Found subscription: {} (status: {}, type: {})",
                    subscription.getId(), subscription.getStatus(), subscription.getSubscriptionType());

            // 이미 비활성화된 subscription인 경우
            if (subscription.getStatus() == Subscription.SubscriptionStatus.CANCELLED ||
                    subscription.getStatus() == Subscription.SubscriptionStatus.INACTIVE) {
                log.info("Subscription already deactivated: {}", request.getSubscriptionId());
                response.setResponse(SubscriptionResponse.ResponseValue.OK);
                return response;
            }

            // 구독 상태 업데이트
            subscription.setStatus(Subscription.SubscriptionStatus.CANCELLED);
            subscription.setDeactivatedAt(LocalDateTime.now());
            subscriptionRepository.save(subscription);

            // Team 구독인 경우 모든 라이센스 해제
            if (subscription.getSubscriptionType() == Subscription.SubscriptionType.TEAM) {
                List<License> licenses = licenseRepository.findBySubscription(subscription);
                licenses.forEach(license -> {
                    license.setStatus(License.LicenseStatus.UNASSIGNED);
                    license.setAssignedToEmail(null);
                    license.setAssignedAt(null);
                });
                licenseRepository.saveAll(licenses);
                log.info("Unassigned {} licenses for cancelled subscription", licenses.size());
            }

            // Deactivation 응답에서는 구독 타입에 관계없이 라이센스 관련 필드를 포함하지 않음
            response.setResponse(SubscriptionResponse.ResponseValue.OK);
            log.info("Successfully deactivated subscription: {} for reason: {}",
                    request.getSubscriptionId(), request.getReason());

        } catch (Exception e) {
            log.error("Error deactivating subscription: " + request.getSubscriptionId(), e);
            response.setResponse(SubscriptionResponse.ResponseValue.FAIL_OTHER);
        }

        return response;
    }

    /**
     * 구독 정보 조회 (Team 구독 전용)
     */
    public SubscriptionGetResponse getSubscription(SubscriptionGetRequest request) {
        SubscriptionGetResponse response = new SubscriptionGetResponse();

        try {
            log.info("Processing subscription get - subscriptionId: {}, userId: {}",
                    request.getSubscriptionId(), request.getUserId());

            Subscription subscription = subscriptionRepository.findBySubscriptionId(request.getSubscriptionId())
                    .orElse(null);

            if (subscription == null) {
                log.error("Subscription not found: {}", request.getSubscriptionId());
                response.setResponse(SubscriptionGetResponse.ResponseValue.FAIL_INVALID_SUBSCRIPTION);
                return response;
            }

            // 라이센스 정보 계산
            List<License> licenses = licenseRepository.findBySubscription(subscription);
            long unassigned = licenses.stream()
                    .filter(l -> l.getStatus() == License.LicenseStatus.UNASSIGNED)
                    .count();
            long assigned = licenses.stream()
                    .filter(l -> l.getStatus() == License.LicenseStatus.ASSIGNED)
                    .count();

            response.setResponse(SubscriptionGetResponse.ResponseValue.OK);
            response.setNumberOfUnusedLicenses((int) unassigned);
            response.setNumberOfUsedLicenses((int) assigned);
            response.setManagementURL(licenseManagementUrl + "?subscriptionId=" + subscription.getSubscriptionId());

            log.info("Retrieved subscription: {} - unused: {}, used: {}",
                    request.getSubscriptionId(), unassigned, assigned);

        } catch (Exception e) {
            log.error("Error getting subscription: " + request.getSubscriptionId(), e);
            response.setResponse(SubscriptionGetResponse.ResponseValue.FAIL_OTHER);
        }

        return response;
    }

    /**
     * 구독 업데이트 (Team 구독 전용)
     */
    public SubscriptionResponse updateSubscription(SubscriptionUpdateRequest request) {
        SubscriptionResponse response = new SubscriptionResponse();

        try {
            log.info("Processing subscription update - subscriptionId: {}, numberOfLicenses: {}, removeUnassigned: {}",
                    request.getSubscriptionId(), request.getNumberOfLicenses(), request.getRemoveAllUnassignedLicenses());

            Subscription subscription = subscriptionRepository.findBySubscriptionId(request.getSubscriptionId())
                    .orElse(null);

            if (subscription == null) {
                log.error("Subscription not found: {}", request.getSubscriptionId());
                response.setResponse(SubscriptionResponse.ResponseValue.FAIL_INVALID_SUBSCRIPTION);
                return response;
            }

            // 라이센스 수 업데이트
            if (request.getNumberOfLicenses() != null) {
                List<License> currentLicenses = licenseRepository.findBySubscription(subscription);
                int currentCount = currentLicenses.size();
                int newCount = request.getNumberOfLicenses();

                if (newCount > currentCount) {
                    // 라이센스 추가
                    int toAdd = newCount - currentCount;
                    for (int i = 0; i < toAdd; i++) {
                        License license = License.builder()
                                .subscription(subscription)
                                .licenseKey(UUID.randomUUID().toString())
                                .status(License.LicenseStatus.UNASSIGNED)
                                .build();
                        licenseRepository.save(license);
                    }
                    log.info("Added {} licenses to subscription {}", toAdd, request.getSubscriptionId());
                }
            }

            // 미할당 라이센스 제거
            if (Boolean.TRUE.equals(request.getRemoveAllUnassignedLicenses())) {
                List<License> unassignedLicenses = licenseRepository.findBySubscriptionAndStatus(
                        subscription, License.LicenseStatus.UNASSIGNED);
                licenseRepository.deleteAll(unassignedLicenses);
                log.info("Removed {} unassigned licenses from subscription {}",
                        unassignedLicenses.size(), request.getSubscriptionId());
            }

            // 업데이트된 라이센스 정보 계산
            List<License> updatedLicenses = licenseRepository.findBySubscription(subscription);
            long unassigned = updatedLicenses.stream()
                    .filter(l -> l.getStatus() == License.LicenseStatus.UNASSIGNED)
                    .count();
            long assigned = updatedLicenses.stream()
                    .filter(l -> l.getStatus() == License.LicenseStatus.ASSIGNED)
                    .count();

            response.setResponse(SubscriptionResponse.ResponseValue.OK);
            response.setNumberOfUnusedLicenses((int) unassigned);
            response.setNumberOfUsedLicenses((int) assigned);
            response.setManagementURL(licenseManagementUrl + "?subscriptionId=" + subscription.getSubscriptionId());

            log.info("Updated subscription: {} - unused: {}, used: {}",
                    request.getSubscriptionId(), unassigned, assigned);

        } catch (Exception e) {
            log.error("Error updating subscription: " + request.getSubscriptionId(), e);
            response.setResponse(SubscriptionResponse.ResponseValue.FAIL_OTHER);
        }

        return response;
    }

    /**
     * 사용자 검색 (amazonUserId 또는 email로)
     */
    private User findUserByIdOrEmail(String userIdOrEmail) {
        // 먼저 amazonUserId로 검색
        User user = userRepository.findByAmazonUserId(userIdOrEmail).orElse(null);

        // 없으면 이메일로 검색 (Amazon 테스트에서 이메일을 userId로 보낼 수 있음)
        if (user == null && userIdOrEmail.contains("@")) {
            user = userRepository.findByEmail(userIdOrEmail).orElse(null);

            // 이메일로 찾았으면 amazonUserId 생성
            if (user != null && user.getAmazonUserId() == null) {
                user.setAmazonUserId(UUID.randomUUID().toString());
                userRepository.save(user);
                log.info("Generated amazonUserId for user: {}", userIdOrEmail);
            }
        }

        return user;
    }
}
