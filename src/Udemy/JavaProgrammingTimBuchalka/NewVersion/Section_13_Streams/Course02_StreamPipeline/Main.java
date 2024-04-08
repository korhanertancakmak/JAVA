package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_13_Streams.Course02_StreamPipeline;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//Part-1
/*
        In the last lecture, I left off with our first example of using a stream in code. I'll display it here. This entire
    chain of operations is what's called a Stream Pipeline.

                        bingoPool.stream()
                                .limit(15)
                                .filter(s -> s.indexOf('G') == 0 || s.indexOf("O") == 0)
                                .map(s -> s.charAt(0) + "-" + s.substring(1))
                                .sorted()
                                .forEach(s -> System.out.print(s + " "));

    The source of the stream is where the data elements are coming from. In our example, it's coming from a list, bingoPool.
    All pipelines start with a stream, so in this example, we need to call the stream method on the bingoPool list to get
    a stream. There are a lot of other kinds of sources, and ways to create new streams, including infinite streams. I'll
    be discussing these shortly.

        Stream Pipelines end in a "terminal operation", which produces a result or side-effect. In this example, the forEach
    operation executes a Consumer implementation. This prints out some data, about each element that was processed, and
    its current state. A terminal operation is "required". There are many different kinds of terminal operations, returning
    many different types. The forEach operation, along with the similar, forEachOrdered operation, are the only ones that
    don't return anything, they're void in other words. I'll be reviewing all of these with you over the courses of this
    section. Everything else, between the source and the terminal operation is an intermediate operation.

        An intermediate operation is "not required". You can have a pipeline that just has a source and terminal operation,
    and these are quite common, and I'll be giving you examples shortly. Every intermediate operation processes elements
    on the stream, and returns a stream as a result. Knowing this piece of information will help you identify a terminal
    vs. intermediate operation in the Stream API. Let me show you that. As we scroll through these methods, pay attention
    to the return type. For example, the first two, allMatch and anyMatch return a boolean. These are terminal operations,
    as are the next two, which are overloaded versions of a function called collect. Scrolling down a bit, you can see now,
    distinct, dropWhile, and filter here. I used filter in my example code. This operation returns a Stream, and is an
    intermediate operation, as are distinct and dropWhile, and others that return a Stream. Some return special streams
    for primitive types, like DoubleStream, IntStream, and LongStream. You'll remember that the Stream interface, which
    is generic, can't be typed with a primitive. Java provides these special streams, when you don't want the overhead of
    a wrapper type. I'm going to cover most of these operations, and what they mean and how to use them. What I also want
    you to see is, that nearly every one of these, uses some kind of functional interface. You can see Consumer in the
    forEach, Supplier in generate, A UnaryOperator in iterate, a Function in map, and so on. It's definitely a lambda
    expression landscape. Again, if you feel like you might be a little weak on lambdas, or method references, I hope
    you'll give yourself a review of that subject. Lambdas and Streams were built for each other, and are both part of
    Java's tilt towards functional programming.

        Finally, you can always just search for terminal in your browser session to scroll through the different terminal
    operations. If I keep hitting next, I'll work my way through the terminal operations. I could do the same for intermediate
    as well, but I won't do that now. There are some additional topics I want to discuss about the pipeline, before I get
    back to the code. Scrolling almost all the way back to the top of this page, I want to highlight the next important
    point.

        Streams are lazy; computation on the source data is only performed when the terminal operation is initiated, and
    source elements are consumed only as needed. What does that really mean? Without worrying about semantics, I want you
    to imagine the stream pipeline as a black box. The source is your input, the result of your terminal operation is your
    output. Everything in between, isn't going to happen until something tells that terminal operation to start. What
    actually happens in that black box, may not happen exactly as you've described it, or in the order you've specified.
    This is quite different from chaining methods, where the execution of every method is guaranteed to occur, and it
    occurs in the order you defined it, against a known set of elements. Execution of the intermediate operations is
    dependent, first on a terminal operation being specified, and second on an optimization process occurring.

        Stream computations are optimized for performance. What this means is that your stream pipeline is kind of a workflow
    suggestion. Before the process begins, the stream implementation will perform an evaluation, to optimize the means
    to the end. It will determine the best way to get the elements needed, and the most efficient way to process them,
    to give you the result you've asked for. The result will be consistent each time, but the process to get there is
    not guaranteed to be. Optimizations may change the order of the intermediate operations, it may combine operations,
    or even skip them altogether. For this reason, you should avoid side effects in your intermediate operations. For
    example, in the code I've been working with, I've requested that my stream be filtered by the G and O labels. If the
    source stream only contained G labels, this filter might be omitted altogether. If I'd included code in the lambda
    to do something else, to increment a counter on another object or something like that, it may never be executed.
    I'm going to come back to this point again a bit later, in the intermediate operations lecture.
*/
//End-Part-1

