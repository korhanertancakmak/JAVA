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

Another static method on the **Stream** class is _iterate,_ 
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

| Method                                                                            | Finite              | Infinite                  |
|-----------------------------------------------------------------------------------|---------------------|---------------------------|
| &nbsp;&nbsp;`Collection.stream()`                                                 | &nbsp;&nbsp;&nbsp;X |                           |
| &nbsp;&nbsp;`Arrays.stream((T[])`                                                 | &nbsp;&nbsp;&nbsp;X |                           |
| &nbsp;&nbsp;`Stream.of(T...)`                                                     | &nbsp;&nbsp;&nbsp;X |                           |
| &nbsp;&nbsp;`Stream.iterate(T seed, UnaryOperator<T> f)`                          | &nbsp;&nbsp;&nbsp;X |                           |
| &nbsp;&nbsp;`Stream.iterate(T seed, Predicate<? super T> p, UnaryOperator<T> f)`  | &nbsp;&nbsp;&nbsp;X |                           |
| &nbsp;&nbsp;`Stream.generate(Supplier<? extends T> s)`                            |                     | &nbsp;&nbsp;&nbsp;&nbsp;X |
| **`IntStream.range(int startInclusive, int endExclusive)`                         | &nbsp;&nbsp;&nbsp;X |                           |
| **`IntStream.rangeClosed(int startInclusive, int endExclusive)`                   | &nbsp;&nbsp;&nbsp;X |                           |

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

## [d. Stream Sources Challenge](https://github.com/korhanertancakmak/JAVA/tree/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_13_Streams/Course04_StreamSourcesChallenge/README.md#stream-sources-challenge)
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

| Return Type  | Operation                                                                                                                                  |
|--------------|--------------------------------------------------------------------------------------------------------------------------------------------|
| `Stream<T>`  | `distinct()`                                                                                                                               |
| `Stream<T>`  | `filter(Predicate<? super T> predicate)`<br/> `takeWhile(Predicate<? super T> predicate)`<br/> `dropWhile(Predicate<? super T> predicate)` |
| `Stream<T>`  | `limit(long maxSize)`                                                                                                                      |
| `Stream<T>`  | `map(Function<? super T, ? extends R> mapper)`                                                                                             |
| `Stream<T>`  | `peek(Consumer<? super T> action)`                                                                                                         |
| `Stream<T>`  | `skip(long n)`                                                                                                                             |
| `Stream<T>`  | `sorted()`<br/> `sorted(Comparator<? super T> comparator)`                                                                                 |

As you can see from this table, 
the operations you've already seen briefly 
cover half of the basic operations available
to your stream pipelines.

| Return Type  | Operation                                                                                                                                 | Description                                                                                                                                                                                                                                                                        |
|--------------|-------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `Stream<T>`  | `distinct()`                                                                                                                              | Removes duplicate values from the Stream.                                                                                                                                                                                                                                          |
| `Stream<T>`  | `filter(Predicate<? super T> predicate`<br/> `takeWhile(Predicate<? super T> predicate)`<br/> `dropWhile(Predicate<? super T> predicate)` | These methods allow you to reduce the elements in the output stream.<br/> Elements that match the filter's Predicate are kept in the outgoing stream, for the filter and takeWhile operations.<br/> Elements will be dropped until or while the dropWhile's predicate is not true. |
| `Stream<T>`  | `limit(long maxSize)`                                                                                                                     | This reduces your stream to the size specified in the argument.                                                                                                                                                                                                                    |
| `Stream<T>`  | `skip(long n)`                                                                                                                            | This method skips elements, meaning they won't be part of the resulting stream.                                                                                                                                                                                                    |

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
  <tr>
     <td rowspan="2">IntStream</td>
     <td rowspan="2">mapToInt(ToIntFunction<? super T> mapper)</td>
     <td>mapToObj(IntFunction<? extends U> mapper)</td>
     <tr>
         <td>boxed()</td>
     </tr>
  <tr>
     <td rowspan="2">LongStream</td>
     <td rowspan="2">mapToLong(ToLongFunction<? super T> mapper)</td>
     <td>mapToObj(LongFunction<? extends U> mapper)</td>
     <tr>
         <td>boxed()</td>
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

| Return Type                                                                | Terminal Operations   | Stream                                    |
|----------------------------------------------------------------------------|-----------------------|-------------------------------------------|
| long                                                                       | `count()`             | ALL                                       |
| Optional                                                                   | `max()`               | ALL                                       |
| Optional                                                                   | `min()`               | ALL                                       |
| OptionalDouble                                                             | `average()`           | DoubleStream<br/>IntStream<br/>LongStream |
| double<br/>int<br/>long                                                    | `sum()`               | DoubleStream<br/>IntStream<br/>LongStream |
| DoubleSummaryStatistics<br/>IntSummaryStatistics<br/>LongSummaryStatistics | `summaryStatistics()` | DoubleStream<br/>IntStream<br/>LongStream |

The methods shown on this table have no arguments. 
They all return numerical data, either directly, 
or in specialized types to hold that data. 
I'll show examples of the rest of these 
after the Optional lecture. 
Before that, I want to talk about methods 
for matching elements on a specific condition.

| Return Type | Method                                      | Description                                                          |
|-------------|---------------------------------------------|----------------------------------------------------------------------|
| boolean     | `allMatch(Predicate<? super T> predicate)`  | Returns true if all stream elements meet the condition specified.    |
| boolean     | `anyMatch(Predicate<? super T> predicate)`  | Returns true there is at least one match to the condition specified. |
| boolean     | `noneMatch(Predicate<? super T> predicate)` | This operation returns true if no elements match.                    |

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

## [g. Streaming Student Project](https://github.com/korhanertancakmak/JAVA/tree/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_13_Streams/Course08_StreamingStudentsProject/README.md#streaming-student-project)
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

## [h. Terminal Operations Challenge-1](https://github.com/korhanertancakmak/JAVA/tree/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_13_Streams/Course09_TerminalOperationsChallenge1/README.md#terminal-operations-challenge)
<div align="justify">

In this challenge, 
you'll use the terminal operations shown on the table below, 
in combination with some of the intermediate operations 
you've learned about.

| Return Type      | Terminal Operations                         |
|------------------|---------------------------------------------|
| long             | `count()`                                   |
| DoubleStatistics | `summaryStatistics()`                       |
| boolean          | `allMatch(Predicate<? super T> predicate)`  |
| boolean          | `anyMatch(Predicate<? super T> predicate)`  |
| boolean          | `noneMatch(Predicate<? super T> predicate)` |

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
Remember, you can use _peek_ to help you understand your stream processing 
if you don't feel confident about your results.
</div>

## [i. Collector and Collectors Class]()
<div align="justify">

In the last section, 
we looked at some simple results of processing stream elements. 
We used _count_ and _summaryStatistics_ operations, 
which are **reduction** terminal operations.

**Reduction operations** combine the contents of a stream, 
to return a value, 
or they can return a collection. 
On this table, I want to show you some additional terminal operations,
and their return types and signatures.

| Return Type                         | Terminal Operations                                                                                                                                                                                    |
|-------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| R<br/>R                             | `collect(Collector<? super T.A.R> collector)`<br/> `collect(Supplier <R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner)`                                                   |
| `Optional<T>` <br/>`T`<br/> `<U> U` | `reduce(BinaryOperator < T > accumulator)<br/> reduce(T identity, BinaryOperator < T > accumulator)`<br/> `reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator < U > combiner)` |
| `Object[]`<br/> `A[]`               | `toArray()`<br/> `toArray(IntFunction<A[ ]> generator)`                                                                                                                                                |
| `List<T>`                           | `toList()`                                                                                                                                                                                             |

Some of these signatures, namely the parameters, 
look pretty cryptic. 
Don't let this intimidate or overwhelm you.
Hopefully you recognize many of these types, 
which are functional interfaces you know by now. 
You can see the **Supplier**, the **BiConsumer**, 
the **BinaryOperator**, the **BiFunction**, 
and the **IntFunction**, in these parameters. 
This tells you we'll be using lambdas 
or method references with these operations. 
This is the important part of the signature, 
and I would recommend ignoring the generic types, 
as you consider these. 
Let me show you the same set of operations, 
without the generics in the signature.

| Return Type                  | Terminal Operations                                                                                                                                                    |
|------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| R<br/>R                      | `collect(Collector collector)`<br/> `collect(Supplier supplier, BiConsumer accumulator, BiConsumer combiner)`                                                          |
| Optional <br/>T<br/> `<U> U` | `reduce(BinaryOperator accumulator)`<br/> `reduce(T identity, BinaryOperator accumulator)`<br/> `reduce(U identity, BiFunction accumulator, BinaryOperator combiner)`  |
| `Object[]`<br/> `A[]`        | `toArray()`<br/> `toArray(IntFunction generator)`                                                                                                                      |
| List                         | `toList()`                                                                                                                                                             |

Ok, this view of each method is a little easier on the eyes 
and brain, I think. 
There is one interface I haven't talked about yet, 
and that's the **Collector**, 
which you can see in the first collect operation. 
This is not a functional interface, 
but there are helper methods on another class, 
named **Collectors**, with an _s_, 
that provide these special types, 
that let us create collections of any kind. 
I'll be covering this interface in more detail, 
as I review these methods. 
Starting from the bottom and working upwards, 
you can see _toList_ there. 
**List** is one of the most common types 
you'd put stream elements into, 
so Java provided a terminal operation 
to give us a **List** back. 
We can also get arrays of objects, 
or arrays of types we declare, 
using one of the two **Array** operations.
What's not so obvious, 
when you look at the _reduce_ 
and _collect_ operations, 
is that we can also get back **Maps**, **Sets**, 
or any other resulting type 
we want from those operations. 
In some ways, lambdas and streams are a whole new language, 
living inside Java. 
You never have to use them at all, 
Java existed before these features were included. 
Once you do understand them though, 
I think you'll appreciate them. 
Let's get started. 
I'm back in the **StreamingStudents** project, 
because there's a lot in this code to practice on. 
At the end of the challenge section, 
I left off with identifying five long-term students.

```java  
Arrays.stream(students)
    .filter(s -> (s.getAge() - s.getAgeEnrolled() >= 7) && (s.getMonthsSinceActive() < 12))
    .filter(s -> !s.hasProgrammingExperience())
    .limit(5)
    .toList()
    .forEach(System.out::println); // It's not a stream anymore, List<Student>
```

Now, I want to do something, 
or process these students in some way, 
so I want to get these five students into some
kind of container type. 
In this case, I'll use the _toList_ terminal operation, 
inserting it after the _limit_ operation,
in my last pipeline. 
I'll put that just before the _forEach_ statement. 
This compiles, and you might be wondering why,
because I have _toList_, 
and _forEach_ in the same pipeline here. 
Are both of these terminal operations? 
Wouldn't the second one cause an exception? 
Let me run this code:

```html  
Student{studentId=4, countryCode='GB', yearEnrolled=2015, ageEnrolled=44, gender='U', programmingExperience=false, engagementMap={JMC=JMC: Kas 2023 Lecture 15 [1], PYMC=PYMC: Mar 2017 Lecture 7 [81]}}
Student{studentId=49, countryCode='CN', yearEnrolled=2015, ageEnrolled=86, gender='M', programmingExperience=false, engagementMap={JMC=JMC: Kas 2023 Lecture 11 [1], PYMC=PYMC: Mar 2017 Lecture 5 [81]}}
Student{studentId=135, countryCode='CN', yearEnrolled=2015, ageEnrolled=40, gender='U', programmingExperience=false, engagementMap={JMC=JMC: Ağu 2023 Lecture 2 [4], PYMC=PYMC: Kas 2019 Lecture 21 [49]}}
Student{studentId=175, countryCode='US', yearEnrolled=2016, ageEnrolled=57, gender='M', programmingExperience=false, engagementMap={JMC=JMC: Nis 2016 Lecture 20 [92], PYMC=PYMC: Şub 2023 Lecture 19 [10]}}
Student{studentId=219, countryCode='US', yearEnrolled=2016, ageEnrolled=70, gender='U', programmingExperience=false, engagementMap={JMC=JMC: May 2023 Lecture 20 [7], PYMC=PYMC: Mar 2016 Lecture 24 [93]}}
```

