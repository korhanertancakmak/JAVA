package Udemy.JavaProgrammingTimBuchalka.Exercies.Challenge0040_Polymorphism;

public class Car {
    private boolean engine = true;
    private int cylinders, wheels = 4;
    private String name;

    public Car(int cylinders, String name) {
        this.cylinders = cylinders;
        this.name = name;
    }

    public int getCylinders() {
        return cylinders;
    }

    public String getName() {
        return name;
    }

    public String  startEngine() {
        return "Car -> startEngine()";
    }

    public String accelerate() {
        return "Car -> accelerate()";
    }

    public String brake() {
        return "Car -> brake()";
    }
}





