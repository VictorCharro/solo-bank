package com.solo_bank.solobank.core.usecase;

import com.solo_bank.solobank.core.model.Account;
import com.solo_bank.solobank.core.model.Transaction;
import com.solo_bank.solobank.core.model.enums.AccountType;
import com.solo_bank.solobank.core.model.exceptions.NegativeValueException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateAccountUseCaseTest {

    @Test
    void shouldCreateAccountSuccessfully() {
        CreateAccountUseCase useCase = new CreateAccountUseCase();
        AccountType type = AccountType.CHECKING;  // ou outro tipo válido
        BigDecimal initialBalance = BigDecimal.valueOf(1000);
        ArrayList<Transaction> transactions = new ArrayList<>();

        Account account = useCase.execute(type, initialBalance, transactions);

        assertNotNull(account);
        assertEquals(type, account.getType());
        assertEquals(initialBalance, account.getBalance());
        assertNotNull(account.getId());
        assertTrue(account.getTransactions().isEmpty());
    }

    @Test
    void shouldThrowExceptionForNegativeBalance() {
        CreateAccountUseCase useCase = new CreateAccountUseCase();
        AccountType type = AccountType.SAVINGS;
        BigDecimal initialBalance = BigDecimal.valueOf(-10);
        ArrayList<Transaction> transactions = new ArrayList<>();

        Exception exception = assertThrows(NegativeValueException.class, () -> {
            useCase.execute(type, initialBalance, transactions);
        });

        assertEquals("The initial balance cannot be negative", exception.getMessage());
    }
}
