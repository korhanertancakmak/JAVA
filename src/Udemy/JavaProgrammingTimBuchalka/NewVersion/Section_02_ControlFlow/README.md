# ControlFlow Statements

In this section, we'll be learning about the switch statement which, 
like the if then else statement, 
allows us to execute different code blocks on different conditions. 
Also, we'll be looking at 4 additional statements:
* the **switch statement**,
* the **for statement**, 
* the **while statement**, and 
* the **do while statement**, 

which are used to repeat code segments based on conditions.

## The switch statement

The syntax for the switch statement is quite a bit different from it is for the if statement.

```java  
switch(value) {
    case x:
        // code for value == x
        break;
    case y:
        // code for value == y
        break;
    default:
        // code for value not equal to x or y
}
```                 

The case keywords, as shown above, are used to with the switch statement for comparison. 
So, switch value, case x, essentially means, in the case that value equals x, 
execute this code. 
If it doesn't, then move on to check the next case. 
The break keyword tells the switch statement to terminate any further checks. 
It's technically optional, and we'll talk about it more shortly. 
And, as you can probably guess, the default keyword means 
if none of the above cases were true, execute this code.

Valid switch value types are: 

* byte, short, int, char
* Byte, Short, Integer, Character
* String
* enum
    
But, importantly, note that the primitive types of boolean, long, 
float and double cannot be used. 
They'll result in an error in your code if you try to use them.

### Fall through in a switch statement

Once a switch case label matches the switch variable, no more cases are checked. 
Any code after the case label where there was a match found, will be executed 
until a break statement, or the end of the switch statement occurs. 
Without a break statement, execution will continue to fall through any case labels 
declared below the matching one, and execute each case's code.

### Enhanced Switch Statement

Enhancing the switch statement and Java has introduced new syntax for the switch 
as shown from the old one here;

```java  
switch (switchValue) {
    case 1:
        System.out.println("Value was 1");
        break;
    case 2:
        System.out.println("Value was 2");
        break;
    case 3: case 4: case 5:
        System.out.println("Was a 3, a 4, or a 5");
        System.out.println("Actually it was a " + switchValue);
        break;
    default:
        System.out.println("Was not 1, 2, 3, 4 or 5");
        break;
}
```  

to new one here;

```java  
switch (switchValue) {
    case 1 -> System.out.println("Value was 1");
    case 2 -> System.out.println("Value was 2");
    case 3, 4, 5 -> {
        System.out.println("Was a 3, a 4, or a 5");
        System.out.println("Actually it was a " + switchValue);
    }
    default -> System.out.println("Was not 1, 2, 3, 4 or 5");
}
```  

When we compare these two statements, the first thing we notice is 
that the colon after each case label has been replaced with the arrow token as shown here. 
There are no breaks in the enhanced switch statement, 
the enhanced statement doesn't require them. 
Fall through, which we examined above, never occurs in the enhanced switch statement.
Also, notice that it's replaced the multiple case labels 
we had, case 3, case 4, and case 5, with a comma-delimited list of the values.

The previous version uses colons (:) to start a case block, "case 1: " for example, 
which makes it a traditional switch. 
This means it permits fall through to occur, and it can't be used as an expression. 
Therefore, in this type of switch, it's important to ensure you include a "break" statement 
in each case block, to prevent accidental fall through.
On the new version, where the arrow (->) token replaces the colon (:) in the case label, 
the break statement is part of the new syntax, and you shouldn't use it.

### Traditional Switch Challenge

In this challenge, we'll be using the NATO alphabet to replace a character or letter 
with NATO's standardized word for that letter.

* A = Able, B = Baker, C = Charlie, D = Dog, E = Easy, F = Fox, 
* G = George, H = How, I = Item, J = Jig, K = King, L = Love, 
* M = Mike, N = Nan, O = Oboe, P = Peter, Q = Queen, R = Roger, 
* S = Sugar, T = Tare, U = Uncle, V = Victor, W = William, 
* X = X-ray, Y = Yoke, Z = Zebra.

In radio transmissions, the word car, "C," "A," "R," would be read, 
"Charlie Able Roger," for clarity. 
We'll take a single character, and return the matching word from the 
NATO phonetic alphabet, shown above. We'll just do this for the letter A, 
through E.

To do this:

