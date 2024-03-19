package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_19_Concurrency.Course15_ExecuterServiceChallenge;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//Part-1
/**
        In this challenge, I want you to modify, and enhance, the ShoeWarehouse Fulfillment code you created, from the
 previous challenge lecture. You should use ExecutorService classes, any of the three that I've covered, so use a SingleThreadExecutor,
 a FixedThreadPool, or a CachedThreadPool, or a combination of these. You'll use these, instead of managing individual
 threads, as you did in that previous challenge. This means, you'll Add an ExecutorService in the main method, to send
 orders to the warehouse. And you'll also want to Add an ExecutorService to the ShoeWarehouse class, which will fulfill
 orders. You should create, and fulfill, 15 shoe orders.
 **/
//End-Part-1

record Order(long orderId, String item, int qty) {
};

//Part-2
/**
        I've created a new project called ExecutorChallenge, and I've pulled in the two source files, from my own solution
 to the previous Synchronization Challenge. In this case I have the ShoeWarehouse class. I've also got a Main class, and
 an Order record, in the main.java source file. Let's quickly review what this code does.

        In the main method's code, we've set up a new instance of the ShoeWarehouse class. We've got a Producer Thread,
 that takes a runnable argument. This runnable generates 10 orders, and calls the receiveOrder method, on the warehouse
 instance for each iteration. It then creates 2 Consumer Threads, each of which processes 5 items, calling fulfill Order
 on the warehouse. To change this, first I'll remove the Consumer Thread code altogether from this method. It makes more
 sense to have the warehouse manage its own fulfillment threads. Next, I'll create a private method on my main class,
 that'll generate an order for me. This will make the code in the main method a bit cleaner, and easier to read.
 **/
//End-Part-2

public class Main {

    private static final Random random = new Random();

