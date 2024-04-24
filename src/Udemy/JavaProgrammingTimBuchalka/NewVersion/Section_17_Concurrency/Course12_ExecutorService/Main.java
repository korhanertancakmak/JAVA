package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_17_Concurrency.Course12_ExecutorService;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

//Part-1
/**
        We've spent a lot of time up until this point, talking about concurrency problems, and how to deal with some of
 them. These are very important, and I'll be coming back to the subject later. But let's take a break from that study,
 to look at another set of classes in the java.util.concurrent package. These are the ExecutorService classes, and they
 exist to manage the creation and execution of threads. As you've seen, when using a Thread class, you have rudimentary
 control over that thread. You can interrupt a thread, and join it to another thread. You can name the thread, try to
 prioritize it, and start each manually, one at a time. You can also pass it an UncaughtExceptionHandler, to deal with
 exceptions that happen in a thread. Managing threads manually can be complex and error-prone. It can lead to complex
 issues like resource contention, thread creation overhead, and scalability challenges. For these reasons, you'll want
 to use an ExecutorService, even when working with a single thread. The ExecutorService type in Java is an interface.
 Java provides several implementations of this type which provide the following benefits;

    * These simplify thread management, by abstracting execution, to the level of tasks which need to be run.
    * They utilize resources more efficiently, through the use of thread pools, reducing the cost of creating new threads.
    * They provide efficient Scaling, by utilizing multiple processor cores.
    * They come with built-in synchronization, reducing the chances of concurrency-related errors.
    * They implement a graceful shutdown, preventing resource leaks.
    * Scheduled implementations exist to further help with management workflows.

 We'll start looking at the simplest implementation of this type, the SingleThreadedExecutor. Getting back to IntelliJ,
 I've already created a new project, and I've got my Main class open here. I've included the color enum from the Multithreading
 and Memory project, from earlier in this section. If you've got that handy, grab it from there and paste into this project
 as I have done. I'll start with this first, without using an ExecutorService. I'll start with a method on my Main class,
 which I'll call countDown.
 **/
//End-Part-1

class ColorThreadFactory implements ThreadFactory {

    private String threadName;

    public ColorThreadFactory(ThreadColor color) {
        this.threadName = color.name();
    }

    @Override
    public Thread newThread(Runnable r) {

        Thread thread = new Thread(r);
        thread.setName(threadName);
        return thread;
    }
}

//Part-8
/**
        I'm getting an error, because I need to implement the newThread method, so I'll generate an override for that now.
 Before I implement this method, I'll first add a field to this class, as well as a constructor. I'll call this field
 threadName. For the constructor, I'm going to have it take an enum value, so a Thread Color instance. I'll assign my
 thread name, to the enums name property. Now I need to replace return null with some custom code, so I'll do that next.
 I'll create an instance of a new Thread. I'll set its name to the thread name field, and then I'll return the thread.
 This is the factory method for creating a thread. Now that I have a factory class, I can pass it the call to get a new
 single thread executor.
 **/
//End-Part-8

public class Main {

