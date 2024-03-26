package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_07_Abstraction_Interface.Course04_default_MethodsOn_Interfaces;

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

    public void achieveOrbit() {
    // void achieveOrbit() {                        // attempting to assign weaker access privileges
        System.out.println("Orbit achieved!");
    };

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

interface OrbitEarth extends FlightEnabled {

    void achieveOrbit();
}


interface FlightEnabled {

    //public abstract void takeOff();
    void takeOff();
    //abstract void land();
    void land();
    void fly();

    double MILES_TO_KM = 1.60934;
    //public static final double MILES_TO_KM = 1.60934;
    double KM_TO_MILES = 0.621371;


    //FlightStages transition(FlightStages stage);                  // replacing it with the default method version below
    default FlightStages transition (FlightStages stage) {
        /*System.out.println("transition not implemented on " + getClass().getName());      //updated via Part-10
        return null;*/
        FlightStages nextStage = stage.getNextStage();
        System.out.println("Transitioning from " + stage + " to " + nextStage);
        return nextStage;
    }
}

interface  Trackable {

    //void track() {};
    void track();
}

public abstract class Animal {

    public abstract void move();
}
