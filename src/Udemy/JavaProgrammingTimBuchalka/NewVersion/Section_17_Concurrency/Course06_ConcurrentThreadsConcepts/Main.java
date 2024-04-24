package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_17_Concurrency.Course06_ConcurrentThreadsConcepts;

import java.util.concurrent.TimeUnit;

//Part-1
/**                                             Interference

        In the last lecture, I created three color threads, all tasked to run the countdown method, on a single instance
 of a StopWatch. Initially, when the loop variable, i, was a local variable, the threads had no conflict with each other./
 They all successfully counted down, from some initial starting point. When I used an instance field however, for the loop
 variable, my concurrent application fell apart. On this table, I'm showing you a conceptual picture of the countDown method,
 simplified to show the for loop block.

            _____________________________________________________________________________________________
            |countDown() method                                                                         |
            |     __________________________________________________________________________________    |
            |    |for loop block                                                                   |    |
            |    |                                                                                 |    |
            |    |     __________________________________________________________________________  |    |
            |    |     |                      for loop initialization                           |  |    |
            |    |     |    ______________         __________         ________________________  |  |    |
            |    |     |    |i=unitCount |         | i >= 0 |         |         i--          |  |  |    |
            |    |     |    |____________|         |________|         | _______     ________ |  |  |    |
            |    |     |                                              | | i = |     | i-1  | |  |  |    |
            |    |     |                                              | |_____|     |______| |  |  |    |
            |    |     |                                              |______________________|  |  |    |
            |    |     |________________________________________________________________________|  |    |
            |    |                                                                                 |    |
            |    |     __________________________________________________________________________  |    |
            |    |     |                             printf                                     |  |    |
            |    |     |    ______________     _________________     _______________________    |  |    |
            |    |     |    |Substitution|     | Concatenation |     |     Output to       |    |  |    |
            |    |     |    |____________|     |_______________|     |      console        |    |  |    |
            |    |     |                                             |_____________________|    |  |    |
            |    |     |________________________________________________________________________|  |    |
            |    |_________________________________________________________________________________|    |
            |___________________________________________________________________________________________|

 Specifically, I'm showing the for loops initialization statement, as well as the printf statement. Each box shown in this
 diagram is a unit of work, and you can see that units are logically nested, one in another. Only the smallest blocks might
 be atomic. I'll come back to this term in a minute. Think about each of these blocks as having a door, through which many
 threads can enter at any single time. There's no queue or line, so all threads can show up and enter at each of these
 boundaries, at the exact same time. A thread can be halfway through the work in any one of these blocks, when it's time
 slice expires, and it then has to pause or suspend execution, to allow other threads to wake up and execute. This means
 another active thread has an open door, to that same unit of work, where the paused thread is only partially done. When
 threads start and pause, in the same blocks as other threads, this is called interleaving.

                                                Interleaving

        As we saw in the last lecture, one result of interleaving is, that a shared resource's state may change, while a
 thread is paused in the middle of its work. In this case, one thread interferes with the work of another. When multiple
 threads run concurrently, their instructions can overlap or interleave in time. This is because, the execution of multiple
 threads happens in an arbitrary order. Remember, the order in which the threads execute their instructions, can't be
 guaranteed.

                                                Atomic Actions

        In programming, an atomic action is one, that effectively happens "all at once". An atomic action can't be stopped
 in the middle, It either happens completely, or it doesn't happen at all. Additionally, Side effects of an atomic action
 "are never visible until the action completes". Even the simplest operations, like some decrements and increments, aren't
 atomic, nor are all primitive assignments. For example, long and double assignments may not be atomic in all JVMs.

                    Increment Operand      Decrement Operand       Assignment of a long value
                            i++                    --i                i = 100_000_000_000L

 This table shows three examples of operations that may not be atomic. In other words, even simple statements can translate
 to multiple non-atomic steps by the virtual machine, which opens the door to interleaving threads. Now that we know what
 interleaving is, how can we prevent it? Well, first, be very careful about shared objects.
**/
//End-Part-1

public class Main {

