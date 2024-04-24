package CourseCodes.NewSections.Section_19_Concurrency.Course16_SchedulingTasks;

import java.sql.Time;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

//Part-1
/**
        Ok, so we've covered a lot of ground in a very short time frame. You've seen the SingleThreadExecutor, the
 FixedThreadPool and CachedThreadPool Executors. You've learned how to both execute and submit a Runnable, submit a Callable,
 and also how to get a result back, in a Future instance. You've also learned how to use invokeAll, and invokeAny, and
 hopefully have an idea of when invokeAny might be useful. In this lecture, I'll be talking about scheduling tasks. You
 schedule tasks with a special type of ExecutorService, the ScheduledExecutorService. I've created a new Project, and
 called it SchedulingTasks, with the usual Main class and main method.
 **/
//End-Part-1

public class Main {

    public static void main(String[] args) {

        var dtf = DateTimeFormatter.ofLocalizedDateTime(
                FormatStyle.MEDIUM,
                FormatStyle.LONG
        );

        Callable<ZonedDateTime> waitThenDoIt = () -> {
            ZonedDateTime zdt = null;
            try {
                TimeUnit.SECONDS.sleep(2);
                zdt = ZonedDateTime.now();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return zdt;
        };

//Part-2
/**
        Since I'll be using a scheduler, I want to deal with some date time data. I'll start out by creating a date time
 formatter variable, for a local date time instance. Remember, the DateTimeFormatter has some factory methods, so I'll
 use of localized date time. I'll print the date part out in the medium format style for a date. And I'll print the time
 in the long style. Next, I'll set up a Callable variable that returns a ZonedDateTime. I'll call this variable, waitThenDoIt.
 Next, I'll initialize a ZonedDateTime variable to null. I need a try catch, Because I want to sleep for 2 seconds before
 I get the time. I'll set my variable to what I get back from ZonedDateTime.now. And I'll include the usual catch clause.
 Finally, I'll return the zoned date time variable from this lambda expression.
 **/
//End-Part-2

        var threadPool = Executors.newFixedThreadPool(2);
        List<Callable<ZonedDateTime>> list = Collections.nCopies(4, waitThenDoIt);

        try {
            System.out.println("---> " + ZonedDateTime.now().format(dtf));
            List<Future<ZonedDateTime>> futureList = threadPool.invokeAll(list);
            for (Future<ZonedDateTime> result : futureList) {
                try {
                    System.out.println(result.get(1, TimeUnit.SECONDS).format(dtf));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            threadPool.shutdown();
        }

//Part-3
/**
        To execute this code, I'll create a fixed thread pool. I get this by calling new fixed thread pool, and pass 4
 to that method, so four tasks can run concurrently at any one time using this pool. I'll set up a list of my Callables,
 and I can get a collection of 4 of these, using nCopies, passing it 4, and my Callable variable, waitThenDoIt. Now, I'll
 kick off the tasks in a try catch, but first, I'll print the current date time, with my formatter. I'll call now on the
 ZoneDateTime class, passing my formatter variable, DTF, to the format method on the zoned date time. I'll call invokeAll,
 on my thread pool, passing it my task list. This returns a list of futures. Each future will contain a zoned date time,
 passed back from the task. In a for loop, I'll check the results of these futures. Since I'll be using the get method,
 on future, I need to catch one of several exceptions, so I need a nested try catch. I'll print the date that I get back,
 using my formatter variable. And add a catch, with a generic Exception. I'll end the for loop. For the outer catch, the
 exception will be an InterruptedException. If that happens, I'll throw it as a runtime exception. Lastly, I've got a
 finally clause, To shut down my thread pool smoothly. So I ran through this pretty fast, but we've used a fixed pool
 before, with a list of tasks, and called invoke all. This is going to run all four of the tasks in my list. I'll run this.

             ---> 22 Ara 2023 23:02:07 TRT
             22 Ara 2023 23:02:09 TRT
             22 Ara 2023 23:02:09 TRT
             22 Ara 2023 23:02:09 TRT
             22 Ara 2023 23:02:09 TRT

 First, I get the statement that includes the arrow. This was printed from the main method, and not from an asynchronous
 task. Your output may look a bit different, based on your time zone, and localized data. After about 2 seconds, I get
 the dates from the other 4 tasks, when those tasks were executed. Each of these tasks seems to have been executed
 2 seconds after that first statement, which is what you'd probably expect, since I fired them all off at the same time.
 Now, I'll change my fixed thread pool to handle 2 threads at a time. Running the code this way,

             ---> 22 Ara 2023 23:43:29 TRT
             22 Ara 2023 23:43:31 TRT
             22 Ara 2023 23:43:31 TRT
             22 Ara 2023 23:43:33 TRT
             22 Ara 2023 23:43:33 TRT

 What I want you to notice here is that, although all four are printed at the same time, the first two threads were recorded
 2 seconds after the initial date time. Even though you think you might be scheduling your threads 2 seconds after they
 get invoked, this really isn't up to you. Your thread pool's manager, and the operating system, will determine how best
 to manage a series of threads. If you really need to manage exactly when things run, you'll want to use a scheduled
 executor service. Let's look at a simple example of this next.
 **/
//End-Part-3

        /*System.out.println("---> " + ZonedDateTime.now().format(dtf));
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        executor.schedule(() -> System.out.println(ZonedDateTime.now().format(dtf)), 2, TimeUnit.SECONDS);

        executor.shutdown();*/

//Part-4
/**
        I'll again print the date, in the main thread, with an arrow to identify it. I'll set up an executor variable,
 using the specialized Interface, ScheduledExecutorService. I'll call Executors dot new Single Thread Scheduled Executor.
 I can call the schedule method on this instance, and in this case, I'll pass it a lambda expression, that again prints
 a formatted date time. In addition to the task, I now include a delay time, which is a number and a unit, so I'll pass
 2 seconds. And I have to include shutdown on that executor. I'll run that.

             ---> 22 Ara 2023 23:49:14 TRT
             22 Ara 2023 23:49:16 TRT
             22 Ara 2023 23:49:16 TRT
             22 Ara 2023 23:49:18 TRT
             22 Ara 2023 23:49:18 TRT
             ---> 22 Ara 2023 23:49:18 TRT
             22 Ara 2023 23:49:20 TRT

 After my first four tasks are run from the previous code, I can see another print statement with an arrow. Then after a
 2 second delay, another date time is printed from the scheduled task. This time the delay was managed by the executor.
 You can see it's two seconds after the previous statement.
 **/
//End-Part-4

        /*System.out.println("---> " + ZonedDateTime.now().format(dtf));
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(4);

        for (int i = 0; i < 4; i++) {
            executor.schedule(() -> System.out.println(ZonedDateTime.now().format(dtf)), 2 * (i + 1), TimeUnit.SECONDS);
        }
        executor.shutdown();*/

//Part-5
/**
        Instead of a SingleThreadedExecutor, I'll switch to a scheduled thread pool, which is my other option. This is
 actually a cached thread pool. This takes a numeric argument, which is the number of threads, this pool will be started
 up with. I'll just pass 4 here, so the pool will get created, with four threads ready to work. And instead of scheduling
 one task, I'll schedule four, by wrapping the schedule statement in a for loop I'll just use a traditional for loop. I'll
 also change the delay, so a time gets printed every two seconds. If I run this,

                     ---> 23 Ara 2023 00:03:26 TRT
                     23 Ara 2023 00:03:28 TRT
                     23 Ara 2023 00:03:28 TRT
                     23 Ara 2023 00:03:30 TRT
                     23 Ara 2023 00:03:30 TRT
                     ---> 23 Ara 2023 00:03:30 TRT
                     23 Ara 2023 00:03:32 TRT
                     23 Ara 2023 00:03:34 TRT
                     23 Ara 2023 00:03:36 TRT
                     23 Ara 2023 00:03:38 TRT

 I now get a second set of four tasks. Each is executed at different delays, a multiple of 2 seconds. The first is printed
 after 2 seconds, the second after 4 seconds, then 6, and finally 8 seconds. We can actually use a different method on
 the scheduled executor service, to do a similar thing to this.
 **/
//End-Part-5

        /*System.out.println("---> " + ZonedDateTime.now().format(dtf));
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(4);

        executor.scheduleWithFixedDelay(() -> System.out.println(ZonedDateTime.now().format(dtf)),2, 2, TimeUnit.SECONDS);

        executor.shutdown();*/

//Part-6
/**
        I'll revert the last 3 changes, so I'll remove that last change to the tasks schedule statement, and remove the
 for loop. Now, I'll change schedule, to scheduleWithFixedDelay. This method has 4 arguments, not 3. The second argument
 is a time, for an initial delay. In this case, this task will run after an initial delay of 2 seconds, and will be executed
 every two seconds after that. If I run this though,

                     ---> 23 Ara 2023 00:07:32 TRT
                     23 Ara 2023 00:07:34 TRT
                     23 Ara 2023 00:07:34 TRT
                     23 Ara 2023 00:07:36 TRT
                     23 Ara 2023 00:07:36 TRT
                     ---> 23 Ara 2023 00:07:36 TRT

 Notice what's happened. I don't get anything printed out from any tasks. The reason for this though, is because of the
 following call, to the executor shutdown. This method shutdowns an executor, after currently executing tasks have completed,
 and before future ones are started. So in this case, I'm actually shutting down the executor, before the task ever has
 a chance to run. Before I do shut this down a scheduled executor, I'll first want to get some data back, after a few
 tasks have executed. A scheduled thread executor will return an instance of a ScheduledFuture, instead of a Future, which
 we can poll for information.
 **/
//End-Part-6

        /*System.out.println("---> " + ZonedDateTime.now().format(dtf));
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(4);

        var scheduleTask = executor.scheduleWithFixedDelay(
                () -> System.out.println(ZonedDateTime.now().format(dtf)),2, 2, TimeUnit.SECONDS);

        long time = System.currentTimeMillis();
        while(!scheduleTask.isDone()) {
            try {
                TimeUnit.SECONDS.sleep(2);
                if ((System.currentTimeMillis() - time) / 1000 > 10) {
                    scheduleTask.cancel(true);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        executor.shutdown();*/

//Part-7
/**
        I'll create a variable called scheduledTask, using var as my type, and insert that before the call to executor.
 You can see that IntelliJ's inlay hints, tell me that the inferred type, for the scheduledTask, is a ScheduledFuture,
 and it's generic type parameter is a wildcard, the question mark, which means it can be any type. I can now use this
 instance, to keep checking the state of the future task. I'll insert this code, before the shutdown call, on the executor.
 I'll shutdown my executor after a certain amount of time has elapsed. To do this, I'll get the current time using the
 system clock, so I'll create a variable called time, and assign that System.currentTimeMillis. I'll set up a while loop,
 and continue looping, while the isDone method is not true, on my scheduledTask. I need a try clause, Because I'll execute
 this once every 2 seconds. I'll check that the time elapsed, is greater than 10 seconds. I can get the elapsed time by
 again calling System.currentTimeMillis, and subtracting my initial time. This will give me the elapsed time in milliseconds,
 so I'll divide that by 1000, and then check if that's greater than 10. If it is, I'll cancel any future tasks, using
 the cancel method on my ScheduledFuture variable. I'll include the usual catch clause. Running this,

                 ---> 23 Ara 2023 00:21:06 TRT
                 23 Ara 2023 00:21:08 TRT
                 23 Ara 2023 00:21:08 TRT
                 23 Ara 2023 00:21:10 TRT
                 23 Ara 2023 00:21:10 TRT
                 ---> 23 Ara 2023 00:21:10 TRT
                 23 Ara 2023 00:21:13 TRT
                 23 Ara 2023 00:21:15 TRT
                 23 Ara 2023 00:21:17 TRT
                 23 Ara 2023 00:21:19 TRT
                 23 Ara 2023 00:21:21 TRT
                 23 Ara 2023 00:21:23 TRT

 For the second series of statement, I get the date time printed every 2 seconds. This continues, until the elapsed time
 is greater than 10 seconds, so in my case I might get 5 or 6 dates printed, from this scheduled process. This scheduleWithFixedDelay
 method actually schedules the next task, after the first finishes, so it may not actually be at 2 second intervals.
 **/
//End-Part-7

        /*Runnable dateTask = () -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(ZonedDateTime.now().format(dtf));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        System.out.println("---> " + ZonedDateTime.now().format(dtf));
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(4);

        var scheduleTask = executor.scheduleWithFixedDelay(dateTask,2, 2, TimeUnit.SECONDS);

        long time = System.currentTimeMillis();
        while(!scheduleTask.isDone()) {
            try {
                TimeUnit.SECONDS.sleep(2);
                if ((System.currentTimeMillis() - time) / 1000 > 10) {
                    scheduleTask.cancel(true);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        executor.shutdown();*/

//Part-8
/**
        To make this a bit clearer, I'll set up a Runnable that includes a time delay. I'll insert this before the scheduled
 executor code. I'll call this runnable, dateTask. I'll sleep for 3 seconds in this task After this delay, I'll again
 print the date, formatted as usual And I'll include the usual catch clause. I'll change the scheduledTask statement so
 that the scheduleWithFixedDelay method's first argument, is the date task. Ok, let's see what happens if I run this now.

                 ---> 23 Ara 2023 00:27:22 TRT
                 23 Ara 2023 00:27:24 TRT
                 23 Ara 2023 00:27:24 TRT
                 23 Ara 2023 00:27:26 TRT
                 23 Ara 2023 00:27:26 TRT
                 ---> 23 Ara 2023 00:27:26 TRT
                 23 Ara 2023 00:27:31 TRT
                 23 Ara 2023 00:27:36 TRT

 Notice that I get two dates formatted, and the time appears to be every 5 seconds. Again, this method schedules the next
 task, after the first finishes, and not every 2 seconds. Because each task takes 3 seconds to run, and then the scheduler
 schedules the next task, with a two second delay, these tasks are 5 seconds apart. If I really want a new task to run at
 exactly 2 second intervals, regardless of how long the task takes, there's another method I can use. This one is called,
 scheduleAtFixedRate.
 **/
//End-Part-8

        Runnable dateTask = () -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println("a " + ZonedDateTime.now().format(dtf));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        System.out.println("---> " + ZonedDateTime.now().format(dtf));
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(4);

        var scheduleTask = executor.scheduleAtFixedRate(dateTask,2, 2, TimeUnit.SECONDS);

        var scheduleTask2 = executor.scheduleAtFixedRate(
                () -> System.out.println("b " + ZonedDateTime.now().format(dtf)),2, 2, TimeUnit.SECONDS);

        long time = System.currentTimeMillis();
        while(!scheduleTask.isDone()) {
            try {
                TimeUnit.SECONDS.sleep(2);
                if ((System.currentTimeMillis() - time) / 1000 > 10) {
                    scheduleTask.cancel(true);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        executor.shutdown();

//Part-9
/**
        First, I'll go up to the date task, and print out the letter a, in the statement, before the date. I'll change
 the code that schedules the task, to use schedule at Fixed Rate. I'll add another scheduledTask, I'll just call it scheduledTask2.
 I'll call executor.scheduleAtFixedRate here as well. This time I'll just pass a simple lambda, that prints the letter b,
 and then prints the date. this task will first start after 2 seconds, and execute every 2 seconds. I'll run this code
 again.

                        ...(same)
             ---> 23 Ara 2023 00:36:38 TRT
             b 23 Ara 2023 00:36:40 TRT
             b 23 Ara 2023 00:36:42 TRT
             a 23 Ara 2023 00:36:43 TRT
             b 23 Ara 2023 00:36:44 TRT
             b 23 Ara 2023 00:36:46 TRT
             a 23 Ara 2023 00:36:46 TRT
             b 23 Ara 2023 00:36:48 TRT
             a 23 Ara 2023 00:36:49 TRT
             b 23 Ara 2023 00:36:50 TRT

 Ok, so what am I trying to show you here? Well, the scheduled task, the b task, is executed every 2 seconds because it's
 task quickly finishes in the interval. But the first task, that includes the second delay in it's task code, still doesn't
 get run every 2 seconds. This is because the scheduled task manager is scheduling every 2 seconds, but the task exceeds
 that time. Notice that these are printed every 3 seconds, and not 5 as they were before. So the scheduler has already
 scheduled the task, and it immediately starts when it finishes the previous task. Also note, that another thread did not
 pick up the scheduled task. This may seem a bit complicated, but it's important to understand this subtle difference.
 With the fixed rate method, the next task that gets scheduled, may be scheduled before the previous task actually completes,
 and they'll queue up. With the delayed rate method, the next task isn't scheduled until the previous task completes, and
 then it's scheduled with the delay at that point. I've covered most of the ExecutorServices available from Java. Next,
 I'll talk about another, the WorkStealingPool, as well as derivatives of this concept, which include the ForkJoinPool
 and parallel streams. These features split up large tasks into smaller ones, that can run in parallel, to speed up the
 processing time of the overall task.
 **/
//End-Part-9
    }
}
