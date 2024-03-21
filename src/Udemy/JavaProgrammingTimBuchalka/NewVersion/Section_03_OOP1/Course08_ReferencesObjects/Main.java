package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_03_OOP1.Course08_ReferencesObjects;


public class Main {
    public static void main(String[] args) {

        House blueHouse = new House("blue");
        House anotherHouse = blueHouse;

        System.out.println(blueHouse.getColor());       // prints blue
        System.out.println(anotherHouse.getColor());    // blue

        anotherHouse.setColor("yellow");
        System.out.println(blueHouse.getColor());       // yellow
        System.out.println(anotherHouse.getColor());    // yellow

        House greenHouse = new House("green");
        anotherHouse = greenHouse;

        System.out.println(blueHouse.getColor());       // yellow
        System.out.println(greenHouse.getColor());      // green
        System.out.println(anotherHouse.getColor());    // green

    }
}
