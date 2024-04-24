package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_17_Concurrency.Course21_ThreadSafeListQueues;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//Part-1
/**
        In the last lecture, I used Map implementations to show you examples of both concurrent and synchronized versions,
 of the Map. The standard List implementations, LinkedList and ArrayList, as well as the standard Set implementations like
 TreeSet and HashSet, are also "NOT thread-safe". Each of these can be used with a synchronized wrapper, which you can get
 from the Collections helper class. You should be aware that it's available for these types as well, especially if you
 find yourself maintaining or altering some legacy code, in a multi-threaded application. The synchronized wrappers provide
 a thread-safe option for you, with less impact on the design, if you need to make existing code work concurrently. If
 you're starting with new code though, I'd recommend using concurrent collections.

        For lists, there are two concurrent collection choices, depending on the type of work (meaning how many reads and
 writes are executed against a list) which needs to be done in parallel. You'll want to

    * Use ConcurrentLinkedQueue when you'll have frequent insertions and removals, such as producer-consumer scenarios,
    or task scheduling.
    * Use CopyOnWriteArrayList when you have a read-heavy workload with infrequent modifications. This type of list is
    useful for scenarios like configuration management, or read-only views of data.

        For an array, you can use one of the concurrent list options above or you could

    * Use anArrayBlockingQueue. This is a fixed-size queue, that blocks under two circumstances. The first is if you try
 to poll or remove an element from an empty queue. The second is if you try to offer, or add an element to a full queue.
 This is designed as a First In First Out or FIFO queue.

        Let's look at the ArrayBlockingQueue class next. I've got the project open from the last lecture. I'll continue
 using this project, because I still want to use the code, that'll generate random persons. I'll switch away from parallel
 streams for this next code, and set up a producer and consumer example, which will use an ArrayBlockingQueue. I'll create
 a new class named VisitorsList in the same package, and set up a main method, using psvm.
**/
//End-Part-1

public class VisitorList {

    private static final ArrayBlockingQueue<Person> newVisitors = new ArrayBlockingQueue<>(5);

//Part-2
/**
        Let's imagine we work at a park, and we have a kiosk in the visitor's center. Visitors can enter their data, and
 have an electronic coupon credited to their record, if they aren't in the existing list. A visitor can use this coupon
 in the visitor center's store, or be applied to their next entry fee. In the main method, I'll first create a producer
 task, to add visitor records, to a temporary queue. The producer code will just add the visitor data, to an interim queue,
 to be processed by consumer code later. First I'll set up an ArrayBlockingQueue variable on this class. I'll make it
 private static and final. I'll call the variable new visitors. The ArrayBlockingQueue takes a capacity as its argument.
 I'm going to make this five to start with. This means I can only have 5 visitors in this queue, at a max. You use an
 ArrayBlockingQueue when you want to control the number of elements being processed in some fashion.
**/
//End-Part-2

