package org.example.effectivejava.chapter2;

import org.example.effectivejava.chapter2.item6.RomanNumberUtils;
import org.junit.Test;

import static org.junit.Assert.*;

public class Item6Test {

    /**
     * Item 6: Avoid creating unnecessary objects, when you should reuse an existing one
     */
    @Test
    public void Fail_StringObject_AnotherInstance() {

        // will create a new object instance, do not do it
        String s = new String("initialValue");

        //this type of assignation allows reusing same object and only changing
        //its value
        String sReusable = "initialValue";
    }

    @Test
    public void Validate_RomanNumber_IsValidNumber() {
        String romanNumber = "MMD";
        // Performance can be improved
        assertTrue(RomanNumberUtils.validateRomanNumber(romanNumber));

        String validRomanNumber = "MCD";
        // Reusing expensive object for improved performance
        assertTrue(RomanNumberUtils.isValid(validRomanNumber));

        String invalidRomanNumber = "XFD12";
        assertFalse(RomanNumberUtils.isValid(invalidRomanNumber));
    }

    /**
     * Prefer primitives to boxed primitives, and watch for unintentionally autoboxing
     */
    @Test
    public void Get_IntValues_SumAsLong() {
        // By using Long instead of primitive long, each iteration of the for will unnecessarily create a Long instance
        // and will be used once to be added to the sum result
        Long sum = 0L;
        for (long i = 0; i <= Integer.MAX_VALUE; i++) {
            sum += i;
        }

        // by using primitive value, the same sumLong instance will be reused for each iteration of the sum, which
        // improves the efficiency and use of resources in memory
        long sumLong = 0L;
        for (long i = 0; i <= Integer.MAX_VALUE; i++) {
            sumLong += i;
        }

        assertEquals(sum.longValue(), sumLong);
    }


}
