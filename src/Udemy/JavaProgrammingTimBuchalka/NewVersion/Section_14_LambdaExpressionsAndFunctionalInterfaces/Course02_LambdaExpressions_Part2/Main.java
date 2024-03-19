package CourseCodes.NewSections.Section_14_LambdaExpressionsAndFunctionalInterfaces.Course02_LambdaExpressions_Part2;

import java.util.ArrayList;
import java.util.List;

//Part-1
/*
        In the last lecture, I talked about how the functional interface is the framework that lets a lambda expression
    be used. Lambda expressions are also called lambdas for short. Many of Java's classes, use functional interfaces in
    their method signatures, which allows us to pass lambdas as arguments to them. You'll soon discover that lambdas will
    reduce the amount of code you write. Once you get the hang of using lambdas, I think you'll really going to appreciate
    this feature of Java. In an upcoming lecture, I'm going to cover many of Java's built-in functional interfaces, but
    in this lecture, I'll introduce just one for now.

                                                The Consumer Interface

        The Consumer interface is in the java.util.function package. It has one abstract method, that takes a single
    argument, and doesn't return anything.

                                void accept(T t)

    This doesn't seem like a very interesting interface at first, but let's see what this means in practice, as far as
    the lambda expressions we can use with it.
*/
//End-Part-1
public class Main {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>(List.of(
                "alpha", "bravo", "charlie", "delta"));

        for (String s : list) {
            System.out.println(s);
        }

//Part-2
/*
        This isn't new, but you may remember that this felt like a really nice improvement over the traditional for loop,
    which requires us to use indexing. But actually, the ArrayList comes with an easier way, which I haven't yet discussed.
    Let me show you how you'd do this, using a method that's a default method on the Iterable interface, which List inherits,
    called forEach. This method takes a lambda expression, a Consumer type, which I just talked about, as its argument.
    First I'll add a separator line, then I'll invoke forEach on my list, and pass it a lambda expression.
*/
//End-Part-2

        System.out.println("-------");
        //list.forEach((s) -> System.out.println(s));
        //list.forEach((myString) -> System.out.println(myString));
        //list.forEach((String myString) -> System.out.println(myString));
        //list.forEach(myString -> System.out.println(myString));
        list.forEach((var myString) -> System.out.println(myString));

//Part-3
/*
        My lambda expression in this case has one parameter, which I'm simply calling s. This parameter is the element
    that's being iterated over, so much like I did with the enhanced for loop, I'm simply calling this variable s. Then
    I have the arraw "->" token and call "System.out.println", passing that method s, my lambda expression parameter. And
    running that,

                alpha
                bravo
                charlie
                delta
                -------
                alpha
                bravo
                charlie
                delta

    you can see my strings are printed out, one on each line, just as before. How does it know "s" is the element in the
    list? It's part of the mechanism of that forEach method. If you're curious what the underlying code looks like, you
    can control click on the forEach method, or alternately, right click to show the menu bar, select "Go to", and then
    select Declarations or usages. This will open the Iterable interface to that method, and you can see, the underlying
    implementation is

                        default void forEach(Consumer<? super T> action) {
                            Objects.requireNonNull(action);
                            for (T t : this) {
                                action.accept(t);
                            }
                        }

    just an enhanced for loop after all, that calls the accept method on the argument passed. Each element is passed as
    an argument to that method. Hopefully that helped you understand that this "forEach" method is really looping through
    Strings and each is getting printed. I don't have to call the parameter s in that expression, I can call it anything
    I want. Like the loop element in the for loop, I can name the argument variable anything that seems relevant, so let
    me now call it my String, instead of s.

                        list.forEach((s) -> System.out.println(s));
                                                to
                        list.forEach((myString) -> System.out.println(myString));

    Whatever I call the variable on the left, is what I need to refer to it as, on the right, in the code in the lambda
    expression code section. I can also include a type in the parameter declaration, if I wanted to. I'll do that next,
    including String as the type of my String.

                        list.forEach((myString) -> System.out.println(myString));
                                                    to
                        list.forEach((String myString) -> System.out.println(myString));

    This might help with readability, but it's not really necessary, since Java can infer the type, because of our list's
    type. I'll revert that change. If you've only got one parameter, as I do here, you don't even need the parentheses
    around that parameter, so I'll remove them.

                        list.forEach((myString) -> System.out.println(myString));
                                                to
                        list.forEach(myString -> System.out.println(myString));

    This is also a valid way to write a lambda expression. But if I now try to include the type again, without the parentheses,
    the java compiler doesn't like that. Parentheses are optional only when you have a single parameter, and aren't including
    the reference type of that parameter. I'll put the parentheses back in this case to get it to compile. Instead of string
    in the lambda expression parameter, I can use var. I'll do that, replacing String with var.

                        list.forEach((String myString) -> System.out.println(myString));
                                                    to
                        list.forEach((var myString) -> System.out.println(myString));

    Java can infer the type, again because my list was declared with the type argument, String. I'm also not limited to
    a single statement of code to the right of the arrow token. Now, IntelliJ's trying to get our attention with System.out.println.
    If I hover over that you'll see the message, "Lambda can be replaced with method reference". I'll be covering method
    references shortly, but again for now, just ignore these warnings as I introduce you to these expressions. Ok now,
    if I want to include multiple statements, I need to use opening and closing curly braces around the statements. Let
    me show you that.
*/
//End-Part-3

