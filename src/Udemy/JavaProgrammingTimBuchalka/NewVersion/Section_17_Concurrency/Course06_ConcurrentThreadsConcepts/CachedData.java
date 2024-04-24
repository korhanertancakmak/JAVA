package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_17_Concurrency.Course06_ConcurrentThreadsConcepts;

import java.util.concurrent.TimeUnit;

public class CachedData {

    //private boolean flag = false;

    private volatile boolean flag = false;

    public void toggleFlag() {
        flag = !flag;
    }

    public boolean isReady() {
        return flag;
    }

//Part-3
/**
        I'll set up a private boolean field called flag, and initialize it to false. Next, I'll have a method to toggle
 this flag. So flag will be set to not flag, getting the opposite value each time this method is called. I'll call my
 accessor method here, isReady. And that just returns the flag value. Next, in the main method,
**/
//End-Part-3

    public static void main(String[] args) {

        CachedData example = new CachedData();

        Thread writerThread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            example.toggleFlag();
            System.out.println("A. Flag set to " + example.isReady());
        });

        Thread readerThread = new Thread(() -> {
            while (!example.isReady()) {
                // Busy-wait until flag becomes true
            }
            System.out.println("B. Flag is " + example.isReady());
        });

        writerThread.start();
        readerThread.start();

//Part-4
/**
        I'll create an instance of my class first. Now, I'll set up a thread, that's responsible, after some kind of work,
 for setting this flag to true. I'll create a variable, named writer Thread, and construct it from a lambda. I need a try
 catch, because I'm going to call sleep. I'll sleep for one second, and I'll use Time Unit here to do that. And I'll do
 the usual catch here. After sleeping for 1 second, which is just a placeholder right now, for some kind of task that
 might need a little time. I'll toggle the flag, so the object is in a ready state. I'll print out the value of the flag
 at this point. My second thread is another task that needs to wait, until the first thread is ready. I'll call this one
 the reader thread. And while the ready flag is false, I'll just keep looping. Once the example instance is ready, I'll
 print my next statement. I'll start both threads here. Running this code

                         A. Flag set to true

 I see the first statement gets printed, indicating that my object's flag has been set to true. But notice, that my code
 in the second thread, the reader thread, seems to be stuck in that loop. I'll stop execution manually. So what's happened
 here? This code is a demonstration of something called Memory Inconsistency.

                                        Memory Consistency Errors

        To optimize the performance of threads, the operating system may read from heap variables, and "make a copy" of
 the value, in each thread's own storage cache. In other words, each thread has its own small and fast memory storage,
 that holds its own copy of a shared resource's value. This is a little bit different, from the concept of the thread's
 stack, which represents data visible only to its own operations. Because of this caching, one thread can modify a shared
 variable, but this "change might not be immediately reflected or visible", on the heap. Instead, it's first updated in
 the thread's local cache. When multiple threads are accessing the same data, it's possible for operations of one thread
 to not be visible, to the other thread immediately. This is because The operating system may not flush, or push, the
 first thread's changes to the heap, until the thread has finished executing. This is called Memory Inconsistency, and
 leads to errors, like we're seeing in this example.

        Looking at this code, let's talk about what's happening here. The writer thread sets the flag to true, after a
 delay of 1 second, using example.toggleFlag. Meanwhile, the reader thread is actively waiting in its busy-wait loop. The
 reader thread's local cache isn't getting updated with the modified value of the flag variable, after the writer thread
 toggled it. As a result, the reader thread is stuck in its loop indefinitely, waiting for the flag to become true, even
 though the writer thread has set it to true. To fix this situation, I can add one key word, a modifier, to my flag's
 declaration statement.

                                     private boolean flag = false;
                                                    to
                                     private volatile boolean flag = false;

 This modifier is volatile, and I'll add that, before the return type, and after the private modifier. Let's run the code
 now with this one change.

                     B. Flag is true
                     A. Flag set to true

 Now, you can see both statements are printed, and the application terminates naturally. What does this volatile key word
 really mean?

                                                    Volatile

        The volatile keyword is used as a modifier for class variables, or fields in other words. It's an indicator that
 this variable's value may be changed by multiple threads. This modifier ensures that the variable is always read from,
 and written to the main memory, rather than from any thread-specific caches. This provides memory consistency for this
 variable's value across threads. Volatile has limited usage though. It doesn't guarantee atomicity, so in the previous
 example, with our interleaving colorized threads, volatile wouldn't have helped. There are specific scenarios when you'll
 want to use volatile. The first is when a variable is used to track the state of a shared resource, such as a counter
 or a flag. Or, When a variable is used to communicate between threads. You should never use volatile when a variable is
 only used by a single thread. Or when a variable is used to store a large amount of data. In the next lecture, I'll be
 talking about another way to ensure your code is thread safe. This is called synchronization.
 **/
//End-Part-4
    }
}
