package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_10_LambdaExpressionsAndFunctionalInterfaces.Course05_FunctionAndSupplierInterfaces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;


//Part-1
/*
        The next category of interfaces I want to cover is the Function category.

                                            The Function Interface

        Below, I'm showing four of the most common interfaces in this category.

            Interface Name          Method Signature        Interface Name              Method Signature
            Function<T, R>          R apply(T t)            UnaryOperator<T>            T apply(T t)
            BiFunction<T, U, R>     R apply(T t, U u)       BinaryOperator<T>           T apply(T t1, T t2)

    Each has a return type, shown here as either T, or R, which stands for result, meaning a result is expected for any
    of these. In addition to Function and BiFunction, there is also UnaryOperator and BinaryOperator. You can think of
    the UnaryOperator as a Function Interface, but where the argument type is the same as the result type. The Binary
    Operator is a BiFunction interface, where both arguments have the same type, as does the result, which is why the
    result is shown as T, and not R. This reminds us that the result will be the same type as the arguments to the methods.
    I've also included the type parameters with each interface name above, because I wanted you to see that the result,
    for a Function or BiFunction, is declared as the last type argument. For UnaryOperator and BinaryOperator, there is
    only one type argument declared, because the types of the arguments and results, will be the same. Below, I'm showing
    an example of a lambda expression which targets a Function interface.

            Example                     Function Method         Variable Declaration for this example
            s -> s.split(",");          R apply(T t)            Function<String, String[]> f1;

    This lambda expression takes a String, s, and splits that String on commas, returning an array of String. In this case,
    the argument type, T, is a String, and the result, R, is an array of String. To demonstrate how to declare a variable
    of this type, I'm showing a variable declaration as well, for this specific example. So let's get back to the code,
    and use some of these interfaces.
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
        var result3 = calculator(
                (a, b) -> a.toUpperCase() + " " + b.toUpperCase(),
                "Ralph", "Kramden");

        var coords = Arrays.asList(
                new double[]{47.2160, -95.2348},
                new double[]{29.1566, -89.2495},
                new double[]{35.1556, -90.0659});

        coords.forEach(s -> System.out.println(Arrays.toString(s)));

        BiConsumer<Double,Double> p1 = (lat, lng) ->
                System.out.printf("[lat:%.3f lon:%.3f]%n", lat, lng);

        var firstPoint = coords.get(0);
        processPoint(firstPoint[0], firstPoint[1], p1);

        System.out.println("-------");
        coords.forEach(s -> processPoint(s[0], s[1], p1));
        coords.forEach(s -> processPoint(s[0], s[1],
                (lat, lng) ->
                        System.out.printf("[lat:%.3f lon:%.3f]%n", lat, lng)));

        list.removeIf(s -> s.equalsIgnoreCase("bravo"));
        list.forEach(s -> System.out.println(s));

        list.addAll(List.of("echo", "easy", "earnest"));
        list.forEach(s -> System.out.println(s));

        System.out.println("-------");
        list.removeIf(s -> s.startsWith("ea"));
        list.forEach(s -> System.out.println(s));

//Part-2
/*
        There are 2 useful methods, 1 on List, 1 one on the java.util.Arrays class, I haven't yet reviewed which I'll
    cover now. As with the forEach and removeIf methods, it didn't make sense to talk about them before this without
    understanding interfaces and lambdas. I'll use these as examples of 2 different interfaces in this Function category.
    The first method is on List and it's called "replaceAll". It takes a UnaryOperator

                    s -> s.charAt(0) + " - " + s.toUpperCase()

    as an argument. Can you guess why? A UnaryOperator takes one argument and returns a result which has the same type
    as the argument. By executing this kind of method, we're guaranteed to get back the same type we pass to it, so we
    can pass an array element, and get a transformed array element back, which replaces the current value in the specified
    array position. I'll again use my NATO Alphabet list for this example. I'll execute the replace all method on list,
    and pass it a lambda expression, this one also takes a single parameter, so I'll make that s for string. I'll execute
    the charAt method on that, concatenate a dash, then execute to UpperCase on s, also adding that to the result. I'll
    print a separator line, and then print my list elements. Running that:

                -------
                a - ALPHA
                c - CHARLİE
                d - DELTA
                e - ECHO

    you can see a is for Alpha. You'll remember I removed BRAVO earlier, and so I have c - CHARLIE, d - DELTA, and e -
    ECHO. With one statement, I can update every element in the array, with a formula I pass directly to the replaceAll
    method. Again, I want to look at the implementation of this "replaceAll" method, by ctrl clicking on it in my code.
    This method uses ListIterator, because for each element, it's going to call the set method, replacing the current
    element in the iterator with a different value, the value that gets returned from the apply method. This should
    remind you that the ListIterator gives us this option, meaning using set, which the Iterator by itself doesn't, because
    it only supports removing elements. I hope some of these examples are starting to capture your attention and generate
    enthusiasm for using lambdas. They'll ultimately save you from wiring a lot of unnecessary code. Getting back to the
    code in Main.java, I next want to show you the "setAll" method on the java.util.Arrays method.
*/
//End-Part-2


        list.replaceAll(s -> s.charAt(0) + " - " + s.toUpperCase());
        System.out.println("-------");
        list.forEach(s -> System.out.println(s));

