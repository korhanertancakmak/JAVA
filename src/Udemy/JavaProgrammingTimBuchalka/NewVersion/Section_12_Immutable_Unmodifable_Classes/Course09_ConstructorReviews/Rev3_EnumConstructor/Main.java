package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course09_ConstructorReviews.Rev3_EnumConstructor;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course09_ConstructorReviews.Rev3_EnumConstructor.external.Child;

//Part-1
/*
        In this lecture, I want to look again at the enum type. I didn't get too deep into them before, because for the
    most part, you'll probably be using them in their simplest form. Usually, you'll just use an enum as a list of named
    constants, and sometimes the ordinal values are important. I did show you how to include a method, in the body of the
    enum, and use that method in code as well, but I didn't cover constructors. What does it mean if I set up a constructor
    on an enum? Let me set this up. First I'll create a new Java class, I'll pick enum as the type, and call that Generation.
*/
//End-Part-1

public class Main {

    public static void main(String[] args) {

        Parent parent = new Parent("Jane Doe", "01/01/1950", 4);
        Child child = new Child();

        System.out.println("Parent: " + parent);
        System.out.println("Child: " + child);

        Person joe = new Person("Joe", "01-01-1950");
        System.out.println(joe);

        Person joeCopy = new Person(joe);
        System.out.println(joeCopy);

        //Generation g;
        Generation g = Generation.BABY_BOOMER;

//Part-4
/*
        Let's see what happens if I use this enum. I won't even use it, I'll just set up a local variable with that type
    to start. If I run that code,

                    Parent static initializer: class being constructed
                    In Parent Initializer
                    In Parent Constructor
                    In Parent Initializer
                    In Parent Constructor
                    Child: Initializer, birthOrder = 3, birthOrderString = Middle
                    Child: Constructor
                    Parent: name='Jane Doe', dob='01/01/1950'
                    Child: name='Jane Doe', dob='02/02/1920', Middle child
                    Person[name=Joe, dob=01/01/1950]
                    Person[name=Joe, dob=01/01/1950]

    there's no evidence that the enum constructor was ever called. Next, I'll assign a value to that local variable,
    assigning it BABY BOOMER. Running this code,

                    ---(same)
                    Person[name=Joe, dob=01/01/1950]
                    Person[name=Joe, dob=01/01/1950]
                    GEN_Z
                    MILLENNIAL
                    GEN_X
                    BABY_BOOMER
                    SILENT_GENERATION
                    GREATEST_GENERATION

    you can see, the constructor didn't just run for the BABY BOOMER, it ran for each one of the enum constants. When an
    Enum is initialized, all its constants are initialized, which means the constructor was called for each constant.
    Now, let's use the javap tool, to examine what an enum really is, under the hood. I'll type in javap, then dash p
    which means I want all members printed, including private and package private ones. I'll include the path, where the
    class file is, and then just type Generation, and then enter. Running this,

                    Compiled from "Generation.java"
                    public final class Rev3_EnumConstructor.Generation extends java.lang.Enum<Rev3_EnumConstructor.Generation> {
                      public static final Rev3_EnumConstructor.Generation GEN_Z;
                      public static final Rev3_EnumConstructor.Generation MILLENNIAL;
                      public static final Rev3_EnumConstructor.Generation GEN_X;
                      public static final Rev3_EnumConstructor.Generation BABY_BOOMER;
                      public static final Rev3_EnumConstructor.Generation SILENT_GENERATION;
                      public static final Rev3_EnumConstructor.Generation GREATEST_GENERATION;
                      private static final Rev3_EnumConstructor.Generation[] $VALUES;
                      public static Rev3_EnumConstructor.Generation[] values();
                      public static Rev3_EnumConstructor.Generation valueOf(java.lang.String);
                      private Rev3_EnumConstructor.Generation();
                      private Rev3_EnumConstructor.Generation[] $values();
                      static {};
                    }

    you can see that Generation is a final class. I'll be covering final classes shortly. This class extends a special
    class in Java, a java.lang.Enum. Now, what's so interesting is, you can see that each one of my constants is really
    a final static field, that has the same type as this enum, Generation. Notice also that there's a constructor, it's
    private, near the bottom of this output. Let's see what else we can do here.
*/
//End-Part-4

        //Generation h = new Generation();
        //Generation h = new Generation.BABY_BOOMER(1900, 2000);

//Part-6
/*
        I'll set up another local variable, and try to instantiate a new Generation. IntelliJ tells me enum types can't
    be instantiated. What about an enum constant? I'll try to instantiate a new Generation Baby Boomer, passing it a
    different range of values. And that too, isn't permissible, so I'll just remove that last statement altogether.
    Getting back to the Generation enum,
*/
//End-Part-6
    }
}
