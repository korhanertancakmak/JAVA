package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_19_Concurrency.Course05_MultithreadingAndMemory;

import java.util.concurrent.TimeUnit;

//Part-1
/**
        In the last couple of lectures, you learned how to communicate with concurrently running threads. In this lecture,
 I want to visit, in more depth, the types of memory that concurrent threads have access to.

                                                    Process
                Thread One ----------------------------↓---------------------------------Thread Two
                    ↓                                  ↓                                      ↓
          Only      ↓  can access                      ↓                            Only      ↓  can access
        ThreadOne   ↓  its stack                       ↓                          ThreadTwo   ↓  its stack
                    ↓                                  ↓                                      ↓
                    ↓                                  ↓                                      ↓
               Stack Memory                        HEAP(SHARED)                          Stack Memory
             Method arguments                        Objects                           Method arguments
              Local variable                                                            Local variable

 In the introduction, I told you that Each thread has its own stack for local variables and method calls. One thread
 doesn't have access to another thread's stack. You can think of the stacks as silos. Every concurrent thread additionally
 has access to the process memory, or the heap. This is where objects and their data reside. This shared memory space
 allows all threads, to read and modify the same objects. When one thread changes an object on the heap, these changes
 are visible to other threads. This has some inherent problems associated with it, which is what I want to discuss in
 this lecture.
**/
//End-Part-1

public class Main {

    public static void main(String[] args) {

        StopWatch stopWatch = new StopWatch(TimeUnit.SECONDS);
        Thread green = new Thread(stopWatch::countDown, ThreadColor.ANSI_GREEN.name());
        green.start();

//Part-5
/**
        I'll create and set one thread in motion. First, I'll create a new instance of stop Watch, which will countdown
 in seconds. Next, I'll create a thread, called green, using new Thread. My runnable will call the countdown method that
 has no arguments, on stopwatch, so I can pass a method reference for this. The second parameter is thread name, and I'll
 use my enum, and the ANSI GREEN value's name, to name my thread. Finally, I'll start my thread running concurrently. If
 I run this code,

                 ANSI_GREEN Thread : i = 5
                 ANSI_GREEN Thread : i = 4
                 ANSI_GREEN Thread : i = 3
                 ANSI_GREEN Thread : i = 2
                 ANSI_GREEN Thread : i = 1

 I'll see a message printed to the console, every second, for 5 iterations. Hopefully, you'll see that the text is printed
 in green, in your IntelliJ console. This doesn't work for all operating systems, and IDEs, but should work for IntelliJ,
 and Unix operating systems, as well as windows. Ok, so that was a bit of set up. Let's fire off a few more threads.
 **/
//End-Part-5

        Thread purple = new Thread(() -> stopWatch.countDown(7), ThreadColor.ANSI_PURPLE.name());
        Thread red = new Thread(stopWatch::countDown, ThreadColor.ANSI_RED.name());
        purple.start();
        red.start();

//Part-6
/**
        I'll set up a purple thread, starting out the same way. Instead of a method reference, this time I want to use
 a lambda, to set the unit count to 7 for this thread, meaning this thread will be a stop watch for 7 seconds. I'll make
 this thread's name be ANSI PURPLE. For the third thread, I'll copy the green thread's declaration, and paste that under
 purple. I'll change the variable name to red, and the Thread color to ANSI RED. Finally, I'll call start on these two
 new threads. purple.start, then red.start. Ok, so let's run this.

                         ANSI_GREEN Thread : i = 5              (in green)
                         ANSI_RED Thread : i = 5                (in red)
                         ANSI_PURPLE Thread : i = 7             (in purple)
                         ANSI_RED Thread : i = 4                (in red)
                         ANSI_GREEN Thread : i = 4              (in green)
                         ANSI_PURPLE Thread : i = 6             (in purple)
                         ANSI_GREEN Thread : i = 3              (in green)
                         ANSI_PURPLE Thread : i = 5             (in purple)
                         ANSI_RED Thread : i = 3                (in red)
                         ANSI_GREEN Thread : i = 2              (in green)
                         ANSI_PURPLE Thread : i = 4             (in purple)
                         ANSI_RED Thread : i = 2                (in red)
                         ANSI_GREEN Thread : i = 1              (in green)
                         ANSI_PURPLE Thread : i = 3             (in purple)
                         ANSI_RED Thread : i = 1                (in red)
                         ANSI_PURPLE Thread : i = 2             (in purple)
                         ANSI_PURPLE Thread : i = 1             (in purple)

 And hopefully you can see, that each stop watch thread is running, counting down from the method argument that it was
 called with. Purple was called with 7 seconds, so it's counting down from 7, 1 second at a time. In each of these threads,
 the unitCount is a method argument, and therefore, it's stored on each thread's stack. Each thread has it's own version
 of unitCount for example. What would happen though, if I made the i variable, in my for loop, not a local variable, but
 a field on my StopWatch class?
 **/
//End-Part-6
    }
}

class StopWatch {

    private TimeUnit timeUnit;

