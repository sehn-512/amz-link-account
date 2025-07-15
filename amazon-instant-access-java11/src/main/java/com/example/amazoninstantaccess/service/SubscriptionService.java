package com.example.amazoninstantaccess.service;

import com.example.amazoninstantaccess.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubscriptionService {

    public SubscriptionActivateResponse activateSubscription(SubscriptionActivateRequest request) {
        log.info("Activating subscription: {}", request.getSubscriptionId());

        SubscriptionActivateResponse response = new SubscriptionActivateResponse();
        response.setResponse("OK");

        if (request.getNumberOfLicenses() != null) {
            response.setNumberOfUsedLicenses(0);
            response.setNumberOfUnusedLicenses(request.getNumberOfLicenses());
            response.setManagementURL("https://your-domain.com/licenses?subscriptionId=" +
                request.getSubscriptionId());
        }

        return response;
    }

    public SubscriptionResponse deactivateSubscription(SubscriptionDeactivateRequest request) {
        log.info("Deactivating subscription: {}", request.getSubscriptionId());

        SubscriptionResponse response = new SubscriptionResponse();
        response.setResponse("OK");
        return response;
    }

    public SubscriptionGetResponse getSubscription(SubscriptionGetRequest request) {
        log.info("Getting subscription: {}", request.getSubscriptionId());

        SubscriptionGetResponse response = new SubscriptionGetResponse();
        response.setResponse("OK");
        response.setNumberOfUsedLicenses(0);
        response.setNumberOfUnusedLicenses(5);
        response.setManagementURL("https://your-domain.com/licenses?subscriptionId=" +
            request.getSubscriptionId());

        return response;
    }

    public SubscriptionActivateResponse updateSubscription(SubscriptionUpdateRequest request) {
        log.info("Updating subscription: {}", request.getSubscriptionId());

        SubscriptionActivateResponse response = new SubscriptionActivateResponse();
        response.setResponse("OK");
        response.setNumberOfUsedLicenses(0);
        response.setNumberOfUnusedLicenses(request.getNumberOfLicenses() != null ? request.getNumberOfLicenses() : 0);
        response.setManagementURL("https://your-domain.com/licenses?subscriptionId=" +
            request.getSubscriptionId());

        return response;
    }
}
