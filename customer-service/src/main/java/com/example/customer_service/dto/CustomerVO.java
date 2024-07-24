package com.example.customer_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerVO {

    private String id;
    private String identityNumber;
    private String password;
    private String role;
}