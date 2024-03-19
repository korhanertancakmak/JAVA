package CourseCodes.NewSections.Section_11_Abstraction_Interface.Course01_Abstract;


//Part-1
/*
                                            Generalization And Abstraction

        In this section, we'll be talking about abstraction and generalization. These concepts, in action, reduce the amount
    of code we have to write, and encourage extensible and flexible code. When I say code is extensible, I mean it can
    support future enhancements and changes, with little or no effort. An extensible application is designed with change
    in mind. In this section of the course, we'll be looking at Java's support for these two concepts.

        We use the terms "Abstraction" and "Generalization", when we start trying to model real world things in software.
    Before I launch into interface types and abstract classes, I want to talk about what these concepts mean. When you
    start modeling objects for your application, you start by identifying what features and behavior your objects have in
    common. We generalize when we create a class hierarchy. A base class is the most general class, the most basic building
    block, which everything can be said to have in common.

        Part of generalizing is using abstraction. You can generalize a set of characteristics and behavior into an abstract
    type. If you consider an octopus, a dog, and a penguin, you would probably say they're all animals. An animal is really
    an abstract concept. An animal doesn't really exist, except as a way to describe a set of more specific things. If
    you can't draw it on a piece of paper, it's probably abstract. An animal would be hard to draw for example, if you
    weren't given more information about the actual type of animal to draw. Abstraction simplifies the view of a set of
    items' traits and behavior, so we can talk about them as a group, as well as generalize their functionality. Java
    supports abstraction in several different ways:

  - Java allows us to create a class hierarchy, where the top of the hierarchy, the base class, is usually an abstract
    concept, whether it's an abstract class or not.
  - Java lets us create abstract classes.
  - Java gives us a way to create interfaces.

                                                Abstract Method

        An abstract method has a method signature, and a return type, but doesn't have a method body. Because of this,
    we say an abstract method is "unimplemented". Its purpose is to describe behavior, which any object of that type will
    always have. Conceptually, we can understand behaviors like move or eat on an Animal, so we might include those as
    abstract methods, on an abstract type. You can think of an abstract method as a contract. This contract promises that
    all subtypes will provide the promised functionality, with the agreed upon name and arguments.

                                                Concrete Method

        A concrete method has a method body, usually with at least one statement. This means it has operational code, that
    gets executed, under the right conditions. A concrete method is said to implement an abstract method, if it overrides
    one. Abstract classes and interfaces, can have a mix of abstract and concrete methods.

                                                Method Modifiers

        I've already covered access modifiers and what they mean for types, as well as members of types. And we know we
    have public, protected, package, and private access modifiers, as options for the members. In addition to access modifiers,
    methods have other modifiers, which we'll list here, as a high-level introduction:

    MODIFIERS    | PURPOSE
    abstract     : When you declare a method abstract, a method body is always omitted. An abstract method can only be
                   declared on an abstract class or an interface.
    static       : Sometimes called a class method, rather than an instance method, because it's called directly on the
                   Class instance and doesn't access any single instance of the class.
    final        : A method that is final cannot be overridden by subclasses.
    default      : This modifier is only applicable to an interface, and we'll talk about it in our interface courses.
    native       : This is another method with no-body, but it's very different from the abstract modifier. The method
                   body will be implemented in platform-dependent code, typically written in another programming language
                   such as C. This is an advanced topic and not generally commonly used, and we won't be covering it in
                   this course.
    synchronized : This modifier manages how multiple threads will access the code in this method.  We'll cover this in
                   a later section on multithreaded code.

                                                What is "it"?

        Now consider these sentences:

  - We adopted a new pet this weekend.
  - I ordered something I really wanted from the store.
  - I bought a ticket and won a prize.

    If we said any of these things to a friend or coworker, it might be frustrating for them. We haven't given them enough
    information in any of these cases. In other words, they can't paint a picture in their head, because they lack details.
    New pet, something ordered, and a ticket, are too general when talking about one item. On the other hand, when we talk
    about "groups of things", we don't usually need too many specifics. Consider these sentences:

  - I need to get home to feed the animals.
  - I'm waiting for my box of stuff from an online store to be delivered.

    So here, "animals", and "stuff", are probably enough information, to fully describe the situation.

                                                Abstract Class

        The abstract class is declared with the "abstract" modifier. Here we are declaring an abstract class called Animal.

                        abstract class Animal() {}      // An abstract class is declared with the abstract modifier

    An "abstract" class is a class that's "incomplete". For that reason, You can't create an instance of an abstract class.

                        Animal a = new Animal();        // INVALID, an abstract class never gets instantiated.

    But an abstract class can still have a constructor, which will be called by its subclasses, during their construction.
    An abstract class's purpose, is to define the behavior, its subclasses are required to have, so it always participates
    in "inheritance". For the examples below, assume that Animal is an abstract class. Classes extend abstract classes,
    and can be concrete.

                        class Dog extends Animal {}     // Animal is abstract, Dog is not

    Here, Dog extends Animal, Animal is abstract, but Dog is concrete. A class that extends an abstract class, can also
    be abstract itself, as I show with this next example.

                        abstract class Mammal extends Animal {}         // Animal abstract, Mammal is also an abstract

    Mammal is declared abstract, and it extends Animal, which is also abstract. And finally an abstract class can extend
    a concrete class.

                        abstract class BestOfBreed extends Dog {}       // Dog is not abstract, but BestOfBreed is

    Here we have BestOfBreed, an abstract class, extending Dog, which is concrete.

                                        What's an Abstract Method is?

        An abstract method is declared with the modifier "abstract". You can see below, that we're declaring an abstract
    method called move, with a void return type. It simply ends with a semicolon. It doesn't have a body, not even curly
    braces.

                        abstract class Animal() {
                            public abstract void move();
                        }

    Abstract methods can only be declared on an abstract class or interface. An abstract method tells the outside world,
    that all Animals will move, in the example we show here. Any code that uses a subtype of Animal, knows it can call the
    move method, and the subtype will implement this method with this signature. This is also true for a concrete class,
    and a concrete method that's overridden. You might be asking, what's the difference, and when would you use an abstract
    class.

        In the courses on inheritance, we created a very basic Animal class, and then we extended it to create a Dog. Here,
    I show you the class diagram from that lecture again.

                          Animal =>                         Dog =>
                                 type: String                   earShape   : String
                                 size: String                   tailShape  : String
                                 weight: double       <<<<<<    -------------------
                                 ---------------------          bark()
                                 move(String speed)             run()
                                 makeNoise()                    walk()
                                                                wagTail()

    In that example, Animal was a concrete class, so the move and makeNoise methods had code in their method bodies. Subclasses
    have choices when they extend a concrete class with concrete methods.
  - They can inherit the same behavior from their parent. This means they don't have to even declare the methods in their
    class bodies.
  - They can override the behavior from their parent. This means they have a method with the same signature, but with their
    own code in there, ignoring the parent's code altogether.
  - They can also override the behavior but leverage the parent's method code, by calling the parent's method, using super
    in their overridden code.

        But what happens if Animal is declared as abstract, and the move and makeNoise methods are also abstract? If Animal
    is abstract and it's methods are abstract, subclasses no longer have the options we just talked about. There is no
    concrete method for a subclass to inherit code from. Instead, the subclass must provide a concrete method for any abstract
    method declared on its parent. The subclass won't compile if it doesn't implement the abstract methods. Why would this
    behavior be preferred to a concrete class? There may be times when it really doesn't make any sense at all, for a base
    class to provide default behavior for a method. This mechanism forces designers of the subclasses, to provide the
    implementation that's appropriate, to the class they're designing. Now let's build this new version of our model. I'll
    create a new class in this package, called Animal.


*/
//End-Part-1

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        // Animal animal = new Animal("animal", "big", 100);

