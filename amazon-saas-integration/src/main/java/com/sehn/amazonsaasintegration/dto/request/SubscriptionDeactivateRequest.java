package com.sehn.amazonsaasintegration.dto.request;

import lombok.Data;

@Data
public class SubscriptionDeactivateRequest {
    private String operation;
    private String subscriptionId;
    private String period;
    private String reason;
}
