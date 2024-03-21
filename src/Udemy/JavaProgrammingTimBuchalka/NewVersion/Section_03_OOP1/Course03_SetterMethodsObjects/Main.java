package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_03_OOP1.Course03_SetterMethodsObjects;

public class Main {
    public static void main(String[] args) {

        Car car = new Car();
        //car.setMake("Porsche");
        car.setMake("Maserati");
        car.setModel("Carrera");
        car.setDoors(2);
        car.setConvertible(true);
        car.setColor("black");
        System.out.println("make = " + car.getMake());
        System.out.println("model = " + car.getModel());
        car.describeCar();
    }
}
