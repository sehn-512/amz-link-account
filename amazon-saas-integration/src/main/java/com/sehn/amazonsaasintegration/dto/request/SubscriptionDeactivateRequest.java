package com.sehn.amazonsaasintegration.dto.request;

import lombok.Data;

@Data
public class SubscriptionDeactivateRequest {
    private String operation;
    private String subscriptionId;
    private String productId;        // Amazon에서 보내는 필드 추가
    private String userId;           // Amazon에서 보내는 필드 추가
    private String period;
    private String reason;
    private String numberOfLicenses; // Amazon 테스트에서 보내는 필드 (문자열로)
    private String infoFields;       // Amazon 테스트에서 보내는 필드
}
