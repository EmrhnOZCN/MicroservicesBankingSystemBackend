package com.example.account_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "accounts")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID customerId;

    @Column(nullable = false, unique = true)
    private String accountNumber;

    @Column(nullable = false)
    private Double balance;

    @Column(nullable = false)
    private String accountType;

    @Column(nullable = false)
    private LocalDateTime createdAt;


    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private String currency;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;
}
