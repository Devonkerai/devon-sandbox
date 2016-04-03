package com.betfair.training.BankKata;

import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {

    private List<Transaction> transactions = new ArrayList<>();

    public void add(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactions;
    }
}
