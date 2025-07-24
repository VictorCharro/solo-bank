package com.solo_bank.solobank.core.usecase;

import com.solo_bank.solobank.core.model.Account;
import com.solo_bank.solobank.core.model.exceptions.NegativeValueException;

import java.math.BigDecimal;

public class DepositToAccountUseCase {

    public Account execute(Account account, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0){
            throw new NegativeValueException("The value must be greater than zero");
        }
        account.deposit(amount);
        return account;
    }
}
