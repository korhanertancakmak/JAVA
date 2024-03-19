package CourseCodes.NewSections.Section_17_Streams.Course04_StreamSourcesChallenge;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

//Part-1
/*
                        Method                                                              Finite          Infinite
                  Collection.stream()                                                         X
                  Arrays.stream(T[])                                                          X
                  Stream.of(T...)                                                             X
                  Stream.iterate(T seed, UnaryOperator<T> f)                                  X                 X
                  Stream.iterate(T seed, Predicate<? super T> p, UnaryOperator<T> f)          X
                  Stream.generate(Supplier<? extends T> s)                                                      X
                **IntStream.range(int startInclusive, int endExclusive)                       X
                **IntStream.rangeClosed(int startInclusive, int endExclusive)                 X

        In the last lecture, I reviewed the following methods to generate a Stream. In this coding challenge, you'll be
    using most of these methods to set up a few streams of your own. For this challenge, I want you to Generate the bingo
    ball labels as 5 different streams, using different Stream creation methods for each. Assign each pipeline to a stream
    variable. Concatenate the five streams together. Apply the terminal operation, forEach, to the final concatenated
    stream, to print each label. These should be printed in order as follows. B1-throughB15, I16-toI30, N31- throughN45,
    G45- toG60, finally, O61- throughO75. Remember that the map intermediate operation, takes a Function, so you can return
    a different type, than the input stream element. In other words, if you start with a Stream containing Integer, you
    can finish the pipeline with a Stream containing String elements. To do this, you'd use map to return a String, by
    executing a method or expression that takes an integer, and returns a string. The generate method may be difficult
    to use, without creating side effects, or using other intermediate operations I haven't yet mentioned, but if you
    want a good challenge, you can play around with this one.
*/
//End-Part-1

public class Main {

    static int counter = 0;

