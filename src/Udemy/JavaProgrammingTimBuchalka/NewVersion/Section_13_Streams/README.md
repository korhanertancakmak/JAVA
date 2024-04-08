# [Section-13: Streams]()
<div align="justify">

In this section of the course, 
I'll be looking at streams, 
which were introduced in Java 8. 
When you think of a stream, 
you might be thinking of Input and Output or I/O streams, 
like a buffered input stream or file output.
That isn't the type of stream I'm talking about here. 
Oracle's Java documentation describes a stream as:

**NOTE**: A sequence of elements supporting sequential and parallel aggregate operations.

Another way of putting that is, 
a stream is a set of computational steps against a data set 
that are chained together.
In previous lectures, 
I showed you method chaining with lambda expressions, 
by using convenience methods on predicates,
 functions, and consumers. 
I've also shown you that 
you can assign lambda expressions to variables. 
This lets you pass around unexpected code in the form of a variable, 
and these can be passed to methods. 
This means the code in the variable is executed at a later date, 
lazy execution in other words. 
Streams are a mechanism for describing a whole series of processes, 
before actually executing them.

A stream is different from a collection in significant ways. 
The stream and the collection types were designed for different purposes. 
A _collection_ is used to _store and manage a series of elements_ in Java, 
providing **direct access** to the Collection elements. 
As I've been showing you now for quite a few sections, 
you can use collections, without knowing anything about streams, 
to manipulate or query a set of data.
In fact, all the examples I've shown you to date have done a lot of that, 
with iterators, and a host of methods provided 
by the collection framework interfaces and classes. 
There's nothing you can do with a stream 
that you couldn't already do with a Collection. 
However, a **stream** was designed to **manage the processing of elements**. 
This means that you, as a developer, 
don't have to design the processing code, just the process itself. 
Streams don't store elements; instead, these elements are computed on demand, 
from a data providing source.
This source may be a collection, an aye o stream, or a database result. 

First, this interface is generic, and takes a collection of _T_, 
so a stream can process any type, that's not primitive. 
Java provides special Streams for the primitive types as well, 
with _IntStream_, _DoubleStream_ and _LongStream_. 
If I select the method tab on this page, 
there are a lot of methods on this interface, 
but what I want you to notice is, what's not here. 
There's no _add_, _addAll_, _contains_, _get_, _put_ or _remove_ methods, 
the methods that are integral to the **Collection** interface. 
This is because you're not going to be manipulating 
data elements individually in a stream. 
You'll be treating the stream as a unit or whole, 
oftentimes aggregating or reducing the data. 
Maybe you want to count the elements, or group elements in the stream in some way. 
Another important difference is that streams are lazy, 
like lambda expression variables. 
When you call many of the methods on a stream, 
execution may not immediately occur. 
Instead, you'll need to invoke a special operation on the stream, 
like you would be calling a lambda's functional method. 
This special operation is called a terminal operation. 
You might wonder when would you want to use a stream instead of a Collection?
Streams are an exciting addition to Java, because they provide several benefits.

* First, they make the code to process data uniform, concise and repeatable, 
in ways that feel similar to a database's structured query language (or SQL).
* Second, when working with large collections, 
parallel streams will provide a performance advantage. 
I'm not going to talk about parallel streams just yet, 
because I'll have an entire section on concurrency later.

There's plenty to cover on basic stream processing 
before diving into parallel streams.
All the code samples I've provided up to this point, 
using collections, will continue to be valuable for many use cases. 
Now though, It's time to talk about this new way of doing things, 
using this additional functional programming feature. 
The combination of collections, lambda expressions, 
and streams makes it so much easier to produce the results you want, 
with very little code, and the code is uniform, concise and readable. 
Stream operations make heavy use of lambda expressions and method references. 
If you haven't developed a strong degree of comfort with lambda expressions, 
please take a few minutes to review that section again. 
It's especially important to be comfortable 
with the four commonly used category types, **Consumer**, **Predicate**, **Supplier** 
and **Function**, in the `java.util.function` package. 
Ok, let's get started.
</div>

## [a. Stream In Action]()
<div align="justify">

I'm going to set up a _collections_ example, 
and then show you how to do the same thing using _streams_. 
Inside the _main_ method, 
I'll be creating a list of strings to represent 
the ball labels in a **bingo** game. 
If you haven't ever played **bingo**, 
or if you've never played in person, 
there's usually a container of labeled balls. 
Each ball is identified with either a 
_B_, _I_, _N_, _G_, or **O**, and followed by a number. 
A _B_ ball will have a number from 1 to 15, 
an '_I_' ball will get a number between 16 through 30, and so on. 
Someone will draw a ball out of the container 
(if you're playing in a physical setting), 
and announce it.
Every player will have a five-by-five card,
with some randomly generated set of these numbers on it. 
The first column has the _B_ numbers, 
the second column has the _I_ numbers, and so on. 
You win by being the first person to match 
five called numbers in a row, either horizontally,
vertically, or diagonally.
I'll be using this bingo example for a couple of lectures. 
Getting back to the code, 
the first thing I want to do is create a collection 
of all possible 75 bingo balls, or labels. 
I'll set up my Arraylist of String, calling it bingoPool. 
It's a new Arraylist with 75 elements.


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