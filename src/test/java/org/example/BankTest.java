package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankTest {
    private Bank bank;

    @BeforeEach
    public void setUp() {
        bank = new Bank();
    }

    @Test
    public void testCreateAccount() {
        BankAccount account = bank.createAccount("12345");
        assertNotNull(account);
        assertEquals("12345", bank.getAccount("12345").getAccountNumber());
    }

    @Test
    public void testCreateDuplicateAccount() {
        bank.createAccount("12345");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            bank.createAccount("12345");
        });
        assertEquals("Conta já existente.", exception.getMessage());
    }

    @Test
    public void testTransferFundsBetweenAccounts() {
        bank.createAccount("12345").deposit(200.0);
        bank.createAccount("67890");
        bank.transferFunds("12345", "67890", 50.0);
        assertEquals(150.0, bank.getAccount("12345").getBalance());
        assertEquals(50.0, bank.getAccount("67890").getBalance());
    }

    @Test
    public void testTransferFundsFromNonExistentAccount() {
        bank.createAccount("67890");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            bank.transferFunds("12345", "67890", 50.0);
        });
        assertEquals("Conta(s) inválida(s).", exception.getMessage());
    }
}