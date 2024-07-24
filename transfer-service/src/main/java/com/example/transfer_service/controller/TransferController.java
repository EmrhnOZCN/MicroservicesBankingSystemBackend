package com.example.transfer_service.controller;

import com.example.transfer_service.dto.TransferRequestDTO;
import com.example.transfer_service.dto.TransferResponseDTO;
import com.example.transfer_service.exception.AccountNotFoundException;
import com.example.transfer_service.exception.InsufficientFundsException;
import com.example.transfer_service.service.TransferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/transfers")
@RequiredArgsConstructor
public class TransferController {

    private final TransferService transferService;

    @PostMapping
    public ResponseEntity<TransferResponseDTO> createTransfer(@Valid @RequestBody TransferRequestDTO transferRequestDTO) {
        System.out.println(transferRequestDTO.getFromAccount());
        try {
            TransferResponseDTO transferResponseDTO = transferService.processTransfer(transferRequestDTO);
            return new ResponseEntity<>(transferResponseDTO, HttpStatus.CREATED);
        } catch (AccountNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (InsufficientFundsException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping()
    public ResponseEntity<List<TransferResponseDTO>> getTransfersByAccountId(@RequestParam String accountId) {
        List<TransferResponseDTO> transfers = transferService.getTransfersByAccountId(accountId);
        return ResponseEntity.ok(transfers);
    }

}