It runs fine, 
and I get the five students printed out. 
This is because the last _forEach_ 
is not technically part of the stream pipeline at all. 
In fact, if you've got IntelliJ's Inlay hints turned on, 
you can see the inlay hint after the _toList_ operation. 
It tells us that at this point, we have a **List** of **students**, 
and it's not a stream anymore. 
The _forEach_ call here is a method on that list, 
and not a terminal operation on the stream. 
In this scenario, the _List_ method provides an unmodifiable list, 
restricting your ability to perform further operations, 
or chain additional methods. 
Instead, it's more likely you'd want to assign 
the result of your list to some variable, 
so I'll do that next:

```java  
List<Student> longTimeLearners = Arrays.stream(students)
        .filter(s -> (s.getAge() - s.getAgeEnrolled() >= 7) && (s.getMonthsSinceActive() < 12))
        .filter(s -> !s.hasProgrammingExperience())
        .limit(5)
        .toList();
```

I'll set up a local variable assignment. 
I'll use **List** with **Student**, 
and call it _longTimeLearners_, 
and assign it the result of the stream pipeline. 
I'll add a semicolon after the terminal operation, 
_toList_, and remove the _forEach_ statement altogether.
Again, the returned list is unmodifiable. 
But now you have a concrete collection of elements
you could pass to other methods, 
as long as they don't attempt to modify the original list. 
I can always pass this list to the constructor, 
of a new **ArrayList** or **LinkedList**, 
if I want my own modifiable list. 
Let's look at the _toArray_ operation next.

```java  
var longTimeLearners1 = Arrays.stream(students)
        .filter(s -> (s.getAge() - s.getAgeEnrolled() >= 7) && (s.getMonthsSinceActive() < 12))
        .filter(s -> !s.hasProgrammingExperience())
        .limit(5)
        .toArray(size -> new Student[size]);
```

First, I'll change my variable type to just _var_. 
I'll change the _toList_ operation, 
making it _toArray_, without passing that any arguments. 
This compiles, but again let's see what IntelliJ's inlay hints can tell us. 
I'm getting back an array of **Object**, 
which is basically an untyped array. 
I know every object is a **Student** in this stream, 
but if I want to access a **Student** method on these array elements, 
I'd have to cast that to a **Student** array. 
There's an overloaded version of this operation, 
that lets us specify the type of array we want back. 
It takes an **IntFunction** type, which means, 
it takes an int as a parameter, 
and then returns an instance of something else. 
This means I can use the parameter
to create a new array of **Student**, 
and use that argument value, as the size of the array. 
I'll use the variable name, _size_, 
as the parameter variable name in my lambda, 
to make this really clear.
I can then use this _size_, 
and pass that to the array construction code. 
This means it goes in the square brackets
when I declare a new array of **Student**. 
IntelliJ is telling me there's a better way to represent this, 
but I wanted you to see it this way first, 
so it's easier to see what's happening.

```java  
var longTimeLearners2 = Arrays.stream(students)
        .filter(s -> (s.getAge() - s.getAgeEnrolled() >= 7) && (s.getMonthsSinceActive() < 12))
        .filter(s -> !s.hasProgrammingExperience())
        .limit(5)
        .toArray(Student[]::new);
```

Now let's use IntelliJ's popup 
to change that to a method reference. 
This is probably the first time I've showed you 
an array instantiation as a method reference. 
It's doing exactly what I showed you in the lambda expression. 
This is another example of the unbounded receiver method reference type. 
In this case, the unbounded receiver is the array size, 
and it's used in the array's specialized construction statement. 
Now, the inlay hint is telling me 
I'm getting back an array of **Student**, 
because of this minor change. 
I can also use the _reduce_ terminal operation 
to give me a list back. 
I'll copy all the code and paste that below.

```java  
var learners = Arrays.stream(students)
        .filter(s -> (s.getAge() - s.getAgeEnrolled() >= 7) && (s.getMonthsSinceActive() < 12))
        .filter(s -> !s.hasProgrammingExperience())
        .limit(5)
        .collect(Collectors.toList());

Collections.shuffle(learners);
```

I'll change the name of my variable to just learners. 
I'll replace the _toArray_ operation with _collect_, 
and I'm going to pass the result of calling a method 
on that Collectors helper class. 
In this case, it has a _toList_ method also. 
Now, IntelliJ is giving me a warning on that. 
If I hover over that, the message is that this can be simplified
and replaced with just the **Stream**'s _toList_ terminal operation. 
There's a crucial difference between these two operations, 
between the **Stream**'s _toList_ operation, 
and the _collect_ operation; 
that take's `Collectors.toList` as an argument. 
In fact, I'll add a statement after this, 
I'll call `Collections.shuffle` on my _learners_ list. 
You'll remember this just shuffles up values in the list. 
Ok, now the warning disappears. 
That's because IntelliJ can tell I want to modify this list. 
The collect terminal operation returns mutable reductions, 
as I've demonstrated here. 
I can run this code without any errors. 
If I changed that operation back to the _toList_ call:

```java  
var learners = Arrays.stream(students)
        .filter(s -> (s.getAge() - s.getAgeEnrolled() >= 7) && (s.getMonthsSinceActive() < 12))
        .filter(s -> !s.hasProgrammingExperience())
        .limit(5)
        .toList();

Collections.shuffle(learners);
```

I would get a warning on that call to shuffle, 
and I'd get an exception if I tried to run it. 
There are two versions of the _collect_ method,
as I showed you on the first couple of operations in the table. 
This one, that I'm showing you here, takes one argument 
with a type of **Collector**. 
I was able to get this by calling the _toList_ method, 
on the **Collectors** class.

So what's a **Collector** anyway? 
Let's go look at that interface, 
I'll pull it up in the api documentation. 
There are three really important things on that page 
I want to point out. 
First, the Interface has three type parameters.

* _T_ is the type of input elements to the reduction, 
so this is the type of your stream elements.
* _A_ is the mutable type you want to collect elements into. 
This can be a single type, like a **StringBuilder**, or a collection type, like **List**.
* _R_ is the final result type of the reduction operation.

The second two types are usually the same, 
meaning your result type(_R_) is often the same type 
you've been accumulating elements(_A_) into, 
but you're not limited to that. 
The second really important thing is that 
this is a mutable reduction operation. 
I already demonstrated this to you, 
with the list I got back from the collect operation, 
in my last code sample.

Lastly, a **Collector** is specified by four functions 
that work together, to accumulate entries, 
into a mutable result container.

* The supplier, accumulator, and combiner methods are 
the primary methods you'll be using, to build a result container.
* There is also a less used method, called the finisher, 
that provides a way to do a final transformation 
on the fully combined accumulated instance created.

Let's look at the abstract methods on this interface. 
I'll select methods, then abstract methods at the top of this page. 
And there they are, the four methods just discussed, 
and one additional one. 
This interface means 
we could create our own implementations of a **Collector** interface, 
and implement these methods. 
But Java has done this work for us, 
for most of the types we'd want to use. 
These are supplied as static methods, in the **Collectors** class. 
Additionally, rather than implement a class, 
there is an overloaded version of the _collect_ method. 
This provides a way to specify code for the **supplier**, 
the **accumulator** and the **combiner**. 
This means we can just pass lambda expressions
or method references, rather than build a type 
that implements the **Collector** interface. 
Let's look at the **Collectors** class now.

First, there's a list here of the most common usages, with examples. 
You see there at the top, is an example using the `Collectors.toList` 
method I used in my own example. 
I'll again select the method tab at the top of the screen.
There are a lot of methods here, and this can seem quite overwhelming. 
But look at the names, they start with things like _averaging_, 
_filtering_, _groupingBy_, _joining_, and _partitioningBy_, to name a few. 
Again, this is the language you'd see in data queries. 
You can do a lot with just a few of these in your arsenal, 
and that's what I'm going to show you next. 
Now, we'll start exploring the _collect_ and _reduce_ operations, 
and some of the **Collectors** methods.

I'll create a new class called **MainCollect**. 
This way I can keep the collect code in a single place. 
I'll include a _main_ method in this class. 
I'll copy the code to create the two courses, 
from the **Main** class's _main_ method.

```java  
public class MainCollect {

    public static void main(String[] args) {

        Course pymc = new Course("PYMC", "Python Masterclass");
        Course jmc = new Course("JMC", "Java Masterclass");

        List<Student> students = Stream.generate(() -> Student.getRandomStudent(jmc, pymc))
                        .limit(1000)
                        .toList();
    }
}
```

Instead of setting up an array 
and populating it with the randomly generated students, 
this time I'll stream students, with the _generate_ method, 
into a list, using _toList_. 
At this point, I really don't want to modify 
my first collection of students once I have them,
so getting back an unmodifiable list is fine. 
I'll start with a **List** of **Students**, called _students_. 
I'll call `Stream.generate` 
and pass it almost the same lambda expression I used, 
in the _setAll_ method for the array in the **Main** class's _main_ method. 
Here, its type is **Supplier**, 
which means it takes no arguments. 
It starts with empty parentheses, 
and then returns random students from that static method on **Student**. 
I'll limit this to 1000 _students_. 
And I'll call _toList_, 
which gives me a list of **Students**, 
in the order they were created.

```java  
Set<Student> australianStudents = students.stream()
        .filter((s) -> s.getCountryCode().equals("AU"))
        .collect(Collectors.toSet());
System.out.println("# of Australian Students = " + australianStudents.size());
```

Next, I want to get a set of my Australian students. 
I'll set up a local variable, a **Set**, 
with a type argument of **Student**, 
and call it _australianStudents_. 
I'll start by getting a stream from my _students_ list. 
I'll filter, using _getCountryCode_ on _student_, 
and testing for "AU". 
Now, I'll _collect_ to a set, 
using `Collectors.toSet`. 
I'll print out the number of students in my set. 
This method is a lot like `Collectors.toList`, 
but it returns a HashSet. 
If I run this code:

```html  
# of Australian Students = 147
```

I'll get a different number of Aussie students each time, 
but that number should be about a seventh of the total students. 
Next, I want another set, called _underThirty_:

```java  
Set<Student> underThirty = students.stream()
        .filter((s) -> s.getAgeEnrolled() < 30)
        .collect(Collectors.toSet());
System.out.println("# of Under Thirty Students = " + underThirty.size());
```

For students who enrolled when they were under thirty at the time. 
I'll copy the statements above and paste a copy just below. 
I'll change the variable name to _underThirty_. 
I'll change the condition I'm filtering on 
to check if the age enrolled is less than 30. 
Lastly, I want the text that gets printed out, 
to print the under thirty students, 
and use the `underThirty.size`. 
Ok, so this is just a different query 
about our student population. 
Running this several times:

```html  
# of Under Thirty Students = 187
```

I'll get a different number of under thirties. 
Now let's say I want to understand 
how many of the under thirties are also Australian.

```java  
Set<Student> youngAussies1 = new TreeSet<>(Comparator.comparing(Student::getStudentId));
youngAussies1.addAll(australianStudents);
youngAussies1.retainAll(underThirty);
youngAussies1.forEach((s) -> System.out.print(s.getStudentId() + " "));
System.out.println();
```

If your population is huge, 
and your sets are much smaller, 
there's an argument for using some set math,
rather than processing another stream pipeline. 
Let's walk through an example of this first. 
I'll create a new set,
and call it young _youngAussies1_. 
I want this to be a **TreeSet**, 
and I want it ordered by student id, 
so I can do this by passing a comparator 
into the constructor of **TreeSet**. 
I'll add all my australian students. 
I'll retain all students who are also _underThirty_. 
You'll remember this is really intersected of the two sets, 
returning only the students who are in both sets. 
I'll print each student's id out, in a single line, 
separated by spaces, and finish with a newline.
If I run this code:

```html  
45 53 74 107 147 150 283 299 309 350 419 420 439 447 485 510 511 520 560 616 636 693 698 731 758 805 817 824 848 852 859 968 974 1000
```

I'll get a list of students id's. 
These are the students who are both from Australia 
and were under thirty when they enrolled. 
Now, let's do the same thing, but use a stream pipeline.

