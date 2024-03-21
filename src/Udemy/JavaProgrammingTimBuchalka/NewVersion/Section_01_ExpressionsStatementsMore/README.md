# [Section-1: Expressions, Statements, and More]()

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

![Step-1](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_01_ExpressionsStatementsMore/Images/01codeInNotepad.png?raw=true)

Most likely, you'll be using an integrated development environment, or IDE, to develop your Java code.

![Step-2](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_01_ExpressionsStatementsMore/Images/02HelloWorld.png?raw=true)

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

## [a. Statements, Keywords, Variables, Primitive Data Types]()

### Statements

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

### Keywords

A keyword is **any one of a number of reserved words 
that have a predefined meaning in the Java language.**

In Java syntax, all code is case-sensitive, and this includes keywords. 
As we'll soon see, an **int**, all in lowercase is not the same as **Int**, 
with a capital I. 
Here, an **int**, (all in lowercase) is a keyword in Java.

### Variables

They are a way to store information in our computer 
that we define in a program, 
can be accessed by a name we give them, 
and the computer does the hard work 
of figuring out where they get stored 
in the computer's ***random access memory***, or RAM.

### Java's Primitive Types

There are lots of different types of data 
that we can define for our variables, 
some of which I've shown you in the keyword list previously.
Collectively, these are known as data types.
As you may have guessed, some data types are keywords in Java.  
When we get to the Object-Oriented features of Java, 
you will see that we have a lot of flexibility for creating our own data types, 
but we'll explore primitive data types, which are built into the Java language.

A declaration statement is used to define a variable by indicating the data type, 
and the name, then optionally to set the variable to a specific value.

An expression is a coding construct that evaluates to a single value.

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
What this means is that the allowable range of values is NOT infinite.
There's a defined minimum, and maximum value, for each numeric data type, 
meaning you can't assign a number bigger or smaller (outside of that range).

The plus sign, "+," when used in System.out.print will print different data types together 
as a single line of text. 
In the example:

```java  
System.out.print("Integer Minimum Value = " + myMinIntValue);
```

We want to print a label before a numeric integer value.
Whatever follows the plus sign in System.out.print here, 
is converted to a String by Java, and concatenated to the String before it.
This is a perfectly valid syntax in Java.

## [b. Wrapper Classes]()

A class is a building block for object-oriented programming, 
and allows us to build custom data types.

Java uses the concept of a wrapper class for all of its eight primitive data types.
A wrapper class provides simple operations, as well as some basic information about 
the primitive data type, which cannot be stored on the primitive itself.
We saw that MIN_VALUE, and MAX_VALUE, are elements of this basic information for the int data type.

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

To discover the minimum and maximum range of numbers that can be stored in an int, 
as we saw when we printed out these values previously:

```java  
Integer Value Range (-2147483648 to 2147483647)
```

### Overflow and Underflow in Java

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

### Size of Primitive Types and Width

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

How big is the difference in the range of values that a long can store compared to the int?
You can see from this table that the difference is quite significant.

| Data Type | Width(in bits) | Min Value            | Max Value           |
|-----------|----------------|----------------------|---------------------|
| int       | 32             | -2147483648          | 2147483647          |
| long      | 64             | -9223372036854775808 | 9223372036854775807 |

A numeric literal that exceeds Integer.MAX_VALUE must use the 'L' suffix.
We cannot create a numeric literal in Java that exceeds Integer.MAX_VALUE, 
without using the 'L' suffix, we'll always get the error 'integer number too large.'

## [c. Casting in Java](https://github.com/korhanertancakmak/JAVA/tree/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_01_ExpressionsStatementsMore#c-casting-in-java)

Casting means to treat or convert a number from one type to another. 
We put the type we want the number to be, in parentheses like this:

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

This statement doesn't work because the expression (myMinShortValue / 2) is an int, 
and an int can't be assigned to a short, because the compiler won't guess the result.

This statement works because the result of (-128 / 2) is an int, 
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

### Floating-Point Numbers

Unlike whole numbers, floating-point numbers have fractional parts that 
we express with a decimal point.
In this table, you can see some examples of both whole numbers and 
floating point numbers, in comparison.

| Whole number Examples | Floating Point Examples | 
|-----------------------|-------------------------|
| 3                     | 3.14159                 |
| 100000                | 10.0                    |
| -2147483649L          | -0.666666666666666667   |

Floating-point numbers are also known as real numbers.

