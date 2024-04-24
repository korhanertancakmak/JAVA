package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_17_Concurrency.Course13_FixedThreadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

//Part-1
/**
        In the last lecture, I introduced you to one of the simpler ExecutorService implementations, the SingleThreadedExecutor
 Service. Even with this one service, and handling only a single thread, you could see that

    * The job of managing threads is simplified. In general, you get an executor service from one of the static methods
    on Executors class. You pass it a lambda expression that represents a task that you want to be run. You'll then call
    execute on that service, and afterwards, you have to call shutdown, otherwise the executor service will continue to
    stay up and running. Maybe you'll want to include a call to the awaitTermination method, if you have something pending
    or that needs to wait, on the completion of that task. In the last lecture, I also demonstrated additional functionality
    you can have, by creating your own ThreadFactory, rather than relying on the implicit one, built into these services.

    * These ExecutorService implementations let you stay focused on tasks that need to be run, rather than thread creation
    and management. This becomes even more apparent, when we use an ExecutorService that can handle multiple threads.

 Let's look at that next. I've got the project, and I'm looking at the main method. I'll again simply rename the main
 method, so I'll name it single main here, and then
 **/
//End-Part-1

class ColorThreadFactory implements ThreadFactory {

    private String threadName;

    private int colorValue = 1;

    public ColorThreadFactory(ThreadColor color) {
        this.threadName = color.name();
    }

    public ColorThreadFactory() {
    }

    @Override
    public Thread newThread(Runnable r) {

        Thread thread = new Thread(r);
        String name = threadName;
        if (name == null) {
            name = ThreadColor.values()[colorValue].name();
        }

        if (++colorValue > (ThreadColor.values().length - 1)) {
            colorValue = 1;
        }
        thread.setName(name);
        return thread;
    }

//Part-3
/**
        I can do this, with a private field on this class. This will represent the ordinal value, of the last color used
 for a thread name, and text color. I'll call this field colorValue, and assign it a 1, because I don't want to use the
 default color value at zero, which is the ANSI DEFAULT. I need a no args constructor, so I'll get IntelliJ to generate
 that. Next, I'm going to add some code to the newThread method. I'll set up a local variable for the revised thread
 name. Now, I only want to do this, if threadName is null, because I don't want to break my existing code, which uses the
 single argument constructor, to pass a specific threadName value. Name will only be null when the no args constructor on
 this class is used. I can use the values on the enum, in conjunction with the color value, to get a color, by it's ordinal,
 or numeric value. Here, I'll increment the color value, then check if that number is greater than the number of colors
 set up in the enum. If it is, I'll set the color value back to 1. This means, the code will cycle through all the non
 default ansi colors as new threads are created. Finally, I'll set the name of the thread, to the this new name variable,
 rather than the thread Name. Getting back to my new main method,
 **/
//End-Part-3
}

public class Main {

