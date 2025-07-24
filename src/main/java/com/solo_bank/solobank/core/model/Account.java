package com.solo_bank.solobank.core.model;

import com.solo_bank.solobank.core.model.enums.AccountType;
import com.solo_bank.solobank.core.model.exceptions.InsufficientBalance;
import com.solo_bank.solobank.core.model.exceptions.InvalidAccountCreationException;
import com.solo_bank.solobank.core.model.exceptions.NegativeValueException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.solo_bank.solobank.core.model.enums.TransactionType.DEPOSIT;
import static com.solo_bank.solobank.core.model.enums.TransactionType.WITHDRAWAL;

public class Account {

    private final UUID id;
    private BigDecimal balance;
    private final AccountType type;
    private final List<Transaction> transactions;

    public Account(UUID id, BigDecimal balance, AccountType type, List<Transaction> transactions) {
        if (balance == null || balance.compareTo(BigDecimal.ZERO) < 0)
            throw new InvalidAccountCreationException("Invalid balance");
        if (type == null)
            throw new InvalidAccountCreationException("Account type cannot be null");
        if (transactions == null)
            throw new InvalidAccountCreationException("Transaction list cannot be null");

        this.id = id;
        this.balance = balance;
        this.type = type;
        this.transactions = transactions;
    }

    public UUID getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public AccountType getType() {
        return type;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void deposit(BigDecimal amount){
        if (amount.compareTo(BigDecimal.ZERO) <= 0){
            throw new NegativeValueException("The value must be greater than zero");
        }
        Transaction transaction = new Transaction(null, id, LocalDateTime.now(), amount, DEPOSIT);
        transactions.add(transaction);
        balance = balance.add(amount);
    }

    public void withdrawal(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0){
            throw new NegativeValueException("The value must be greater than zero");
        }
        if (amount.compareTo(balance) > 0) {
            throw new InsufficientBalance("Insufficient Balance");
        }
        Transaction transaction = new Transaction(id, null, LocalDateTime.now(), amount, WITHDRAWAL);
        transactions.add(transaction);
        balance = balance.subtract(amount);
    }
}