    private int i;

//Part-7
/**
        I'll add a private int, i, variable, on Stop watch. And I'll change the for loop, in the countdown method. Would
 you ever do this? Probably not, but play along with me, for a minute or two. I'll run my code again.

                 ANSI_GREEN Thread : i = 5
                 ANSI_RED Thread : i = 5
                 ANSI_PURPLE Thread : i = 5
                 ANSI_GREEN Thread : i = 2
                 ANSI_PURPLE Thread : i = 2
                 ANSI_RED Thread : i = 2
                 ANSI_GREEN Thread : i = -1

 Something very strange has happened. First it looks like they're all counting down from 5 (or yours may have started with
 7), and we only had one of our threads, the purple thread, set up to count down from 7. Second, the threads don't appear
 to be decrementing, by one second each, any more. It looks like they're even decrementing by 2's and 3's in some cases.
 What happened? Well, first, we're sharing a single StopWatch instance among three threads. In my main method, I only have
 one stop watch instance set up. This wasn't a problem, when the state of the Stop watch instance was unimportant to the
 countdown operation. When we changed the variable i, to be a field on Stop watch, we told all three threads, to share
 the "i" field. Since our three threads were kicked off at the same time, they all arrived at the countdown for loop at
 about the same time. Each initialized, or set the value of i. So why did purple win? If I rerun this code a couple of
 times, they'll either start with five or seven. It's really last man in, that gets to say what i, will be, when these
 threads get around to printing i, in the first iteration. Maybe purple got there last in one case, but red or green got
 there last, in the next run. Because this loop variable is shared, by the time a second elapses, and these threads go
 to print the value of i, i will have the value in it from the last thread initializing it at the start of the for loop.
 It might even be decremented for two threads, before they have a chance to print it's first value, if the first thread
 was able to decrement, before the other two had their chance to print. This exercise demonstrates, that our threads are
 all changing the field, i, and those changes are visible to all threads, as they progress in their work. This has pretty
 ugly consequences for our stop watch behavior. None of them are keeping time correctly, or counting down correctly. The
 other thing I want you to grasp is, that none of these operations, not our method as a whole, or one iteration of this
 for loop, or even the print statement alone, is what's called an atomic unit of work. Each operation or statement is
 split into much smaller pieces of a task. Between each of these much smaller bits of work getting accomplished, is an
 opportunity for another thread to have its moment on the stage. This is called time slicing.

                                                Time Slicing

        Time slicing is also known as time-sharing or time division. It's a technique used in multitasking operating systems,
 to allow multiple threads or processes to share a single CPU or Central Processing Unit for execution. Available CPU time
 is sliced into small time intervals, which are divided out to the threads. Each thread gets that interval, to attempt to
 make some progress, on the tasks it has to do. Whether it completes its task or not, in that time slice, doesn't matter
 to the thread management system. When the time is up, it has to yield to another thread, and wait until its turn again.
 Unfortunately, when your threads are sharing heap memory, things can change during that wait.

                                        The Java Memory Model (JMM)

        The Java Memory Model, or JMM, is a specification that defines some rules and behaviors for threads, to help control
 and manage shared access to data, and operations. I'm not going to cover all of this specification, but I will touch on
 some important points, and techniques to prevent problems. First, you have to understand the problems, two of which include:

    * The Atomicity of Operations. I've explained how few operations are truly atomic, and this leads to problems with
      shared objects, because of time slicing.
    * Synchronization is the process of controlling threads' access to shared resources. There are multiple ways to do
      this, and we'll be getting into this in an upcoming lecture.

 Next, I want to use my stopwatch example, to talk more about these issues.
**/
//End-Part-7

    public StopWatch(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

//Part-3
/**
        I'll call this StopWatch, and it'll have a method to countdown to zero. I'll include this class, after the Main
 class, in the source file. Hopefully you remember, the only access modifier I can use is no modifier, or package default.
 My stop watch will count down by some unit of time, for this, I'll have a local variable, a TimeUnit, called time unit.
 I'll use IntelliJ to generate a constructor. Next, I'll set up a countdown method.
**/
//End-Part-3

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

//Part-4
/**
        This will be public, void, called countDown, with one parameter, a unitCount, an integer. I will assume my running
 threads, are going to be named by their color, so first, I'll get the thread name and put it in a local variable. I'll
 initialize another local variable, threadColor, to the ANSI RESET enum constant. I'm doing a little defensive programming
 here, so that if the thread name isn't a valid enum value, my code won't throw an exception. I'll use the thread name
 with the enums value of method, to get a ThreadColor instance. If for some reason that failed, I'll catch the error and
 just ignore it. The thread will use the system color, because it will be set to ANSI RESET. Now that I've got the thread
 color, I'll continue. I'll get the string value of the color, by calling my color accessor method on that enum, and assign
 it to a local variable. I'll set up a for loop to do a count down. You can imagine that this might be minutes or seconds.
 I'll default to starting with the unit count, the method argument, and count down by 1 for that. I'm going to use my time
 unit variable, to have the stopwatch wait at certain intervals, before it decrements the count, so I'll need to catch or
 specify an Interrupted Exception. Here I plan to catch it. I can pass one, to the timeUnit's sleep method, so if my stop
 watch's unit was minutes, this would wait a minute before decrementing the value again. I have to catch the exception,
 And I'll just print that out, if I get it. I'll use a printf statement, so this all fits on one line. This will print
 the color string, the thread's name, the value of i, and a new line. That's my StopWatch code. I'm going to add an
 overridden method to this, so that the default unitCount will be 5. I'll have a second method, called countdown, that
 has no arguments. This will delegate to its overridden method, passing it 5 as the default unit count. I included this
 method, so that when I create one of my threads, I can demonstrate how to use a method reference, which this method will
 allow me to do. Now, it's time to test this out. Moving up to the main method,
 **/
//End-Part-4
}
