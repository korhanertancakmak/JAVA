package CourseCodes.NewSections.Section_11_Abstraction_Interface.Course04_default_MethodsOn_Interfaces;

//Part-3
/*
        I'll add a public static void main method in this class. And I'm going to copy the inFlight method from the Main
    class, and paste it into the Test class.
*/
//End-Part-3
public class Test {

    public static void main(String[] args) {

//Part-5
/*
        I want to run this code, but I have to be sure to run the main method on Test class, and not Main class.

                Jet is taking off
                transition not implemented on Section_11_..._default_PublicStatic_Methods.Jet
                Jet is flying
                Jet's coordinates recorded
                Jet is landing

    I hope you can see that message, transition not implemented for Section_11_..._default_PublicStatic_Methods.Jet, on
    the second line of the output. This means, in this case, that Jet didn't override this method. If you really want your
    default method to get implemented, or overridden, on every class, you'd want to code something like this in the default
    method. If I don't want this to be what happens, and I probably don't, I have to go to the Jet class, and override
    that method. To do that, I want to generate an override method for that transition method.
*/
//End-Part-5

        inFlight(new Jet());

    }

    private static void inFlight(FlightEnabled flier) {

        flier.takeOff();

//Part-4
/*
        I want to insert a call to the transition method, between takeOff and fly. Going back to the main method on the
    Test class, I'll call the inFlight method, and pass it a new instance of the Jet class.
*/
//End-Part-4
        flier.transition(FlightStages.LAUNCH);

        flier.fly();
        if (flier instanceof Trackable tracked) {
            tracked.track();
        }
        flier.land();
    }
}
