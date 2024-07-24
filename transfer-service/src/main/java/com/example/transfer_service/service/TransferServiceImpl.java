package com.example.transfer_service.service;

import com.example.transfer_service.client.AccountClient;
import com.example.transfer_service.dto.AccountResponseDTO;
import com.example.transfer_service.dto.TransferRequestDTO;
import com.example.transfer_service.dto.TransferResponseDTO;
import com.example.transfer_service.exception.AccountNotFoundException;
import com.example.transfer_service.exception.InsufficientFundsException;
import com.example.transfer_service.model.TransferEntity;
import com.example.transfer_service.repository.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

    private final TransferRepository transferRepository;
    private final AccountClient accountClient;

    @Transactional
    @Override
    public TransferResponseDTO processTransfer(TransferRequestDTO requestDTO) {


        AccountResponseDTO fromAccount = accountClient.getAccountByAccountNumber(requestDTO.getFromAccount());
        AccountResponseDTO toAccount = accountClient.getAccountByAccountNumber(requestDTO.getToAccount());


        // Hesapların varlığını ve yeterli bakiyeyi kontrol et
        if (fromAccount == null) {
            throw new AccountNotFoundException("Gönderen hesap bulunamadı.");
        }

        if (toAccount == null) {
            throw new AccountNotFoundException("Alıcı hesap bulunamadı.");
        }


        if (fromAccount.getBalance() < requestDTO.getAmount()) {
            throw new InsufficientFundsException("Yetersiz bakiye.");
        }

        fromAccount.setBalance(fromAccount.getBalance() - requestDTO.getAmount());
        toAccount.setBalance(toAccount.getBalance() + requestDTO.getAmount());

        accountClient.updateAccount(fromAccount.getId(), fromAccount.getBalance());
        accountClient.updateAccount(toAccount.getId(), toAccount.getBalance());

        TransferEntity transferEntity = new TransferEntity();
        transferEntity.setFromAccountId(requestDTO.getFromAccount());
        transferEntity.setToAccountId(requestDTO.getToAccount());
        transferEntity.setAmount(requestDTO.getAmount());
        transferEntity.setTransferDate(LocalDateTime.now());
        transferEntity.setCurrency(fromAccount.getCurrency());
        transferEntity.setTransferStatus(true);
        TransferEntity savedTransfer = transferRepository.save(transferEntity);

        TransferResponseDTO responseDTO = new TransferResponseDTO();
        responseDTO.setId(savedTransfer.getId());
        responseDTO.setFromAccount(savedTransfer.getFromAccountId().toString());
        responseDTO.setToAccount(savedTransfer.getToAccountId().toString());
        responseDTO.setAmount(savedTransfer.getAmount());
        responseDTO.setTransferDate(savedTransfer.getTransferDate());

        return responseDTO;
    }

    @Override
    public List<TransferResponseDTO> getTransfersByAccountId(String accountId) {
        List<TransferEntity> transfers = transferRepository.findByFromAccountIdOrToAccountId(accountId, accountId);
        return transfers.stream().map(transfer -> new TransferResponseDTO(
                transfer.getId(),
                transfer.getFromAccountId(),
                transfer.getToAccountId(),
                transfer.getAmount(),
                transfer.getTransferDate(),
                transfer.getCurrency()
        )).collect(Collectors.toList());
    }

}
