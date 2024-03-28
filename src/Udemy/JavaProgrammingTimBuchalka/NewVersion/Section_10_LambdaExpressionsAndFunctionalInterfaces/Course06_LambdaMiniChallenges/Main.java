package CourseCodes.NewSections.Section_14_LambdaExpressionsAndFunctionalInterfaces.Course06_LambdaMiniChallenges;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

//Part-1
/*
                                            Lambda Mini Challenge

        This challenge lecture is going to be a little different, and consist of several small tasks, to help you really
    practice creating and using lambda expressions. I'll assign a task for you to try on your own, and then we can solve
    it together. Then I'll present the next one.
*/
//End-Part-1

public class Main {

    public static void main(String[] args) {

//Part-2
/*
                                                Mini Challenge 1

        Write the following anonymous class that you can see below, as a lambda expression.

            Consumer<String> printTheParts = new Consumer<String>() {

                @Override
                public void accept(String sentence) {
                    String[] parts = sentence.split(" ");
                    for (String part : parts) {
                        System.out.println(part);
                    }
                }
            };

    Try to do it manually on your own, and don't rely on IntelliJ's tools to do it for you. This will help you understand
    lambdas better.
*/
//End-Part-2

        Consumer<String> printWords = new Consumer<String>() {

            @Override
            public void accept(String sentence) {
                String[] parts = sentence.split(" ");
                for (String part : parts) {
                    System.out.println(part);
                }
            }
        };

//Part-3
/*
        I've included the code from a couple of the mini challenge 1, in this class. This is the anonymous class we want
    to change to a lambda. Ok, I'm starting out nearly the same way as the anonymous class, by declaring a Consumer with
    a type argument of String. I'm calling this one, printWordsLambda. But after the assignment, I just have sentence,
    an arrow token, and opening and closing braces. I'm going to copy the code in the accept method above, to this lambda
    expression. This is a multi-line lambda, with opening and closing curly braces, and semi colons terminating the code.
    Multi-line lambdas look a lot more like methods, so in some ways, they're a little easier to understand. Let's execute
    both of these.
*/
//End-Part-3

        Consumer<String> printWordsLambda = sentence -> {
            String[] parts = sentence.split(" ");
            for (String part : parts) {
                System.out.println(part);
            }
        };

//Part-4
/*
        I'll first execute the accept method on the printWords variable, which is the anonymous class. I'll pass a string
    with the text, "let's split this up into an array". Then I'll do the same on the printWordsLambda variable, calling
    accept, and passing the same string. Running that code:

                    Let's
                    split
                    this
                    up
                    into
                    an
                    array
                    Let's
                    split
                    this
                    up
                    into
                    an
                    array

    you can see the words printed on each line in both cases. I want to use another consumer lambda, to make this code a
    bit more concise.
*/
//End-Part-4

        printWords.accept("Let's split this up into an array");
        printWordsLambda.accept("Let's split this up into an array");

//Part-5
/*
        I'll create another lambda expression by copying printWordsLambda, and pasting it at the end of the main method.
    I'll change the name to printWordsForEach. Now, instead of an enhanced for loop, I'm going to use the forEach method
    on list. Do you remember how to do that? First I'll remove the for loop. Now, I'll add the code to call forEach on a
    list backed by my words array parts, which comes back from the split method.

                    Arrays.asList(parts).forEach(s -> System.out.println(s))

    Here, my lambda expression code(in the forEach method) is itself now also using a lambda expression, another Consumer,
    passed to the forEach method.
*/
//End-Part-5

        Consumer<String> printWordsForEach = sentence -> {
            String[] parts = sentence.split(" ");
            Arrays.asList(parts).forEach(s -> System.out.println(s));
        };

//Part-6
/*
        I'll call the accept method on this new lambda with the same sentence as before. That gives us the same output.
    Let's go one better.
*/
//End-Part-6

        printWordsForEach.accept("Let's split this up into an array");

//Part-7
/*
        I'll copy the variable printWordsForEach and it's corresponding code, including the statement below it, and paste
    it as the last statements in my main method. I'll rename it to printWordsConcise, and change the last statement to
    call accept on printWordsConcise. I'll remove the 2 statements in the code block of the lambda, and replace that with
    a single line of code. Lambda expressions are usually best when the code is concise, so I'll make this a single line
    lambda now. Just arrays, as list, passing that sentence.split, then chaining forEach to that, with the usual lambda
    expression to print a string on each line. Since it's a single line lambda, I don't need curly braces. Later, I'll
    show you how to make this even more concise, when I cover method references. This code works the same as all the previous
    examples. Let's move on the next challenge.
*/
//End-Part-7

        Consumer<String> printWordsConcise = sentence -> {
            Arrays.asList(sentence.split(" ")).forEach(s -> System.out.println(s));
        };

        printWordsConcise.accept("Let's split this up into an array");

//Part-9
/*
        First, do you know what type of interface will be the target for this method? The method has to take a String,
    and return a String. You could do this with a Function, with both type arguments as Strings, or maybe you can use a
    UnaryOperator with a single String type argument. Both would work, because they take a string, and return a string,
    but I'm going to go with the Function here, and 2 Strings. That's named everySecondChar, assigned to source, followed
    by an arrow token, opening bracket. That's my variable, and I'm assigning it a lambda expression with a code block.
    Now I want to copy and paste the code, from the method which was the method in the challenge info. And that's it, I
    now have a lambda expression that can replace that method. Alternately, I can swap out Function, declared with 2 Strings
    type arguments, to UnaryOperator with a type argument of String. Let me do that. Remember that a UnaryOperator is
    just a special type of function, where all type arguments are the same reference type, String in this case. All right,
    so that's challenge 2 completed. Let's move on the next challenge.
*/
//End-Part-9

/*
        Function<String, String> everySecondChar = source -> {
            StringBuilder returnVal = new StringBuilder();
            for (int i = 0; i < source.length(); i++) {
                if (i % 2 == 1) {
                    returnVal.append(source.charAt(i));
                }
            }
            return returnVal.toString();
        };
*/

        UnaryOperator<String> everySecondChar = source -> {
            StringBuilder returnVal = new StringBuilder();
            for (int i = 0; i < source.length(); i++) {
                if (i % 2 == 1) {
                    returnVal.append(source.charAt(i));
                }
            }
            return returnVal.toString();
        };

//Part-10
/*
                                                Mini Challenge 3

        The lambda expression we created in Challenge 2 doesn't do anything right now. I want you to write the code to
    execute this lambda expression's functional method, using 1234567890, as the argument to that method, and print the
    result out.

            UnaryOperator<String> everySecondChar = source -> {
                StringBuilder returnVal = new StringBuilder();
                for (int i = 0; i < source.length(); i++) {
                    if (i % 2 == 1) {
                        returnVal.append(source.charAt(i));
                    }
                }
                return returnVal.toString();
            };

        Firstly, I'm going to do a println statement. In that, I'm going to call the apply method on my UnaryOperator,
    everySecondChar. And I'll pass that a string that has the digits 1 through 9, plus 0 in it. Do you remember how you
    can figure out what method to call? Don't forget that useful gutter icon in IntelliJ, if you forget the name of each
    interface's functional method. Clicking on that, you can see that it shows me the apply method, that takes 1 argument,
    and returns some results. And if I run that:

                ---(same)
                24680

    you can now see the output, in this case, 24680. Now let's move on to the next challenge.
*/
//End-Part-10

        System.out.println(everySecondChar.apply("1234567890"));

//Part-12
/*
                                                Mini Challenge 5

        Call the method you created from Challenge 4, passing the lambda variable we created earlier, and the string 1234567890,
    then print the result returned from the method.

        Let's call that method that took a lambda function as an argument, and print out the result in the main method.
    Running this:

                ---(same)
                24680

    you can see the same result I got previously, 24680. All right, so let's move on to the next challenge.
*/
//End-Part-12

        String result = everySecondCharacter(everySecondChar, "1234567890");
        System.out.println(result);

//Part-13
/*
                                                Mini Challenge 6

        Write a lambda expression that is declared with the Supplier interface. This lambda should return the String,
    "I love Java", and assign it to a variable called, iLoveJava.

        I need to write a lambda expression that has the reference type of the Java supplier interface. Supplier, which
    will have a String type, and my variable, I'll call "ILoveJava". I'll assign that a lambda, and remember, a supplier
    doesn't have any arguments, so just parentheses to the left of the arrow token. On the right, I can have any kind of
    String expression, but the challenge was to make it the text, "I love Java!". Since the lambda body here is a single
    statement, we don't have to include the return keyword, because it's implied. But if I wanted to include it for clarity,
    I could change this slightly to do that. Let me create an ILoveJava2 variable, and show you this, which has a code
    block, and a return statement. Again, when you use a return statement, you have to use curly braces to start a code
    block, then we also have to include the semicolon at the end of each statement within the body. We still need a semi
    colon at the end of the code block, after the closing curly brace to complete the statement, the lambda expression
    assignment statement. Even though I have a code block defined, you can see I still have it all in a single one line
    statement. Remember that extra lines are really just optional, recommended for readability. Usually I'd discourage
    you from writing an entire code block on a single line. But for lambda expressions sometimes being concise might
    actually make it more readable. That was challenge 6, so I have one more mini challenge for you in this lecture.
*/
//End-Part-13

        Supplier<String> iLoveJava = () -> "I love Java!";
        Supplier<String> iLoveJava2 = () -> {return "I love Java!";};

//Part-14
/*
                                                Mini Challenge 7

        As with the Function example, the Supplier lambda won't do anything until we use it. Remember, lambdas represent
    deferred execution of snippets of code. Use this Supplier to assign a String, "I love Java", to a variable called
    supplierResult. Print that variable to the console.

        I'll just call println passing it "ILoveJava", and then ".get". I'll just run that to make sure it works. For good
    measure, I'll copy and paste that last line, and change ILoveJava to ILoveJava2 there. Running that:

            ---(same)
            I love Java!
            I lova Java!

    I get the second statement from my second string supplier, "I love Java" printed again. Ok, that's it for the mini
    challenges.
*/
//End-Part-14

        System.out.println(iLoveJava.get());
        System.out.println(iLoveJava2.get());
    }

//Part-8
/*
                                                Mini Challenge 2

        Write the following method as a lambda expression.

            public static String everySecondChar(String source) {

                StringBuilder returnVal = new StringBuilder();
                for (int i = 0; i < source.length(); i++) {
                    if (i % 2 == 1) {
                        returnVal.append(source.charAt(i));
                    }
                }
                return returnVal.toString();
            }

    In other words, create a variable, using a type that makes sense for this method. Don't worry about executing it though.
*/
//End-Part-8

