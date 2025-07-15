package com.example.amazoninstantaccess.dto;
import lombok.Data;

@Data
public class SubscriptionDeactivateRequest {
    private String operation;
    private String subscriptionId;
    private String period;
    private String reason;
}
