package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_10_LambdaExpressionsAndFunctionalInterfaces.Course08_MethodReferences_Part1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;

//Part-1
/*
                                                Method References

        In some of the lambda expressions I've used so far, I've written an operation or expression. In others, I've simply
    referenced other named methods like System.out.println, for example. Java gives us an alternative syntax to use for
    this second kind of lambda, that uses named methods. These are called method references. These provide a more compact,
    easier-to-read lambda expression, for methods that are already defined on a class. For the last couple of lectures for
    example, I've been ignoring Intelli-J's warnings and hints, whenever I've used System.out.println in a lambda expression.
    That's because it can be replaced with a method reference.
*/
//End-Part-1

class PlainOld {

    private static int last_id = 1;

    private int id;

    public PlainOld() {
        id = PlainOld.last_id++;
        System.out.println("Creating a PlainOld Object: id = " + id);
    }
}

//Part-7
/*
        I'll set up a public constructor for that, using IntelliJ's generation tool to do that. Print creating a Plain
    Old Object. I now want to add a private static int variable that keeps track of the last id. I'll initialize that to
    1. Create a field o this class ID. I'll set that field in the constructor, assigning the Plain Old last id to id, and
    incrementing the last id with a post decrement operator. I'll also add id to the statement that gets printed there.
    Remember there's only one copy of last id, shared by all instances, so this code that is incrementing the shared value.
*/
//End-Part-7

public class Main {

