package com.solo_bank.solobank.core.usecase;

import com.solo_bank.solobank.core.model.Account;
import com.solo_bank.solobank.core.model.exceptions.InsufficientBalance;
import com.solo_bank.solobank.core.model.exceptions.NegativeValueException;

import java.math.BigDecimal;

public class WithdrawFromAccountUseCase {

    public Account execute(Account account, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0){
            throw new NegativeValueException("The value must be greater than zero");
        }
        if (amount.compareTo(account.getBalance()) > 0){
            throw new InsufficientBalance("Insufficient balance");
        }
        account.withdrawal(amount);
        return account;
    }
}
