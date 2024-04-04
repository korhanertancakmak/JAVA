package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course03_WhenChangeIsGood;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course03_WhenChangeIsGood.consumer.specific.ChildClass;
import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course03_WhenChangeIsGood.external.util.Logger;
import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course03_WhenChangeIsGood.generic.BaseClass;

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

        String xArgument = "This is all I've got to say about Section ";
        StringBuilder zArgument = new StringBuilder("Only saying this: Section ");
        doXYZ(xArgument, 16, zArgument);
        System.out.println("After Method, xArgument: " + xArgument);
        System.out.println("After Method, zArgument: " + zArgument);

        StringBuilder tracker = new StringBuilder("Step 1 is abc");
        Logger.logToConsole(tracker.toString());
        tracker.append(", Step 2 is xyz.");
        Logger.logToConsole(tracker.toString());
        System.out.println("After logging, tracker = " + tracker);

//Part-4
/*
        First, I'll create a new StringBuilder variable, tracker, and assign that a new instance, constructing it with
    the text, step 1 is abc. I'll call log to console, on Logger, passing it tracker. I'll continue processing, appending
    data to the tracker variable. I'll execute log to console again. Then I'll print the text, after logging, and print
    out tracker. If you were just reading this code, you might guess that you'd get Step 1 is abc, a comma, then Step 2
    is xyz, printed out, after two logging messages. Let me run this.

                    12/03/23 22:20:16 : Step 1 is abc
                    12/03/23 22:20:16 : , Step 2 is xyz.
                    After logging, tracker =

    There you can see the logged statements, with date and time in common formats, and after that, the message. After
    logging, tracker variable is empty. That could be a pretty confusing result. The log To Console method has a pretty
    nasty side effect. It clears my StringBuilder instance, after each log statement is output. Are you really going to
    have to worry about every library class you use? Probably not, but there are some steps you can take, which is called
    defensive coding, to minimize risk. One simple thing I can do with this code, is pass a string to the log message,
    and not the reference. Let me change how I invoke those methods, and instead of passing tracker, I'll pass tracker.
    toString. Rerunning this code now,

                    12/03/23 22:20:16 : Step 1 is abc
                    12/03/23 22:20:16 : Step 1 is abc, Step 2 is xyz.
                    After logging, tracker = Step 1 is abc, Step 2 is xyz.


    my results make a lot more sense. After logging, tracker equals Step 1 is abc, Step 2 is xyz, so this code had no
    unintended consequences, or side effects, from calling log to console. It's very easy to forget that method arguments
    are copies of references. This is especially easy to overlook, when you're dealing with collections and arrays. I'll
    be talking about making defensive copies in an upcoming lecture, but right now, let's explore another problem caused
    by mutable objects. I'll create a new class and call it MainMailer,
*/
//End-Part-4
    }

    private static void doXYZ(String x, int y, final StringBuilder z) {

        final String c = x + y;
        System.out.println("c = " + c);
        x = c;
        z.append(y);
//        z = new StringBuilder("This is a new reference");
    }

}