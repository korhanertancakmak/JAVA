package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_03_OOP1.Course15_Inheritance3;

/*
Course-45

                                                  Inheritance (Part-III)

        In the last course, we were working on a simple class diagram, to create two classes, Animal and Dog, where Dog
    inherited from Animal. Let's continue with this example, by adding other methods that are really specific to Dog.
    Remember the Class diagram for Dog and Animal:

            Dog =>
                    earShape  : String
                    tailShape : String
                    ------------------
                    bark()
                    run()
                    walk()
                    wagTail()

    We can see there, the methods on Dog are bark, run, walk, and wagTail. Let's add these, starting with the bark method.
    I'm going to make this method private, because I'm going to call it from the move method. This is a reminder, that not
    all methods need to be exposed, especially if you only intend them to be called internally from the current class.

                    private void bark() {
                        System.out.println("Woof! ");
                    }

                    private void run() {
                        System.out.println("Dog running ");
                    }

                    private void walk() {
                        System.out.println("Dog walking ");
                    }

                    private void wagTail() {
                        System.out.println("Tail wagging ");
                    }

    Okay, so those are our Dog behaviors, so let's use some of these methods from Dog's move method.

                    @Override
                    public void move(String speed) {
                        super.move(speed);
                        //System.out.println("Dogs walk, run and wag their tail ");
                        if (speed == "slow") {
                            walk();
                            wagTail();
                        } else {
                            run();
                            bark();
                        }
                    }

    Now if we run that:

                    ..... // above all the same(since there is no move method output)
                    Yorkie moves fast
                    Dog running
                    Woof!
                    Dog{earShape='Perky', tailShape='Curled'} Animal{type='Yorkie', size='medium', weight=15.0}
                    _ _ _ _
                    Labrador Retriever moves slow
                    Dog walking
                    Tail wagging
                    Dog{earShape='Floppy', tailShape='Swimmer'} Animal{type='Labrador Retriever', size='large', weight=65.0}
                    _ _ _ _

    the output shows our dogs moving, first the "yorkie moves fast", and then it prints "Dog running, woof!". Then we have
    "Labrador Retriever moves slow", and that's now also printing "Dog walking, tail wagging". And again, nothing in the
    doAnimalStuff method had to change, for this new functionality to be called. I hope you're starting to get a little
    glimpse at how powerful a feature this is. And lastly, we can call the bark method in the overridden method, makeNoise,
    which right now, has no code in it. Here we're not calling Animal's makeNoise method, and we don't want to. We want
    the Dog to have behavior, that the Animal doesn't have.

                    public void makeNoise() {
                        bark();
                    }

    Let's run that:

                    Woof!
                    Yorkie moves fast
                    Dog running
                    Woof!
                    Dog{earShape='Perky', tailShape='Curled'} Animal{type='Yorkie', size='medium', weight=15.0}
                    _ _ _ _
                    Woof!
                    Labrador Retriever moves slow
                    Dog walking
                    Tail wagging
                    Dog{earShape='Floppy', tailShape='Swimmer'} Animal{type='Labrador Retriever', size='large', weight=65.0}
                    _ _ _ _

    Now, we can get "woof", for both types of dogs, when we call the makeNoise method, in the main class. We've overridden
    the makeNoise method, with code unique to the dog, which in this case, makes a call to the bark method. And that's
    how you do it, you can separate the functionality that's just for a dog, and only include it in the dog class. Let's
    try to change our makeNoise method again. This time, if the type of the dog is a Wolf, let's have the Dog howl, and
    bark.

                    public void makeNoise() {
                        if (type == "Wolf") {
                            System.out.println("Ow Woooo! ");
                        }
                        bark();
                    }

    Now, in this case, where we're referencing type, we get a compiler error, which says "'type' has private access in
    'Animal'. This is because type has private access in Animal. But type is one of the fields inherited by Dog. Yes, but
    because type is private on Animal, no other classes, not even subclasses, can access or use this field, in its own
    methods. We've said there's a modifier that allows access for subclasses, and that's the protected modifier. Let's go
    to the Animal class, and change the modifier from private to protected, for the type field. What this modifier says
    is, let any class, that is a subclass, access this field. This is conditional encapsulation. We're allowing some limited
    access, to our internal fields, and that's to subclasses. Protected access also means that any classes in the same
    package, will also have access. And changing that modifier means our code compiles successfully.

        Let's look at that code in Dog again. Notice here, that we just simply reference type here. We didn't add any other
    qualifier, not this(), or super(), and we didn't have to call type from a different instance of Dog. This is another
    advantage of Inheritance, for fields and methods that aren't private. They can be accessed directly, as if they really
    were declared, on the subclass itself. Java first looks on the subclass for a method or a field with that name, then
    it'll go up the inheritance tree, looking for a match. Let's quickly test this, by creating a wolf in the main method
    of the Main class:

                    .... (nothing changed)
                    Dog wolf = new Dog("Wolf", 40);
                    doAnimalStuff(wolf, "slow");

    And now, running this code:

                    ..... (nothing changed)
                    Ow Woooo!
                    Woof!
                    Wolf moves slow
                    Dog walking
                    Tail wagging
                    Dog{earShape='Perky', tailShape='Curled'} Animal{type='Wolf', size='large', weight=40.0}
                    _ _ _ _

    we can see all the information about our wolf, and we can also see the output, that our wolf is howling, as well as
    barking. That was the Dog class. Let's add another subclass, so you can get used to this inheritance concept, and the
    idea of extending another class.

        Let's add the Fish class to our hierarchy. What are some unique characteristics of a fish? Well, let's go with a
    couple, like gills, and fins. And instead of a generic move method, we might have more specific methods like moveMuscles,
    and moveBackFins. Let's look at our class diagram that includes this new Fish class:

                    Fish =>
                            fins  : int
                            gills : int
                            -----------
                            moveMuscles()
                            moveBackFins()

    It's quite a bit different from Dog, but it's still an Animal. This diagram shows a new class named Fish, that extends
    Animal. It has 2 fields and 2 methods, specific to its own type. Let's build that Fish class.

                    public class Fish extends Animal {
                        private int gills;
                        private int fins;

                        public Fish(String type, double weight, int gills, int fins) {
                            super(type, "small", weight);
                            this.gills = gills;
                            this.fins = fins;
                        }
                    }

    Also we pick the Animal constructor with parameters as its first constructor, and then 2 field for Fish. And like we
    did with Dog, we changed "size" parameter from the input parameter, and this time, to be hard coded to "small", for
    simplicity. This constructor is a lot like Dog's. We're calling the super constructor, the constructor on Animal, and
    we pass the type, the size, and this time we'll make all fish small. And finally, we pass the weight. Then we add the
    assignments for Fish's more specialized fields, gills and fins. So, very similar to the dog class. But in this case,
    we've created a new fish class, that inherits from the animal class, and we've defined some unique characteristics
    for the fish, namely, gills and fins. Let's next add Fish's custom behavior, and add the method, moveMuscles first,
    and we'll just print out a statement for that. And we'll make this method private, because we only want the move method
    to call it. We won't expose this behavior in other words, for any outside code to call it directly.

                    private void moveMuscles() {
                        System.out.print("muscles moving ");
                    }

                    private void moveBackFin() {
                        System.out.print("Backfin moving ");
                    }

    And now, we'll override the move method from Animal, so that our fish moves(or swims). With our cursor right before
    the closing brace of the Fish class, we'll start typing public void, and you'll see IntelliJ pops up a list of methods,
    and from that we'll select move.

                    @Override
                    public void move(String speed) {
                        super.move(speed);
                    }

    And now we have the overridden move method generated for us. Like we did with the Dog class, let's extend this behavior
    for a fish. We'll have our fish move its muscles regardless of the speed, but use its backfin if it wants to go fast.

                    @Override
                    public void move(String speed) {
                        super.move(speed);
                        moveMuscles();
                        if (speed == "fast") {
                            moveBackFin();
                        }
                        System.out.println();
                    }

    That would be one way to model the fish moving, or swimming. It moves its muscles, and it moves the back fin, which
    the net result of that is, it actually propels itself or moves. Let's add a code-generated toString method for Fish,
    like we did for Dog, that print's both Fish's fields as well as Animal's.

                    @Override
                    public String toString() {
                        return "Fish{" +
                                "gills=" + gills +
                                ", fins=" + fins +
                                "} " + super.toString();
                    }

    And that's it. We've built the Fish class, so let's create an instance of fish, and call our doAnimalStuff method. And
    we can call that method with Fish, without changing that method at all, because Fish is another type of Animal.

                    Fish goldie = new Fish("Goldfish", 0.25, 2, 3);
                    doAnimalStuff(goldie, "fast");

    And running that:

                    _ _ _ _
                    Goldfish makes some kind of noise
                    Goldfish moves fast
                    muscles moving Backfin moving
                    Fish{gills=2, fins=3} Animal{type='Goldfish', size='small', weight=0.25}
                    _ _ _ _

    we can see the output from this additional code. Goldfish makes some kind of noise, Goldfish moves fast, and there we
    have muscles moving backfin moving. Again, we used Animal's fields and behaviors, the ones we wanted to use, and then
    added some more specific elements to the Fish class. And we passed Fish to a method, that never even had to know a Fish
    class existed. We're going to be coming back to this particular feature a lot, because it has a special name, Polymorphism.
    It simply means "many forms".

        In this course, we showed that Animal can take multiple forms, the base class Animal, or a Dog, or a Fish. And as
    you've seen, some advantages of Polymorphism are:

  - It makes code simpler. We can write code once, using the base class or super class, as we did with our doAnimalStuff
    method. We wrote that code without ever having to know about subclass types. We didn't have to write code to check the
    type of the object, and then decide what method to call, Java did all that at runtime.

  - It encourages code extensibility. It's very easy to subclass, and override or extend the method, that'll be called as
    we demonstrated. We have a whole course on polymorphism, where we cover this powerful object-oriented concept, as well
    as others.
*/

public class Main {
    public static void main(String[] args) {

        Animal animal = new Animal("Generic Animal", "Huge", 400);
        doAnimalStuff(animal, "slow");

        Dog dog = new Dog();
        doAnimalStuff(dog, "fast");

        Dog yorkie = new Dog("Yorkie", 15);
        doAnimalStuff(yorkie, "fast");
        Dog retriever = new Dog("Labrador Retriever", 65,
                "Floppy", "Swimmer");
        doAnimalStuff(retriever, "slow");

        Dog wolf = new Dog("Wolf", 40);
        doAnimalStuff(wolf, "slow");

        Fish goldie = new Fish("Goldfish", 0.25, 2, 3);
        doAnimalStuff(goldie, "fast");
    }

    public static void doAnimalStuff(Animal animal, String speed) {

        animal.makeNoise();
        animal.move(speed);
        System.out.println(animal);
        System.out.println("_ _ _ _");
    }
}
