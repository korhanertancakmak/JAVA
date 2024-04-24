package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_17_Concurrency.Course11_ConcurrentLocksClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//Part-1
/**
        In the last few lectures, I've spent quite a bit of time talking about the intrinsic, or monitor lock, and how it
 works. Any time we use a synchronized method or statement, we're obtaining an intrinsic lock. In the case of a synchronized
 method, it's on the instance itself, on which the method was invoked. In the case of a synchronized statement, it's on
 the instance we pass to it, and the lock is acquired by the thread, only for the duration of the code block in that statement.
 This gives us a few additional options, as we try to manage access to shared resources.

                                             The Purpose of a Lock

        First, let's remember that the purpose of a lock is to control access to a shared resource by multiple threads.
 The monitor lock is pretty easy to use, but it does have limitations.

 * For one thing, there's no way to test if the intrinsic lock has already been acquired. This could give a thread an
   alternative to blocking indefinitely.
 * Another limitation is there's no way to interrupt a blocked thread.
 * There's also not an easy way to debug, or examine the intrinsic lock.
 * Finally, the intrinsic lock is an exclusive lock. This means one thread unconditionally acquires a lock, and excludes
 all other threads from acquiring any other kind of lock.

 To try to address some of these concerns, Java introduced additional locking features.

                                            java.util.concurrent.locks Package

        JDK5 gave us the java.util.concurrent package. This provided developers with some additional solutions, to prevent
 problems in a multi-threaded environment. Included as part of this package, is the locks package. The Lock Interface,
 and some of the provided implementations, can give us a bit more control, and flexibility over locking, and when and
 how to block threads. Let me pull up this interface in the API documentation. Any class that implements this interface
 is required to override, and provide code for, the six abstract methods you see here. We'll be talking about these methods
 shortly, but you can get a sense that these methods address some of the limitations I just discussed. You can see
 lockInterruptibly, and several tryLock methods, one with a timeout and one without, and also an unlock method. These
 features come with advanced capability, but can be a bit more complicated to use, than the simpler monitor lock. Let's
 see if we can test some of these methods out in our code. I'll start with the code from the Consumer Producer project
 from two lectures ago.
**/
//End-Part-1

class MessageRepository {

    private String message;
    private boolean hasMessage = false;
    private final Lock lock = new ReentrantLock();

