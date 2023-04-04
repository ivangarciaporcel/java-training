package chapter3;

public class Whip extends CondimentDecorator {
    public Whip(Beverage beverage) {
        super(beverage, "Whip", .10);
    }
}