1. Create a new char variable.
2. Use the traditional switch statement (with a colon in case labels) that 
tests the value in the variable from Step-1.
   * Create cases for the characters, A, B, C, D, and E.
   * Display a message in each case block, with the letter and the NATO word, then break.
   * Add a default block, which displays the letter with a message saying not found.

### Another Switch Challenge

In this next challenge, we're going to use the enhanced switch expression. 
Let's look at these statements again, but this time, 
we're going to make the enhanced switch an expression by assigning it to a variable.

```java  
switch (month) {
    case "JANUARY":
    case "FEBRUARY":
    case "MARCH":
        return "1st";
    case "APRIL":
    case "MAY":
    case "JUNE":
        return "2nd";
    case "JULY":
    case "AUGUST":
    case "SEPTEMBER":
        return "3rd";
    case "OCTOBER":
    case "NOVEMBER":
    case "DECEMBER":
        return "4th";
}
return "bad";
```
        
The version on the above uses colons (:) to start a case block, 
which makes it a traditional switch. 
This means it permits fall through to occur, and cannot be used as an expression. 
But we can use it in a method, with return statements as we show above 
on the traditional version.

```java  
return switch (month) {
    case "JANUARY", "FEBRUARY", "MARCH" -> { yield "1st"; }
    case "APRIL", "MAY", "JUNE" -> "2nd";
    case "JULY", "AUGUST", "SEPTEMBER" -> "3rd";
    case "OCTOBER", "NOVEMBER", "DECEMBER" -> "4th";
    default -> {
        String badResponse = month + " is bad";
        yield badResponse;
    }
};
```

On the enhanced one, where the arrow (->) token replaces the colon (:) in the case label, 
you not only don't need the return statement, you can put the value being returned there. 
If you do use a code block as we show in the first case label, "{ yield "1st"; }", 
the keyword yield is required as shown.

### Day of the Week Challenge

1. Create a method called printDayOfWeek, that takes an int parameter called day, 
but doesn't return any values.

   * Use the enhanced switch statement to return the name of the day,
   based on the parameter passed to the switch statement, 
   so that 0 will return "Sunday," 1 will return "Monday," and so on. 
   Any number not between 0 and 6 should return "Invalid Day."
   * Use the enhanced switch statement as an expression, 
   returning the result to a String named dayOfTheWeek.
   * Print both the day variable and the dayOfTheWeek variable.

2. In the main method, call this method for the values 0 through 7.

3. Bonus: Create a second method called printWeekDay, that uses an if-then-else statement, 
instead of a switch, to produce the same output.

## The For Statement
                                                     
Looping lets us execute the code a multiple number of times. 
Java supports several statements for looping, or executing code repetitively.

* **for** statements: The for loop is more complex to set up but is commonly used 
when you are iterating over a set of values.
* **while** statements: The while loop executes until a specified condition becomes false.
* **do while** statements: The do while loop always execute at least one regardless of 
whether the loop condition is true or false, and then continue looping until a specified 
condition becomes false.

The for statement is often referred to as the for loop. 
It repeatedly loops something until a condition is satisfied.
When we look at its syntax:

```java  
for (init; expression; increment) {
    // block of statement
}
```
                                    
There are 3 parts of the basic for statement's declaration. 
These are declared in parentheses, after the "for" keyword, 
and are separated by semicolon(;). 
These parts are all optional and consist of the following:

* The initialization section declares or sets state, 
usually declaring and initializing a loop variable, before the loop begins processing.
* The expression section, once it becomes false, will end the loop processing.
* The increment section is executed after the expression is tested, 
and is generally the place where the loop variable is incremented.

## The Break Statement

A break statement transfers control out of an enclosing statement. 
We've seen the break statement in the switch statement, but it can also be used in any loop.

```java  
public static void main(String[] args) {
        for (int counter = 1; counter <= 5; counter++) {
            System.out.println(counter);
        }
        for (double rate = 2.0; rate <= 5.0; rate++) {
            double interestAmount = calculateInterest(10000, rate);
            System.out.println("10,000 at " + rate + "% interest = " + interestAmount);
        }
        for (double i = 7.5; i <= 10.0; i += 0.25) {
            double interestAmount = calculateInterest(100.00, i);
            if (interestAmount > 8.5) {
                break;
            }
            System.out.println("$100.00 at " + i + "% interest = $" + interestAmount);
        }
    }
    public static double calculateInterest(double amount, double interestRate) {
        return (amount * (interestRate / 100));
    }
```

