package com.sehn.amazonsaasintegration.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "licenses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class License {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscription_id", nullable = false)
    private Subscription subscription;

    @Column(name = "license_key", unique = true)
    private String licenseKey;

    @Column(name = "assigned_to_email")
    private String assignedToEmail;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private LicenseStatus status = LicenseStatus.UNASSIGNED;

    @Column(name = "assigned_at")
    private LocalDateTime assignedAt;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public enum LicenseStatus {
        ASSIGNED, UNASSIGNED
    }
}
