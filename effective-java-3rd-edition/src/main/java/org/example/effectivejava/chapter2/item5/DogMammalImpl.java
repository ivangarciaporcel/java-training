package org.example.effectivejava.chapter2.item5;

public class DogMammalImpl implements Mammal {

    @Override
    public String sound() {
        return "guau";
    }

    @Override
    public int aproxYearsOfLife() {
        return 15;
    }
}
