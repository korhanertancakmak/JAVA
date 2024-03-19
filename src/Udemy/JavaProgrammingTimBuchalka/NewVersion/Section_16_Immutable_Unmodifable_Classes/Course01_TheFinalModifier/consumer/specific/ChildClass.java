package CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course01_TheFinalModifier.consumer.specific;

import CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course01_TheFinalModifier.generic.BaseClass;

//Part-3
/*
        and I want it to extend BaseClass here. Hopefully it finds the generic. BaseClass for you, otherwise you'll have
    to manually import that. Doing nothing else, I should be able to create instances of both classes, executing the
    recommendedMethod on both. I'll do this in the main method on my Main class.
*/
//End-Part-3

public class ChildClass extends BaseClass {

//Part-5
/*
        and use IntelliJ's override tools to generate this method. I'll customize this. I'm ok with the code that's on
    the BaseClass, but I want to include some extra functionality before that executes. I'll just add a statement that
    some extra stuff happened. I'll go back to the Main class and re-run this.

            [BaseClass.recommendedMethod]: Best Way to Do it
            [BaseClass.optionalMethod]: Customize Optional Method
            [BaseClass.mandatoryMethod]: NON-NEGOTIABLE!
            --------------------
            [BaseClass.recommendedMethod]: Best Way to Do it
            [Child:optionalMethod] EXTRA Stuff Here
            [BaseClass.optionalMethod]: Customize Optional Method
            [BaseClass.mandatoryMethod]: NON-NEGOTIABLE!
            --------------------
            [BaseClass.recommendedMethod]: Best Way to Do it
            [Child:optionalMethod] EXTRA Stuff Here
            [BaseClass.optionalMethod]: Customize Optional Method
            [BaseClass.mandatoryMethod]: NON-NEGOTIABLE!

    you can see, for the two child instances, the last two output segments show, that the optionalMethod on Child was
    executed, and the statements, child, extra stuff here, is printed. Our library class is being utilized as designed,
    and this is what we used to call, S W A D, System works as designed. But now, let's say the sub class designer decides
    to override the recommended method, on his own class. I'll make the change.
*/
//End-Part-5

    @Override
    protected void optionalMethod() {

        System.out.println("[Child:optionalMethod] EXTRA Stuff Here");
        super.optionalMethod();
    }

//Part-6
/*
        It is after all, only recommended. Again, I'll use the override tools to generate an override for recommendedMethod.
    The designer of this class didn't read the specification, or maybe we forgot to mention, that if you do this, you
    should always call super's recommended method. Now, I'm going to completely replace that statement, with my own code.
    I'll just print that I'll do things my way. And I'll remember to call optionalMethod. Back to the Main class, I'll
    re-run again.

            [BaseClass.recommendedMethod]: Best Way to Do it
            [BaseClass.optionalMethod]: Customize Optional Method
            [BaseClass.mandatoryMethod]: NON-NEGOTIABLE!
            --------------------
            [Child:recommendedMethod]: I'll do things my way
            [Child:optionalMethod] EXTRA Stuff Here
            [BaseClass.optionalMethod]: Customize Optional Method
            --------------------
            [Child:recommendedMethod]: I'll do things my way
            [Child:optionalMethod] EXTRA Stuff Here
            [BaseClass.optionalMethod]: Customize Optional Method

    the two child instances demonstrate that they're not following our best practices suggestion. They've customized the
    entire work flow, and even worse, the non-negotiable method is never run. Since it can't be accessed by subclasses,
    they can't even invoke it. What choices do you have now? You could make the non negotiable method, mandatoryMethod,
    be protected, so subclasses can call it, but that still puts the onus of invoking it, on the subclasses. You could
    recommend that designers of subclasses, copy and paste the mandatoryMethod into the subclass. That takes all the
    control of that functionality away from the BaseClass. If the BaseClass makes a change to its private mandatoryMethod,
    how will it broadcast to all the subclasses, that they should change their copies? The better solution is to make the
    recommendedMethod final, on the BaseClass. Let me go back to the BaseClass,
*/
//End-Part-6

/*
    @Override
    public void recommendedMethod() {

        System.out.println("[Child:recommendedMethod]: I'll do things my way");
        optionalMethod();
    }
*/

//Part-8
/*
        When you make a method final, a subclass can't override it. I'll comment out this method. Now, the only code the
    subclass can override, is the optionalMethod, which is exactly what I want. I now have control over the functionality
    of subclasses, where it's important. I've used final, and designed the methods accessed by the final method in various
    ways, as I showed you here. For code that's non-negotiable, I can make that private, or alternatively I could make
    that final. What happens if I add final to a private method? Let me go back to the BaseClass and try that,
*/
//End-Part-8

    private void mandatoryMethod() {
        System.out.println("[Child:mandatoryMethod]: My own important stuff");
    }

//Part-10
/*
        If you try to do this using IntelliJ's override help, you won't see this method listed. I'll type this method out,
    using the same signature. I'll add a print statement, so Child's mandatory method, my own important stuff. Ok, there's
    a couple of things I want to point out here. First, this isn't an override, you can't override methods that are private,
    on a BaseClass. Second, I was able to create this method, even though I added final on the BaseClass's method. The
    modifiers private and final are somewhat redundant. When a method is private, no other class, including subclasses,
    has access to it, and therefore any code that calls it, will only execute the code on the class where it's declared.
    To put it another way, using the private modifier means this method will never be polymorphic. That means if I run
    my code now, even though I have my own private method called mandatoryMethod, on the child, it's never going to be
    the method that's executed from the recommendedMethod, on BaseClass. I'll go back to the Main class and rerun this.

                [BaseClass.recommendedMethod]: Best Way to Do it
                [BaseClass.optionalMethod]: Customize Optional Method
                [BaseClass.mandatoryMethod]: NON-NEGOTIABLE!
                --------------------
                [BaseClass.recommendedMethod]: Best Way to Do it
                [Child:optionalMethod] EXTRA Stuff Here
                [BaseClass.optionalMethod]: Customize Optional Method
                [BaseClass.mandatoryMethod]: NON-NEGOTIABLE!
                --------------------
                [BaseClass.recommendedMethod]: Best Way to Do it
                [Child:optionalMethod] EXTRA Stuff Here
                [BaseClass.optionalMethod]: Customize Optional Method
                [BaseClass.mandatoryMethod]: NON-NEGOTIABLE!

    and you can see for all instances, the non-negotiable statement, meaning the private method on BaseClass, was executed
    every time. Getting back to the BaseClass,
*/
//End-Part-10
}
