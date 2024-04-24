package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_17_Concurrency.Course14_AdditionalThreadPools;

import java.util.List;
import java.util.concurrent.*;

//Part-1
/**
        In the last lecture, I introduced you to the first of five Thread Pool Executor Services that Java offers, this
 was the FixedThreadPool. This service created a fixed number of threads in its pool, and never increased or decreased
 them. There was no dependency on the number of tasks in other words. I'm going to again use the Executors project, to
 continue with this discussion, introducing you to some other thread pools. I've got the code open to the Main class. In
 this class, I'll add a new method.
**/
//End-Part-1

class ColorThreadFactory implements ThreadFactory {

    private String threadName;

    private int colorValue = 1;

    public ColorThreadFactory(ThreadColor color) {
        this.threadName = color.name();
    }

    public ColorThreadFactory() {
    }

    @Override
    public Thread newThread(Runnable r) {

        Thread thread = new Thread(r);
        String name = threadName;
        if (name == null) {
            name = ThreadColor.values()[colorValue].name();
        }

        if (++colorValue > (ThreadColor.values().length - 1)) {
            colorValue = 1;
        }
        thread.setName(name);
        return thread;
    }
}

public class Main {

    public static void main(String[] args) {

        var multiExecutor = Executors.newCachedThreadPool();
        /*try {
            multiExecutor.execute(() -> Main.sum(1, 10, 1, "red"));
            multiExecutor.execute(() -> Main.sum(10, 100, 10, "blue"));
            multiExecutor.execute(() -> Main.sum(2, 20, 2, "green"));
        } finally {
            multiExecutor.shutdown();
        }*/

//Part-4
/**
        I'll use type inference, and again call my variable multi Executor, but this time I'll call new cached thread pool,
 with no arguments at all. I'll now wrap this code in a try block. I can execute a task as a lambda expression, using the
 execute method. In this case, I'll pass parameters to the lambda, rather than use a method reference. So first, I want
 to sum all the numbers from 1 to 10, incrementing by 1 each time, and print that in red. For the next task, I'll start
 at 10, and go to 100, incrementing by 10, so this task will sum 10, 20, 30 and so on, and print that sum in blue. Lastly,
 I'll sum the even numbers between 2 and 20, printing that amount in green. I'll include a finally clause, which is considered
 best practice. This clause will always shutdown the executor. Now that you understand thread pools a little bit, you might
 understand why the shutdown method is necessary. A thread pool will stay in existence until something tells it to stop
 running, and that's exactly what the shutdown method does. Ok, so I'll run this code.

             pool-1-thread-2, blue 550
             pool-1-thread-3, green 110
             pool-1-thread-1, red 55

 I'll get three statements back in no specific order. Red will give me a sum of 55, green will give me 110, and blue will
 be 550. You can see I have three distinct thread names, so this pool had three threads executing, even though I never
 specified a thread count for this executor service.
 **/
//End-Part-4

        /*try {
            multiExecutor.execute(() -> Main.sum(1, 10, 1, "red"));
            multiExecutor.execute(() -> Main.sum(10, 100, 10, "blue"));
            multiExecutor.execute(() -> Main.sum(2, 20, 2, "green"));

            multiExecutor.execute(() -> Main.sum(1, 10, 1, "yellow"));
            multiExecutor.execute(() -> Main.sum(10, 100, 10, "cyan"));
            multiExecutor.execute(() -> Main.sum(2, 20, 2, "purple"));
        } finally {
            multiExecutor.shutdown();
        }*/

//Part-5
/**
        I'll copy my three execute statements, and paste a copy right below the last one here. I'll change the colors for
 each of these new statements, but just keep the lambda the same. I'll change red to yellow, blue to cyan, and green to
 purple. I'll run this code again.

             pool-1-thread-6, purple 110
             pool-1-thread-5, cyan 550
             pool-1-thread-3, green 110
             pool-1-thread-4, yellow 55
             pool-1-thread-2, blue 550
             pool-1-thread-1, red 55

 Now I should see six statements, in different colors, and again there's no real order. Notice the thread names, they're
 all still unique, so my thread pool is growing, as my tasks increase.
 **/
//End-Part-5

        /*try {
            multiExecutor.execute(() -> Main.sum(1, 10, 1, "red"));
            multiExecutor.execute(() -> Main.sum(10, 100, 10, "blue"));
            multiExecutor.execute(() -> Main.sum(2, 20, 2, "green"));

            multiExecutor.execute(() -> Main.sum(1, 10, 1, "yellow"));
            multiExecutor.execute(() -> Main.sum(10, 100, 10, "cyan"));
            multiExecutor.execute(() -> Main.sum(2, 20, 2, "purple"));

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Next Tasks Will get executed");
            for (var color : new String[]{"red", "blue", "green", "yellow"}) {
                multiExecutor.execute(() -> Main.sum(1, 10, 1, color));
            }

        } finally {
            multiExecutor.shutdown();
        }*/

//Part-6
/**
        Before I shut down, I'll hang out for a second, then I'll execute a few more tasks. To do this, I'll call sleep
 before the shutdown method. I'll do it with the TimeUnit seconds here. I'll sleep for one second. I'll print out a message
 that the new task will be executed. I'll use an enhanced for loop, to loop through some colors, while I call the sum method.
 If I run this code,

                 pool-1-thread-1, red 55
                 pool-1-thread-5, cyan 550
                 pool-1-thread-2, blue 550
                 pool-1-thread-4, yellow 55
                 pool-1-thread-6, purple 110
                 pool-1-thread-3, green 110
                 Next Tasks Will get executed
                 pool-1-thread-1, green 55
                 pool-1-thread-3, red 55
                 pool-1-thread-4, yellow 55
                 pool-1-thread-2, blue 55

 My first six tasks will complete, I'll see a statement that says, Next Four Tasks Will get executed, Then I'll see the
 statements for the final four. Notice the thread names used in the second set of four tasks. The pool "appears" to be
 reusing existing threads, at this point in the pool. I say appears, because I don't really know for sure, since this is
 all managed for me, behind the scenes. I'll talk about how the cached thread pool creates and destroys threads in just
 a minute.
 **/
//End-Part-6

        /*try {
            multiExecutor.execute(() -> Main.sum(1, 10, 1, "red"));
            multiExecutor.execute(() -> Main.sum(10, 100, 10, "blue"));
            multiExecutor.execute(() -> Main.sum(2, 20, 2, "green"));

            multiExecutor.execute(() -> Main.sum(1, 10, 1, "yellow"));
            multiExecutor.execute(() -> Main.sum(10, 100, 10, "cyan"));
            multiExecutor.execute(() -> Main.sum(2, 20, 2, "purple"));

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Next Tasks Will get executed");
            for (var color : new String[]{"red", "blue", "green", "yellow", "purple", "cyan", "black"}) {
                multiExecutor.execute(() -> Main.sum(1, 10, 1, color));
            }

        } finally {
            multiExecutor.shutdown();
        }*/

//Part-7
/**
        First though, I'll add three more colors to this array. I'll rerun this.

                 pool-1-thread-4, yellow 55
                 pool-1-thread-3, green 110
                 pool-1-thread-6, purple 110
                 pool-1-thread-5, cyan 550
                 pool-1-thread-1, red 55
                 pool-1-thread-2, blue 550
                 Next Tasks Will get executed
                 pool-1-thread-3, yellow 55
                 pool-1-thread-7, black 55
                 pool-1-thread-4, cyan 55
                 pool-1-thread-2, blue 55
                 pool-1-thread-1, purple 55
                 pool-1-thread-6, red 55
                 pool-1-thread-5, green 55

 You'll notice that I now have 7 uniquely named threads, so again, this thread pool is growing. I'm going to come up a
 little bit here in my code, to the first statement in the main method. I'll control click on the Executors method there,
 so on the method newCachedThreadPool. You can see this is creating something called a new ThreadPoolExecutor. What I want
 you to see though, is the arguments, and the parameter names, which IntelliJ is showing as inlay hints.

    * There's a "corePoolSize", so in this case, it starts at 0. This means the pool won't create threads when it's instantiated.
    The maximum pool size is the MAX VALUE of Integer, and we know by now that's 2.14 billion something. That's a lot of
    threads.
    * Next, there's a "keepAliveTime" as well as TimeUnit, and here, we can see it's 60 seconds. This means, after 60 seconds,
    cached threads will be dropped.

 Getting back to the Main class's main method,
 **/
//End-Part-7

        /*try {
            multiExecutor.submit(() -> Main.sum(1, 10, 1, "red"));
            multiExecutor.submit(() -> Main.sum(10, 100, 10, "blue"));
            multiExecutor.submit(() -> Main.sum(2, 20, 2, "green"));

        } finally {
            multiExecutor.shutdown();
        }*/

//Part-8
/**
        I'll delete this last bit of code I'm also going to delete the last three of the six statements above this. In
 addition to the execute method, which I've been using, these classes have a submit method. I'll change my code, to call
 submit, instead of execute, in each of these cases. This code compiles, and I can run it.

             pool-1-thread-1, red 55
             pool-1-thread-2, red 550
             pool-1-thread-3, red 110

 I get three threads completing, just as if I had used the execute method. So what's the difference here? For this particular
 example, execute and submit do the same thing. The method I'm executing, the sum method, doesn't return a value from it's
 method, so the submit method knows it's executing a Runnable. The submit method though, unlike the execute method, can
 also run a Callable. What's the difference between Runnable and Callable?

                                      Difference between Runnable and Callable

                        Runnable's Functional Method                  Callable's Functional Method
                              void run()                                V call() throws Exception

 Significantly, Callable returns a value. This means you can get data back from your running threads. You can have two
 ways of communication when using submit.

                                                Execute vs. Submit

                            method                          signature

                            execute                         void execute(Runnable command)

                            submit                          Future<?> submit(Runnable task)
                                                            <T>Future<T> submit(Runnable task, T result)
                                                            <T>Future<T> submit(Callable<T> task)

 On this table, I'm showing you the method signatures for both the execute and submit methods. There is only a single
 execute method, and it takes a Runnable and returns no value. The submit method has three overloaded versions. Notice
 that in all cases, each returns a result, an instance of something called a Future. The submit method I just demonstrated
 in my code, was an example of the first submit method, which executed a runnable. In this case, I simply ignored any
 result coming back. I'll be demonstrating the differences between each of these shortly. First, let's talk about the
 Future.

                                                The Future Interface

                                            _____________________________
                                            |<<Interface>>              |
                                            |Future<V>                  |
                                            |---------------------------|
                                            |  cancel(boolean): boolean |
                                            |  get(): V                 |
                                            |  get(long, TimeUnit): V   |
                                            |  isCancelled: boolean     |
                                            |  isDone: boolean          |
                                            |___________________________|

 A Future represents a result, of an asynchronous computation. It's a generic type, a placeholder for a result instance.
 It has methods that cancel the task, retrieve the result, or check if the computation was completed or cancelled. The
 get() method returns the result, but you can only call this get() method, when the computation is complete, otherwise
 the call will block, until it does complete. The overloaded version of the get() method allows you to specify a wait time,
 rather than blocking. Let's examine this in some code.
 **/
//End-Part-8

        /*try {
            var redValue = multiExecutor.submit(() -> Main.sum(1, 10, 1, "red"));
            var blueValue =multiExecutor.submit(() -> Main.sum(10, 100, 10, "blue"));
            var greenValue =multiExecutor.submit(() -> Main.sum(2, 20, 2, "green"));

            try {
                System.out.println(redValue.get(500, TimeUnit.SECONDS));
                System.out.println(blueValue.get(500, TimeUnit.SECONDS));
                System.out.println(greenValue.get(500, TimeUnit.SECONDS));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        } finally {
            multiExecutor.shutdown();
        }*/

//Part-9
/**
        First, I'll change my sum method. I'll continue to print the result, but I also want to return the sum from the
 method. So I'll return an int. If I go back to the main method, notice my code still compiles. The submit method, doesn't
 really care if I do anything with the result of it's call. If I hover over one of the submits. I can see, I'm calling a
 different overloaded version of submit now, which takes a Callable, as it's argument. In my code, I'll use type inference,
 and assign each of the submit calls to a variable. Notice IntelliJ's inlay hints, in each case, the result is a Future,
 with a type parameter of Integer. I'll print each result, by calling the get method that let's me specify a time out.
 I'll wrap all of these calls in a single try catch, and just catch any Exception, since there's a variety of exception
 types that could get thrown here. In each case, I'll invoke the get method on each tasks result, with 500 milliseconds,
 starting with the result of the red task. I'll do the same for the result of the blue task, as well as the green task.
 In this case, I'll catch any exception, using the more generic Exception class. And I'll just rethrow the exception, if
 I do get one. Running that code.

             pool-1-thread-2, blue  550
             pool-1-thread-3, green  110
             pool-1-thread-1, red  55
             55
             550
             110

 I should be able to see just the results, in other words just the sum of each task, printed out. The future is a way for
 a task to communicate its result, back to the calling code. Up until now, I've only executed a task individually, using
 either the submit or the execute method. The ExecutorService implementations have a method, named invokeAll. I'll again
 rename the main method, to cached main, and create a new main method.
 **/
//End-Part-9

        /*List<Callable<Integer>> taskList = List.of(
                () -> Main.sum(1, 10, 1, "red"),
                () -> Main.sum(10, 100, 10, "blue"),
                () -> Main.sum(2, 20, 2, "green")
        );

        try {
            var results = multiExecutor.invokeAll(taskList);
            for (var result : results) {
                System.out.println(result.get(500, TimeUnit.MILLISECONDS));
            }
        } catch (InterruptedException | TimeoutException | ExecutionException e) {
            throw new RuntimeException(e);
        } finally {
            multiExecutor.shutdown();
        }*/

//Part-10
/**
        I'll copy the first statement from cached main, as well as the try statement, and paste that in my new main method.
 I'll do the same as well for the finally clause. Now, instead of individually executing tasks, I'll first set up a list
 of Callable tasks. I'll create a list of callables, which will return integers. My tasks will be the same as I used
 previously, I'll just set them all up here individually, so Main.sum, and this will sum 1 through 10, printing it in red.
 The blue task sums numbers by 10, starting at 10 and up to, and including 100. Green sums even numbers, between 2 and 20,
 same as before. Next, I'll add code in my try block. I'll call invokeAll on my multiExecutor instance. This method takes
 a Collection of Callables, so I can pass my task List. From the inlay hint, we can see the inferred type of the result.
 It's a list of futures, which will wrap integer value results. I'll loop through the results, printing out the result
 of each callable, waiting up to half a second before retrieving it. I'm getting two errors now, but if I hover over get,
 It's showing that three exceptions need to be handled. Instead of getting IntelliJ to generate a catch block for each
 exception, I'll just create one multi exception catch, for all three exceptions. I'll catch InterruptedException,
 TimeoutException, and ExecutionException. If I get any of these, I'll re throw it as a runtime exception. Running this
 code,

             pool-1-thread-1, red  55
             pool-1-thread-2, blue  550
             pool-1-thread-3, green  110
             55
             550
             110

 I get the same results as before, when I called submit on each task individually. The difference here is, I was able to
 create a collection of tasks up front, or before I needed them, then pass them all to the Executor service's invokeAll
 method. And I can get the results in a single collection, rather than having to get each result individually. There's
 another method that's a bit different, called invokeAny.
 **/
//End-Part-10

        List<Callable<Integer>> taskList = List.of(
                () -> Main.sum(1, 10, 1, "red"),
                () -> Main.sum(10, 100, 10, "blue"),
                () -> Main.sum(2, 20, 2, "green")
        );

        try {
            var results = multiExecutor.invokeAny(taskList);
            System.out.println(results);

        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        } finally {
            multiExecutor.shutdown();
        }

//Part-11
/**
        Let's look at that real quick. I'll change invokeAll to invokeAny in my code. Notice that I've got errors now,
 and that's because my variable, results, isn't a list coming back from this method, it's actually just single value. And
 the result's not a Future, it's just an integer. I'll comment out the for loop, and remove the call to get, and, instead,
 just print the integer out. I also don't need to catch aTimeoutException, so I'll remove that from my multi-exception
 catch. I'll run this and see what I get.

             pool-1-thread-1, red  55
             pool-1-thread-3, green  110
             pool-1-thread-2, blue  550
             55

 I'll run it a couple of times. Notice I get messages that all threads were run, but results for only one of those, and
 it appears to be arbitrary. The result we get back is actually the result from the task that completes first. It returns
 only that one completed tasks results.

                                            invokeAll vs. invokeAny

        Even though, in our example, we can clearly see all our tasks were executed, this isn't necessarily always the
 case, when you use invokeAny. When would you want to use invoke Any vs. invokeAll?

            Characteristic           invokeAny                                  invokeAll

            Task Executed            At least one, the first to complete.       All tasks get executed.

            Result                   Result of the first task to complete,      Returns a list of results, as futures, for all
                                     not a Future.                              of tasks, once they have all completed.

            Use cases                Use this method when you need a quick      Use this method when you want all the tasks
                                     response back from one of several tasks,   to be executed concurrently, and all tasks
                                     and you don't care if some will fail.      must complete before proceeding.

 For invokeAny, only one task is guaranteed to run, and that's the first one to complete. For invokeAll, all tasks are
 guaranteed to be executed. For invokeAny, remember out result wasn't a future, in this example, it was simply an integer,
 and was the result of the first task to complete. For invokeAll, we got a list of the results of all the tasks that
 completed, which were all the tasks we passed to that method. What's a good use case for invokeAny? Imagine trying to
 get a result back from three possible web servers available to answer some kind of question (perhaps google, bard, and
 chat gpt for example). The first response is all you need, so you fire off three tasks, and return the first, and fastest
 result, to your client. This is quite a different use case than one where you want your application to troll multiple
 web sites, and then compile a list of answers from all three, for example, which would better fit the invokeAll model.
 Next, I'll cover a couple of other classes for managing threads, but first I think it's time for a challenge.
 **/
//End-Part-11

    }

