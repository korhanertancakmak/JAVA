package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_11_Abstraction_Interface.Course01_Abstract;
//Part-2
/*
        But I'm going to add the abstract modifier in this case, after the public access modifier. You don't have to declare
    the modifiers in this order, but it's common practice to have the access modifier be declared first. An abstract class
    can have inherited fields just like a concrete field, so I'll add 3: type, size and weight.
*/
//End-Part-2

public abstract class Animal {

    protected String type;
    private  String size;
    private double weight;

//Part-3
/*
        I've made type protected, but size and weight private. This means subclasses can access type directly, without a
    getter method. And I'll set up a constructor, by having IntelliJ generate it, using all 3 fields.

*/
//End-Part-3

    public Animal(String type, String size, double weight) {
        this.type = type;
        this.size = size;
        this.weight = weight;
    }

//Part-4
/*
        And now, I'll set up the 2 method on the diagram,

                            Animal =>                         Dog =>
                                 type: String                   earShape   : String
                                 size: String                   tailShape  : String
                                 weight: double       <<<<<<    -------------------
                                 ---------------------          bark()
                                 move(String speed)             run()
                                 makeNoise()                    walk()
                                                                wagTail()

    but this time, I'm going to make those methods abstract.
*/
//End-Part-4

    public abstract void move(String speed);
    public abstract void makeNoise();

//Part-5
/*
        Here, I'm using abstract in the declaration of the methods, again after the public access modifier. After the method
    signature, I end with a semicolon. We don't even specify an empty method body, meaning a set of curly braces {}. Actually,
    let's see what happens if I do that. I'll add a set of curly braces to the first method, inserting them, before the
    ending semicolon.

                    public abstract void move(String speed) {};

    And you can see we've got an error there, and IntelliJ tells us that "Abstract methods cannot have a body". So including
    the curly braces is really including a method body, even though there's no code in it. It's an empty code block, or
    empty method body, but it's still a method body, and abstract methods can't be declared with a method body. So let's
    revert that change.

        When we declare abstract method, this means we don't provide any default behavior for our subclasses. And we're
    actually forcing subclasses to declare and implement these methods. Another thing you can't do is have an abstract
    method that's private. Let's try that next.

                    private abstract void move(String speed);

    And that gives us a different error, "Illegal combination of modifiers: "abstract" and "private"". Can you understand
    why this combination would be illegal? When we use abstract, we're saying subclasses have to declare this method. But
    if it's private, the subclass wouldn't even see it. I'll revert that change, so the Animal class compiles. Let's create
    the Dog class now, in the same package.
*/
//End-Part-5

        //public String getExplicitType()
        public final String getExplicitType() {
            return getClass().getSimpleName() + " (" + type + ")";
        }

//Part-18
/*
        Now we have a concrete method, on an abstract class. And it has a body. We get the class's simple name, and print
    that with the type that was passed as the field. And like any method on a parent class, that's a method we can use in
    the subclasses. So going to Dog, and the move method.
*/
//End-Part-18

//Part-22
/*
        And convention has that again, being declared after the access modifier, public in this case. If I ran this code,
    the behavior would be exactly the same. But if we went to our Dog class, and we decided we want to create our own version
    of getExplicitType, we actually can't do it. Then going to Dog class,
*/
//End-Part-22
}

abstract class Mammal extends Animal {
//Part-24
/*
        Here, I'm declaring an abstract class, Mammal, that extends the abstract class, Animal. Now, we're getting an error
    on this class, because of the constructor, so I'll include the constructor using code generation.
*/
//End-Part-24

    public Mammal(String type, String size, double weight) {
        super(type, size, weight);
    }

//Part-25
/*
        Now, this code compiles. But wait a minute. We weren't forced to create implementations for the move and makeNoise
    methods, like we were for the Dog and Fish class. Why not?

                        An Abstract class doesn't have to implement abstract methods

        An abstract class that extends another abstract class has some flexibility:
  - It can implement all the abstract methods of its parent.
  - It can implement some of them.
  - Or it can implement none of them.
  - It can also include additional abstract methods, which will force subclasses to implement both Animal's abstract methods
    and Mammal's abstract methods.

        Ok, so first, let's create a new class in the same package, and I'll call it Horse.
*/
//End-Part-25

    @Override
    public void move(String speed) {

        System.out.print(getExplicitType() + " ");
        System.out.println(speed.equals("slow") ? "walks" : "runs");
    }


//Part-27
/*
        And now, I'll just put something in there. So first, we'll print out the explicit type, and then for mammals, if
    the speed is slow, we'll print walks, otherwise runs. And now I'm going to add an abstract method, specific for mammals,
    like shedHair.
*/
//End-Part-27

    public abstract void shedHair();

//Part-28
/*
        And again, we just enter the method signature, remembering to include the abstract modifier there. And we follow
    that with a semicolon. This is the structure of an abstract method. And going back to Horse, we still have that error,
    but it's specifying that now, we need to implement the method shedHair on Mammal. And I'll implement all the methods
    left now.
*/
//End-Part-28
}