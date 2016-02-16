package com.betfair.training.BankKata;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Clock {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public String dateAsString() {
        return LocalDate.now().format(DATE_TIME_FORMATTER);
    }
}