    public static void main(String[] args) {

        int count = 6;
        var multiExecutor = Executors.newFixedThreadPool(3, new ColorThreadFactory());

//Part-2
/**
        I'll create a new main method, using the psvm shortcut. I'll add a count variable which will represent the number
 of threads I want to run. This will be called count, and I'll initialize it to 3. I'll set up a multiExecutor variable,
 using type inference again, and I'll start with the Executors class, and then dot, so that IntelliJ will list the methods
 that are available, on this class. I'll scroll down a little bit. What I want you to see from this list is, all the methods
 that end in Pool. These all return executor service implementations, that manage a pool, or a group of threads. I'll
 scroll up a bit, and select the newFixedThreadPool method, in particular, I want the one that takes two arguments. I'll
 pass count as the first argument, and new ColorThreadFactory as the second argument. This time though, I won't actually
 pass a color to this constructor. I'll change my ColorThreadFactory, to be a bit more flexible, and to generate a color
 thread, without having to actually specify a color.
 **/
//End-Part-2

        for (int i = 0; i < count; i++) {
            multiExecutor.execute(Main::countDown);
        }
        multiExecutor.shutdown();

//Part-4
/**
        I can see that my code compiles now. I still need to execute the executor, passing it the task I want it to run,
 which again is the countdown method on this class. I'll iterate using a traditional for loop, and the count variable,
 to determine how many tasks to create. I'll call execute on the multiExecutor service, using the same method reference
 I had, for the Single Thread example, which I showed you previously. Finally, I can't forget to shutdown this executor.
 And that's it. Let's run this.

 WHITE  20      WHITE  14       WHITE  12       WHITE  10       BLACK  15       WHITE  6        WHITE  2        BLACK  10       BLUE  7         BLUE  4     BLACK  1
 WHITE  19      BLACK  20       BLACK  18       BLACK  16       WHITE  8        BLUE  12        WHITE  1        BLUE  9         BLACK  6        BLUE  3     BLUE  0
 WHITE  18      BLUE  20        BLUE  18        BLUE  16        BLUE  13        BLACK  12       WHITE  0        BLACK  9        BLUE  6         BLACK  3    BLACK  0
 WHITE  17      WHITE  13       WHITE  11       WHITE  9        BLACK  14       WHITE  5        BLUE  11        BLACK  8        BLACK  5        BLUE  2
 WHITE  16      BLACK  19       BLACK  17       BLUE  15        WHITE  7        WHITE  4        BLACK  11       BLUE  8         BLUE  5         BLACK  2
 WHITE  15      BLUE  19        BLUE  17        BLUE  14        BLACK  13       WHITE  3        BLUE  10        BLACK  7        BLACK  4        BLUE  1

 As usual, I'll just run it a couple of times. I get a variety of statements in colored text, in Black, Blue and white
 countdowns. With this implementation, I can very easily dial up the number of my threads. I'll set count to 6 here. I'll
 run it again.

 WHITE  20      PURPLE  19      BLUE  17        CYAN  16        WHITE  14       CYAN  10        PURPLE  12      BLUE  9         BLACK  9        BLACK  4        BLUE  6     GREEN  2    WHITE  1
 BLACK  20      CYAN  19        GREEN  17       CYAN  15        BLACK  14       BLACK  13       WHITE  11       BLACK  10       BLACK  8        GREEN  5        BLACK  3    GREEN  1    CYAN  0
 BLUE  20       WHITE  18       PURPLE  17      CYAN  14        BLUE  13        BLUE  12        CYAN  8         GREEN  7        BLACK  7        PURPLE  6       GREEN  4    CYAN  2     BLUE  2
 GREEN  20      BLACK  18       CYAN  17        CYAN  13        GREEN  14       GREEN  10       BLUE  10        PURPLE  9       BLACK  6        PURPLE  5       PURPLE  0   BLUE  4     WHITE  0
 PURPLE  20     BLUE  18        WHITE  16       WHITE  15       GREEN  13       PURPLE  13      BLACK  11       PURPLE  8       BLACK  5        PURPLE  4       WHITE  4    BLACK  1    BLUE  1
 CYAN  20       GREEN  18       BLACK  16       BLACK  15       GREEN  12       WHITE  12       GREEN  8        WHITE  9        GREEN  6        PURPLE  3       CYAN  3     WHITE  2    BLUE  0
 WHITE  19      PURPLE  18      BLUE  16        BLUE  14        GREEN  11       CYAN  9         PURPLE  11      WHITE  8        PURPLE  7       PURPLE  2       BLUE  5     GREEN  0
 BLACK  19      CYAN  18        BLUE  15        GREEN  15       PURPLE  14      BLUE  11        PURPLE  10      WHITE  7        WHITE  6        PURPLE  1       BLACK  2    CYAN  1
 BLUE  19       WHITE  17       GREEN  16       PURPLE  15      WHITE  13       BLACK  12       WHITE  10       CYAN  6         CYAN  5         WHITE  5        GREEN  3    BLUE  3
 GREEN  19      BLACK  17       PURPLE  16      CYAN  12        CYAN  11        GREEN  9        CYAN  7         BLUE  8         BLUE  7         CYAN  4         WHITE  3    BLACK  0

 That adds the green, purple, and cyan threads to the mix. I think you'll agree now, that using an executor service in
 this case was much easier than the alternative. Without this service, I'd have to manually instantiate three new thread
 variables, start each, and write some code, to have the main thread wait appropriately, if needed. This code is cleaner,
 easier to read, and scalable, meaning that by changing one number, in one statement, I can quickly be using a lot more
 threads to run tasks. Now, what happens if I pass three as the count to the Fixed Pool, but leave the rest of the code
 as is?

                 int count = 6;
                 var multiExecutor = Executors.newFixedThreadPool(count, new ColorThreadFactory());
                                                        to
                 int count = 6;
                 var multiExecutor = Executors.newFixedThreadPool(3, new ColorThreadFactory());

 I'll leave the variable count set to 6, and instead of passing count to the newFixedThreadPool method, I'll pass the number
 3 there. I'll run this code again.

         BLACK  20      BLACK  15       BLUE  10        WHITE  19       BLUE  17        WHITE  6        WHITE  0
         WHITE  20      WHITE  10       BLUE  9         WHITE  18       WHITE  12       BLACK  8        BLUE  2
         BLUE  20       WHITE  9        WHITE  3        WHITE  17       BLACK  14       BLACK  7        BLACK  1
         BLACK  19      BLUE  15        BLACK  7        BLUE  2         BLUE  16        BLUE  9         BLUE  1
         WHITE  19      BLACK  14       BLUE  8         BLACK  0        BLUE  15        BLUE  8         BLACK  0
         BLUE  19       BLACK  13       BLUE  7         WHITE  16       WHITE  11       WHITE  5        BLUE  0
         BLACK  18      WHITE  8        WHITE  2        WHITE  15       BLACK  13       BLACK  6
         WHITE  18      BLUE  14        BLACK  6        BLUE  1         BLUE  14        BLUE  7
         BLUE  18       BLACK  12       BLUE  6         BLACK  20       WHITE  10       WHITE  4
         BLACK  17      WHITE  7        BLACK  5        BLACK  19       BLACK  12       BLACK  5
         WHITE  17      BLACK  11       WHITE  1        BLACK  18       BLUE  13        BLUE  6
         BLUE  17       BLUE  13        BLACK  4        BLACK  17       WHITE  9        WHITE  3
         BLACK  16      WHITE  6        WHITE  0        WHITE  14       BLACK  11       BLACK  4
         WHITE  16      BLACK  10       BLACK  3        BLUE  0         BLUE  12        BLUE  5
         WHITE  15      BLUE  12        BLUE  5         BLACK  16       WHITE  8        WHITE  2
         WHITE  14      WHITE  5        BLACK  2        BLUE  20        BLACK  10       BLACK  3
         WHITE  13      BLACK  9        WHITE  20       WHITE  13       BLUE  11        BLUE  4
         WHITE  12      BLUE  11        BLUE  4         BLACK  15       WHITE  7        BLUE  3
         WHITE  11      WHITE  4        BLUE  3         BLUE  19        BLACK  9        WHITE  1
         BLUE  16       BLACK  8        BLACK  1        BLUE  18        BLUE  10        BLACK  2

 In this case, I'm only seeing 3 colors in this output. At first glance, you might think that only 3 different tasks were
 run, and not 6. But let's take a closer look at the output. There actually appears to be two sets of black countdown
 statements, two sets of blue countdowns, and two of the white also. Did our code fail? No, our code didn't fail at all.
 This behavior is a feature of this class, because only three threads were created, in this scenario. Remember, It's the
 thread creation process that give each thread a unique color. 3 threads were created, and pooled. When 6 tasks were
 submitted, the 3 threads were used to execute the first 3 tasks, and then reused to execute the second 3 tasks. This is
 where the FixedThreadPool gets its name. It will only ever create, at a maximum, the number of threads we specify, regardless
 of the number of tasks submitted. Let's pause here a minute, and just talk about thread pools in general.

                                            Creating Threads is Expensive

        I think I've stated this before, but I'll say it again. Creating threads, destroying threads, and then creating
 them again can be expensive. A thread pool mitigates the cost, by keeping a set of threads around, in a pool, for current
 and future work. Threads, once they complete one task, can then be reassigned to another task, without the expense of
 destroying that thread and creating a new one.

                                            The Mechanics of a Thread Pool

        A thread pool consists of three components. There are

    * Worker Threads are available in a pool to execute tasks. They're pre-created and kept alive, throughout the lifetime
    of the application.
    * Submitted Tasks are placed in a First-In First-Out queue. Threads pop tasks from the queue, and execute them, so
    they're executed in the order they're submitted.
    * The Thread Pool Manager allocates tasks to threads, and ensures proper thread synchronization.

 Java has five variations of the Thread Pool. You've seen the first in operation, the FixedThreadPool. This maintains a
 fixed number of threads.

             Class                     Description                                              Executors method

        FixedThreadPool         Has a fixed number of threads.                                  newFixedThreadPool
        CachedThreadPool        Creates new threads as needed, so its a variable size pool.     newCachedThreadPool
        ScheduledThreadPool     Can schedule tasks to run at a specific time or repeatedly      newScheduledThreadPool
                                at regular intervals.
        WorkStealingPool        Uses a work-stealing algorithm to distribute tasks among        newWorkStealingPool
                                the threads in the pool.
        ForkJoinPool            Specialized WorkStealingPool for executing ForkJoinTasks.       n/a

 Threads are reused, when the number of tasks exceed the number of threads. The cached thread pool on the other hand will
 grow or shrink in size, as the number of tasks changes. The size is managed by the thread pool manager. It's not a one
 to one match, to the number of submitted tasks, but you do have to proceed with caution. This kind of pool may create
 a large number of threads, leading to resource problems. The scheduled thread pool is a special type of cached thread
 pool, with mechanisms for providing a schedule for any submitted tasks. The work stealing pool breaks tasks into subtasks,
 and executes the subtasks in parallel, in a divide and conquer way. The Fork join pool is a type of work stealing pool,
 that provides the means for describing how to divide and conquer a single large task. It does this by allowing us to
 control how to fork a task into parallel sub-tasks, and then how to join the sub-tasks back. In the next few lectures,
 I'll be demonstrating some of these other thread pool executor services, in conjunction with some other methods I haven't
 yet covered, available on these services.
 **/
//End-Part-4
    }

