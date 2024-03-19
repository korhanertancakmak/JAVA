package CourseCodes.NewSections.Section_19_Concurrency.Course22_ArrayBlockingQueueConsumerTasks;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Stream;

public class VisitorList {

    private static final CopyOnWriteArrayList<Person> masterList;

    static {
        masterList = Stream.generate(Person::new)
                .distinct()
                .limit(2500)
                .collect(CopyOnWriteArrayList::new,
                        CopyOnWriteArrayList::add,
                        CopyOnWriteArrayList::addAll);
    }

//Part-1
/**
        In the last lecture, I set up half of a Producer Consumer Application, using the thread safe, array blocking
 queue, as a shared resource. I'll be adding code to the VisitorsList class. First, I'll create a master List of visitors.
 This will represent the park system's currently registered visitors. Our visitor's center only wants to give out discounts
 to new visitors. I'll make this private static and final as well. This time though, I'm going to use a CopyOnWriteArrayList,
 and not an ArrayBlockingQueue. This class is also thread safe, but it's not a fixed size, and it never blocks. I'll call
 this masterList. I'll populate this list with a static initializer block. Because this is final, it's the only place,
 other than the declaration statement itself, to assign a value. Again, this is a good opportunity to review the static
 initializer. It also lets me demonstrate one way you can collect a stream of elements, into this concurrent class. A static
 initializer block starts with the word static. I'll assign masterList to the results of a stream pipeline. This should
 look familiar, I'm generating an infinite stream of persons. I don't want duplicate persons, so I'll use distinct. I'll
 limit it to 2500 distinct persons. Remember each customer is unique by the combination of name and age. This should severely
 limit the number of new visitors we encounter. Finally, I can use the collect terminal operation, that takes a supplier
 first, so that's a method reference for a new CopyOnWriteArrayList. Because I already specified the type in this collection,
 in the declaration, I don't have to specify it here, because it can be inferred. The next argument is the accumulator,
 so a method reference just to add an element to this array list. The last is a combiner, so addAll goes there. That should
 hopefully limit the number of new visitors. Next, I'll set up the Consumer Runnable.
 **/
//End-Part-1

    private static final ArrayBlockingQueue<Person> newVisitors = new ArrayBlockingQueue<>(5);