```java  
Set<Student> youngAussies2 = students.stream()
        .filter((s) -> s.getAgeEnrolled() < 30)
        .filter((s) -> s.getCountryCode().equals("AU"))
        .collect(Collectors.toSet());

youngAussies2.forEach((s) -> System.out.print(s.getStudentId() + " "));
System.out.println();
```

I'll copy the underThirty stream pipeline 
and paste it at the end of this method. 
I'll change the variable name to _youngAussies2_. 
I'll copy the filter from Australian students, 
and paste that as a second filter in my new stream pipeline. 
I'll also copy the two output statements, 
pasting it at the end of this method. 
I'll change _youngAussies1_ to _youngAussies2_. 
I'll run that now:

```html  
45 53 74 107 147 150 283 299 309 350 419 420 439 447 485 510 511 520 560 616 636 693 698 731 758 805 817 824 848 852 859 968 974 1000
309 848 74 420 299 974 1000 805 350 616 693 852 147 510 511 859 107 45 447 968 817 53 419 485 731 636 698 758 439 283 520 150 824 560
```

The good news is I get the same set of id's, 
but the bad news is that the last set's not ordered. 
Can't I just add sorted to the pipeline? Let me try that. 

```java  
Set<Student> youngAussies2 = students.stream()
        .filter((s) -> s.getAgeEnrolled() < 30)
        .filter((s) -> s.getCountryCode().equals("AU"))
        //.sorted()
        .collect(Collectors.toSet());

youngAussies2.forEach((s) -> System.out.print(s.getStudentId() + " "));
System.out.println();
```

I'll insert that 
before the collect terminal operation. 
Notice that IntelliJ has grayed out. 
If I hover over that, 
I get the message that 
_sorted is redundant, because two **Set** doesn't depend on the sort order_. 
The pipeline knows the set isn't ordered, 
so it would be wasted effort to sort it here. 
But let's say I really do want it sorted. 
This means I want a **TreeSet**, and not a **HashSet**. 
Is there a **Collectors** helper method for that? 
Well, there isn't, at least not like the `Collectors.toSet` method. 
This means that I could either pass this **HashSet** 
to a **TreeSet** constructor later. 
My other option is to use the overloaded version of _collect_. 
Before I do that, I want to first go up to this _toSet_ method call, 
and I'll _ctrl_ click on it. 
This opens a window that shows me the code it's using. 
You don't have to understand all of this 
to understand a few important points. 
Namely, that there are four parameters, 
three are either method references or lambda expressions. 
These are the three we'd use if we were to do something similar. 
In truth, I do want to do something very similar, 
using the alternate _collect_ method, 
passing expressions that mirror these. 
The first method reference in this code instantiates the new **HashSet**. 
This is the **Supplier**. 
The second method reference adds one element at a time to the **HashSet**. 
This is the **Accumulator**. 
The third is a lambda expression, and a little more complex. 
Mainly what I want you to see is that 
it's calling _addAll_ on the set, 
so it's adding one collection, to another collection. 
That's the **Combiner**. 
That's enough information to get us started, 
so let's get back to the code.

```java  
Set<Student> youngAussies3 = students.stream()
        .filter((s) -> s.getAgeEnrolled() < 30)
        .filter((s) -> s.getCountryCode().equals("AU"))
        //.sorted()
        .collect(TreeSet::new, TreeSet::add, TreeSet::addAll);

youngAussies3.forEach((s) -> System.out.print(s.getStudentId() + " "));
System.out.println();
```

I'm going to remove `Collectors.toSet` as an argument, 
in the _collect_ terminal operation. 
I'll replace it with three arguments this time. 
First, the **Supplier**. 
You saw that the code we just looked at, 
had `HashSet::new` for its supplier,
so you'd imagine I could put `TreeSet::new` here, 
which I'll do. 
The second parameter is the **accumulator**, 
and that would be the same as when I'd use the **HashSet**, 
so I'll add _add_ as a method reference too. 
The **combiner** might be a little confusing 
because I haven't covered parallel streams, 
or splitting up your streams to process them more efficiently.
In this case, they'd need to be put back together, 
and that's what this method would be needed for. 
It joins the results of multiple stream's accumulated outputs, 
into a single collection, 
and this would be done using an _addAll_ method. 
In our case, it's not likely to be used, but it's still required. 
And that's it, we've set up our first _collect_ method with a **supplier**,
**accumulator** and **combiner**. 
Notice that the _sorted_ method isn't grayed out anymore,
but it isn't needed either, so I'll remove it. 
The tree set is ordered, as elements are added. 
I'll run this:

```html  
Exception in thread "main" java.lang.ClassCastException: class Student cannot be cast to class java.lang.Comparable
```

But this doesn't work, I get an exception. 
That's because **Student** doesn't implement **comparable**. 
This is the same reason I had to set up the tree set 
for the _youngAussies1_ variable, with my own **Comparator**. 
I want to do that here, 
but how do I do it? 
Well, I can't use a method reference for the **Supplier** to do it.

```java  
Set<Student> youngAussies4 = students.stream()
        .filter((s) -> s.getAgeEnrolled() < 30)
        .filter((s) -> s.getCountryCode().equals("AU"))
        .collect(() -> new TreeSet<>(Comparator.comparing(Student::getStudentId)), TreeSet::add, TreeSet::addAll);

youngAussies4.forEach((s) -> System.out.print(s.getStudentId() + " "));
System.out.println();
```

I need to change that first argument to use a lambda expression. 
I'll start with the empty parentheses, 
and after the arrow token, I'll have new **TreeSet**, 
and in the constructor parentheses, I'll pass a **Comparator**. 
I'll build this like I did before, 
when I created the **TreeSet** for the _youngAussies1_ set. 
If I run my code now:

```html  
45 53 74 107 147 150 283 299 309 350 419 420 439 447 485 510 511 520 560 616 636 693 698 731 758 805 817 824 848 852 859 968 974 1000
309 848 74 420 299 974 1000 805 350 616 693 852 147 510 511 859 107 45 447 968 817 53 419 485 731 636 698 758 439 283 520 150 824 560
45 53 74 107 147 150 283 299 309 350 419 420 439 447 485 510 511 520 560 616 636 693 698 731 758 805 817 824 848 852 859 968 974 1000
```

It works, and I get the same results from both sets. 
The _collect_ method has two overloaded versions. 
I show them here again, without showing the generic type parameters. 
The first can be used by passing it the result of 
any of the many factory methods, on the **Collectors** class. 
I showed you _asList_, and _asSet_, 
as two examples of static methods on that class. 
The second is more complex, but gives you ultimate flexibility, 
as you saw with the **TreeSet** example I just showed you. 
You could also create your own **Collector** type 
by writing a class that implements Collector, 
overriding those abstract methods with custom functionality.

```java  
String countryList = students.stream()
        .map(Student::getCountryCode)
        .distinct()
        .sorted()
        .reduce("", (r, v) -> r + " " + v);
System.out.println("countryList = " + countryList);
```

Lastly, I want to cover the _reduce_ terminal operation, 
so let's get back to the code. 
The _reduce_ method is different from _collect_, 
because you're not accumulating elements into a container. 
Instead, you're accumulating elements into a single type. 
Let me show you an example of this. 
I'll set up a variable called _countryList_ 
and start a stream pipeline on students.
I'll map to the country, using _getCountryCode_. 
I only want _distinct_ countries. 
I'll _sort_. 
And now I'll seed this, with an empty string, 
returning a concatenated string with the currently accumulated one, 
plus the new value or country. 
And I'll print that out. 
Running this code:

```html  
countryList =  AU CA CN GB IN UA US
```

I'll get my country codes printed out as a single concatenated string. 
You can imagine this would be useful 
if you didn't know the _distinct_ values in some fields, 
because this method lets you get them all in a single string. 
Ok, so that covers the introduction 
to the _collect_ and _reduce_ terminal operations. 
These methods can be used 
to do almost any kind of transformation you can think of. 
I'll be covering more complex features as we move forward.
</div>

## [j. Terminal Operations Challenge-2](https://github.com/korhanertancakmak/JAVA/tree/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_13_Streams/Course12_TerminalOperationsChallenge2/README.md#terminal-operations-challenge-2)
<div align="justify">

Create a new class called **MainChallenge**, 
with a _main_ method that does the following:
* Copy the two courses, _jmc_ and _pymc_, 
from the **MainCollect**'s _main_ method, 
passing both an additional argument 
for the lecture count, so 50 for _pymc_, and 100 for _jmc_.
* Add a third course, titled _Creating Games in Java_. 
You don't have to pass a lecture count for this one.
* Use `Stream.generate` or `Stream.iterate` to generate 5000 random students, 
and create a list of these.
* Use your _getPercentComplete_ method 
to calculate the average percentage completed for all students 
for just the Java Masterclass, using the _reduce_ terminal operation.

Hint: **DoubleStream**, **LongStream**, and **IntStream** have 
both an _average_ and a _sum_ terminal operation. 
For this challenge, I'd like you to try to use _reduce_ instead, 
to get a sum of the percentages. 
You can divide that number, by the student population, 
to get the overall average.

* Use this result, multiplying it by 1.25, 
to collect a group of students (either as a list, or a set). 
These would be the students who've completed more than 
three quarters of that average percentage.
* Sort by the longest enrolled students who are still active, 
because you're going to offer your new course 
to 10 of these students for a trial run.
* Add the new course to these ten students. 
Make one change to the **Student**'s _getRandomStudent_ method, 
using a minimum lecture of 30. 
This will mean more students will have a completion rate greater 
than 50% and will reduce the qualifying students considerably. 
Imagine thanking these students, 
as well as getting their opinion about the new course, 
by inviting them to try this new course for free.
</div>

## [k. What's Optional Class?]()
<div align="justify">

You've seen Optional in the tables
I've shown you on terminal operations.
Optional is a generic class, whose purpose is to be a container 
for a value which may or may not be _null_.
It was created by Java's engineers 
to address the problem of the _NullPointerException_, 
which is one of the most common errors in Java.
The Java documentation says this type is **primarily intended** 
for use as a **method return type**, under specific conditions.
Optional tries to solve the problem of when no result, 
or no data, is a perfectly valid situation, vs. 
when no result might really be an error.
You can think of many situations
where no data makes sense.
Not everyone has a middle initial in their name, 
or even a last name for that matter.
No data for a birthdate may or may not be an exception. 
For genealogy applications, it may not yet be known, 
but for employment, it's required. 
New inventory may not yet have a sales price. 
Using a $0 price as a default, rather than no price 
could lead to problems.
These are just a few examples 
where no value is an actual exception or error.
Optional is a way of telling you 
that a value may not be present, 
therefore, you can ignore the value in processing.
Optional is just another generic class,
so you declare it like any other type, with type arguments.
But you don't construct an Optional.
Instead, you use one of the static factory methods 
I'm showing here.

| Factory Method                    | When to Use                                                                               | Best Practice Notes                                                                                                   |
|-----------------------------------|-------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------|
| Optional< T > empty()             | Use this method to create an Optional that you know has no value.                         | Never return null from a method that has Optional as a return type.                                                   |
| Optional< T > of(T value)         | Use this method to create an Optional that you know has a value.                          | Passing null to this method raises a NullPointerException. Use ofNullable instead, if a possible value might be null. |
| Optional< T > ofNullable(T value) | Use this method to create an Optional when you are uncertain if the value is null or not. |                                                                                                                       |


These methods are _empty_, _of_, and _ofNullable_.
These are easier to explain in code,
so let's get back to that.
I'll again continue with the **StreamingStudents** project.
I'll create another class with a _main_ method, this one I'll call **MainOptional**.

```java  
public class MainOptional {

    public static void main(String[] args) {

        Course pymc = new Course("PYMC", "Python Masterclass");
        Course jmc = new Course("JMC", "Java Masterclass");

        List<Student> students = Stream.generate(() -> Student.getRandomStudent(jmc, pymc))
                                        .limit(1000)
                                        .collect(Collectors.toList());
    }
    
    private static Optional<Student> getStudent(List<Student> list, String type) {
        if (list == null || list.size() == 0) {
            return null;
        } else if (type.equals("first")) {
            return Optional.of(list.get(0));
        } else if (type.equals("last")) {
            return Optional.ofNullable(list.get(list.size() - 1));
        }
        return Optional.ofNullable(list.get(new Random().nextInt(list.size())));
    }
}
```

