package org.example.effectiveJava;

import org.example.effectiveJava.item2.Computer;
import org.junit.Test;

import java.math.BigDecimal;

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
}

