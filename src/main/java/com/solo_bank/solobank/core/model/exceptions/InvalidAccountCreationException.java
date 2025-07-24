package com.solo_bank.solobank.core.model.exceptions;

public class InvalidAccountCreationException extends RuntimeException {
    public InvalidAccountCreationException(String message) {
        super(message);
    }
}
