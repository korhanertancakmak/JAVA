package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_07_Abstraction_Interface.Course03_Interfaces;

enum FlightStages implements Trackable {GROUNDED, LAUNCH, CRUISE, DATA_COLLECTION;

    @Override
    public void track() {

        if (this != GROUNDED) {
            System.out.println("Monitoring " + this);
        }
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
}

interface  Trackable {

    //void track() {};
    void track();
}

public abstract class Animal {

    public abstract void move();
}
