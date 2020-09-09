package org.example.effectivejava.chapter2.item7;

import java.util.Arrays;
import java.util.EmptyStackException;

import static com.google.common.base.Preconditions.checkArgument;

public class Stack {

    private int INITIAL_SIZE = 16;
    private Object[] elements;
    private int size;

    public Stack() {
        elements = new Object[INITIAL_SIZE];
        size = 0;
    }

    public void push(Object o) {
        checkArgument(o != null);
        ensureAvailability();
        elements[size++] = o;
    }

    @Deprecated
    public Object pop() {
        if (size == 0)
            throw new EmptyStackException();
        return elements[--size];
    }

    public Object improvedPop() {
        if (size == 0)
            throw new EmptyStackException();
        Object o = elements[--size];
        elements[size] = null; // Eliminate obsolete reference
        return o;
    }

    private void ensureAvailability() {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }

    public int size() {
        return this.size;
    }
}
