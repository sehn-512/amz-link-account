package com.sehn.amazonsaasintegration.dto.request;

import lombok.Data;

@Data
public class SubscriptionUpdateRequest {
    private String operation;
    private String subscriptionId;
    private String productId;
    private String userId;
    private Integer numberOfLicenses;
    private Boolean removeAllUnassignedLicenses;
}
