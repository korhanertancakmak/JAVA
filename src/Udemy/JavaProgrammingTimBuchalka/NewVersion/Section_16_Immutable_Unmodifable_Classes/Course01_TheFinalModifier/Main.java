package CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course01_TheFinalModifier;
import CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course01_TheFinalModifier.consumer.specific.ChildClass;
import CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course01_TheFinalModifier.generic.BaseClass;

//Part-1
/*
        In this section, I want to review the terms mutable and immutable, and talk about issues related to these two terms.
    Objects have state, which is the data stored in instance fields. State can change after an object is created, either
    intentionally or unintentionally. When state remains constant throughout the lifetime of the object, and code is prevented
    from changing the state, this object is called an immutable object. An immutable object is an object whose internal
    state remains constant. A mutable object is an object whose internal state does not remain constant. Which is better?
    Well, like anything else, that depends. Working with immutable objects has some advantages. An immutable object isn't
    subject to unwanted, unplanned and unintended modifications, known as side-effects. This means we don't have to write
    defensive or protective code for these objects, to protect them from possible mutations. An immutable class is inherently
    thread-safe, because no threads at all can change it, once it's been constructed. This allows us to use more efficient
    collections and operations, which don't have to manage synchronization of access to this object. We'll talk more about
    this when we get to the threading and synchronization section of the course. These are two of the most important
    advantages. Working with immutable objects has some disadvantages. An immutable object can't be modified after it's
    been created. This means that when a new value is needed, you're probably going to need to make a copy of the object
    with the new value. You'll remember the discussion comparing String vs. StringBuilder. If you're constantly needing
    to alter text values, it's more efficient to use a mutable object like a StringBuilder instance, then generating tons
    of new String objects. I'll be talking about immutable class design coming up. It's important to understand that POJOs
    and JavaBeans, which many code generation tools create, are not by design, immutable, and therefore this architecture
    may not be the preferred design for your class. This all sounds rather simple, yet there are many topics related to
    it. One of the most useful tools in our arsenal to build immutable classes, is the final access modifier. I'll be
    revisiting this modifier in much more detail in the next lecture.
*/
//End-Part-1

//Part-2
/*
        When we use the final modifier, we prevent any further modifications to that component. A final method means it
    can't be overridden by a subclass. A final field means an object's field can't be reassigned or given a different value,
    after its initialization. A final static field is a class field that can't be reassigned, or given a different value,
    after the class's initialization process. A field declared on an Interface is always public, static and final. A final
    class can't be overridden, meaning no class can use it, in the extends clause. A final variable, in a block of code,
    means that once it's assigned a value, any remaining code in the block can't change it. and A final method parameter
    means, we can't assign a different value to that parameter in the method code block. In this lecture, I'll review all
    of these uses of the final modifier in more detail.

                                          Using the Final Modifier on Methods

        You can use the final modifier on methods. Using final with methods only makes sense in the context of wanting
    to restrict what your subclasses can override or hide. Using final on an instance method means subclasses can't
    override it. Using final on a class (static) method means subclasses can't hide it. Let's switch over to some code,
    and examine these two different scenarios. I've created a Main class and main method. Before I include anything there,
    I'm going to create a class called BaseClass, in a generic package.
*/
//End-Part-2

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

//Part-4
/*
        I'll create an instance of a BaseClass, and assign it to a BaseClass reference, named parent. Then I'll create
    an instance of ChildClass, assigning it to a Child Class reference, and I'll call that child. I also want an instance
    of a Child class, but I'm going to assign it to a BaseClass reference type, so I'll call that child referred to as
    base, to keep it straight. Next, I'll call my recommendedMethod on each of those instances. So first, I'll call
    recommendedMethod on the parent instance. I'll put a separator line between output. I'll call the same method on my
    child instance, that's assigned to a base class reference. And I'll repeat that for the child instance. If I run this
    code,

                    [BaseClass.recommendedMethod]: Best Way to Do it
                    [BaseClass.optionalMethod]: Customize Optional Method
                    [BaseClass.mandatoryMethod]: NON-NEGOTIABLE!
                    --------------------
                    [BaseClass.recommendedMethod]: Best Way to Do it
                    [BaseClass.optionalMethod]: Customize Optional Method
                    [BaseClass.mandatoryMethod]: NON-NEGOTIABLE!
                    --------------------
                    [BaseClass.recommendedMethod]: Best Way to Do it
                    [BaseClass.optionalMethod]: Customize Optional Method
                    [BaseClass.mandatoryMethod]: NON-NEGOTIABLE!

    the results are all the same, as you'd expect, for all three instances. Now, let's say the designer of the child sub
    class reads our specification, and learns they should override the optionalMethod, if they want some custom functionality.
    I'll go to the Child class,
*/
//End-Part-4

        System.out.println("--------------------");
        parent.recommendedStatic();
        System.out.println("--------------------");
        childReferredToAsBase.recommendedStatic();
        System.out.println("--------------------");
        child.recommendedStatic();

//Part-12
/*
        I'll insert a separator line. I'll copy the statements above, pasting that at the end of the main method. I'll
    change each of the method calls to call the static methods, so I'll replace Method with Static on there. Ok, so maybe
    you're wondering why I'm calling static methods, using instance references, meaning using parent and child, rather
    than BaseClass and ChildClass. Although I can do this, the code compiles, IntelliJ is highlighting each of these calls.
    I've discussed this previously, and IntelliJ is good at reminding us, that calling a static method, using an instance
    reference, isn't a great idea. I'm going to leave the code this way, and run it anyway.

                [BaseClass.recommendedStatic] BEST Way to Do it
                [BaseClass.optionalStatic]: Optional
                [BaseClass.mandatoryStatic]: MANDATORY
                --------------------
                [BaseClass.recommendedStatic] BEST Way to Do it
                [BaseClass.optionalStatic]: Optional
                [BaseClass.mandatoryStatic]: MANDATORY
                --------------------
                [BaseClass.recommendedStatic] BEST Way to Do it
                [BaseClass.optionalStatic]: Optional
                [BaseClass.mandatoryStatic]: MANDATORY

    Because these methods are only on BaseClass right now, all three segments, the last 3 segments in the output, are the
    same. This feels like a good place to pause in this discussion. I'll be coming back to this example in the next
    lecture, to explain what is really going on here.
*/
//End-Part-12
    }
}
