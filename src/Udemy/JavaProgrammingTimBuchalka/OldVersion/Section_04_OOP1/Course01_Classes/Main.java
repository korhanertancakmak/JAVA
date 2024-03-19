package Udemy.JavaProgrammingTimBuchalka.OldVersion.Section_04_OOP1.Course01_Classes;

public class Main {

    public static void main(String[] args) {
	    Car porsche = new Car();                             // Creating a new Car object, which is assigned to "porsche"
        Car holden = new Car();                              // Creating a new Car object, which is assigned to "holden"

        //porsche.setModel("carrera");                       // Valid model
        porsche.setModel("911");                             // Invalid model
        System.out.println("Model is " + porsche.getModel());
    }
}