    public static void main(String[] args) {

        /*Thread blue = new Thread(Main::countDown, ThreadColor.ANSI_BLUE.name());

        Thread yellow = new Thread(Main::countDown, ThreadColor.ANSI_YELLOW.name());

        Thread red = new Thread(Main::countDown, ThreadColor.ANSI_RED.name());*/

        /*blue.start();
        yellow.start();
        red.start();

        try {
            blue.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            yellow.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            red.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/

//Part-3
/**
        I'll use the primary colors this time, so blue, yellow, and red. And for each, I'll pass a method reference to my
 count down method, and pass each a thread name, the color of the text it should print. In the first case, this will be
 ansi blue. For the yellow thread, I'll call the same method, and pass ANSI Yellow there. And the red thread, will also
 call countdown. Next, I'll call start on each of these threads. It doesn't matter what order, but I'll just keep the
 order I declared them in. So blue, then yellow, and lastly red. Remember, even though I call start in this order, this
 doesn't mean they'll be executed in this order. Now, I'm going to call join on all three. Each of these will join to the
 main thread, as they finish. But again, what order that occurs in, is determined by the processes, executing the threads.
 Ok, we have an error now, and that's because the join method throws an interrupted exception. I'll get IntelliJ to add
 a try catch block to each thread. I'll run this, and I'm actually just going to run this a couple of times here.

 YELLOW  20     YELLOW  17      RED  19         YELLOW  12      BLUE  14        RED  15         YELLOW  7       BLUE  9         RED  10         YELLOW  2       BLUE  4        BLUE  2     RED  2
 BLUE  20       BLUE  18        BLUE  16        RED  18         YELLOW  10      BLUE  12        RED  13         YELLOW  5       BLUE  7         RED  8          YELLOW  0      RED  4      RED  1
 YELLOW  19     RED  20         YELLOW  15      BLUE  15        RED  16         YELLOW  8       BLUE  10        RED  11         YELLOW  3       BLUE  5         RED  6         BLUE  1     RED  0
 YELLOW  18     BLUE  17        YELLOW  14      YELLOW  11      BLUE  13        RED  14         YELLOW  6       BLUE  8         RED  9          YELLOW  1       BLUE  3        RED  3
 BLUE  19       YELLOW  16      YELLOW  13      RED  17         YELLOW  9       BLUE  11        RED  12         YELLOW  4       BLUE  6         RED  7          RED  5         BLUE  0

 The output is sporadic, as you might expect. Sometimes it looks like all the numbers of one color are printed altogether,
 and sometimes you can see that it looks like it's dividing up the work for each thread, and you'll see the colors interspersed.
 But I can see all the colors counting down from 20. There's nothing new here, and the reason I'm setting this code up
 this way is, to give you an appreciation of the ExecutorService. If I wanted these to be called asynchronously, but only
 one asynchronous thread at a time, I would rearrange my starts and joins. I'll do that next, so
 **/
//End-Part-3

        /*blue.start();
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
        }*/

//Part-4
/**
        I'll cut the try catch that surrounds the blue.join call, and paste that after the blue.start method call. I'll
 do the same thing with the try catch around the yellow.join call, pasting that after yellow.start. If I run this code,

                 BLUE  20   YELLOW  20  RED  20
                 BLUE  19   YELLOW  19  RED  19
                 BLUE  18   YELLOW  18  RED  18
                 BLUE  17   YELLOW  17  RED  17
                 BLUE  16   YELLOW  16  RED  16
                 BLUE  15   YELLOW  15  RED  15
                 BLUE  14   YELLOW  14  RED  14
                 BLUE  13   YELLOW  13  RED  13
                 BLUE  12   YELLOW  12  RED  12
                 BLUE  11   YELLOW  11  RED  11
                 BLUE  10   YELLOW  10  RED  10
                 BLUE  9    YELLOW  9   RED  9
                 BLUE  8    YELLOW  8   RED  8
                 BLUE  7    YELLOW  7   RED  7
                 BLUE  6    YELLOW  6   RED  6
                 BLUE  5    YELLOW  5   RED  5
                 BLUE  4    YELLOW  4   RED  4
                 BLUE  3    YELLOW  3   RED  3
                 BLUE  2    YELLOW  2   RED  2
                 BLUE  1    YELLOW  1   RED  1
                 BLUE  0    YELLOW  0   RED  0

 you can see this structure is more of a workflow structure, where one process is running, allowing the main thread to
 continue doing something, or other threads to run, but the red and yellow threads have to wait. There are several ways
 to do this, using ExecutorService implementations.
 **/
//End-Part-4

        /*var blueExecutor = Executors.newSingleThreadExecutor();
        blueExecutor.execute(Main::countDown);*/

//Part-6
/**
        I'll set up a local variable with type inference, so var, blue Executor, equals. Java provides a class, called
 Executors, which contains static factory methods, to get a wide variety of different executor service implementations.
 All these methods have a new prefix, and in this case, I'll call new SingleThreadExecutor. Once I have an executor service,
 I can pass it a Runnable, so I'll pass it the lambda I used in my earlier code, Main countDown. Notice what's not in this
 code. There's barely a mention of a thread anywhere. Certainly, I'm not instantiating any threads, or starting them. This
 ExecutorService, and all executor service implementations, will create threads as needed, whose run methods implement
 the runnable code we pass to it. How it creates the threads and when, is one of the distinctions between each of the
 different kinds of executor services. Let's run this code and see what happens.

             pool-1-thread-1  20        pool-1-thread-1  9
             pool-1-thread-1  19        pool-1-thread-1  8
             pool-1-thread-1  18        pool-1-thread-1  7
             pool-1-thread-1  17        pool-1-thread-1  6
             pool-1-thread-1  16        pool-1-thread-1  5
             pool-1-thread-1  15        pool-1-thread-1  4
             pool-1-thread-1  14        pool-1-thread-1  3
             pool-1-thread-1  13        pool-1-thread-1  2
             pool-1-thread-1  12        pool-1-thread-1  1
             pool-1-thread-1  11        pool-1-thread-1  0
             pool-1-thread-1  10

 First, I do get a count down from 20 to 0, but it's not in color. That's because I didn't give my thread a thread name,
 so it's used the default color. Also notice, that my application appears to still be running. I'll shut that down manually
 now. An executor service must be shutdown, by calling a shutdown method. I'll add that, below the execute method.
 **/
//End-Part-6

        /*blueExecutor.shutdown();*/

//Part-7
/**
        If I run this again,

             pool-1-thread-1  20        pool-1-thread-1  9
             pool-1-thread-1  19        pool-1-thread-1  8
             pool-1-thread-1  18        pool-1-thread-1  7
             pool-1-thread-1  17        pool-1-thread-1  6
             pool-1-thread-1  16        pool-1-thread-1  5
             pool-1-thread-1  15        pool-1-thread-1  4
             pool-1-thread-1  14        pool-1-thread-1  3
             pool-1-thread-1  13        pool-1-thread-1  2
             pool-1-thread-1  12        pool-1-thread-1  1
             pool-1-thread-1  11        pool-1-thread-1  0
             pool-1-thread-1  10

 I can see my application ends and exits smoothly. I'll come back to the shutdown method in a little bit, but first let's
 talk about the thread name. This executor service is creating threads for me, and giving them default names. In this case
 I have one thread, and it's name is "pool 1", "thread 1", which is java's convention for naming threads in executor services.
 Normally, the thread name's not really important information about a running thread. In many circumstances, I wouldn't
 really care what the thread name is, especially if managing a whole bunch of them. For this scenario though, I do care
 about the thread name, and I do want to set it to the ANSI color. How can I go about passing a thread name to this code?
 Well, I can't, not the way this is written. But I can create a class, which implements something called a ThreadFactory
 interface. I can then pass my ThreadFactory instance, to an overloaded version of the method, Executors.newSingleThreadExecutor.
 By supplying my own thread factory class, I can override the standard way an executor creates a thread, and use custom
 functionality instead. In the Main.java file, I'll create a new class. I'll call this class, ColorThreadFactory, since
 each thread will be named by a color.
 **/
//End-Part-7

        /*var blueExecutor = Executors.newSingleThreadExecutor(new ColorThreadFactory(ThreadColor.ANSI_BLUE));
        blueExecutor.execute(Main::countDown);
        blueExecutor.shutdown();*/

//Part-9
/**
        Running this code,

                 BLUE  20       BLUE  10
                 BLUE  19       BLUE  9
                 BLUE  18       BLUE  8
                 BLUE  17       BLUE  7
                 BLUE  16       BLUE  6
                 BLUE  15       BLUE  5
                 BLUE  14       BLUE  4
                 BLUE  13       BLUE  3
                 BLUE  12       BLUE  2
                 BLUE  11       BLUE  1
                                BLUE  0

 I'll get the same results as before, but in blue now. Although this doesn't feel like it's simpler code, because of the
 ThreadFactory class, I'd guess that 90% of the time, you won't need a ThreadFactory. Still, it's good to know how to create
 and use one, and this code was a perfect opportunity to demonstrate that. Let's now kick off our other 2 tasks.
 **/
//End-Part-9

        /*var blueExecutor = Executors.newSingleThreadExecutor(new ColorThreadFactory(ThreadColor.ANSI_BLUE));
        blueExecutor.execute(Main::countDown);
        blueExecutor.shutdown();

        var yellowExecutor = Executors.newSingleThreadExecutor(new ColorThreadFactory(ThreadColor.ANSI_YELLOW));
        yellowExecutor.execute(Main::countDown);
        yellowExecutor.shutdown();

        var redExecutor = Executors.newSingleThreadExecutor(new ColorThreadFactory(ThreadColor.ANSI_RED));
        redExecutor.execute(Main::countDown);
        redExecutor.shutdown();*/

//Part-10
/**
        First, I'll copy the blue Executor code, and paste it twice below. Out of these two blocks of code, I'm going to
 create the yellow and red executors. I'll change blue executor to yellow executor in all three cases, and I'll change
 ansi blue to ansi yellow, in the first copied block of code. I'll also do the same thing for the red executor. I'll run
 this code.

 BLUE  20     BLUE  18      BLUE  16        BLUE  14        BLUE  12        BLUE  10        BLUE  8     BLUE  6     BLUE  4     BLUE  2     BLUE  0
 YELLOW  20   YELLOW  18    YELLOW  16      YELLOW  14      YELLOW  12      YELLOW  10      YELLOW  8   YELLOW  6   YELLOW  4   YELLOW  2   YELLOW  0
 RED  20      RED  18       RED  16         RED  14         RED  12         RED  10         RED  8      RED  6      RED  4      RED  2      RED  0
 BLUE  19     BLUE  17      BLUE  15        BLUE  13        BLUE  11        BLUE  9         BLUE  7     BLUE  5     BLUE  3     BLUE  1
 YELLOW  19   YELLOW  17    YELLOW  15      YELLOW  13      YELLOW  11      YELLOW  9       YELLOW  7   YELLOW  5   YELLOW  3   YELLOW  1
 RED  19      RED  17       RED  15         RED  13         RED  11         RED  9          RED  7      RED  5      RED  3      RED  1

 In fact, I'll run it a couple of times. This depends on your system, but for me, I'll see that my threads are running
 concurrently with each other. A SingleThreadedExecutorService can still be used to create a multi threaded environment,
 by executing multiple of them, as I demonstrated here. Although I called shutdown on the Blue thread, the shutdown methods
 wait for all the threads to finish, either with a successful completion, or an exception. In this case, there's only one
 thread to wait for, but while it's waiting to shutdown, the yellow and red threads can start. Ok, so that's one of the
 Executor Service types. If I wanted to create the workflow situation, which I implemented in the earlier code, I can do
 this with another method on the executor service. I'll add this after the blue.shutdown method.
 **/
//End-Part-10

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

//Part-11
/**
        This method is called await Termination. It returns a boolean, so I'll first want to create a local variable, to
 assign the result to. I'll name this boolean is done, and I'll assign it false. I'll then assign to it the result of
 calling await termination. I'm getting an error, and if I hover over that, I can see that await termination throws an
 Interrupted exception. I'll click on more actions, and I'll surround it with a try catch block. Next, I'll wrap the
 remaining code in an if block. if isDone is true, this block of code will be executed. I'll add a message that blue
 finished, and yellow is starting. I'll do the same thing for yellow executor. I'll call the await termination method on
 it, and I'll assign the result to isDone. I'm getting an error again, so I'll get IntelliJ to add the try catch block.
 For red executor, I'll wrap that in an if block as well. The message will be yellow finished, starting red. Once again,
 I'll call await termination on the executor, and then assign it to is done. I'll also get IntelliJ to wrap it in a try
 catch block. Finally, I'll print a message, that all processes completed. This will also be in an if block, and if isDone
 is true, I'll print the message. Running this again,

                 BLUE  20                                  YELLOW  20                                  RED  20
                 BLUE  19                                  YELLOW  19                                  RED  19
                 BLUE  18                                  YELLOW  18                                  RED  18
                 BLUE  17                                  YELLOW  17                                  RED  17
                 BLUE  16                                  YELLOW  16                                  RED  16
                 BLUE  15                                  YELLOW  15                                  RED  15
                 BLUE  14                                  YELLOW  14                                  RED  14
                 BLUE  13                                  YELLOW  13                                  RED  13
                 BLUE  12                                  YELLOW  12                                  RED  12
                 BLUE  11                                  YELLOW  11                                  RED  11
                 BLUE  10                                  YELLOW  10                                  RED  10
                 BLUE  9                                   YELLOW  9                                   RED  9
                 BLUE  8                                   YELLOW  8                                   RED  8
                 BLUE  7                                   YELLOW  7                                   RED  7
                 BLUE  6                                   YELLOW  6                                   RED  6
                 BLUE  5                                   YELLOW  5                                   RED  5
                 BLUE  4                                   YELLOW  4                                   RED  4
                 BLUE  3                                   YELLOW  3                                   RED  3
                 BLUE  2                                   YELLOW  2                                   RED  2
                 BLUE  1                                   YELLOW  1                                   RED  1
                 BLUE  0                                   YELLOW  0                                   RED  0
         Blue finished, starting Yellow         Yellow finished, starting Red                  All processes completed

 I can see I've got one asynchronous thread at a time running now. So I can use one executor service, that operates on a
 single thread or task in different ways. I can wire it up in ways similar to those I did, when manipulating the individual
 threads. The SingleThreadExecutor Service makes sense to use in the second case I've showed here. But if you really want
 these three threads to fire up asynchronously, all executing the same tasks, there are several other alternatives that
 probably make more sense. Next lecture, I'll introduce you to some additional options.
 **/
//End-Part-11

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

//Part-5
/**
        Let's start with the simplest implementation, the SingleThreadedExecutor. First, I'll rename my main method, to notmain.
 I want you to see these implementations, sort of side by side, so I'll create a new main method, in this same class.
 I'll type psvm, and then I can start my code.
 **/
//End-Part-5

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
            System.out.println(color + " " + threadName.replace("ANSI_", "") + "  " + i);
        }
    }

//Part-2
/**
        This method is going to be private static, and it won't return anything. I'll store the current thread's name in
 a variable called threadName. I'm going to use the Thread color enum here, and I'll assign it to a var named threadColor.
 I need a try catch block here. I'll assign the value of the current thread to thread color. I'll catch an IllegalArgumentException,
 if the user passes a bad color, and I'll just ignore that. I'll assign the color of the current thread to a String variable
 called color. I'm going to loop backwards from 20, and I'll print the color, as well as replacing the String ANSI, with
 an empty space. Now, I'll set up a series of threads, which will print a different color text for each thread. I'll set
 these up in the main method.
 **/
//End-Part-2
}