I'm going to copy some code from the **MainCollect** class.
And then paste these in **MainOptional**'s _main_ method.
I'll change _toList_ to the _collect_ operation instead,
because I plan to make modifications to the list.
I'll next add a private static method on this class.
This method will return an **Optional**,
with a type argument of **student**.
It will take a **List** of students,
as well as a string for the type of retrieval,
which can be first, last, or any.
If the list is _null_ or _empty_, I'll return _null_.
If the type is _first_, I'll return an **Optional**, using `Optional.of`,
and pass that the first element in the list.
If the type is _last_, I'll again return `Optional.of`,
with the last element in the list.
If the type is anything else, I'll pick a random element,
and pass that back in an **Optional** container.
I'm going to ignore Java's warning on _null_ there, for the moment.
Getting back to the _main_ method, I'll test this method out.

```java  
Optional<Student> o1 = getStudent(null, "first");
System.out.println("Empty = " + o1.isEmpty() + ", Present = " + o1.isPresent());
System.out.println(o1);
```

First, I'll set up an **Optional** variable, _o1_,
and assign it the value I get,
from invoking my _getStudent_ method,
passing _null_ for the student list,
and _first_ as the type.
I'll execute two methods, _isEmpty_ and _isPresent_,
on what I get back.
And I'll simply print the optional instance, I get back.
If I run this code:

```html  
Exception in thread "main" java.lang.NullPointerException Cannot invoke "java.util.Optional.isEmpty()" 
because "o1" is null at MainOptional.main
```

I get a NullPointerException, even though I'm using **Optional**.
**Optional** is only as good as the developer, coding the methods.
The first rule for developers who use optional is,
any method that returns an optional, should never return null.
Instead, it should return an empty optional.
IntelliJ had flagged it for me in my _getStudent_ method,
and if I hover over that, the message is
_Null is used for **Optional** type in return statement_.

```java  
private static Optional<Student> getStudent(List<Student> list, String type) {
    if (list == null || list.size() == 0) {
        //return null;
        return Optional.empty();
    } else if (type.equals("first")) {
        return Optional.of(list.get(0));
    } else if (type.equals("last")) {
        return Optional.of(list.get(list.size() - 1));
    }
    return Optional.ofNullable(list.get(new Random().nextInt(list.size())));
}
```

This is an easy fix,
so I'll make that change in my method,
and simply return `Optional.empty`,
rather than _null_.
If I run that code now:

```html  
Empty = true, Present = false
Optinal.empty
```

I see the _isEmpty_ method returned **true**,
and the _isPresent_ method returned **false**,
and the **Optional** I get back is _empty_.
I'll be talking about how to use **Optionals**,
in just a bit, but for now,
I want to explore **Optional** creation a little longer.

```java  
Optional<Student> o1 = getStudent(new ArrayList<>(), "first");
System.out.println("Empty = " + o1.isEmpty() + ", Present = " + o1.isPresent());
System.out.println(o1);
```

Next, I'll test my code
by passing an empty _ArrayList_ instead.
Running this code:

```html  
Empty = true, Present = false
Optinal.empty
```

I get the same result as before, so that's good.
I'll copy these three lines of code,
and paste that just below.

```java  
Optional<Student> o2 = getStudent(students, "first");
System.out.println("Empty = " + o2.isEmpty() + ", Present = " + o2.isPresent());
System.out.println(o2);
```

I'll change _o1_ to _o2_, and this time,
I'll pass my _students_ list in.
I'll change _o1_ to _o2_
in the `System.out.println` statements as well.
Running this:

```html  
Empty = false, Present = true
Optinal.[Student{studentId=1, countryCode='US', yearEnrolled=2019, ageEnrolled=42, gender='U', programmingExperience=true, engagementMap={JMC=JMC: Nov 2021 Lecture 37 [18], PYMC=PYMC: Feb 2020 Lecture 34 [39]}}]
```

I get the opposite result, for this instance,
that _empty_ is **false**, and _present_ is **true**.
I've also got a **Student** in the optional container.
But what happens if one of my list elements is _null_?

```java  
students.add(0, null);
Optional<Student> o2 = getStudent(students, "first");
System.out.println("Empty = " + o2.isEmpty() + ", Present = " + o2.isPresent());
System.out.println(o2);
```

I'll add _null_ to the beginning of my list,
so I'll pass position 0 to the students dot add method,
and null as the value.
Running this code:

```html  
Empty = true, Present = false
Optional.empty
Exception in thread "main" java.lang.NullPointerException
    at java.base/java.util.Objects.requireNonNull
    at java.base/java.util.Optional.of
    at MainOptional.getStudent
    at MainOptional.main
```

I get another exception;
this one is another _NullPointerException_.
As it turns out,
you can't create an **Optional** using the _of_ method,
and pass it a _null_ value, which is what is happening here.

```java  
private static Optional<Student> getStudent(List<Student> list, String type) {
    if (list == null || list.size() == 0) {
        //return null;
        return Optional.empty();
    } else if (type.equals("first")) {
        //return Optional.of(list.get(0));
        return Optional.ofNullable(list.get(0));
    } else if (type.equals("last")) {
        //return Optional.of(list.get(list.size() - 1));
        return Optional.ofNullable(list.get(list.size() - 1));
    }
    return Optional.ofNullable(list.get(new Random().nextInt(list.size())));
}
```

Instead, I have to use the _ofNullable_ method,
so let me change the three places where I use the of method,
changing it to _ofNullable_, in each case.
Running this code with these changes,

```html  
Empty = true, Present = false
Optional.empty
Empty = true, Present = false
Optional.empty
```

I'll get an empty **Optional** back.
I'll remove that line of code
that adds a _null_ value to _students_.

```java  
//students.add(0, null);
Optional<Student> o2 = getStudent(students, "first");
System.out.println("Empty = " + o2.isEmpty() + ", Present = " + o2.isPresent());
System.out.println(o2);
```

If I run that:

```html  
Empty = true, Present = false
Optional.empty
Empty = false, Present = true
Optional.[Students{studentId=1, countryCode='GB', yearEnrolled=2017, ageEnrolled=87, gender='U', programmingExperience=true, engagementMap={JMC=JMC: Nov 2021 Lecture 37 [18], PYMC=PYMC: Feb 2020 Lecture 34 [39]}}]
```

I can see in the second case
that the **Optional** isn't _empty_ and has a value,
a student, but how do I go about accessing it?
The **Optional** type has a _get_ method, as you might expect.
Let's see what happens if I use it in both these cases.

```java  
System.out.println(o1.get());

//students.add(0, null);
Optional<Student> o2 = getStudent(students, "first");
System.out.println("Empty = " + o2.isEmpty() + ", Present = " + o2.isPresent());
System.out.println(o2);
        
System.out.println(o2.get());        
```

I'll add a `System.out.println` statement after
each of the other, passing `o1.get` in the first case,
and `o2.get` in the second one.
I'll move the `o2.get` to the right place after the **Optional**.
Again, I'll ignore IntelliJ's warnings, and run this:

```html  
Empty = true, Present = false
Optional.empty
Exception in thread "main" java.util.NoSuchElementException : No value present
    at java.base/java.util.Optional.get
    at MainOptional.main
```

I'm getting an exception, where I called `o1.get`.
You can only call the _get_ method
without getting this kind of exception
if _isPresent_ is **true**.
I'll comment this line out.

```java  
//System.out.println(o1.get());

//students.add(0, null);
Optional<Student> o2 = getStudent(students, "first");
System.out.println("Empty = " + o2.isEmpty() + ", Present = " + o2.isPresent());
System.out.println(o2);
        
System.out.println(o2.get());        
```

Running the code now:

```html  
Empty = true, Present = false
Optional.empty
Empty = false, Present = true
Optional[Student{studentId=1, countryCode='AU', yearEnrolled=2021, ageEnrolled=27, gender='M', programmingExperience=false, engagementMap={JMC=JMC: Eki 2022 Lecture 31 [18], PYMC=PYMC: Şub 2023 Lecture 35 [14]}}]
Student{studentId=1, countryCode='AU', yearEnrolled=2021, ageEnrolled=27, gender='M', programmingExperience=false, engagementMap={JMC=JMC: Eki 2022 Lecture 31 [18], PYMC=PYMC: Şub 2023 Lecture 35 [14]}}
```

I see that I do get the actual **Student** printed
when I call `o2.get`.
But what does this warning at `o2.get()` from IntelliJ say?
If I hover over that, I see the message,
_`Optional.get` without _isPresent_ check_.
Ok, that means I'm calling _get_
before checking the result of the method, _isPresent_.
I can fix this if I wrap that call in an if block,
first checking that _isPresent_ is **true**.

```java
Optional<Student> o2 = getStudent(students, "first");
System.out.println("Empty = " + o2.isEmpty() + ", Present = " + o2.isPresent());
System.out.println(o2);
if (o2.isPresent()) {
    System.out.println(o2.get());
}
```

One warning goes away, and another appears, this time
IntelliJ wants to tell me something about this conditional expression.
This code can be replaced with a single expression in functional style.

```java
Optional<Student> o2 = getStudent(students, "first");
System.out.println("Empty = " + o2.isEmpty() + ", Present = " + o2.isPresent());
System.out.println(o2);
o2.ifPresent(System.out::println);
```

I'll pick the option below that message,
replace **Optional** presence condition
with functional style expression.
IntelliJ replaces that if statement block,
with a single call, to an _ifPresent_ method,
on the **Optional** instance.
This method takes a **Consumer**,
so I can pass the usual `println` method reference.

```html  
Empty = true, Present = false
Optional.empty
Empty = false, Present = true
Optional[Student{studentId=1, countryCode='AU', yearEnrolled=2021, ageEnrolled=27, gender='M', programmingExperience=false, engagementMap={JMC=JMC: Eki 2022 Lecture 31 [18], PYMC=PYMC: Şub 2023 Lecture 35 [14]}}]
Student{studentId=1, countryCode='AU', yearEnrolled=2021, ageEnrolled=27, gender='M', programmingExperience=false, engagementMap={JMC=JMC: Eki 2022 Lecture 31 [18], PYMC=PYMC: Şub 2023 Lecture 35 [14]}}
```

I get the same output as before.
What happens if I copy that statement,
and paste a copy.

```java
Optional<Student> o1 = getStudent(new ArrayList<>(), "first");
System.out.println("Empty = " + o1.isEmpty() + ", Present = " + o1.isPresent());
System.out.println(o1);
o1.ifPresent(System.out::println);

Optional<Student> o2 = getStudent(students, "first");
System.out.println("Empty = " + o2.isEmpty() + ", Present = " + o2.isPresent());
System.out.println(o2);
o2.ifPresent(System.out::println);
```

I'll change _o2_ to _o1_.
Running this:

```html  
Empty = true, Present = false
Optional.empty
Empty = false, Present = true
Optional[Student{studentId=1, countryCode='UA', yearEnrolled=2015, ageEnrolled=56, gender='U', programmingExperience=false, engagementMap={JMC=JMC: Nis 2024 Lecture 36 [0], PYMC=PYMC: Ağu 2023 Lecture 33 [8]}}]
Student{studentId=1, countryCode='UA', yearEnrolled=2015, ageEnrolled=56, gender='U', programmingExperience=false, engagementMap={JMC=JMC: Nis 2024 Lecture 36 [0], PYMC=PYMC: Ağu 2023 Lecture 33 [8]}}
```

It's a little harder to see,
but I don't get any output from that call,
and that's because that optional is empty.
It didn't throw an exception though either.

```java
Optional<Student> o1 = getStudent(new ArrayList<>(), "first");
System.out.println("Empty = " + o1.isEmpty() + ", Present = " + o1.isPresent());
System.out.println(o1);
o1.ifPresentOrElse(System.out::println);

Optional<Student> o2 = getStudent(students, "first");
System.out.println("Empty = " + o2.isEmpty() + ", Present = " + o2.isPresent());
System.out.println(o2);
o2.ifPresent(System.out::println);
```

There isn't an _ifEmpty_ method on **Optional**,
but there is an _ifPresentOrElse_,
so I'll change that first call to that.
That gives me an error at the moment.
I'll click on that method;
then control click, to bring up that particular method's code.