We use a floating-point number when we need more precision in calculations.
There are two primitive types in Java for expressing floating-point numbers, 
the float and the double.
The double is Java's default type for any decimal or real number.
Precision refers to the format and amount of space occupied by the relevant type.
This table shows the width of each of the floating point types and their ranges.
The ranges are shown in Java's scientific notation, which we show below in blue color.

| Data Type    | Width (in bits)       | Data Type    | Width (in bits)         |
|--------------|-----------------------|--------------|-------------------------|
| float        | 32                    | 1.4E-45      | 3.4028235E38            |
| double       | 64                    | 4.9E-324     | 1.7976931348623157E308  |

You can see the e-notation followed by either a positive or negative number.

**Important**: The double data type is Java's default type for real numbers.
For example, any number with a decimal is a double.  
So, 10.5 is a double by default in Java.
The double data type can be specified as a numeric literal with a suffix of either 
lowercase 'd', or uppercase 'D', but because doubles are the default 
in Java, the suffix is optional to use.
On the other hand, the float data type can be specified as a numeric literal with 
a suffix of lowercase 'f', or uppercase 'F'.  
This suffix is required if you are assigning a real number to a variable that was 
declared with a float type.

Why should we choose double?
First, it's actually faster to process on many modern computers.
That's because computers have, at the chip level,
the functionality to actually deal with these double numbers faster than the equivalent float.
Second, the Java libraries that we'll get into later in the course, 
particularly math functions, are often written to process doubles and 
not floats, and to return the result as a double.
The creators of Java selected the double because it's more precise, 
and it can handle a larger range of numbers.

In general, float and double are great for general floating point operations.
But neither should be used when precise calculations are required.
This is due to a limitation with how floating point numbers are stored, 
and not a Java problem as such.
Java has a class called BigDecimal that overcomes this.

### String Literal Example

If you recall, we've used literal strings before, and that's 
where we've typed some text in double quotes.

```java  
jshell> System.out.print("Hello World");
```

We haven't really used a String variable yet, 
but we'll be doing that in upcoming videos.

| char                               | String                            | 
|------------------------------------|-----------------------------------|
| Holds one, and only one, character | Can hold multiple characters      |
| Literal enclosed in Single Quotes  | Literal enclosed in Double Quotes |

This table is a quick summary of the differences between the char and the String.

Why would you want to use a variable that only allows you to store one character?
One example might be to store the last key pressed by a user in a game.
Another example might be to loop programmatically through the letters in an alphabet.

### char Data Type

A char occupies two bytes of memory, or 16 bits, and thus has a width of 16.
The reason it's not just a single byte is that a char is stored as a 2-byte number, 
similar to the short.  
This number gets mapped to a single character by Java.
So, when you print a char, you will see the mapped character, 
and not the representative number.
And you can use single quotes and a character literal to assign a value to a char, 
which is much simpler than looking up the representative number.

### Unicode

Unicode is an international encoding standard for use with different languages 
and scripts by which each letter, digit, or symbol is assigned a unique numeric value 
that applies across different platforms and programs.
In the English alphabet, we've got the letters A through Z, 
meaning only 26 characters are needed in total to represent the entire English alphabet.
But other languages need more characters, and often a lot more.

There are three ways to assign a value to a char. 
Each of these methods represents storing the letter, capital D, in memory.

| Assignment Type     | Example Code            | 
|---------------------|-------------------------|
| a literal character | char myChar = 'D';      |
| a Unicode value     | char myChar = '\u0044'; |
| an integer value    | char myChar = 68;       |

There are a lot more characters, which are not on the usual keyboard, 
that can be output by this method, for example, the copyright symbol.
But if you are testing this out on your own, using Windows, 
you should be aware that JShell may give you an unexpected result, 
because UTF-8 is not supported, by default, for command line operations.
I point it out here, in case you are being adventurous, and do encounter this problem.  
Again, this is only a problem with JShell and Windows users.
This won't be a problem in Java or IntelliJ, or if you're using MAC or Linux.

### Boolean Primitive Type

A boolean value allows for two opposite choices, true or false, yes or no, one or zero.
In Java terms, we've got a boolean primitive type, and it can be set to two values only, 
either true or false.
The wrapper for boolean is Boolean with a capital B.

Developers will often use the word, is, as a prefix for a boolean variable name.
This creates a name that seems to ask a question, which makes reading the code more intuitive.
But other prefixes can be just as valid.

Here are some example boolean variable names, such as "isMarried" and "hasChildren"; 
that clearly define what condition is being tested:

| Boolean variable name examples |
|--------------------------------|
| isCustomerOverTwentyOne        |
| isEligibleForDiscount          |
| hasValidLicence                |
| isMarried                      |
| hasChildren                    |

![Step-3](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_01_ExpressionsStatementsMore/Images/03HandlingData.png?raw=true)

This schema demonstrates that most Java programs use some combination of the data 
types shown in this diagram.
You'll use Java's primitive data types, Java's built-in classes, 
and probably some combination of your own custom classes and somebody else's.

To execute multiple lines of code as a set, in JShell, first start with an opening curly brace and press enter.

```java  
jshell> {       // start with a curly opening brace
    ...>    first_statement;
    ...>    second_statement;
    ...>    third_statement;
    ...> }      // end with a curly closing brace
```

JShell will display an alternate prompt as you can see, three dots and a greater than sign.
You can add a statement and press enter until you've added as many statements as you want to run.
Finally, add the closing curly brace, noting that a semicolon is not required after this brace.
Once you press enter after the closing brace, all of your statements will run in the order you put them.

There are two ways to execute multiple statements in JShell:
* Put your statements on a single line.
* Or, enclose your statements in a set of curly braces {}.

### String Concatenation

In Java, the + symbol is an operator that can mean addition if used for numbers.
But it also means concatenation when applied to a String.
A String "+" anything else, gives us a String as a result, 
concatenating anything after the String as text to the initial String.

NOTE: ***Strings are Immutable.***

Immutable means that you can't change a String after it's created.
So in the case of the code we've written, 
the value 120.47 is technically not appended to the current contents of **lastString**.

```java  
lastString = lastString + myInt;
```

Instead, a new String is created automatically by Java. 
The new String consists of the previous value of **lastString**, 
plus a textual representation of the double value 120.47.
The net result is that our variable, **lastString**, has the concatenated value. 
However, Java created a new String in the process, 
and the old one will get discarded from memory automatically.

### String vs StringBuilder

The String class is immutable, but can be used much like a primitive data type.
The StringBuilder class is mutable, but does not share the String's special features, 
such as being able to assign it a String literal or use the + operator on it.
Both are classes, but the String class is in a special category in the Java language.
The String is so intrinsic to the Java language, it can be used like a ninth primitive type.
But it's not a primitive type at all, it's a class.

## [d. Operators](https://github.com/korhanertancakmak/JAVA/tree/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_01_ExpressionsStatementsMore#d-operators)

So what are operators?
Operators in Java are special symbols that perform specific operations on one, 
two, or three operands, and then return a result.
In the example below, we used the plus or addition operator, as well as the 
multiplication operator.

```java  
long longTotal = 50000L + 10L * (byteValue + shortValue + intValue);
```

But there are many other operators in Java.
So what is an operand?
An operand is a term used to describe any object that is manipulated by an operator.
So if we consider this:

```java  
int myVar = 15 + 12;
```

The plus here is the operator, and 15 and 12 are the operands. 
Variables used instead of literals can also be operands. 
In the previous example above, byteValue, shortValue and intValue are operands, 
as are the numeric literals.

### Expressions

