package com.example.amazoninstantaccess.repository;

import com.example.amazoninstantaccess.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, String> {
    Optional<UserAccount> findByEmail(String email);
}
