package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_17_Concurrency.Course03_InteractingRunningThread;

//Part-1
/**
         In the last lecture, we looked at the Thread class, and two different ways to create a thread, and to run them
 concurrently with the main thread. In this lecture, I'll focus on methods to both manipulate and communicate with running
 threads. There's one method you've already seen before and that's the sleep method, a static method on Thread. This method
 allows you to pause the execution of a thread for a specified amount of time. It temporarily suspends the execution of
 the current thread. You can do this in the main thread, or in any concurrent threads you create.
**/
//End-Part-1

public class Main {

    public static void main(String[] args) {

        System.out.println("Main thread running");
        try {
            System.out.println("Main thread paused for one second");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //System.out.println("Main thread would continue here");

//Part-2
/**
        I've created a new usual main method. I'll first print a message, that the main thread is running. Next, I'll
 simply call Thread.sleep here, passing 1000 milliseconds, which we know is one second. IntelliJ is giving me an error,
 and if I hover over that, you'll see that I'm getting an unhandled exception, an InterruptedException. By now, you know
 this is a checked exception, and that I have to either handle this, or include it in a throws clause of the method. I
 don't want to add this exception to my method signature, so I'll pick More Actions and I'll select try catch. Now, notice,
 that the catch block is throwing a run time exception. This is one way to handle the situation, and I don't have any
 compiler errors. I'll instead change that code, to just print the stack trace. I'll output a statement before this
 thread sleeps. I'll print that the main thread is paused for one second. I'll also print a message that the main thread
 will continue here. Running this code,

                             Main thread running
                             Main thread paused for one second
                             Main thread would continue here

 you can see the Main thread running, then the code pauses for one second, and finishes by printing, Main thread would
 continue here. Now, to create a concurrent thread, I'll create a new instance of Thread, and pass it a runnable lambda
 expression. I can define this, directly in the constructor, all in one statement.
 **/
//End-Part-2

        /*Thread thread = new Thread(() -> {
            String tname = Thread.currentThread().getName();
            System.out.println(tname + " should take 10 dots to run.");
            for (int i = 0; i < 10; i++) {
                System.out.print(". ");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("\nWhoops!! " + tname + " interrupted.");
                    return;
                }
            }
            System.out.println("\n" + tname + " completed.");
        });

        System.out.println(thread.getName() + " starting");
        thread.start();*/

        //System.out.println("Main thread would continue here");

//Part-3
/**
        I'll create a new thread, and start a new multi-line lambda expression here. I'll start by getting the thread name.
 Because this code will execute in a new thread, it will return that thread's name. I'll print a message, that this task
 should take ten dots to run. Inside this lambda's code block, I want to print a dot every half second, for 10 iterations.
 To start with, I'll set up a for loop, to loop 10 times. I'll print a dot in each iteration. I need a try block, because
 I'll be using Thread.sleep. I'll have the thread sleep for half a second. Here, I want to catch the interrupted exception.
 And I'll print a message that prints whoops, the thread was interrupted. I'll include the thread name in my message.
 Finally I'll exit the loop if this happened. After this for loop, I'll print that the thread completed its work successfully.
 Finally, I'll print that I'm kicking off this concurrent thread, and I'll include the thread name, by calling getName,
 on the thread variable. Lastly, I'll start the new thread Running this code,

                     Main thread running
                     Main thread paused for one second
                     Thread-0 starting
                     Main thread would continue here
                     Thread-0 should take 10 dots to run.
                     . . . . . . . . . .
                     Thread-0 completed.

 I see that my new thread's name is Thread 0, and it's starting. Notice that the method about the main thread continuing
 was printed, before any dots were printed. I get the message that Thread 0 should print 10 dots, then I start seeing the
 10 dots. Finally, after the dots are printed, I get the message that Thread 0 completed. Now that I've got a running
 thread, that catches an interruption, I'm going to actually interrupt this thread, from the main thread.
 **/
//End-Part-3

        /*try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();*/

//Part-4
/**
        First, I want to give it a little time to run, so I'll have the main thread sleep for 2 seconds. I'll print an
 error message, if the main thread is interrupted. I'll interrupt the new thread, by calling the interrupt method on the
 thread variable. Running this code,

                     Main thread running
                     Main thread paused for one second
                     Thread-0 starting
                     Main thread would continue here
                     Thread-0 should take 10 dots to run.
                     . . . .
                     Whoops!! Thread-0 interrupted.

 The output starts the same, until I get to 4 dots, which if you think about it, is 2 seconds. Then I get the message,
 Whoops, Thread zero interrupted. That's how you'd manually interrupt a running thread, from the code that started the
 thread. You can use the thread variable, as our main method does here, and call interrupt on that variable. You can
 imagine, that if your asynchronous thread was downloading a file, to the user's computer as one example, and the user
 decided to cancel it for some reason, this interrupt would stop the download and close any resources as necessary. After
 commenting out the thread lambda expression and the last 6 lines code, and rewriting it below,
 **/
//End-Part-4

        /*Thread thread = new Thread(() -> {
            String tname = Thread.currentThread().getName();
            System.out.println(tname + " should take 10 dots to run.");
            for (int i = 0; i < 10; i++) {
                System.out.print(". ");
                try {
                    Thread.sleep(500);
                    System.out.println("A. State = " + Thread.currentThread().getState());
                } catch (InterruptedException e) {
                    System.out.println("\nWhoops!! " + tname + " interrupted.");
                    System.out.println("A1. State = " + Thread.currentThread().getState());
                    return;
                }
            }
            System.out.println("\n" + tname + " completed.");
        });

        System.out.println(thread.getName() + " starting");
        thread.start();*/

//Part-5
/**
        Let's change this code a little bit, and only interrupt the thread, if it's taking longer than 3 seconds. I'll
 comment all the code I have here, after the thread.start method. Before I add code though, I want to change the code in
 my concurrent thread. In this case, I'll just add some print statements, that will print the state of the running thread.
 I'll print the current thread's state, after each sleep method, using A as my placement indicator. I'll also print the
 state, when the interrupted exception is thrown, this time I'll use A1, not A, so I know this message is coming from this
 catch clause. Next, I'll get the current time in milliseconds, elapsed from the epoch time.
 **/
//End-Part-5

        /*long now = System.currentTimeMillis();

        while (thread.isAlive()) {
            System.out.println("\nwaiting for thread to complete");
            try {
                Thread.sleep(1000);
                System.out.println("B. state = " + thread.getState());
                if (System.currentTimeMillis() - now > 2000) {
                    thread.interrupt();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("C. State = " + thread.getState());*/

//Part-6
/**
        I'll get the current time in milliseconds, elapsed from the epoch time. I use System.currentTimeMillis for this.
 I'll use this, to determine how much time has elapsed. Now, I'll hang out in a while loop, as long as the isAlive method
 returns true, on my asynchronous thread. I'll print that I'm waiting for the thread to complete. I need a try, because
 I'm going to use Thread.sleep. I'll sleep for 1 second. I'll print the thread's state here too, using B as my indicator
 in this thread. I'll catch the Interrupted Exception, And print the error's stack trace. Next, I'll add an if statement
 after the Thread.sleep method. If the current milliseconds minus the number in my now variable, results in a total elapsed
 time that's greater than 2 seconds, I'll interrupt the thread. After this while loop, I'll print data about my thread
 variable now. I'll print the state one more time here, using C as my placement indicator. I'll run this.

                             Main thread running
                             Main thread paused for one second
                             Thread-0 starting

                             waiting for thread to complete
                             Thread-0 should take 10 dots to run.
                             . A. State = RUNNABLE
                             . B. State = TIMED_WAITING

                             waiting for thread to complete
                             A. State = RUNNABLE
                             . A. State = RUNNABLE
                             . B. State = TIMED_WAITING

                             waiting for thread to complete

                             Whoops!! Thread-0 interrupted.
                             A1. State = RUNNABLE
                             B. State = TERMINATED
                             C. State = TERMINATED

 Notice, when the thread starts, the status of the thread, within its own run method is RUNNABLE. This is prefixed by the
 A. But look at the status of B, where we ask for the thread status, outside of the thread. The status is more often than
 not, TIMED WAITING. We see these both printed, after each half a second. Next, you can see A1 is printed, which is in
 the interrupted exception block, and the status is still RUNNABLE. But by the time B's statement is called, the state
 of this thread is TERMINATED, as it is when this while loop exits, or at C there. These different states are values on
 an enum, that's declared on the Thread class, and called Thread.State.
 **/
//End-Part-6

//Part-7
/**                                         Thread States on Thread.State

     New           : A thread that has not yet started is in this state.
     RUNNABLE      : A thread executing in the java virtual machine is in this state.
     BLOCKED       : A thread that is blocked waiting for a monitor lock is in this state.
     WAITING       : A thread that is waiting indefinitely for another thread to perform a particular action is in this state.
     TIMED_WAITING : A thread that is waiting for another thread to perform an action for up to a specified waiting time is in this state.
     TERMINATED    : A thread that has exited is in this state.

 A thread is in the new state, before we execute the start method on it. An executing thread will be in the runnable state,
 as long as it's not waiting for something. A waiting thread could be blocked, meaning Java recognized a blocking condition.
 I'll be covering these a bit later. A thread could be waiting on another thread, which can occur if you join this thread
 to another. It could be waiting on some timed event in which case you'll see TIMED WAITING, for example, for a Thread.sleep
 to end, or some other time out condition. Finally, a thread is terminated, when it successfully completes, or is interrupted,
 or it's run execution is stopped by some other means.
 **/
//End-Part-7

        /*Thread thread = new Thread(() -> {
            String tname = Thread.currentThread().getName();
            System.out.println(tname + " should take 10 dots to run.");
            for (int i = 0; i < 10; i++) {
                System.out.print(". ");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("\nWhoops!! " + tname + " interrupted.");
                    return;
                }
            }
            System.out.println("\n" + tname + " completed.");
        });

        Thread installThread = new Thread(() -> {
            try {
                for (int i = 0; i < 3; i++) {
                    Thread.sleep(250);
                    System.out.println("Installation Step " + (i + 1) + " is completed.");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "InstallThread");

        System.out.println(thread.getName() + " starting");
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (!thread.isInterrupted()) {
            installThread.start();
        } else {
            System.out.println("Previous thread was interrupted, " + installThread.getName() + " can't run.");
        }*/

//Part-8
/**
        Ok, so now we know how to get thread states, but you'll notice there's not an interrupted state. That's because
 there are methods to check if a thread was interrupted on Thread. Before I look into this, I want to switch gears for
 just a minute, to talk about the join method on thread. This method let's you create task dependencies. Let's imagine
 that our current thread is downloading an installation package. When it completes, we want to start a separate installation
 thread, but only if the download actually completed. I'll create the install thread here next. Before I do that, I'll
 just clean up my code, and remove all the statements that output the thread states. I'll clean up the two in my initial
 thread's lambda expression, first in the try block, then in the catch block. I'll then remove the statement in my main
 thread's while loop, after the Thread.sleep statement. Lastly, I'll remove the statement at the end of the main method.
 Now, I'll create a new Thread, and put this code immediately after the first thread variable. This time, I'll call this
 thread variable, installThread. I'll start with a new instance of thread, and pass it a lambda. I'll be using Thread.sleep
 so I need a try catch. I'll loop 3 times, Sleeping 250 milliseconds each time. Then I'll print, that an installation step
 is completed. I'll catch the interrupted exception, and just print the info. Here, I'll pass a second argument, which
 let's me set up my own name for the thread, so I'm going to call this thread the install thread. Now, I definitely don't
 want this thread to run, until the first has completed successfully. I'll call thread.join, which joins this thread to
 the current thread, which in this case is the main thread. This means the main thread will wait here, until this thread
 completes. This code doesn't compile without a try catch block, so I'll add that. After this thread completes, I'll check
 if the thread was interrupted, using the isInterrupted method, on the thread variable. If it wasn't interrupted, I'll
 kick off the install Thread. Otherwise, I'll print that the previous thread was interrupted. And I'll say that the install
 thread can't run, and I'll use that thread's name here. Remember this is the name I passed to the constructor of this
 thread. If I run this code as it is, the while loop I have here, this one, where I check thread.isAlive, is going to
 basically negate what the join method does. I mean it's going to wait for 2 seconds, then interrupt the first thread, so
 that by the time I do the join, there won't be a thread running, to join. I'll comment this code out for a moment. Ok,
 so now I'll run this.

                 Main thread running
                 Main thread paused for one second
                 Thread-0 starting
                 Thread-0 should take 10 dots to run.
                 . . . . . . . . . .
                 Thread-0 completed.
                 Installation Step 1 is completed.
                 Installation Step 2 is completed.
                 Installation Step 3 is completed.

 I'll see the 10 dots printed, and then the message that thread zero completed. Only after this, I will see the Installation
 steps, in the install thread, be executed. This join method, allowed my main thread, to wait here 10 dots created, until
 that other concurrent task completed, and before I proceeded with my dependent task. For good measure now, let's create
 a third thread, that will be responsible for interrupting the first thread, if it takes longer than 3 seconds.
 **/
//End-Part-8

        Thread thread = new Thread(() -> {
            String tname = Thread.currentThread().getName();
            System.out.println(tname + " should take 10 dots to run.");
            for (int i = 0; i < 10; i++) {
                System.out.print(". ");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("\nWhoops!! " + tname + " interrupted.");
                    Thread.currentThread().interrupt();                             // Created in Part-9
                    return;
                }
            }
            System.out.println("\n" + tname + " completed.");
        });

        Thread installThread = new Thread(() -> {
            try {
                for (int i = 0; i < 3; i++) {
                    Thread.sleep(250);
                    System.out.println("Installation Step " + (i + 1) + " is completed.");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "InstallThread");

        Thread threadMonitor = new Thread(() -> {
            long now = System.currentTimeMillis();                                  // Uncommented part via Part-8

            while (thread.isAlive()) {
                try {
                    Thread.sleep(1000);
                    if (System.currentTimeMillis() - now > 2000) {
                        thread.interrupt();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }                                                                       // Uncommented part via Part-8
        });

        System.out.println(thread.getName() + " starting");
        thread.start();
        threadMonitor.start();

        try {
            thread.join();
        } catch (
                InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (!thread.isInterrupted()) {
            installThread.start();
        } else {
            System.out.println("Previous thread was interrupted, " + installThread.getName() + " can't run.");
        }

//Part-9
/**
        I'll uncomment out the code I just commented out. I'll wrap this code, with a Thread instance, and make it part
 of the lambda expression of this thread. I'll call this thread, threadMonitor. I'm going to remove the println statement
 in this code, that says "waiting for thread to complete". I want to start this thread at the same time, as my original
 first thread. First, I'll just cut the statement and the thread.start above this. I'll paste that after this threadMonitor
 declaration. After the thread.start, I'll execute threadMonitor's start method. I'll run this.

                     Main thread running
                     Main thread paused for one second
                     Thread-0 starting
                     Thread-0 should take 10 dots to run.
                     . . . .
                     Whoops!! Thread-0 interrupted.
                     Installation Step 1 is completed.
                     Installation Step 2 is completed.
                     Installation Step 3 is completed.

 I get about 4 or 5 dots printed, before thread 0 is interrupted, by the concurrent threadMonitor thread. But something
 has gone wrong here, because the installation code ran, when it shouldn't have. The test on thread, using the isInterrupted
 method failed. What's happened here? If you do a bit of research, you'll find that Java recommends that any method that
 catches an interrupt exception, and is not prepared to deal with it immediately, should reassert the exception. They
 recommend reasserting it, rather than rethrowing it, because sometimes you can't rethrow it. The thread has to re-interrupt
 itself, or in other words, it has to call interrupt on itself. Scrolling back up to my original thread method, and looking
 at the interrupted exception block there,

                 catch (InterruptedException e) {
                     System.out.println("\nWhoops!! " + tname + " interrupted.");
                     Thread.currentThread().interrupt();
                     return;
                 }

 I need to reassert this interruption, in this code. I'll call interrupt on the current thread. I'll run this code again.

                     Main thread running
                     Main thread paused for one second
                     Thread-0 starting
                     Thread-0 should take 10 dots to run.
                     . . . .
                     Whoops!! Thread-0 interrupted.
                     Previous thread was interrupted, InstallThread can't run.

 I can see that the thread was interrupted, and this time, my code runs correctly, because now the interrupted method
 returns the correct result, that this thread was interrupted. That's a little confusing I know, but just remember, it's
 a good idea, to add this bit of code, "Thread.currentThread().interrupt()", and this should fix any kind of weirdness,
 around this status being reset during the thread process. For good measure, I'm going to change my threadMonitor method,
 from 2000 milliseconds, to 8000 milliseconds. Running this again,

                     Main thread running
                     Main thread paused for one second
                     Thread-0 starting
                     Thread-0 should take 10 dots to run.
                     . . . . . . . . . .
                     Whoops!! Thread-0 interrupted.
                     Installation Step 1 is completed.
                     Installation Step 2 is completed.
                     Installation Step 3 is completed.

 my thread should actually print its 10 dots successfully. Now, you can see that Thread 0 completed, and the installation
 steps were executed. In this lecture, we used the interrupt method, to interrupt a long running thread. I also demonstrated
 how to use join to make a dependent task, waiting until a previous asynchronous task finishes, before any thread will
 be launched. I'll have an opportunity to show this to you in upcoming lectures. In the next lecture, I want to give you
 a little thread challenge. After that, I'll be talking about some problems that can occur, if your concurrent threads
 start working with objects, in your process's heap memory.
 **/
//End-Part-9
    }
}
