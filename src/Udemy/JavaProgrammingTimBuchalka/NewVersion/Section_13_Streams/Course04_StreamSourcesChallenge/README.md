# [Stream Sources Challenge]()
<div align="justify">

Let's walk through one possible set of solutions together here. 
I've created **Main** class,
and I'll start out with a local variable called _seed_, 
and set that to 1. 

```java  
public class Main {

    static int counter = 0;

    public static void main(String[] args) {

        int seed = 1;
        var streamB = Stream.iterate(seed, i -> i <= 15, i -> i + 1)
                .map(i -> "B" + i);
        streamB.forEach(System.out::println);
    }
}
```

Remember that each set of balls 
has a different range of numbers.
I'll use this variable
to manage the starting index of each range. 
For my _b_ labels, 
I'm going to use `Stream.iterate`,
the version that includes a predicate, 
because I think this is one of the easiest methods to use. 
First, I'll set up a variable with type inference, so _var_, 
and I'll call it _streamB_. 
I'll assign it the result of calling Stream.iterate.
My first argument is _seed_, so in this case it's 1, 
I'll continue until the numbers generated reach 15, 
and I'll increment by one for each iteration. 
This is a finite stream. 
Finally, I'll map the integer to a String 
that starts with _B_, then is followed by the generated number. 
I'll test that out by adding a separate terminal operation. 
Running this code:

```html  
B1
B2
.
.
B14
B15
```

You can see that works, 
and I get the labels, B1 through B15. 
Ok, that's the first one, 
using the finite version of the static method,
_iterate_, on the **Stream** interface. 
Next I'll work on the labels 
that start with the letter _i_.

```java  
seed += 15;
var streamI = Stream.iterate(seed, i -> i + 1)
                .limit(15)
                .map(i -> "I" + i);
streamI.forEach(System.out::println);
```

I want to start by updating my _seed_ value, 
adding 15 to that, 
because the range of numbers here needs to be 16 through 30. 
I'll call my variable _streamI_, 
and use the two-parameter versions of _iterate_. 
I'll pass my _seed_ variable, 
and the same lambda for the **UnaryOperator** 
as I did previously. 
_Seed_ is now 16. 
Nowhere, I have an infinite stream. 
I'll include a _limit_ operation, 
limiting it to 15 elements. 
I'll again use _map_ to create a **String** starting with _I_, 
and the value of the element in the stream. 
To test this, I'll change that last statement 
from stream _B_ to stream _I_. 
I'll run that:

```html  
I16
I17
.
.
I29
I30
```

To confirm, I get the right labels. 
I could have replaced these with **IntStream**. 
This time I get _I16_ through _I30_, 
so that's all good.

```java  
seed += 15;
int nSeed = seed;
String[] oLabels = new String[15];
Arrays.setAll(oLabels, i -> "N" + (nSeed + i));
var streamN = Arrays.stream(oLabels);
streamN.forEach(System.out::println);
```

Again, I'll include the next statements 
before that last terminal operation,
first incrementing seed by 15 again.
This time I'm going to use `Arrays.stream` for my source. 
I'll create an array of **Strings** with 15 _null_ references.
I'll call _setAll_, to set the values directly here, 
with a lambda that is similar to the _map_ operations on stream.
Since my array already has everything set up, 
I'll simply create a new stream variable, _streamN_, 
and assign it the value of `Arrays.stream`, passing it o labels. 
I could have used _map_ on the stream, 
but it was just as easy to set the array up as **Strings**, 
as I've shown. 
But notice that I do have a problem with my lambda expression. 
My variable isn't _final_ or _effectively final_, 
and that's fairly obvious, because I keep changing it. 
This is a pretty easy fix. 
I can just create a second variable here, 
and assign it the current value of _seed_. 
I'll set up a variable called _nSeed_, 
and assign that the value of _seed_. 
I'll change my lambda to use this new variable, 
so I'll make that _nSeed_, instead of _seed_. 
I'll also change that last _forEach_ operation 
to now print _streamN_. 
Running this:

```html  
N31
N32
.
.
N44
N45
```

I've got _N31_ to _N45_ printing, so that's good. 
You might be asking why I'd do this, 
and this is sort of a forced example,
but it's possible you'd join data from an array, 
to other sources of streams. 
For the next one, I'm going to use `Stream.of`:

```java  
seed += 15;
var streamG = Stream.of("G46", "G47", "G48", "G49", "G50",
        "G51", "G52", "G53", "G54", "G55", "G56", "G57", "G58", "G59", "G60");
streamG.forEach(System.out::println);
```

So again, first I'll increment _seed_ by 15, 
and I'll put that before the last statement. 
This time, it's a bit tedious, 
but it's a valid option. 
I'll create a variable, _streamG_, 
and assign it the result of `Stream.of`. 
I'm just going to create a list of my 15 labels, manually, 
one at a time, starting with _G46_, and ending with _G60_. 
I'll again change the stream being used 
for the terminal operation, to _StreamG_. 
Running that:

```html  
G46
G47
.
.
G59
G60
```

I get _G46_ to _G60_. 
Okay, so that's four out of 5. 
Now I want to consider the infinite generate method 
that takes a supplier. 
What kind of supplier could I use 
to generate a list of 15 different numbers, all sequential?

```java  
private static int getCounter() {
    return counter++;
}
```

One way to do this is 
to create a static variable in **Main** class, 
and a static method, which I'll do here. 
First, the static variable. 
I'll make it an int, called _counter_, 
and set it to 0. 
Next, I'll create a static method called _getCounter_. 
This will be private, return an int, 
and be called _getCounter_. 
It'll return the value in the static field,
_counter_, then increment it. 

