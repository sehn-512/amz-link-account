package com.sehn.amazonsaasintegration.repository;

import com.sehn.amazonsaasintegration.entity.License;
import com.sehn.amazonsaasintegration.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LicenseRepository extends JpaRepository<License, Long> {
    List<License> findBySubscription(Subscription subscription);
    List<License> findBySubscriptionAndStatus(Subscription subscription, License.LicenseStatus status);
    Optional<License> findByLicenseKey(String licenseKey);
    long countBySubscriptionAndStatus(Subscription subscription, License.LicenseStatus status);
}