## The for Statement Challenge Exercise

This challenge will use prime numbers, which is not a composite number, 
and which are the numbers we "canNOT" make them by multiplying other whole numbers. 
Another way to think of it, that a prime number is only divisible by itself, and one.

Create a prime number counter-variable that will keep count of 
how many prime numbers were found. 
Create a for statement, using any range of numbers, where the maximum number is <= 1000. 
For each number in the range.

* Check to see if it's a prime number using the isPrime method.
* If the number is prime, print it out and increment the prime number counter-variable.
* Once the prime number counter equals three, exit the loop (Hint, use the break statement to exit).

Your challenge is to create a for statement, using any range of numbers, 
to determine if the numbers are prime numbers. 
You'll use the isPrime method. 
If it's a prime number, print it out, and increment a count of the number of prime numbers found. 
If you get to the stage where 3 or more prime numbers are found, end the loop. 
In other words, you'll be iterating through the loop, 
but you've found three prime numbers before the range is fully processed. 
I want you to exit the for loop, and as a hint, use the break statement to exit. 
So you need to use an if statement to check the count, and use break to get out of the loop, 
even before it completes processing all the numbers in the range you picked.

## Sum 3 and 5 Challenge

Create a for loop using a range of numbers from 1 to 1000 inclusive. 
Sum all the numbers that can be divided by both 3 and 5. 
Print out the numbers that have met the above conditions. 
Break out of the loop once you have found 5 numbers that met the conditions. 
After breaking out of the loop, print the sum of the numbers that met the conditions.

Note: type all code in the main method

## The while and do while statements

What if you want to loop until some condition is met, 
that's not associated with a known range of values? 
Java has two types of while loops:

* while: Continues executing code block until the loop expression becomes false.
* do-while: Execute the code block once, then continue executing 
until the loop condition becomes false.

So, we've also stated several times that the for loop has three declaration 
parts in the parentheses, separated by semicolons. 
But looking at the while loop in comparison:

| The for statement                 | The while statement   | The do-while statement |
|-----------------------------------|-----------------------|------------------------|
| for (init; expression; increment) | while (expression {   | do {                   |
| // block of statements            | // block of statement | // block of statements |
| }                                 | }                     | } while (expression);  | 

## The Continue Statement

There's another statement that's important to all of these loops, 
and that's the continue statement. 
The continue statement, in its simplest form, will stop executing the current iteration 
of a block of code in a loop, and start a new iteration. 
In some cases, you'll have a loop where the majority of iterations in your loop 
meet the criteria, for which you want to do a lot of work. 
But you might have a couple of exceptions.
So rather than using a big if-then-else statement, you want to ignore a few iterations, 
and skip the code that would be normally executed, but keep the loop going.

```java  
public static void main(String[] args) {

        // basic for statement example
        for (int i = 1; i <= 5; i++) {
            System.out.println(i);
        }

        // while statement example
        int j = 1;
        while (j <= 5) {
            System.out.println(j);
            j++;
        }

        // while with break statement example
        int k = 1;
        boolean isReady = false;
        while (isReady) {
            if (k > 5) {
                break;
            }
            System.out.println(k);
            k++;
        }

        // do-while statement example
        int m = 1;
        do {
            if (m > 5) {
                break;
            }
            System.out.println(m);
            m++;
        } while (isReady);

        // while with continue statement example
        int number = 0;
        while (number < 50) {
            number += 5;
            if (number % 25 == 0) {
                continue;            // it doesn't process the number 25 and its multiple orders.
            }
            System.out.println(number + "_");
        }
    }
```

## The while loop challenge

Create a method called isEvenNumber, that takes a parameter of type int. 
Its purpose is to determine if the argument passed to the method is an even number or not. 
Return true from the method, if it's an even number, otherwise return false.
Next, use a while loop to test a range of numbers, from 5, up to and including 20, 
but printing out only the even numbers, determined by the call to the isEvenNumber method.

```java  
int i = 4;
while (i <= 20) {     
    i++;
    if (!isEvenNumber(i)) {
        continue;
    }
    System.out.println("Number " + i + " is an even number.");
}
if (countEvenNumbers == 5) {
    System.out.println("5 even numbers are already found.");
    break;
}
```

After that, modify the while code to make it also record the total number of even numbers 
it has found. 
Break out of the loop, once 5 even numbers are found. 
Finally, display the total number of odd and even numbers found.

