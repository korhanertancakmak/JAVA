package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_16_Immutable_Unmodifable_Classes.Course09_ConstructorReviews.Rev1_IntroductionToInitializer;

//Part-3
/*
        I'll make them private, so name, and date of birth or d o b for short, both strings at the moment. I'll quickly
    add getters and setters for both fields. I'll also include a toString method, selecting none for fields. I'll change
    this, replacing that return statement, with one that prints the name and date of birth. Now my objects are a little
    more interesting. If I go to the main method,
*/
//End-Part-3

public class Parent {

    static {
        System.out.println("Parent static initializer: class being constructed");
    }

//Part-13
/*
        The static keyword is very important, and the only differentiator between a static initializer and an instance
    initializer. I'll just print out that this is the Parent's static initializer, and the Parent Class is being constructed
    Running the code with this addition,

                            Parent static initializer: class being constructed
                            In Parent Initializer
                            In Parent Constructor
                            In Parent Initializer
                            In Parent Constructor
                            Child: Initializer, birthOrder = 2, birthOrderString = Middle
                            Child: Constructor
                            Parent: name='Jane Doe', dob='01/01/1950'
                            Child: name='Jane Doe', dob='02/02/1920', Middle child

    you see that statement was only printed once, just before I used that class for any reason. A static initializer is
    called the first time a class is referenced or constructed. A class can have any number of static initialization blocks.
    They can be declared anywhere in the class body. They're called in the order they appear in the source code. You might
    use this to set up some environment data or log information, that's related to the class before it can be used. Remember,
    this will get executed only during the class's construction and not each instance's construction. We covered final
    fields, initialization of final fields, and the use of instance and static initializers. You've seen that the no args
    constructor is implicitly created only when an explicit constructor isn't declared. In the next lecture, I want to talk
    about record and enum constructors.
*/
//End-Part-13

    private final String name;
    private final String dob;

//Part-9
/*
        Called number of kids, and I'll make this protected, so my subclasses can directly access it. It will be an int,
    and I'll make it final. I don't want a setter, because it's final, but I do want to add this to my one constructor.
    I'll add siblings to the parameter list of this constructor. I'll assign that to my instance field. this.siblings,
    equals siblings. Now, I'll go back to the Child class.
*/
//End-Part-9
    protected final int siblings;

//Part-5
/*
        Now, I'll make my fields final.

                        private String name;    to     private final String name;
                        private String dob;            private final String dob;

    Those two changes give me four compiler errors. First, I've got errors on the field declarations themselves. Second,
    I've got errors in my setter methods. This is a reminder, that when you use final fields, you must initialize them.
    When they weren't final, they were initialized to null, but now I can't run this code, without specifically assigning
    values to these two fields. I also can't ever reassign them, so these setter methods, which do that, aren't going to
    compile. First, I'll remove the two setter methods. Now, I'm going to add a code block directly after the declaration
    of these two fields. I'll assign the name John Doe to name. I'll set date of birth to January 1st 19 hundred.

                                {
                                    name = "John Doe";
                                    dob = "01/01/1900";
                                    System.out.println("In Parent Initializer");
                                }

    Before I talk about this, first notice this eliminated my compiler errors, and I can run my main method.

                    Parent: name='Jane Doe', dob='01/01/1900'
                    Child: name='Jane Doe', dob='02/02/1900'

    My parent and child now both have those values in their fields. What is this random block of code, thrown in at the
    class level like this? This is an instance initializer.

                                        The instance initializer block

        An instance initializer is a block of code declared directly in a class body. This code gets executed when an
    instance of the class is created. Instance initializers are executed, before any code in class constructors is executed.
    You can have multiple initializer blocks. They will be executed in the order they are declared. I'm going to now add
    a constructor to this Parent class. I'll include that after the initializer block there,
*/
//End-Part-5

    {
//        name = "John Doe";
//        dob = "01/01/1900";
        System.out.println("In Parent Initializer");
    }

//    public Parent() {
//        System.out.println("In Parent's No Args Constructor");
//    }

    public Parent(String name, String dob, int siblings) {
        this.name = name;
        this.dob = dob;
        this.siblings = siblings;
        System.out.println("In Parent Constructor");
    }

//Part-6
/*
        and I'll generate it, selecting both fields. But this constructor has an error, on both assignments. Hovering over
    name, you can see it says, "name might already have been assigned to. That's because it was, in the initializer block.
    I'll comment out both of those assignments in the constructor. I'll next include a statement to the console in the
    initializer. I'll just print, In Parent Initializer. I'll put one in the constructor too. This time, I'll print, in
    parent constructor. Getting back to the main code,
*/
//End-Part-6

    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +", dob='" + dob+ '\'';
    }
}
