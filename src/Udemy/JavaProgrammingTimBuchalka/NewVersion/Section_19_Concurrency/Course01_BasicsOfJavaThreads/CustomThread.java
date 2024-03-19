package CourseCodes.NewSections.Section_19_Concurrency.Course01_BasicsOfJavaThreads;

//Part-7
/**
        Now notice, I don't have any errors. The run method on the Thread class, isn't abstract, and I'm not required to
 override it. If I don't override it though, when I'm creating a Thread subclass, my thread isn't going to do anything.
 I'll use IntelliJ's tools, to override the run method on Thread here. As I showed you earlier, this method has no parameters,
 and no return type. Let's ctrl click on super.run, to see what the run method actually does do, on the java.lang.Thread
 class. Here, we can see that if the target field on this class is not null, it'll run the target's run method. I'll be
 talking about this target shortly, because this is what's passed to a Thread constructor, the Runnable instance in other
 words, when we create a thread the second way. For our purposes now though, this run method on super, isn't going to do
 anything. For that reason, I'll remove the super.run statement.

        Next, I'll add what I want this code to really do, which is to output the number 1, at certain time intervals.
 I'll set up a loop, to iterate five times. Foreach iteration, I'll print a 1, from this thread. I'll set up a try catch
 block. I'm going to use a method on Thread that requires me to catch or specify, a checked exception, in other words.
 This method on Thread is the sleep method. I think I used it once previously. It tells the current thread to sleep, or
 do nothing, for a certain defined time. This method takes a long value, for the number of milliseconds to sleep. If I
 pass 500, I'm saying make this thread wait for half a second, before it does anything else. I need to catch an InterruptedException,
 a checked exception. If this thread does get interrupted, I'll print out the stack trace. Ok, that's my sub class, a
 Thread ultimately, but one that has custom code, in it's run method. In the next lecture, we'll be creating our first
 multi-threaded application, by firing up this new thread.
 **/
//End-Part-7

public class CustomThread extends Thread {

    @Override
    public void run() {

        for (int i = 1; i <= 5; i++) {
            System.out.print(" 1 ");
            try {
                Thread.sleep(500);  // Adding a 1-second delay between each count
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
