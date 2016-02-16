package com.betfair.training.LeapYear;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class YearShould {

    private Year year;

    @Test
    public void willDivideBy400() {
        year = new Year(1600);
        assertTrue(year.isLeapYear());
    }

    @Test
    public void willDivideBy4() {
        year = new Year(16);
        assertTrue(year.isLeapYear());
    }

    @Test
    public void willNotDivideBy4() {
        year = new Year(17);
        assertFalse(year.isLeapYear());
    }

    @Test
    public void willNotDivideBy100() {
        year = new Year(200);
        assertFalse(year.isLeapYear());
    }

}
