package CourseCodes.NewSections.Section_07_OOP1.Course20_StringClassMethods2;

/*
Course-50

                                        String Manipulation Methods

        The first set of method, don't actually change the underlying meaning of the text value, but perform some kind of
    clean up.

                method              description
                --------------------------------------------------------------------------------------------------------
                indent              This method adds or removes spaces from the beginning of lines in multi-line text.
                --------------------------------------------------------------------------------------------------------
                strip
                stripLeading        The difference between the strip method and trim method is that the strip() supports
                stripTrailing
                trim
                --------------------------------------------------------------------------------------------------------
                toLowerCase         Returns a new String, either in a lower case or in upper case
                toUpperCase
                --------------------------------------------------------------------------------------------------------

    For example, the methods remove unnecessary white space, or they change the case of the text, or they add, or remove
    white space, for indenting purposes. We have method named indent, strip, trim, toLowerCase, and toUpperCase.

        The second set of string manipulation methods, transform the String value, and return a String with a different
    meaning, than the original String.

                method              description
                --------------------------------------------------------------------------------------------------------
                concat              Similar to the plus operator for strings, it concatenates text to the String and returns
                                    a new String as the result.
                --------------------------------------------------------------------------------------------------------
                join                Allows multiple strings to be concatenated together in a single method, specifying a
                                    delimiter.
                --------------------------------------------------------------------------------------------------------
                repeat              Returns the String repeated by the number of times specified in the argument.
                --------------------------------------------------------------------------------------------------------
                replace             These methods replace characters or strings in the string, returning a new String
                replaceAll          with replacements made.
                replaceFirst
                --------------------------------------------------------------------------------------------------------
                substring           These return a part of the String, its range defined by the start and end index
                subSequence         specified.
                --------------------------------------------------------------------------------------------------------

    These include methods for creating strings, from other strings, with such methods as concat, join, and repeat. There
    are methods to replace characters in the String, or replace some parts of the text with a different set of characters,
    with replace, replaceFirst and replaceAll. And lastly, we have methods to get parts of the String value, with the
    substring, and subSequence.

        And now, let's get going, by reviewing a method we looked at briefly in a previous challenge, the substring method.
    Let's type this out, then we'll talk about it.

                    String birthDate = "02/09/1990";
                    int startingIndex = birthDate.indexOf("1990");
                    System.out.println("startingIndex = " + startingIndex);
                    System.out.println("Birth year = " + birthDate.substring(startingIndex));

    Ok, so here we have a birthdate as a String. Java has classes for dealing with dates, but for now, we'll make this
    date a String, to explore some of the String methods. And next, we call the indexOf method we talked about, passing
    the String "1990". This will tell us where, in the String, the year 1990 can be found. We print that out, then we
    print out the Birth year, which was extracted from the birthDate variable, with the substring method, using that index
    we just got. Now running that code,

                    startingIndex = 6
                    Birth year = 1990

    we see that the startIndex was 6, meaning the String "1990" was found starting at index 6, in the sequence of characters.
    And we were able to extract the year from the birthdate variable successfully, Birth year = 1990. The substring method
    that has 1 argument, will retrieve a string starting at the index passed, and retrieving the remaining String. But the
    method substring has an overloaded version, that takes an ending index as well, which will get the String starting at
    the specified start position. Instead of returning the rest of the String, this version of substring returns the part
    of the string, up to but not including, the character at the end position we specify.

        Ok, let's extract the month from this date, which is in the format, DD for day, MM, for month, and YYYY for year.

                    0 1 2 3 4 5 6 7 8 9
                    D D / M M / Y Y Y Y

    For this date String, the month is starting at position 3, if we start counting at 0. And because we only want 2 characters
    in the result, we need to use 5, as the second argument to this method. This method will get the characters, starting
    at index 3 and include the characters, up to but excluding, index 5. In other words, it gets the characters at position
    3 and 4.

                    System.out.println("Month = " + birthDate.substring(3,5));

    And the result of that, is Month = 09. That's substring, which you'll most likely be using a lot. Let's look at another
    very useful method, and that's join, a static method on String. This joins a series of String together, with some
    delimiter. In fact, we can build a date string here, using this method:

                    String newDate = String.join("/", "02", "09", "1990");
                    System.out.println("newDate = " + newDate);

    And if we run that:

                    Month = 09
                    newDate = 02/09/1990

    you see we built a date String, with the same date as before, using this method. This is a good method to use if you
    need to create a comma delimited String, as an example. That's a useful method, but we could also do this, using the
    concat method. It would be a little tedious, but let's do it.

                    newDate = "02";
                    newDate = newDate.concat("/");
                    newDate = newDate.concat("09");
                    newDate = newDate.concat("/");
                    newDate = newDate.concat("1990");
                    System.out.println("newDate = " + newDate);

    First of all, let me discourage you from writing code like this. Although it's perfectly valid, it's pretty inefficient.
    Each call is a new creation of a String object. But if we run this,

                    newDate = 02/09/1990

    we get the same result as we got with join. The concat method does what the plus operator does, when used with Strings,
    but actually less efficiently, when it's used with String literals. There are 2 other ways to write this code. Let's
    look at the first, which is just using the plus operator on a bunch of String literals:

                    newDate = "02" + "/"  + "09" + "/" + "1990";
                    System.out.println("newDate = " + newDate);

    Again, it's unlikely you'd write code like this, but it's more efficient than the previous code. This is because Java's
    compiler recognizes this statement as a single string, because of the use of literals and the plus operator. The full
    string, in other words the full date string, will be used in the generated bytecode.

        And let's look at another way to do this:

                    newDate = "02".concat("/").concat("09").concat("/")
                            .concat("1990");
                    System.out.println("newDate = " + newDate);

    What about this code? This coding style has a special name, and it's called method chaining. You can write String
    methods this way because they return Strings. But again, this is just like writing the concat statements, each on its
    own line. Each call to the concat method is still a new String object being created, but instead of assigning that
    result to a variable, we chain it to another method result. Ok, so that was the concat method.

        For replace method:

                    System.out.println(newDate.replace('/', '-'));

    and running that:

                    02-09-1990

    you can see it replaces every "/" with "-".

                    System.out.println(newDate.replace("2", "00"));

    and running that:

                    000/09/1990

    we get every 2, replaced by 2 sets of zeros. Let's look at replaceFirst and replaceAll methods:

                    System.out.println(newDate.replaceFirst("/", "-"));
                    System.out.println(newDate.replaceAll("/", "---"));

    and if we run that:

                    02-09/1990
                    02---09---1990

    the replaceFirst method just replaces the first instance, where there's a match, so only the first "/" was replaced
    with a "-". In contrast, the replaceAll method, replaced all occurrences.

        There's another important difference between these 2 methods, and the replace method. The first argument for these
    last 2 methods, is really a regular expression String, which is a special syntax, that looks for patterns in a String.
    We'll look at regular expressions later in the course, but in general, you can use replaceFirst with basic strings,
    to replace the first instance. But if you want to replace all occurrences of one string with another, use the replace
    method, rather than the replaceAll method.

        Ok, so lastly, we can talk about repeat() and indent() methods. Firstly for repeat:

                    System.out.println("ABC\n".repeat(3));
                    System.out.println("-".repeat(20));

    the second statement was added, so the output will be easier to compare, so let's run this:

                    ABC
                    ABC
                    ABC

                    --------------------

    the repeat code produced 3 lines, all having "ABC" in them, plus an empty line in the first case, and a separating line
    of dashes in the second case. That's the repeat method, but now let's use the indent method:

                    System.out.println("ABC\n".repeat(3).indent(8));
                    System.out.println("-".repeat(20));

    Let's run this:

                    --------------------
                            ABC
                            ABC
                            ABC

                    --------------------

    And you can see the output, compared to the previous output, every line of text in this String was indented by 8 spaces.
    And this method, the indent, can also be used to remove some leading spaces, so let's try that next.

                    System.out.println("    ABC\n".repeat(3).indent(-2));
                    System.out.println("-".repeat(20));

    And running that:

                    --------------------
                      ABC
                      ABC
                      ABC

                    --------------------

    you can see that the multiple line String, wasn't outputted with 4 spaces, which we had in the first String, but only
    2 spaces, at the start of each line. Using indent with a negative number, is a way to remove spaces from each line,
    in a multi-line text value.
*/

