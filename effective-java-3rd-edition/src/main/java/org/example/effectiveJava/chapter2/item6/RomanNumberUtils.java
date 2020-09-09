package org.example.effectiveJava.chapter2.item6;

import java.util.regex.Pattern;

public class RomanNumberUtils {

    private static final Pattern ROMAN = Pattern.compile("^(?=.)M*(C[MD]|D?C{0,3})"
            + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");

    private RomanNumberUtils() {
        throw new RuntimeException("Class cannot be instantiated");
    }

    /**
     * Reusing expensive {@code ROMAN} object for improved performance
     *
     * @param s number to be evaluated
     * @return true if {@code s} is a valid roman number, otherwise returns false
     */
    public static boolean isValid(String s) {
        return ROMAN.matcher(s).matches();
    }

    /**
     * Method to detect if {@code s} is a valid roman number
     * String.matches is not suitable for repeated use in performance-critical situations,
     * it internally creates a {@code Pattern} instance for the regular expression each time is invoked
     * @param s
     * @return
     */
    @Deprecated
    public static boolean validateRomanNumber(String s) {
        return s.matches("^(?=.)M*(C[MD]|D?C{0,3})"
                + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
    }
}
