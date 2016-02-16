package com.betfair.training.BankKata;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class BankTransactionsTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private AccountService accountService;
    @Mock
    private Clock clock;
    @Mock
    private Console console;
    private TransactionRepository transactionRepository;
    private StatementPrinter statementPrinter;

    @Before
    public void setup() {

        System.setOut(new PrintStream(outputStream));
        initMocks(this);
        transactionRepository = new TransactionRepository();
        statementPrinter = new StatementPrinter(console);
        accountService = new AccountService(clock, transactionRepository, statementPrinter);

        when(clock.dateAsString()).thenReturn("16/02/2014");
    }

    @Test
    public void printBankStatement() {
        accountService = new AccountService(clock, transactionRepository, statementPrinter);
        accountService.deposit(1000);
        accountService.withdraw(100);
        accountService.deposit(500);

        accountService.printStatement();

        assertEquals(outputStream.toString(),
                "  DATE       | AMOUNT  | BALANCE\n" +
                "  10/04/2014 | 500  | 500\n" +
                "  02/04/2014 | -100 | 900\n" +
                "  01/04/2014 | 1000 | 1000");
    }
}
