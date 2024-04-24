package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_17_Concurrency.Course04_ThreadChallenge;

//Part-1
/**
         In this challenge, you'll create and manage threads, using two approaches
    * by subclassing the Thread class,
    * by implementing the Runnable interface.

        Your task is to create two threads. You should make one thread subclass the java.lang.Thread class. The other
 should be created using a Runnable, which you can pass to the Thread constructor. This can be any class that implements
 Runnable, or a lambda expression. Each thread should have a run method. The first thread's code should print 5 even numbers,
 and the second thread should print 5 odd numbers. You should execute them asynchronously, calling the start method on
 each, in two consecutive statements, in your main code. Have your main method, after these threads run a few seconds,
 interrupt one or both of these threads. Your application shouldn't crash, meaning your threads should handle an interrupted
 exception.
**/
//End-Part-1

class OddThread extends Thread {

    @Override
    public void run() {

        for (int i = 1; i <= 10; i += 2) {
            System.out.println("OddThread: " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("OddThread interrupted!");
                break;
            }
        }
    }
}

//Part-2
/**
        I've created the usual Main class and main method. Before I start with the main method, I'll create a class that
 extends the Thread class. I'll just put this class in the Main.java source file. I'll call this class, OddThread, and
 it'll extend the Thread class. In this class, I'll implement the run method to perform a task. In this case, I'll use
 IntelliJ's code generation tool, to override the run method. I'll remove that super.run call inside the run method. I'll
 start off with a for loop, that starts at one, printing every other number, the odd numbers, that are less than or equal
 to ten. In the loop, I'll print OddThread, and the current value of i. I want to call Thread.sleep, so I need a try
 block here. I'll make the thread sleep for one second every iteration. I'll catch the Interrupted Exception that the
 sleep method throws, and I'll print that the thread was interrupted. Finally, I'll break out of the loop. The next class
 will implement the Runnable class, and I'll call this class, Even Runnable.
 **/
//End-Part-2

class EvenRunnable implements Runnable {

    @Override
    public void run() {

        for (int i = 2; i <= 10; i += 2) {
            System.out.println("EvenRunnable: " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("EvenRunnable interrupted!");
                break;
            }
        }
    }
}

//Part-3
/**
        Notice how IntelliJ is giving us an error. If I hover over that, it's telling me that I need to implement the run
 method. Instead of using IntelliJ's override generation tool, in this case, I'll just copy the run method from the Odd
 Thread class, and paste it in here. I'll change the initialization of i, in the for loop, setting it to 2. I'll also
 change both println statements, to say Even Runnable, instead of Odd Thread. In the main method, I need to create my two
 thread instances.
 **/
//End-Part-3

public class Main {

    public static void main(String[] args) {

        /*OddThread oddThread = new OddThread();
        Thread evenThread = new Thread(new EvenRunnable());
        oddThread.start();
        evenThread.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        oddThread.interrupt();*/

//Part-4
/**
        So first, I'll set up the odd thread, and that's just going to be a new instance of my OddThread class. For the
 EvenRunnable thread, this is similar, but in this case, I pass a new instance of EvenRunnable, to the thread's constructor.
 I'll start odd thread first. and then call start on the even thread instance. Next, I want to put the main thread to
 sleep, before I interrupt one of the threads. This lets them do a little work first. As usual, I need a try block. I'll
 put the thread to sleep for 2 seconds. Next, I need to catch the interrupted exception, and then I'll just print out the
 stack trace. I'm going to interrupt the odd thread, which means at some point it'll stop executing, but Even Runnable
 should continue running. Let's run this code.

                     EvenRunnable: 2
                     OddThread: 1
                     EvenRunnable: 4
                     OddThread: 3
                     OddThread interrupted!
                     EvenRunnable: 6
                     EvenRunnable: 8
                     EvenRunnable: 10

 As you can see, the program didn't crash when Odd Thread was interrupted, but rather it handled the exception, printed
 a message, while Even Runnable kept executing. Now let's look at this EvenRunnable class a minute. It includes no additional
 code, and really kind of adds no extra value, by it's existence. What I mean by that is, because it's only a run method,
 it's a perfect candidate to be replaced with a lambda expression. Let's do that.
 **/
//End-Part-4

        Runnable runnable = () -> {
            for (int i = 2; i <= 10; i += 2) {
                System.out.println("EvenRunnable: " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("EvenRunnable interrupted!");
                    break;
                }
            }
        };

        OddThread oddThread = new OddThread();
        Thread evenThread = new Thread(runnable);
        oddThread.start();
        evenThread.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        oddThread.interrupt();

//Part-5
/**
        I'll call this lambda runnable. Now, I'll copy the for loop from the run method in Even Runnable, and paste it
 inside this lambda expression. I'll also change the instance passed to the Thread constructor to runnable, so that this
 lambda gets executed. I'll run this.

                     EvenRunnable: 2
                     OddThread: 1
                     OddThread: 3
                     EvenRunnable: 4
                     EvenRunnable: 6
                     OddThread interrupted!
                     EvenRunnable: 8
                     EvenRunnable: 10

 And we get the same results. Whether you use a lambda or a class that implements Runnable is a matter of style. If you
 need additional helper methods or some state for your running method, then obviously using a class makes more sense.
 Next, I'll be talking about Multi threading, and the types of memory that can be accessed by concurrent threads.
 **/
//End-Part-5

    }
}
