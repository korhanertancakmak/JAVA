package CourseCodes.NewSections.Section_11_Abstraction_Interface.Course04_default_MethodsOn_Interfaces;

enum FlightStages implements Trackable {GROUNDED, LAUNCH, CRUISE, DATA_COLLECTION;

    @Override
    public void track() {

        if (this != GROUNDED) {
            System.out.println("Monitoring " + this);
        }
    }

//Part-8
/*
        I'll declare it as public, returning FlightStages, and I'll call it getNextStage. Next, I'll create a FlightStages
    array and set it to the array returned from calling the values() method. And finally, I'll return an element from the
    array created on the previous line.
*/
//End-Part-8

    public FlightStages getNextStage() {

        FlightStages[] allStages = values();
        return allStages[(ordinal() + 1) % allStages.length];
    }

//Part-9
/*
        Ok, so with this code, I've purposely avoided using any of the enum constant values, like GROUNDED or LAUNCH. If
    the enum constants change, and more are included, or some are moved around, I still want this method to work.

        Firstly, I create a local variable allStages, which gets assigned the array of enum values, that we get back from
    the values() method. I want to return the next stage, which is the stage at ordinal + 1, in every instance, except
    when the current stage is the last enum constant, DATA_COLLECTION. In that case, there is no next stage, so I want to
    return the first stage. This expression,

                return allStages[(ordinal() + 1) % allStages.length];

    uses the modulus operator, to return ordinal + 1 in every case, except when ordinal + 1 is equal to the length of the
    array, allStages. In that case it will be 4 over 4, as the constants list stands now, with a reminder of zero, and it
    will give us the first stage. What I'm really doing is cycling through the stages. Now, I want to use this method in
    the default transition method on FlightEnabled.
*/
//End-Part-9
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

//Part-2
/*
        All I did here, was to define a new abstract method on this interface. Unfortunately, this one change means every
    class, that implements this interface, now won't compile. This means that for us, Bird, Jet, Satellite, and DragonFly,
    all have to change. This is painful, and we've only got a small set of classes. Imagine facing this kind of change,
    in a production environment, especially if this is a library type. If you'd published this interface, to be used in
    multiple applications, making this kind of change wouldn't go well for anyone using it. Your updated interface wouldn't
    be backwards compatible. Before JDK 8, you didn't have a lot of options, for making changes like this to your interfaces.
    You either had to force all classes implementing it, to be edited so that they'd compile, or provide an alternate
    interface, with tne new functionality. Java came to the rescue with a feature officially called, the extension method.

                            The Interface Extension Method - the default method (as of JDK8)

        An extension method is identified by the modifier "default", so it's more commonly known as the default method.
    This method is a "concrete" method, meaning it has a code block, and we can add statements to it. In fact, it has to
    have a method body, even just an empty set of curly braces. It's a lot like a method on a superclass, because we can
    override it. Adding a default method doesn't break any classes currently implementing the interface. Let's get back
    to the code, and I'll switch to using a default implementation for this new method. I'm going to comment that single
    statement, for the abstract method, and write right below it with code for a default method.

        In the body of this method, I'm printing the text, and including the class name.

                    System.out.println("transition not implemented on " + this.getClass().getName());

    It's common practice to write a statement like this, or throw an exception, that a default method isn't implemented.
    This means I really want every class, using this interface, to override this method before they actually use it in
    code. I can't force them to implement their own version, like I could if it were an abstract method. What I can do
    though, is either cause an error, or print a statement like this, so they'll implement it, when they actually are
    ready to use it.

        Now, the other thing I want you to see here, is that I'm using this.getClass().getName(). By now you know this
    will give us the class name of the instance. But this is really something new for an instance, because it isn't a
    class, and an object is never really an instance of an interface. We may not even have any idea, when we create an
    interface, what an object executing this method will really be. It could be anything. But the Java developers worked
    their magic, and now give us the ability, to access a runtime instance from this method. And normally, we wouldn't
    use the explicit this keyword in a class, only in a setter or constructor, as we've previously talked about. I can
    remove this,

                    System.out.println("transition not implemented on " + getClass().getName());

    because it's implicit in non-static methods, including this default method on an interface. Next, I'll create a new
    class, called Test, in the same package, because I want to keep the Main class, the way we left in the last course.
*/
//End-Part-2

    //FlightStages transition(FlightStages stage);                  // replacing it with the default method version below
    default FlightStages transition (FlightStages stage) {
        /*System.out.println("transition not implemented on " + getClass().getName());      //updated via Part-10
        return null;*/
        FlightStages nextStage = stage.getNextStage();
        System.out.println("Transitioning from " + stage + " to " + nextStage);
        return nextStage;
    }

//Part-10
/*
        I'll comment out the statements in there.

                    default FlightStages transition (FlightStages stage) {
                        System.out.println("transition not implemented on " + getClass().getName());
                        return null;
                    }

    Since I overrode this method on the Jet class, I want to invoke this updated default method from there. Going back
    to that Jet class, and the transition method, I'll return the next stage I get from default transition method of FlightEnabled,
    instead of the hardcoded CRUISE stage.
*/
//End-Part-10
}

interface  Trackable {

    //void track() {};
    void track();
}

public abstract class Animal {

    public abstract void move();
}
