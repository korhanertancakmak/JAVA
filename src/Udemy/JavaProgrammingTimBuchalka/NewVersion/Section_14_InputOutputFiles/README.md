# [16. Input & Output in Java]()
<div align="justify">

```java  

```

```html  

```

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



Java has what seems like a very
confusing and large series of classes,
in many packages, to support input/output.
I'll be covering the most commonly used types, but
first let's talk about Java's somewhat confusing
terminology which includes IO, NIO and NIO.2.
First, IO was a term for input/output,
and java.io is a package that contains the
original set of types, which support reading
and writing data from external resources.
NIO was introduced as Non-blocking IO, with
the java.nio package in Java 1.4, as well as
a few other related packages. The communication
with resources is facilitated through specific
types of Channels, and the data stored in
containers called Buffers, when exchanged.
NIO.2 stands for New IO, and is a term
that came into being with Java 1.7,
emphasizing significant improvements to
the java.nio package, most importantly
the java.nio.file package and its types.
Does this mean everything in java.io has
been replaced with new functionality
in the java dot N I O packages?
No, but in many cases, there are
different means, for doing the same thing.
NIO 2 introduced the Path and FileSystem types,
and some helper classes, such as Files, Paths,
and FileSystems, that do make some common
functionality for working with operating
system file systems, much easier as well as more
efficient, delegating work to native systems.
Where functionality overlaps, I'll
try to show these to you side by side.
You'll likely see a lot of legacy
code, using the classes you see
on this slide in the java.io package, so
BufferedInputStreams and FileWriters and so on.
But I also want you to see the NIO 2 types at
work, many of which provide more support for
functional programming.
Let's get started.

</div>


## [a. Stream In Action]()
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



<div align="justify">

```java  

```

```html  

```

</div>
