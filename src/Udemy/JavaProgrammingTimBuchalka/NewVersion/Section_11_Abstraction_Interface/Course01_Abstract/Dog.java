package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_11_Abstraction_Interface.Course01_Abstract;
//Part-6
/*
        And I'll add extends Animal there, meaning Dog inherits from Animal, or Dog is an Animal.

                                        public class Dog extends Animal {
                                        }

    So we have an error on that class declaration, says "Class 'Dog' must either be declared abstract or implement abstract
    method 'move(String)' in 'Animal'". Actually there are 2 methods there, that we need to implement, but this little
    error message is only popping up the first one. And still hovering my mouse over that, let me show you that IntelliJ
    has a selectable option in that popup, Implement methods. I'm going to pick that, which will bring up this next popup,
    and I want to select both the methods there.
*/
//End-Part-6

public class Dog extends Mammal {
    public Dog(String type, String size, double weight) {
        super(type, size, weight);
    }

    @Override
    public void move(String speed) {

        if (speed.equalsIgnoreCase("slow")) {
            //System.out.println(type + " walking");
            System.out.println(getExplicitType() + " walking");
        } else {
            //System.out.println(type + " running");
            System.out.println(getExplicitType() + " running");
        }
    }

//Part-19
/*
        In both cases, where I was printing out type, I can make a call getExplicitType, which is the method this class
    inherits from Animal. And I want to do that same thing on the Fish class, and it's move method.
*/
//End-Part-19

    @Override
    public void makeNoise() {

        if (type == "Wolf") {
            System.out.print("Howling! ");
        } else {
            System.out.print("Woof! ");
        }
    }

//Part-7
/*
        And that generates the methods, with the same signature as Animal's abstract methods, and empty code blocks.

                                        public class Dog extends Animal {       // still error

                                        ....
    You'll notice we still have an error there, "There is no default constructor available in 'CourseCodes.NewSections.Section_11_Abstraction_Interface.
    Course01_Abstract.Animal'". We didn't create a default constructor (meaning a no arguments constructor) in Animal.
    And because of that, we're forced to create a constructor, that will in turn call the explicit constructor we created
    on Animal. Using again IntelliJ's options, and creating constructor matching super,

                                        public Dog(String type, String size, double weight) {
                                            super(type, size, weight);
                                        }

    I want you to think about what this means for a minute. Even though our abstract class won't ever be instantiated, a
    constructor we declared on it must be called by every subclass constructor. This means we're forcing subclasses to also
    use this constructor. Are you starting to get a picture of the abstract class, as a much stricter parent? The abstract
    class can make subclasses follow its rules. Truthfully any parent can, but an abstract class never gets instantiated,
    so you have more freedom in building the rules on this kind of class. And well-behaved classes are a good idea.

        In the inheritance example, we had Dog implement with its own methods, like bark, run and wagTail. And we could
    still do that, but for the sake of time, I won't be implementing these extra methods. I'll just make the code, in these
    overridden methods, pretty simple. First I'll implement makeNoise.

                                        if (type == "Wolf") {
                                            System.out.print("Howling! ");
                                        } else {
                                            System.out.print("Woof! ");
                                        }

    Here, we can directly access type, a field on the abstract class, because we made that field protected. We didn't create
    any getters or setters on the Animal class. And this protected modifier lets subclasses use the field directly, as we
    show in this if condition. And again, if the type is a wolf, the noise will be howling, otherwise the noise will be
    a "Woof". Now notice, if we did nothing else, our Dog class works. Even though the abstract class is forcing us to
    create a method named move in our class, we don't actually have to put statements in that block. This is probably not
    what you'd do in a real application, but it's an option, if that method really makes no sense for your class. Let's
    go now to the main method, and see what we can do with Animal and Dog. I'll create an Animal variable.
*/
//End-Part-7

//    @Override
//    public String getExplicitType() {
//        return "";
//    }

//Part-23
/*
        Looking at control+o options, notice, it's not an option to override this method in the list shown there. But let's
    try anyway. This is the syntax for overriding a method from the parent class, but look, we've got an error.

        'getExplicitType()' cannot override 'getExplicitType()' in 'Section_11_....Animal'; overridden method is final

    we can see that overridden method is final, and we can't do this. I haven't really talked about the final keyword very
    much, but it simply means, for a method, that a subclass can't override it. So the final method is final and cannot
    be overridden. Again this isn't specific to an abstract class, but I wanted to show you how an abstract class can have
    both concrete, and abstract methods. And the concrete methods can be designed, so that the subclass can't change them.
    I'll comment this method so that the code compiles. There are 2 other things I want to cover, and I showed this on
    earlier courses, First I want to create another abstract class that extends Animal. I'll do that in the Animal.java
    source file.
*/
//End-Part-23

    @Override
    public void shedHair() {

        System.out.println(getExplicitType() + " shed hair all the time");
    }

//Part-31
/*
        And then I'll just add a line of code in that generated method. So every dog I've ever had, pretty much shed hair
    all year long, so I'll put that there. So go to main method and run this code,
*/
//End-Part-31
}
