package com.example.account_service.service;

import com.example.account_service.dto.AccountRequestDTO;
import com.example.account_service.dto.AccountResponseDTO;
import com.example.account_service.model.AccountEntity;

import java.util.List;
import java.util.UUID;

public interface AccountService {
    AccountResponseDTO createAccount(AccountRequestDTO accountRequestDTO);
    AccountResponseDTO getAccountById(UUID id);
    List<AccountResponseDTO> getAllAccounts();
    AccountResponseDTO updateAccount(UUID id, Double balance);

    List<AccountResponseDTO> getAccountsByCustomerId(UUID userId);
    void deleteAccount(UUID id);

    AccountResponseDTO getAccountByAccountNumber(String accountNumber);
}
