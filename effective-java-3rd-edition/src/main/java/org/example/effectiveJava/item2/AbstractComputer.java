package org.example.effectiveJava.item2;

import java.math.BigDecimal;
import java.util.EnumSet;
import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;

public abstract class AbstractComputer {

    private final Set<Accessories> accessories;
    private final BigDecimal price;

    abstract static class Builder<T extends Builder<T>> {
        EnumSet<Accessories> accesories = EnumSet.noneOf(Accessories.class);
        BigDecimal price;

        public T addAccessory(Accessories accessory) {
            checkArgument(accessory != null, "Accessory cannot be null");
            accesories.add(accessory);
            return self();
        }

        public T addPrice(BigDecimal price) {
            this.price = price;
            return self();
        }

        abstract AbstractComputer build();

        protected abstract T self();
    }

    AbstractComputer(Builder<?> builder) {
        accessories = builder.accesories.clone();
        price = builder.price;
    }

    public Set<Accessories> getAccessories() {
        return this.accessories;
    }

    public BigDecimal getPrice() {
        return this.price;
    }
}
