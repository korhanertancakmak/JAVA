package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_17_Concurrency.Course23_RevisitingDeadlockCommonProblems;

import java.io.File;

//Part-1
/**
        In an earlier lecture, I demonstrated a deadlock, with the ConsumerProducer sample code. Both classes were accessing
 a single boolean field, on a shared object, in a while loop with no code in it.

                                                                                         ____________________________________________________
                                                                                         | public synchronized void write(String message) { |
                                                                                         |                                                  |
                         _____________________________                                   |     while (hasMessage) {                         |
                         | Stuck in a while loop,    |                                   |     }                                            |
                         | waits for flag to be true |                                   |     hasMessage = true;                           |
                         |   never releases lock     |                                   |     this.message = message;                      |
                         |___________________________|                                   | }                                                |
                                                     |                                   |_____________↑____________________________________|
                                                     |                                                 ↑
                       Thread A          READ        |    Message        |         WRITE            Thread B
                       Consumer         >>>>>>>      |   Repository      |        <<<<<<<<          Producer
                          ↓                          | Lock = (ThreadA)  |
                          ↓                                              |
             _____________↓_________________________                     |________________________
             | public synchronized String read() { |                     |    Can't get lock,    |
             |                                     |                     | can't execute write,  |
             |     while (!hasMessage) {           |                     |  can't change flag    |
             |     }                               |                     |_______________________|
             |     hasMessage = false;             |
             |     return message;                 |
             | }                                   |
             |_____________________________________|

 You might remember this scenario. We resolved this situation by using the notifyAll, and wait methods on object, in both
 of these methods. A deadlock is one of three types of thread contention problems that can occur. In addition to the deadlock,
 you might also run into a livelock, or a starvation scenario.

                                The Common Problems in a Multi-Threaded Application

         Problem                    Description

         Deadlock                   Two or more threads are blocked, waiting for each other to release a resource.
         Livelock                   Two or more threads are continuously looping, each waiting for the other thread to take some action.
         Starvation                 A thread is not able to obtain the resources it needs to execute.

 I showed you one example for a deadlock, where one thread was blocked and the other was just caught in an infinite loop.
 A more common occurrence is, when two or more threads are blocked, waiting for each other to release a resource. This is
 a bit different than the previous scenario, and I'll explain the difference shortly. A livelock is different from a
 deadlock, because the threads aren't blocked and waiting. Instead, two or more threads are continuously looping, each
 reacting to another thread's action. Finally, a starvation situation is when one or multiple threads aren't able to make
 progress, because they can't obtain the resources they need. The threads aren't blocked, and they're not continuously
 reacting or looping. Let's see if we can see the differences, in code.
 **/
//End-Part-1

public class Main {

    public static void main(String[] args) {

        File resourceA = new File("inputData.csv");
        File resourceB = new File("outputData.json");

//Part-2
/**
        I'll start with the classic example of a deadlock, with two threads trying to acquire the locks on two different
 shared resources, at the same time. I've created the usual Main class. Let's imagine we have two files, one is a csv file,
 and one is a json file. So resourceA is the csv file. And resourceB will be a json file. The first process is responsible
 for reading data from the csv file, then dumping it to the json file.
 **/
//End-Part-2

        Thread threadA = new Thread(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " attempting to lock resourceA (csv)");
            synchronized (resourceA) {
                System.out.println(threadName + " has lock on resourceA (csv)");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(threadName + " NEXT attempting to lock resourceB (json), " + "still has lock on resource A (csv)");
                synchronized (resourceB) {
                    System.out.println(threadName + " has lock on resourceB (json)");
                }
                System.out.println(threadName + " has released lock on resourceB (json)");
            }
            System.out.println(threadName + " has released lock on resourceA (csv)");
        }, "THREAD-A");

//Part-3
/**
        I'll call this thread a, and create a new instance of a Thread, passing it a lambda. I'll get the thread name.
 I'll print that name, and that this thread is attempting to lock resourceA. Here I set up a synchronized block, synchronizing
 on resourceA, the csv file. If I get to this statement, in the block, then I know this thread was able to get the lock,
 so I'll print that information out. After this, I'll set up a try block. I'll call sleep on Thread, with 1000 milliseconds.
 I'm just putting this here, as a placeholder, to represent some kind of work, like reading from that file. I'm keeping
 it uncomplicated on purpose, but imagine this would be where you'd read in data, and maybe transform the data, or run
 statistics or something. And of course, there's the catch clause. If I get an exception, I'll print it. After the block
 completes, I'll print that the thread has released resourceA. Finally, I'll include my own custom thread name, as the
 second argument, and I'll call this, Thread A. So in this code, the thread will lock file a, and most likely read from
 it. For now, I'm just putting the sleep code in there to represent some kind of work, that might be done. Once the data
 is read, our process will dump the results into a json file. First, I'll add another print statement. That this thread
 is now attempting to get a lock on resource B, but still has a lock on resource A. Again, I'll start a block, synchronizing
 on the resource, but this time it's resource B. And if the thread gets the lock, I'll print that this thread now also
 has a lock on resource B, the json file. After the synchronized block completes, I'll print that the lock was released,
 on resource B. I'll do something really similar for the second Thread. I could copy and paste this whole statement, but
 it's just as easy to type it out, so I'll do that.
 **/
