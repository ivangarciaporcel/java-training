package edu.coursera.concurrent;

import edu.rice.pcdp.Actor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static edu.rice.pcdp.PCDP.finish;

/**
 * An actor-based implementation of the Sieve of Eratosthenes.
 * <p>
 * TODO Fill in the empty SieveActorActor actor class below and use it from
 * countPrimes to determin the number of primes <= limit.
 */
public final class SieveActor extends Sieve {
    /**
     * {@inheritDoc}
     * <p>
     * TODO Use the SieveActorActor class to calculate the number of primes <=
     * limit in parallel. You might consider how you can model the Sieve of
     * Eratosthenes as a pipeline of actors, each corresponding to a single
     * prime number.
     */
    @Override
    public int countPrimes(final int limit) {
        SieveActorActor actor = new SieveActorActor(2);
        finish(() -> {
            for (int i = 3; i <= limit; i += 2) {
                actor.send(i);
            }
            actor.send(0);
        });
        SieveActorActor loopActor = actor;
        int nPrimes = 0;
        while (loopActor != null) {
            nPrimes += loopActor.getNPrimes();
            loopActor = loopActor.getNextActor();
        }
        return nPrimes;
    }

    /**
     * An actor class that helps implement the Sieve of Eratosthenes in
     * parallel.
     */
    public static final class SieveActorActor extends Actor {

        private List<Integer> primes;
        private SieveActorActor nextActor;
        private final int maxPrimes = 100;

        public SieveActorActor(int candidate) {
            this.primes = new ArrayList<>();
            this.primes.add(candidate);
            this.nextActor = null;
        }

        /**
         * Process a single message sent to this actor.
         * <p>
         * TODO complete this method.
         *
         * @param msg Received message
         */
        @Override
        public void process(final Object msg) {
            int candidate = (Integer) msg;
            if (candidate <= 0) {
                if (this.nextActor != null) {
                    this.nextActor.send(0);
                }
            } else {
                if (isLocallyPrime(candidate)) {
                    if (this.primes.size() < this.maxPrimes) {
                        this.primes.add(candidate);
                    } else {
                        if (this.nextActor == null) {
                            this.nextActor = new SieveActorActor(candidate);
                        } else {
                            this.nextActor.send(msg);
                        }
                    }
                }
            }
        }

        private boolean isLocallyPrime(int candidate) {
            return primes.stream().noneMatch(prime -> candidate % prime == 0);
        }

        public int getNPrimes() {
            return this.primes.size();
        }

        public SieveActorActor getNextActor() {
            return this.nextActor;
        }
    }
}
