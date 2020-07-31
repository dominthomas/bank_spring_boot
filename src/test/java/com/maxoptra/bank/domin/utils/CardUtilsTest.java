package com.maxoptra.bank.domin.utils;

import org.junit.jupiter.api.Test;

public class CardUtilsTest {

    /**
     * Test for both even & odd numbers, including min & max.
     */

    @Test
    public void test_card_obfuscator_16_digits(){
        long cardNum = 1111222233334444L;
        String expected = "XXXX-XXXX-XXXX-4444";
        String o = CardUtils.obfuscateCardNumber(cardNum);
        assert(expected.equals(o));
    }

    @Test
    public void test_card_obfuscator_19_digits(){
        long cardNum = 1111222233334444555L;
        String expected = "XXXX-XXXX-XXXX-XXX4-555";
        String o = CardUtils.obfuscateCardNumber(cardNum);
        assert(expected.equals(o));
    }

    @Test
    public void test_card_obfuscator_9_digits(){
        long cardNum = 111122223L;
        String expected = "XXXX-X222-3";
        String o = CardUtils.obfuscateCardNumber(cardNum);
        assert(expected.equals(o));
    }
}
