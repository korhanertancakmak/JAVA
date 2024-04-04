package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course09_ConstructorReviews.Rev1_IntroductionToInitializer;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course09_ConstructorReviews.Rev1_IntroductionToInitializer.external.Child;

//Part-1
/*
        Ok, What's left to know about constructors? Let me encourage you not to skip this lecture. Maybe you were surprised
    to learn in a lecture in this section, that you can create private or package constructors, and this prevents classes
    from extending your class. Perhaps you didn't know, that using a final modifier on your instance fields, is going to
    force you to assign a value either in a constructor, or in another code block I haven't covered yet, an initializer.
    These topics are a bit more advanced, and now that you've mastered the basics, it's time to look at these additional
    features. In the next couple of lectures, I'll be covering:

        - Initializing final fields.
        - Initializers, both static and instance.
        - Record and enum constructors.

*/
//End-Part-1

public class Main {

    public static void main(String[] args) {

        Parent parent = new Parent("Jane Doe", "01/01/1950", 4);
        Child child = new Child();

//Part-2
/*
        I've created Main class and I'll create another class called Parent.

                                    Parent parent = new Parent();

    This is all I need to create an instance of this class, and I'll do that in my main method. I have a new object, not
    particularly useful, an instance of the Parent type. Maybe you remember that Java has created an implicit constructor
    for me, the no args constructor. This invisible constructor is kind of important. Let me show you why. Next, I'll
    create a child class, and I'll put this in a different package, external.

        I'll have this class extend the Parent class. This class compiles, and again, I can create a new instance of this
    child class in my main method. The point here is, you don't ever have to create a constructor, to instantiate a class.
    It's only when you start doing so, that it gets complicated. I'll add some fields to the Parent class.
*/
//End-Part-2

        System.out.println("Parent: " + parent);
        System.out.println("Child: " + child);

//Part-4
/*
        I'll print both the child and parent out. I can run this code,

                    Parent: name='Jane Doe', dob='01/01/1950'
                    Child: name='Jane Doe', dob='02/02/1920'

    and I see that my name and date of birth, for both the Parent and Child have been initialized to null.
*/
//End-Part-4
    }
}

//Part-7
/*
        you'll notice I have a compiler error, where I'm trying to instantiate the Parent class. What happened to my
    implicit no args constructor? Don't forget, once you create an explicit constructor, Java will no longer provide you
    with the implicit one. If I want a no args constructor now, I have to manually add it. Let me do that, getting back
    to the Parent class, I'll generate and insert it above the one with two parameters. I'll add a system.out println
    statement into this constructor. I'll print that this is the parent's no args constructor. Running this code,

                        In Parent Initializer
                        In Parent's No Args Constructor
                        In Parent Initializer
                        In Parent's No Args Constructor
                        Parent: name='Jane Doe', dob='01/01/1900'
                        Child: name='Jane Doe', dob='02/02/1900'

    what I want you to notice is, that first, both the initializer, and the no args constructor were run. They were run
    for both the Parent, and the Child classes. Importantly, the initializer block ran first. The instance initializer
    might be a good place to initialize default values, but it's probably not a great place to initialize final instance
    fields. By doing this, I can't now change those values in my constructors. Think about that for a minute. It's probably
    likely that's exactly what I want to do in my constructors, initialize instance fields, from the constructor arguments.
    I'll comment out the two assignments in the initializer block for now. And I'll un-comment the ones in my constructor.
    I still have a problem though, a compiler error on my final fields. I can't assign values to the fields, only in one
    constructor, and not the other. The truth is, I don't really want that no args constructor, so I'll comment that out
    I'll get back to the main method, and pass in data for the Parent.

                        Parent parent = new Parent();
                                      to
                        Parent parent = new Parent("Jane Doe", "01/01/1950", 4);

    But I have another problem, and that's with my Child class. Let's go to that class, and see what IntelliJ is telling
    us here.
*/
//End-Part-7

//Part-11
/*
        I have to include the number of siblings for the parent instance, and for that I'll just put 4 in there. Running
    this code,

                            In Parent Initializer
                            In Parent Constructor
                            In Parent Initializer
                            In Parent Constructor
                            Child: Initializer, birthOrder = 6, birthOrderString = Last
                            Child: Constructor
                            Parent: name='Jane Doe', dob='01/01/1950'
                            Child: name='Jane Doe', dob='02/02/1920', Last child

    I want you to notice a couple of things. The first two statements were generated when I created the parent instance.
    The next two statements were generated when I created the child instance, so the parent's initializer is called, and
    then the constructor. After this, the child's initializer's code is executed, and then the code in the child constructor.
    The child initializer was not called before the birth order initialization though. Any assignments that occur before
    the declaration of the initialization block, get assigned before that block is executed. I can confirm this, because
    I was able to use birth order in the initialization block code. Now I'll move that statement,
*/
//End-Part-11