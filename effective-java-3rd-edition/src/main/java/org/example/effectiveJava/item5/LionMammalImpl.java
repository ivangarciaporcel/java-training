package org.example.effectiveJava.item5;

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
