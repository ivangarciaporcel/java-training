package org.example.effectiveJava.chapter2.item3;

public class SingletonPrivateInstance {

    private static SingletonPrivateInstance singletonPrivateInstance = new SingletonPrivateInstance();
    private int counter;

    private SingletonPrivateInstance() {
        this.counter = 0;
    }

    public static SingletonPrivateInstance getInstance() {
        return singletonPrivateInstance;
    }

    public int getCounter() {
        return counter;
    }

    public void count() {
        this.counter++;
    }
}
