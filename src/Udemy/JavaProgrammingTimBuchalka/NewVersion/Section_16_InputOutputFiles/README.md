# [Section-16. Input & Output in Java]()
<div align="justify">

Up until now, our code has stayed internal to the JVM.
At this point, we're going to branch out and start communicating
with the environment the JVM is running in.
We'll do this by taking advantage of resources.
Resources can be files, network connections, database connections, streams, or sockets.
We use these resources to interact with file systems, networks and databases, 
to exchange information.
It's impossible to access a resource without first needing to address exceptions,
whether we want to deal with them or not.
I've only briefly touched on this subject, using try catch blocks, and printing
a stack trace when an error occurs.
When you're dealing with external resources, exception handling becomes much more important,
so I'll be covering a lot more aspects of exception handling.
When you instantiate one of Java's file access classes,
Java will delegate to the operating system to open a file from the OS File System.
This then performs some or all of the following steps.

* First, it checks if the file exists.
* If the file exists, it next checks if the user 
or process has the proper permissions for the type of access being requested.
* If this is true, then file metadata is retrieved, and a file descriptor is allocated.
This descriptor is a handle to the opened file.
* An entry is made in the file table or file control block table of the operating system,
to track the opened file. 
* The file may be locked.
* The file may be buffered by the OS, meaning memory is allocated, 
to cache all or part of the file contents, to optimize read and write operations.
Java uses a file handle to communicate with the operating system for file
operations it wants to perform.

Many of the methods in Java make opening a file
look just like instantiating another object.
You don't have to call open on a file, so it's easy to forget
that you've really opened a resource.
Closing the file handle will free up the memory used to store any file related data,
and allow other processes to access the file.
Fortunately, most of the Java classes you'll use to interact with files,
also implement an AutoCloseable interface, which makes closing resources seamless. 
However, it's still crucial to be diligent and to understand 
the consequences of keeping file resources open.
I'll be discussing this a lot more during the exception handling sections.

![image01](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_14_InputOutputFiles/images/image01.png?raw=true)

Java has what seems like a very confusing and large series of classes,
in many packages, to support input/output.
I'll be covering the most commonly used types, 
but first let's talk about Java's somewhat confusing terminology, 
which includes **IO**, **NIO** and **NIO.2**.

First, **IO** was a term for input/output, and `java.io` is a package 
that contains the original set of types, 
which support reading and writing data from external resources.

**NIO** was introduced as _Non-blocking **IO**_, 
with the `java.nio` package in _Java 1.4_, 
as well as a few other related packages. 
The communication with resources is facilitated 
through specific types of **Channels**, 
and the data stored in containers called **Buffers**, when exchanged.

**NIO.2** stands for new **IO**, 
and is a term that came into being with _Java 1.7_,
emphasizing significant improvements to the `java.nio` package, 
most importantly the `java.nio.file` package and its types.
Does this mean everything in `java.io` has been replaced 
with new functionality in the `java.NIO` packages?
No, but in many cases, there are different means, 
for doing the same thing.
**NIO.2** introduced the **Path** and **FileSystem** types,
and some helper classes, such as **Files**, **Paths**, and **FileSystems**, 
that do make some common functionality for working 
with operating system file systems, 
much easier as well as more efficient, 
delegating work to native systems.
Where functionality overlaps, 
I'll try to show these to you side by side.
You'll likely see a lot of legacy code, 
using the classes you see on this slide in the `java.io` package, 
so **BufferedInputStreams** and **FileWriters** and so on.
But I also want you to see the **NIO.2** types at work, 
many of which provide more support for functional programming.
Let's get started.
</div>

## [a. Exception Handling]()

### [try-catch Clause with Checked and Unchecked Exceptions]()
<div align="justify">

As I said in the introduction, I'll be starting out by talking about Exception Handling.
I've created the Main class and method.

```java  
public class Main {

    public static void main(String[] args) {

        String filename = "testing.csv";
        Path path = Paths.get(filename);
        List<String> lines = Files.readAllLines(path);
    }
}
```

I'll create a variable for a filename,
which I'll just assign a **String** literal to, so `testing.csv`.
Next, using the filename as an argument,
I'm going to get a **Path** instance, with the help of a static method,
on a helper class called **Paths**.
Right now, I don't want you to worry too much about what these types are,
or how they work just yet.
I'll be covering them in detail shortly.
After I have a **Path** instance,
I can call the static method _readAllLines_, on the **Files** class,
passing it the path instance, to get an array of **String**,
which would be all the lines in the file,
assuming the file doesn't exceed this method's limitations.
Again, I'll be getting into how this all works shortly,
but what I want you to see, is that this last statement doesn't compile.
If I hover over that error, I'll see the message,
unhandled exception, an _IOException_.
The _IOException_ is a special kind of exception, called a **Checked Exception**.
It's the parent class of many common exceptions you'll encounter
when working with external resources.
A checked exception represents an expected or common problem that might occur.
You can imagine that a typo in the file name might be a common occurrence,
and the system wouldn't be able to locate a file.
It's so common that Java usually has a named exception just for that situation,
the _FileNotFoundException_, which is a subclass of _IOException_.
How do you handle a checked exception?
You have two options.
You can wrap the statement that throws a checked exception, in a try catch block,
and then handle the situation in the catch block.
Or, alternately, you can change the method signature, declaring a _throws_ clause,
and specifying this exception type.
Let's see what both of these options look like in code.

```java  
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        String filename = "testing.csv";
        Path path = Paths.get(filename);
        List<String> lines = Files.readAllLines(path);
    }
}
```

If I again hover over that error, you'll see IntelliJ is prompting me
with one choice there on the left, _Add exception to method signature_.
If I select that, IntelliJ adds a _throws_ clause to the _main_ method.
If this was not the _main_ method, this might make sense,
but just having the application throw an exception,
isn't the most user-friendly solution.
I'm going to revert that last change,
and pick a different option, this time selecting _more actions_
on the right side of this dialog.
This lists two options at the top.
First, the one we already tried, and second,
_surround with try catch_, which is the one I want to pick now.

```java  
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        String filename = "testing.csv";
        Path path = Paths.get(filename);
        try {
            List<String> lines = Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
```

Let's see what happens if I run the code this way?

```html  
Exception in thread "main" java.lang.RuntimeException : java.nio.file.NoSuchFileException : testing.csv
    at Main.main
Caused by: java.nio.file.NoSuchFileException : testing.csv
    at java.base/sun.nio.fs.WindowsException.translateToIOException()
    at java.base/sun.nio.fs.WindowsException.rethrowAsIOException()
    at java.base/sun.nio.fs.WindowsException.rethrowAsIOException()
    at java.base/sun.nio.fs.WindowsFileSystemProvider.newByteChannel()
    at java.base/sun.nio.file.Files.newByteChannel()
    at java.base/sun.nio.file.Files.newByteChannel()
    at java.base/sun.nio.file.spi.FileSystemProvider.newInputStream()
    at java.base/sun.nio.file.Files.newInputStream()
    at java.base/sun.nio.file.Files.newBufferedReader()
    at java.base/sun.nio.file.Files.readAllLines()
    at java.base/sun.nio.file.Files.readAllLines()
    at Main.main()
```

I get a RuntimeException wrapped around another exception, _NoSuchFileException_,
because this file doesn't yet exist.
This way of handling the exception was not to handle the problem at all,
and not to force the calling code to handle it either.
It did inform the user, and quit out of the application.
It might be true that this is a fatal mistake,
and that this file, `testing.csv`, may really have to exist for the application to run.
Another approach might be to check if the file exists in the first place.
I'll comment out the try catch block here for now.
Now, I'm going to use another class 
I'll be talking about shortly, which is the **File** class.

```java  
File file = new File(filename);
if (!file.exists()) {     
    System.out.println("I can't run unless this file exists");
    System.out.println("Quitting Application, go figure it out");
    return;
}
System.out.println("I'm good to go.");
```

In this case, I can create a new file instance, 
using the constructor on this class, that takes a filename.
This class has a method, called _exists_ on it, that will test if the file exists,
as I specified it in my string.
If it doesn't exist, I'll print that _this application can't run_,
and _this situation will quit the application_. 
I'll then return from the method, which, 
in this case, will cause the application to end. 
If the file exists, I'll print the message, _I'm good to go_.
In this case, I'm first checking if an error situation exists.
In the previous example, the code simply assumed that the file would exist,
and threw an exception if not.
These two different approaches have the acronyms, **LBYL**, and **EAFP**.
**LBYL** stands for, **Look Before You Leap.** 
This style of coding involves checking for errors before you perform an operation.
**EAFP** stands for, **Easier to Ask Forgiveness than Permission.** 
This assumes an operation will usually succeed, 
and then handles any errors that occur if they do occur.
Which approach is better? 
Like all questions about software, the answer is, that depends.

| Feature       | LBYL                                             | EAFP                                                                     |
|---------------|--------------------------------------------------|--------------------------------------------------------------------------|
| Approach      | Check for errors before performing an operation. | Assume that the operation will succeed and handle any errors that occur. |
| Advantages    | Can be more efficient if errors are rare.        | Can be more concise and easier to read.                                  |
| Disadvantages | Can be more verbose if errors are common.        | Can be more difficult to debug if errors are unexpected.                 |

If Errors are rare, you might want to use look before you leap, 
but it does make the code more verbose.
If errors are unexpected, rather than the garden variety type,
the _Easier to Ask for Forgiveness than Permission_ approach 
might make it more difficult to debug.
You can see from my code that both of these ways of dealing 
with a checked exception had the same effect, 
because this method is the _main_ method.
In both cases, the application was terminated because of this condition.

How do you recognize a checked exception? 
When you're coding with an IDE, it's pretty easy to recognize one, 
because your code won't compile
if one is thrown from code you're calling.
If you're taking the certification exam, you can remember 
that a checked exception means it's NOT an **unchecked exception**.
An **Unchecked Exception** is an instance of a **RuntimeException**, 
or one if its subclasses.
 
In general, any code in your code block that comes 
after the statement that threw an exception isn't going to be executed.
But there's one exception to this, no pun intended.
The _try_ clause has an additional clause, called the _finally_ clause.
Any code in the _finally_ clause will get executed,
whether an exception occurs or not.
Let me set this up.

```java  
public static void main(String[] args) {
    String filename = "testing.csv";
    Path path = Paths.get(filename);

    testFile(filename);
    
    File file = new File(filename);
    if (!file.exists()) {
        System.out.println("I can't run unless this file exists");
        System.out.println("Quitting Application, go figure it out");
        return;
    }
    System.out.println("I'm good to go.");
}

private static void testFile(String filename) {

    Path path = Paths.get(filename);
    try {
        List<String> lines = Files.readAllLines(path);
    } catch (IOException e) {
        throw new RuntimeException(e);
    } 
    System.out.println("File exists and able to use as a resource");
}
```

First, I'll uncomment that code with the _try-catch_ block.
Next, I want to create a private static method that returns void, 
and I'll call it _testFile_,
with one parameter, a **String** called _filename_.
I'll cut the code that has the **Path** variable statement,
and that _try-catch_ block from the _main_ method.
Next, I'll just add a statement at the end of this method
that says the file exists, and able to use as a resource.
I'll pop back up and where I had the _try-catch_ block in the _main_ method,
I'll instead make a call to this method.
If I run this code:

```html  
Exception in thread "main" java.lang.RuntimeException : java.nio.file.NoSuchFileException : testing.csv
    at Main.testFile()
    at Main.main()
Caused by: java.nio.file.NoSuchFileException : testing.csv
    at java.base/sun.nio.fs.WindowsException.translateToIOException()
    at java.base/sun.nio.fs.WindowsException.rethrowAsIOException()
    at java.base/sun.nio.fs.WindowsException.rethrowAsIOException()
    at java.base/sun.nio.fs.WindowsFileSystemProvider.newByteChannel()
    at java.base/sun.nio.file.Files.newByteChannel()
    at java.base/sun.nio.file.Files.newByteChannel()
    at java.base/sun.nio.file.spi.FileSystemProvider.newInputStream()
    at java.base/sun.nio.file.Files.newInputStream()
    at java.base/sun.nio.file.Files.newBufferedReader()
    at java.base/sun.nio.file.Files.readAllLines()
    at java.base/sun.nio.file.Files.readAllLines()
    at Main.main()
```

I get an exception as I did before, with one difference.
Notice the stack trace here.
You can see that the exception occurred in the _testFile_ method,
and because it wasn't handled there,
it propagated up to the _main_ method, which called it.
Here again, the exception's not handled, so the program exits ungracefully.
The statement at the end of the _testFile_ method was never printed.
As I said previously, any code after
where the exception occurred isn't going to get executed.
That is, unless it's in a _finally_ clause.
Now, I'll include a _finally_ clause in this
_try-catch_ statement of the _testFile_ method.
I'll add _finally_ after the _catch_ clause.
I'll print out, maybe I'd log something either way.

```java  
public static void main(String[] args) {
    String filename = "testing.csv";
    Path path = Paths.get(filename);

    testFile(filename);
    
    File file = new File(filename);
    if (!file.exists()) {
        System.out.println("I can't run unless this file exists");
        System.out.println("Quitting Application, go figure it out");
        return;
    }
    System.out.println("I'm good to go.");
}

private static void testFile(String filename) {

    Path path = Paths.get(filename);
    try {
        List<String> lines = Files.readAllLines(path);
    } catch (IOException e) {
        throw new RuntimeException(e);
    } finally {
        System.out.println("Maybe I'd log something either way...");
    }
    System.out.println("File exists and able to use as a resource");
}
```

A _finally_ clause doesn't have any parameters like the _catch_ clause.
Now, if I run this code:

```html  
Maybe I'd log something either way...
Exception in thread "main" java.lang.RuntimeException : java.nio.file.NoSuchFileException : testing.csv
    at Main.testFile()
    at Main.main()
Caused by: java.nio.file.NoSuchFileException : testing.csv
    at java.base/sun.nio.fs.WindowsException.translateToIOException()
    at java.base/sun.nio.fs.WindowsException.rethrowAsIOException()
    at java.base/sun.nio.fs.WindowsException.rethrowAsIOException()
    at java.base/sun.nio.fs.WindowsFileSystemProvider.newByteChannel()
    at java.base/sun.nio.file.Files.newByteChannel()
    at java.base/sun.nio.file.Files.newByteChannel()
    at java.base/sun.nio.file.spi.FileSystemProvider.newInputStream()
    at java.base/sun.nio.file.Files.newInputStream()
    at java.base/sun.nio.file.Files.newBufferedReader()
    at java.base/sun.nio.file.Files.readAllLines()
    at java.base/sun.nio.file.Files.readAllLines()
    at Main.main()
```

You can see the _Maybe I'd log something either way_ was printed.
Whether an exception occurs or not, this code in the _finally_ clause will be executed.
In this case, we were expecting an error, and wrapped our statement in a try clause.
I'm not really handling it, but I'm masking it as a runtime exception,
and throwing it in the catch clause.
Before that gets thrown, and execution returns to the _main_ method,
the code in the _finally_ clause gets executed as you saw.

A _finally_ clause is used in conjunction with a _try_ statement.
A traditional _try_ statement requires
either a _catch_ or a _finally_ clause, or can include both.
The _finally_ clause is always declared
after the _catch_ block if one is declared.
The _finally_ block's code is always executed,
regardless of what happens in the _try_ or _catch_ blocks.
The _finally_ block does not have access to either the _try_
or _catch_ block's local variables.

The original purpose for the _finally_ clause was
to have a single block of code to perform cleanup operations.
This included closing open connections,
releasing locks, or freeing up resources.
This clause ensured that this code was executed,
both during normal completion as well as in the event of an exception.

The **try with resources** syntax, introduced in JDK7,
**is a better approach** than using the _finally_ clause
for closing resources, which I'll cover shortly.
The _finally_ clause can be used to execute other important tasks,
such as logging or updating the user interface.
The _finally_ clause has some disadvantages.

* It can be challenging to read and understand code that uses this clause.
* It can be used to hide errors, which can make debugging more difficult.
* If you execute code not related to clean up tasks,
this will make it harder to maintain your code.

Ok, so getting back to the code, let me cover a couple of these points.

```java  
import java.io.IOException;

public static void main(String[] args) throws IOException{
    String filename = "testing.csv";
    Path path = Paths.get(filename);

    testFile(filename);

    File file = new File(filename);
    if (!file.exists()) {
        System.out.println("I can't run unless this file exists");
        System.out.println("Quitting Application, go figure it out");
        return;
    }
    System.out.println("I'm good to go.");
}

private static void testFile(String filename) throws IOException {

    Path path = Paths.get(filename);
    try {
        List<String> lines = Files.readAllLines(path);
        //} catch (IOException e) {
        //  throw new RuntimeException(e);
    } finally {
        System.out.println("Maybe I'd log something either way...");
    }
    System.out.println("File exists and able to use as a resource");
}
```

First, I'll comment out the catch statement.
Now I'm back with a **checked** exception problem,
because I'm not handling a possible **IOException**.
I'll add it to the method signature for a moment,
just to get rid of that error.
Now, you can see that this code compiles,
but I've pushed the problem up to the _main_ method, on the call to this method.
Since I didn't handle it in my method,
any code that calls this method must now either have to catch this exception,
or specify it in the _throws_ clause.
I'll again choose to add it to the _main_ method signature.
Running this code:

```html  
Maybe I'd log something either way...
Exception in thread "main" java.nio.file.NoSuchFileException : testing.csv
    at java.base/sun.nio.fs.WindowsException.translateToIOException()
    at java.base/sun.nio.fs.WindowsException.rethrowAsIOException()
    at java.base/sun.nio.fs.WindowsException.rethrowAsIOException()
    at java.base/sun.nio.fs.WindowsFileSystemProvider.newByteChannel()
    at java.base/sun.nio.file.Files.newByteChannel()
    at java.base/sun.nio.file.Files.newByteChannel()
    at java.base/sun.nio.file.spi.FileSystemProvider.newInputStream()
    at java.base/sun.nio.file.Files.newInputStream()
    at java.base/sun.nio.file.Files.newBufferedReader()
    at java.base/sun.nio.file.Files.readAllLines()
    at java.base/sun.nio.file.Files.readAllLines()
    at Main.testFile()
    at Main.main()
```

You'll see that I again get the message, _Maybe I'd log something either way_.
This demonstrates that you can have a try statement, with just a _finally_ clause.
I'm going to revert those last changes, first removing **throws IOException**
from _main_, and next removing **throws IOException** from the _testFile_ method.
And finally, reverting the comments,
putting back the _catch_ clause in the _testFile_ method.

```java  
import java.io.IOException;

public static void main(String[] args){
    String filename = "testing.csv";
    Path path = Paths.get(filename);

    testFile(filename);

    File file = new File(filename);
    if (!file.exists()) {
        System.out.println("I can't run unless this file exists");
        System.out.println("Quitting Application, go figure it out");
        return;
    }
    System.out.println("I'm good to go.");
}

private static void testFile(String filename) {

    Path path = Paths.get(filename);
    try {
        List<String> lines = Files.readAllLines(path);
        } catch (IOException e) {
          int i = 1/0;
    } finally {
        System.out.println("Maybe I'd log something either way...");
    }
    System.out.println("File exists and able to use as a resource");
}
```

Now, in that _catch_ clause, I'm going to force another error.
I'll replace the throw new **RuntimeException**,
and then just purposely code a divide by zero exceptions there.
Now here, an exception is definitely
occurring, but it's going to happen
in the catch clause itself, while it's
trying to handle a previous exception.
What happens if I run this?

```html  
Maybe I'd log something either way...
Exception in thread "main" java.lang.ArithmeticException : / by zero
    at Main.testFile()
    at Main.main()
Maybe I'd log something either way...
```

Well, you can see, I still get the statement printed from the _finally_ clause,
but the exception that got thrown was actually an **ArithmeticException**,
so the last exception that occurred.
This means that if you get an exception in your _try_ block,
or your _catch_ block, the code in the _finally_ clause still executes.
I'll revert that last change.
If my finally block code gets an exception, however, 
then I'm out of luck, and execution will end at that point.
</div>

### [try-with-resources Statement]()
<div align="justify">

In the last section, we looked at the _try-catch_ clause, with _checked_ and _unchecked_ exceptions.
I also talked about using the _finally_ clause, as a way to close resources.
Since JDK 7, there's been a better alternative to this approach.

```java  
public static void main(String[] args) {
    String filename = "testing.csv";
    Path path = Paths.get(filename);

    testFile(filename);
    
    File file = new File(filename);
    if (!file.exists()) {
        System.out.println("I can't run unless this file exists");
        System.out.println("Quitting Application, go figure it out");
        return;
    }
    System.out.println("I'm good to go.");
}

private static void testFile(String filename) {

    Path path = Paths.get(filename);
    FileReader reader = null;
    try {
        List<String> lines = Files.readAllLines(path);
        reader = new FileReader(filename); 
    } catch (IOException e) {
        throw new RuntimeException(e);
    } finally {
        if (reader != null) {
            try {
                reader.close(); 
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Maybe I'd log something either way...");
    }
    System.out.println("File exists and able to use as a resource");
}
```

Here, I'm using `Files.readAllLines`, 
which is one way to read data from a text file.
An alternative is using a class called **FileReader**.
Later in this section, I'll cover some of the numerous ways you have to open
and communicate with files, but right now,
I still just want to focus on exceptions.

```java  
public static void main(String[] args) {
    String filename = "testing.csv";
    Path path = Paths.get(filename);

    testFile(filename);
    
    File file = new File(filename);
    if (!file.exists()) {
        System.out.println("I can't run unless this file exists");
        System.out.println("Quitting Application, go figure it out");
        return;
    }
    System.out.println("I'm good to go.");
}

private static void testFile(String filename) {

    Path path = Paths.get(filename);
    try {
        //List<String> lines = Files.readAllLines(path);
        FileReader reader = new FileReader(filename); 
    } catch (IOException e) {
        throw new RuntimeException(e);
    } finally {
        if (reader != null) {
            try {
                reader.close(); 
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Maybe I'd log something either way...");
    }
    System.out.println("File exists and able to use as a resource");
}
```

I'll first comment out the readAllLines statement.
Next, I'm going to declare and initialize a **FileReader** class, 
passing my filename string to the constructor.
This class has methods on it to read data in from a character file,
but for now, I'll just instantiate it.
Now, notice that IntelliJ is highlighting the **FileReader** type here.
Let's see why.
Hovering over that, I can see that
_FileReader is being used without a **try-with-resources** statement_.
**Try-with-resources** was introduced in JDK 7, as I mentioned on an earlier.
If you do use a traditional _try-catch_ block with classes, 
like **FileReader**, that implicitly open a resource, 
it's very important to include closing the resource in the _finally_ block.
Let me show you what this would look like, in my private method, _testFile_.

```java  
public static void main(String[] args) {
    String filename = "testing.csv";
    Path path = Paths.get(filename);

    testFile(filename);
    
    File file = new File(filename);
    if (!file.exists()) {
        System.out.println("I can't run unless this file exists");
        System.out.println("Quitting Application, go figure it out");
        return;
    }
    System.out.println("I'm good to go.");
}

private static void testFile(String filename) {

    Path path = Paths.get(filename);
    FileReader reader = null;
    try {
        //List<String> lines = Files.readAllLines(path);
        reader = new FileReader(filename); 
    } catch (IOException e) {
        throw new RuntimeException(e);
    } finally {
        if (reader != null) {
            try {
                reader.close(); 
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Maybe I'd log something either way...");
    }
    System.out.println("File exists and able to use as a resource");
}
```

First of all, I need to declare my **FileReader** variable,
outside the _try-catch_ block, so that it will be in scope for the _finally_ block,
where I need to close it.
I'll copy the declaration part of the statement,
and paste that on the line above the _try_ clause.
I'll initialize that to **null**.
Next, I'll remove **FileReader** as the type in the _try_ block,
because otherwise it's a declaration of a new variable.
Now, I'll close the reader manually in my _finally_ clause, 
before that last `system.out.println` statement.
I want to check and make sure the reader variable's not **null**. 
If it's not **null**, I'll try to close the resource.
And again, here I get an error, 
because the close method itself throws a **checked** exception.
I have to choose to catch or specify.
I'll use IntelliJ's help to enclose that statement in a try catch block.
Ok, so this code has gotten a bit ugly pretty quickly.
Before JDK 7, this is how you would've closed a file resource.
I'm going to leave this method as is, 
and now create a second one, called _testFile2_,
with the same signature as _testFile_, except the name.

```java  
private static void testFile2(String filename) {
    
    FileReader reader = new FileReader(filename)
    System.out.println("File exists and able to use as a resource");
}
```

I'm going to start like I did before, 
with one statement that instantiates a **FileReader** instance,
using the _filename_ argument. 
I'll also include that last print statement.
Not surprisingly, we know this will give us a compile error 
because of the **unchecked** exception.

```java  
private static void testFile2(String filename) {
    try (FileReader reader = new FileReader(filename)) {                
    } 
    System.out.println("File exists and able to use as a resource");
}
```

I'll select more actions, and now, what I want you to see are the options
below the first two we looked at previously.
Notice the very last one, _Surround with try-with-resources block_.
I'll select that one.
IntelliJ has inserted some code surrounding our statement.
This _try_ block is different, 
because now it's got a set of parentheses associated with it.
In those parentheses, I have my **FileReader** instantiation statement, 
minus the semicolon.
These differences are a little easier to see
if we look at them side by side below.

| try-with-resources examples                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        | traditional try clause                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     |
|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `//First Example: `<br/> `try (FileReader reader = new FileReader(filename)) {`<br/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`// do Something`<br/> `}`<br/><br/>  `// Second Example` <br/>`try (FileReader reader = new FileReader(filename); `<br/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`FileWriter writer = new FileWriter("New" + filename))`<br/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`// do Something`<br/> `}` | `FileReader reader = null`<br/> `try {`<br/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`reader = new FileReader(filename);`<br/> `} finally {`<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; `if (reader != null) {`<br/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`try {`<br/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`reader.close();`<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; `} catch (IOException e) {`<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; `//do Something`<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`}`<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`}`<br/> `}`  |


The _try-with-resources_ takes a colon delimited list of resource variables.
The resources in this list must implement the **AutoCloseable** or the **Closeable** interface.
The **Closeable** interface extends **AutoCloseable** as of JDK 7.
The _try-with-resources_ statement can be used without a _finally_ block, 
because all resources are automatically closed 
when this type of _try_ block completes, or if it gets an exception.
Let's continue looking at this in code.

```java  
private static void testFile2(String filename) {
    try (FileReader reader = new FileReader(filename)) {                
    } 
    System.out.println("File exists and able to use as a resource");
}
```

You can see the _try_ block has no code in it, 
and there's also no _catch_ block.
Our code still doesn't compile yet either.
I'll again choose actions from the first dialog.
Now, I've got to add _catch_ clauses, and here, 
on the left, IntelliJ is showing me two catch clauses.
I'll select this option.

```java  
private static void testFile2(String filename) {
    try (FileReader reader = new FileReader(filename)) {                
    } catch (FileNotFoundException e) {   
        throw new RuntimeException(e);
    } catch (Exception e) {
        throw new RuntimeException(e);
    } 
    System.out.println("File exists and able to use as a resource");
}
```

Ok, so what is going on here?
First of all, multiple _catch_ clauses aren't unique 
to the _try-with-resources_ clause.
You can have multiple _catch_ clauses with either _try_ statement, 
though I don't think I've yet reviewed this feature with you.
The reason that there are two is the first one,
the _FileNotFoundException_ may occur when opening the resource.
The _IOException_ might occur when working or closing the resource.
Ironically, IntelliJ doesn't like this code, 
notice the warning highlight on the second catch clause.
If I hover over that, it says,
_**catch** branch identical to **FileNotFoundException**_,
and one of the hints is to collapse _catch_ blocks.

