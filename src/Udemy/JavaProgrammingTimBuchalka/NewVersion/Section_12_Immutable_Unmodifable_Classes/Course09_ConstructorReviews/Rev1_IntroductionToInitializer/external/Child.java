package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course09_ConstructorReviews.Rev1_IntroductionToInitializer.external;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course09_ConstructorReviews.Rev1_IntroductionToInitializer.Parent;
import java.util.Random;

//Part-8
/*
        There's no default constructor on Parent. When I added a custom constructor, the implicit one wasn't generated,
    and I commented out the one I generated. Without it, we can't have a subclass that doesn't declare a constructor.
    Let me add an explicit constructor that looks like the one Java would generate for me, so this is a little easier to
    understand.

                                    public Child() {
                                        super();
                                    }

    This constructor has the same problem, but it's more obvious. Calling super with no arguments is the problem. Hovering
    over that, it says, "Expected two arguments but found zero".

                                public Child() {                        public Child() {
                                     super();            to                  super("Jane Doe", "02/02/1920");
                                }                                       }

    I'll pass some default values here, passing Jane Doe, and this time February 2, 1920. Now my code compiles again and
    runs.

                                    In Parent Initializer
                                    In Parent Constructor
                                    In Parent Initializer
                                    In Parent Constructor
                                    Parent: name='Jane Doe', dob='01/01/1950'
                                    Child: name='Jane Doe', dob='02/02/1920'

    I want to add another field to Parent,
*/
//End-Part-8

public class Child extends Parent {

//Part-10
/*
        I want two private and final fields on this class, an integer, birth order, and a birth order string. Again, this
    class won't compile, unless these final fields are initialized. Another way to initialize a final instance field, is
    to call a final method. I'll create a private final method called getBirthOrder that returns an int. If the number
    of siblings is 0, I'll just return 1. I'll return a new random number, between 1 and the siblings. I'll add two to
    the sibling number, so that I get a number between 1 and the number of actual kids, the parents might have had. Notice
    here, I'm accessing number of kids, a field this class inherits from Parent. I can access this field during construction
    or initialization, because the parent's constructor is called first, before this code is executed, so this field
    should be assigned a value by this point. I can now assign my birth Order value to what I get back from this method.

        Now that I have the birth order, I want to set up the Birth Order String, such as First Child, only child, middle
    child or Last Child. I'll do this with an instance initializer block. I'll check if the number of siblings is equal
    to 0. If it is, I'll set birthOrderString to Only. Now, notice, that although I have code setting birthOrderString
    in this initializer, I still have a compiler error, on the declaration of that field. And the message is the same,
    birthOrderString might not have been initialized. Why do I get this, when I have code to initialize it, in this
    initializer code block?

        Well, consider what happens if the number of siblings is not 0. In that case, birthOrderString has no value, and
    since it's final, this is an invalid situation, and the compiler recognizes that. I need to fully initialize birthOrderString,
    by providing a value in all possible conditions. I could complete this with an else, but I want multiple conditions,
    so I'll continue. I'll add a condition to see if the birth order is 1. And if so, that means this will be the first
    born, so I'll set birth order string to first. Again, I still have the error. Because I used else if, there are still
    other possible unmet conditions. I'll continue with the rest of the conditions. If the birth order is equal to the
    number of siblings plus 1, then this means the birthOrderString should be last. For any other condition, this must be
    a middle child. Now, when I added that last else condition, the compiler error was resolved. I want to add a println
    statement in this initializer code. I'll print that this is the initializer code in the child, and print the birth
    order, and the birth order string. Now, my constructor needs to include a number for siblings, so I'll change it. In
    the no args constructor, I'll pass 5 as the number of siblings for Jane Doe. I want to include a println statement
    as well. I'll print that this is the Child Constructor.

        And I'll add a toString method, using Alt insert again, and select none. And I'll just replace the generated code,
    returning super.toString, and concatenating the birthOrder String to that. Getting back to my main method,
*/
//End-Part-10

    private final int birthOrder = getBirthOrder();
    private final String birthOrderString;

    {
        if (siblings == 0) {
            birthOrderString = "Only";
        } else if (birthOrder == 1) {
            birthOrderString = "First";
        } else if (birthOrder == (siblings + 1)) {
            birthOrderString = "Last";
        } else {
            birthOrderString = "Middle";
        }
        System.out.println("Child: Initializer, birthOrder = " + birthOrder +
                ", birthOrderString = " + birthOrderString);
    }

    public Child() {
        super("Jane Doe", "02/02/1920",5);
        System.out.println("Child: Constructor");
    }

//Part-12
/*
        I'll cut it, and paste it right below Child no args constructor. Notice, the compiler errors I'm getting, in the
    initializer block, on this field in the if statements. If I hover over one of those, it says Illegal forward reference.
    I can't reference a field that hasn't been initialized, in my initializer block. I'll revert that change, putting the
    birth order statement back top. In addition to an instance initializer block, there's also a static initializer.
    This block of code lets us initialize static variables, and is called as part of the Class construction. You'll
    remember there are no static constructors, so this is a way to include code you want to occur, while the class itself
    is being initialized. I'll add a static initializer to the Parent class.
*/
//End-Part-12

    private final int getBirthOrder() {

        if (siblings == 0) return 1;
        return new Random().nextInt(1, siblings + 2);
    }

    @Override
    public String toString() {
        return super.toString() + ", " + birthOrderString + " child";
    }
}