```java
public void ifPresentOrElse(Consumer<? super T> action, Runnable emptyAction) {
    if (value != null) {
        action.accept(value);
    } else {
        emptyAction.run();
    }
}
```

Here, you can see this method takes a second parameter,
a **Runnable** called _emptyAction_.
I haven't covered **Runnable**, but let's follow the trail,
so I'll control click on **Runnable**.

```java
@FunctionalInterface
public interface Runnable {
    
    public abstract void run();
}
```

Here, you can see it's another functional interface,
so it's a target for lambda expressions.
It doesn't take any parameters, and it doesn't return any.
Getting back to my code:

```java
Optional<Student> o1 = getStudent(new ArrayList<>(), "first");
System.out.println("Empty = " + o1.isEmpty() + ", Present = " + o1.isPresent());
System.out.println(o1);
o1.ifPresentOrElse(System.out::println, () -> System.out.println("---> Empty"));

Optional<Student> o2 = getStudent(students, "first");
System.out.println("Empty = " + o2.isEmpty() + ", Present = " + o2.isPresent());
System.out.println(o2);
o2.ifPresent(System.out::println);
```

I'll simply add a lambda that prints some text,
I'll just print an arrow and the word Empty.
Running this:

```html  
Empty = true, Present = false
Optional.empty
---> Empty
Empty = false, Present = true
Optional[Student{studentId=1, countryCode='UA', yearEnrolled=2015, ageEnrolled=56, gender='U', programmingExperience=false, engagementMap={JMC=JMC: Nis 2024 Lecture 36 [0], PYMC=PYMC: Ağu 2023 Lecture 33 [8]}}]
Student{studentId=1, countryCode='UA', yearEnrolled=2015, ageEnrolled=56, gender='U', programmingExperience=false, engagementMap={JMC=JMC: Nis 2024 Lecture 36 [0], PYMC=PYMC: Ağu 2023 Lecture 33 [8]}}
```

You can see that this _Empty_ was printed
on the third line of output.
This method lets you have a single call,
whether optional is empty or not,
which helps promote consistent,
concise, and more readable code.
The _ifPresent_ and _ifPresentOrElse_methods provide a means
for doing something with your **Optional** instance.
They aren't used to retrieve the value from **Optional**,
or assign it to another local variable,
like the simple _get_ method does.
We can still use _get_, as I've shown you,
preferably with a test of the _isPresent_ method.
Let me show you a slightly different version.

```java
Student firstStudent =(02.isPresent() ? o2.get() : null);
long id = (firstStudent == null) ? -1 : firstStudent.getStudentId();
System.out.println("firstStudent's id is " + id);
```

I'll use a ternary operator, to test _isPresent_,
and if **true**,
I'll assign the value of the first _Student_
to the value in the **Optional** instance,
otherwise I'll assign that _null_.
Now, let's say I'm interested in the student's id,
so I'll retrieve that,
but I'm back having to check for nulls here.
I'll print the _id_ out.
I can run this as the code is written:

```html  
firstStudent's id is 1
```

And I'll get the output, first Student's id is 1.
IntelliJ again is warning me, or prompting me
to consider the way I've written this code.
If I hover over that highlighted code,
IntelliJ tells me,
I can _replace this with a functional style expression_,
so I'll select that link in this popup.

```java
//Student firstStudent =(02.isPresent() ? o2.get() : null);
Student firstStudent = o2.orElse(null);
long id = (firstStudent == null) ? -1 : firstStudent.getStudentId();
System.out.println("firstStudent's id is " + id);
```

That changes my code from the ternary,
to instead use a method called _orElse_.
I'll remove the enclosing parentheses,
since they were there to make the ternary easier to read.
This is a special kind of _get_,
if you will, that will get the value,
but if the value isn't present,
I can specify another default value, in this case _null_.
Let's say I don't want to return a _null_ **Student**,
as the other value,
but maybe some dummy variable.

```java
private static Student getDummyStudent(Course... courses) {
    
    System.out.println("Getting the dummy student");
    return new Student("NO", 1, 1, "U", false, courses);
}
```

For this, I'm going to create a quick private static method
that will return a dummy or fake **Student** instance.
I need a parameter for courses
because my **Student** constructor requires it.
I'm going to print out that
I'm in this method getting a dummy student.
I'll return a mocked-up **student** instance.

```java
//Student firstStudent =(02.isPresent() ? o2.get() : null);
//Student firstStudent = o2.orElse(null);
Student firstStudent = o2.orElse(getDummyStudent(jmc));
//long id = (firstStudent == null) ? -1 : firstStudent.getStudentId();
long id = firstStudent.getStudentId();
System.out.println("firstStudent's id is " + id);
```

Now, I'll change my code,
and instead of returning _null_,
I'll return the dummy instance.
Notice that there's a warning on the next line,
on the expression `firstStudent == null`.
IntelliJ has figured out that
_null_ is never going to be a valid option.
I'll hover over that, and select
the **Simplify** option there.
If I run this code:

```html  
Getting the dummy student
firstStudent's id is 1
```

It works the same, but with a notable difference.
Even though my optional instance has a value, a valid **Student**,
I see that the call to _getDummyStudent_ was made.
If I pass a method invocation as an argument,
as I'm doing in this code,
that method gets called, whether I need that value or not.
If you're processing data in a stream using an optional,
this could impact performance greatly.
The better alternative, in most cases,
is to use the _orElseGet_ method,
whose parameter is a target for a lambda expression.

```java
//Student firstStudent =(02.isPresent() ? o2.get() : null);
//Student firstStudent = o2.orElse(null);
//Student firstStudent = o2.orElse(getDummyStudent(jmc));
Student firstStudent = o2.orElseGet(() -> getDummyStudent(jmc));
//long id = (firstStudent == null) ? -1 : firstStudent.getStudentId();
long id = firstStudent.getStudentId();
System.out.println("firstStudent's id is " + id);
```

I'll change my code to use that instead.
I'll comment out the original _orElse_ statement.
I'll again set up my _firstStudent_ local variable,
and assign it to a method on my **o2** instance,
this time I'll call _orElseGet_.
This method takes a **Supplier**,
so I'll pass a lambda expression,
which calls the _getDummyStudent_ method, passing _jmc_.
Now, if I run this code:

```html  
Empty = true, Present = false
Optional.empty
---> Empty
Empty = false, Present = true
Optional[Student{studentId=1, countryCode='UA', yearEnrolled=2015, ageEnrolled=56, gender='U', programmingExperience=false, engagementMap={JMC=JMC: Nis 2024 Lecture 36 [0], PYMC=PYMC: Ağu 2023 Lecture 33 [8]}}]
Student{studentId=1, countryCode='UA', yearEnrolled=2015, ageEnrolled=56, gender='U', programmingExperience=false, engagementMap={JMC=JMC: Nis 2024 Lecture 36 [0], PYMC=PYMC: Ağu 2023 Lecture 33 [8]}}
firstStudent's id is 1
```

I don't get the message
_Getting the dummy student_.
The lambda expression's functional method
does not get invoked,
unless the optional is empty.

```java
public T orElse(T other) {
    return value != null ? value : other;
}

public T orElseGet(Supplier<? extends T> supplier) {
    return value != null ? value : supplier.get();
}
```

If I control click that method,
and scroll up a tiny bit,
I can see both the _orElse_
and the _orElseGet_ methods together on this screen.
Looking at these together,
hopefully makes it more obvious to you why,
that first one is executing _getDummyStudent_ in either case.
The _orElseGet_ method uses a ternary operator,
and you'll remember, with a ternary,
it's a special syntax for a conditional statement.
When one condition is met,
any code related to the other condition won't get executed or evaluated.
Getting back to the code,
there's one other thing
that's kind of interesting in the **Optional** class
I want to talk about.

```java
List<String> countries = students.stream()
        .map(Student::getCountryCode)
        .distinct()
        .toList();

Optional.of(countries)
        .map(l -> String.join(",", l))
        .filter(l -> l.contains("FR"))
        .ifPresentOrElse(System.out::println, () -> System.out.println("Missing FR"));
```

This class has methods that seem
to mirror some of the stream pipeline operations.
Let me set up a little code to show you this.
First, I'll use a stream operation
to create a list of strings,
to get distinct country codes for my list of students.
Now, I'll create an instance of **Optional**,
passing it that list of string.
The optional has a _map_ method,
so I can transform the value into something else,
so here, I'll join my list of strings
into a single comma delimited string.
**Optional** has a _filter_ method too,
so I'll check to see
if the value contains the country code **FR**.
This means if this is false,
the value is going to be empty.
I'll use _ifPresentOrElse_ to print either the country code list,
or the message that _FR_ is missing.
Running this code:

```html  
Empty = true, Present = false
Optional.empty
---> Empty
Empty = false, Present = true
Optional[Student{studentId=1, countryCode='UA', yearEnrolled=2015, ageEnrolled=56, gender='U', programmingExperience=false, engagementMap={JMC=JMC: Nis 2024 Lecture 36 [0], PYMC=PYMC: Ağu 2023 Lecture 33 [8]}}]
Student{studentId=1, countryCode='UA', yearEnrolled=2015, ageEnrolled=56, gender='U', programmingExperience=false, engagementMap={JMC=JMC: Nis 2024 Lecture 36 [0], PYMC=PYMC: Ağu 2023 Lecture 33 [8]}}
firstStudent's id is 1
Missing FR
```

You can see I get the message, _Missing FR_,
because _FR_ wasn't one of the country codes I used.
These chained methods look an awful lot like a stream pipeline,
but they're not.
Because **Optional** is the resulting type of various terminal operations,
these methods provide familiar functionality,
similar to streams, for the **optional**'s value.
I'll show you how to combine the pipeline stream I have here,
with the optional code, as we explore a few more
additional terminal and intermediate operations.

Some developers took the **Optional** class
and really ran with it,
to try to solve for that most common exception,
the _NullPointerException_.
It's kind of important to remember that
this type was designed to be a return type, from a method.
You should exercise some caution trying to use it elsewhere,
or for every getter or local variable, for example.

* Wrapping elements in **Optional** will consume more
  memory and has the possibility of slowing down execution.
* Wrapping elements in **Optional** adds complexity,
  and reduces readability of your code.
* Optional is not serializable.
  I haven't talked about this yet,
  and will cover it when we talk about IO streams.
* Using **Optional** for fields or method parameters is not recommended.

Ok, so now that we know what **Optional** is,
let's see how stream pipelines might use it.
</div>

## [l. Terminal Optional Operations]()
<div align="justify">

In this section, I'll be covering the final few terminal operations,
all of which return an **Optional**.

| Return Type     | Terminal Operations                     |
|-----------------|-----------------------------------------|
| OptionalDouble  | average()                               |
| Optional< T >   | findAny()                               |
| Optional< T >   | findFirst()                             |
| Optional< T >   | max(Comparator<? super T > comparator)  |
| Optional< T >   | min(Comparator<? super T > comparator)  |
| Optional< T >   | reduce(BinaryOperator< T > accumulator) |

I have average, listed first.
This operation is only available on the primitive streams, 
**IntStream**, **LongStream**, and **DoubleStream**.
It returns a special **Optional** type, 
**OptionalDouble**,
a container for a **double** primitive.
There's also _findAny_ and _findFirst_,
which are used in combination with a _filter_,
to get a single element back from the stream.
_Max_ and _min_ are somewhat self-explanatory.
Lastly, there's another form of the _reduce_ method 
that just takes an **accumulator**.
Let's see what we can do with these, in some code.
I'll again be using the **StreamingStudents** project.
I'll create another class, **MainTerminalOptional**,
so we have a fresh start,
and that'll have a _main_ method.

```java  
public class MainTerminalOptional {

    public static void main(String[] args) {

        Course pymc = new Course("PYMC", "Python Masterclass");
        Course jmc = new Course("JMC", "Java Masterclass");

        List<Student> students =
                Stream.generate(() -> Student.getRandomStudent(jmc, pymc))
                        .limit(1000)
                        .toList();

     int minAge = 21;
     students.stream()
             .filter(s -> s.getAge() <= minAge)
             .findAny()
             .ifPresentOrElse(s -> System.out.printf("Student %d from %s is %d%n",
                             s.getStudentId(), s.getCountryCode(), s.getAge()),
                     () -> System.out.println("Didn't find anyone under " + minAge));
    }
}
```

