package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_13_Streams.Course07_TerminalOperations;

import java.util.Arrays;
import java.util.stream.IntStream;

//Part-1
/*
        We've covered a lot of ground in this section, using just one terminal operation, forEach. Now it's time to see
    why stream processes are such a welcome feature, as I show you other terminal operations we can use.

            Matching and Searching      Transformations and Type Reductions     Statistical (Numeric) Reductions    Processing

                  allMatch                            collect                            **average**                  forEach
                  anyMatch                            reduce                               count                      forEachOrdered
                  *findAny*                           toArray                              *max*
                  *findFirst*                         toList                               *min*
                  noneMatch                                                                **sum**
                                                                                           **summaryStatistics**
        * Returns an Optional instance
       ** Available on DoubleStreams, IntStreams, LongStreams

        This table has them categorized by the result. Some are designed to find matches, most of which are targets for
    a Predicate lambda expression. Some are designed to transform stream data into a collection, or some other reference
    type. Others aggregate information, to count elements, or find a minimum or maximum value, and don't take arguments.
    The primitive streams have average and sum as well, and a summaryStatistics operation which gives you count, min,
    max, average and sum in one result. A couple of these operations return a special type, called an Optional. I have
    a lecture coming up on the Optional class. And of course we've talked about forEach, but there's also a forEachOrdered
    operation. A reduction operation is a special type of terminal operation. Stream elements are processed, to produce
    a single output. The result can be a primitive type, like a long, in the case of the count operation. The result can
    be a reference type, like Optional or one of the Statistics types I'll be covering shortly. It can also be any type
    of your choice, such as an array, a list, or some other type. I want to start by looking at some of the statistical
    reduction methods. Let's look at some code.
*/
//End-Part-1

public class Main {

