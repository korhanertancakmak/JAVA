package CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course02_AppliedFinalModifiers.consumer.specific;


import CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course02_AppliedFinalModifiers.generic.BaseClass;

public class ChildClass extends BaseClass {

    @Override
    protected void optionalMethod() {

        System.out.println("[Child:optionalMethod] EXTRA Stuff Here");
        super.optionalMethod();
    }

//    @Override
//    public void recommendedMethod() {
//
//        System.out.println("[Child:recommendedMethod]: I'll do things my way");
//        optionalMethod();
//    }

    private void mandatoryMethod() {
        System.out.println("[Child:mandatoryMethod]: My own important stuff");
    }

//Part-2
/*
        I'll change BaseClass to Child in the printed statement. The mandatoryStatic method is a private static method
    on the BaseClass, which means I can't call it from this subclass, or any class for that matter, so I'll comment out
    the last statement, which invokes it. Ok, so what am I really doing here? I've created a static method with the same
    signature of a static method, on the BaseClass. If you guessed overriding, you'd be in good company, but this is really
    something else. This is called hiding a method. First, you might have noticed, that static methods won't show up in
    Intelli J's override method help. The behavior, of a hidden static method, is different than the behavior you might
    expect when overriding a method. I'll run this, and then I want to compare the first 3 segments of output, with the
    last 3.

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
                --------------------
                [BaseClass.recommendedStatic] BEST Way to Do it
                [BaseClass.optionalStatic]: Optional
                [BaseClass.mandatoryStatic]: MANDATORY
                --------------------
                [BaseClass.recommendedStatic] BEST Way to Do it
                [BaseClass.optionalStatic]: Optional
                [BaseClass.mandatoryStatic]: MANDATORY
                --------------------
                [Child.recommendedStatic] BEST Way to Do it
                [BaseClass.optionalStatic]: Optional

    First, you can see, in the second and third segments, the text, child, extra stuff here. This tells me the overridden
    method, the method on child, was executed for both of my child class instances, so the one I called child, and the
    one I called childReferredToAsBase. It doesn't matter what the reference type of the variable is. In other words,
    childReferredToAsBase, a BaseClass variable, exhibited the same behavior as child, a ChildClass variable. Compare that,
    to the results of the static method being called, on the same three instances. Only the output from the last call is
    different. This is the output of the child instance, assigned to a Child variable. Only this variable executed the
    static method declared on Child. Why didn't the other child instance variable, call that method declared on the Child
    class? I have a Base class, with one static method, called doClassMethod. I also have one instance method, called
    doObjectMethod. The child class has it's own copies of these methods, with the same signatures. The child class hides
    the base class's static method, doClassMethod. The child overrides the base class's instance method, doObjectMethod.

        Now, consider we have three declarations. X is declared as a BaseClass, and refers to an instance of a BaseClass.
    Y is declared as a BaseClass, and is referring to an instance of a Child class. In this case, BaseClass is the reference
    type, and Child is the instance or runtime type. Z is declared as a Child, and is referring to an instance of a Child
    class. When you execute a static method on an instance, the reference type determines which method is called, the one
    on BaseClass, or the one on Child. What this means is, even though Y is really a child instance, it doesn't matter
    for static methods. Static methods are based on the reference, so in this example, X and Y would both execute the static
    method declared on the Base class. Instance methods are called, based on the runtime type of the instance, and not
    the declared type. This means that Y and Z execute the doObjectMethod on Child, because they're both instances of the
    Child class.

                        Recommendation: Always use the type, to invoke the static method

        Best practice recommends always using the type reference, when executing a static method. If you're hiding a
    static method on a parent class, make sure you understand what the implications are, for doing this. If you stick
    to using a qualifier, the type reference, to execute the specific static method, you'll avoid the confusion shown on
    the previous paragraph. Ok, so now I want to go back to my code. Going main method,
*/
//End-Part-2

    public static void recommendedStatic() {

        System.out.println("[Child.recommendedStatic] BEST Way to Do it");
        optionalStatic();
//        mandatoryStatic();
    }
}
