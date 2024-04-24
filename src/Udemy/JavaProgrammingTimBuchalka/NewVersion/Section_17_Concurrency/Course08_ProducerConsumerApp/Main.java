package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_17_Concurrency.Course08_ProducerConsumerApp;

import java.util.Random;

//Part-1
/**
        In the last lecture, I covered Java's built-in locking mechanism, which is implicitly used, whenever you declare
 a synchronized method or statement. Next, I want to talk about the wait and notify methods, that are defined on the Object
 class, and used when threads acquire the monitor lock. To demonstrate this, I'll set up a Producer Consumer application.
 This kind of application has a class that produces data, so the Producer. It also has a class that reads the data, or
 consumes it in some way, this is the Consumer. I've created a new project called ConsumerProducer for this. Before I
 code anything in my main method, I'll create a couple of other classes. The first, I'll call MessageRepository, and I'll
 put this in the Main.java source file.
**/
//End-Part-1

class MessageRepository {

    private String message;
    private boolean hasMessage = false;

    public synchronized String read() {

        while (!hasMessage) {

        }
        hasMessage = false;
        return message;
    }

    public synchronized void write(String message) {

        while (hasMessage) {

        }
        hasMessage = true;
        this.message = message;
    }
}

//Part-2
/**
        This class is going to hold a single message. I'll place this class just above the Main class. Both the Producer
 and consumer will interact with the message, so it's a shared resource. I'll include a boolean field, called hasMessage,
 which will indicate to both threads, whether there is work for them to do. When hasMessage is false, the Producer can
 populate the shared message. When has message is true, the Consumer can read it. Next, I need methods that read the message,
 as well as write to it, or populate it. Because the message is a shared resource, I'll synchronize the read method. When
 the consumer class calls this method, it will wait until there's a message to read. I'll set this up with a while loop
 on the Message flag. If there's no message, it will stay in this while loop. I'll just make this an empty block. Once
 the message is successfully retrieved, this code will set hasMessage to false. Finally, the method returns the message
 to the consumer, who's waiting for it. The write method will also be synchronized, and it takes a String as an argument.
 If there's already a message in the message repository, it will hang out and wait, presumably until the Consumer has a
 chance to read the message, and set this flag to false. Once there's no more message, meaning it was consumed, the hasMessage
 flag will be set back to true, because this code sets a new message. I'll set that message here, as the final step. Ok,
 so that's the shared resource that both the Consumer and Producer are interested in. Now, I'll write the Producer class,
 which I'm going to call MessageWriter.
 **/
//End-Part-2

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

//Part-3
/**
        This class will implement Runnable. I'm getting an error on the class, because I need to declare the run method,
 so I'll do that now. Before I implement the run method, I'll add a couple of fields. The first field will be a MessageRepository
 item, and I'll call that outgoingMessage. For the source of the messages, I'll use a nursery rhyme, setting it up as a
 text block. And you probably know this nursery rhyme, "Humpty Dumpty sat on a wall, Humpty Dumpty had a great fall". And
 I'll just finish this off. All the king's horses and so on. Couldn't put Humpty together again. I'll create a constructor
 next, and that'll take a message repository as its argument. I'll get IntelliJ to generate it for me. Ok, so those are
 the fields, and the constructor. Next, I'll implement the run method. I'll use random, to randomize the time that the
 thread sleeps. I can get the lines of text, by splitting the text block by the newline character. I'll loop through the
 lines, using a for loop. I'll write each line of text, as a new message, to the outgoingMessage Repository instance. I'll
 call the sleep method next, so I need a try block here. I'll wait between half a second and two seconds, a randomly chosen
 interval. This means this code writes a message, and waits a bit before writing the next. This gives the consumer a bit
 of time to read it. I'll catch the Interrupted Exception thrown by the sleep method. If this does get thrown, I'll wrap
 it in a Runtime Exception and rethrow it. Ok, so that does it for the Producer class. Next I'll work on the consumer class.
 I'll call it MessageReader, and again, I'll make this class implement Runnable.
 **/
//End-Part-3

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

//Part-4
/**
        This class will also be in the Main.java source file, and I'll place it just above the Main class. When I add the
 implements Runnable clause, it gives me the same error message as before, so I'll get IntelliJ to generate the override,
 for the run method. I'll do the same thing I did with the Message Writer class, so I'll add an incoming message field,
 before I implement the run method. The field is going to be a Message Repository type. Once again, I'll get IntelliJ to
 generate my constructor for this class. Now the only thing left to do for this class, is to implement the run method.
 I'll again use random, to randomize the sleep time. I'll set the latest Message to an empty String. Here, I'll set up a
 do while loop. I'll include a try catch block, because I know I'm going to need it. Inside the try block, I'll put the
 thread to sleep first. This gives the writer a bit of time to get its message out there, before this code attempts to
 read it. I'll include the usual Interrupted Exception clause. After this, I'll read the incoming message, and then assign
 it to latest message. I'll print that out. The do while loop will keep looping, until latest message equals Finished.
 This is the message returned when the Producer is done writing out the nursery rhyme messages. Now that I've got both
 classes, the Consumer and the Producer, it's time to start them up, working asynchronously. Getting back to the Main
 class's main method,
 **/
