package com.example.customer_service.service;

import com.example.customer_service.dto.CustomerRequestDTO;
import com.example.customer_service.dto.CustomerResponseDTO;
import com.example.customer_service.dto.CustomerVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.UUID;

public interface CustomerService {

    CustomerRequestDTO createCustomer(CustomerRequestDTO requestDTO);

    CustomerRequestDTO getCustomerById(UUID id);

    Page<CustomerResponseDTO> getAllCustomers(Pageable pageable);

    CustomerResponseDTO updateCustomer(UUID id, CustomerRequestDTO requestDTO);

    void deleteCustomer(UUID id);

    CustomerVO authenticate(String identityNumber, String password);
}
