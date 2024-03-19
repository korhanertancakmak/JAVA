package CourseCodes.NewSections.Section_19_Concurrency.Course01_BasicsOfJavaThreads;

//Part-1
/**                                             Introduction

        I want to start this section, on concurrency and multi threading, by defining a few key terms that you need to
 know, as they relate to this topic in Java. I'll start with the term,

                                                  Process

        It is a unit of execution, that has its own memory space. Now, most instances of a java virtual machine, the JVM,
 run as a process. When we run a java console application, what we're doing is, we're kicking off a process. The terms
 process and application are often used interchangeably, and I've done that myself, and will again in this section. If
 one java application is running, and we start up another one, each application has its own memory space, also known as
 the "heap".

                                        Process memory space = Heap

 The first application can't access the heap that belongs to the second java application. In other words, the heap isn't
 shared between two applications or two processes, they each have their own. Next, Let's talk about a thread.

                                                  Thread

        It is a single unit of execution, within a process. "Each process can have multiple threads". In Java, every process
 or application has at least one thread, and that's "the main thread" for most programs. In fact, just about every Java
 process also has multiple system threads, that handle every day tasks, like memory management, and input output. We don't
 have to explicitly create, and code these kinds of threads, the JVM does this work. Our code will run on the main thread,
 which is created automatically by your Java program. We can also have our code run in other threads, which we can explicitly
 create and start. In this section of the course, you're going to be learning different ways to create threads, execute
 them, and manage them.

                                         Threads Share Process Memory

        Creating a thread doesn't require as many resources as creating a process does. It's important to understand that
 every thread created by a process, shares that process's memory space, the heap. This can cause big problems with your
 applications, and you'll find out about that, in a later lecture.

                                        Threads also have Stack Memory

        In addition to the process memory, or heap, each thread's got what's called a thread stack. This is memory, that
 only a single thread, will have access to. I'll be comparing the heap, versus the thread stack, in a later lecture in
 this section as well. Every Java application runs as a single process, and each process can then have multiple threads
 within it. Every process has a heap, and every thread has a thread stack.

                                          Why use multiple threads?

        I guess at this point you might be asking, why would we want to use multiple threads in our application? Why not
 just stick with the main thread? What are some of the advantages, to creating a multi-threaded application? Well, there
 are several reasons why you'd want to do this. One of the most common reasons, is to offload long running tasks. Now,
 for example, we might want to query a database, or we might want to fetch some data, from a website on the internet.
 Since code executes linearly, if we performed this task in the main thread, we'd be stuck waiting until this operation
 completed, before the main thread could do anything else. To the user, this could appear as if the application has died,
 or is locked up, especially when we're dealing with a user interface application. Instead of tying up the main thread,
 we can create additional threads, to execute tasks that might take a long time. This frees up the main thread so that it
 can continue working, and executing, and being responsive to the user. You can report progress, or accept user input,
 and perform other tasks on the screen, or in other parts of the program, while that long-running task, continues to execute,
 in the background. You also might use multiple threads to process large amounts of data, which can improve performance,
 of data intensive operations. A web server, is another use case for many threads, allowing multiple connections and requests
 to be handled, simultaneously. That now brings us to the concept of Concurrency,

                                                 Concurrency

        Which refers to an application doing more than one thing at a time. Concurrency allows "different parts of a program
 to make progress independently", often leading to better resource utilization, and improved performance. Underneath the
 covers, there's a lot of thread management tasks that occur, which give the impression that progress is being made on
 multiple fronts, even in a single CPU environment. Let's say that an application wants to download data, and separately,
 draw a shape on the screen. If it's a concurrent application, what it can do is, it can download a bit of data, then
 switch to drawing part of the shape. It can then switch back to downloading some more data, then back again to drawing
 more of the shape, and so on. Each task is done a bit at a time, and can report on its progress, to the user. What
 concurrency really means is, that one task doesn't have to complete, before another one can start, and "multiple threads
 can make incremental progress". Concurrency is something that's best learnt by example. Now, I'll be examining the main
 thread a little bit, to explain some key Thread concepts, and then I'll create and kick off, a concurrent thread.
 **/
//End-Part-1


//Part-2
/**                                      Java's Threads and Thread Basics

        Threads are the fundamental building blocks, to support concurrency, in a Java application. They're essential,
 because they allow us to perform multiple tasks simultaneously, within a single process. We've really been using a thread
 all along, because the JVM fires up a main thread, when you execute any application, using the main method. Let's jump
 into some code, and first get access to this main thread.
 **/
//End-Part-2

public class Main {

