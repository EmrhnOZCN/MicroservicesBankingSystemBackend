package com.example.account_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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


}