public class Main {
    public static void main(String[] args) {

        String birthDate = "02/09/1990";
        int startingIndex = birthDate.indexOf("1990");
        System.out.println("startingIndex = " + startingIndex);
        System.out.println("Birth year = " + birthDate.substring(startingIndex));

        System.out.println("Month = " + birthDate.substring(3,5));

        String newDate = String.join("/", "02", "09", "1990");
        System.out.println("newDate = " + newDate);

        newDate = "02";
        newDate = newDate.concat("/");
        newDate = newDate.concat("09");
        newDate = newDate.concat("/");
        newDate = newDate.concat("1990");
        System.out.println("newDate = " + newDate);

        newDate = "02" + "/"  + "09" + "/" + "1990";
        System.out.println("newDate = " + newDate);

        newDate = "02".concat("/").concat("09").concat("/")
                .concat("1990");
        System.out.println("newDate = " + newDate);

        System.out.println(newDate.replace('/', '-'));
        System.out.println(newDate.replace("2", "00"));

        System.out.println(newDate.replaceFirst("/", "-"));
        System.out.println(newDate.replaceAll("/", "---"));

        System.out.println("ABC\n".repeat(3));
        System.out.println("-".repeat(20));

        System.out.println("ABC\n".repeat(3).indent(8));
        System.out.println("-".repeat(20));

        System.out.println("    ABC\n".repeat(3).indent(-2));
        System.out.println("-".repeat(20));
    }
}
