package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class BankAccountTest {
    private BankAccount account;
    @BeforeEach
    public void setUp() {
        account = new BankAccount("12345");
    }
    @Test
    public void testDepositPositiveAmount() {
        account.deposit(100.0);
        assertEquals(100.0, account.getBalance());
    }
    @Test
    public void testDepositNegativeAmount() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(-50.0);
        });
        assertEquals("O valor do depósito deve ser positivo.",
                exception.getMessage());
    }
    @Test
    public void testWithdrawWithinBalance() {
        account.deposit(200.0);
        account.withdraw(100.0);
        assertEquals(100.0, account.getBalance());
    }
    @Test
    public void testWithdrawExceedingBalance() {
        account.deposit(50.0);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(100.0);
        });
        assertEquals("Saldo insuficiente.", exception.getMessage());
    }
    @Test
    public void testTransferBetweenAccounts() {
        BankAccount accountB = new BankAccount("67890");
        account.deposit(200.0);
        account.transfer(accountB, 50.0);
        assertEquals(150.0, account.getBalance());
        assertEquals(50.0, accountB.getBalance());
    }
}