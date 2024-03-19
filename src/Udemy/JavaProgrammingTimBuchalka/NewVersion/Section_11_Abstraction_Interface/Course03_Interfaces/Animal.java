package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_11_Abstraction_Interface.Course03_Interfaces;
//Part-22
/*
        Ok, so we've created the OrbitEarth interface, which extends FlightEnabled, and we have one abstract method declared
    on this interface. And that's the achieveOrbit method. To keep it simple, above this interface, I'll create a new class
    called Satellite. And I'll make that implement orbit earth. And we see that doesn't compile. Instead of picking implement
    methods there, I'm just going to copy the method achieveOrbit from the interface just below, and paste it into my class.
    And I'll just put a statement, in its block, that orbit was achieved. But I still have a compile error on this method.
    If I hover over that, you can see I get an error we've seen before, but maybe you've forgotten about.

    achieveOrbit()' in 'CourseCodes.NewSections.Section_11_Abstraction_Interface.Course03_Interfaces.Satellite' clashes with 'achieveOrbit()' in
    'CourseCodes.NewSections.Section_11_Abstraction_Interface.Course03_Interfaces.OrbitEarth'; attempting to assign weaker access privileges
    ('package-private'); was 'public'

    Well, even though we copied this method from the interface, we didn't copy the implicit public access modifier on that
    method. If you don't use IntelliJ's tools, to implement an interface's abstract methods, you may discover these kinds
    of problems. Because this method, as I've stated before, is really public on the interface, I need to specify public
    here. I'll change that. Ok, so that fixed that compiler error, and we can see that IntelliJ recognizes that we're
    implementing a method on the interface. We can see though, there's still a problem with this class. We still have to
    implement all the methods on FlightEnabled, because OrbitEarth extends that interface. And I'll implement those. The
    default implementations are good enough for this demonstration. Although for a Satellite, you can imagine the implementation
    of these methods might not be the same as for a Bird.

        And one other thing I want to show you, is that both records and enums can implement interfaces. But they can't
    extend classes, abstract or not. Again, for simplicity, above the Satellite class, I'll create a record, let's say
    it's a DragonFly. And I'll add 2 parameters of type String, and it'll implement FlightEnabled. And normally, records
    wouldn't have class bodies, but because I'm implementing FlightEnabled, this record needs to implement FlightEnabled's
    abstract methods. I'll generate those methods. I could implement special functionality for this DragonFly, that means
    it can be treated like any other FlightEnabled object, so that's pretty cool. This is also true for the enum type too.

        Again, I'll create an enum here, above the DragonFly record. And after the enum name, I'll add implements Trackable,
    before the constants list. And for the constants list, I'll add grounded, launch, cruise, and data collection. This
    enum describes 4 stages of a satellite launch. What we're saying by doing this, is that we really want each of these
    stages, to be trackable. And like before, I've got an error, because this enum doesn't yet implement the track method.
    I'll generate that method. Let me add code in that method next. I'll just say if the current stage, represented by
    this keyword, isn't GROUNDED, I'll do some monitoring. You can imagine that you'd want to monitor each of these stages
    in some different way, but I'll keep this simple. I'll come back to this method in a bit, but what this means is, you
    can have all these various types of classes implement an interface. This means we can treat these just like other types,
    and refer to them by the interface type. Let's loop back and talk about reference types again, now that we've covered
    both abstract methods and interfaces.

                                    Abstracted Types - Coding to an Interface

        Both interfaces and abstract classes are "abstracted reference types". Reference types can be used in code, as
    variable types, method parameters, and return types, list types, and so on. When you use an abstracted reference type,
    this is referred to as "coding to an interface". This means your code doesn't use specific types, but more generalized
    ones, usually an interface type. "This technique is preferred", because it allows many runtime instances of various
    classes, to be processed uniformly, by the same code. It also allows for substitutions of some other class or object,
    that still implements the same interface, without forcing a major refactor of your code. Using interface types as the
    reference type, is considered a best practice. In my coding examples to date, I didn't demonstrate this, and that was
    on purpose, because we hadn't yet reviewed interfaces. I want to walk through some examples of this next with you.
    I'll go back to our main method, and create an ArrayList of FlightEnabled types.
*/
//End-Part-22

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

//Part-2
/*
        I'll include 2 interfaces, in the same source file. Next, I want a new class named Bird, in the same package.
*/
//End-Part-2

