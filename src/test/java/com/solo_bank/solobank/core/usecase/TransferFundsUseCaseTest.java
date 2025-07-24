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

public class TransferFundsUseCaseTest {

    @Test
    void shouldTransferTest() {
        CreateAccountUseCase createSenderAccountUseCase = new CreateAccountUseCase();
        CreateAccountUseCase createReceiverAccountUseCase = new CreateAccountUseCase();
        AccountType type = AccountType.SAVINGS;
        BigDecimal initialAmount = BigDecimal.valueOf(100);
        ArrayList<Transaction> transactions = new ArrayList<>();

        Account senderAccount = createSenderAccountUseCase.execute(type, initialAmount, transactions);
        Account receiverAccount = createReceiverAccountUseCase.execute(type, initialAmount, transactions);
        TransferFundsUseCase transferFundsUseCase = new TransferFundsUseCase();

        BigDecimal transferAmount = BigDecimal.valueOf(10);
        transferFundsUseCase.execute(senderAccount, receiverAccount, transferAmount);

        assertNotNull(senderAccount);
        assertNotNull(receiverAccount);
        assertEquals(BigDecimal.valueOf(90), senderAccount.getBalance());
        assertEquals(BigDecimal.valueOf(110), receiverAccount.getBalance());
    }

    @Test
    void shouldThrowInsufficientBalanceException() {
        CreateAccountUseCase createSenderAccountUseCase = new CreateAccountUseCase();
        CreateAccountUseCase createReceiverAccountUseCase = new CreateAccountUseCase();
        AccountType type = AccountType.SAVINGS;
        BigDecimal initialAmount = BigDecimal.valueOf(100);
        ArrayList<Transaction> transactions = new ArrayList<>();

        Account senderAccount = createSenderAccountUseCase.execute(type, initialAmount, transactions);
        Account receiverAccount = createReceiverAccountUseCase.execute(type, initialAmount, transactions);
        TransferFundsUseCase transferFundsUseCase = new TransferFundsUseCase();

        BigDecimal transferAmount = BigDecimal.valueOf(1000);

        assertThrows(InsufficientBalance.class, () -> {
            transferFundsUseCase.execute(senderAccount, receiverAccount, transferAmount);
        });
    }

    @Test
    void shouldThrowNegativeValueException() {
        CreateAccountUseCase createSenderAccountUseCase = new CreateAccountUseCase();
        CreateAccountUseCase createReceiverAccountUseCase = new CreateAccountUseCase();
        AccountType type = AccountType.SAVINGS;
        BigDecimal initialAmount = BigDecimal.valueOf(100);
        ArrayList<Transaction> transactions = new ArrayList<>();

        Account senderAccount = createSenderAccountUseCase.execute(type, initialAmount, transactions);
        Account receiverAccount = createReceiverAccountUseCase.execute(type, initialAmount, transactions);
        TransferFundsUseCase transferFundsUseCase = new TransferFundsUseCase();

        BigDecimal transferAmount = BigDecimal.valueOf(-10);

        assertThrows(NegativeValueException.class, () -> {
            transferFundsUseCase.execute(senderAccount, receiverAccount, transferAmount);
        });
    }
}
