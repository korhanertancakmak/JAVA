package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_07_Abstraction_Interface.Course04_default_MethodsOn_Interfaces;

public class Test {

    public static void main(String[] args) {

        inFlight(new Jet());

    }

    private static void inFlight(FlightEnabled flier) {

        flier.takeOff();

        flier.transition(FlightStages.LAUNCH);

        flier.fly();
        if (flier instanceof Trackable tracked) {
            tracked.track();
        }
        flier.land();
    }
}
