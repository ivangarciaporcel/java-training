package org.example.effectiveJava.chapter2.item3;

public enum EnumSingleton {

    ENUM_SINGLETON;

    EnumSingleton() {
        this.counter = 1;
    }

    private int counter;

    public int getCounter() {
        return this.counter;
    }

    public void count() {
        this.counter++;
    }
}
