package org.example.effectiveJava.chapter2;

import org.example.effectiveJava.chapter2.item4.ItemUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Item4Test {

    /**
     * Item 4: Enforce noninstantiability with a private constructor
     */
    @Test
    public void Get_ListOfNumbers_SumOfNumbers() {
        int sum = ItemUtils.sumElements(1, 2, 3);
        assertEquals(6, sum);
        sum = ItemUtils.sumElements(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        assertEquals(55, sum);
    }

    @Test
    public void Get_ListOfNumbers_Average() {
        double average = ItemUtils.average(1, 2, 3);
        assertEquals(2, average, 0.0);
        average = ItemUtils.average(1, 2, 3, 4);
        assertEquals(2.5, average, 0.0);
        Integer[] v = new Integer[]{1, 2};
        average = ItemUtils.average(v);
        assertEquals(1.5, average, 0.0);
    }
}