## Digit Sum challenge

In this challenge, your task is to write a method, with the name sumDigits, 
that has a single parameter named number, of type int, and it should return an int. 
The method should only take a number that is a positive number. 
If a negative number is passed, it should return -1, meaning an invalid value was passed. 
The method should parse out each digit from the number, and sum the digits up.

So if 125 is the value passed to the method, the code should sum each digit, 
in this case, 1 + 2 + 5, and return 8, as a value. 
And another example, if the value is 1000, the code should sum each digit, 
1 + 0 + 0 + 0, and return 1 as a value. 
If the number is a single digit number, simply return the number itself as the result.

## Local Variables And Scope

In the past couple of courses, we've looked at many of Java's flow statements, 
the switch statement, the for statement, the while statement, as well as the do-while statement. 
Before that, we covered the if-else-then statement.

All of these statements may, and probably will, have their own code blocks. 
We've talked about code blocks quite a bit, but we haven't really talked 
about variables declared locally to many of these code blocks.

A local variable is called local, because it is available for use by the code 
block in which it was declared. 
It is also available to code blocks that are contained by a declaring block.

```java  
{   // Starts on outer block - for example a method block
    int firstVariable = 5;
    int secondVariable = 10;
    if (firstVariable > 0) {  // flow statement block starts inner block
        // Inner block code has access to outer block's variable
            System.out.println(secondVariable);
    }
}
```
                    
In the example above, we have two variables declared at the start of a code block.
We use the firstVariable in an if-then statement expression, 
and can print the secondVariable inside the if statement block. 
The if block is contained by the method block, and has access to the method block's 
variables as this demonstrates. 
This accessibility is also known as "variable scope."

Scope describes the accessibility of a variable. 
"In scope" means the variable can be used by an executing block or any nested blocks. 
"Out of scope" means the variable is no longer available.

Local variables are always in scope, in the block they are declared. 
They are also in scope for any nested blocks, or blocks contained within the outer block. 
So for example, a method block can declare local variables, and any flow statements, 
contained in the method block, will have access to the method's local variables. 
This is also true for the method parameters. Any code in the method, 
and any nested blocks, have access to the parameters. 
There's no limit to how deep you can nest code blocks, but generally, 
for readability and maintainability, consider replacing deeply nested blocks with method calls.

Local variables are always out of scope, for outer blocks, or the containing blocks 
they are declared in. 
Let's look at an example:

```java  
public static void aMethod(boolean aBoolean) {
    if (aBoolean) {
        int myCounter = 10;              // myCounter is local to this if block
    }
    System.out.println(myCounter);       // myCounter is out of scope here
}
```

In this example, we've declared a local variable called myCounter, inside the if block. 
This means myCounter will be out of scope for any containing blocks. 
The method block is the containing block in this instance. 
You can see that the "System.out.println" statement, outside the if block, 
can't use a variable declared inside the if block. 
This line of code will cause a compiler error. 
IntelliJ shows this error with, "Cannot resolve symbol 'myCounter'".

It is considered best practice:

* To declare and initialize variables in the same place if possible, in a single statement.
* To declare variables in the narrowest scope possible.

Declaring variables in the narrowest scope means 
if your variable is only used in a nested block, declare it there. 
Another example that we've seen, if you're using a variable only in a loop code block, 
like iteration variable, and won't be using it outside the loop block, 
declare it in the loop code, or in the for loop initialization block.

## Local Variables and the For Statement

In this "for" statement, as part of the declaration, there is an initialization part, 
as we've described. 
In this case, we declared a variable, i, that isn't accessible outside the loop.

```java  
{       // Starts on outer block - for example a method block

    for (int i = 1; i <= 5; i++) {     // i declared in for loop declaration
        System.out.println(i);
    }
    System.out.println(i);   // ERROR! i is out of scope.
}
```
                    
This is because any variables declared in the init section are local to the loop, 
meaning they exist and are accessible in memory, only while the loop is executed, 
and only to the loop code block. 
This is also true for most flow statements, for example, the if statement.

However, the switch statement is different from the if-then-else statement blocks. 
In the switch statement, a variable declared in one case label code block, 
can be accessed in another case label code block, but only if that block is 
after the declaration, and initialization of the variable.

## The class, the object, static & instance fields and methods

