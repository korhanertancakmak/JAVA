package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_16_Immutable_Unmodifable_Classes.Course02_AppliedFinalModifiers;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_16_Immutable_Unmodifable_Classes.Course02_AppliedFinalModifiers.consumer.specific.ChildClass;
import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_16_Immutable_Unmodifable_Classes.Course02_AppliedFinalModifiers.generic.BaseClass;

public class Main {

    public static void main(String[] args) {

        BaseClass parent = new BaseClass();
        ChildClass child = new ChildClass();
        BaseClass childReferredToAsBase = new ChildClass();

        parent.recommendedMethod();
        System.out.println("--------------------");
        childReferredToAsBase.recommendedMethod();
        System.out.println("--------------------");
        child.recommendedMethod();

        System.out.println("--------------------");
        parent.recommendedStatic();
        System.out.println("--------------------");
        childReferredToAsBase.recommendedStatic();
        System.out.println("--------------------");
        child.recommendedStatic();

        System.out.println("--------------------");
        BaseClass.recommendedStatic();
        ChildClass.recommendedStatic();

//Part-3
/*
        Even though IntelliJ really wants me to change this code, I'll leave it the way it is. The message that I get is,
    static member is accessed via instance reference, and this behavior, as I just mentioned, isn't recommended. How should
    I call this code? Well, I can simply use the class types. I'll output another dashed line first. I'll simply call
    the recommendedStatic method on the Baseclass. I'll also do the same with that method on ChildClass. And running that,

                    --------------------
                    [BaseClass.recommendedStatic] BEST Way to Do it
                    [BaseClass.optionalStatic]: Optional
                    [BaseClass.mandatoryStatic]: MANDATORY
                    [Child.recommendedStatic] BEST Way to Do it
                    [BaseClass.optionalStatic]: Optional

    you can see each method was called, on the qualifying type, with a lot less confusion this time. You can also make a
    static method final. Let's do this on the Base class, to the recommendedStatic method. Remember this is the method
    that the ChildClass is hiding. I'll include the final key word on the declaration of that method on BaseClass. Now
    I'll go back to the Child class, and just like when I made an instance method final, I get an error on this static
    method. Making the static method final on the BaseClass, means subclasses can't hide that method. If I look at that
    error, on the ChildClass, it says I can't override this method. Why doesn't it say hide? Well, the IDE doesn't really
    know if I'm trying to override, or hide this method. If I remove the static keyword, I get the exact same message,
    but for a completely different reason. This is because I can't create a method on a subclass, an instance method,
    when the parent's method, is static. Let me revert that change, so that this method is static again, and I'll go back
    over to the BaseClass. IntelliJ is highlighting the final keyword, and gives me a pretty uninformative message, static
    method declared final. This is not a compiler error, just a warning, and in fact, there could be valid reasons for
    making a static method final sometimes, preventing subclasses from creating their own versions. If you do see this
    warning though, use it as a reminder to think about other ways to design your classes. For example, maybe making the
    whole class final, is a better option, and I'll be covering final classes in just a bit. For now, I'll remove the
    final key word, so that my ChildClass can hide this method. Next, I want to revisit final variables. I discussed
    these a little bit, when I talked about using final, or effectively final variables, in lambda expressions.
*/
//End-Part-3

        String xArgument = "This is all I've got to say about Section ";
        //doXYZ(xArgument, 16);

//Part-5
/*
        I'll set up a local variable, xArgument, setting that to the text, This is all I've got to say about Section.
    I'll pass that variable, and the integer 16, to the do xyz method. Ok, so running this code,

                    c = This is all I've got to say about Section 16

    there's not really any surprises there. It prints This is all I've got to say about Section 16. This code would work
    the same, whether I declared this variable final or not, and that's because, the local variable c, is effectively
    final. That just means I'm not assigning a different value to c, after the initial assignment. If I add code to assign
    c to x, for example, as the last statement in this method. You can see this is a problem, and I get the error, cannot
    assign a value to final variable c. I'll revert that last change, removing that statement. You might be wondering
    why you'd want to use the final keyword, when the compiler can figure out that it's effectively final? Well, maybe
    you plan to use the variable in lambda expressions. By specifying final, you're informing future developers, that
    this variable needs to remain unchanged. In addition to local variables, I can also make method parameters final as
    well. Let me do that next.
*/
//End-Part-5

        StringBuilder zArgument = new StringBuilder("Only saying this: Section ");
        doXYZ(xArgument, 16, zArgument);
        System.out.println("After Method, xArgument: " + xArgument);
        System.out.println("After Method, zArgument: " + zArgument);

//Part-7
/*
        I'll print out the value of the xArgument variable, after the call to this method. Ok, so if I run this,

                    After Method, xArgument: This is all I've got to say about Section

    you can see, that the value in xArgument didn't change. It's value is the same before, and after the method call. In
    the method, I reassigned the value of the method argument x, but it had no effect outside of this method. This reference,
    x will go out of scope when the method ends. Let's see what happens if I do something similar, with a mutable object
    I've shown you this before, but let me set this up.

        First, I'll add a parameter to the do xyz method, a StringBuilder I'll call z. I'll add a statement at the end
    of this method, that appends y, the integer, to the string builder argument. In the main method, I'll set up a StringBuilder,
    calling it zArgument, initializing that to the text, only saying this, Section. I'll pass the zArgument to my method.
    I'll then print the value after the method. If I run this,

                    After Method, zArgument: Only saying this: Section 16

    the output says that after method, z argument says, Only saying this: Section 16. Before the method it did not include
    16. This means, the method had a side effect on my string builder variable. After the method finishes execution, my
    zArgument is different. What would happen if I made that third parameter final? Let me do that, I'll put final before
    the StringBuilder type. I don't have any compiler errors. Maybe you expected to see one, on that last statement in
    this method. If I run this code, we can see, nothing's changed, I get the exact same result. There are still side
    effects, and my zArgument value was changed. What good is final then, when it's used with a method parameter?

        First, I'll comment out that last line. Instead of using the append method on the z argument, I'll reassign it
    to a new StringBuilder instance, with different text. Now I've got a compiler error. Like before, I can't assign a
    new variable, meaning I can't reassign it, to the method parameter z. I'll comment out that last line, and I'll uncomment
    the line above that, leaving this method with side effects. Using final with a method parameter, isn't going to change
    whether a method has side effects or not. It will simply produce a compiler error if you try to reassign a method
    parameter's value. This is an important distinction. It's important to understand, when you use final, it doesn't
    mean the variable is immutable at that point. It means you can't assign or reassign a new instance, or variable or
    expression to it, after the initialization. If you use final for a local variable in a code block, you can only
    initialize it fully, or assign it a value, just once. Any other additional assignments will result in a compiler
    error. If you use final for method parameters, this means you cannot assign any values to the method parameters in
    the code. Remember that method arguments get initialized implicitly when the method is invoked. I'll cover final
    fields and final classes, when I cover topics more specific to designing immutable classes. In the next lecture, I'll
    be talking about why change isn't always a good thing.
*/
//End-Part-7
    }

//Part-4
/*
        I'll start with declaring a final local variable, in a block of code. To demonstrate this, I'll create a new
    method on the Main class, private static void, do XYZ. It'll have two parameters, a string I'll call x, and an int
    I'll call y. I'll use the final keyword, before my local variable declaration, so final String c, and I'll assign
    x + y to that. And I'll print out c. I'll call this from the main method.
*/
//End-Part-4

    private static void doXYZ(String x, int y, final StringBuilder z) {

        final String c = x + y;
        System.out.println("c = " + c);
        x = c;
        z.append(y);
//        z = new StringBuilder("This is a new reference");
    }


//Part-6
/*
        Now, I'm going to assign a new value, to the variable x. I'll assign c to x here. Again, I can't do this, if the
    method parameter is declared as final. I'll remove the final keyword from the method parameter. The truth is, declaring
    immutable type parameters final, like I just did here, isn't going to buy much. Let me show you why.
*/
//End-Part-6
}