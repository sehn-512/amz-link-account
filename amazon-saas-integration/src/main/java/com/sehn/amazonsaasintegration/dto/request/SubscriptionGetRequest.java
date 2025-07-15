package com.sehn.amazonsaasintegration.dto.request;

import lombok.Data;

@Data
public class SubscriptionGetRequest {
    private String operation;
    private String subscriptionId;
    private String productId;
    private String userId;
    private String numberOfLicenses; // Amazon 테스트에서 추가로 보낼 수 있는 필드
    private String infoFields;       // Amazon 테스트에서 추가로 보낼 수 있는 필드
}
