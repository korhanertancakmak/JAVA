# Expressions, Statements, and More

Java Development Kit (JDK): is used to create Java programs.
After we have Java installed on our machines, 
let's open a command prompt on Windows, 
or a Terminal on Mac or Linux, to check our Java version, 
to make sure we are ready.

On Windows, you can press and hold the Windows key located next to the space bar, 
and then press the R key.  
This will take you to the 'Run' window, where you can type in cmd and hit the enter key.

On a Mac, you can press the Command key and space bar at the same time, 
to get to Spotlight, and then type in the Terminal.  
This should take you to the built-in terminal on your Mac.

For Ubuntu Linux, click on 'show applications,' 
in the bottom left of your screen.  
Then in the search bar, you can type in the Terminal and hit entering.

You could use a simple text editor to write Java code.

![Step-1]()

Most likely, you'll be using an integrated development environment, or IDE, to develop your Java code.

![Step-2]()

JShell became a standard component of the Java Developers Kit in Java 9.
It is what is known as a Read-Eval-Print-Loop interactive program (or REPL for short) 
which means it does pretty much just that:
* it **reads** the command or code segment we type in.
* it **evaluates** and executes the code, and often allows shortcuts to be used.
* it **prints** out the results of the evaluation or execution, without making the developer 
write code to output the results.   
* Lastly, it loops right back for more input (more code segments or commands).

JShell runs in a terminal (or on the command line for Windows) and is useful for quickly 
trying out new ideas.

JShell does not replace the need for an IDE.
It's just a handy tool to quickly get started with Java. 
We will be transitioning to an IDE later in the course.

## Statement

It's a complete command to be executed. 
It can include one or more expressions, and I'll be talking about expressions and related topics, 
as we progress through the course.

```java  
System.out.println("Hello World");
```

What we've typed above is a command to print some information to the screen, 
using syntax provided by the Java language.
We specified what we wanted Java to print in the parentheses and double quotes – in this case, 
the text "Hello World" – effectively we're telling Java to print out the words, 
as we've specified them in the quotes – "Hello World."
After we press the **enter** key,
the program executing the code should print that text to the screen 
– in this case, the output is printed on the very next line.

Typing forward slash and the word 'exit,' 
or forward slash with the shortcut text ex will end your JShell session if you get stuck.
An example would be /exit or /ex.

## Keywords

A keyword is **any one of a number of reserved words, 
that have a predefined meaning in the Java language.**

In Java syntax, all code is case-sensitive, and this includes keywords. 
As we'll soon see, an **int**, all in lowercase is not the same as **Int**, 
with a capital I. 
Here, an **int**, (all in lowercase) is a keyword in Java.

## Variables

They are a way to store information in our computer, 
that we define in a program, 
can be accessed by a name we give them, 
and the computer does the hard work, 
of figuring out where they get stored, 
in the computer's ***random access memory***, or RAM.

## Data Type

There are lots of different types of data, 
that we can define for our variables, 
some of which I've shown you in the keyword list previously.
Collectively, these are known as data types.
As you may have guessed, some data types are keywords in Java.  
When we get to the Object-Oriented features of Java, 
you will see that we have a lot of flexibility for creating our own data types, 
but we'll explore primitive data types, which are built into the Java language.

A declaration statement is used to define a variable by indicating the data type, 
and the name, then optionally to set the variable to a specific value.

An expression is a coding construct, that evaluates to a single value.

Java operators, or just operators, perform an operation (hence the term) on a variable or value.
Addition, Subtraction, Division, and Multiplication are four common ones that 
I feel sure you're familiar with, 
but there are lots more operators you will work with as we go through the course.

Remember the expression is the code segment on the right side of the equals sign in an 
assignment or declaration statement.
This code can be a simple literal value, like the number 5, or it can be a complex mathematical 
equation using multiple literal values and mathematical operators.

```java  
int myFirstNumber = (10 + 5) + (2 * 10);
```