//Part-8
/*
        And this won't compile. Error saying ""Animal" is abstract; cannot be instantiated". Animal is abstract, and even
    though we have a constructor and fields, we can't create an instance of an abstract class. This is another advantage
    of an abstract class. What is an animal after all, that you'd want a real instance of one? So let's comment that code
    out, and create a Dog instead.
*/
//End-Part-8

        Dog dog = new Dog("Wolf", "big", 100);
        dog.makeNoise();

//Part-9
/*
        Ok, so we have a Dog variable, and create a new instance of Dog, passing Wolf as the type, big as the size, and
    100 as the weight. And we call makeNoise on the dog object. Running that,

                    Howling!

    Because we made the type wolf, this dog is howling. This is all well and good, but it doesn't really explain why we'd
    build a hierarchy with an abstract class. Where this becomes interesting, is when we can use Dog anywhere an Animal
    is used. Let me create a method on this Main class. I'm going to call id doAnimalStuff.
*/
//End-Part-9

        doAnimalStuff(dog);

//Part-11
/*
        And when I run that,

                    Howling! Howling!

    we see that our dog howled twice, and yet didn't do anything for moving. Let's put some simple code in that method,
    on the dog class.

                    if (speed.equalsIgnoreCase("slow")) {
                        System.out.println(type + " walking");
                    } else {
                        System.out.println(type + " running");
                    }

    And running the code again,

                    Howling! Howling! Wolf walking

    See that even though we called move on the Animal class, in the Main Class's doAnimalStuff method, and that class didn't
    have any code in it for the move method, we get Wolf Walking. This is really calling Dog's move method, because we
    passed a dog instance to this method. And for good measure, let's create a fish class. For this, I'm just going to
    copy Dog.
*/
//End-Part-11

        ArrayList<Animal> animals = new ArrayList<>();