    public static void main(String[] args) {

        var currentThread = Thread.currentThread();
        System.out.println(currentThread.getClass().getName());

//Part-3
/**
        I've created the usual Main class and main method. I can get information about the main thread that's running, or
 any running thread actually, by executing static methods, on the Thread class. The first method is a static method, named
 currentThread, that returns an instance of the currently running thread. I'll set up a variable called currentThread,
 using var as the type, and assign it the value I get back from Thread.currentThread. I'll print the class name of that
 variable. Now, notice IntelliJ's overlay hint, that this method, currentThread, is returning a Thread instance. Let's
 just run this, and confirm what the instance's runtime type really is.

                    java.lang.Thread

 The return type of the running thread, really is an instance of java.lang.Thread. Let's see what this class is all about.

                                        The java.util.Thread Class

        I'm showing you the java.util.Thread class, greatly simplified. You can see that this class implements the Runnable
 interface, which has a single abstract method, the run method. This method has no parameters, and doesn't return any data.
 It simply executes some code, without parameterized input, or output.

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
                                 | private group: ThreadGroup        |
                                 | private name: String              |
                                 | private priority: int             |
                                 | private target: Runnable          |
                                 | private threadStatus: int         |
                                 | private tid: int                  |
                                 |___________________________________|
                                 | Thread()                          |
                                 | Thread(Runnable target)           |
                                 | ....                              |
                                 | run(): void                       |
                                 | start(): void                     |
                                 |___________________________________|

        Each instance of a thread has some state. The fields displayed here are all encapsulated, so they're private, and
 this includes the fields, thread group, a name, a priority, a status, and a thread id. I'm also showing just two constructors,
 of several that are available, because these are the basic two. A thread can be constructed with no arguments. Or It can
 be constructed by passing a Runnable instance to it. I'm also only showing two of many methods on the Thread class, because
 these two are important in the execution of the thread. The first method is the run method, which has to be overridden,
 since it's declared abstract on the Runnable interface. The second is a method called start, and I'll be talking about
 the differences, between the run and start methods shortly. Getting back to my code, I'll print out the attributes I
 showed you here, to examine the main thread.
 **/
//End-Part-3

        System.out.println(currentThread);
        printThreadState(currentThread);

//Part-5
/**
        First I'll just print the default representation of the Thread. I mean, if I just pass thread to the System.out.println
 statement, its toString method will get called, so let's see what data that prints. Then I'll call my method, passing
 it currentThread. Running this code now,

                     java.lang.Thread
                     Thread[#1,main,5,main]
                     ----------------------
                     Thread ID: 1
                     Thread Name: main
                     Thread Priority: 5
                     Thread State: RUNNABLE
                     Thread Group: java.lang.ThreadGroup[name=main,maxpri=10]
                     Thread Is Alive: true
                     ----------------------

 First, I see that the to String method, printed the class name, so Thread, and then in brackets, threadId, main, the number
 5, and again the text main. If I ctrl click on the Thread class in my first statement, this will bring up the Thread class's
 code. I'll search for the toString method. From this code, I can see that the first element in brackets is the thread
 id, the second is the thread name, the third is the thread priority, and the fourth element, if there is one, will be a
 group name. I'll talk about each of these shortly, so let me get back. I want to next examine what I got back, from my
 own method, that's printed next in the console. This is what my method printed out, one attribute at a time. My Thread
 Id is 1, the thread name is main, its priority is 5, which we knew from the toString method. This state had a state of
 Runnable, at the time I queried it. Every thread can optionally be part of what's called a thread group, so this thread's
 group, is also named main. Finally, at the time of this execution, this thread was alive, so the method, is alive, returned
 true. In addition to being able to retrieve this data, you can also modify some of these fields with setters. I'll do
 that next.
 **/
//End-Part-5

        currentThread.setName("MainGuy");
        currentThread.setPriority(Thread.MAX_PRIORITY);
        printThreadState(currentThread);

//Part-6
/**
        First, I'll set the the thread name, to MainGuy. For priority, you'd enter a number between 1 and 10, where 10
 is the highest priority, or the most important. Rather than entering the number 10, there are constants on the Thread
 class, which I'll cover shortly. Here, I'm setting the priority of the thread to the max priority, a constant on thread,
 which has the value of 10. I'll print the thread state after I make these changes. Now, if I run the code,

                 ----------------------
                 Thread ID: 1
                 Thread Name: MainGuy
                 Thread Priority: 10
                 Thread State: RUNNABLE
                 Thread Group: java.lang.ThreadGroup[name=main,maxpri=10]
                 Thread Is Alive: true
                 ----------------------

 You can see by the last block of information, that my thread name is MainGuy, and my priority is 10. Let's talk about
 this priority for a second.

                                                Thread Priority

 Thread priority is a value from 1 to 10. The Thread class has three pre-defined priorities, included as constants.

                 Thread.MIN_PRIORITY = 1 (low)
                 Thread.NORM_PRIORITY = 5 (default)
                 Thread.MAX_PRIORITY = 10 (high)

 These are the MIN Priority, with a value of 1, which is for your least important thread. There is NORM Priority, or normal,
 the default, which has a value of 5. There is MAX Priority, a 10, which can be assigned to your most important threads.
 Higher-priority threads have a better chance of being scheduled, by a thread scheduler, over the lower-priority threads.
 However, priority behavior can vary across different operating systems and JVM implementations. You can think of this
 priority as more of a suggestion, to the thread management process. Ok, so that's what a thread's fields look like,
 specifically the main thread. There are additional constructors that let you pass in a name, or a group, when you do
 create a thread this way. I'll be talking about the different thread states, of which Runnable is one, in a little bit.

                                            Creating a Thread Instance

        Now, let's create and execute more than just this one running thread. As I said earlier, there are multiple ways
 to create Thread instances.

    * You can Extend the Thread class, and create an instance of this new subclass.
    * You can create a new instance of Thread, and pass it any instance that implements theRunnable interface. This includes
      passing a lambda expression.
    * You can also Use something called an Executor, to create one or more threads for you.

 I'll be covering this example, in a later lecture. I'll start by showing you how to extend the Thread class, to create
 a new thread instance. I'll create a new class and call it CustomThread that needs to extend the Thread class.
 **/
//End-Part-6
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

//Part-4
/**
        You can retrieve these attributes using the accessor or getter methods, on the thread instance. I'll set this up
 as a separate method, and I want to make it public, so other classes can use it, to query information about a thread's
 state. It won't return any data, but it'll take a thread instance, and use it, to print its relevant information. I'll
 start with a separator line, so it's easier to read. I'll call getId, on the thread passed to this method. Next, I'll
 call get name on the same instance. after this, I'll get the priority. Then the thread state, and the thread group. And
 finally, I'll call the method, is alive, on this main thread. I'll end with a separator line as well. Getting back to
 the main method,
 **/
//End-Part-4
}
