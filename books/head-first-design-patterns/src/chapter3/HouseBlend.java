package chapter3;

public class HouseBlend implements Beverage {

    @Override
    public String getDescription() {
        return "House Blend Coffee";
    }

    @Override
    public double getCost() {
        return .89;
    }
}
