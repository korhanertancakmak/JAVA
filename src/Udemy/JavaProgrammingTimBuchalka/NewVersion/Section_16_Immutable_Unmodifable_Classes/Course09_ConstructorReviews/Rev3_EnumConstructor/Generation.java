package CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course09_ConstructorReviews.Rev3_EnumConstructor;

import java.time.LocalDate;

//Part-2
/*
        I'll add my constants. I'm going to set these up, one on each line. You'll understand why in just a minute. Each
    one of these represents a generational name, so a range of birth dates, that describe if a person is in one of these
    groups. Hopefully you're somewhat familiar with these labels. I have GEN Z, and MILLENNIAL, GEN X, then Baby boomer,
    silent generation, and finally the greatest generation. Each of these represents a range of birth years. If you've
    got a young child, he or she is probably a GEN Z, and a grandparent might be a baby boomer, or from the silent generation.
    Maybe you're a millennial, or a gen x. Like any class, I can generate a constructor for this enum. I'll insert it
    after my constants list, and before the class's closing brace.
*/
//End-Part-2

public enum Generation {

    GEN_Z {
        {
            System.out.println("-- SPECIAL FOR " + this + " --");
        }
    },

//Part-10
/*
        I'll do this with Gen z, adding a class body, by including a starting and ending curly brace. Ok, so what happens
    if I just add a System.out.println statement there? I want this to print out, special for gen z. But this doesn't
    compile. If you thought about GEN Z as a class, you'd realize that what I'm really doing here is, inserting a line
    of code in a class body. I can't do that, but I can wrap it in another code block.I'll add another set of starting
    and ending curly braces. This compiles, and what do you think I'm doing here? Well, it's an instance initializer,
    for the GEN Z instance. Let's run this code now,

                            GEN_Z 2001 - 2023
                            -- SPECIAL FOR GEN_Z 2001 - 2023 --
                            MILLENNIAL 1981 - 2000
                            GEN_X 1965 - 1980
                            BABY_BOOMER 1946 - 1964
                            SILENT_GENERATION 1927 - 1945
                            GREATEST_GENERATION 1901 - 1926

    and here you can see that statement. You can also declare methods in these constant class bodies. It's generally not
    a good idea to make your enum too complex. If you need that much complexity, maybe a different approach would make
    more sense. Ok, so I think that covers constructors for records and enums. In the next lecture, I want to talk a little
    more about final classes. You saw from the disassembled class, that the Generation enum is declared as a final class.
    This is also true of the person record. I'll be talking about that next, but first, I want to give you a challenge,
    to test what you've learned about these additional initialization techniques.
*/
//End-Part-10