Up until now, we've only used literal values in our expressions, 
and we've also used several operators, such as in the example above.

Java code is case-sensitive.
This includes not only keywords and language syntax, but variable names and data types as well.
myLastOne is not the same variable as MyLastOne with a capital M.
int in lowercase, is not the same as Int with the first letter capitalized, 
or INT, all in uppercase, etc.

Keywords need to be in lowercase.
And variables will always be exactly as you declare them, including capitalization.   
Remember that case matters in Java code!
The /vars command in JShell can help you identify any misspellings you may have made.

## Java's Primitive Types

The eight primitive data types in Java are shown in the table below, 
listed by the type of data stored for each:

| Whole number      | Real number (floating point or decimal) | Single Character | Boolean value |
|-------------------|-----------------------------------------|------------------|---------------|
| byte              | float                                   |    char          |    boolean    |
| short             | double                                  |                  |               | 
| int               |                                         |                  |               |
| long              |                                         |                  |               |

Consider these types as the building blocks of data manipulation.  
Remember that primitive data types are simply placeholders in memory for a value.

An integer is a whole number, meaning it doesn't contain a fractional element, or a decimal.
There's a specified range of values allowed for the int, which is true for most data types.
What this means is, that the allowable range of values is NOT infinite.
There's a defined minimum, and maximum value, for each numeric data type, 
meaning you can't assign a number bigger or smaller (outside of that range).

The plus sign, "+," when used in System.out.print will print different data types together 
as a single line of text. 
In the example:

```java  
System.out.print("Integer Minimum Value = " + myMinIntValue);
```

We want to print a label, before a numeric integer value.
Whatever follows the plus sign in System.out.print here, 
is converted to a String by Java, and concatenated to the String before it.
This is a perfectly valid syntax in Java.

## Classes

A class is a building block for object-oriented programming, 
and allows us to build custom data types.

## Wrapper Classes

Java uses the concept of a wrapper class, for all of its eight primitive data types.
A wrapper class provides simple operations, as well as some basic information about 
the primitive data type, which cannot be stored on the primitive itself.
We saw that MIN_VALUE, and MAX_VALUE, are elements of this basic information, for the int data type.

The primitive types, and their respective wrapper classes, are shown in the table below.

| Primitive | Wrapper Class |
|-----------|---------------|
| byte      | Byte          |
| short     | Short         |
| char      | Character     |
| int       | Integer       |
| long      | Long          |
| float     | Float         |
| double    | Double        |
| boolean   | Boolean       |

You can see there that in general, it's pretty straightforward to remember the wrapper class name  
for your primitive data type. 
It's the same name, but with an uppercase letter at the start.

The wrapper classes for char and int, Character and Integer respectively, 
are the only two that differ in name (other than that first capitalized letter) 
from their primitive types.

In the code we just reviewed, we were able to use MIN_VALUE, and MAX_VALUE, 
on the wrapper class Integer.

```java  
int myMinIntValue = Integer.MIN_VALUE;
int myMaxIntValue = Integer.MAX_VALUE;
```

To discover the minimum and maximum range of numbers, that can be stored in an int, 
as we saw when we printed out these values previously:

```java  
Integer Value Range (-2147483648 to 2147483647)
```

## Overflow and Underflow in Java

If you try and put a value larger than the maximum value into an int, 
you'll create something called an Overflow situation.
And similarly, if you try to put a value smaller than the minimum value into an int, 
you cause an Underflow to occur.
These situations are also known as integer wraparounds.

The maximum value, when it overflows, wraps around to the minimum value, 
and just continues processing without an error.
The minimum value, when it underflows, wraps around to the maximum value, and continues processing.
This is not usually behavior you really want, and as a developer, 
you need to be aware that this can happen, and choose the appropriate data type.

An integer wraparound event, either an overflow or underflow, 
can occur in Java when you are using expressions that are not a simple literal value.   
The Java compiler doesn't attempt to evaluate the expression to determine its value, 
so it DOES NOT give you an error.

