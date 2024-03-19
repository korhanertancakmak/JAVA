package CourseCodes.NewSections.Section_19_Concurrency.Course09_DeadlocksWaitNotify;

import java.util.Random;

//Part-1
/**
        In the last lecture, I demonstrated a deadlock situation that can occur, in a Producer Consumer Application. This
 happens when you have two synchronized methods on a shared resource. One process is waiting for the other process to do
 something, but it can't, because the first process won't release the monitor lock. I've got the project up from the last
 lecture, the ConsumerProducer project. Before I get back to the code, let's talk about a couple of methods I haven't yet
 covered, that are on the Object class. These are, the wait, notify, and notifyAll methods, which are used to manage some
 monitor lock situations, to prevent threads from blocking indefinitely. Because these methods are on Object, any instance
 of any class, can execute these methods, from within a synchronized method or statement. I'll pull up the API documentation,
 for Object which, as you know is the parent of all other classes. There aren't very many methods on the Object class.
 Several of these, you've become very familiar with, like to String and getClass. But there's also two others, notify and
 notifyAll here, and there's three overloaded versions of a wait method. I'm going to click on the very last wait method,
 the one that has two arguments, because it has the best definition, of how these methods work. The key thing I want you
 to read, is the third statement down. We're told that, this method causes the current thread to place itself in the wait
 set, for this object, and then to relinquish any and all synchronization claims on this object. Think about that for a
 minute. The wait method, will put the current thread in a wait queue, if you will, for the object it might otherwise
 have a lock on. This frees up the object's lock, to be acquired by other threads, while that thread is in this waiting
 state, or set. If we read further, we find that the thread which calls wait, will lie dormant, until something happens.
 In other words, the thread that had the lock relinquishes the lock when it calls wait, but only until it gets woken up
 by some outside event. That event can be any one of the five things listed here. This list includes being notified by
 other threads that something's happened, when those threads broadcast either a notify or a notifyAll method. I'll talk
 about this in just a minute. In addition to being notified, a thread could be interrupted. There is an overloaded version
 of the wait method, which, if it times out, will wake up this thread at that point. The last condition is if the thread
 is awakened spuriously. That doesn't sound good. If we read further, we're told this is a rare thing, but we need to code
 for it. I'll cover how to do that shortly. Now, let's quick look at the notify method. This method we're told, wakes up
 a single thread. But how does it know which one? Well, if we continue reading, we find that the choice is arbitrary.
 Arbitrary doesn't seem like a good thing either, in application code, especially since the thread that has the lock,
 probably really needs to be notified. I want you to think about this for a minute. If a thread broadcasts notify, and
 there are 2 or 3 threads in the wait set, any of those threads might get notified, but only just one of them. So it might
 not be the one you really want to notify at all. It could be another thread completely unrelated to the process at hand.
 There are some good use cases for this notify method, but it's not what we want in our case. Now, I'll scroll down to
 the second notify method, notify All, this says that it will wake up all threads, which are waiting on the lock or monitor.
 And even though it wakes up all threads, they can't acquire the lock, before the notifying thread releases the lock.
 Ok, now that we know in theory, what these methods do, I'll apply them to my Consumer Producer example.
 **/
//End-Part-1

class MessageRepository {

    private String message;
    private boolean hasMessage = false;

    public synchronized String read() {

        while (!hasMessage) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        hasMessage = false;
        notifyAll();
        return message;
    }

//Part-2
/**
        Any code in a synchronized method, that's sitting in a loop, and waiting for something to change, should be calling
 the wait method. This will suspend it's synchronization claims on the object, while the thread waits for some condition
 to be met. In other words, it won't block other threads. This needs to be done in the MessageRepository class, in both
 the read and write methods, where we have the while loops. I'll start with the read method. Inside the while loop, I'll
 call wait, with no arguments. You might have guessed, this will throw a checked exception, so I'll use IntelliJ's help,
 to wrap it in a try catch block. I'll also add the notifyAll call, outside of the loop, and after this code toggles the
 Message flag. This is the trigger another thread is waiting on. I'll next repeat this for the write method.
**/
//End-Part-2

