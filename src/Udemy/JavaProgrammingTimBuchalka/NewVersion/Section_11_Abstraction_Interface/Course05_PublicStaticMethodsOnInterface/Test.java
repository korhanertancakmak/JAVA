package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_11_Abstraction_Interface.Course05_PublicStaticMethodsOnInterface;

//Part-1
/*
                                         Public Static Methods on an Interface

        In the last course, I talked about the default method, and why it's such a welcome improvement. It lets us extend
    an interface's functionality, while staying backwards compatible.

        Another enhancement that Java included in JDK 8, was support for public static methods on the interface. Before
    JDK 8, an interface would often come packaged with a helper class, that provided static method. With this change, the
    static methods can be included on the interface type itself. Static methods don't need to specify a public access
    modifier, because it's implied. When you call a public static method on an interface, you must use the interface name
    as a qualifier. In the ArrayList lectures, you may remember I used two static helper methods, on the Comparator
    interface, which were added in JDK 8. These were Comparator.naturalOrder() and Comparator.reverseOrder().

        Let's see what this really means, when we create a static method on our own interface. This time, I want to work
    with the OrbitEarth interface.
*/
//End-Part-1

public class Test {

    public static void main(String[] args) {

        inFlight(new Jet());

        //OrbitEarth.log("Testing " + new Satellite());         // commented via Part-5

//Part-4
/*
        And if I run that,

                Wed Aug 09 19:39:54 TRT 2023: Testing Section_11_..._PublicStaticMethodsOnInterface.Satellite@9807454

    you'll see, the date and time, printed out in what is called the long form along with the other items. We didn't
    override the toString method on Satellite, so we got the default output for that class. That's not complicated, but
    the inclusion of public static methods, is a definite bonus. It allows us to include helper methods, on the interface
    type itself, as I've shown right above Part-3.

        We covered a default method, and a public static method introduced in JDK8. It wasn't long before developers were
    requesting more.

                                            Private methods (JDK 9)

        JDK 9 gave us private methods, both static and not static. This enhancement primarily addresses the problem of
    re-use of code, within concrete methods on an interface. A private static method can be accessed by either a public
    static method, a default method, or a private non-static method. A private non-static method is used to support default
    methods, and other private methods.

        Getting back to the code, I created a public log method on OrbitEarth. I can also make this method private and
    static, which means only methods on this interface can actually call it. I'll make that change next. Going to the
    OrbitEarth interface,
*/
//End-Part-4

        orbit(new Satellite());

//Part-8
/*
        And now if I run this code, you'll see there is no output from that change. Nothing gets printed out from any of
    the Satellite's methods. Before I work on those, I want to next create a private method on OrbitEarth, and call it
    logStage.
*/
//End-Part-8
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

//Part-6
/*
        While I'm this class, I want a method, similar to the inFlight method, but which is more specific to things that
    orbit the earth. I'll copy it and paste below, renaming the method to orbit, and changing the parameter type to OrbitEarth,
    which is the name of the interface I just changed. I also want to remove the transition method, and that if statement.
    I'll explain why in a minute.
*/
//End-Part-6

    private static void orbit(OrbitEarth flier) {

        flier.takeOff();
        flier.fly();
        flier.land();
    }

//Part-7
/*
        And right now, the only class that implements OrbitEarth, is Satellite. And this class actually doesn't do anything,
    for any of the method. It overrode the methods, but I didn't include any code in them yet. And going to the main method
    on the Test class, I'll now add a call to orbit and pass it a new Satellite, which you'll remember I created in a
    previous course.
*/
//End-Part-7
}
