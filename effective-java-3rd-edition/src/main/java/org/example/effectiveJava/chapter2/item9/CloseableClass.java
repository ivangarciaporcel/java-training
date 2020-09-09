package org.example.effectiveJava.chapter2.item9;

import java.lang.ref.Cleaner;

/**
 * User: Ivan.Garcia
 * Time: 9/8/2020 6:36 PM
 */
public class CloseableClass implements AutoCloseable {
    private static final Cleaner CLEANER = Cleaner.create();

    private static class State implements Runnable {

        @Override
        public void run() {
            System.out.println("Closing object");
        }
    }

    private final Cleaner.Cleanable cleanable;

    public CloseableClass() {
        State state = new State();
        cleanable = CLEANER.register(this, state);
    }

    @Override
    public void close() throws Exception {
        cleanable.clean();
    }
}
