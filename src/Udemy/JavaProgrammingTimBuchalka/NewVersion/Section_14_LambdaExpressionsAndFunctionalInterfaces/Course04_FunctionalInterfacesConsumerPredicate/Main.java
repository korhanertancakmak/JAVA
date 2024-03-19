package CourseCodes.NewSections.Section_14_LambdaExpressionsAndFunctionalInterfaces.Course04_FunctionalInterfacesConsumerPredicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;

//Part-1
/*
        In the last lecture, I created an interface called Operation which had a single abstract method, called operate.
    Operate was the functional method for that interface. I made it a method with a generic type, taking 2 values and
    returning a result value, all required to be the same type. Although this was a good way to understand how to create
    a functional interface, it turns out this interface is almost an exact copy of another interface that Java provides
    for us.

                                            java.util.function Package

        Java provides a library of functional interfaces in the java.util.function package. We looked at one already, when
    I demonstrated the forEach method which had an argument for a type that implemented the Consumer interface. I'll look
    at another of these interfaces now, the BinaryOperator, in code.
*/
//End-Part-1

public class Main {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>(List.of(
                "alpha", "bravo", "charlie", "delta"));

        for (String s : list) {
            System.out.println(s);
        }

        System.out.println("-------");
        list.forEach((var myString) -> System.out.println(myString));

        System.out.println("-------");
        String prefix = "nato";
        list.forEach((var myString) -> {
            char first = myString.charAt(0);
            System.out.println(prefix + " " + myString + " means " + first);
        });

        int result = calculator((var a, var b) -> a + b,5, 2);
        var result2 = calculator((a, b) -> a / b, 10.0, 2.5);
        var result3 = calculator((a, b) -> a.toUpperCase() + " " + b.toUpperCase(), "Ralph", "Kramden");

//Part-4
/*
        Getting back to the code, I want to set up an example that uses both Consumer and BiConsumer. I'll start with a
    list of arrays, each which has 2 double values. These sets of values are going to represent the latitude and longitude
    locations of three points on the Mississippi River. You may remember that I used these coordinates in a previous lecture.
    I'll create a local variable, "coords" and simplifying the type declaration, by letting Java infer it, with "var". I'll
    assign it the value off Arrays, asList. With some hard-coded double arrays, containing some coordinate data. That gives
    me a list of double arrays where my double arrays only contains 2 doubles in each array. Now I want to print that out,
    and I'll use the forEach method with println. This time, I'll pass it Arrays.toString with s, as its argument. Remember,
    the forEach method is really using an enhanced for loop, iterating over each element in my coordinates list. That
    means, the "s" variable here, is really an array of double, because my list is made up of arrays of doubles. I use
    Arrays.toString to print out the elements in my array, and pass that to System.out.println. I showed you this method
    before, except I just printed Strings previously, and here I want to print arrays. Running that:

                ---(same)
                [47.216, -95.2348]
                [29.1566, -89.2495]
                [35.1556, -90.0659]

    I get my coordinates printed out, one for each line. But what if I want to print this out a different way? I'll do
    this, demonstrating a BiConsumer functional interface in the process.
*/
//End-Part-4

        var coords = Arrays.asList(
                new double[]{47.2160, -95.2348},
                new double[]{29.1566, -89.2495},
                new double[]{35.1556, -90.0659});

        coords.forEach(s -> System.out.println(Arrays.toString(s)));

//Part-6
/*
        I'll set up a variable with a reference type, BiConsumer, and the same type arguments, both Double. I'll call that
    p1, and assign it a lambda expression. My lambda takes 2 parameters, so I'm calling those lat and long, and then I'm
    going to print out my coordinates in a nicely formatted way, using the printf method. If I run this code:

            ----(same)
            [47.216, -95.2348]
            [29.1566, -89.2495]
            [35.1556, -90.0659]

    nothing additional is executed, even though I've declared this lambda expression here. Declaring a variable with lambda
    expression isn't going to actually execute that expression. Instead I need to manually call the functional method on
    that variable or pass that variable to a method that does the same thing. I'll pass this variable by calling my processPoint
    method.
*/
//End-Part-6

        BiConsumer<Double, Double> p1 = (lat, lng) -> System.out.printf("[lat:%.3f lon:%.3f]%n", lat, lng);

//Part-7
/*
        First I'll create a local variable called firstPoint, and assign it the first element in the coordinates list,
    by calling get(0), this gives me the first coordinate pair for the River location data. Then I'll call processPoint,
    passing the first double in the firstPoint array, and the second double, as well as the variable p1, my lambda expression
    variable. And running that:

            ---(same)
            [47.216, -95.2348]
            [29.1566, -89.2495]
            [35.1556, -90.0659]
            [lat:47,216 lon:-95,235]

    you can see the firstPoint gets printed out, using that lambda expression's formatted String. Next, I'll print all
    the points, and use the forEach method to do it. First I'll print a separator line.
*/
//End-Part-7

