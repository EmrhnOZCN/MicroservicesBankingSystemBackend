package com.example.account_service.repository;

import com.example.account_service.model.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<AccountEntity, UUID> {
    List<AccountEntity> findByCustomerIdAndIsActive(UUID customerId, boolean isActive);

    Optional<AccountEntity> findByAccountNumber(String accountNumber);
}
