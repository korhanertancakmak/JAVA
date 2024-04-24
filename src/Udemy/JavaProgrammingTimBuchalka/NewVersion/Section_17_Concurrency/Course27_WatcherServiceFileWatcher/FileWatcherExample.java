package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_17_Concurrency.Course27_WatcherServiceFileWatcher;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class FileWatcherExample {

    public static void main(String[] args) throws IOException {

        WatchService watchService = FileSystems.getDefault().newWatchService();

        Path directory = Paths.get(".");
        WatchKey watchKey = directory.register(watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.ENTRY_DELETE);

//Part-2
/**
        I can get a file watcher service, for a FileSystem instance. In this case, I'll use the default file system, by
    calling the static method getDefault on the FileSystems class, and I'll chain a call to the new WatchService on that.
    I want to get a path, or directory, that I'm interested in watching. In this case, I'll just use the current working
    directory, so I'll put a single period there. The first step is to register with the watchService. The Path class,
    has a register method. I can pass this the watch Service, and some flags, which indicate the types of events I want
    to listen for. This method returns whats called a watch key, which you can think of as a handle to the service. In
    this case, I'll listen for file creation, modification and delete events. I'm getting a couple of errors here, so I'll
    hover over one of them. I need to either declare or handle this IO Exception, so I'll click on Add exception to method
    signature. And that gets rid of both of my errors. I'll keep adding some code.
 **/
//End-Part-2

        /*boolean keepGoing = true;                                                      // Commented via Part-4
        while (keepGoing) {

            try {
                watchKey = watchService.take();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            List<WatchEvent<?>> events = watchKey.pollEvents();

            for (WatchEvent<?> event : events) {
                Path context = (Path) event.context();
                System.out.printf("Event type: %s - Context: %s%n", event.kind(), context);
            }
            watchKey.reset();
        }*/

//Part-3
/**
        I'll set up a boolean to drive the while loop. I'll use that in my while loop expression. I'll need a try catch,
    because like any thread, the watchService may throw an interrupted exception. I'll query the watch service queue,
    using the take method, which returns another watchKey instance. And I'll include the usual interrupted exception
    catch clause. I can get a list of events, using poll events on the watchKey. I'll loop through these events, And
    I'll get the context of the event. In this case, the context is the Path of the file for which the event was registered.
    I'll print the event type and this context. Finally, a watch key has to be Reset. Don't worry if you don't get it.
    I'll walk through each of these steps in more detail. First, let's just run this and see what it does.



        You can see that it looks the application is running, but there's no output, and the application isn't exiting.
    That's because right now it's in the while loop, polling for an events that might happen on the current working directory.
    That's the root of my project. I'll create a new file here. I'll open the project panel, highlighting the project root,
    which is ConcurrencyExtras, and select new, then file, and I'll create a file called Testing.txt.

                        Event type: ENTRY_CREATE - Context: Testing.txt

        Not only did that open an editor window, but look at the output console. I've now got a print statement. You can
    see that I have output showing event type, entry create, because the file, Testing.txt was created. You may also see
    the.idea showing, which is an IntelliJ configuration file, here as well. That didn't show up for me but does on other
    Windows machines.

                        Testing...

        I'll put some text in this file. You may see more output statements show up immediately. For me, they don't show
    up until I move context from IntelliJ to another window like this. This probably relates to me recording my screen.

                         Event type: ENTRY_CREATE - Context: Testing.txt
                         Event type: ENTRY_CREATE - Context: Testing.txt~
                         Event type: ENTRY_CREATE - Context: Testing.txt~
                         Event type: ENTRY_CREATE - Context: Testing.txt
                         Event type: ENTRY_CREATE - Context: Testing.txt~

    You can see that IntelliJ creates a temporary file of the same name, with a tilda at the end, and that our original
    file, and this file were modified. And then the temp file was deleted. So IntelliJ has some underlying processes here.
    Now I'll go to the project panel and delete this file. I'm going to uncheck safe delete because not doing so will
    affect the running of the program.

                         Event type: ENTRY_CREATE - Context: Testing.txt
                         Event type: ENTRY_CREATE - Context: Testing.txt~
                         Event type: ENTRY_CREATE - Context: Testing.txt~
                         Event type: ENTRY_CREATE - Context: Testing.txt
                         Event type: ENTRY_CREATE - Context: Testing.txt~
                         Event type: ENTRY_CREATE - Context: Testing.txt

    Towards the end of the program I'll explain why. And here we see a similar thing in the output, but with an entry delete.
    So that's kind of fun I think. I have to manually shut this down right now. Lets add some code to shut down the watch
    service, when the test file is deleted.
 **/
//End-Part-3

        boolean keepGoing = true;
        while (keepGoing) {

            try {
                watchKey = watchService.take();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            List<WatchEvent<?>> events = watchKey.pollEvents();

            for (WatchEvent<?> event : events) {
                Path context = (Path) event.context();
                System.out.printf("Event type: %s - Context: %s%n", event.kind(), context);
                if (context.getFileName().toString().equals("Testing.txt") && event.kind() == StandardWatchEventKinds.ENTRY_DELETE) {
                    System.out.println("Shutting down watch service");
                    watchService.close();
                    keepGoing = false;
                    break;
                }
            }
            watchKey.reset();
        }

//Part-4
/**
        Here, I'll check that the file name, of the file being deleted, is Testing.txt, and the type of event is an ENTRY
    DELETE. I'll print that the watch service is shutting down. Then, I'll close the watchService. And I'll set the keep
    going flag, to false, so the code exits from the while loop. I'll run this code, and go through the same process.
    I'll create the file, Testing.txt, in the root of this project.

                        Event type: ENTRY_CREATE - Context: Testing.txt

    I'll add some text to the file again.

                        Testing...

    Again I'll change context to another window so that the output appears.

                         Event type: ENTRY_CREATE - Context: Testing.txt
                         Event type: ENTRY_CREATE - Context: Testing.txt~
                         Event type: ENTRY_CREATE - Context: Testing.txt~
                         Event type: ENTRY_CREATE - Context: Testing.txt
                         Event type: ENTRY_CREATE - Context: Testing.txt
                         Event type: ENTRY_CREATE - Context: Testing.txt~

    For you it may appear automatically. Then, I'll delete the file.

                         Event type: ENTRY_CREATE - Context: Testing.txt
                         Event type: ENTRY_CREATE - Context: Testing.txt~
                         Event type: ENTRY_CREATE - Context: Testing.txt~
                         Event type: ENTRY_CREATE - Context: Testing.txt
                         Event type: ENTRY_CREATE - Context: Testing.txt
                         Event type: ENTRY_CREATE - Context: Testing.txt~
                         Event type: ENTRY_CREATE - Context: Testing.txt
                         Shutting down watch service

    Now, the service shuts down smoothly. Let's dissect this code in more detail.
 **/
//End-Part-4

    }
}


//Part-5
/**
        The first statement in this code, gets a WatchService,

                     WatchService watchService = FileSystems.getDefault().newWatchService();

    in this case it's a service that lets us monitor file system changes.

                     WatchKey watchKey = directory.register(watchService,

    A WatchKey is what the Java documentation calls a token. It represents a watchable object's registration, with a
    WatchService. You can see this is returned, from the register method on Path. Let's examine this with a diagra.

                                                   / \
                                                 /     \
                                               /         \
                                             / Initialized \
                                             \             /
                                               \         /
                                                 \     /
                                                   \ /
                                                    ↓
                                                    ↓
                    / \                             ↓  READY STATE
                  /     \                           ↓
                /         \                         ↓
              /    Reset    \→→→→→→→→→→→→→→→→→→→→→→
              \             /                       ↓
                \         /                         ↓
                  \     /                           ↓
                    \ /                             ↓           Queued          ___________________
                                                   / \ →→→→→→→→→→→→→→→→→→→→→→→→ | WatchService    |
                                                 /     \                        |-----------------|
          ___________________                  /         \                      | WatchKey        |
          | FileSystem Path |                /    Event    \                    |  .......        |         ___________________
          |-----------------|     EVENT      \   Occurs    /                    |  .......        |         | WatchKey        |
          | create          |→→→→→→→→→→→→→→→→→ \         /                      |_________________|         |-----------------|
          | update          |                    \     /                                                    | WatchEvent      |
          | delete          |                      \ /→→→→→→→→→→→→→→→→                                      | WatchEvent      |
          |_________________|                            SIGNALLED STATE                                    | WatchEvent      |
                                                                                                            |_________________|

    When initially created, the WatchKey is said to be "ready". When an event is detected, then the WatchKey is "signalled",
    a special state, which means it can be polled. A Watch key gets queued at this point, on the WatchService, so that
    it can be retrieved, by invoking the watch service's poll or take methods. Once signalled, a WatchKey remains in this
    state, until its reset method is invoked. When that happens, the WatchKey returns to the ready state. Events detected
    while the key is in the signalled state, are added to the WatchKey, but they don't cause the key to be re-queued for
    retrieval, on the watch service. So not only do we get a WatchService when we register a resource, but additional watch
    keys are queued when events occur. A Watch Key has a list events, that occurred while the WatchKey was in the signalled
    state. Each WatchEvent object represents a specific change, to the watchable object.

                                     watchKey = watchService.take();

        Getting back to the code, notice I get a WatchKey from the WatchService, using watchService.take. I could have
    also used the poll method. If there's nothing on the queue, the code will wait here, until something is added. If
    something's on the queue, this method gets the element, removing it from the queue.

                            List<WatchEvent<?>> events = watchKey.pollEvents();

    Once I have this Signalled WatchKey, I next get the WatchEvents from that WatchKey, using the method called PollEvents.

                             for (WatchEvent<?> event : events) {
                                 Path context = (Path) event.context();
                                 System.out.printf("Event type: %s - Context: %s%n", event.kind(), context);
                                 if (context.getFileName().toString().equals("Testing.txt") && event.kind() == StandardWatchEventKinds.ENTRY_DELETE) {
                                     System.out.println("Shutting down watch service");
                                     watchService.close();
                                     keepGoing = false;
                                     break;
                                 }
                             }
                             watchKey.reset();

    Here, I loop through all the events, print some information about each, as you saw. The last thing I do in the while
    loop, is reset the watch key, meaning all the data's been processed. Now the while loop will wait on the take method,
    until something new is added to the queue. I had promised you, I'd cover this functionality when we were reviewing
    file access, and I want to encourage you to play around with this. There are many uses for it, and you can sort of
    imagine how a tool like IntelliJ might be using something like it, to create an interactive environment for us. You
    can use a Watch Service on other resources, besides files and directories, like databases, message queues, shared memory
    regions, and other types of resources.

        Right, so I'm going to run the program one last time. I'll quickly go through the steps of creating the Testing.txt
    file and putting some content in it. I'll delete the file, but this time I'll leave Safe Delete on. After clicking OK,
    I now get this extra prompt, where IntelliJ is showing whats going to be deleted. This is very useful for larger programs,
    to make sure that you are not making undesirable changes, elsewhere in your program. I recommend leaving safe delete
    on. But when running this particular program, if I click the Do Refactor button now, you will see it actually closes
    the program. Not what we wanted.
 **/
//End-Part-5