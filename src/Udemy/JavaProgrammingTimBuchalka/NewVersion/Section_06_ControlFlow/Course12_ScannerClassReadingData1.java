package CourseCodes.NewSections.Section_06_ControlFlow;/*
Course-28

                                Parsing Values and Reading Input using System.console()

        In the last course, we talked about static and instance fields, and methods, on the class. We also talked about a
    concept called instantiating a class, which creates an object, or instance. We'll be using both of these features in
    this course.

        We're going to create an interactive application, where a user will enter their name, and year of birth, and then
    the application will calculate the current age of the user. Before we start though, let's talk about parsing data.

        When we read data in, from either a file, or from user input, it's common for the data to be initially a String,
    which we'll need to convert to a numeric value. Let's review what happens when our numeric data is really a String.
    You might remember we talked about this previously, when we talked about operators in Java. You may remember that the
    plus symbol, means something different for numeric values, than it does for Strings. Also, most of the other operators
    aren't applicable to Strings.

         Operator       Numeric types          char                boolean              String
            +           Addition            Addition                 n/a             Concatenation
            -           Subtraction         Subtraction              n/a                  n/a
            *           Multiplication      Multiplication           n/a                  n/a
            /           Division            Division                 n/a                  n/a
            %           Remainder(Mod)      Remainder(Mod)           n/a                  n/a

        Having our data in String variables means, we can't even do basic math on our data. To use our data, we have to
    parse, or transform, that String data, and extract the numeric value from it. Since this is so common, Java provides
    ways to parse a String into a number. This is done using the Wrapper classes we've seen before.

                                Wrapper methods to parse string to numeric values

        You'll remember, we used the wrapper classes to get min and max values. In this case, we're going to use a static
   method, on the wrapper class, to let that class do the transformation for us.

                        Wrapper        Wrapper Method
                        Integer       parseInt(String)
                        Double        parseDouble(String)

    Let's look at this example:

                String usersDateOfBirth = "1999";
                int dateOfBirth = Integer.parseInt(usersDateOfBirth);
                System.out.println("Age = " + (2023 - dateOfBirth);

    Well, you can probably guess what "Integer.parseInt" doing, it's converting a String with a value of 1999, and returning
    an integer with the value 1999. But what is this really?

        Well, we know Integer is a class. It's not an object. On this class, there's a static method called "parseInt",
    that takes a String, and returns an integer. And we just saw, to access static methods on a class, we have to use
    the Class name and the dot notation, and the name of the method, so in other words, what we're really doing, is we're
    calling a method on the class named Integer. We can only call static methods this way, so parseInt is a static method,
    on the class Integer.

        So now that we've covered dealing with numeric values that are in String, let's talk about where that input might
    come from. This is often in the form of an input file, a console, or some kind of user interface.

        When reading data from the console, we have some different options.

                                        Reading data from the console

 - System.in      : Like System.out, Java provides System.in which can read input from the console or terminal. It's not
                    easy to use for beginners, and lots of code has been built around it, to make it easier.
 - System.console : This is Java's solution for easier support for reading a single line and prompting user for information.
                    Although this is easy to use, it doesn't work with I.D.E.'s because these environments disable it.
 - Command Line
    Arguments     : This is calling the Java program and specifying data in the call. This is very commonly used but doesn't
                    let us create an interactive application in a loop in Java.
 - Scanner        : The Scanner class was built to be a common way to read input, either using System.in or a file. For
                    beginners, it's much easier to understand than the bare bones System.in.

        For our final objective, and the challenges in following courses, we'll be using the Scanner class, which we can
    run directly in IntelliJ. So, next we'll set up 2 methods. We just want to set them up, and have them return a dummy
    value for the moment, as we plan out how we'll code the next parts. You'll remember we've done this before.

        From the code below:

            public static String getInputFromConsole(int currentYear) {

                String name = System.console().readLine("Hi, What's your name?");
                System.out.println("Hi " + name + ", Thanks for taking the course!");

                String dateOfBirth = System.console().readLine("What year were you born?");
                int age = currentYear - Integer.parseInt(dateOfBirth);

                return "So you are " + age + " years old";
            }

    We get "java.lang.System.console()" is null" error. So, when we try to run System.console() in IntelliJ, it's actually
    giving us a null value. Normally System.console() would return an object, that is a wrapper to System.in, but now
    we get this "exception". And unfortunately, we can't get this to work in IntelliJ's IDE. But what we can do, is program
    our code, so that we handle this situation. This is done by what's called, "catching and handling this exception".

                                        What's an exception?

        An exception is an error that happens in code. Some types of errors can be predicted and named. NullPointerException,
    which is the exception we saw when we tried to run our code, using System.console(), in IntelliJ, is an example of a
    named Java exception.

        Java has many of these named exceptions, and if you go to the JDK's exception API page, you can see some of them
    listed on there. We'll be getting deeper into Exceptions later, but right now, we want to try a different approach,
    if we do get this error. We can do this by setting up the code, to catch the exception.

                                        Catching an exception
        An exception is caught, first by creating a code block around the code, that might get the error. This is done
    with the try statement code block. The try statement actually has two code blocks.

             try {
                    // statements that might get errors
             } catch (Exception e) {
                    // code to 'handle' the exception
             }

    The first is declared directly after the "try" keyword, and this code block ends, and is followed by the declaration
    of the catch keyword. The "catch" keyword includes the declaration of variables, in parentheses, and then has its own
    block. In the main method below:

            try {
                System.out.println(getInputFromConsole(currentYear));
            } catch (NullPointerException e) {
                System.out.println(getInputFromScanner(currentYear));
            }

    we have the start of the try statement, before the method call that we know throws a NullPointerException when we run
    it in IntelliJ. We finish the code block after that statement, and then add the "catch" keyword, which is required.
    This expects a declaration of the exception type, which we just said was NullPointerException. We also need to include
    a variable name. It's common practice, to set this to e initially, but we can name this variable anything we want. And
    this needs to be a code block, and in that code block, we're just going to call the other method. Which of course, we
    haven't yet implemented or built out.

        So now, we're ready to code the second method, and use the Scanner class.

                                                The Scanner Class

        The Scanner class is described as a simple text scanner, which can parse primitive types and strings. To use the
    Scanner class, we have to create an instance of Scanner. You'll remember, this means we're creating an object of type
    Scanner. We'll use the key word, new, to do it.

        The "new" keyword is used in what Java calls, a Class Instance Creation Expression. In its simples form, it's the
    word new, followed by the class name, and empty parentheses:

                                ClassName variableName = new Classname();

    And often in many cases, we can pass parameters in the parentheses, as we saw with methods.

                                ClassName variableName = new Classname(argument1, argument2);

    And we saw that we could do this with the String class, passing the text in the parentheses.

                                                Instantiating Scanner

        For reading input from the console or terminal, we instantiate a scanner object using new, followed by the Scanner
    class name, and passing in System.in, in the parentheses.

                                Scanner sc = new Scanner(System.in);

    For reading input from a file, we instantiate a scanner object using new, again with the Scanner class name, but pass
    in a File object, in the parentheses.

                                Scanner sc = new Scanner(new File("nameOfFileOnFileSystem"));

    File is another class provided by Java, for reading and writing files. We'll talk about file input later.

                                         Using the import Statement

        We haven't talked about the import statement yet, but this statement lets us use classes from other people's code.
    In this case, Java provides a library of code, which includes the Scanner class, in a library called java.util.

                                        import java.util.Scanner;


*/
import java.util.Scanner;
public class Course12_ScannerClassReadingData1 {
    public static void main(String[] args) {

        int currentYear = 2023;

        try {
            System.out.println(getInputFromConsole(currentYear));
        } catch (NullPointerException e) {
            System.out.println(getInputFromScanner(currentYear));
        }
    }

    public static String getInputFromConsole(int currentYear) {

        String name = System.console().readLine("Hi, What's your name?");  // ERROR! IDE's disable the console. IntelliJ disabled this to be used!
        System.out.println("Hi " + name + ", Thanks for taking the course!");  // But you can use the "Terminal" on below of the IntelliJ window!

        String dateOfBirth = System.console().readLine("What year were you born?");
        int age = currentYear - Integer.parseInt(dateOfBirth);

        return "So you are " + age + " years old";
    }

    public static String getInputFromScanner(int currentYear) {

        Scanner cs = new Scanner(System.in);

        System.out.println("Hi, What's your name? ");
        String name = cs.nextLine();

        System.out.println("Hi " + name + ", Thanks for taking the course!");

        System.out.println("What year were you born? ");
        String dateOfBirth = cs.nextLine();
        int age = currentYear - Integer.parseInt(dateOfBirth);

        return "So you are " + age + " years old";
    }
}
