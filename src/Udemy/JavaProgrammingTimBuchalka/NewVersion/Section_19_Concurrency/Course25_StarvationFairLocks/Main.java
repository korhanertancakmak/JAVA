package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_19_Concurrency.Course25_StarvationFairLocks;

import java.io.File;

//Part-1
/**                                                 Starvation

        Ok, so we've covered deadlocks and live locks. In this lecture, I'll briefly look at the final scenario, which is
 thread starvation. You can probably guess what this one means. Starvation occurs when one thread is unable to obtain the
 resource it needs, to execute. This is usually caused by other concurrent threads being greedy. Some threads are able to
 make progress, but others can't. This means your application may still keep running, and some of the work is getting done,
 but not all of it. Let's take a look at this in some code. I'll create a new class, and I'll call it StarvingThread.
**/
//End-Part-1

public class Main {

    public static void main(String[] args) {

        File resourceA = new File("inputData.csv");
        File resourceB = new File("outputData.json");

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