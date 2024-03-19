package CourseCodes.NewSections.Section_11_Abstraction_Interface.Course03_Interfaces;

//Part-3
/*
        And I'll extend the Animal Class and implement both interfaces. What's important to see from this declaration,
    is that we can use both extends and implements in the same class declaration.

                    A class can use "extends" and "implements" in the same declaration

        A class can only "extend" a "single class", which is why Java is called single inheritance. But a class can "implement
    many interfaces". This gives us "plug and play functionality", which is what makes them so powerful. A class can "both
    extend" another class, and "implement" one or more interfaces. In this example, the Bird class extends, or inherits
    from Animal, but it's implementing both a FlightEnabled, and Trackable interface. We can describe Bird by what it is,
    and what it does.

        Now, that we have this Bird class, I want to get back to the main method, and create an instance of this class.
*/
//End-Part-3
public class Bird extends Animal implements FlightEnabled, Trackable{
//Part-7
/*
        Let me produce the default implementation by using IntelliJ tools. Let's put in a print statement so that we know
    when the method is invoked. Going back to the main method, I want to call this new method on some of our variables.
*/
//End-Part-7
    @Override
    public void move() {
        System.out.println("Flaps wings");
    }

//Part-11
/*
        Similar to the way an abstract class forces its subclasses, to implement the abstract methods it declares, the
    interface requires any classes that implement it, to do the same. This class, because it implements these 2 interfaces,
    now has to implement the 3 methods on FlightEnabled, takeOff, land, and fly, and the one method on Trackable, track.
    And like an abstract class, we can do the same, by hovering over that error, and selecting implement method. And just
    like it did for an abstract class, IntelliJ generates method shells for all the abstract methods, for all the interfaces
    this class implements. To keep this simple, I'm just going to print something out in each of these methods. So for the
    takeOff method, I'll print the class name and a message. In the land method, I'll change the same message as landing.
    In the fly method, I'll make that is flying. And finally, in the track method, I want to print out its coordinates
    recorded. And for the last method, track, if we implemented it in a real application, while something is flying or
    is in transit, this might periodically send its coordinates to the application. This would make it possible to track
    the flight path of this bird, for example. Let's add calls to these methods in the main method.
*/
//End-Part-11
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



}
