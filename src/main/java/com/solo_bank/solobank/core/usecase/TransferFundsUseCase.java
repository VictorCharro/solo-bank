package com.solo_bank.solobank.core.usecase;

import com.solo_bank.solobank.core.model.Account;
import com.solo_bank.solobank.core.model.exceptions.InsufficientBalance;
import com.solo_bank.solobank.core.model.exceptions.NegativeValueException;

import java.math.BigDecimal;

public class TransferFundsUseCase {

    public void execute(Account sender, Account receiver, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new NegativeValueException("The value must be greater than zero");
        }
        if (amount.compareTo(sender.getBalance()) > 0) {
            throw new InsufficientBalance("Sender insufficient balance");
        }

        sender.withdrawal(amount);
        receiver.deposit(amount);
    }
}
