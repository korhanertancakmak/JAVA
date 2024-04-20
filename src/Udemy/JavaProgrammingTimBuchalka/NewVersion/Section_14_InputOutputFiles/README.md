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
    FileReader reader = null;
    try {
        List<String> lines = Files.readAllLines(path);
        reader = new FileReader(filename); 
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
    FileReader reader = null;
    try {
        List<String> lines = Files.readAllLines(path);
        reader = new FileReader(filename); 
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
    FileReader reader = null;
    try {
        List<String> lines = Files.readAllLines(path);
        reader = new FileReader(filename);
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
    FileReader reader = null;
    try {
        List<String> lines = Files.readAllLines(path);
        reader = new FileReader(filename);
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


### [try-catch Clause with Checked and Unchecked Exceptions]()
<div align="justify">

In the last section, we looked at the _try-catch_ clause, with _checked_ and _unchecked_ exceptions.
I also talked about using the _finally_ clause, as a way to close resources.
Since JDK 7, there's been a better alternative to this approach.

```html  

```

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
Later in this section, I'll cover some of the numerous ways you have to open,
and communicate with files, but right now,
I still just want to focus on exceptions.
I'll first comment out the readAllLines statement.
Next, I'm going to declare and initialize
a FileReader class, passing my
filename string to the constructor.
This class has methods on it to
read data in from a character file,
but for now, I'll just instantiate it.
Now, notice that IntelliJ is highlighting
the FileReader type here.
Let's see why.
Hovering over that, I can see, that FileReader is
being used without a try with resources statement.
Try with resources was introduced in JDK
7, as I mentioned on an earlier slide.
If you do use a traditional try catch block with
classes, like FileReader, that implicitly open
a resource, it's very important to include
closing the resource in the finally block.
Let me show you what this would look
like, in my private method, testFile.
First of all, I need to declare my FileReader
variable, outside of the try catch block,
so that it will be in scope for the
finally block, where I need to close it.
I'll copy the declaration part of the statement,
and paste that on the line above the try clause.
I'll initialize that to null.
Next, I'll remove FileReader as the
type in the try block, because otherwise
it's a declaration of a new variable.
Now, I'll close the reader manually
in my finally clause, before that
last system.out.println statement.
I want to check, and make sure the
reader variable's not null. If it's not
null, I'll try to close the resource.
And again, here I get an error, because the
close method itself throws a checked exception.
I have to choose to catch or specify.
I'll use IntelliJ's help to enclose
that statement in a try catch block.
Ok, so this code has gotten
a bit ugly pretty quickly.
Before JDK 7, this is how you
would've closed a file resource.
I'm going to leave this method as is, and
now create a second one, called testFile2,
with the same signature as
testFile, except the name.
I'm going to start like I did before, with one
statement that instantiates a FileReader instance,
using the filename argument. I'll also
include that last print statement.
Not surprisingly, we know this will give us a
compile error because of the unchecked exception.
I'll select more actions,
and now, what I want you to see are the options
below the first two we looked at previously.
Notice the very last one, Surround with
try-with-resources block.
I'll select that one.
Intelli J has inserted some
code surrounding our statement.
This try block is different, because now it's
got a set of parentheses associated with it.
In those parentheses, I have my FileReader
instantiation statement, minus the semi-colon.
These differences are a little easier to see
if we look at them side by side on a slide.
The try-with-resources takes a colon
delimited list of resource variables.
The resources in this list must implement
the AutoCloseable or the Closeable interface.
The Closeable interface extends
AutoCloseable as of JDK 7.
The try-with-resources statement can be used
without a finally block, because all resources
are automatically closed when this type of try
block completes, or if it gets an exception.
Let's continue looking at this in code.
You can see the try block has no code
in it, and there's also no catch block.
Our code still doesn't compile yet either.
I'll again choose actions from the first dialog.
Now, I've got Add catch clauses, and here, on the
left, Intelli J is showing me two catch clauses.
I'll select this option.
Ok, so what is going on here?
First of all, multiple catch clauses aren't
unique to the try with resources clause.
You can have multiple catch clauses with
either try statement, though I don't
think I've yet reviewed this feature with you.
The reason that there are two, is the first one,
the FileNotFoundException may
occur when opening the resource.
The IOException might occur when
working or closing the resource.
Ironically, Intelli J doesn't like
this code, notice the warning highlight
on the second catch clause.
If I hover over that, it says,
catch branch identical to FileNotFoundException,
and one of the hints is to collapse catch blocks.
Ok, that's an option, but right now
I'm going to manually edit this code.
I'll change the catch block
for FileNotFoundException,
and add a system.out.println statement there.
Now, my catch blocks have different code blocks,
and Intelli J is no longer giving me
a warning on that second catch clause.
This code is a lot easier to read,
because there's no finally block.
You don't need the finally block to close this
resource any more, but you can still include it,
if you want to do logging or something else.
I'll add it here, and print the same thing
I had in the finally block in testFile, so
that's maybe I'd log something either way.
I'll go back to my main method, and call
this method, instead of just testFile.
Running this code,
There are some rules around having multiple catch
blocks, so let's play with these a little bit.
First, I'll cut the second catch block with
the IO Exception and it's throw statement.
I'll paste that above the
FileNotFoundException catch block.
Changing the order of these catch clauses has
introduced a compiler error,
on the second catch clause.
If I hover over that, I see, the Exception
FileNotFoundException has already been caught.
How is that possible?
As it turns out, Java evaluates
When IOException is declared, it catches all
instances of that Exception, as well
as all instances of its subclasses.
Because FileNotFoundException is a subclass of
IOException, it gets caught by the first clause,
as we have it declared in this code.
This means the FileNotFoundException
clause is redundant or unreachable,
when specified after it's super class.
If you do have code that's different
for specific exceptions, you need to
declare your clauses in hierarchical order from
most specific, downward to the most general.
I'll revert those last two changes.
Next, I'll add a truly catch all
clause, catching just Exception,
and I'll put this as the last clause.
I'll print that something
unrelated and unexpected happened.
The class Exception extends Throwable, so I could
include Throwable in this hierarchy as well,
Let's quick look at a hierarchical
chart of the Throwable class,
which is the parent class, of all of
Java's exceptions and error classes..
This chart shows you which classes are Checked,
in blue, and Unchecked, in Reddish Brown.
I had said before that you know a Checked
Exception, because it's not a runtime Exception.
An error is also an unchecked exception, meaning
you aren't forced to catch or specify its type.
The Error class indicates serious problems,
that a reasonable application
shouldn't try to catch or recover from.
I've already mentioned there are two
types of Exceptions, those that subclass
from RuntimeException, and those that don't.
There are quite a lot of Exception classes,
and this diagram represents only a
couple for demonstration purposes.
This hierarchy becomes important for
the second variation of a catch clause.
Getting back to the code, I want to show
you another variation to the catch clause,
which lets you catch multiple targeted
Exceptions with a single clause.
Instead of listing one type of Exception and
a local variable for that exception, you list
multiple exceptions separated by a pipe character.
This kind of looks like an or statement,
and helps you remember that this catch
expression means one exception or the other.
There is still only a single
variable declared though.
To demonstrate this, I'll
use NullPointerException,
then a pipe, then IllegalArgumentException,
and call my exception variable, bad data.
In this case, if either of these exceptions
is thrown, I'll print user has added bad data,
and print the error message.
I'll now pass null to the call to testFile2 in the main method.
Running this code now,
I don't get an exception thrown or a stack trace.
I just get a series of confusing messages.
You can see the code that was in
our new catch block got executed.
This code printed out, User
has added bad data, null.
Apparently, passing a null to FileReader,
doesn't throw a FileNotFoundException,
but a NullPointerException.
In this case, I'm not
propagating any errors up the stack.
The finally clause was executed because
that gets called either way, so I see
Maybe I'd log something either way.
After this, the last statement in the
testFile2 method was reached and executed.
This printed File exists and able to use as a
resource, which actually isn't factually correct.
When the code goes back to the main method,
it checks for the existence of the
previous file, and doesn't find it.
Finally, it prints Quitting
Application, go figure it out.
You can't list exceptions in the multi exception
clause, that are derivatives of the same class.
Look what happens if I now add the more
generic RunTimeException to that clause.
Hovering over the error in this statement,
Intelli J is telling me, that types
in a multi-catch must be disjoint.
This means these types can't have
direct relationships with one another.
In this case both NullPointerException, and
IllegalArgumentException, are subclasses
of RuntimeException, which
means they aren't disjoint.
I'll revert that last change, so the
code compiles, and runs as previously.
Ok, so I'll close this lecture here.
I know these lectures on exceptionhandling were a bit dry,
but from now on,
we'll be dealing with a lot of exceptions,
as we explore using external resources.
The key takeaways are, that many of the
types to read and write to files, are
instantiated using the new key word.
Underneath the covers, the constructor opens
the file resource, and it's important to
close the resource when you're done with it.
Using try with resources is the recommendedapproach,
both to make your code more
concise, and to avail yourself of
Java's built-in support for automatically
closing resources with that try block.
In the next video, I'll be covering some of the
most basic classes in the i o, and n i o packages.
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
