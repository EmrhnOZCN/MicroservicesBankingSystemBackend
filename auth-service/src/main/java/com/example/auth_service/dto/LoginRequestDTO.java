package com.example.auth_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {


    @NotBlank(message = "Identity number cannot be blank")
    private String identityNumber;

    @NotBlank(message = "Password cannot be blank")
    private String password;
}