```java  
private static void testFile2(String filename) {
    try (FileReader reader = new FileReader(filename)) {                
    } catch (FileNotFoundException e) {                                 
        System.out.println("File '" + filename + "' does not exist");   
        throw new RuntimeException(e);
    } catch (Exception e) {
        throw new RuntimeException(e);
    } 
    System.out.println("File exists and able to use as a resource");
}
```

Ok, that's an option, but right now I'm going to manually edit this code.
I'll change the _catch_ block for _FileNotFoundException_,
and add a `system.out.println` statement there.
Now, my _catch_ blocks have different code blocks,
and IntelliJ is no longer giving me a warning on that second _catch_ clause.
This code is a lot easier to read, because there's no _finally_ block.
You don't need the _finally_ block to close this resource anymore, 
but you can still include it if you want to do logging or something else.

```java  
private static void testFile2(String filename) {
    try (FileReader reader = new FileReader(filename)) {                
    } catch (FileNotFoundException e) {                                 
        System.out.println("File '" + filename + "' does not exist");   
        throw new RuntimeException(e);
    } catch (IOException e) {                                           
        throw new RuntimeException(e);
    } finally {                                                         
        System.out.println("Maybe I'd log something either way...");    
    }
    System.out.println("File exists and able to use as a resource");
}
```

I'll add it here, and print the same thing 
I had in the _finally_ block in _testFile_, 
so that's _maybe I'd log something either way_.

```java  
public static void main(String[] args) {
    String filename = "testing.csv";
    Path path = Paths.get(filename);

    //testFile(filename);
    testFile2(filename);
    
    File file = new File(filename);
    if (!file.exists()) {
        System.out.println("I can't run unless this file exists");
        System.out.println("Quitting Application, go figure it out");
        return;
    }
    System.out.println("I'm good to go.");
}
```

I'll go back to my _main_ method, 
and call this method, instead of just _testFile_.
Running this code:

```html  
File 'testing.csv' does not exist
Maybe I'd log something either way...
Exception in thread "main" java.lang.RuntimeException : java.io.FileNotFoundException : testing.csv (The system cannot find the file specified)
    at Main.testFile2()
    at Main.main()
Caused by: java.io.FileNotFoundException : testing.csv (The system cannot find the file specified)
    at java.base/sun.nio.fs.WindowsException.translateToIOException()
    at java.base/sun.nio.fs.WindowsException.rethrowAsIOException()
    at java.base/sun.nio.fs.WindowsException.rethrowAsIOException()
    at java.base/sun.nio.fs.WindowsFileSystemProvider.newByteChannel()
    at java.base/sun.nio.file.Files.newByteChannel()
    at java.base/sun.nio.file.Files.newByteChannel()
    at java.base/sun.nio.file.spi.FileSystemProvider.newInputStream()
    at java.base/sun.nio.file.Files.newInputStream()
    at java.base/sun.nio.file.Files.newBufferedReader()
    at java.base/sun.nio.file.Files.readAllLines()
    at java.base/sun.nio.file.Files.readAllLines()
    at Main.main()
```

There are some rules around having multiple _catch_ blocks, 
so let's play with these a little bit.

```java  
private static void testFile2(String filename) {
    try (FileReader reader = new FileReader(filename)) {                
    } catch (IOException e) {
        throw new RuntimeException(e);
    } catch (FileNotFoundException e) {                                 
        System.out.println("File '" + filename + "' does not exist");   
        throw new RuntimeException(e);
    } finally {                                                         
        System.out.println("Maybe I'd log something either way...");    
    }
    System.out.println("File exists and able to use as a resource");
}
```

First, I'll cut the second catch block with the _IOException,_
and it's _throw_ statement.
I'll paste that above the **FileNotFoundException** _catch_ block.
Changing the order of these catch clauses has introduced a compiler error,
on the second _catch_ clause.
If I hover over that, I see, 
_the Exception **FileNotFoundException** has already been caught_.
How is that possible?
As it turns out, Java evaluates when _IOException_ is declared, 
it catches all instances of that exception, 
as well as all instances of its subclasses.
Because **FileNotFoundException** is a subclass of **IOException**, 
it gets caught by the first clause, as we have it declared in this code.
This means the **FileNotFoundException** clause is redundant or unreachable,
when specified after it's **super** class.
If you do have code that's different for specific exceptions, 
you need to declare your clauses in hierarchical order 
from most specific, downward to the most general.
I'll revert those last two changes.

```java  
private static void testFile2(String filename) {
    try (FileReader reader = new FileReader(filename)) {
    } catch (FileNotFoundException e) {
        System.out.println("File '" + filename + "' does not exist");
        throw new RuntimeException(e);
    } catch (IOException e) {
        throw new RuntimeException(e);
    } catch (Exception e) {
        System.out.println("Something unrelated and unexpected happened");
    } finally {
        System.out.println("Maybe I'd log something either way...");
    }
    System.out.println("File exists and able to use as a resource");
}
```

Next, I'll add a catch-all clause, catching just exception,
and I'll put this as the last clause.
I'll print that _something unrelated and unexpected happened_.
The class **Exception** extends **Throwable**, 
so I could include **Throwable** in this hierarchy as well,
Lets quick look at a hierarchical chart of the **Throwable** class,
which is the parent class, of all of Java's exceptions and error classes.

![image01b](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_14_InputOutputFiles/images/image01b.png?raw=true)

This chart shows you which classes are **Checked**, in blue, 
and **Unchecked**, in Reddish Brown.
I had said before that you know a **Checked Exception**, 
because it's not a **runtimeException**.
An error is also an **unchecked** exception, 
meaning you aren't forced to **catch** or specify its type.
The **Error** class indicates serious problems
that a reasonable application shouldn't try 
to catch or recover from.
I've already mentioned there are two types of **Exceptions**, 
those that subclass from **RuntimeException**, 
and those that don't.
There are quite a lot of **Exception** classes,
and this diagram represents only a couple for demonstration purposes.
This hierarchy becomes important for the second variation of a _catch_ clause.
Getting back to the code, I want to show you another variation 
to the _catch_ clause, which lets you catch multiple targeted
**Exceptions** with a single clause.
Instead of listing one type of **Exception** 
and a local variable for that exception, 
you list multiple exceptions separated by a pipe character.
This kind of looks like an or statement,
and helps you remember that this catch expression means
one exception or the other.
There is still only a single variable declared though.

```java  
private static void testFile2(String filename) {
    try (FileReader reader = new FileReader(filename)) {
    } catch (FileNotFoundException e) {
        System.out.println("File '" + filename + "' does not exist");
        throw new RuntimeException(e);
    } catch (NullPointerException | IllegalArgumentException badData) {
        System.out.println("User has added bad data " + badData.getMessage());
    } catch (IOException e) {
        throw new RuntimeException(e);
    } catch (Exception e) {
        System.out.println("Something unrelated and unexpected happened");
    } finally {
        System.out.println("Maybe I'd log something either way...");
    }
    System.out.println("File exists and able to use as a resource");
}
```

To demonstrate this, I'll use **NullPointerException**,
then a pipe, then **IllegalArgumentException**,
and call my exception variable, bad data.
In this case, if either of these exceptions
is thrown, I'll print user has added _badData_,
and print the error message.

```java  
public static void main(String[] args) {
    String filename = "testing.csv";
    Path path = Paths.get(filename);

    //testFile(filename);
    //testFile2(filename);
    testFile2(null);
    
    File file = new File(filename);
    if (!file.exists()) {
        System.out.println("I can't run unless this file exists");
        System.out.println("Quitting Application, go figure it out");
        return;
    }
    System.out.println("I'm good to go.");
}
```

I'll now pass **null** to the call to _testFile2_ in the _main_ method.
Running this code now:

```html  
User has added bad data null
Maybe I'd log something either way...
File exists and able to use as a resource
I can't run unless this file exists
Quitting Application, go figure it out
```

I don't get an exception thrown or a stack trace.
I just get a series of confusing messages.
You can see the code in our new _catch_ block got executed.
This code printed out, _User has added bad data, null_.
Apparently, passing a null to **FileReader**
doesn't throw a **FileNotFoundException**,
but a **NullPointerException**.
In this case, I'm not propagating any errors up the stack.
The _finally_ clause was executed 
because that gets called either way, 
so I see _Maybe I'd log something either way_.
After this, the last statement in the _testFile2_ method 
was reached and executed.
This printed _File exists and able to use as a resource_, 
which actually isn't factually correct.
When the code goes back to the _main_ method,
it checks for the existence of the previous file, 
and doesn't find it.
Finally, it prints _Quitting Application, go figure it out_.
You can't list exceptions in the multi exception clause,
that are derivatives of the same class.

```java  
private static void testFile2(String filename) {
    try (FileReader reader = new FileReader(filename)) {
    } catch (FileNotFoundException e) {
        System.out.println("File '" + filename + "' does not exist");
        throw new RuntimeException(e);
    } catch (NullPointerException | IllegalArgumentException | RuntimeException badData) {
        System.out.println("User has added bad data " + badData.getMessage());
    } catch (IOException e) {
        throw new RuntimeException(e);
    } catch (Exception e) {
        System.out.println("Something unrelated and unexpected happened");
    } finally {
        System.out.println("Maybe I'd log something either way...");
    }
    System.out.println("File exists and able to use as a resource");
}
```

Look what happens if I now add more generic **RunTimeException** to that clause.
Hovering over the error in this statement, IntelliJ is telling me 
that _types in a multi-catch must be disjoint_.
This means these types can't have direct relationships with one another.
In this case, both **NullPointerException**, and **IllegalArgumentException**, 
are subclasses of **RuntimeException**, 
which means they aren't disjoint.
I'll revert that last change, 
so the code compiles, and runs as previously.

```java  
private static void testFile2(String filename) {
    try (FileReader reader = new FileReader(filename)) {
    } catch (FileNotFoundException e) {
        System.out.println("File '" + filename + "' does not exist");
        throw new RuntimeException(e);
    } catch (NullPointerException | IllegalArgumentException badData) {
        System.out.println("User has added bad data " + badData.getMessage());
    } catch (IOException e) {
        throw new RuntimeException(e);
    } catch (Exception e) {
        System.out.println("Something unrelated and unexpected happened");
    } finally {
        System.out.println("Maybe I'd log something either way...");
    }
    System.out.println("File exists and able to use as a resource");
}
```
</div>

## [b. Using Files and Paths Classes]()
<div align="justify">

```java  
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
```

I'll start this section by looking a little more closely 
at the types I used in the last section's code.
Take a look at the import statements in this code.
You can see a mix of types, some in the `java.io` package,
and some in the `java.nio.file` package.
That feels like a confusing mix, I'm sure.
Let's first look at these types on a couple of diagrams.

![image02](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_14_InputOutputFiles/images/image02.png?raw=true)

Here are the two in the `java.io` package.
The **File** class and the **FileReader** class
have been part of Java, since version 1.
The **FileReader** class implements the **AutoCloseable** interface, 
through its parent class, **Reader**.
This class opens a file resource implicitly.
I'm going to be covering this type, and others like it, 
in a later section, on reading data from files.
In contrast, when you create an instance of a **File**, 
you aren't opening that file.
Instead, you're working with something called a _file-handler_
that lets you perform OS-like operations.
Let me get back to the code, and
examine this class just a little bit.

```java  
public static void main(String[] args) {
    String filename = "testing.csv";
    Path path = Paths.get(filename);

    //testFile(filename);
    //testFile2(filename);
    //testFile2(null);
    
    File file = new File(filename);
    if (!file.exists()) {
        System.out.println("I can't run unless this file exists");
        //System.out.println("Quitting Application, go figure it out");
        return;
    }
    System.out.println("I'm good to go.");
}
```

The first thing I want to do is, remove the call to the _testfile2_ method.
I'm also going to remove that second `System.out.println` statement, 
in my _if_ statement.
Now that we've gone through the _try-with-resources_ catch block,
you might be wondering why I didn't use it here, creating a new **File** instance.
Not only that, I didn't have to wrap anything in a _try_ catch block 
for this bit of code.
Getting an instance of a file handler is different from 
getting an instance of a file resource.
That's confusing, especially since the terms might be used interchangeably,
but in this case, the difference is important, so let me explain it.
A file handle is a reference to a file 
that is used by the operating system to keep track of the file.
It is an abstract representation of the file,
and it does not contain any of the actual data from the file.
A file resource, on the other hand, is the actual data from the file.
It is stored on the disk, and it can be accessed
by the operating system, and by applications.
In the case of the **File** class,
we're only ever dealing with a file handler, 
so we don't open or close this.
With that in mind, let's look at this class.
You can see I was able to create a new instance of it, 
using **new**, and this constructor takes a **String** literal.
This string literal represents a path name.
A _path_ can include a _filename_, as this one does.
In fact, this path only includes a _filename_.
I could add a directory or folder here,
and I'll do that, starting with files then a forward slash.

```java  
public static void main(String[] args) {

    //String filename = "testing.csv";
    String filename = "src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_14_InputOutputFiles/Course02_UsingFilesAndPaths/Part1/files/testing.csv";

    //testFile(filename);
    //testFile2(filename);
    //testFile2(null);
    
    File file = new File(filename);
    if (!file.exists()) {
        System.out.println("I can't run unless this file exists");
        return;
    }
    System.out.println("I'm good to go.");
}
```

I'll use IntelliJ to create both this directory and this file, 
so I'll open the project pane, and select new file, 
and enter the same thing, `files/testing.csv`, 
I have in my string literal.
This will create an empty file, named `testing.csv` in a folder,
called _files_, in my project directory.
For now, I'm not going to put anything in that file,
so I'll just close that tab.
Back in the **Main** class, I can run this code:

```html  
I'm good to go.
```

Now I get, _I'm good to go_, because the file was found, in the place we specified it would be.
How did it know to start looking in the project directory of this project?
Because I used what's called a **relative path**.
All this means is that my **String** literal here, did not include characters
that said start at the root.
I'll explain this in just a minute.
First, let's get the current working directory,
which is the directory where the application process is running from.
Many of you may be familiar with **cwd**, a linux command,
for displaying the _current working directory_.
Mac has **pwd**, and for windows, you can type **cd** in a command prompt.
For windows, the current working directory is usually included in the prompt itself.
In fact, if you open a terminal session in IntelliJ, you can see this.
But let's get it programmatically, using the **File** class.
I'll print out _Current working directory_ or **cwd** in parentheses is equal to.

```java  
public static void main(String[] args) {

    System.out.println("Current Working Directory (cwd) = " + new File("").getAbsolutePath());

    String filename = "src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_14_InputOutputFiles/Course02_UsingFilesAndPaths/Part1/files/testing.csv";

    File file = new File(filename);
    if (!file.exists()) {
        System.out.println("I can't run unless this file exists");
        return;
    }
    System.out.println("I'm good to go.");
}
```

Then I'll instantiate a new file, and pass it an empty string as the path, 
or string literal, then I'll _getAbsolutePath_ on that instance.
Running this code:

```html  
Current Working Directory (cwd) = D:\JAVA_STUDY\Github\JAVA
I'm good to go.
```

You'll see the project folder printed there.
Yours will be different from mine.
A relative path just means the path is relative to the current working directory.
An absolute path, on the other hand, means the path is starting at the root directory.
How do I know what my root directories are?

```java  
public static void main(String[] args) {

    System.out.println("Current Working Directory (cwd) = " + new File("").getAbsolutePath());

    String filename = "src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_14_InputOutputFiles/Course02_UsingFilesAndPaths/Part1/files/testing.csv";

    File file = new File(filename);
    if (!file.exists()) {
        System.out.println("I can't run unless this file exists");
        return;
    }
    System.out.println("I'm good to go.");
}
```

There is a static method on **File**, called _getRoots_.
I'll call this in a for loop, at the end of my main method.
This method returns an array of **File** instances.
I'll print each file that's returned.
Running this code:

```html  
Current Working Directory (cwd) = D:\JAVA_STUDY\Github\JAVA
I'm good to go.
C:\
D:\
E:\
G:\
```

Again, you may get a different result.
For me on windows, I see the letter _C_, _D_, _E_ 
and _G_ colon backslash.

I'm going to open the project panel now, 
and copy my **files** directory that contains my empty file.
I'll open the project folder in Explorer.
I'll drop my pasted directory, on my root drive _D_, at the root, 
so that I have a files directory directly under the root.

![image02b](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_14_InputOutputFiles/images/image02b.png?raw=true)

If you're trying to do this on a _mac_, the root is hidden by default, 
and may be complicated to do because of user privileges.
It's not really important that you do this, but that 
you understand how to access files and folders from the root.
Getting back to my code, I now want to test if this file exists, 
and not the one that's under my project directory.

```java  
public static void main(String[] args) {

    System.out.println("Current Working Directory (cwd) = " + new File("").getAbsolutePath());

    //String filename = "src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_14_InputOutputFiles/Course02_UsingFilesAndPaths/Part1/files/testing.csv";
    String filename = "/files/testing.csv";

    File file = new File(filename);
    System.out.println(file.getAbsolutePath());
    if (!file.exists()) {
        System.out.println("I can't run unless this file exists");
        return;
    }
    System.out.println("I'm good to go.");
}
```

To do this, I can simply add a forward slash as the first character,
and remove the exact path location of our project.
This indicates that this is an absolute path.
To prove this, I'll print out the absolute
path after I get the file instance.
If I run this code:

```html  
Current Working Directory (cwd) = D:\JAVA_STUDY\Github\JAVA
D:\files\testing.csv
I'm good to go.
```

I can see the file was found, but this time, 
the absolute path is, d colon, then files, then the filename.
There are overloaded constructors for **File**, 
and the next one I want to show you, takes a parent path.
I'll change my filename back to a relative path,
by removing that first forward slash.

```java  
public static void main(String[] args) {

    System.out.println("Current Working Directory (cwd) = " + new File("").getAbsolutePath());

    //String filename = "/files/testing.csv";
    String filename = "files/testing.csv";

    //File file = new File(filename);
    File file = new File("/", filename);
    System.out.println(file.getAbsolutePath());
    if (!file.exists()) {
        System.out.println("I can't run unless this file exists");
        return;
    }
    System.out.println("I'm good to go.");
}
```

I'll change the next statement, and this time pass two arguments;  
the first one is another string, which is the parent path.
Here, I'll pass just the root, so a single forward slash stands for the root.
Running this code:

```html  
Current Working Directory (cwd) = D:\JAVA_STUDY\Github\JAVA
D:\files\testing.csv
I'm good to go.
```

I get the exact same results as before.
Now, I'll change that first argument to be the current working directory.

```java  
public static void main(String[] args) {

    System.out.println("Current Working Directory (cwd) = " + new File("").getAbsolutePath());

    String filename = "files/testing.csv";

    //File file = new File("/", filename);
    File file = new File("./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_14_InputOutputFiles/Course02_UsingFilesAndPaths/Part1", filename);
    System.out.println(file.getAbsolutePath());
    if (!file.exists()) {
        System.out.println("I can't run unless this file exists");
        return;
    }
    System.out.println("I'm good to go.");
}
```

I can do this by passing in a dot, and the relative path for the project folder 
as a string literal.
This is a common way in many programming languages 
to reference the current directory.
Running this:

```html  
Current Working Directory (cwd) = D:\JAVA_STUDY\Github\JAVA
D:\JAVA_STUDY\Github\JAVA\.\src\Udemy\JavaProgrammingTimBuchalka\NewVersion\Section_14_InputOutputFiles\Course02_UsingFilesAndPaths\Part1\files\testing.csv
I'm good to go.
```

You'll now see this code has found the file in the project folder.
Notice that the `\.\` here was printed in the absolute path.
This is called a redundant name element.
When we get to the **nio.2** types,
you'll find methods to normalize the path,
which removes these redundant name elements.
There's another constructor I'll show you,
that takes a **File** instance as the parent,
as the first argument.

```java  
public static void main(String[] args) {

    System.out.println("Current Working Directory (cwd) = " + new File("").getAbsolutePath());

    String filename = "files/testing.csv";

    //File file = new File("./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_14_InputOutputFiles/Course02_UsingFilesAndPaths/Part1", filename);
    File file = new File(new File("src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_14_InputOutputFiles/Course02_UsingFilesAndPaths/Part1").getAbsolutePath(), filename);
    System.out.println(file.getAbsolutePath());
    if (!file.exists()) {
        System.out.println("I can't run unless this file exists");
        return;
    }
    System.out.println("I'm good to go.");
}
```

Now, I'll pass it the current working directory directly as the parent,
instead of a string literal.
The _getAbsolutePath_ actually returns a file instance, not a string.
Running this:

```html  
Current Working Directory (cwd) = D:\JAVA_STUDY\Github\JAVA
D:\JAVA_STUDY\Github\JAVA\src\Udemy\JavaProgrammingTimBuchalka\NewVersion\Section_14_InputOutputFiles\Course02_UsingFilesAndPaths\Part1\files\testing.csv
I'm good to go.
```

You now see I'm still getting the file in the projects directory, but this time,
the absolute path doesn't include `\.\` in the path.
Let me summarize a few of the concepts I've covered here.

* A **directory** (or folder) is a file system container for other directories or files.
* A **path** is either a directory or a filename, and may include information about the parent directories or folders.
* A **root directory** is the top-level directory in a file system. 
In windows, this is often the _c_ drive, for example.
* The **current working directory** is the directory that the current process is working in or running from.
* An **absolute path** Includes the root (by either starting with / or optionally, 
`C:\` in windows, where _c_ is the root identifier. 
* A **relative path** defines a path relative to the current working directory, 
and therefore would not start with `/`, but may optionally start with a dot `.`
then a file separator character.

These concepts will become more important, as we continue to examine the enhanced
functionality the **NIO.2** types provide.
Before we move to those types, I'll put up the API for the **File** class's methods.

![image02c](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_14_InputOutputFiles/images/image02c.png?raw=true)

Right away, you can see that there are methods
to check permissions of a file or directory,
such as _canExecute_, _canRead_, _canWrite_.
You can create a new **File**.
You can delete the current file.
If I scroll down, I'll see many more methods, 
like _isDirectory_ and _isFile_,
which will tell me the type of path.
Here is _listFiles_, which will give me a directory listing
if my file type is a directory, and so on.
This is pretty familiar functionality
to anyone dealing with a file system.
Now, let's switch to the documentation for the methods 
on the **Files** class, in the `java.nio.Files` package.

![image02d](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_14_InputOutputFiles/images/image02d.png?raw=true)

The first thing you might notice is they're all static, 
so you don't create an instance of this **Files** class.
Instead, you call static methods, 
passing a **Path** instance in most cases, on which it will operate.
Right away, I can see functionality that's similar to the **File** class, 
like _createFile_.
But here's _createSymbolicLink_, 
the **File** class doesn't support symbolic links,
so this is an additional feature.
Don't worry if you don't know what a symbolic link is.
The point is, this class contains a lot more functionality 
than the older **File** class.
That's true not only for file system operations,
but it also gives us some additional helper methods 
for reading and writing files.
I'll scroll down to _read_ methods.
Here, you see we have many options,
like _readAllBytes_, or _readAllLines_.
Here's a _readString_, 
which will read the entire contents of a file, in as a single string.
This method does have some limitations, and I'll get to them later.
At the end of this list, you'll see _write_ methods here as well.
This is powerful stuff, simplifying many of the jobs you'll do 
for basic file reads and writes.
Now, let's try to get a birds eye view of the **Files** type.

![image03](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_14_InputOutputFiles/images/image03.png?raw=true)

Here are the types I reference in my code, from the `java.nio.File` package.
You can see that **Path** is an interface, and not a class, 
like the **File** class was.
The **Paths** class consists exclusively of static methods 
that return a **Path** instance.
The **Files** class, on the other hand,
has many static methods that perform operations on files and directories.
This class operates on a **Path** instance, which you pass to these methods.
It has a lot of functionality in common with the older `java.io.File` class, 
but provides much more, as you saw briefly in the API documentation.

![image04](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_14_InputOutputFiles/images/image04.png?raw=true)

When you use the **File** class, you get an instance with a **File** constructor, 
and then you execute a method on that instance.
Here, behavior is a member of the instance itself.
This is a pretty familiar pattern.

![image05](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_14_InputOutputFiles/images/image05.png?raw=true)

Now, if we look at how to use the **NIO2** types, to do something, 
we first have to get an instance of **Path**.
We don't do this directly.
Instead, We use factory methods on the **Path** class, 
or the **Paths** class, or other types I'll talk about later.
We then call the static method on the **Files** class, 
to do something, on the **path** instance passed as an argument.
I'll just add a bit of code to the _main_ method,
that does what `File.exists` does,
but uses the **nio2** types.

```java  
import java.nio.file.Files;
import java.nio.file.Paths;

public static void main(String[] args) {

    System.out.println("Current Working Directory (cwd) = " + new File("").getAbsolutePath());
    String filename = "files/testing.csv";

    File file = new File(new File("src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_14_InputOutputFiles/Course02_UsingFilesAndPaths/Part1").getAbsolutePath(), filename);
    System.out.println(file.getAbsolutePath());
    if (!file.exists()) {
        System.out.println("I can't run unless this file exists");
        return;
    }
    System.out.println("I'm good to go.");

    Path path = Paths.get("src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_14_InputOutputFiles/Course02_UsingFilesAndPaths/Part1/files/testing.csv");
    System.out.println(file.getAbsolutePath());
    if (!Files.exists(path)) {
        System.out.println("2. I can't run unless this file exists");
        return;
    }
    System.out.println("2. I'm good to go.");
}
```

First, I need to get an instance of a type that implements the **Path** interface.
I don't have to hunt for this type in the API docs.
I just have to use a factory method to get an instance.
I'll start with the factory method on the **Paths** class, 
which I've said is a class strictly in existence to provide these factory methods.
It has overloaded get methods on it.
I'll pass a String literal, the same as I did above. 
Remember, this is a relative path.
I'm going to copy the if statement in this method, 
and the statement right below it.
I'll paste that at the end of this method.
Once I paste that, I'll change _File_ to _Files_ 
in the if expression, and pass _path_ to the _exists_ method.
I'll also just put a `2.`, at the start of each output statement, 
so I can tell these are from this code.
If I run this code:

```html  
Current Working Directory (cwd) = D:\JAVA_STUDY\Github\JAVA
D:\JAVA_STUDY\Github\JAVA\src\Udemy\JavaProgrammingTimBuchalka\NewVersion\Section_14_InputOutputFiles\Course02_UsingFilesAndPaths\Part1\files\testing.csv
I'm good to go.
D:\JAVA_STUDY\Github\JAVA\src\Udemy\JavaProgrammingTimBuchalka\NewVersion\Section_14_InputOutputFiles\Course02_UsingFilesAndPaths\Part1\files\testing.csv
2. I'm good to go.
```

You'll see it found the file, as it had when I just used my file instance.
This doesn't seem that special, I admit.
The newer code does what the old code does,
so where's the advantage?
You'll have to wait for an answer 
until I'll cover both the common functionality 
and the additional features available in the **NIO2** types.

```java  
public class Main {

    public static void main(String[] args) {

        useFile("testfile.txt");
    }

