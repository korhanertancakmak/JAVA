package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_07_Abstraction_Interface.Course05_PublicStaticMethodsOnInterface;

enum FlightStages implements Trackable {GROUNDED, LAUNCH, CRUISE, DATA_COLLECTION;

    @Override
    public void track() {

        if (this != GROUNDED) {
            System.out.println("Monitoring " + this);
        }
    }

    public FlightStages getNextStage() {

        FlightStages[] allStages = values();
        return allStages[(ordinal() + 1) % allStages.length];
    }

}

record DragonFly(String name, String type) implements FlightEnabled {


    @Override
    public void takeOff() {

    }

    @Override
    public void land() {

    }

    @Override
    public void fly() {

    }
}

class Satellite implements OrbitEarth {

    FlightStages stage = FlightStages.GROUNDED;

    public void achieveOrbit() {
        //System.out.println("Orbit achieved!");                // updated via Part-14
        transition("Orbit achieved!");
    };

    @Override
    public void takeOff() {

        transition("Taking Off");
    }

//Part-13
/*

*/
//End-Part-13

    @Override
    public void land() {

        transition("Landing");
    }

    @Override
    public void fly() {

        achieveOrbit();
        transition("Data Collection while Orbiting");
    }

    public void transition(String description) {
        System.out.println(description);
        stage = transition(stage);
        stage.track();
    }
}

interface OrbitEarth extends FlightEnabled {

    void achieveOrbit();

/*    Commented after Part-5 comes up for rewriting purpose
    static void log(String description) {
        var today = new java.util.Date();
        System.out.println(today + ": " + description);
    }
*/

    private static void log(String description) {

        var today = new java.util.Date();
        System.out.println(today + ": " + description);
    }

    private void logStage(FlightStages stage, String description) {

        description = stage + ": " + description;
        log(description);
    }

    @Override
    default FlightStages transition(FlightStages stage) {
        FlightStages nextStage = FlightEnabled.super.transition(stage);
        logStage(stage, "Begining Transition to " + nextStage);
        return nextStage;
    }
}


interface FlightEnabled {

    void takeOff();
    void land();
    void fly();

    double MILES_TO_KM = 1.60934;
    double KM_TO_MILES = 0.621371;

    default FlightStages transition (FlightStages stage) {
        FlightStages nextStage = stage.getNextStage();
        System.out.println("Transitioning from " + stage + " to " + nextStage);
        return nextStage;
    }
}

interface Trackable {

    void track();
}

public abstract class Animal {

    public abstract void move();
}
