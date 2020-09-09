package org.example.effectiveJava.chapter2;

import org.example.effectiveJava.chapter2.item5.Animal;
import org.example.effectiveJava.chapter2.item5.DogMammalImpl;
import org.example.effectiveJava.chapter2.item5.LionMammalImpl;
import org.example.effectiveJava.chapter2.item5.Mammal;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Item5Test {

    /**
     * Item 5: Prefer dependency injection to hardwiring resources
     */
    @Test
    public void Get_InstantiatedMammal_LionInformation() {
        Mammal lion = new LionMammalImpl();
        Animal animal = new Animal(lion);
        assertNotNull(animal);
        assertEquals(lion.aproxYearsOfLife(), animal.getLifeTime());
        assertEquals(lion.sound(), animal.moveAndSound());
    }

    @Test
    public void Get_InstantiatedMammal_DogInformation() {
        Mammal dogMammal = new DogMammalImpl();
        Animal animal = new Animal(dogMammal);
        assertNotNull(animal);
        assertEquals(dogMammal.aproxYearsOfLife(), animal.getLifeTime());
        assertEquals(dogMammal.sound(), animal.moveAndSound());
    }
}
