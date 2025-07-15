package com.example.amazoninstantaccess.entity;

import javax.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "subscriptions")
@Data
public class Subscription {

    @Id
    @Column(name = "subscription_id")
    private String subscriptionId;

    @Column(name = "product_id", nullable = false)
    private String productId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "is_active")
    private boolean active = true;

    @Column(name = "number_of_licenses")
    private Integer numberOfLicenses;

    @Column(name = "number_of_used_licenses")
    private Integer numberOfUsedLicenses;

    @Column(name = "number_of_unused_licenses")
    private Integer numberOfUnusedLicenses;

    @Column(name = "deactivation_reason")
    private String deactivationReason;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
