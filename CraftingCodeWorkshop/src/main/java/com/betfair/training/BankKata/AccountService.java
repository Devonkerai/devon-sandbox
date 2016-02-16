package com.betfair.training.BankKata;

public class AccountService {

    private Clock clock;
    private TransactionRepository transactionRepository;
    private StatementPrinter statementPrinter;

    public AccountService(Clock clock, TransactionRepository transactionRepository, StatementPrinter statementPrinter) {
        this.clock = clock;
        this.transactionRepository = transactionRepository;
        this.statementPrinter = statementPrinter;
    }

    public void printStatement() {
        statementPrinter.print(transactionRepository.getAllTransactions());
    }

    public void deposit(int amount) {
        transactionRepository.add(new Transaction(clock.dateAsString(), amount));
    }

    public void withdraw(int amount) {
        transactionRepository.add(new Transaction(clock.dateAsString(), -amount));
    }
}
