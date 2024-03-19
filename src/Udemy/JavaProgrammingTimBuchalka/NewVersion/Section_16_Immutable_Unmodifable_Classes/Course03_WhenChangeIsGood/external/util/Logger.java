package CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course03_WhenChangeIsGood.external.util;

import java.time.LocalDateTime;

//Part-2
/*
        I want a method on this class. This is going to be public, static, and void, called log to console, and take a
    Char Sequence called message. This means it can take either a String or StringBuilder argument. I'll use the LocalDateTime
    class, which is like LocalDate, but it maintains information about both date and time, not just date alone. It also
    has a now method, to get the current date and time. I'll print a formatted string, using this date time field and
    the message, the method argument. Let me pause here to show you a useful page on formatting date and time, and where
    to get information about more format specifiers.

        'R' : Time formatted for the 24-hour clock as "%tH:%tM".
        'T' : Time formatted for the 24-hour clock as "%tH:%tM:%tS".
        'r' : Time formatted for the 12-hour clock as "%tI:%tM:%tS %Tp". The location of the morning or afternoon marker ('%Tp')
        'D' : Date formatted as "%tm/%td/%ty".
        'F' : ISO 8601 complete date formatted as "%tY-%tm-%td".
        'c' : Date and time formatted as "%ta %tb %td %tT %tZ %tY", e.g. "Sun Jul 20 16:17:00 EDT 1969".

    There are many ways to format date and time. A couple of standardized ones are shown here. These apply to the formatted
    method on String, as well as the printf method. The specifier for any date or time data is percent t. That is followed
    by any one of these conversion characters, and you can see there are many. A good place to start is the third table,
    which has 6 rows, and are commonly used date conversions. Capital D and Capital T are also easy to remember, so I'm
    going to use these. Capital T gives you the time formatted for the 24 hour clock, in the format of the hour colon,
    the minute colon, and the second. Capital D gives you the Date as month slash day slash year.

                                               Format Specifier Description
                                        %[argument_index$][flags][width]conversion

    This explains the code I'm using in a bit more detail. It's common when using date time conversions, to use the
    argument index feature, which is called "Explicit Indexing". This lets you list your date time variable just once as
    an argument. You then use an index, to tell the formatter, which argument is used for which specifier. If you do use
    an argument index, you need to use it for all specifiers,

                                "%1$tD %1$tT : %2$s %n".formatted(LocalDateTime.now(), message)

    which I'm showing on this example, using 2 for the message, String argument. I thought it was important to review the
    format specifiers a bit, especially for date time. This seemed like a good place to do it, even though it's a bit off
    topic. Let's get back to the code, and back on topic.
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
