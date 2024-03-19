package CourseCodes.NewSections.Section_14_LambdaExpressionsAndFunctionalInterfaces.Course03_LambdaExpressions_Part3;


import java.util.ArrayList;
import java.util.List;

//Part-1
/*
        Previously, we've studied a functional interface with a functional method that took one parameter and didn't return
    a value, it was void in other words. Here, I want to make my own interface, that will perform some calculations on
    2 values, and return a value from the calculation. I'll create a new interface in the same package, and call it Operation.
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

//Part-4
/*
        I'll pass a lambda expression with 2 parameters, and the code will add these together with the plus operator. And
    I'll pass the values 5 and 2. That's one statement, and the first argument is the lambda expression. This looks a little
    like the one I used in the first forEach method example. Except here though, I've got 2 parameters, a and b. Notice
    I haven't specified a type here anywhere. The expression on the right of the arrow token is, a + b. Now remember, this
    is going to map back to the Operation's method under the covers. How do I know? Well, notice the pink gutter icon next
    to this statement. If I hover over that, IntelliJ tells me, that this lambda expression, "Overrides method in Operation".
    Clicking on the icon, takes me to that interface's functional method's declaration, operate here, on Operation. This
    IntelliJ feature will help you identify the inferred method which will be used, in the context of your lambda expression
    usage. In this example, I purposely created my own interface, so this information was known to me. But let's go up to
    the forEach method now, above this, and click on that gutter icon. This time, you see the Consumer interface is opened
    in the editor, and the "accept" method is displayed. If you're ever in doubt, about the parameters needed, or what
    the resulting type should be, this feature can be very helpful. There's a lot more to cover here, but first I want to
    run this code from the Main class.

                    ----(same)
                    Result of operation: 7

    and you can see that, result of operation is 7. Now, let me start working through some variations to this lambda expression.
    First, I'll change "a + b", to "System.out.println(a + b)". This change results in a compiler error. If I hover over
    that, IntelliJ gives "Bad return type in lambda expression: void cannot be converted to Integer". Even though it doesn't
    look like I'm returning a value from this expression, I really am because the underlying abstract method has declared
    a return type. I can't have an expression here that doesn't return a value, like I'm trying to do by using System.out.println.
    I'll undo that. I also can't return a result that has a different value type than the parameters. But I haven't defined
    the types for the parameters have I? Well, I actually have passed integers as the second and third parameters to the
    calculator method. The Operation interface's type is inferred in this case, not with an int value, because you'll remember
    Generics don't work with primitive values, but with Integer, the wrapper class. I can assign it to an int result because
    of auto unboxing, and the values are auto boxed before the operation is performed. Now what happens if I return something
    other than an int from this expression? I'll include adding a string as the first part of my lambda expression. And
    that's another error, similar to the previous, "Bad return type in lambda expression: String cannot be converted to
    Integer". When I included "Test" in part of the equation there, everything was evaluated as Strings, but 5 and 2 are
    integers, so I can't return a String from my lambda code. Again I'll undo that. Now let me type my parameters this time.
    And this is another valid lambda expression. Whether we specify the types, or their inferred, when you have multiple
    parameters, you need the parentheses either way. If I try to remove the parentheses, that will be an error. A couple
    more things here, first I'll change Integer b, to var b. Ok, now this too is an error, and hovering over that," Cannot
    mix var and explicitly typed parameters in lambda expression". If you want to use var, to get java to infer the type,
    you have to use var for every parameter in the lambda expression parameter list. And let me do that. Ok, so now the
    compiler's happy, so I want to perform some other calculations. I'll next try division, on 2 decimal values.
*/
//End-Part-4

        //int result = calculator((a, b) -> a + b, 5, 2);
        //int result = calculator((a, b) -> System.out.println(a + b), 5, 2);
        //int result = calculator((a, b) -> "Test " + a + b, 5, 2);
        //int result = calculator((Integer a, Integer b) -> a + b,5, 2);
        int result = calculator((var a, var b) -> a + b,5, 2);
        //int result = calculator((var a, var b) -> return a + b,5, 2);
        //int result = calculator((var a, var b) -> { return a + b; }, 5, 2);
        //int result = calculator((var a, var b) -> { int c = a + b; }, 5, 2);

//Part-5
/*
        This time I'll call this result2, and use the var type, which will display a Java hint for us. I'll execute calculator
    with a lambda expression with the code to divide a by b. What I want to point out here, is the IntelliJ hint, tells
    me I'm getting a Double, with a capital D, meaning a double wrapper instance back. And running that:

            ---(same)
            Result of operation: 4.0

    I get result of operation is 4.0. I've used my single calculator method on both integers and doubles, and with different
    operations, the first addition, and second division. Let's keep going.
*/
//End-Part-5

        var result2 = calculator((a, b) -> a / b, 10.0, 2.5);

