package com.example.customer_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {



    @NotBlank(message = "Identity number cannot be blank")
    private String identityNumber;

    @NotBlank(message = "Password cannot be blank")
    private String password;
}