//Part-3
/*
        This one is similar to the list.replaceAll method, but it takes an IntFunction. What's an IntFunction? Can you
    guess? It's a function interface that has the apply method with an int primitive argument. And it returns any type
    but when executed as part of setAll, the type will match the type of the array element. I'll create a new array of
    strings first, and print the elements out, using 2 string method on Arrays. Here, I'm just creating an array of 10
    strings, printing them out. Hopefully you'll remember, this just gives us an array of 10 null references, and you
    can see that when I run this code.

            ---(same)
            [null, null, null, null, null, null, null, null, null, null]
*/
//End-Part-3

        String[] emptyStrings = new String[10];
        System.out.println(Arrays.toString(emptyStrings));

//Part-4
/*
        Next, I'll use the fill method, which I have covered previously, and it just takes a single string, and sets each
    element to that string. This time I'll fill the array with empty strings, and again print that out.

                ---(same)
                [, , , , , , , , , ]

    And now you can see, I don't have null references anymore. But even better than the fill method, there's a setAll
    method, that lets us use a formula to populate each element. This interface is different from the one in the list.replaceAll
    method, because instead of having access to each element, we actually have access to each index value in the array,
    so the parameter is an integer representing the current index of the array element.
*/
//End-Part-4

        Arrays.fill(emptyStrings,"");
        System.out.println(Arrays.toString(emptyStrings));

//Part-5
/*
        I'll invoke the setAll method on the Arrays class, first passing it the emptyStrings array, and then I'll include
    a lambda expression. The parameter is i, which traditionally is the way to refer to an array index in a loop, and on
    the right side of the arrow token, I'm setting up a string, that includes the number, i + 1, and a period ". " after
    that. And I'll again print that. Running that:

            ---(same)
            [1. , 2. , 3. , 4. , 5. , 6. , 7. , 8. , 9. , 10. ]

    my array elements now include numbers in their Strings. Again, I'll control click the "setAll" method. That opens
    the Arrays class, and you can see the "setAll" method. You can see it takes an array, and this second parameter,
    IntFunction type, called generator. Now, instead of an enhanced for loop or an iterator, it's using a traditional
    for loop shown there, iterating over every element in the array. It then sets each array element to the result of the
    apply method on the generator. For my code, this is the lambda expression which used index to populate each array
    element with a number, starting with 1, not 0. Ok, let's get back to the main method, and explore what else we can do
    in that lambda expression a little bit.
*/
//End-Part-5

        Arrays.setAll(emptyStrings, (i) -> "" + (i + 1) + ". ");
        System.out.println(Arrays.toString(emptyStrings));

//Part-6
/*
        Because Java now gave us switch expressions, I can use a switch expression directly in my lambda expression. I'll
    copy those last 2 statements and paste them right below. And I'll add a new line before the closing parentheses on
    the Arrays.setAll statement. Now, I'll add a switch expression, a simple one, as part of this lambda expression single
    statement. I'll add plus then switch with i. For case 0, I want to add the text "one", case 1, I'll add the text
    "two", case 2 gets "three", and then the default will just return an empty string. And running that code:

            [1. one, 2. two, 3. three, 4. , 5. , 6. , 7. , 8. , 9. , 10. ]

    you can see I'm able to fill the array with values, using a switch expression in my lambda. As with anything, you'll
    want to achieve a balance between the readability of code, and it's succinctness. This lambda could be a candidate
    for a method somewhere. Here, I just wanted to show you, that there's really no limit to what you can do, when a method
    supports these functional interface types.
*/
//End-Part-6

        Arrays.setAll(emptyStrings, (i) -> "" + (i + 1) + ". "
            + switch (i) {
                    case 0 -> "one";
                    case 1 -> "two";
                    case 2 -> "three";
                    default -> "";
                }
        );
        System.out.println(Arrays.toString(emptyStrings));

//Part-7
/*
        Ok, those are 2 useful methods you're sure to have opportunities to use, and 2 examples of interfaces that fall
    into the function category. This leaves us with one more major interface category left to discuss, and that's the
    Supplier.

                                            The Supplier Interface

        The supplier interface takes no arguments but returns an instance of some type, T.

            Interface Name          Method Signature
            Supplier                T get()

    You can think of this as kind of like a factory method code. It will produce an instance of some object. However, this
    doesn't have to be a new or distinct result returned. In the example I'm showing you below,

            () -> random.nextInt(1, 100);

    I'm using the Random class to generate a random Integer. This method takes no argument, but lambda expressions can
    use final or effectively final variables in their expressions, which I'm demonstrating here. The variable random is
    an example of a variable, from the enclosing code. Let's get back to the code, and I'll demonstrate code similar to
    this in my Main method, but first I'll create a method on the main class,
*/
//End-Part-7

