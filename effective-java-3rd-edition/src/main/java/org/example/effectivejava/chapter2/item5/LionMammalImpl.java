package org.example.effectivejava.chapter2.item5;

public class LionMammalImpl implements Mammal {

    @Override
    public String sound() {
        return "roaar";
    }

    @Override
    public int aproxYearsOfLife() {
        return 20;
    }
}