//Part-15
/*
        And we do this by passing the type parameter as Animal. If we used Dog or Fish, we'd have to have 2 array lists.
    But if we type it with Animal, this list can hold any kind of Animal, and that includes both Fish and Dog. Again, this
    feature is not unique to an abstract class, it's just a benefit of inheritance, in this case. Now I'll add some animals
    to this list.
*/
//End-Part-15

        animals.add(dog);
        animals.add(new Dog("German Shepard", "big", 150));
        animals.add(new Fish("Goldfish", "small", 1));
        animals.add(new Fish("Barracuda", "big", 75));
        animals.add(new Dog("Pug", "small", 20));

//Part-16
/*
        In this code, we use the add method on the array list, and can pass any instance that inherits from animal. You
    can see I've added a number. Next, I'll add an enhanced for loop.
*/
//End-Part-16

//        for (Animal animal : animals) {
//            doAnimalStuff(animal);
//        }

//Part-17
/*
        Notice that loop variable is an Animal. Remember we can do this for any object that is an Animal. And then we call
    doAnimalStuff, passing the animal in our array list. And now if we run this,

                    Howling! Wolf walking
                    Woof! German Shepard walking
                    swish Goldfish lazily swimming
                    splash Barracuda lazily swimming
                    Woof! Pug walking

    we see output for all our different types of dogs and fish. So that's nice, that we can have an array list of an abstracted
    type. And at runtime, instances that inherit from that class, can use polymorphism to execute code specific to the
    concrete type.

        Eventually, our abstract class had protected and private fields, and 2 abstract methods. But an abstract class
    can also have concrete methods. Let's say we wanted a helper method on Animal, that printed out both the instance's
    class name, and its type. Going to the Animal Class, I'll add that.
*/
//End-Part-17

//Part-21
/*
        And running,

                    Woof! Dog (German Shepard) walking
                    swish Fish (Goldfish) lazily swimming
                    splash Fish (Barracuda) lazily swimming
                    Woof! Dog (Pug) walking

    we can see we have the simple class name, Dog or Fish, and the type of the dog or fish in parentheses. Your abstract
    class can provide concrete methods as we showed here, that follow the same rules as a concrete class's method. Our
    subclasses can simply use them, as we did here, inheriting that behavior from the parent. We could also override those
    methods. But let's just say Animal is a strict parent indeed, and doesn't want us to override this method. It likes
    this format, and wants all classes to use it, just the way it's written. Going back to the Animal class, I'll add
    the modifier final.
*/
//End-Part-21

        animals.add(new Horse("Clydesdale", "large", 1000));
        for (Animal animal : animals) {
            doAnimalStuff(animal);
            if (animal instanceof Mammal currentMammal) {
                //animal.shedHair();
                currentMammal.shedHair();
            }
        }

