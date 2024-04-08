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

## [d. Stream Sources Challenge]()
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