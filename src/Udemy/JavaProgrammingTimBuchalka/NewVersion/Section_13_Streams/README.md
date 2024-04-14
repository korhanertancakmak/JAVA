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
Streams are an exciting addition to Java because they provide several benefits.

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

![image01](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_13_Streams/images/image01.png?raw=true)

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
List<String> bingoPool = new ArrayList<>(75);

int start = 1;
for (char c : "BINGO".toCharArray()) {
    for (int i = start; i < (start + 15); i++) {
        bingoPool.add("" + c + i);
        //System.out.println("" + c + i);
    }
    start += 15;
}
```

The first number on a ball starts at 1, 
so I want a local variable, start, and that gets set to one. 
I'll set up an enhanced for loop to iterate over the characters. 
I'll use the literal string, **BINGO** with the _toCharArray_, 
to get a character array.
This means I'll be looping through the characters in the **BINGO** word. 
I want a nested loop, with 15 iterations. 
Each letter will get 15 different numbers, 
but for each letter, these numbers will increase by fifteen.
If I just passed c plus i to this method, 
I'd get an integer back as a result
not the character letter concatenated to the integer value. 
If I start with an empty string, the values are concatenated. 
Next, I'll print out each generated ball label. 
After each letter is processed, I need to increase the starting number by 15. 
If I run this code, I'll get 75 labels printed out, from B1 to O75. 
Next, I want to comment out that `System.out.println` statement
in the for loop, since I've confirmed this is working.

```java  
Collections.shuffle(bingoPool);
for (int i = 0; i < 15; i++) {
    System.out.println(bingoPool.get(i));
}
System.out.println("------------------------------------");
```

Now, I'll shuffle these, using `Collections.shuffle`, 
passing it my _bingoPool_. 
After they're shuffled, I'll print the first 15. 
I'll use a traditional for loop to do this, 
stopping after 15 iterations. 
I'll include a separation line here.
Running this, I'll see the first fifteen shuffled labels.

```java  
List<String> firstOnes = bingoPool.subList(0, 15);
List<String> firstOnes = new ArrayList<>(bingoPool.subList(0, 15));
firstOnes.sort(Comparator.naturalOrder());
firstOnes.replaceAll(s -> {
    if (s.indexOf('G') == 0 || s.indexOf("O") == 0) {
        String updated = s.charAt(0) + "-" + s.substring(1);
        System.out.print(updated + " ");
        return updated;
    }
    return s;
});
System.out.println("\n----------------------------------");
```

Now, I'll assign those first fifteen to another list variable, 
called _firstOnes_, by using subList, with the arguments, 0 to 15. 
I'll now sort these first 15, in natural order, meaning the string order. 
I'll use the _replaceAll_ method, to do some kind of transformation on each one. 
And actually, I'll only do this for the G and O labels, 
let's say I want to add a dash between the character and the number, 
so that the G and O are more easily visible to the reader. 
I'll print the updated label for this selected group. 
I'll return the new label, which will replace the old label in the list.
If it's not an O, or a G label, I'll just keep the old value, 
so I'll return that. 
I'll print a new line, and a separator line. 
Running this code:

```html  
G55
G53
B11
G60
G52
N38
B3
G49
N36
N42
B15
B9
O72
B10
B4
----------------------------------
G-49 G-52 G-53 G-55 G-60 O-72
----------------------------------
```
                        
The result will be different each time, 
but you should see a sorted list of the G and O labels, 
with dashes included.
Ok, so nothing new here. 
I'm creating a collection, shuffling it, getting a sublist, 
and filtering part of it to do 
some kind of transformation on that set of elements.

```java  
for (int i = 0; i < 15; i++) {
    System.out.println(bingoPool.get(i));
}
System.out.println("------------------------------------");
```

I'm going to next print the first 15 labels 
again from my bingo pool list. 
I'll copy and paste the statements at the end of my _main_ method. 
Running this, what I want you to see is 
that everything I did to my _firstOnes_ list 
was done to the _bingoPool_ list. 
This is just a reminder that the _subList_ method returns a view, 
and to use sublist only when you really do want 
to alter the original list. 
If I didn't want these changes 
to affect my original shuffled bingo pool, 
and I don't here, then I should make a modifiable copy of the sublist. 
I can do this by creating a new array list, 
and passing that sublist to it. 

```java  
//List<String> firstOnes = bingoPool.subList(0, 15);
```

I'll comment out that first assignment. 
I'll wrap a new ArrayList instance around that call to sublist. 
I'll run this code again:

```html  
---(same)
G55
G53
B11
G60
G52
N38
B3
G49
N36
N42
B15
B9
O72
B10
B4
----------------------------------
G-49 G-52 G-53 G-55 G-60 O-72
----------------------------------
```

And now you can see I haven't changed anything 
on the original bingo pool list. 
It's the same as it was previously. 
Next, I want to do the same kind of thing, 
on the first 15 labels, using a stream.

```java  
bingoPool.stream()
        .limit(15)
        .filter(s -> s.indexOf('G') == 0 || s.indexOf("O") == 0)
        .map(s -> s.charAt(0) + "-" + s.substring(1))
        .sorted()
        .forEach(s -> System.out.print(s + " "));

System.out.println("\n----------------------------------");
```

You may have noticed in your own research 
of the Collection interface and its methods 
that it declares both a stream and _parallelStream_ method. 
That means we can create a stream from any Collection type, 
and I'll do that here, with the _bingoPool_. 
That returns an implementation of the **Stream** interface, 
and in this case, the type of implementation isn't important. 
Having a stream, I now have access to all the stream methods,
so I can chain them to this stream.
It's common practice to start each new chained operation, 
on its own line, starting with the dot in the chain. 
I'll start with a function called limit, 
which limits the number of elements I get from the stream. 
Like before, I just want the first 15, 
and I can do this by passing that value, 
to the limit operation, as the first operation. 
I'll next call filter, and pass this operation 
to the same condition I had before, 
to get the G and O labels. 
I'll follow this with map, 
which you can think of as similar 
to the _replaceAll_ method on collections. 
I'll add that dash between the character and number. 
I'll sort the stream at this point. 
Finally, I'll print each label followed by a space on one line.
Running this code, I get the same set of labels, 
in the same format, as the code above. 
For good measure, I'll copy, pasting those statements 
at the end of the method, 
to see if this code had any effect on the original bingo pool collection. 

```java  
for (int i = 0; i < 15; i++) {
    System.out.println(bingoPool.get(i));
}
```

Running that, you'll see that my first 15 elements 
on the bingo pool are unchanged. 
This stream code didn't have any effects on it. 
Take a minute to compare that. 
The stream code is a little more concise, 
meaning one or two statements less, 
but it's a lot more readable and ordered. 
Also, the code above was left to the programmer, 
to decide and implement both what to do, 
and how to get the job done.
With streams, there's a pattern of operations to choose from, 
which means the developer just describes what needs to be done, 
and doesn't have to code the how to do it part.
The results are also different from these two segments of code. 
In the first, I have an Array list of fifteen labels,
and I've changed a few of those labels in that list. 
In the second segment of code, 
I'm simply printing information out about a subset of the data, 
after I've filtered and transformed it. 
Right now, it's not collecting results into a variable, 
though I could have done that as well. 
Each executable step in this second segment of code 
is called an operation, when using streams. 
I'll be covering each of these operations 
and quite a few more, over the course of the next couple of lectures. 
But this chain, this listing of these five operations, 
in this particular order, is called a stream pipeline. 
This is what I want to examine next, 
because not all operations in the pipeline are equal.
</div>

## [b. Stream Pipeline]()
<div align="justify">

In the last section, 
I left off with our first example of using a stream in code. 
I'll display it here. 
This entire chain of operations is 
what's called a **Stream Pipeline**.

![image02](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_13_Streams/images/image02.png?raw=true)

The source of the stream is 
where the data elements are coming from. 
In our example, it's coming from a list, _bingoPool_.
All pipelines start with a stream, so in this example, 
we need to call the stream method on the _bingoPool_ list 
to get a stream. 

![image03](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_13_Streams/images/image03.png?raw=true)

There are a lot of other kinds of sources 
and ways to create new streams, including infinite streams. 
I'll be discussing these shortly.

![image04](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_13_Streams/images/image04.png?raw=true)
        
Stream Pipelines end in a **terminal operation**, 
which produces a result or side effect. 
In this example, the _forEach_ operation 
executes a **Consumer** implementation. 
This prints out some data about each element 
that was processed, and its current state. 
A terminal operation is **required**. 
There are many different kinds of terminal operations, 
returning many different types. 
The _forEach_ operation along 
with the similar _forEachOrdered_ operation are 
the only ones that don't return anything, 
they're void in other words. 
I'll be reviewing all of these 
with you over the courses of this section. 
Everything else between the source 
and the _terminal_ operation 
is an **intermediate** operation.

![image05](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_13_Streams/images/image05.png?raw=true)

An intermediate operation is **not required**. 
You can have a pipeline that just has a _source_ and _terminal_ operation, 
and these are quite common, and I'll be giving you examples shortly. 
Every _intermediate_ operation processes elements on the stream, 
and returns a stream as a result. 
Knowing this piece of information will help you 
identify a terminal vs. _intermediate_ operation in the **Stream** API.
Let me show you that.
As we scroll through these methods, 
pay attention to the return type. 
For example, the first two, 
_allMatch_ and _anyMatch_ return a boolean. 
These are _terminal_ operations, 
as are the next two, 
which are overloaded versions of a function called _collect_. 
Scrolling down a bit, you can see now, 
_distinct_, _dropWhile_, and _filter_ here. 
I used _filter_ in my example code. 
This operation returns a **Stream**, 
and is an _intermediate_ operation, 
as are _distinct_ and _dropWhile_, 
and others that return a **Stream**. 
Some return special streams for primitive types, 
like **DoubleStream**, **IntStream**, and **LongStream**. 
You'll remember that the **Stream** interface,
which is generic, can't be typed with a primitive. 
Java provides these special streams, 
when you don't want the overhead of a wrapper type. 
I'm going to cover most of these operations, 
and what they mean and how to use them. 
What I also want you to see is 
that nearly every one of these, 
uses some kind of **functional** interface. 
You can see **Consumer** in the _forEach_, 
**Supplier** in _generate_, 
**UnaryOperator** in _iterate_, 
**Function** in _map_, and so on. 
It's definitely a lambda expression landscape. 
Again, if you feel like you might be a little weak 
on lambdas, or method references, 
I hope you'll give yourself a review of that subject.
**_Lambdas_ and _Streams_ were built for each other**, 
and are both part of Java's tilt towards functional programming.

Finally, you can always search for _terminal_ 
in your browser session to scroll 
through the different _terminal_ operations. 
If I keep hitting next, 
I'll work my way through the _terminal_ operations. 
I could do the same for _intermediate_ as well, 
but I won't do that now. 
There are some additional topics 
I want to discuss the pipeline, 
before I get back to the code. 
Scrolling almost all the way back 
to the top of this page, 
I want to highlight the next important point.

**Streams** are lazy; 
computation on the source data is only performed 
when the _terminal_ operation is initiated, 
and _source_ elements are consumed only as needed. 
What does that really mean? 
Without worrying about semantics, 
I want you to imagine the stream pipeline as a black box. 
The _source_ is your input, 
the result of your _terminal_ operation is your output. 
Everything in between isn't going to happen 
until something tells that _terminal_ operation to start. 
What actually happens in that black box may not happen 
exactly as you've described it, 
or in the order you've specified.
This is quite different from chaining methods, 
where the execution of every method is guaranteed to occur, 
and it occurs in the order you defined it,
against a known set of elements. 
Execution of the _intermediate_ operations is dependent, 
first on a _terminal_ operation being specified, 
and second on an optimization process occurring.

**Stream** computations are optimized for performance. 
What this means is that your stream pipeline is
kind of a workflow suggestion. 
Before the process begins, 
the stream implementation will perform an evaluation, 
to optimize the means to the end. 
It will determine the best way to get the elements needed, 
and the most efficient way to process them, 
to give you the result you've asked for. 
The result will be consistent each time, 
but the process to get there is not guaranteed to be. 
Optimizations may change 
the order of the _intermediate_ operations, 
it may combine operations, or even skip them altogether. 
For this reason, 
you should avoid side effects in your _intermediate_ operations. 
For example, in the code I've been working with, 
I've requested that my stream be filtered by the _G_ and _O_ labels. 
If the source stream only contained _G_ labels, 
this filter might be omitted altogether. 
If I include code in the lambda to do something else, 
to increment a counter on another object 
or something like that 
it may never be executed. 
I'm going to come back to this point again a bit later, 
in the _intermediate_ operations section.

```java  
/*
bingoPool.stream()
    .limit(15)
    .filter(s -> s.indexOf('G') == 0 || s.indexOf("O") == 0)
    .map(s -> s.charAt(0) + "-" + s.substring(1))
    .sorted()
    .forEach(s -> System.out.print(s + " "));
*/
```

Before we move on, 
let's get back to the code for just a minute. 
First, I want to comment the last couple of statements
that print out the first 15 strings in the bingo pool. 
Next, I'll comment out the statement that has the terminal
operation for a moment.
And end the statement above that with a semicolon.

```java  
bingoPool.stream()
    .limit(15)
    .filter(s -> s.indexOf('G') == 0 || s.indexOf("O") == 0)
    .map(s -> s.charAt(0) + "-" + s.substring(1))
    .sorted();
