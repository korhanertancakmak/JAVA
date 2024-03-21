package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_03_OOP1.Course13_Inheritance1;

/*
Course-43

                                                  Inheritance (Part-I)

         We can look at Inheritance as a form of code re-use. It's a way to organize classes into a parent-child hierarchy,
    which lets the child inherit(re-use), fields and methods from its parent.

                                                    Animal
                                                      ↓
                                         |--------Vertebrates-------|
                                         ↓                          ↓
                             |------Warm-blooded----|           |---Cold-blooded---|
                             ↓                      ↓           ↓                  ↓
                      |----Mammal---|              Bird     |--Fish--|             Reptiles
                      ↓             ↓                       ↓        ↓
                     Dog           Cat                   Salmon    Goldfish

    The animal kingdom, with its many classifications, is a pretty good place to start looking at hierarchical relationships.
    On this scheme, we show a small part of the Animal classification chart. Each word on this diagram represents a Class.
    The most generic, or base class, starts at the top of the hierarchy, "Animal". Every class below it is a subclass. So
    the "Animal" is the base class. All the other classes can be said to be subclasses of "Animal". A parent can have multiple
    children, as we see with "Mammal", which is the parent of Dog and Cat. A child can only have 1 direct parent, in Java.
    But it will inherit from its parent class's parent, and so on.

        Let's actually start working through some scenarios in code, so that i can show you what inheritance looks like
    in Java. First, let's start with an animal example. We'll create the animal base class, and define all the attributes
    and behaviors that animals have in common. We know that any animal would have characteristics, like size and weight.
    And animals move and make noise. Though animals move and make noise in very unique ways, we declare the methods on the
    base class. Let's see what this class might look like on a class diagram. This is just a drawing of the class, showing
    its fields first, and methods or behaviors in the section below that.

        A class diagram, allows us to design our classes before we build them. This diagram shows the Animal class, with
    the attributes we think that every kind of animal has. All animals have a type(what kind of animal it is). All animals
    have a size, and a weight. Below the fields, we have the behavior that animals have in common, move, and makeNoise.
    The move method will include a speed, fast or slow, as an argument.

        Let's create this Animal class now. After we have the class in the editor, we'll add the fields from our diagram:

                                private String type;
                                private String size;
                                private double weight;

    Okay, those are the fields, so now let's add a constructor. We'll use IntelliJ's code generation tool to do this:

                                public Animal(String type, String size, double weight) {
                                    this.type = type;
                                    this.size = size;
                                    this.weight = weight;
                                }

    Now we've got the first constructor. Let's keep this code simple, so we'll leave the getters and setters out. But we
    do want a method to print the fields out, so again using IntelliJ's generation tool, we'll pick Code, Generate, and
    this time, we'll select toString. And again, pick all the fields, and hit OK.

                                @Override
                                public String toString() {
                                    return "Animal{" +
                                            "type='" + type + '\'' +
                                            ", size='" + size + '\'' +
                                            ", weight=" + weight +
                                            '}';
                                }

    And there's our toString method, with all the fields in it. Ok, so we have attributes, a constructor, and a way to print
    out information about the animal, but we haven't implemented any behavior on Animal. Now, we'll add 2 methods on Animal,
    move and makeNoise. For move, we'll take a speed, which will just be a String for fast or slow, and then we'll print
    the animal type and the speed at which it's moving:

                                public void move(String speed) {
                                    System.out.println(type + " moves " + speed);
                                }

    And now we'll add makeNoise, that doesn't have any parameters:

                                public void makeNoise() {
                                    System.out.println(type + " makes some kind of noise");
                                }

    There you have it, we have our base class, the Animal, with its attributes, type, size and weight, and we have 3 methods
    on this class, toString, as well as move and makeNoise. Next, we want to create a more specific type of Animal, and
    for this, we're going to create a Dog class. Let's look at a class diagram that includes the Dog class.

                        Dog =>
                              earShape   : String
                              tailShape  : String
                              -------------------
                              bark()
                              run()
                              walk()
                              wagTail()

        So, here we show a Dog class above, but connected to Animal. This means Dog inherits from Animal. We can also say
    Dog, is, a type of Animal when we create a Dog object, it'll inherit type, size, and weight fields from the Animal
    class. These don't have to be explicitly declared in Dog. This is also true for the methods move, and makeNoise. We
    can specialise the Dog class with its own fields and behavior. We do this here, with earShape and tailShape, because
    dogs have ears and tails, whose shape is unique to their breeds. We're also saying Dogs have behaviors like bark, run,
    walk, and wagTail. How do we build this in Java, and make Dog inherit from Animal? Let's create the Dog class, first.
    To specify we want this class to inherit from Animal, we use another Java keyword, "extends".

        Using extends specifies the superclass(or the parent class) of the class we're declaring. We can say Dog is a subclass,
    or child class, of Animal. We can say Animal is a parent, or super class, of Dog. A class can specify one, and only
    one, class in its extends clause. Let's add that clause to our Dog class, it goes right after the class name, and before
    the opening curly brace:

                            public class Dog extends Animal {
                            }

    Now, you notice straight away that we've got an error. The error that's on the screen is saying, "There is no default
    constructor available in 'CoursesFrom032To065.Course043.Animal' class". And that's true, we never did create one, meaning we never created
    a constructor, with no arguments, on Animal. But why does matter to the Dog class? Well, right now we haven't declared
    any constructor for Dog, but maybe you'll remember that Java will declare one, implicitly, if we don't explicitly
    declare one. And just to see that, let's add a constructor to Dog, which mirrors the implicit constructor Java creates
    for us:

                            public class Dog extends Animal {
                                public Dog() {
                                    super();
                                }
                            }

    This is what the implicit constructor looks like, and now our error is in "super()". The reason it isn't working, is
    that statement on that line, super parentheses, saying that "Expected 3 arguments but found 0". So maybe, you're asking
    the question, what's super parentheses? You'll remember we used the keyword this, followed by parentheses and parameters,
    as a way to call another constructor in the same class. Well, super parentheses is similar to that. It's a way to call
    the constructor on the parent class, or super class. Here, we're calling Animal's constructor by using the keyword
    super, and then parentheses, which calls the default constructor on Animal.

        "super()" is a lot like "this()".  It's a way to call a constructor on the super class, directly from the subclass's
    constructor. Like "this()", it has to be the first statement of the constructor. Because of that rule, "this()" and
    "super()" can never be called from the same constructor. If you don't make a call to super(), then Java makes it for
    you, using super's default constructor. If your super class doesn't have a default constructor, then you must explicitly
    call "super()" in all of your constructors, passing the right arguments to that constructor. Those are a lot of rules,
    but don't worry if that's confusing right now. We'll be reviewing these rules coming up and in future videos.

        But for now, we have a compiler error on Dog, because Animal doesn't have a default, or no arguments constructor
    declared for it. Let's go back to Animal and add that in:

                            public Animal() {}

    And now we've got everything compiling, so that's a good thing. Before we do anything else with Dog, let's go back to
    the Main class. Before we create any instances, I want to create a method on the Main class, that'll take any Animal
    object, and execute its 3 methods. We'll call it doAnimalStuff, and pass it an Animal object, and the speed we want
    this animal to move. And then we'll have animal makeNoise, move, and then we'll print out all the attributes on animal.

                            public static void doAnimalStuff(Animal animal, String speed) {
                                animal.makeNoise();
                                animal.move(speed);
                                System.out.println(animal);
                                System.out.println("_ _ _ _");
                            }

    This method is static, because we want to call it from the main method. The last line is just to separate the data, so
    reading the output is easier. Let's create an instance of Animal first, and then pass that to this method:

                            public static void main(String[] args) {
                                Animal animal = new Animal("Generic Animal", "Huge", 400);
                                doAnimalStuff(animal, "slow");
                            }

    We created an animal object, gave it the type "Generic Animal" and the size "huge" as well as a weight of 400. And
    running that, we get:

                            Generic Animal makes some kind of noise
                            Generic Animal moves slow
                            Animal{type='Generic Animal', size='Huge', weight=400.0}
                            _ _ _ _

    Ok, so that's a generic animal. Let's create a dog this time:

                            Dog dog = new Dog();
                            doAnimalStuff(dog, "fast");

    Before we run this, do you notice what we're doing here? We're passing a Dog object as the method argument, when the
    type was declared as an Animal. Why is this okay? It works because Dog inherits from Animal, it's a type of Animal,
    as we've said, and where that becomes really important, is in code like this. We can pass a dog instance to any method,
    that takes an animal. And running that:

                            Generic Animal makes some kind of noise
                            Generic Animal moves slow
                            Animal{type='Generic Animal', size='Huge', weight=400.0}
                            _ _ _ _
                            null makes some kind of noise
                            null moves fast
                            Animal{type='null', size='null', weight=0.0}
                            _ _ _ _

    The code compiles and runs and we get output. We created a Dog with a default constructor(no arguments passed), so
    nothing got set on this class, but you can see Dog has inherited all of Animals attributes, on that last line. The
    values have the default values for their type, because we didn't create a way to pass any data to these fields on Dog.
    So far, all we've done with Dog is extend Animal. Next, let's change our default constructor in Dog, and this time,
    when we call "super()", we'll pass values to Animal's 3 parameter constructor. We'll pass the type of dog, which here
    we'll call it a mutt, then the size, big, and 50 for the weight.

                            public Dog() {
                                super("Mutt", "Big", 50);
                            }

    And we can rerun our main method:

                            Generic Animal makes some kind of noise
                            Generic Animal moves slow
                            Animal{type='Generic Animal', size='Huge', weight=400.0}
                            _ _ _ _
                            Mutt makes some kind of noise
                            Mutt moves fast
                            Animal{type='Mutt', size='Big', weight=50.0}
                            _ _ _ _

    And now, the output has our Mutt's attributes, so it prints out that Mutt makes some kind of noise, Mutt moves fast,
    and then it shows the other attributes on Mutt. What i want you to see here is, look at the Dog class. It's 6 lines of
    code, including the opening and closing braces and white space. But this code can now execute 3 methods, and has 3
    attributes we can set, all inherited from Animal. And better yet, we can treat this Dog like any Animal, for any code
    that uses the Animal class. Our doAnimalStuff method in the Main class, didn't have to change at all, even though we
    introduced a new class. Up until this point, we haven't specialised anything for the Dog.

        In the next course, we'll add the fields and methods that make the Dog class special.
*/

public class Main {
    public static void main(String[] args) {

        Animal animal = new Animal("Generic Animal", "Huge", 400);
        doAnimalStuff(animal, "slow");

        Dog dog = new Dog();
        doAnimalStuff(dog, "fast");
    }

    public static void doAnimalStuff(Animal animal, String speed) {

        animal.makeNoise();
        animal.move(speed);
        System.out.println(animal);
        System.out.println("_ _ _ _");
    }
}
