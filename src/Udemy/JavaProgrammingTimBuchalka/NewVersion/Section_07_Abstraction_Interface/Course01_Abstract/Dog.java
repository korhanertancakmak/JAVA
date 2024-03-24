package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_07_Abstraction_Interface.Course01_Abstract;

public class Dog extends Mammal {
    public Dog(String type, String size, double weight) {
        super(type, size, weight);
    }

    @Override
    public void move(String speed) {

        if (speed.equalsIgnoreCase("slow")) {
            //System.out.println(type + " walking");
            System.out.println(getExplicitType() + " walking");
        } else {
            //System.out.println(type + " running");
            System.out.println(getExplicitType() + " running");
        }
    }

    @Override
    public void makeNoise() {

        if (type == "Wolf") {
            System.out.print("Howling! ");
        } else {
            System.out.print("Woof! ");
        }
    }

//    @Override
//    public String getExplicitType() {
//        return "";
//    }

    @Override
    public void shedHair() {

        System.out.println(getExplicitType() + " shed hair all the time");
    }
}
