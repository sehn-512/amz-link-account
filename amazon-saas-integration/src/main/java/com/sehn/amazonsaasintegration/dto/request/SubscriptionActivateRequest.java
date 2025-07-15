package com.sehn.amazonsaasintegration.dto.request;

import lombok.Data;

@Data
public class SubscriptionActivateRequest {
    private String operation;
    private String subscriptionId;
    private String productId;
    private String userId;
    private Integer numberOfLicenses; // Team Subscription only
}