    public String read() {

//Part-4
/**
 I'll take out the wait and notify all calls, in that method on the Message Repository class. For the wait method,
 I'll replace it with a Thread.sleep in there instead, so I'm polling the hasMessage flag, every half a second or so.
 Now, when I use an explicit lock, I have to both lock, and unlock the lock instance. Similar to using a synchronized
 statement, I can pick and choose where to do this. I'll start out, like the synchronized method, and acquire the lock
 at the start of this method.

                                                                        lock.lock();
    while (!hasMessage) {                                 >>>>>            try {
        try {                                              TO                  while (!hasMessage) {
                                                          >>>>>                     try {
            Thread.sleep(500);                                                         Thread.sleep(500);
        } catch (InterruptedException e) {                                          } catch (InterruptedException e) {
            throw new RuntimeException(e);                                             throw new RuntimeException(e);
        }                                                                           }
    }                                                                      } finally {
 hasMessage = false;                                                            lock.unlock();
                                                                           }
 return message;                                                        hasMessage = false;
                                                                        return message;

 I'll call lock on my lock field. I'll next include a try statement, and wrap all my code in this try block. instead of
 a catch, I'll use a finally clause. Any time I lock an explicit lock, It's now up to me to explicitly unlock it too. For
 a monitor lock, this is done for us. This is best done in a finally clause. This means that if this code executes fine,
 or gets an exception, this finally block is always going to be executed. This is exactly where you want your unlock code
 to be, and this is considered best practice. In essence, what I've done here, meaning the way I've set this up, is the
 same thing as I did, when I had this set up as a synchronized method. I mean this is basically what I had, before I
 included the wait and notifyAll methods in my code. So what do you think will happen if I run this?

                Humpty Dumpty sat on a wall,

 If you guessed deadlock, you'd be right, for the same reasons it deadlocked before. I have to stop my application manually,
 since it's hanging here. So Using an explicit lock isn't going to fix all your deadlocks. But unlike the implicit lock,
 it is type of lock gives us some options to choose from, before a thread acquires the lock and blocks. Next, I'll be
 showing you some of the advantages we get by using this kind of lock.
 **/
//End-Part-4

        if (lock.tryLock()) {
            try {
                while (!hasMessage) {
                    try {
                        // wait();
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                hasMessage = false;
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("** read blocked " + lock);
            // notifyAll();
            hasMessage = false;
        }
        return message;

//Part-5
/**
        For the explicit lock, we don't have the wait and notify all methods, to help us out of this ugly situation. But
 we do have something else, something that gives us more control, before we even acquire the lock and jump into a deadlock.
 But running my code though, simply produced a deadlock, so you might have been left wondering, what's the advantage of
 using an explicit lock here? Instead of the lock method though, I have other methods available, and one of these is the
 "tryLock" method. This method returns a boolean, so I'm going to wrap this, in an if and else statement. This method
 will test if another thread has already got this lock. If another thread doesn't, then this method will acquire the lock,
 and returns true back. If another thread does have the lock, then this method returns false. If I can't acquire the lock,
 I need to figure out something else to do. In this case, I'll print that read was blocked. And I'll set hasMessage to
 false here as well. Now, it's possible by setting the hasMessage flag to false, without first reading the message, there's
 a chance this code will miss a message. But that might be more desirable than blocking here. This tryLock method, lets
 you figure out if there's a better approach than having threads block at that point. Now that I've got my read method
 using an explicit lock, it doesn't really make sense for the write method, to still be using an intrinsic lock at this
 point. I'll take a minute to implement it with a lock, much like I did with the read method.
 **/
//End-Part-5
    }

//Part-2
/**
        In that code, you'll remember, it deadlocked. For this reason, I added wait, and notify all, in both synchronized
 methods of the shared access object, the MessageRepository class. Now, let's see how we'd start implementing this, with
 an explicit locking mechanism, using a class that implements Lock. First, I'll add a lock to the MessageRepository class,
 as a private final field. It's type is the interface type, so simply Lock there. I'll name it lock with a lowercase L,
 and I'll set that to a new instance of a type called ReentrantLock. This lock is reentrant and mutually exclusive, so
 it has the same basic behavior as the intrinsic lock. I'll first remove the synchronized keyword from my read method.
 Before I do anything else, I'm going to run this, as it is.

             Exception in thread "Thread-0" java.lang.IllegalMonitorStateException : current thread is not owner
                    at java.base/java.lang.Object.notifyAll(Native Method)

 This gives me an exception on the MessageReader class, originating in the read method, where I call the wait method on
 this object. Because this method isn't synchronized, the thread that's reading the message, never acquires the monitor
 lock. You can't call wait or notifyAll methods, without exceptions being thrown as they are here, if your current thread
 doesn't have that lock, or is the owner of the lock. The Wait and notifyAll methods, are only used for the intrinsic lock.
 But also notice, my code is still running. I'll shut that down. This is a different problem altogether. My reader thread
 has had an exception, and has actually been shut down because of it. My writer thread is hanging out in its while loop,
 waiting for a reader thread to flip the flag, and that's now never going to happen. Let me show you a way to deal with
 this situation, when one thread gets an exception. It's not really related to explicit locks, but it could be important
 to know what to do here. The Thread class has a method on it called, setUncaughtExceptionHandler. This takes a single
 argument, an interface type, that has a single abstract method. That means, I can pass a lambda expression to it. This
 method, has two parameters, the current thread, and the exception that was thrown. I'll insert this code between the
 declaration of the threads, and the lines where I call start on each thread.
**/
//End-Part-2

    public void write(String message) {

        try {
            if (lock.tryLock(3, TimeUnit.SECONDS)) {
                try {
                    while (hasMessage) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    hasMessage = true;
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("** write blocked " + lock);
                hasMessage = true;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.message = message;
    }
}

//Part-6
/**
        First thing I'll do is, remove the synchronized keyword. Next, I'll add the try block, after that, I'll include
 the finally block, which ensures that I'll always release the lock. So I'll call unlock in this block. And now, I'll
 start with an if block. Just like the read method, I'll try to acquire the lock before proceeding. Before I add the else
 statement, I'll remove the notifyAll call. Inside the else, meaning If the lock can't be acquired, I'll just print a
 message that this thread, the write process, is blocked, and then I'll set hasMessage to true. Again, there's a chance,
 that by doing this, my code might skip over a write method. It's possible, that maybe in the write message, it's more
 critical to write the message reliably, but you can see, this method lets me decide how to code this. I'll just code it
 this way, to make sure my code won't deadlock. Finally, I'll replace the wait method call, with Thread dot sleep 500,
 so this method will only poll the status flag every half second, instead of as many times as it can. Ok, so let's run
 this.

                 ** read blocked
                 Humpty Dumpty sat on a wall,
                 Humpty Dumpty had a great fall,
                 All the king's horses and all the king's men,
                 ** read blocked
                 Couldn't put Humpty together again.
                 Finished

 You can see, that although sometimes our Producer and Consumer code couldn't get a lock, it still manages to come out ok.
 This may not always be true, depending on the timing of the reads and the writes, but the code doesn't lock the application
 up. Still, this might not be an acceptable solution, if messages do eventually get skipped. Another option, is to use
 the overloaded version of the tryLock method. This takes a number, and a time unit value, as arguments. I'll do this for
 the write method. I'll pass 3, and TimeUnit.SECONDS to this method. Ok, so this means this code will wait here, up until
 three seconds have passed, attempting to acquire the lock from the Consumer code. Unlike the tryLock method that has no
 arguments, this one throws a checked exception, so I'll wrap it in a try catch. I'll catch the Interrupted Exception,
 and I'll throw it as a Runtime Exception instead. That will wrap all this code in another try block, so this code isn't
 pretty. It's a bit more work, and requires more design processes, to figure out how to handle these situations. So why
 does this throw an InterruptedException anyway? Well, unlike the simpler tryLock method, it's possible to interrupt this
 thread, before it times out. This might be something you'd want to do, if you've identified some problem, and really
 don't want this thread to keep trying at this point. If I run this code,

                 Humpty Dumpty sat on a wall,
                 ** read blocked
                 Humpty Dumpty had a great fall,
                 ** read blocked
                 All the king's horses and all the king's men,
                 Couldn't put Humpty together again.
                 Finished

 you might notice there's a bit of a wait, before my first or second message, because the reader got the lock before the
 writer. The writer is going to wait up to three seconds to attempt to acquire that lock. In addition to these methods,
 the Reentrant lock can offer invaluable information about the lock. To show you this, first I'll go to the main method,
 **/
//End-Part-6

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

        Thread reader = new Thread(new MessageReader(messageRepository),"Reader");
        Thread writer = new Thread(new MessageWriter(messageRepository),"Writer");

//Part-7
/**
        And pass thread names to both of my thread constructors. The names will be Reader and Writer. Now, I'll go back
 to the read message on the MessageRepository. In that final else clause, in the println statement where I'm printing
 read blocked, I'll include the lock itself. I'll do the same thing in the write method. I'll run this now.

                 ** read blocked java.util.concurrent.locks.ReentrantLock@7c045af9[Locked by thread Writer]
                 Humpty Dumpty sat on a wall,
                 Humpty Dumpty had a great fall,
                 ** read blocked java.util.concurrent.locks.ReentrantLock@7c045af9[Locked by thread Writer]
                 All the king's horses and all the king's men,
                 Couldn't put Humpty together again.
                 Finished

 Now, in addition to knowing the lock has already been acquired, you can see exactly which thread actually does have the
 lock, which of course, is just the opposite thread in this case. You can imagine this would be useful information, if
 you were managing many threads for example. I'll pull up the documentation for this lock class, the ReentrantLock to the
 method summary. You can see, there are quite a few more methods, than the six methods declared on the Lock interface.
 Many of these other methods let us get information about the lock, and its owner. You can see a series of get methods
 here, getHoldCount, getOwner, though it's protected, we saw the result of that in the toString method. There's getQueuedThreads,
 getQueueLength, and a couple that take Condition as an argument. I'm not going to cover too many of these methods in
 this lecture. As we progress through the rest of this section, I'll have the opportunity to cover some of them then.
 This should give some idea, that these explicit locks offer information about the state of the lock, that you can't get
 easily from the monitor lock. I do however, want to talk to you about the hold count.

                                                    Lock Hold Count

        The hold count of a lock counts the number of times that a single thread, the owner of the lock, has acquired the
 lock. Maybe that doesn't seem to make sense, because you might imagine this is always one. But remember, in this case,
 the lock is reentrant.

    * When a thread acquires a lock for the first time, the lock's hold count is set to one.
    * If a lock is re-entrant, and a thread, in a subsequent method call for example, reacquires the same lock, the lock's
    hold count will get incremented.
    * When a thread releases a lock, the lock's hold count is decremented.
    * "A lock is only released when its hold count becomes zero".

 Because of this, it's really important to include a call to the unlock method in a finally clause, of any code that will
 acquire a lock, even if it's re-entrant.

        With explicit locks, you're able to have

    * "Explicit Control" over when to acquire and release locks, making it easier to avoid deadlocks, and manage other
    concurrency challenges.
    * Locks provide mechanisms for "timeouts" which allow you to attempt to acquire a lock without blocking indefinitely.
    * Along with timeouts, you can also have "Interruptible Locking" which lets you handle interruptions during acquisition
    more gracefully.
    * You've also seen from some of the methods on the ReentrantLock, there's "Improved Debugging" methods that let you
    query the number of waiting threads, and check if a thread holds a lock.

 In addition to these advantages, there are other more complex locking topics, such as condition variables, and custom
 lock implementations. While the explicit Lock offers all of these advantages, you'll want to use it judiciously. In many
 cases, the monitor lock is sufficient, and easier to use, for basic synchronization needs. However, when dealing with
 more complex concurrency scenarios, you may need fine-grained control, and explicit locks can be another tool, to ensure
 thread safety. In addition to locks, the java.util.concurrent package offers us concurrent collections, and classes for
 managing a group of concurrent threads. Next, I want to start talking about those classes for managing threads. These
 implement an Interface appropriately called the Executor.
 **/
//End-Part-7

        writer.setUncaughtExceptionHandler((thread, exc) -> {
            System.out.println("Writer had exception: " + exc);
            if (reader.isAlive()) {
                System.out.println("Going to interrupt the reader");
                reader.interrupt();
            }
        });

        reader.setUncaughtExceptionHandler((thread, exc) -> {
            System.out.println("Reader had exception: " + exc);
            if (writer.isAlive()) {
                System.out.println("Going to interrupt the writer");
                writer.interrupt();
            }
        });

//Part-3
/**
        First, I'll call this method on writer, and I'll pass it an inline multi-line lambda expression. I need two parameters,
 thread, and exception, so I'll type exc for short. I'll print that my writer thread got an exception, and what it is.
 Next, I'll check the reader thread. If it's still alive, I'll print that I'm going to interrupt it. And I'll call interrupt
 on the reader thread. I'll copy that whole statement, and paste that, just below it, again before calling start on either
 thread. I'll change writer to reader, in the first two lines, so this is setting an uncaught exception handler on reader
 now, and I'll print that Reader had the exception. But after this, I want the reader thread, to communicate with the
 writer thread, so I'll change reader.isAlive to writer.isAlive. I'll change my statement to say this is going to interrupt
 the writer, and finally I'll call interrupt on writer not reader. I'll run this.

         Reader had exception: java.lang.IllegalMonitorStateException: current thread is not owner
         Going to interrupt the writer
         Writer had exception: java.lang.RuntimeException: java.lang.InterruptedException

 I see that I get the same exception, but now the reader thread, is going to shut down my writer before it exits. The writer
 thread prints it had an interrupted exception, and shuts down smoothly. The code doesn't just hang. Now, I could have
 maybe started up a new reader thread in this instance. But it would just shut down again with the same exception, and
 it'll keep doing that, until I fix my application code, to address that exception. In truth, you probably won't be writing
 much code like this, managing one thread when another has an exception. In upcoming lectures, I'll be covering classes
 that exist to do just this kind of thing and more. They manage all kinds of thread interactions like this. But it doesn't
 hurt to know this simple solution, for situations like this, where you might have a one to one dependency on your threads.
 Ok, this was a good opportunity to introduce you to the setUncaughtExceptionhandler method, but I really just want to
 go fix the read method.
 **/
//End-Part-3

        reader.start();
        writer.start();
    }
}