Local variables are a way to store and manipulate temporary data. 
In addition to local variables, we can set up data to be defined, 
and used as part of a class, or an object.

### Classes
        
A class can be described as:

* a custom data type.
* a special code block that contains method.

A class is like an empty form that gets copied and handed out. 
It describes information, or placeholders, for data that'll be filled in 
when that form is given to a unique individual.

The class may have a field for name; the object will have a value in the name field, 
which will be unique to the object. 
The class may have a field for address, the object will have values for the address field, 
and so on. 
The process of copying that empty form, and then delivering it to some process or 
person to fill in the blanks, is a loose analogy to what happens when you create an object. 
The empty form (the class) is the template for the data to be collected. 
The populated form(the object) may be completely different each time, 
because of the values used to fill in the data. 
But the data being collected each time, is determined by the class, or the form in this analogy.

### An Object

An object is called an instance of a particular class. 
We'll often use the word "instance" interchangeably with "object." 
This means an object is created by instantiating a class. 
There are multiple ways to do that, and we'll be talking about that more in a bit. 
There is no limit to the number of objects you can create from a class. 
A class is sometimes compared to a cookie cutter, and the cookies are your objects. 
The class provides a shape, or framework, that describes the object being created. 
A template is another word you'll hear used when trying to describe 
a class's relationship to an abject.

### Declaring and instantiating a new object from a class

The most common way to create an object is to use the new keyword. 
The new keyword creates an instance, and you can sometimes pass data, 
when creating an instance, to set up data on that object.

The String is special because we can create a String, just by using a literal which we've seen.

```java  
String s = "Hello";
```

But we could also use new:

```java  
String s = new String("Hello");
```

Like other data types, you can assign this objects memory location, also called a reference, 
to a local variable, as we've done with this String. We've assigned to the local variable s, 
an instance of String. 
All manipulation of the object's data and methods are then done using the local variable name.

In both of the statements for String above, we're creating a new object of type String, 
and initializing it with the text "Hello," and assigning it to a String variable named s. 
The second statement makes this a bit clearer.
When we create an object, we can pass initial data to be associated with it in parentheses.

We've stated previously that the class can be thought of as a special data type. 
This is because you can create variables on classes. 
These are called fields, or attributes, on the class or object. 
There are two ways to create fields on classes: 
One is with the "static" keyword, and one is without the "static" keyword.

### Static and Instance Fields

| Static Field                                                                  | Instance Field                                                                                                                                                              |
|-------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Requires "static" keyword when declared on the class                          | Omits 'static' keyword when declared on the class                                                                                                                           |
| Value of the field is stored in special memory location and only in one place | Value of the field is not allocated any memory and has no value until the object is created                                                                                 |
| Value is accessed by ClassName.fieldName Ex: Integer.MAX_VALUE                | Value is accessed by ObjectVariable.fieldName Ex: myObject.myFieldName (myObject is our variable name for an object we create and myFieldName is an attribute on the class) |

When the "static" keyword is used, it's called a static field on the class. 
This means the value of that field always stays with the class.
It's stored in a special memory location for values that aren't changing constantly, 
unlike local variables and objects. 
In our form analogy, this would be a field that is pre-populated on the form, 
and would not change for any of the copied forms. 
But unlike the form, this type of field in a class doesn't really get copied down to the object. 
It maintains its single value on the master copy, the class. 
The field in memory is accessed differently because we can access that field using 
the class name with dot notation, and we've done that already (Integer.MAX_VALUE). 
This data was stored on the class (INTEGER), and not on an instance of the class.

When the static key word isn't used, it's called an instance field. 
Until the class is instantiated, and an object created, the instance field has no place in memory. 
These instance fields can have different values for every instance created. 
This field is accessed using the variable name for the object, 
and the dot notation used with the field name. 
In the same way that there are static fields and instance fields, 
there are static methods and instance methods.

### Static and Instance Methods

| Static Method                                                                                                                                 | Instance Method                                                                                                                                                   |
|-----------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Requires "static" keyword when declared on the class                                                                                          | Omits 'static' keyword when declared on the class                                                                                                                 |
| Method is accessed by ClassName.methodName Ex: Integer.parseInt("123"); (A method called parseInt is called directly from the class, Integer) | Method is accessed by ObjectVariable.methodName Ex: "hello".toUpperCase(); (A method called toUpperCase is called on the instance of a String with value "hello") |

