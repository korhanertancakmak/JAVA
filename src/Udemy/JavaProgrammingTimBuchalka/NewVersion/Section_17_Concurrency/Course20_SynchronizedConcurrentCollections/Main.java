package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_17_Concurrency.Course20_SynchronizedConcurrentCollections;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

public class Main {

    public static void main(String[] args) {

        Map<String, Long> lastNameCounts =
                Stream.generate(Person::new)
                        .limit(10000)
                        .parallel()
                        /*.collect(Collectors.groupingBy(
                                        Person::lastName,
                                        Collectors.counting()
                                )*/
                        .collect(Collectors.groupingByConcurrent(
                                Person::lastName,
                                Collectors.counting()
                        ));

        lastNameCounts.entrySet().forEach(System.out::println);

        long total = 0;
        for (long count : lastNameCounts.values()) {
            total += count;
        }
        System.out.println("Total = " + total);

        System.out.println(lastNameCounts.getClass().getName());

//Part-1
/**
        I left off, in the last lecture, having generated a map of last names and counts, using a parallel stream. Although
 this code seemed to work fine, it's not how you'd want to write this code. I'll start out by printing the type of the map,
 that got returned from that last stream pipeline. I'll print the class name of the map instance, last name counts. I'll
 run this code,

            java.util.HashMap

 The last thing that's printed out, is from this new statement, and I can see that the type I get back, is the java.util.HashMap.
 You might be surprised to learn that the HashMap, as well as the other common collection implementations, like ArrayList,
 LinkedList, HashSet, and TreeSet, aren't thread safe.

                                            Why the HashMap isn't thread-safe

        The HashMap like the other mentioned collections,

    * Lacks synchronization. This class doesn't provide any internal synchronization mechanisms, to ensure thread-safety
    by concurrent processes. As a result, two or more threads modifying the same HashMap, can interfere with each other,
    leading to issues like lost updates, infinite loops, or inconsistent data.
    * There are also no guarantees of memory consistency, while iterating. When one thread is iterating over a HashMap
    while another thread modifies it, there's no guarantee that the iterator will reflect the most up-to-date state of
    the map. This can lead to ConcurrentModificationException, or unpredictable behavior during the iteration process.

 Let's pull up Java's API documentation for the HashMap class for a minute. I'll scroll down just a bit, until I see a
 note in bold. The note says, that "this implementation, meaning the HashMap, is not synchronized. If multiple threads
 access a hash map concurrently, and one of the threads modifies the map structurally, it must be synchronized externally.
 A structural modification is any operation, that adds or deletes one or more mappings. Merely changing the keyed value,
 doesn't count as a structural modification". We also learn here, that we can wrap the hashmap, with the Collections.synchronizedMap
 method. This method can be easily used, rather than writing your own custom code each time you want to synchronize this
 class externally. I'll be talking about this method in just a minute. But first let's talk about iterators while we're
 still looking at this documentation. These are all fail-fast, as it explains here, and throw a Concurrent Modification
 Exception when concurrent modifications are made. But what I'll point out here is, the statement that says, it would be
 wrong to write a program that depended on this exception, for its correctness. Ok, so what does all this mean? Although
 the HashMap isn't thread safe, each parallel thread will actually have its own copy of a map instance, and won't be
 contending with another thread for access to a single copy. When the threads complete, however, the code operates by
 merging two maps together, by key. This type of merge is computationally expensive, so it's recommended that for maps,
 you don't code your parallel streams this way.

                                 .collect(Collectors.groupingBy(
                                     Person::lastName,
                                     Collectors.counting()
                                 )

                                                to

                                .collect(Collectors.groupingByConcurrent(
                                        Person::lastName,
                                        Collectors.counting()
                                )

 I'll change my code here, to use a different Collectors method, which is more efficient for parallel streams. This is as
 simple as changing grouping by, to groupingByConcurrent. If I run this code,

                 Richardson=1654
                 Quincy=1670
                 Smith=1654
                 Norton=1674
                 Petersen=1659
                 OHara=1689
                 Total = 10000
                 java.util.concurrent.ConcurrentHashMap

 I still get 10000 counts total, distributed somewhat evenly over the six last names. What I want you to notice is, the
 class name of this new Map instance, that comes back from this stream pipeline. Instead of a HashMap, I've now got a
 ConcurrentHashMap. This is a class defined in the java.util.concurrent package. The concurrent hash map is one of several
 concurrent classes in this package, and I'll be talking about these more shortly. First though, I'll do something I don't
 want you to do in real life, if you can avoid it. I'm going to create a pipeline stream that has side effects, meaning
 it's going to change the state of an instance, that's not part of the pipeline. To do this, I'll set up a TreeMap variable
 manually, before I execute the stream pipeline.
 **/
//End-Part-1

        //var lastCounts = new TreeMap<String, Long>();
        //var lastCounts = new ConcurrentSkipListMap<String, Long>();
        var lastCounts = Collections.synchronizedMap(new TreeMap<String, Long>());

        Stream.generate(Person::new)
                .limit(10000)
                .parallel()
                .forEach((person) -> lastCounts.merge(person.lastName(), 1L, Long::sum));

        System.out.println(lastCounts);

        total = 0;
        for (long count : lastCounts.values()) {
            total += count;
        }
        System.out.println("Total = " + total);

        System.out.println(lastCounts.getClass().getName());

//Part-2
/**
        I'll create a scenario where the map already exists. In other words, the stream won't create the map, but it will
 add or change data in the map. I'll generate a stream of 10 thousand persons, as I did before. I'll make this a parallel
 stream. In the for Each method, I'll use the map merge method, to merge data into the existing map. If the last name isn't
 in the map, this should add the last name, with an initial value of 1 to start. Remember, this kind of change, where I'm
 adding a key is a structural change to the map. If the key exists, this code will just add 1 to the existing value.
 Finally, I'll print out this map. I'll copy my code that prints the total counts using the map values. I'll paste it after
 this last pipeline. I'll change the name of the variable in this pasted code, from last name counts, to just last counts,
 which is the name of my TreeMap variable. I'll run this code.

             {Norton=1341, OHara=1305, Petersen=1296, Quincy=1291, Richardson=1311, Smith=1363}
             Total = 7907
             java.util.TreeMap

 The output of this code is going to be unpredictable. Under many circumstances, I'll get an exception, when the merge
 method is executed in the stream. I get aConcurrentModificationException, from the stream pipeline code, while it's trying
 to use some kind of iterator. I'll run this a couple of times, until it actually hopefully completes, without an exception.
 You can see the last names are in order, but notice the total counts in the map. I get less than 10 thousand, which is
 how many elements were in the stream. This code, the way I have it coded, using a TreeMap, with a parallel stream making
 updates to it, potentially has many different kinds of problems. The first was the ConcurrentModification problem, and
 the second is probably due to memory consistency errors. These are just some of the problems that can occur, when you
 use a class that's not thread safe, in a multi-threaded operation. Using it with a parallel stream, like I'm doing here,
 really demonstrates the problems. Now, I'll change my TreeMap variable, to a different type of Concurrent Map, changing
 it to a sorted concurrent map, called Concurrent Skip List Map. I'll run this.

             {Norton=1653, OHara=1648, Petersen=1714, Quincy=1656, Richardson=1642, Smith=1687}
             Total = 10000
             java.util.concurrent.ConcurrentSkipListMap

 You can see that this map is still sorted by last name, but I didn't get any exception, and I'm also getting 10 thousand
 counts in my map here. I'll run it a couple of times, and confirm it works each time. Using a ConcurrentSkipListMap is
 one thread-safe option. To show you another, I'll revert that last change. This time, I'll wrap the instantiation of the
 TreeMap, in a call to a synchronized wrapper collection. I can do this by calling a method on the Collectors class, called
 synchronizedMap. I'll run this code.

             {Norton=1621, OHara=1657, Petersen=1744, Quincy=1716, Richardson=1592, Smith=1670}
             Total = 10000
             java.util.Collections$SynchronizedMap

 Notice, that the last names are again sorted, that this map also has 10 thousand records. The type was printed in this
 output too, and this is a special type on the Collections class, called SynchronizedMap. I'll run the code a couple of
 times, and here too, you can see, I always get 10 thousand records back, and I'm not getting any exceptions. So you can
 see you have several options to choose from. I've run through these pretty quickly, so now let me show you some of these
 special map types, on a table.

                                                Map Classes

                                    Sorted      Blocking      Thread-Safe       Stream Pipeline-Collectors Method
    HashMap                           No           No             No            .collect(groupingBy(Function classifier, ...)
    TreeMap                          Yes           No             No            .collect(TreeMap<keyType, valueType>::new, ...)
    ConcurrentHashMap                 No           No            Yes            .collect(groupingByConcurrent(Function classifier, ...)
    ConcurrentSkipListMap            Yes           No            Yes            .collect(ConcurrentSkipListMap<keyType, valueType>:: new, ...)
    Collections$SynchronizedMap      Yes          Yes            Yes

        You're already familiar with HashMap and TreeMap, and hopefully you remember that a TreeMap is sorted. Neither
 of these classes is thread-safe, and both should be avoided when you're working with parallel processes, if multiple
 threads are accessing them asynchronously. I've included the type of Collector you might use, if you were collecting
 stream pipeline data in either of these cases. By default, the groupingBy collector returns a HashMap. To get a different
 type of map back, you would specify your own map type, using the collect method, which I show for the TreeMap. There are
 two concurrent classes in the java.util.concurrent package for the map. These are ConcurrentHashMap and ConcurrentSkipListMap,
 and this last one is sorted. There's also a synchronized class we can get, by using a static method on the Collections
 classes. Only the SynchronizedMap is considered a blocking type, of the three thread-safe types shown on this table.
 This means other threads will block, waiting for access to the map. That's one of the big differences, between synchronized
 classes and concurrent classes.

                                Concurrent Classes vs. Synchronized Wrapper Classes

        Both concurrent and synchronized collections are thread-safe, and can be used in parallel streams, or in a multi-threaded
 application.

    * "Synchronized collections" are implemented using locks which protect the collection from concurrent access. This
    means a single lock is used to synchronize access to the entire map.
    * "Concurrent collections" are more efficient than synchronized collections, because they use techniques like fine-grained
    locking, or non-blocking algorithms to enable safe concurrent access without the need for heavy handed locking, meaning
    synchronized or single access locks.

 Concurrent collections are recommended over synchronized collections in most scenarios. Ok, so I've started with the Map,
 because it's often used with parallel streams, to collect and aggregate data from large data sets. Next, I'll show you
 examples using other collection types.

        For arrays and lists, you can use the terminal operations, toArray or toList respectively, with parallel streams.
 Again, this means each parallel process will be collecting elements first, in its own array or list, and the final part
 of the stream process combines each thread's data, into a single source. Let's quick look at these. To demonstrate these,
 I'll start with a new class, and call it MainLists, in the same package.
 **/
//End-Part-2

    }
}