    public synchronized void write(String message) {

        while (hasMessage) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        hasMessage = true;
        notifyAll();
        this.message = message;
    }
}

//Part-3
/**
        I'll copy the try catch block from the read method, and paste it into the write method. I'll also add the call
 to notify all, again after this code toggles the message flag. Let's just go through and discuss a few things about this
 code. First of all, we're still looping. Now you might be wondering, can we do this outside of a loop, maybe with just
 an if statement, for example? The answer is, that you always want to call wait, within a for loop. The loop should test
 for whatever condition this thread is waiting on, so it can proceed and finish it's task. When a thread is notified to
 wake up, it might not be because the condition it's waiting on has changed. Remember, notify all wakes up all threads
 that are waiting, so this thread could be woken up for a totally unrelated reason. If it wasn't in a for loop, it might
 not complete the task it's really asked to do. If there's no message, it should continue to wait. You should always call
 wait within a loop, so that when it returns, meaning when there's been a notification of some sort, it'll go back to the
 beginning of the loop. At that point, it'll check the condition it's interested in, and call wait again if that condition
 hasn't changed. So in other words, never assume that a thread is being woken up, because the condition that it's waiting
 on, has changed. This might not be true. The next thing here is, you probably noticed, I called notify all. The notify
 method doesn't accept any parameters, so there's no way for it to notify one specific thread. It's conventional to use
 notify all, unless there are many threads waiting, and they're all set up to do the same task. In that case, you'd want
 to avoid waking up every thread, because of contention and performance issues. In that scenario, calling notify will wake
 one thread. Because they all do the same work, it's sufficient to wake just one thread, in that case. Ok, let's see if
 this actually works. I'll run this.

                     Humpty Dumpty sat on a wall,
                     Humpty Dumpty had a great fall,
                     All the king's horses and all the king's men,
                     Couldn't put Humpty together again.
                     Finished

 And there I can see all the lines in the nursery rhyme, followed by the finished message, so that's a good sign. I'll run
 it again. it's taking a second or two to process each message, but I'm consistently getting the Finished message, and
 the application exits smoothly. In this lecture, I gave you one solution to deal with the problem of a deadlock. This is
 to use wait and notify all methods, in each synchronized code block, in this case the methods. Coming up, I'll be talking
 about other locking mechanisms, and the pros and cons of using those, rather than relying on the implicit monitor lock.
 First though, I've got a challenge for you.
 **/
//End-Part-3

class MessageWriter implements Runnable {

    private MessageRepository outgoingMessage;

    private final String text = """
            Humpty Dumpty sat on a wall,
            Humpty Dumpty had a great fall,
            All the king's horses and all the king's men,
            Couldn't put Humpty together again.""";

    public MessageWriter(MessageRepository outgoingMessage) {
        this.outgoingMessage = outgoingMessage;
    }

    @Override
    public void run() {

        Random random = new Random();
        String[] lines = text.split("\n");

        for (int i = 0; i < lines.length; i++) {
            outgoingMessage.write(lines[i]);
            try {
                Thread.sleep(random.nextInt(500, 2000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        outgoingMessage.write("Finished");
    }
}

class MessageReader implements Runnable {

    private MessageRepository incomingMessage;

    public MessageReader(MessageRepository incomingMessage) {
        this.incomingMessage = incomingMessage;
    }

    @Override
    public void run() {

        Random random = new Random();
        String latestMessage = "";

        do {
            try {
                Thread.sleep(random.nextInt(500, 2000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            latestMessage = incomingMessage.read();
            System.out.println(latestMessage);
        } while (!latestMessage.equals("Finished"));
    }
}

public class Main {

    public static void main(String[] args) {

        MessageRepository messageRepository = new MessageRepository();

        Thread reader = new Thread(new MessageReader(messageRepository));
        Thread writer = new Thread(new MessageWriter(messageRepository));

        reader.start();
        writer.start();
    }
}