    MILLENNIAL(1981, 2000),
    GEN_X(1965, 1980),
    BABY_BOOMER(1946, 1964),
    SILENT_GENERATION(1927, 1945),
    GREATEST_GENERATION(1901, 1926);

//Part-5
/*
        It would be nice if I could include the corresponding year ranges, associated with each enum constant. I could
    set up a method, as I did in a previous example, and return the result of a switch expression, which could be the year
    range. But I really want the range to be two fields, a start year, and an end year. Turns out, I can add additional
    fields to an enum, and use a constructor to populate them.

        As with most classes, I can add instance fields, so I'll add a start year and an end year. I'll insert these
    before my constructor, but after my constants list. I'm going to pass these as arguments to the constructor next.
    I'll assign the instance fields. this.startYear, equals, startYear. this.endYear, equals, endYear. I'll also print
    that year range out, concatenating the two variables to my println statement.

        Now, look what these changes did to my enum constant declarations. Every single one is showing an error. Because
    I created this explicit constructor, the implicit no args one doesn't get created. This means, I have to pass values
    to the constructor, for each of these constants. How do I do that? Well, I can just define the arguments in parentheses,
    after each constant name.

        I'll add 2001 and 2025 to GEN Z, these are the birth date years, the range of years, that get a person labeled
    as part of the GEN Z generation. I'll set millennial to be 1981 to 2000. GEN Z is 1965 to 1980. Baby Boomer is 1946
    to 1964. The silent generation was born between 1927 and 1945. Finally, the greatest generation is 1901 to 1926.
    Now, my code compiles, and I can run it.

                    GEN_Z 2001 - 2025
                    MILLENNIAL 1981 - 2000
                    GEN_X 1965 - 1980
                    BABY_BOOMER 1946 - 1964
                    SILENT_GENERATION 1927 - 1945
                    GREATEST_GENERATION 1901 - 1926

    You can see that each one got constructed, and includes the year range. Let's run java p again. I can just press the
    up arrow on that terminal session.

                     private final int startYear;
                     private final int endYear;
                     private Rev3_EnumConstructor.Generation(int, int);

    This hasn't changed much, except it has the two instance fields, startYear and endYear. And there on the third to
    last line, is the new signature for the constructor. You can see each parameter type is an int. It's also private,
    even though I didn't include an access modifier. In fact, let me add the public modifier to that constructor.

                                        Generation(int startYear, int endYear) {
                                                         to
                                        Generation(int startYear, int endYear) {

        That gives me an error, so I'll hover over that, and I see the modifier public is not allowed here. Why not?
    Well, a constructor on an enum is implicitly private, whether we declare it that way or not. We can't declare a
    constructor here as either public or protected. I'll revert that last statement. The other thing that you should be
    aware of, now that you know these constructors are private, is that I can't actually create an instance of an enum.
    Let me go back to the main method, and demonstrate that.
*/
//End-Part-5

//Part-7
/*
        I want those instance fields to be final. There won't be any need to change those values, so I'll add final to
    each of those declaration statements. I'll print that date range out in the toString method,
*/
//End-Part-7

    private final int startYear;
    private final int endYear;

//Part-9
/*
        Inserting it before this other constructor, and select none for the fields. Now I've get an explicit no args
    constructor. Like other classes, I can chain constructors, so I'll chain the two args constructor, passing 2001, and
    the current year, which I get using LocalDate.now, with getYear. Now, for the Gen z constant, I can remove the arguments,
    so that it'll execute the no args constructor. I can define this with empty parentheses, or I can remove them all
    together. Running this code,

                        GEN_Z 2001 - 2023
                        MILLENNIAL 1981 - 2000
                        GEN_X 1965 - 1980
                        BABY_BOOMER 1946 - 1964
                        SILENT_GENERATION 1927 - 1945
                        GREATEST_GENERATION 1901 - 1926

    my gen z end year, is the current year. Now that you know that each constant is really a Generation class, you can
    actually include a class body for each, or any of these.
*/
//End-Part-9

    Generation() {
        this(2001, LocalDate.now().getYear());
    }

//Part-3
/*
        Notice, after that's inserted, that IntelliJ added a semi-colon after the greatest generation constant. Any time
    you add code to an enum, other than the constants, you must include this semi-colon. Ok, so first, I'll just print a
    statement in this constructor. I'll have the code print out the value of this. Getting back to the main method,
*/
//End-Part-3

    Generation(int startYear, int endYear) {
        this.startYear = startYear;
        this.endYear = endYear;
        //System.out.println(this + " " + startYear + " - " + endYear);
        System.out.println(this);
    }

//Part-8
/*
        So I'll generate that, selecting none for fields. I'll return this.name, and the year range from this method.
    I'll also change that println statement in the constructor, just printing this alone again. Running this code,

                            GEN_Z 2001 - 2025
                            MILLENNIAL 1981 - 2000
                            GEN_X 1965 - 1980
                            BABY_BOOMER 1946 - 1964
                            SILENT_GENERATION 1927 - 1945
                            GREATEST_GENERATION 1901 - 1926

    I get the same results. You can have more than one constructor in an enum. Again I'll generate a constructor,
*/
//End-Part-8

    @Override
    public String toString() {
        return this.name() + " " + startYear + " - " + endYear;
    }
}
