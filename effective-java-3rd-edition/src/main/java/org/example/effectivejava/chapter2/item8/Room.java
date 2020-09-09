package org.example.effectivejava.chapter2.item8;

import java.lang.ref.Cleaner;

/**
 * User: Ivan.Garcia
 * Time: 9/8/2020 5:44 PM
 */
// An autoclosable class using a cleaner as a safety net
public class Room implements AutoCloseable {
    private static final Cleaner CLEANER = Cleaner.create();

    //Resource that requires cleaning. Must not refer to Room!
    // It must be a static nested class because nonstatic nested classes contain references to their enclosing instances
    private static class State implements Runnable {
        int numJunkPiles;

        State(int numJunkPiles) {
            this.numJunkPiles = numJunkPiles;
        }

        // Invoked by close method or cleaner
        @Override
        public void run() {
            System.out.println("Cleaning room");
            numJunkPiles = 0;
        }
    }

    //The state of this room, shared with our cleanable
    private final State state;

    // Our cleanable. Cleans the room when it's eligible for gc
    private final Cleaner.Cleanable cleanable;

    public Room(int numJunkPiles) {
        state = new State(numJunkPiles);
        cleanable = CLEANER.register(this, state);
    }

    @Override
    public void close() {
        cleanable.clean();
    }
}
