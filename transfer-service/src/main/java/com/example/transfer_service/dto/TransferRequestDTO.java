package com.example.transfer_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransferRequestDTO {


    private String fromAccount;


    private String toAccount;

    @NotNull(message = "Amount is required")
    private Double amount;
}
