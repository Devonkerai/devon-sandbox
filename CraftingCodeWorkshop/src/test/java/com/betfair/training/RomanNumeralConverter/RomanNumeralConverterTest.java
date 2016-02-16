package com.betfair.training.RomanNumeralConverter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class RomanNumeralConverterTest {

    private RomanNumeralConverter romanConverter;

    @Before
    public void setup() {
        romanConverter = new RomanNumeralConverter();
    }

    @Test
    public void DecimalToRomanEquivalent() {
        assertThat(romanConverter.convert(1), is("I"));
        assertThat(romanConverter.convert(3), is("III"));
        assertThat(romanConverter.convert(4), is("IV"));
        assertThat(romanConverter.convert(5), is("V"));
        assertThat(romanConverter.convert(7), is("VII"));
        assertThat(romanConverter.convert(9), is("IX"));
        assertThat(romanConverter.convert(10), is("X"));
        assertThat(romanConverter.convert(13), is("XIII"));
        assertThat(romanConverter.convert(18), is("XVIII"));
        assertThat(romanConverter.convert(28), is("XXVIII"));
        assertThat(romanConverter.convert(50), is("L"));
        assertThat(romanConverter.convert(100), is("C"));
        assertThat(romanConverter.convert(500), is("D"));
        assertThat(romanConverter.convert(1000), is("M"));
    }


}
