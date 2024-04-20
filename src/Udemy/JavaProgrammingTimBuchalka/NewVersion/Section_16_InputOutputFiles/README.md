# [16. Input & Output in Java]()
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
and then just purposely code a divide by zero exception there.
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
Now, I've got Add catch clauses, and here, 
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

Next, I'll add a truly catch all clause, catching just exception,
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
Here, I'll use the _fileExists_ boolean variable,
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



If I open my project pane, I can see my `testfile.txt` file there, 
in the project's directory, so it was successfully created.
If I run the code a second time,
I can see that my code recognized that the file does now exist.
This is followed by the statement, deleting file.
Then my code creates it
again, and would write to it.
Ok, just to summarize, in this
code, I used an instance of File,
to test some conditions in the file system,
as well as to create and delete a file.
Now, I'll do the same thing, but I'll instead of
using an instance of the older File class, I'm
going to use an instance of the newer Path type,
with the help of the methods on the Files class.
I'll copy this entire method, and
paste a copy below the useFile method.
I'll first change the name
from useFile to usePath.
I need to replace the statement
here, where I create a file instance.
First, I'll create a path
variable, simply called path.
In the last video, if you remember, I
used the get method on the Paths class.
JDK11 introduced a static factory
method on the Path interface itself,
a method called of, so that's what I'll
use here, passing it my fileName argument.
Right away, that gives me four
compiler errors, everywhere where
I was accessing the file instance.
I'll need to replace each instance,
first replacing the file variable with the Files
class name, and instead of passing no arguments
to each method, I'll pass my path instance.
First, I'll do that for the file.exists method,
changing file to Files,
and pass path to the exists method.
Next, I want to do the same
thing, for file.delete.
In this case, I've got an error
after I make those changes.
I'll come back to that in a minute.
I'll keep going with the file to
Files replacement, so next, I
need to change createNewFile.
After I make that change, I can
see I have an error here as well.
That's because the method doesn't exist on Files.
Again, I'll come back to this.
I'll next change file.canWrite
to Files.canWrite, and pass path.
There's another error, because as
before, this method isn't on Files.
Ok, let's address each of these issues,
starting with the delete method.
I can hover over that, and now I see, the
delete method on Files throws an IOException,
so I have to either catch or specify.
I'll pick surround with try catch here.
Now, I have a different error, and this is
another difference between the delete methods.
Because the one on this class throws
an exception when there's a problem,
it doesn't return a boolean value, or
any value for that matter, it's void.
I have to change my code.
I'll remove fileExists,
the assignment, and the not
before that method call.
I'll set fileExists to falls, after that delete statement.
I also don't want to throw a runtime exception,
so I'll change the generated catch clause.
I'll remove that throw new
Runtime Exception statement too,
because I'm going to put something else there.
In the try clause, I'll just
print the exception I got.
The delete method is all good now, so moving
down to the create new File method, I'll delete
that method, since it doesn't exist on Files.
I'll delete the dot there, and enter it again,
so IntelliJ will display a list
of methods available on Files.
Here you can see createFile
near the top of the list.
I'll pick that one.
This change seems to be fine,
and I already have it in a try catch block.
The one difference I do want to point out
to you is, The Files class's version to
create a file, doesn't return a boolean,
as did the file class, which returned
true for success, and false for failure.
Similar to the delete method, it will
throw an exception for a failure.
This feels like we're moving from a look
before you leap approach, to an easy to ask
for forgiveness response, doesn't it?
Moving on, to the canWrite method,
let's delete it, retyping the dot, again
to see the list of possible methods.
Right away, I'll can see an isWritable
method, so I'll select that.
Now, everything compiles,
and that wasn't too painful.
I'll add a call to this method, with a
different filename, in the main method.
I'll call usePath, and my file
name will be pathfile.txt.
I'll run this.
In the second set of outputs, I can
see file pathfile.txt does not exist,
and that the code says it was created,
and that it can be written to.
Again, opening the project panel, I
want to confirm it's been created,
and sure enough, I can see it's there.
All I've really demonstrated by this exercise,
is that you can do the same thing with Files and
path, as you can, simply using a File instance.
But now, let's make another
change to the usePath method.
Here, after I test if the file is Writable,
I print this statement to the console.
This time however, I really
am going to write to the file.
I'll call a method named writeString
on this Files helper class,
again passing it my path variable,
and a String as the second argument.
I'll pass it a text block
with several lines of text.
I want this text to be written to my newly created
file. I should see in my file, after this code
is run, Here is some data, for my file, just to
prove, Using the Files class and path are better.
I'm getting the error, that says
this throws an unhandled exception.
Rather than surround this with a
second try catch, what I'll do is,
include this code with the code
above it, in one big try catch.
I'll cut the catch clause I have above this code.
I'll paste that, after my nested if statement.
I can reformat that with the control
alt L on windows, or for mac.
I'll delete line 65 because we have now
implemented code to write to file and
this comment is redundant.
Running with that change,
I can now open that file, pathfile.txt,
and there is the text from my text block.
Ok, that was super easy to do.
I can also read this information
from the file, just as easily.
I'll add a couple of statements,
after that isWritable if clause.
I'll print that I can read too,
following that with a separator line.
And now, in a single line of code, I can call
the readAllLines method on Files, passing the
result directly to a forEach method, and I'll pass
that the method reference for System.out.println.
Running this,
I can see in my output the statement,
And I can read too, followed by dashes.
After that, here are the lines that the code
read from my text file, printed to the console.
I'll be talking more about reading
and writing to files in future videos,
but that's just about as easy as it gets.
Ok, Let's quick look at some of the functionality the I O,
and N I O 2methods, have in common, on a slide.
This slide shows you quite a bit
of the functionality you'd expect
from a File System, and the methods
you'd use for each of these classes.
You can see the methods are often similarly
named, and methods exist for all the basics.
I'm not going to go through each of these
individually, and compare them to each other.
What I want you to remember is, if
you need to support legacy code,
or if you need to be backwards compatible, to
a version of the JDK that's older than JDK7,
you can still rely on the File class.
If you're starting out new,
I'm recommending you use the Files class
(Files with an s), and a Path instance,
otherwise known as the NIO2 way of doing things.
Let's review why NIO2 is better, under the covers.
First of all, File operations have been improved,
The NIO2 types include support for:
Asynchronous file I/O operations. And File
locking, including more granular locking.
This means, instead of locking the entire file,
a region of it can be locked. There's enhanced
support for File metadata retrieval. As well
as new support for Symbolic link manipulation.
Finally, the NIO2 types have support for
File system notifications. This means changes
occurring on a path, can be made watchable
to registered services.
I plan to give you an example of this in the concurrency section of the course, where it should make more sense.
The NIO2 types have been
designed to be better performing.
NIO2 types are non-blocking, meaning
asynchronous access to resources,
by multiple threads, is supported.
They manage memory more efficiently, reading
and writing files directly to and from memory into
buffers, through something called a FileChannel.
I'll be covering reading from, and
writing to files shortly, and I'll
discuss the FileChannel and it's advantages.
You can also read from or write to multiple
buffers in a single operation,
which I'll be showing you as well.
At this point, I'll be switching to
just using the Files and Path code,
otherwise called the NIO2 types, and focusing
on the functionality these types offer us.
In the next video, I'll cover directory
listings and related topics, so let's move on.

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
