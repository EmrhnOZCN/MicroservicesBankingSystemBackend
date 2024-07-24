package com.example.account_service.controller;

import com.example.account_service.dto.AccountRequestDTO;
import com.example.account_service.dto.AccountResponseDTO;
import com.example.account_service.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {


    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountResponseDTO> createAccount(@Valid @RequestBody AccountRequestDTO accountRequestDTO) {

        AccountResponseDTO accountResponseDTO = accountService.createAccount(accountRequestDTO);
        return new ResponseEntity<>(accountResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/getAccountById")
    public ResponseEntity<AccountResponseDTO> getAccountById(@RequestParam UUID id) {
        System.out.println(id);
        System.out.println("burda");
        AccountResponseDTO accountResponseDTO = accountService.getAccountById(id);
        return ResponseEntity.ok(accountResponseDTO);
    }

    @GetMapping()
    public ResponseEntity<List<AccountResponseDTO>> getAccountsByCustomerId(@RequestParam UUID userId) {
        List<AccountResponseDTO> accounts = accountService.getAccountsByCustomerId(userId);
        return ResponseEntity.ok(accounts);
    }


    @GetMapping("/getString")
    public ResponseEntity<String> getString() {
        return ResponseEntity.ok("Fein Client Test Başarılı");
    }


    @GetMapping("/getAllAccounts")
    public ResponseEntity<List<AccountResponseDTO>> getAllAccounts() {
        List<AccountResponseDTO> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountResponseDTO> updateAccount(@PathVariable UUID id, @Valid @RequestBody Double balance) {
        System.out.println(id);

        AccountResponseDTO accountResponseDTO = accountService.updateAccount(id, balance);
        return ResponseEntity.ok(accountResponseDTO);
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteAccount(@RequestParam UUID accountId) {
        accountService.deleteAccount(accountId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/getAccountByAccountNumber")
    public ResponseEntity<AccountResponseDTO> getAccountByAccountNumber(@RequestParam String accountNumber) {
        AccountResponseDTO accountResponseDTO = accountService.getAccountByAccountNumber(accountNumber);
        return ResponseEntity.ok(accountResponseDTO);
    }


}
