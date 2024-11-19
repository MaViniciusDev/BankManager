package org.example;

import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<String, BankAccount> accounts = new HashMap<>();

    public BankAccount createAccount(String accountNumber) {
        if (accounts.containsKey(accountNumber)) {
            throw new IllegalArgumentException("Conta já existente.");
        }
        BankAccount account = new BankAccount(accountNumber);
        accounts.put(accountNumber, account);
        return account;
    }

    public BankAccount getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public void transferFunds(String fromAccountNumber, String toAccountNumber, double amount) {
        BankAccount fromAccount = getAccount(fromAccountNumber);
        BankAccount toAccount = getAccount(toAccountNumber);
        if (fromAccount == null || toAccount == null) {
            throw new IllegalArgumentException("Conta(s) inválida(s).");
        }
        fromAccount.transfer(toAccount, amount);
    }
}