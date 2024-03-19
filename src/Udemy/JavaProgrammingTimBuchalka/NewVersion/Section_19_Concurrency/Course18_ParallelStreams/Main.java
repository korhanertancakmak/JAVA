package CourseCodes.NewSections.Section_19_Concurrency.Course18_ParallelStreams;

import java.util.Arrays;
import java.util.Random;

//Part-1
/**
        For this lecture, I'll be revisiting streams, not the IO kind, but Java's stream pipelines. When I covered streams
 previously, I didn't cover parallel streams. That's because, I felt it made more sense, to include them in this section,
 on asynchronous programming. Parallel streams were introduced in Java 8, along with serial streams.They allow you to
 perform operations on collections in parallel, thus potentially speeding up data processing. The key advantages of parallel
 streams are:

    * Improved performance on multi-core CPUs.
    * Simplified code for concurrent processing.
    * Automatic workload distribution among available threads.

 In the last lecture, you saw ways of using a ForkJoinPool, to break up one operation into many smaller ones, running in
 parallel. I did this manually, first by creating a List of Callable tasks, and then by creating a RecursiveTask, that
 I had the ForkJoinPool invoke. Parallel streams do this work implicitly for us, making them simpler to use. I've created
 the usual Main class and main method. I'll start out just like I did in the previous lecture,
 **/
//End-Part-1

public class Main {