        var firstPoint = coords.get(0);
        processPoint(firstPoint[0], firstPoint[1], p1);

//Part-8
/*
        Then I'll call for each on my coordinates list and pass it a new lambda. We know that it takes a single parameter,
    and in turn this will call processPoint method, passing the first and second elements in the array, and also the p1
    variable I set up earlier. Now If I run this:

                ---(same)
                -------
                [lat:47,216 lon:-95,235]
                [lat:29,157 lon:-89,250]
                [lat:35,156 lon:-90,066]

    I get all 3 of my points printed out, using my custom formatting. I could replace p1 in this expression with the actual
    lambda expression that I assigned to that variable. Let me show you that.
*/
//End-Part-8

        System.out.println("-------");
        coords.forEach(s -> processPoint(s[0], s[1], p1));

//Part-9
/*
        First I'll copy and past that the last statement, adding a new line after the first 2 args. Then I'll go where
    I've created p1 local variable, and copy everything after the equals sign, after "p1 =" on the line, and the next,
    except the ending semicolon. I'll go down and replace p1, in the last statement with that. Here, you can see I've
    really got nested lambda expressions, which may not have been obvious, when I was using a lambda expression local
    variable. Now, this code is getting a bit ugly really, for anyone trying to read it, but it works the same. If I run
    that, I get my 3 coordinates printed yet again. Ok, so this covers the Consumer and BiConsumer interfaces.

        In addition to these 2, there are various special variations of other Consumer category type interfaces. For example,
    some let you pass a primitive as an argument. I won't cover all the variations of all these built-in interfaces here,
    because that would take a long time, and wouldn't really add a lot of value. Many are for very specific cases, and
    not used quite as often, but as the course progresses, you'll be seeing me use them where appropriate. Moving on form
    the Consumer, I now want to look at the Predicate interface, which is the second category of interfaces in this package.
*/
//End-Part-9

        coords.forEach(s -> processPoint(s[0], s[1], (lat, lng) -> System.out.printf("[lat:%.3f lon:%.3f]%n", lat, lng)));

//Part-10
/*
                                                The Predicate Interface

        The predicate interfaces take one or two arguments, and always returns a boolean value. They are used to test a
    condition, and if the condition is true, some operation will be performed.

            Interface Name                Method Signature
            Predicate                     boolean test(T t)
            BiPredicate                   boolean test(T t, U u)

    In this example, the expression takes a String, and tests if it's equal to the literal text, “Hello” here, ignoring
    case, so it returns either true or false.

            Example Lambda Expression for Consumer
            s -> s.equalsIgnoreCase("Hello")

    Getting back to our code, I want to review another method on the List interface, that I haven't yet covered, again
    because you needed to understand some basics about lambdas first. This is a method called "removeIf", which takes a
    predicate type as an argument. I want to use my list of nato alphabet strings to demonstrate this method, so I'll invoke
    removeIf on list, and pass a lambda expression. The lambda has 1 parameter, like a consumer, and in this case, it will
    be inferred to be a string, because my list is made up of strings. I'll follow that statement with a forEach call to
    print my list elements. Can you guess what the results will be, by looking at this lambda expression? I'm calling
    removeIf and passing it an expression that says "s = bravo", ignoring the case. If I run that:

                ---(same)
                alpha
                charlie
                delta

    you can see that I'e successfully removed bravo from my list. This occurred because if the lambda expression that gets
    passed to removeIf, evaluates to true for any element in the list, it gets removed. That's pretty slick. This method
    removes any elements that match that predicate code, meaning it can remove zero to all elements in the list, depending
    on the expression used.
*/
//End-Part-10

        list.removeIf(s -> s.equalsIgnoreCase("bravo"));
        list.forEach(s -> System.out.println(s));

//Part-11
/*
        I'll add a couple more elements to this list, just some strings starting with e, so echo, easy, and earnest. And
    I'll print my list out. And running that:

                ---(same)
                alpha
                charlie
                delta
                echo
                easy
                earnest

    you can see these strings printed out. Next, I want to use this same removeIf method, but this time to remove any
    string that starts with e, and a.
*/
//End-Part-11