//Part-30
/*
        After commenting the for loop to rewrite it after the code we add, and running this code,

                Horse (Clydesdale) walks

    we see that our horse is included now in the output. But how would we call the shedHair method? Well, maybe you remember
    instanceof method, we introduce you to earlier. I'll use it here. So in the if loop, we know animal is an Animal, but
    it could be a lot of things at this point, like Dog, Fish or Horse. But it could also be a Mammal, a type of Mammal.
    And we should always try to code generically, so that when new subclasses are added, it won't be disruptive to our
    code. In the if loop, we use the pattern matching version, of the instanceof operator. We check if our loop variable,
    currentAnimal, is a mammal. If that's true, we'll get a mammal variable, named currentMammal, which gets assigned the
    loop element, only if it's really a Mammal subtype. Doing this, means we can call any method on currentMammal, that's
    declared on the Mammal abstract class, in addition to the ones on Animal. And running this,

                Horse (Clydesdale) sheds in the spring

    we can see, since Horse is the only class that extends Mammal, it prints out "Horse (Clydesdale) sheds in the spring".
    Notice what happens if we try to call shedHair on the animal variable.

                animal.shedHair();

    This code won't even compile. That's because animal is an Animal type, and the method shedHair's not on the Animal class.
    I want to make 1 more change. I want to change "Dog", to extend Mammal instead of Animal.

                public class Dog extends Mammal {
                }

    Hopefully, you're not surprised we get an error here, and that's because Mammal has that abstract method, shedHair.
    And this class, Dog, doesn't have a method for that. So I need to implement shedHair.
*/
//End-Part-30


//Part-32
/*
        And running the code again,

                Howling! Howling! Dog (Wolf) walking
                Howling! Dog (Wolf) walking
                Dog (Wolf) shed hair all the time                       <<<<<<<<<
                Woof! Dog (German Shepard) walking
                Dog (German Shepard) shed hair all the time             <<<<<<<<<
                swish Fish (Goldfish) lazily swimming
                splash Fish (Barracuda) lazily swimming
                Woof! Dog (Pug) walking
                Dog (Pug) shed hair all the time                        <<<<<<<<<
                Horse (Clydesdale) walks
                Horse (Clydesdale) sheds in the spring

    you can see that for all our dogs, we're getting that they shed hair all the time. So that's an example of an abstract
    class, extending another abstract class. I won't cover the use case where we have an abstract class, that extends a
    concrete class. It's a lot rarer to do something like that, but just be aware that that's an option. You might be asking,

                                                Why use an Abstract Class?

        In truth, you may never need to use an abstract class in your design, but there are some good arguments for using
    them. An abstract class in your hierarchy forces the designers of subclasses, to think about, and create unique and
    targeted implementations, for the abstracted methods. It may not always make sense to provide a default, or inherited
    implementation, of a particular method. An abstract class can't be instantiated, so if you're using abstract classes
    to design a framework for implementation, this is definitely an advantage.

        In our example, we don't really want people creating instances of Animals or Mammals. We used those classes to
    abstract behavior, at different classification levels. All Animals have to implement the move and makeNoise methods,
    but only Mammals needed to implement shedHair, as we demonstrated.
*/
//End-Part-32
    }

    private static void doAnimalStuff(Animal animal) {

        animal.makeNoise();
        animal.move("slow");
    }

//Part-10
/*
        I've said that you can't create an instance of Animal, because it's abstract. That's true, but that doesn't prevent
    us from using that type in methods, declarations, and lists. In fact, this is what makes the code so flexible and
    scalable. The abstract class told the world that animals move and make noise, so anyone can use an Animal class, and
    call those methods on the object that it gets at runtime. And regardless of what that object is, as long as it's an
    Animal, meaning it has the Animal class in its hierarchy, this code will continue to work. This is abstraction, promoting
    polymorphism techniques. I'll add a call to this in the main method.
*/
//End-Part-10
}
