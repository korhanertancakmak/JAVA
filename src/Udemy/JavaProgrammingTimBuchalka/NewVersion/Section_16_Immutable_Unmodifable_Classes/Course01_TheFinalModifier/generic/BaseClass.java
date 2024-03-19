package CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course01_TheFinalModifier.generic;

//Part-2
/*
        Let's imagine that I've been tasked to write a library class, and although it should be extensible, I don't want
    everything to be customizable. This class will have three methods. The first, a public method, is there to control the
    work flow, and its expected this method will always be called. I'm going to make it public and void, and call it
    recommendedMethod. I'll print that this is on the Base class, and this is the best way to do it. This code has a
    method where optional code could go, or extensions could be made. This will go in a optionalMethod method. There's
    also a mandatory method, which this base class expects to be executed for every instance. I'll add the method,
    optionalMethod, next. This will be protected, meaning the only classes that can call it are the subclasses, or in
    the same package as this class. I'll just print that this is on BaseClass, and it's meant to be customizable or
    optional. Next, I want the mandatoryMethod. This one is private, because I don't want subclasses to alter or override
    this code. I'll print that this method is on BaseClass, and this code is non-negotiable. This feels like a solid way
    to allow a little bit of customization, but still have most subclasses execute the same work flow. Now, I'm going to
    emulate a class that might use my library class. I'll create a new class, calling that ChildClass. To keep it more
    real, I'll put this in completely different package structure. I'll expand the project panel, and highlight my new
    class name is consumer.specific.ChildClass,
*/
//End-Part-2

public class BaseClass {

//Part-7
/*
        and do that, adding final to the recommendedMethod.

                                        public void recommendedMethod()
                                                    to
                                        public final void recommendedMethod()

    The subclass, ChildClass, doesn't compile now.
*/
//End-Part-7

    public final void recommendedMethod() {

        System.out.println("[BaseClass.recommendedMethod]: Best Way to Do it");
        optionalMethod();
        mandatoryMethod();
    }

    protected void optionalMethod() {
        System.out.println("[BaseClass.optionalMethod]: Customize Optional Method");
    }


//Part-9
/*
        Adding final to my mandatory method. Notice that IntelliJ has grayed out the final keyword in this case. I'll
    come back to that in just a minute. I want to first jump back to the child class, and override this method, mandatoryMethod.
*/
//End-Part-9

    private final void mandatoryMethod() {
        System.out.println("[BaseClass.mandatoryMethod]: NON-NEGOTIABLE!");
    }

//Part-11
/*
        let's see why IntelliJ has grayed that out. Although the message isn't great, private method declared final, the
    suggestion is to remove the final modifier. It's redundant for private methods, but not a compiler error. I'll remove
    that key word from this method. Now, I want to compare instance methods, with static methods, for a few minutes.
    I'm going to create three static methods on my BaseClass.

        These will be similar to the instance methods, so first a public one, I'll call recommendedStatic. It will print
    that this is base class recommendStatic, and the best way to do it. It will in turn call optional Static and mandatory
    Static methods. I'll create the optionalStatic method next, and that's protected and static. This will print that
    it's the optionalStatic method on base class. Lastly, I need the mandatoryStatic method, and that will be private
    and static. This will print that it's the mandatory Static method on base class. Getting back to the main method on
    the Main class,
*/
//End-Part-11

    public static void recommendedStatic() {

        System.out.println("[BaseClass.recommendedStatic] BEST Way to Do it");
        optionalStatic();
        mandatoryStatic();
    }

    protected static void optionalStatic() {
        System.out.println("[BaseClass.optionalStatic]: Optional");
    }

    private static void mandatoryStatic() {
        System.out.println("[BaseClass.mandatoryStatic]: MANDATORY");
    }

}