A static method can be called directly, using the Class name and dot notation. 
In other words, you don't need an instance to use this method.

An instance method requires an instance exists first, and the method be called on that instance. 
String class has many instance methods defined on it, which we can use to manipulate Strings. 
One of these is the toUpperCase method, which simply returns a String with all upper case letters, 
so the result of this call would be a new String, with the value "HELLO," all in capital letters.

The most important difference to remember right now is that to use an instance method, 
you have to create an instance, or object, first.

## Parsing Values and Reading Input using System.console()

We're going to create an interactive application, where a user will enter their name, 
and year of birth, and then the application will calculate the current age of the user. 
Before we start though, let's talk about parsing data.

When we read data in, from either a file, or from user input, 
it's common for the data to be initially a String, which we'll need to convert to a numeric value. 
Let's review what happens when our numeric data is really a String.
You might remember we talked about this previously when we talked about operators in Java. 
You may remember that the plus symbol means something different for numeric values; 
then it does for Strings. 
Also, most of the other operators aren't applicable to Strings.

| Operator | Numeric Types  | char           | boolean | String        |
|----------|----------------|----------------|---------|---------------|
| +        | Addition       | Addition       | n/a     | Concatenation |
| -        | Subtraction    | Subtraction    | n/a     | n/a           |
| *        | Multiplication | Multiplication | n/a     | n/a           |
| /        | Division       | Division       | n/a     | n/a           |
| %        | Remainder(Mod) | Remainder(Mod) | n/a     | n/a           |

Having our data in String variables means we can't even do basic math on our data. 
To use our data, we have to parse, or transform, that String data, 
and extract the numeric value from it. 
Since this is so common, Java provides ways to parse a String into a number. 
This is done using the Wrapper classes we've seen before.

## Wrapper methods to parse string to numeric values

You'll remember, we used the wrapper classes to get min and max values. In this case, 
we're going to use a static method, on the wrapper class, 
to let that class do the transformation for us.

| Wrapper | Wrapper Method      |
|---------|---------------------|
| Integer | parseInt(String)    |
| Double  | parseDouble(String) |

Let's look at this example:

```java  
String usersDateOfBirth = "1999";
int dateOfBirth = Integer.parseInt(usersDateOfBirth);
System.out.println("Age = " + (2023 - dateOfBirth);
```

Well, you can probably guess what "Integer.parseInt" doing, 
it's converting a String with a value of 1999, and returning 
an integer with the value 1999. But what is this really?

Well, we know Integer is a class. 
It's not an object.
In this class, there's a static method called "parseInt,"
that takes a String, and returns an integer. 
And we just saw, to access static methods on a class, 
we have to use the Class name and the dot notation, 
and the name of the method, so in other words, 
what we're really doing, is we're calling a method on the class named Integer. 
We can only call static methods this way, so parseInt is a static method, 
on the class Integer.

So now that we've covered dealing with numeric values that are in String, 
let's talk about where that input might come from. 
This is often in the form of an input file, a console, or some kind of user interface.

When reading data from the console, we have some different options.

## Reading data from the console

* **System.in**: Like "System.out," Java provides System.in which can read input 
from the console or terminal. 
It's not easy to use for beginners, and lots of code has been built around it, 
to make it easier.
* **System.console**: This is Java's solution for easier support for reading 
a single line and prompting user for information.
Although this is easy to use, it doesn't work with I.D.E.'s 
because these environments disable it.
* **Arguments**: This is calling the Java program and specifying data in the call. 
This is very commonly used but doesn't let us create an interactive application in a loop in Java.
* **Scanner**: The Scanner class was built to be a common way to read input, 
either using System.in or a file. 
For beginners, it's much easier to understand than the bare-bones System.in.

For our final objective, and the challenges in following courses, 
we'll be using the Scanner class, which we can run directly in IntelliJ. 
So, next we'll set up 2 methods. 
We just want to set them up, and have them return a dummy value for the moment, 
as we plan out how we'll code the next parts. 
You'll remember we've done this before.

From the code below:

```java  
public static String getInputFromConsole(int currentYear) {
    String name = System.console().readLine("Hi, What's your name?");
    System.out.println("Hi " + name + ", Thanks for taking the course!");

    String dateOfBirth = System.console().readLine("What year were you born?");
    int age = currentYear - Integer.parseInt(dateOfBirth);

    return "So you are " + age + " years old";
}
```

