package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_10_LambdaExpressionsAndFunctionalInterfaces.Course10_MethodReferenceChallenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.UnaryOperator;

//Part-1
/*
                                            Method and Lambda Challenge

        At the end of the last lecture, I introduced you to the transform method on String. In this challenge, I want you
    to explore what you can do with that method. First, create an array of names, in mixed case, as you did in the Lambda
    Expression Challenge. Create a list of Function interfaces, or alternately UnaryOperator, which will contain all the
    operations you want executed on each name in your array. Do something similar to what we did in the Lambda Expression
    challenge:

        - Make each name upper case,
        - Add a random middle initial,
        - Add a last name, which should be the reverse of the first.

    In addition to this, add some custom transformations of your own. Use a mix of lambda expressions, and method references.
    Create a method that takes the name array, and the function list, and applies each function to each name, using the
    transform method on String, to do this. All changes should be applied to the original array. Make sure you explore
    as many transformations as you can, trying as many different types of method references as you can think of.
*/
//End-Part-1

public class Main {

//Part-2
/*
        I'm going to add a private static Random field to support different types of randomization, assigning it a new
    instance of the random class. You've seen me do this several times now. Next, I want to set up the method that will
    execute my list of functions, against an array of names.
*/
//End-Part-2

    private static Random random = new Random();

//Part-13
/*
        Ok, for the last method reference type, I'll set up a private person record in my Main class with one field, a
    String first. I want to add a public method to this record, called last, that takes a string. It returns the first
    field with a space concatenated to it. This is followed by a sub string of the argument passed, which represent the
    first name of that argument, or first string in a space delimited string.
*/
//End-Part-13

    private record Person(String first) {

        public String last(String s) {
            return first + " " + s.substring(0, s.indexOf(" "));
        }

    }