interface FlightEnabled {
//Part-9
/*
        We have 3 methods here, and you can see this code compiles. None of these methods have a method body, but we haven't
    used the abstract modifier on the fly method, so why is this still ok? Why does it compile?

                            The abstract modifier is implied on an interface

        We don't have to declare the interface type abstract, because this modifier is implicitly declared, for all interfaces.

                abstract interface FlightEnabled {      // abstract modifier here is unnecessary
                }                                       // and redundant

    Likewise, we don't have to declare any method abstract. In fact, any method declared without a body, is really "implicitly
    declared both public and abstract". The three declarations shown below, result in the same thing, under the covers:

                public abstract void fly();         // public and abstract modifiers are redundant, meaning unnecessary to declare
                abstract void fly();                // abstract modifier is redundant, meaning unnecessary to declare
                void fly();                         // This is PREFERRED declaration, public and abstract are implied.

    If I hover over the public modifier in the first statement, you can see the message, "Modifier 'public' is redundant
    for interface members". I'll mouse over the abstract modifier in the second statement, and this time, "Modifier 'abstract'
    is redundant for interface methods". So these declarations are all the same. But now, if I change public to protected,
    in that first statement:

                public abstract void takeOff();       to        protected abstract void takeOff();

    we do get a compiler error, because modifier protected not allowed here.

                            All members on an interface are implicitly public

        If we omit an access modifier on a "class member", it's "implicitly package private". If we omit an access modifier
    on an "interface member", it's "implicitly public". This is an important difference, and one you need to remember.
    Changing the access modifier of a method to "protected", on an interface, is a "compiler error", whether the method
    is concrete or abstract. Only a concrete method can have private access. Let's go back to the code and remove those
    modifiers. And while we're in this source file, let's add a method to the Trackable interface.
*/
//End-Part-9

    //public abstract void takeOff();
    void takeOff();
    //abstract void land();
    void land();
    void fly();

//Part-19
/*
        Here I've created 2 fields, both doubles. And it looks like I've gone against the naming convention, and used all
    uppercase letters with underscores between them. Why did I do this? Well, it turns out, any fields declared on an interface,
    are not really instance fields. They're implicitly public, static, and final. Which means they're really constants.
    We haven't talked a lot about constants yet. We referred to them a little bit, when we talked about the enum, but only
    briefly.

                                            The final modifier in Java

        "When we use the final modifier, we prevent any further modifications to that component."
  - a final method means it can't be overridden by a subclass.
  - a final field means an object's field can't be reassigned or given a different value, after its initialization.
  - a final static field is a class field that can't be reassigned, or given a different value, after the class's initialization
    process.
  - a final class can't be overridden, meaning no class can use it, in the extends clause.
  - a final variable, in a block of code, means that once it's assigned a value, any remaining code in the block can't
    change it.
  - a final method parameter means, we can't assign a different value to that parameter in the method code block.

    We'll be covering each of these scenarios in turn, but right now I want to talk about the final static field, is what
    you're really creating, when you declare a field on an interface.

                                                Constants in Java

        A constant in Java is a variable that can't be changed. A constant variable is a final variable of primitive type,
    or type String, that is initialized with a constant expression. Constants in Java, are usually named with all uppercase
    letters, and with underscores between words. A static constant means we access it via the type name. We saw this with
    the INTEGER.MAX_VALUE, and the INTEGER.MIN_VALUE fields.

                        A field declared on an Interface is always public, static and final

        Java lets us specify these like an ordinary field on an interface, which might be kind of confusing, and misleading
    to a new Java programmer. But we can declare them with any combination of those modifiers, or none at all, with the
    same result. These all mean the same thing on an interface.

                                        double MILES_TO_KM = 1.60934;
                                        final double MILES_TO_KM = 1.60934;
                                        public final double MILES_TO_KM = 1.60934;
                                        public static final double MILES_TO_KM = 1.60934;

    But coding it in IntelliJ, any of the statements, except the first statement, will give you the information, that the
    modifiers are redundant. So let's go try that out. Going to the code,
*/
//End-Part-19

    double MILES_TO_KM = 1.60934;
    //public static final double MILES_TO_KM = 1.60934;
    double KM_TO_MILES = 0.621371;

//Part-20
/*
        Let's add "public static final" in front of double. Notice that, all 3 of those modifiers are greyed out in IntelliJ,
    and if we hover over any one of them, we see that "Modifier "public static final" is redundant for interface fields".
    So let's revert that last change. You can access these fields like you do with a class's static fields, using the type
    name, and then the field name. I'll add some code that uses that constant, to convert km to miles. Going back to the
    main method,
*/
//End-Part-20
}

interface  Trackable {
//Part-10
/*
        I'll call this one track. What's wrong with this code? Well, every method on an interface is assumed to be public
    and abstract, unless otherwise specified, and in this case we've included a method body. The method body means something
    is wrong. Either this method is missing a more meaningful body, or the method body shouldn't be here. I'll be talking
    about concrete methods on an interface in a little bit. But for now, I'll remove those curly braces, and prelace it
    with a semicolon.

        What good is a type that has methods that don't do anything? Why is this such a powerful thing? Let's get back
    to the Bird class to find out why.
*/
//End-Part-10

    //void track() {};
    void track();
}

public abstract class Animal {
//Part-6
/*
        Remember that an abstract method on an abstract class, has to be declared with the abstract keyword. This causes
    our Bird class to have a compiler error, because we haven't implemented that method on Bird yet. Going to Bird.java
    source file,
*/
//End-Part-6

    public abstract void move();
}