//.forEach(s -> System.out.print(s + " "));
```

IntelliJ is giving me a warning, 
but the code compiles, and I can run it, 
which I'll do next.

```html  
----------------------------------

----------------------------------
```

None of these operations are executed. 
I've said they won't be executed 
until a _terminal_ operation is executed, 
and I've also said a _terminal_ operation is required, 
to complete the pipeline. 
In this case, 
I haven't included a _terminal_ operation in my pipeline at all. 
Can I make a call to the _terminal_ operation later? 
Let's try that. 

```java  
var tempStream = bingoPool.stream()
        .limit(15)
        .filter(s -> s.indexOf('G') == 0 || s.indexOf("O") == 0)
        .map(s -> s.charAt(0) + "-" + s.substring(1))
        .sorted();
//.forEach(s -> System.out.print(s + " "));

tempStream.forEach(s-> System.out.print(s + " "));
System.out.println("\n----------------------------------");
```

I'll assign the result of the pipeline to a local variable, 
just using _var_ for the type, 
and name the variable _tempStream_.
Next, I'll use that variable 
to call the _forEach_ operation on, 
in a separate statement, 
passing it the same lambda as before.
This all compiles, and if I run it:

```html  
G54
N43
N41
G50
G48
N36
G46
O62
G57
G53
O70
I25
G51
O71
O64
----------------------------------
G-46 G-48 G-50 G-51 G-53 G-54 G-57 O-62 O-64 O-70 O-71
----------------------------------
```

Now I get a result. 
You don't have to build your pipeline all in one piece. 
It's important to understand, though, 
that unlike the chaining of method calls on other types, 
these operations don't get executed until _terminal_ operation.

```java  
//tempStream.forEach(s-> System.out.print(s + " "));
tempStream.forEach(s-> System.out.print(s.toLowerCase() + " "));
```

Ok, now let's say I want to use my stream, 
to do something else. 
Maybe next, I want to print things out in lowercase.
I'll copy _terminal_ operation
and paste it at the end of my method. 
I'll change the _s_ that gets printed out 
to `s.toLowerCase`. 
I have a warning from IntelliJ. 
If I hover over that, the message says that, 
_Stream has already been linked or consumed_. 
What's that all about? 
You may be wondering. 
I'll try to run this anyway, 
since it's just a warning.

```html  
Exception in thread "main" java.lang.IllegalStateException: stream has already been operated or closed
```

But, now I do get an error, 
this one says the stream has already been operated upon or closed. 
Let me comment that last statement first. 
Once you invoke a _terminal_ operation on a stream, 
you can think of the pipeline as being opened, 
and the flow beginning. 
The flow is allowed to continue 
until all processes have been performed 
and a result produced. 
At that point, the valve is shut, 
and the pipeline closed. 
You can't turn it back on 
or reuse it for a new source. 
If you want to do the same sort of thing 
with a different variable 
for one of the _intermediate_ operations, 
you'd need to set up a new pipeline. 
Now that you have a bit of understanding 
about the stream pipeline, 
let's review different ways 
to create sources for your streams. 
</div>

## [c. Stream Sources]()
<div align="justify">

In this section, 
I'm going to continue 
with the same code I used in the last sections. 
In the example I worked with previously, 
the source for my stream was a type of collection, an **ArrayList**. 
Because Lists and Sets implement the **Collection** interface, 
the _stream_ method is available from any type 
that implements those, so **ArrayList**, **LinkedList**,
**HashSet**, **TreeSet**, and so on.

```java  
String[] strings = {"One", "Two", "Three"};
var firstStream = Arrays.stream(strings)
        .sorted(Comparator.reverseOrder());
        //.forEach(System.out::println);
```

Next, let's look at using an array as a source. 
I'll just set up a **Strings** array 
with a simple array initializer with three string literals, 
_One_, _Two_ and _Three_. 
I can use another helper method on the `java.util.Arrays` class, 
called _stream_, to generate a stream of elements from an array. 
I'll call that method, passing it my array of _strings_. 
I'll use sorted, as an _intermediate_ operation, 
and pass that, the _reverseOrder_ comparator. 
The _terminal_ operation is _forEach_, 
and I'll use a method reference 
you're familiar with by now. 
Running that code:

```html  
Two
Three
One
```

I get the _strings_ printed out in reverse alphabetical order,
so in this case I get _Two_, _Three_, _One_.

```java  
var secondStream = Stream.of("Six", "Five", "Four")
        .map(String::toUpperCase);
        //.forEach(System.out::println);
```

I could create a similar stream 
by using the static of method on the **Stream** interface. 
`Stream.of` takes a variable argument of any type, 
so I can simply start listing my **Strings** here, 
and here I'll put _Six_, _Five_ and _Four_. 
I'll use _map_ remember this is similar 
to replacing a value with another, 
so I'll use the method reference `String::toUpperCase`.
This is going to transform all my stream elements to uppercase. 
And I'll print each of those out, 
again executing _forEach_, to do that. 
Running this code:

```html  
Two
Three
One
SİX
FİVE
FOUR
```

I get _SIX_, _FIVE_, and _FOUR_ in uppercase.

```java  
var firstStream = Arrays.stream(strings)
    .sorted(Comparator.reverseOrder());
    //.forEach(System.out::println);

var secondStream = Stream.of("Six", "Five", "Four")
    .map(String::toUpperCase);
    //.forEach(System.out::println);


Stream.concat(secondStream, firstStream)
    .forEach(System.out::println);
```

Let's look at another static method on **Stream**, 
that's kind of fun. 
To set this up, I want to assign the two streams 
I just created, to local variables. 
First, I'll set up a local variable, 
using _var_ as the type for simplicity, 
and call that _firstStream_, 
and assign that to the stream pipeline 
I had for `Arrays.stream`. 
I'll add a semicolon after the _sorted_ operation, 
so this stream won't have a _terminal_ operation. 
I'll comment out the _forEach_ terminal operation.
Doing the same kind of thing for the next pipeline, 
I'll set up a second local variable, 
called _secondStream_, and assign it my stream pipeline, 
which I got from `Stream.of`. 
I'll add a semicolon after the _map_ operation, 
and comment out the _forEach_, 
as I did for the _firstStream_. 
I can use the concat method on Stream, 
to produce a single Stream, 
from two streams produced from different sources. 
I'll pass the second stream first, 
and then first stream.
I'll chain the _forEach_ method 
to the result of that call. 
If I run this:

```html  
SİX
FİVE
FOUR
Two
Three
One
```

I get the combined result of the two pipelines. 
The first three are in upper case, _SIX_, _FIVE_, 
then _FOUR_, and the second three are ordered 
in the reverse order they were declared. 
Think about what I did here for a minute. 
Each stream had different operations declared 
for the different sources. 
These weren't executed 
until I joined them to a single stream, 
and executed a _terminal_ operation 
on the concatenated stream. 
Each stream still performed different operations 
on each of its different source data. 
Once merged, any operations after the _concat_ operation 
were performed on the entire stream. 
Next, I'll add an intermediate operation to that last pipeline, 
so that you can see this more clearly.

```java  
Stream.concat(secondStream, firstStream)
    .map(s -> s.charAt(0) + " - " + s)
    .forEach(System.out::println);
