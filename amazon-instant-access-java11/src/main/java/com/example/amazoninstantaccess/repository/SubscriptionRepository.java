package com.example.amazoninstantaccess.repository;

import com.example.amazoninstantaccess.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, String> {
    List<Subscription> findByUserId(String userId);
    List<Subscription> findByUserIdAndActive(String userId, boolean active);
}