//End-Part-3

        /*Thread threadB = new Thread(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " attempting to lock resourceB (json)");
            synchronized (resourceB) {
                System.out.println(threadName + " has lock on resourceB (json)");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(threadName + " NEXT attempting to lock resourceA (csv), " + "still has lock on resource B (json)");
                synchronized (resourceA) {
                    System.out.println(threadName + " has lock on resourceA (csv)");
                }
                System.out.println(threadName + " has released lock on resourceA (csv)");
            }
            System.out.println(threadName + " has released lock on resourceB (json)");
        }, "THREAD-B");*/

//Part-4
/**
        This time, I'll have thread B. I'll get the thread name. I'll print that this thread is attempting to get a lock,
 on resource B first here. This thread wants access to the same resources, but in the reverse order, of the first thread.
 I'll set up a synchronized block, synchronizing on resource B. I'll print that the thread got the lock. Again, I need a
 try block. I'll just put another sleep here. Again, picture this as the place for code that does something with the data,
 in the json file, whatever that might be. And of course, I've got the catch. And I'll just print any error. After the
 synchronized block, I'll print that this thread has released the lock on resource B. I'll give this thread a custom name,
 this time, I'll make it Thread B in this case. This thread, like thread A, has another part, to open the second resource,
 resource A, the csv file, for whatever reason. This part of the process will be enclosed in the first synchronized block.
 Again, I'll print the thread name, and that it's attempting to lock resource A, next, and it's already got a lock on
 resource B. I'll synchronize on resource A. If the code gets to this point, it'll print that this thread, got the lock
 on resource A. And after that synchronized block, I'll print out that the thread has released the lock, on resource A.
 Ok, so those are my two threads.
 **/
//End-Part-4

        /*threadA.start();
        threadB.start();

        try {
            threadA.join();
            threadB.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

//Part-5
/**
        Now I'll start them off. I'll call start on thread A, followed by thread B. I need a try block, because I'll be
 using the join method. I'll wait until the threads complete here, using join, first on thread A. Then on thread B. And
 the usual catch clause. Ok, so that's the scenario. I'll run this code.

             THREAD-A attempting to lock resourceA (csv)
             THREAD-B attempting to lock resourceB (json)
             THREAD-A has lock on resourceA (csv)
             THREAD-B has lock on resourceB (json)
             THREAD-B NEXT attempting to lock resourceA (csv), still has lock on resource B (json)
             THREAD-A NEXT attempting to lock resourceB (json), still has lock on resource A (csv)

 And you can see, the two threads are running concurrently. Thread A is attempting a lock on resource A, which is the csv
 file. And Thread B is attempting to get the lock on resource B, which is the json file. They're both successful at obtaining
 these locks, and we see that with the next two statements printed out. Next, both threads attempt to lock the next resource
 in their process. Thread B wants to access resource A, but it's locked by Thread A. Thread A wants to access resource B,
 but it's locked by Thread B. Neither Thread can proceed, which means they are blocked inside a synchronized block. This
 is bad. It means each thread will keep the lock indefinitely on the resource they already have, so we've got the classic
 deadlock. I'll stop this application manually. Let me show you what this code is doing on a diagram.
 **/
//End-Part-5

