package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_10_LambdaExpressionsAndFunctionalInterfaces.Course09_MethodReferences_Part2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

//Part-1
/*
        In the lat lecture, we talked about 3 of the 4 types of method references. These were straight forward, but this
    last one can be harder to grasp. First I want to talk about some terminology that will hopefully help you understand
    this last type of reference. A Type Reference refers to a class name, an interface name, an enum name, or a record
    name. Remember that static methods are usually called using Type References, but can also be called by instances in
    our code. This is NOT true however for method references. Static methods, in method references and lambda expressions,
    must be invoked using a reference type only. There are two ways to call an instance method.

        The first is when you refer to the method with an instance derived from the enclosing code. This instance is
    declared outside of the method reference. The System.out::println method reference is an example. You'll find that
    some web sites call this instance a bounded receiver, and I actually like that terminology as a differentiator. A
    Bounded Receiver is an instance derived from the enclosing code, used in the lambda expression, on which the method
    will be invoked.

        The second way is where the confusion starts. The instance used to invoke the method, will be the first argument
    passed to the lambda expression or method reference when executed. This is known in some places as the Unbounded Receiver.
    It gets dynamically bound to the first argument, which is passed to the lambda expression, when the method is executed.
    Unfortunately this looks an awful lot like a static method reference, using a reference type. This means there are
    two method references that resemble each other, but have "two very different meanings".

        The first actually does call a static method, and uses a reference type to do it. We saw this earlier, when we
    used the sum method on the Integer wrapper class.

                                                    Integer::sum

    This is a Type Reference (Integer is the type), which will invoke a static method. This is easy to understand. But
    there is another, which you'll see when we start working with String method references in particular. Here, I show a
    method reference for the concat method on String.

                                                    String::concat

    Now, we know by now I hope, that the concat method, isn't a static method on String. Why is this method reference even
    valid? We could never call concat from the String class directly, because it needs to be called on a specific instance.
    As I just said that, instance methods can't be called using Reference Types. But the example shown right above,
    "String::concat", is a special syntax, when its declared in the right context, meaning when it's associated to the
    right type of interface. "String::concat" is valid when we use a method reference in the context of an "unbounded
    receiver".

        "Remember, the unbounded receiver means, the first argument becomes the instance used, on which the method gets
    invoked".

        Any method reference that uses String::concat, must be in the context of a two parameter functional method. The
    first parameter is the String instance on which the concat method gets invoked, and the second argument is the String
    argument passed to the concat method. This will hopefully make more sense when you see it in code.
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

public class Main {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>(List.of("Anna", "Bob", "Chuck", "Dave"));

        list.forEach(System.out::println);

        calculator(Integer::sum, 10, 25);
        calculator(Double::sum, 2.5, 7.5);

        Supplier<PlainOld> reference1 = PlainOld::new;

        PlainOld newPojo = reference1.get();

        System.out.println("Getting array");
        PlainOld[] pojo1 = seedArray(PlainOld::new, 10);

//Part-2
/*
        I'm going to invoke my calculator method again, passing a different lambda expression. This lambda will have 2
    parameters, s1 and s2, and I'll use the "+" operator on them, and I'll pass the strings, "hello" and "world", as the
    last 2 parameters to the calculator method.

                                calculator((s1, s2) -> s1 + s2, "Hello ", "World");

    This looks like a simple addition operation, but it's not, because we're operating on Strings. The "+" sign, when used
    with String operands, is a concatenation operator. Running this code:

            Result of operation: Hello World

    you can see that we get "Hello World" as the result of this. I'm going to change my lambda expression to use the concat
    method, instead of the "+" sign operator. And executing that,

            Result of operation: Hello World

    I get the same results. But once again, IntelliJ is requesting my attention, and sure enough, it's saying "this could
    be a method reference", so let me take that suggestion.
*/
//End-Part-2

        //calculator((s1, s2) -> s1 + s2, "Hello ", "World");
        calculator((s1, s2) -> s1.concat(s2), "Hello ", "World");