    public static void cachedmain(String[] args) {

        var multiExecutor = Executors.newCachedThreadPool();
        try {
            var redValue = multiExecutor.submit(() -> Main.sum(1, 10, 1, "red"));
            var blueValue = multiExecutor.submit(() -> Main.sum(10, 100, 10, "blue"));
            var greenValue = multiExecutor.submit(() -> Main.sum(2, 20, 2, "green"));

            try {
                System.out.println(redValue.get(500, TimeUnit.SECONDS));
                System.out.println(blueValue.get(500, TimeUnit.SECONDS));
                System.out.println(greenValue.get(500, TimeUnit.SECONDS));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } finally {
            multiExecutor.shutdown();
        }
    }

    public static void fixedmain(String[] args) {

        int count = 6;
        var multiExecutor = Executors.newFixedThreadPool(3, new ColorThreadFactory());

        for (int i = 0; i < count; i++) {
            multiExecutor.execute(Main::countDown);
        }
        multiExecutor.shutdown();
    }

//Part-3
/**
        I'll once again rename the current main method, because I do think it will help you to keep these multiple versions
 around, to compare the different Executors. I'll rename that method to fixed main, and create a new main, so psvm.
**/
//End-Part-3

    public static void singlemain(String[] args) {

        var blueExecutor = Executors.newSingleThreadExecutor(
                new ColorThreadFactory(ThreadColor.ANSI_BLUE)
        );
        blueExecutor.execute(Main::countDown);
        blueExecutor.shutdown();

        boolean isDone = false;
        try {
            isDone = blueExecutor.awaitTermination(500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (isDone) {
            System.out.println("Blue finished, starting Yellow");
            var yellowExecutor = Executors.newSingleThreadExecutor(
                    new ColorThreadFactory(ThreadColor.ANSI_YELLOW)
            );
            yellowExecutor.execute(Main::countDown);
            yellowExecutor.shutdown();

            try {
                isDone = yellowExecutor.awaitTermination(500, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (isDone) {
                System.out.println("Yellow finished, starting Red");
                var redExecutor = Executors.newSingleThreadExecutor(
                        new ColorThreadFactory(ThreadColor.ANSI_RED)
                );
                redExecutor.execute(Main::countDown);
                redExecutor.shutdown();
                try {
                    isDone = redExecutor.awaitTermination(500, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (isDone) {
                    System.out.println("All processes completed");
                }
            }
        }
    }

    public static void notmain(String[] args) {

        Thread blue = new Thread(
                Main::countDown, ThreadColor.ANSI_BLUE.name());

        Thread yellow = new Thread(
                Main::countDown, ThreadColor.ANSI_YELLOW.name());

        Thread red = new Thread(
                Main::countDown, ThreadColor.ANSI_RED.name());

        blue.start();

        try {
            blue.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        yellow.start();

        try {
            yellow.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        red.start();

        try {
            red.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void countDown() {

        String threadName = Thread.currentThread().getName();
        var threadColor = ThreadColor.ANSI_RESET;
        try {
            threadColor = ThreadColor.valueOf(threadName.toUpperCase());
        } catch (IllegalArgumentException ignore) {
            // User may pass a bad color name, Will just ignore this error.
        }

        String color = threadColor.color();
        for (int i = 20; i >= 0; i--) {
            System.out.println(color + " " +
                    threadName.replace("ANSI_", "") + "  " + i);
        }
    }

    private static int sum(int start, int end, int delta, String colorString) {

        var threadColor = ThreadColor.ANSI_RESET;
        try {
            threadColor = ThreadColor.valueOf("ANSI_" + colorString.toUpperCase());
        } catch (IllegalArgumentException ignore) {
            // User may pass a bad color name, Will just ignore this error.
        }

        String color = threadColor.color();
        int sum = 0;
        for (int i = start; i <= end; i += delta) {
            sum += i;
        }
        System.out.println(color + Thread.currentThread().getName() + ", " + colorString + "  " + sum);
        return sum;
    }

//Part-2
/**
        This method will sum up a series of numbers. The task will pass the starting number, the ending number, the delta,
 or the amount each iteration will increase by, and the color the thread should be printed out in, as a string here. This
 will be a private static method, and it won't return anything. I'll set ansi reset to the thread color variable. In the
 try block, I'll assign the value of color String to thread color. I'll catch an IllegalArgumentException, if the user
 passes a bad color, and I'll just ignore it. I'll assign the thread color to a String variable, color. I'm going to have
 an int sum, and I'll set that to zero. I'll use a regular for loop to loop from start to end, summing up the delta values.
 Finally, I'll print out the color, the current thread, the color String, and the sum.
 **/
//End-Part-2
}
