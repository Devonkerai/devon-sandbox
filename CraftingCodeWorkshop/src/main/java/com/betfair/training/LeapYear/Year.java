package com.betfair.training.LeapYear;

public class Year {

    private int year;

    public Year(int year) {
        this.year = year;
    }

    public Boolean isLeapYear() {
        return isYearDivisibleBy(100) ? isYearDivisibleBy(400) : isYearDivisibleBy(4);
    }

    private boolean isYearDivisibleBy(int year) {
        return this.year % year == 0;
    }
}