```

I'll use _map_ again, 
and return a string that is the first letter of the current string, 
a dash, and then the string.
Running that:

```html  
S - SİX
F - FİVE
F - FOUR
T - Two
T - Three
O - One
```
                    
You can see that this _map_ operation was executed on all six strings, 
but the first _map_ operation was executed only on part of the stream, 
the three elements that came from the `Stream.of` method. 
The _concat_ method lets you create a network of pipelines,
with different operations for each stream segment. 
You might remember that the **Map** interface didn't extend 
the **Collection** interface, 
and the **Map**and it's implementing classes were 
sort of in a category of their own. 
The **Map** interface doesn't have a stream method either, 
but you can use any of **Map**'s collection views, 
**keySet**, **entrySet**, or values, 
to generate a stream to process parts of the map.

```java  
Map<Character, int[]> myMap = new LinkedHashMap<>();
int bingoIndex = 1;
for (char c : "BINGO".toCharArray()) {
    int[] numbers = new int[15];
    int labelNo = bingoIndex;
    Arrays.setAll(numbers, i -> i + labelNo);
    myMap.put(c, numbers);
    bingoIndex += 15;
}

myMap.entrySet()
    .stream()
    .map(e -> e.getKey() + " has range: " + e.getValue()[0] + " - " +
        e.getValue()[e.getValue().length - 1])
    .forEach(System.out::println);
```

I'll show a quick example of that. 
First, I'll set up a map, instead of a list, 
for the bingo ball labels. 
My map will be keyed by each character in the word _Bingo_, 
and will contain an integer array for the bingo numbers. 
I'll make it a **LinkedHashMap**,
because I want it to stay in the character order 
for the word _Bingo_. 
I'll set up a local variable, _bingoIndex_, 
for the bingo numbers. 
I'll loop through the valid characters. 
I'll set up an array of 15 integers. 
I want a local variable. 
This can be an effectively final variable, 
which means I can use it in a lambda. 
I'll set that to the _bingoIndex_, 
which changes for each iteration, 
and isn't effectively final. 
I'll set my numbers using `Arrays.setAll`,
to start at the array index plus the _labelNumber_, 
which is really the _bingoIndex_. 
I'll add this array to my map,
using the corresponding character as the key.
Finally, I'll increment the bingo index by 15.
Now let's say I want to use a stream, 
to make sure my map looks good. 
I can use any of the map's set views, 
so I'll use the **entrySet**, 
and start a stream off of that. 
I'll use the map to create a string 
that prints the keyed character, 
and the first and last number in the integer array, 
mapped to that character.
And I'll print this out. 
Let me run this code:

```html  
B has range: 1 - 15
I has range: 16 - 30
N has range: 31 - 45
G has range: 46 - 60
O has range: 61 - 75
```

And you can see I get the map keys printed, 
with the range of numbers shown there.
Notice that I'm starting out with a stream of entry instances, 
which I can see as an inlay hint, 
after the call to stream there. 
After the _map_ operation though, 
I have a **Stream** of **String**. 
The _map_ operation, which I'll be covering in a lot more detail, 
lets you change the stream type.

I said earlier that an _intermediate_ operation can 
usually be recognized by its signature, 
because it returns a stream. 
I want to point out that 
this doesn't mean the element type of the stream can't change. 
In this example, 
I'm starting out with a stream of `Map.Entry` instances, 
and this gets transformed to a **Stream** of **String** instances. 
I mapped an _Entry_ instance to a **String** 
for every element in the stream. 
In practice, you'll be regularly transforming 
your stream element to a different type. 
I didn't need a print the map out this way, 
there were probably simpler ways to do it. 
I did want to show you how you could use a stream pipeline, 
to do some processing on your map entries. 
In later sections, we'll do more complex processing, 
and this will get more interesting.

Ok, those are some examples of sources, 
and these all have one thing in common. 
They're finite streams, 
which means we know at the start, 
exactly how many elements we're dealing with. 
We can also use infinite streams. 
You might ask, won't infinite streams be like infinite loops, 
and grind on until you get the out of memory error? 
They do have this potential, 
but somewhere in your pipeline, 
you'll be adding a limiting operation. 
I haven't discussed 
all the _intermediate_ operations yet, 
but I've shown you some simple ones 
like _filter_, _sorted_, _map_, and _limit_. 
I'll use _limit_ in the next examples.

```java  
Random random = new Random();
Stream.generate(() -> random.nextInt(2))
    .limit(10)
    .forEach(s -> System.out.print(s + " "));
```

I want to first look at the static method 
on the interface **Stream**, named _generate_. 
This method takes a **Supplier**. 
Hopefully you'll remember that 
the **Supplier**'s functional method, 
returns a value, but doesn't take any arguments. 
I'm going to use a random generator in this next example, 
so I'll create a local variable, for a new instance of random.
Next, I'll execute generate on the **Stream** interface type, 
and pass it a lambda. 
The lambda has no arguments, 
so I need empty parentheses, 
and then I'll call `random.nextInt`, 
passing 2 as the upper bound. 
This will generate a series of 0's and 1's infinitely.
I'll limit my stream to 10 integers. 
And I'll print those out. 
Running this code:

```html  
1 1 0 0 1 0 0 1 1 0
```

You can see I get a list of 10 0's and 1's. 
Each time I run it, I'll get a different set, 
but always 10 values, because of the _limit_ operation.

```java  
System.out.println();
IntStream.iterate(1, n -> n + 1)
    .limit(20)
    .forEach(s -> System.out.print(s + " "));
```

Another static method on the **Stream** class is _iterate_ 
which gives us the option of generating either 
a finite or infinite stream. 
I'll start with an example of an infinite stream. 
The simplest form of `Stream.iterate` takes two arguments.
The first is a seed or starting value, 
and after that it's a **UnaryOperator**, 
which is a special kind of function, 
so this means you want some kind of equation here. 
Let me show you this in action. 
I'll print out a new line first. 
To start with, I'm going to use **IntStream**, 
instead of **Stream**, 
simply because I'll be dealing with integers here. 
I could have used **IntStream** 
for the pipeline above as well. 
My first parameter is the starting value, 
called a seed. 
In this case, my first value will be one. 
The second parameter is a lambda expression 
that will do something with that seed 
to get the next value. 
In this case, it's just going to add 1 to the last value. 
This code works if I use **IntStream** or **Stream**. 
If I had used **Stream** though, 
Java would have been autoboxing and unboxing integers, 
on each method call, which makes it less efficient, 
especially with large amounts of data. 
I'll limit this set of data to 20 numbers. 
And I'll print them out. 
Again, the first parameter of this method 
is the seed or starting value, 
so this code generates a stream of integers, 
starting at one. 
To figure out later numbers, 
the code will execute whatever 
I pass as the second parameter, 
which needs to be a **UnaryOperator**. 
A **UnaryOperator** is any lambda expression 
that takes a parameter of one type, 
returning a result of the same type. 
In this case, the operator needs to operate on integers, 
because that's the type of my stream values. 
The seed will be fed into the unary operator, 
this lambda expression I've declared here, 
and it will operate first on the seed, 
and then every value after that. 
Running this code:

```html  
1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
```

I get the numbers 1 through 20 printed out. 
Now, let's make this example more interesting.

```java  
public static boolean isPrime(int wholeNumber) {

    if (wholeNumber <= 2) {
        return (wholeNumber == 2);
    }

    for (int divisor = 2; divisor < wholeNumber; divisor++) {
        if (wholeNumber % divisor == 0) {
            return false;
        }
    }

    return true;
}
```

I'll add a method you've seen before, 
the _isPrime_ method from a previous section. 
I'll just paste this in, as a method on my **Main** class. 

```java  
System.out.println();
IntStream.iterate(1, n -> n + 1)
    .filter(Main::isPrime)
    .limit(20)
    .forEach(s -> System.out.print(s + " "));
```

I'll use this method, 
and my infinite stream to get the first 20 prime numbers. 
Getting back to my stream pipeline, 
I'm going to add a filter 
to check if the number is prime. 
I'm going to add this 
before the _limit_ operation. 
I don't really know how many integers it will take 
to get the first 20 prime numbers, 
and with this code I really don't need to know. 
I can use a method reference on a static method, 
and that's what I'll do here, with `Main::isPrime`. 
Ok, let's run this:

```html  
2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71
```

And now you can see the first 20 primes, 
and it took 71 integers to get to that requirement. 
If I want all the prime numbers less than 100, 
I can reverse my intermediate operations. 
I'll do this next.

```java  
System.out.println();
IntStream.iterate(1, n -> n + 1)
    .limit(100)
    .filter(Main::isPrime)
    .forEach(s -> System.out.print(s + " "));
```

I'll copy the statements from the codes above, 
and paste a copy. 
For this pasted code,
I'll cut the _limit_ operation, 
and paste it above the _filter_ operation. 
I'll change 20 to 100 in that _limit_ operation,
so it will process the first 100 integers, 
but filter those that are prime numbers. 
Running this:

```html  
2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71 73 79 83 89 97
```

I now get the prime numbers that are less than or equal to 100. 
Consider the differences 
between these two pipelines for a minute. 
In the first case, 
numbers can be fed indefinitely into the streaming process, 
until enough numbers match the condition first, 
and then match the limiting size. 
In the second case, though, 
I'm saying that I only want 
to check a specified number of elements, 
and will operate on that specific size. 
Because streams are lazy, 
the stream process isn't going to generate 
an infinite number of possibilities first 
and then process the other operations. 
In other words, 
it's not going to hang on the stream creation. 
The evaluation stage I talked about earlier, 
that optimizes stream operations, 
will manage how many elements are actually 
created in the stream,
to provide the desired result.
However, let me add a word of caution. 
If you don't provide a limiting factor, 
you can still produce the out of memory condition. 
The iterate method has an overloaded version, 
which provides a finite stream, 
because it's conditional.

```java  
System.out.println();
IntStream.iterate(1, n -> n <= 100, n -> n + 1)
    .filter(Main::isPrime)
    .forEach(s -> System.out.print(s + " "));
```

I'll copy my last pipeline code, 
and paste a copy of that just below. 
The overloaded _iterate_ method 
has three parameters, 
the first is still the seed, 
but the second parameter, 
the additional parameter, 
is a **predicate** functional interface type. 
The third parameter is the **UnaryOperator**. 
I'll change this code, 
and insert a lambda expression 
that takes a number, so _n_ again, 
and will return a boolean. 
I want to check if _n_ is less than 
or equal to 100. 
After this, I'll remove the _limit_ operation.
Running this new pipeline:

```html  
2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71 73 79 83 89 97
```

I get the exact same result 
as the previous pipeline.
The first uses an infinite stream, 
limited by an _intermediate_ operation. 
The second uses a finite stream, 
it's limiting condition declared 
as the second parameter. 
There are two additional methods, 
which I can use on any of the primitive streams, 
so on **IntStream** like I have here, 
or with **DoubleStream** or **LongStream**. 
These are _range_ and _rangeClosed_.

```java  
System.out.println();
//IntStream.range(1, 100)
IntStream.rangeClosed(1, 100)
    .filter(Main::isPrime)
    .forEach(s -> System.out.print(s + " "));