    public static void main(String[] args) {

        int seed = 1;
        var streamB = Stream.iterate(seed, i -> i <= 15, i -> i + 1)
                .map(i -> "B" + i);
        //streamB.forEach(System.out::println);

//Part-2
/*
        Let's walk through one possible set of solutions together here. I've created Main class and I'll start out with
    a local variable called seed, and set that to 1. Remember that each set of balls, has a different range of numbers.
    I'll use this variable to manage the starting index of each range. For my b labels, I'm going to use Stream.iterate,
    the version that includes a predicate, because I think this is one of the easiest methods to use. First I'll set up
    a variable with type inference, so var, and I'll call it streamB. I'll assign it the result of calling Stream.iterate.
    My first argument is seed, so in this case it's 1, I'll continue until the numbers generated reach 15, and I'll
    increment by one for each iteration. This is a finite stream. Finally I'll map the integer to a String that starts
    with B, then is followed by the generated number. I'll test that out by adding a separate terminal operation. Running
    this code,

                    B1
                    B2
                    .
                    .
                    B14
                    B15

    you can see that works, and I get the labels, B1 through B15. Ok, that's the first one, using the finite version of
    the static method, iterate, on the Stream interface. Next I'll work on the labels that start with the letter i.
*/
//End-Part-2

        seed += 15;
        var streamI = Stream.iterate(seed, i -> i + 1)
                        .limit(15)
                                .map(i -> "I" + i);
        //streamI.forEach(System.out::println);

//Part-3
/*
        I'll insert this code before that last statement. I want to start by updating my seed value, adding 15 to that,
    because the range of numbers here needs to be 16 through 30. I'll call my variable streamI, and use the two parameter
    version of iterate. I'll pass my seed variable, and the same lambda for the UnaryOperator as I did previously. Seed
    is now 16. Nowhere, I have an infinite stream. I'll include a limit operation, limiting it to 15 elements. I'll again
    use map to create a String starting with I, and the value of the element in the stream. To test this, I'll change that last
    statement from stream B to stream I. I'll run that,

                        I16
                        I17
                        .
                        .
                        I29
                        I30

    to confirm I get the right labels. I could have replaced these with IntStream. This time I get I 16 through I 30, so
    that's all good.
*/
//End-Part-3

        seed += 15;
        int nSeed = seed;
        String[] oLabels = new String[15];
        Arrays.setAll(oLabels, i -> "N" + (nSeed + i));
        var streamN = Arrays.stream(oLabels);
        //streamN.forEach(System.out::println);

//Part-4
/*
        Again, I'll include the next statements before that last terminal operation, first incrementing seed by 15 again.
    This time I'm going to use Arrays.stream for my source. I'll create an array of Strings, with 15 null references.
    I'll call setAll, to set the values directly here, with a lambda that is similar to the map operations on stream.
    Since my array already has everything set up, I'll simply create a new stream variable, streamN, and assign it the
    value of Arrays.stream, passing it o labels. I could have used map on the stream, but it was just as easy to set the
    array up as Strings, as I've shown. But notice that I do have a problem with my lambda expression. My variable isn't
    final or effectively final, and that's fairly obvious, because I keep changing it. This is a pretty easy fix. I can
    just create a second variable here, and assign it the current value of seed. I'll set up a variable called nSeed, and
    assign that the value of seed. I'll change my lambda to use this new variable, so I'll make that nSeed, instead of
    seed. I'll also change that last for Each operation to now print stream N. Running this,

                        N31
                        N32
                        .
                        .
                        N44
                        N45

    I've got N31 to N45 printing, so that's good. You might be asking why I'd do this, and this is sort of a forced example,
    but it's possible you'd join data from an array, to other sources of streams. For the next one, I'm going to use
    Stream.of,
*/
//End-Part-4

        seed += 15;
        var streamG = Stream.of("G46", "G47", "G48", "G49", "G50",
        "G51", "G52", "G53", "G54", "G55", "G56", "G57", "G58", "G59", "G60");
        //streamG.forEach(System.out::println);

//Part-5
/*
        So again, first I'll increment seed by 15, and I'll put that before the last statement. This time, it's a bit
    tedious, but it's a valid option. I'll create a variable, stream G, and assign it the result of Stream.of. I'm just
    going to create a list of my 15 labels, manually, one at a time, starting with G46, and ending with G60. I'll again
    change the stream being used for the terminal operation, to StreamG. Running that,

                        G46
                        G47
                        .
                        .
                        G59
                        G60

    I get G46 to G60. Okay, so that's four out of 5. Now I want to consider the infinite generate method that takes a
    supplier. What kind of supplier could I use to generate a list of 15 different numbers, all sequential?
*/
//End-Part-5

        seed += 15;
        int rSeed = seed;

        var streamO = Stream.generate(Main::getCounter)
                        .limit(15)
                                .map(i -> "O" + (rSeed + i));
        //streamO.forEach(System.out::println);

//Part-6
/*
        One way to do this is to create a static variable in Main class, and a static method, which I'll do here. First
    the static variable. I'll make it an int, called counter, and set it to 0. Next, I'll create a static method called
    getCounter. This will be private, return an int, and be called getCounter. it'll return the value in the static field,
    counter, then increment it. Now, getting back to the main method, I'll increment seed by 15 as usual. I'm also going
    to set up another local variable, setting it to the new value of seed, so I have an effectively final variable. I can
    use in a lambda. My next variable is stream o. That gets the value from Stream.generate, and here I'm going to use a
    method reference, that uses my static method getCounter. Because that's an infinite stream, I need to limit it, and
    I want 15 sequential numbers. I'll map that data to a String, prefixed with O, adding rSeed to the stream's sequential
    numbers. Next, I'll change streamG to streamO, in the for each statement.

                            O61
                            O62
                            .
                            .
                            O74
                            O75

    That works, but I've created an intermediate operation that has side effects. This means this operation potentially
    changes the state of something outside of the process. Worse, it depends on that state being changed, to function
    correctly. Remember, that though I've specified how I want things to be handled in the pipeline, the optimization
    process might not see it as the most efficient way. In this case, all is well, but you should avoid producing side
    effects like this, in stream pipelines. In a minute, I'll show you another way to use the generate method, but first,
    let me just finish the challenge. The last part of the challenge was to concatenate all the streams, finishing by
    printing all the labels from the concatenated stream.
*/
//End-Part-6

        var streamBI = Stream.concat(streamB, streamI);
        var streamNG = Stream.concat(streamN, streamG);
        var streamBING = Stream.concat(streamBI, streamNG);
        Stream.concat(streamBING, streamO)
                        .forEach(System.out::println);

        //streamO.forEach(System.out::println);

//Part-7
/*
        I'll insert this code before the last for Each statement. I'll create an interim stream, called stream BI, and
    I'll use Stream.concat, to merge Streams B and I. The next stream will be streams N and G concatenated. stream Bing
    will concatenate those two new stream variables together. Finally I'll concatenate stream Bing, with stream o. I'll
    chain forEach directly to that, printing each element of this concatenated stream. Running this,

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

    I get all 75 labels printed, but I also get an error. I wanted to remind you that once a stream is used, we can't try
    to use it again. When we concatenate streams, this rule still applies. I concatenated streamO, and executed a terminal
    operation on that concatenated stream. This used the streamO pipeline, so I can't execute this operation. I'll remove
    that last statement. Now the code compiles and runs. Ok, so let's revisit the generate method. Another way to do this,
    is to use an intermediate method I'll be covering shortly and haven't yet demonstrated, called distinct. Maybe you
    discovered, this operation on your own. It's always a good idea, when you're introduced to a new class or interface,
    to read the user's manual, which in this case is the java api documentation. Next, I'll show you that I can create
    an infinite stream of randomly generated numbers, in the target range, apply the distinct operation, and limit that
    to 15. Let me set this up at the end of the main method.
*/
//End-Part-7

        System.out.println("-------------------------------------");
        Stream.generate(() -> new Random().nextInt(rSeed, rSeed + 15))
                .distinct()
                .limit(15)
                .map(i -> "O" + i)
                .sorted()
                .forEach(System.out::println);


//Part-8
/*
        I'll add a separator line first. I won't set up a local variable, because I'll call for each directly in the pipeline.
    First I'll call next int on a new random instance, passing it rSeed and rSeed + 15. This will give me numbers between
    60 and 75 in an infinite stream. I'll next call distinct. This has no parameters, and will allow only distinct numbers
    to pass through the pipeline. I only want 15 distinct numbers, in my resulting stream. I'll map my labels, prefixing
    them with O. Next I need to sort my labels. Since they were generated in random order, I want them printed in sorted
    order. Finally, I'll terminate the pipeline and print each element. Running this,

                            -------------------------------------
                            O61
                            O62
                            .
                            .
                            O74
                            O75

    I have my labels from O61 to O75. Obviously, using iterate for this type of process, was a better, more efficient
    operation. if you know what your number range is, and the start and end values are well defined, then use iterate.
    The generate operation is better at producing values until enough elements, match more complex conditions that aren't
    dependent on a known range of numbers. I showed you an example of this with the prime number code, when we wanted the
    first 20 prime numbers, and we didn't really know how many numbers it would take to meet that condition. I'm going to
    make one minor change to this last stream, changing that bound, from rSeed plus 15, to rSeed plus 14. I'll run this code.

                            -------------------------------------

    It runs but it never completes, so I'll terminate that manually. Do you know why? This random generator is only going
    to generate 14 distinct random numbers, but my limit operation is waiting for 15. This condition is never going to
    be met, so this really is an infinite situation. Even when you use the limit operation, if your operations and conditions
    aren't well thought out, you could get into a situation like this. Ok, I'll revert that last change so this code runs,
    and completes successfully.
*/
//End-Part-8
    }

    private static int getCounter() {
        return counter++;
    }
}
