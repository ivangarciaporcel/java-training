package org.example.effectivejava.chapter2.item5;

public class Animal {

    private final Mammal mammal;

    // Injecting dependency mammal into class Animal
    public Animal(Mammal mammal) {
        this.mammal = mammal;
    }

    public String moveAndSound() {
        mammal.walk();
        return mammal.sound();
    }

    public int getLifeTime() {
        return mammal.aproxYearsOfLife();
    }
}