    public static void main(String[] args) {

        Runnable producer = () -> {

            Person visitor = new Person();
            //System.out.println("Adding " + visitor);
            System.out.println("Queueing " + visitor);

//Part-5
/**
        First, I'll change the adding statement to queueing. I'll also just comment out the line that prints the entire
 queue, in the if statement.

        Ok, so looking at this code, I should get 5 visitors queued up, before any consumer threads get started. While
 the sixth visitor is waiting to be queued, in the offer method, for that 5 seconds, the consumer threads will start
 polling the queue, and this sixth visitor should be successfully added to the queue. This means the code won't be writing
 to the Draining Queue text file. Three consumer threads will work on the queue,at fixed intervals, starting up every 3
 seconds, after an initial delay of 6 seconds. This will give the single threaded producer the chance to add 3 more visitors
 to the queue. Ok, so let's run this and see what we get.

                 Queueing Quincy, Fred (47)
                 Queueing Petersen, Eve (72)
                 Queueing Richardson, Fred (43)
                 Queueing Petersen, Able (50)
                 Queueing Smith, Fred (29)
                 Queueing Smith, Fred (63)                      >>>> Pauses at the 6th visitor. The offer method has 5 secs timeout.
                 pool-2-thread-2 Polling queue 5
                 pool-2-thread-3 Polling queue 5                >>>> Consumer threads polling the queue.
                 pool-2-thread-1 Polling queue 5
                 pool-2-thread-1 Richardson, Fred (43)
                 pool-2-thread-2 Quincy, Fred (47)              >>>> Each will get a visitor at the head of the queue. Notice that each got a different visitor.
                 pool-2-thread-3 Petersen, Eve (72)
                 pool-2-thread-1 done 3
                 pool-2-thread-3 done 3                         >>>> After processing the queue size is 3
                 pool-2-thread-2 done 3
                 Queueing Smith, Eve (22)
                 Queueing Petersen, Fred (83)                   >>>> We'll see this continue.
                 pool-2-thread-1 Polling queue 5
                 pool-2-thread-3 Polling queue 4
                 pool-2-thread-2 Polling queue 4
                 pool-2-thread-2 Smith, Fred (63)
                 pool-2-thread-1 Petersen, Able (50)
                 pool-2-thread-3 Smith, Fred (29)
                 pool-2-thread-2 done 2
                 pool-2-thread-3 done 2
                 --> New Visitor gets Coupon!: Petersen, Able (50)      >>>>> 1 or 2 visitors added to the masterList, and getting a coupon.
                 pool-2-thread-1 done 2
                 Queueing OHara, Charlie (30)
                 pool-2-thread-1 Polling queue 3
                 pool-2-thread-2 Polling queue 3
                 pool-2-thread-1 Smith, Eve (22)
                 pool-2-thread-3 Polling queue 3
                 pool-2-thread-2 Petersen, Fred (83)
                 pool-2-thread-1 done 0
                 pool-2-thread-3 OHara, Charlie (30)
                 pool-2-thread-2 done 0
                 pool-2-thread-3 done 0

 You can see the visitors queuing up, every second. It pauses at the sixth visitor. Remember, this call, the call to the
 offer method, has a 5 second time out. So it'll wait up to 5 seconds to succeed. Meanwhile, you can see the consumer
 threads polling the queue. Each finds a full queue. Each will get a visitor at the head of the queue. Notice that each
 got a different visitor. This means the ArrayBlockingQueue is doing it's job, managing the head of the queue efficiently,
 in a multi-threaded environment. After processing, the queue size is 3, that's because the producer was able to add that
 sixth visitor while these threads were processing their element. So we'll see this continue. I should also see statements
 that 1 or 2 visitors added to the master list, and getting a coupon. One other thing, I want you to notice, is that the
 poll method doesn't block, if the queue is empty. It just returns a null value. I'll come back to this in a minute.
 First though, I'll run this again. Again, I want you to see that one or two visitors are added, out of a total of 9 or
 10 queued. Adding visitors to the master list is an infrequent occurrence, which is the reason I chose the CopyOnWriteArrayList.

                                                CopyOnWriteArrayList

        The name "CopyOnWrite" is important. Whenever this list is modified, by adding, updating, or removing elements,
 a new copy of the underlying array is created. The modification is performed on the new copy, allowing concurrent read
 operations to use the original unmodified array. This ensures that reader threads aren't blocked by writers. Since changes
 are made to a separate copy of the array, there aren't any synchronization issues between the reading and writing threads.
 This is ordinarily too costly, but may be more efficient than alternatives when traversal operations, vastly outnumber
 mutations.

        Although this example, with 9 or 10 reads, didn't vastly outnumber changes, meaning the 1 or 2 inserts, you can
 imagine that a master list could contain hundreds of thousands of visitors, while new visitors might be a small drop in
 the bucket. In this code, I used the poll method to get the visitor from the queue.

                                 Removing Single Element From the ArrayBlockingQueue

        The ArrayBlockingQueue has several different methods to get an element from the queue. Most of these will get the
 element at the head of the queue, or the first in.

                                          Block?                           Returns          Throws InterruptedException?    Removes element from queue
    peek()                                 No                         Array item or null                No                             No
    poll()                                 No                         Array item or null                No                            Yes
    poll(long timeout, TimeUnit unit)      Temporarily                Array item or null               Yes                            Yes
    remove()                               Yes, when Queue is empty   Array item                        No                            Yes
    remove(Object o)                       No                         boolean                           No                            Yes, if o was in the queue
    take()                                 Yes, when Queue is empty   Array item                       Yes                            Yes

 If the queue is empty, a method will return null or block as described on this table. I've included the peek method here,
 because it will return an element from the queue, but it doesn't actually remove it from the queue. This method could
 be used to check the queue, before attempting a blocking method. I showed you an example of the poll method, with no
 arguments. This method simply returns a null if the queue is empty, as I demonstrated in my code. The overloaded version
 of this method takes time out information, and will block for that duration. In addition to the poll method, there's also
 the remove method. Calling this method with no arguments, will return an element if there's one on the queue, or it will
 block indefinitely, until an element is added to the queue. This method doesn't throw an interrupted exception, so you
 can call it without a try catch block. The second version of the remove method is significantly different. It lets you
 define which element you want to remove from the queue, and doesn't return the element, instead it returns a boolean
 value. Finally, there's the take method, which like the no args remove method, blocks if the queue is empty. This one
 though, throws an interrupted exception. So that's a lot of options. I want to get back to my code, and try a couple of
 these other methods.
 **/
//End-Part-5

            boolean queued = false;
            try {
                queued = newVisitors.offer(visitor, 5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                System.out.println("Interrupted Exception!");
            }
            if (queued) {
//                System.out.println(newVisitors);
            } else {
                System.out.println("Queue is Full, cannot add " + visitor);
                System.out.println("Draining Queue and writing data to file");
                List<Person> tempList = new ArrayList<>();
                newVisitors.drainTo(tempList);
                List<String> lines = new ArrayList<>();
                tempList.forEach((customer) -> lines.add(customer.toString()));
                lines.add(visitor.toString());

                try {
                    String pathWay = "./src/CourseCodes/NewSections/Section_19_Concurrency/Course22_ArrayBlockingQueueConsumerTasks/";
                    Files.write(Path.of(pathWay + "DrainedQueue.txt"), lines, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };

//Part-6
/**
        First, I'll change my poll method to include a time out. This will make my code more efficient, forcing a thread
 to hang out and wait, rather than disposing of a thread and creating a new one, when there's no work. Getting back to
 the consumer Runnable code, I'll add the time out arguments, to the poll method. I'll make this 5 seconds, so a thread
 will wait up to 5 seconds for a queued element to be added. This code won't compile, because this method throws the
 checked exception, InterruptedException. I'll use intelliJ to surround it with a try catch. I'll change the rate the
 producer adds a visitor, from 1 second to say 3 seconds, so the queue will be empty more often.

                     producerExecutor.scheduleWithFixedDelay(producer, 0, 1, TimeUnit.SECONDS);
                                                      to
                     producerExecutor.scheduleWithFixedDelay(producer, 0, 3, TimeUnit.SECONDS);

 I'll also change the fixed rate, so that the consumer threads run more often, every second, after the first delay of 6
 seconds.

                     consumerPool.scheduleAtFixedRate(consumer, 6, 3, TimeUnit.SECONDS);
                                                    to
                     consumerPool.scheduleAtFixedRate(consumer, 6, 1, TimeUnit.SECONDS);

 I'll run the code this way.

                     Queueing Quincy, Fred (79)
                     Queueing Richardson, Eve (30)
                     pool-2-thread-3 Polling queue 2
                     pool-2-thread-1 Polling queue 2
                     pool-2-thread-2 Polling queue 2
                     pool-2-thread-3 Quincy, Fred (79)
                     pool-2-thread-1 Richardson, Eve (30)
                     pool-2-thread-1 done 0
                     pool-2-thread-3 done 0
                     Queueing Quincy, Fred (51)                 >>>> 3 visitors queued (and added). And up to now 3 threads kicked off to do their work.
                     pool-2-thread-2 Quincy, Fred (51)
                     pool-2-thread-2 done 0
                     pool-2-thread-1 Polling queue 0
                     pool-2-thread-2 Polling queue 0
                     pool-2-thread-3 Polling queue 0            >>>> These 3 threads polling and getting an empty queue
                     Queueing Richardson, Charlie (89)          >>>> Producer adds a 4th visitor
                     pool-2-thread-1 Richardson, Charlie (89)
                     pool-2-thread-1 done 0
                     pool-2-thread-1 Polling queue 0
                     pool-2-thread-2 done 0
                     pool-2-thread-3 done 0
                     pool-2-thread-1 done 0

 Three visitors are added by the time our 3 consumer threads are kicked off, and those 3 threads can do their work. But
 then you can see the 3 threads polling again, and getting an empty queue, a queue with size zero. Then the producer adds
 a 4th visitor, who's immediately processed by one of the waiting 3 threads. At this point 10 seconds have passed, and
 the application ends. So this was a bit harder to see, but I hope you get the idea. You might have a good reason for
 waiting a little bit, to wait for elements to be added to the queue, instead of trying, then immediately failing. Next,
 I'll try this, using the take method.
 **/
//End-Part-6

        Runnable consumer = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " Polling queue " + newVisitors.size());

            //Person visitor = newVisitors.poll();
            Person visitor = null;
            try {
                //visitor = newVisitors.poll(5, TimeUnit.SECONDS);
                visitor = newVisitors.take();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

//Part-7
/**
        I'll replace the poll method, with it's two arguments, with just the take method. This causes an IntelliJ warning
 on the if statement, which I'm going to ignore, because visitor may not always be null, so I'm not sure why it's giving
 me that warning. I'll run this code.

                 Queueing Smith, Fred (49)
                 Queueing Norton, Eve (28)
                 pool-2-thread-1 Polling queue 2
                 pool-2-thread-3 Polling queue 2
                 pool-2-thread-2 Polling queue 2
                 pool-2-thread-1 Smith, Fred (49)
                 pool-2-thread-3 Norton, Eve (28)
                 pool-2-thread-1 done 0
                 pool-2-thread-3 done 0
                 Queueing Norton, Donna (34)
                 pool-2-thread-2 Norton, Donna (34)
                 pool-2-thread-2 done 0
                 pool-2-thread-1 Polling queue 0
                 pool-2-thread-3 Polling queue 0
                 pool-2-thread-2 Polling queue 0
                 Queueing Norton, Charlie (18)
                 pool-2-thread-1 Norton, Charlie (18)
                 --> New Visitor gets Coupon!: Norton, Charlie (18)
                 pool-2-thread-1 done 0
                 pool-2-thread-1 Polling queue 0

 This code runs as it did before, until the second round of consumer threads encounter an empty queue. Now, my application
 just hangs, and never shuts down, so I'll stop it manually. There might be very valid reasons for blocking indefinitely,
 and having these threads waiting here.

        In this lecture, I used this Consumer Producer example, to introduce you to two more thread-safe classes. The
 ArrayBlockingQueue gives you a lot of different options, to control and manage the shared data being processed. The
 CopyOnWriteArrayList is useful, when the shared data is mostly going to be accessed for reading. An expensive copy of
 the array list is made, if adds, or updates are made to the list, so make sure you know how your shared list will be
 accessed, before making a decision to use this type. The alternative to the CopyOnWriteArrayList, is the ConcurrentLinkedQueue,
 which I discussed on a previous table. Next, I want to review some of the other threading problems. We've seen one example
 of a deadlock. I'll review another example, then compare that to the live lock and starvation problems.
 **/
//End-Part-7

            if (visitor != null) {
                System.out.println(threadName + " " + visitor);
                if (!masterList.contains(visitor)) {
                    masterList.add(visitor);
                    System.out.println("--> New Visitor gets Coupon!: " + visitor);
                }
            }
            System.out.println(threadName + " done " + newVisitors.size());
        };

//Part-2
/**
        I'll insert that just after the Producer Runnable. I'll call it consumer. I'll get the thread name. I'll print
 that, and the text, polling queue, then output the queue size. The poll method returns the visitor at the head of the
 queue. If the visitor's not null, I'll print which thread is processing which visitor. I'll check if this visitor is in
 the masterList. A record includes an equals method, which checks equality, for each field. This means a visitor will match,
 if their first and last name and their age are all equal, to a person in the master list. If they aren't in the master
 list, I'll add them. I'll also print they're new, and should get a coupon. Finally, I'll print the newVisitors queue size
 again, after this process.
 **/
//End-Part-2

        ScheduledExecutorService producerExecutor = Executors.newSingleThreadScheduledExecutor();
        producerExecutor.scheduleWithFixedDelay(producer, 0, 3, TimeUnit.SECONDS);

        ScheduledExecutorService consumerPool = Executors.newScheduledThreadPool(3);
        for (int i = 0; i < 3; i++) {
            consumerPool.scheduleAtFixedRate(consumer, 6, 3, TimeUnit.SECONDS);
        }

//Part-3
/**
        Since I want several consumer threads running, I'll create a thread pool. I also want this to be scheduled. I'll
 call the variable, consumerPool. And I'll get a new Scheduled Thread Pool, with 3 threads at the start, since I want to
 schedule 3 tasks. I'll wrap this in a for loop. In this case, I'll use the scheduleAtFixedRate method. I'll pass the
 consumer task. I'll have the first tasks start after 6 seconds have elapsed, then I'll have them scheduled, at every 3
 seconds thereafter. Now, I need to shut the consumer executor down at some point. I'll add this code after the producerExecutor
 shutdown.
 **/
//End-Part-3

        while (true) {
            try {
                if (!producerExecutor.awaitTermination(10, TimeUnit.SECONDS))
                    break;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        producerExecutor.shutdown();

        while (true) {
            try {
                if (!consumerPool.awaitTermination(3, TimeUnit.SECONDS))
                    break;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        consumerPool.shutdown();

//Part-4
/**
        I'll copy the while loop and the shutdown statements, for that executor, and paste a copy at the end of this method.
 I'll change the producerExecutor to the consumerPool in the try block. I'll also change that time, from 20 seconds, in
 the await Termination to 3 seconds. Lastly I'll change producer Executor to consumerPool, in that last statement, shutting
 down the consumer pool. I'm also going to change the time out in the await termination call, for the producer executor,
 from 20 to 10. Before I run this, I'll delete the DrainedQueue text file. Since I'll have consumer threads running, I
 don't expect this code to execute, but I'll remove that file just in case. I'll change the print statements in the producer
 runnable code, so the output will be easier to understand.
 **/
//End-Part-4

    }
}
