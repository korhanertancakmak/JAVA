package CourseCodes.NewSections.Section_11_Abstraction_Interface.Course04_default_MethodsOn_Interfaces;

public class Jet implements FlightEnabled, Trackable {

    @Override
    public void takeOff() {
        System.out.println(getClass().getSimpleName() + " is taking off");
    }

    @Override
    public void land() {
        System.out.println(getClass().getSimpleName() + " is landing");
    }

    @Override
    public void fly() {
        System.out.println(getClass().getSimpleName() + " is flying");
    }

    @Override
    public void track() {
        System.out.println(getClass().getSimpleName() + "'s coordinates recorded");
    }

//Part-6
/*
                @Override
                public FlightStages transition(FlightStages stage) {
                    return FlightEnabled.super.transition(stage);
                }

        And this gives me the default overridden implementation. And this looks a lot like what we'd see, if we overrode
    a class's method, except the keyword super here, is qualified by the interface type, FlightEnabled. This means the
    default method on the Interface type gets called. But that's not what I want either, because that's what happens if
    I don't override the method at all. I want to change the code in my overridden method, I'll print something out and
    return a different stage. I'll print the class name, with a string literal.

                System.out.println(getClass().getSimpleName() + " transitioning");

    And then I'll return an enum constant.

                return FlightEnabled.super.transition(stage);
*/
//End-Part-6

    //@Override                                                                 // overrode via part-7
    //public FlightStages transition(FlightStages stage) {
        //return FlightEnabled.super.transition(stage);                         // removed at the beginning of part-6
        //System.out.println(getClass().getSimpleName() + " transitioning");
        //return FlightStages.CRUISE;                                           // updated via part-10
    //}

//Part-7
/*
        Before I run that, take a look at the little icon in IntelliJ, comparing it to the one next to the previous method,
    track. There is a green circle with an "i", and red arrow next to the track method, vs. a blue circle with an "o",
    and red arrow, for the transition method. The green icon indicates we're implementing an abstract method, where as
    the blue icon implies an override, even though we use the override annotation for each. This is one small way, to
    differentiate between these 2 types of methods. Now, I'll run that.

                Jet is taking off
                Jet transitioning
                Jet is flying
                Jet's coordinates recorded
                Jet is landing

    And this code runs, and you can see that the new method was executed, because we see Jet transitioning. Now, I could've
    been nicer, and provided a better default implementation. In some cases, the default method on the interface, can be
    used much like a class method, providing some behavior that'll work for all classes.

                                        Overriding a default method

        So like overriding a method on a class, you have three choices, when you override a default method on an interface.
  - You can choose not to override it at all.
  - You can override the method and write code for it, so that the interface method isn't executed.
  - Or you can write your own code, and invoke the method on the interface, as part of your implementation.

        Let's look at this in some code. First I'll change the transition method on the FlightEnabled interface, to have
    something other than the unimplemented statement. But even before I do that, I want to first add a method to the enum
    type, that gets the next Stage.
*/
//End-Part-7

    @Override
    public FlightStages transition(FlightStages stage) {
        System.out.println(getClass().getSimpleName() + " transitioning");
        return FlightEnabled.super.transition(stage);
    }

//Part-11
/*
        And now, you can see I'm using FlightEnabled.super.transition. If i tried to use just super, I'd get a compiler
    error. When we try that, IntelliJ gives us the message, "Cannot resolve method transition in Object", which is the
    Jet's implied super class. And that's because no class in Jet's hierarchy has a method called transition. Remember,
    interfaces aren't classes, and aren't really a part of a class, or the object that gets created from a class. They're
    just a way to make your class and object behave a certain way.

        Whenever you call a default method from an overridden method, you need to qualify super with the interface type.
    I'll run that,

                Jet is taking off
                Jet transitioning
                Transitioning from LAUNCH to CRUISE
                Jet is flying
                Jet's coordinates recorded
                Jet is landing

    and you can see there, Jet transitioning, and it's going from the LAUNCH stage to the CRUISE stage. That's how a default
    method works on an interface. Java introduced it to help with building extensible, and backwards compatible interfaces.
*/
//End-Part-11
}
