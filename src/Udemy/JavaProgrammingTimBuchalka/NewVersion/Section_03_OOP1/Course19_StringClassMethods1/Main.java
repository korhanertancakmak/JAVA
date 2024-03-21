package CourseCodes.NewSections.Section_07_OOP1.Course19_StringClassMethods1;

/*
Course-49

                                      Another Look at the String

        The String has over 60 methods available. We won't cover them all here, but we'll cover the ones you'll probably
    use the most. The String is a sequence of characters, meaning its characters are ordered and indexed.

        Index       :   0   1   2   3   4   5   6   7   8   9   10
        Character   :   H   e   l   l   o       W   o   r   l   d

    The index starts at 0, and not 1. This is important to know if you're going to operate on Strings successfully. In
    the table below, we show the indices above each character for the String, "Hello World". We can say the character, 'H',
    is at index "0", and "W", is at index "6". The length of this String is 11, but its last index is "10".

        We can split String's methods up into 3 basic categories:
 1. String Inspection Methods: These provide some information about the string, with methods like length(), isEmpty(), and
    isBlank().
 2. Methods for Comparing String values: These usually return a boolean value (String values are equal or not).
 3. String Manipulation Methods: These transform one String value into another.

    Let's start with a look at some of the String Inspection Methods.

                method                      description
                --------------------------------------------------------------------------------------------------------
                length                      Returns the number of characters in the String
                --------------------------------------------------------------------------------------------------------
                charAt                      Returns the character at the index that's passed
                --------------------------------------------------------------------------------------------------------
                indexOf                     Returns an integer, representing the index in the sequence where the String
                lastIndexOf                 or character passed, can be located in the String.
                --------------------------------------------------------------------------------------------------------
                isEmpty                     Returns true if length is zero.
                --------------------------------------------------------------------------------------------------------
                isBlank                     Returns true if length is zero OR the string only contains whitespace characters.
                --------------------------------------------------------------------------------------------------------

    The method length tells us the number of characters in a sequence. The method charAt, will return a character, if we
    specify an index position. There are 2 methods, indexOf, and lastIndexOf, that can tell us if the String value contains
    a specified character, or sub string. The method isEmpty() and isBlank() tell us whether the sequence is empty, or
    contains just white space characters.

        Let's start with another method, that will print some String information, using a couple of the inspection
    methods:

                    public static void printInformation(String string) {
                        int length = string.length();
                        System.out.printf("Length = %d %n", length);
                        System.out.printf("First char = %c %n", string.charAt(0));
                    }

    First, with this code, we're creating a variable called length, and setting it to the length method. Then we print that
    out using printf, to reinforce what we learned in the last course. We also want to print the first character, and for
    this, we use the method charAt(), and pass it 0, which gets the first character in the string. And notice this additional
    specifier, %c, that we're using, we haven't used this before, but it just prints a single character. Let's call this
    method from the main method, and we'll pass it "Hello World".

                    printInformation("Hello World");

    And if we run that:

                    Length = 11
                    First char = H

    we see the length of the String is 11, and the first character of the String is "H". Let's add some more functionality
    to the printInformation method, and now get the last character. For character sequences in Java, the position starts
    with 0, and the last character, is one less than the String length. Let's add that code:

                    System.out.printf("Last char = %c %n", string.charAt(length - 1));

    And running that,

                    Length = 11
                    First char = H
                    Last char = d

    You can use the method charAt() with any index from 0 to the length - 1, to retrieve the character at that index. Passing
    any other index, that's not valid, will cause a runtime exception when the code runs.

        Now, let's look at the difference between isEmpty() and isBlank() next. First of all, if a String is empty, its
    length will be zero. We don't want to pass a bad index to the charAt method, so if the string is empty, we'll return
    from the method before those statements. Let's add that code:

                    if (string.isEmpty()) {
                        System.out.println("String is Empty");
                        return;
                    }

    Let's test an empty String, by calling this from the main method:

                    printInformation("");

    And running that,

                    Length = 11
                    First char = H
                    Last char = d
                    Length = 0
                    String is Empty

    we see the last 2 lines, that length is 0, and that "String is Empty". But if the String is blank, this means there
    are still characters, but they're just white space characters, like tabs, new lines, spaces, etc. We can still check
    the length and use the charAt method, for the first and last character, so we won't return from the method in this
    if statement:

                    if (string.isBlank()) {
                        System.out.printf("String is Blank");
                    }

    Now, we'll add another call to our print method, in the main method:

                    printInformation("\t   \n");

    And when we run that,

                    Length = 5
                    String is BlankFirst char =
                    Last char =

    We can see there are 5 characters in the String where isBlank was true, and the first and the last characters are white
    space of some sort. That's the difference between isEmpty() and isBlank().

        Now lastly, let's talk about the indexOf() and lastIndexOf() methods. First, these methods have several overloaded
    varieties. You can search for a single character, or a String. Let's look for the letter "R", in the "Hello World"
    String.

                    String helloWorld = "Hello World";
                    System.out.printf("index of r = %d %n", helloWorld.indexOf('r'));

    And running this,

                    index of r = 8

    Now, let's call that same method, but this time we'll search for a String, and not just a letter. Let's also look for
    String "World", in "Hello World".

                    System.out.printf("index of World = %d %n", helloWorld.indexOf("World"));

    And running that,

                    index of World = 6

    Hopefully, there's no surprises with those 2 overloaded versions. What happens if we use this method with letter 'l'?

                    System.out.printf("index of l = %d %n", helloWorld.indexOf('l'));
                    System.out.printf("index of l = %d %n", helloWorld.lastIndexOf('l'));

    And running that,

                    index of l = 2
                    index of l = 9

    The first method, indexOf, gave us the position of the first "l", in "Hello World". And the second method, lastIndexOf,
    gave us the position of the last "l", in "HelloWorld". That's the difference between those 2 methods. But what if we
    wanted get the position of the section "l"? Well, both of these methods each have a second parameter, an integer. Passing
    this argument, tells the code where to start searching for a match. We found the first "l" at position 2. If we wanted
    to start looking for the next "l", we'd start looking after that, at index 3. We can pass 3 as the second argument to
    the indexOf method. And we can do something similar with the lastIndexOf method. We said the last "l" was at index 9,
    and since this method searches forward for the character, and not backwards, we'll use 8 as the second argument.

                    System.out.printf("index of l = %d %n", helloWorld.indexOf('l', 3));
                    System.out.printf("index of l = %d %n", helloWorld.lastIndexOf('l', 8));

    if we now run this,

                    index of l = 3
                    index of l = 3

    both of these calls find the 2nd "l" in the string, using different methods. The indexOf method found it looking forward,
    starting at index 3, and searching towards the end of the String. The lastIndexOf method found it looking backwards,
    starting from index 8, and searching towards the beginning of the String.

        Now, let's look at the next set, the String Comparison Methods. Some of the string comparison methods will check
    for equality of the String values, like equals, equalsIgnoreCase, and contentEquals.

                method                              description
                --------------------------------------------------------------------------------------------------------
                contentEquals                       Returns a boolean if the String's value is equal to the value of the
                                                    argument passed. This method allows for arguments other than String,
                                                    for any type that is a character sequence.
                --------------------------------------------------------------------------------------------------------
                equals                              Return a boolean if the String's value is equal to the value of the
                                                    argument passed.
                --------------------------------------------------------------------------------------------------------
                equalsIgnoreCase                    Return a boolean if the String's value is equal(ignoring case), to the
                                                    value of the argument passed.
                --------------------------------------------------------------------------------------------------------
                contains                            Returns a boolean if the String contains the argument passed.
                --------------------------------------------------------------------------------------------------------
                endsWith                            These return a boolean, and are much like the contains method, but
                startsWith                          more specific to the placement of the argument in the String.
                --------------------------------------------------------------------------------------------------------
                regionMatches                       Returns a boolean, if defined sub-regions are matched.

    Others test whether a substring is part of a String value, like the contains method, as well as the endsWith and StartsWith
    methods. The regionMatches methods also do this, but it lets you specify regions of the strings to test for matches.
    There are 2 others that fit into this category, but not shown here, because we'll be talking about the compareTo(),
    and the matches methods, later in the course for more advanced topics, in the context of sorting, and using regular
    expressions. Most of these methods are pretty straightforward.

        Let's create a variable called helloWorld lower, and assign that, to the result of the toLowerCase method, from
    helloWorld variable. Then we'll use the equals method, and test if helloWorld, equals helloWorldLower.

                    String helloWorldLower = helloWorld.toLowerCase();
                    if (helloWorld.equals(helloWorldLower)) {
                        System.out.println("Values match exactly");
                    }
                    if (helloWorld.equalsIgnoreCase(helloWorldLower)) {
                        System.out.println("Values match ignoring case");
                    }

    And if we run that,

                    Values match ignoring case

    We don't get any output from the first if statement, because the cases of the text values aren't equal. But we do get
    output from the second if statement, that the String values match, if we do ignore case. Let's quickly look at a couple
    of the others. We'll add a test using the startsWith, endsWith, and lastly contains methods.

                    if (helloWorld.startsWith("Hello")) {
                        System.out.println("String starts with Hello");
                    }
                    if (helloWorld.endsWith("World")) {
                        System.out.println("String ends with World");
                    }
                    if (helloWorld.contains("World")) {
                        System.out.println("String contains World");
                    }

    Ok, so we have 3 new tests, and running this code shows us the results we'd expect.

                    String starts with Hello
                    String ends with World
                    String contains World

    Ok, so there's one more we haven't yet tested, and that's contentEquals().

                    if (helloWorld.contentEquals("Hello World")) {
                        System.out.println("Values match exactly");
                    }

    And we run that:

                    Values match exactly

    You might be wondering why Java has both the equals method, and the contentEquals method. The contentEquals method
    isn't limited, to just comparing String objects. It can be used to compare a StringBuilder's value, which the equals
    method doesn't support. We'll talk about this more, when we look at StringBuilder.
*/