    public static void main(String[] args) {

        StopWatch greenWatch = new StopWatch(TimeUnit.SECONDS);
        StopWatch purpleWatch = new StopWatch(TimeUnit.SECONDS);
        StopWatch redWatch = new StopWatch(TimeUnit.SECONDS);
        Thread green = new Thread(greenWatch::countDown, ThreadColor.ANSI_GREEN.name());
        Thread purple = new Thread(() -> purpleWatch.countDown(7), ThreadColor.ANSI_PURPLE.name());
        Thread red = new Thread(redWatch::countDown, ThreadColor.ANSI_RED.name());
        green.start();
        purple.start();
        red.start();

//Part-2
/**
        In the case of this code, it doesn't make any sense to have all threads sharing a single stop watch instance.
 Imagine if a running thread had a different time unit, for example, or could change the time unit while running. A lot
 of things can go wrong, if your threads are sharing objects not meant to be shared. To fix my code, I'm simply going to
 create three stop watch instances.

                 StopWatch stopWatch = new StopWatch(TimeUnit.SECONDS);
                 Thread green = new Thread(stopWatch::countDown, ThreadColor.ANSI_GREEN.name());
                 Thread purple = new Thread(() -> stopWatch.countDown(7), ThreadColor.ANSI_PURPLE.name());
                 Thread red = new Thread(stopWatch::countDown, ThreadColor.ANSI_RED.name());
                                            ↓
                                            to
                                            ↓
                 StopWatch greenWatch = new StopWatch(TimeUnit.SECONDS);
                 StopWatch purpleWatch = new StopWatch(TimeUnit.SECONDS);
                 StopWatch redWatch = new StopWatch(TimeUnit.SECONDS);
                 Thread green = new Thread(greenWatch::countDown, ThreadColor.ANSI_GREEN.name());
                 Thread purple = new Thread(() -> purpleWatch.countDown(7), ThreadColor.ANSI_PURPLE.name());
                 Thread red = new Thread(redWatch::countDown, ThreadColor.ANSI_RED.name());

 I'll first re-name stopWatch to greenWatch. I'll copy this statement and paste it twice, directly below this. I'll change
 the first copied variable, to be purpleWatch, and the next one, to be redWatch. Now, I've got compiler errors in each of
 my thread creation statements. I'll change stopwatch in each case, to be the colored coded watch, that matches the thread
 name. I'll run this.

                         ANSI_PURPLE Thread : i = 7             (in purple)
                         ANSI_GREEN Thread : i = 5              (in green)
                         ANSI_RED Thread : i = 5                (in red)
                         ANSI_PURPLE Thread : i = 6             (in purple)
                         ANSI_GREEN Thread : i = 4              (in green)
                         ANSI_RED Thread : i = 4                (in red)
                         ANSI_PURPLE Thread : i = 5             (in purple)
                         ANSI_RED Thread : i = 3                (in red)
                         ANSI_GREEN Thread : i = 3              (in green)
                         ANSI_PURPLE Thread : i = 4             (in purple)
                         ANSI_RED Thread : i = 2                (in red)
                         ANSI_GREEN Thread : i = 2              (in green)
                         ANSI_RED Thread : i = 1                (in red)
                         ANSI_GREEN Thread : i = 1              (in green)
                         ANSI_PURPLE Thread : i = 3             (in purple)
                         ANSI_PURPLE Thread : i = 2             (in purple)
                         ANSI_PURPLE Thread : i = 1             (in purple)

 This output looks a lot better. By giving each thread its own instance of StopWatch, I've almost insured that I won't
 have trouble, with interleaving threads. I say almost, because I could have made that field, i, in this case, static.
 That would result in the same behavior, as if we were sharing stop watch, because a static variable is shared by all
 its instances. What if we had designed the stop watch to be an immutable class, by following the rules we learned in an
 earlier section to do this? Certainly, the field i, if it were final, would never be used as a loop variable. Using
 immutable objects, when dealing with concurrent threads, simplifies techniques we might need to use otherwise, and eliminates
 some bad programming practices. An immutable object is said to be thread-safe for this reason. What does it mean to be
 thread-safe?

                                                Thread-Safe

        An object or a block of code is thread safe, if it isn't compromised, by the execution of concurrent threads. This
 means, the correctness and consistency of the program's output or its visible state, is unaffected by other threads.
 Atomic operations and immutable objects are examples of thread-safe code. In real life, there are shared resources,
 which have to be available to multiple concurrent threads, in near real time. For these, we'll need to control access
 to the resource, to protect it from the effects of interleaving threads. You can think of some of these techniques,
 as forcing threads to wait in line, until one thread has finished a full unit of work, and not just an atomic operation.
 Before we get into this, there's one more problem I want to demonstrate. It's similar to the problem we just saw, but
 it has nothing to do with atomic operations. For this, I'll create a new class called CachedData, with a main method,
 in the same package.
 **/
//End-Part-2
    }
}

class StopWatch {

    private TimeUnit timeUnit;
    private int i;

    public StopWatch(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    public void countDown() {
        countDown(5);
    }

    public void countDown(int unitCount) {

        String threadName = Thread.currentThread().getName();

        ThreadColor threadColor = ThreadColor.ANSI_RESET;
        try {
            threadColor = ThreadColor.valueOf(threadName);
        } catch (IllegalArgumentException ignore) {
            // User may pass a bad color name, Will just ignore this error.
        }
        String color = threadColor.color();
        for (i = unitCount; i > 0; i--) {
            try {
                timeUnit.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("%s%s Thread : i = %d%n", color, threadName, i);
        }
    }
}
