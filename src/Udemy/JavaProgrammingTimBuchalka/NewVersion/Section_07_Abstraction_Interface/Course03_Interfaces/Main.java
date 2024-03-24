package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_07_Abstraction_Interface.Course03_Interfaces;

//Part-1
/*
                                            Interfaces vs Abstract Class

        We saw that an abstract class requires its subclasses, to implement its abstract methods. An "interface" is similar
    to an abstract class, although it "isn't a class" at all. It's a "special" type, that's more like a "contract" between
    the class and client code, that the compiler enforces. By declaring it is using an interface, your class must implement
    all the abstract methods, on the interface. A class agrees to this, because it wants to be "known by that type", by
    the outside world, or the client code. An "interface" lets "classes that might have little else in common", be recognized
    as a special reference type.

                                            Declaring an interface

        Declaring an interface is similar to declaring a class, using the keyword "interface", where you would use "class".
    Below, I'm declaring a public interface named FlightEnabled.

                        public interface FlightEnabled {}

    An interface is usually named, according to the set of behaviors it describes. Many interfaces will end in 'able',
    like Comparable, and Iterable, again meaning something is capable, or can do, a given set of behaviors.

                                            Using an interface

        A class is associated to an interface, by using the implements clause in the class declaration. In this example,
    the class Bird implements the FlightEnabled interface.

                       public class Bird implements FlightEnabled {}

    Because of this declaration, we can use FlightEnabled as the reference type, and assign it an instance of bird.

                        FlightEnabled flier = new Bird();

    In this code sample, we create a new Bird object, but we assign it to the FlightEnabled variable named flier. Let's
    look at this interesting type in some code. I'm going to first create an abstract class in the same package with Main
    and name it Animal. Once created I need to manually add the keyword abstract to it.
*/
//End-Part-1

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
//Part-4
/*
        Ok, so this is the usual way to create a new instance of bird, and assign it to a reference variable, bird. But
    we can say a Bird is an Animal, and it's also FlightEnabled, and Trackable. That means I can assign my bird object,
    to different reference types, which I'll do now. First I'll assign the bird object to Animal. Then FlightEnabled.
    And then Trackable.
*/
//End-Part-4

        Bird bird = new Bird();
        Animal animal = bird;
        FlightEnabled flier = bird;
        Trackable tracked = bird;

//Part-5
/*
        A bird, as it turns out, can be referred to, by these 4 different types. I've got all this set up, but none of it's
    very interesting without adding some methods. I'll start by going back to the Animal abstract class. and adding an
    abstract method there, and I'll call that move.
*/
//End-Part-5

        animal.move();
//        flier.move();
//        tracked.move();

//Part-8
/*
        First call to move will be the Animal reference. Then FlightEnabled. And then Trackable. Note that only the first
    call to move compiles, the other 2 don't. I've talked about this, and I want to review this again, so you are clear
    on why this is happening. The type you use, meaning the variable's declared type, determines which methods you can
    call in your code. When we assigned the bird object to FlightEnabled and Trackable variables, those types don't have
    a move method on them, so this is why we get this error. The compiler only cares about the declared type. I'll comment
    our those last 2 lines. And running that,

                Flaps wings

    we see that this animal, which is really a bird instance, flags its wings. Ok, now I want to add some methods, related
    to take-off, land, and flying on that FlightEnabled interface.
*/
//End-Part-8

//        flier.takeOff();
//        flier.fly();
//        tracked.track();
//        flier.land();

//Part-12
/*
        Running that,

                Bird is taking off
                Bird is flying
                Bird's coordinates recorded
                Bird is landing

    you can see that all of Bird's methods were called. In this code, we have a variable, flier, of type FlightEnabled,
    and when we call any FlightEnabled methods on that variable, it executes bird's methods, because bird is our runtime
    object. And the same thing with Trackable. We can describe a bird in many ways now, in the code, treating it as a member
    of multiple, and quite different groups.

        We executed all the implemented interface methods on the Bird class. Now, I want to create another method in the
    Main class, called inFlight. I'll declare it as private static void with a FlightEnabled parameter.
*/
//End-Part-12

        inFlight(flier);

