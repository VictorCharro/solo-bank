package com.solo_bank.solobank.core.usecase;

import com.solo_bank.solobank.core.model.Account;
import com.solo_bank.solobank.core.model.Transaction;
import com.solo_bank.solobank.core.model.enums.AccountType;
import com.solo_bank.solobank.core.model.exceptions.NegativeValueException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CreateAccountUseCase {

    public Account execute(AccountType type, BigDecimal initialBalance, List<Transaction> initialTransactions){
        if (initialBalance.compareTo(BigDecimal.ZERO) < 0){
            throw new NegativeValueException("The initial balance cannot be negative");
        }
        if (initialTransactions == null) {
            initialTransactions = new ArrayList<>();
        }
        if (type == null) {
            throw new IllegalArgumentException("Account type cannot be null");
        }

        UUID accountId = UUID.randomUUID();
        Account account = new Account(accountId, initialBalance, type, initialTransactions);
        return account;
    }
}