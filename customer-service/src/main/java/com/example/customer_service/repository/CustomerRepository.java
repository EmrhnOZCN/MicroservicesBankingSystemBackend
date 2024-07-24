package com.example.customer_service.repository;

import com.example.customer_service.model.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<CustomerEntity,UUID> {

    CustomerEntity findByIdentityNumber(String identityNumber);
}
