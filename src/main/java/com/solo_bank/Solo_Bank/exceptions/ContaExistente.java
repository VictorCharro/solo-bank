package com.solo_bank.Solo_Bank.exceptions;

public class ContaExistente extends RuntimeException {
    public ContaExistente(String message) {
        super(message);
    }
}
