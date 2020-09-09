package org.example.effectiveJava.chapter2;

import org.example.effectiveJava.chapter2.item2.BasicComputer;
import org.example.effectiveJava.chapter2.item2.CompleteComputer;
import org.example.effectiveJava.chapter2.item2.Computer;
import org.junit.Test;

import java.math.BigDecimal;

import static org.example.effectiveJava.chapter2.item2.Accessories.*;
import static org.example.effectiveJava.chapter2.item2.MonitorSize.LARGE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Item2Test {

    /**
     * Item 2. Consider a builder when faced with many constructor parameters
     */
    @Test
    public void Get_ComputerInformation_ComputerInstance() {
        String name = "Computer test";
        String model = "dv7";
        long purchasedDate = System.currentTimeMillis();
        BigDecimal price = BigDecimal.valueOf(2000);
        Computer computer = new Computer.Builder(name)
                .model(model)
                .purchasedDate(purchasedDate)
                .price(price)
                .build();
        assertNotNull(computer);
        assertEquals(name, computer.getName());
        assertEquals(model, computer.getModel());
        assertEquals(purchasedDate, computer.getPurchasedDate());
        assertEquals(price, computer.getPrice());
    }

    /**
     * The Builder pattern is well suited to class hierarchies. Use a parallel hierarchy
     * of builders, each nested in the corresponding class. Abstract classes have
     * abstract builders; concrete classes have concrete builders.
     */
    @Test
    public void Instantiate_BasicAndCompleteComputerRequirements_ComputersWithAskedParts() {
        BasicComputer basicComputer = new BasicComputer.Builder()
                .addAccessory(KEYBOARD)
                .addAccessory(MOUSE)
                .addAccessory(CASE)
                .addAccessory(MONITOR)
                .addPrice(BigDecimal.valueOf(2000))
                .build();
        assertNotNull(basicComputer);
        assertEquals(4, basicComputer.getAccessories().size());
        assertEquals(2000, basicComputer.getPrice().longValue());

        CompleteComputer completeComputer = new CompleteComputer.Builder()
                .addAccessory(KEYBOARD)
                .addAccessory(MOUSE)
                .addAccessory(CASE)
                .addAccessory(MONITOR)
                .addMonitorSize(LARGE)
                .addAccessory(HEADPHONES)
                .addAccessory(MOUSEPAD)
                .addAccessory(CHAIR)
                .addAccessory(CAMERA)
                .addPrice(BigDecimal.valueOf(5000))
                .build();
        assertNotNull(completeComputer);
        assertEquals(8, completeComputer.getAccessories().size());
        assertEquals(5000, completeComputer.getPrice().longValue());
        assertEquals(LARGE, completeComputer.getMonitorSize());
    }
}

