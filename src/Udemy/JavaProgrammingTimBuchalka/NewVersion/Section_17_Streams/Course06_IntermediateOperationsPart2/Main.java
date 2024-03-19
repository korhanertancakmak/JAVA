package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_17_Streams.Course06_IntermediateOperationsPart2;

import java.util.Comparator;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

//Part-1
/*
                             Intermediate Operations that operate on every element

        Return Type         Operation                                       Description

        Stream<R>           map(Function<? super T, ? extends R> mapper)    This is a function applied to every element in the stream.
                                                                            Because it's a function, the return type can be different,
                                                                            which has the effect of transforming the stream to a different
                                                                            stream of different types.
        Stream<T>           peek(Consumer<? super T> action)                This function doesn't change the stream, but allows you to perform
                                                                            some interim consumer function while the pipeline is processing.
        Stream<T>           sorted()                                        There are two versions of sorted.

                            sorted(Comparator<? super T> comparator)        The first uses the naturalOrder sort, which means elements in the
                                                                            stream must implement Comparable.

                                                                            If your elements don't use Comparable, you'll want to use sorted
                                                                            and pass a Comparator.

        In the last two examples of stream pipelines, I used map and sorted. It's kind of hard not to use these in many
    cases. Much of what you want to do in a stream processing pipeline is to filter, transform and sort. The map function
    is both simple and powerful. It can turn one stream into another kind, and since we can use as many map operations as
    we want, we could do a lot with this one operation. It can be as complex or simple as you want it to be as well.

        The peek method is aptly named because its commonly used to print elements in interim operations, so you can get
    an understanding of what is really happening. Of course, you're not limited to just printing output. This operation
    is susceptible to intentional, or unintentional, side effects.

        Finally, there's the sorted operation. We've covered sorted a lot with collections, and this operation is similarly
    structured to the sort method on implemented collections. You can use it without specifying an argument, for elements
    that implement the Comparable interface. If you want a different kind of sort, or the elements in your stream don't
    implement Comparable, you'd pass a Comparator. The sorted method gets complicated when you use parallel streams, but
    I'll cover those complexities in the concurrency section of the course.

        I want to examine the map operation in a little more detail. First though, I'm going to create a Seat record for
    the examples I'll be using in this lecture.
*/
//End-Part-1

public class Main {

