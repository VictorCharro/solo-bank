package com.solo_bank.solobank.core.usecase;

import com.solo_bank.solobank.core.model.Account;
import com.solo_bank.solobank.core.model.Transaction;
import com.solo_bank.solobank.core.model.enums.AccountType;
import com.solo_bank.solobank.core.model.exceptions.InsufficientBalance;
import com.solo_bank.solobank.core.model.exceptions.NegativeValueException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WithdrawalFromAccountUseCaseTest {

    @Test
    void shouldWithdrawalTest() {
        CreateAccountUseCase useCase = new CreateAccountUseCase();
        AccountType type = AccountType.SAVINGS;
        BigDecimal initialBalance = BigDecimal.valueOf(100);
        ArrayList<Transaction> transactions = new ArrayList<>();

        Account account = useCase.execute(type, initialBalance, transactions);
        WithdrawFromAccountUseCase withdrawUseCase = new WithdrawFromAccountUseCase();

        BigDecimal withdrawalAmount = BigDecimal.valueOf(10);
        Account updateAccount = withdrawUseCase.execute(account, withdrawalAmount);

        assertNotNull(updateAccount);
        assertEquals(BigDecimal.valueOf(90), updateAccount.getBalance());

    }

    @Test
    void shouldThrowExceptionWhenWithdrawalIsGreaterThanBalance() {
        CreateAccountUseCase useCase = new CreateAccountUseCase();
        AccountType type = AccountType.SAVINGS;
        BigDecimal initialBalance = BigDecimal.valueOf(100);
        ArrayList<Transaction> transactions = new ArrayList<>();

        Account account = useCase.execute(type, initialBalance, transactions);
        WithdrawFromAccountUseCase withdrawUseCase = new WithdrawFromAccountUseCase();

        BigDecimal excessiveAmount = BigDecimal.valueOf(150);

        assertThrows(InsufficientBalance.class, () -> {
            withdrawUseCase.execute(account, excessiveAmount);
        });
    }

    @Test
    void shouldThrowExceptionWhenWithdrawalIsZeroOrNegative() {
        CreateAccountUseCase useCase = new CreateAccountUseCase();
        AccountType type = AccountType.SAVINGS;
        BigDecimal initialBalance = BigDecimal.valueOf(100);
        ArrayList<Transaction> transactions = new ArrayList<>();

        Account account = useCase.execute(type, initialBalance, transactions);
        WithdrawFromAccountUseCase withdrawUseCase = new WithdrawFromAccountUseCase();

        BigDecimal invalidAmount = BigDecimal.ZERO;

        assertThrows(NegativeValueException.class, () -> {
            withdrawUseCase.execute(account, invalidAmount);
        });
    }

}