//Part-14
/*
        I'll comment out the last 4 lines. And I'll add a call to inFlight instead. And running that,

                    Bird is taking off
                    Bird is flying
                    Bird's coordinates recorded
                    Bird is landing

    we get the output, Bird is taking off, Bird is flying, then we get the track method statement, coordinates recorded,
    and finally bird is landing. Ok, so why couldn't we just have put all these methods on the abstract class, Animal,
    or a subclass Bird. Well, first of all, not all Animals fly, and not even all Birds fly. And we don't want all animals
    or birds, to be tracked either. There are a lot of different things that fly, and things we might want to track, that
    aren't birds. Continuing on, I'm going to create another new class in our package, called Jet.
*/
//End-Part-14

        inFlight(new Jet());

//Part-16
/*
        Running that,

                    Jet is taking off
                    Jet is flying
                    Jet's coordinates recorded
                    Jet is landing

    you can see that we're able to pass both a Jet and a bird, to a method that can use them, because both of these classes,
    implement FlightEnabled. What's really nice is, that you can mix and match interfaces, as you need them. To demonstrate
    this, I'll create another class, this time called Truck in the same package.
*/
//End-Part-16

        Trackable truck = new Truck();
        truck.track();

//Part-18
/*
        I'm calling track method from the truck variable, which we've declared with the Trackable type. Running that,

                    Truck's coordinates recorded

    we can see that a Truck can be tracked, because it implements the Trackable interface. Let's review what we did here.

                                                    The Bird Class

               _________________________      ________________________________         _____________________________
               |abstract Animal =>     |      |<<Interface>> FlightEnabled =>|         |<<Interface>> Trackable => |
               |_______________________|      |______________________________|         |___________________________|
               |-----------------------|      |------------------------------|         |---------------------------|
               |  abstract move()      |      |  takeOff()                   |         |  track()                  |
               |_______________________|      |  fly()                       |         |___________________________|
                         ↑                    |  land()                      |                      ↑
                         ↑                    |______________________________|                      |
                         ↑                               ↑                                          |
                         ↑            Extends            |                 Implements               |
                         ↑←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←|------------------------------------------|
                                                  _______↑________
                                                  |Bird          |
                                                  |______________|
                                                  |--------------|
                                                  |  move()      |
                                                  |  takeOff()   |
                                                  |  fly()       |
                                                  |  land()      |
                                                  |  track()     |
                                                  |______________|

        An interface lets us treat an instance of a single class as many different types. The Bird Class inherits behavior
    and attributes from Animal, because we used the extends keyword in the declaration of Bird. Because the move method
    was abstract on Animal, Bird was required to implement it. The Bird Class implements the FlightEnabled interface. This
    required the Bird class to implement the takeOff, fly, and land methods, the abstract methods on FlightEnabled.

        The Bird Class also implements the Trackable interface. This required the Bird class to implement the track method,
    which was the abstract method declared on Trackable. Because of these declarations, any instance of the Bird class
    can be treated as a Bird. This means it has access to all of bird's methods, including all those from Animal, FlightEnabled,
    and Trackable. An instance of Bird can be treated like, or declared as an Animal, with access to the Animal functionality,
    described in that class, but customized to Bird.

        It can be used as a FlightEnabled type, with just the methods a FlightEnabled type needs, but again customized for
    the Bird. Or it can take the form of a Trackable object, and be tracked, with specifics for the Bird class.

                                            The FlightEnabled Interface

                                         _________________________________
                                         |<<Interface>> FlightEnabled => |
                                         |_______________________________|
                                         |-------------------------------|
                                         |  takeOff()                    |
                                         |  fly()                        |
                                         |  land()                       |
                                         |_______________________________|
                                                         ↑  implements
                          --------------------------------------------------------------------
                     _____↑______                  ______↑______                     ________↑_________
                     |Jet =>     |                 |Bird =>    |                     |DragonFly =>    |
                     |___________|                 |___________|                     |________________|
                     |-----------|                 |-----------|                     |----------------|
                     | takeOff() |                 | move()    |                     | takeOff()      |
                     |  fly()    |                 | takeOff() |                     | fly()          |
                     |  land()   |                 | fly()     |                     | land()         |
                     |  track()  |                 | land()    |                     | track()        |
                     |___________|                 | track()   |                     |________________|
                                                   |___________|

        Interfaces let us take objects, that may have almost nothing in common, and write reusable code, so we can process
    them all in a like manner. Above, you can see that a Jet, a Bird, and a DragonFly, are very different entities. But
    because they implement FlightEnabled, we can treat them all as the same type, as something that flies, and ignore the
    differences in the classes. Interfaces allow us to type our objects differently, "by behavior only".

        Ok, so I have a few more things I want to cover. And this is best done in code, so let's add a field to the FlightEnabled
    Interface.
*/
//End-Part-18

        double kmsTraveled = 100;
        double milesTraveled = kmsTraveled * FlightEnabled.KM_TO_MILES;
        System.out.printf("The truck traveled %.2f km or %.2f miles%n",
                kmsTraveled, milesTraveled);

