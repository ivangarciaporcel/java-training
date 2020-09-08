package org.example.effectiveJava.utils;

import java.util.Objects;

/**
 * User: Ivan.Garcia
 * Time: 9/8/2020 6:22 PM
 */
public class JavaEffectiveUtils {

    public static String getPathOfFile(String fileName) {
        ClassLoader classLoader = JavaEffectiveUtils.class.getClassLoader();
        return Objects.requireNonNull(classLoader.getResource(fileName)).getPath();
    }
}
