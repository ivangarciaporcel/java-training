package org.example.effectiveJava.chapter2.item2;

public class BasicComputer extends AbstractComputer {

    private BasicComputer(Builder builder) {
        super(builder);
    }

    public static class Builder extends AbstractComputer.Builder<Builder> {

        @Override
        public BasicComputer build() {
            return new BasicComputer(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }
}
