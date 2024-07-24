package com.example.transfer_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransferResponseDTO {

    private UUID id;
    private String fromAccount;
    private String toAccount;
    private Double amount;
    private LocalDateTime transferDate;

    private String currency;
}
