package com.example.transfer_service.service;

import com.example.transfer_service.dto.TransferRequestDTO;
import com.example.transfer_service.dto.TransferResponseDTO;

import java.util.List;
import java.util.UUID;

public interface TransferService {

    TransferResponseDTO processTransfer(TransferRequestDTO requestDTO);

    List<TransferResponseDTO> getTransfersByAccountId(String accountId);


}
