package com.example.transfer_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountResponseDTO {

    private UUID id;
    private UUID customerId;
    private String accountNumber;
    private Double balance;
    private String currency;
    private String accountType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isActive;


}
