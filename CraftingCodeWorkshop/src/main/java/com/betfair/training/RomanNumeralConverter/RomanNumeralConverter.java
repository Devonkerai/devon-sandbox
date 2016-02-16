package com.betfair.training.RomanNumeralConverter;

import java.util.LinkedHashMap;
import java.util.Map;

public class RomanNumeralConverter {

    private static LinkedHashMap<Integer, String> decimalToRomanDictionary = new LinkedHashMap<>();

    static {
        decimalToRomanDictionary.put(1000, "M");
        decimalToRomanDictionary.put(500, "D");
        decimalToRomanDictionary.put(100, "C");
        decimalToRomanDictionary.put(50, "L");
        decimalToRomanDictionary.put(10, "X");
        decimalToRomanDictionary.put(9, "IX");
        decimalToRomanDictionary.put(5, "V");
        decimalToRomanDictionary.put(4, "IV");
        decimalToRomanDictionary.put(1, "I");
    }

    public String convert(int number) {
        String string = "";
        for (Map.Entry<Integer, String> entry : decimalToRomanDictionary.entrySet()) {
            while (number >= entry.getKey()) {
                string += entry.getValue();
                number -= entry.getKey();
            }
        }
        return string;
    }
}
