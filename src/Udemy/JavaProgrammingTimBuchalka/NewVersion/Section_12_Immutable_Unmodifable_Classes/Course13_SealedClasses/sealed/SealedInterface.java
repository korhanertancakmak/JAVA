package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course13_SealedClasses.sealed;

import java.util.function.Predicate;

public sealed interface SealedInterface permits BetterInterface, StringChecker {

    boolean testData(Predicate<String> p, String... strings);
}

//Part-6
/*
        I'll add a single abstract method, which will take a predicate functional interface for a String, and a variable
    argument with Strings. This method could be used to test some kind of relationship for a set of Strings. I'll create
    a class that implements this, and I'll call that StringChecker, again in the same package. I'll add the implements
    SealedInterface to the class declaration. I'll implement the method, using IntelliJ's tools. That's all I need for
    my purposes. I'll also extend my Sealed Interface with a Better interface. That will extend SealedInterface.

        Now, I'll seal my SealedInterface, adding the sealed keyword. I'll hover over that and select the link in that
    popup to add missing subclasses. Now notice, it added a permits clause, and here it has the interface that extends
    SealedInterface. That's BetterInterface, but it also has my StringChecker class, that implements the SealedInterface.
    Similarly to a sealed class, I have to go back to both those types, and decide if they're final, sealed or non-sealed.
    For an interface, the final modifier isn't valid. An interface, by its nature, is a contract of abstracted methods.
    For the BetterInterface, I can only choose between sealed or non-sealed. I'll go with non-sealed here, for simplicity's
    sake. For the class that implements SealedInterface, I have all three options. I'll go to my StringChecker class,
    and make that final. Ok, so that's one of Java's newer features. I've demonstrated, in this section of the course,
    that extensibility, might inadvertently expose your code, to problematic subclasses. This access could provide access
    to the internal state of your objects, leading to intentional or unintentional mutability. Using sealed types gives
    you another level of control, by allowing you to choose which types can leverage your code. This lecture ends our
    section on immutability, but I have one last challenge for you. I'll be again visiting the Pirate Game for this.
*/
//End-Part-6