    public static void main(String[] args) {

        ShoeWarehouse warehouse = new ShoeWarehouse();

        /*   ~~Previous Challenge Code~~
        Thread producerThread = new Thread(() -> {
            for (int j = 0; j < 10; j++) {
                warehouse.receiveOrder(new CourseCodes.NewSections.Section_19_Concurrency.Course10_SynchronizationChallenge.Order(
                        random.nextLong(1000000, 9999999),
                        CourseCodes.NewSections.Section_19_Concurrency.Course10_SynchronizationChallenge.ShoeWarehouse.PRODUCT_LIST[random.nextInt(0, 5)],
                        random.nextInt(1, 4)));
            }
        });
        producerThread.start();

        for (int i = 0; i < 2; i++) {
            Thread consumerThread = new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    CourseCodes.NewSections.Section_19_Concurrency.Course10_SynchronizationChallenge.Order item = warehouse.fulfillOrder();
                }
            });
            consumerThread.start();*/

        ExecutorService orderingService = Executors.newCachedThreadPool();

        Callable<Order> orderingTask = () -> {

                Order newOrder = generateOrder();
                try {
                    Thread.sleep(random.nextInt(500, 5000));
                    warehouse.receiveOrder(newOrder);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return newOrder;
        };

//Part-4
/**
        I'm actually just going to get rid of all the producer thread code too, and start fresh in this method. For my
 producer side of things, I'm going to use a CachedThreadPool. I'll name this variable, orderingService, and get an instance
 of this, using one of the static factory methods on the Executors class. I could have used any of the three here. There's
 not really a right or wrong way, it just depends on what your objective is, and how you want to use your resources. Since
 this code just adds orders, it should be fast, and allow as many orders as possible to be submitted, as quickly as possible.
 I'll set up a variable next, which is a Callable, and will process an Order.

        I'll call it ordering Task, and set that up as a multi line lambda. I'll use my new method to generate an order,
 and assign that to a local variable, named newOrder. I'll set up a try block, because I want to have each task sleep for
 a random time, between half a second and 5 seconds, so my orders won't be received all at once. This is just here, to
 make watching the processes a little simpler. I'll call warehouse.receiveOrder, passing it the newOrder variable. I'll
 catch the exception. and throw a different type of exception. Finally, I'll return newOrder. Notice what I don't have
 here, and that's a for loop in the task. That's because I can use the executor service to fire off multiple tasks.
 **/
//End-Part-4

        /*List<Callable<Order>> tasks = Collections.nCopies(15, orderingTask);
        try {
            orderingService.invokeAll(tasks);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        orderingService.shutdown();

        try {
            orderingService.awaitTermination(6, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/

//Part-5
/**
        What I'll do next is, set up an Array of these tasks. I can do that using the nCopies method on Collections. First,
 I'll create a List. This is a collection of Callables. I'll call this variable tasks, and create it by calling Collection.nCopies.
 This creates a list of 15 ordering Tasks. I'll now call invokeAll on my ordering service, passing it my task list. I'll
 get an error on that line of code, because I need a try catch, so I'll use IntelliJ to help me do that. Right now, I
 don't really need to get any information back, when I submit an order. You could imagine maybe we might want a confirmation
 or something after submitting, but this is fine for now. I'll call orderingService.shutdown next. Then I'll wait for all
 the threads to terminate, but not longer than 6 seconds. And of course, I'll need a try catch around the awaitTermination
 method too. I could run this now, but it's only one side of the producer consumer equation. For this challenge, you were
 asked to include an executor service, on the warehouse class, so next, I'll switch over to the Shoe Warehouse class.
 **/
//End-Part-5

        //warehouse.shutDown();

//Part-8
/**
        This will shut down the warehouse, which shuts down that thread pool service. If I don't do this, my application
 code won't shut down. Ok, so now, if I run this.

                     pool-2-thread-13 Incoming: Order[orderId=7359678, item=Boots, qty=3]
                     pool-1-thread-1 Fulfilled: Order[orderId=7359678, item=Boots, qty=3]
                     pool-2-thread-12 Incoming: Order[orderId=2688187, item=Sandals, qty=3]
                                                    .....
                     pool-2-thread-11 Incoming: Order[orderId=5361352, item=Sandals, qty=1]
                     pool-1-thread-2 Fulfilled: Order[orderId=5361352, item=Sandals, qty=1]
                     pool-2-thread-6 Incoming: Order[orderId=6351202, item=Sandals, qty=1]
                     pool-1-thread-3 Fulfilled: Order[orderId=6351202, item=Sandals, qty=1]

 I should get 30 statements printed, 15 from the threads that are doing the ordering, and 15 statements from the threads
 fulfilling those orders. The Producer's pool is producing the Incoming statements. This might be pool 1 or pool 2, but
 either way, look at the thread names for that pool. You can see that these start at thread 1, and go up to thread 15.
 The Consumer's pool of threads are the ones that print Fulfilled. These threads only ever have 3 different names, 1 through
 3. This is because these threads are re-used for tasks. In this example, my runnable code tried to include a delay,
 between half a second and 5 seconds. I'm going to rewrite this a little bit. Instead of including the delay in the run
 method, or as part of the executing task, instead I'll include the delay, when submitting the task to the executor service,
 which is more realistic. I'll comment out the code that creates a list of 15 tasks, as well as the invoke all method.
 **/
//End-Part-8

        try {
            //Thread.sleep(random.nextInt(500, 2000));
            for (int j = 0; j < 15; j++) {
                Thread.sleep(random.nextInt(500, 2000));
                orderingService.submit(() -> warehouse.receiveOrder(generateOrder()));
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        orderingService.shutdown();
        try {
            orderingService.awaitTermination(6, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        warehouse.shutDown();

//Part-9
/**
        Instead, I'll set up a loop to submit tasks. I'll start with a try block. I'll use the sleep here, so that the
 code waits a bit, before submitting a bunch of tasks. I'll use a loop to submit each task separately. I'll use a one-liner
 lambda here, rather than use the Runnable variable. I created earlier, since I don't want anything in this process to
 include an additional sleep. I'll run this.

                     pool-2-thread-1 Incoming: Order[orderId=4191033, item=High Tops, qty=3]
                     pool-2-thread-15 Incoming: Order[orderId=2428957, item=High Tops, qty=3]
                     pool-2-thread-14 Incoming: Order[orderId=5108290, item=Sandals, qty=1]
                     pool-2-thread-5 Incoming: Order[orderId=1605391, item=High Tops, qty=2]
                                                    ......
                     pool-1-thread-1 Fulfilled: Order[orderId=8786264, item=High Tops, qty=3]
                     pool-1-thread-2 Fulfilled: Order[orderId=2752400, item=Running Shoes, qty=3]
                     pool-1-thread-2 Fulfilled: Order[orderId=7208486, item=Boots, qty=3]
                     pool-1-thread-3 Fulfilled: Order[orderId=6520161, item=Slippers, qty=1]
                     pool-1-thread-1 Fulfilled: Order[orderId=8932056, item=High Tops, qty=2]

 This output will look a lot like the previous output, with 15 individually named threads in the Producer's pool. All I'm
 really doing here is, waiting up to 2 seconds, then firing off 15 tasks all at once, so the cached pool again increases
 to the same size as the tasks. But look what happens, if I move that sleep statement, to be inside the for loop. Running
 this code now.

                     pool-2-thread-1 Incoming: Order[orderId=8323168, item=Slippers, qty=2]
                     pool-1-thread-1 Fulfilled: Order[orderId=8323168, item=Slippers, qty=2]
                     pool-2-thread-1 Incoming: Order[orderId=6799858, item=High Tops, qty=3]
                     pool-1-thread-2 Fulfilled: Order[orderId=6799858, item=High Tops, qty=3]
                     pool-2-thread-1 Incoming: Order[orderId=9429608, item=Sandals, qty=3]
                     pool-1-thread-3 Fulfilled: Order[orderId=9429608, item=Sandals, qty=3]
                     pool-2-thread-1 Incoming: Order[orderId=7190914, item=Boots, qty=3]
                                                    .......

 You can see there's a delay between each order being received. More importantly though, check out the Incoming Pool's
 unique thread names. I'm no longer seeing 15 unique threads in this case. I might just see just one or two. Remember,
 that a cached thread pool will also reuse threads, like a fixed thread pool does. In this case, tasks are completing
 before other tasks get queued up, and before the thread expires, within 60 seconds, so the thread can be reused. The
 thread manager on this service, determines the best time to create a new thread. Because we're not submitting a lot of
 tasks all at the same time, it's reusing a single thread or two, to handle the work. Ok, so that was the challenge.
 Remember, there's no right way or wrong way to set this up, and you might have tried a different executor, or a different
 approach. Choosing any of the executor services we've covered so far could have worked, as long as you submitted 15
 orders, and fulfilled all 15. The choice of submitting tasks here, instead of executing them, wasn't really because I
 wanted the data back. It's more common to use submit, because the submit method works for either a Callable or a Runnable
 statement. Ok, next, I want to cover scheduling tasks. You saw in this lecture, I tried to delay submitting each task
 by some random bit of time. But we can also use a schedule executor to do this in a more controlled fashion.
 **/
//End-Part-9
    }

    private static Order generateOrder() {
        return new Order(
                random.nextLong(1000000, 9999999),
                ShoeWarehouse.PRODUCT_LIST[random.nextInt(0, 5)],
                random.nextInt(1, 4));
    }

//Part-3
/**
        This method can be private and static, and returns an order. I'll start with a return statement next. Next, I
 really just want to cut out the code, from the main method, that creates a new order, omitting the last parentheses.
 I'll paste that after the return statement in my new method, and I'll add a semi colon at the end. Back in the main method,
 **/
//End-Part-3
}
