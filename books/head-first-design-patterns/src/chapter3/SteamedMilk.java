package chapter3;

public class SteamedMilk extends CondimentDecorator {
    public SteamedMilk(Beverage beverage) {
        super(beverage, "Steamed Milk", .10);
    }
}
