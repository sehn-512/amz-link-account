package com.sehn.amazonsaasintegration.dto.request;

import lombok.Data;

@Data
public class SubscriptionGetRequest {
    private String operation;
    private String subscriptionId;
    private String productId;
    private String userId;
}
