package CourseCodes.NewSections.Section_14_LambdaExpressionsAndFunctionalInterfaces.Course07_LambdaExpressionChallenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

//Part-1
/*
                                            Lambda Expression Challenge

        This challenge, is to exercise your skills with Arrays, ArrayLists, and the methods on these, which are targets
    for lambda expressions. First, I want you to create an array of String, which is populated with first names, in mixed
    case. Include at least one name, which is spelled the same backwards, and forwards, like Bob, or Anna. Use "Arrays.setAll",
    or "List.replaceAll", to change the values in this array. If you use List methods, you'll need "a list backed by the
    array", so that changes get made to the initial array. In other words, I don't want you to make a copy of the array
    elements for the first part of this challenge.

        Using one of those two methods, perform the following functions on the elements in the array, with appropriate
    lambda expressions.

        - Transform names to all uppercase.
        - Add a randomly generated middle initial, and include a period.
        - Add a last name that is the reverse of the first name.

    Print your array or the array elements, after each change, using the forEach method, at least once. Finally, create
    a new modifiable ArrayList from your names array, removing any names where the last name equals the first name. Use
    removeIf with a lambda expression to do this last operation. You can create helper methods as needed to facilitate
    some of this functionality. You can use single line, or multi-line lambda expressions. Ignore IntelliJ's warnings
    and hints which prompt you to replace any lambda expressions with method references for now. We'll be reviewing method
    references in the next lecture.
*/
//End-Part-1

public class Main {

//Part-2
/*
        I'm going to be using the Random class to facilitate my random middle initial, so I'm first going to set that up
    as a private static variable on my Main class. I'm going to call it random, and assign it a new instance of the Random
    class. If you have some previous experience in Java, or you've explored solutions on the internet, you might have
    found examples using Math.random. I prefer to start with this Random class, because I think it's a bit easier to use
    for new students. In addition, JDK 17 brought some enhancements to the Random class which make it even easier to use.
    It now implements an interface called RandomGenerator with several default methods, one of which is nextInt which
    both a lower bound and an upper bound. Prior to JDK 17, this method wasn't available. In an upcoming section of the
    course, I'll be thoroughly reviewing both the Math and Random classes. Getting back to this code now, I want to create
    my array of Strings, called names. I'll use an array initializer with the name values. These could be any names, but
    you want 1 of them to be a name that is spelled the same backwards, or forwards, like Bob or Anna. Now, the challenge
    said you could use setAll on the java.util.Arrays class, or replaceAll on List to perform some of these operations.
*/
//End-Part-2

    private static Random random = new Random();