Here are two more examples that will compile, and result in an overflow.  
The second example may be surprising.  
Even though we are using numeric literals in the expression, 
the compiler still won't try to evaluate this expression, 
and the code will compile, resulting in an overflow condition.

```java  
int willThisCompile = (Integer.MAX_VALUE + 1);
int willThisCompile = (2147483647 + 1);
```

If you assign a numeric literal value to a data type that is outside the range, 
the compiler DOES give you an error.   
We looked at a similar example previously.

```java  
int willThisCompile = 2147483648;
```

In Java, you cannot put commas in a numeric literal.
For example, the following is not valid syntax.

```java  
int myMaxIntTest = 2,147,483,647;
```

So Java provided an alternative way to improve readability, the underscore.

```java  
int myMaxIntTest = 2_147_483_647;
```

You can put the underscore anywhere you might want a comma, 
but you can't use an underscore at the start or end of the numeric literal.

## Size of Primitive Types and Width

The minimum value of a byte is -128.
The maximum value of a byte is 127.   
Given its small range, you probably won't be using the byte data type a lot.
The byte wrapper class is the Byte with a capital B.

The minimum value of a short is -32768.
The maximum value of a short is 32767.   
The short wrapper class is the Short with a capital S.

Size, or Width, is the amount of space that determines (or limits) 
the range of values we've been discussing:

| Data Type | Width(in bits) | Min Value   | Max Value  |
|-----------|----------------|-------------|------------|
| byte      | 8              | -128        | 127        |
| short     | 16             | -32768      | 32767      |
| int       | 32             | -2147483648 | 2147483647 |

A byte can store 256 numbers and occupies eight bits, and has a width of 8.
A short can store a large range of numbers and occupies 16 bits, and has a width of 16.
An int has a much larger range as we know, and occupies 32 bits, and has a width of 32.

The number 100, by default, is an int.
Java allows certain numeric literals to have a suffix appended to the value, 
to force it to be a different data type from the default type.
The long is one of these types. 
Its suffix is an 'L.'
This is one of the few instances Java is not case-sensitive, 
a lowercase 'l' or an uppercase 'L' at the end of a whole number means the same thing 
– the number is long.

How big is the difference in the range of values that a long can store, compared to the int?
You can see, from this table that the difference is quite significant.

| Data Type | Width(in bits) | Min Value            | Max Value           |
|-----------|----------------|----------------------|---------------------|
| int       | 32             | -2147483648          | 2147483647          |
| long      | 64             | -9223372036854775808 | 9223372036854775807 |

A numeric literal that exceeds Integer.MAX_VALUE must use the 'L' suffix.
We cannot create a numeric literal in Java, that exceeds Integer.MAX_VALUE, 
without using the 'L' suffix, we'll always get the error 'integer number too large.'

## Casting in Java

Casting means to treat or convert a number, from one type to another. We put the type we want the number to be, in parentheses like this:

```java  
(byte) (MyMinByteValue / 2);
```

Other languages have casting too, this is common practice and not just a Java thing.
So, what effect does int, being the default value, have on our code?
Looking at the scenarios we just looked at in summary, we know the following:
This statement works because the result is an int, and assigning it to an int variable is fine.

```java  
int myTotal = (myMinIntValue / 2);
```

This statement doesn't work, because the expression (myMinShortValue / 2) is an int, 
and an int can't be assigned to a short, because the compiler won't guess the result.

This statement works, because the result of (-128 / 2) is an int, 
but when calculations use only literal values, 
the compiler can determine the result immediately, 
and knows the value fits into a short.

```java  
short myNewShortValue = (-128 / 2);
```

And finally, this code works because we tell the compiler we know what 
we're doing by using this cast, and the compiler doesn't give an error.

```java  
short myNewShortValue = (short) (myNewShortValue / 2);
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


