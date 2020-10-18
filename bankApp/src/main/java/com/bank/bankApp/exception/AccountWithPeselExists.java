package com.bank.bankApp.exception;

public class AccountWithPeselExists extends RuntimeException {
    public AccountWithPeselExists(String message) {
        super(message);
    }
}
