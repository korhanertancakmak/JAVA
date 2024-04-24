package CourseCodes.NewSections.Section_19_Concurrency.Course17_WorkStealingPool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

class RecursiveSumTask extends RecursiveTask<Long> {

    private final long[] numbers;
    private final int start;
    private final int end;
    private final int division;

    public RecursiveSumTask(long[] numbers, int start, int end, int division) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
        this.division = division;
    }

//Part-6
/**
        RecursiveTask is generic, and I want it to return a long, the sum of all my numbers in my array. This code doesn't
 compile, because I have to override the compute method, so I'll use IntelliJ to help me do that. Before I code this method,
 I'll add a couple of fields. I'll set up an array of numbers. I'll make this an array of long, so it matches what I had
 previously. I'll include a field for a starting index, and a field for an ending index. Lastly, I want to divide up the
 tasks, so if I pass two, it will create two tasks, if I pass four, it will create four tasks, and so on. I'll generate
 a constructor. Now, it's time to talk about the compute method.
**/
//End-Part-6

    @Override
    protected Long compute() {

        if ((end - start) <= (numbers.length / division)) {
            System.out.println(start + " : " + end);
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += numbers[i];
            }
            return sum;
        } else {
            int mid = (start + end) / 2;
            RecursiveSumTask leftTask = new RecursiveSumTask(numbers, start, mid, division);
            RecursiveSumTask rightTask = new RecursiveSumTask(numbers, mid, end, division);
            leftTask.fork();
            rightTask.fork();
            return leftTask.join() + rightTask.join();
        }
    }

//Part-7
/**
        I'll first remove the default statement, return null. If the end index - the start index, is less than the array
 length divided by the number of tasks, that means the array length is smaller than the size we want to split by, so it
 will process it here. I'll print the start and end index. I'll initialize a long sum variable. I'll loop through the
 numbers array, starting with the start index up to the end index. I'll add each number to the sum. And I'll return the
 sum. If we weren't dividing the array up, or if I passed 1 as the value for division, this would be the only code I'd
 need. But I want to split up the work, so I'll include an else statement. I'll first get the mid point, between the start
 and end index. I'll create a recursive task for the first part of the elements, which I'll call left task. I'll create
 a second recursive task, using the second half of the elements. The fork method will execute this task, in the same pool
 the current task is running in, so I'll kick off the left Task, and I'll do the same for the right task. Finally I can
 call join on each of those tasks. The join method here, on recursive task, is similar to calling get on a Future instance.
 It returns the result of the task's computation, when it's done. It doesn't however, throw a checked exception. In this
 case I'll add the result of these two tasks. This method will continue to spawn two recursive tasks, until the size of
 the elements is within the range, for each division. Now, let's actually run this.
 **/
//End-Part-7
}

public class Main {