I'll switch over to **MainCollect**,
and copy lines 14 through 20.
Coming back to **MainTerminalOptional**,
I'll paste that code in the _main_ method.
After this, I'll start with a local variable called _minAge_, and set that to 21.
I'll start a stream, using my _students_ list.
I'll _filter_, by testing 
if student's current age is less than 
or equal to the minimum age.
Next, I'll use the terminal operation, _findAny_.
It doesn't take any arguments. 
At this point, I have an optional result.
I can chain methods on optional, 
so I'll invoke _ifPresentOrElse_. 
This takes two parameters.
The first is what gets executed, 
if the resulting stream had at least one value.
The second parameter gets executed 
if the stream is empty. 
In the first instance, 
I'll print the student id, the country code, 
and the student age, of the one student that gets returned.
Otherwise, I'll print out that 
there were not any student records found, 
for anyone under that min age.
You can see, I don't have to assign anything to local variables, 
or check for nulls.
I can print information, from what looks like a seamless pipeline of code.
If I run this:

```html  
Student 52 from CN is 21
```

I'll usually get a student id, with the country code and age.
If I run it multiple times, 
I'll get different data each time.
The _findAny_ method returns any student on this stream.
It's not guaranteed to be the first student found 
in the stream's processing order.
If you need the first in the stream order,
you should use the _findFirst_ method instead.
I'll copy the stream code, and paste that on below.

```java  
students.stream()
        .filter(s -> s.getAge() <= minAge)
        .findFirst()
        .ifPresentOrElse(s -> System.out.printf("Student %d from %s is %d%n",
                        s.getStudentId(), s.getCountryCode(), s.getAge()),
                () -> System.out.println("Didn't find anyone under " + minAge));
```

I'll change _findAny_, to _findFirst_.
If I run this code:

```html  
Student 66 from US is 20
Student 66 from US is 20
```

I'll likely get the same value
as the first pass through.
Again, you can't count on _findAny_ 
to always return the first stream element,
so if you absolutely need the first instance, 
make sure you use _findFirst_.
I'll make another copy of the same code.

```java  
students.stream()
        .filter(s -> s.getAge() <= minAge)
        .sorted(Comparator.comparing(Student::getAge))
        .findFirst()
        .ifPresentOrElse(s -> System.out.printf("Student %d from %s is %d%n",
                        s.getStudentId(), s.getCountryCode(), s.getAge()),
                () -> System.out.println("Didn't find anyone under " + minAge));
```

Let's now say I want the youngest student,
so I'll add a _sorted_ intermediate operation, 
before the _findFirst_.
Because my **Student** class doesn't implement **comparable**, 
I need to pass a **Comparator**.
I'll do that with `Comparator.comparing`,
using the `Student::getAge` method reference.
This code will run:

```html  
Student 8 from US is 20
Student 8 from US is 20
Student 151 from UA is 19
```

And in my case,
I'll get the youngest student,
rather than the student with the lowest student id 
who's under 21.
IntelliJ has something to say about this code, 
which is why it's highlighted, so let me review that.
This popup tells me that this code,
the _findFirst_ operation, _can be replaced with **min**_.
I'll choose to replace that with _min_.

```java  
students.stream()
        .filter(s -> s.getAge() <= minAge)
        .min(Comparator.comparing(Student::getAge))
        .ifPresentOrElse(s -> System.out.printf("Student %d from %s is %d%n",
                        s.getStudentId(), s.getCountryCode(), s.getAge()),
                () -> System.out.println("Didn't find anyone under " + minAge));
```

It's a little easier to see now 
that both the _sorted_ and _findFirst_ operations 
aren't in this stream pipeline anymore.
Instead, _sorted_ has been removed,
and _findFirst_ has been replaced
with this single terminal operation,
and that takes a **Comparator**, like _sorted_ did.
The _min_ and _max_ operations always requires a **Comparator**.
Running this:

```html  
Student 131 from GB is 21
Student 131 from GB is 21
Student 151 from CA is 18
```

I get the same behavior, 
but you can see why this is a better alternative.
It's much easier to understand the intention of the stream pipeline.
I'll copy that last segment code again.

```java  
students.stream()
        .filter(s -> s.getAge() <= minAge)
        .max(Comparator.comparing(Student::getAge))
        .ifPresentOrElse(s -> System.out.printf("Student %d from %s is %d%n",
                        s.getStudentId(), s.getCountryCode(), s.getAge()),
                () -> System.out.println("Didn't find anyone under " + minAge));
```

This time, I want to get the oldest student, 
who is less than 21, so I can replace _min_ with _max_.
Running that code:

```html  
Student 14 from IN is 19
Student 14 from IN is 19
Student 92 from US is 18
Student 73 from CN is 21
```

I should get a student from my group who's closer to 21, 
or who is exactly 21. 
Let's next get the average age of the student's under 21.
I'll start out the same, 
so I'll copy the first two statements of my last pipeline.

```java  
students.stream()
        .filter(s -> s.getAge() <= minAge)
        .mapToInt(Student::getAge)
        .average()
        .ifPresentOrElse(a -> System.out.printf("Avg age under 21: %.2f%n", a),
                        ()-> System.out.println("Didn't find anyone under " + minAge));
```

This time, I want to map to an **IntStream**, using age, 
so I'll add a _mapToInt_ operation.
I'll pass the method reference, `Student::getAge`. 
Because now I have an **IntStream**,
I can use the _average_ terminal operation.
That operation returns an **OptionalDouble**,
which has the same methods on it, 
as an optional, so I can still use _ifPresentOrElse_.
I'll print the average under 21 there, 
with the value, formatting that to two decimals.
Otherwise, I'll print that I did not find anyone under 21.
Running that code:

```html  
Student 296 from US is 19
Student 296 from IN is 19
Student 885 from GB is 18
Student 579 from GB is 21
Avg age under 21: 19.57
```

I can see I get a decimal value back,
and that the average age is usually somewhere between 20 and 21.
The last terminal operation I want to show you
is a different form of the _reduce_ operation.
This is a single parameter version that returns an **Optional**.
I'll use it to give me
the countries of my under 21 population,
in a single comma delimited list.
I'll start out the same, with a stream from students,
and filtering by age less than
or equal to the _minAge_,
so I'll copy the first two statements of my previous pipeline.

```java  
students.stream()
        .filter(s -> s.getAge() <= minAge)
        .map(Student::getCountryCode)
        .distinct()
        .reduce((a, b) -> String.join(",", a, b))
        .ifPresentOrElse(System.out::println,
                        () -> System.out.println("None found"));
```

I'll chain a map to that, 
to extract the country code.
I've said I want a distinct list, 
so I'll add _distinct_.
I'll execute the _reduce_ method 
that takes one parameter, a **BinaryOperator**.
A **BinaryOperator** function takes two parameters of the same type.
In this case, it's the type from the stream, so a **string**.
This method accumulates a value in memory, 
using the function I specify here.
I'll use `String.join`, to join countries by a comma. 
If something comes back, meaning optional has a value, 
I'll print it. 
Otherwise, I'll print _None found_.
Let me run this.

```html  
Student 12 from US is 21
Student 12 from US is 21
Student 555 from GB is 18
Student 12 from US is 21
Avg age under 21: 20.10
US,GB,IN,AU,CA,CN
```

I should get some country codes printed out
in a comma-delimited list.
If I run this several times,
my country codes will change.
What happens if I make the minAge 17, instead of 21, up on line 19?
I'll go and change that.

```java  
int minAge = 17;
```

```html  
Didn't find anyone under 17
Didn't find anyone under 17
Didn't find anyone under 17
Didn't find anyone under 17
Didn't find anyone under 17
None found
```

I shouldn't ever get students that match these criteria, 
so all the _orElse_ functions will get called 
for all of these pipeline operations.
If I make that 18 next:

```java  
int minAge = 18;
```

```html  
Didn't find anyone under 18
Didn't find anyone under 18
Didn't find anyone under 18
Didn't find anyone under 18
Didn't find anyone under 18
None found
```
I should be able to test both scenarios
if I run this code multiple times.
Sometimes I'll get students and a list of country codes.
Sometimes I'll get the text, didn't find anyone under 18,
and none found as the final output statement.

```html  
Student 735 from CA is 18
Student 735 from CA is 18
Student 735 from CA is 18
Student 735 from CA is 18
Student 735 from CA is 18
Avg age under 21: 18.00
CA
```

Now, let's take the code from the previous section 
in the **MainOptional** class's _main_ method.
I want the code from lines 38 to 47,
and I'm going to paste that at the end
of **MainTerminalOptional**'s _main_ method.

```java  
List<String> countries = students.stream()
        .map(Student::getCountryCode)
        .distinct()
        .toList();

Optional.of(countries)
        .map(l -> String.join(",", l))
        .filter(l -> l.contains("FR"))
        .ifPresentOrElse(System.out::println,
                        () -> System.out.println("Missing FR"));
```

I'm going to combine these two sections into a single statement.

```java  
students.stream()
        .map(Student::getCountryCode)
        .distinct()
        .map(l -> String.join(",", l))
        .filter(l -> l.contains("FR"))
        .findAny()
        .ifPresentOrElse(System.out::println, () -> System.out.println("Missing FR"));
```

First, I'll remove the assignment to the local variable.
I'll remove lines 67 to 69,
so I'm removing the _toList_ terminal operation, 
and that `Optional.of` code.
This code has one thing missing a terminal operation 
before the _ifPresentOrElse_ call.
I can actually use any of the terminal operations, 
I've showed you in this section here.
In this case, it probably just makes sense 
to use _findAny_, 
since I'm really just concerned with understanding
if any of my younger students are from France.
If I run this code:

```html  
Student 221 from CA is 18
Student 221 from CA is 18
Student 221 from CA is 18
Student 221 from CA is 18
Student 221 from CA is 18
Avg age under 21: 18.00
IN,GB,CA
Missing FR
```

I should see _Missing FR_, 
since FR wasn't one of my random country codes.

```java  
students.stream()
        .map(Student::getCountryCode)
        .distinct()
        .map(l -> String.join(",", l))
        .filter(l -> l.contains("AU"))
        .findAny()
        .ifPresentOrElse(System.out::println, () -> System.out.println("Missing AU"));
```

I'll change _FR_ to _AU_ here, 
first in the contains statement, 
and next, in the _println_ statement.
I'll rerun that:

```html  
Student 239 from CA is 18
Student 239 from CA is 18
Student 239 from CA is 18
Student 239 from CA is 18
Avg age under 21: 18.00
AU,UA
AU
```

And hopefully get an under 18 student from Australia.
I'll keep rerunning that until I do.
The methods of **Optional** let you process 
this instance with similar methods, 
almost like it's just another part of the stream pipeline,
though of course, it's not.
Ok, so those were the last few terminal operations,
and how to use them, with the **Optional** type.
</div>

## [m. Streams to Maps]()
<div align="justify">

When I showed you intermediate operations in a previous section, 
I covered the _collect_ method.
I showed you examples of returning a list,
and a **TreeSet**, ordered by a **Comparator**.
In this section, I want to show you
how to create a **Map** from a **stream**,
using another method on the **Collectors** helper class.

I'll again be using **StreamingStudents**, 
and I'll start with a **MainMapping** class for these examples.
I'll copy the code from the **MainChallenge** class, 
so lines 11 through 18, to get a list of 5000 students.
I'll paste this in my new class's _main_ method.

```java  
public class MainMapping {

    public static void main(String[] args) {

        Course pymc = new Course("PYMC", "Python Masterclass", 50);
        Course jmc = new Course("JMC", "Java Masterclass", 100);
        Course jgames = new Course("JGAME", "Creating games in Java");

        List<Student> students = IntStream
                .rangeClosed(1, 5000)
                .mapToObj(s -> Student.getRandomStudent(jmc, pymc))
                .toList();

     var mappedStudents = students.stream()
             .collect(Collectors.groupingBy(Student::getCountryCode));

     mappedStudents.forEach((k, v) -> System.out.println(k + " " + v.size()));
    }
}
```

