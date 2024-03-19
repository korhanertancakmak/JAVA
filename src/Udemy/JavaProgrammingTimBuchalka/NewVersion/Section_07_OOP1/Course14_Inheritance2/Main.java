package CourseCodes.NewSections.Section_07_OOP1.Course14_Inheritance2;

/*
Course-44

                                                  Inheritance (Part-II)

        In the last course, we introduced you to the concept of Inheritance, and showed you how to implement it in Java,
    using the "extends" keyword. We talked about using the statement "super()" to call the constructor on the super class.
    In the last course, we created Dog as a subclasses of Animal, and demonstrated that Dog inherited all of Animal's fields,
    and that it can be passed to methods that are declared with the type Animal. In this course, we want to make Dog different
    from Animal, by declaring the fields and methods that are specific to it.

        Let's look at the class diagram for just Dog, at the moment.

                    Dog =>
                            earShape  : String
                            tailShape : String
                            ------------------
                            bark()
                            run()
                            walk()
                            wagTail()

    We want to add the attributes, earShape and tailShape, as shown here, in our class diagram. Then we'll implement the
    methods, bark, run, walk, and wagTail. Going back to the Dog.java file, we'll add earShape and tailShape.

                        public class Dog extends Animal {
                            private String earShape;
                            private String tailShape;

                            public Dog() {
                                super("Mutt", "Big", 50);
                            }
                        }

    Those are our a Dog specific fields. Let's create a more specific Dog constructor than the one we have. This time, we
    have an additional choice, and that's which parent constructor we should use. Let's pick the one with parameters. And
    then, we pick both of our dog attributes, and hit OK.

                        public Dog(String type, String size, double weight, String earShape, String tailShape) {
                            super(type, size, weight);
                            this.earShape = earShape;
                            this.tailShape = tailShape;
                        }

    Let's actually change this constructor, so it's easier to create a Dog object. We'll remove size as a parameter, and
    instead write code to derive it, passing that to the super call.

                        public Dog(String type, double weight, String earShape, String tailShape) {
                            super(type, weight < 15 ? "small" : (weight < 35 ? "medium" : "large"), weight);
                            this.earShape = earShape;
                            this.tailShape = tailShape;
                        }

    This constructor has a combination of the Dog and the Animal fields, in its argument list. We can pass it the type of
    the dog, the dog's weight, and the earShape and tailShape. We're calling the super constructor to set some of our fields,
    the Animal specific fields. And for the size, we're deriving the size of the dog from the weight, small, medium, or
    large. To do this, we use a nested ternary operator, which is passed directly to the super constructor. We couldn't
    do this operation before the call to super, because "super()" must be the first statement. But we can do it directly
    like this, as an expression, in the argument list. This is one way to perform calculations, in your constructor, and
    pass the result to the super call. After the call to the super constructor, we set some of the Dog-specific attributes,
    the earShape and tailShape, that were passed to us.

        Before we do anything else, let's generate toString method for the Dog class. But this time, not the template as
    "String concat(+)", which gives this:

                        @Override
                        public String toString() {
                            return "Dog{" +
                                    "earShape='" + earShape + '\'' +
                                    ", tailShape='" + tailShape + '\'' +
                                    '}';
                        }

    but the one says the template as "String concat(+) and super.toString()", which also includes super.toString:

                        @Override
                        public String toString() {
                            return "Dog{" +
                                    "earShape='" + earShape + '\'' +
                                    ", tailShape='" + tailShape + '\'' +
                                    "} " + super.toString();
                        }

    You can see 2 new fields there, plus a call to super.toString(). Now, this super is different than the super parentheses
    call. It's a lot like using the "this." notation, to access a field on the current instance. This code lets us call
    a super class's method. We'll talk about this a lot more in upcoming courses. I want to add 1 more constructor, after
    the first one, before we test this:

                        public Dog(String type, double  weight) {
                            this(type, weight, "Perky", "Curled");
                        }

    This constructor makes it even simpler, to create a Dog object, for the majority of dogs(if their ears are Perky, and
    their tails are Curled). It calls the other Dog constructor that has 4 parameters, which in turn calls the Animal constructor.
    We're using constructor chaining to make this work. Let's go back to Description.txt, and create some more dogs, calling
    the doAnimalStuff for each one:

                        Dog yorkie = new Dog("Yorkie", 15);
                        doAnimalStuff(yorkie, "fast");
                        Dog retriever = new Dog("Labrador Retriever", 65,
                                "Floppy", "Swimmer");
                        doAnimalStuff(retriever, "slow");

    Dogs have over 20 ear types, and more than 9 tail types, one of which is a Swimmer tail type, which we use here. And
    now, check out what gets printed for both of these Dog objects, when we run this code:

                        Yorkie makes some kind of noise
                        Yorkie moves fast
                        Dog{earShape='Perky', tailShape='Curled'} Animal{type='Yorkie', size='medium', weight=15.0}
                        _ _ _ _
                        Labrador Retriever makes some kind of noise
                        Labrador Retriever moves slow
                        Dog{earShape='Floppy', tailShape='Swimmer'} Animal{type='Labrador Retriever', size='large', weight=65.0}
                        _ _ _ _

    We get all the fields that are specific for Dog, and the fields that are more general to the Animal. That's because
    our toString method printed out the Dog fields then made a call to super.toString(), which was Animal's toString method.
    We were also able to calculate the size of the dog, based on its weight. And we created constructors for Dog, that
    targeted more Dog-like features, and passed default values for Animal's more general fields.

        Let's talk a little bit about the behavior of Animal and Dog next. Up to now, we called methods on Animal, and were
    able to access the functionality that's part and parcel of the animal class, and we're able to use methods on, and
    through Dog, that were defined on Animal. And even more importantly, we're doing this from a method, that really doesn't
    even know anything about the Dog class. Dog can use methods on Animal, and print out its own object's values, in this
    case, both the move and makeNoise methods printed the type field. You can see why inheritance promotes code re-use.

                                                    Code Re-use

        All subclasses can execute methods, even though the code is declared on the parent class. The code doesn't have
    to be duplicated in each subclass. But it does even get better than that. We can use code, out of the box, from the
    parent, as we did in this example. Or, we can change that code for the subclass. We did this with the toString() method.

        The toString() method that was called in the doAnimalStuff method, of the Main class, didn't actually call the Animal
    toString method. It called the Dog toString() method, when animal is an instance of a Dog. I want you to really understand
    that, because it's so important. And really, it's one of the best parts about this inheritance feature.

        We told doAnimalStuff method that we were dealing with an Animal class(the first parameter of it), and we called
    the toString method(by the command of "System.out.println(animal)"), which is declared as a method on Animal.

                        @Override
                        public String toString() {
                            return "Animal{" +
                                    "type='" + type + '\'' +
                                    ", size='" + size + '\'' +
                                    ", weight=" + weight +
                                    '}';
                        }

    At run time, Java figures out the Animal object is even more specific than Animal, it's really a Dog, and it actually
    calls the toString() method on Dog (if one exists on Dog). If the toString() method doesn't exist on Dog, that's no
    problem, because then it just uses the toString method on Animal. This is good stuff, so let's explore this a little
    bit more.

        Let's create a makeNoise method on Dog next, and this method has the same signature as makeNoise on Animal:

                        public void makeNoise() {
                        }

    And now what happens if we run our code?

                        Generic Animal makes some kind of noise
                        Generic Animal moves slow
                        Animal{type='Generic Animal', size='Huge', weight=400.0}
                        _ _ _ _
                        Mutt moves fast
                        Dog{earShape='null', tailShape='null'} Animal{type='Mutt', size='Big', weight=50.0}
                        _ _ _ _
                        Yorkie moves fast
                        Dog{earShape='Perky', tailShape='Curled'} Animal{type='Yorkie', size='medium', weight=15.0}
                        _ _ _ _
                        Labrador Retriever moves slow
                        Dog{earShape='Floppy', tailShape='Swimmer'} Animal{type='Labrador Retriever', size='large', weight=65.0}
                        _ _ _ _

    The last time we ran this code, we had statements that said "the Yorkie and Labrador made noise". But now we don't see
    anything like that. This method, makeNoise on Dog, which doesn't do anything, was called, and not the makeNoise method,
    on Animal. What have we really done here? Well, we've overridden Animal's makeNoise method.

                                                Overriding a method

        Overriding a method is when you create a method on a subclass, which has the same signature as a method on a super
    class. Remember that a method signature consists of the method name, and the number and types of parameters. You override
    a parent class method, when you want the child class to show different behavior for that method.

        So notice, in IntelliJ, that it has a special icon next to this makeNoise method, the little o with an arrow. This
    is IntelliJ telling us this method is overriding a parent class's method. Another option, is to use IntelliJ's code
    generation tool to override methods.

        Let's use that way now, to override the move method on Animal. Select 'Code' from the menu, but let's select 'Override
    Methods' this time. And IntelliJ is showing us all the methods we could override, starting with Animal's, but it's also
    showing us methods on java.lang.Object. I'll be talking about java.lang.Object, in just a bit. Let's pick the move
    method on Animal, and hit the ok button:

                        @Override
                        public void move(String speed) {
                            super.move(speed);
                        }

    Now, look at the difference, between the code we created manually, the makeNoise method, and this one, the move method
    that IntelliJ created for us. IntelliJ's generation tool adds this @Override symbol, and that's to remind us, that
    we're overriding a method that's in the superclass. In this case, it's in the Animal class. And notice too, that the
    automatically generated code, simply makes a call to the parent class's method, move using the keyword super and dot
    move. What that means is, we're calling the move method on the parent class, the Animal class. This code kind of does
    the same thing as not having that overridden method at all. It simply executes the Animal class's move method, which
    would have happened, if we didn't create this method at all. Why would we do this? Well, most likely, we'll want to
    change or extend the code here. We changed the makeNoise method, by having a method with no code at all. This changed
    the behavior of makeNoise for all Dog objects. It made all our dogs silent, for the moment.

        Next, let's extend the functionality for the move method. This means we'll do what the animal class does, but we'll
    do additional stuff as well. We'll leave the super.move statement there, but we'll add more code. Here, we'll just
    print out another statement, that Dogs walk and run, and wag their tail:

                        @Override
                        public void move(String speed) {
                            super.move(speed);
                            System.out.println("Dogs walk, run and wag their tail ");
                        }

    And running the code now:

                        Generic Animal makes some kind of noise
                        Generic Animal moves slow
                        Animal{type='Generic Animal', size='Huge', weight=400.0}
                        _ _ _ _
                        Mutt moves fast
                        Dogs walk, run and wag their tail
                        Dog{earShape='null', tailShape='null'} Animal{type='Mutt', size='Big', weight=50.0}
                        _ _ _ _
                        Yorkie moves fast
                        Dogs walk, run and wag their tail
                        Dog{earShape='Perky', tailShape='Curled'} Animal{type='Yorkie', size='medium', weight=15.0}
                        _ _ _ _
                        Labrador Retriever moves slow
                        Dogs walk, run and wag their tail
                        Dog{earShape='Floppy', tailShape='Swimmer'} Animal{type='Labrador Retriever', size='large', weight=65.0}
                        _ _ _ _

     we can see from the output, that when we called the move method, we did what Animal had us do with that statement,
     "Yorkie moves fast", but we added another line of text to the output, "Dogs walk, run and wag their tail". We extended
     the functional behavior of Animal for Dogs. We used what was there(with the call to super.move), but then added our
     own code to it. I think this is a good place to end this course.

        In this course, we created methods on a super class, then called them from a subclass, directly, showing you that
     methods can be inherited, as well as fields. We also showed you that a subclass can override a superclass's methods.
     The overridden method can do one of 3 things:

  1. It can implement completely different behavior, overriding the behavior of the parent.
  2. It can simply call the parent class's method, which is somewhat redundant to do. This is the default behavior of an
     inherited method.
  3. Or the method can call the parent class's method, and include other code to run, so it can extend the functionality
     for the Dog, for that behavior.

        In the next course, we'll implement methods unique to Dog. In other words, the behavior we think only makes sense
     for Dogs.
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
    }

    public static void doAnimalStuff(Animal animal, String speed) {

        animal.makeNoise();
        animal.move(speed);
        System.out.println(animal);
        System.out.println("_ _ _ _");
    }
}
