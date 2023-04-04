package chapter3;

public class Mocha extends CondimentDecorator{

    public Mocha(Beverage beverage) {
        super(beverage, "Mocha", .20);
    }
}
