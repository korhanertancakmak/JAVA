package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_11_Abstraction_Interface.Course05_PublicStaticMethodsOnInterface;

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

//Part-11
/*
        Now, our Satellite is going to have each stage set, as it cycles through the flight stages. The only thing left
    now, is to implement the rest of Satellite's methods. But for this, I want a private method on Satellite, also called
    transition, but this method isn't overriding the interface's default method. It's overloading it. Remember, that means
    we're changing the signature, so in this case, I'm passing a String, and not a FlightStages variable.
*/
//End-Part-11
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
        Here, I just make a call to our transition method, with the description we're taking off. And then the land method
    will do a similar thing, calling transition with the description Landing. And same for the fly method
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

//Part-14
/*
        Here, I've also got a call to achieveOrbit, because flying for an orbiting vehicle, consists of 2 stages, the
    launch, then achieving orbit. And then I call transition again. When a Satellite has achieved orbit, it can start
    collecting data, or taking photos, and so on. And for completeness, I'll change the code in achieveOrbit(), so it calls
    the transition method as well:

            transition("Orbit achieved!");

    There's the setup. And now if I run this,

            Taking Off
            Transitioning from GROUNDED to LAUNCH                                              <<<< Stage-1
            Wed Aug 09 20:25:59 TRT 2023: GROUNDED: Begining Transition to LAUNCH
            Monitoring LAUNCH
            Orbit achieved!
            Transitioning from LAUNCH to CRUISE                                                <<<< Stage-2
            Wed Aug 09 20:25:59 TRT 2023: LAUNCH: Begining Transition to CRUISE
            Monitoring CRUISE
            Data Collection while Orbiting
            Transitioning from CRUISE to DATA_COLLECTION                                       <<<< Stage-3
            Wed Aug 09 20:25:59 TRT 2023: CRUISE: Begining Transition to DATA_COLLECTION
            Monitoring DATA_COLLECTION
            Landing
            Transitioning from DATA_COLLECTION to GROUNDED                                     <<<< Stage-4
            Wed Aug 09 20:25:59 TRT 2023: DATA_COLLECTION: Begining Transition to GROUNDED

    you can now see all the stages get cycled through, including going back to GROUNDED at the end. And notice that each
    stage is Trackable, so you're seeing that with the Monitoring Launch, Monitoring Cruise, and Monitoring Data Collection
    statements there.
*/
//End-Part-14

    public void transition(String description) {
        System.out.println(description);
        stage = transition(stage);
        stage.track();
    }

//Part-12
/*
        The method parameter is just a description that gets printed out. Then I set stage using the OrbitEarth transition
    method. Now, I could've just called stage.getNextStage here. But you can imagine that when you're transitioning from
    one flight stage to another, there's some other work involved. And maybe a transition might fail, and you'd want to
    deal with that. In a real application, you'd probably have support for these kind of different transition stages. On
    the last line, I'm calling track for each stage. Remember that our enum type is really trackable, meaning every stage
    is trackable in some way.

        Now, back to Satellite, I want to put some code in the 3 remaining methods: takeOff, fly, and land. First the
    takeOff method.
*/
//End-Part-12
}

interface OrbitEarth extends FlightEnabled {

    void achieveOrbit();

//Part-2
/*
        And I'm going to add a method, to log data to the console in a flexible way.
*/
//End-Part-2

/*    Commented after Part-5 comes up for rewriting purpose

    static void log(String description) {

        var today = new java.util.Date();
        System.out.println(today + ": " + description);
    }

*/

//Part-3
/*
        Notice here, I don't specify public. I could have, but it would just be redundant. All methods on an interface are
    public, unless otherwise specified. And next, I'm using java.util.Date, a class we haven't covered yet, but will in
    an upcoming lecture, in much more detail. And what I want you to see here is, I'm using the fully qualified name, and
    not waiting for IntelliJ to add that import statement. There are 2 classes called Date in Java's libraries, one in
    this package, java.util, and another in java.sql. This is one way to do it. If you choose to have IntelliJ generate
    the import statement, make sure to use the Date class in java.util. I wanted to show you an example of using a fully
    qualified name. You might do something like this, if you're only ever referencing that class once in your code, as
    I'm doing here. If we create a new instance of that class, it gives us today's date and time, for our own local time,
    so that's pretty handy. And now I'll call this, from the main method on the Test class.
*/
//End-Part-3

    private static void log(String description) {

        var today = new java.util.Date();
        System.out.println(today + ": " + description);
    }

//Part-5
/*
        And like any private method, static or otherwise, this means it's only available to be used by the interface's
    concrete method. And now, I've got an error in my main method of the Test class. I'll comment out the line where I'm
    using it. Going to that class,
*/
//End-Part-5

    private void logStage(FlightStages stage, String description) {

        description = stage + ": " + description;
        log(description);
    }

//Part-9
/*
        In this code, I'm just prefixing the stage to the description that was passed. We can only use a private method,
    from either another private method, or a default method. I want to override the transition method, that was on FlightEnabled,
    doing that on OrbitEarth. Right below, I'll generate that overridden method. And change it a little bit.
*/
//End-Part-9

    @Override
    default FlightStages transition(FlightStages stage) {
        FlightStages nextStage = FlightEnabled.super.transition(stage);
        logStage(stage, "Begining Transition to " + nextStage);
        return nextStage;
    }

//Part-10
/*
        First, I'm assigning what comes back from the transition method of FlightEnabled to a local variable called nextStage,
    because I want to print it out. On the second line, I do that, passing the current stage as the first parameter to
    the private method, and appending the nextStage to the text in the second argument. And then I'll just return the
    nextStage variable.

        Ok, so now it's time to have the Satellite class actually do something. First, I want to add a stage field to the
    Satellite class, which has the type of our enum, FlightStages.
*/
//End-Part-10
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