//Part-3
/*
        But once again, IntelliJ is requesting my attention, and sure enough, it's saying "this could be a method reference",
    so let me take that suggestion. Does this method reference give you pause? Maybe not, since I just gave you a few
    notes on this topic, but wait, how does this work again? Notice that, there are 2 parameters at the right side of the
    arrow token for the version of "(s1, s2) -> s1.concat(s2)", the concat method is invoked on the first argument, s1.
    The second argument, s2, is passed to the concat method. "String::concat" is exactly what the method reference will
    do implicitly, because it's declared in the context of a 2 parameter function method. How's that again? The method
    calculator has as it's first argument a BinaryOperator. A BinaryOperator has a method, called "apply", that takes 2
    arguments of the same type, and returns a result, also of that same type. Let me make this really clear, by setting
    up a local with the type BinaryOperator, using String as its type argument.
*/
//End-Part-3

        calculator(String::concat, "Hello ", "World");

//Part-4
/*
        I'll assign it the same lambda expression I was using, s1.concat, passing it s2. I could also make this a BiFunction
    interface, using String for all 3 type arguments(which is all the Binary Operator really does). I'll copy that last
    statement and paste it, changing BinaryOperator to BiFunction, and I'll have to declare 3 types for a BiFunction.

                                    BinaryOperator<String> b1 = (s1, s2) -> s1.concat(s2);
                                                            to
                                    BiFunction<String, String, String> b2 = (s1, s2) -> s1.concat(s2);

    The first 2 are the method parameters, strings, and the last is the result type, so String again. I want to click on
    the gutter icon next to the last statement here, on the BiFunction statement line, and show you the method, apply,
    that has 2 parameters. The 2 parameters in the targeted method, is the very reason we can use a method reference like
    String::concat. Going back to the main method, I'll change both of these method references, using IntelliJ's recommendation.

                                    BinaryOperator<String> b1 = (s1, s2) -> s1.concat(s2);
                                    BiFunction<String, String, String> b2 = (s1, s2) -> s1.concat(s2);
                                                             to
                                    BinaryOperator<String> b1 = String::concat;
                                    BiFunction<String, String, String> b2 = String::concat;
*/
//End-Part-4

        //BinaryOperator<String> b1 = (s1, s2) -> s1.concat(s2);
        //BiFunction<String, String, String> b2 = (s1, s2) -> s1.concat(s2);

        BinaryOperator<String> b1 = String::concat;
        BiFunction<String, String, String> b2 = String::concat;

//Part-5
/*
        What happens if I try to use an interface that has a method without 2 arguments?

                            BinaryOperator<String> b1 = String::concat;
                                                to
                            UnaryOperator<String> u1 = String::concat;

    IntelliJ is giving me an error there, on concat, "Non-static method cannot be referenced from a static context". Are
    you confused? Don't worry, this is definitely confusing for a while. If I click the gutter icon next to the UnaryOperator
    line, I can see the method. It's actually showing me the method on Function, which ultimately UnaryOperator is, but
    has the constraint that the returned type equals the method parameter type. And you can see the method is "apply", it
    has only 1 argument. Only 1 argument is available to the method reference, assigned to a variable of this type. Well,
    with only 1 argument available, calling concat on the first argument, would leave us with no additional argument to
    pass to the concat method, so it doesn't make any sense to use concat for this interface. Let me try to type this out
    a lambda expression, so I'll pop back to the Main.java file.

                            UnaryOperator<String> u1 = String::concat;
                                                to
                            UnaryOperator<String> u1 = (s1, s2) -> s1.concat(s2);

    This is an invalid lambda expression, and hopefully this is easier to understand. For a Unary Operator, there's no 2
    available to it, so we can't set this up s2, so I'll remove s2.

                            UnaryOperator<String> u1 = (s1, s2) -> s1.concat(s2);
                                                to
                            UnaryOperator<String> u1 = (s1) -> s1.concat(s2);

    And now you can see, the lambda expression has no idea what the variable s2 is. This code doesn't work out of the box
    like this. Java figures all of this out, and won't let us use String::concat, for the Unary Operator. But I can call
    a method on String that doesn't take a parameter, for example the instance method, toUpperCase. Let me do that now,

                            UnaryOperator<String> u1 = (s1) -> s1.concat(s2);
                                                to
                            UnaryOperator<String> u1 = (s1) -> s1.toUpperCase();

    And that compiles, and I can now change that to a method reference, so I'll do that. "String::toUpperCase" again, the
    implicit argument is used to invoke the toUpperCase method on. The good news is that Java and IntelliJ figure most
    of this out for us. The bad news is that this may be confusing for a while, until you get the hang of this.
*/
//End-Part-5

        //UnaryOperator<String> u1 = String::concat;
        //UnaryOperator<String> u1 = (s1, s2) -> s1.concat(s2);
        //UnaryOperator<String> u1 = (s1) -> s1.toUpperCase();
        UnaryOperator<String> u1 = String::toUpperCase;