//Part-21
/*
        We start with 100 kms, and we multiply that by the constant on FlightEnabled, which is the conversion factor, to
    go from kms to miles. And then we just print out those 2 values. Running that,

                            The truck traveled 100,00 km or 62,14 miles

    we can see that the truck traveled 100 kms, or 62.14 miles. So that's fields on interfaces. It's important to remember
    that an interface never gets instantiated, and won't have a subclass that gets instantiates either. There's no such
    thing as instance fields on an interface, because of this. You might ask, isn't a bird an instance of FlightEnabled?
    No, not really, bird is an instance of a class, that implements the methods of FlightEnabled. Bird doesn't inherit
    traits from FlightEnabled, it just agrees to have a certain way.

        So we discovered that interfaces only support public static and final fields on them, or constants in general.
    We'll continue to look at this unique type.

                                                Extending Interfaces

        Interfaces can be extended, similar to classes, using the extends keyword.

                            interface OrbitEarth extends FlightEnabled {}

    Above, I show an interface, OrbitEarth, that extends the FlightEnabled interface. This interface requires all classes
    to implement both the OrbitEarth, and the FlightEnabled abstract methods.

                            interface OrbitEarth extends FlightEnabled, Trackable {}

    Unlike a class, an interface can use the extends expression with multiple interfaces.

                                        Implements is invalid on an interface

        An interface doesn't implement another interface, so the code below won't compile.

                            interface OrbitEarth implements FlightEnabled {}    // INVALID, invalid, interfac

    In other words, implements is an invalid clause in an interface declaration. Let's see this in code. Above the FlightEnabled
    interface, I'll add the OrbitEarth interface.
*/
//End-Part-21

        LinkedList<FlightEnabled> fliers = new LinkedList<>();
        fliers.add(bird);

        List<FlightEnabled> betterFliers = new LinkedList<>();
        betterFliers.add(bird);

//Part-23
/*
        I'll call it fliers and will also initialize it. And add bird to our array list. Our declared type is the same as
    the instance type, an ArrayList, and I've made the elements in this list, FlightEnabled. But ArrayList implements the
    List interface, and its recommended, to use the interface type for the declared variable. I'll do that next.

        Same thing, but this time with List as the reference type. And I'll add bird to the list. And I'll add bird to
    the list. This time, the declared variable is a List of FlightEnabled elements, and not an ArrayList. List is the
    interface type. Ok, so why is this better? It's not really immediately obvious why just yet. Let me add some methods,
    to demonstrate why the second declaration is better than the first. The first method, I'll call triggerFliers, and
    it'll have a method parameter, ArrayList.
*/
//End-Part-23

        triggerFliers(fliers);
        flyFlyer(fliers);
        landFliers(fliers);

