package CourseCodes.NewSections.Section_14_LambdaExpressionsAndFunctionalInterfaces.Course11_ChainingLambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

//Part-1
/*
                                            Convenience Methods

        In this lecture, we'll learn how to do something similar, using what are called convenience methods. These are
    default methods on some of the functional interfaces I've been covering in the last few lectures. The Consumer, Predicate,
    and Function interface all come with these methods, as does the Comparator, which I'll also include here.
*/
//End-Part-1

public class Main {

    public static void main(String[] args) {

//Part-2
/*
        I'll create a couple of local variables here. First a String, and second a Function, that has 2 String types,
    called uCase. I'll set that to Tim. I'll set this to the method reference, String::toUpperCase. And I'll print out
    the result of the apply method on that function, passing it my name. Running this code:

            TİM

    you shouldn't be surprised to see this printed out, all in uppercase. This function interface has 2 default methods.
    The first is called, and then, which lets us chain functions, one to another. I'm going to set up a couple more lambda
    expressions next.
*/
//End-Part-2

        String name = "Tim";
        Function<String, String> uCase = String::toUpperCase;
        System.out.println(uCase.apply(name));

//Part-3
/*
        The first will concatenate my last name to a string. The second will execute the first function variable, which
    I called uCase, and then chain a call to the method, appropriately called andThen, passing it the second variable,
    lastName. What this does is execute uCase, and then executes last name, using the result of uCase as input. Running
    that:

            TİM Buchalka

    you can see, that tim was capitalized, and then buchalka was concatenated. In addition to the andThen method, this
    interface also has a compose method.
*/
//End-Part-3

        Function<String, String> lastName = s -> s.concat(" Buchalka");
        Function<String, String> uCaseLastName = uCase.andThen(lastName);
        System.out.println(uCaseLastName.apply(name));

//Part-4
/*
        I'll execute compose on the uCase function, again with last name as an argument. And print it out. What does compose
    do? The opposite of the andThen method actually. Here, it will execute the lastName lambda first, then executes uCase.
    Let's confirm that. Running this code:

            TİM BUCHALKA

    you can see my full name in capital letters. For that to have occurred, my last name had to be concatenated first, and
    the upper case method reference applied after that. You can use either, in the context of your code, which best fits
    the situation. The compose method is only available to lambda expressions, that target either a Function, or a UnaryOperator
    interface. BiFunction, BinaryOperator, and other function category interfaces that take a primitive, don't support
    the compose method. Now, your chained lambdas or method references don't have to return all same types, within the
    chain. Let me show you what I mean.
*/
//End-Part-4

        uCaseLastName = uCase.compose(lastName);
        System.out.println(uCaseLastName.apply(name));

//Part-5
/*
        I'll set up another variable, aFunction, and call it f0. This will be the result of 2 chained calls to the andThen
    method, first on the uCase function, and passing the lambda expression to concatenate "Buchalka" to the name. Then
    a call to split the string as the second lambda. By now, you know this will return an array of String. Print the result,
    using Arrays.toString. In this code, you can see by my last declared type arguments, String[], what I expect to be
    returned, an array of String. The first expression, uCase, a method reference, takes a string and returns a string.
    The second expression, a lambda, takes a string(the result from the previous method reference) and returns a String.
    The final method takes a string, and returns an array of String though. I hope you can see that the interim methods,
    don't have to return the same result, only the last expression has to return the declared type argument. You can see
    the array printed in my output when I run this.

            [TİM, Buchalka]
*/
//End-Part-5

        Function<String, String[]> f0 = uCase
                .andThen(s -> s.concat(" Buchalka"))
                .andThen(s -> s.split(" "));
        System.out.println(Arrays.toString(f0.apply(name)));

//Part-6
/*
        I'll copy those statements in the previous Function variable above, and paste them. I want to change the second
    type argument, from an array of string, to just a String again, and I want to change f0 to f1. And after the lambda
    with the split method, I'll remove the semi colon and insert a new line. Now, I'll chain another call to andThen,
    passing a lambda, that will take the second element of the array element which is its input, make it uppercase. Then
    the expression adds a comma, and finally the first element of the array. What's different here, and interesting, is
    the input to this expression is an array of String with first name at index 0, and last name at index 1. IntelliJ,
    if you have hints turned on, is really helpful when you are chaining function lambda expressions like this. It will
    tell you what the inferred types of the interim expressions are. The lambda expressions in the first invocation of
    andThen, is a function that takes a string, and returns a string. The second takes a String and returns a String
    array. Running this code,

            BUCHALKA, TİM

    you can see my name is all in caps, but with my last name listed first, with a comma, then my first name. Let's just
    try one more.
*/
//End-Part-6

        Function<String, String> f1 = uCase
                .andThen(s -> s.concat(" Buchalka"))
                .andThen(s -> s.split(" "))
                .andThen(s -> s[1].toUpperCase() + ", " + s[0]);
        System.out.println(f1.apply(name));

//Part-7
/*
        I'll again copy the first 3 of the previous function. I want to change the return type, the second type argument
    to an Integer, and f1 to f2. Now, I'll add 2 more chained calls to the andThen method, with different lambda expressions.
    The first lambda will join a string array into a string, delimited by a comma followed by a space. The second is a
    method reference that returns the length of the input string. I'll print the result of this chain of functionality.
    Now, look at the IntelliJ hints, specifically the second type argument of each inferred function. You can see it
    goes from aString, to a String array, and back to a String, in the hints shown here. But the ultimate return type,
    is Integer, which is how I set up the declaration. Again, it doesn't matter what the parameters or return types are,
    in the interim expressions, as long as the one chaining to the other is passing the expected data. Running that:

            13

    I get the length of my name, the length is 13. You can imagine, for the last challenge, instead of cycling through
    a list of functions, we could write code chaining functions like this. Ok, so those are the 2 convenience methods
    provided to us on the Function interface. The andThen method is also available on the BiFunction, and BinaryOperator,
    but not for any interfaces that have a primitive argument, like IntFunction, DoubleFunction, or LongFunction. The
    Consumer and BiConsumer interfaces, as well as the primitive versions of Consumer, also have an andThen method. Remember
    a consumer doesn't return any data, so whatever the input is at the start of the chain, will remain the input for any
    following chained expressions.
*/
//End-Part-7

        Function<String, Integer> f2 = uCase
                .andThen(s -> s.concat(" Buchalka"))
                .andThen(s -> s.split(" "))
                .andThen(s -> String.join(", ", s))
                .andThen(String::length);
        System.out.println(f2.apply(name));

//Part-8
/*
        I'll set up an array of a couple of names. Next, I want 2 consumer variables, assigning 1 lambda expression, and
    1 method reference. This lambda prints the first character of a string. This method reference is a call to the println
    method on System.out. The first expression will print the first letter of the name. The second expression prints the
    entire name with a newline. In this case, nothing gets returned from these methods, and therefore, s, is always the
    value of the parameter passed to the functional method.
*/
//End-Part-8

        String[] names = {"Ann", "Bob", "Carol"};
        Consumer<String> s0 = s -> System.out.print(s.charAt(0));
        Consumer<String> s1 = System.out::println;

//Part-9
/*
        Now I'll loop through my names, and execute a chain of andThen methods. The lambda expression I'm passing to the
    forEach is a compound statement with multiple andThen calls, chained together. If I run this:

            A - Ann
            B - Bob
            C - Carol

    I get the first initial of each name printed, from the s0 lambda, a hyphen "-" from the lambda passed to the first
    call of the andThen method, and then the full name, which is the result of the s1 variable being passed to the last
    andThen call. If you had a series of methods, with void return types, you can imagine chaining them this way, executing
    a variety of different methods on an array of Strings like this.
*/
//End-Part-9

        Arrays.asList(names).forEach(s0
                .andThen(s-> System.out.print(" - "))
                .andThen(s1));

//Part-10
/*
        Ok, now let's look at the convenience methods on Predicate. These are completely different, because Predicate
    returns a boolean value. These methods allow you to create compound test expressions, that evaluate to a single boolean
    value. The names of these 3 methods are:

                1. negate
                2. and
                3. or

    To try these out, I'll set up 4 Predicate variables, each with a different lambda expression. This lambda tests if
    the input string equals "TIM", all in caps. This second one checks if the input string equals "Tim", ignoring case.
    The 3rd one checks if the string starts with "T". Finally, the 4th lambda will be true if the string ends in an "e".
    Now, I want to set up a combined predicate, using the "or" method, and I can do this by calling the "or" method on 1
    predicate variable, and passing it another.
*/
//End-Part-10

        Predicate<String> p1 = s -> s.equals("TIM");
        Predicate<String> p2 = s -> s.equalsIgnoreCase("Tim");
        Predicate<String> p3 = s -> s.startsWith("T");
        Predicate<String> p4 = s -> s.endsWith("e");

//Part-11
/*
        I'll call this new variable combined1, and assign it "p1.or", passing p2 to the "or" method. And I'll print it out.
    Here, this code will test if either of the predicate p1 or p2, is true, and return true if either is true. Running that:

                combined1 = true

    I get combined1 is true, when I test it against my name in mixed case. This test true on the p2 predicate, which tests
    equality with equalsIgnoreCase.
*/
//End-Part-11

        Predicate<String> combined1 = p1.or(p2);
        System.out.println("combined1 = " + combined1.test(name));

//Part-12
/*
        Ok, now I want to test the "and" method with p3 and p4 variables. Running that:

            combined2 = false

    I get it as false, when we run it for tim, because tim doesn't end with an "e".
*/
//End-Part-12

        Predicate<String> combined2 = p3.and(p4);
        System.out.println("combined2 = " + combined2.test(name));

//Part-13
/*
        And what if now, I wanted the opposite result of a test? I can simply chain a call to the "negate" method. My next
    variable, combined3, will do what combined2 did, but additionally chain negate to it. Negate takes no parameters, and
    simply returns the opposite result of the result from the previous functions. And print out the result. And running
    the code:

            combined3 = true

    I get the opposite value of combined2, combined3 equals true. So that might be useful down the road. Predicates are
    going to be used a lot more, when we get to streams, but don't forget this kind of functionality, could be useful in
    the "removeIf" method on lists as well, for example.
*/
//End-Part-13

        Predicate<String> combined3 = p3.and(p4).negate();
        System.out.println("combined3 = " + combined3.test(name));

//Part-14
/*
        Let me show you the methods we just worked with all above, in 1 chart to summarize. For andThen, and compose, on
    the function category of interfaces, any Interim Functions are not required to have the same type arguments. Instead,
    one function's output becomes the next function's input, and the next function's output is not constrained to any
    specific type, except the last function executed.

        _____________________________________________________________________________________________
        | Category of Interface  | Convenience method example   |   Notes                           |
        |________________________|__________________________________________________________________|
        | Function               | function1.andThen(function2) |  Not implemented on IntFunction,  |
        |                        |                              |  DoubleFunction, LongFunction     |
        |________________________|______________________________|___________________________________|
        | Function               | function2.compose(function1) |  Only implemented on Function &   |
        |                        |                              |  UnaryOperator                    |
        |________________________|______________________________|___________________________________|
        | Consumer               | consumer1.andThen(consumer2) |                                   |
        |________________________|______________________________|___________________________________|
        | Predicate              | predicate1.and(predicate2)   |                                   |
        |________________________|______________________________|___________________________________|
        | Predicate              | predicate1.or(predicate2)    |                                   |
        |________________________|______________________________|___________________________________|
        | Predicate              | predicate1.negate()          |                                   |
        |________________________|______________________________|___________________________________|

        The Consumer's andThen method is different, because it never returns a result, so you use this when you're chaining
    methods independent of one another. The Predicate methods always return a boolean, which will combine the output of
    the two expressions, to obtain a final boolean result.

        I covered 2 of Comparator's static methods, natural Order, and reverseOrder earlier. Now, I want to cover the
    additional convenience methods, since as you can see from this table, many take a functional interface instance, as
    an argument.

                        ______________________________________________________________________
                        | Type of Method  | Method Signature                                 |
                        |_________________|__________________________________________________|
                        | static          | Comparator "comparing"(Function keyExtractor)    |
                        |_________________|__________________________________________________|
                        | static          | Comparator naturalOrder()                        |
                        |_________________|__________________________________________________|
                        | static          | Comparator reverseOrder()|                       |
                        |_________________|__________________________________________________|
                        | default         | Comparator "thenComparing"(Comparator other)     |
                        |_________________|__________________________________________________|
                        | default         | Comparator "thenComparing"(Function keyExtractor)|
                        |_________________|__________________________________________________|
                        | default         | Comparator reversed()                            |
                        |_________________|__________________________________________________|

    There is a comparing static method, and an overloaded default method named, thenComparing, and finally a default
    reversed method. Let's look at these in some code.

        Continuing with the code from the where we left off, I'm now going to include a record in the main method. I did
    something similar, creating a record as an inner class, but now, I'm going to create a local record, as part of this
    main method. This record will be called "Person", and have 2 String fields, first name and last name. And this is valid
    to do. Next, I'll set up an ArrayList of a few persons, some fictional characters.
*/
//End-Part-14

        record Person(String firstName, String lastName) {}

//Part-15
/*
        My declared type is a List of Person, the name of my list is list, and I create a new arraylist, created from a
    bunch of Person instances. My persons will be Peter Pan, Peter Pumpkin Eater, and Mickey and Minnie Mouse. Now, that
    I have my list, I want to sort it. I can do that with the sort method on list, passing it a lambda expression, representing
    a comparator.
*/
//End-Part-15

        List<Person> list = new ArrayList<>(Arrays.asList(
                new Person("Peter", "Pan"),
                new Person("Peter", "PumpkinEater"),
                new Person("Minnie", "Mouse"),
                new Person("Mickey", "Mouse")
        ));

//Part-16
/*
        I'll pass it a lambda which you've seen before, that compares last names, using compare 2 method on String. I'll
    print each name in the list, after this sort. Because my record is local to this method, I can directly access this
    records fields, as I show here, referencing the field lastName on o1 and o2. Running this:

            Person[firstName=Minnie, lastName=Mouse]
            Person[firstName=Mickey, lastName=Mouse]
            Person[firstName=Peter, lastName=Pan]
            Person[firstName=Peter, lastName=PumpkinEater]

    I get my characters printed out in last name order. But an easier way to do this is to use a static method on comparator,
    called comparing. This method takes a Function as it's method parameter, and returns a Comparator.
*/
//End-Part-16

        list.sort((o1, o2) -> o1.lastName.compareTo(o2.lastName));
        list.forEach(System.out::println);

//Part-17
/*
        This method takes a Function as it's method parameter, and returns a Comparator. I'll separate the output here.
    I'll call sort on list again, but this time I want to pass it the result of executing the method, comparing, on the
    Comparator interface type. To this I pass a method reference, Person::lastName. And I'll print the element sorted
    this way. In this case, the method comparing is a static method on the interface, so I call it directly from the
    Comparator type. And I can pass it a method reference. This reference implicitly accepts a Person instance, and retrieves
    the lastName field from that instance. Running that code,

                ------------------------------------
                Person[firstName=Minnie, lastName=Mouse]
                Person[firstName=Mickey, lastName=Mouse]
                Person[firstName=Peter, lastName=Pan]
                Person[firstName=Peter, lastName=PumpkinEater]

    I get the same results. But what's even nicer, I can chain another convenience method, called thenComparing, and pass
    a second method reference or lambda expression, to do some multi-level sorting.
*/
//End-Part-17

        System.out.println("------------------------------------");
        list.sort(Comparator.comparing(Person::lastName));
        list.forEach(System.out::println);

//Part-18
/*
        First I'll copy those last 3 statements, and paste a copy below. I'll remove the last parentheses and semi colon,
    from the second statement, and then I'll add a new line. Now, I'll chain a call to the thenComparing method, and pass
    the method reference, Person firstName. And if I run that:

                ------------------------------------
                Person[firstName=Mickey, lastName=Mouse]
                Person[firstName=Minnie, lastName=Mouse]
                Person[firstName=Peter, lastName=Pan]
                Person[firstName=Peter, lastName=PumpkinEater]

    I get my characters printed out sorted by last name, then first name in this output, Mickey comes before Minnie. And
    finally I can take this sort, and simply reverse it.
*/
//End-Part-18

        System.out.println("------------------------------------");
        list.sort(Comparator.comparing(Person::lastName)
                .thenComparing(Person::firstName));
        list.forEach(System.out::println);

//Part-19
/*
        I'll copy those last 4 lines above this and paste them. Now, I can chain the reverse method after the call to then
    comparing. And now running that:

                ------------------------------------
                Person[firstName=Peter, lastName=PumpkinEater]
                Person[firstName=Peter, lastName=Pan]
                Person[firstName=Minnie, lastName=Mouse]
                Person[firstName=Mickey, lastName=Mouse]

    you can see the characters printed out in reverse from the previous sort. These methods on Comparator certainly make
    it much easier to create many different ways to sort. I'll be using these methods moving forward, when sorting custom
    classes or records. Ok, so this lecture ends our discussion about lambda expressions, which have really changed the
    way developers write java code.
        We've already investigated some classes and interfaces that are part of Collections, namely lists, Array lists,
    linked lists and iterators. In addition to these, there are other collections such as sets and maps, and that's what
    I want to focus on in the next section.
*/
//End-Part-19

        System.out.println("------------------------------------");
        list.sort(Comparator.comparing(Person::lastName)
                .thenComparing(Person::firstName).reversed());
        list.forEach(System.out::println);
    }
}