We get "java.lang.System.console()" is null" error. 
So, when we try to run System.console() in IntelliJ, 
it's actually giving us a null value. 
Normally System.console() would return an object, that is a wrapper to System.in, 
but now we get this "exception." 
And unfortunately, we can't get this to work in IntelliJ's IDE. 
But what we can do is program our code, so that we handle this situation. 
This is done by what's called, "catching and handling this exception."

## What's an exception?

An exception is an error that happens in code. 
Some types of errors can be predicted and named. 
NullPointerException, which is the exception we saw when we tried to run our code, 
using System.console(), in IntelliJ, is an example of a named Java exception.

Java has many of these named exceptions, and if you go to the JDK's exception API page, 
you can see some of them listed on there. 
We'll be getting deeper into Exceptions later, but right now, 
we want to try a different approach if we do get this error. 
We can do this by setting up the code to catch the exception.

### Catching an exception

An exception is caught, first by creating a code block around the code, 
that might get the error. 
This is done with the try statement code block. 
The try statement actually has two code blocks.

```java  
try {
    // statements that might get errors
} catch (Exception e) {
    // code to 'handle' the exception
}
```

The first is declared directly after the "try" keyword, and this code block ends, 
and is followed by the declaration of the catch keyword. 
The "catch" keyword includes the declaration of variables, in parentheses, 
and then has its own block. 
In the main method below:

```java  
try {
    System.out.println(getInputFromConsole(currentYear));
} catch (NullPointerException e) {
    System.out.println(getInputFromScanner(currentYear));
}
```

We have the start of the try statement before the method call that 
we know throws a NullPointerException when we run it in IntelliJ. 
We finish the code block after that statement, and then add the "catch" keyword, 
which is required. 
This expects a declaration of the exception type, 
which we just said was NullPointerException. 
We also need to include a variable name. 
It's common practice to set this to e initially, 
but we can name this variable anything we want. 
And this needs to be a code block, and in that code block, 
we're just going to call the other method. 
Which, of course, we haven't yet implemented or built out.

So now, we're ready to code the second method, and use the Scanner class.

## The Scanner Class

The Scanner class is described as a simple text scanner, 
which can parse primitive types and strings. 
To use the Scanner class, we have to create an instance of the Scanner. 
You'll remember, this means we're creating an object of type Scanner. 
We'll use the key word, new, to do it.

The "new" keyword is used in what Java calls, a Class Instance Creation Expression. 
In its simple form, it's the word new, followed by the class name, and empty parentheses:

```java  
ClassName variableName = new Classname();
```

And often in many cases, we can pass parameters in the parentheses, as we saw with methods.

```java  
ClassName variableName = new Classname(argument1, argument2);
```

And we saw that we could do this with the String class, passing the text in the parentheses.

### Instantiating Scanner

For reading input from the console or terminal, we instantiate a scanner object using new, 
followed by the Scanner class name, and passing in System.in, in the parentheses.

```java  
Scanner sc = new Scanner(System.in);
```

For reading input from a file, we instantiate a scanner object using new, 
again with the Scanner class name, but pass in a File object, in the parentheses.

```java  
Scanner sc = new Scanner(new File("nameOfFileOnFileSystem"));
```

File is another class provided by Java for reading and writing files. 
We'll talk about file input later.

### Using the import Statement

We haven't talked about the import statement yet, 
but this statement lets us use classes from other people's code. 
In this case, Java provides a library of code, which includes the Scanner class, 
in a library called java.util.

```java  
import java.util.Scanner;
```

### Reading Input with Scanner

We're going to continue with our class and testing the Scanner method to read input, 
but we'll first write a validation method. 
This method should make sure that the date of birth the user enters 
should not be any later than the current year, which we have set to 2023.

We should also make sure that the year that's entered is greater than 
the current year minus 125 years, because we'll assume the oldest living human  
won't be older than 125 years old. 
This validation naturally addresses negative years as well. 
So let's create a new method called checkDate.
We'll pass it the current year we've set up, in the main method, 
and the date of birth the user entered.In this method, 
we'll parse that information with Integer.parseInt.

```java  
do {
    System.out.println("Enter a year of birth >= " +
                               (currentYear - 125) + " and <= " + currentYear);
    age = CheckData(currentYear, cs.nextLine());
    validDOB = age < 0 ? false : true;
} while (!validDOB);
```

