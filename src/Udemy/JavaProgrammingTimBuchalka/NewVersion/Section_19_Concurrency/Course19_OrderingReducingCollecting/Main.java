package CourseCodes.NewSections.Section_19_Concurrency.Course19_OrderingReducingCollecting;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

//Part-1
/**                                                 Parallel Streams

        In the last lecture, I gave you a very simple demonstration of using a parallel stream, using an intermediate operation.
 But even with that simple example, we learned that It's not always a straightforward decision, whether to use parallel
 operations or not. Nothing is free in life generally, and that's true of software too. So even though it's easy and seems
 like it would be a good idea, to always use parallel streams when you can, there are some very good reasons not to do so.
 In this lecture, I'll continue to show you some of these considerations. Obviously in the last example, when we got the
 average of a million numbers, the order of the elements being processed didn't matter. There are cases though where you
 might want to process an ordered stream. Doing this with a parallel stream is possible, but again, depending on the size
 of the data, it might not get you the performance gain you were hoping for, since ordering requires a synchronization
 process, and time to sort. Let's consider a couple of ordered operations in the next sample of code.
**/
//End-Part-1

record Person(String firstName, String lastName, int age) {

    private final static String[] firsts =
            {"Able", "Bob", "Charlie", "Donna", "Eve", "Fred"};
    private final static String[] lasts =
            {"Norton", "OHara", "Petersen", "Quincy", "Richardson", "Smith"};

    private final static Random random = new Random();

    public Person() {
        this(firsts[random.nextInt(firsts.length)],
                lasts[random.nextInt(lasts.length)],
                random.nextInt(18, 100));
    }

    @Override
    public String toString() {
        return "%s, %s (%d)".formatted(lastName, firstName, age);
    }
}

//Part-2
/**
        I've created a new project called ParallelStreamsAndMore, with a new main class and method. Before I do anything
 with streams, I'll create a record for a Person. I'll include this in the main.java source file. I'll just have each
 person have a first name, last name and age. I'll randomly assign each person's name, and their age. For the first and
 last names, I'll create a short array of six values to pick from. I'll start with a private final static array of strings,
 with six possible first names specified. I'll use names that start with the letters A, through F, so Able, Bob, Charlie,
 Donna, Eve and Fred will do. I'll do the same thing for last names, but use N, through S names, so Norton, Ohara, Petersen,
 Quincy, Richardson and Smith here. I want a random number generator, so I'll set that up as private final and static as
 well, and initialize it. I'll create a custom constructor, with no arguments. I'll just code this manually. I'll make
 the constructor public and no arguments, since all the fields will be generated. I'll get the first name randomly, from
 the first names array, And I'll do the same for the last name. And the age will be a random integer, between 18 and 99,
 so my upper bound has to be 100. Finally I want to override the toString method. I'll insert a generated toString, but
 next select none, for the fields. I'll remove the generated return statement, and instead, return a formatted string.
 I'll print the last name first, a comma, the first name, and then the age in parentheses. Ok, so with this record, I can
 now use it to run some tests, with parallel streams. Getting back to the main method now,
 **/
//End-Part-2

public class Main {