```

I'm going to take that last bit of code 
and paste a copy just below. 
I'll replace _iterate_ with _range_, 
which takes a starting and ending value, 
and automatically increments by one. 
I'll replace the parameters with just 1 and 100.
Running this code, 
I'll get the same data as the other two methods. 
This 100 is the upper bound, and it's exclusive,
meaning this stream is really returning numbers from 1 to 99. 
If I wanted the full range, 
I could replace range with _rangeClosed_, 
and I'll do that next.
I'll re-run it now. 
In this case, it doesn't change the results, 
but you should recognize the difference between the two. 
The _rangeClosed_ method in this example, 
produces numbers from 1, up to and including 100.
Let me show you these stream creation methods on a summary table.

| Method                                                                           | Finite              | Infinite                  |
|----------------------------------------------------------------------------------|---------------------|---------------------------|
| &nbsp;&nbsp;Collection.stream()                                                  | &nbsp;&nbsp;&nbsp;X |                           |
| &nbsp;&nbsp;Arrays.stream((T[])                                                  | &nbsp;&nbsp;&nbsp;X |                           |
| &nbsp;&nbsp;Stream.of(T...)                                                      | &nbsp;&nbsp;&nbsp;X |                           |
| &nbsp;&nbsp;Stream.iterate(T seed, UnaryOperator<T> f)                           | &nbsp;&nbsp;&nbsp;X |                           |
| &nbsp;&nbsp;Stream.iterate(T seed, Predicate<? super T> p, UnaryOperator<T> f)   | &nbsp;&nbsp;&nbsp;X |                           |
| &nbsp;&nbsp;Stream.generate(Supplier<? extends T> s)                             |                     | &nbsp;&nbsp;&nbsp;&nbsp;X |
| **IntStream.range(int startInclusive, int endExclusive)                          | &nbsp;&nbsp;&nbsp;X |                           |
| **IntStream.rangeClosed(int startInclusive, int endExclusive)                    | &nbsp;&nbsp;&nbsp;X |                           |

This table shows the eight methods 
I covered in this section. 
Two can produce infinite streams,
the `Stream.generate` method as well as 
`Stream.iterate`, which doesn't include 
a **Predicate** parameter. 
There are other factory methods that
return empty and single element streams. 
I'll discuss these in a later context, 
because at this stage, 
it probably wouldn't make sense 
why you'd want to use these. 
</div>

## [d. Stream Sources Challenge](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_13_Streams/Course04_StreamSourcesChallenge/README.md#stream-sources-challenge)
<div align="justify">

In the last section, 
I reviewed the following methods 
to generate a **Stream**. 
In this coding challenge, 
you'll be using most of these methods 
to set up a few streams of your own. 

For this challenge, 
I want you to generate the bingo ball labels as five different streams, 
using different stream creation methods for each. 
Assign each pipeline to a stream variable.
Concatenate the five streams together. 
Apply the terminal operation, _forEach_, 
to the final concatenated stream, to print each label. 
These should be printed in order as follows. 
B1-through B15, 
I16 to I30, 
N31 through N45, 
G45 to G60, finally, 
O61 through O75. 
Remember that the _map_ intermediate operation 
takes a **Function**, 
so you can return a different type, 
than the input stream element. 
In other words, 
if you start with a **Stream** containing **Integer**, 
you can finish the pipeline 
with a **Stream** containing **String** elements. 
To do this, you'd use _map_ to return a **String**, 
by executing a method 
or expression that takes an integer, and returns a string. 
The generate method may be difficult to use, 
without creating side effects, 
or using other intermediate operations I haven't yet mentioned, 
but if you want a good challenge, you can play around with this one.
</div>

## [e. Intermediate Operations]()
<div align="justify">

Up until now, I've kind of glossed over intermediate operations. 
For one thing, most aren't that complicated 
or challenging to understand. 
I've used _filter_, _limit_, _map_ and _sorted_ in my examples. 
I also showed you a quick example of using 
_distinct_ in the last challenge.

| Return Type | Operation                                                                                                                            |
|-------------|--------------------------------------------------------------------------------------------------------------------------------------|
| Stream<T>   | distinct()                                                                                                                           |
| Stream<T>   | filter(Predicate<? super T> predicate)<br/> takeWhile(Predicate<? super T> predicate)<br/> dropWhile(Predicate<? super T> predicate) |
| Stream<T>   | limit(long maxSize)                                                                                                                  |
| Stream<T>   | map(Function<? super T, ? extends R> mapper)                                                                                         |
| Stream<T>   | peek(Consumer<? super T> action)                                                                                                     |
| Stream<T>   | skip(long n)                                                                                                                         |
| Stream<T>   | sorted()<br/> sorted(Comparator<? super T> comparator)                                                                               |

As you can see from this table, 
the operations you've already seen briefly 
cover half of the basic operations available
to your stream pipelines.

| Return Type | Operation                                                                                                                           | Description                                                                                                                                                                                                                                                                        |
|-------------|-------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Stream<T>   | distinct()                                                                                                                          | Removes duplicate values from the Stream.                                                                                                                                                                                                                                          |
| Stream<T>   | filter(Predicate<? super T> predicate<br/> takeWhile(Predicate<? super T> predicate)<br/> dropWhile(Predicate<? super T> predicate) | These methods allow you to reduce the elements in the output stream.<br/> Elements that match the filter's Predicate are kept in the outgoing stream, for the filter and takeWhile operations.<br/> Elements will be dropped until or while the dropWhile's predicate is not true. |
| Stream<T>   | limit(long maxSize)                                                                                                                 | This reduces your stream to the size specified in the argument.                                                                                                                                                                                                                    |
| Stream<T>   | skip(long n)                                                                                                                        | This method skips elements, meaning they won't be part of the resulting stream.                                                                                                                                                                                                    |

I'll start by talking about the set of operations
that may change the number of elements in the resulting stream.
In this group, the first is the _distinct_ operation, 
which removes any duplicate values. 
This operation depends on the stream type's equals method, 
to determine the uniqueness of a stream element. 
Next, there is _filter_ which takes a conditional statement 
that evaluates to true or false, 
usually based on some attribute of the stream element. 
Elements that match an operation's _predicate_, 
meaning the condition returns true for that element, 
are kept in the outgoing stream, 
for both the _filter_ and _takeWhile_ operations.
In contrast, elements will be dropped from the stream, 
until or while the operation, 
_dropWhile_'s predicate is not true. 
You've already seen the _limit_ operation in action. 
This defines the exact size of the stream, 
cutting off the stream elements 
when this size has been reached. 
This operation usually turns infinite streams into finite streams. 
The _skip_ method allows you to skip a certain number of elements,
meaning they won't be in the outgoing stream. 
Let's see what we can do with some of these in code.

```java  
IntStream.iterate((int) 'A', i -> i <= (int) 'z', i -> i + 1)
    .forEach(d -> System.out.printf("%c ", d));

System.out.println();
```

I'm going to start with the _ABC_'s. 
I'll use `IntStream.iterate`, 
casting the capital letter _A_ 
to an integer value.
This lets me do integer math easier on my stream. 
This also means my Stream is made up of integer primitives. 
I'll use the three parameter version of _iterate_, 
so my second argument is a predicate lambda. 
I want to continue to produce integers for the stream, 
until I get to the integer value of a lowercase _z_. 
To do this, I'll make this condition test 
that the value of _i_ is less than or equal 
to whatever the integer value of lowercase _z_ might be. 
Finally, the last argument is a **UnaryOperator**, 
and that's just going to increment 
each subsequent value by one. 
I'm going to get integers representing any characters 
between uppercase _A_ and lowercase _z_. 
I'll print that out, using _printf_. 
I can pass an int to a character specifier, 
as I show here. 
Here is another example of a pipeline, 
that has no _intermediate_ operations.
If I run this:

```html  
A B C D E F G H I J K L M N O P Q R S T U V W X Y Z [ \ ] ^ _ ` a b c d e f g h i j k l m n o p q r s t u v w x y z
```

You can see, I get a couple of characters 
that aren't alphabetical characters.

```java  
IntStream.iterate((int) 'A', i -> i <= (int) 'z', i -> i + 1)
    .filter(Character::isAlphabetic)
    .forEach(d -> System.out.printf("%c ", d));

System.out.println();
```

I'll filter those out. 
The Character wrapper provides a method, 
called _isAlphabetic_, 
that takes an integer, 
and returns a boolean, 
so this is a perfect opportunity 
for a method reference. 
Running this:

```html  
A B C D E F G H I J K L M N O P Q R S T U V W X Y Z a b c d e f g h i j k l m n o p q r s t u v w x y z
```

Gives us just the uppercase and lowercase alphabet now. 
Let's say that next:

```java  
IntStream.iterate((int) 'A', i -> i <= (int) 'z', i -> i + 1)
    .filter(Character::isAlphabetic)
    .dropWhile(i -> Character.toUpperCase(i) <= 'E')
    .forEach(d -> System.out.printf("%c ", d));

System.out.println();
```

I don't want any letters, from _A_ through _E_. 
This can be done with an additional _filter_ 
I can add the code to my first lambda expression, 
or I can include an additional _filter_ operation, 
which is what I'll do here. 
In this case, I can use another method 
on the **Character** wrapper class, to uppercase, 
that takes an integer, 
and compare that to the character, a literal _E_. 
If the value is greater than _E_, 
it will be part of the output stream. 
There's really no limit 
to the number of _intermediate_ operations you specify, 
and you can use one operation many times, 
and in any order you want. 
Again, these operations are a specification to the stream processor. 
The processor may decide it's more efficient 
to join those two conditions together, 
in the black box of the stream process. 
This is why we have to stop thinking of these operations as methods, 
because in some cases, 
they'll never be used in the way we define them, 
but will be optimized by the stream processing code. 
Running this code now:

```html  
F G H I J K L M N O P Q R S T U V W X Y Z a b c d e f g h i j k l m n o p q r s t u v w x y z
```

you can see I get the result I wanted, 
regardless of the way the processor decided 
to get that work done.

