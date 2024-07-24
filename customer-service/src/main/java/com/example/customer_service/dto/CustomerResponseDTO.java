package com.example.customer_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDTO {

    private UUID id;
    private String name;
    private String surname;
    private LocalDate birthdate;
    private String gender;
    private String nationality;
    private String identityNumber;
    private String password;
    private String contactInformation;
    private String address;
    private String occupation;
}