What's an expression?
An expression is formed by combining variables, literals, method return values, 
(which we haven't covered yet), and operators.
They are a way of forming and combining those values to produce a result.
In the last example above, 15 plus 12 is the expression, which returns the value of 27.
In the statement below, byteValue + shortValue + intValue is the expression.

```java  
int sumOfThree = byteValue + shortValue + intValue;
```

How many operators would you say are in this statement?

Well, we've actually got two, the equal operator, and the plus operator.

```java  
int result = 1 + 2; // 1 + 2 = 3
```

### Comments

Comments are ignored by the computer, and are added to a program 
to help describe something. 
Comments are there for humans to read. 
We use two forward slashes in front of any code, or on a blank line.
The computer, right through to the end of the line, ignores anything 
after the two forward slashes.
So aside from describing something about a program, comments can also be 
used to temporarily disable code.

```java  
int result = 1 + 2; 
int previousResult = result;
result = result - 1;
```

Before we move on, some of you may be wondering about the value now in the 
"**previousResult**" variable after the last statement.
To summarize, we assigned "**result**" to "**previousResult**", 
and then we changed the value of "**result**".  
But did this also change the value in "**previousResult**"? 
That is a good question.

### "+" Operator on char

You might remember that we said chars are stored as two byte numbers in memory.
When you use the plus operator with chars, it is these numbers in memory that get added together.
The character values don't get concatenated.

### "%" Remainder Operator

The % sign represents the remainder operator in Java.
The remainder operator goes by several other names: modulus, modulo or just plain mod for short.
The remainder operator returns the remaining value from a division operation.
If there is no remaining value, the result is 0.

| Division Result | Remainder Result | Explanation                                                                                      |
|-----------------|------------------|--------------------------------------------------------------------------------------------------|
| 10 / 5 = 2      | 10 % 5 = 0       | 10 can be divided evenly by 5, so there is no remainder.                                         |
| 10 / 2 = 5      | 10 % 2 = 0       | 10 can be divided evenly by 2, so there is no remainder.                                         |
| 10 / 3 = 3      | 10 % 3 = 1       | 10 can be divided evenly by 3, but we get 3 from the division which gives us 9 with 1 remaining. |
| 10 / 1 = 10     | 10 % 1 = 0       | Using 1 on the right side of the remainder operate will always give a result of 0.               |

### Abbreviating Operators

So, why use multiple statements in curly braces?
* First, it's a way to group statements together before executing them.
* It allows us to put statements on multiple lines which are more natural and readable.
* We can execute the group of statements as a whole, 
which more closely resembles running code in Java.

Incrementing by one is a widespread requirement in programming.
We can do the following:

```java  
result = result + 1;
```

But we also have two other shorthand ways to do this same thing.

| Shorthand (or Abbreviating) Operator     | Code Sample  |
|------------------------------------------|--------------|
| Post-fix Increment Operator              | result++;    |
| Compound Assignment Operator with + sign | result += 1; |


Decrementing by one is also widespread
We can decrement simply by using the equation:

```java  
reult = result -1;
```

But we also have two other shorthand ways to do this same thing.

| Shorthand (or Abbreviating) Operator     | Code Sample  |
|------------------------------------------|--------------|
| Post-fix Decrement Operator              | result--;    |
| Compound Assignment Operator with - sign | result -= 1; |

Using the code we have been using, either by scrolling up and editing the group of statements, 
or creating a new group.
* Initialize an **int** variable, named **result**, to the value of 10, rather than 1.
* Next, use the compound assignment operator, with the minus sign, 
to subtract a number from **result**, using a value of your choice.
* Print the result out, using the System.out.print statement.

When **result** is an **int**, the compound operator assignment

```java  
resutl -= 5.5;
```

give us a different result from what we expected, which was

```java  
result = result - 5.5;
```

The compound assignment operator

```java
x -= y;
```

it is often said to be

```java  
x = (data type of x) x -y;
```

But that's not entirely true if y is not the same data type as x.
An implicit cast is done when using this operator, so no error occurs, 
but unexpected results may occur.
The abbreviating operators we've discussed so far are:

| Shorthand Operator                  | Code Sample  |
|-------------------------------------|--------------|
| Post-fix Increment Operator         | result++;    |
| Post-fix Decrement Operator         | result--;    |
| Addition Compound Assignment        | result += 5; |
| Subtraction Compound Assignment     | result -= 5; |
| Multiplication Compound Assignment  | result *= 5; |
| Division Compound Assignment        | result /= 5; |

### Why do we need an Integrated Development Environment (IDE)?

An IDE is the easiest, least error-prone way to develop, 
manage and deploy Java classes. 
It provides many benefits to developers, including

* increased productivity,
* code completion,
* refactoring of code,
* debugging tools,
* version control,
* and team development, just to name a few. 
And don't worry if you don't understand 
some of these terms, they will make sense as we progress through the course.

I like to think that the jump from JShell to an IDE is like the jump from a typewriter 
to a word processing program on a modern computer.
It's unlikely that you will want to go back once you have experienced the benefits.

### What's IntelliJ?

IntelliJ IDEA is one of several IDEs available for Java.
It's also written in Java, developed by JetBrains, and simply known as IntelliJ.
JetBrains offers a free and open-source community edition.

The JDK is effectively a Software Development Kit, or SDK. 
Whatever you call it, it contains the tools you need to write programs.
The Java Development Kit includes the tools that enable the computer to understand your java code 
and to execute it. 
It also has a debugger, and we'll be seeing what that is and how to use it  
when we've written a program to debug.

## [e. Access Modifiers, Classes, Methods](https://github.com/korhanertancakmak/JAVA/tree/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_01_ExpressionsStatementsMore#e-access-modifiers-classes-methods)

### Access Modifiers

The public Java keyword is what's called an access modifier.
An access modifier allows us to define which parts of our code, 
or even someone else's code, can access a particular element.

### "class" Keyword

The **class** keyword is used to define a class. 
The class name will be the text following the keyword, so **FirstClass** in this case.
Notice the **left and right curly braces**, 
they are used to define the class code block, or class body.

```java  
public class FirstClass {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}
```

### What is a Method?

A method is a collection of statements that perform an operation.
We'll be using a special method called the main method, 
that Java looks for when running a program.
It's the entry point for any Java code, and Java looks for the main method 
to start and run the program when we use it.
You can also create your own methods, as you'll see later.

### If-then Statement

The **if-then** statement is the most basic of all the control flow statements.  
It tells your program to execute a certain section of code, only if particular 
tests evaluate to **true**.
This is known as **conditional logic**.
**Conditional logic** uses specific statements in Java to allow us to check a condition 
and execute certain code based on whether that condition (the expression) is **true** or **false**.

The assignment operator(=) assigns the value of an expression  
to the variable to the left of the operator.

```java  
Boolean isAlien = false;
```

So, isAlien is the variable in this case, and it's been set to false, 
which is the value of our expression.

The equality operator(==) tests to see if two operands are considered equal, 
and returns a boolean value.

```java  
If (isAlien == false) {
```

So, here **isAlien** is being tested against the value false.
Instead of using the if statement as we can see here, we should instead use a code block.

```java  
boolean isAlien = true;
if (isAlien == false)
    System.out.println("It is not an alien!");
    System.out.println("And I am scared of aliens");
```

### Code Block

A code block allows more than one statement to be executed, in other words, a block of code.  
The format is:

```java  
if (expression) {
    // put one or more statements here    
}
```

### Logical "AND" and "OR" Operators

The **and** operator comes in two flavors in Java, as does the **or** operator.
**&&** is the Logical **and** which operates on **boolean** operands, 
checking if a given condition is **true** or **false**.
The **&** is a bitwise operator working at the bit level.  
This is an advanced concept that we won't get into here.

Likewise, **||** is the Logical **or**, and again it operates on boolean operands,
checking if a given condition is **true** or **false**.
The | is a bitwise operator, which is also working at the bit level.
And just like the bitwise **and** operator, we won't be using it as much as their 
logical counterparts.
We'll almost always be using the logical operators.

```java  
int newValue = 50;
if (newValue = 50) {
    System.out.println("This is an error");    
}
```

As you can see, we've used the assignment operator (one equal sign) in the if statement.
What we need to do is to use the "equals to" operator (two equal signs).
This is what the code should look like:

```java  
int newValue = 50;
if (newValue == 50) {
    System.out.println("This is not an error");    
}
```

We're not assigning a value here, instead we want to test 
if the values are equal to each other.

### The "NOT"(!) Operator

The exclamation mark (!), or **NOT** operator, is also known as the Logical Complement Operator.
It can be used with a boolean variable, to test for the opposite value.

```java  
boolean isCar = false;
if (isCar) {}
```

In the code above, we are simply testing the value in the isCar variable, false in this case.

```java  
boolean isCar = false;
if (!isCar) {}
```

But if we use the NOT operator, we are testing for the opposite value of the isCar variable,
true in this case.
I'd generally recommend using the abbreviated form if your variables are booleans, for two reasons.
One, It's much harder to identify the error if you accidentally use an assignment operator.
Remember, IntelliJ won't flag this as an error when you're testing a boolean variable, 
so the only way you'll know you made this common mistake  
is by discovering the output from your code isn't what you expected.
Secondly, the code is more concise, and more concise code can often be more readable code.

### Ternary(?) Operator

Java officially calls it the Conditional Operator, has three operands, 
the only operator currently in Java that does have three.
The structure of this operator is:

```java  
operand1 ? operand2 : operand3
```

The **ternary** operator is a shortcut to assigning one of two values to a variable, 
depending on a given condition.
So think of it as a shortcut of the **if-then-else** statement. 
So far in the course, we've only discussed if-then and not else.  
I'll be discussing else in the next section when we go deeper into control blocks.
Consider this example:

```java  
int ageOfClient = 20;
String ageText = ageOfClient >= 18 ? "Over Eighteen" : "Still a kid";
System.out.println("Our client is " + ageText);
```

Operand one: **ageOfClient >= 18** in this case is the condition we're checking. 
It needs to return true, or false.

Operand two: **"Over Eighteen"** here is the value to assign to the variable ageText, 
if the condition above is true.

Operand three: **"Still a kid"** here is the value to assign to the variable ageText, 
if the condition above is false.

In this particular case, ageText is assigned the value "Over Eighteen," 
because ageOfClient has value 20, which is greater than or equal to 18.

Now it can be a good idea to use parentheses, like this example below, 
to make the code more readable, particularly in the ternary operator.

```java  
String ageText = (ageOfClient >= 18) ? "Over Eighteen" : "Still a kid";
```

In the first example we looked at in our code, 
we returned a boolean value from the ternary operation.

```java  
boolean isDomestic = makeOfCar == "Volkswagen" ? false : true;
```

This was a good way to demonstrate the ternary operator, but wouldn't be something you'd do when writing proper code.
A much simpler way to write this code is shown here:

```java  
boolean isDomestic = (makeOfCar != "Volkswagen");
```

You can see that this code has the same effect and is quite a bit easier to read.

### Java's Code Units

Writing code is similar to writing a document. 
It consists of special hierarchical units, which together form a whole.
These are:

* **The Expression** : An expression computes to a single value.
* **The Statement** : Statements are stand-alone units of work.
* **Code Blocks** : A code block is a set of zero, one, or more statements, 
usually grouped together in some way to achieve a single goal.

### Whitespaces

* It is any extra spacing, horizontally or vertically, placed around Java source code.
* It's usually added for human readability purposes.
* In java, all these extra spaces are ignored.

So Java treats code like this:

```java  
int anotherVariable = 50;myVariable--; System.out.println("myVariable = " +myVariable);
```

The same as code like this:

```java  
int anotherVariable = 50;
myVariable--;
System.out.println("myVariable = " +myVariable);
```

Code conventions for whitespace do exist, which you can refer to for more detail.
The Google Java Style Guide, which was seen previously in this course, 
has a section on whitespace, so refer to that for more information, 
and the link to that is again in the resources section of this video.

### Methods

Java's description of the method is:
A method declares executable code that can be invoked, 
passing a fixed number of values as arguments.
A method is a way of reducing code duplication.
A method can be executed many times with potentially different results  
by passing data to the method in the form of arguments.
One of the simplest ways to declare a method is shown on this slide.   
This method has a name, but takes no data in, and returns no data from the method 
(which is what the special word void means in this declaration).

```java  
public static void methodName() {
    // Method statements from the method body
}
```

To execute a method, we can write a statement in code, which we say is calling, 
or invoking, the method.
For a simple method like calculateScore, we just use the name of the method, 
where we want it to be executed, followed by parentheses, 
and a semicolon to complete the statement.
So for this example, the calling statement would look like the code shown here:

```java  
calculateScore();
```

Where we previously had empty parentheses after the method name, 
we now have method parameters in the declaration.

```java  
public static void methodName(p1type p1, p2type p2, {more}) {
    // Method statements from the method body    
}
```

Parameters and arguments are terms that are often used interchangeably by developers.
But technically, a parameter is the definition as shown in the method declaration, 
and the argument will be the value passed to the method when we call it.

To execute a method defined with parameters, you have to pass variables, values, 
or expressions that match the type, order and number of the parameters declared.
In the calculateScore example, we declared the method with four parameters, 
the first a boolean, and the other three of int data types.
So we have to pass first a boolean, and then three int values as shown in this example:

```java  
public static void main(String[] args) {
    boolean gameOver = true;
    int score = 800;
    int levelCompleted = 5;
    int bonus = 100;
    int finalScore = score;

    calculateScore(true, 800, levelCompleted, bonus);

    score = 10000;
    levelCompleted = 8;
    bonus = 200;
    finalScore = score;
    if (gameOver) {
        finalScore += (levelCompleted * bonus);
        System.out.println("Your final score was " + finalScore);
    }
}

public static void calculateScore(boolean gameOver, int score, int levelCompleted, int bonus) {
    int finalScore = score;
    if (gameOver) {
        finalScore += (levelCompleted * bonus);
        finalScore += 1000;
        System.out.println("Your final score was " + finalScore);
    }
}
```

We can delete the code part which is right after first calculateScore, 
and add the second method call.
But notice this time, I'm actually passing the values directly, 
This lets us delete all the extra code that was assigning new values to our variables.
Now, the other thing we could do here is, instead of mixing literal values and variables
in the arguments we're passing, we could just pass all literal values in that first call.
This lets us make this even more efficient by first deleting these variables,
because of course we don't need them anymore.
We'll pass values directly to the method call, so we'll update that first call to the calculate
Score method:

```java  
calculateScore(true, 800, 5, 100);
```

We've literally only got two lines that actually send the information needed to the method.
You can notice that now, we cleaned up a lot of code.
There was a lot of extra code we had in our main method, but now we're down to just two lines.

```java  
public static void main(String[] args) {
    calculateScore(true, 800, 5, 100);
    calculateScore(true, 10000, 8, 200);
}
```

But we can't pass the boolean type in any place, other than as the first argument, without an error.
The statement below would cause an error.

```java  
calculate(800, 5, 100, true);
```

And you can't pass only a partial set of parameters as shown here.
This statement, too, would cause an error.

```java  
calculate(true, 800);
```

So, similar to declaring a variable with a type, we can declare a method to have a type.
This declared type is placed just before the method name.
In addition, a return statement is required in the code block, as shown on the slide, 
which returns the result from the method.
We can get our method to do some calculations much like doing before.
But we can send the result of that calculation back to the code that called it.
We do this by declaring a data type before the method name,
much like we do for a variable, declaring a data type before the variable name.
Method return type is a declared data type for the data that will be returned from the method.
So similar to declaring a variable with a type, we can declare a method to have a type.

```java  
// Method return type is a declared data type for the data that
// will be returned from the method
public static dataType methodName(p1type p1, p2type p2, {more}) {
        // Method statements from the method body
        return value;
}
```

This declared type is placed just before the method name. 
In addition, a return statement is required in the code
block, as shown above, which returns the result from the method.
In previous examples, we declared the type to be void, 
which has the special meaning that no data would be returned
from the method.
An example of a method declaration with a return type is shown here.
In this case, the return type is an int.

```java  
public static int calculateMyAge(int dateOfBirth) {
        return (2023 - dateOfBirth);
}
```

This method will return an integer when it finishes executing successfully.
Being able to return a value from a method,
lets the calling code have a two-way conversation with the method code.
So we can calculate something, and send that value back to the code,
called the method in the first place.

So, what's a return statement?
Java states that a return statement returns control to the invoker of a method.
* The most common usage of the return statement is to return a value from a method.
* In a method that doesn't return anything, in other words, 
a method declared with void as the return type, a return statement is not required.
But in methods that do return data, a return statement with a value is required.

**=>** Is the method a statement or an expression?
Like some of the abbreviated operators we learned about, 
a method can be a statement or an expression in some instances.
Any method can be executed as a statement.  
A method that returns a value can be used as an expression, or as part of any expression.
**=>** What are functions and procedures?
Some programming languages will call a method that returns a value, 
a function, and a method that doesn't return a value, a procedure.  
You'll often hear function and method used interchangeably in Java.
The term procedure is somewhat less common, when applied to Java methods, 
but you may still hear a method with a void return type, called procedure.

So there are quite a few declarations that need to occur as we create a method.
This consists of:
* Declaring Modifiers. 
These are keywords in Java with special meanings, 
we've seen **public** and **static** as examples, but there are others.

* Declaring the return type.
  * **void** is a Java keyword meaning no data is returned from a method.
  * Alternatively, the return type can be any primitive data type or class.
  * If a return type is defined, the code block must use at least one return statement, 
  returning a value of the declared type or comparable type.
  * Declaring the method name. 
  Lower camel case is recommended for method names.
  * Declaring the method parameters in parentheses. 
  A method is not required to have parameters, 
  so a set of empty parentheses would be declared in that case.
  * Declaring the method block with opening and closing curly braces. 
  This is also called the method body.

Parameters are declared as a list of comma-separated specifiers, 
each of which has a parameter type and a parameter name (or identifier).
Parameter order is important when calling the method.
The calling code must pass arguments to the method, 
with the same or comparable type, and in the same order, as the declaration.
The calling code must pass the same number of arguments as the number of parameters declared.

When declaring a return type:
* **void** is a valid return type, and means no data is returned.
* Any other return type requires a return statement in the method code block.

If a method declares a return type, meaning it's not void, 
then a return type is required at any exit point from the method block.
Consider the method block shown here:

```java  
public static boolean isTooYoung(int age) {
    if (age < 21) {
        return true;
    }
}
```

So in the case of using a return statement in nested code blocks in a method, 
all possible code segments must result in a value being returned.
The following code demonstrates one way to do this:

```java  
public static boolean isTooYoung(int age) {
    if (age < 21) {
        return true;
    }
    return false;
}
```

One common practice is to declare a default return value at the start of a method, 
and only have a single return statement from a method, returning that variable, 
as shown in this example method:

```java  
public static boolean isTooYoung(int age) {
    boolean result = false;
    if (age < 21) {
        return true;
    }
    return result;
}
```

The return statement can return with no value from a method, 
which is declared with a void return type.
In this case, the return statement is optional, 
but it may be used to terminate execution of the method, 
at some earlier point than the end of the method block, as we show here:

```java  
public static void methodDoesSomething(int age) {
    if (age < 21) {
        return;
    }
    // Do more stuff here
}
```

A method is uniquely defined in a class by its name, 
and the number and type of parameters that are declared for it.
This is called the method signature.
You can have multiple methods with the same method name, 
as long as the method signature (meaning the parameters declared) are different.
This will become important later in this section, when we cover overloaded methods.

In many languages, methods can be defined with default values, 
and you can omit passing values for these when calling the method.
But Java doesn't support default values for parameters.
There are workarounds for this limitation, and we'll be reviewing those at a later date.
But it's important to state again, in Java, the number of arguments you pass, 
and their type must-match the parameters in the method declaration exactly.

Now that we're armed with knowledge about methods, we can revisit the main method  
and examine it again.
The main method is special in Java, because Java's virtual machine (JVM) looks for the method, 
with this particular signature, and uses it as the entry point for execution of code.

```java  
public static void main(String[] args) {
    // code in here
}
```

## [f. Method Overloading](https://github.com/korhanertancakmak/JAVA/tree/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_01_ExpressionsStatementsMore#f-method-overloading)

Method overloading occurs when a class has multiple methods with the same name, 
but the methods are declared with different parameters. 
So you can execute a method with one name, but call it with different arguments.

Java can resolve which method it needs to execute, based on the arguments being 
passed when the method is invoked. 
This technique lets us create methods with the same name, 
for many types and number of arguments, and the calling code 
does not have to sort out which method to call.

To the calling code, it looks like the method takes a variable set of arguments; 
when in truth, this isn't the case.
Instead, there are a variable number of methods with the same name,
and different sets of parameters defined, which can be called, 
and Java will figure out which one to execute.

A method signature consists of the name of the method, 
and the uniqueness of the declaration of its parameters.
In other words, a signature is unique, not just by the method name, 
but in combination with the number of parameters, their types, 
and the order in which they are declared.

A method's return type is not part of the signature. 
A parameter name is also not part of the signature.

The type, order and number of parameters, in conjunction with the name, 
make a method signature unique. 
A unique method signature is the key for the Java compiler  
to determine if a method is overloaded correctly.
The name of the parameter is not part of the signature, 
and therefore it doesn't matter, from Java's point of view,
what we call our parameters. 

The example below demonstrates some valid overloaded methods for the doSomething method:

```java  
public static void doSomething(int parameterA) {
    // method body
}
public static void doSomething(float parameterA) {
    // method body
}
public static void doSomething(int parameterA, float parameterB) {
    // method body
}
public static void doSomething(float parameterA, int parameterB) {
    // method body
}
public static void doSomething(int parameterA, int parameterB, float parameterC) {
    // method body
}
```
                
Parameter names are not important when determining if a method is overloaded. 
Nor are return types used when determining if a method is unique.

```java  
public static void doSomething(int parameterA) {
    // method body
}
public static void doSomething(int parameterB) {
    // method body
}
public static int doSomething(int parameterA) {
    return 0;
}
```

In the second method of the example above, we have the same number of parameters, 
and the same type, as the first doSomething method, and therefore this is not a 
valid overloaded method.
This will cause a compiler error. 
In the third method, we have the same name and parameter, 
but we have a different return type. 
But a return type is not used in the determination of whether a method signature is unique, 
and so, this method too will cause a compiler error, when it's in the same class as the first.

```java  
public static void main(String[] args) {
        int newScore = calculateScore("Korhan", 500);
        System.out.println("New score is " + newScore);

        calculateScore(75);
        calculateScore();

        System.out.println("New score is " + calculateScore("Korhan", 500));
        System.out.println("New score is " + calculateScore(10));
    }

    public static int calculateScore(String playerName, int score) {

        System.out.println("Player " + playerName + " scored " + score + " points ");
        return score * 1000;
    }

    public static int calculateScore(int score) {
        return calculateScore("Anonymous", score);
    }

    public static int calculateScore() {

        System.out.println("No player name, no player score. ");
        return 0;
    }
```