    public static void main(String[] args) {

        /*Runnable producer = () -> {

            Person visitor = new Person();
            System.out.println("Adding " + visitor);
            boolean queued = false;
            queued = newVisitors.add(visitor);
            if (queued) {
                System.out.println(newVisitors);
            } else {
                System.out.println("Queue is Full, cannot add " + visitor);
            }
        };

        ScheduledExecutorService producerExecutor = Executors.newSingleThreadScheduledExecutor();
        producerExecutor.scheduleWithFixedDelay(producer, 0, 1, TimeUnit.SECONDS);

        while (true) {
            try {
                if (!producerExecutor.awaitTermination(10, TimeUnit.SECONDS))
                    break;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        producerExecutor.shutdown();*/

//Part-3
/**
        Next, I'll create a runnable, called producer. And I'll do this with a no args lambda. I'll create a new visitor,
 which is just a new person. If you were really working with visitors, you might create a Visitor record, with a country
 and state field, and maybe an email. But I'll just keep this simple, and use our person record. I'll print out that I'm
 adding a visitor here. I'll set up a boolean variable named queued, and initialize it to false. I'll add this visitor to
 my new Visitors field. The add method returns a boolean, whether the visitor was added successfully or not, so I'll assign
 that to my queued variable. If the visitor was added, meaning queued is true, I'll print the newVisitors list here, after
 a successful add. Otherwise, I'll print that the queue was full. To execute this code, I'll create a scheduled executor.
 I'll call it producerExecutor, and assign that a new single threaded; scheduled executor, that I get from the Executors
 class. I'll have this scheduler execute a task every second, starting immediately, with 0 second delay in other words,
 for the first task. At some point I want to stop, but I'll have it run for 10 seconds first, by using the await termination
 method with a time out. I'll start with a while loop. I need a try block. Because I'll call await termination, passing
 it 10 seconds, meaning, this will wait 10 seconds for the executor to complete, which of course it won't, so then it will
 break out of this loop after that. And of course, I need to catch the interrupted exception. I'll pass that along, as a
 runtime exception. Finally, I'll call shutdown on the producerExecutor. This lets any tasks in process complete smoothly,
 but won't let any scheduled ones start. I'll run this code here.

             Adding Smith, Bob (45)
             [Smith, Bob (45)]
             Adding Richardson, Fred (19)
             [Smith, Bob (45), Richardson, Fred (19)]
             Adding Quincy, Eve (38)
             [Smith, Bob (45), Richardson, Fred (19), Quincy, Eve (38)]
             Adding Richardson, Eve (61)
             [Smith, Bob (45), Richardson, Fred (19), Quincy, Eve (38), Richardson, Eve (61)]
             Adding Petersen, Donna (32)
             [Smith, Bob (45), Richardson, Fred (19), Quincy, Eve (38), Richardson, Eve (61), Petersen, Donna (32)]
             Adding Richardson, Eve (26)

 You can see that I'm adding visitors, each subsequent visitor gets added after a one second delay. And you can see the
 new Visitors queue growing. Right now there's no consumer threads to do anything with this information. Then, on the sixth
 visitor, my application seems to just hang here. I'll wait here a couple of seconds, and see what happens. And now you
 see, it finally quits, because of the timeout I have specified, on the awaitTermination code. This visitor never gets
 added to the queue, because the producer thread actually errored out, on that add method. When you're using a ArrayBlockingQueue,
 there are several different methods to choose from, to add an element. Each is slightly different.

                                    Adding an Element to the ArrayBlockingQueue

                                                Blocks?         Returns     Throws InterruptedException?        Adds To Queue
    add(E e)                                    No              boolean     No, throws IllegalStateException    Yes
                                                                            (Unchecked)
    offer(E e)                                  No              boolean     No                                  Yes
    offer(E e, long timeout, TimeUnit unit)     Temporarily     boolean     Yes                                 Yes
    put(E e)                                    Yes             void        Yes                                 Yes

 On this table, you can see four methods you can use, to add an element to the ArrayBlockingQueue. I've demonstrated the
 first, the add method. It's throwing an unchecked exception in a thread, which I'm not monitoring, with my current code.
 So the only information I get, is that the customer wasn't added, because the queue was full at the time. In addition
 to the add method, we could use one of two offer methods, which also add an element to the tail, of the queue. The first
 doesn't block, or throw an interrupted exception. It simply returns false, if the queue is full, and the element can't
 be added. We could additionally call the offer method specifying a time out. This method will block temporarily during
 that time out, then return false, if the queue is full, or at capacity. If you use this method, you have to catch or
 specify the Interrupted Exception. Finally, the put method, is a blocking method, meaning this method blocks indefinitely,
 if the queue is full. These methods give you plenty of options to choose from, depending on your business use case.
 Getting back to the code, I'm going to wrap the add method with a try catch.
 **/
//End-Part-3

        /*Runnable producer = () -> {

            Person visitor = new Person();
            System.out.println("Adding " + visitor);
            boolean queued = false;

            try {
                queued = newVisitors.add(visitor);
            } catch (IllegalStateException e) {
                System.out.println("Illegal State Exception!");
            }

            if (queued) {
                System.out.println(newVisitors);
            } else {
                System.out.println("Queue is Full, cannot add " + visitor);
            }
        };

        ScheduledExecutorService producerExecutor = Executors.newSingleThreadScheduledExecutor();
        producerExecutor.scheduleWithFixedDelay(producer, 0, 1, TimeUnit.SECONDS);

        while (true) {
            try {
                if (!producerExecutor.awaitTermination(10, TimeUnit.SECONDS))
                    break;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        producerExecutor.shutdown();*/

//Part-4
/**
        I'll insert the try statement before I call the method. The add method throws an IllegalStateException, so I'll
 catch that. And I'll print something to that effect. I'll rerun that.

                 Adding Richardson, Able (57)
                 [Richardson, Able (57)]
                 Adding Petersen, Bob (49)
                 [Richardson, Able (57), Petersen, Bob (49)]
                 Adding Smith, Charlie (84)
                 [Richardson, Able (57), Petersen, Bob (49), Smith, Charlie (84)]
                 Adding Petersen, Able (54)
                 [Richardson, Able (57), Petersen, Bob (49), Smith, Charlie (84), Petersen, Able (54)]
                 Adding Quincy, Fred (91)
                 [Richardson, Able (57), Petersen, Bob (49), Smith, Charlie (84), Petersen, Able (54), Quincy, Fred (91)]
                 Adding Petersen, Able (74)
                 Illegal State Exception!
                 Queue is Full, cannot add Petersen, Able (74)
                 Adding OHara, Eve (50)
                 Illegal State Exception!
                 Queue is Full, cannot add OHara, Eve (50)
                 Adding Petersen, Charlie (34)
                 Illegal State Exception!
                 Queue is Full, cannot add Petersen, Charlie (34)
                 Adding Smith, Bob (90)
                 Illegal State Exception!
                 Queue is Full, cannot add Smith, Bob (90)
                 Adding Petersen, Bob (96)
                 Illegal State Exception!
                 Queue is Full, cannot add Petersen, Bob (96)

 And now we at least have more information about what happened, that the queue is full. But this doesn't fix the problem.
 I'll keep getting this exception until something starts emptying the queue. Mean while, I'm not adding these visitors
 to the queue, and they wouldn't be getting their coupons. What happens if I swap out add, for the put method? I'll do
 that.
 **/
//End-Part-4

        /*Runnable producer = () -> {

            Person visitor = new Person();
            System.out.println("Adding " + visitor);
            boolean queued = false;

            try {
                newVisitors.put(visitor);
                queued = true;
            } catch (InterruptedException e) {
                System.out.println("Interrupted Exception!");
            }

            if (queued) {
                System.out.println(newVisitors);
            } else {
                System.out.println("Queue is Full, cannot add " + visitor);
            }
        };

        ScheduledExecutorService producerExecutor = Executors.newSingleThreadScheduledExecutor();
        producerExecutor.scheduleWithFixedDelay(producer, 0, 1, TimeUnit.SECONDS);

        while (true) {
            try {
                if (!producerExecutor.awaitTermination(10, TimeUnit.SECONDS))
                    break;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        producerExecutor.shutdown();*/

//Part-5
/**
        The put method, unlike all the others, doesn't return a boolean, so I won't be assigning it to the queued variable.
 I also have to change the exception I'm catching, from IllegalStateException to an InterruptedException Before I run this,
 I do want to set queued to true, after I call the put method. I'll run this again.

             Adding Richardson, Donna (36)
             [Richardson, Donna (36)]
             Adding OHara, Bob (78)
             [Richardson, Donna (36), OHara, Bob (78)]
             Adding Norton, Bob (63)
             [Richardson, Donna (36), OHara, Bob (78), Norton, Bob (63)]
             Adding OHara, Charlie (79)
             [Richardson, Donna (36), OHara, Bob (78), Norton, Bob (63), OHara, Charlie (79)]
             Adding Norton, Able (36)
             [Richardson, Donna (36), OHara, Bob (78), Norton, Bob (63), OHara, Charlie (79), Norton, Able (36)]
             Adding Richardson, Eve (58)

 So again, I'll get 5 visitors added fine, but this time the code is definitely blocked, or hanging. Without consumer code,
 or some code draining the queue, I'm stuck here, until I kill the app. I'll do that now. You probably don't want code
 that will block indefinitely here. There's still another option with this class, and that's an offer method with a time
 out.
 **/
//End-Part-5

        /*Runnable producer = () -> {

            Person visitor = new Person();
            System.out.println("Adding " + visitor);
            boolean queued = false;

            try {
                queued = newVisitors.offer(visitor, 5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                System.out.println("Interrupted Exception!");
            }

            if (queued) {
                System.out.println(newVisitors);
            } else {
                System.out.println("Queue is Full, cannot add " + visitor);
            }
        };

        ScheduledExecutorService producerExecutor = Executors.newSingleThreadScheduledExecutor();
        producerExecutor.scheduleWithFixedDelay(producer, 0, 1, TimeUnit.SECONDS);

        while (true) {
            try {
                if (!producerExecutor.awaitTermination(10, TimeUnit.SECONDS))
                    break;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        producerExecutor.shutdown();*/

//Part-6
/**
        I'll remove the two statements in my try block. I still need the try block though, because the offer method, with
 the timeout arguments, still throws an Interrupted Exception. I'll add the code to execute the offer method. There are
 two overloaded versions of this method. I'll use the one that lets me specify a time out. I'll wait here 5 seconds, then
 I'll call it a lost cause. I'll run my code again.

             Adding Petersen, Able (33)
             [Petersen, Able (33)]
             Adding Smith, Fred (78)
             [Petersen, Able (33), Smith, Fred (78)]
             Adding OHara, Charlie (88)
             [Petersen, Able (33), Smith, Fred (78), OHara, Charlie (88)]
             Adding Smith, Eve (75)
             [Petersen, Able (33), Smith, Fred (78), OHara, Charlie (88), Smith, Eve (75)]
             Adding OHara, Donna (92)
             [Petersen, Able (33), Smith, Fred (78), OHara, Charlie (88), Smith, Eve (75), OHara, Donna (92)]
             Adding OHara, Eve (47)
             Queue is Full, cannot add OHara, Eve (47)

 Now, when I get to the sixth visitor, the code sits here for 5 seconds. Then I get the message from the producer thread,
 that the queue is full, and that the visitor wasn't added. If this were real code, I'd probably want to send off some
 alarm bells some how, letting the outside world know there's a problem here. Or I might try to recover from this situation,
 by having the producer thread kick off some consumer threads maybe. At least for now, this code won't hang or block,
 while trying to add a visitor to the queue. For our use case, this just means some visitors won't get coupons. This may
 or may not be the way you want to handle this. Actually, let's do something here. I'll log all the visitors in the queue
 to a file, as well as the visitor waiting to be added to the queue. There's a method on the ArrayBlockingQueue, that
 let's you drain the queue to another collection, so let me show you this.
 **/
//End-Part-6

        Runnable producer = () -> {

            Person visitor = new Person();
            System.out.println("Adding " + visitor);
            boolean queued = false;
            try {
                queued = newVisitors.offer(visitor, 5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                System.out.println("Interrupted Exception!");
            }
            if (queued) {
                System.out.println(newVisitors);
            } else {
                System.out.println("Queue is Full, cannot add " + visitor);
                System.out.println("Draining Queue and writing data to file");
                List<Person> tempList = new ArrayList<>();
                newVisitors.drainTo(tempList);
                List<String> lines = new ArrayList<>();
                tempList.forEach((customer) -> lines.add(customer.toString()));
                lines.add(visitor.toString());

                try {
                    String pathWay = "./src/CourseCodes/NewSections/Section_19_Concurrency/Course21_ThreadSafeListQueues/";
                    Files.write(Path.of(pathWay + "DrainedQueue.txt"), lines, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        ScheduledExecutorService producerExecutor = Executors.newSingleThreadScheduledExecutor();
        producerExecutor.scheduleWithFixedDelay(producer, 0, 1, TimeUnit.SECONDS);

        while (true) {
            try {
                if (!producerExecutor.awaitTermination(20, TimeUnit.SECONDS))
                    break;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        producerExecutor.shutdown();

//Part-7
/**
        I'll use another println in the if-else statement that this code is draining the queue and writing data to file.
 I'll set up a temporary list, an array list of Persons. I'll call the "drainTo" method, on the newVisitors queue, passing
 it the temp list. This will populate the tempList with all the visitors in the queue, as well as empty it. I'll create
 an array List of strings next. I'll populate that from the tempList elements. I'll also add the current visitor's data,
 to this strings list. I'll be writing to a file, which will be a good quick review. I'll need a try block first. Using
 Files.write, I'll pass a path for a file that doesn't yet exist, which I'll call DrainedQueue.txt, and I'll pass what
 I want printed, so the visitor data strings. I want the file to be created if it doesn't exist, and I want to append
 data if it does, so I'll pass those options, using the enum Standard Open Option's values, CREATE, and APPEND. I'll
 catch the IO Exception and simply re throw it as a runtime exception. Before I run this, I'll change my timeout, in the
 awaitTermination code, to 20 seconds. Ok, so that's a rough attempt at trying to handle the situation, while the consumer
 threads are missing in action. And of course, I haven't got any consumer threads. But you can imagine there could be
 situations, where something like this could happen, if they did exist. I'll run this.

             Adding Petersen, Charlie (36)
             [Petersen, Charlie (36)]
             Adding OHara, Donna (22)
             [Petersen, Charlie (36), OHara, Donna (22)]
             Adding Norton, Able (80)
             [Petersen, Charlie (36), OHara, Donna (22), Norton, Able (80)]
             Adding Richardson, Charlie (68)
             [Petersen, Charlie (36), OHara, Donna (22), Norton, Able (80), Richardson, Charlie (68)]
             Adding Petersen, Eve (65)
             [Petersen, Charlie (36), OHara, Donna (22), Norton, Able (80), Richardson, Charlie (68), Petersen, Eve (65)]
             Adding Petersen, Eve (49)
             Queue is Full, cannot add Petersen, Eve (49)
             Draining Queue and writing data to file
             Adding Smith, Donna (73)
             [Smith, Donna (73)]
             Adding Quincy, Charlie (35)
             [Smith, Donna (73), Quincy, Charlie (35)]
             Adding Norton, Donna (95)
             [Smith, Donna (73), Quincy, Charlie (35), Norton, Donna (95)]
             Adding Norton, Charlie (40)
             [Smith, Donna (73), Quincy, Charlie (35), Norton, Donna (95), Norton, Charlie (40)]
             Adding Norton, Charlie (96)
             [Smith, Donna (73), Quincy, Charlie (35), Norton, Donna (95), Norton, Charlie (40), Norton, Charlie (96)]
             Adding Petersen, Eve (45)
             Queue is Full, cannot add Petersen, Eve (45)
             Draining Queue and writing data to file

 So again, I get to the sixth visitor, and wait there for 5 seconds, but then I see the text that the queue is full, and
 that it's draining and being written to file. Then I see 5 more visitors are added successfully, and once again, on the
 12th one, the process repeats itself. Then my 20 second time out completes. If I open the project pane, I'll see I now
 have a file there, DrainedQueue.txt. I'll open that. There you can see all 12 of my visitors listed. This list could be
 consulted, if there's any question about a missing coupon, or a customer service representative could add the coupons
 manually. Ok, so let's add some consumer code, to consume this queue, and look at the other side, getting data out of
 this thread safe queue. I'll demonstrate that next.
 **/
//End-Part-7
    }
}