    /** Creating and deleting a file by testing its existence with an instance of File. **/
    private static void useFile(String fileName) {

        File file = new File(fileName);
        boolean fileExists = file.exists();

        System.out.printf("File '%s' %s%n", fileName, fileExists ? "exists." : "does not exist.");

        if (fileExists) {
            System.out.println("Deleting File: " + fileName);
            fileExists = !file.delete();
        }

        if (!fileExists) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Something went wrong");
            }
            System.out.println("Created File: " + fileName);
            if (file.canWrite()) {
                System.out.println("Would write to file here");
            }
        }
    }
}
```

Now, I'll have a new **Main** class.
I'll start by creating a private static void method, 
named _useFile_. 
This takes a string, a _filename_.
I'll create a new file variable, named _file_,
and get a new instance via the single argument constructor 
on the **File** class, that takes a **String**, 
so I'll pass it _filename_.
Next, I'll create a **boolean**, _fileExists_,
and assign that the result of calling the exists method, 
on my _file_ instance.
Using a formatted **String**, 
I'll print the _filename_, and whether it exists or not.
Here, I'll use the _fileExists_ boolean variable
to adjust my output message accordingly.
Now, if the file exists, 
I want to start out by deleting it, 
because I'm always going to recreate this file in this code.
To do this, I'll set up an _if_ statement, 
checking the _fileExists_ variable again.
If that's **true**, I'll first print a statement
that I'm deleting the file. 
Then, I'll execute the `file.delete` method, on my _file_ instance.
I'll assign the result of that method, 
or rather the inverse, to my _fileExists_ variable. 
If this file can't be deleted, 
meaning I get a false back from this method, 
I'll set _fileExists_ to **true**.
This is because I'll be using this flag later, 
to determine if I should continue.
Next, I'll create another if statement, 
this time checking whether the file doesn't exist.
If it doesn't exist, I'll call `file.createNewFile`.
This returns a boolean, but for now,
to keep this code simpler, I'll ignore the result. 
There's a chance this could fail,
and I wouldn't really know, 
because my code would indicate everything was fine. 
I'm going to talk about this a more little later. 
I'll print a statement after this, that the file was created. 
Now, for good measure, I'll test if this file can be written to, 
and if so, I'll just print that I would write to it here.
The **File** method by itself doesn't have any methods 
to write to a _file_ resource.
For right now, I'll just print something to the console.
Notice that I've got an error, on the _createNewFile_ method.
If I hover over that, I can see that I need to handle an _IOException_.
I'll just use the more actions link, and pick surround with _try-catch_ there.
Instead of throwing a runtime exception in this generated _catch_ clause,
I'll just print _something went wrong_ instead, to the console.
Now, I'll call this method from my main method, 
passing it a String literal representing a filename, `testfile.txt`.
Running this code for the first time:

```html  
File 'testfile.txt' does not exist.
Created File: testfile.txt
Would write to file here
```

I'll get the statement in the output the file `testfile.txt` does not exist.
I can see the code created the file, or says it has,
and that it would write to the file, 
which means the _canWrite_ method returned true.

![image05b](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/images/image05b.png?raw=true)

If I open my project pane, I can see my `testfile.txt` file there, 
in the project's directory, so it was successfully created.
If I run the code a second time:

```html  
File 'testfile.txt' exists.
Deleting File: testfile.txt
Created File: testfile.txt
Would write to file here
```

I can see that my code recognized that the file does now exist.
This is followed by the statement, deleting file.
Then my code creates it again, and would write to it.
Ok, just to summarize, in this code, I used an instance of **File**,
to test some conditions in the file system,
as well as to create and delete a file.
Now, I'll do the same thing, but I'll instead of using 
an instance of the older **File** class, 
I'm going to use an instance of the newer **Path** type,
with the help of the methods on the **Files** class.

```java  
private static void usePath(String fileName) {

    Path path = Path.of(fileName);
    boolean fileExists = Files.exists(path);

    System.out.printf("File '%s' %s%n", fileName, fileExists ? "exists." : "does not exist.");
     if (fileExists) {
         System.out.println("Deleting File: " + fileName);
         fileExists = !Files.delete(path);                          // Error-1
     }

     if (!fileExists) {
         try {
             Files.createNewFile(path);                             // Error-2
         } catch (IOException e) {
             System.out.println("Something went wrong");
         }
         System.out.println("Created File: " + fileName);
         if (Files.canWrite(path)) {                                // Error-3
             System.out.println("Would write to file here");
         }
     }
}
```

I'll copy this entire method, and paste a copy below the _useFile_ method.
I'll first change the name from _useFile_ to _usePath_.
I need to replace the statement here, where I create a _file_ instance.
First, I'll create a **path** variable, simply called _path_.
If you remember, I used the _get_ method on the **Paths** class.
**JDK11** introduced a static factory method on the **Path** interface itself,
a method called _of_, so that's what I'll use here, 
passing it my _fileName_ argument.
Right away, that gives me four compiler errors, 
everywhere where I was accessing the _file_ instance.
I'll need to replace each instance,
first replacing the _file_ variable with the **Files** class name, 
and instead of passing no arguments to each method, 
I'll pass my _path_ instance.
First, I'll do that for the `file.exists` method,
changing _file_ to _Files_,
and pass _path_ to the _exists_ method.
Next, I want to do the same thing, for `file.delete`.
In this case, I've got an error after I make those changes.
I'll come back to that in a minute.
I'll keep going with the _file_ to _Files_ replacement, 
so next, I need to change _createNewFile_.
After I make that change, I can see I have an error here as well.
That's because the method does not exist on **Files**.
Again, I'll come back to this.
I'll next change `file.canWrite` to `Files.canWrite`, and pass _path_.
There's another error, because as before, this method isn't on **Files**.
Ok, let's address each of these issues, starting with the _delete_ method.

```java  
private static void usePath(String fileName) {

    Path path = Path.of(fileName);
    boolean fileExists = Files.exists(path);

    System.out.printf("File '%s' %s%n", fileName, fileExists ? "exists." : "does not exist.");
    if (fileExists) {
        System.out.println("Deleting File: " + fileName);
        //fileExists = !Files.delete(path);                          // Error-1: throws an IOException

        try {
            fileExists = !Files.delete(path);                        // Error-1a: Operator ! cannot be applied to 'void'
        } catch (IOException e) {
            throw new RuntimeException(e); 
        }
    }

    if (!fileExists) {
        try {
            Files.createNewFile(path);
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
        System.out.println("Created File: " + fileName);
        if (Files.canWrite(path)) {
            System.out.println("Would write to file here");
        }
    }
}
```

I can hover over that, and now I see,
_the **delete** method on **Files** throws an IOException_,
so I have to either catch or specify.
I'll pick surround with _try-catch_ here.
Now, I have a different error, 
and this is another difference between the _delete_ methods.
Because the one on this class throws an exception when there's a problem,
it doesn't return a **boolean** value, or any value for that matter, it's **void**.
I have to change my code.

```java  
private static void usePath(String fileName) {

    Path path = Path.of(fileName);
    boolean fileExists = Files.exists(path);

    System.out.printf("File '%s' %s%n", fileName, fileExists ? "exists." : "does not exist.");
    if (fileExists) {
        System.out.println("Deleting File: " + fileName);
        //fileExists = !Files.delete(path);                          // Error-1: throws an IOException

        try {
            //fileExists = !Files.delete(path);                        // Error-1a: Operator ! cannot be applied to 'void'

            Files.delete(path);
            fileExists = false;
        } catch (IOException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }
    }

    if (!fileExists) {
        try {
            Files.createNewFile(path);
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
        System.out.println("Created File: " + fileName);
        if (Files.canWrite(path)) {
            System.out.println("Would write to file here");
        }
    }
}
```

I'll remove _fileExists_, the assignment, and the not before that method call.
I'll set _fileExists_ to **false**, after that _delete_ statement.
I also don't want to throw a runtime exception,
so I'll change the generated _catch_ clause.
I'll remove that throw new _RuntimeException_ statement too,
because I'm going to put something else there.
In the _try_ clause, I'll just print the exception I got.
The _delete_ method is all good now, 
so moving down to the _createNewFile_ method.

```java  
private static void usePath(String fileName) {

    Path path = Path.of(fileName);
    boolean fileExists = Files.exists(path);

    System.out.printf("File '%s' %s%n", fileName, fileExists ? "exists." : "does not exist.");
    if (fileExists) {
        System.out.println("Deleting File: " + fileName);

        try {
            Files.delete(path);
            fileExists = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    if (!fileExists) {
        try {
            //Files.createNewFile(path);                             // Error-2: 'createNewFile' doesn't exist on Files.

            Files.createFile(path);
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
        System.out.println("Created File: " + fileName);
        if (Files.canWrite(path)) {
            System.out.println("Would write to file here");
        }
    }
}
```

I'll delete that method, since it doesn't exist on **Files**.
I'll delete the dot there, and enter it again,
so IntelliJ will display a list of methods available on **Files**.
Here you can see _createFile_ near the top of the list.
I'll pick that one.
This change seems fine, and I already have it in a _try-catch_ block.
The one difference I do want to point out to you is, 
the **Files** class's version to create a file, doesn't return a boolean,
as did the **file** class, which returned **true** for success, 
and **false** for failure.
Similar to the _delete_ method, it will throw an exception for a failure.
This feels like we're moving from a look before you leap approach, 
to an easy to ask for forgiveness response, doesn't it?

```java  
private static void usePath(String fileName) {

    Path path = Path.of(fileName);
    boolean fileExists = Files.exists(path);

    System.out.printf("File '%s' %s%n", fileName, fileExists ? "exists." : "does not exist.");
    if (fileExists) {
        System.out.println("Deleting File: " + fileName);

        try {
            Files.delete(path);
            fileExists = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    if (!fileExists) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
        System.out.println("Created File: " + fileName);
        if (Files.canWrite(path)) {                                // Error-3: 'canWrite' doesn't exist on Files.
            if (Files.isWritable(path)) {
                System.out.println("Would write to file here");
            }
        }
    }
```

Moving on to the _canWrite_ method, let's delete it, retyping the dot, 
again to see the list of possible methods.
Right away, I'll see an _isWritable_ method, so I'll select that.
Now, everything compiles, and that wasn't too painful.

```java  
public class Main {

    public static void main(String[] args) {

        useFile("testfile.txt");
        usePath("pathfile.txt");
    }
]
```

I'll add a call to this method, with a different filename, in the _main_ method.
I'll call _usePath_, and my file name will be `pathfile.txt`.
I'll run this:

```html  
File 'pathfile.txt' does not exist.
Created File: pathfile.txt
Would write to file here
```

In the second set of outputs, I can see file `pathfile.txt` does not exist,
and that the code says _it was created_, and that it can be written to.
Again, opening the project panel, I want to confirm it's been created,
and sure enough, I can see it's there.
All I've really demonstrated by this exercise is 
that you can do the same thing with **Files** and **path**, 
as you can, simply using a **File** instance.
But now, let's make another change to the _usePath_ method.
Here, after I test if the file is **Writable**,
I print this statement to the console.

```java  
private static void usePath(String fileName) {

    Path path = Path.of(fileName);
    boolean fileExists = Files.exists(path);

    System.out.printf("File '%s' %s%n", fileName, fileExists ? "exists." : "does not exist.");

    if (fileExists) {
        System.out.println("Deleting File: " + fileName);
        try {
            Files.delete(path);
            fileExists = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    if (!fileExists) {
        try {
            Files.createFile(path);
            System.out.println("Created File: " + fileName);
            if (Files.isWritable(path)) {
                //System.out.println("Would write to file here");
                Files.writeString(path, """
                        Here is some data,
                        For my file,
                        just to prove,
                        Using the Files class and path are better!
                        """);
            }
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
    }
}
```

This time, however, I really am going to write to the file.
I'll call a method named _writeString_ on this **Files** helper class,
again passing it my path variable, and a **String** as the second argument.
I'll pass it a text block with several lines of text.
I want this text to be written to my newly created file. 
I should see in my file, after this code is run. 
_Here is some data, for my file, just to prove, 
using the **Files** class and **path** are better._
I'm getting the error, that says _this throws an unhandled exception_.
Rather than surround this with a second _try-catch_, 
what I'll do is, include this code with the code above it, 
in one big _try-catch_.
I'll cut the _catch_ clause I have above this code.
I'll paste that, after my nested if statement.
I can reformat that with the control+alt+L on windows, or for mac.
I'll comment _println_ because we have now implemented code 
to write to file and this comment is redundant.
Running with that change:

```html  
File 'pathfile.txt' exists.
Deleting File: pathfile.txt
Created File: pathfile.txt
```

I can now open that file, `pathfile.txt`, 
and there is the text from my text block.
Ok, that was super easy to do.
I can also read this information from the file, just as easily.

```java  
private static void usePath(String fileName) {

    Path path = Path.of(fileName);
    boolean fileExists = Files.exists(path);

    System.out.printf("File '%s' %s%n", fileName, fileExists ? "exists." : "does not exist.");

    if (fileExists) {
        System.out.println("Deleting File: " + fileName);
        try {
            Files.delete(path);
            fileExists = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    if (!fileExists) {
        try {
            Files.createFile(path);
            System.out.println("Created File: " + fileName);
            if (Files.isWritable(path)) {
                Files.writeString(path, """
                        Here is some data,
                        For my file,
                        just to prove,
                        Using the Files class and path are better!
                        """);
            }
            System.out.println("And I can read too");
            System.out.println("-------------------------");
            Files.readAllLines(path).forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
    }
}
```

I'll add a couple of statements, after that _isWritable_ if-clause.
I'll print that _I can read too_, following that with a separator line.
And now, in a single line of code, 
I can call the _readAllLines_ method on **Files**, 
passing the result directly to a _forEach_ method, 
and I'll pass that the method reference for `System.out.println`.
Running this:

```html
And I can read too
-------------------------
Here is some data,
For my file,
just to prove,
Using the Files class and path are better!
```

I can see in my output the statement, and _I can read too_, followed by dashes.
After that, here are the lines that the code read from my text file, 
printed to the console.
I'll be talking more about reading and writing to files in future sections,
but that's just about as easy as it gets.
Ok, let's quick look at some of the functionality the **IO**,
and **NIO2** methods, have in common, on a table.

| Functionality                   | File instance methods          | File static methods, with Path argument                   |
|---------------------------------|--------------------------------|-----------------------------------------------------------|
| create file                     | `createNewFile()`              | `createFile(Path p)`                                      |
| delete directory of file        | `delete()`                     | `delete(Path p)`<br/>`deleteIfExists(Path p)`             |
| check path type                 | `isDirectory()`<br/>`isFile()` | `isDirectory(Path p)`<br/>`isRegularFile(Path p)`         |
| get byte size of file           | `length()`                     | `size(Path p)`                                            |
| List directory contents         | `listFiles`                    | `list(Path p)`                                            |
| create directory or directories | `mkdir()`<br/>`mkdirs()`       | `createDirectory(Path p)`<br/>`createDirectories(Path p)` |
| Rename                          | `renameTo(File dest)`          | `move(Path src, Path dest)`                               |


This table shows you quite a bit of the functionality 
you'd expect from a **File System**, 
and the methods you'd use for each of these classes.
You can see the methods are often similarly named, 
and methods exist for all the basics.
I'm not going to go through each of these individually, 
and compare them to each other.
What I want you to remember is, if you need to support legacy code,
or if you need to be backwards compatible, 
to a version of the JDK that's older than JDK7,
you can still rely on the **File** class.
If you're starting out new, I'm recommending you use the **Files** class
(**Files with an s**), and a **Path** instance,
otherwise known as the **NIO2** way of doing things.
Let's review why **NIO2** is better, under the covers.

First of all, **File** operations have been improved.
The **NIO2** types include support for:

* Asynchronous file I/O operations. 
* File locking, including more granular locking.
This means, instead of locking the entire file, a region of it can be locked. 
* There's enhanced support for file metadata retrieval. 
* As well as new support for symbolic link manipulation.
* Finally, the **NIO2** types have support for file system notifications. 
This means changes occurring on a path, can be made watchable to registered services.

I plan to give you an example of this in the concurrency section of the course, 
where it should make more sense.
The **NIO2** types have been designed to be better performing.
NIO2 types are non-blocking, meaning asynchronous access to resources,
by multiple threads, is supported.
They manage memory more efficiently, reading and writing files 
directly to and from memory into buffers, through something called a **FileChannel**.
I'll be covering reading from and writing to files shortly, 
and I'll discuss the **FileChannel** and its advantages.
You can also read from or write to multiple buffers in a single operation,
which I'll be showing you as well.
At this point, I'll be switching to just using the **Files** and **Path** code,
otherwise called the **NIO2** types, 
and focusing on the functionality these types offer us.
</div>

### [Path Class]()
<div align="justify">

In this section, I'm going to begin by reviewing some methods 
on the **Path** interface with you.

```java  
public static void main(String[] args) {

    Path path = Path.of("files/testing.txt"); 
    printPathInfo(path);
}

private static void printPathInfo(Path path) {

        System.out.println("Path: " + path); 
        System.out.println("fileName = " + path.getFileName()); 
        System.out.println("parent = " + path.getParent()); 
        Path absolutePath = path.toAbsolutePath();
        System.out.println("Absolute Path: = " + absolutePath);
        System.out.println("Absolute Path Root: = " + absolutePath.getRoot());  
        System.out.println("Root = " + path.getRoot()); 
        System.out.println("isAbsolute = " + path.isAbsolute());
        System.out.println("-----------------------");
    }
```

I'll start, though, with a private static void method, 
called _printPathInfo_, that takes a **Path** instance, 
and will use several methods on _path_.
I'll print the _path_.
**Path** has a method called _getFileName_, so I'll print that.
I'll also print the result of another method, `getParent()`.
I'll get the _toAbsolutePath_, and assign that to a variable. 
And print that.
I'll use the _absolutePath_ to get the root and print that.
I'll also call the _getRoot_ method, on the current path, 
to compare that to what I get above.
Next, I'll print the **boolean** value I get back, 
from the _isAbsolute_ method.
Lastly, I'll print a separator line.
In the _main_ method, I'll call this method.
First, I'll create a _path_ variable 
and assign that a _path_ instance, 
using the factory method on **Path**.
In this case, I'll use a **String** literal 
which contains a _filename_,
as well as a reference to its parent folder.
I'll pass that to my method, _printPathInfo_.
Now, this folder and this file don't yet exist in my project.
If I run this:

```html  
Path: files\testing.txt
fileName = testing.txt
parent = files
Absolute Path: = D:\JAVA_STUDY\Github\JAVA\files\testing.txt
Absolute Path Root: = D:\
Root = null
isAbsolute = false
-----------------------
```

There are a couple of things I want to point out.
In the first case, when you print _path_ 
you can see it's what you constructed it with,
so it's giving me back what I passed in the **String** literal.
The _getFilename_ gave me `testing.txt`,
and the _getParent_ returned just files.
Here is the absolute path, using the **Path** instance.
From that, I can get the absolute path's root directory.
But notice next, that just calling _getRoot_ on the original _path_,
returns a **null**.
This is one indication that this is a relative path.
To confirm that, I can see that
the result of calling _isAbsolute_ is **false**.
Now let's print the hierarchy of this absolute path.
I'll insert this code before the last separator line there.

```java  
public static void main(String[] args) {

    Path path = Path.of("folders/testing.txt"); 
    printPathInfo(path);
}

private static void printPathInfo(Path path) {

        System.out.println("Path: " + path); 
        System.out.println("fileName = " + path.getFileName()); 
        System.out.println("parent = " + path.getParent()); 
        Path absolutePath = path.toAbsolutePath();
        System.out.println("Absolute Path: = " + absolutePath);
        System.out.println("Absolute Path Root: = " + absolutePath.getRoot());  
        System.out.println("Root = " + path.getRoot()); 
        System.out.println("isAbsolute = " + path.isAbsolute());  
        
        System.out.println(absolutePath.getRoot());
        
        int i = 1;
        var it = path.toAbsolutePath().iterator();
        while (it.hasNext()) {
            System.out.println(".".repeat(i++) + " " + it.next());
        }
    
        int pathParts = absolutePath.getNameCount();
        for (int i = 0; i < pathParts; i++) {
            System.out.println(".".repeat(i + 1) + " " + absolutePath.getName(i));
        }
        System.out.println("-----------------------");
    }
```

I'll print the root again. 
I'll initialize a counter, which I'll use for indentation.
I can get an _iterator_ of all the folders 
that make up the **Path** instance.
Here, I'll use an absolute path, since I want to start at the root.
I can loop through this _iterator_, with a while loop, 
checking the _hasNext_ value on the _iterator_.
I'll print the current element, so `it.next()`, with indentation.
I'll re-run this code:

```html  
Path: files\testing.txt
fileName = testing.txt
parent = files
Absolute Path: = D:\JAVA_STUDY\Github\JAVA\files\testing.txt
Absolute Path Root: = D:\
Root = null
isAbsolute = false
-----------------------
D:\
. JAVA_STUDY
.. Github
... JAVA
.... files
..... testing.txt
-----------------------
```

Ok, so now I hope you can see the full hierarchy of the file path, 
including where the new file would be placed, in this tree.
Each iteration represents another subfolder,
and I've displayed this, in a rudimentary way.
Instead of using an _iterator_, to loop through the directory tree,
I can instead use two other methods available to me on _path_, 
which I'll show you next.

```java  
public static void main(String[] args) {

    Path path = Path.of("folders/testing.txt"); 
    printPathInfo(path);
    logStatement(path);
    extraInfo(path);
}

private static void printPathInfo(Path path) {

    System.out.println("Path: " + path); 
    System.out.println("fileName = " + path.getFileName()); 
    System.out.println("parent = " + path.getParent()); 
    Path absolutePath = path.toAbsolutePath();
    System.out.println("Absolute Path: = " + absolutePath);
    System.out.println("Absolute Path Root: = " + absolutePath.getRoot());  
    System.out.println("Root = " + path.getRoot()); 
    System.out.println("isAbsolute = " + path.isAbsolute());  
    
    System.out.println(absolutePath.getRoot());
/*    
    int i = 1;
    var it = path.toAbsolutePath().iterator();
    while (it.hasNext()) {
        System.out.println(".".repeat(i++) + " " + it.next());
    }
*/

    int pathParts = absolutePath.getNameCount();
    for (int i = 0; i < pathParts; i++) {
        System.out.println(".".repeat(i + 1) + " " + absolutePath.getName(i));
    }
    System.out.println("-----------------------");
}
```

Getting back to the _printPathInfo_ method, 
I'll first comment out the iterator code there.
I can get the depth of the directory tree by using _getNameCount_.
I'll use the absolutePath here again, because
I want to start at the root for this exercise.
I'll loop from zero to the folder depth.
I'll print the same statement I had before, but in this case,
I'll get the directory name, at each level, 
by calling _getName_, on the _absolutePath_,
passing it the current index value. 
I need to add 1 to the index I use in the _repeat_ method, 
because the first folder, or index 0, isn't the root.
Running this code:

```html  
Path: files\testing.txt
fileName = testing.txt
parent = files
Absolute Path: = D:\JAVA_STUDY\Github\JAVA\files\testing.txt
Absolute Path Root: = D:\
Root = null
isAbsolute = false
-----------------------
D:\
. JAVA_STUDY
.. Github
... JAVA
.... files
..... testing.txt
-----------------------
```

This gives me the same output as I had when I used the _iterator_.
Using the _getName_ method though, gives you a lot more flexibility
in how you might iterate through the file tree.
In this hierarchy, I know that the last two paths don't exist yet.
I'll create some code to fix this, first by creating another private static void method.

```java  
public static void main(String[] args) {

    Path path = Path.of("files/testing.txt");
    printPathInfo(path);
    logStatement(path);
}

private static void logStatement(Path path) {

    try {
        Path parent = path.getParent();
        if (!Files.exists(parent)) {
            Files.createDirectory(parent);
        }
        Files.writeString(path, Instant.now() + ": hello file world\n", 
                StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
```

I'm going to call this _logStatement_.
This might seem like a strange name for a method that will create my file,
but you'll see why I'm calling it this, in just a moment.
This takes a _path_ as an argument.
I'll start out with a _try_ block, because I know I'm going to need it.
I want to first get the parent folder, 
of the last element in the path, which the _getParent_ method does.
If this folder doesn't exist, in the file system, I want to create it.
I can do that with the static _createDirectory_ method on **Files**,
passing it the parent _path_.
And that method throws an _IOException_, 
so I'm catching that here. 
Since my plan is to log statements to a file, 
I don't want to throw an exception, or quit if I can't.
This kind of ignores the error unless someone's watching the default output.
Next, I need to create the file.
There's a method that lets me both create and write to, 
a file in a single statement, so let me show you that.
I'm going to again use `Files.writeString`, which I used in the last section,
passing it _path_ as the first argument.
The second argument is the string that will get printed.
Since this is a log file, I'll include date and time.
I'll use `Instant.now`, to get a timestamp, 
and I'll print _hello file world_, with an ending new line character.
This method takes a third argument, the last,
and that's a variable list of **Option** types.
You can find these options, on the **StandardOpenOption** enum,
in the `java.nio.file` package.
First, I'll specify the _create_ option, 
so if this file doesn't exist, it will get created for me.
Second, I'll say I want the _APPEND_ option, which means each statement
will get appended to the end of the file.
I'll cover these options, and others, in more detail in upcoming sections, 
that cover reading and writing to files.
Next, I'll go to the _main_ method, and add a call to this method.
Running this code:

```html  
Path: files\testing.txt
fileName = testing.txt
parent = files
Absolute Path: = D:\JAVA_STUDY\Github\JAVA\files\testing.txt
Absolute Path Root: = D:\
Root = null
isAbsolute = false
-----------------------
D:\
. JAVA_STUDY
.. Github
... JAVA
.... files
..... testing.txt
-----------------------
```

I don't get any additional messages, but if I open the project panel, 
I can see the files folder has been created, 
and in it, is the `testing.txt` file.
If I open that file, there I can see my log statement.
If I run the code again and look at the file, 
I'll see a second log statement was added.
Ok, that's great, but what happens 
if my file path contains several folders that don't yet exist?
I'll set that up, and change my path to include some more subfolders.

```java  
public static void main(String[] args) {

    //Path path = Path.of("files/testing.txt");
    Path path = Path.of("this/is/several/folders/testing.txt");
    printPathInfo(path);
    logStatement(path);
}
```

Here, this entire path of folders doesn't exist.
If I run the code with this path:

```html  
Path: files\testing.txt
fileName = testing.txt
parent = files
Absolute Path: = D:\JAVA_STUDY\Github\JAVA\files\testing.txt
Absolute Path Root: = D:\
Root = null
isAbsolute = false
-----------------------
D:\
. JAVA_STUDY
.. Github
... JAVA
.... files
..... testing.txt
-----------------------
java.nio.file.NoSuchFileException : this\is\several\folders
    at java.base/sun.nio.fs.WindowsException.translateToIOException()
    at java.base/sun.nio.fs.WindowsException.rethrowAsIOException()
    at java.base/sun.nio.fs.WindowsException.rethrowAsIOException()
    at java.base/sun.nio.fs.WindowsFileSystemProvider.createDirectory()
    at java.base/sun.nio.file.Files.createDirectory()
    at Main.logStatement()
    at Main.main()
```

I get a _NoSuchFileException_ when I call the _createDirectory_ method.
Now, I could put this code in a loop, one that iterates through the paths, 
similar to what I did printing out the paths earlier.
But the **Files** class gives us a better option, 
a method appropriately called _createDirectories_.

```java  
public static void main(String[] args) {

    Path path = Path.of("files/testing.txt");
    printPathInfo(path);
    logStatement(path);
}

private static void logStatement(Path path) {

    try {
        Path parent = path.getParent();
        if (!Files.exists(parent)) {
            //Files.createDirectory(parent);
            Files.createDirectories(parent);
        }
        Files.writeString(path, Instant.now() + ": hello file world\n", 
                StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
```

I'll go to my _logStatement_ method, and simply change the method 
I'm calling here.
This _createDirectories_ method will work on one, or many folders, 
so I could have used it previously, with `files/testing.txt` as well.
Ok, so if I run this now:

```html  
Path: files\testing.txt
fileName = testing.txt
parent = files
Absolute Path: = D:\JAVA_STUDY\Github\JAVA\files\testing.txt
Absolute Path Root: = D:\
Root = null
isAbsolute = false
-----------------------
D:\
. JAVA_STUDY
.. Github
... JAVA
.... files
..... testing.txt
-----------------------
```

I don't get any errors.
From the project panel, I can see the directories were all created,
and there's my new _testing.txt_ file, so I'll open that, 
and there again is a log statement.
I don't want to go too deeply into writing to files in this section, 
but I did want to give you a real quick sample, 
of this one-stop shop method, to create and write to a file.
Now, I want to get back to the **Path**'s methods,
so let's see what kind of information, we can get about this file.

```java  
public static void main(String[] args) {

    Path path = Path.of("this/is/several/folders/testing.txt");
    //printPathInfo(path);
    logStatement(path);
    extraInfo(path);
}

private static void logStatement(Path path) {

    try {
        Path parent = path.getParent();
        if (!Files.exists(parent)) {
            //Files.createDirectory(parent);
            Files.createDirectories(parent);
        }
        Files.writeString(path, Instant.now() + ": hello file world\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

private static void extraInfo(Path path) {

    try {
        var atts = Files.readAttributes(path, "*");
        atts.entrySet().forEach(System.out::println);
    } catch (IOException e) {
        System.out.println("Problem getting attributes");
    }
}
```

I'll create another method, called _extraInfo_,
so private static void, and it also takes a **Path**.
I'll start out with a _try_ statement, to save time.
I'll call `Files.readAttributes`, 
which takes at least two parameters, the **path**, and a **String**.
The string defines a list of attribute names you want information about. 
You can pass an asterisk, which is a wildcard to retrieve 
all the file attributes, which is what I'm doing here.
This method returns a map, so I'll use the _forEach_ method 
on the **entrySet**, to quickly print each attribute key value pair. 
This method throws a **checked IOException**.
I'll just print a message to the console if there's a problem.
Getting back to the _main_ method, 
I'll comment out the call to the _printPathInfo_ method.
I'll then call _extraInfo_, after the _logStatement_ call.
Running this code:

```html  
lastAccessTime=2024-04-20T21:12:37.1007896Z
lastModifiedTime=2024-04-20T21:12:37.0987898Z
size=49
creationTime=2024-04-20T21:12:37.0967879Z
isSymbolicLink=false
isRegularFile=true
fileKey=null
isOther=false
isDirectory=false
```

I'll get OS specific attributes in this file.
I've got _lastAccessTime_, _lastModifiedTime_, _size_
which is a few bytes, because of the couple of log statements
contained in this file.
There's _creationTime_, and a few others.
If you're using **linux** or **mac**, 
you might see a different set of attributes.
**File** attributes are going to be different, 
on different operating systems.
In addition to _getAttributes_, there's also _getAttribute_, 
singular, so you can get one attribute at a time, 
by passing a specific string literal.

| String Literal to pass to the<br/> `Files.getAttribute` method | Type:    | Alternate method            |
|----------------------------------------------------------------|----------|-----------------------------|
| "lastModifiedTime"                                             | FileTime | `Files.getLastModifiedTime` |
| "lastAccessTime"                                               | FileTime |                             |
| "creationTime"                                                 | FileTime |                             |
| "size"                                                         | Long     | `Files.size`                |
| "isRegularFile"                                                | Boolean  | `Files.isRegularFile`       |
| "isDirectory"                                                  | Boolean  | `Files.isDirectory`         |
| "isSymbolicLink"                                               | Boolean  | `Files.isSymbolicLink`      |
| "isOther"                                                      | Boolean  |                             |

This table shows you the possible values for the string literal,
you can pass to the `Files.getAttribute` method.
The `Files.getAttribute` method returns this data as an **Object**, 
which means you may have to cast it 
to the actual type shown in the second column, before processing.
In addition to the _getAttributes_, and _getAttribute_ method, 
you can get a couple of these fields,
by specifically named methods on the **Files** class, 
as shown in the last column.
Let me show you one more method that will help you understand 
the content of the file you might be working with.

```java  
public static void main(String[] args) {

    Path path = Path.of("this/is/several/folders/testing.txt");
    //printPathInfo(path);
    logStatement(path);
    extraInfo(path);
}

private static void logStatement(Path path) {

    try {
        Path parent = path.getParent();
        if (!Files.exists(parent)) {
            //Files.createDirectory(parent);
            Files.createDirectories(parent);
        }
        Files.writeString(path, Instant.now() + ": hello file world\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

private static void extraInfo(Path path) {

    try {
        var atts = Files.readAttributes(path, "*");
        atts.entrySet().forEach(System.out::println);
        System.out.println(Files.probeContentType(path));
    } catch (IOException e) {
        System.out.println("Problem getting attributes");
    }
}
```

This method is called _probeContentType_, 
and I'll just print what that is for this empty file named `myData.txt`.
Running this:

```html  
lastAccessTime=2024-04-20T21:12:37.1007896Z
lastModifiedTime=2024-04-20T21:12:37.0987898Z
size=49
creationTime=2024-04-20T21:12:37.0967879Z
isSymbolicLink=false
isRegularFile=true
fileKey=null
isOther=false
isDirectory=false
text/plain
```

You'll see the last statement, 
indicating this file has the content type of `text/plain`.
You may be familiar with content in the context of web development
because the content type is used 
to indicate the type of data is being sent or received.
Ok, so this section has covered most of the methods 
available on the **Path** class.
I also demonstrated how to use the _createDirectories_ method,
and the overloaded version of `writeString` on the **Files** class, 
which takes multiple _options_.
In this case, I used _options_ that let me both create a file, 
and write to it at the same time.
</div>

### [Files Class]()
<div align="justify">

I'm just going to jump right in, 
and show you some other beneficial methods 
on the **Files** class.
These are _list_, _walk_, and _find_, 
all which are methods that can be used to get a source, 
for a stream pipeline of path elements.

```java  
public static void main(String[] args) {

    Path path = Path.of("");
    System.out.println("cwd = " + path.toAbsolutePath());
         
    try (Stream<Path> paths = Files.list(path)) {
        paths.forEach(System.out::println);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}

```

I'll start by creating a path variable, named _path_.
You'll remember that if I create a _path_ with an empty string,
this is the current working directory, which is what I'm doing here. 
I'll print the full path of my current directory, 
using the _toAbsolutePath_ method, on the **Path** interface.
A widespread need is to understand what the contents of a directory are.
Maybe you've used the `dir` command on **windows**,
or `ls` on **linux**, this is the same concept.
The **Files** class has a _list_ method, and as usual, 
it takes a _path_ instance.
This method returns a stream of path instances,
each path representing either a file, 
or a subfolder, in the directory I specified.
Next, I'll create a local variable, and that's going to be a **Stream**,
typed with **Path**, and called _paths_.
I'll assign this the result of calling the static method named list, 
on the **Files** class, passing it my _path_ instance, 
and that's going to return a **Stream** of type **Path**.
Now, here again, you can see I can't use this method,
without either catching or specifying an _IOException_.
I'll surround it with a _try-catch_, using IntelliJ's help.
I still have a warning though, on the _list_ method, 
which I want to look into further.
Hovering over that, IntelliJ tells me that 
_Stream is used without a **try-with-resources** statement_.
Why is IntelliJ giving us this warning?
We didn't see it for other methods in this class,
like _writeString_, or _readAllLines,_ for example.
What's happening with this method?
Remember, a stream is lazily executed.
This means a reference to an open directory is maintained 
after this call, and remains open 
until the stream's terminal operation is executed.
Sometimes it's easy to forget the methods that return streams 
are opening a resource.
Other methods, like _readAllLines_, for example, 
are both opening and closing the resource for you, 
as a convenience.
You should always use a _try-with-resources_ statement, 
for the _list_, _walk_, and _find_ methods, 
that return these streams of paths.
Again, I'll use IntelliJ to assist me 
with making the change to _try-with-resources_.
First, I'll revert what I did earlier, 
removing the _try-catch_.
This time, I'll place my cursor or hover over that method name, 
_list_, and use the key combination, alt enter for windows, 
or option return for a mac.
From this dialog, I'll select, surround with _try-with-resources_ block.
I can't stop there though, since I still have to catch an _IOException_.
I need to add _catch_ clauses, and I can preview the generated code in that dialog.
I'll select that option.
Ok, now I can start adding code in the _try_ block, 
to actually do something with this stream of paths.
In this case, I'll add a single statement, 
the familiar _forEach_ terminal operation on the stream called _paths_ here.
I'll call _forEach_, printing each path.
If I run this:

```html  
cwd = D:\JAVA_STUDY\Github\JAVA
.git
.gitignore
.idea
JAVA.iml
out
README.md
src
```

I'll see all the folders and files in my current working directory.
This method isn't recursive, so I won't see the content of any subfolders.
From this listing, the only way to tell if it's a directory or file is 
by observing that files usually have file extensions,
but this isn't a rule you can depend on.

```java
private static String listDir(Path path) {

    try {
        boolean isDir = Files.isDirectory(path);
        FileTime dateField = Files.getLastModifiedTime(path);
        return "%s %-15s %s".formatted(dateField, (isDir ? "<DIR>" : ""), path);
    } catch (IOException e) {
        System.out.println("Whoops! Something went wrong with " + path);
        return path.toString();
    }
}
```

I'll create a private static method next, 
that'll use attributes from each _path_ instance,
to print a friendly directory listing.
I'll call this method _listDir_, 
and it'll take a _path_, returning a **string**.
I'll start with a _try_ block, 
because some of the methods I want to use throw a _checked IOException_.
I'll use the `Files.isDirectory` method, 
assigning that to a **boolean** variable, I'm calling _isDir_.
If there's an _IOException_ for any statements I call above, 
I'll print _something went wrong_,
and I'll simply return the path as a string.
I'll use my boolean variable to determine what gets printed, 
outputting different information, for a directory item, versus a file.
Before I do that, I'll get the _lastModifiedTime_.
In an earlier section, I mentioned that there are multiple ways 
to get data about a file, either by calling _getAttributes_ 
or _getAttribute_ on **File**.
A simpler way is just to _getLastModifiedTime_.
I'll use **FileTime** as the type, and _dateField_ as the variable name, 
and then call `Files.getLastModifiedTime`.
Now, I'll return a formatted String, 
my first field will be _lastModifiedTime_.
Here, I'll just use the `%s` modifier, 
because I'm just going to pass the _dateField_ as is,
so the _toString_ method will get called on this field. 
If the _path_ is a directory, I'll include _DIR_ in angle brackets, 
and lastly, I'll print the string representation of the path.

```java  
public static void main(String[] args) {

    Path path = Path.of("");
    System.out.println("cwd = " + path.toAbsolutePath());
         
    try (Stream<Path> paths = Files.list(path)) {
        paths.map(Main::listDir)
                .forEach(System.out::println);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}

```

Getting back to the _main_ method, I'll now include a _map_ intermediate operation 
in my stream pipeline.
I'll add a new line before the `.forEach`.
Now, I'll insert the map operation, that will call my _listDir_ method.
I'll pass this as a method reference to the method
I just created, so `Main::listDir`.
This will transform my path to a String.
Running this code:

```html  
cwd = D:\JAVA_STUDY\Github\JAVA
2024-04-20T22:19:19.7669491Z <DIR>           .git 
2024-03-15T09:31:51.6053631Z                 .gitignore
2024-04-20T22:46:14.5103977Z <DIR>           .idea
2024-03-19T00:18:21.6302488Z                 JAVA.iml
2024-03-18T21:42:22.5590715Z <DIR>           out
2024-03-23T20:46:28.8488077Z                 README.md
2024-04-20T21:25:32.490289Z <DIR>            src
```

My output is looking a little closer to 
what a windows directory listing might look like.
I can easily identify a directory now.
Notice the dates in the output, they end in _Z_.
Remember, _Z_ is code for UTC date and time.
For the average user, this would be confusing to see, 
so it would make more sense to show them their local date time.
I'll refine my listing method to do this.
I'll reformat the date and time here,
and including the size of the file.

```java
private static String listDir(Path path) {

    try {
        boolean isDir = Files.isDirectory(path);
        FileTime dateField = Files.getLastModifiedTime(path);
        //return "%s %-15s %s".formatted(dateField, (isDir ? "<DIR>" : ""), path);

        LocalDateTime modDT = LocalDateTime.ofInstant(dateField.toInstant(), ZoneId.systemDefault());
        return "%tD %tT %-5s %12s %s".formatted(modDT, modDT, (isDir ? "<DIR>" : ""), 
                (isDir ? "" : Files.size(path)), path);
    } catch (IOException e) {
        System.out.println("Whoops! Something went wrong with " + path);
        return path.toString();
    }
}
```

I'll start by setting up a _LocalDateTime_ variable, _modDT_.
I can create a _LocalDateTime_ out _ofInstant_ 
and a _ZoneId_, using the of _Instant_ method.
The **FileTime** type has a _toInstant_ method on it, 
so I can pass that, as the first argument.
Next, I can get the local Zone, from `ZoneId.systemDefault`.
I'll also change the formatted string, for the output.
First, I'll add two date time specifiers.
I'll replace the first `%s` with `%tD` for **Date**,
and add a `%t` capital `T` for time, after that.
Next, where I have _dateField_, I'll replace that with _modDT_, 
but I need _modDT_ in there twice.
I could change my formatted string to include argument indices, 
but for now, this is just simpler.
Before I run this, I'll also include `Files.size`, if it's a file.
First, I'll change the `-15` in my output to `-5`, 
this is left justifying.
My date time fields should all be the same width now, 
so I can adjust for just this field's size.
I'll add percent `%12s` after that, for the size, 
this is right justified,
and allows size a maximum width of 12.
Next, in the argument list, I need to include the size, 
for files only, so I'll include a ternary operator, 
before the last path argument.
If it's a directory, I'll return an empty string, 
if it's a file, I'll return the size variable, 
and I'll get that with a call to `Files.size`, passing it _path_.
Running this code:

```html  
cwd = D:\JAVA_STUDY\Github\JAVA
04/21/24 01:19:19 <DIR>              .git
03/15/24 12:31:51                344 .gitignore
04/21/24 01:51:19 <DIR>              .idea
03/19/24 03:18:21                592 JAVA.iml
03/19/24 00:42:22 <DIR>              out
03/23/24 23:46:28                603 README.md
04/21/24 00:25:32 <DIR>              src
```

You can see that I have the file size, in bytes, 
of my three files in this directory.
This code gives you one example of how to use path data, 
and the _list_ method, to give your user information 
about a single path's components.
There's another method on **Files**, that's similar to list, 
this one is called _walk_.
Let me set this up.

```java  
public static void main(String[] args) {

    Path path = Path.of("");
    System.out.println("cwd = " + path.toAbsolutePath());
         
    try (Stream<Path> paths = Files.list(path)) {
        paths.map(Main::listDir)
                .forEach(System.out::println);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

    System.out.println("---------------------------------------");
    try (Stream<Path> paths = Files.walk(path, 1)) {
        paths.map(Main::listDir).forEach(System.out::println);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
```

I'll first print a separation line, to split the output, in the _main_ method.
I'll copy the entire _try-catch_ block that uses `Files.list`,
and paste a copy, below my separator line.
I'll change the method from list to walk, and I'll add a second argument, 
an integer, which I'll just make one, to start.
I'll explain this in a minute.
Running this code:

```html  
cwd = D:\JAVA_STUDY\Github\JAVA
04/21/24 01:19:19 <DIR>              .git
03/15/24 12:31:51                344 .gitignore
04/21/24 01:51:19 <DIR>              .idea
03/19/24 03:18:21                592 JAVA.iml
03/19/24 00:42:22 <DIR>              out
03/23/24 23:46:28                603 README.md
04/21/24 00:25:32 <DIR>              src
---------------------------------------
04/21/24 00:12:43 <DIR>
04/21/24 01:19:19 <DIR>              .git
03/15/24 12:31:51                344 .gitignore
04/21/24 01:51:19 <DIR>              .idea
03/19/24 03:18:21                592 JAVA.iml
03/19/24 00:42:22 <DIR>              out
03/23/24 23:46:28                603 README.md
04/21/24 00:25:32 <DIR>              src
```

I get the same output, using either _walk_ or _list_.
The significant difference between these methods,
is that _walk_ is recursive if I specify a depth greater than 1.

```java  
public static void main(String[] args) {

    Path path = Path.of("");
    System.out.println("cwd = " + path.toAbsolutePath());
         
    try (Stream<Path> paths = Files.list(path)) {
        paths.map(Main::listDir)
                .forEach(System.out::println);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

    System.out.println("---------------------------------------");
    try (Stream<Path> paths = Files.walk(path, 2)) {
        paths.filter(Files::isRegularFile)
                .map(Main::listDir)
                .forEach(System.out::println);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
```

The second argument in the call to the _walk_ method is the depth, 
so now I'll change that to two.
Running this:

```html  
cwd = D:\JAVA_STUDY\Github\JAVA
04/21/24 01:19:19 <DIR>              .git
03/15/24 12:31:51                344 .gitignore
04/21/24 01:51:19 <DIR>              .idea
03/19/24 03:18:21                592 JAVA.iml
03/19/24 00:42:22 <DIR>              out
03/23/24 23:46:28                603 README.md
04/21/24 00:25:32 <DIR>              src
---------------------------------------
04/21/24 00:12:43 <DIR>
04/21/24 01:19:19 <DIR>              .git
04/21/24 00:24:57                  4 .git\COMMIT_EDITMSG
03/15/24 12:36:53                424 .git\config
03/15/24 12:34:05                 73 .git\description
03/15/24 12:34:05                 23 .git\HEAD
03/15/24 12:34:05 <DIR>              .git\hooks
04/21/24 01:19:19             371645 .git\index
03/26/24 12:42:37 <DIR>              .git\info
03/26/24 12:42:12 <DIR>              .git\logs
04/21/24 00:24:57 <DIR>              .git\objects
03/26/24 12:42:12                241 .git\packed-refs
03/15/24 12:34:15 <DIR>              .git\refs
03/15/24 12:31:51                344 .gitignore
04/21/24 01:51:19 <DIR>              .idea
03/15/24 12:31:56                 50 .idea\.gitignore
03/24/24 13:59:12 <DIR>              .idea\inspectionProfiles
03/19/24 03:18:21 <DIR>              .idea\libraries
03/15/24 13:24:08                190 .idea\markdown.xml
03/15/24 12:31:52                284 .idea\misc.xml
03/15/24 12:31:52                255 .idea\modules.xml
03/15/24 12:40:49               8915 .idea\uiDesigner.xml
03/15/24 12:34:13                185 .idea\vcs.xml
04/21/24 01:51:19              28733 .idea\workspace.xml
03/19/24 03:18:21                592 JAVA.iml
03/19/24 00:42:22 <DIR>              out
03/19/24 00:42:22 <DIR>              out\production
03/23/24 23:46:28                603 README.md
04/21/24 00:25:32 <DIR>              src
04/21/24 01:15:31 <DIR>              src\external
03/19/24 03:03:40 <DIR>              src\GeeksForGeeksChallenges
02/26/24 04:46:48 <DIR>              src\HackerRankChallenges
12/13/23 03:08:24            2495536 src\mysql-connector-j-8.3.0.jar
03/17/24 20:05:56 <DIR>              src\PatikaDev
03/01/24 19:43:23            7296329 src\sqlite-jdbc-3.34.0.jar
03/21/24 13:09:40 <DIR>              src\Udemy
```

The code is now walking through the subfolders of the original path,
which was the current working directory.
The output is now showing us the contents of the `.idea` folder,
the _out_ folder and the _source_ folder, again,
not going any deeper than that second level.
I can use my stream pipeline techniques to filter out directories, 
and just list files.
I'll add this filter first, before the map operation.

```java  
public static void main(String[] args) {

    Path path = Path.of("");
    System.out.println("cwd = " + path.toAbsolutePath());
         
    try (Stream<Path> paths = Files.list(path)) {
        paths.map(Main::listDir)
                .forEach(System.out::println);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

    System.out.println("---------------------------------------");
    try (Stream<Path> paths = Files.walk(path, 2)) {
        paths.filter(Files::isRegularFile)
                .map(Main::listDir)
                .forEach(System.out::println);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
```

I'll filter with a method reference, Files _isRegularFile_.
Running this code now:

```html  
cwd = D:\JAVA_STUDY\Github\JAVA
04/21/24 01:19:19 <DIR>              .git
03/15/24 12:31:51                344 .gitignore
04/21/24 01:51:19 <DIR>              .idea
03/19/24 03:18:21                592 JAVA.iml
03/19/24 00:42:22 <DIR>              out
03/23/24 23:46:28                603 README.md
04/21/24 00:25:32 <DIR>              src
---------------------------------------
04/21/24 00:24:57                  4 .git\COMMIT_EDITMSG
03/15/24 12:36:53                424 .git\config
03/15/24 12:34:05                 73 .git\description
03/15/24 12:34:05                 23 .git\HEAD
04/21/24 01:19:19             371645 .git\index
03/26/24 12:42:12                241 .git\packed-refs
03/15/24 12:31:51                344 .gitignore
03/15/24 12:31:56                 50 .idea\.gitignore
03/15/24 13:24:08                190 .idea\markdown.xml
03/15/24 12:31:52                284 .idea\misc.xml
03/15/24 12:31:52                255 .idea\modules.xml
03/15/24 12:40:49               8915 .idea\uiDesigner.xml
03/15/24 12:34:13                185 .idea\vcs.xml
04/21/24 02:08:10              28731 .idea\workspace.xml
03/19/24 03:18:21                592 JAVA.iml
03/23/24 23:46:28                603 README.md
12/13/23 03:08:24            2495536 src\mysql-connector-j-8.3.0.jar
03/01/24 19:43:23            7296329 src\sqlite-jdbc-3.34.0.jar
```

I only get a list of files, up to two levels deep from the current directory.
I could continue to filter and alter the depth 
to find a specific set of files with a single extension, or name, for example.
But actually, the **Files** class has an even better method 
for this kind of operation, and that's the _find_ method.
This is basically like the _walk_ method, but you can specify your condition 
as an argument, which is more efficient.

```java  
public static void main(String[] args) {

    Path path = Path.of("");
    System.out.println("cwd = " + path.toAbsolutePath());
         
    try (Stream<Path> paths = Files.list(path)) {
        paths.map(Main::listDir)
                .forEach(System.out::println);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

    System.out.println("---------------------------------------");
    try (Stream<Path> paths = Files.walk(path, 2)) {
        paths.filter(Files::isRegularFile)
                .map(Main::listDir)
                .forEach(System.out::println);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

    System.out.println("---------------------------------------");
    try (Stream<Path> paths = Files.find(path, 2, (p, attr) -> Files.isRegularFile(p))) {
        paths.map(Main::listDir).forEach(System.out::println);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
```

I'll copy all the code, including the separator line, and the code below it.
I'll paste that copy, at the end of my _main_ method.
In this last block of code, I'll change _walk_ to _find_,
and I'll insert a comma and a new line after the number 2 there.
The third argument is a **BiPredicate** type.
Hopefully you'll remember what that means,
**Bi** tells us it takes two arguments, 
and **Predicate** means it returns a **boolean**.
The first argument is the **path** itself, 
and the second is a type, named **BasicFileAttributes**.
You can think of this second argument as providing you 
with convenient access to all the attributes in your file or directory.
Whether I actually make use of both arguments in the expression is 
up to me, but I do need to include both when I define the lambda arguments.
Just a reminder, whenever I have two arguments, 
I need to include parentheses around them. 
Here, I'm going to call the path variable, _p_, 
so it won't conflict with the _path_ variable I already have.
This _p_ variable represents each stream element.
Next, I have the attribute data for each path, so I'll shorten that to _attr_. 
I can make the expression anything I want. 
First, I use the same _filter_ operation as the _walk_ statement, 
checking if the path on the stream is a regular file,
by calling _isRegularFile_, on the **Files** class, passing it _p_.
Finally, I can simply remove the _filter_ operation, from my pipeline.
If I run this:

```html  
---------------------------------------
04/21/24 00:24:57                  4 .git\COMMIT_EDITMSG
03/15/24 12:36:53                424 .git\config
03/15/24 12:34:05                 73 .git\description
03/15/24 12:34:05                 23 .git\HEAD
04/21/24 01:19:19             371645 .git\index
03/26/24 12:42:12                241 .git\packed-refs
03/15/24 12:31:51                344 .gitignore
03/15/24 12:31:56                 50 .idea\.gitignore
03/15/24 13:24:08                190 .idea\markdown.xml
03/15/24 12:31:52                284 .idea\misc.xml
03/15/24 12:31:52                255 .idea\modules.xml
03/15/24 12:40:49               8915 .idea\uiDesigner.xml
03/15/24 12:34:13                185 .idea\vcs.xml
04/21/24 02:08:10              28731 .idea\workspace.xml
03/19/24 03:18:21                592 JAVA.iml
03/23/24 23:46:28                603 README.md
12/13/23 03:08:24            2495536 src\mysql-connector-j-8.3.0.jar
03/01/24 19:43:23            7296329 src\sqlite-jdbc-3.34.0.jar
---------------------------------------
04/21/24 00:24:57                  4 .git\COMMIT_EDITMSG
03/15/24 12:36:53                424 .git\config
03/15/24 12:34:05                 73 .git\description
03/15/24 12:34:05                 23 .git\HEAD
04/21/24 01:19:19             371645 .git\index
03/26/24 12:42:12                241 .git\packed-refs
03/15/24 12:31:51                344 .gitignore
03/15/24 12:31:56                 50 .idea\.gitignore
03/15/24 13:24:08                190 .idea\markdown.xml
03/15/24 12:31:52                284 .idea\misc.xml
03/15/24 12:31:52                255 .idea\modules.xml
03/15/24 12:40:49               8915 .idea\uiDesigner.xml
03/15/24 12:34:13                185 .idea\vcs.xml
04/21/24 02:08:10              28731 .idea\workspace.xml
03/19/24 03:18:21                592 JAVA.iml
03/23/24 23:46:28                603 README.md
12/13/23 03:08:24            2495536 src\mysql-connector-j-8.3.0.jar
03/01/24 19:43:23            7296329 src\sqlite-jdbc-3.34.0.jar
```

My last block of output will look like the output from the previous _walk_ code.
That's nice and all, but why not just use _walk_ with _filter_,
which seems a little easier to understand.
Let's keep going and change this lambda.

```java  
public static void main(String[] args) {

    Path path = Path.of("");
    System.out.println("cwd = " + path.toAbsolutePath());
         
    try (Stream<Path> paths = Files.list(path)) {
        paths.map(Main::listDir)
                .forEach(System.out::println);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

    System.out.println("---------------------------------------");
    try (Stream<Path> paths = Files.walk(path, 2)) {
        paths.filter(Files::isRegularFile)
                .map(Main::listDir)
                .forEach(System.out::println);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

    System.out.println("---------------------------------------");
    try (Stream<Path> paths = Files.find(path, 2, (p, attr) -> attr.isRegularFile())) {
        paths.map(Main::listDir).forEach(System.out::println);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
```

First of all, I don't have to use `Files.isRegularFile`, at all.
I can use the attributes of the path, which are available to me here,
in an easy-to-use way, to _filter_ instead.
I'll replace **Files** with my argument name, _attr_.
Now, I don't have to pass an argument 
because this attribute is already associated with the current path in the stream.
Running that:

```html
---------------------------------------
04/21/24 00:24:57                  4 .git\COMMIT_EDITMSG
03/15/24 12:36:53                424 .git\config
03/15/24 12:34:05                 73 .git\description
03/15/24 12:34:05                 23 .git\HEAD
04/21/24 01:19:19             371645 .git\index
03/26/24 12:42:12                241 .git\packed-refs
03/15/24 12:31:51                344 .gitignore
03/15/24 12:31:56                 50 .idea\.gitignore
03/15/24 13:24:08                190 .idea\markdown.xml
03/15/24 12:31:52                284 .idea\misc.xml
03/15/24 12:31:52                255 .idea\modules.xml
03/15/24 12:40:49               8915 .idea\uiDesigner.xml
03/15/24 12:34:13                185 .idea\vcs.xml
04/21/24 02:08:10              28731 .idea\workspace.xml
03/19/24 03:18:21                592 JAVA.iml
03/23/24 23:46:28                603 README.md
12/13/23 03:08:24            2495536 src\mysql-connector-j-8.3.0.jar
03/01/24 19:43:23            7296329 src\sqlite-jdbc-3.34.0.jar
```

I get the same output.
Now, let's have a bit more fun.
First, I'll look more than just two levels deep.

```java  
public static void main(String[] args) {

    Path path = Path.of("");
    System.out.println("cwd = " + path.toAbsolutePath());
         
    try (Stream<Path> paths = Files.list(path)) {
        paths.map(Main::listDir)
                .forEach(System.out::println);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

    System.out.println("---------------------------------------");
    try (Stream<Path> paths = Files.walk(path, 2)) {
        paths.filter(Files::isRegularFile)
                .map(Main::listDir)
                .forEach(System.out::println);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

    System.out.println("---------------------------------------");
    try (Stream<Path> paths = Files.find(path, Integer.MAX_VALUE,
            (p, attr) -> attr.isRegularFile() && attr.size() > 1000000)) {
        paths.map(Main::listDir).forEach(System.out::println);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
```

A common approach, is to put `Integer.MAX_VALUE` as the depth there, 
so it will search all nested levels.
Next, Let's find files that are at least one million bytes in size.
I can do this by checking that variable, attr, for its size greater than 1000000.
Running this code:

```html
---------------------------------------
04/21/24 01:05:57           78737802 .git\objects\pack\pack-db1d196a5481432f3c3583128bb55ad06bf3bf95.pack
12/13/23 03:08:24            2495536 out\production\JAVA\mysql-connector-j-8.3.0.jar
03/01/24 19:43:23            7296329 out\production\JAVA\sqlite-jdbc-3.34.0.jar
03/21/24 15:46:25            1112600 out\production\JAVA\Udemy\JavaProgrammingTimBuchalka\NewVersion\Section_03_OOP1\images\01StaticMethod.png
03/22/24 09:06:14            1673550 out\production\JAVA\Udemy\JavaProgrammingTimBuchalka\NewVersion\Section_03_OOP1\images\05AnimalFamilyTree.png
03/22/24 09:48:51            1836911 out\production\JAVA\Udemy\JavaProgrammingTimBuchalka\NewVersion\Section_03_OOP1\images\08WorkerClassTree.png
06/27/23 22:31:51            1138936 out\production\JAVA\Udemy\JavaProgrammingTimBuchalka\SectionSlides\Section_02_ProgrammingTools\002_Slides-Programming-Tools-Setup-Confirming-installation-and-ntro-to-JShell.pptx
06/28/23 00:04:52            4536738 out\production\JAVA\Udemy\JavaProgrammingTimBuchalka\SectionSlides\Section_03_FirstSteps\003_Slides-First-Steps-Hello-World.pptx
06/28/23 21:32:00            2272216 out\production\JAVA\Udemy\JavaProgrammingTimBuchalka\SectionSlides\Section_03_FirstSteps\013_Slides-First-Steps-Primitive-Types-Recap-and-the-String-Data-Type.pptx
07/03/23 03:01:07            1191893 out\production\JAVA\Udemy\JavaProgrammingTimBuchalka\SectionSlides\Section_06_ControlFlow\051_Slides-Control-Flow-Local-Variables-and-Scope.pptx
07/04/23 04:23:12            1107412 out\production\JAVA\Udemy\JavaProgrammingTimBuchalka\SectionSlides\Section_06_ControlFlow\054_Slides-Control-Flow-Exception-Handling-and-Introduction-to-Scanner.pptx
07/12/23 06:33:20            1590779 out\production\JAVA\Udemy\JavaProgrammingTimBuchalka\SectionSlides\Section_07_OOP1\064_Slides-OOP-Part-1-Inheritance-Reference-vs-Object-vs-Instance-vs-Class.pptx
07/12/23 07:56:07            1158854 out\production\JAVA\Udemy\JavaProgrammingTimBuchalka\SectionSlides\Section_07_OOP1\065_Slides-OOP-Part-1-Inheritance-Static-vs-Instance-Variables.pptx
07/12/23 10:57:47            1092383 out\production\JAVA\Udemy\JavaProgrammingTimBuchalka\SectionSlides\Section_07_OOP1\066_Slides-OOP-Part-1-Inheritance-Static-vs-Instance-Methods.pptx
07/18/23 07:54:52            1474006 out\production\JAVA\Udemy\JavaProgrammingTimBuchalka\SectionSlides\Section_07_OOP1\075_Slides-OOP-Part-1-Inheritance-this-vs-super.pptx
07/27/23 17:26:47            1013564 out\production\JAVA\Udemy\JavaProgrammingTimBuchalka\SectionSlides\Section_09_Arrays\104_Slides-Arrays-Arrays-Recap.pptx
07/28/23 13:12:42            1148108 out\production\JAVA\Udemy\JavaProgrammingTimBuchalka\SectionSlides\Section_10_ArrayList_LinkedList_Iterator_Autoboxing\113_Slides-Arrays-vs-ArrayLists.pptx
12/13/23 03:08:24            2495536 src\mysql-connector-j-8.3.0.jar
03/01/24 19:43:23            7296329 src\sqlite-jdbc-3.34.0.jar
03/21/24 15:46:25            1112600 src\Udemy\JavaProgrammingTimBuchalka\NewVersion\Section_03_OOP1\images\01StaticMethod.png
03/22/24 09:06:14            1673550 src\Udemy\JavaProgrammingTimBuchalka\NewVersion\Section_03_OOP1\images\05AnimalFamilyTree.png
03/22/24 09:48:51            1836911 src\Udemy\JavaProgrammingTimBuchalka\NewVersion\Section_03_OOP1\images\08WorkerClassTree.png
06/27/23 22:31:51            1138936 src\Udemy\JavaProgrammingTimBuchalka\SectionSlides\Section_02_ProgrammingTools\002_Slides-Programming-Tools-Setup-Confirming-installation-and-ntro-to-JShell.pptx
06/28/23 00:04:52            4536738 src\Udemy\JavaProgrammingTimBuchalka\SectionSlides\Section_03_FirstSteps\003_Slides-First-Steps-Hello-World.pptx
06/28/23 21:32:00            2272216 src\Udemy\JavaProgrammingTimBuchalka\SectionSlides\Section_03_FirstSteps\013_Slides-First-Steps-Primitive-Types-Recap-and-the-String-Data-Type.pptx
07/03/23 03:01:07            1191893 src\Udemy\JavaProgrammingTimBuchalka\SectionSlides\Section_06_ControlFlow\051_Slides-Control-Flow-Local-Variables-and-Scope.pptx
07/04/23 04:23:12            1107412 src\Udemy\JavaProgrammingTimBuchalka\SectionSlides\Section_06_ControlFlow\054_Slides-Control-Flow-Exception-Handling-and-Introduction-to-Scanner.pptx
07/12/23 06:33:20            1590779 src\Udemy\JavaProgrammingTimBuchalka\SectionSlides\Section_07_OOP1\064_Slides-OOP-Part-1-Inheritance-Reference-vs-Object-vs-Instance-vs-Class.pptx
07/12/23 07:56:07            1158854 src\Udemy\JavaProgrammingTimBuchalka\SectionSlides\Section_07_OOP1\065_Slides-OOP-Part-1-Inheritance-Static-vs-Instance-Variables.pptx
07/12/23 10:57:47            1092383 src\Udemy\JavaProgrammingTimBuchalka\SectionSlides\Section_07_OOP1\066_Slides-OOP-Part-1-Inheritance-Static-vs-Instance-Methods.pptx
07/18/23 07:54:52            1474006 src\Udemy\JavaProgrammingTimBuchalka\SectionSlides\Section_07_OOP1\075_Slides-OOP-Part-1-Inheritance-this-vs-super.pptx
07/27/23 17:26:47            1013564 src\Udemy\JavaProgrammingTimBuchalka\SectionSlides\Section_09_Arrays\104_Slides-Arrays-Arrays-Recap.pptx
07/28/23 13:12:42            1148108 src\Udemy\JavaProgrammingTimBuchalka\SectionSlides\Section_10_ArrayList_LinkedList_Iterator_Autoboxing\113_Slides-Arrays-vs-ArrayLists.pptx
```

I get both my class and java source **files**, which have met these criteria,
as well as some of intellij's project jar files.
You can imagine using this method to search for any files, modified in the past week, 
or any files that exceed a certain size, and so on.
There's one more method of **Files**, which I'll show you next, 
which can be a bit more efficient.

```java  
public static void main(String[] args) {

    Path path = Path.of("");
    System.out.println("cwd = " + path.toAbsolutePath());
         
    try (Stream<Path> paths = Files.list(path)) {
        paths.map(Main::listDir)
                .forEach(System.out::println);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

    System.out.println("---------------------------------------");
    try (Stream<Path> paths = Files.walk(path, 2)) {
        paths.filter(Files::isRegularFile)
                .map(Main::listDir)
                .forEach(System.out::println);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

    System.out.println("---------------------------------------");
    try (Stream<Path> paths = Files.find(path, Integer.MAX_VALUE,
            (p, attr) -> attr.isRegularFile() && attr.size() > 300
    )) {
        paths.map(Main::listDir).forEach(System.out::println);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

    System.out.println("==============Directory Stream==============");
    try (var dirs = Files.newDirectoryStream(path)) {
        dirs.forEach(d -> System.out.println(Main.listDir(d)));
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
```

First, I'll print out a dividing line with a header that says **DirectoryStream**.
**DirectoryStream** is another Java **NIO2** class.
It provides an iterable for directories.
Like the others, I'll put this in a _try-with-resources_ block, 
and I'm going to use _var_ here,
the variable name _dirs_, and set that 
to the result of another method on **Files**.
This one is called _newDirectoryStream_, 
and takes a **path**. 
Because the result is an iterable type,
I can use _forEach_ on it, 
and I'll print each item, using `Main.listDir` to get
the usual directory listing string.
I also need to catch an _IOException_.
If I run this code:

```html
==============Directory Stream==============
04/21/24 01:19:19 <DIR>              .git
03/15/24 12:31:51                344 .gitignore
04/21/24 02:08:10 <DIR>              .idea
03/19/24 03:18:21                592 JAVA.iml
03/19/24 00:42:22 <DIR>              out
03/23/24 23:46:28                603 README.md
04/21/24 00:25:32 <DIR>              src
```

I get a directory listing of my current working directory.
This method has an overloaded version 
that lets us _filter_ which paths we want back,
first with the use of a string, called a **glob**.
A glob is a limited pattern language that resembles regular expressions
but with a simpler syntax.
You can use to match on a file name.
To show you this call, using a globbing pattern,
I want to first change the directory I'm starting at.
I could use one of the **Path** creation factory methods, 
but there's a method on **path**, 
that will let me navigate, from the path I'm at to another.
This is called _resolve_, and I can pass it the name of a subfolder.

```java  
public static void main(String[] args) {

    Path path = Path.of("");
    System.out.println("cwd = " + path.toAbsolutePath());
         
    try (Stream<Path> paths = Files.list(path)) {
        paths.map(Main::listDir)
                .forEach(System.out::println);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

    System.out.println("---------------------------------------");
    try (Stream<Path> paths = Files.walk(path, 2)) {
        paths.filter(Files::isRegularFile)
                .map(Main::listDir)
                .forEach(System.out::println);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

    System.out.println("---------------------------------------");
    try (Stream<Path> paths = Files.find(path, Integer.MAX_VALUE,
            (p, attr) -> attr.isRegularFile() && attr.size() > 300
    )) {
        paths.map(Main::listDir).forEach(System.out::println);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

    path = path.resolve(".idea");
    System.out.println("==============Directory Stream==============");
    try (var dirs = Files.newDirectoryStream(path, "*.xml")) {
        dirs.forEach(d -> System.out.println(Main.listDir(d)));
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
```

In this case, I'll pass it `.idea`, so intelliJ's project configuration folder.
I'll include the glob `*.xml`, as the second argument to the _newDirectoryStream_ method.
Running this code now:

```html
==============Directory Stream==============
03/15/24 13:24:08                190 .idea\markdown.xml
03/15/24 12:31:52                284 .idea\misc.xml
03/15/24 12:31:52                255 .idea\modules.xml
03/15/24 12:40:49               8915 .idea\uiDesigner.xml
03/15/24 12:34:13                185 .idea\vcs.xml
04/21/24 02:08:10              28731 .idea\workspace.xml
```

I'll only get files that end in `.xml`.
Ok, so that method's pretty easy to use,
and the glob is probably something you might already be a little familiar with.
There's another overloaded version of this method, that takes a type,
that's on the **DirectoryStream** class, and that's a _filter_, a functional interface.
This means it's a target for a lambda.
It takes one argument and returns a **boolean**.

```java  
public static void main(String[] args) {

    Path path = Path.of("");
    System.out.println("cwd = " + path.toAbsolutePath());
         
    try (Stream<Path> paths = Files.list(path)) {
        paths.map(Main::listDir)
                .forEach(System.out::println);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

    System.out.println("---------------------------------------");
    try (Stream<Path> paths = Files.walk(path, 2)) {
        paths.filter(Files::isRegularFile)
                .map(Main::listDir)
                .forEach(System.out::println);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

    System.out.println("---------------------------------------");
    try (Stream<Path> paths = Files.find(path, Integer.MAX_VALUE,
            (p, attr) -> attr.isRegularFile() && attr.size() > 300
    )) {
        paths.map(Main::listDir).forEach(System.out::println);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

    path = path.resolve(".idea");
    System.out.println("==============Directory Stream==============");
    try (var dirs = Files.newDirectoryStream(path, "*.xml")) {
        dirs.forEach(d -> System.out.println(Main.listDir(d)));
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

    System.out.println("==============Directory Stream==============");
    try (var dirs = Files.newDirectoryStream(path, p -> p.getFileName().toString().endsWith(".xml"))) {
        dirs.forEach(d -> System.out.println(Main.listDir(d)));
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
```

I'll copy the code, starting with my header there, and paste a copy just below.
I'll delete the string with `.xml` there, keeping the comma, 
and inserting a couple of new lines.
I'm going to include my lambda on that empty line, which will work, like the glob.
My lambda takes a path, so I'll get the last part of the path, file name,
with a call to get file name, and I have to make that a string, 
because it actually returns a **path**.
Then I'll call _endsWith_ here, passing it `.xml`.
Running this code:

```html
==============Directory Stream==============
03/15/24 13:24:08                190 .idea\markdown.xml
03/15/24 12:31:52                284 .idea\misc.xml
03/15/24 12:31:52                255 .idea\modules.xml
03/15/24 12:40:49               8915 .idea\uiDesigner.xml
03/15/24 12:34:13                185 .idea\vcs.xml
04/21/24 02:08:10              28731 .idea\workspace.xml
==============Directory Stream==============
03/15/24 13:24:08                190 .idea\markdown.xml
03/15/24 12:31:52                284 .idea\misc.xml
03/15/24 12:31:52                255 .idea\modules.xml
03/15/24 12:40:49               8915 .idea\uiDesigner.xml
03/15/24 12:34:13                185 .idea\vcs.xml
04/21/24 02:08:10              28731 .idea\workspace.xml
```

I get the exact same results as before.

```java  
public static void main(String[] args) {

    Path path = Path.of("");
    System.out.println("cwd = " + path.toAbsolutePath());
         
    try (Stream<Path> paths = Files.list(path)) {
        paths.map(Main::listDir)
                .forEach(System.out::println);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

    System.out.println("---------------------------------------");
    try (Stream<Path> paths = Files.walk(path, 2)) {
        paths.filter(Files::isRegularFile)
                .map(Main::listDir)
                .forEach(System.out::println);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

    System.out.println("---------------------------------------");
    try (Stream<Path> paths = Files.find(path, Integer.MAX_VALUE,
            (p, attr) -> attr.isRegularFile() && attr.size() > 300
    )) {
        paths.map(Main::listDir).forEach(System.out::println);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

    path = path.resolve(".idea");
    System.out.println("==============Directory Stream==============");
    try (var dirs = Files.newDirectoryStream(path, "*.xml")) {
        dirs.forEach(d -> System.out.println(Main.listDir(d)));
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

    System.out.println("==============Directory Stream==============");
    try (var dirs = Files.newDirectoryStream(path, 
            p -> p.getFileName().toString().endsWith(".xml") 
                    && Files.isRegularFile(p) && Files.size(p) > 1000
    )) {
        dirs.forEach(d -> System.out.println(Main.listDir(d)));
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
```

But I can make this lambda be anything I want,
so here, I'll add that I only want regular files, 
and let's say I only want ones that are greater than a kilobyte.
I'll enter 1000 which is actually slightly less than a kilobyte,
which is 1024 bytes.
Running this:

```html
==============Directory Stream==============
03/15/24 13:24:08                190 .idea\markdown.xml
03/15/24 12:31:52                284 .idea\misc.xml
03/15/24 12:31:52                255 .idea\modules.xml
03/15/24 12:40:49               8915 .idea\uiDesigner.xml
03/15/24 12:34:13                185 .idea\vcs.xml
04/21/24 02:08:10              28731 .idea\workspace.xml
==============Directory Stream==============
03/15/24 13:24:08                190 .idea\markdown.xml
03/15/24 12:31:52                284 .idea\misc.xml
03/15/24 12:31:52                255 .idea\modules.xml
03/15/24 12:40:49               8915 .idea\uiDesigner.xml
03/15/24 12:34:13                185 .idea\vcs.xml
04/21/24 02:08:10              28731 .idea\workspace.xml
==============Directory Stream==============
03/15/24 12:40:49               8915 .idea\uiDesigner.xml
04/21/24 02:08:10              28731 .idea\workspace.xml
```

I get only two files back now.
This method probably reminds you of the _filter_ method, 
except it doesn't include depth.
Another important thing to note about this method is 
that the elements returned by the _iterator_ are in no specific order.
These methods make it pretty easy to walk the file tree 
or list data in directories.
Maybe, if you've examined the **Files** class API closely,
you'll have noticed there's also a _walkFileTree_ method.
I'm going to give you an example of this method next.
Because it's not so straight forward, 
I think it warrants a little extra attention.

![image06](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/images/image06.png?raw=true)

The **Files** class already had a method called _walkFileTree_ 
that's been around since **jdk.7**.
This method _walkFileTree_, depth first (as does the _walk_ method).
**Depth first** means the code will recursively visit 
all the child elements before visiting any of a folder's siblings.
The image above tries to graphically show you what this means.
If our current working directory has two subfolders, 
out and source, the walk methods won't first examine 
all the first level paths, then examine the second level.
Instead, it examines the first path, and if that's a folder,
than it will walk into that folder, and so on.
This probably feels rather natural to programmers, 
and seems a bit like a stack trace.
The alternative is **breadth first**, 
which means any dependent nodes are walked
after the sibling nodes, but remember,
_walk_ and _walkFileTree_ are depth first.
Because it is depth first,
the `Files.walkFileTree` method, provides a mechanism to accumulate information,
about all the children, up to the parent.
Java provides entry points in the walk to execute operations, 
through a **FileVisitor** interface.
This stubs out methods you can implement, by overriding them, 
at certain events in your walk.
These events are:

* Before visiting a directory. 
* After visiting a directory. 
* When visiting a file. 
* And at the point of A failure to visit a file.

![image07](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/images/image07.png?raw=true)

On this image, I've included a simple class diagram for the **FileVisitor** Interface, 
and its simplest default implementation, **SimpleFileVisitor**.
Here, you can see the methods that correspond to those critical points in the walk.
When you override these methods, you'll have the method arguments 
as data you can work with, at that point in the walk.
You can see from these method signatures that in most cases, 
you'll have access to the current path, either a directory or a file. 
I didn't include the return type, which for all of these methods is the same, 
an enum value, as shown on the **FileVisitResult** enum.
In addition, you have access to basic attributes 
on both the _visitFile_ and _preVisitDirectory_ methods.
This is similar to the _find_ method's **predicate**,
which gave us access, by one of its arguments.
Ok, so with that high-level view, 
let's see what we can build with this method.

```java  
public class Main {

    public static void main(String[] args) {

        Path startingPath = Path.of("./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles");
        FileVisitor<Path> statsVisitor = new StatsVisitor();
        try {
            Files.walkFileTree(startingPath, statsVisitor);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class StatsVisitor extends SimpleFileVisitor<Path> {
        
    }
}
```

I've created a new project for this lecture called **FileWalker**.
Before I do anything in the _main_ method,
I'm going to add a static class as a member.
I'll make it private, call it _StatsVisitor_, extending **SimpleFileVisitor**,
and since it's generic, I need to specify a type, which will be **Path**.
For now, I'll leave it like that.
In the _main_ method, I'll set up a path variable, called _startingPath_,
for my current working directory.
This time, I'll use the dot notation, 
`"./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles"`, 
for the current directory. 
Now, I'll set up a variable for an instance of my **StatsVisitor** class. 
I can make the type the interface type, 
and again, it's generic, so I'll specify its type as **path**.
I'll just create a new instance of this class.
Because I extended **SimpleFileVisitor**, 
I can use its default no args constructor.
Next, I'll call the _walkFileTree_ method on the **Files** class, 
passing it my starting path, and my instance of the **StatsVisitor** class.
As usual, I need to wrap that in a _try-catch_.
I don't have to wrap this method in a _try-with-resources_, 
because this method will get executed at this point, 
and resources will be closed, as part of its execution.
If I run this now, it's not going to look like it's doing anything,
and that's because, the **SimpleFileVisitor**'s methods 
just returns **CONTINUE**, the enum value.
If I ran this on my root drive, 
it would take a pretty long time, but I'd still get no output.
To make it useful, I have to override some methods on this class.
Let's start simple, and override the _visitFile_ method.

```java  
private static class StatsVisitor extends SimpleFileVisitor<Path> {
    
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
            throws IOException {

        return super.visitFile(file, attrs);
    }
}
```

I can do that with control+O, picking _visitFile_ from the dialog there.
You can see this just returns a call to the **super** class's method.
I'm going to control click on _visitFile_ there, in that return statement.

```java  
@Override
public FileVisitResult visitFile(T file, BasicFileAttributes attrs)
        throws IOException {
    
    Objects.requireNonNull(file);
    Objects.requireNonNull(attrs);
    return FileVisitResult.CONTINUE;
}
```

I'll copy those three statements, and paste them,
where I had the return statement in my own method.

```java  
private static class StatsVisitor extends SimpleFileVisitor<Path> {
    
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
            throws IOException {

        //return super.visitFile(file, attrs);
        Objects.requireNonNull(file);
        Objects.requireNonNull(attrs);
        System.out.println(file.getFileName());
        return FileVisitResult.CONTINUE;
    }
}
```

You might remember that `Objects.requireNonNull` simply 
throws a _NullPointerException_, if the argument is **null**.
This also returns the enum constant, **CONTINUE**.
The value that I return from this method will determine 
how the _walk_, is processed after this method.
I can continue, or skip all my siblings, skip a subtree, 
or terminate altogether.
The first thing I'll do is print the filename.
If I run this code:

```html  
Main.java
Main.java
testing.csv
Main.java
Main.java
Main.java
Main.java
Main.java
Main.java
Challenge.java
ChallengeStreams.java
Main.java
file.txt
Main.java
numbers.txt
file.txt
fixedWidth.txt
Main.java
fixedWidth.txt
Main.java
article.txt
bigben.txt
Main.java
Main.java
Course.java
CourseEngagement.java
Student.java
StudentDemographics.java
students.csv
take2.csv
Main.java
Course.java
CourseEngagement.java
Student.java
StudentDemographics.java
students.csv
take2.csv
take3.csv
take4.csv
take4.txt
Challenge.java
Main.java
Course.java
CourseEngagement.java
Student.java
StudentDemographics.java
students.csv
students.json
take2.csv
take3.csv
take4.csv
take4.txt
student-activity.json
Main.java
sloppy.txt
student-activity.json
students-backup.json
Main.java
sloppy.txt
student-activity.json
students-backup.json
USPopulationByState.csv
USPopulationByState.txt
Main.java
BuildStudentData.java
Main.java
MainSeparate.java
student.dat
student.idx
studentData.dat
students.json
employees.dat
Main.java
data.dat
Main.java
tim.dat
data.dat
Main.java
tim.dat
image01.png
image01b.png
image02.png
image02b.png
image02c.png
image02d.png
image03.png
image04.png
image05.png
image05b.png
image06.png
image07.png
image08.png
image09.png
image10.png
image11.png
image12.png
image13.png
image14.png
image15.png
README.md
```

I'll get all the files, in any of the subfolders or in the current directory listed out.
And remember, this is depth first, so they're printed in that order.
Right away, I hope you've noticed that _visitFile_ is really only visiting types 
that are files, though _codeStyles_ here doesn't have an extension, so it's hard to tell.
How would I print the directory names?
I can add code in another spot, either pre-visit or post-visit of the directory.
I'll do this in the pre-visit, so again, I'll come out to the body of this class,
meaning below that _fileVisit_ method, and control+o, 
and pick the option, _preVisitDirectory_.

```java  
private static class StatsVisitor extends SimpleFileVisitor<Path> {
    
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
            throws IOException {

        //return super.visitFile(file, attrs);
        Objects.requireNonNull(file);
        Objects.requireNonNull(attrs);
        System.out.println(file.getFileName());
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
            throws IOException {

        return super.preVisitDirectory(dir, attrs);
    }
}
```

I'll do the same thing I did before, going into the method of the **super** class 

```java  
@Override
public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
        throws IOException {

    Objects.requireNonNull(dir);
    Objects.requireNonNull(attrs);
    return FileVisitResult.CONTINUE;
}
```

And copying its code, pasting it in this method.

```java  
private static class StatsVisitor extends SimpleFileVisitor<Path> {
    
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
            throws IOException {

        //return super.visitFile(file, attrs);
        Objects.requireNonNull(file);
        Objects.requireNonNull(attrs);
        System.out.println(file.getFileName());
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
            throws IOException {

        //return super.preVisitDirectory(dir, attrs);
        Objects.requireNonNull(dir);
        Objects.requireNonNull(attrs);
        System.out.println("--------------------------------------");
        System.out.println(dir.getFileName());
        return FileVisitResult.CONTINUE;
    }
}
```

This code looks almost exactly like _visitFile_.
I'll add a `System.out.println` there as well, 
this time getting _filename_ on `dir`.
Executing that code:

```html  
--------------------------------------
Section_16_InputOutputFiles
--------------------------------------
Course01_ExceptionHandling
--------------------------------------
ChecedUncheckedFinally
Main.java
--------------------------------------
TryWithResources
Main.java
--------------------------------------
Course02_UsingFilesAndPaths
--------------------------------------
Part1
--------------------------------------
files
testing.csv
Main.java
--------------------------------------
Part2
Main.java
--------------------------------------
Course03_MethodsOnPath
Main.java
--------------------------------------
Course04_FilesClass
--------------------------------------
DirectoryListings
Main.java
--------------------------------------
FileTree
Main.java
Main.java
--------------------------------------
Course05_FileTreeWalkingChallenge
Challenge.java
ChallengeStreams.java
Main.java
--------------------------------------
Course06_ReadingTextFromInputFiles
--------------------------------------
Part1_ReadingFiles
file.txt
Main.java
numbers.txt
--------------------------------------
Part2_ScannerProject
file.txt
fixedWidth.txt
Main.java
--------------------------------------
Part3_ReadingWithNIO2
fixedWidth.txt
Main.java
--------------------------------------
Course07_ReadingFileChallenge
article.txt
bigben.txt
Main.java
--------------------------------------
Course08_WritingDataToFiles
--------------------------------------
Part1
Main.java
--------------------------------------
student
Course.java
CourseEngagement.java
Student.java
StudentDemographics.java
students.csv
take2.csv
--------------------------------------
Part2
Main.java
--------------------------------------
student
Course.java
CourseEngagement.java
Student.java
StudentDemographics.java
students.csv
take2.csv
take3.csv
take4.csv
take4.txt
--------------------------------------
Course09_WritingFilesChallenge
Challenge.java
Main.java
--------------------------------------
student
Course.java
CourseEngagement.java
Student.java
StudentDemographics.java
students.csv
students.json
take2.csv
take3.csv
take4.csv
take4.txt
--------------------------------------
Course10_ManagingFiles
--------------------------------------
Part1
--------------------------------------
files
--------------------------------------
data
student-activity.json
Main.java
--------------------------------------
Part2
--------------------------------------
files
--------------------------------------
data
--------------------------------------
newdata
sloppy.txt
student-activity.json
students-backup.json
Main.java
--------------------------------------
resources
--------------------------------------
data
--------------------------------------
newdata
sloppy.txt
student-activity.json
students-backup.json
USPopulationByState.csv
USPopulationByState.txt
--------------------------------------
Course11_FileManipulationChallenge
Main.java
--------------------------------------
public
--------------------------------------
assets
--------------------------------------
icons
--------------------------------------
Course12_RandomAccessFile
BuildStudentData.java
Main.java
MainSeparate.java
student.dat
student.idx
studentData.dat
students.json
--------------------------------------
Course13_RandomAccessFileChallenge
employees.dat
Main.java
--------------------------------------
Course14_DataStreamsAndSerialization
data.dat
Main.java
tim.dat
--------------------------------------
Course15_SerializationAndChange
data.dat
Main.java
tim.dat
--------------------------------------
images
image01.png
image01b.png
image02.png
image02b.png
image02c.png
image02d.png
image03.png
image04.png
image05.png
image05b.png
image06.png
image07.png
image08.png
image09.png
image10.png
image11.png
image12.png
image13.png
image14.png
image15.png
README.md
```

It's a little hard to see where everything belongs without indentation.
One advantage of using a **FileVisitor** implementation, 
is that you can keep track of some state, during the _walk_.
Let's do this with tracking the depth level.
There are other ways to do this, but I'll show you this, 
just so you can get an idea, how you might work with state, during the walk.

```java  
private static class StatsVisitor extends SimpleFileVisitor<Path> {
    
    private int level;
    
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
            throws IOException {

        //return super.visitFile(file, attrs);
        Objects.requireNonNull(file);
        Objects.requireNonNull(attrs);
        System.out.println(file.getFileName());
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
            throws IOException {

        //return super.preVisitDirectory(dir, attrs);
        Objects.requireNonNull(dir);
        Objects.requireNonNull(attrs);
        System.out.println("--------------------------------------");
        level++;
        System.out.println(dir.getFileName());
        return FileVisitResult.CONTINUE;
    }
}
```

I'll set up a private variable in my **StatsVisitor** class, 
an integer, and call that level.
It's implicitly initialized to zero, because it's a class field.
I'll increment this every time I visit a directory, 
and decrement when I leave a directory.
That means in the _preVisitDirectory_ method, 
I can simply increment this field.

```java  
private static class StatsVisitor extends SimpleFileVisitor<Path> {
    
    private int level;
    
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
            throws IOException {

        Objects.requireNonNull(file);
        Objects.requireNonNull(attrs);
        //System.out.println(file.getFileName());
        System.out.println("\t".repeat(level) + file.getFileName());
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
            throws IOException {

        Objects.requireNonNull(dir);
        Objects.requireNonNull(attrs);
        level++;
        //System.out.println(dir.getFileName());
        System.out.println("\t".repeat(level) + dir.getFileName());
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc)
            throws IOException {

        //return super.postVisitDirectory(dir, exc);

        Objects.requireNonNull(dir);
        //if (exc != null)
        //throw exc;

        level--;
        return FileVisitResult.CONTINUE;
    }
}
```

I need to override the _postVisitDirectory_ next, and again, 
I'm going to use the **super** class's code as my starting template.
You might be starting to wonder why I am even extending _SimpleFileVisitor_ at all.
If I decide to implement all four methods, and I never call the parent's methods 
using **super**, that's a pretty good question.
_SimpleFileVisitor_ is a kind of short-cut option 
if you plan only to implement a couple of its methods.
Otherwise, you could just have your class implement **FileVisitor**, 
and not extend _SimpleFileVisitor_ at all.
Ok, so this code is a little different,
because one of the arguments is an exception.
And here, the code is throwing it,
which may be a very valid thing to do.
In my case, I don't want to stop executing, 
if for example, I don't have permissions on a folder, 
or something like that, or whatever the case may be.
I'm just going to comment that if statement out.
Before I return from this method,
I want to decrement level.
Finally, in both places where I print out the **path**'s filename,
`"\t".repeat(level)`, I'll include indentation based on this level.
Running this code:

```html  
Section_16_InputOutputFiles
		Course01_ExceptionHandling
			ChecedUncheckedFinally
			Main.java
			TryWithResources
			Main.java
		Course02_UsingFilesAndPaths
			Part1
				files
				testing.csv
			Main.java
			Part2
			Main.java
		Course03_MethodsOnPath
		Main.java
		Course04_FilesClass
			DirectoryListings
			Main.java
			FileTree
			Main.java
		Main.java
		Course05_FileTreeWalkingChallenge
		Challenge.java
		ChallengeStreams.java
		Main.java
		Course06_ReadingTextFromInputFiles
			Part1_ReadingFiles
			file.txt
			Main.java
			numbers.txt
			Part2_ScannerProject
			file.txt
			fixedWidth.txt
			Main.java
			Part3_ReadingWithNIO2
			fixedWidth.txt
			Main.java
		Course07_ReadingFileChallenge
		article.txt
		bigben.txt
		Main.java
		Course08_WritingDataToFiles
			Part1
			Main.java
				student
				Course.java
				CourseEngagement.java
				Student.java
				StudentDemographics.java
			students.csv
			take2.csv
			Part2
			Main.java
				student
				Course.java
				CourseEngagement.java
				Student.java
				StudentDemographics.java
			students.csv
			take2.csv
			take3.csv
			take4.csv
			take4.txt
		Course09_WritingFilesChallenge
		Challenge.java
		Main.java
			student
			Course.java
			CourseEngagement.java
			Student.java
			StudentDemographics.java
		students.csv
		students.json
		take2.csv
		take3.csv
		take4.csv
		take4.txt
		Course10_ManagingFiles
			Part1
				files
					data
				student-activity.json
			Main.java
			Part2
				files
					data
						newdata
					sloppy.txt
				student-activity.json
				students-backup.json
			Main.java
				resources
					data
						newdata
					sloppy.txt
				student-activity.json
				students-backup.json
		USPopulationByState.csv
		USPopulationByState.txt
		Course11_FileManipulationChallenge
		Main.java
			public
				assets
					icons
		Course12_RandomAccessFile
		BuildStudentData.java
		Main.java
		MainSeparate.java
		student.dat
		student.idx
		studentData.dat
		students.json
		Course13_RandomAccessFileChallenge
		employees.dat
		Main.java
		Course14_DataStreamsAndSerialization
		data.dat
		Main.java
		tim.dat
		Course15_SerializationAndChange
		data.dat
		Main.java
		tim.dat
		images
		image01.png
		image01b.png
		image02.png
		image02b.png
		image02c.png
		image02d.png
		image03.png
		image04.png
		image05.png
		image05b.png
		image06.png
		image07.png
		image08.png
		image09.png
		image10.png
		image11.png
		image12.png
		image13.png
		image14.png
		image15.png
	README.md
```

I'll see some indenting, which helps me see the tree structure.
There are a couple of things I want to point out here.
First, everything is indented, so this indicates that 
the pre-visit directory was called on the initial path,
or the current working directory in this case.
We can see that path printed with `Section_16_InputOutputFiles` there.
Second, our files are printed on the same level as their parent folder.
We're keeping track of the level of the folder, not the files.
The simple way to fix this is just to add one to _level_ in 
that _repeat_ method, for our files, which I'll do.

```java  
@Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
            throws IOException {

        Objects.requireNonNull(file);
        Objects.requireNonNull(attrs);
        //System.out.println("\t".repeat(level) + file.getFileName());
        System.out.println("\t".repeat(level + 1) + file.getFileName());
        return FileVisitResult.CONTINUE;
    }
```

Running this code:

```html  
Section_16_InputOutputFiles
		Course01_ExceptionHandling
			ChecedUncheckedFinally
				Main.java
			TryWithResources
				Main.java
		Course02_UsingFilesAndPaths
			Part1
				files
					testing.csv
				Main.java
			Part2
				Main.java
		Course03_MethodsOnPath
			Main.java
		Course04_FilesClass
			DirectoryListings
				Main.java
			FileTree
				Main.java
			Main.java
		Course05_FileTreeWalkingChallenge
			Challenge.java
			ChallengeStreams.java
			Main.java
		Course06_ReadingTextFromInputFiles
			Part1_ReadingFiles
				file.txt
				Main.java
				numbers.txt
			Part2_ScannerProject
				file.txt
				fixedWidth.txt
				Main.java
			Part3_ReadingWithNIO2
				fixedWidth.txt
				Main.java
		Course07_ReadingFileChallenge
			article.txt
			bigben.txt
			Main.java
		Course08_WritingDataToFiles
			Part1
				Main.java
				student
					Course.java
					CourseEngagement.java
					Student.java
					StudentDemographics.java
				students.csv
				take2.csv
			Part2
				Main.java
				student
					Course.java
					CourseEngagement.java
					Student.java
					StudentDemographics.java
				students.csv
				take2.csv
				take3.csv
				take4.csv
				take4.txt
		Course09_WritingFilesChallenge
			Challenge.java
			Main.java
			student
				Course.java
				CourseEngagement.java
				Student.java
				StudentDemographics.java
			students.csv
			students.json
			take2.csv
			take3.csv
			take4.csv
			take4.txt
		Course10_ManagingFiles
			Part1
				files
					data
					student-activity.json
				Main.java
			Part2
				files
					data
						newdata
						sloppy.txt
					student-activity.json
					students-backup.json
				Main.java
				resources
					data
						newdata
						sloppy.txt
					student-activity.json
					students-backup.json
			USPopulationByState.csv
			USPopulationByState.txt
		Course11_FileManipulationChallenge
			Main.java
			public
				assets
					icons
		Course12_RandomAccessFile
			BuildStudentData.java
			Main.java
			MainSeparate.java
			student.dat
			student.idx
			studentData.dat
			students.json
		Course13_RandomAccessFileChallenge
			employees.dat
			Main.java
		Course14_DataStreamsAndSerialization
			data.dat
			Main.java
			tim.dat
		Course15_SerializationAndChange
			data.dat
			Main.java
			tim.dat
		images
			image01.png
			image01b.png
			image02.png
			image02b.png
			image02c.png
			image02d.png
			image03.png
			image04.png
			image05.png
			image05b.png
			image06.png
			image07.png
			image08.png
			image09.png
			image10.png
			image11.png
			image12.png
			image13.png
			image14.png
			image15.png
		README.md
```

It looks a bit better.
Truthfully, you could have done this with _walk_, 
in a lot less code, so why would you ever use this method?
Well, you probably wouldn't for this type of thing, 
but I wanted to give you a basic example, so you'd understand the mechanism.
Now, let's actually talk about a better use case for this.
Let's say you want to get the total number of bytes of a folder, 
or the sum of its file sizes, and you want each parent's size, 
to include all of its children's sizes.

```java  
private static class StatsVisitor extends SimpleFileVisitor<Path> {
    
    //private int level;
    
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
            throws IOException {

        Objects.requireNonNull(file);
        Objects.requireNonNull(attrs);
        //System.out.println("\t".repeat(level) + file.getFileName());
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
            throws IOException {

        Objects.requireNonNull(dir);
        Objects.requireNonNull(attrs);
        //level++;
        //System.out.println("\t".repeat(level) + dir.getFileName());
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc)
            throws IOException {


        Objects.requireNonNull(dir);
        //level--;
        return FileVisitResult.CONTINUE;
    }
}
```

First, I'm going to clean up my code, removing the level field.
I want to remove any `system.out.println` statements as well, 
so first the one in _visitFile_.
In _preVisitDirectory_, I'll remove the reference to level, 
and remove the `system.out.println` statement.
Finally, in _postVisitDirectory_, I'll remove the statement 
with level in it.

```java  
private static class StatsVisitor extends SimpleFileVisitor<Path> {

    private Path initialPath = null;
    private final Map<Path, Long> folderSizes = new LinkedHashMap<>();
    private int initialCount;
    private int printLevel;
    
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
            throws IOException {

        Objects.requireNonNull(file);
        Objects.requireNonNull(attrs);
        
        folderSizes.merge(file.getParent(), 0L, (o, n) -> o += attrs.size());
        
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
            throws IOException {

        Objects.requireNonNull(dir);
        Objects.requireNonNull(attrs);

        if (initialPath == null) {
            initialPath = dir;
            initialCount = dir.getNameCount();
        } else {
            int relativeLevel = dir.getNameCount() - initialCount;
            if (relativeLevel == 1) {
                folderSizes.clear();
            }
            folderSizes.put(dir, 0L);
        }
        
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc)
            throws IOException {


        Objects.requireNonNull(dir);
        return FileVisitResult.CONTINUE;
    }
}
```

Ok, to start with, I'll set up some new fields.
The first field will just be the _initialPath_,
and I'll set that to **null** here.
Next, I'll set up a **Map**, keyed by **Path**,
with a value of type **Long**, 
which represents the cumulative size of the folder. 
Instead of level, I can use the path's name count 
to figure out the level. 
This count is for the initial path,
and all paths will be relative to it.
The reason I want this to be a **LinkedHashMap**
because I want to maintain the insertion order,
so my directories will get printed in order.
I'll start with the code in _preVisitDirectory_, 
since this is the entry point, for all accumulations.
First, I want to set my _initialPath_,
if its **null**, which it will be on the first visit to this method.
I'll set _initialPath_ to _dir_ only once here, 
the very first directory.
I can figure out what the level is, from the root or relative path this is, 
by using _getNameCount_.
I'll add an else statement next.
I'll calculate the relative indentation level,
using the _getNameCount_ on the current directory, minus my initial count field.
If the relativeLevel is 1, I'll clear my map.
You could maintain a map for the full _walk_,
but if you're doing a large file tree, 
it's more efficient to keep track of one folder at a time.
This also lets me print info to the user
after each subfolder is calculated.
I'll initialize the keyed entry to 0,
the key being the _path_, or the _dir_ here.
This ensures data will go in, in insertion order.
Ok, so this code will only get exercised, 
meaning a map will get cleared and reinitialized 
for the first level of subfolders, in my selected path.
Next, I'll add a file's size to the parent entry, in the _visitFile_ method.
`folderSizes.merge(file.getParent(), 0L, (o, n) -> o += attrs.size());`
I'll use _merge_ on my map.
The key is the parent of this file, because I'm accumulating sizes to the parent.
This method will put a new entry in the map,
with the default value specified, zero in this case if the key doesn't exist.
If the key does exist, then it will execute the function 
I enter here, adding the size of the current file to the parent's running total.
The merge method takes a **BiFunction**.
The first argument is the existing value in the map, so I made that _o_ for old.
The second argument is the new value (which in this case is zero), 
so I want to take the old value, and add my file's size to it.
The truth is I didn't really have to use a _merge_ method here.
I'll always have an entry for a path, with a value of 0, 
because of the _put_, in the _preVisitDirectory_ method.
If you didn't care about the order that your directories were in, 
then you could remove that, and put it in the _preVisitDirectory_ method, 
and this code would still work.
I could have used _computeIfPresent_, for example, 
but I did sort of want to refresh your memory on this, for a later challenge.
This code is tallying up file sizes on the parent folder, 
but these totals aren't propagated up to their parent yet.
To do this, I'll include some code in the _postVisitDirectory_ method.

```java  
private static class StatsVisitor extends SimpleFileVisitor<Path> {

    private Path initialPath = null;
    private final Map<Path, Long> folderSizes = new LinkedHashMap<>();
    private int initialCount;
    
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
            throws IOException {

        Objects.requireNonNull(file);
        Objects.requireNonNull(attrs);
        folderSizes.merge(file.getParent(), 0L, (o, n) -> o += attrs.size());
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
            throws IOException {

        Objects.requireNonNull(dir);
        Objects.requireNonNull(attrs);

        if (initialPath == null) {
            initialPath = dir;
            initialCount = dir.getNameCount();
        } else {
            int relativeLevel = dir.getNameCount() - initialCount;
            if (relativeLevel == 1) {
                folderSizes.clear();
            }
            folderSizes.put(dir, 0L);
        }
        
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc)
            throws IOException {
        
        Objects.requireNonNull(dir);

        if (dir.equals(initialPath)) {
            return FileVisitResult.TERMINATE;
        }

        int relativeLevel = dir.getNameCount() - initialCount;
        if (relativeLevel == 1) {
            folderSizes.forEach((key, value) -> {
                int level = key.getNameCount() - initialCount - 1;
                System.out.printf("%s[%s] - %,d bytes %n",
                        "\t".repeat(level), key.getFileName(), value);
            });

        } else {
            long folderSize = folderSizes.get(dir);
            folderSizes.merge(dir.getParent(), 0L, (o, n) -> o += folderSize);
        }
        
        return FileVisitResult.CONTINUE;
    }
}
```

First, I'll check if the current directory is equal to the _initialPath_.
If that's the case, I'll just send back **terminate**, and be done with the _walk_.
**Terminate** is another value on that **FileVisitResult** enum. 
**Continue** would have worked just as well here, honestly, 
since when this current directory is equal to the _initialPath_, 
the _walk_ is over, but I wanted to demonstrate another option.
Next, I'll set up a local variable again 
for the relative level of the current path or directory, as I did earlier.
Now, if this relative level is 1, I'm back at level 1 subfolder of my initial path.
At this point, I'll print the data I've collected,
so I'll do that with a for each on my map.
Next, I'll get the level of each subfolder, subtracting 1, 
so I don't indent the first level.
I'll print an indent based on the level, then the simple folder name in brackets, 
and then I'll print the cumulative size of the folder, with commas, so I make that `%,d`.
For the arguments, I'll repeat a tab using the level. 
I'll print the file name of the key, the path, 
and the value is the accumulated size of that folder or directory.
If it's not the first level, I need to do a little more work, 
so I need an _else_ statement.
I'll get the folder size for the current directory.
Remember this is post-processing, so this should have the correct size of this folder,
because all the children have been processed.
I'll do the same thing as I did with each file,
merging the data for this directory's parent, adding the folder size here.
Ok, that's it.
Now the fun part, let's run it.

```html  
[Course01_ExceptionHandling] - 5,189 bytes 
	[ChecedUncheckedFinally] - 1,192 bytes 
	[TryWithResources] - 3,997 bytes 
[Course02_UsingFilesAndPaths] - 9,381 bytes 
	[Part1] - 6,381 bytes 
		[files] - 0 bytes 
	[Part2] - 3,000 bytes 
[Course03_MethodsOnPath] - 2,476 bytes 
[Course04_FilesClass] - 11,425 bytes 
	[DirectoryListings] - 5,000 bytes 
	[FileTree] - 3,455 bytes 
[Course05_FileTreeWalkingChallenge] - 10,031 bytes 
[Course06_ReadingTextFromInputFiles] - 25,501 bytes 
	[Part1_ReadingFiles] - 8,896 bytes 
	[Part2_ScannerProject] - 7,121 bytes 
	[Part3_ReadingWithNIO2] - 9,484 bytes 
[Course07_ReadingFileChallenge] - 140,871 bytes 
[Course08_WritingDataToFiles] - 57,298 bytes 
	[Part1] - 21,369 bytes 
		[student] - 9,546 bytes 
	[Part2] - 31,307 bytes 
		[student] - 9,546 bytes 
[Course09_WritingFilesChallenge] - 50,200 bytes 
	[student] - 18,603 bytes 
[Course10_ManagingFiles] - 3,178,099 bytes 
	[Part1] - 640,460 bytes 
		[files] - 627,118 bytes 
			[data] - 258 bytes 
	[Part2] - 2,530,010 bytes 
		[files] - 1,254,628 bytes 
			[data] - 947 bytes 
				[newdata] - 269 bytes 
		[resources] - 1,254,668 bytes 
			[data] - 963 bytes 
				[newdata] - 273 bytes 
[Course11_FileManipulationChallenge] - 13,617 bytes 
	[public] - 1,442 bytes 
		[assets] - 760 bytes 
			[icons] - 276 bytes 
[Course12_RandomAccessFile] - 1,944,940 bytes 
[Course13_RandomAccessFileChallenge] - 11,910 bytes 
[Course14_DataStreamsAndSerialization] - 16,513 bytes 
[Course15_SerializationAndChange] - 31,251 bytes 
[images] - 1,524,935 bytes 
```

If you're on windows, you can use file explorer to verify these values, 
opening the properties on each folder, to check the size.
Let's embellish this just a tiny bit more, because maybe you really only want 
to look at that first level of summaries.
I'll add another private field on my **StatsVisitor** class, 
and call it _printLevel_, an integer.

```java  
private static class StatsVisitor extends SimpleFileVisitor<Path> {

    private Path initialPath = null;
    private final Map<Path, Long> folderSizes = new LinkedHashMap<>();
    private int initialCount;
    
    private int printLevel;
    
    public StatsVisitor(int printLevel) {
        this.printLevel = printLevel;
    }
    
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
            throws IOException {

        Objects.requireNonNull(file);
        Objects.requireNonNull(attrs);
        folderSizes.merge(file.getParent(), 0L, (o, n) -> o += attrs.size());
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
            throws IOException {

        Objects.requireNonNull(dir);
        Objects.requireNonNull(attrs);

        if (initialPath == null) {
            initialPath = dir;
            initialCount = dir.getNameCount();
        } else {
            int relativeLevel = dir.getNameCount() - initialCount;
            if (relativeLevel == 1) {
                folderSizes.clear();
            }
            folderSizes.put(dir, 0L);
        }
        
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc)
            throws IOException {
        
        Objects.requireNonNull(dir);

        if (dir.equals(initialPath)) {
            return FileVisitResult.TERMINATE;
        }

        int relativeLevel = dir.getNameCount() - initialCount;
        if (relativeLevel == 1) {
            folderSizes.forEach((key, value) -> {
                int level = key.getNameCount() - initialCount - 1;
                //System.out.printf("%s[%s] - %,d bytes %n", "\t".repeat(level), key.getFileName(), value);
                if (level < printLevel) {
                    System.out.printf("%s[%s] - %,d bytes %n", "\t".repeat(level), key.getFileName(), value);
                }
            });

        } else {
            long folderSize = folderSizes.get(dir);
            folderSizes.merge(dir.getParent(), 0L, (o, n) -> o += folderSize);
        }
        
        return FileVisitResult.CONTINUE;
    }
}
```

I'll generate a custom constructor,
so alt+insert if you're on windows, pick constructor, 
and select _printLevel_ as the field.
This field will drive the output, 
and since the output is in the _postVisitDirectory_ method, 
I'll go to that method.
I'll wrap my output in an if statement.
I'll check if the level is less than the _printLevel_, 
and only print if that's true.

```java  
public static void main(String[] args) {

    Path startingPath = Path.of("./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles");
    FileVisitor<Path> statsVisitor = new StatsVisitor(1);
    try {
        Files.walkFileTree(startingPath, statsVisitor);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
```

To test this, I'll go back to the _main_ method,
and pass 1 to the constructor of my _StatsVisitor_.
Now, if I run this code:

```html  
[Course01_ExceptionHandling] - 5,189 bytes 
[Course02_UsingFilesAndPaths] - 9,381 bytes 
[Course03_MethodsOnPath] - 2,476 bytes 
[Course04_FilesClass] - 11,689 bytes 
[Course05_FileTreeWalkingChallenge] - 10,031 bytes 
[Course06_ReadingTextFromInputFiles] - 25,501 bytes 
[Course07_ReadingFileChallenge] - 140,871 bytes 
[Course08_WritingDataToFiles] - 57,298 bytes 
[Course09_WritingFilesChallenge] - 50,200 bytes 
[Course10_ManagingFiles] - 3,178,099 bytes 
[Course11_FileManipulationChallenge] - 13,617 bytes 
[Course12_RandomAccessFile] - 1,944,940 bytes 
[Course13_RandomAccessFileChallenge] - 11,910 bytes 
[Course14_DataStreamsAndSerialization] - 16,513 bytes 
[Course15_SerializationAndChange] - 31,251 bytes 
[images] - 1,524,935 bytes 
```

I'll get the directory sizes for the first level of subfolders only.
If I change my path, this time I'm going to use 
`"./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/"`,
which means use the parent path of the current directory.
This will be the folder I have all of my IntelliJ Projects in.
Running this:

```html  
[Section_01_ExpressionsStatementsMore] - 2,025,110 bytes
[Section_02_ControlFlow] - 62,523 bytes
[Section_03_OOP1] - 8,296,747 bytes
[Section_04_OOP2] - 1,104,423 bytes
[Section_05_Arrays] - 69,925 bytes
[Section_06_ArrayList_LinkedList_Iterators_Autoboxing] - 609,954 bytes
[Section_07_Abstraction_Interface] - 459,992 bytes
[Section_08_Generics] - 510,221 bytes
[Section_09_NestedClassesAndTypes] - 129,617 bytes
[Section_10_LambdaExpressionsAndFunctionalInterfaces] - 198,795 bytes
[Section_11_Collections] - 3,165,931 bytes
[Section_12_Immutable_Unmodifable_Classes] - 2,724,102 bytes
[Section_13_Streams] - 1,638,738 bytes
[Section_16_InputOutputFiles] - 7,237,214 bytes
```

You might get a lot of folders if you've been following along with this course, 
and creating a project each time I do.
Ok, so I hope this code took the mystery out of this method.
I could have passed in an anonymous class to the _walkFileTree_ method, 
and you'll often see that are done when you see examples of this method.
If you're only overriding one or two methods of **SimpleFileVisitor**, 
then that's fine, but I think this way, creating a static class,
is a little easier for others to read and understand what you're doing.
</div>

## [c. File Tree Walking Challenge](https://github.com/korhanertancakmak/JAVA/tree/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course05_FileTreeWalkingChallenge#file-tree-walking-challenge)
<div align="justify">

In the last couple of sections, I've shown you a lot of ways
to navigate the file tree with the many different methods on **Files**.
These include:

* `Files.list`
* `Files.walk`
* `Files.find`

These three methods return a **Stream** of **Paths**.

* `Files.newDirectoryStream`

This method returns a **DirectoryStream** instance,
an iterable class, that's only one level deep,
but is more efficient than creating a stream.

* Files.walkFileTree

Which you've seen can be more complex to use,
but provides hooks into the process which can be leveraged.
In the last section, I used the sum of the file sizes
to determine the size of a directory,
and accumulated those sizes up to the parent directories.

In this challenge, I want you to do something very similar.
In addition to summing up directory sizes,
I want you to summarize the number of **Files** in a directory.
For a bonus, include the summary of the number of subfolders.
These numbers should include nested files or folders.
If you're on windows, you might be familiar with the _properties_ feature,
in the **File Explorer**.

![image08](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/images/image08.png?raw=true)

I'm showing a screenshot on this image.
You can see the **Size**, which is the cumulative size of all the files.
You also can see that this folder contains 17 _files_ and 9 _folders_ in total.
This folder count doesn't include the current folder, but does include nested folders.

You're welcome to use the _walkFileTree_ code from the last section,
as your starting point.
That will be my own approach.
Here's a hint, instead of the map value being a **Long**,
I'll be making that a nested **Map**.
But if you're feeling more adventurous, and want to see
if you can figure out a way to use one of the streaming methods, go ahead.
This would be a great learning exercise for you.
Or, you may want to try using _newDirectoryStream_ in a recursive way.
You should test your code first on the current working directory,
and then on some larger directory, to see how well it performs
with a much greater set of data.
That's the challenge, and I want you to try
to have some fun with this.
</div>

## [d. Reading Input Files]()

### Reader Class
<div align="justify">

Now that you've got files and paths under your belt,
it's time to start looking at reading data from files.
I'll be jumping right into a bit of code,
using it to discuss some important concepts about reading data from a file.
In a previous section, I showed you a basic way to read lines from a file,
with `Files.readAllLines`.
I'm definitely going to recommend you go that route, 
for most of your file reading needs.
But To understand how that magical little method works, 
I want to roll back the clock a little bit 
to the methods offered in Java 1.0.
These methods mirrored a bit, how data is really read from a file.
A black box method called _readAllLines_.

Before I start working on the code in the _main_ method,
I'm going to create a new file in my projects folder, 
calling that `file.txt`.
When that opens up in the editor pane, 
I'm just going to type in some numbers here.

```html  
12345678910
```

Now, I'll set this code up, reviewing it afterward, 
to talk about some concepts and terms.
This will eventually lend itself to the discussion 
of why Java has such a wide variety of options 
for reading and writing data to files.
I'll start with a _try-with-resources_ statement,
and in the parentheses,
I'm declaring a variable of type **FileReader**, called **reader**. 

```java  
public class Main {
    public static void main(String[] args) {

        String fileName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course06_ReadingTextFromInputFiles/Part1_ReadingFiles/numbers.txt";
        try (FileReader reader = new FileReader(fileName)) {
            int data;
            while ((data = reader.read()) != -1) {
                System.out.println(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

I'll assign that a new **FileReader**, passing the constructor a string, 
which should specify the name of the text file, and where it can be found. 
In this case, the file is in the project package directory.
The file reader is used to read text files, 
and it reads data by default, one integer at a time.
An integer, you'll remember is four bytes in Java.
To read all the data from the file, 
I'll set up a while loop, populating the data variable,
by assigning it the result of `reader.read`.
This will run as long as data isn't `-1`. 
`-1` means it's the end of the file. 
I'll print the data I get for each read. 
And probably no surprise, I need to do something with the _IOException_, 
so I'll catch it, and just print the stackTrace.
Before I even get into how this works, I'll just run it:

```html  
49
50
51
52
53
54
55
56
57
49
48
```

There you can see, I get integers printed out between the values of 48 and 57.
Maybe that's not what you expected to see.
Remember that a character is represented in Java, by an unsigned integer.

```java  
public class Main {
    public static void main(String[] args) {

        String fileName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course06_ReadingTextFromInputFiles/Part1_ReadingFiles/numbers.txt";
        try (FileReader reader = new FileReader(fileName)) {
            int data;
            while ((data = reader.read()) != -1) {
                System.out.println((char) data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

If I want to see the character value, 
I can use the character wrapper to parse the int, 
or I can just cast data, to a char, which I'll do here.
Running that:

```html  
1
2
3
4
5
6
7
8
9
1
0
```

My data is encoded, using a default character set, 
and I now get each character printed, as I typed it, in the file.
Not only is this a pretty tedious way to read data from a file, 
but it could get expensive if each calls this _read_ method were a **disk read**.
A **disk read** means something is physically or mechanically, 
occurring on your hard disk to read that character from the file.
This is **expensive**, and Java provides ways to reduce the number of disk
reads being done.
It would be a lot more efficient to read many characters at a time.
This would reduce the number of disk reads, 
and make processing the information we get back a lot easier.
In truth, the **FileReader** actually already does some of this.
It has a default buffer size, 
meaning it reads a certain number of characters into a memory space, 
called a **buffer**.
A **file buffer** is just computer memory temporarily used to hold data,
while it's being read from a file.
Its primary purpose is to improve the efficiency of data transfer and processing.
It reduces the number of direct interactions, or disk reads, 
against the actual storage device.
I can't override this buffer size with the **FileReader** class, 
but later, I'll be talking about another class 
that I can set the buffer size to something larger than the default.
In this case, though we might be executing 11 characters read, 
only one of them, the first was an actual disk read.
The later characters were a read from memory, or a buffered read.
Java's API says the size of the buffer in the case of the **FileReader** 
is implementation-specific.
This means it is based on the underlying operating system and other factors.
You can read more than one character at a time,
and avoid the cast by passing a character array to the read method.

```java  
public class Main {
    public static void main(String[] args) {

        String fileName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course06_ReadingTextFromInputFiles/Part1_ReadingFiles/numbers.txt";
        try (FileReader reader = new FileReader(fileName)) {
            char[] block = new char[1000];
            int data;
            while ((data = reader.read(block)) != -1) {

                //System.out.println((char) data);
                String content = new String(block, 0, data);
                System.out.printf("---> [%d chars] %s%n", data, content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
}
```

In this case, I'll create a character array to hold one thousand characters.
I can pass that variable to the read method.
I'll create a **String** by passing it the _block_, 
and the starting character and ending index.
Remember, even though we specified 2000 characters, 
less might be read in if we reach the end of the file.
I'll replace this _println_ statement with a _printf_ statement.
If I run this code with the current data in `numbers.txt`, 
I'll get all my numbers printed at once.

```html  
---> [11 chars] 12345678910
```

Now, I'll add another txt file which is `file.txt`.
I'm going to paste some text from the _United States Declaration of Independence_ here.

```java  
public class Main {
    public static void main(String[] args) {

        String fileName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course06_ReadingTextFromInputFiles/Part1_ReadingFiles/numbers.txt";
        fileName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course06_ReadingTextFromInputFiles/Part1_ReadingFiles/file.txt";

        try (FileReader reader = new FileReader(fileName)) {
            char[] block = new char[1000];
            int data;
            while ((data = reader.read(block)) != -1) {

                //System.out.println((char) data);
                String content = new String(block, 0, data);
                System.out.printf("---> [%d chars] %s%n", data, content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
}
```

You can find this file in the resources' folder.
Running my code now:

```html  
---> [1000 chars] We hold these truths to be self-evident, that all men are created equal,
that they are endowed by their Creator with certain unalienable Rights,
that among these are Life, Liberty and the pursuit of Happiness.
–That to secure these rights, Governments are instituted among Men,
deriving their just powers from the consent of the governed,
–That whenever any Form of Government becomes destructive of these ends,
it is the Right of the People to alter or to abolish it, and to institute new Government,
laying its foundation on such principles and organizing its powers in such form,
as to them shall seem most likely to effect their Safety and Happiness.
Prudence, indeed, will dictate that Governments long established should not be changed
for light and transient causes; and accordingly all experience hath shewn,
that mankind are more disposed to suffer, while evils are sufferable,
than to right themselves by abolishing the forms to which they are accustomed.
But when a long train
---> [648 chars]  of abuses and usurpations,
pursuing invariably the same Object evinces a design to reduce them under absolute Despotism,
it is their right, it is their duty, to throw off such Government,
and to provide new Guards for their future security.
–Such has been the patient sufferance of these Colonies;
and such is now the necessity which constrains them to alter their former Systems of Government.
The history of the present King of Great Britain is a history of repeated injuries and usurpations,
all having in direct object the establishment of an absolute Tyranny over these States.
To prove this, let Facts be submitted to a candid world.
```

You'll see that this text was retrieved with only two reads,
the first returned 1000 characters,
and the second was the remaining 648 characters.
Let's talk about how a **FileReader** actually works.
Before I can do that, I have to introduce you to the **InputStream**.

![image09](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/images/image09.png?raw=true)

An **InputStream** is an **abstract** class, representing an input stream of bytes.
It represents **a source of data**, 
and a **common interface** for reading that data.
**InputStreams** can return a byte stream or a character stream.
One input stream you're already familiar with is `System.in`.
For files, the implementation we want to focus on is the **FileInputStream**.
This class is used for files containing binary data,
so we'll be getting back to it later.
Using the read method on a **FileInputStream** is very inefficient, 
because each read is a hard disk read, 
so if you're going to use a **FileInputStream**,
you'll want to wrap it in a **BufferedInputStream**.
Notice that almost all the _read_ methods return byte arrays,
or accept a byte array as a parameter.
This is your hint that, 
if you're reading from a text-based file, there are other options.
This image shows you the hierarchy of these streams, 
and the methods they inherit from **InputStream**.

We've talked a lot about streams before this,
but an input stream is not that kind of stream.
It's a similar concept, in that we get a stream of data 
in some kind of sequential way.
However, an InputStream can't be used in a Stream pipeline 
without first transforming it.

![image10](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/images/image10.png?raw=true)

Readers read characters, as you can see from the methods 
on the abstract parent shown on this image.
An **InputStreamReader** is a bridge from byte streams to character streams.
If you want to read a character stream, it's recommended 
you use a **FileReader**, an **InputStreamReader**.
The **FileReader** will do the work of opening a **FileInputStream** for you.
As I've mentioned already, a **FileReader** is doing buffered reading, 
so it's doing a hard disk read, for a certain number of characters,
and storing those characters in memory.
A **BufferedReader** will also do buffered reading, 
using a much larger buffer size than the **FileReader**.
You can modify the size of the buffer on **BufferedReader** 
by passing it to the constructor.
But Java states that the default buffer size of 
the **BufferedReader** is large enough for most purposes.
The **BufferedReader** also provides convenience methods for reading lines of text.
So that's probably pretty confusing if that's the first time 
you've been exposed to these Java IO classes.
In truth, Java's NIO2 provides functionality 
that reduces the need to use these classes, under many circumstances.
I wanted to show you these classes, however, 
so that you'd get more familiar with some terms, 
such as binary data vs. character data, input streams and readers, 
as well as buffers, and disk reads.
Getting back to the code, I'll give you an example of the **BufferedReader**,
and then I want to move on to simpler ways, to read data from a file.
To use a buffered reader, you usually wrap a **FileReader** with it.
What I mean by that is, you'll pass a **FileReader**
to the constructor of the **BufferedReader**.
Let me set this up.

```java  
public class Main {
    public static void main(String[] args) {

        String fileName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course06_ReadingTextFromInputFiles/Part1_ReadingFiles/numbers.txt";
        try (FileReader reader = new FileReader(fileName)) {
            int data;
            char[] block = new char[1000];
            while ((data = reader.read(block)) != -1) {

                //System.out.println(data);
                //System.out.println((char) data);

                String content = new String(block, 0, data);
                System.out.printf("---> [%d chars] %s%n", data, content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("-----------------------------------");
        fileName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course06_ReadingTextFromInputFiles/Part1_ReadingFiles/file.txt";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
}
```

First I'll print a separator line, and then I'll set up a _try-with-resources_ statement.
My variable is called _bufferedReader_, 
and I can pass that a new instance of **FileReader**,
which I'll create by passing that the literal string, `file.txt`.
I'll leave a little room for the code.
I'll add the catch _IOException_ here, 
and print the _stackTrace_ if I get an error.
With a file reader, I read the data either by integers, or by character arrays.
What's nice about the buffered reader, 
besides it being more efficient, 
is that it gives us methods to read the data by lines.
I'll set up a line variable, a string.
I'll use a while loop like I did before, 
but this time I'll read the line of data in, 
and quit the while loop if null comes back from that.
I'll print the line of data I get. 
And that's it.
Other than the somewhat ugly instantiation of a **BufferedReader**, 
that's pretty easy code.
If I run it:

```html  
-----------------------------------
We hold these truths to be self-evident, that all men are created equal,
that they are endowed by their Creator with certain unalienable Rights,
that among these are Life, Liberty and the pursuit of Happiness.
–That to secure these rights, Governments are instituted among Men,
deriving their just powers from the consent of the governed,
–That whenever any Form of Government becomes destructive of these ends,
it is the Right of the People to alter or to abolish it, and to institute new Government,
laying its foundation on such principles and organizing its powers in such form,
as to them shall seem most likely to effect their Safety and Happiness.
Prudence, indeed, will dictate that Governments long established should not be changed
for light and transient causes; and accordingly all experience hath shewn,
that mankind are more disposed to suffer, while evils are sufferable,
than to right themselves by abolishing the forms to which they are accustomed.
But when a long train of abuses and usurpations,
pursuing invariably the same Object evinces a design to reduce them under absolute Despotism,
it is their right, it is their duty, to throw off such Government,
and to provide new Guards for their future security.
–Such has been the patient sufferance of these Colonies;
and such is now the necessity which constrains them to alter their former Systems of Government.
The history of the present King of Great Britain is a history of repeated injuries and usurpations,
all having in direct object the establishment of an absolute Tyranny over these States.
To prove this, let Facts be submitted to a candid world.
```

I'll get each line printed.
It actually gets easier than that because as of JDK 8,
another method was added, which gives us a source of lines for a stream pipeline.

```java  
public class Main {
    public static void main(String[] args) {

        String fileName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course06_ReadingTextFromInputFiles/Part1_ReadingFiles/numbers.txt";
        try (FileReader reader = new FileReader(fileName)) {
            int data;
            char[] block = new char[1000];
            while ((data = reader.read(block)) != -1) {

                //System.out.println(data);
                //System.out.println((char) data);

                String content = new String(block, 0, data);
                System.out.printf("---> [%d chars] %s%n", data, content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("-----------------------------------");
        fileName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course06_ReadingTextFromInputFiles/Part1_ReadingFiles/file.txt";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
/*
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
*/

            bufferedReader.lines().forEach(System.out::println);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
```

I'll remove that local variable as well as the while loop.
I can replace that with a single statement.
I'll call the lines method on the _bufferedReader_ instance, 
and immediately call the terminal operation, for each, and print each line.
Running that:

```html  
-----------------------------------
We hold these truths to be self-evident, that all men are created equal,
that they are endowed by their Creator with certain unalienable Rights,
that among these are Life, Liberty and the pursuit of Happiness.
–That to secure these rights, Governments are instituted among Men,
deriving their just powers from the consent of the governed,
–That whenever any Form of Government becomes destructive of these ends,
it is the Right of the People to alter or to abolish it, and to institute new Government,
laying its foundation on such principles and organizing its powers in such form,
as to them shall seem most likely to effect their Safety and Happiness.
Prudence, indeed, will dictate that Governments long established should not be changed
for light and transient causes; and accordingly all experience hath shewn,
that mankind are more disposed to suffer, while evils are sufferable,
than to right themselves by abolishing the forms to which they are accustomed.
But when a long train of abuses and usurpations,
pursuing invariably the same Object evinces a design to reduce them under absolute Despotism,
it is their right, it is their duty, to throw off such Government,
and to provide new Guards for their future security.
–Such has been the patient sufferance of these Colonies;
and such is now the necessity which constrains them to alter their former Systems of Government.
The history of the present King of Great Britain is a history of repeated injuries and usurpations,
all having in direct object the establishment of an absolute Tyranny over these States.
To prove this, let Facts be submitted to a candid world.
```

I get the same results as before.
What's nice though, is that you now have all the stream operations at your disposal, 
to _query_, _filter_, _transform_, and _slice_ and _dice_ the file data, line by line.
I can also use the familiar **Scanner** class to read data from a file, 
which provides similar options to the **BufferedReader**,
as well as even more granular operations.
Though we've used **Scanner** before, 
there's still more to cover, 
so I'll devote the next talk to this class, 
and make sure by the end of it, you'll understand it 
as another option for reading data from text files, 
and what it offers, that other methods may not.
</div>

### Scanner Class
<div align="justify">

I'll paste the same file from the previous section,
which had some oft quoted text in it here.
In the main method, I'll create a new variable, 
type **Scanner**, named _scanner_, followed by equals **new**.
Pausing there, what I want you to see is the number of options here 
for constructing a **Scanner**.

```java  
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(File source);
        Scanner scanner = new Scanner(Path source);
        Scanner scanner = new Scanner(String source);
        Scanner scanner = new Scanner(Readable source);
        Scanner scanner = new Scanner(InputStream source);
        Scanner scanner = new Scanner(ReadableByteChannel source);
        Scanner scanner = new Scanner(File source, Charset charset);
        Scanner scanner = new Scanner(Path source, Charset charset);
        Scanner scanner = new Scanner(File source, String charsetName);
        Scanner scanner = new Scanner(Path source, String charsetNName);
    }
}
```

You can pass many different types of sources to this constructor.
There's a **file**, **path**, a **string** which I've covered previously, 
a **Readable**, an **InputStream**, and something called **ReadableByteChannel**.
There are overloaded versions that let you pass a character set, 
or a character set name.
We haven't really talked about character sets a lot, 
and I'll cover them a little bit in the next section.

```java  
public class Main {

    public static void main(String[] args) {

        String pathName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course06_ReadingTextFromInputFiles/Part2_ScannerProject/file.txt";
        try (Scanner scanner = new Scanner(new File(pathName))) {
           while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
```

Let me finish setting up this first **Scanner** variable, 
using a new **File** instance, 
and passing that the name of my file as a string literal.
Not unexpectedly, I get a compiler error 
because of the possibility of a checked exception.
Before I deal with that, 
I'll first surround this declaration in a _try-with-resources_ block.
A scanner isn't automatically closed, 
so if I didn't put that in a _try-with-resources_ block,
this code would keep the file open.
I didn't have to worry about closing a scanner
when the source was a **String**, 
because a **String** doesn't open and hold onto an open resource.
We've also used **Scanner** with `System.in`.
As it turns out, `System.in` is 
a special **InputStream** called the standard input stream.
It's a special case because the JVM opens one instance of it, 
for console or keyboard input, and you don't really want to close it.
You've had lots of practice with this type of input stream.
Working with other kinds of input streams, won't be too different.
Next, I'll add the catch clause so this code will compile.
What's nice about using Scanner is 
that once you get familiar with all its functionality,
you can process data from different inputs, in a standardized way, 
which the Scanner lets you do.
Next, I'll use a while loop, checking the _hasNextLine_ method result,
to determine if there's more data to process I can use nextLine 
to get the next line in the file.
Running this:

```html  
We hold these truths to be self-evident, that all men are created equal,
that they are endowed by their Creator with certain unalienable Rights,
that among these are Life, Liberty and the pursuit of Happiness.
–That to secure these rights, Governments are instituted among Men,
deriving their just powers from the consent of the governed,
–That whenever any Form of Government becomes destructive of these ends,
it is the Right of the People to alter or to abolish it, and to institute new Government,
laying its foundation on such principles and organizing its powers in such form,
as to them shall seem most likely to effect their Safety and Happiness.
Prudence, indeed, will dictate that Governments long established should not be changed
for light and transient causes; and accordingly all experience hath shewn,
that mankind are more disposed to suffer, while evils are sufferable,
than to right themselves by abolishing the forms to which they are accustomed.
But when a long train of abuses and usurpations,
pursuing invariably the same Object evinces a design to reduce them under absolute Despotism,
it is their right, it is their duty, to throw off such Government,
and to provide new Guards for their future security.
–Such has been the patient sufferance of these Colonies;
and such is now the necessity which constrains them to alter their former Systems of Government.
The history of the present King of Great Britain is a history of repeated injuries and usurpations,
all having in direct object the establishment of an absolute Tyranny over these States.
To prove this, let Facts be submitted to a candid world.
```

You can see the text file printed out line for line.
This isn't quite as easy as using `BufferedReaders.lines` method, 
but it's still not very complicated code.
You might remember, in a previous section, 
we used the tokens method to get a stream of **Strings**.
Let me comment out this while loop.

```java  
public class Main {

    public static void main(String[] args) {

        String fileName = "./src/CourseCodes/NewSections/Section_18_InputOutputFiles/Course06_ReadingTextFromInputFiles/Part2_ScannerProject/fixedWidth.txt";

        try (Scanner scanner = new Scanner(new File(pathName))) {
            
/*
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
*/
            System.out.println(scanner.delimiter());
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
```

I'll use tokens next to read the file, but first, 
let's review what delimiter the tokens method will use.
I can do this by printing out the result of invoking 
the delimiter method on _scanner_.
Running this code:

```html  
\p{javaWhitespace}+
```

You'll see a regular expression.
Hopefully you know what this means.
The text will be split by one or more white space, any white space,
which includes new line characters.
What this means is, if I use tokens with the default delimiter, 
I'll just get a list of words.
That's not what I want, so I'll set the scanner's delimiter.

```java  
public class Main {

    public static void main(String[] args) {

        String fileName = "./src/CourseCodes/NewSections/Section_18_InputOutputFiles/Course06_ReadingTextFromInputFiles/Part2_ScannerProject/fixedWidth.txt";

        try (Scanner scanner = new Scanner(new File(pathName))) {

            System.out.println(scanner.delimiter());
            scanner.useDelimiter("$");
            scanner.tokens().forEach(System.out::println);
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
```

I'll use a regular expression, and just put a dollar sign there,
which is a meta character, for end of line.
Now I'll call tokens, which returns a stream of **String**, 
and print each string.
Running this code:

```html  
\p{javaWhitespace}+
We hold these truths to be self-evident, that all men are created equal,
that they are endowed by their Creator with certain unalienable Rights,
that among these are Life, Liberty and the pursuit of Happiness.
–That to secure these rights, Governments are instituted among Men,
deriving their just powers from the consent of the governed,
–That whenever any Form of Government becomes destructive of these ends,
it is the Right of the People to alter or to abolish it, and to institute new Government,
laying its foundation on such principles and organizing its powers in such form,
as to them shall seem most likely to effect their Safety and Happiness.
Prudence, indeed, will dictate that Governments long established should not be changed
for light and transient causes; and accordingly all experience hath shewn,
that mankind are more disposed to suffer, while evils are sufferable,
than to right themselves by abolishing the forms to which they are accustomed.
But when a long train of abuses and usurpations,
pursuing invariably the same Object evinces a design to reduce them under absolute Despotism,
it is their right, it is their duty, to throw off such Government,
and to provide new Guards for their future security.
–Such has been the patient sufferance of these Colonies;
and such is now the necessity which constrains them to alter their former Systems of Government.
The history of the present King of Great Britain is a history of repeated injuries and usurpations,
all having in direct object the establishment of an absolute Tyranny over these States.
To prove this, let Facts be submitted to a candid world.
```

I get the same result as the while loop example.
Each line of text in the file is returned on the stream, 
and I can work with lines of text, rather than words.
Another method that I don't think I covered, in the regular expressions section,
is the _findAll_ method.
I'm going to comment on the three statements in this _try_ block.
Next, I'll call `scanner.findAll`, and I'm going to pass that a string,
but one that contains a regular expression.

```java  
import java.util.regex.MatchResult;
public class Main {

    public static void main(String[] args) {

        String fileName = "./src/CourseCodes/NewSections/Section_18_InputOutputFiles/Course06_ReadingTextFromInputFiles/Part2_ScannerProject/fixedWidth.txt";

        try (Scanner scanner = new Scanner(new File(pathName))) {

/*
            System.out.println(scanner.delimiter());
            scanner.useDelimiter("$");
            scanner.tokens().forEach(System.out::println);
*/

            scanner.findAll("[A-Za-z]{10,}")                // Stream<MatchResult>
                    .map(MatchResult::group)                // Stream<Object>
                    .distinct()
                    .sorted()
                    .forEach(System.out::println);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
```

I'll type this in, and I want you to study it a minute.
A mini challenge for you is, 
can you figure out what this regular expression will match on?
Before I give you the answer, 
you should know this returns a **Stream** of **Match Result** items.
This is very similar to the _findLine_ method I reviewed 
in the regular expressions section.
Because it returns a **MatchResult**, 
I'm really interested in what came back as the whole match.
Maybe you'll recall that the **MatchResult** type has group methods on it, 
and the group method with no arguments, 
returns all the characters that matched the regular expression.
I'll map the **MatchResult** to a **string**, 
using that group method, here I'll use a method reference,
so `MatchResult::group`.
Next I'll include the _distinct_ intermediate operation.
Followed by the _sorted_ operation.
And I'll just print out each stream element.
**MatchResult** has not been imported automatically for some strange reason.
I'll click it to see if I can get an IntelliJ popup.
No luck with that, so I'll just enter the import manually.
This does happen occasionally with IntelliJ, even with auto imports on.
Ok, so were you able to guess what the result would be?
I'll run this:

```html  
Government
Governments
abolishing
accordingly
accustomed
constrains
destructive
established
establishment
experience
foundation
instituted
invariably
organizing
principles
sufferable
sufferance
themselves
unalienable
usurpations
```

What I get back is, a list of words that are 10 characters or more,
so the big words in other words, that are in the full body of the text.
I don't have any duplicates, and they're ordered naturally, 
in alphabetical order in other words.
You can see that this method can help you very quickly scan 
the entire text in the file for matching elements.
I'll show you a slightly different take on this.
In the project folder, you'll find a file called `fixedWidth.txt`.

```html  
Name           AgeDept        Salary  ST
John Doe        30HR             50000NY
Jane Smith      25IT             60000CA
Michael Brown   40Finance        75000TX
Alice Johnson   35Marketing      55000IL
Robert Lee      28IT             58000WA
Emily Wang      32Marketing      52000NY
Daniel Kim      29HR             49000CA
Sarah Davis     31Finance        72000TX
Jessica Chen    27IT             61000IL
David Miller    33Marketing      53000WA
Oliver Scott    26Finance        65000CA
Sophia Adams    37IT             59000NY
William Clark   34Marketing      54000TX
Ava Turner      29HR             51000IL
Ethan Hall      31Finance        70000WA
Isabella King   24IT             62000CA
James Evans     36Marketing      56000NY
Grace Baker     27HR             48000TX
Liam Brooks     32Finance        68000IL
Charlotte Hill  28IT             57000WA
```

If you open that up, you'll see it has a list of employees, 
with a name, an age, a department, a salary and the US state they live in.
This is a fixed width file, which is a file containing data with no delimiters.
These files are usually accompanied by a specification, 
telling you the field names and the start and end index of that field in the line of data.
In this case, the first line of the file is telling me the field names.
The name is 15 characters, age is 3 characters, the department is 12, salary is 8, 
and the state is 2 characters.
Getting back to my code, I'll first change the name of my file to `fixedWidth.txt`.

```java  
public class Main {

    public static void main(String[] args) {

        //String fileName = "./src/CourseCodes/NewSections/Section_18_InputOutputFiles/Course06_ReadingTextFromInputFiles/Part2_ScannerProject/fixedWidth.txt";
        String pathName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course06_ReadingTextFromInputFiles/Part2_ScannerProject/fixedWidth.txt";

        try (Scanner scanner = new Scanner(new File(pathName))) {
/*
            scanner.findAll("[A-Za-z]{10,}")                // Stream<MatchResult>
                    .map(MatchResult::group)                // Stream<Object>
                    .distinct()
                    .sorted()
                    .forEach(System.out::println);
*/
            
            var results = scanner.findAll(
                    "(.{15})(.{3})(.{12})(.{8})(.{2}).*")   // Stream<MatchResult>
                    .map(m -> m.group(5))                   // Stream<String>
                    .distinct()
                    .sorted()
                    .toArray(String[]::new);                // String[]
            System.out.println(Arrays.toString(results));
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
```

I'm also going to again comment out the statements in this _try_ block, 
in case you want to revisit this code later.
I'll create a local variable, named _results_, 
and assign that the result of using `scanner.findAll`.
In this case, my regular expression is going to group each column.
I'll use parentheses to specify each group, and in the parentheses I'll have a dot,
meaning any character, then specify the exact width in curly braces, 
so it will match the fixed width length of characters.
The first will be 15 in curly braces.
Next I'll make it 3 characters to match, then 12, then 8 for the fourth column, 
and 2 for the last column, which will be state.
I'll end with `.*`, so that the line won't fail if there are extra characters.
I'll use a lambda expression in the map operation, 
and I'll first get the States from the last column, which is group 5.
I'll again use _distinct_ and _sorted_. 
I'll collect these distinct values into a **String** array, 
with the _toArray_ terminal operation,
and specifying the kind of array I want. 
Finally, I'll print the results out in one statement.
I'll just fix that typo I made and format the code, 
so it shows better on screen.
If I run this:

```html  
[CA, IL, NY, ST, TX, WA]
```

Notice that **ST** is in my list of distinct states,
and that's because of the header row.

```java  
public class Main {

    public static void main(String[] args) {

        String pathName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course06_ReadingTextFromInputFiles/Part2_ScannerProject/fixedWidth.txt";

        try (Scanner scanner = new Scanner(new File(pathName))) {
            
            var results = scanner.findAll(
                    "(.{15})(.{3})(.{12})(.{8})(.{2}).*")   // Stream<MatchResult>
                    .skip(1)
                    .map(m -> m.group(5))                   // Stream<String>
                    .distinct()
                    .sorted()
                    .toArray(String[]::new);                // String[]
            System.out.println(Arrays.toString(results));
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
```

I can use the skip operation in my stream pipeline to skip the header, 
so just skipping the first stream element.
Running that again:

```html  
[CA, IL, NY, TX, WA]
```

I get the distinct list of states.

```java  
public class Main {

    public static void main(String[] args) {

        String pathName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course06_ReadingTextFromInputFiles/Part2_ScannerProject/fixedWidth.txt";

        try (Scanner scanner = new Scanner(new File(pathName))) {
            
            var results = scanner.findAll(
                    "(.{15})(.{3})(.{12})(.{8})(.{2}).*")   // Stream<MatchResult>
                    .skip(1)
                    .map(m -> m.group(3))                   // Stream<String>
                    .distinct()
                    .sorted()
                    .toArray(String[]::new);                // String[]
            System.out.println(Arrays.toString(results));
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
```

I can now do this with any field, so let me try it for department, the third column.
I'll run that again:

```html  
[Finance     , HR          , IT          , Marketing   ]
```

Notice this data contains the extra spaces
because each value is fixed at 12 characters, and this includes any whitespace.
I can fix that, by tacking on the trim method, in my map operation.

```java  
public class Main {

    public static void main(String[] args) {

        String pathName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course06_ReadingTextFromInputFiles/Part2_ScannerProject/fixedWidth.txt";

        try (Scanner scanner = new Scanner(new File(pathName))) {
            
            var results = scanner.findAll(
                    "(.{15})(.{3})(.{12})(.{8})(.{2}).*")   // Stream<MatchResult>
                    .skip(1)
                    .map(m -> m.group(3).trim())                   // Stream<String>
                    .distinct()
                    .sorted()
                    .toArray(String[]::new);                // String[]
            System.out.println(Arrays.toString(results));
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
```

Running it again:

```html  
[Finance, HR, IT, Marketing]
```

My department listing is now trimmed of extra white space.
The combination of regular expressions, and streams,
has made it straightforward to do analysis on files you receive.
There are a lot of methods on **Scanner**,
which let you read different data types from the input,
but these are more geared to reading object stream data,
which I'll cover later.
What I want to focus on right now
is this overloaded constructor on **Scanner**.

```java  
public Scanner(File source) throws FileNotFoundException {
    this((ReadableByteChannel)(new FileInputStream(source).getChannel()));
}
```

Going up to the declaration of my **scanner**, 
I'll control click on **Scanner**.
This brings up the **Scanner** constructor code 
that takes a **File** as the source.
By now, you know the **File** class is **IO** 
and not **NIO.2**, but let's examine this code a little bit.
You can see it's calling another overloaded constructor, 
and passing that what looks like a **ReadableByteChannel**.
This is definitely using **NIO.2** functionality.
I haven't covered channels yet. 
I will in a bit, but it's interesting to know,
that even though we think we might be using an **IO** class, 
like **Files** and **Scanner**,
the underlying functionality is taking advantage of the **NIO.2** enhancements.
Let's get back to the _main_ method, 
and instead of a **File**, I'll now construct this scanner with a **Path** variable.

```java  
public class Main {

    public static void main(String[] args) {

        String fileName = "./src/CourseCodes/NewSections/Section_18_InputOutputFiles/Course06_ReadingTextFromInputFiles/Part2_ScannerProject/fixedWidth.txt";

        //try (Scanner scanner = new Scanner(new File(pathName))) {
        try (Scanner scanner = new Scanner(Path.of(pathName))) {

            var results = scanner.findAll(
                            "(.{15})(.{3})(.{12})(.{8})(.{2}).*")   // Stream<MatchResult>
                    .skip(1)
                    .map(m -> m.group(3).trim())                   // Stream<String>
                    .distinct()
                    .sorted()
                    .toArray(String[]::new);                // String[]
            System.out.println(Arrays.toString(results));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
```

Now, instead of a _FileNotFoundException_,
I'll need a _catch_ clause for _IOException_.
I'll click on **Scanner**, 
and that should give me the light bulb gutter icon, 
and that will show me several options.
The one I want is, replace _FileNotFoundException_
with more generic _IOException_.
If I run this code:

```html  
[Finance, HR, IT, Marketing]
```

I get the same results.
I'll do the same thing I did earlier,
and control+click on the **Scanner** keyword in the _try_ block, 
to have a look at the constructor code.

```java  
public Scanner(Path source) throws IOException {
    this(Files.newInputStream(source));
}
```

Somewhat surprisingly, this is a bit different from what we saw previously.
Here, our **path** is an argument to a method on the **Files** class;
I haven't really covered, _newInputStream_.
Let's follow this trail, and see where it leads.
I'll control click on that method name, _newInputStream_.

```java  
public InputStream newInputStream(Path path, OpenOption... options) throws IOException {
    return provider(path).newInputStream(path, options);
}
```

That's delegating to something called a **provider**,
and its _newInputStream_ method,
so I'll control click on that.

```java  
public InputStream newInputStream(Path path, OpenOption... options) throws IOException {
    for (OpenOption opt : options) {
        // All OpenOption values except for APPEND and WRITE are allowed
        if (opt == StandardOpenOption.APPEND ||
                opt == StandardOpenOption.WRITE)
            throw new UnsupportedOperationException("'" + opt + "' not allowed");
    }
    ReadableByteChannel rbc = Files.newByteChannel(path, options);
    if (rbc instanceof FileChannelImpl) {
        ((FileChannelImpl) rbc).setUninterruptible();
    }
    return Channels.newInputStream(rbc);
}
```

Now if I look past that first if statement, I'll see that here again, 
is the **ReadableByteChannel**.
This means that again, this code is taking advantage of 
the **NIO.2** enhancements to file input.
Let's try yet another constructor in our _main_ method.

```java  
public class Main {

    public static void main(String[] args) {

        String fileName = "./src/CourseCodes/NewSections/Section_18_InputOutputFiles/Course06_ReadingTextFromInputFiles/Part2_ScannerProject/fixedWidth.txt";

        //try (Scanner scanner = new Scanner(Path.of(pathName))) {
        try (Scanner scanner = new Scanner(new FileReader(pathName))) {

            var results = scanner.findAll(
                            "(.{15})(.{3})(.{12})(.{8})(.{2}).*")   // Stream<MatchResult>
                    .skip(1)
                    .map(m -> m.group(3).trim())                   // Stream<String>
                    .distinct()
                    .sorted()
                    .toArray(String[]::new);                // String[]
            System.out.println(Arrays.toString(results));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
```

This time I'll pass a new **FileReader**.
This code compiles with that one change.
I'll confirm it works as before.
I'll run that:

```html  
[Finance, HR, IT, Marketing]
```

And my results are the same.
Once more, I'll see what the constructor code will reveal.

```java  
public Scanner(Readable source) {
    this(Objects.requireNonNull(source, "source"), WHITESPACE_PATTERN);
}
```

This is calling an overloaded constructor,
so I'll control click on **this** keyword.

```java  
private Scanner(Readable source, Pattern pattern) {
    assert source != null : "source should not be null";
    assert pattern != null : "pattern should not be null";
    this.source = source;
    delimPattern = pattern;
    buf = CharBuffer.allocate(BUFFER_SIZE);
    buf.limit(0);
    matcher = delimPattern.matcher(buf);
    matcher.useTransparentBounds(true);
    matcher.useAnchoringBounds(false);
    useLocale(Locale.getDefault(Locale.Category.FORMAT));
}
```

Here, what's notable is there isn't any transformation of the source 
to another type of input stream.
When you construct your scanner with a **FileReader**,
the **Scanner** will use the _IO_ **FileReader**, 
meaning it will have minimal buffering, and so on.
I'll go back to the _main_ method one last time,
and wrap that **FileReader** in a **BufferedReader** constructor.

```java  
public class Main {

    public static void main(String[] args) {

        String fileName = "./src/CourseCodes/NewSections/Section_18_InputOutputFiles/Course06_ReadingTextFromInputFiles/Part2_ScannerProject/fixedWidth.txt";

        //try (Scanner scanner = new Scanner(new FileReader(pathName))) {
        try (Scanner scanner = new Scanner(
                new BufferedReader(new FileReader(pathName)))) {

            var results = scanner.findAll(
                            "(.{15})(.{3})(.{12})(.{8})(.{2}).*")   // Stream<MatchResult>
                    .skip(1)
                    .map(m -> m.group(3).trim())                   // Stream<String>
                    .distinct()
                    .sorted()
                    .toArray(String[]::new);                // String[]
            System.out.println(Arrays.toString(results));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
```

This code compiles and runs with the same results.

```html  
[Finance, HR, IT, Marketing]
```

Once more, I'll examine the constructor.

```java  
public Scanner(Readable source) {
    this(Objects.requireNonNull(source, "source"), WHITESPACE_PATTERN);
}
```

You can see this looks like the same constructor the **FileReader** used.
I'll control click on this and confirm it.

```java  
private Scanner(Readable source, Pattern pattern) {
    assert source != null : "source should not be null";
    assert pattern != null : "pattern should not be null";
    this.source = source;
    delimPattern = pattern;
    buf = CharBuffer.allocate(BUFFER_SIZE);
    buf.limit(0);
    matcher = delimPattern.matcher(buf);
    matcher.useTransparentBounds(true);
    matcher.useAnchoringBounds(false);
    useLocale(Locale.getDefault(Locale.Category.FORMAT));
}
```

There I can see the same code.
OK, so what was the point of this exercise?
I wanted you to see that the **Scanner** will take advantage of **NIO.2** functionality, 
if you use either **Path** or **File**, to construct the scanner.
There are times when using that functionality may not be a good fit, 
and I'll discuss that when I get to **channels** later.
For those times, you can still use a **FileReader** 
or preferably the **BufferedFileReader**, and use the underlying legacy IO sources.
</div>

### Character Set
<div align="justify">

Before I cover additional options for reading text from a file,
I'd like to do a brief overview of character sets.
In truth, you're probably not likely to use anything but the default,
but it's a good idea to understand what that default is, 
and why it is the default.
A character set is a defined collection of symbols, letters, numbers, 
punctuation marks, and other characters.
Each character in the set is assigned a unique numerical code, 
called a code point, which allows computers to store, transmit, 
and interpret text.
Two of the most common character sets are **ASCII** and **Unicode**.
**ASCII** stands for the _American Standard Code for Information Interchange_.
It's the oldest and most widely used character set.
**Unicode** is a newer character set, designed to support 
all the world's writing systems.
Character encoding is the process of assigning numbers 
to various characters, called **glyphs**.
A glyph can be an alphabetical character in any language, 
punctuation, or emojis — for example.
There are different ways to represent glyphs with a numeric value.

|            | Size   | Includes Latin Alphabet | Notes                               |
|------------|--------|-------------------------|-------------------------------------|
| US-ASCII   | 7 bits | No                      | Smaller range of characters         |
| ISO-8859-1 | 8 bits | Yes                     | More Widely Supported than US-ASCII |

For the **ASCII** character set, _ISO-8859-1_, 
and **US-ASCII**, are two encodings.
**US-ASCII** is a 7-bit character encoding standard.
**ISO-8859-1** is 8-bit, meaning it can represent 
a wider range of characters than **US-ASCII**.
**ISO-8859-1** includes all the characters 
that are used in the **Latin** alphabet,
while **US-ASCII** only includes the characters
that are used in the **English** alphabet.
This means that **ISO-8859-1** can be used
to represent text in more languages than **US-ASCII**.
**ISO-8859** is a more recent standard than **US-ASCII**, 
and it's been more widely adopted by modern systems than **US-ASCII**.

|        | Size                   | Benefits                                                                                                                              |
|--------|------------------------|---------------------------------------------------------------------------------------------------------------------------------------|
| UTF-8  | Variable(1 to 4 bytes) | **Most popular encoding on the internet.**<br/> Includes ISO-8859-1, and more.<br/> Can represent characters from all writing systems |
| UTF-16 | 2 bytes                | Widely Supported                                                                                                                      |
| UTF-32 | 4 bytes                | More efficient and straightforward to process, but uses more storage space. Rarely used.                                              |

**UTF-8**, **UTF 16**, and **UTF-32**, are all different encodings 
used to represent **Unicode** characters.
All of these encodings are backwards compatible,
meaning they can store **ASCII** characters with the same encoding.
**UTF-8** is variable length, 
so each character might use a different number of bytes.
**UTF-16** and **UTF-32** are fixed width encodings.
Each character is represented by two bytes for **UTF-16**, 
and four bytes for **UTF-32**.
**UTF-8** and **UTF-16** are both very popular encoding systems, 
but **UTF-32** is rarely used, because it takes up more disk space.
In general, **UTF-8** is the better choice for most applications.
It's more efficient, more widely supported,
and can represent a wider range of characters.
However, if you're only working with **ASCII** characters,
**ISO-8859-1** may be a better choice for efficiency reasons.
Java has the most common encodings specified on an enum, 
in the `java.nio.char` set package, called **StandardCharsets**, 
and I've included that URL 
[here](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/nio/charset/StandardCharsets.html), 
for your convenience.
</div>

### Reading With NIO2 Functionality
<div align="justify">

Before I look at reading data from a file,
I'll first check what the default character encoding is.
There are a couple of ways to determine the default.
The first is by getting a system property, called `file.encoding`.

```java  
public class Main {

    public static void main(String[] args) {

        System.out.println(System.getProperty("file.encoding"));
        System.out.println(Charset.defaultCharset());
    }
}
```

The second way is to call the static method,
_defaultCharset_ on the **Charset** class.
Running this code:

```html  
UTF-8
UTF-8
```

Both methods give me the same result, 
and I can see my system's default encoding is _UTF-8_.
Yours may be different, however.
I'm going to use the default in all of my examples, 
but you should know you can override this, 
by passing in the character set you'd rather use, 
in most class constructors that read text files.
I've included the _fixedWidth_ file from the last section, in the package.
In the _main_ method, similar to how I started with the _IO_ classes,
I'm going to start out by reading the smallest unit, in this case bytes.

```java  
public class Main {

    public static void main(String[] args) {

        System.out.println(System.getProperty("file.encoding"));
        System.out.println(Charset.defaultCharset());

        String fileName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course06_ReadingTextFromInputFiles/Part3_ReadingWithNIO2/fixedWidth.txt";
        Path path = Path.of(fileName);
        try {
            System.out.println(new String(Files.readAllBytes(path)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
```

Ultimately, all data is really in bytes, 
and it gets encoded to characters 
if it contains text if we use a text-based reader.
I'll first start with creating a path variable to my file, 
which is in the current package directory.
I know I need a _try-catch_ block, 
so I'll include it now. 
I'll write this code out in a single statement. 
I'll use _readAllBytes_ on the **Files** class, 
passing it my _path_.
I'll pass that result, 
a byte array to a new **String** instance, 
and I'll print it.
I'll catch the _IOException_.
And throw a runtime exception instead.
Running this code:

```html  
UTF-8
UTF-8
Name           AgeDept        Salary  ST
John Doe        30HR             50000NY
Jane Smith      25IT             60000CA
Michael Brown   40Finance        75000TX
Alice Johnson   35Marketing      55000IL
Robert Lee      28IT             58000WA
Emily Wang      32Marketing      52000NY
Daniel Kim      29HR             49000CA
Sarah Davis     31Finance        72000TX
Jessica Chen    27IT             61000IL
David Miller    33Marketing      53000WA
Oliver Scott    26Finance        65000CA
Sophia Adams    37IT             59000NY
William Clark   34Marketing      54000TX
Ava Turner      29HR             51000IL
Ethan Hall      31Finance        70000WA
Isabella King   24IT             62000CA
James Evans     36Marketing      56000NY
Grace Baker     27HR             48000TX
Liam Brooks     32Finance        68000IL
Charlotte Hill  28IT             57000WA
```

You can see, I've read my entire file in one fell swoop, 
into a single **String**, so that's kind of painless.
I can do the same thing, 
but this time with the method _readString_ on **Files**.

```java  
public class Main {

    public static void main(String[] args) {

        System.out.println(System.getProperty("file.encoding"));
        System.out.println(Charset.defaultCharset());

        String fileName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course06_ReadingTextFromInputFiles/Part3_ReadingWithNIO2/fixedWidth.txt";
        Path path = Path.of(fileName);
        try {
            System.out.println(new String(Files.readAllBytes(path)));
            System.out.println("----------------");
            System.out.println(Files.readString(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
```

I'll include a separator line. 
I'll call `Files.readString`, 
passing it path, and print that out.
If I run that:

```html  
UTF-8
UTF-8
Name           AgeDept        Salary  ST
John Doe        30HR             50000NY
Jane Smith      25IT             60000CA
Michael Brown   40Finance        75000TX
Alice Johnson   35Marketing      55000IL
Robert Lee      28IT             58000WA
Emily Wang      32Marketing      52000NY
Daniel Kim      29HR             49000CA
Sarah Davis     31Finance        72000TX
Jessica Chen    27IT             61000IL
David Miller    33Marketing      53000WA
Oliver Scott    26Finance        65000CA
Sophia Adams    37IT             59000NY
William Clark   34Marketing      54000TX
Ava Turner      29HR             51000IL
Ethan Hall      31Finance        70000WA
Isabella King   24IT             62000CA
James Evans     36Marketing      56000NY
Grace Baker     27HR             48000TX
Liam Brooks     32Finance        68000IL
Charlotte Hill  28IT             57000WA
----------------
Name           AgeDept        Salary  ST
John Doe        30HR             50000NY
Jane Smith      25IT             60000CA
Michael Brown   40Finance        75000TX
Alice Johnson   35Marketing      55000IL
Robert Lee      28IT             58000WA
Emily Wang      32Marketing      52000NY
Daniel Kim      29HR             49000CA
Sarah Davis     31Finance        72000TX
Jessica Chen    27IT             61000IL
David Miller    33Marketing      53000WA
Oliver Scott    26Finance        65000CA
Sophia Adams    37IT             59000NY
William Clark   34Marketing      54000TX
Ava Turner      29HR             51000IL
Ethan Hall      31Finance        70000WA
Isabella King   24IT             62000CA
James Evans     36Marketing      56000NY
Grace Baker     27HR             48000TX
Liam Brooks     32Finance        68000IL
Charlotte Hill  28IT             57000WA
```

I should see the same output above and below the separator line.
If I control+click on readString:

```java  
public static String readString(Path path) throws IOException {
    return readString(path, UTF_8.INSTANCE);
}
```

It's delegating to an overloaded _readString_, 
so I'll control+click on that.

```java  
public static String readString(Path path, Charset cs) throws IOException {
    Objects.requireNonNull(path);
    Objects.requireNonNull(cs);

    byte[] ba = readAllBytes(path);
    if (path.getClass().getModule() != Object.class.getModule())
        ba = ba.clone();
    return JLA.newStringNoRepl(ba, cs);
}
```

There's more code here, 
but ultimately you can see its just calling _readAllBytes_,
similar to what we did earlier.
This method, the _readString_ method, should be used
if you know you're reading a text file, 
because it handles some security checks and access issues,
which is this additional code you see here.
Ok, so let's get back to the _main_ method.
You've already seen _readAllLines_ previously,
but now let's see if we can use it to do something similar 
to what we did with **Scanner** previously.

```java  
public class Main {

    public static void main(String[] args) {

        System.out.println(System.getProperty("file.encoding"));
        System.out.println(Charset.defaultCharset());

        String fileName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course06_ReadingTextFromInputFiles/Part3_ReadingWithNIO2/fixedWidth.txt";
        Path path = Path.of(fileName);
        try {
            String regexPattern = "(.{15})(.{3})(.{12})(.{8})(.{2}).*";
            Pattern p = Pattern.compile(regexPattern);
            Set<String> values = new TreeSet<>();
            Files.readAllLines(path).forEach(s -> {
                if (!s.startsWith("Name")) {
                    Matcher m = p.matcher(s);
                    if (m.matches()) {
                        values.add(m.group(3).trim());
                    }
                }
            });
            System.out.println(values);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
```

I'll parse the _fixedWidth_ file to get distinct values out of certain columns,
using the _readAllLines_ method.
This method returns an array of **String** though, 
so I'll show you a solution here, without a stream pipeline.
First, I'll create a compiled pattern, using the same pattern 
I used in the previous section.
If you skipped over that section, 
this pattern simply groups a specified number of characters,
representing a column of data, in _fixedWidth_ file.
I'll create a tree set to store the values. 
I'm using a set because it does not allow duplicates, 
and a tree set because I want it sorted.
I'll execute `files.readAllLines`, passing it _path_, 
chaining the _forEach_ method to that.
I'll set up a multi-line lambda. 
I want to ignore the first row, which is a header row, 
and that will start with _Name_. 
I don't recommend this in real life. 
You'd never want to depend on a header name being the same, for example,
each time you got a new file in, but for now it's a quick work-around.
I'll get a matcher, using my compiled pattern,
passing each string read, from the file.
If there's a match, I'll again, using the group method on the matcher result,
passing it 3 for the third group, and trim it.
I'll add that to my set. 
I'll print my set.
Running this code:

```html  
[Finance, HR, IT, Marketing]
```

I'll get the distinct values in the third column of my fixed width file,
which is the department of the employee.
This is a reminder that there are many ways to write code, 
to perform the same functionality.
**Files** has its own method that returns a stream of **Strings**, 
one string for each line.
When using the stream methods on **Files**,
you need to wrap the assignment and call, 
in a _try-with-resources_ block.
This is very similar to the issue when getting a stream of paths.
I want to again remind you that streams are lazily executed,
so resources are opened and never closed 
until the terminal operation is applied to the stream.

```java  
public class Main {

    public static void main(String[] args) {

        System.out.println(System.getProperty("file.encoding"));
        System.out.println(Charset.defaultCharset());

        String fileName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course06_ReadingTextFromInputFiles/Part3_ReadingWithNIO2/fixedWidth.txt";
        Path path = Path.of(fileName);
        try (var stringStream = Files.lines(path)) {                        //Stream<String>
            String regexPattern = "(.{15})(.{3})(.{12})(.{8})(.{2}).*";
            Pattern p = Pattern.compile(regexPattern);                      
            Set<String> values = new TreeSet<>();                           

            var results = stringStream                                      
                    .skip(1)                                                //Stream<String>
                    .map(p::matcher)                                        //Stream<Matcher>
                    .filter(Matcher::matches)
                    .map(m -> m.group(3).trim())                            //Stream<String>
                    .distinct()
                    .sorted()
                    .toArray(String[]::new);                                //String[]
            System.out.println(Arrays.toString(results));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
```

Inside the parentheses, I'll have a local variable called _stringStream_, 
and assign that the result of `Files.lines`.
I'm going to have this stream return an array,
to a variable called _results_.
I'll skip the header row. 
My pattern is in the _p_ variable, so I'll use that to get a matcher.
This becomes a stream of **matcher** instances at this point.
I want to filter by actual _matches_ because they may not all match.
Some of your data might not be properly formatted, 
but I want to keep processing.
I'll map again, this time to a string, 
using the matcher's group 3, and trimming it. 
I want the values to be distinct and sorted, 
and returned in a string array. 
I'll print the results.
Running this code:

```html  
[Finance, HR, IT, Marketing]
```

I'll again get my list of departments printed.
Let's up the game a little bit, and get counts of employees in each department.
Do you remember how to do this?

```java  
public class Main {

    public static void main(String[] args) {

        System.out.println(System.getProperty("file.encoding"));
        System.out.println(Charset.defaultCharset());

        String fileName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course06_ReadingTextFromInputFiles/Part3_ReadingWithNIO2/fixedWidth.txt";
        Path path = Path.of(fileName);
        try (var stringStream = Files.lines(path)) {                        //Stream<String>
            String regexPattern = "(.{15})(.{3})(.{12})(.{8})(.{2}).*";
            Pattern p = Pattern.compile(regexPattern);
            Set<String> values = new TreeSet<>();

            var results = stringStream
                    .skip(1)                                                //Stream<String>
                    .map(p::matcher)                                        //Stream<Matcher>
                    .filter(Matcher::matches)
                    .collect(Collectors.groupingBy(m -> m.group(3).trim(), Collectors.counting()));

            results.entrySet().forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
```

I'll remove everything after the _filter_ operation,
including my `System.out.println` statement.
Now, I'll use the _collect_ terminal operation,
first passing it a `Collectors.groupingBy`.
I'll group by the column I'm interested in, column 3 or the department,
which I can get from the matcher's group 3, 
and I'll trim that here as well.
I'll follow that up with the `Collectors.counting` methods.
This counts all records within the group.
The result of this is I get back a map, keyed by a string, the department name, 
and the value is the number of records in the department.
I'll print that data out, using the **entrySet**.
Running this code:

```html  
Finance=5
HR=4
IT=6
Marketing=5
```

I see that I have5 employees each in Finance and Marketing,
four in HR or human resources, and six in IT.
Ok, so that's using the various methods on the **Files** class, 
to read data from a text file.
I hope you're starting to get a feel how powerful
these methods can be to diagnose text files.
I'll summarize these methods on a quick table.

| Method Signature                                          | Description                                                   | Closes file?          |
|-----------------------------------------------------------|---------------------------------------------------------------|-----------------------|
| `byte[] readAllBytes(Path path) throws IOException`       | Reads entire contents of **any** file into a byte array.      | Yes                   |
| `String readString(Path path) throws IOException`         | Reads entire contents of a **text** file into a string.       | Yes                   |
| `List<String> readAllLines(Path path) throws IOException` | Reads entire contents of a text file, into a list of string.  | Yes                   |
| `Stream<String> lines(Path path) throws IOException`      | Reads entire contents of a text file                          | On Terminal Operation |

All of these methods read the entire contents of a file into memory.
These methods support files up to about 2 gigabytes.
After that, you're in danger of an out-of-memory error.
For large files, you'll want to use a **BufferedReader**,
or a **Channel** which I'll talk about shortly.
_readAllBytes_ reads the entire contents of any file into a byte array, 
and it will close the resource for you, 
meaning it doesn't need to be included in a _try-with-resources_ block.
_readString_ is similar, but can only be used with text files, 
and is preferred over _readAllBytes_ for text files.
_readAllLines_ also reads the entire contents of a text file, 
but it returns a list of **String**, 
each element representing a line of text from the file.
The lines method is like the _readAllLines_ method,
but the result is a stream source of **String**, 
each element a line of text.
This method should be included in a _try-with-resources_ block.
There are other ways to read data from files, 
and I'll cover those later.
</div>

## [e. Reading File Challenge]()
<div align="justify">

In this challenge, I want you to pick some text of your choice, 
from a document you have, or an online article, or some wiki page.
You'll create a program to read the text document, 
with one of the methods we talked about in the last couple of sections.
You can pick any method you want to use, but whichever you use,
your program should do the following.

* Tokenize the text into words, remove any punctuation.
* Ignore words with five characters or less.
* Count the occurrences of each word.
* Display the top 10 most used words.

The point of this exercise is to see
if you can pick out what the article might be about,
by simply getting the most used words.

After you use one method, try a second method.
If you used a method that used a stream, 
try some code without using a streaming method, or vice versa.
</div>







<div align="justify">

```java  

```

```html  

```

</div>



<div align="justify">

```java  

```

```html  

```

</div>



<div align="justify">

```java  

```

```html  

```

</div>



<div align="justify">

```java  

```

```html  

```

</div>



<div align="justify">

```java  

```

```html  

```

</div>



<div align="justify">

```java  

```

```html  

```

</div>



<div align="justify">

```java  

```

```html  

```

</div>



<div align="justify">

```java  

```

```html  

```

</div>



<div align="justify">

```java  

```

```html  

```

</div>