    public static void main(String[] args) {

        var result = IntStream
                .iterate(0, i -> i <= 1000, i -> i = i + 3)
                .summaryStatistics();
        System.out.println("Result = " + result);

//Part-2
/*
        I'll use an IntStream in this example. Like any stream, I can use the iterate method on that, and I'll pass 0 as t
    he seed. I'll make the condition less than equal to 1000, and I'll increment by 10 each time. I'll use the summaryStatistics
    operation. I'll print the result. I've again used type inference, and you can see from IntelliJ's inlay hints, the
    result is inferred to be an IntSummaryStatistics type. I'll run this

                Result = IntSummaryStatistics{count=101, sum=50500, min=0, average=500.0, max=1000}

    and see what I get. There you can see that the type gets printed out, so IntSummaryStatistics, with data about this
    stream. In this process, I had 101 stream elements, the sum was 50500, the minimum value was zero. The average is an
    even 500, and the max value was 1000. That's kind of a useful operation. Let's try this code again, but this time,
    I'll change the number I'm incrementing by, from 10 to 3. Running that,

                Result = IntSummaryStatistics{count=334, sum=166833, min=0, average=499,500000, max=999}

    we get a different set of stats, I have more elements, and the statistics are different. Using this operation would
    be a good place to start, when you get a set of data, and you want to try to start to understand it. Remember, if you
    have a stream of instances, you can stream any data field to an int or double stream, using one of the map operations,
    and then use this terminal operation to give you a quick overview. You can also individually call the count, sum, min,
    max and average terminal operations, but I'll show you these after I cover the Optional class, which is returned from
    several of these methods. I'll show another example of code, to evaluate leap year data, between the years 2000 and
    2025.
*/
//End-Part-2

        var leapYearData = IntStream
                .iterate(2000, i -> i <= 2025, i -> i = i + 1)
                .filter(i -> i % 4 == 0)
                .peek(System.out::println)
                .summaryStatistics();
        System.out.println("Leap Year Data = " + leapYearData);

//Part-3
/*
        I'll set up another variable, leapYearData. Again I'll use iterate, with 2000 as the starting year, and 2025 as
    the maximum year. I'll increment each next year by 1. A leap year is evenly divisible by 4, so I'll use the modulus operator to
    filter. If i mod 4 is equal to zero, then it's evenly divisible by 4 and a leap year. I'll get statistics first. And
    I'll print those out If I run this,

                    Leap Year Data = IntSummaryStatistics{count=7, sum=14084, min=2000, average=2012,000000, max=2024}

    I can see that there are 7 leap years between 2000 and 2025, with 2000 being the minimum year, and 2024 being the
    maximum. In fact, this is a good place for the peek operation, because it might be nice to see what those years are.
    I'll insert that after the filter operation, but before summary statistics. I'll print out the filtered numbers here.
    Running that,

                    2000
                    2004
                    2008
                    2012
                    2016
                    2020
                    2024
                    Leap Year Data = IntSummaryStatistics{count=7, sum=14084, min=2000, average=2012,000000, max=2024}

    I get the 7 leap years printed out first. You can see that peek is useful when you're doing reductions. It gives you
    a window into what is happening. I can use terminal operations to return information about the aggregated data set.

                 Return Type             Terminal Operations            Stream

                    long                      count()                     ALL
                  Optional                    max()                       ALL
                  Optional                    min()                       ALL

                OptionalDouble                average()                   DoubleStream
                                                                          IntStream

                                                                          LongStream
                   double                     sum()                       DoubleStream
                    int                                                   IntStream
                    long                                                  LongStream

            DoubleSummaryStatistics           summaryStatistics()         DoubleStream
             IntSummaryStatistics                                         IntStream
             LongSummaryStatistics                                        LongStream

    The methods shown on this table have no arguments, They all return numerical data, either directly, or in specialized
    types to hold that data. I'll show examples of the rest of these after the Optional lecture. Before that, I want to
    talk about methods for matching elements on a specific condition.

                Return Type                      Method                                  Description
                  boolean          allMatch(Predicate<? super T> predicate)              Returns true if all stream elements meet
                                                                                         the condition specified.
                  boolean          anyMatch(Predicate<? super T> predicate)              Returns true if there is at least one match
                                                                                         to the condition specified.
                  boolean          noneMatch(Predicate<? super T> predicate)             This operation returns true if no elements match.

    There are three terminal operations that let you get an overall sense, of what your stream elements contain, based
    on some specified condition. These all return a boolean, and take a Predicate as an argument. You can think of these
    as ways to ask true or false questions about the data set, the stream, as a whole. The all Match operation returns
    true if every stream element meets the condition specified. AnyMatch returns true, if there is a single stream element
    meeting the condition. Lastly, none match returns true if no elements can be found that match the condition. I'm going
    to quickly create a Seat record again.
*/
//End-Part-3

        Seat[] seats = new Seat[100];
        Arrays.setAll(seats, i -> new Seat((char) ('A' + i / 10), i % 10 + 1));
        Arrays.asList(seats).forEach(System.out::println);

//Part-5
/*
        Instead of creating a stream, I'm going to first create an array of seats. I'll start with a new Seat array, with
    100 null references. This seat creation code is basically the same as I used before, in the intermediate operations
    lecture, so the row marker will be A through J in this case, depending on the value of i, and the seat number itself
    will be 1 through 10. I'll print those out, to confirm the data is what I expect it to be. If I run this code,

                    Seat[rowMarker=A, seatNumber=1, isReserved=false]
                    Seat[rowMarker=A, seatNumber=2, isReserved=false]
                                         ...
                    Seat[rowMarker=J, seatNumber=9, isReserved=false]
                    Seat[rowMarker=J, seatNumber=10, isReserved=false]

    I can confirm that my seat rows start with A, and end with J, and that the seat numbers are a number between 1 and 10.
    I can also see the random nature of the seats that are reserved. I'm going to use this array as the source of my streams.
*/
//End-Part-5

        long reservationCount = Arrays
                .stream(seats)
                .filter(Seat::isReserved)
                .count();
        System.out.println("reservationCount = " + reservationCount);

//Part-6
/*
        I want to use the count operation first, to see how many reserved seats I have total. The other statistics aren't
    that interesting in this case. I'll start with a long variable, which is what the count operation returns. I'll use
    Arrays.stream, and pass my seats array to create a source. Next, I want to count only reserved seats, so I'll use
    Seat::isReserved in my filter operation. I'll use the count terminal operation. I'll print out the number of reserved
    seats. Running that code,

                    reservationCount = 54

    I should get a different count each time I run it, since I'm randomly reserving these seats. Next, I'll demonstrate
    each of the matching terminal operations.
*/
//End-Part-6

        boolean hasBookings = Arrays
                .stream(seats)
                .anyMatch(Seat::isReserved);
        System.out.println("hasBookings = " + hasBookings);

//Part-7
/*
        For the first, I'll create a boolean variable called hasBookings, and assign that to the result of my stream
    pipeline. The source will again be my seats array. I don't have to filter with an intermediate operation, when I use
    these operations because the predicate is built in. I can call anyMatch, passing it the same predicate, the method
    reference. I'll print this out. This operation just checks to see if there's at least one element that's reserved.
    Running this,

                    hasBookings = true

    I can see that it's found some matches, and that has Bookings equals true, which you'd expect for the randomly generated
    reserved field. I'll copy that last segment of code and paste a copy below.
*/
//End-Part-7

        boolean fullyBooked = Arrays
                .stream(seats)
                .allMatch(Seat::isReserved);
        System.out.println("fullyBooked = " + fullyBooked);

//Part-8
/*
        In the copied code, I'll change hasBookings, my local variable name, to fullyBooked. I'll change the terminal
    operation anyMatch, to allMatch. Lastly, I'll change my print statement, to print fullyBooked equals, and use
    fullyBooked as the variable that gets printed. Running this code,

                    fullyBooked = false

    my reservationCount will change, but hasBookings should still be true. FullyBooked though will be false, since the
    random process I'm using to book seats, is unlikely to reserve every seat. Similar to all match is none match, so let
    me copy those last couple of statements and paste a copy below.
    challenge video on terminal operations.
*/
//End-Part-8

        boolean eventWashedOut = Arrays
                .stream(seats)
                .noneMatch(Seat::isReserved);
        System.out.println("eventWashedOut = " + eventWashedOut);

//Part-9
/*
        This time I'll change the local variable to be eventWashedOut. I'll change allMatch to noneMatch. And I'll print
    out eventWashedOut equals, and include the variable there. Running this code,

                        eventWashedOut = false

    you can see that eventWashedOut is false as well. Like fullyBooked, the random generation is likely to return several
    booked seats. I'll go back to the Seat record, and comment out that call to the canonical constructor. I'll copy and
    paste it below, changing the random part, to just true, meaning every seat will be reserved. This means the event is
    sold out, so if I run this, I get reservationCount is 100, and hasBookings equals true. Now even though fullyBooked
    is true, eventWashedOut is still false. I'll change the seat constructor one more time, passing just false this time.
    Running this code, I get a different story. There are no reservations, hasBookings is false, fullyBooked is certainly
    false, and eventWashedOut is true. These three conditions cover every scenario, and allow you to use stream terminal
    operations, to get information about all elements in that stream. I still have two more types of terminal operations
    to cover, I'll bring this to a close here. I'll talk about the Transform and Process terminal operations next, but
    first I want to set up some code that I'll be using for the remainder of the lectures, and that will be used in a
    challenge for you on the terminal operations we've looked at so far. You can use the next lecture as an additional
    challenge. You can also just skip ahead, and import the code that I'll be creating, in the challenge lecture on terminal
    operations.
*/
//End-Part-9
    }
}
