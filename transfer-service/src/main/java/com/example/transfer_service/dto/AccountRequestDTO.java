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
public class AccountRequestDTO {

    @NotNull(message = "Customer ID is required")
    private UUID customerId;



    @NotBlank(message = "Account type is required")
    private String accountType;


    private String currency;


    private Double balance;


}
