package com.sehn.amazonsaasintegration.repository;


import com.sehn.amazonsaasintegration.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByAmazonUserId(String amazonUserId);
    boolean existsByEmail(String email);
}
