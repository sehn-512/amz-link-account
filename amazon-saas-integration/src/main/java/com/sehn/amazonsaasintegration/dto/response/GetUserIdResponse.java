package com.sehn.amazonsaasintegration.dto.response;

import lombok.Data;

@Data
public class GetUserIdResponse {
    private String response;
    private String userId;

    public static class ResponseValue {
        public static final String OK = "OK";
        public static final String FAIL_ACCOUNT_INVALID = "FAIL_ACCOUNT_INVALID";
    }
}