//Part-6
/*
        I want to execute all of these method references next, and I'll call the apply method on each of these 3 variables,
    passing each to println. For b1 and b2, I'll pass 2 strings, "Hello" and "World". For u1, I'll just pass 1 string,
    "Hello". And executing this:

                ---(same)
                Hello World
                Hello World
                HELLO

    you can see the 3 output statements at the end. The first 2 concatenated "Hello" and "World", and the second changed
    "hello" to uppercase.
*/
//End-Part-6

        System.out.println(b1.apply("Hello ","World"));
        System.out.println(b2.apply("Hello ","World"));
        System.out.println(u1.apply("Hello "));

//Part-7
/*
        Before I end this lecture, I want to introduce you to another method on String, one I haven't shared with you yet.
    This method is named transform, and takes first a Function with a String type, as an argument. and it returns an object.
    Let's use this, to test a method reference or two. First, I already have a UnaryOperator variable set up, which is
    a derivative of the Function interface. This means I can simply pass my variable u1 to the transform method on a string.
    I'll set up a local variable, result, also a String and assign it the result of calling the transform method on the
    string "Hello", and I'll pass my u1 variable to that transform method. The transform method isn't required to return
    a String. It does in this case, because we've defined the variable to be aUnaryOperator with a String type argument.
    This means String is used as the argument, and returns a String as well. Running this code:

            ---(same)
            Result = HELLO

    you can see I get the result with hello all in uppercase.
*/
//End-Part-7

        String result = "Hello".transform(u1);
        System.out.println("Result = " + result);

//Part-8
/*
        I'll just pass a method reference directly executing transform on the result variable, and passing it String::toLowerCase.
    This will take the result and apply this method reference, and make it all lowercase now, which you can see when I
    run it:

            ---(same)
            Result = hello
*/
//End-Part-8

        result = result.transform(String::toLowerCase);
        System.out.println("Result = " + result);

//Part-9
/*
        You're not restricted to returning a String from this method. Let's try another method reference. First I'll create
    a Function variable, f0, which will take a String and return a Boolean. I'll assign this, the method reference String::isEmpty.
    Next, I want a boolean variable, and I'll call this result boolean, and that gets assigned the result of the transform
    method with the f0 variable on the result string. You can see with this code, I'm specifying a local variable, a Function
    with 2 different type arguments, String and Boolean. The last argument describes the type of the result. I could have
    passed the method reference directly to the transform method, but I wanted you to see this declaration, because it
    describes the result a bit more clearly. All of this could have been inferred by Java. Running this code,

            ---(same)
            Result = false

    you can see the last output statement. That's because the string variable, result, wasn't empty. Ok, so I hope that
    was interesting and helpful to you. We're going to be using lambda expressions a lot over the next sections of the
    course, and method references where appropriate.
*/
//End-Part-9

        Function<String, Boolean> f0 = String::isEmpty;
        boolean resultBoolean = result.transform(f0);
        System.out.println("Result = " + resultBoolean);