    public static void main(String[] args) {

//Part-2
/*
        I'll start with a new project(or package), MethodReferences, and the usual Main class and main method. I'll set
    up a simple list of names, so List, type String, named list, equals a new ArrayList, and I'll pass a List of Strings
    with values Anna, Bob, Chuck, and Dave. And as you've seen many times, I'll execute the forEach method on list, and
    pass the lambda expression that uses System.out.println. You've probably noticed, IntelliJ's been highlighting this
    lambda expression, each time I've used it. If I hover over that, it says "Lambda can be replaced with method reference",
    and I can select that link, so I'll do that.

                                list.forEach(s -> System.out.println(s));
                                                to
                                list.forEach(System.out::println);

    That replaces my code with a different kind of expression. This is probably going to be the most commonly used method
    reference you'll see.
*/
//End-Part-2

        List<String> list = new ArrayList<>(List.of( "Anna", "Bob", "Chuck", "Dave"));

        //list.forEach(s -> System.out.println(s));
        list.forEach(System.out::println);

//Part-3
/*
        At first glance, it's not really obvious why a method reference has this syntax.

                Lambda expression                               Method Reference
                s -> System.out.println(s)                      System.out::println

    A method reference abstracts the lambda expression even further, eliminating the need to declare formal parameters.
    We also don't have to pass arguments to the method in question, in this case println. A method reference has double
    colons, between the qualifying type, or object, and the method name. In this example of a Consumer interface, not
    only is the method inferred, but the parameters are as well. Does this mean you can use any method in method references?

        Methods which can be used, are based on the context of the lambda expression. This means the method reference, is
    again dependent on the targeted interface's method. You can reference a static method on a class. You can reference
    an instance method from either an instance external to the expression, or an instance passed as one of the arguments.
    Or you can reference a constructor, by using new as the method. Method references can be used to increase the readability
    of your code. The System.out.println method reference is an example of an instance method, called on an external instance.
    The instance is the PrintStream object that gets returned from the System.out method. I'm going to add a method similar
    to the one I included in the lambda expressions lectures, called calculator.

                                                Deferred Method Invocation

        When you create variables that are lambda expressions or method references, it's important to remember that the
    code isn't invoked at that point. The statement or code block gets invoked at the point in the code that the targeted
    functional method is called.

*/
//End-Part-3

//Part-5
/*
        I'll set up a call to that method, using a lambda expression, which I also used previously that has 2 parameters,
    a and b, and returns a + b, and I'll also pass 10 and 25 to that method. Again, IntelliJ has highlighted part of this
    lambda expression. Again, IntelliJ has highlighted part of this lambda expression. Hovering over that, you can see it
    still says I can replace this with a method reference, so let me do that.

                                calculator((a, b) -> a + b, 10, 25);
                                                to
                                calculator(Integer::sum, 10, 25);

    That gets replaced with the method reference, Integer, colon colon, sum. I don't think we talked about the "sum" method,
    but it's simply a static method on the Integer wrapper class, that returns the sum of 2 integers, which obviously
    replaces the plus operator, when the operands are integers. You may or may not agree, that this code is a little simpler
    to read, than trying to look at the lambda expression itself. This is the second type of method reference, that uses
    a static method on a class with the class type as the reference on the left. Let's try that same exercise with decimal
    numbers.
*/
//End-Part-5

        //calculator((a, b) -> a + b, 10, 25);
        calculator(Integer::sum, 10, 25);

//Part-6
/*
        I'll call calculator with the same lambda expression, but pass some decimal values instead, 2.5 and 7.5. And again,
    IntelliJ is prompting me to turn this into a method reference, so I will:

                                    calculator((a, b) -> a + b, 2.5, 7.5);
                                                    to
                                    calculator(Double::sum, 2.5, 7.5);

    You can see it's using the static method on Double this time, and this still produces 10.0 from the calculation. Before
    we look at the second type of instance method reference, which can be a little confusing, I want to look about using
    a method reference to instantiate a new instance of a class. I'll create a class above Main, called Plain old.
*/
//End-Part-6

        //calculator((a, b) -> a + b, 2.5, 7.5);
        calculator(Double::sum, 2.5, 7.5);

//Part-8
/*
        In the main code, I want to create a local variable. Its type will be a Supplier. You'll remember this is a functional
    interface, that has the functional method, get, that takes no arguments, but returns an instance. I'll set this up,
    using a lambda expression that returns a new instance of a Plain Old class. Here, my variable is a Supplier, and I've
    used Plain Old as a type argument. I've assigned it a lambda expression with no arguments, which means I need to use
    the empty parentheses there. And with the expression itself to the right of the array token, this just calls new keyword
    with the class name, and empty parentheses. We know this calls the empty constructor, and will return a new instance
    of the PlainOld object. Here again, IntelliJ is indicating that I can make this code better, saying it can be replaced
    with a method reference, so I'll do that:


                            Supplier<PlainOld> reference1 = () -> new PlainOld();
                                                    to
                            Supplier<PlainOld> reference1 = PlainOld::new;

    This method reference is a special type, a constructor method reference. If I run this code like this, nothing happens
    though. I didn't see my constructor executed, or see a statement that a PlainOld object was created. Why not? Well,
    a method reference, like lambda expression variables is sort of like a method declaration. It's created and then used
    at a later time. It's not immediately executed at the time it's declared, it's deferred, and the code snippet gets
    passed around.
*/
//End-Part-8

        //Supplier<PlainOld> reference1 = () -> new PlainOld();
        Supplier<PlainOld> reference1 = PlainOld::new;

//Part-9
/*
                                                Deferred Method Invocation

        When you create variables that are lambda expressions or method references, it's important to remember that the
    code isn't invoked at that point. The statement or code block gets invoked at the point in the code that the targeted
    functional method is called.

        How can I execute this method reference then? I could simply execute the get method on the variable, so I can set
    up another PlainOld variable, named new Pojo, and assign it to reference1.get. If I run that now,

                ---(same)
                Creating a PlainOld Object: id = 1

    I can see that the constructor was executed. Why would you ever do this? Isn't it just simpler to call new on the
    PlainOld class, as we would any new instance? Yes, in this case it would be. Later, we'll learn methods for creating
    a lot of instances at once. For now, I'll set up my own method that does a little bit of that.
*/
//End-Part-9

        PlainOld newPojo = reference1.get();

//Part-11
/*
        First I'll print out a descriptive statement, getting array, and then I'll assign another local variable, pojo1,
    an array of PlainOld instances, and I'll assign that to the result of a call to this new method. I'll use a method
    reference as the first argument, and the number 10 as the second argument. And running that:

                Getting array
                Creating a PlainOld Object: id = 2
                Creating a PlainOld Object: id = 3
                Creating a PlainOld Object: id = 4
                Creating a PlainOld Object: id = 5
                Creating a PlainOld Object: id = 6
                Creating a PlainOld Object: id = 7
                Creating a PlainOld Object: id = 8
                Creating a PlainOld Object: id = 9
                Creating a PlainOld Object: id = 10
                Creating a PlainOld Object: id = 11

    you can see I've created 10 of these objects now, and put them in an array. We'll be revisiting suppliers, and additional
    use cases for using them, when we get to streams. There's one more method reference type to cover, and I'll do that
    in the next lecture.
*/
//End-Part-11

        System.out.println("Getting array");
        PlainOld[] pojo1 = seedArray(PlainOld::new, 10);
    }

//Part-4
/*
        I'll make it private and static, and generic with a type T. It won't have a return type, so void, and I'm going
    to call it calculator with the first parameter a BinaryOperator, and 2 additional parameters, value1 and value2, also
    both type T. As I did before, inside this method, I'll call the Binary Operator's functional method, apply, on function,
    and this method will print the result, instead of returning it. In the main method,
*/
//End-Part-4

    private static <T> void calculator(BinaryOperator<T> function, T value1, T value2) {

        T result = function.apply(value1, value2);
        System.out.println("Result of operation: " + result);
    }

//Part-10
/*
        I'll make this private static, and it'll return an array of PlainOld instances. I'll call it seed array, and the
    parameters will be Supplier called reference, and an int called count. Now, I'll set up an array variable in this method,
    and assign it new PlainOld with the count in brackets. I'll call Arrays.setAll, passing it my array, and a lambda
    expression, where i is the parameter, but instead of doing anything with i, in the lambda, I'll just call reference.get.
    I'll then return array from this method. This code will assign every element in the array, whatever is the result of
    executing get for the lambda expression passed to this method. We've said this is going to be a PlainOld class, by
    making the array that type. Let me execute this from the main method, using my method reference as the Supplier variable.

*/
//End-Part-10

    private static PlainOld[] seedArray(Supplier<PlainOld> reference, int count) {

        PlainOld[] array = new PlainOld[count];
        Arrays.setAll(array, i -> reference.get());
        return array;
    }

}
