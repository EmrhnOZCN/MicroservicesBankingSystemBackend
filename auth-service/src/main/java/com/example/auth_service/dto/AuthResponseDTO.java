package com.example.auth_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDTO {

    private String userId;
    private String message;
    private String token;
    private String accessToken;
}