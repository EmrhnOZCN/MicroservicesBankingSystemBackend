package com.example.auth_service.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {


    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Surname cannot be blank")
    private String surname;

    @NotNull(message = "Birthdate cannot be null")
    @Past(message = "Birthdate must be in the past")
    private LocalDate birthdate;

    @NotBlank(message = "Gender cannot be blank")
    @Pattern(regexp = "^(Male|Female)$", message = "Gender must be Male or Female")
    private String gender;

    @NotBlank(message = "Nationality cannot be blank")
    private String nationality;


    @NotBlank(message = "Identity number cannot be blank")
    @Size(min = 11, max = 11, message = "Identity number must be 11 digits")
    @Pattern(regexp = "\\d{11}", message = "Identity number must be 11 digits")
    private String identityNumber;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;



    @NotBlank(message = "Contact information cannot be blank")
    private String contactInformation;

    @NotBlank(message = "Address cannot be blank")
    private String address;

    @NotBlank(message = "Occupation cannot be blank")
    private String occupation;
}