//End-Part-4

public class Main {

    public static void main(String[] args) {

        MessageRepository messageRepository = new MessageRepository();

        Thread reader = new Thread(new MessageReader(messageRepository));
        Thread writer = new Thread(new MessageWriter(messageRepository));

        reader.start();
        writer.start();

//Part-5
/**
        I'll first create an instance of my shared object. This is an instance of the MessageRepository class. Next, I'll
 want two threads, one for the Message Reader, and one for the Message Writer. I'll declare a Thread variable called reader.
 I'll assign it a Thread instance. I'll pass a message reader instance to the constructor of this thread. The MessageReader
 is constructed here too, and it's passed the messageRepository instance. I'll do something very similar for Message Writer.
 Both threads are working with the same instance of the MessageRepository class. I'll then call start on both variables.
 Let's give this a try now, so I'll run it.

                 Humpty Dumpty sat on a wall,
                 Humpty Dumpty had a great fall,
                 All the king's horses and all the king's men,
                 Couldn't put Humpty together again.

 Usually, I'll get one or two messages printed out, and then the code will just hang. You might get no messages, or you
 might actually have the code complete, with no issues at all. This is the maddening effect, of a concurrent application,
 that has a problem. Sometimes it's really hard to reproduce multi-threading problems, because it's based on thread
 management by an operating system. Obviously I shouldn't have to wait much longer than 2 seconds for something to happen,
 so I know I've got a problem. I'll quit the application when it does hang, and I'll try it a couple more times. Each time
 the output is a little bit different, but it usually locks up. Maybe you already can guess why it's hanging here. There
 are several situations that can occur in a multi-threaded application, that are pretty undesirable. These situations are
 called deadlock, live lock, and starvation, and later in this section of the course, I'll talk about them in more detail,
 and compare them to each other. In this particular case, I've created an application, that can produce a deadlock.

                                                    Deadlock

        A deadlock usually occurs, when you have two or more threads accessing multiple shared resources, and I'll cover
 that scenario in a later lecture. But it can also occur in the scenario we've just seen, in the case of a single resource
 with multiple synchronized methods.
                                                                                    ____________________________________________________
                                                                                    | public synchronized void write(String message) { |
                                                                                    |                                                  |
                    _____________________________                                   |     while (hasMessage) {                         |
                    | Stuck in a while loop,    |                                   |     }                                            |
                    | waits for flag to be true |                                   |     hasMessage = true;                           |
                    |   never releases lock     |                                   |     this.message = message;                      |
                    |___________________________|                                   | }                                                |
                                                |                                   |_____________↑____________________________________|
                                                |                                                 ↑
                  Thread A          READ        |    Message        |         WRITE            Thread B
                  Consumer         >>>>>>>      |   Repository      |        <<<<<<<<          Producer
                     ↓                          | Lock = (ThreadA)  |
                     ↓                                              |
        _____________↓_________________________                     |________________________
        | public synchronized String read() { |                     |    Can't get lock,    |
        |                                     |                     | can't execute write,  |
        |     while (!hasMessage) {           |                     |  can't change flag    |
        |     }                               |                     |_______________________|
        |     hasMessage = false;             |
        |     return message;                 |
        | }                                   |
        |_____________________________________|

        ThreadA is our Consumer, the MessageReader. It can usually get in, to run the read method, because the hasMessage
 flag is usually true. So when that flag is true, it won't go into the while loop. But If for some reason, the flag is
 false, it will execute it's while loop, and that's where the problem lies. It's waiting on that hasMessage flag to change
 value, to exit the loop. But because of the way this code is currently written, that flag is never going to change its
 value. ThreadA has acquired a lock on the shared resource, in this case the Message Repository, and ThreadB can't get
 that lock, so it's blocked. Because ThreadB is blocked, it can't change the flag, that would set the condition to let
 ThreadA exit it's while loop, and release the lock. The threads are stuck, one spinning indefinitely, the other blocked
 from doing anything. This is a classic deadlock situation, and the resolution isn't always pretty. In this case, I have
 to shut down the application, or kill it manually. This situation could be equally true in reverse. This means the Producer
 could be in its while loop, waiting on the Consumer to flip the flag, but the Consumer can't get the lock to do it. So
 what can we do? I'll answer that question in the next lecture.
 **/
//End-Part-5
    }
}