After we created the method called CheckData, 
and implemented it to our new getInputFromScanner method, 
what happens if the user enters a non-numeric value. 
You can try "196c," and you can see that our application sort of crashes with an error, 
another exception called "NumberFormatException." 
We have no checks in place for this kind of bad data. 
So let's add that, but this time, let's try another example of the try statement.

```java  
do {
    System.out.println("Enter a year of birth >= " +
                               (currentYear - 125) + " and <= " + currentYear);
    try {
        age = CheckData(currentYear, cs.nextLine());
        validDOB = age < 0 ? false : true;
    } catch (NumberFormatException badUserData) {
        System.out.println("Characters not allowed!!! Try again.");
    }
} while (!validDOB);
```

So here, we're catching the exception, which we just saw, was NumberFormatException. 
Now the reason you create a variable, in the parentheses, of the catchphrase, 
is if you wanted to access information about the exception, you could use that variable. 
We'll explore that more later, when we cover exceptions much more thoroughly, 
but it's required to set up a variable this way, even if we aren't going to use it. 
In this case, all we're going to do is print an extra statement that they entered characters.

```java  
public static void main(String[] args) {
        int currentYear = 2023;
        System.out.println(getInputFromScanner(currentYear));
    }

    public static String getInputFromScanner(int currentYear) {
        Scanner cs = new Scanner(System.in);
        System.out.println("Hi, What's your name? ");
        String name = cs.nextLine();
        System.out.println("Hi " + name + ", Thanks for taking the course!");
        System.out.println("What year were you born? ");

        boolean validDOB = false;
        int age = 0;
        do {
            System.out.println("Enter a year of birth >= " +
                    (currentYear - 125) + " and <= " + currentYear);
            try {
                age = CheckData(currentYear, cs.nextLine());
                validDOB = age < 0 ? false : true;
            } catch (NumberFormatException badUserData) {
                System.out.println("Characters not allowed!!! Try again.");
            }
        } while (!validDOB);
        return "So you are " + age + " years old";
    }

    public static int CheckData(int currentYear, String dateOfBirth) {
        int dob = Integer.parseInt(dateOfBirth);
        int minimumYear = currentYear - 125;
        if ((dob < minimumYear) || (dob > currentYear)) {
            return -1;
        }
        return (currentYear - dob);
    }
```

## Reading User Input Challenge

In this challenge, you'll read five valid numbers from the console, 
entered by the user, and print the sum of those five numbers. 
You'll want to check that the numbers entered are valid integers. 
If not, print out the message "Invalid number" to the console, 
but continue looping until you do have five valid numbers.

Before the user enters each number, prompt them with the message, "Enter number #x:", 
where x represents the count, 1, 2, 3, etc. 
And as an example there, the first message would look something like:

```java  
Enter number #1:
```

the next,

```java  
Enter number #2:
```

And so on. The hints for doing this are:

* Firstly, use a while loop, or a "do while" loop.
* Use a Scanner object, and the nextLine method, to read input as a String.
* Use Integer.parseInt, as we did in the previous courses.
* You'll need some local variables to keep track of the count of valid integers, 
as well as the sum of the integers.

## Min and Max Challenge

This is the minimum and maximum challenge.
This challenge is similar to the last one in some ways. You'll be using an endless loop which:

* Prompts the user to enter a number, or any character to quit.
* Validates if the user-entered data really is a number, 
you can choose either an integer, or double validation method.
* If the user-entered data is not a number, quit the loop.
* Keep track of the minimum number entered.
* Keep track of the maximum number entered.

If the user has previously entered a set of numbers (or even just one), 
display the minimum and maximum number, which the user entered. 
So, you want to create a loop that continues to process 
until the user enters non-numeric data. 
You'll prompt the user to enter a number, or type a character to quit each iteration. 
After the user enters some data, you'll read the input as a String, 
and then test if it can be parsed to a number.

You can decide if you want to solicit integers or decimal numbers from the user. 
If the user entered a valid number, you'll want to see if it is less than 
what you have for a minimum number, and if it is, you'll set that to the current number. 
You'll do the same check for the maximum number. 
For example, after one valid numeric entry, minimum and maximum numbers 
should be the same number.

![Step-3]()

![Step-3]()

![Step-3]()


```java  

```
```java  

```
```java  

```
```java  

```
```java  

```
```java  

```
```java  

```