```java  
IntStream.iterate((int) 'A', i -> i <= (int) 'z', i -> i + 1)
    .filter(Character::isAlphabetic)
    .skip(5)
    .forEach(d -> System.out.printf("%c ", d));

System.out.println();
```

If I only wanted to skip the capital letters, _a_, 
through _e_, I could use the _skip_ method. 
I'll comment out that second _filter_, to show you that. 
Next I'll add skip as the second operation, skipping 5. 
Running this:

```html  
F G H I J K L M N O P Q R S T U V W X Y Z a b c d e f g h i j k l m n o p q r s t u v w x y z
F G H I J K L M N O P Q R S T U V W X Y Z a b c d e f g h i j k l m n o p q r s t u v w x y z
```

You can see this operation did exactly that, 
it skipped the first five generated elements, 
and my letters start with a capital _F_. 
You might ask why you'd do this, 
and this probably isn't a very good example, 
since I could just change my iterate method to do this.

```java  
IntStream.iterate((int) 'A', i -> i <= (int) 'z', i -> i + 1)
    .filter(Character::isAlphabetic)
    .dropWhile(i -> i <= 'E')
    .forEach(d -> System.out.printf("%c ", d));

System.out.println();
```

You'll definitely encounter situations though, 
when you want to skip elements in a stream. 
There are two other methods 
that let you fine tune what gets skipped 
or what gets included, _takeWhile_ and _dropWhile_. 
I'll remove out the skip 5 statement, 
and next include a _dropWhile_ statement 
that will do the same thing. 
This operation takes a predicate 
and will filter out any elements 
while they match this condition. 
Unlike _filter_, this predicate is only evaluated for elements, 
until it first becomes false. 
I'll explain what I mean by that in more detail, in just a minute. 
Here, I'll drop any characters 
that are less than or equal to a capital letter _E_. 
If I run this:

```html  
F G H I J K L M N O P Q R S T U V W X Y Z a b c d e f g h i j k l m n o p q r s t u v w x y z
```

I get the same output as with skip 5, 
but this method gives you a lot more control. 
As I said, this will continue 
to exclude elements until this predicate turns false.

```java  
IntStream.iterate((int) 'A', i -> i <= (int) 'z', i -> i + 1)
    .filter(Character::isAlphabetic)
    .dropWhile(i -> Character.toUpperCase(i) <= 'E')
    .forEach(d -> System.out.printf("%c ", d));

System.out.println();
```

I'll change my predicate to ignore the case 
by making the character uppercase before I test it. 
Running this:

```html  
F G H I J K L M N O P Q R S T U V W X Y Z a b c d e f g h i j k l m n o p q r s t u v w x y z
```

The output didn't change. 
Did you expect the letters, _a_ through _e_, 
in lowercase to be dropped? 
Well, that's where the word 
while in the operation name becomes important. 
This happens until the predicate becomes false the first time,
and then that condition is no longer checked. 
Think of this as a mini while loop. 
It'll drop data until the condition becomes false, 
and then it moves past the loop altogether. 
Its job in the pipeline is complete, and won't be revisited.
If you combine this with a take while operation, 
you can effectively describe a range of elements you want.

```java  
IntStream.iterate((int) 'A', i -> i <= (int) 'z', i -> i + 1)
    .filter(Character::isAlphabetic)
    .dropWhile(i -> Character.toUpperCase(i) <= 'E')
    .takeWhile(i -> i < 'a')
    .forEach(d -> System.out.printf("%c ", d));

System.out.println();
```

In this case, I'll take elements 
while they are less than a lowercase _a_. 
Running this:

```html  
F G H I J K L M N O P Q R S T U V W X Y Z
```

You can see I get letters _F_ through _Z_, in uppercase. 
The _dropWhile_ and _takeWhile_ work well with ordered streams. 
If the stream isn't ordered, 
oracle tells us the result will be non-deterministic, 
meaning the results may differ upon subsequent executions. 
Finally, there's the _distinct_ operation.

```java  
IntStream.iterate((int) 'A', i -> i <= (int) 'z', i -> i + 1)
    .filter(Character::isAlphabetic)
    .map(Character::toUpperCase)
    .distinct()
    .forEach(d -> System.out.printf("%c ", d));

System.out.println();
```

First, I'll comment out the _dropWhile_ and _takeWhile_ operations. 
I'll use a simple _map_ function, 
to map all elements to an upper case letter, 
then I'll insert the _distinct_ operation after that. 
Again, this is probably not the most logical piece of code, 
but it does demonstrate how _distinct_ works. 
I'll run this 

```html  
A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
```

And confirm I only get _A_ to _Z_ here.
For another example, 
I'll generate some random letters instead. 
First, I'll print out a new line to separate
the output. 

```java  
System.out.println();
Random random = new Random();

Stream.generate(() -> random.nextInt((int)'A', (int)'Z' + 1))
    .limit(50)
    .distinct()
    .sorted()
    .forEach(d -> System.out.printf("%c ", d));

System.out.println();
```

For another example, 
I'll generate some random letters instead. 
First, I'll print out a new line to separate the output. 
I'll set up a local variable, _random_, 
a new instance of the **Random** class. 
I'll create a **Stream** source.
I'm going to use **Stream** 
this time instead of **IntStream**, 
so that you can see I can use **Stream** 
with integers somewhat seamlessly. 
Java will do all the auto boxing and unboxing required. 
I'll execute `Stream.generate`, 
which takes a supplier, so it has no arguments, 
but returns a value. 
In this case, I want to return 
the result of the `random.nextInt` method.
I like to just pass the characters, 
casting them to ints, so it's clearer 
that I'm focusing on character values. 
Since this is a random generator, on an infinite stream, 
I'm going to set a limit of 50. 
Now I'll call `distinct`. 
I'll then sort my integers. 
And I'll print them out.

```html  
A B D E G H J K L M N O Q R S T U V X Y Z
```

I chose 50 to limit the stream 
because I don't want to actually get 
a full set of characters each time. 
Using fifty as the limit means 
the probability of producing a nearly 
full alphabetical set, is less than likely. 
Running this multiple times, 
you'll see that I get most of the letters 
when I use 50, as the limiting number. 
I won't usually get a full alphabet.

```java  
Stream.generate(() -> random.nextInt((int)'A', (int)'Z' + 1))
    .limit(50)
    .sorted()
    .forEach(d -> System.out.printf("%c ", d));
```

If I remove `distinct`, 
I'll see every letter generated, but in order. 
Running that:

```html  
A A B C C C D D D E E E F F H I I J J K K L L L L M M M M M N O O P P R T T T T T T U W W X X Y Y Z
```

You can see the results without `distinct`. 
If these pipelines are starting 
to feel like database queries to you, 
that's for good reason. 
The Java API designers designed the **Stream** 
to let you process data in a declarative way, 
much like a structured query language or SQL in a database. 
Again, this lets you say _what should happen_, 
and _not actually how it will happen_. 
If you've had experience querying databases, 
you might be familiar with 
the _limit_ and _distinct_ keywords,
available in some database query languages. 
The _filter_ operation represents your where clause, 
and sorted would be your order by clause, and so on. 
When we get to _terminal_ operations, 
you'll see there are aggregate functions commonly
used in queries as well, 
such as _max_, _min_, _count_ and so on. 
</div>

### Intermediate Operations that operate on every element
<div align="justify">

| Return Type | Operation                                             | Description                                                                                                                                                                                                                           |
|-------------|-------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Stream<R>   | map(Function<? super T, ? extends R> mapper)          | This is a function applied to every element in the stream. Because it's a function, the return type can be different, which has the effect of transforming the stream to a different stream of different types.                       |
| Stream<T>   | peek(Consumer<? super T> action)                      | This function does not change the stream, but allows you to perform some interim consumer function while pipeline is processing.                                                                                                      |
| Stream<T>   | sorted()<br/>sorted(Comparator<? super T> comparator) | There are two versions of sorted.<br/> The first uses the naturalOrder sort, which means elements in the stream must implement Comparable.<br/>If your element don't use Comparable, you'll want to use sorted and pass a Comparator. |

In the last two examples of stream pipelines, 
I used _map_ and _sorted_. 
It's kind of hard not to use these 
in many cases.
Much of what you want to do in a stream processing 
pipeline is to _filter_, _transform_, and _sort_. 
The _map_ function is both straightforward and powerful. 
It can turn one stream into another kind,
and since we can use as many _map_ operations, 
as we want, we could do a lot with this one operation. 
It can be as complex or simple as you want it to be as well.

The _peek_ method is aptly named 
because it's commonly used to print elements in interim operations, 
so you can get an understanding of what is really happening. 
Of course, you're not limited to just printing output. 
This operation is susceptible to intentional, 
or unintentional, side effects.

Finally, there's the sorted operation. 
We've covered sorted a lot with collections, 
and this operation is similarly structured 
to the sort method on implemented collections. 
You can use it without specifying an argument 
for elements that implement the **Comparable** interface. 
If you want a different kind of sort, 
or the elements in your stream don't implement **Comparable**, 
you'd pass a Comparator. 
The sorted method gets complicated when you use parallel streams, 
but I'll cover those complexities in the **concurrency** section of the course.

I want to examine the _map_ operation in a little more detail. 
First, though, I'm going to create a **Seat** record for the examples 
I'll be using in this section.

```java  
public record Seat(char rowMarker, int seatNumber, double price) {

    public Seat(char rowMarker, int seatNumber) {
        this(rowMarker, seatNumber, rowMarker > 'C' && (seatNumber <= 2 || seatNumber >= 9) ? 50 : 75);
    }

    @Override
    public String toString() {
        return "%c%03d %.0f".formatted(rowMarker, seatNumber, price);
    }
}
```

You may remember in previous sections, 
I created collections of theater **Seats**. 
I want to do something similar 
to demonstrate some of the stream operations. 
I'll create a new Java class, a record, 
and just call that **Seat**. 
It will have the fields _rowMarker_, _seatNumber_, 
and
a _price_ of the seat. 
_RowMarker_ will be a letter, 
_seatNumber_ will be an int,
and _price_ will be a double. 
I'll generate a custom constructor, 
and for that, I just want the first two fields,
_rowMarker_, and _seatNumber_. 
I'll change this code. 
First, I want to insert the _seatNumber_, 
before the zero. 
Next, I'll replace 0 with a ternary operator, 
to set up a pricing rule for seats. 
All seats will be $75, 
but I'll have the outer seats, 
seats 1 and 2, and seats 9 and 10 be discounted, 
after row _C_. 
I'll check that _rowMarker_ is greater 
than a literal _C_ character. 
I'll use a conditional _and_, 
and next check the seat number values.
That's it for the constructor.

