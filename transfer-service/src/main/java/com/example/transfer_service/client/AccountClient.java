package com.example.transfer_service.client;

import com.example.transfer_service.dto.AccountResponseDTO; // Bu DTO'yu kullanmalısınız
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "account-service" ,path = "/api/v1/account")
public interface AccountClient {

    @GetMapping("/getAccountById")
    AccountResponseDTO getAccountById(@RequestParam("id") UUID id);

    @GetMapping
    List<AccountResponseDTO> getAccountsByCustomerId(@RequestParam("userId") UUID userId);

    @PutMapping("/{id}")
    AccountResponseDTO updateAccount(@PathVariable("id") UUID id,Double balance);

    @GetMapping("getString")
    String getTestString();

    @GetMapping("/getAccountByAccountNumber")
    AccountResponseDTO getAccountByAccountNumber(@RequestParam String accountNumber);


}