    public static void singlemain(String[] args) {

        var blueExecutor = Executors.newSingleThreadExecutor(new ColorThreadFactory(ThreadColor.ANSI_BLUE));
        blueExecutor.execute(Main::countDown);
        blueExecutor.shutdown();

        boolean isDone = false;
        try {
            isDone = blueExecutor.awaitTermination(500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (isDone) {
            System.out.println("Blue finished, starting Yellow");
            var yellowExecutor = Executors.newSingleThreadExecutor(new ColorThreadFactory(ThreadColor.ANSI_YELLOW));
            yellowExecutor.execute(Main::countDown);
            yellowExecutor.shutdown();

            try {
                isDone = yellowExecutor.awaitTermination(500, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (isDone) {
                System.out.println("Yellow finished, starting Red");
                var redExecutor = Executors.newSingleThreadExecutor(new ColorThreadFactory(ThreadColor.ANSI_RED));
                redExecutor.execute(Main::countDown);
                redExecutor.shutdown();
                try {
                    isDone = redExecutor.awaitTermination(500, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (isDone) {
                    System.out.println("All processes completed");
                }
            }
        }
    }

    public static void notmain(String[] args) {

        Thread blue = new Thread(Main::countDown, ThreadColor.ANSI_BLUE.name());

        Thread yellow = new Thread(Main::countDown, ThreadColor.ANSI_YELLOW.name());

        Thread red = new Thread(Main::countDown, ThreadColor.ANSI_RED.name());

        blue.start();

        try {
            blue.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        yellow.start();

        try {
            yellow.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        red.start();

        try {
            red.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void countDown() {

        String threadName = Thread.currentThread().getName();
        var threadColor = ThreadColor.ANSI_RESET;
        try {
            threadColor = ThreadColor.valueOf(threadName.toUpperCase());
        } catch (IllegalArgumentException ignore) {
            // User may pass a bad color name, Will just ignore this error.
        }

        String color = threadColor.color();
        for (int i = 20; i >= 0; i--) {
            System.out.println(color + " " +
                    threadName.replace("ANSI_", "") + "  " + i);
        }
    }
}
