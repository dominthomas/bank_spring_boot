package com.maxoptra.bank.domin.utils;

import java.util.Arrays;

public class CardUtils {

    private static final char SEPARATOR = '-';

    private static final int SEPARATOR_INTERVAL = 4;

    /**
     *
     * @param cardNumber >= 9 && <=19 tested.
     * @return String - e.g. 1111 2222 3333 will become "XXXX-XXXX-3333"
     */
    public static String obfuscateCardNumber(Long cardNumber) {
        int cardNumberLength = (int) (Math.log10(cardNumber) + 1);
        String lastFourDigits = Long.toString(cardNumber % 10000);
        return addDashes(createX(cardNumberLength - SEPARATOR_INTERVAL, 'X') + lastFourDigits);
    }

    private static String createX(int length, char letter) {
        if (length > 0) {
            char[] array = new char[length];
            Arrays.fill(array, letter);
            return new String(array);
        }
        // Would create a custom exception if method was public.
        return "____";
    }

    private static String addDashes(String s) {
        StringBuilder sb = new StringBuilder(s);

        for (int i = 0; i < s.length() / SEPARATOR_INTERVAL; i++) {
            int currentInterval = ((i + 1) * SEPARATOR_INTERVAL) + i;
            if (currentInterval <= s.length()) {
                sb.insert(currentInterval, SEPARATOR);
            }
        }
        return sb.toString();
    }
}
