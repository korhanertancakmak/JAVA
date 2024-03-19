package CourseCodes.NewSections.Section_19_Concurrency.Course26_MoreConcurrencyFeatures;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//Part-9
/**
                                                    Timer Class

        The timer class has been around since Java 1.3, and preceded the java.util.concurrent package. This is a single
     threaded task scheduler. I'll create a new class in my project, called TimerExample, and use psvm to generate a main
     method.
 **/
//End-Part-9

public class TimerExample {

    public static void main(String[] args) {

        /*Timer timer = new Timer();*/                                          // Commented via Part-12

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                String threadName = Thread.currentThread().getName();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                System.out.println(threadName + " Timer task executed at: " + formatter.format(LocalDateTime.now()));
            }
        };

//Part-10
/**
        I'll set up a local variable, timer, using the type Timer. And I can just use a no args constructor to create
     an instance of a timer class. I'll select java.util.Timer here. When a Timer is created, a new background thread is
     created. Instead of a Thread, or a Callable or Runnable, I need to create something called a TimerTask. I have to
     override the TimerTask's run method, which has the signature public void run. I want to get the thread name here.
     I'll create a date time formatter variable, and pass that a specific pattern. I'll print the thread name, and that
     the timer task was executed, and I'll print out the current local date time, formatted. I can call a method on this
     timer instance, called scheduleAtFixedRate.
 **/
//End-Part-10

        /*timer.scheduleAtFixedRate(task, 0, 2000);                             // Commented via Part-11

        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        timer.cancel();*/

//Part-11
/**
        That might sound a little familiar. Here though, this method takes a TimerTask, so I'll pass the task variable.
    Unlike the scheduled executor services, this method takes only two long values, both representing time in milliseconds.
    Here, I want the task to run immediately, then every 2 seconds after. This code will run the task at this point, and
    every 2 seconds. It will run until I cancel it, through the timer instance. I know I need a try block. I want the
    thread to sleep for 12 seconds, giving it some time to run. And finally, I need to cancel the timer instance. If I
    run this,

             Timer-0 Timer task executed at: 2024-02-18 22:31:39
             Timer-0 Timer task executed at: 2024-02-18 22:31:41
             Timer-0 Timer task executed at: 2024-02-18 22:31:43
             Timer-0 Timer task executed at: 2024-02-18 22:31:45
             Timer-0 Timer task executed at: 2024-02-18 22:31:47
             Timer-0 Timer task executed at: 2024-02-18 22:31:49

    First, I can see my thread name is different, from the executor service's default thread names. Here, I have just
    Timer dash zero, and I can see that the task is executed every 2 seconds. You might be asking, should you use this
    or the Scheduled Executor Service? In truth, this may seem simpler on first look, but let's do the same thing with
    a ScheduledSingleThreadExecutor. First I'll comment out two lines, the timer.schedule At Fixed Rate statement, and
    the timer.cancel statement. I'll now use an executorService.
 **/
//End-Part-11

        var executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);

        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.shutdown();

//Part-12
/**
        I'll get a new single thread scheduled executor. I'll call a method by the same name, schedule at fixed rate, on
    this instance. Here, I have a tiny bit of flexibility, because I can specify the time unit. Notice that I can pass
    the timer task as the Runnable. That's because the TimerTask actually implements the Runnable interface. You can't
    use aRunnable though, in the timer instance's method. Instead of timer.cancel, I call executor shutdown. If I run this.

             pool-1-thread-1 Timer task executed at: 2024-02-18 22:37:06
             pool-1-thread-1 Timer task executed at: 2024-02-18 22:37:08
             pool-1-thread-1 Timer task executed at: 2024-02-18 22:37:10
             pool-1-thread-1 Timer task executed at: 2024-02-18 22:37:12
             pool-1-thread-1 Timer task executed at: 2024-02-18 22:37:14
             pool-1-thread-1 Timer task executed at: 2024-02-18 22:37:16
             pool-1-thread-1 Timer task executed at: 2024-02-18 22:37:18
                                    ... (STUCK)

    I get the same number of messages, and can see them printed every 2 seconds. I see the familiar thread name, so pool
    1, thread 1. But look, my application isn't exiting smoothly. That's because I've created a Timer instance, as the
    first statement in the main method, and all timer constructors start a timer thread. My application won't shut down,
    unless I execute cancel on this. I'll comment this line of code out instead, because I'm not using a timer any more.

             pool-1-thread-1 Timer task executed at: 2024-02-18 22:39:31
             pool-1-thread-1 Timer task executed at: 2024-02-18 22:39:33
             pool-1-thread-1 Timer task executed at: 2024-02-18 22:39:35
             pool-1-thread-1 Timer task executed at: 2024-02-18 22:39:37
             pool-1-thread-1 Timer task executed at: 2024-02-18 22:39:39
             pool-1-thread-1 Timer task executed at: 2024-02-18 22:39:41

    I'll rerun this. Now, the code prints the statements, and executes smoothly. Ok, so I just wanted to show you those
    two classes side by side. As the Timer documentation states, ConfiguringScheduledThreadPoolExecutorWith one thread,
    makes it equivalent toTimer. Which you use, is up to you. If you need multiple threads running tasks at fixed rates,
    then you'd want to use aScheduledThreadPoolExecutor. But sometimes, using the Timer may be the simplest route, if
    you just need to schedule a one off task. The final thing I want to talk about, is something called the Watch Service.
 **/
//End-Part-12
    }
}