    public static void main(String[] args) {

        int numbersLength = 100_000_000;
        long[] numbers = new Random().longs(numbersLength, 1, numbersLength).toArray();

        /*long start = System.nanoTime();
        double averageSerial = Arrays.stream(numbers).average().orElseThrow();
        long elapsedSerial = System.nanoTime() - start;
        //System.out.printf("Ave = %.2f , elapsed = %d nanos%n", averageSerial, elapsedSerial);
        System.out.printf("Ave = %.2f , elapsed = %d nanos or %.2f ms%n", averageSerial, elapsedSerial, elapsedSerial/ 1000000.0);*/

//Part-2
/**
        Getting a random stream of longs as an array. I'll make the length of the array a variable. I'll call it numbers
 Length, and set that to 100 thousand. I'll use the Random class, to generate a stream of random long values, from 1 to
 100 thousand in this case. I'll call to Array on this stream, to get an array back. I'll take a snapshot of the UTC time,
 in nano seconds, using the method, System.nano Time. I'll execute the average operation, a terminal operation, on a stream
 of my numbers array. I'll use Optional's orElseThrow method, to get that from the optional instance returned. You might
 remember that this method throws an exception, if for some reason the stream doesn't return the average. I'll get the
 time elapsed, again using System.nano time, but here I'll subtract the start time from that to get the elapsed time.
 I'll print out a formatted string, that has the average of the array values, and the elapsed time in nanoseconds. These
 values are in the averageSerial, and elapsedSerial variables. Running this code,

            Ave = 50028,88 , elapsed = 3897200 nanos

 I'll get an average value and the elapsed time in nanoseconds. I'll run it a couple of times, and of course these values
 will be different each time, but the average should be about half of my numbers length value, so about 50 thousand right now.
 I can transform nanoseconds to milliseconds, so I'll do that here, since milliseconds are a bit easier to grasp. I'll
 add another format specifier, and some text for the milliseconds. I'll include the formula to get milliseconds from nanoseconds,
 which is just dividing by a million. I'll make that million a double, so the result will be a double. Running this, with
 this change.

            Ave = 50104,60 , elapsed = 3018000 nanos or 3,02 ms

 I can see, when I run it a couple of times, the time is usually somewhere between 3 and 5 milliseconds, though not always,
 and can be sometimes larger than that. Your elapsed time will be different from mine, depending on what system you're
 operating on.

        In the last lecture, I showed you two ways to perform the summation in parallel parts. The use of the ForkJoinPool,
 with either Callable or a RecursiveTask is valuable to know, especially if you can divide up your one large unit of work
 into smaller tasks. In the case of dividing the work over a data set, as we did in that example, using a parallel stream
 is by far, the simplest option. There are two ways to make a stream parallel.
 **/
//End-Part-2

        /*start = System.nanoTime();
        double averageParallel = Arrays.stream(numbers).parallel().average().orElseThrow();
        long elapsedParallel = System.nanoTime() - start;
        System.out.printf("Ave = %.2f , elapsed = %d nanos or %.2f ms%n", averageParallel, elapsedParallel, elapsedParallel/ 1000000.0);*/

//Part-3
/**
        The first is simply to include the parallel intermediate operation. I'm going to copy the last 4 statements I have
 here, and paste a copy directly below. And of course I have errors, where I have duplicate variable names. I'll remove
 the type declaration from the first statement, which is assigning a value to start, since I don't need a separate variable
 each time for this. I'll change averageSerial to averageParallel on the second statement, and elapsedSerial to elapsedParallel
 on the 3rd statement. I have to also change these variables in the system.out.printf statement. I haven't yet made this
 a parallel stream. It's very easy to turn a stream, into a parallel stream.

                 double averageParallel = Arrays.stream(numbers).average().orElseThrow();
                                                    to
                double averageParallel = Arrays.stream(numbers).parallel().average().orElseThrow();

 I can add an intermediate operation, parallel, after I get the stream. And that's it. Java will now perform this average
 operation in parallel, using ForkJoinPool's common pool, like I used in the previous lecture, but it's used implicitly
 here. I'll run this.

                 Ave = 49971,32 , elapsed = 3718300 nanos or 3,72 ms
                 Ave = 49971,32 , elapsed = 3378100 nanos or 3,38 ms

 And I'll run it several times. Your results may be different, but for my system, the results are interesting, and sometimes
 unexpected. The parallel operation isn't always faster, according to my timings here. Let's explore this a little further,
 and execute each of these multiple times, and get an average elapsed time difference. I'll start by declaring a couple
 of variables, before I do either of these calculations.
 **/
//End-Part-3

        long delta = 0;
        int iterations = 25;

        for (int i = 0; i < iterations; i++) {
            long start = System.nanoTime();
            double averageSerial = Arrays.stream(numbers).average().orElseThrow();
            long elapsedSerial = System.nanoTime() - start;

            start = System.nanoTime();
            double averageParallel = Arrays.stream(numbers).parallel().average().orElseThrow();
            long elapsedParallel = System.nanoTime() - start;
            delta += (elapsedSerial - elapsedParallel);
        }

        System.out.printf("Parallel is [%d] nanos or [%.2f] ms faster on average %n", delta / iterations, delta / 1000000.0 / iterations);

//Part-4
/**
        I'll create a long variable, I'll call delta, that'll track the time differences between the serial execution,
 and the parallel execution. I'll set up a variable for the number of times I want to execute these tests, and call it
 iterations. Now, I'll set up a for loop, around my two existing tests. I'll loop from 0 to the number of iterations in
 my variable, so this will execute 100 times, to start with. I'll enclose the code in the ending for loop bracket. I'll
 remove both print statements. I'll include a calculation of the difference, between the serial elapsed time, and the
 parallel elapsed time. I'll keep a running total of the differences, in the delta field. Next, I'll print out the average
 delta. I don't really care about the average of the array any more, since it will generally be half of whatever value
 I've got, in the numbers length field. I'll compare the parallel to the serial, both in nanoseconds and milli seconds.
 To do that, I'll get the delta divided by the loop iterations, for nanoseconds, and for milliseconds, I'll again divide
 by one million. So this code, will loop 100 times, for the same array, and get the average each time, using a serial
 stream, then get the average again, next using a parallel stream. It will calculate the difference in elapsed time,
 subtracting the parallel time from the serial time, which we might assume is higher, because you'd expect the serial
 stream to be slower. We'll print the average difference after the loop. Ok, so I'll run this.

             Parallel is [-131764] nanos or [-0,13] ms faster on average

 For my own test cases, I usually get a negative number, meaning the parallel isn't faster than the serial processing at
 all. Maybe this surprises you, maybe it doesn't. I'll change the size of my array, by changing the value in numbers length,
 from 100 thousand, and bump that up to 100 million. I'll also change my loop iterations, from 100 to 25, because these
 calculations will take longer. I'll run this again.

            Parallel is [41936620] nanos or [41,94] ms faster on average  (first run)
            Parallel is [38272264] nanos or [38,27] ms faster on average  (second run)
            Parallel is [37948228] nanos or [37,95] ms faster on average  (third run)
            Parallel is [39116408] nanos or [39,12] ms faster on average  (fourth run)

 This time it's going to take longer to run each iteration, so maybe I'll have to wait 10 seconds or so here, for this
 process to complete. When it does complete, I now should see that the parallel processing is faster than the serial. I'll
 run it a couple of times, just to confirm, that this remains true over several tests. This is one way to do what's called
 benchmark testing. Both times I run it, I get a similar result, that the parallel stream is somewhere in the neighborhood
 of 40 milliseconds faster, for this much larger array. So why do you think, when my array had 100 thousand elements, it
 wasn't always consistent, or it tipped the other way. Why was serial processing faster in the case of a smaller array?
 There's some overhead associated with using parallel streams. Some operations are very fast naturally, like summations
 and averages. For these types of operations, the overheads involved with parallel streams or operations, may actually
 not be faster as we've seen, until your data set gets large enough.

                Why a parallel stream may not always be faster than a serial stream

    * Parallel streams introduce some overhead, such as the need to create and manage multiple threads. This overhead can
    be significant for small arrays.
    * Parallel streams need to synchronize their operations to ensure that the results are correct. This synchronization
    can also add overhead, especially for small arrays.

 The cautionary tale here is, don't assume a parallel stream will always improve performance. In fact, there are times,
 though you specify you want to use a parallel stream, Java's optimizations may not even use one. If your collection of
 data needs to be ordered, this too may effect, how well the parallel operations perform. I'll get into these two topics,
 in the next lecture, as we start looking at ordered streams and reductions in parallel.
 **/
//End-Part-4


    }
}
