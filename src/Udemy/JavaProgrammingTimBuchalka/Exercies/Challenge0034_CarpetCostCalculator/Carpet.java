package Udemy.JavaProgrammingTimBuchalka.Exercies.Challenge0034_CarpetCostCalculator;

public class Carpet {
    private double cost;

    public Carpet(double cost) {
        if (cost < 0) {
            this.cost = 0d;
        } else {
            this.cost = cost;
        }
    }

    public double getCost() {
        return cost;
    }

}
