package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_18_WorkingWithDatabases.Course02_UsingMySQL;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
/*
        CustomThread customThread = new CustomThread();
        customThread.start();*/
        //customThread.run();

//Part-1
/**
        In the last lecture, we looked at thread state, using the main thread. We'd just left off, after creating a custom
 sub class of our own, that extended the java.lang.thread class. In this lecture, I'm going to execute the code I included
 in that class, as a concurrent thread, to the main thread. To do this, I'll first create an instance of it, in the Main
 class's main method. I'll call it customThread, and I'll just use the no args constructor to create a new instance. To
 run this thread concurrently, I call start on the variable instance, so customThread.start. If you're paying attention,
 you might be asking, why am I executing start, and not the run method, that I created and overrode? That's a really good
 question. I'll explain why, in just a bit, but first I want to execute this code, as I've got it set up here.

                 1  1  1  1  1

 You can see from the output, as it's running, the number 1 is printed every half second. The main thread didn't exit,
 until this thread completed. But how do I really know, that this thread is running concurrently, to the main thread?
 I'll test this, by setting up another for loop, for the main thread, that will do something similar.
**/
//End-Part-1
/*

        Runnable myRunnable = () -> {

            for (int i = 1; i <= 8; i++) {
                System.out.print(" 2 ");
                try {
                    TimeUnit.MILLISECONDS.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread myThread = new Thread(myRunnable);
        myThread.start();
*/

//Part-3
/**
        I'll create a Runnable variable, using a lambda expression, and I'll call that my runnable. Don't forget that the
 code I write here, represents the code that goes in the run method of runnable. In this code, I'll define the task I want
 the thread to perform, when it gets executed. You can think of this as your thread's task. I'll add a for loop in my
 lambda expression. This time I'll do 8 iterations. I'll print the number 2, for my 2nd concurrent thread, on each iteration.
 I know I need a try block, so I'll set it up. Here, I'll use the MILLISECONDS value on TimeUnit, and pass 250. Whether
 you use Thread's sleep method, or one of these enum values like I've done here, and call its sleep method, this is really
 up to you. For myself, I kind of like that this method is self documenting. As usual, I'll catch any interrupted exception,
 And print information about it. That's the lambda expression, containing the code I want to run, but how do I actually
 use it in a thread? Well, I first need to create a new Thread instance, and pass this lambda expression, which is ultimately
 just a Runnable, to the Thread constructor, that takes a Runnable as an argument. I'll call new Thread, passing the my
 Runnable variable, to that constructor. And again, I can simply call the start method, on this new thread instance.
 Now, if I run this code,

            1  0  2  2  1  2  2  0  1  2  2  1  2  2  0  1

 I can see all three threads, running at the same time. The main thread prints the zeros out, the CustomThread is printing
 ones, and this last thread, created using a Runnable lambda expression, is printing twos. OK, so what's really the difference
 between these two approaches? Let's take a minute to review the differences, between these two thread creation methodologies
 a couple of diagrams.

                             _____________________________________
                             |  <<Functional Interface>>         |
                             |   Runnable                        |
                             |-----------------------------------|
                             | run(): void                       |
                             |___________________________________|
                                               ↑
                                               ↑
                                               ↑
                             __________________↑__________________
                             |  Thread                           |
                             |___________________________________|
                             | run(): void                       |
                             | start(): void                     |
                             |___________________________________|
                                               ↑
                                               ↑
                                               ↑
                             __________________↑__________________
                             |  CustomThread                     |
                             |___________________________________| →→→→→→→→→ new CustomThread().start()
                             | run(): void                       |
                             |___________________________________|

 This diagram demonstrates extending the Thread class. The new subclass overrides the Thread's run method, to provide the
 concurrent thread's task. To use this thread, you create a new instance of your subclass, with a no argument constructor,
 and execute the start method on that instance.

                                        Advantages of Extending Thread

        The advantages of this methodology are that

    * You have more control over the thread's behavior and properties.
    * You can access the thread's methods and fields directly from your sub class.
    * You can also create a new thread for each task.

                                        Disadvantages of Extending Thread

        The disadvantages, of this way of thread creation are that

    * You can only extend one class in Java, so your subclass can't extend any other classes. In other words, a subclass
      of thread, is external to any domain specific hierarchy, that you might also want for this subclass.
    * Your class is tightly coupled to the Thread class, which may make it difficult to maintain.

                                             Implementing Runnable

                 _____________________________________              _____________________________________
                 |  <<Functional Interface>>         |              |  AnyOrManyClass                   |
                 |   Runnable                        |              |-----------------------------------|
                 |-----------------------------------| ←←←←←←←←←←   | run(): void                       |
                 | run(): void                       |              |________________↓__________________|
                 |___________________________________|                               ↓              Sub Class
                                   ↑                                                 ↓              Anonymous Class
                                   ↑                                                 ↓              Lambda Expression
                                   ↑                                                 ↓              Method Reference
                 __________________↑__________________                               ↓
                 |  Thread                           |              new Thread(CustomRunnable).start()
                 |___________________________________|
                 | run(): void                       |
                 | start(): void                     |
                 |___________________________________|


 Besides extending the Thread class, you can create threads, by implementing the Runnable interface. This method allows
 any class, to implement Runnable, meaning any class at all, can be used in a thread. This class is passed to the Thread
 constructor, that accepts a Runnable. You can also pass an anonymous class, lambda expression, or a method reference to
 this constructor, to create an instance of a Thread. You again call start on the new thread instance, to execute the
 code asynchronously.

                        Advantages of Implementing a Runnable and Creating a Thread Instance with it

         The advantages here are that

    * You can extend any class and still implement Runnable. This gives you more flexibility in your design.
    * Your class (if you create a class) is loosely coupled to the Thread class, which makes it easier to maintain.
    * You can use anonymous classes, lambda expressions, or method references, to very quickly describe thread behavior.

                      Disadvantages of Implementing a Runnable and Creating a Thread Instance with it

        The disadvantages, in my opinion, are few, but

    * You do have less control over the thread's behavior and properties. In other words, You can't access the thread's
      methods and fields directly, from the run method.

 Now that you've seen how to create threads, and how to fire them up asynchronously, it's time to talk about a bit of thread
 management. In the next lecture, I'll cover how to interrupt a running thread, as well as how to create dependencies among
 the running threads.
 **/