    public static String everySecondChar(String source) {

        StringBuilder returnVal = new StringBuilder();
        for (int i = 0; i < source.length(); i++) {
            if (i % 2 == 1) {
                returnVal.append(source.charAt(i));
            }
        }
        return returnVal.toString();
    }

//Part-11
/*
                                                Mini Challenge 4

        Instead of executing this function directly, like the one that we've just created, suppose that we want to pass
    it to a method. Now, your task is to write another method on your class, called everySecondCharacter. This method
    should accept a Function, or UnaryOperator, as a parameter, as well as a second parameter that lets us pass, "1234567890".
    In other words, don't hard code that string in your method code. The method code should execute the functional method
    on the first argument, passing it the value of the string passed, from the enclosing method. It should return the
    result of the call to the functional method.

        I'll make it public and static and return a string, and its name is everySecondCharacter. My arguments are a functional
    interface, so I'll put Function with 2 string type arguments, and that parameter's going to just be called func, and
    the second parameter is a String source. This method will return the result of calling apply on the first method argument,
    passing it the second method argument, source. You can see, I'm setting up the first method parameter to take a Function,
    specifically typed to 2 Strings. Inside this method, I'm calling the apply method, passing it the String we want to
    process, in this case, which I'm using instead of hard coding that value. I think a really good question some of you
    might be asking at this point is, why are we doing this? We can simply call the apply method after the declaration
    of the function lambda expression in the main method, as we've done already. It's important to understand how to

        1) Declare lambda variables, or pass lambdas directly to methods that are targets. I show 2 examples here.

    Local Variable Declaration                                              Method argument
    Function<String, String> myFunction = s -> s.concat(" " + suffix);      list.forEach(s -> System.out.println(s));

    On the flip side, you'll want to understand when and how to

        2) Create methods that can be targets for lambda expressions.

    In this challenge, I'm asking you to think about this second objective, creating a method that's a target for a lambda.
    I can pass any Function to this method, which could do anything to a String and pass it back. When designing your code,
    you'll sometimes want to consider writing a method, that can be a target for a lambda expression, like this one. This
    allows your code to be highly extensible to client, and support unknown future customizations. I've introduced you
    to methods like forEach, removeIf, and replaceAll on List, that give you a lot of flexibility working with your own
    lists, because they're designed this way. All right, so that's challenge 4 completed.
*/
//End-Part-11

    public static String everySecondCharacter(Function<String, String> func, String source) {
        return func.apply(source);
    }

}
