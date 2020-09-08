package org.example.effectiveJava;

import org.example.effectiveJava.item8.Room;
import org.junit.Test;

/**
 * Avoid finalizers and cleaners
 * 1.- Cleaners are less dangerous than finalizers, but still unpredicable, slow and generally unnecessary.
 * 2.- Never depend on a finalizer or cleaner to update persistent state.
 * 2.1.- Uncaught exceptions thrown inside a finalizer will be ignored and the finalization of an object will end.
 * 3.- There is a severe performance penalty for using finalizers and cleaners.
 * 4.- Finalizers have a serious security problem: they open your class up to finalizer attacks.
 */
public class Item8Test {

    /**
     * As Room instantiations are surrounded in try-with-resource blocks, automatic cleaning (gc) will never be
     * required, as room will be running {@code close} method after finalizing in try block.
     */
    @Test
    public void Clean_DirtyRoom_CleanedRoom() {
        try (Room room = new Room(7)) {
            System.out.println("Goodbye");
        }
    }

    /**
     * If room instantiations are not surrounded then there is no guarantee that that cleaning actions (close method)
     * or garbage collector will be invoked after it finished running.
     */
    @Test
    public void Clean_DirtyRoom_RoomNotCleaned() {
        Room room = new Room(7);
        System.out.println("Peace out");
    }
}