    public static void main(String[] args) throws Exception {

        int numbersLength = 100_000;
        long[] numbers = new Random().longs(numbersLength, 1, numbersLength).toArray();

        long sum = Arrays.stream(numbers).sum();
        System.out.println("sum = " + sum);

//Part-1
/**
        For this lecture, I'm going to jump right into a bit of code, to demonstrate one more factory method, on the Executors
 class. I've created the Main class and main method. I'll set up a variable to set the numbers to be processed, so in this
 case I plan to process 100 thousand numbers. I'll generate an array of randomly generated longs, using the numbersLength
 to limit the array to 100 thousand, as well as limit the numbers to be between 1 and 100 thousand. I'll quickly use a
 stream, to give me the sum of those 100 thousand numbers. I can get a stream from the Arrays class's stream method, passing
 it my array of numbers, and just call the terminal operation sum there. I want the result to be a long. I'll print that out.
 Running this code,

                sum = 4991309719

 I'll get a value, that will be different, each time I run it because of the random values used. I included this here,
 because I want to verify the sum I'll get, after I split up this summation operation, into multiple sub tasks. Now, you'll
 agree, whether I sum these numbers in a single array of 100 thousand records, or in 10 arrays of 10000 records, and summing
 those 10 results, the final sum should be the same. Splitting the summation task into tasks operating on smaller sets
 of data, and performing them in parallel, might not be the best case scenario for parallel processing, but it's an easy
 one to understand I think, as we look at this next type of executor service. This is the work stealing thread pool.
 **/
//End-Part-1

        /*ExecutorService threadPool = Executors.newWorkStealingPool(4);*/

//Part-2
/**
        I'll Create a work stealing pool. Again, I just use the Executors class factory method to do this. The number I'm
 passing to this pool is not technically the number of threads, that will be executed. It's the level of parallelism I
 want, and you'd usually set this to the number of CPUs available on your system, or less. So what's a work stealing pool?

                                        Work Stealing Thread Pool

 The work stealing thread pool is used for parallelism, and concurrent execution of tasks. Each worker thread has
 its own task queue. When a worker thread finishes its own tasks, and its queue is empty, it can "steal" tasks from the
 back of other worker threads' queues. This helps to balance the workload among threads, reduces idle time, and optimizes
 resource usage. In the case of my code here, I'm going to demonstrate how you might split up the single task of adding
 100 thousand numbers, into smaller tasks. I'll use my work stealing pool to distribute the work.
 **/
//End-Part-2

        /*List<Callable<Long>> tasks = new ArrayList<>();

        int taskNo = 10;
        int splitCount = numbersLength / taskNo;
        for (int i = 0; i < taskNo; i++) {
            int start = i * splitCount;
            int end = start + splitCount;
            tasks.add(() -> {
                long tasksum = 0;
                for (int j = start; j < end; j++) {
                    tasksum += (long) numbers[j];
                }
                return tasksum;
            });
        }

        List<Future<Long>> futures = threadPool.invokeAll(tasks);

        long taskSum = 0;
        for (Future<Long> future : futures) {
            taskSum += future.get();
        }

        System.out.println("Thread Pool Sum = " + taskSum);

        threadPool.shutdown();

        System.out.println(threadPool.getClass().getName());*/

//Part-3
/**
        I'll first set up a List of Callable tasks, each will return a long value. I'll use the Long wrapper type, since
 we can't use primitives with generics, and I'll just instantiate that as a new arraylist. I'll create 10 sub tasks. Each
 sub task will sum a 10th of the randomized array. I'll set up a variable for the number of tasks, and set it to 10. Each
 array will be the total number of elements divided by the task number, so in this case it'll be 10 thousand. I'll loop
 from 1 to 10. I'll get the starting index, which is i * the element count each task will process, so split count. I'll
 get the ending index which is just the starting index + the split count. I'll add a Callable lambda to my tasks array
 list. In the lambda, I'll sum up the numbers the old fashioned way, with a simple for loop, and use the addition operator
 to keep the running sum. I'll return that sum from the task, so the calling code can get it. And that's it, for the creation
 of these 10 tasks. I'll submit the tasks to the thread pool, and wait for all of them to complete. I want a new sum variable,
 and I'll loop through the future results, Adding each future result to the task sum variable. Finally, I'll print the
 sum of the tasks out. And, I can't forget to shutdown the thread pool. I'm getting an error from the invokeAll and get
 methods, so I'll add a throws exception to the main method. If I run this,

                 sum = 4991309719
                 Thread Pool Sum = 4991309719

 I'll get the same sum, as I did when using a stream, so that's good. I'll add one statement to this code, to print out
 the actual class name of this work stealing pool. I can include this after the shutdown, so I'll print the full class
 name of this pool's instance. Running this now,

                 sum = 4991309719
                 Thread Pool Sum = 4991309719
                 java.util.concurrent.ForkJoinPool

 I see that the thread pool is really something called a ForkJoinPool.

                                                The ForkJoinPool

        The ForkJoinPool class is Java's implementation of the Work Stealing Pool. It's based on the fork-join, or divide
 and conquer algorithm of computing. This algorithm

    * breaks down a complex task into smaller subtasks,
    * processes them independently and in parallel,
    * and then combines the results to solve the original problem.

 I'll bring up the API documentation for this class, just to show you a couple of interesting points about it. The first
 thing we're told is that it's an executor service, for running ForkJoinTasks. Now, my code never did anything with a
 ForkJoinTask. But if I continue reading, I can see that it's also an entry point for submissions, from non-ForkJoinTask
 clients, which is the way I just executed it. It then tells me this pool is different from others because it steals work,
 which I've discussed already. This means a thread in this pool won't sit idle, if another thread has work in its task
 queue, that this thread can do instead. More interestingly, I want you to focus on this statement. "A static common pool
 is available and appropriate for most applications." And then "Using the common pool normally reduces resource usage,
 because its threads are slowly reclaimed during periods of non-use", so it's recommended to use this common pool in many
 circumstances. It's actually the pool that's used when we use parallel streams, which I'll be talking about next. Before
 we go back to the code, first I want to look at the methods, available on this class. There's some interesting ones here,
 and I want to encourage you, to explore learning about some of these on your own. I'll scroll down to the submit methods,
 and show you the several overloaded versions listed here.

             Modifier and Type          Method                          Description

             ForkJoinTask<?>            submit(Runnable task)           Submits a Runnable task for execution and returns a
                                                                        Future representing that task.

             <T> ForkJoinTask<T>        submit(Runnable task, T result) Submits a Runnable task for execution and returns a
                                                                        Future representing that task.

             <T> ForkJoinTask<T>        submit(Callable<T> task)        Submits a value-returning task for execution and
                                                                        returns a Future representing the pending result of
                                                                        the task.

             <T> ForkJoinTask<T>        submit(ForkJoinTask<T> task)    Submits a ForkJoinTask for execution.

 Notice, we've got submit methods, that takes a Runnable, and a Callable, and there's a different one for this class, that
 takes A ForkJoinTask. I'll click on that class, ForkJoinTask.

        A ForkJoinTask is an abstract base class, it's thread-like but much lighter weight. Then we're told, huge numbers
 of tasks and sub tasks, may be hosted by a small number of actual threads in a ForkJoinPool. That sounds promising. Notice
 the 3 direct known subclasses, a CountedCompleter, a RecursiveAction and a RecursiveTask. Let's get back to the code,
 and look at the ForkJoinPool's common pool.
 **/
//End-Part-3

        /*ForkJoinPool threadPool = (ForkJoinPool) Executors.newWorkStealingPool(4);

        List<Callable<Long>> tasks = new ArrayList<>();

        int taskNo = 10;
        int splitCount = numbersLength / taskNo;
        for (int i = 0; i < taskNo; i++) {
            int start = i * splitCount;
            int end = start + splitCount;
            tasks.add(() -> {
                long tasksum = 0;
                for (int j = start; j < end; j++) {
                    tasksum += (long) numbers[j];
                }
                return tasksum;
            });
        }

        List<Future<Long>> futures = threadPool.invokeAll(tasks);

        System.out.println("Parallelism = " + threadPool.getParallelism());
        System.out.println("Pool size = " + threadPool.getPoolSize());
        System.out.println("Steal count = " + threadPool.getStealCount());

        long taskSum = 0;
        for (Future<Long> future : futures) {
            taskSum += future.get();
        }

        System.out.println("Thread Pool Sum = " + taskSum);

        threadPool.shutdown();

        System.out.println(threadPool.getClass().getName());*/

//Part-4
/**
        I'm going to change the type of my threadPool variable type, to be a ForkJoinPool. I need to cast the result of
 the new Work Stealing Pool to a ForkJoinPool as well. I'm doing this because I want to use a couple of the methods that
 are specific to this class. I'll start by printing out some information, after I've invoked my tasks. I can print the
 level of parallelism. I can print the pool size. And I'll print the steal count. I'll run this.

                 sum = 4995833400
                 Parallelism = 4
                 Pool size = 4
                 Steal count = 10
                 Thread Pool Sum = 4995833400
                 java.util.concurrent.ForkJoinPool

 You can see that the parallelism is 4, which matches what I set up, and the pool size is the same. The steal count is
 the same as the number of tasks I submitted. If I rerun this a couple of times, I always get the same results.
 Now, I'll switch to the common pool.
 **/
//End-Part-4

        ForkJoinPool threadPool = ForkJoinPool.commonPool();

        List<Callable<Long>> tasks = new ArrayList<>();

        int taskNo = 10;
        int splitCount = numbersLength / taskNo;
        for (int i = 0; i < taskNo; i++) {
            int start = i * splitCount;
            int end = start + splitCount;
            tasks.add(() -> {
                long tasksum = 0;
                for (int j = start; j < end; j++) {
                    tasksum += (long) numbers[j];
                }
                return tasksum;
            });
        }

        List<Future<Long>> futures = threadPool.invokeAll(tasks);

        System.out.println("CPUs: " + Runtime.getRuntime().availableProcessors());
        System.out.println("Parallelism = " + threadPool.getParallelism());
        System.out.println("Pool size = " + threadPool.getPoolSize());
        System.out.println("Steal count = " + threadPool.getStealCount());

        long taskSum = 0;
        for (Future<Long> future : futures) {
            taskSum += future.get();
        }

        System.out.println("Thread Pool Sum = " + taskSum);

        /*threadPool.shutdown();

        System.out.println(threadPool.getClass().getName());*/

//Part-5
/**
        I'll remove everything to the right of the equals sign, and replace it with ForkJoinPool.commonPool. I don't need
 to cast the result of that, because it returns a ForkJoinTool type. If I run this code,

                 sum = 5000894985
                 Parallelism = 19
                 Pool size = 5
                 Steal count = 10
                 Thread Pool Sum = 5000894985
                 java.util.concurrent.ForkJoinPool

 it works just the same as before. Notice now, that the numbers are different. The Parallelism for my system, is 19. The
 Pool size is 5 when these statements were executed, and the steal count is 10. I'll run this a couple of times, and you'll
 see the pool size, and steal count may change by one or 2 each time. I can get the number of CPUs on my system, and I'll
 do that now. I haven't really talked about the Runtime class, but it can give us information about the running system,
 including the number of availableProcessors. I'll rerun this,

                 sum = 4985387747
                 CPUs: 20
                 Parallelism = 19
                 Pool size = 3
                 Steal count = 10
                 Thread Pool Sum = 4985387747
                 java.util.concurrent.ForkJoinPool

 and I can see that my system has 20 CPUs. In general, the common pool will use as many CPUs as possible, but won't typically
 use all of them, and we see that here, with 19 as the parallelism count. The common pool starts with a small number of
 threads in its pool, and it will add more threads as needed. Threads will also be removed, if they're idle for too long.
 The steal count returns an estimate, of the total number of completed tasks, that were executed by a thread, other than
 their submitter. When parallelism is higher, there's less work stealing.

        Next, I want to show you how to use a RecursiveTask. I'll set up a class, in my Main java source file, that extends
 the RecursiveTask.
 **/
//End-Part-5

        //RecursiveTask<Long> task = new RecursiveSumTask(numbers, 0, numbersLength, 2);
        RecursiveTask<Long> task = new RecursiveSumTask(numbers, 0, numbersLength, 4);
        long forkJoinSum = threadPool.invoke(task);
        System.out.println("RecursiveTask sum is: " + forkJoinSum);

        threadPool.shutdown();

        System.out.println(threadPool.getClass().getName());

//Part-8
/**
        I'll insert code, before the call to the shutdown method, on the thread pool. I'll create a new RecursiveTask
 variable, called task and instantiate a new task. I'll pass it my numbers array, the starting index, zero, and the ending
 index, the full size of the array, and I'll pass 2, as the division, or the number of tasks I want this to run in. I'll
 get the sum, calling the variable, fork join sum, and assign that what I get back from calling the invoke method on thread
 pool, passing it my recursive task. And I'll print that sum out. If I run this,

                 sum = 4992392550
                 CPUs: 20
                 Parallelism = 19
                 Pool size = 5
                 Steal count = 10
                 Thread Pool Sum = 4992392550
                 50000 : 100000
                 0 : 50000
                 RecursiveTask sum is: 4992392550
                 java.util.concurrent.ForkJoinPool

 You'll see the results of the previous code, but then you'll see that my recursive task, split the array up, into 2 tasks,
 as I requested, And I get the same result, the same sum of numbers for all three methods, so that's good. I'll dial that
 number up, from 2 to 4. And run it again.

                 sum = 5001130496
                 CPUs: 20
                 Parallelism = 19
                 Pool size = 4
                 Steal count = 10
                 Thread Pool Sum = 5001130496
                 25000 : 50000
                 50000 : 75000
                 0 : 25000
                 75000 : 100000
                 RecursiveTask sum is: 5001130496
                 java.util.concurrent.ForkJoinPool

 Now you can see that the array was split 4 ways, with the same result as the other methods. I'll change it to 8 next, and
 run it again. Now you can see that each task is processing 12 thousand 500 records. Ok, so I hope this helped you see how
 the tasks are recursively split into smaller tasks. The ForkJoinPool uses parallelism to divide up the work.

        There are two main types of parallelism:

    * Task parallelism divides a program into smaller tasks that get executed concurrently. Each task can run on a separate
    thread or processor core.
    * Data parallelism processes different parts of the same data concurrently.

 In the example I've shown here, I've really been doing data parallelism. It's more common for data parallelism, to take
 advantage of parallel streams. I'll be talking about parallel streams in the next lecture.
 **/
//End-Part-8

    }
}
