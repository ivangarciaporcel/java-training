package org.example.effectiveJava;

import org.example.effectiveJava.item7.Stack;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class Item7Test {

    /**
     * Item 7: Eliminate obsolete object references
     */

    /**
     * This method uses deprecated {@code Stack.pop} method, which will eventually cause a Memory leak, because of an
     * obsolete reference, each time an element is popped an obsolete reference will remain to the retrieved element,
     * it will not be collected for the garbage collector, so it will remain in memory
     */
    @Test
    public void Add_IntElement_StackWithElements() {
        Stack stack = new Stack();
        assertEquals(0, stack.size());
        int firstElement = 1;
        stack.push(firstElement);
        assertEquals(1, stack.size());
        int object = (int) stack.pop();
        assertEquals(firstElement, object);
    }

    /**
     * This method uses {@code Stack.improvedPop} method, which after retrieving an element, nullifies the reference
     * to it, in this way the reference in memory will be deleted immediately, avoiding keeping obsolete references
     */
    @Test
    public void Retrieve_IntElements_IntElementsFromStack() {
        Stack stack = new Stack();
        assertEquals(0, stack.size());
        int firstElement = 1;
        stack.push(firstElement);
        assertEquals(1, stack.size());
        int object = (int) stack.improvedPop();
        assertEquals(firstElement, object);
    }
}