//End-Part-3
/*

        for (int i = 1; i <= 3; i++) {
            System.out.print(" 0 ");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
*/

//Part-2
/**
        This time, I'll loop for three iterations. I'll print a zero for this main thread, instead of a 1, for each iteration.
 Again, I need a try catch, to catch an Interrupted Exception. Now, this time, I want to show you another way to make a
 thread sleep. I can use an enum, TimeUnit, and one of its values, which are all units of time. In other words, I'm not
 limited to just specifying MILLISECONDS. I can specify any unit on this enum, so I can use NANOSECONDS, SECONDS, MINUTES,
 HOURS and so on. In this case I'll use SECONDS, and pass 1 to that, so the thread will sleep for 1 second. This code calls
 Thread.sleep under the covers, and does the math, to figure out how many milliseconds this is. And like I did for my
 customThread, I'll print the stack trace if I get this exception If I run this code now,

            0  1  1  0  1  1  0  1

 Notice, the numbers, zero and 1, are printed out, and they're alternating. This is a pretty good sign, that the threads
 really are running concurrently, each doing its own thing, and not waiting on each other.

        Now, I want to get back to the earlier question, why did I call the start method, and not the run method. Let's
 see what happens if I do call run, so I'll change customThread.start, to customThread.run. If I run the code this way,

            1  1  1  1  1  0  0  0

 I'll get all five ones printed first, followed by the three zeros. When you execute the run method, it gets done synchronously,
 in the current thread. The main thread runs this method like any other method on a class, and waits for it to complete,
 before continuing with its next tasks.

                             The difference between executing run and start on a thread

        There's a big difference between calling run() and start(). If you execute the "run" method, it's executed
 "synchronously", by the running thread it's invoked from. If you want your code to be run "asynchronously, you must
 call the start method". I'll revert that last change, so that it's executing the start method again.

        I'm going to ctrl click on that start method, to see what the java code is really doing inside this method. You
 can see the method has a synchronized modifier.

                 public void start() { synchronized (this) {
                     if (holder.threadStatus != 0)
                       throw new IllegalThreadStateException();
                     start0();
                     }
                 }

 I haven't covered this yet, but I'll be coming back to this special keyword later in this section. Notice next, that
 there's some code to add this thread to a group, and then it calls this.start zero. I'll ctrl click on that method, to
 follow the trail.

                     private native void start0();

 Here, I've got a method declaration with no body, its private, and it's got this native key word. What is this telling
 me?

                                            The native modifier on a method

        The native modifier indicates that this method's source code isn't written in Java. It's written in another language,
 such as C or C++. The code in this example, is part of a native library, such as a dll file. When would you want to use
 a native library method? The reasons to do this include when you want

        * to access system-level functionality that's platform-specific.
        * to interface with hardware
        * to optimize performance for tasks that might be computationally-intensive.

 In truth, creating a thread is an expensive operation. Later, we'll talk about why you'd want to use thread pools, to
 reduce the work needed to create a thread, on your operating system. In this case, the Java code is handing off to a
 native library, to do the thread creation here, to both access system level functionality and to optimize performance.
 That calling the run method directly from your thread instance, isn't creating a new thread at all, it's simply executing
 that code directly. The start method is the method that will do the work, of creating the new thread, and on that new
 thread, executing the code in your run method. Now, let's look at the second way to create a new thread, which is by
 implementing the Runnable interface.

                                        Runnable is a Functional Interface

        It's important to recognize that Runnable is a functional interface. It's functional method, or its single access
 method, SAM, is the run method. This is really important, because this means that Anywhere you see a Runnable type, it's
 a target for a lambda expression. Not only that, but You can have any class implement the Runnable interface, and then
 pass it to the Thread constructor, to run asynchronously. I'll insert this next code, before the last for loop in the
 main method. This is where I have the main thread printing a zero three times. I want this code to run concurrently,
 both with my custom thread, as well as the main thread, so I'll insert my code, before this final for loop.
 **/
//End-Part-2

    }

    public static void printThreadState(Thread thread) {

        System.out.println("----------------------");
        System.out.println("Thread ID: " + thread.getId());
        System.out.println("Thread Name: " + thread.getName());
        System.out.println("Thread Priority: " + thread.getPriority());
        System.out.println("Thread State: " + thread.getState());
        System.out.println("Thread Group: " + thread.getThreadGroup());
        System.out.println("Thread Is Alive: " + thread.isAlive());
        System.out.println("----------------------");
    }
}
