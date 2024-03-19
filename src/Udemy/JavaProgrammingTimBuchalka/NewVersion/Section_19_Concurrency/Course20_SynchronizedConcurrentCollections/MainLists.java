package CourseCodes.NewSections.Section_19_Concurrency.Course20_SynchronizedConcurrentCollections;

import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Stream;

public class MainLists {

    public static void main(String[] args) {

        /*var persons = Stream.generate(Person::new)
                .limit(10000)
                .parallel()
                .toArray(Person[]::new);

        System.out.println("Total = " + persons.length);*/

//Part-3
/**
        I'll add a main method in this class as well, using psvm. I'll start with a variable called persons, and I'll
 just use local variable type inference, so var as the type. I'll generate a stream of persons. Again, limiting it to 10000,
 and running it as a parallel stream. I'll call the toArray terminal operation, and I'll pass the method reference, that's
 a constructor for a new Person array. And just a reminder. If I didn't do this, and just had no arguments, I'd get an
 Object array back, rather than a typed Person array. I'll print the total number of array elements out. Running this code,

                Total = 10000

 I'll see that I get 10 thousand records in my array, and the inferred type is an array of Persons, so this is good. There
 are some instances where you may include the parallel operation, but the Stream's pipeline manager may not actually execute
 it in parallel. Let's see if this is the case in this instance.
 **/
//End-Part-3

        var threadMap = new ConcurrentSkipListMap<String, Long>();

        var persons = Stream.generate(Person::new)
                .limit(10000)
                .parallel()
                .peek((p) -> {
                    var threadName = Thread.currentThread().getName()
                            .replace("ForkJoinPool.commonPool-worker-", "thread_");
                    threadMap.merge(threadName, 1L, Long::sum);
                })
                .toArray(Person[]::new);

        System.out.println("Total = " + persons.length);

        System.out.println(threadMap);

        long total = 0;
        for (long count : threadMap.values()) {
            total += count;
        }
        System.out.println("ThreadCounts = " + total);

//Part-4
/**
        To do this, I'll set up a concurrent map variable, that'll collect the thread names, and count the total processes
 each thread executes. This map will be keyed by a thread name, and contain long values. I'll use ConcurrentSkipListMap
 in this case, which means it will be sorted by the natural order of the keys, so alphabetically by thread name in this
 case. Next, I'll use the peek intermediate operation, inserting that after parallel, but before the toArray terminal
 operation. Each element in the stream is a person record, so I'll use "p" as my lambda expression argument. I'll get the
 current thread name. I'll strip out part of the name, if it contains information about the common Pool worker, and replace
 it with a shorter thread name. I'll merge the threadName into the map, with 1 as the initial value, or summing the values
 for each processed thread. Ok so let me just say, I don't recommend that you use the peek method like this in real life.
 The peek method is usually used for some sort of debugging code, and here, my debugging code is creating side effects.
 In this case, it's intentional because I'm using the code this way, just to demonstrate how to examine which threads are
 doing the work. Next, I'll print this data out. First, I'll just print out the entire map. Next, I want to add up the
 values in each entry, so I'll set up a total variable, a long. I'll loop through the thread map's values. I'll add them
 to total. And I'll print the total, calling it thread counts. If I run the code now,

         Total = 10000
         {main=768, thread_1=512, thread_10=640, thread_11=384, thread_12=384, thread_13=528, thread_14=384, thread_15=768, thread_16=512, thread_17=512, thread_18=512, thread_19=640, thread_2=384, thread_3=512, thread_4=512, thread_5=640, thread_6=512, thread_7=384, thread_8=128, thread_9=384}
         ThreadCounts = 10000

 I'll see how the work was distributed across different threads, and I can confirm this is really running in parallel,
 and that it accurately collected 10 thousand values on my stream, into a Person Array. The number of threads and which
 threads will change each time I run it. I'll run it a couple of times, and each time, you might see just a couple of threads,
 or you might see as many as there are in the ForkJoinPool's connection pool. In my own case that's seven, but yours may
 be different, plus the main thread is also processing data. Next, I'll pick up at this same point in the code, and continue,
 to talk about additional concurrent classes, as well as atomic variables.
 **/
//End-Part-4


    }
}
