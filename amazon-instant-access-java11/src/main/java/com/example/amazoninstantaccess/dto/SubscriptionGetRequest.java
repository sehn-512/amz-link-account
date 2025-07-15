package com.example.amazoninstantaccess.dto;
import lombok.Data;

@Data
public class SubscriptionGetRequest {
    private String operation;
    private String subscriptionId;
    private String productId;
    private String userId;
}