public class Main {

    public static void main(String[] args) {

        List<String> bingoPool = new ArrayList<>(75);

        int start = 1;
        for (char c : "BINGO".toCharArray()) {
            for (int i = start; i < (start + 15); i++) {
                bingoPool.add("" + c + i);
//                System.out.println("" + c + i);
            }
            start += 15;
        }

        Collections.shuffle(bingoPool);
        for (int i = 0; i < 15; i++) {
            System.out.println(bingoPool.get(i));
        }
        System.out.println("------------------------------------");

//        List<String> firstOnes = bingoPool.subList(0, 15);
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

        for (int i = 0; i < 15; i++) {
            System.out.println(bingoPool.get(i));
        }
        System.out.println("------------------------------------");

/*
        bingoPool.stream()
                .limit(15)
                .filter(s -> s.indexOf('G') == 0 || s.indexOf("O") == 0)
                .map(s -> s.charAt(0) + "-" + s.substring(1))
                .sorted()
                .forEach(s -> System.out.print(s + " "));
*/

//Part-2
/*
        Before we move on, let's get back to the code for just a minute. First, I want to comment the last couple of statements
    that print out the first 15 strings in the bingo pool. Next, I'll comment out the statement that has the terminal
    operation for a moment,

                                .forEach(s -> System.out.print(s + " "));

    and end the statement above that with a semi-colon. IntelliJ is giving me a warning, but the code compiles, and I can
    run it, which I'll do next.

                    ----------------------------------

                    ----------------------------------

    None of these operations are executed. I've said they won't be executed until a terminal operation is executed, and
    I've also said a terminal operation is required, to complete the pipeline. In this case, I haven't included a terminal
    operation in my pipeline at all. Can I make a call to the terminal operation later? Let's try that. I'll assign the
    result of the pipeline to a local variable, just using var for the type, and name the variable temp stream. Next,
    I'll use that variable to call the forEach operation on, in a separate statement, passing it the same lambda as before.
    This all compiles, and if I run it,

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

    now I get a result. You don't have to build your pipeline all in one piece. It's important to understand though, that
    unlike the chaining of method calls on other types, these operations don't get executed until terminal operation.
*/
//End-Part-2

        var tempStream = bingoPool.stream()
                .limit(15)
                .filter(s -> s.indexOf('G') == 0 || s.indexOf("O") == 0)
                .map(s -> s.charAt(0) + "-" + s.substring(1))
                .sorted();
        //.forEach(s -> System.out.print(s + " "));

        tempStream.forEach(s-> System.out.print(s + " "));
        System.out.println("\n----------------------------------");

/*
        for (int i = 0; i < 15; i++) {
            System.out.println(bingoPool.get(i));
        }
*/

        //tempStream.forEach(s-> System.out.print(s.toLowerCase() + " "));

//Part-3
/*
        Ok, now let's say I want to use my stream, to do something else. Maybe next, I want to print things out in lowercase.
    I'll copy terminal operation

                                    tempStream.forEach(s-> System.out.print(s + " "));

    and paste it at the end of my method. I'll change the s that gets printed out to s.toLowerCase. I have a warning from
    IntelliJ. If I hover over that, the message says that, Stream has already been linked or consumed. What's that all
    about? you may be wondering. I'll try to run this anyway, since it's just a warning.

                Exception in thread "main" java.lang.IllegalStateException: stream has already been operated or closed

    But, now I do get an error, this one says stream has already been operated upon or closed. Let me comment that last
    statement first. Once you invoke a terminal operation on a stream, you can think of the pipeline as being opened, and
    the flow beginning. The flow is allowed to continue until all processes have been performed and a result produced.
    At that point, the valve is shut, and the pipeline closed. You can't turn it back on, or reuse it for a new source.
    If you want to do the same sort of thing with a different variable for one of the intermediate operations, you'd need
    to set up a new pipeline. Now that you have a bit of understanding about the stream pipeline, let's review different
    ways to create sources for your streams. I'll start that discussion in the next lecture.
*/
//End-Part-3
    }
}