    public static void main(String[] args) {

//Part-4
/*
        I'll call my local variable names and use an array initializer as names. Then, I need a list of functions. I'll
    set this up, so List, and set the type argument to UnaryOperator, which also needs a type argument, and that's String,
    so this declaration looks a little ugly. I'll call my list "list", and assign it a new ArrayList, passing a call to
    the List.Of method.
*/
//End-Part-4

        String[] names = {"Anna", "Bob", "Cameron", "Donald", "Eva", "Francis"};

//Part-14
/*
        I'll set up a Person in the main method, and pass Tim as the name.
*/
//End-Part-14

        Person tim = new Person("Tim");

//Part-5
/*
        First, I want to first make the names all uppercase, as I did in the lambda expression challenge, so what does
    this look like as a method reference? It's "String::toUpperCase". This may look like a static method reference, but
    hopefully you recognize that it's not. It's an instance method, used on the unbounded retriever. In other words, the
    method will get called on the first argument.
*/
//End-Part-5

//Part-8
/*
        I'll need a comma after the first method reference, then I'll do a method reference. This should look familiar,
    because I used almost the exact same one in the lambda expression challenge.

                                s += " " + getRandomChar('D', 'M') + "."

    But this time I want a middle initial between D and M, and I'll append a period to that initial. And running that code
    now,

            [ANNA E., BOB I., CAMERON F., DONALD E., EVA K., FRANCİS E.]

    I get a random letter between D and M for all of my names. The selection of this letter range is just arbitrary. The
    third thing we did in the Lambda Expression Challenge was to take the first name, and reverse the letters, and add
    that as the last name. How should we do that here?
*/
//End-Part-8

//Part-10
/*
        And I'm simply going to call Main::reverse. This will reverse my entire string which isn't quite what I want to
    do, but I did want to talk about this kind of method reference. What do you think? What kind is this? Well, this one
    is really a static method reference, and the string is passed as the argument to this reverse method. Running the code
    with this extra function,

            [.J ANNA, .M BOB, .H NOREMAC, .M DLANOD, .K AVE, .F SİCNARF]

    you can see the entire name was reversed. Since that's not what I want, I'll actually insert a lambda expression before
    that last method reference.

                                s -> s += " " + reverse(s, 0, s.indexOf(" "))

    Here, this means I'm really passing just the first name to this reverse method, and that will get reversed and concatenated
    to the current name. Running this code:

            ---(same)
            [ANNA J. ANNA, BOB M. BOB, CAMERON H. NOREMAC, DONALD M. DLANOD, EVA K. AVE, FRANCİS F. SİCNARF]
            [ANNA .J ANNA, BOB .M BOB, CAMERON .H NOREMAC, DONALD .M DLANOD, EVA .K AVE, FRANCİS .F SİCNARF]

    you can now see on my 3rd line of output, the effect of this method, which again did the same thing we did in the lambda
    expression challenge. Notice the last bit of output, the fully reversed name, look almost the same as unreversed, only
    the period and middle initial swapped.
*/
//End-Part-10

//Part-11
/*
        Ok, so I've include 2 of the 4 types of method references. Is there a way to use the other 2? Well, what happens
    if I had a String constructor method reference? I'll add a comma after the "Main::reverse", and then use a method
    reference for a new String.

                                        String::new

    This compiles, but how does that work? Let me add the corresponding lambda expression, directly below it. Again, I
    need to add a comma after the last expression first.

                                        s -> new String(s)

    Both of these statements, return a new String, which is constructed using the current text value. Running this code,

            [ANNA .J ANNA, BOB .M BOB, CAMERON .H NOREMAC, DONALD .M DLANOD, EVA .K AVE, FRANCİS .F SİCNARF]
            [ANNA .J ANNA, BOB .M BOB, CAMERON .H NOREMAC, DONALD .M DLANOD, EVA .K AVE, FRANCİS .F SİCNARF]

    you can't really see anything change, since we're really setting each name to a new String instance passing it its
    own text value.
*/
//End-Part-11

//Part-12
/*
        Instead of new, I can use valueOf with the same results, so another comma after last expression, then String::valueOf.

            [ANNA .J ANNA, BOB .M BOB, CAMERON .H NOREMAC, DONALD .M DLANOD, EVA .K AVE, FRANCİS .F SİCNARF]

    The results in all of these cases are new String instances, but with text values, the same as before. These aren't
    particularly useful, though I have more flexibility with the lambda expression. I could for example, add something
    to the name as I create a new string "Howdy", and running that

            [Howdy ANNA .K ANNA, Howdy BOB .J BOB, Howdy CAMERON .D NOREMAC, Howdy DONALD .K DLANOD, Howdy EVA .L AVE, Howdy FRANCİS .K SİCNARF]
            [Howdy ANNA .K ANNA, Howdy BOB .J BOB, Howdy CAMERON .D NOREMAC, Howdy DONALD .K DLANOD, Howdy EVA .L AVE, Howdy FRANCİS .K SİCNARF]

    you'll see the last 2 outputs have "Howdy" there. You've probably noticed that IntelliJ isn't really happy with me
    using new String, and wants me to just use the String literal added to s. I'm not going to make that change. I included
    this line to help you understand what the 2 methods, "String::new" and "String::valueOf" look like as a lambda expression
    using constructor code, so now I'll just comment this line out.
*/
//End-Part-12

//Part-15
/*
        And now, I can demonstrate the 4th type of method reference, an instance method called on an instance, using a
    bounded receiver. This means the instance is coming from code external to the reference itself. Running that code,

            [Tim ANNA, Tim BOB, Tim CAMERON, Tim DONALD, Tim EVA, Tim FRANCİS]

    you can see my name, with the first part of all the other names, in that last output. This was the result of executing
    last on the tim record, but passing each name to the last method, on the tim instance. When you're using an instance,
    you can use an expression on the left side of the method reference. Let me add an example of that.

                                    (new Person("MARY"))::last

    Running that:

            [MARY Tim, MARY Tim, MARY Tim, MARY Tim, MARY Tim, MARY Tim]

    and that gives me records with "MARY" as the first name, and tim as the last name. There's really no end to the number
    and type of method references, and lambda expressions, you could add here. Would you ever code something this way?
    Probably not, but I hope you can get creative with it. If you want to execute a lot of functionality in a sort of
    ordered task list way, like this, you'd probably do it using functional interface method chaining, or with Streams.
*/
//End-Part-15

        List<UnaryOperator<String>> list = new ArrayList<>(List.of(
                String::toUpperCase,
                s -> s += " " + getRandomChar('D', 'M') + ".",
                s -> s += " " + reverse(s, 0, s.indexOf(" ")),
                Main::reverse,
                String::new,
                //s -> new String(s),
                //s -> new String("Howdy " + s),
                String::valueOf,
                tim::last,
                (new Person("MARY"))::last
        ));

//Part-6
/*
        And now, all I have to do is call my applyChanges method, passing my names array, and my list of functions. I'll
    run this, and see what I get.

            [ANNA, BOB, CAMERON, DONALD, EVA, FRANCİS]

    You can see my names, they're transformed to uppercase with that method reference. My first operation worked succesfully
    on my names array. Now, I want to add a random character, as a middle initial.
*/
//End-Part-6

        applyChanges(names, list);
    }

//Part-3
/*
        This will be a private static method, named applyChanges, that has a void return type. The first parameter will
    be a String array, and the second will be a List of typed UnaryOperators, called string functions. This list will
    consist of instances which implement the Unary Operator interface, and accepts Strings. I said in the last lecture that
    the transform method will return other values, but here I'm going to use it with just Strings being returned. Using
    a UnaryOperator is basically the same as using a Function with Strings as both type arguments. Now I'll add the main
    parts of this method.

         First, I create a list backed by an array, my names array. Then I'm looping through the list of functions which
    were passed as the second argument of this method. These are the operations that will be done on each name in the array.
    I'm taking advantage of the replaceAll method on List here, which takes a UnaryOperator, and I simply pass this a lambda
    expression. This lambda in turn calls transform on every name string, and executes the function that was in the list
    element. After this, I print out the array values, and because I'm using a list backed by an array, my array is really
    changing during these transformations. Ok, so I want to test that out, but first I need some names in an array.
*/
//End-Part-3

