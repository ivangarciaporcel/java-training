package org.example.effectivejava.chapter2.item5;

public interface Mammal {

    default void walk() {
        System.out.println("Mammal is walking...");
    }

    public String sound();

    public int aproxYearsOfLife();
}