    public static void main(String[] args) {

        System.out.println();
        int maxSeats = 100;
        int seatsInRow = 10;
        var stream = Stream.iterate(0, i -> i < maxSeats, i -> i + 1)
                        .map(i -> new Seat((char) ('A' + i / seatsInRow), i % seatsInRow + 1));

        //stream.forEach(System.out::println);

//Part-4
/*
        I'll first print a new line. I'll set maxSeats to 100, and seats in row to 10. I'm going to create a stream variable,
    using var as the type for simplicity. I'll use Stream.iterate, starting with zero. I want to generate numbers up to the
    maxSeats value, which I made 100. The third parameter is a function lambda, and it just increments by one. These
    parameters kind of remind you of a traditional for loop don't they? Here, I'm going to return new instances of the
    Seat record, and instead of using generate, I'm doing this with iterate, saying how many seats I want. Then I'm going
    to use map, to return a new Seat record. I can figure out the row's character, by using the integer on the stream,
    and dividing that by the seats in this row. For example, i divided by seats in row, will be 0 for the first 10 sets,
    so they'll be in row A, it'll be 1 from the next 10 seats, so they'll all be B, and so on. Next, I need the seat number.
    I don't want to use the stream's integer value, because each seat number is numbered from 1 to 10. Again, I can figure
    this out with a simple math equation. I can use the modulus of i with seatsInRow and add one. When i is 0 or 10, or
    20, the seat number is 1, the first seat in the row. When i is 9, or 19, the modulus returns 9, and adding 1 makes
    this 10, which is the last seat in the row. Finally, I'll print these records out. Ok, so I purposely wanted my terminal
    operation, as a separate statement. This lets me demonstrate a couple of things using IntelliJ's inlay hints. You can
    see my local stream variable's type has been inferred to be, a Stream of Seat there. In the code I'm showing you here, I
    start out with a Stream of integers, which then becomes a Stream of theatre seats. If I run this,

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

    I can see all my seats printed. Most of the seats are priced at $75, but after row C, you can start seeing discount
    seats, the seats that have a seat number of 001, or 002, as well as those that are 009, or 010.
*/
//End-Part-4

        var stream1 = Stream.iterate(0, i -> i < maxSeats, i -> i + 1)
                .map(i -> new Seat((char) ('A' + i / seatsInRow), i % seatsInRow + 1))
                //.map(Seat::toString);
                //.map(Seat::price)
                .mapToDouble(Seat::price)
                //.map("%.2f"::formatted);
                .mapToObj("%.2f"::formatted);

        //stream1.forEach(System.out::println);

//Part-5
/*
        Now, I'll do another map, and put that after the first one, in this pipeline. I'll remove the closing semi-colon.
    I'll add another map operation, and return a method reference, Seat::toString. This will return a String. I can confirm
    this by examining the inlay hint for my stream variable again. This always shows me the final stream type. IntelliJ
    has an inlay hint now, after the first map operation, which tells me that at that point my Stream had Seat elements
    in it. Also notice that after the iterate method, it's showing me that I started with a Stream of Integer. I'll change
    this map to return a stream of the prices, so I'll just change toString, to simply price. IntelliJ tells me the result
    is a Stream of Double, or double wrapper instances. In addition to Streams that take any type, I've already demonstrated
    that Java has primitive streams for ints, doubles and longs. I can switch to these, in a pipeline that started, out
    as a Stream. To do this, I can use specialized map functions, and I'll show you this next. In this case, I want to
    use an operation called mapToDouble, rather than map. Now look at the resulting stream type. It's not a Stream of
    Double, but a new Stream type, a DoubleStream. This is a special stream, that handles primitive doubles. It can be a
    lot more efficient to process a large number of doubles with this type of stream, rather than incur the overhead or
    additional memory required when operating on a double wrapper. I'll add another map operation. First, I'll again remove
    the last semi-colon. Now I'll include an additional map operation. This time I want the price, but as a formatted
    string. I'll have a formatted float specifier, allowing for two decimals, and use the formatted method. I can do this
    with a method reference too. When using stream pipelines, often times the method references are easier to read, to
    get a gist of what is happening. IntelliJ is flagging this map operation as an error. Hovering over that, I get the
    message bad return type in method reference. Cannot convert java.lang.String to double. When you use a DoubleStream,
    all your map operations have to return a double. To map to something other than a double, I'll have to use another
    specialty map operation, called mapToObj. I'll replace that last map operation, with mapToObj. The stream inlay hint,
    shows the resulting stream, is now a Stream again, with a String Type. Let's pause here a minute to look at a table.

            Special Primitive Streams          Mapping from Reference Type to Primitive            Mapping from Primitive Stream to Reference Type
                  DoubleStream              mapToDouble(ToDoubleFunction<? super T> mapper)          mapToObj(DoubleFunction<? extends U> mapper)
                                                                                                     boxed()
                  IntStream                 mapToInt(ToIntFunction<? super T> mapper)                mapToObj(IntFunction<? extends U> mapper)
                                                                                                     boxed()
                  LongStream                mapToLong(ToLongFunction<? super T> mapper)              mapToObj(LongFunction<? extends U> mapper)
                                                                                                     boxed()

    In addition to the generic Stream, that lets you stream any reference type, Java has three primitive streams. These
    are DoubleStream, IntStream, and LongStream. To change to a primitive stream, you choose a corresponding mapTo operation
    as shown in the second column, on this slide. To switch to a double stream, you'd use mapToDouble. Map To Int will
    result in using an IntStream, and LongStream comes from mapToLong. To switch back to a generically typed stream, you
    use mapToObj. Alternately you can use boxed(). I'll show you that next.
*/
//End-Part-5

        var stream2 = Stream.iterate(0, i -> i < maxSeats, i -> i + 1)
                .map(i -> new Seat((char) ('A' + i / seatsInRow), i % seatsInRow + 1))
                //.mapToDouble(Seat::price)
                //.boxed()
                //.map("%.2f"::formatted);
                .sorted();

        //stream2.forEach(System.out::println);

//Part-6
/*
        I'll add a boxed operation before the last mapToObj operation. Now, I have an error on MapToObj, and because I
    used boxed above it, I can simply make that map here. I'll switch mapToObj back to map. The primitive streams have
    many of the same operations, but do include a few extra terminal operations specific to their numeric types. I'll
    cover these in the next lecture. I think the sorted operation is pretty self explanatory, but let me comment out
    some of these extra map operations, and I'll give that a quick test. I'll add sorted after the map. When I run this,

                Exception in thread "main" java.lang.ClassCastException: class Seat cannot be cast to class java.lang.Comparable

    I get an exception, and that's because my record doesn't implement Comparable. I can simply pass a Comparator here,
    so I'll do that.
*/
//End-Part-6

        var stream3 = Stream.iterate(0, i -> i < maxSeats, i -> i + 1)
                .map(i -> new Seat((char) ('A' + i / seatsInRow), i % seatsInRow + 1))
                .sorted(Comparator.comparing(Seat::price).thenComparing(Seat::toString));

        //stream3.forEach(System.out::println);

//Part-7
/*
        I want to first sort by the lowest seat price, and then the seat numbers string. I'll use Comparator's convenience
    methods to build this, so I'll start with comparing, passing that the method reference for Seat price. I'll chain
    the then Comparing method to that. For that method, I want to pass the method reference for the toString method.
    Running that code,

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

    I get my lowest priced seats listed first, D001, D002, D009 and so on. Lastly, I want to finish up this lecture with
    a quick look at peek.


    Peek takes a Consumer, and therefore its purpose is not to have any side effects on stream members.
    In fact, the Java docs state that peek mainly exists to support debugging, to see elements, as they flow past a certain point in a pipeline.
    First, I'll comment out the last statement, meaning the for each statement. I'll use peek, but for this example, I just want
    to do it on a limited set, so I'll skip the first 5 and limit the output to just 10, and now I'll include peek, printing out each generated record.
    Running this code, I don't get any output at all. This is a reminder, that peek is still an intermediate operation too,
    and because of lazy streams, it won't process until I include a terminal operation. I'll uncomment that last line of code in this method.
    Running this, I can see the output from the peek operation, which includes the arrow. You wouldn't probably use
    peek in a stream pipeline, if your terminal operation is for each with a println like this. There are terminal operations that aggregate
    data and give you a single result, and peek is often valuable in those circumstances, to try to understand the stream pipeline process.
    Ok, so this video covered the most commonly used intermediate stream operations. There are a couple of others, but I'll come back
    to those after I review terminal operations. In the next video, I'll cover these. You've seen forEach, but there are many
    others, so I'll see you in that next video.
*/
//End-Part-7


        var stream4 = Stream.iterate(0, i -> i < maxSeats, i -> i + 1)
                .map(i -> new Seat((char) ('A' + i / seatsInRow), i % seatsInRow + 1))
                .sorted(Comparator.comparing(Seat::price).thenComparing(Seat::toString))
                .skip(5)
                .limit(10)
                .peek(s -> System.out.println("--> " + s));

        stream4.forEach(System.out::println);

//Part-8
/*
        Peek takes a Consumer, and therefore its purpose is not to have any side effects on stream members. In fact, the
    Java docs state that peek mainly exists to support debugging, to see elements, as they flow past a certain point in
    a pipeline. First, I'll comment out the last statement, meaning the forEach statement. I'll use peek, but for this
    example, I just want to do it on a limited set, so I'll skip the first 5 and limit the output to just 10, and now
    I'll include peek, printing out each generated record. Running this code,

        I don't get any output at all. This is a reminder, that peek is still an intermediate operation too, and because
    of lazy streams, it won't process until I include a terminal operation. I'll uncomment that last line of code in this
    method. Running this,

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

    I can see the output from the peek operation, which includes the arrow. You wouldn't probably use peek in a stream
    pipeline, if your terminal operation is forEach with a println like this. There are terminal operations that aggregate
    data and give you a single result, and peek is often valuable in those circumstances, to try to understand the stream
    pipeline process. Ok, there are a couple of others, but I'll come back to those after I review terminal operations.
    In the next lecture, I'll cover these. You've seen forEach, but there are many others.
*/
//End-Part-8
    }
}