Next, I want a _toString_ method, 
and I'll generate that with _ALT+INSERT_, 
and _Select None_ for fields. 
I'm going to return a formatted **String**. 
The seat number will be in the format, of _B001_, 
for example, so I'll print the row marker
with a character specifier. 
That'll be followed by the seat number 
with an integer specifier, with a width of 3, 
but I want leading zeros, so I make that zero 3. 
Finally, the price has a float identifier. 
I'll ignore any decimal value when this is printed, 
so I make that `.0f`. 
Ok, that's a pretty simple record. 
This pricing mechanism is a bit flawed,
since my seat numbers may not always be from 1 to 10 
under some circumstances, but it's good enough for this test.
Getting back to the **Main** class and the _main_ method,

```java  
public class Main {

    public static void main(String[] args) {

        System.out.println();
        int maxSeats = 100;
        int seatsInRow = 10;
        var stream = Stream.iterate(0, i -> i < maxSeats, i -> i + 1)
                .map(i -> new Seat((char) ('A' + i / seatsInRow), i % seatsInRow + 1));

        stream.forEach(System.out::println);
    }
}
```

I'll first print a new line. 
I'll set _maxSeats_ to 100, 
and _seatsInRow_ to 10. 
I'm going to create a stream variable,
using _var_ as the type for simplicity. 
I'll use `Stream.iterate`, starting with zero. 
I want to generate numbers up to the
_maxSeats_ value, which I made 100. 
The third parameter is a function lambda, 
and it just increments by one. 
These parameters kind of remind 
you of a traditional for loop, don't they? 
Here, I'm going to return new instances of the
**Seat** record, and instead of using _generate_, 
I'm doing this with _iterate_, 
saying how many seats I want. 
Then I'm going to use _map_, 
to return a new **Seat** record. 
I can figure out the row's character,
by using the integer on the stream,
and dividing that by the seats in this row. 
For example, _i_ divided by _seatsInRow_, 
will be 0 for the first 10 seats,
so they'll be in row _A_, 
it'll be 1 from the next 10 seats, 
so they'll all be _B_, and so on. 
Next, I need the seat number.
I don't want to use the stream's integer value, 
because each seat number is numbered from 1 to 10. 
Again, I can figure this out with a simple math equation. 
I can use the modulus of _i_ with _seatsInRow_ and add one. 
When _i_ is 0 or 10, or 20, the seat number is 1, 
the first seat in the row. 
When _i_ is 9, or 19, the modulus returns 9, 
and adding 1 makes this 10, which is the last seat in the row. 
Finally, I'll print these records out. 
Ok, so I purposely wanted my terminal
operation, as a separate statement. 
This lets me demonstrate a couple of things using IntelliJ's inlay hints. 
You can see my local stream variable's type 
has been inferred to be, a `Stream<Seat>` there. 
In the code I'm showing you here, 
I start out with a `Stream<Integer>`, 
which then becomes a `Stream<Seat>`. 
If I run this:

```html  
A001 75
A002 75
.
.
I008 75
I009 50
I010 50
J001 50
J002 50
J003 75
.
J008 75
J009 50
J010 50
```

I can see all my seats printed. 
Most of the seats are priced at $75, 
but after row C, you can start seeing discount seats, 
the seats that have a seat number of _001_, or _002_, 
as well as those that are _009_, or _010_.

```java  
var stream1 = Stream.iterate(0, i -> i < maxSeats, i -> i + 1)
                .map(i -> new Seat((char) ('A' + i / seatsInRow), i % seatsInRow + 1))
                .map(Seat::toString);

stream1.forEach(System.out::println);
```

Now, I'll do another _map_, 
and put that after the first one, 
in this pipeline. 
I'll remove the closing semicolon.
I'll add another _map_ operation, 
and return a method reference, `Seat::toString`. 
This will return a **String**. 
I can confirm this by examining 
the inlay hint for my stream variable again. 
This always shows me the final stream type. 
IntelliJ has an inlay hint now, 
after the first _map_ operation, 
which tells me that at that point 
my **Stream** had **Seat** elements in it. 
Also notice that after the _iterate_ method, 
it's showing me that I started with a `Stream<Integer>`. 
I'll change this map to return a stream of the prices, 
so I'll just change _toString_, to simply price. 

```java  
var stream1 = Stream.iterate(0, i -> i < maxSeats, i -> i + 1)
                .map(i -> new Seat((char) ('A' + i / seatsInRow), i % seatsInRow + 1))
                //.map(Seat::toString)
                .map(Seat::price);

stream1.forEach(System.out::println);
```

IntelliJ tells me the result
is a Stream<Double>, 
or double wrapper instances. 
In addition to **Streams** 
that take any type, 
I've already demonstrated
that Java has primitive streams for ints, 
doubles and longs. 
I can switch to these in a pipeline 
that started out as a **Stream**. 
To do this, I can use specialized _map_ functions, 
and I'll show you this next. 

```java  
var stream1 = Stream.iterate(0, i -> i < maxSeats, i -> i + 1)
        .map(i -> new Seat((char) ('A' + i / seatsInRow), i % seatsInRow + 1))
        //.map(Seat::toString)
        //.map(Seat::price);
        .mapToDouble(Seat::price);

stream1.forEach(System.out::println);
```

In this case, I want to
use an operation called _mapToDouble_, 
rather than _map_. 
Now look at the resulting stream type. 
It's not a `Stream<Double>`, 
but a new Stream type, a _DoubleStream_. 
This is a special stream 
that handles primitive doubles. 
It can be a lot more efficient 
to process a large number of doubles 
with this type of stream, 
rather than incur the overhead 
or additional memory required 
when operating on a double wrapper. 
I'll add another _map_ operation. 

```java  
var stream1 = Stream.iterate(0, i -> i < maxSeats, i -> i + 1)
        .map(i -> new Seat((char) ('A' + i / seatsInRow), i % seatsInRow + 1))
        //.map(Seat::toString)
        //.map(Seat::price);
        //.mapToDouble(Seat::price);
        .map("%.2f"::formatted);

stream1.forEach(System.out::println);
```

First, I'll again remove the last semicolon. 
Now I'll include an additional _map_ operation. 
This time I want the price, 
but as a formatted string. 
I'll have a formatted float specifier, 
allowing for two decimals, 
and use the formatted method. 
I can do this with a method reference too. 
When using stream pipelines, 
often times the method references are easier to read, 
to get a gist of what is happening. 
IntelliJ is flagging this _map_ operation as an error. 
Hovering over that, 
I get the message bad return type in method reference. 
Cannot convert `java.lang.String` to double. 
When you use a _DoubleStream_, 
all your _map_ operations have to return a double. 
To map to something other than a double, 
I'll have to use another specialty _map_ operation, 
called _mapToObj_. 

```java  
var stream1 = Stream.iterate(0, i -> i < maxSeats, i -> i + 1)
        .map(i -> new Seat((char) ('A' + i / seatsInRow), i % seatsInRow + 1))
        .map(Seat::toString);
        //.map(Seat::price)
        //.mapToDouble(Seat::price)
        //.map("%.2f"::formatted);
        .mapToObj("%.2f"::formatted);

stream1.forEach(System.out::println);
```

I'll replace that last _map_ operation with _mapToObj_. 
The stream inlay hint, shows the resulting stream, 
is now a Stream again, with a String type. 
Let's pause here a minute to look at a table.

<table>
  <tr>
     <th> Special Primitive Streams </th>
     <th> Mapping from Reference Type to Primitive </th>
     <th> Mapping from Primitive Stream to Reference Type </th>
  </tr>
  <tr>
     <td rowspan="2">DoubleStream</td>
     <td rowspan="2">mapToDouble(ToDoubleFunction<? super T> mapper)</td>
     <td>mapToObj(DoubleFunction<? extends U> mapper)</td>
     <tr>
         <td>boxed()</td>
     </tr>
  </tr>
  <tr>
     <td rowspan="2">IntStream</td>
     <td rowspan="2">mapToInt(ToIntFunction<? super T> mapper)</td>
     <td>mapToObj(IntFunction<? extends U> mapper)</td>
     <tr>
         <td>boxed()</td>
     </tr>
  </tr>
  <tr>
     <td rowspan="2">LongStream</td>
     <td rowspan="2">mapToLong(ToLongFunction<? super T> mapper)</td>
     <td>mapToObj(LongFunction<? extends U> mapper)</td>
     <tr>
         <td>boxed()</td>
     </tr>
  </tr>
</table>

In addition to the generic **Stream**, 
that lets you stream any reference type, 
Java has three primitive streams. 
These are **DoubleStream**, **IntStream**,
and **LongStream**. 
To change to a primitive stream, 
you choose a corresponding _mapTo_ operation 
as shown in the second column, on this table. 
To switch to a double stream, you'd use _mapToDouble_. 
_MapToInt_ will result in using an _IntStream_, 
and _LongStream_ comes from _mapToLong_. 
To switch back to a generically typed stream, 
you use _mapToObj_. 
Alternately, you can use `boxed()`. 
I'll show you that next.

```java  
var stream2 = Stream.iterate(0, i -> i < maxSeats, i -> i + 1)
        .map(i -> new Seat((char) ('A' + i / seatsInRow), i % seatsInRow + 1))
        .boxed()
        .mapToObj("%.2f"::formatted);

stream2.forEach(System.out::println);
```

I'll add a _boxed_ operation 
before the last mapToObj operation. 
Now, I have an error on _MapToObj_, 
and because I used _boxed_ above it, 
I can simply make that _map_ here. 

```java  
var stream2 = Stream.iterate(0, i -> i < maxSeats, i -> i + 1)
        .map(i -> new Seat((char) ('A' + i / seatsInRow), i % seatsInRow + 1))
        .boxed()
        //.map("%.2f"::formatted);
        .sorted();

stream2.forEach(System.out::println);
```

I'll switch _mapToObj_ back to _map_. 
The primitive streams have many of the same operations, 
but do include a few extra terminal operations 
specific to their numeric types. 
I'll cover these in the next section. 
I think the _sorted_ operation is pretty self-explanatory, 
but let me comment out some of these extra _map_ operations, 
and I'll give that a quick test. 
I'll add _sorted_ after the _map_. 
When I run this:

```html  
Exception in thread "main" java.lang.ClassCastException: 
class Seat cannot be cast to class java.lang.Comparable
```

