package com.example.transfer_service.repository;

import com.example.transfer_service.model.TransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransferRepository extends JpaRepository<TransferEntity, UUID> {


    List<TransferEntity> findByFromAccountIdOrToAccountId(String accountId, String accountId1);
}
