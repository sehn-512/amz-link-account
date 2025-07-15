package com.sehn.amazonsaasintegration.repository;

import com.sehn.amazonsaasintegration.entity.Subscription;
import com.sehn.amazonsaasintegration.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Optional<Subscription> findBySubscriptionId(String subscriptionId);
    boolean existsBySubscriptionId(String subscriptionId);
    List<Subscription> findByUser(User user);
    List<Subscription> findByUserAndStatus(User user, Subscription.SubscriptionStatus status);
}