I'll use this list of students to create a map, 
that'll be keyed by country code,
and have a **List** of students for each country.
This is actually really easy, 
thanks to methods on the **collectors** class.
I'll use _var_ as my type, in the next couple of examples.
Java can figure the type out, 
and I think the code just looks a lot cleaner,
without all the generic typing going on.
I'll call my variable, _mappedStudents_, 
and start out with a stream from the _students_ list.
I'll use the _collect_ method, 
then call a static method on **Collectors**, 
this one is called _groupingBy_. 
It takes a lambda expression, and here, 
I'm just going to pass it the method reference 
for _getCountryCode_ on **student**.
I'll loop through the key value pairs, 
on the resulting type, and print them out.
First, look at the inlay hint,
after the variable _mappedStudents_.
My type is a **map**, with **String** as a key,
and a **List** of **Students** as the entry value.
The `Collectors.groupingBy` method returns a **Collector**, 
that collects data into a map.
This map gets keyed by the result of the **Function** lambda we provide, 
so it's keyed by country code.
Running this code, 

```html  
AU 721
IN 709
GB 717
CN 729
UA 724
CA 699
US 701
```

I'll be able to see how many students I have in each country.
Ok, so I think that's pretty neat and super easy to do.
Let's try another, this time getting the same map,
but this time only for students age 25 or under.

```java  
System.out.println("-----------------------");
int minAge = 25;
var youngerSet = students.stream()
        .collect(groupingBy(Student::getCountryCode, filtering(s -> s.getAge() <= minAge , toList())));

youngerSet.forEach((k, v) -> System.out.println(k + " " + v.size()));
```

This code won't compile at first, but let me start it.
I'll first print a separator line.
After that, I'll set up a local variable, 
named _minAge_ and set it to 25.
Next, _var_, and a variable named _youngerSet_,
and starting with a stream from _students_.
I'll start as before, calling _collect_. 
I'll use _groupingBy_ again, with the _getCountryCode_ method reference.
Now, I'm including a second parameter to the _groupingBy_ method.
This one expects a **predicate**, 
and a collector's method to be executed, if that is true.
This code doesn't compile.
Do you know why not?
It's because IntelliJ can't figure out what methods these are, 
_groupingBy_ and _filtering_.
I haven't qualified them with a type name like I did on line 21, 
when I put `Collectors.groupingBy` there.
I'm doing this on purpose.
To fix this, I can use a static import,
so that my code is easier to read.
I've covered how to import other classes into your package, 
using the import statement,
so we can refer to types in other packages,
without having to use a fully qualified name.
`import static java.util.stream.Collectors.*`
The static import statement is a similar idea.
It lets you import one or more static members of a class.
The **Collectors** class has well over 40 static methods on it.
I can fully qualify each method call, 
with the **Collectors** type name, 
as you've seen me do so far.
This can get a little tedious,
and some would argue the code would be a lot more readable without it.
I can use the static import statement,
specifying the **Collectors** class, 
with a wildcard to import all static members.
I want to show you this next, 
in case you're looking up examples in Java's documentation or elsewhere,
and see code samples this way.

```java  
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import static java.util.stream.Collectors.*;

public class MainMapping {
    public static void main(String[] args) {

        Course pymc = new Course("PYMC", "Python Masterclass", 50);
        Course jmc = new Course("JMC", "Java Masterclass", 100);
        Course jgames = new Course("JGAME", "Creating games in Java");

        List<Student> students = IntStream
                .rangeClosed(1, 5000)
                .mapToObj(s -> Student.getRandomStudent(jmc, pymc))
                .toList();

        var mappedStudents = students.stream()
                .collect(Collectors.groupingBy(Student::getCountryCode));

        mappedStudents.forEach((k, v) -> System.out.println(k + " " + v.size()));

        System.out.println("-----------------------");
        int minAge = 25;
        var youngerSet = students.stream()
                .collect(groupingBy(Student::getCountryCode, filtering(s -> s.getAge() <= minAge, toList())));

        youngerSet.forEach((k, v) -> System.out.println(k + " " + v.size()));
    }
}
```

I'll add this on line 6, after all the other import statements.
In this case, I'll do a static **import** of everything in the **Collectors** class.
I can do this by using a wildcard, so `.*`.
Scrolling back down, you can see that the last bit of code compiles.
Now, I want to explain this line of code, line 29, I mean.
In this case, I'm using an overloaded version of the _groupingBy_ method, on **Collectors**.
This has a second parameter, which is a **Collector** type.
This means I can use any of the methods on **Collectors** that return a **Collector** type,
and this time, I'm using one called _filtering_.
_Filtering_ takes a **predicate** as its first argument, and another **Collector** as its second.
You can see this could go on for a while, this nesting of methods with **Collector** types, 
inside of one another.
Here, I'm making the **predicate** to check that age is less than 
or equal to the min age. 
I'm returning `Collectors.toList` method, as the collector on this.
I'll copy that print statement on line 24, and paste it on line 32, 
and I'll change that from _mappedStudents_, to _youngerSet_.
I'll run this:

```html  
-----------------------
AU 50
IN 36
GB 43
CN 39
UA 42
US 32
CA 35
```

And now I get the counts by country, for my students who are 25 years or younger.
Now, imagine being able to read data from a file or database, 
and running a couple of these simple stream pipelines,
to give you information about the data.
Some of you probably know how to do this, with operating system commands,
or database commands, but it's nice to know;
you can query data so easily like this.
I want to show you two other commonly used collectors that return maps.

```java  
var experienced = students.stream()
        .collect(partitioningBy(Student::hasProgrammingExperience));
System.out.println("Experienced Students = " + experienced.get(true).size());
```

The next one returns a **Map** of **boolean** values, so
you can split your population into two buckets,
a bifurcated map, based on any condition of your choice.
We can do this with the _partitioningBy_ method, on **Collectors**.
I'll set up a new variable, _experienced_, 
and use a stream on _students_. 
I'll use _collect_, pass it _partitioningBy_ this time, 
another static method on the **Collectors** class. 
I'll pass the method reference for **Student**, 
_hasProgrammingExperience_.
I'll print out how many experienced students I have, 
by using true as the key, to the resulting map, 
which was partitioned with a **Boolean** wrapper.
Running this:

```html  
-----------------------
AU 35
IN 31
GB 38
CN 37
UA 36
US 47
CA 44
Experienced Students = 2497
```

I'll get the number of students who said they had previous programming experience.
This is usually a number, somewhere near the mid-point of my population, 
so 2500 more or less.
Let's say instead of a map that has a **List** of **Students**, 
I really just want counts.
There is another method, called counting on the **Collectors** class.
I can use the overloaded version of _partitioningBy_, 
that takes a **Collector** as it's second argument.
I'll copy lines 34 through 36, and paste that just below.

```java  
var expCount = students.stream()
        .collect(partitioningBy(Student::hasProgrammingExperience, counting()));
System.out.println("Experienced Students = " + expCount.get(true));
```

I'll change experience to be _expCount_.
I'll include a second argument to the _partitioningBy_ method,
passing it counting with parentheses there.
Remember that counting is a method on **Collectors**.
In the output, I'll change _experienced_ to _expCount_, 
and remove `.size()` there.
I get the same result if I run it:

```html  
-----------------------
AU 45
IN 46
GB 45
CN 42
UA 38
US 40
CA 37
Experienced Students = 2488
Experienced Students = 2488
```

As I did with my map of lists of _students_, 
but maybe I don't really need all those lists in a map.
Instead, I can just get counts like this.
The _partitioningBy_ method takes a **Predicate** expression.
In this example, I used a method reference,
but I could also pass any expression I want, 
that evaluates to a **boolean** value.
Let's say I want to see _student_'s that have experience 
that were active in the current month.
I'll copy lines 38 through 40, and paste a copy below that.

```java  
var experiencedAndActive = students.stream()
        .collect(partitioningBy(
                s -> s.hasProgrammingExperience() && s.getMonthsSinceActive() == 0,
                counting()));
System.out.println("Experienced and Active Students = " + experiencedAndActive.get(true));
```

I'll change the name of the variable to _experiencedAndActive_.
I'll add a new line after the opening parentheses after _partitioningBy_.
Instead of a method reference, I want a more complex expression, 
so I'll make this a lambda.
I'll include _s_, the arrow token, and here, 
I'll include an expression to check both _hasExperience_,
and also if `getMonthsSinceActive() == 0` on a new line.
Finally, I'll change the output, and put _experiencedAndActive_ there.
I'll replace _expCount_ with _experiencedAndActive_.
If I run that:

```html  
-----------------------
AU 39
IN 49
GB 24
CN 48
UA 44
US 39
CA 50
Experienced Students = 2471
Experienced Students = 2471
Experienced and Active Students = 790
```

I get a count of my experienced and recently active students.
Lastly, I want to show you how to get a multi-leveled map.
I'll start with a variable called _multiLevel_, and start another stream.

```java  
var multiLevel = students.stream()
        .collect(groupingBy(Student::getCountryCode, groupingBy(Student::getGender)));

multiLevel.forEach((key, value) -> { System.out.println(key);
            value.forEach((key1, value1) -> System.out.println("\t" + key1 + " " + value1.size()));
        });
```

I can pass _groupingBy_ as the first parameter, 
with a method reference to _getCountryCode_. 
I can pass a nested _groupingBy_, as the second parameter,
which will return a **Map** within each **Country**.
In this case, the nested _map_ will be keyed on gender.
Printing this out is a little more complicated.
I'll use for each on the _multiLevel_ variable,
and that takes key and value.
I'll print the key.
The value I get back is another map, 
so I'll repeat this process, using _forEach_ on the value.
I'll indent, printing the key and value of the nested map.
If I run this:

```html  
AU
    U 221
    F 237
    M 256
IN
    U 221
    F 227
    M 248
CN
    U 226
    F 244
    M 249
GB
    U 251
    F 218
    M 252
UA
    U 229
    F 235
    M 244
CA
    U 241
    F 237
    M 227
US
    U 245
    F 233
    M 259
```

I get a better picture of my student population, 
first by country, then by gender.
There are many variations of the **Collectors** methods to use,
and it's not possible to cover them all in a single section.
Let me pull the API document up for this class, and select methods.
Most of these make sense to use 
when you're dealing with mapped collections.

![image09](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_13_Streams/images/image09.png?raw=true)

There are usually simpler reduction operations for the other collections.
Let's read some of these method names 
to get an idea of the kinds of things you could do.
There are several averaging methods, so imagine a map keyed by country,
and getting the average age of enrollment by each country.
I showed you counting, and _filtering_.
This filtering 
`filtering(Predicate< ? super T> predicate, Collector <? super T, A, R> downstream)`
occurs within a grouped segment which is different from _filtering_ on every stream element.
We've also looked at _groupingBy_, in this section,
but here's _joining_, much like **String** _joining_, 
concatenating strings together.
Here are _maxBy_ and _minBy_, so you can imagine something like
getting the maximum percentage complete of each course,
based on the student population you have.
I'll encourage you to explore the many methods in this class.
The samples I've shown you here, in this code,
will take you a pretty good distance with whatever you're trying to do.
At some point, though, you might be having a disability 
with something a bit more complex. 
You might find just the right solution 
by using one of these other methods or variations.
I'll continue to show you as many variations as possible, 
as the course continues, 
where the context presents an opportunity to do that.
</div>

## [n. Maps to Streams]()
<div align="justify">

In the last section, I showed you many different ways to get maps from a stream.
There's one more intermediate operation I want to cover
before I finish this **Streams** section of the course, and that's the **flatMap**.

The **flatMap** intermediate operation performs one-to-many transformations
on elements in a stream pipeline.
It's called **flatMap**,
because it flattens results from a hierarchical collection
into one stream of uniformly typed elements.

```java  
| Stream<R> | map(Function<T, R> mapper) | flatMap(Function<T, Stream> mapper) |
```

Above, I'm showing the **flatMap** operation, next to the **map** operation, 
with some of the generic typing removed for clarity.
You can see these operations look very similar.
The difference is in the return type of the function.
For **map**, you return a different instance of an object.
In this case, you're exchanging one type, for another, 
for each element on the stream.
For **flatMap**, you return a **Stream**, 
which means you're exchanging one element,
for a stream of elements back.

Let's see why this is a big convenience, and examine it in the context of some code.
I'm still using the **StreamingStudents** project,
and the **MainMapping** class from the previous section.
I left off in my code, with several different maps of my students.

