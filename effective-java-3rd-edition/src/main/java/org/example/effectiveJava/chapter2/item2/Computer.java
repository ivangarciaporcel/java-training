package org.example.effectiveJava.chapter2.item2;

import java.math.BigDecimal;

public class Computer {

    private String name;
    private String model;
    private long purchasedDate;
    private BigDecimal price;

    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }

    public long getPurchasedDate() {
        return purchasedDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public static class Builder {

        private String name;
        private String model;
        private long purchasedDate;
        private BigDecimal price;

        public Builder(String name) {
            this.name = name;
        }

        public Builder model(String model) {
            this.model = model;
            return this;
        }

        public Builder purchasedDate(long date) {
            this.purchasedDate = date;
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }

    private Computer(Builder builder) {
        this.name = builder.name;
        this.model = builder.model;
        this.purchasedDate = builder.purchasedDate;
        this.price = builder.price;
    }

}
