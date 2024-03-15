# Exception Handling

## What are the Exceptions?

***Exception*** refers to situations that unexpectedly interrupt the normal flow of the program. For example, many errors may occur when reading a file, such as an error that the file is not on the hard disk, a connection error when connecting to the database, a connection error when calling a web service, or an error accessing a null object. Java has offered various opportunities to software developers to manage these erroneous situations that interrupt the normal flow. Thanks to this feature, Java programs can run consistently and safely.

In Java language, errors are called ***exceptions***. Exception, as the name suggests, indicates ***exceptional*** (***abnormal***) situations that occur during the execution of the program. In other words, errors that occur at ***runtime*** are called exceptions.

When we write a program, we write certain rules. The Java runtime runs our program according to these rules. Error is an exceptional (i.e. out of the rule) situation. In such a case, the Java environment does not know what to do and finds the solution by terminating the program. In short, when an error occurs, the program stops running. To prevent this, error management must be done well while writing the code.

![Exception Diagram](https://raw.githubusercontent.com/Kodluyoruz/taskforce/main/java102/exception/figures/exceptions-callstack.png)

What we mean by error management is not to prevent errors from occurring. Error handling, in its simplest terms, is to ensure that the program continues to run even if an error occurs during execution. This is possible in Java. Thanks to Java's error handling mechanism, the program runs normally; If an error occurs, an action is taken in accordance with the code we wrote (the user is informed of the error, the error is recorded, etc.) and then the program continues to run.

Java's error handling mechanism works as follows: If an exceptional situation occurs during the execution of the program, an object related to this situation is created and thrown with the ***throws*** statement. In such a case, the normal flow of the program is stopped and this error is waited to be caught. In order for the error to be caught, the code that causes the error must be written in the ***try-catch*** block. In this case, the Java runtime looks for a catch block that can catch the error that occurs, and if it finds it, this ***catch*** block is executed. Finally, if a ***finally*** block is written, this block is executed and the program continues its normal flow.

Java bugs are propagation bugs. This means that the error must be caught within the method where it occurs, otherwise the error is passed to the parent method (the calling method). As long as the error is not caught, it continues to be transferred to the next method. If we did not catch the error anywhere in the code we wrote, the error is transferred to the Java runtime. The Java runtime provides us with a default error trapping mechanism. This mechanism terminates the program when an error occurs. When an error occurs, it can be handled with two different situations.

* It is an option to take precautions at the point where the error occurs and ensure that the program continues running from where it left off after the error is taken under control. For example, you can create a file that is not in the folder you are trying to read and start the program from where it left off.

* When an error occurs, this error can be thrown upwards. The client side that listens to this thrown error can show the error to the user with an appropriate message. For example, the user can be informed on the frontend with a notification such as the TR number he/she is trying to query is missing or incorrect.

We have two types of errors.

* ***Unchecked Exceptions***: These are error types that cannot be known during the compilation of the program, but occur while the program is running. Because they occur at runtime, they are harder to reproduce and detect.

* ***Checked Exceptions***: Errors detected during the compilation phase. It is known in advance that these errors may occur. For example, opening a file is an operation that may cause an error in Java. Since it has been previously stated that this function may throw errors, the Java development platform asks us to take precautions accordingly while writing the code.

### Exception Hierarchy

Exceptions are also classes in Java. All of these classes are derived from the "Throwable" class.

![Exception Hierarchy](https://raw.githubusercontent.com/Kodluyoruz/taskforce/main/java102/exception/figures/exceptions-errorOccurs.gif)

***Throwable***: Exception is the class at the top of the hierarchy. All Exception classes inherit from it.

***Error***: Represents a serious error in the program. These are errors that occur outside the application transmitted by the JVM. These types of errors are also of the "Unchecked Exceptions" type. For example, if it gives a connection error when trying to connect to the database server, we can only understand this at run time.

***Exception***: It is the ATA class of all Exception subclasses, including user-defined Exception classes. All Exception errors except "RuntimeException" are of "Checked Exceptions" type. These error types are specified at the compilation stage. It expects us to write code taking these errors into consideration.

***RuntimeException***: Errors that occur in the application as a result of an invalid or incorrect operation. These are also launched by the JVM. It falls into the "Unchecked Exceptions" category. Because they only appear at run time. We have no chance of catching these errors during the compilation phase. For example, we can give examples of errors such as calling a function on a null object or trying to convert data that does not comply with the number format to a number.

## Using Try-Catch-Finally

There are 2 methods to manage error conditions.

* With try-catch blocks, we can control the error where it is expected to occur or

* By throwing the error to a higher point where it is called with the throws keyword, we force the solution to be done there.

### "try-catch-finally" Mechanis

In this method, first the lines of code that are predicted to cause errors and need to be followed are placed in the "try" block. If no error occurs, the requested operations will be completed and the code will continue without any problems, but when an error occurs, it will fall into the "catch" block.

The "catch" block is the block in which we program how to deal with the error we receive. This structure determines how our program will behave in case of errors, saves the necessary data even if it is to be closed, and ensures that the program is closed consciously or the system continues to operate by taking the necessary precautions in case of errors. In this way, our application will work more stable.

The use of the "try-catch" mechanism is costly. Therefore, it is necessary to use "try-catch" blocks where necessary.

```java  
public class DataConverter { 

    public int convertToInt(String numberAsText) {
        // In this example, we detect the error and take precautions at the point where it occurs.
        try { 
            int number = Integer.parseInt(numberAsText); 
            return number;
        } catch (NumberFormatException e) { 
            e.printStackTrace();
            // It is recommended that you log in this section.
            // Error tracking and log monitoring in corporate projects is very important for resolving errors.
        } catch (NullPointerException e) { 
            // You can open more than one catch block for different error types.
            e.printStackTrace();
        }
        return -1; 
    }

    public Date convertToDate(String dateAsText) throws ParseException { 
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        // In this example, we tried to convert date information in String form into Date data.
        // Since the parse function threw an error of type "ParseException", we forwarded this error to a parent where we called.
        return dateFormatter.parse(dateAsText); 
    }
}
```

The order of the "catch" blocks is important. Because it is operated according to its sorted form.

```java  
catch (NumberFormatException e) { 
    e.printStackTrace();
    // It is recommended that you log in this section.
    // Error tracking and log monitoring in corporate projects is very important for resolving errors.
} catch (NullPointerException e) { 
    // You can open more than one catch block for different error types.
    e.printStackTrace();
}
```

In the above example, the first thing to check is whether the error is of the "NumberFormatException" type. If the error received is not of this type, then the catch blocks below are checked one by one. The "catch" block is executed in whichever block the appropriate error occurs.

At the same time, these blocks should be sorted according to the order in the hierarchical structure of the Exception class.

```java  
catch (Exception e) {     
    ...
}
catch (NullPointerException e) {     
    ...
}
```

Since the most general "Exception" class is used in a "catch" block listed as in this example, every "Exception" that arrives will be caught by this block and that block will run. This will cause the behavior in the "NullPointerException" block to not work at all, that is, the application will not behave as expected when the "NullPointerException" is caught in the program.

If you do not need to take action according to certain error types, you can write a single "catch" block and ensure that all errors fall into the same "catch" block. In this, you need to specify an error type of type "Exception", which is the ATA class.

```java  
catch (Exception e) { 
    e.printStackTrace();
}
```

### "finally" Block

You can optionally add the "finally" code block after "try-catch". Whether the code block in the "try" block receives an error or not, the "finally" block is executed under all circumstances. Let's explain this with an example.

```java  
public int readIntFromKeyboard() { 
    Scanner scanner = new Scanner(System.in);
    // In this example, we detect the error and take precautions at the point where it occurs.
    try {
        String inputFromKeyboard = scanner.nextLine();
        int number = Integer.parseInt(inputFromKeyboard); 
        return number; 
    } catch (Exception e) {
            e.printStackTrace();
    } finally { // It must be executed whether there is an error or not.
        scanner.close();
    }    
    return -1; 
}
```

In the example above, we create an object of the "Scanner" class. This object receives the value entered from the keyboard. We convert the value we receive into an int type number. Whether or not there is an error during this transformation, we close the Stream that the "Scanner" class is listening to in the "finally" block.

### HackerRank-Excercise: Java Exception Handling (Try-catch)

## Throw Usage

### Throwing an Error with the "throw" Keyword

We could check the error and prevent the application from breaking with the "try-catch" method. Another method is to throw an error and check the error at the point where it is called.

```java  
public int indexOf(String value, String searchedText) throws BatuxException {
    if (value == null) { 
        throw new BatuxException("The incoming value cannot be null!"); 
    }

    return value.indexOf(searchedText); 
}
```

In the example above, we are trying to find at which index the searched expression in a String value is located. However, if the sent value is "null", we throw an error of our own error type that we created above, with the "throw" keyword.

```java  
import java.util.Scanner; 
public class CatchError { 
    public static void checkAge(int age) { 
        if (age < 18) { 
            throw new ArithmeticException("Wrong age");         
        }

        System.out.println("Exempt from The law !");     
    }

    public static void main(String[] args) { 
        Scanner scan = new Scanner(System.in); 
        System.out.print("Yaşınız : "); 
        int a = scan.nextInt(); 
        try {
            checkAge(a);
        } catch (ArithmeticException e) {             
            System.out.println(e.getMessage());
        }
    }
}
```

### HackerRank-Excercise: Java Exception Handling

### Creating a Custom Debug (Exception)

We can create our own error types by deriving them from the "Exception" ancestor class.

```java  
public class Stu { 
    private int id; 
    private String name; 
    
    public Stu(int id, String name) { 
        this.id = id; 
        this.name = name;     
    }

    public static Stu find(int id) throws StuException { 
        if (id == 123) { 
            return new Stu(123, "Mustafa Çetindağ"); 
        } else { 
            throw new StuException("Öğrenci Bulunamadı");         
        }
    }

    public int getId() { 
        return id;     
    }

    public void setId(int id) { 
        this.id = id;     
    }

    public String getName() { 
        return name;     
    }

    public void setName(String name) { 
        this.name = name;     
    }
}

public class StuException extends Exception {
    public StuException(String msg) {
        super(msg);
    }
}

public class JAVA.MyList.Main {
    public static void main(String[] args) {

        Stu s = null;
        try {
            s = Stu.find(22);
            System.out.println("ID : " + s.getId());
            System.out.println("Name : " + s.getName());
        } catch (StuException e) {
            System.out.println(e.getMessage());
        }

    }
}
```

### Specifying the Error in the Method Definition

If we are calling a method that may throw an error while writing a method, we must either catch this error with a ***try-catch*** block within the method or leave the error catching to a higher method. But in this case, we must specify in the definition of the method that the method can throw an error so that the calling method can be informed of this error. We do this with the ***throws*** statement. Let's look at the example:

```java  
public class Person { 
    private int age; 
    public void setAge(int age) throws IllegalArgumentException {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be less than zero!"); 
        }
        this.age = age; 
    }
}
```

As you can see, we stated in the method definition with the throws statement that the setAge() method is a method that may throw an error. Now the methods that call this method must catch this error or leave it to a higher method.

### Some Classes of Errors in Java

There are predefined error classes for some common errors in Java. Let's briefly examine the most common of these:

* ***ArithmeticException***: Indicates mathematical errors, especially division by zero.

* ***ArrayIndexOutOfBoundsException***: Throws when trying to access an element outside the range of an array.

* ***ClassCastException***: Throws on invalid type casts.

* ***IllegalArgumentException***: Throws when one of the parameters given to the method is incorrect.

* ***IndexOutOfBoundsException***: Throws for incorrect index accesses.

* ***NullPointerException***: Throws when an attempt is made to operate on variables that have not yet been assigned a value. It is one of the most common errors in Java. The checks we make to take precautions against this error are called null-check.

* ***NumberFormatException***: When we try to convert a String value to a numeric type, it is thrown if the String value does not represent a proper number.

* ***UnsupportedOperationException***: Throws when an attempt is made to perform an unsupported operation.

### Test

1. Which code blocks catch exceptions?

    a. try
    b. catch
    c. finally
    d. if

2. Which of these keywords should be used to rationally handle the exception thrown by the try block?
catch

    a. catch
    b. try
    c. finally
    d. throw

3. Which of the following keywords is used to create exceptions manually?

    a. throw
    b. finally
    c. try
    d. catch

4. Which of the following classes can catch all exceptions and errors that cannot be caught by the developer?

    a. RuntimeException
    b. error
    c. Exception
    d. ParentException

5. Which of the following classes is used to define exceptions?

    a. exception
    b. finally
    c. throw
    d. catch

Answers: 1.b, 2.a, 3.a, 4.b, 5.a
