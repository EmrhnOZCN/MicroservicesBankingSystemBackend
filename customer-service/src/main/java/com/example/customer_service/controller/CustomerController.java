package com.example.customer_service.controller;

import com.example.customer_service.dto.*;
import com.example.customer_service.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@Validated
public class CustomerController {

    private final CustomerService customerService;


    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping
    public ResponseEntity<Page<CustomerResponseDTO>> getAllCustomers(@RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CustomerResponseDTO> customers = customerService.getAllCustomers(pageable);
        return ResponseEntity.ok(customers);
    }

    @PostMapping
    public ResponseEntity<CustomerRequestDTO> createCustomer(@Valid @RequestBody CustomerRequestDTO requestDTO) {
        CustomerRequestDTO createdCustomer = customerService.createCustomer(requestDTO);
        return ResponseEntity.ok(createdCustomer);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<CustomerVO> authenticateUser(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        CustomerVO isAuthenticated = customerService.authenticate(loginRequestDTO.getIdentityNumber(), loginRequestDTO.getPassword());



        return ResponseEntity.status(HttpStatus.CREATED).body(isAuthenticated);
    }




}
