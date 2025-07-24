package com.solo_bank.solobank.core.model;

import com.solo_bank.solobank.core.model.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {

    private final UUID senderId;
    private final UUID receiverId;
    private final LocalDateTime date;
    private final BigDecimal amount;
    private final TransactionType type;

    public Transaction(UUID senderId, UUID receiverId, LocalDateTime date, BigDecimal amount, TransactionType type) {
        if (senderId == null || receiverId == null || date == null || amount == null || type == null) {
            throw new IllegalArgumentException("Transaction fields cannot be null");
        }
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Transaction amount must be greater than zero");
        }
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.date = date;
        this.amount = amount;
        this.type = type;
    }

    public UUID getSenderId() {
        return senderId;
    }

    public UUID getReceiverId() {
        return receiverId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }

}