```java  
seed += 15;
int rSeed = seed;

var streamO = Stream.generate(Main::getCounter)
        .limit(15)
        .map(i -> "O" + (rSeed + i));
streamO.forEach(System.out::println);
```

Now, getting back to the _main_ method, 
I'll increment seed by 15 as usual. 
I'm also going to set up another local variable,
setting it to the new value of _seed_, 
so I have an effectively final variable. 
I can use in a lambda. 
My next variable is _streamO_. 
That gets the value from `Stream.generate`, 
and here I'm going to use a method reference, 
that uses my static method _getCounter_. 
Because that's an infinite stream, I need to limit it, 
and I want 15 sequential numbers. 
I'll _map_ that data to a **String**, 
prefixed with _O_, adding _rSeed_ 
to the stream's sequential numbers. 
Next, I'll change _streamG_ to _streamO_, 
in the _forEach_ statement.

```html  
O61
O62
.
.
O74
O75
```

That works, but I've created an intermediate operation 
that has side effects. 
This means this operation potentially changes 
the state of something outside the process. 
Worse, it depends on that state being changed 
to function correctly. 
Remember that though I've specified 
how I want things to be handled in the pipeline, 
the optimization process might not see 
it as the most efficient way. 
In this case, all is well, 
but you should avoid producing side effects 
like this, in stream pipelines. 
In a minute, I'll show you another way 
to use the generate method, but first,
let me finish the challenge. 
The last part of the challenge was 
to concatenate all the streams, 
finishing by printing all the labels 
from the concatenated stream.

```java  
var streamBI = Stream.concat(streamB, streamI);
var streamNG = Stream.concat(streamN, streamG);
var streamBING = Stream.concat(streamBI, streamNG);
Stream.concat(streamBING, streamO)
    .forEach(System.out::println);

streamO.forEach(System.out::println);
```

I'll insert this code 
before the last _forEach_ statement. 
I'll create an interim stream, 
called _streamBI_, 
and I'll use `Stream.concat`, 
to merge _StreamsB_ and _StreamsI_. 
The next stream will be _streamsN_ 
and _streamsG_ concatenated.
_streamBING_ will concatenate those 
two new stream variables together. 
Finally, I'll concatenate 
_streamBING_, with _streamO_. 
I'll chain _forEach_ directly to that, 
printing each element of this concatenated stream. 
Running this:

```html  
B1
B2
.
B14
B15
I16
I17
.
I29
I30
N31
N32
.
N44
N45
G46
G47
.
G59
G60
O61
O62
.
O74
O75
```

I get all 75 labels printed, 
but I also get an error. 
I wanted to remind you that 
once a stream is used, we can't try to use it again. 
When we concatenate streams, 
this rule still applies. 
I concatenated _streamO_, 
and executed a terminal operation on that concatenated stream. 
This used the _streamO_ pipeline, 
so I can't execute this operation. 
I'll remove that last statement. 
Now the code compiles and runs. 
Ok, so let's revisit the generate method. 
Another way to do this is to use an intermediate method 
I'll be covering shortly and haven't yet demonstrated, 
called _distinct_. 
Maybe you discovered this operation on your own. 
It's always a good idea 
when you're introduced to a new class or interface,
to read the user's manual, 
which in this case is the java api documentation. 
Next, I'll show you 
that I can create an infinite stream of randomly generated numbers 
in the target range, apply the _distinct_ operation, 
and limit that to 15. 
Let me set this up at the end of the _main_ method.

```java  
System.out.println("-------------------------------------");
Stream.generate(() -> new Random().nextInt(rSeed, rSeed + 15))
    .distinct()
    .limit(15)
    .map(i -> "O" + i)
    .sorted()
    .forEach(System.out::println);
```

I'll add a separator line first. 
I won't set up a local variable 
because I'll call for each directly in the pipeline.
First, I'll call next int on a new random instance, 
passing it _rSeed_ and _rSeed + 15_. 
This will give me numbers
between 60 and 75 in an infinite stream. 
I'll next call _distinct_. 
This has no parameters and 
will allow only distinct numbers to pass through the pipeline. 
I only want 15 distinct numbers in my resulting stream. 
I'll map my labels, prefixing them with O. 
Next I need to sort my labels. 
Since they were generated in random order, 
I want them printed in sorted order. 
Finally, I'll terminate the pipeline and print each element. 
Running this:

```html  
-------------------------------------
O61
O62
.
.
O74
O75
```

I have my labels from _O61_ to _O75_. 
Using _iterate_ for this type of process was a better, 
more efficient operation. 
If you know what your number range is, 
and the start and end values are well-defined, 
then use _iterate_.
The generate operation is better at producing values
until enough elements, match more complex conditions 
that aren't dependent on a known range of numbers. 
I showed you an example of this with the prime number code, 
when we wanted the first 20 prime numbers, 
and we didn't really know how many numbers 
it would take to meet that condition. 
I'm going to make one minor change to this last stream, 
changing that bound, from _rSeed + 15_, to _rSeed + 14_. 
I'll run this code.

```html  
-------------------------------------
```

It runs, but it never completes, 
so I'll terminate that manually. 
Do you know why?
This random generator is only going 
to generate 14 distinct random numbers, 
but my limit operation is waiting for 15. 
This condition is never going to be met, 
so this really is an infinite situation. 
Even when you use the limit operation, 
if your operations and conditions aren't well-thought-out, 
you could get into a situation like this. 
Ok, I'll revert that last change so this code runs
and completes successfully.
</div>