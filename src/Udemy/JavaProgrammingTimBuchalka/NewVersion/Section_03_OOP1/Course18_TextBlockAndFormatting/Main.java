package CourseCodes.NewSections.Section_07_OOP1.Course18_TextBlockAndFormatting;

/*
Course-48

                                      The Text Block and other Formatting Options

        In the next couple of courses, I want to revisit Strings, because they are so intrinsic to Java, it's important
    to talk about them a bit more. But before we do that, let's take a look at a fairly new feature in Java, the Text Block.

        A Text Block is just a special format for multi-line String literals. It's simply a "String" with a new representation
    in the source code. It became part of the official language as of JDK 15. To start with, let's look at an example in
    code of handling multiple lines of text:

                    String bulletIt = "Print a Bulleted List:" +
                            "\u2022 First Point" +
                            "\u2022 Sub Point";

                    System.out.println(bulletIt);

    Ok, so in "bulletIt" we're using the concatenation operator, the plus sign, to add several Strings together. And we're
    using the unicode character, "\u2022", to include a bullet point character, on these 2 lines. And if we print that out,

                    Print a Bulleted List:• First Point• Sub Point

    we get a single line of text. That's not what I really wanted, because I wanted some formatting, and these Strings on
    multiple lines. I can do this with some special combinations of characters, called escape sequences.

                                        Some Common Escape Sequences

        An escape sequence starts with a "\". Java has several, but the most common ones, are shown below. These can insert
    a tab, a newline, a double quote character, or a backslash character if you need it, in your text.

                            Escape Sequence                     Description
                                  \t                            Insert a tab character
                                  \n                            Insert a new line character
                                  \"                            Insert a double quote character
                                  \\                            Insert a backslash character

    Let's add some of these characters to our multi-line String in this code:

                    String bulletIt = "Print a Bulleted List:\n" +
                            "\t\u2022 First Point\n" +
                            "\t\t\u2022 Sub Point";

                    System.out.println(bulletIt);

    and if we run it:

                    Print a Bulleted List:
                        • First Point
                            • Sub Point

    we get a formatted bulleted list. That looks pretty good. Now let's use a text block, to reproduce this output.

                    String textBlock = """
                            Print a Bulleted List:
                                    \u2022 First Point
                                        \u2022 Sub Point""";

                    System.out.println(textBlock);

    You can see right at the start, we're creating a String variable, and assigning it something. This something is the
    new format, called a "text block", for creating a multi-line formatted String. You'll notice that we start with 3 double
    quote there("""). This is required, and they must be on their own line. The text that follows, is the text that's part
    of the String. And we end with another triple quotes, which close, or complete, the text block. Everything between the 2
    sets of triple quotes, is the text block itself, and you can see we didn't include any additional quotes, or plus signs.
    And we also got rid of the tab, and newline escape sequences. The text block lets us format text in the source code,
    the same way we want to see it in the output. Let's run it and we get,

                    Print a Bulleted List:
                        • First Point
                            • Sub Point
                    Print a Bulleted List:
                            • First Point
                                • Sub Point

    And, you can see, we get the exact the same output, as we did when we concatenated several strings, and used escape
    sequences. That's really it. The text block makes outputting many lines of text, using indents and new lines, a lot
    easier, as I hope you can see.

        We've looked at formatting text over multiple lines in 2 different way. Next, let's look at formatting numbers in
    text, that we'll output. There are several ways to do this. Up until now, we've used System.out.println, and System.out.
    print, but there are 2 other methods, we haven't yet discussed. These are System.out.printf, and System.out.format.
    These methods behave the same, so for our examples, we'll just use System.out.printf, but be aware you can change printf
    to format, with the same results. Let's start with a simple example, then we'll explain how this works.

                    int age = 35;
                    System.out.printf("Your age is %d\n", age);

    You'll notice we're using printf, in place of print or println, on System.out. Printf is like print, in that it doesn't
    end with a newline character. But this method has multiple arguments, the first is a String which will be printed to
    the console, and the following arguments are values, that'll be used in the String. First, you'll notice our String
    contains "%d" in it. This is a special indicator, called a format specifier. This is a placeholder for other data,
    which should replace this specifier in the text. In this example, we're going to replace "%d" with "age", and that's
    why we pass age as the second argument to this method. Now running this code,

                    Your age is 35

    we see we get the output. Let's try another example. This time, we'll pass age, and year of birth, which we'll try to
    calculate using the year 2023 as the current year. Before we do that, we'll first add an escape sequence in the first
    String, so we have a newline printed between statements.

                    int yearOfBirth = 2023 - age;
                    System.out.printf("Age = %d, Birth year = %d", age, yearOfBirth);

    In this last statement, we have 2 instances of percent d, so we should pass 2 arguments, age and year of birth. And
    running that we see the result,

                    Your age is 35Age = 35, Birth year = 1988

    Let's take a moment, and look more closely at this format specifier, "%d". At their most complex, format specifiers
    take the form shown here.

                    %[argument_index$][flags][width][.precision]conversion

    They start with a "%" sign, and end with a conversion symbol, and have lots of options in between. We've looked at one
    conversion type, "d", which is the symbol used for a decimal integer value. Let's go to Java's API documentation from
    oracle website for a class called Formatter, which is the basis for many of the formatting methods we'll be talking
    about.

        Conversion      Argument Category       Description
        ----------      -----------------       ---------------------------------------------
            'd'	        integral	            The result is formatted as a decimal integer
            'f'	        floating point	        The result is formatted as a decimal number
            'n'	        line separator	        The result is the platform-specific line separator

    The ones you'll be using the most are above for 2 different numeric types, 'd' and 'f'. 'd', formats a decimal integer,
    as we've said, and would be used for any of Java's whole number primitive types, like short, int, and long. 'f' formats
    a decimal number, so it can be used for floating point numbers, a double, or float. But you can see there are many
    others, at the website, for different types of numbers, dates and time, as well as general specifiers. And look at the
    last one, the letter n, called the line separator, so this would print a newline character. The difference between
    the escape sequence, "\n", and this format specifier, "%n", is that "%n" outputs the platform's specific line separator,
    so it's preferred.

        From that documentation, we learned that we can replace our escape sequence, "\n", with a format specifier, "%n",
    so let's do that in our current code. And we'll also add it to the end of our second statement.

                    int age = 35;
                    System.out.printf("Your age is %d%n", age);

                    int yearOfBirth = 2023 - age;
                    System.out.printf("Age = %d, Birth year = %d%n", age, yearOfBirth);

                    System.out.printf("Your age is %f%n", age);

    Now, let's see what happens if we replace "%d", with "%f". We'll copy the first System.out.printf line, which prints
    out age only, and paste it, and we'll replace "d" with "f". This compiles, but if we run it, we get an exception,
    IllegalFormatConversionException. This is because it was expecting a float or double, and we passed an integer. Let's
    actually cast age to a float then, now, the code runs, and look at the output:

                    Your age is 35
                    Age = 35, Birth year = 1988
                    Your age is 35,000000

    We see age is printed, with a decimal point, and 6 zeros following it. This is the default format, for the floating
    point number specifier.

        What's nice about using this method, actually the reason it exists, is we can configure the specifier, so that we
    can output this number, in any way we want. Let's say we want 2 decimal places, and not 6, we do this by specifying
    the number of decimal places, called precision, between the "%" sign and the conversion symbol, f. We add the decimal,
    and then 2, and that tells the formatter, we only want 2 decimal places, for the number printed.

                    System.out.printf("Your age is %.2f%n", (float) age);

    now running this,

                    Your age is 35,00

    Ok, so that's how we specify precision, but now let's consider formatting the width of numbers, which is a separate
    option. Let's create a quick loop, that'll print out a series of numbers:

                    for (int i = 1; i <= 100000; i *= 10) {
                        System.out.printf("Printing %d %n", i);
                    }

    This loop just prints out numbers, multiplying by 10 each time. And running that,

                    Printing 1
                    Printing 10
                    Printing 100
                    Printing 1000
                    Printing 10000
                    Printing 100000

    we get output for 6 numbers, 1 through 100 thousand. What's important to notice are the numbers are all aligned on the
    left. We can change this by specifying the width of the number, in the specifier. First we'll set a width for the number.
    Our largest number in the output is 100000, so let's make the width, 6, so we'll add 6 in the specifier.

                    for (int i = 1; i <= 100000; i *= 10) {
                        System.out.printf("Printing %6d %n", i);
                    }

    And running that:

                    Printing      1
                    Printing     10
                    Printing    100
                    Printing   1000
                    Printing  10000
                    Printing 100000

    we get the numbers aligned on the right, because we said each number will fill up 6 spaces. Those are some of the most
    common things you'd do with formatting. We used System.out.printf, and remember System.out.format, can be used anywhere
    System.out.printf is used. But there'll be times you want to format Strings, and output them to a file, or error log
    for example, or maybe to a database. The String class itself has 2 methods, to support this type of formatting as well.
    One is a static method, called format. Let's see what that one looks like in code:

                    String formattedString = String.format("Your age is %d", age);
                    System.out.printf(formattedString);

    Here, we're creating a String variable, and assigning it the output of the call, to String.format, a static method.
    Like System.out.printf, this method has a String as its first argument, followed by arguments that match the specifiers
    in the String. And running that,

                    Your age is 35

    we get the same as before. And we could also have done that with the String instance method, "formatted". This method
    works the same as String.format, except you don't need to pass the formatString as an argument. The String itself is
    format String. Let's code that one next:

                    formattedString = "Your age is %d".formatted(age);
                    System.out.printf(formattedString);

    When you use this method, you just pass the arguments that match the specifiers in the current String, and the result
    is a formattedString. Running this output gives us the same output as before: "Your age is 35".

        Ok, so those are some of your formatting options. There was a time, when some of these formatting options were
    much slower, than simply using the concatenate operator, the "+" sign. But Java continues to make performance enhancements
    on these methods. The argument for using them, is that they make the code easier to read and maintain, and you have
    many options for outputting different specifier types.
*/

public class Main {
    public static void main(String[] args) {

        String bulletIt = "Print a Bulleted List:\n" +
                "\t\u2022 First Point\n" +
                "\t\t\u2022 Sub Point";

        System.out.println(bulletIt);

        String textBlock = """
                Print a Bulleted List:
                        \u2022 First Point
                            \u2022 Sub Point""";

        System.out.println(textBlock);

        int age = 35;
        System.out.printf("Your age is %d%n", age);

        int yearOfBirth = 2023 - age;
        System.out.printf("Age = %d, Birth year = %d%n", age, yearOfBirth);

        System.out.printf("Your age is %.2f%n",(float) age);

        for (int i = 1; i <= 100000; i *= 10) {
            System.out.printf("Printing %6d %n", i);
        }

        String formattedString = String.format("Your age is %d", age);
        System.out.printf(formattedString);

        formattedString = "Your age is %d".formatted(age);
        System.out.printf(formattedString);
    }
}