    public static void main(String[] args) {

        /*Stream.generate(Person::new)
                .limit(10)
                .sorted(Comparator.comparing(Person::lastName))
                .forEach(System.out::println);*/

//Part-3
/**
        I'll generate a list of 10 persons. I can use Stream.generate, passing it the method reference for a new Person.
 That gives me an infinite stream, so I'll limit it to 10. I'll sort by the last name, using the sorted intermediate
 operation, and passing it a Comparator derived using the Person's last name. Again I can pass that as a method reference.
 Finally, I'll print each record, using the for Each terminal operation. Running this code,

                 Norton, Charlie (31)
                 Norton, Eve (59)
                 OHara, Fred (72)
                 Petersen, Charlie (26)
                 Petersen, Able (45)
                 Petersen, Eve (87)
                 Quincy, Donna (59)
                 Richardson, Charlie (26)
                 Richardson, Charlie (55)
                 Richardson, Charlie (48)

 I'll get 10 persons, with randomly selected last and first names. Because I have such short lists to choose from, I may
 get duplicates, but the names will be printed in order by the last name. Now, let's make this stream parallel.
 **/
//End-Part-3

        /*Stream.generate(Person::new)
                .limit(10)
                .parallel()
                .forEachOrdered(System.out::println);*/

//Part-4
/**
        I can add that right after the limit operation. If you're observant, you might notice that IntelliJ has grayed out
 the sorted operation. I'll come back to that in a second, but first I'll run this.

             Quincy, Donna (33)
             Norton, Fred (96)
             Quincy, Bob (94)
             Smith, Bob (30)
             Norton, Charlie (38)
             Norton, Charlie (52)
             OHara, Bob (70)
             Quincy, Charlie (98)
             Norton, Eve (49)
             Petersen, Charlie (74)

 So regardless of whether this operation was faster or not, in parallel, you can see we've got a problem. My persons aren't
 sorted right. Let's highlight the sorted operation, and see what IntelliJ has to say about it. "Redundant sorted call.
 The Subsequent for each operation, doesn't depend on the sort order, for parallel streams". Replace the terminal operation
 with for Each Ordered. What does this mean? Let's hover over the forEach operation in IntelliJ. This pops up the API
 documentation. What I want you to read and understand is, that

    * For parallel stream pipelines, this operation, meaning the "forEach" operation, does not guarantee to respect the
    encounter order of the stream. As doing so, sacrifices the benefit of parallelism.

 If you want things ordered by parallel processes, you have to do things a little differently, at the expense of some of
 the benefits you might get from parallelism. I'll comment out the sorted operation. And I'll change forEach, to for EachOrdered.
 Running this code,

             Norton, Fred (97)
             Quincy, Able (92)
             Petersen, Bob (32)
             Smith, Eve (72)
             Quincy, Bob (92)
             OHara, Donna (72)
             Richardson, Able (97)
             Quincy, Charlie (75)
             OHara, Eve (76)
             Petersen, Bob (37)

 I can see this isn't sorted in anyway that I can see, so what does for Each Ordered really mean or do? This is a bit
 easier to see, if we save off some persons in a sorted array, then use the sorted array in a stream.
 **/
//End-Part-4

        /*var persons = Stream.generate(Person::new)
                .limit(10)
                .sorted(Comparator.comparing(Person::lastName))
                .toArray();

        for (var person : persons) {
            System.out.println(person);
        }
        System.out.println("-----------------------------");

        Arrays.stream(persons)
                .limit(10)
                .parallel()
                .forEachOrdered(System.out::println);*/

//Part-5
/**
        I'll insert the code to do this, before this stream.generate method. This will look like the code I already have,
 generating 10 new persons. Again, I'll limit this stream to 10 for now. I'll include the sorted operation again, sorting
 by last name only. And I'll call the to Array terminal operation. I'll next add the code to print this generated array
 out. I'll loop through the persons array. And print each person. I'll include a separation line. Before I run this, I'll
 change my second stream, to use this array as the source. I'll run this now.

             Norton, Able (67)
             Norton, Charlie (63)
             OHara, Donna (48)
             OHara, Fred (88)
             Petersen, Fred (55)
             Quincy, Charlie (68)
             Richardson, Charlie (81)
             Richardson, Bob (22)
             Richardson, Able (95)
             Smith, Bob (93)
             -----------------------------
             Norton, Able (67)
             Norton, Charlie (63)
             OHara, Donna (48)
             OHara, Fred (88)
             Petersen, Fred (55)
             Quincy, Charlie (68)
             Richardson, Charlie (81)
             Richardson, Bob (22)
             Richardson, Able (95)
             Smith, Bob (93)

 You can see the array is sorted by last name. And now the stream that is sourced by this array, is also printed in the
 same order.
 **/
//End-Part-5

        var persons = Stream.generate(Person::new)
                .limit(10)
                .sorted(Comparator.comparing(Person::lastName))
                .toArray();

        for (var person : persons) {
            System.out.println(person);
        }
        System.out.println("-----------------------------");

        Arrays.stream(persons)
                .limit(10)
                .parallel()
                .forEach(System.out::println);

//Part-6
/**
        I'll change forEachOrdered back to forEach, and run that again.

             Norton, Fred (72)
             Norton, Fred (39)
             Quincy, Eve (77)
             Quincy, Eve (42)
             Quincy, Donna (92)
             Richardson, Able (26)
             Richardson, Charlie (25)
             Richardson, Fred (63)
             Richardson, Eve (54)
             Smith, Donna (38)
             -----------------------------
             Richardson, Charlie (25)
             Richardson, Able (26)
             Richardson, Eve (54)
             Smith, Donna (38)
             Richardson, Fred (63)
             Norton, Fred (39)
             Norton, Fred (72)
             Quincy, Eve (77)
             Quincy, Eve (42)
             Quincy, Donna (92)

 Now you can see that the second set of names is not ordered, not in the stream source's order here. So the for each ordered
 operation guarantees the order, to be the same as the stream source's order. It doesn't guarantee that the stream will
 be sorted. Using the forEachOrdered method on a parallel stream is going to increase the overhead, of the parallel processing.
 To figure out how much overhead is incurred, you'd need to do metrics on your pipeline, using different data set sizes,
 as I did in the last lecture. I won't cover that here, because I want to cover two other topics, reduction and collection,
 using parallel streams.
 **/
//End-Part-6

        System.out.println("-----------------------------");

        int sum = IntStream.range(1, 101)
                .parallel()
                .reduce(0, Integer::sum);

        System.out.println("The sum of the numbers is: " + sum);

//Part-7
/**
        First, let's look at a simple reduction on a parallel stream. I'll add another separator line. I'll get a stream
 of integers, from the IntStream class, using the range method. The second argument is an exclusive upper bound, so if I
 want a list from 1 to 100, I need to put 101 as the upper bound. I'll make this a parallel stream. I'll use the reduce
 terminal operation, that takes 2 arguments, the identity which both creates and initializes the value, and the accumulator
 which adds valued. Here, it will just be the sum. I'll print the sum. Running this code,

             -----------------------------
             The sum of the numbers is: 5050

 I get the correct sum of the numbers, 5050, using parallel processing, with a reduction. Again, you'd want to benchmark
 your timings, but you'd expect that at some point getting the sum of a large set of numbers, using a reduction method
 like this, would tip the scales to being better performant in parallel. Let's look at another reduction though, using
 strings.
 **/
//End-Part-7

        String humptyDumpty = """
                Humpty Dumpty sat on a wall.
                Humpty Dumpty had a great fall.
                All the king's horses and all the king's men
                couldn't put Humpty together again.
                """;

        System.out.println("-----------------------------");
        var words = new Scanner(humptyDumpty).tokens().toList();
        words.forEach(System.out::println);
        System.out.println("-----------------------------");

//Part-8
/**
        I'll set up a text block, with a familiar nursery rhyme. I'll call it humpty dumpty. And quickly type up the rhyme
 here. I'll print a separator line. I'll use scanner, with the text block as input, and get the tokens stream, to get a
 list of words. I'll print the words out, And I'll print another separator line. I'll run this.

                 -----------------------------
                 Humpty
                 Dumpty
                 sat
                 on
                 .....
                 put
                 Humpty
                 together
                 again.
                 -----------------------------

 I'll see each word, or token from the scanner in this case, printed on each line.
 **/
//End-Part-8

        /*var backTogetherAgain = words
                .stream()
                .reduce(
                    new StringJoiner(" "),
                    StringJoiner::add,
                    StringJoiner::merge
                );

        System.out.println(backTogetherAgain);*/

//Part-9
/**
        So now, I'll use a stream, to put humpty dumpty back together again. I can do this with the reduce method, using
 the method that takes 3 arguments. These are an identity, an accumulator and a combiner. I'll use a new StringJoiner,
 with a space for a delimiter, as the identity. The accumulator will be the add method on StringJoiner. Finally, the combiner
 will be the merge method, on the StringJoiner class. I'll print the result out next. I'll run this code.

             -----------------------------
             Humpty Dumpty sat on a wall. Humpty Dumpty had a great fall. All the king's horses and all the king's men couldn't put Humpty together again.

 And you can see that worked, using a serial stream. The nursery rhyme was reconstructed from my list of words, using
 StringJoiner with a space.
 **/
//End-Part-9

        /*var backTogetherAgain = words
                .parallelStream()
                .reduce(
                        new StringJoiner(" "),
                        StringJoiner::add,
                        StringJoiner::merge
                );

        System.out.println(backTogetherAgain);*/

//Part-10
/**
        I can change this to a parallelStream, by changing stream to parallelStream. This is the second way to get a stream.
 Like the stream method on most collection classes, most also have a parallel stream method, which I'll use here. Running
 this code, may have unexpected results.

             Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException
             at java.base/jdk.internal.reflect.DirectConstructorHandleAccessor.newInstance(DirectConstructorHandleAccessor.java:62)
             at java.base/java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:502)
             .....
             at java.base/java.util.concurrent.ForkJoinTask.getThrowableException(ForkJoinTask.java:542)
             at java.base/java.util.concurrReferencePipeline.java:667)
             at Course19_OrderingReducingCollecting.Main.main(Main.java:332)
             Caused by: java.lang.ArrayIndexOutOfBoundsException: arraycopy: last destination index 24795 out of bounds for byte[24794]
             at java.base/java.lang.System.arraycopy(Native Method)
             at java.base/java.lang.String.getBytes(String.java:4722)
             .....
             at java.base/java.util.concurrent.ForkJoinPool.scan(ForkJoinPool.java:1843)
             at java.base/java.util.concurrent.ForkJoinPool.runWorker(ForkJoinPool.java:1808)
             at java.base/java.util.concurrent.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:188)

 I'll usually get one of several different exceptions, but occasionally I'll get an empty string result. So what's going
 on here? Well, it turns out that StringJoiner isn't thread safe, and these errors are being caused by interleaving threads.
 **/
//End-Part-10

        /*var backTogetherAgain = words
                .parallelStream()
                        .collect(Collectors.joining(" "));

        System.out.println(backTogetherAgain);*/

//Part-11
/**
        I'm going to replace this code, this reduce method, with a special type of Collector operation instead. I'll first
 remove the entire reduce operation. I'll then add a collect operation, and as the only argument, I'll call Collectors.joining,
 passing that a space in quotes. This method uses a StringJoiner, but in a thread safe way. I'll run this code.

         -----------------------------
         Humpty Dumpty sat on a wall. Humpty Dumpty had a great fall. All the king's horses and all the king's men couldn't put Humpty together again.

 This code completes and prints out the nursery rhyme correctly. I'll run it a couple of times, and you can see, this works
 each time.
 **/
//End-Part-11

        /*var backTogetherAgain = words
                .parallelStream()
                .reduce("", (s1, s2) -> s1.concat(s2).concat(" "));

        System.out.println(backTogetherAgain);*/

//Part-12
/**
        Instead of StringJoiner, I'll try a different reduction operation, this time, I'll use Strings and String concatenation.
 This time, I'll use the two argument reduce operation, so the first is the identity. I'll pass a new empty string. The
 second argument is an accumulator. In this case, I'll pass a lambda, that concatenates one string to another, then concatenates
 a space after that. I'll run this code like this.

         -----------------------------
         Humpty Dumpty sat   on a wall.    Humpty Dumpty had   a great  fall. All     the king's horses   and all the    king's men couldn't   put Humpty  together again.

 Notice that this code seems to have different numbers of spaces, almost randomly throughout. When you run a reduction
 in parallel, each thread will use the identity, to create a new instance. It will then start processing some part of the
 data, using the accumulator, to combine it's queue of data. It will then again use the accumulator to join its reduction
 to other threads' results. I'll revert this last update, so that I revert back to a parallel stream that works correctly:
 **/
//End-Part-12

        var backTogetherAgain = words
                .parallelStream()
                .collect(Collectors.joining(" "));

        System.out.println(backTogetherAgain);

        Map<String, Long> lastNameCounts =
                Stream.generate(Person::new)
                        .limit(10000)
                        .parallel()
                        .collect(Collectors.groupingBy(
                                Person::lastName,
                                Collectors.counting()
                        ));

        lastNameCounts.entrySet().forEach(System.out::println);

//Part-13
/**
        Parallel streams are great, in some cases, as you can see, but can get complicated in others. You can't just flip
 a stream to a parallel stream, without some due diligence and thorough testing. I want to show you one more example, with
 a collection, this time, getting a Map back. I'll set up a variable named lastNameCounts, that will be a Map, with a key
 that's a string, and value that's a long. This will be the count of persons, that have the same last name. I'll generate
 a new set of 10 thousand persons. I'll process these persons in parallel. I'll use the collect method, passing it Collectors.groupingBy,
 I'll group by last name. And get the counts, with the counting collector. Lastly, I'll print this map, each key value pair.
 I'll run this.

             Richardson=1642
             Quincy=1694
             Smith=1698
             Norton=1750
             Petersen=1632
             OHara=1584

 And I'll get each last name printed, with the counts of each that were on this stream. You can see there's a pretty good
 average distribution of counts, across the six last names, of our ten thousand randomly generated persons. I'll add a
 little bit of code, to add these counts up.
 **/
//End-Part-13

        long total = 0;
        for (long count : lastNameCounts.values()) {
            total += count;
        }
        System.out.println("Total = " + total);

//Part-14
/**
        I'll set up a total variable, initializing it to zero. I'll loop through the values in the mapped results. I'll
 add each value to the total. Finally, I'll print that total out.

                 Richardson=1679
                 Quincy=1662
                 Smith=1725
                 Norton=1655
                 Petersen=1673
                 OHara=1606
                 Total = 10000

 Running this code confirms that the counts equal 10 thousand. Although you can use groupingBy with parallel streams,
 Java states in it's API for this method, that the merge method, of combining multiple thread's maps, is very costly, and
 that using groupingByConcurrent is much more efficient. The Collectors.groupingBy Concurrent method will return a concurrent
 implementation of a map. Next, I'll be discussing what this means, and what concurrent collections are.
 **/
//End-Part-14

    }
}
