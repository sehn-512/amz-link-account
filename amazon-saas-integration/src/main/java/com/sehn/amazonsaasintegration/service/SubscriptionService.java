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
import java.util.List;
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

    /**
     * 구독 활성화
     */
    public SubscriptionResponse activateSubscription(SubscriptionActivateRequest request) {
        SubscriptionResponse response = new SubscriptionResponse();

        try {
            // 사용자 확인
            User user = userRepository.findByAmazonUserId(request.getUserId()).orElse(null);
            if (user == null) {
                log.error("User not found for Amazon user ID: {}", request.getUserId());
                response.setResponse(SubscriptionResponse.ResponseValue.FAIL_USER_INVALID);
                return response;
            }

            // 구독 중복 확인
            if (subscriptionRepository.existsBySubscriptionId(request.getSubscriptionId())) {
                log.warn("Subscription already exists: {}", request.getSubscriptionId());
                response.setResponse(SubscriptionResponse.ResponseValue.OK);
                return response;
            }

            // 구독 타입 결정
            boolean isTeamSubscription = request.getNumberOfLicenses() != null;
            Subscription.SubscriptionType type = isTeamSubscription
                    ? Subscription.SubscriptionType.TEAM
                    : Subscription.SubscriptionType.SUS;

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

                // Team 구독 응답 설정
                response.setNumberOfUnusedLicenses(licenseCount);
                response.setNumberOfUsedLicenses(0);
                response.setManagementURL(licenseManagementUrl + "?subscriptionId=" + subscription.getSubscriptionId());
            }

            response.setResponse(SubscriptionResponse.ResponseValue.OK);
            log.info("Successfully activated subscription: {}", request.getSubscriptionId());

        } catch (Exception e) {
            log.error("Error activating subscription", e);
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
            Subscription subscription = subscriptionRepository.findBySubscriptionId(request.getSubscriptionId())
                    .orElse(null);

            if (subscription == null) {
                log.error("Subscription not found: {}", request.getSubscriptionId());
                response.setResponse(SubscriptionResponse.ResponseValue.FAIL_INVALID_SUBSCRIPTION);
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
            }

            response.setResponse(SubscriptionResponse.ResponseValue.OK);
            log.info("Successfully deactivated subscription: {} for reason: {}",
                    request.getSubscriptionId(), request.getReason());

        } catch (Exception e) {
            log.error("Error deactivating subscription", e);
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
            Subscription subscription = subscriptionRepository.findBySubscriptionId(request.getSubscriptionId())
                    .orElse(null);

            if (subscription == null) {
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
            log.error("Error getting subscription", e);
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
            Subscription subscription = subscriptionRepository.findBySubscriptionId(request.getSubscriptionId())
                    .orElse(null);

            if (subscription == null) {
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

        } catch (Exception e) {
            log.error("Error updating subscription", e);
            response.setResponse(SubscriptionResponse.ResponseValue.FAIL_OTHER);
        }

        return response;
    }
}
