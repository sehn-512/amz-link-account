package com.sehn.amazonsaasintegration.dto.response;

import lombok.Data;

@Data
public class SubscriptionGetResponse {
    private String response;
    private Integer numberOfUnusedLicenses;
    private Integer numberOfUsedLicenses;
    private String managementURL;

    public static class ResponseValue {
        public static final String OK = "OK";
        public static final String FAIL_USER_INVALID = "FAIL_USER_INVALID";
        public static final String FAIL_INVALID_SUBSCRIPTION = "FAIL_INVALID_SUBSCRIPTION";
        public static final String FAIL_OTHER = "FAIL_OTHER";
    }
}