        System.out.println("-------");
        String prefix = "nato";
        //String myString = "enclosing Method's myString";
        list.forEach((var myString) -> {
            char first = myString.charAt(0);
            System.out.println(prefix + " " + myString + " means " + first);
        });
        //prefix = "NATO";
        //System.out.println(myString);

//Part-4
/*
        First, I'll add another separator line. Then I'll invoke forEach on list with the same parameter, var myString
    in parentheses, followed by the arrow token, but now I'm going to put an opening curly brace here, and I'll set up
    a local variable, char, assigning that the first character of the "myString" variable. I'll print myString, and that
    firstLetter out next. Now, because I'm using curly braces, each statement in this lambda expression code block must
    end in a semi-coon, as you can see I've done here, in this code. And running the code:

                -------
                alpha means a
                bravo means b
                charlie means c
                delta means d

    I get the output. Let's review these syntax variations I just covered to summarize some of what I've just gone over.
    This table shows the different ways to declare a single parameter in a lambda expression.

            Lambda Expression                                       Description
            element -> System.out.println(element);                 A single parameter without a type can omit the parentheses.
            (element) -> System.out.println(element);               Parentheses are optional.
            (String element) -> System.out.println(element);        Parentheses required if a reference type is specified.
            (var element) -> System.out.println(element);           A reference type can be var.

    In the first example, a single parameter without a type, can omit the parentheses around the parameter. Or I could
    include the optional parentheses in the second example. Parentheses are required if a referenced type is specified,
    which I'm showing in the third example, where I'm specifying the type as String. A reference type can be replaced with
    var, as demonstrated by the fourth example. We'll look at how to declare multiple parameters a bit later. The lambda
    body can be a single expression, or can contain a lambda body in opening and closing curly brackets.

                                (element) -> System.out.println(element);

    An expression can be a single statement. Like a switch expression, that does not require yield for a single statement
    result, the use of return is not needed and would result in a compiler error, if you did use it.

                                (var element) -> {
                                    char first = element.charAt(0);
                                    System.out.println(element + " means " + first);
                                };

    An expression can be a code block.  Like a switch expression with a code block, that requires yield, a lambda that
    returns a value, would require a final return statement. We'll be covering these examples in upcoming lectures. All
    statements in the block must end with semi-colons.

        Next, I want to talk about scoped variables and lambda expressions. Like local and anonymous classes, the lambda
    expression's code can use the enclosing code's local variables or method parameters, under certain circumstances.
    These need to be final or effectively final. Let me set up a local variable, just above that last forEach method call.
    I'm going to set up a String variable named prefix, and set it to nato, all in lower case. I can use this prefix
    variable in any of my lambda expressions now, as long as I never make any changes to that variable or reassign it.
    I'm going to add that prefix variable inside my lambda code, printing it out first for each statement.

                    System.out.println("-------");
                    list.forEach((var myString) -> {
                        char first = myString.charAt(0);
                        System.out.println(myString + " means " + first);
                    });
                                        to

                    System.out.println("-------");
                    String prefix = "nato";
                    list.forEach((var myString) -> {
                        char first = myString.charAt(0);
                        System.out.println(prefix + " " + myString + " means " + first);
                    });

    This code runs,

                    -------
                    nato alpha means a
                    nato bravo means b
                    nato charlie means c
                    nato delta means d

    and I now get "nato" printed out on each line. It's important to understand that if we're using local variables from
    the enclosing scope, we can't change them, anywhere in this main method. This means we can't change this String in
    any code in this method, either before or after the lambda expression it's used in. For example, if I added a statement
    after the lambda expression, and set "prefix = "NATO", in all upper case this time, notice what happened. I've got
    an error on prefix inside the lambda expression, even though it's executed before this line of code, "Variable used
    in lambda expression should be final or effectively final". Later, I'm going to cover the concept of deferred lambda
    expressions, which is one reason you can't use variables that aren't effectively final in your lambda. Here, in this
    code, it's not so intuitive, since the lambda was created and executed in the same statement, but this isn't always
    how lambdas are used. We'll be covering deferred lambdas in just a bit. Right now, I want to remove that last statement.

        Another thing you have to remember is that the parameter list you use in the lambda can't conflict with the enclosing
    scope's parameters and local variables. What do I mean by that? Let me show you. I'm going to add a local variable,
    called myString, again right before that last forEach method call, and I'll set that to the text, enclosing method's
    myString. This code also gives me a compiler error on the very next line, where I'm declaring my lambda expressions
    parameters. IntelliJ's message is "Variable 'myString' is already defined in the scope". These lambda parameters can't
    have the same name as the enclosing block's variables. Let me revert that change. And like any other enclosing block,
    if we declare local variables in the lambda expression, we can't access them after that block. I'll demonstrate that
    by trying to print myString after the last forEach call, "System.out.println(myString);". Now, this too is a compiler
    error, which IntelliJ says "Cannot resolve symbol 'myString'", because myString goes out of scope outside of the lambda
    expression. I'll revert that change as well so that my code compiles and runs.

        Now that we've actually seen lambda expressions, how have they enhanced java? Well some developers see lambdas as
    nothing more than syntactic sugar, giving us a more convenient and concise way of writing anonymous classes. I mean
    after all, they don't actually add anything to the language that wasn't already there. We can assign them to variables
    and pass them around, but we can do that with anonymous class objects as well. Now, other people say that lambdas are
    Java's first step into functional programming. This is a programming paradigm that focuses on computing and returning
    results. And just for more information about functional programming, there's a good wikipedia article here.

                                https://en.wikipedia.org/wiki/Functional_programming

    Check that out to find out a bit more about functional programming.

                                                    Streams

        Another feature of Java, makes extensive use of lambda expressions, and that's streams. Streams are exciting,
   because they create a pipeline of work that can be formed into a chain. Many of the stream operations take functional
   interfaces as parameters, meaning we can code them with lambda expressions.
*/
//End-Part-4
    }
}
