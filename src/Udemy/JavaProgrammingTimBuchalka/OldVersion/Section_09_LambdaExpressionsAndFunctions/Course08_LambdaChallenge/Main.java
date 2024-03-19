package CourseCodes.OldSections.Section_09_LambdaExpressionsAndFunctions.Course08_LambdaChallenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        // Challenge-1: Write the following anonymous class as a lambda expression
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                String myString = "Let's split this up into an array";
                String[] parts = myString.split(" ");
                for (String part: parts) {
                    System.out.println(part);
                }
            }
        };

        // Challenge-1 Solution
        Runnable runnable1 = () -> {
            String myString = "Let's split this up into an array";
            String[] parts = myString.split(" ");
            for (String part: parts) {
                System.out.println(part);
            }
        };

        // Challenge-2: Write the following method as a lambda expression.You can assign it to anything.
/*
        public static String everySecondChar (String source) {
            StringBuilder returnVal = new StringBuilder();
            for (int i = 0; i < source.length(); i++) {
                if (i % 2 == 1) {
                    returnVal.append(source.charAt(i));
                }
            }
            return returnVal.toString();
        }
*/
        // Challenge-2 Solution
        Function<String, String> lambdaFunction =  s -> {
            StringBuilder returnVal = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                if (i % 2 == 1) {
                    returnVal.append(s.charAt(i));
                }
            }
            return returnVal.toString();
        };

        // Challenge-3 : Right now, the function doesn't do anything. Write the code that will execute the function with an
        // argument of "1234567890".
        // Challenge-3 Solution
        System.out.println(lambdaFunction.apply("1234567890"));

        // Challenge-4 : Instead of executing this function directly, suppose we want to pass it to a method. Write a method
        // called everySecondCharacter that accepts the function as a parameter and executes it with the argument. It should
        // return the result of the function. For bonus points, don't hard-code the argument string with the method.


        // Challenge-5 : USing the bonus version, call the method with the lambdaFunction we created earlier and the string
        // "1234567890". Print the result returned from the method.
        // Challenge-5 Solution
        String result = everySecondCharacter(lambdaFunction, "1234567890");
        System.out.println(result);

        // Challenge-6 : Now write a lambda expression that maps to the java.util.Supplier interface. This lambda should
        // return the string "I love Java!" Assign it to a variable called iLoveJava.
        // Challenge-6 Solution
        Supplier<String> iLoveJava = () -> "I love Java!";

        // Challenge-7 : As with Function, the Supplier won't do anything until we use it. Use this supplier to assign
        // the string "I love Java!" to a variable called supplierResult. Then print the variable to the console.
        // Challenge-7 Solution
        String supplierResult = iLoveJava.get();
        System.out.println(supplierResult);

        // Challenge-8 : There are many interfaces in the java SDK, and sometimes we can use a lambda expression instead
        // of creating an instance that implements the interface we want to use. Given a specific interface, how can we
        // tell whether we can map a lambda expression to it? What's the criteria that has to be met?
        // Challenge-8 Solution : The interface has to be a functional interface. It can have only a single method that
        // must be implemented. A functional interface can contain more than one method, but all the methods but one must
        // have default implementations. Most of the time, the documentation for an interface will state whether it's a
        // functional interface.

        // Challenge-9 : With that in mind, can we use a lambda expression to represent an instance of the java.util.concurrent.Callable
        // interface? Hint: You'll have to check the documentation. As a Java developer, you have to be comfortable with
        // looking up and reading documentations.
        // Challenge-9 Solution : The Callable interface has only one method that has to be implemented, the call() method.
        // So we can use a lambda for it. The documentation also states that it's a functional interface.

        // Challenge-10 : Is the java.util.Comparator interface a functional interface?
        // Challenge-10 Solution : Yes it is. Despite containing over 10 methods, only one method has to be implemented,
        // compare(). Because of that, it's a functional interface.

        // Challenge-11 : Suppose we have the following list of the top 5 male and female names for 2015:
        List<String> topNames2015 = Arrays.asList(
                "Amelia",
                "Olivia",
                "emily",
                "Isla",
                "Ava",
                "oliver",
                "Jack",
                "Charlie",
                "harry",
                "Jacob"
        );
        // Write code to print the items in the list in sorted order, and with the first letter in each name upper-cased.
        // The name "harry" should be printed as "Harry" and should be printed after "Emily" and before "Isla". Use lambda
        // expressions wherever possible.

        // Challenge-11 Solution :
        List<String> firstUpperCaseList = new ArrayList<>();
        topNames2015.forEach(name -> firstUpperCaseList.add(name.substring(0,1).toUpperCase() + name.substring(1)));
        firstUpperCaseList.sort((s1, s2) -> s1.compareTo(s2));
        firstUpperCaseList.forEach(s -> System.out.println(s));

        // Challenge-12 : Change the code so that it uses method references. Remember that a method reference looks like
        // Class::MethodName
        // Challenge-12 Solution :
        firstUpperCaseList.sort(String::compareTo);
        firstUpperCaseList.forEach(System.out::println);

        // Challenge-13 : Now do the same thing(uppercase first letter, then sort and print the list) using a stream and
        // chain of stream operations.
        // Challenge-13 Solution :
        topNames2015
                .stream()
                .map(name -> name.substring(0, 1).toUpperCase() + name.substring(1))
                .sorted(String::compareTo)
                .forEach(System.out::println);

        // Challenge-14 : Instead of printing out the sorted names, print out how many names being with the letter 'A'
        // instead. Hint: You'll have to modify the stream chain, and you'll have to add another statement to print the
        // number of items.
        // Challenge-14 Solution :
        long namesBeginningWithA = topNames2015
                .stream()
                .map(name -> name.substring(0, 1).toUpperCase() + name.substring(1))
                .filter(name -> name.startsWith("A"))
                .count();

        System.out.println("Number of names beginning with A is: " + namesBeginningWithA);

        // Challenge-15 : Let's get back to the previous version of the code, when we were printing out the sorted names.
        // Let's suppose we want to debug what's going on when the chain is executed. Instead of printing out the names
        // at the end of the chain, maybe we're not sure the code that upper cases the first letter is working correctly.
        // Let's use peek() to print out the names after the map() method has executed.
        // What will the following code print to the console?
        topNames2015
                .stream()
                .map(name -> name.substring(0, 1).toUpperCase() + name.substring(1))
                .peek(System.out::println)
                .sorted(String::compareTo);
        // Challenge-15 Solution : Nothing. This chain doesn't contain a terminal operation. Remember that stream chains
        // are evaluated lazily. Nothing happens until a terminal operation is added to the chain. At that point, the chain
        // is executed.

        // Challenge-16 : Add a terminal operation to this chain so that the peek call will execute. Since the peek() call
        // is printing every item, try to do something else with the terminal operation. Don't print out the items again.
        // Challenge-16 Solution :
        topNames2015
                .stream()
                .map(name -> name.substring(0, 1).toUpperCase() + name.substring(1))
                .peek(System.out::println)
                .sorted(String::compareTo)
                .collect(Collectors.toList());
    }

    // Challenge-4 Solution
    public static String everySecondCharacter(Function<String, String> func, String source) {
        return func.apply(source);
    }
}












