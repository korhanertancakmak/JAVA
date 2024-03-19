package CourseCodes.NewSections.Section_09_Arrays.Course04_VariableArgumentsVarargs;

public class Main {
    public static void main(String[] args) {

/*
Part-1
                                         Variable Arguments (Varargs)

        Notice here that the parameter to the main method, is an array of String. This means we can pass an array of Strings
    to this method, when it's called. Or, if we use this method as the entry point to our application, we can pass data
    on the command line to this method. Up until now, I've only shown you this particular method signature. But this signature
    can be written in a slightly different way.

        public static void main(String... args) {
        }

    We can replace the brackets after the String type, which we know tells us this method will take an array of String.
    And we can instead replace that with three periods. This is a special designation for Java, that means, Java will take
    zero, one, or many Strings, as arguments to this method, and create an array with which to process them, in the method.
    The array will be called args, and be of type String. So what's the difference then? The difference is minor within
    the method body, but significant to the code that calls the method. Let's review this in code.

End-Part-1
*/
        System.out.println("Hello World again");

/*
Part-2
        I'm going to change the main method, to use the varargs version for the parameter: "main(String... args)". And let's
    just print something out and confirm that works. I'll print the string literal, "hello world" again and running that,
    Everything works as before, so this change isn't really evident to us, using the main method, as the entry point to
    the application. Let's create our own method, that will print out an array of Strings, each on its own line.
End-Part-2
*/

        String[] splitStrings = "Hello World again".split(" ");
        printText(splitStrings);

/*
Part-4
        The split method splits a String into a string array, by whatever delimiter you tell it to use. In this code, we
    have a string literal, which is just the "Hello World again" sentence, and we're immediately calling the split method,
    a String instance method. This method, takes a String, which can support regular expressions, which tells the method
    what the String should be split on. In this case, we're going to split by the spaces in the sentence. This means this
    String will get split into pieces, and the method returns an array of the parts, an array of String. You might remember,
    we learned about the join method on String, which concatenated multiple Strings together, into 1 String. The split
    method has the opposite functionality. Let's run this code,

            Hello World again
            Hello
            World
            again

    You see printed out on separate lines, using our method. Let's change our method that prints this array, from accepting
    an array, to accepting variable arguments for the String. And all we need to do is replace "[]" with "...".  And if
    we run that,

            Hello World again
            Hello
            World
            again

    we get the exact same result. So what's the big deal? The variable arguments parameter, gives us a lot more options,
    when we execute the method. Let's look at these. First, we already know we can pass an array to this method. But what's
    really neat, is we can pass a simple String if we want to:
End-Part-4
*/

        System.out.println("_".repeat(20));
        printText("Hello");

/*
Part-5
        First, I'll just include a separating line here, for clarity, when we look at the output. Now notice here though,
    we're calling that same method, but we're only passing a single String. We're not passing an array at all. This code
    compiles and runs,

            Hello World again
            Hello
            World
            again
            ____________________
            Hello

    Instead of having to overload this method, to take an array of String, a single string, 2 Strings, or whatever, we have
    a single method declared. This feature lets us create 1 method, to support multiple ways, of calling this method. In
    fact, we can call this method, with no args at all, so let's copy and paste those same 2 lines, but this time, we
    won't pass anything as an argument to the method.
End-Part-5
*/

        System.out.println("_".repeat(20));
        printText();

/*
Part-6
        And this code runs too. Now there are some restrictions, on when you can use variable arguments as a method parameter.
    There can be only one variable argument in a method. The variable argument must be the last argument. Those rules are
    pretty easy to remember. You'll find that Java uses variable arguments, in many methods contained in their library
    classes. In fact, the String.join method itself, uses a variable argument, as its last argument, which is why the
    delimiter needs to be specified first. We showed you how to use this method with multiple strings, but it can also be
    used with an array of String, so let's look at that:
End-Part-6
*/

        String[] sArray = {"first", "second", "third", "fourth", "fifth"};
        System.out.println(String.join(",", sArray));

/*
Part-7
        I'm using an anonymous array initializer. It's called anonymous because the type isn't included, but Java can figure
    it out because it's being used in a declaration statement. Next, we print out the result of the String.join method,
    with a comma as the first value, and if we run that,

            first,second,third,fourth,fifth

    and we can see the output, printing a single String of our elements, comma delimited. So that was variable arguments,
    and using arrays as method arguments. Whichever you choose, will depend on the other parameters in the method arguments
    list, as well as whether you want your users, to have the ability to pass a variable list of elements, vs. just passing
    an array of elements.
End-Part-7
*/
    }
    private static void printText(String... textList) {

        for (String t : textList) {
            System.out.println(t);
        }
    }

/*
Part-3
        Using for each, we will cycle through the text list array. And print t. This method takes an array of Strings, and
    prints each String on its own line, using an enhanced for loop. I'll call this method from the main method. I'll come
    back and change the method signature in a bit.
End-Part-3
*/
}