    private static void applyChanges(String[] names, List<UnaryOperator<String>> stringFunctions) {

        List<String> backedByArray = Arrays.asList(names);
        for (var function : stringFunctions) {
            backedByArray.replaceAll(s -> s.transform(function));
            System.out.println(Arrays.toString(names));
        }
    }

//Part-7
/*
        I'm going to create a method, similar to the one I used in the lambda expression challenge, calling it getRandomChar.
    This is almost the same method from the previous challenge. It uses a range of characters, specified by the user, to
    get a new random character. And now, I'll add a lambda expression to my list in the main method, that uses this method.
*/
//End-Part-7

    private static char getRandomChar(char startChar, char endChar) {
        return (char) random.nextInt((int) startChar, (int) endChar + 1);
    }

//Part-9
/*
        Well, I'm going to create 2 overloaded methods, both private and static, both named reverse, and both return a
    String. The first takes a String, as well as a starting index, and an ending index. It returns a new StringBuilder,
    which gets passed the substring, derived by the starting and ending indices. The reverse method is chained to that,
    and then the toString method. The overloaded version will reverse the entire string, so it only has a single string
    parameter, and it in turn calls the other version, passing 0 and the length of the string, as arguments to that. Now,
    I'll add a method reference in my list in the main method, after adding a comma after the previous expression.
*/
//End-Part-9

    private static String reverse(String s) {
        return reverse(s, 0, s.length());
    }

    private static String reverse(String s, int start, int end) {
        return new StringBuilder(s.substring(start, end)).reverse().toString();
    }

}
