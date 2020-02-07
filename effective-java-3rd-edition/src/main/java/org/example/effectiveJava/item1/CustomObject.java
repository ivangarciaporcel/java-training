package org.example.effectiveJava.item1;

public class CustomObject {

    private static final CustomObject CUSTOM_OBJECT = new CustomObject(0);
    private int numberOfOperations;

    private CustomObject(int numberOfOperations) {
        this.numberOfOperations = numberOfOperations;
    }

    public static CustomObject getInstance() {
        return CUSTOM_OBJECT;
    }

    public int sumOf(int a, int b) {
        numberOfOperations++;
        return a + b;
    }

    public int multiplicationOf(int a, int b) {
        numberOfOperations++;
        return a * b;
    }

    public int getNumberOfOperations() {
        return this.numberOfOperations;
    }

}