        list.addAll(List.of("echo", "easy", "earnest"));
        list.forEach(s -> System.out.println(s));

//Part-12
/*
        I'll first add a line separator to make the output to read easier then I'll invoke list.removeIf, with a lambda
    that uses the string method, starts with, passing that a string value of EA. And again, I'll print the elements in
    the list out. And running that:

                -------
                alpha
                charlie
                delta
                echo

    you can see that both easy and earnest were removed from the list with a single line of code. Now, let's see what this
    removeIf method is really doing under the covers. I'll use ctrl click on that method name, and that will bring up the
    Collection interface class at the default method, removeIf. The method argument is of type Predicate, and called filter.
    It uses an iterator type to loop through all the elements, and that calls the test method on the filter instance to
    determine if an element should be removed. This is a good reminder that an iterator allows the removal of an element
    being iterated over, as this code demonstrates and which I covered in an earlier lecture. You'll learn more, and be
    able to review many of the concepts I've covered in real-world scenarios, if you make it a practice to see how Java
    implements its own code. This ability to see Java's source code is great learning tool. You can imagine how many ways
    you can customize expressions you pass. This method, by using a functional predicate interface, gives us very easy
    customizable ways to remove elements from a list. Those are real-world usages of both the consumer and predicate
    functional interfaces.

        In next lecture, I'll cover the other 2 category type, the Function and the Supplier interfaces and their different
    variations.
*/
//End-Part-12

        System.out.println("-------");
        list.removeIf(s -> s.startsWith("ea"));
        list.forEach(s -> System.out.println(s));

    }

//Part-2
/*
        I'm just going to change my calculator method. Instead of my own interface which I called Operation, I'm going
    to make the first parameter, a BinaryOperator. This gives me an error on the first statement, where I'm executing
    operate, which was the functional method name, on my own interface. The functional method on the BinaryOperator is
    apply, so I'll change operate, to apply. With those 2 changes, my code compiles and If I run it,

            ---(same)
            Result of operation: 7
            Result of operation: 4.0
            Result of operation: RALPH KRAMDEN

    I get the exact same results. I didn't really need to create this Operation interface, at all. As it turns out, a lot
    can be accomplished with just a few different types of interfaces, and Java gives us a library of these. Java's libraries
    also use these interfaces extensively so that we can leverage lambda expressions in many methods, the forEach method
    on the ArrayList, being just one example.
*/
//End-Part-2

//Part-3
/*
        It's a good idea to know the four basic types of functional interfaces in the java.util.function package. There
    are over forty interfaces in this package, but I don't want you to feel overwhelmed by that. These can all be categorized
    as one of the following types. This table shows the four categories, with the simplest method shown.

            Interface Category            Basic Method Signature          Purpose
            Consumer                      void accept(T t)                execute code without returning data
            Function                      R apply(T t)                    return a result of an operation or function
            Predicate                     boolean test(T t)               test if a condition is true or false
            Supplier                      T get()                         return an instance of something

    Consumer interfaces are used to execute statements but don't pass any data back. They take 1 or 2 arguments of various
    types, but return no value. Function interfaces are used to perform an operation and return a result. These take 1
    or 2 arguments of various types, and return the result of some formula or calculation, much like the Operation interface
    we created in the last lecture. Predicate interfaces are used to test a condition, and take 1 or 2 arguments, again
    of various types, and always returns a boolean result. Supplier interfaces are different because they take no args,
    but return an object which can be various types. Supplier interfaces are used to return an instance of something,
    a factory method might be a good lambda expression for this type of interface.

                                            The Consumer Interface

        On the table below, I'm showing the two most common Consumer interfaces, and the functional method on each.

            Interface Name                Method Signature
            Consumer                      void accept(T t)
            BiConsumer                    void accept(T t, U u)

    The Consumer interface takes one argument of any type. The BiConsumer interface takes two arguments, of two different
    types. Below I show an example consumer lambda expression.

            Example Lambda Expression for Consumer              Consumer Method
            s -> System.out.println(s)                          void accept(T t)

    It takes one argument and executes a single statement. No result is returned. We saw an example of the single argument
    consumer that using the forEach method on a List of Strings, to print out each string.
*/
//End-Part-3

    public static <T> T calculator(BinaryOperator<T> function, T value1, T value2) {

        T result = function.apply(value1, value2);
        System.out.println("Result of operation: " + result);
        return result;
    }

//Part-5
/*
        My method will again be a generic method with no return type, void, and called processPoint. I'll pass t1 and t2,
    both will have the generic type T, and I'll also pass an instance of the BiConsumer interface that takes 2 type arguments.
    I'll make both of these T as well, and name the argument consumer. Then I'll simply execute consumer.accept, passing
    it method arguments, t1 and t2. Now, a BiConsumer takes 2 args, and this code, I'm saying they'll be the same type,
    by using <T, T>. This executes code that doesn't return a value, or whose return value can be ignored. Before I call
    this, another thing I want to show you is that a lambda expression can be assigned to a local variable. I'll do that
    in the main method.
*/
//End-Part-5

    public static <T> void processPoint(T t1, T t2, BiConsumer<T, T> consumer) {
        consumer.accept(t1, t2);
    }
}
