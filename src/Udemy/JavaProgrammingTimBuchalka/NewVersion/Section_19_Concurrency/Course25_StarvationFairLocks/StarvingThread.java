package CourseCodes.NewSections.Section_19_Concurrency.Course25_StarvationFairLocks;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class StarvingThread {

    private static final Lock lock = new ReentrantLock(true);

    public static void main(String[] args) {

        Callable<Boolean> thread = () -> {
            String threadName = Thread.currentThread().getName();
            int threadno = Integer.parseInt(threadName.replaceAll(".*-[1-9]*-.*-", ""));
            boolean keepGoing = true;
            while (keepGoing) {
                lock.lock();
                try {
                    System.out.printf("%d acquired the lock.%n", threadno);
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    System.out.printf("Shutting down %d%n", threadno);
                    Thread.currentThread().interrupt();
                    return false;
                } finally {
                    lock.unlock();
                }
            }

            return true;
        };

//Part-2
/**
        I want a main method, so psvm. This time, I'll use an explicit lock, and I'll set that up as a static field on
 this class. I'll make this private static final, with a reference type of Lock, it'll be named lock, and it will really
 be an instance of the re-entrant lock. In my main method I'll set up a Callable variable. I want this to be callable,
 because I'll be using it with an executor service, to fire off several concurrent threads at once. Remember, a callable
 returns a value, I'll return a Boolean in this case, and my variable will just be called thread. I'll start by getting
 the current thread's name. I really want to get the thread number from Java's generated name, so I'll set up a variable,
 which will use Integer.parseInt. I'll pass it the threadName, but I'll use a little regular expression code here, which
 will replace everything but the thread number with an empty string. A generated thread name consists of the format, first
 of the pool or group name, a dash, the pool or group number, another dash or hyphen, and then the literal text thread.
 I'll set a boolean, called keepGoing to be true. While this is true, this thread will continue to process. It will acquire
 the lock if it can, or block here. If it gets the lock, I'll simulate some time consuming work. First, I'll print which
 thread has acquired the lock. Thread number should be unique for this code. And I'll hang out for one second. I'll catch
 the interrupted exception. In this case, I'll print that this thread should be shutting down. I'll reassert the interruption.
 My executor is going to have to force these threads to shut down, because they are in this while loop, so this will have
 the thread shut down smoothly. I'll return false, and get out of the loop. Because I'm using an explicit lock, I need to
 be diligent, about releasing the lock, when the work is done. So I'll call lock.unlock. Finally, I'll just return true.
 I'm not really going to use these true or false values. Again, I'm setting it up as a Callable, rather than a Runnable
 that returns no value, because I plan to take advantage, of the invoke all method on an executor service. That method
 only takes a collection of Callables. Ok, so that's my thread code. Now I'll get an executor service.
 **/
//End-Part-2

        var executor = Executors.newFixedThreadPool(3);

        try {
            var futures = executor.invokeAll(List.of(thread, thread, thread), 10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        executor.shutdownNow();

//Part-4
/**
        In this case, I'll use a fixed thread pool, passing it 3 as then number of threads to run concurrently. I'll put
 a try here, because I know I'm going to need it. I'll use type inference for my futures variable, this is a list of futures
 that will come back when I call executor.invokeAll. I'm going to pass that a List, using the List.of static method, and
 simply pass three threads. invokeAll has an overloaded version that lets me specify a time out. This means, after the
 time I specify, this executor's going to timeout, waiting for these threads to complete. This is important because these
 threads are in an infinite loop, and the invokeAll method, without a time out, would wait for them to complete normally,
 so it would wait indefinitely if I didn't do this. I'll catch any InterruptedException that the invokeAll method might
 throw. And rethrow it as an unchecked exception. Finally, I can't forget to call the shutdown method on executor. In this
 case I'm going to use shutdownNow. I'm not sure I covered this method. The difference between shutdown and shutdownNow,
 is that the shutdown method attempts an orderly shutdown, meaning it'll wait for your threads to finish processing. In
 this case, my threads aren't going to finish normally, so I have two options. I could have used the array of futures,
 and manually cancelled each thread, by calling cancel on the future. But the shutdownNow method will do that for me.
 It attempts to stop all actively running threads. Let's see what happens. I'll run this code.

             3 acquired the lock.
             3 acquired the lock.
             1 acquired the lock.
             1 acquired the lock.
             1 acquired the lock.
             1 acquired the lock.
             1 acquired the lock.
             1 acquired the lock.
             1 acquired the lock.
             1 acquired the lock.
             Shutting down 1
             2 acquired the lock.
             Shutting down 2
             3 acquired the lock.
             Shutting down 3

 We have to watch it for 10 seconds while it's processing. Each thread holds the lock for a full second. You can see that
 it prints the thread number, and that the thread has acquired a lock. I need to run this a couple of times, so bear with
 me, as we wait for 10 seconds each time. I hope you start to see a pattern here. The thread that manages to acquire the
 lock, becomes a greedy thread, meaning it's going to monopolize the shared resource. This is because the other threads
 are politely waiting, but the greedy thread has a better chance of re-acquiring the lock. That hardly seems fair to the
 other threads, but in Java, locking isn't implicitly fair. I could try to mess around with the priority of the threads,
 but remember, priority is just a suggestion. So what are my options, to try to get the threads to have a more even
 distribution of access, to the resource? With an explicit lock, I can actually change the fairness policy of the locking,
 by passing true, to the constructor of the ReentrantLock instance.

                                 private static final Lock lock = new ReentrantLock();
                                                          to
                                 private static final Lock lock = new ReentrantLock(true);

 I'll do that next, putting true as the constructor argument. I'll re-run my code.

             1 acquired the lock.
             3 acquired the lock.
             2 acquired the lock.
             1 acquired the lock.
             3 acquired the lock.
             2 acquired the lock.
             1 acquired the lock.
             3 acquired the lock.
             2 acquired the lock.
             1 acquired the lock.
             Shutting down 1
             3 acquired the lock.
             Shutting down 3
             2 acquired the lock.
             Shutting down 2

 Now, you can see that each thread is acquiring the lock, and it's a pretty even distribution over the 10 seconds that
 we try this. This is another advantage of explicit locking, meaning using a Lock implementation like the Reentrant Lock.
 You can't set the fairness policy manually of a monitor lock, meaning the lock used when you use the synchronized key
 word. Let's talk about fair locks for a minute.

                                                   Fair Lock

        A fair lock guarantees that all threads waiting to acquire the lock will be given an equal chance of acquiring it.
 This is in contrast to an unfair lock, which doesn't make any guarantees. Remember, the monitor lock is unfair. A Reentrant
 lock can be fair or unfair, which I just demonstrated. When a thread requests access to a fair lock, it gets added to a
 FIFO queue. The lock is then granted to the thread at the head of the queue, or the first in. There are some benefits
 to using a fair lock:

    * Fair locks can help to prevent thread starvation, as we just saw.
    * They may improve the overall performance of a system, by ensuring that all threads get a chance of accessing resources.
    * They can make a system more predictable and easier to debug.

 On the other hand, there are a couple of drawbacks:

    * Fair locks might have a negative impact on performance, especially in systems where threads are frequently competing
    for locks.
    * Fair locks can be more difficult to implement.

 In this lecture, I gave you a demonstration of a starvation situation, where two threads were usually unable to acquire
 a lock on the resource, because the one thread was able to continually acquire it. To prevent this situation, I showed
 you how to use a fair lock, which puts waiting threads in a queue, and then allows acquisition of a lock in the order
 in which a thread was queued. We've spent a lot of time in this section, looking at many of the problems you'll run into
 with concurrency and parallelism. There's still a couple of things to cover, and I'll try to finish up with these items.
 **/
//End-Part-4
    }
}
