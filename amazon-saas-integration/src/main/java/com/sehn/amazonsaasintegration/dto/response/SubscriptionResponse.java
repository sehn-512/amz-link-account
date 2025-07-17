package com.sehn.amazonsaasintegration.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubscriptionResponse {
    private String response;
    private Integer numberOfUnusedLicenses; // Team Subscription only
    private Integer numberOfUsedLicenses;   // Team Subscription only
    private String managementURL;           // Team Subscription only

    public static class ResponseValue {
        public static final String OK = "OK";
        public static final String FAIL_USER_NOT_ELIGIBLE = "FAIL_USER_NOT_ELIGIBLE";
        public static final String FAIL_USER_INVALID = "FAIL_USER_INVALID";
        public static final String FAIL_INVALID_SUBSCRIPTION = "FAIL_INVALID_SUBSCRIPTION";
        public static final String FAIL_OTHER = "FAIL_OTHER";
    }
}