    public static void main(String[] args) {

        String[] names = {"Anna", "Bob", "Carole", "David", "Ed", "Fred", "Gary"};

//Part-3
/*
        For the first one, I'll demonstrate an example of using setAll. In truth, it's more instinctive to use setAll for
    indexed elements when the index is used in the function, but it can be used to retrieve the element in the index, and
    operate on that. Let me show you an example of that. This lambda in the "Arrays.setAll" expression has "i" as a parameter,
    and returns the element in the names array at that index, but in uppercase. I'll print a heading and the names array.
    If I run this code:

                --> Transform to Uppercase
                [ANNA, BOB, CAROLE, DAVİD, ED, FRED, GARY]

    you can see it changes all the names in the array to uppercase, so that's the first function in the challenge done.
    I used "Arrays.toString" to print this out here, because I wanted to be very clear that the original array was changed.
    Another approach to manipulating an array of elements is to wrap it in a list, and use the list methods. The list
    method, replaceAll, is conceptually similar to the enhanced for loop, where the variable being looped over is the
    element, not the index value.
*/
//End-Part-3

        Arrays.setAll(names, i -> names[i].toUpperCase());
        System.out.println("--> Transform to Uppercase");
        System.out.println(Arrays.toString(names));

//Part-4
/*
        I'll create a list backed by an array next, so List of String, named backedByArray, and that gets assigned arrays
    as list, passing it my names array. Remember that creating a list like this gives us the ability to treat an array
    like a list, except for those operations that change the size, like remove or add. This is fun and handy for a lot of
    reasons, especially as we move into lambda expressions and streams. Before I go any further, I want to add 2 private
    static methods to my Main method to make my lambdas a little easier to read.
*/
//End-Part-4

        List<String> backedByArray = Arrays.asList(names);

//Part-7
/*
        The first is to add a middle initial. I'll start by invoking replaceAll, and define a lambda, that has a single
    parameter, s, a string in this case. I'm going to return a string, concatenating the current name to the result of
    calling my getRandomChar method, with A and D as arguments, and a period added to the initial. And I'll just print
    the names again, after a descriptive header. If I run that:

                -> Add random middle initial
                [ANNA A., BOB B., CAROLE D., DAVİD C., ED B., FRED C., GARY C.]

    I get some randomized initials as you can see with my uppercase names. Next, I want to add a last name which is the
    first name reversed. Again, I'll call replace all on backed by array. This time, my lambda expression will concatenate
    the name with the result of the getReversedName method I created earlier. I want to pass only the first name, excluding
    the initial. I'll print that out with a header first.
*/
//End-Part-7

        backedByArray.replaceAll(s -> s += " " + getRandomChar('A', 'D') + ".");
        System.out.println("--> Add random middle initial");
        System.out.println(Arrays.toString(names));

//Part-8
/*
        Next, I want to add a last name which is the first name reversed. Again, I'll call replace all on backed by array.
    This time, my lambda expression will concatenate the name with the result of the getReversedName method I created
    earlier. I want to pass only the first name, excluding the initial. I'll print that out with a header first. Ok, so
    here, I do something similar, concatenating the result of my getReversedName method to the current name with a preceding
    space. Notice that I'm passing only the first name, and not the initial to that method, and I do this by splitting
    the element name on spaces, knowing that the first element is the first name. Next, instead of using toString on Arrays,
    to print my array out, I want to use the forEach method. You can use this method on any array, by first wrapping it
    in a list backed by an array, so I'll do that, rather than simply calling it from the local variable, just to show
    you how you might do this with any array. If you want to leverage the forEach method to do anything with your array
    elements, this is a simple way to do it, by chaining the forEach method, to the Arrays asList method. One of the
    objectives of this challenge was to use the forEach method at least once to print the elements. If you used it directly
    on your local variable, that's perfectly fine. I just wanted you to see, how to use the forEach method on elements
    on an array in this manner. If I run this:

                --> Add reversed name as last name
                ANNA A. ANNA
                BOB B. BOB
                CAROLE D. ELORAC
                DAVİD C. DİVAD
                ED B. DE
                FRED C. DERF
                GARY C. YRAG

    I get my names, including last names as well as middle initials, and now they're listed one on each line, because I
    used println in the lambda expression.
*/
//End-Part-8

        backedByArray.replaceAll(s -> s += " " + getReversedName(s.split(" ")[0]));
        System.out.println("--> Add reversed name as last name");
        Arrays.asList(names).forEach(s -> System.out.println(s));

//Part-9
/*
        The final part of the challenge was to remove names that have the first name equal to the last. In my example here,
    that's both Anna and Bob. Now, a list backed by an array will throw an exception if you try to remove elements with
    removeIf statement. This means that now, I want a List, that's not backed by the array. So I'll create a newList, and
    assign a new ArrayList to it, passing it List.of with names. I could've just passed my local variable, the list backed
    by an array, to that constructor, which would also result in a list with new members. There are usually multiple ways
    to do one thing and not really a perfect right way.
*/
//End-Part-9

        List<String> newList = new ArrayList<>(List.of(names));

//Part-10
/*
        Now that I have a copy of the names, I can execute the removeIf method on this list. I'll pass it a lambda expression
    with a predicate.

                s -> s.substring(0, s.indexOf(" ")).equals(s.substring(s.lastIndexOf(" ") + 1))

    Remember a predicate evaluates to a true or false result. Here, I'll compare the first part of the name, up to the
    first space with the last part of the name from the last space to the end of the string. Any name where this condition
    is true, gets removed from the list. I'll again use forEach to print each name on its own line. Running that code:

                --> Remove names where first = last
                CAROLE D. ELORAC
                DAVİD C. DİVAD
                ED B. DE
                FRED C. DERF
                GARY C. YRAG

    you can see my copied and altered list printed out, and Bob and Anna are no longer in it. Ok, so this code meets all
    the requirements of the challenge.
*/
//End-Part-10

//        newList.removeIf(s -> s.substring(0, s.indexOf(" ")).equals(s.substring(s.lastIndexOf(" ") + 1)));

//Part-11
/*
        Maybe you used multi-line lambda expressions to do some of this work, and that's also perfectly valid. Let me
    demonstrate an example of that now, to reinforce a couple of points. I'll change my removeIf statement to be a multi-line
    lambda. First I'll copy what I have, and comment out the first occurrence. I'll next add an opening curly brace after
    the arrow token. I'll replace the first closing parentheses on the third line with a closing curly brace. When you use
    a code block in a lambda expression, you need semi-colons after each statement, this is easy to forget. You also need
    a return statement, if the target method has a return type. The return statement is implied for a lambda expression
    that doesn't have a code block. I'm going to set up some local variables next, the first will be set to the first name,
    and then last gets assigned the second part of the statement. Now, I've got 2 variables, but I need to return a boolean
    which tests if first equals last. Because I'm using a code block, I need to specify the return statement explicitly,
    so I'll return first.equals(last). That's a multi-line lambda expression. If I run that, I get the same results as
    before. A lot of developers, including myself, enjoy using the most concise way to write code, which isn't always
    the most readable, or even the most efficient in some cases. If you're dealing with a large amount of transformations,
    you'll want to weigh the value of concision against efficiency, and so on. It's always a bit of a balancing act.
*/
//End-Part-11

        newList.removeIf(s -> {
            String first = s.substring(0, s.indexOf(" "));
            String last = s.substring(s.lastIndexOf(" ") + 1);
            return first.equals(last);
        });
        System.out.println("--> Remove names where first = last");
        newList.forEach(s -> System.out.println(s));

    }

//Part-5
/*
        The first, getRandomChar, will take a starting character and an ending character as parameters. The nextInt method
    takes an inclusive lower bound and an exclusive upper bound to determine the range of random numbers to select from.
    I cast the characters to int, but I want to add 1 to the end character so that I can make the character passed to
    this method be included. Finally, this method returns an int and you'll remember I can cast a positive int to a char,
    which I do here. What this method does, if I pass A and E, for example, I'll only get letters that are between A and
    E, including E. This is a one-liner, and I could have included it in my lambda expression. But in my opinion, I liked
    the way my code looked when I made this in a simple method. That's not a technical reason, but sometimes aesthetics
    and readability matter.
*/
//End-Part-5

    public static char getRandomChar(char startChar, char endChar) {
        return (char) random.nextInt((int) startChar, (int) endChar + 1);
    }

//Part-6
/*
        Ok, so the second method I want to add, reverses the characters in a String. Again this is an one liner. I'm
    calling the method getReversedName, and it's got a string argument. I'll use a method on string builder to do the work
    for me, so I'll create a new string builder, passing it my method argument, firstName. I'll chain a call to the reverse
    method on that, and then chain two string after that. Ok, those are the 2 helper methods. Again, there are lots of
    ways to get a random character, or reverse characters in a String. I like the simplicity and flexibility of these 2
    options, but maybe you've found others you like as much, or more. Ok, I want to get back to the main method, and use
    my list backed by an array, in conjunction with these methods to achieve the next couple of tasks.
*/
//End-Part-6

    private static String getReversedName(String firstName) {
        return new StringBuilder(firstName).reverse().toString();
    }

}
