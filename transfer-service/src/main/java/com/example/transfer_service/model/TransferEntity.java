package com.example.transfer_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transfers")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String fromAccountId;

    @Column(nullable = false)
    private String toAccountId;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private LocalDateTime transferDate;


    private String currency;

    @Column(nullable = false)
    private boolean transferStatus;
}