//Part-6
/**
                                            A Second Deadlock Scenario

                                                                    ____________________
                                                                    | Thread B waiting |
                                                                    | on Thread A to   |
                                                                    |  release lock    |
                                                                    |_____↓____________|
                                                                          ↓
                                                   _____________________  ↓
                                   STEP-1          |    ResourceA      |  ↓                 STEP-2
                 Thread A →→→→→→→→→→→→→→→→→→→→→→→→ | Lock = (ThreadA)  | ←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←← Thread B
                     ↓                             |___________________|                                               ↓
                     ↓                                                                                                 ↓
                     ↓                  STEP-2                   _____________________              STEP-1             ↓
                     ↓ →→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→ |    ResourceA      | ←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←↓
        _____________↓____________________                    ↑  | Lock = (ThreadA)  |                    _____________↓____________________
        | run() {                        |                    ↑  |___________________|                    | run() {                        |
        |   synchronized (resourceA) {   |         ___________↑__________                                 |   synchronized (resourceB) {   |
        |     synchronized (resourceB) { |         |  Thread A waiting  |                                 |     synchronized (resourceA) { |
        |     }                          |         |  on Thread B to    |                                 |     }                          |
        |   }                            |         |   release lock     |                                 |   }                            |
        | }                              |         |____________________|                                 | }                              |
        |________________________________|                                                                |________________________________|


        When both threads need access to multiple resources in a certain order, this can cause contention, and produce a
 deadlock, as we just saw. Consider the example on this diagram, which is representative of the code I just demonstrated.
 Thread A locks resource A by left Step-1. Thread B locks resource B by right Step-1. While still keeping the lock on
 resource A, Thread A now tries to get a lock on resource B by left Step-2. Thread B does the same thing, hanging onto
 the lock for resource B, but trying to also acquire resource A's lock by right Step-2. Neither thread can release their
 locks until their synchronization blocks complete, which means they are permanently stuck here. Something will have to
 come along, and shut down one of these threads, to get something to happen. There are many ways to avoid this situation.

                                                Preventing Deadlocks

        A couple of common ways to avoid this kind of deadlock situation, are listed here.

    * Organize your locks into a hierarchy, and ensure that all threads acquire locks in the same order to avoid circular
    waiting, which is a common cause of deadlocks. This approach helps establish a global lock order that all threads must
    follow.
    * Instead of using traditional synchronized blocks or methods, you can use the tryLock method on the Lock interface.
    This method allows you to attempt to acquire a lock. If it fails, you can handle the situation without causing a deadlock.

 Let me show you what the first recommendation means in the code.
 **/
//End-Part-6

        Thread threadB = new Thread(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " attempting to lock resourceA (csv)");
            synchronized (resourceA) {
                System.out.println(threadName + " has lock on resourceA (csv)");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(threadName + " NEXT attempting to lock resourceB (json), " + "still has lock on resource A (csv)");
                synchronized (resourceB) {
                    System.out.println(threadName + " has lock on resourceB (json)");
                }
                System.out.println(threadName + " has released lock on resourceB (json)");
            }
            System.out.println(threadName + " has released lock on resourceA (csv)");
        }, "THREAD-B");

//Part-7
/**
        Consider Thread B for a minute. I've made this code a bit vague, but let's just say Thread B is processing data,
 and really just reading data from both files. It doesn't actually need to read from the json file first. If we organize
 our locks into a hierarchy, as recommended, we'd set up a rule that says all code has to get locks in the same order,
 first getting the lock on the csv file, then getting the lock on the json file. I'll reverse the code in thread b to
 follow this rule. The easiest way for me to do this, is to just comment the first part and rewrite the code in this thread
 below. Now, I'll just copy the code in ThreadA, and paste it into Thread B. I'll run this code again,

             THREAD-B attempting to lock resourceA (csv)
             THREAD-A attempting to lock resourceA (csv)
             THREAD-B has lock on resourceA (csv)
             THREAD-B NEXT attempting to lock resourceB (json), still has lock on resource A (csv)
             THREAD-B has lock on resourceB (json)
             THREAD-B has released lock on resourceB (json)
             THREAD-B has released lock on resourceA (csv)
             THREAD-A has lock on resourceA (csv)
             THREAD-A NEXT attempting to lock resourceB (json), still has lock on resource A (csv)
             THREAD-A has lock on resourceB (json)
             THREAD-A has released lock on resourceB (json)
             THREAD-A has released lock on resourceA (csv)

 In this situation, both threads are able to get both locks, processing whatever they need to do, and completing. You
 should always be really careful when you're using nested synchronized blocks. When you see that situation, make sure you
 understand what other asynchronous code might be doing, and verify all threads acquire locks on resources in the same
 order. If you have to use a nested locking as I've done here, and you feel like you can't follow the hierarchical order,
 than be sure to use Lock implementations instead of the built-in monitor lock. In other words, don't use a synchronized
 block as your nested locking mechanism. Use explicit locks. They give you more options. Next lecture, we'll pick up in
 the same project to look at the Live Lock concurrency problem.
 **/
//End-Part-7

        threadA.start();
        threadB.start();

        try {
            threadA.join();
            threadB.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}