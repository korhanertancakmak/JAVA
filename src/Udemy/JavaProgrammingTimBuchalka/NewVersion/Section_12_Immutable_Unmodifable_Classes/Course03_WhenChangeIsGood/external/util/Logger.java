package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course03_WhenChangeIsGood.external.util;

import java.time.LocalDateTime;

//Part-2
/*

*/
//End-Part-2

public class Logger {

//Part-3
/*
        I'll add one more bit of code to this method. I'll next check instance of operator to see if it's a string builder.
    If it is, it gets assigned to the variable, sb. Next, I'll call set length on this, passing 0. This truncates the data
    in that stringBuilder. Ok, so wait, why would a logging statement do this? Well, hopefully it never would. The point
    is, unless you examine every line of code, you don't really know if a method you're using, might have side effects.
    I'll now call this from my main method.
*/
//End-Part-3

    public static void logToConsole(CharSequence message) {

        LocalDateTime dt = LocalDateTime.now();
        System.out.printf("%1$tD %1$tT : %2$s%n", dt, message);
        if (message instanceof StringBuilder sb) {
            sb.setLength(0);
        }

    }
}