//Part-10
/*
        I'll finish this lecture with a few notes for your reference. These notes are a bit hard to talk over, so I'll
    just made them in charts.

                                            Four Types of Method References

        This chart shows the four different types of method references, with method reference examples, and a corresponding
    lambda expression.

    ___________________________________________________________________________________________________________________________________________________________
    |     Type                         |                     Syntax                        |    Method Reference Example   | Corresponding Lambda Expression  |
    |__________________________________|___________________________________________________|_______________________________|__________________________________|
    |static method                     |ClassName::staticMethodName(p1,p2,...,pn)          |  Integer::sum                 |   (p1, p2) -> p1 + p2            |
    |__________________________________|___________________________________________________|_______________________________|__________________________________|
    |instance method of a particular   |ContainingObject::instanceMethodName(p1,p2,...pn)  |  System.out::println          |   p1 -> System.out.println(p1)   |
    |(Bounded) object                  |                                                   |                               |                                  |
    |__________________________________|___________________________________________________|_______________________________|__________________________________|
    |instance method of an arbitrary   |ContainingType[=p1] ::instanceMethodName(p2,...pn) |  String::concat               |   (p1, p2) -> p1.concat(p2)      |
    |(Unbounded) object (as determined |                                                   |                               |                                  |
    |by p1)                            |                                                   |                               |                                  |
    |__________________________________|___________________________________________________|_______________________________|__________________________________|
    |constructor                       |ClassName::new                                     |  LPAStudent::new              |   () -> new LPAStudent()         |
    |__________________________________|___________________________________________________|_______________________________|__________________________________|

        This chart shows some of the valid ways to use method references when assigned to different interface types. These
    interface types have no arguments in the case of a Supplier, and one argument for the other interfaces.

    ______________________________________________________________________________________________________________________________________________
    |                                  |        No args          |                              One Argument                                     |
    |__________________________________|_________________________________________________________________________________________________________|
    |   Types of Method References     |   Supplier              |   Predicate             |   Consumer              |  Function(UnaryOperator)  |
    |__________________________________|_________________________|_________________________|_________________________|___________________________|
    | Reference Type (Static)          |                         |                         |                         |                           |
    |__________________________________|_________________________|_________________________|_________________________|___________________________|
    | Reference Type (Constructor)     | Employee::new           |                         |        n/a              |   Employee::new           |
    |__________________________________|_________________________|_________________________|_________________________|___________________________|
    | Bounded Retriever (Instance)     |                         |                         |  System.out::println    |                           |
    |__________________________________|_________________________|_________________________|_________________________|___________________________|
    | Unbounded Retriever (Instance)   |          n/a            |  String::isEmpty        |  List::clear            |   String::length          |
    |__________________________________|_________________________|_________________________|_________________________|___________________________|

    If a cell is empty, it's not because it's not valid, but there are many possibilities. n/a means not applicable, so a
    Supplier or an interface method that has no arguments, can never be a target for the unbounded receiver type of method
    reference.

        This chart shows some of the valid ways to use method references when assigned to different interface types. These
    interface types have two arguments, and therefore it's more common to see the unbounded retriever method references
    used for these.

        _______________________________________________________________________________________________________________
        |                                  |                            Two Argument                                  |
        |__________________________________|__________________________________________________________________________|
        |   Types of Method References     |   BiPredicate      |  BiConsumer         |  BiFunction(BinaryOperator)   |
        |__________________________________|____________________|_____________________|_______________________________|
        | Reference Type (Static)          |                    |                     |  Integer::sum                 |
        |__________________________________|____________________|_____________________|_______________________________|
        | Reference Type (Constructor)     |                    |                     |  Employee::new                |
        |__________________________________|____________________|_____________________|_______________________________|
        | Bounded Retriever (Instance)     |                    |  System.out::printf |  new Random()::nextInt        |
        |__________________________________|____________________|_____________________|_______________________________|
        | Unbounded Retriever (Instance)   |  String::equals    |  List::add          |  String::concat, String::split|
        |__________________________________|____________________|_____________________|_______________________________|
*/
//End-Part-10
    }

    private static <T> void calculator(BinaryOperator<T> function, T value1, T value2) {

        T result = function.apply(value1, value2);
        System.out.println("Result of operation: " + result);
    }

    private static PlainOld[] seedArray(Supplier<PlainOld> reference, int count) {

        PlainOld[] array = new PlainOld[count];
        Arrays.setAll(array, i -> reference.get());
        return array;
    }

}
