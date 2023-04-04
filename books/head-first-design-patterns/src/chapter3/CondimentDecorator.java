package chapter3;

public abstract class CondimentDecorator implements Beverage {

    private final Beverage beverage;
    private final String description;
    private final double cost;

    public CondimentDecorator(Beverage beverage, String description, double cost) {
        this.beverage = beverage;
        this.description = description;
        this.cost = cost;
    }

    public String getDescription() {
        return beverage.getDescription() + ", " + this.description;
    }

    public double getCost() {
        return beverage.getCost() + this.cost;
    }
}
