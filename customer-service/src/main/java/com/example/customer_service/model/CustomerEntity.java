package com.example.customer_service.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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
