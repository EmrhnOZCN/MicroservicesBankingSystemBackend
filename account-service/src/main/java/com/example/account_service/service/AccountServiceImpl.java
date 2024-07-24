package com.example.account_service.service;

import com.example.account_service.dto.AccountRequestDTO;
import com.example.account_service.dto.AccountResponseDTO;
import com.example.account_service.kafka.producer.KafkaProducerService;
import com.example.account_service.mapper.AccountMapper;
import com.example.account_service.model.AccountEntity;
import com.example.account_service.repository.AccountRepository;
import com.example.account_service.exception.AccountNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;
    private final KafkaProducerService kafkaProducerService;

    public AccountServiceImpl(AccountMapper accountMapper, AccountRepository accountRepository, KafkaProducerService kafkaProducerService) {
        this.accountMapper = accountMapper;
        this.accountRepository = accountRepository;
        this.kafkaProducerService = kafkaProducerService;
    }

    @Override
    public AccountResponseDTO createAccount(AccountRequestDTO accountRequestDTO) {
        String accountNumber = UUID.randomUUID().toString();

        AccountEntity account = new AccountEntity();
        account.setCustomerId(accountRequestDTO.getCustomerId());
        account.setAccountType(accountRequestDTO.getAccountType());
        account.setCurrency(accountRequestDTO.getCurrency());
        account.setAccountNumber(accountNumber);
        account.setBalance(0.0);
        account.setCreatedAt(LocalDateTime.now());
        account.setUpdatedAt(null);
        account.setActive(true);
        account = accountRepository.save(account);

        kafkaProducerService.sendNewAccountMessage(account.getId().toString());
        return accountMapper.accountEntityToAccountResponseDTO(account);
    }

    @Override
    public AccountResponseDTO getAccountById(UUID id) {
        AccountEntity account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
        return accountMapper.accountEntityToAccountResponseDTO(account);
    }

    @Override
    public AccountResponseDTO getAccountByAccountNumber(String accountNumber) {
        AccountEntity account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
        return accountMapper.accountEntityToAccountResponseDTO(account);
    }


    @Override
    public List<AccountResponseDTO> getAllAccounts() {
        List<AccountEntity> accounts = accountRepository.findAll();
        return accountMapper.accountEntityListToAccountDtoList(accounts);
    }

    @Override
    public AccountResponseDTO updateAccount(UUID id, Double balance) {
        AccountEntity account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        account.setBalance(balance);
        account.setUpdatedAt(LocalDateTime.now());

        account = accountRepository.save(account);

        return accountMapper.accountEntityToAccountResponseDTO(account);
    }

    @Override
    public List<AccountResponseDTO> getAccountsByCustomerId(UUID customerId) {
        List<AccountEntity> accounts = accountRepository.findByCustomerIdAndIsActive(customerId, true);
        return accounts.stream()
                .map(accountMapper::accountEntityToAccountResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(UUID id) {
        AccountEntity account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        if (account.isActive()) {
            account.setActive(false);
            account.setUpdatedAt(LocalDateTime.now());
            accountRepository.save(account);

            kafkaProducerService.sendAccountDeletionMessage(id.toString());
        } else {
            throw new RuntimeException("Account is already inactive");
        }
    }
}
