package com.betfair.training.BankKata;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class AccountServiceShould {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private AccountService accountService;
    @Mock
    private Clock clock;
    private Console console;
    private TransactionRepository transactionRepository;
    private StatementPrinter statementPrinter;


    @Before
    public void setup() {
        System.setOut(new PrintStream(outputStream));
        initMocks(this);
        transactionRepository = new TransactionRepository();
        console = new Console();
        statementPrinter = new StatementPrinter(console);
        accountService = new AccountService(clock, transactionRepository, statementPrinter);

        when(clock.dateAsString()).thenReturn("16/02/2014");
    }

    @Test
    public void printEmptyStatement() {

        String expectedValue = "  DATE       | AMOUNT  | BALANCE\n";

        accountService.printStatement();

        assertEquals(expectedValue, outputStream.toString());
    }

    @Test
    public void depositMoney() {

        Transaction expectedTransaction = new Transaction("16/02/2014", 500);

        accountService.deposit(500);
        assertEquals(expectedTransaction, transactionRepository.getAllTransactions().get(0));
    }

    @Test
    public void withDrawMoney() {

        Transaction expectedTransaction = new Transaction("16/02/2014", -500);

        accountService.withdraw(500);
        assertEquals(expectedTransaction, transactionRepository.getAllTransactions().get(0));
    }
}