public class Main {
    public static void main(String[] args) {

        printInformation("Hello World");
        printInformation("");
        printInformation("\t   \n");

        String helloWorld = "Hello World";
        System.out.printf("index of r = %d %n", helloWorld.indexOf('r'));

        System.out.printf("index of World = %d %n", helloWorld.indexOf("World"));

        System.out.printf("index of l = %d %n", helloWorld.indexOf('l'));
        System.out.printf("index of l = %d %n", helloWorld.lastIndexOf('l'));

        System.out.printf("index of l = %d %n", helloWorld.indexOf('l', 3));
        System.out.printf("index of l = %d %n", helloWorld.lastIndexOf('l', 8));

        String helloWorldLower = helloWorld.toLowerCase();
        if (helloWorld.equals(helloWorldLower)) {
            System.out.println("Values match exactly");
        }
        if (helloWorld.equalsIgnoreCase(helloWorldLower)) {
            System.out.println("Values match ignoring case");
        }

        if (helloWorld.startsWith("Hello")) {
            System.out.println("String starts with Hello");
        }
        if (helloWorld.endsWith("World")) {
            System.out.println("String ends with World");
        }
        if (helloWorld.contains("World")) {
            System.out.println("String contains World");
        }

        if (helloWorld.contentEquals("Hello World")) {
            System.out.println("Values match exactly");
        }
    }

    public static void printInformation(String string) {

        int length = string.length();
        System.out.printf("Length = %d %n", length);

        if (string.isEmpty()) {
            System.out.println("String is Empty");
            return;
        }

        if (string.isBlank()) {
            System.out.printf("String is Blank");
        }

        System.out.printf("First char = %c %n", string.charAt(0));

        System.out.printf("Last char = %c %n", string.charAt(length - 1));


    }
}
