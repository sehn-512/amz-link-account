package com.example.amazoninstantaccess.entity;

import javax.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_accounts")
@Data
public class UserAccount {

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "info_field2")
    private String infoField2;

    @Column(name = "info_field3")
    private String infoField3;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();
}