```java  
public class MainMapping {

    public static void main(String[] args) {

        Course pymc = new Course("PYMC", "Python Masterclass", 50);
        Course jmc = new Course("JMC", "Java Masterclass", 100);
        Course jgames = new Course("JGAME", "Creating games in Java");

        List<Student> students = IntStream
                .rangeClosed(1, 5000)
                .mapToObj(s -> Student.getRandomStudent(jmc, pymc))
                .toList();

        var mappedStudents = students.stream()
                .collect(Collectors.groupingBy(Student::getCountryCode));

        mappedStudents.forEach((k, v) -> System.out.println(k + " " + v.size()));

        System.out.println("-----------------------");
        int minAge = 25;
        var youngerSet = students.stream()
                .collect(groupingBy(Student::getCountryCode, filtering(s -> s.getAge() <= minAge, toList())));

        youngerSet.forEach((k, v) -> System.out.println(k + " " + v.size()));

        var experienced = students.stream()
                .collect(partitioningBy(Student::hasProgrammingExperience));
        System.out.println("Experienced Students = " + experienced.get(true).size());

        var expCount = students.stream()
                .collect(partitioningBy(Student::hasProgrammingExperience, counting()));
        System.out.println("Experienced Students = " + expCount.get(true));

        var experiencedAndActive = students.stream()
                .collect(partitioningBy(s -> s.hasProgrammingExperience() && s.getMonthsSinceActive() == 0, counting()));
        System.out.println("Experienced and Active Students = " + experiencedAndActive.get(true));

        var multiLevel = students.stream()
                .collect(groupingBy(Student::getCountryCode, groupingBy(Student::getGender)));

        multiLevel.forEach((key, value) -> {
            System.out.println(key);
            value.forEach((key1, value1) ->
                    System.out.println("\t" + key1 + " " + value1.size()));
        });
    }
}
```

I have a map of country code, with lists of students in each of those buckets.
I have a bifurcated map, partitioned by a boolean value, 
with true representing my experienced students.
I also have a map of nested maps, the top level key is country code,
and the second level is gender.
Now let's imagine this is how the data was delivered to us.
It might have a couple of elements, 
or it might have many millions or billions of records.

```java  
long studentCount = students.stream().count();
```

Let's start with counting how many elements we've got.
First of all, you don't want to use streams for functionality;
that can be easily deduced without them.
Let me show you an example of that first.
Let's say I'm a new developer and I just really like streams, 
and I'm going to use them everywhere.
I'll set up a new local variable, a long, called _studentCount_,
and I'll use the count terminal operation on my students, by getting a stream first.
Notice that IntelliJ has highlighted the count operation.
Hovering over that, I get the message that 
_this can simply be replaced with `Collection.size()`_.
Why would I use count on a stream, 
when I can just use the size method, on the _students_ list?
Sometimes, it's easy to forget what's a stream,
and what isn't, so luckily for us, 
IntelliJ gives us reminders to pay attention.
I'll revert the code, removing that last statement.
But let's say I'm getting the data, in the experienced map,
from a method, and I want to know how many students are in it.
Getting just the size of that is just going to give me 2,
because I have two keys in my map, **true** and **false**.
I could get the count by looping through the values in my map,
and accumulating the counts from the sizes that way.

```java  
long studentBodyCount = 0;
for (var list : experienced.values()) {
    studentBodyCount += list.size();
}
System.out.println("studentBodyCount = " + studentBodyCount);
```

I'll add that just for clarity, and pretend we don't know the size.
I'll set up a variable that will keep a running count,
_studentBodyCount_, and initialize it to zero.
I'll loop through the values, and add the size to my _studentBodyCount_.
And I'll print that out.

```html  
studentBodyCount = 5000
```

This code gives me the right count, which we really do know is 5000.
How would I go about getting the count of students 
who've been active in the last 3 months, using streams?
That's a little more difficult to answer.
First of all, maps don't have a stream method, 
so you have to pick a view to stream on, 
this could be the keys, or the values, or the entries.
Let's first count our students on the map, using streams, without _filtering_.
Doesn't this appear fairly straightforward?
It's not as straightforward as you'd expect.
If you try to use _count_, you probably figured out,
it's hard to get a number other than two,
the two elements keyed in your map, even if these are lists.
I'll use the _sum_ operation on **IntStream** to do this.

```java  
studentBodyCount = experienced.values().stream() //Stream<List<...>>
        .mapToInt(l -> l.size()) //IntStream
        .sum();
System.out.println("studentBodyCount = " + studentBodyCount);
```

I'll reassign the value I get from my pipeline to the _studentBodyCount_. 
I'll call _mapToInt_, and map each value to an integer, 
the size of the students in that keyed list.
I can use _sum_, because I now have an int stream.
I'll copy _println_, and paste it here.
Running this code:

```html  
studentBodyCount = 5000
studentBodyCount = 5000
```

I do get the right number of students, 5000 for the result, 
and that wasn't too awful.
I myself like it better than the for loop above it.
Just for the record, for the rest of this section,
I'm purposely going to ignore IntelliJ's hints, 
to turn _lambdas into method references_.
I feel like this code will be complex enough,
without adding that extra level of indirection.

Ok, now the question is, 
how I would filter my students by an attribute on student in this code?
At no time, do I have a stream of students, on which I can act.
Because _map_ is one to one, 
I can only examine the collection or list of students as a whole,
or transform them into another singular unit.
What if I mapped to a stream?
I'll try that next.

```java  
studentBodyCount = experienced.values().stream() //Stream<List<...>>
        .map(l -> l.stream()  //Stream<Stream<Student>>
        .mapToInt(l -> l.size())  //size() error
        .sum();
System.out.println("studentBodyCount = " + studentBodyCount);
```

I'll insert an extra _map_ operation above **mapToInt**.
I'll return a stream from the **List** of students I have here.
First of all, this doesn't compile,
but let's ignore that for a moment,
and examine IntelliJ's inlay hints.
Notice what I get back from the _map_ operation.
I don't get a simple **Stream** of **Students**, 
I get a **Stream**, which has a **Stream** of _students_.
It's really still a one to one exchange.
I'm still getting one element back,
it just happens to be a stream.
I get an error on the _mapToInt_ operation,
because stream doesn't have a size.
I can use this stream, though, 
to reduce the elements in the stream to a single value,
and even filter my students here.
Let me show you that.

```java  
studentBodyCount = experienced.values().stream() //Stream<List<Student>>
        .map(l -> l.stream()  
            .filter(s -> s.getMonthsSinceActive() <= 3)
            .count()) //Stream<Long>
        .mapToInt(l -> l.size()) //IntStream, size() error
        .sum();
System.out.println("studentBodyCount = " + studentBodyCount);
```

First, I'll add a new line after the call to stream.
I'll add a filter, and I only want students
who have been active in the last 3 months.
I'll use count to return how many there are.
I still have a problem with _mapToInt_.

```java  
studentBodyCount = experienced.values().stream() //Stream<List<Student>>
        .map(l -> l.stream()  
            .filter(s -> s.getMonthsSinceActive() <= 3)
            .count()) //Stream<Long>
        //.mapToInt(l -> l.size())
        .mapToLong(l -> l) //LongStream
        .sum();
System.out.println("studentBodyCount = " + studentBodyCount);
```

Here, I really want this to be _mapToLong_, 
since that's what I get back from the _map_ operation.
Now, I can just return the value on the stream, which is a **long**.
That compiles and runs.

```html  
studentBodyCount = 5000
studentBodyCount = 5000
studentBodyCount = 2162
```

I do get a count for the number of students 
who've had some activity in the last 4 months.
I don't know about you, but this code feels convoluted and ugly to me.
Imagine now if I wanted the same information,
but for a map like my multi-level map.
I'm not going to code that, I think you've got the picture.
What I'll do is this same thing,
but use the _flatMap_ operation instead.

```java  
long count = experienced.values().stream() //Stream<List<...>>
        .flatMap(l -> l.stream()) //Stream<Student>
        .filter(s -> s.getMonthsSinceActive() <= 3)
        .count();
System.out.println("Active Students = " + count);
```

First, I'll set up a local variable, a **long**,
called _count_, and I'll assign that the result of this pipeline process.
I'll start the same way, with `experienced.values.stream`.
Now I'll call _flatmap_, rather than _map_,
and get a stream from each of my list values.
I'll filter, as part of the main stream's pipeline.
I'll use the terminal operation count here. 
And I'll print the value I get back.
Before I run this, let's examine the inlay hints again.
Notice what comes back from _flatMap_.
My **Stream** which contained a bunch of **Lists**,
now contains Students instead, 
and not a stream of a stream of students.
This means I'm getting back not just one element,
but instead this could be zero to many elements back, 
and all knitted together on one stream.
This operation is called _flatMap_, 
because I've flattened my tree structure into a simple list of **Students**.
It feels a little backwards sometimes,
because you're getting many elements back,
but think about it as flattening the hierarchy of your source data.
If I run this code:

```html  
studentBodyCount = 5000
studentBodyCount = 5000
studentBodyCount = 2186
Active Students = 2186
```

I get the same results as before, as I did from the uglier map example.
Now, I'll try to do this for the multi-level map.

```java  
count = multiLevel.values().stream() //Stream<Map<String, List<...>>>
        .flatMap(map -> map.values().stream()) //Stream<List<Student>>
        .filter(s -> s.getMonthsSinceActive() <= 3)
        .count();
System.out.println("Active Students in multiLevel = " + count);
```

I'll copy that last bit of code, 
everything but the **long** type there, and paste it,
I'll change _experienced_, to _multiLevel_.
In the _flatMap_ operation, 
I'll change the variable _l_, to _map_,
so it's a bit more understandable,
and I'll change `l.stream` to `map.values.stream`.
This code doesn't compile,
and if you look at the inlay hints, you can see,
coming back from the _flatMap_ operation,
I don't have a **Stream** of **Students**.
I have a **Stream** of **List**.
I haven't flattened this enough.
I can do this one of two ways.

```java  
count = multiLevel.values().stream() //Stream<Map<String, List<Student>>>
        .flatMap(map -> map.values().stream() //Stream<List<...>>
                .flatMap(l -> l.stream())) //Stream<Student>
        .filter(s -> s.getMonthsSinceActive() <= 3)
        .count();
System.out.println("Active Students in multiLevel = " + count);
```

In the _flatMap_ operation, 
I can keep processing my _flattenedStream_ a little more.
After the call to `map.values.stream`,
I'll include a new line.
Here, I'll make a call to _flatMap_ on this new stream,
These are really lists,
and I can get a stream from them, 
making a call to _flatMap_ on this nested stream.
This fixes my compiler error, 
and I can run this code:

```html  
studentBodyCount = 5000
studentBodyCount = 5000
studentBodyCount = 2169
Active Students = 2169
Active Students in multiLevel = 2169
```

And I'll get the same number of active students,
as I did in my experienced map.
The _flatMap_ operation allows us to use a single pipeline stream,
to do all our processing, instead of nesting stream pipelines, 
as I had shown you previously.
So, you should be able to see why you'd want to use this operation
if you're dealing with structures of nested data.
</div>

## [o. Stream Challenge](https://github.com/korhanertancakmak/JAVA/tree/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_13_Streams/Course17_StreamsChallenge/README.md#stream-challenge)
<div align="justify">

In this challenge, you'll again use streams with the Student Engagement Code.
Before you start, first change the _getRandomStudent_ method on **Student**,
to select a random number and random selection of courses.
Every student should be enrolled 
and have activity in at least one class.
Set up three or four courses, 
use the lecture count version of the constructor on several of these, 
to pass lecture counts greater than 40.
Generate a list of 10,000 students
who've enrolled in the past 4 years.
Pass the supplier code three or four courses.
Next, answer the following questions.

* How many of the students are enrolled in each class? 
* How many students are taking 1, 2, or 3 classes?
* Determine the average percentage complete, for all courses, for this group of students.
Hint, try `Collectors.averagingDouble` to get this information.
* For each course, get activity counts by year, using the last activity year field.

Think about how you'd go about answering these questions, 
using some of the stream operations you've learned, 
especially the collect terminal operation in conjunction
with the **Collectors** helper class methods.
</div>

