package com.betfair.training.BankKata;

import java.util.List;

public class StatementPrinter {

    private Console console;

    private static final String STATEMENT_COLUMNS = "  DATE       | AMOUNT  | BALANCE\n";

    public StatementPrinter(Console console) {
        this.console = console;
    }

    public void print(List<Transaction> transactions) {

        StringBuilder stringBuilder = new StringBuilder(STATEMENT_COLUMNS);

        for (Transaction transaction : transactions) {
            stringBuilder.append(transaction);
        }
        console.print(stringBuilder.toString());
    }
}