//Part-6
/*
        I'll add another, result3, again using var, and calling calculator. My expression is going to use string methods
    on a and b. I'm going to concat a to b, after calling upper case, on both a and b, and pass 2 strings to the calculator
    method. You can see from this code, that operations aren't restricted to just numbers, or even numeric operations.
    Any operation can be executed by my calculator method, on any 2 elements of the same type, as I'm showing you with
    these Strings. I don't have to write a calculator method for every single type I want to support, and I don't have to
    write every possible operation in my code that I think calculator should support. Instead, the code that gets executed,
    can be anything that can be passed as a lambda, and that's pretty cool. This frees you up to write code once, that
    can be easily extendable. And it frees up client code to have more functionality which can be customized by lambdas.
    Running this code:

            ---(same)
            Result of operation: RALPH KRAMDEN

    I get my answer all in uppercase, as the result. Ok, so you've gotten used to seeing the return keyword, when something
    gets returned from a method. Why is the lambda expression different? First, let me try adding a return in that first
    call to calculator, in that lambda expression.

                            int result = calculator((var a, var b) -> a + b, 5, 2);
                                                    to
                            int result = calculator((var a, var b) -> return a + b, 5, 2);

    And you can see that gives an error. Yu can only use return in a code block, inside a set of curly braces in other words.
    I'll add those curly brackets, keeping the expression on a single line. But this still gives me an error, although
    this time it's a different error. The problem is, I need a semi-colon after the return statement, before the closing
    bracket. All statement in the curly braces must end in a semi-colon, like any code block. I'll add one.

                            int result = calculator((var a, var b) -> return a + b, 5, 2);
                                                    to
                            int result = calculator((var a, var b) -> { return a + b; }, 5, 2);

    Now I've got a valid lambda expression, which has a return statement. I'll take out the return statement, and instead
    just include a local variable, and assign it the result of "a + b".

                            int result = calculator((var a, var b) -> { return a + b; }, 5, 2);
                                                    to
                            int result = calculator((var a, var b) -> { int c = a + b; }, 5, 2);

    And without the return keyword, I get a message that "Missing return statement" from this expression. When you use
    curly braces, and you method has a return type defined for it, you've got to have a return statement, as you would
    with any code inside a method declared with a return type. This isn't true though, when you don't have the curly braces,
    because the result is inferred, and in fact, as I showed earlier, if you do include return in a simple lambda, that's
    also a compiler error. Only use return in a code block, when the functional method has a return type. I'm going to
    revert that lambda back to its simplest form, which is what I started with it. That was a quick tour through a few
    more lambda expression rules. Let me try to summarize these on a couple of tables next.

        The rules for multiple parameters used in a lambda expression are shown here.

        Lambda Expression                               Description
        (a, b) -> a + b;                                Parentheses are always required. Explicit types are not.
        (Integer a, Integer b) -> a + b;                If you use an explicit type for one parameter, you must use explicit
                                                        types for all the parameters.
        (var a, var b) -> a + b;                        If you use var for one parameter, you must use var for all parameters.

    You can't mix and match var with explicit types, or omit var from one or some of the parameters. This table shows the
    2 rules for returning from a lambda expression.

        Lambda Expression                               Description
        (a, b) -> a + b;                                When not using curly braces, the return keyword is unnecessary,
                                                        and will throw a compiler error.
        (a, b) -> {                                     If you use a statement block, meaning you use the curly braces,
            var c = a + b;                              a return is required if the function method has a return type.
            return c;
            }
*/
//End-Part-6

        var result3 = calculator(
                (a, b) -> a.toUpperCase() + " " + b.toUpperCase(),
                "Ralph", "Kramden");

    }

//Part-3
/*
        So I want to include <T> before the return type, which I'm saying is T. I'm calling this calculator, and it's got
    3 arguments, the first is an instance of my new interface type, Operation. And the second and third are the values
    that will be used in the operation of type T, the generic declared type. Next, I'll set up a local variable, result,
    type T, and that's going to get the value that comes back from calling operate on the function instance, passing it
    value1 and value2. I'm going to print that result out and return it from this method. Ok, so what does all this set
    up do for me? Well, I want to use this calculator method, to do different operations, on different types of data.
    Let me set this up. I'll scroll up to the end of the main method, and put the code in there.
*/
//End-Part-3

    public static <T> T calculator(Operation<T> function, T value1, T value2) {

        T result = function.operate(value1, value2);
        System.out.println("Result of operation: " + result);
        return result;
    }
}
