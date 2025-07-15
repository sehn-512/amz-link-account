package com.example.amazoninstantaccess.dto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SubscriptionActivateResponse extends SubscriptionResponse {
    private Integer numberOfUnusedLicenses;
    private Integer numberOfUsedLicenses;
    private String managementURL;
}
