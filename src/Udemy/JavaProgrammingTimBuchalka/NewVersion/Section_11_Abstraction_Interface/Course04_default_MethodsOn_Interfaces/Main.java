package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_11_Abstraction_Interface.Course04_default_MethodsOn_Interfaces;

//Part-1
/*
                                            Default vs static methods

        JDK 8 introduced the "default" method and public "static" methods, and JDK 9 introduced "private" methods, both
    static and non-static. All of these new method types (on the interface) are concrete methods. In last course, I created
    3 interfaces: FlightEnabled, Trackable, and OrbitEarth. I also created classes, records and enums, that implemented
    one or two of these.

        Now, let's consider what happens, if we learn about a new requirement, for flightEnabled objects. A new method
    is needed, called transition, that uses the FlightStages enum type we set up. I'm going to add this method to the
    FlightEnabled interface, which I've declared in the Animal.java source file. And this method takes a stage, one of the
    enum values, and returns another stage, because it's transitioning from one stage to another.
*/
//End-Part-1

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Bird bird = new Bird();
        Animal animal = bird;
        FlightEnabled flier = bird;
        Trackable tracked = bird;

        animal.move();
        inFlight(flier);
        inFlight(new Jet());

        Trackable truck = new Truck();
        truck.track();

        double kmsTraveled = 100;
        double milesTraveled = kmsTraveled * FlightEnabled.KM_TO_MILES;
        System.out.printf("The truck traveled %.2f km or %.2f miles%n",
                kmsTraveled, milesTraveled);


        LinkedList<FlightEnabled> fliers = new LinkedList<>();
        fliers.add(bird);

        List<FlightEnabled> betterFliers = new LinkedList<>();
        betterFliers.add(bird);

        triggerFliers(fliers);
        flyFlyer(fliers);
        landFliers(fliers);

        triggerFliers(betterFliers);
        flyFlyer(betterFliers);
        landFliers(betterFliers);
    }

    private static void inFlight(FlightEnabled flier) {

        flier.takeOff();
        flier.fly();
        if (flier instanceof Trackable tracked) {
            tracked.track();
        }
        flier.land();
    }

    private static void triggerFliers(List<FlightEnabled> fliers) {

        for (var flier : fliers) {
            flier.takeOff();
        }

    }

    private static void flyFlyer(List<FlightEnabled> fliers) {

        for (var flier : fliers) {
            flier.fly();
        }

    }

    private static void landFliers(List<FlightEnabled> fliers) {

        for (var flier : fliers) {
            flier.land();
        }
    }
}