//Part-27
/*
        In each case, I'm passing the fliers variable, which I've declared using the specific type, the ArrayList type.
    And this runs,

                    Bird is taking off
                    Bird is flying
                    Bird is landing

    and we get the messages for a Bird, for takeoff, flying, and landing. I'm going to copy those 3 statements. and this
    time pass the betterFliers variable. Now, this code doesn't compile. We can't pass a List to a method, where an ArrayList
    is declared. So that's one problem. Our method parameters are very specific to the ArrayList type. But do they really
    need to be? For now, I'll leave those 3 statements as is, even though they don't compile. And now, let's say, we've
    decided a LinkedList is a better option for us, than an ArrayList. And I'll change our fliers variable to a LinkedList.
    And now, because our methods are specific about which type they expect, we can't pass the fliers variable to any of
    these methods either. We've got more errors. To pass a LinkedList variable to these method, we'd now have to change
    every single method that was declared with an ArrayList parameter. Next I want to demonstrate coding to the interface.
    For all 3 methods, I'll change ArrayList to just the List type, which is the interface type. Now, the compiler errors
    are gone, on all 6 statements, those that passed fliers, and those that passed betterFliers. We can pass a LinkedList
    to these methods. But more importantly, let's change our betterFliers instance to a LinkedList. In this case, we simply
    swapped one type of list, with another. The code still compiles. And I didn't have to refactor any other code, except
    this one line-316. If you code to the interface, you know anything that implements the interface, has the same set of
    methods, so making changes can be as simple as I just showed you here. That's a large time savings. Method parameters,
    method return types, local variable references, and even class variables should try to use the interface type, as the
    reference variable type, when possible. This will make your code more extensible in the future.

                                            Coding to an Interface

        Coding to an interface scales well, to support new subtypes, and it helps when refactoring code. The downside
    though, is that alterations to the interface may wreak havoc, on the client code. Imagine that you have 50 classes
    using your interface, and you want to add an extra abstract method, to support new functionality. As soon as you add
    a new abstract method, all 50 classes won't compile. Your code isn't backwards compatible, with this kind of change
    to an interface. Interfaces haven't been easily extensible in the past. But Java has made several changes to the
    Interface type over time, to try to address this last problem.
*/
//End-Part-27

        triggerFliers(betterFliers);
        flyFlyer(betterFliers);
        landFliers(betterFliers);
    }

    private static void inFlight(FlightEnabled flier) {

        flier.takeOff();
        flier.fly();
        if (flier instanceof Trackable tracked) {
            tracked.track();
        }
        flier.land();
    }

//Part-13
/*
        The argument to the method is something that flies, it's a parameter of the FlightEnabled type. I'll call the
    takeOff and fly method on that variable. Then after the flier is flying, we want to track it, if it's actually trackable.
    To test this, we use the instanceOf operator with pattern matching. We've seen and used this before, but it's important
    to take note now, that this is testing an interface type, Trackable, and it still works the same. If the object is
    a class that implements Trackable, then a local variable is set up, named tracked, with the type Trackable. And because
    of that, we can call the track method on that variable, tracked, that the instanceOf operator populated for us. So we
    want to make a call to the inFlight method from the main method.
*/
//End-Part-13

    private static void triggerFliers(List<FlightEnabled> fliers) {

        for (var flier : fliers) {
            flier.takeOff();
        }
//Part-24
/*
        This method, triggerFliers, is taking an ArrayList of FlightEnabled things, and then calling the takeOff method
    for each. And let's copy and pate this method, changing the name to flyFlyer, and change the method from takeOff to
    fly.
*/
//End-Part-24
    }

    private static void flyFlyer(List<FlightEnabled> fliers) {

        for (var flier : fliers) {
            flier.fly();
        }
//Part-25
/*
        And I'll paste triggerFliers again, and call this method landFliers, and call the flier.land method in the for
    loop.
*/
//End-Part-25
    }

    private static void landFliers(List<FlightEnabled> fliers) {

        for (var flier : fliers) {
            flier.land();
        }
//Part-26
/*
        Ok, so you can imagine, if you're writing an application for flightEnabled items, you might have a lot of methods,
    that take an ArrayList like this, as a parameter. I'm going to call these from the main method, and I'll pass them
    the fliers variable, the first variable, ArrayList, which matches the type of the methods.
*/
//End-Part-26
    }
}