//Part-9
/*
        I'll now set up my names array, with names. I'll set up a local variable, an array of String. and call that random
    List. I'll assign the result of calling my new method, randomly selected values. I want an array of 15 strings back,
    so that's my first argument, 15, and I'll pass my list of valid name entries, and finally, I want to pass a lambda
    expression. This lambda has no arguments, so I need a set of empty parentheses before the arrow token. After the token,
    I'm going to invoke nextInt on a new random instance, passing it 0 as the first possible value, and names.length as
    the exclusive ending boundary, so this will give me an integer between 0 and 5. And I'll print my list of random
    names after. And if I run that:

                ---(same)
                [Fred, Carol, Fred, Ann, Ed, Ed, Fred, Ann, Fred, Ann, Fred, Carol, Fred, Fred, David]

    I'll get an Array of Strings that contains 15 names, randomly selected from my names array.
*/
//End-Part-9

        String[] names = {"Ann", "Bob", "Carol", "David", "Ed", "Fred"};
        String[] randomList = randomlySelectedValues(15, names, () -> new Random().nextInt(0, names.length));
        System.out.println(Arrays.toString(randomList));

//Part-10
/*
        I want you to look at this lambda expression closely.

                    () -> new Random().nextInt(0, names.length)

    This is the first time I've showed you an example that had no argument at all. In this case, the parentheses, an empty
    set of parentheses, is required. In a lambda expression, on the left side of the arrow token, you can have empty parentheses
    like we do here, which means no arguments. You can have a single variable name, which means there is a single argument.
    You can have a typed single variable, but that needs to be in parentheses. And multiple arguments also need to be
    enclosed in parentheses.

                   _________________________________________________________________________________
                   |Arguments in Functional Method                 |Valid lambda syntax            |
                   |_______________________________________________|_______________________________|
                   |None                                           |() -> statement                |
                   |_______________________________________________|_______________________________|
                   |One                                            |s -> statement                 |
                   |                                               |(s) -> statement               |
                   |                                               |(var s) -> statement           |
                   |                                               |(String s) -> statement        |
                   |_______________________________________________|_______________________________|
                   |Two                                            |(s, t) -> statement            |
                   |  - when using var, all args must use var.     |                               |
                   |  - when specifying explicit types, all args   |(var s, var t) -> statement    |
                   |    must specify explicit types.               |                               |
                   |                                               |(String s, List t) -> statement|
                   |_______________________________________________|_______________________________|

        This table shows the many varieties of declaring a parameter type in a lambda expression. Parentheses are required
    in all but the one case, where the functional method has a single argument, and you don't specify a type, or use var.
    When using var as the type, every argument must use var. When specifying explicit types, every argument must include
    a specific type. This ends our introduction to Java's functional interfaces. There are other interfaces which aren't
    in the java.util.function package, that are considered functional interfaces, one of which is Comparator, which we've
    already covered pretty thoroughly. We'll learn about others as we progress through the course, which are specific to
    other topics. These four sets of interfaces alone cover a lot of ground, and provide endless fertile ground for usage.
*/
//End-Part-10
    }

    public static <T> T calculator(BinaryOperator<T> function, T value1, T value2) {

        T result = function.apply(value1, value2);
        System.out.println("Result of operation: " + result);
        return result;
    }

    public static <T> void processPoint(T t1, T t2, BiConsumer<T,T> consumer) {
        consumer.accept(t1, t2);
    }

//Part-8
/*
        In previous lectures, I used an instance of random to pick randomly generated names from an array of names. That's
    what I want to do here, but using a Supplier lambda expression. This method will return an array of String, and I'll
    call it randomly selected values. It has 3 parameters, an integer count, an array of String I'll call values, and a
    Supplier interface type, that takes an Integer. This method is going to return an Array of String with the same number
    of elements that are passed as the first argument. It'll use the second argument to get a value randomly picked from
    that array. It will use a Supplier interface to get an integer to use as the index to pick the name. Now, let me add
    the code that does all this. "selectedValues" is another array of string and will have count elements. I'll loop from
    0 to count - 1. I'll set each element in my new array, to a corresponding element from the array passed to this method.
    However, I'll get the index from the supplier argument, the lambda expression in other words, that gets passed. Finally,
    I return my new array back. Back to the bottom of the main method,
*/
//End-Part-8

    public static String[] randomlySelectedValues(int count, String[] values, Supplier<Integer> s) {

        String[] selectedValues = new String[count];
        for (int i = 0; i < count; i++) {
            selectedValues[i] = values[s.get()];
        }
        return selectedValues;
    }
}
