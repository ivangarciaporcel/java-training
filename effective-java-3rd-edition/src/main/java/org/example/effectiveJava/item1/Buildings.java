package org.example.effectiveJava.item1;

public class Buildings {

    // Suppresses default constructor, ensuring non-instantiability.
    private Buildings() {
    }

    public static Building getHouseInstance(double squareMeters) {
        if (squareMeters > 2000) { // ground bigger than 2000 square meters, considered as mansion
            return new Mansion(squareMeters);
        } else { // ground smaller or equals than 2000 square meters, considered as house
            return new House(squareMeters);
        }
    }


    private static class House implements Building {

        private final double pricePerSquareMeters = 800;
        private double squareMeters;

        private House(double squareMeters) {
            this.squareMeters = squareMeters;
        }

        @Override
        public double calculatePrice() {
            return pricePerSquareMeters * squareMeters;
        }

        @Override
        public double getPricePerSquareMeter() {
            return pricePerSquareMeters;
        }
    }

    private static class Mansion implements Building {

        private final double pricePerSquareMeters = 1600;
        private double squareMeters;

        private Mansion(double squareMeters) {
            this.squareMeters = squareMeters;
        }

        @Override
        public double calculatePrice() {
            return pricePerSquareMeters * squareMeters;
        }

        @Override
        public double getPricePerSquareMeter() {
            return pricePerSquareMeters;
        }
    }

}
