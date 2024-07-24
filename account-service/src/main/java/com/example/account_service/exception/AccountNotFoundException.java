package com.example.account_service.exception;

public class AccountNotFoundException extends RuntimeException {
    
    public AccountNotFoundException(String message) {
        super(message);
    }
}