I get an exception, and that's 
because my record does not implement **Comparable**. 
I can simply pass a **Comparator** here,
so I'll do that.

```java  
var stream3 = Stream.iterate(0, i -> i < maxSeats, i -> i + 1)
        .map(i -> new Seat((char) ('A' + i / seatsInRow), i % seatsInRow + 1))
        .sorted(Comparator.comparing(Seat::price).thenComparing(Seat::toString));

stream3.forEach(System.out::println);
```

I want to first sort by the lowest seat price, 
and then the seat numbers string. 
I'll use **Comparator**'s convenience methods 
to build this, so I'll start with comparing, 
passing that the method reference for **Seat** price. 
I'll chain the then _Comparing_ method to that. 
For that method, I want to pass the method reference 
for the _toString_ method. 
Running that code:

```html  
D001 50
D002 50
..
J009 50
J010 50
..
A001 75
A002 75
A003 75
..
J007 75
J008 75
```

I get my lowest priced seats listed first, 
_D001_, _D002_, _D009_ and so on.
Lastly, I want to finish up this section 
with a quick look at _peek_.

```java  
var stream4 = Stream.iterate(0, i -> i < maxSeats, i -> i + 1)
        .map(i -> new Seat((char) ('A' + i / seatsInRow), i % seatsInRow + 1))
        .sorted(Comparator.comparing(Seat::price).thenComparing(Seat::toString))
        .skip(5)
        .limit(10)
        .peek(s -> System.out.println("--> " + s));

//stream4.forEach(System.out::println);
```

_Peek_ takes a **Consumer**, 
and therefore its purpose is not 
to have any side effects on stream members. 
In fact, the Java docs state 
that _peek_ mainly exists to support debugging, 
to see elements, as they flow past a certain point in a pipeline. 
First, I'll comment out the last statement, 
meaning the _forEach_ statement. 
I'll use _peek_, but for this example, 
I just want to do it on a limited set, 
so I'll skip the first 5 
and limit the output to just 10,
and now I'll include _peek_, 
printing out each generated record. 
Running this code:

```html  

```

I don't get any output at all. 
This is a reminder 
that _peek_ is still an intermediate operation too, 
and because of lazy streams, 
it won't process until I include a terminal operation. 
I'll uncomment that last line of code in this method. 
Running this:

```html  
--> E002 50
E002 50
--> E009 50
E009 50
--> E010 50
E010 50
--> F001 50
F001 50
--> F002 50
F002 50
--> F009 50
F009 50
--> F010 50
F010 50
--> G001 50
G001 50
--> G002 50
G002 50
--> G009 50
G009 50
```

I can see the output from the _peek_ operation, 
which includes the arrow. 
You wouldn't probably use _peek_ in a stream pipeline 
if your terminal operation is _forEach_ with a _println_ like this. 
There are terminal operations that aggregate data 
and give you a single result, 
and _peek_ is often valuable in those circumstances, 
to try to understand the stream pipeline process. 
Ok, there are a couple of others,
but I'll come back to those after I review terminal operations.
In the next section, I'll cover these. 
You've seen _forEach_, but there are many others.
</div>

## [f. Terminal Operations]()
<div align="justify">

We've covered a lot of ground in this section, 
using just one terminal operation, _forEach_. 
Now it's time to see why stream processes 
are such a welcome feature, 
as I show you other terminal operations 
we can use.

| Matching and Searching | Transformations and Type Reductions | Statistical (Numeric) Reductions | Processing     |
|------------------------|-------------------------------------|----------------------------------|----------------|
| allMatch               | collect                             | **average**                      | forEach        |
| anyMatch               | reduce                              | count                            | forEachOrdered |
| *findAny*              | toArray                             | *max*                            |                |
| *findFirst*            | toList                              | *min*                            |                |
| noneMatch              |                                     | **sum**                          |                |
|                        |                                     | **summaryStatistics**            |                |

*Returns an Optional instance  
**Available on DoubleStreams, IntStream, LongStreams

This table has them categorized by the result. 
Some are designed to find matches, 
most of which are targets for a **Predicate** lambda expression. 
Some are designed to transform stream data into a collection, 
or some other reference type. 
Others are aggregate information, 
to count elements, or find a minimum or maximum value, 
and don't take arguments.
The primitive streams have average and sum as well, 
and a _summaryStatistics_ operation 
which gives you _count_, _min_, _max_, _average_ 
and _sum_ in one result. 
A couple of these operations return a special type, 
called an **Optional**. 
We have a section coming up on the **Optional** class. 
And we've talked about _forEach_, 
but there's also a _forEachOrdered_ operation. 
A reduction operation is a special type of terminal operation. 
Stream elements are processed 
to produce a single output. 
The result can be a primitive type, like a long, 
in the case of the count operation. 
The result can be a reference type, 
like **Optional** or one of the **Statistics** types 
I'll be covering shortly. 
It can also be any type of your choice, such as an array, 
a list, or some other type. 
I want to start by looking at some of the statistical
reduction methods. 
Let's look at some code.

```java  
public class Main {

    public static void main(String[] args) {

        var result = IntStream
                .iterate(0, i -> i <= 1000, i -> i = i + 10)
                .summaryStatistics();
        System.out.println("Result = " + result);
    }
}
```

I'll use an **IntStream** in this example. 
Like any stream, 
I can use the _iterate_ method on that, 
and I'll pass 0 as the seed. 
I'll make the condition less than equal to 1000, 
and I'll increment by 10 each time. 
I'll use the _summaryStatistics_ operation. 
I'll print the result. 
I've again used type inference, 
and you can see from IntelliJ's inlay hints 
the result is inferred to be an _IntSummaryStatistics_ type. 
I'll run this:

```html  
Result = IntSummaryStatistics{count=101, sum=50500, min=0, average=500.0, max=1000}
```

And see what I get. 
There you can see that the type gets printed out, 
so _IntSummaryStatistics_, 
with data about this stream. 
In this process, I had 101 stream elements 
the sum was 50500, the minimum value was zero. 
The average is an even 500, 
and the max value was 1000. 
That's kind of a useful operation. 

```java  
public class Main {

    public static void main(String[] args) {

        var result = IntStream
                .iterate(0, i -> i <= 1000, i -> i = i + 3)
                .summaryStatistics();
        System.out.println("Result = " + result);
    }
}
```

Let's try this code again, but this time,
I'll change the number I'm incrementing by, from 10 to 3. 
Running that:

```html  
Result = IntSummaryStatistics{count=334, sum=166833, min=0, average=499,500000, max=999}
```

We get a different set of stats, 
I have more elements, 
and the statistics are different. 
Using this operation would be a good place to start 
when you get a set of data, 
and you want to try to start to understand it. 
Remember, if you have a stream of instances, 
you can stream any data field to an int or double stream, 
using one of the map operations, 
and then use this terminal operation to give you a quick overview. 
You can also individually call the _count_, _sum_, _min_,
_max_ and _average_ terminal operations, 
but I'll show you these after I cover the **Optional** class, 
which is returned from several of these methods. 
I'll show another example of code, to evaluate leap year data, 
between the years 2000 and 2025.

```java  
public class Main {

    public static void main(String[] args) {
        

        var leapYearData = IntStream
                .iterate(2000, i -> i <= 2025, i -> i = i + 1)
                .filter(i -> i % 4 == 0)
                .peek(System.out::println)
                .summaryStatistics();
        System.out.println("Leap Year Data = " + leapYearData);
    }
}
```

I'll set up another variable, _leapYearData_. 
Again I'll use _iterate_, with 2000 as the starting year, 
and 2025 as the maximum year. 
I'll increment each next year by 1.
A leap year is evenly divisible by 4, 
so I'll use the modulus operator to filter. 
If i mod 4 is equal to zero, 
then it's evenly divisible by 4 and a leap year. 
I'll get statistics first. 
And I'll print those out If I run this:

```html  
Leap Year Data = IntSummaryStatistics{count=7, sum=14084, min=2000, average=2012,000000, max=2024}
```

I can see that there are 7 leap years between 2000 and 2025, 
with 2000 being the minimum year, 
and 2024 being the maximum.
In fact, this is a good place for the peek operation, 
because it might be nice to see what those years are.
I'll insert that after the filter operation, 
but before summary statistics. 
I'll print out the filtered numbers here.
Running that:

```html  
2000
2004
2008
2012
2016
2020
2024
Leap Year Data = IntSummaryStatistics{count=7, sum=14084, min=2000, average=2012,000000, max=2024}
```

I get the 7 leap years printed out first. 
You can see that peek is useful 
when you're doing reductions. 
It gives you a window into what is happening. 
I can use terminal operations 
to return information about the aggregated data set.

| Return Type                                                                | Terminal Operations | Stream                                    |
|----------------------------------------------------------------------------|---------------------|-------------------------------------------|
| long                                                                       | count()             | ALL                                       |
| Optional                                                                   | max()               | ALL                                       |
| Optional                                                                   | min()               | ALL                                       |
| OptionalDouble                                                             | average()           | DoubleStream<br/>IntStream<br/>LongStream |
| double<br/>int<br/>long                                                    | sum()               | DoubleStream<br/>IntStream<br/>LongStream |
| DoubleSummaryStatistics<br/>IntSummaryStatistics<br/>LongSummaryStatistics | summaryStatistics() | DoubleStream<br/>IntStream<br/>LongStream |

The methods shown on this table have no arguments. 
They all return numerical data, either directly, 
or in specialized types to hold that data. 
I'll show examples of the rest of these 
after the Optional lecture. 
Before that, I want to talk about methods 
for matching elements on a specific condition.

| Return Type | Method                                     | Description                                                          |
|-------------|--------------------------------------------|----------------------------------------------------------------------|
| boolean     | allMatch(Predicate<? super T> predicate)   | Returns true if all stream elements meet the condition specified.    |
| boolean     | anyMatch(Predicate<? super T> predicate)   | Returns true there is at least one match to the condition specified. |
| boolean     | noneMatch(Predicate<? super T> predicate)  | This operation returns true if no elements match.                    |

There are three terminal operations 
that let you get an overall sense of 
what your stream elements contain, 
based on some specified condition. 
These all return a boolean, 
and take a **Predicate** as an argument. 
You can think of these as ways
to ask true or false questions about the data set, 
the stream, as a whole. 
The _allMatch_ operation returns true 
if every stream element meets the condition specified. 
_AnyMatch_ returns true, 
if there is a single stream element meeting the condition. 
Lastly, _noneMatch_ returns true 
if no elements can be found that match the condition. 
I'm going to quickly create a **Seat** record again.

