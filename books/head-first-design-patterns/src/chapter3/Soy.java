package chapter3;

public class Soy extends CondimentDecorator{
    public Soy(Beverage beverage) {
        super(beverage, "Soy", .15);
    }
}
