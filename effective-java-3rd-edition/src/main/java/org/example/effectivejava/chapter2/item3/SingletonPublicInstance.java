package org.example.effectivejava.chapter2.item3;

public class SingletonPublicInstance {

    public static SingletonPublicInstance INSTANCE = new SingletonPublicInstance();
    private int counter;

    private SingletonPublicInstance() {
        this.counter = 0;
    }

    public int getCounter() {
        return counter;
    }

    public void count() {
        this.counter++;
    }
}