```java  
public record Seat(char rowMarker, int seatNumber, boolean isReserved) {
    public Seat(char rowMarker, int seatNumber) {
        this(rowMarker, seatNumber, new Random().nextBoolean());
        //this(rowMarker, seatNumber, false);
    }
}
```

I'll generate a custom constructor next, 
and this will just have two fields, 
_rowMarker_ and _seatNumber_. 
Instead of passing false as the _isReserved_ value, 
I want to pass a randomly generated boolean. 
I'll replace false with new `Random()`, 
and call _nextBoolean_ on that. 
Getting back to the _main_ method:

```java  
Seat[] seats = new Seat[100];
Arrays.setAll(seats, i -> new Seat((char) ('A' + i / 10), i % 10 + 1));
Arrays.asList(seats).forEach(System.out::println);
```

Instead of creating a stream, 
I'm going to first create an array of seats. 
I'll start with a new **Seat** array, 
with 100 null references. 
This seat creation code is basically the same 
as I used before, in the intermediate operations section, 
so the row marker will be _A_ through _J_ in this case, 
depending on the value of _i_. 
The seat number itself will be 1 through 10. 
I'll print those out, to confirm the data is 
what I expect it to be. 
If I run this code:

```html  
Seat[rowMarker=A, seatNumber=1, isReserved=false]
Seat[rowMarker=A, seatNumber=2, isReserved=false]
                    ...
Seat[rowMarker=J, seatNumber=9, isReserved=false]
Seat[rowMarker=J, seatNumber=10, isReserved=false]
```

I can confirm that my seat rows start with _A_, and end with _J_, 
and that the seat numbers are a number between 1 and 10.
I can also see the random nature of the seats that are reserved. 
I'm going to use this array as the source of my streams.

```java  
long reservationCount = Arrays
    .stream(seats)
    .filter(Seat::isReserved)
    .count();
System.out.println("reservationCount = " + reservationCount);
```

I want to use the _count_ operation first, 
to see how many reserved seats I have total. 
The other statistics aren't that interesting in this case. 
I'll start with a long variable, 
which is what the _count_ operation returns. 
I'll use `Arrays.stream`, 
and pass my _seats_ array to create a source. 
Next, I want to count only reserved seats, 
so I'll use `Seat::isReserved` in my _filter_ operation. 
I'll use the _count_ terminal operation. 
I'll print out the number of reserved seats. 
Running that code:

```html  
reservationCount = 54
```

I should get a different count each time I run it, 
since I'm randomly reserving these seats. 
Next, I'll demonstrate each of the matching terminal operations.

```java  
boolean hasBookings = Arrays
    .stream(seats)
    .anyMatch(Seat::isReserved);
System.out.println("hasBookings = " + hasBookings);
```

For the first, I'll create a boolean variable called _hasBookings_, 
and assign that to the result of my stream pipeline. 
The source will again be my _seats_ array. 
I don't have to filter with an intermediate operation, 
when I use these operations because the predicate is built in. 
I can call _anyMatch_, passing it the same predicate,
the method reference.
I'll print this out. 
This operation just checks to see 
if there's at least one element that's reserved.
Running this:

```html  
hasBookings = true
```

I can see that it's found some matches, 
and that _hasBookings_ equals **true**, 
which you'd expect for the randomly generated
reserved field. 
I'll copy that last segment of code and paste a copy below.

```java  
boolean fullyBooked = Arrays
    .stream(seats)
    .allMatch(Seat::isReserved);
System.out.println("fullyBooked = " + fullyBooked);
```

In the copied code, I'll change _hasBookings_, 
my local variable name, to _fullyBooked_. 
I'll change the terminal operation _anyMatch_, to _allMatch_. 
Lastly, I'll change my print statement, 
to print _fullyBooked_ equals, 
and use _fullyBooked_ as the variable that gets printed. 
Running this code:

```html  
fullyBooked = false
```

My _reservationCount_ will change, 
but _hasBookings_ should still be true. 
_FullyBooked_ though will be false, 
since the random process I'm using to book seats, 
is unlikely to reserve every seat. 
Similar to _allMatch_ is _noneMatch_, 
so let me copy those last couple of statements 
and paste a copy below.

```java  
boolean eventWashedOut = Arrays
    .stream(seats)
    .noneMatch(Seat::isReserved);
System.out.println("eventWashedOut = " + eventWashedOut);
```

This time I'll change the local variable to be _eventWashedOut_. 
I'll change _allMatch_ to _noneMatch_. 
And I'll print out _eventWashedOut_ equals, 
and include the variable there. 
Running this code:

```html  
eventWashedOut = false
```

You can see that _eventWashedOut_ is **false** as well. 
Like _fullyBooked_, 
the random generation is likely to return several booked seats. 
I'll go back to the **Seat** record, 
and comment out that call to the canonical constructor. 
I'll copy and paste it below, changing the random part, 
to just **true**, meaning every seat will be reserved. 
This means the event is sold out, so if I run this, 
I get _reservationCount_ is 100, and _hasBookings_ equals **true**. 
Now, even though _fullyBooked_ is _true_, 
_eventWashedOut_ is still **false**. 
I'll change the seat constructor one more time, 
passing just **false** this time.
Running this code, I get a different story. 
There are no reservations, _hasBookings_ is **false**, 
_fullyBooked_ is certainly **false**, 
and _eventWashedOut_ is **true**. 
These three conditions cover every scenario, 
and allow you to use stream terminal operations, 
to get information about all elements in that stream. 
I still have two more types of terminal operations to cover, 
I'll bring this to a close here. 
I'll talk about the _Transform_ 
and _Process_ terminal operations next. 
However, first I want to set up some code that 
I'll be using for the remainder of the sections, 
and that will be used in a challenge for you 
on the terminal operations we've looked at so far. 
You can use the next section as an additional challenge. 
You can also skip ahead, 
and import the code that I'll be creating, 
in the challenge section on terminal operations.
</div>

## [g. Streaming Student Project](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_13_Streams/Course08_StreamingStudentsProject/README.md#streaming-student-project)
<div align="justify">

In the code ahead, 
I'll be setting up a couple of familiar classes, 
a **Student**, and a **Course**. 
I'll be mocking up a lot of students 
to put some of the stream operations into practice. 
Again, you can choose to approach this 
as an additional challenge, 
or you could just follow along, 
or even skip over these setup sections. 
This code is very basic,
with only the use of the Stream's generate method 
used at the very end. 
I'll also be using **LocalDate**, 
and a class called **Period**, 
I haven't used before, 
to get the number of months elapsed 
between two dates.

![image06](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_13_Streams/images/image06.png?raw=true)

The **course** type should have a course code, 
a course title, and a lecture count.
You can make this an immutable class.

![image07](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_13_Streams/images/image07.png?raw=true)

Each student will have a **courseEngagement** instance, 
for every **course** they're enrolled in. 
It should have the fields for the **course**, 
the enrollment date, the engagement type, 
the last lecture, and the last activity date. 
It should have the usual getters, 
plus getters for calculated fields as shown here. 
The _getMonthsSinceActive_ method should return
the months elapsed, from the last course activity.

The _getPercentComplete_ method should use the last lecture, 
and the lecture count on course, 
to return a percentage complete. 
The _watchLecture_ method would get called 
when a student engaged in the course,
and should update fields on the engagement record.
It takes a lecture number, and an activity date.

![image08](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_13_Streams/images/image08.png?raw=true)

The **Student** class should have a student id, 
and demographic data, like country code, year enrolled, 
age at time of enrollment, gender, 
and a programming experience flag. 
Your **student** should also have a map of **CourseEngagements**,
keyed by course code. 
Include getters for all of these fields.

In addition to the usual getters, 
add getter methods for calculated fields, 
like _getYearsSinceEnrolled_, and _getAge_.
Include the getters, 
_getMonthsSinceActive_ and _getPercentComplete_, 
that take a course code, and return data, 
from the matching **CourseEngagement** record. 
Add an overloaded version of _getMonthsSinceActive_, 
to get the least number of inactive months, from all courses.

You should have overloaded _addCourse_ methods, 
one that takes a specified activity date, 
and one that will instead default to the current date. 
Include the method, _watchLecture_, 
that takes a course code,
a lecture number and an activity year and month, 
and calls the method of the same name, 
on the **courseEngagement** record.

Finally, create a static factory method on this class, 
_getRandomStudent_, that will return a new instance of **Student**,
with random data, populating a student's fields. 
Make sure to pass courses to this method, 
and pass them to the student constructor. 
For each course, call _watchLecture_, 
with a random lecture number, 
and activity year and month, 
so that each Student will have different activity for each course.
</div>

## [h. Terminal Operations Challenge]()
<div align="justify">

In this challenge, 
you'll use the terminal operations shown on the table below, 
in combination with some of the intermediate operations 
you've learned about.

| Return Type      | Terminal Operations                       |
|------------------|-------------------------------------------|
| long             | count()                                   |
| DoubleStatistics | summaryStatistics()                       |
| boolean          | allMatch(Predicate<? super T> predicate)  |
| boolean          | anyMatch(Predicate<? super T> predicate)  |
| boolean          | noneMatch(Predicate<? super T> predicate) |

You'll be using these to answer 
some questions about a series of students. 
It contains a Student class with demographic data. 
This class has a factory method 
to generate a new instance of a **Student**, 
using random data.
This factory method will also generate some random activity, 
for each course passed as an argument, to the **Student** constructor.

Your challenge is to create a source for a stream of **Students**.

- Use the static method on Student as the Supplier.
- Use a large enough number to get a variety of Student data, 
no less than 1000 students, for example.
- Use a combination of the intermediate and terminal operations we've covered so far, to answer the following questions.
     * How many male and female students are in the group?
     * How many students fall into the three age ranges, 
less than age 30, between 30 and 60, and lastly, over 60 years old? 
Use summaryStatistics on student's age to get a better idea of how old the student population is. 
     * What countries are the students from? 
Print a distinct list of the country codes. 
     * Are there students that are still active, 
that have been enrolled for more than 7 years? 
Use one of the match terminal operations to answer this question.

Next, select five of the students above, 
and print their information out. 
Imagine that maybe you'd like to send them a special coupon, 
for being long time learners, and clients of yours. 
Remember, you can use _peek_ to help you understand your stream processing, 
if you don't feel confident about your results.
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