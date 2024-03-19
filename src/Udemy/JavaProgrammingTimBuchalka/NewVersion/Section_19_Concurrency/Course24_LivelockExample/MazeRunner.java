package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_19_Concurrency.Course24_LivelockExample;

import java.util.Arrays;
import java.util.concurrent.Executors;

record Participant(String name, String searchingFor, Maze maze, int[] startingPosition) {
    Participant {
        maze.moveLocation(startingPosition[0], startingPosition[1], name);
    }
}

//Part-8
/**
        I'll include a couple of extra types in this source file. The first will be a Participant record. This record has
 a name, a searchingFor string which is the partner's name. It will also take a maze, and an integer array, the starting
 position. I'll create a compact constructor. This is just going to set the participation's location on the maze. That's
 all I need for the Participant record. Next, because I need data from this participant, to execute the search, I'll create
 a thread class.
 **/
//End-Part-8

class ParticipantThread extends Thread {
    public final Participant participant;

    public ParticipantThread(Participant participant) {
        super(participant.name());
        this.participant = participant;
    }

//Part-9
/**
        I'll do this right after this record, and call it ParticipantThread. I'll have this class extend the Thread class.
 I need a constructor to set the value of this field, so I'll generate that, using Alt insert. In the first dialog, I'll
 select the Thread with no arguments, And on the second dialog, I'll take the default, so the participant. Since I want
 my thread to have the same name, as my participant, I'll chain a call to the thread's super constructor. I'll pass the
 participant's name to that. Now, I need to override the run method.
**/
//End-Part-9

    @Override
    public void run() {

        int[] lastSpot = participant.startingPosition();
        Maze maze = participant.maze();

//Part-10
/**
        I'll just do this manually. I'll include the override annotation. The signature is public void run. I'll set a
 variable, lastSpot, to the participant's starting position. I'll assign the maze to a variable as well, just to make the
 code easier to read. I'll start a while loop that will continue to run until a successful search has been done. I'll get
 the next location, based on the last spot of my participant. I'll print that the participant is searching, and print the
 maze.
 **/
//End-Part-10

        while (true) {
            int[] newSpot = maze.getNextLocation(lastSpot);
            // Searching code
            try {
                //Thread.sleep(500);                                                    // Commented via Part-15
                Thread.sleep(participant.name().equals("Grace") ? 2900 : 500);          // Uncommented via Part-15
                if (maze.searchCell(participant.searchingFor(), newSpot, lastSpot)) {
                    System.out.printf("%s found %s at %s!%n", participant.name(), participant.searchingFor(), Arrays.toString(newSpot));
                    break;
                }
                //synchronized (maze) {                                                 // Commented and uncommented via Part-15
                synchronized (maze) {
                    maze.moveLocation(newSpot[0], newSpot[1], participant.name());
                //}                                                                     // Commented and uncommented via Part-15
                }
                lastSpot = new int[]{newSpot[0], newSpot[1]};
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            // Searching code End
            System.out.println(participant.name() + " searching " + participant.maze());
        }
    }
}

//Part-11
/**
        Between getting the new spot, and the println statement, I need to actually add the code that does the searching.
 I need a try clause, because I'll be using Thread.sleep. I want this thread to pause a half a second before it's next
 search. I'll call the searchCell method on the maze, passing the name of the person this thread should be looking for.
 I'll also pass the newSpot or position and the last spot. If this method returns a true, I'll print out information about
 the successful search, like the person doing the search, the person they're looking for, and the position they were found
 at. Once the person is found, this thread can stop running, so I'll break out of this while loop. Here, I'll add a
 synchronized block, on the shared maze, because moveLocation is going to change the maze values. If no match was made,
 then this player will move to the next location, which I'll get by calling maze.move location. I'll save off the last
 spot. I need the usual catch block. This time though, I'll reassert the interrupt. Without this, the thread may not be
 properly interrupted. I'll return from this code, if this happens. Ok, now I've got all the parts to run this thing.
 **/
//End-Part-11

public class MazeRunner {

    public static void main(String[] args) {

        Maze maze = new Maze();
        Participant adam = new Participant("Adam", "Grace", maze, new int[]{3, 3});
        Participant grace = new Participant("Grace", "Adam", maze, new int[]{1, 1});

        System.out.println(maze);

//Part-12
/**
        I'll instantiate a new maze. I'll create a new participant, adam, whose name is Adam, and his partner, or the
 person he'll be searching for, is Grace. I'll pass the maze, and Adam's position in the maze. The next participant will
 be grace, searching for adam, and her position. I'll run this, so you can see what the maze looks like at the start.

                [[, , , ], [, G, , ], [, , , ], [, , , A]]

 From this output, you can see the entire grid, printed as a two dimensional array, on a single line of output. You can
 see that Grace is in the second row, and the second cell, which is 1 1. And Adam is in the last cell of the grid, 3 3.
 To help you understand what this code is doing, I'll run each thread on its own first. For this example, I'll use my own
 advice, and use an ExecutorService to manage these threads.
 **/
//End-Part-12

        /*var executor = Executors.newCachedThreadPool();
        //var adamsResults = executor.submit(new ParticipantThread(adam));
        var gracesResults = executor.submit(new ParticipantThread(grace));

        executor.shutdown();*/

//Part-13
/**
        I'll get a cached thread pool from the Executors class. I want to check the status of the running threads, so
 I'll use executor.submit. Remember this method returns a Future, so I'll assign that to a variable called Adams Results.
 I'll pass this method a new participant thread created with my adam participant. I need to call shutdown on my executor.
 I'll run this.

             [[, , , ], [, G, , ], [, , , ], [, , , A]]
             Adam searching [[A, , , ], [, G, , ], [, , , ], [, , , !G]]
             Adam searching [[!G, A, , ], [, G, , ], [, , , ], [, , , !G]]
             Adam searching [[!G, !G, A, ], [, G, , ], [, , , ], [, , , !G]]
             Adam searching [[!G, !G, !G, A], [, G, , ], [, , , ], [, , , !G]]
             Adam searching [[!G, !G, !G, !G], [A, G, , ], [, , , ], [, , , !G]]
             Adam found Grace at [1, 1]!

 You can see the thread working, printing that Adam is Searching. Adam started at the last cell in the grid, and obviously
 Grace wasn't there, so this was marked as !G, and Adam was moved to the first cell in the grid. Each time Adam moves,
 the cell he was in, he can mark that Grace wasn't there. This continues until he actually gets to the cell where Grace
 is. At that point the thread prints that Adam found Grace, and gives her position, and the thread stops running. I'll
 comment out that submit statement. I'll execute the next thread, with Grace as the participant. I'll run this.

             [[, , , ], [, G, , ], [, , , ], [, , , A]]
             Grace searching [[, , , ], [, !A, G, ], [, , , ], [, , , A]]
             Grace searching [[, , , ], [, !A, !A, G], [, , , ], [, , , A]]
             Grace searching [[, , , ], [, !A, !A, !A], [G, , , ], [, , , A]]
             Grace searching [[, , , ], [, !A, !A, !A], [!A, G, , ], [, , , A]]
             Grace searching [[, , , ], [, !A, !A, !A], [!A, !A, G, ], [, , , A]]
             Grace searching [[, , , ], [, !A, !A, !A], [!A, !A, !A, G], [, , , A]]
             Grace searching [[, , , ], [, !A, !A, !A], [!A, !A, !A, !A], [G, , , A]]
             Grace searching [[, , , ], [, !A, !A, !A], [!A, !A, !A, !A], [!A, G, , A]]
             Grace searching [[, , , ], [, !A, !A, !A], [!A, !A, !A, !A], [!A, !A, G, A]]
             Grace found Adam at [3, 3]!

 Now, the thread has Grace searching, and you can see the G moving through the grid, each cell she was at previously marked
 as !A, meaning Adam wasn't there. And she too eventually manages to find Adam. This code works great, when the person
 being searched for, never moves. But what happens when they do? If Grace is looking for Adam, and he moves to another
 cell, it's a valid statement to say that all her previous work is useless. She cannot say with any certainty, after he
 moves, that the cells she looked in previously, are Adam free. That's why my maze reset will reset all cells, that have
 a !A in them, if Adam moves. And it'll do the same if Grace moves, invalidating or removing, the !G setting, in this maze.
 Let's run our two searching participants asynchronously.
 **/
//End-Part-13

        /*var executor = Executors.newCachedThreadPool();
        var adamsResults = executor.submit(new ParticipantThread(adam));
        var gracesResults = executor.submit(new ParticipantThread(grace));

        while (!adamsResults.isDone() && !gracesResults.isDone()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if (adamsResults.isDone()) {
            gracesResults.cancel(true);
        } else if (gracesResults.isDone()) {
            adamsResults.cancel(true);
        }

        executor.shutdown();*/

//Part-14
/**
        I'll uncomment the submit statement that kicks off Adam searching. Before I run this, I'll set up the situation
 that if one thread completes, it will shut down the other. I'll set up a while loop that will check both adamsResults
 and gracesResults. If both return false from the isDone method, that means they're still running. I need a try block,
 So I can call Thread.sleep for 1 second. And the usual catch goes here. If I get to this point, then one of the threads
 was successful, and the other thread should stop searching. So if adam is done, I'll cancel graces thread, passing true
 to the cancel method, which means I want the currently running thread to be interrupted. I'll do the same thing in reverse
 if grace is done, cancelling adams search. Ok, let's try this out. I'll run this.

                 [[, , , ], [, G, , ], [, , , ], [, , , A]]
                 Adam searching [[A, , , ], [, , G, ], [, , , ], [, , , !G]]
                 Grace searching [[A, , , ], [, , G, ], [, , , ], [, , , ]]
                 Adam searching [[, A, , ], [, , , G], [, , , ], [, , , ]]
                 Grace searching [[, A, , ], [, , , G], [, , , ], [, , , ]]
                 Adam searching [[, , A, ], [, , , ], [G, , , ], [, , , ]]
                 Grace searching [[, , A, ], [, , , ], [G, , , ], [, , , ]]
                 Grace searching [[, , , A], [, , , ], [, G, , ], [, , , ]]
                 Adam searching [[, , , A], [, , , ], [, G, , ], [, , , ]]
                 Grace searching [[, , , A], [, , , ], [, !A, G, ], [, , , ]]
                 Adam searching [[, , , !G], [A, , , ], [, , G, ], [, , , ]]
                 Grace searching [[, , , ], [, A, , ], [, , , G], [, , , ]]
                 Adam searching [[, , , ], [, A, , ], [, , , G], [, , , ]]
                 Grace searching [[, , , ], [, A, , ], [, , , !A], [G, , , ]]
                 Adam searching [[, , , ], [, !G, A, ], [, , , ], [G, , , ]]
                 Adam searching [[, , , ], [, !G, !G, A], [, , , ], [!A, G, , ]]
                 Grace searching [[, , , ], [, , , A], [, , , ], [!A, G, , ]]
                 Adam searching [[, , , ], [, , , !G], [A, , , ], [, !A, , ]]
                 Grace searching [[, , , ], [, , , ], [A, , , ], [, !A, G, ]]
                 Grace searching [[, , , ], [, , , ], [A, , , ], [, !A, !A, G]]
                 Adam searching [[, , , ], [, , , ], [!G, A, , ], [, , , G]]
                 Adam searching [[, , , ], [, , , ], [!G, !G, A, ], [, , , ]]
                 Grace searching [[G, , , ], [, , , ], [, , A, ], [, , , ]]
                 Grace searching [[!A, G, , ], [, , , ], [, , , A], [, , , ]]
                                             ....
                                          (no stop)

 And I'll let this run for about 20 or 25 statements, then I'll quit the application. You can see now, that Adam and Grace
 are both searching for each other, and they're both on the move. The threads are active. Each thread is changing the state
 of the maze, which means each thread keeps searching, and will keep searching indefinitely, because each is moving linearly,
 always away from each other, and at the same pace. I've created a liveLock. My threads aren't blocked, they're just
 continually working, responding to the maze's state that was changed by another thread, in a loop that may never end.
 Actually, you can have this problem without locking.
 **/
//End-Part-14

        var executor = Executors.newCachedThreadPool();
        var adamsResults = executor.submit(new ParticipantThread(adam));
        var gracesResults = executor.submit(new ParticipantThread(grace));

        while (!adamsResults.isDone() && !gracesResults.isDone()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if (adamsResults.isDone()) {
            gracesResults.cancel(true);
        } else if (gracesResults.isDone()) {
            adamsResults.cancel(true);
        }

        executor.shutdown();

//Part-15
/**
        I'll comment out the synchronized block for a moment in ParticipantThread class's run method. I'll re-run this,

                 [[, , , ], [, G, , ], [, , , ], [, , , A]]
                 Grace searching [[A, , , ], [, !A, G, ], [, , , ], [, , , ]]
                 Adam searching [[A, , , ], [, !A, G, ], [, , , ], [, , , !G]]
                 Adam searching [[, A, , ], [, , !A, G], [, , , ], [, , , ]]
                 Grace searching [[, A, , ], [, , !A, G], [, , , ], [, , , ]]
                 Adam searching [[, , A, ], [, , , ], [G, , , ], [, , , ]]
                 Grace searching [[, , A, ], [, , , ], [G, , , ], [, , , ]]
                 Grace searching [[, , !G, A], [, , , ], [!A, G, , ], [, , , ]]
                 Adam searching [[, , !G, A], [, , , ], [!A, G, , ], [, , , ]]
                 Grace searching [[, , , ], [A, , , ], [!A, !A, G, ], [, , , ]]
                 Adam searching [[, , , ], [A, , , ], [!A, !A, G, ], [, , , ]]
                 Adam searching [[, , , ], [!G, A, , ], [, , , G], [, , , ]]
                 Grace searching [[, , , ], [, A, , ], [, , , G], [, , , ]]
                 Grace searching [[, , , ], [, , A, ], [, , , ], [G, , , ]]
                 Adam searching [[, , , ], [, , A, ], [, , , ], [G, , , ]]
                 Grace searching [[, , , ], [, , , A], [, , , ], [, G, , ]]
                 Adam searching [[, , , ], [, , , A], [, , , ], [, G, , ]]
                 Adam searching [[, , , ], [, , , ], [A, , , ], [, , G, ]]
                 Grace searching [[, , , ], [, , , ], [A, , , ], [, , G, ]]
                 Grace searching [[, , , ], [, , , ], [, A, , ], [, , , G]]
                 Adam searching [[, , , ], [, , , ], [, A, , ], [, , , G]]
                 Adam searching [[G, , , ], [, , , ], [, !G, A, ], [, , , ]]
                                               ....
                                             (no stop)

 and you can see I get the same situation. In actuality, the locking code might have a better shot of getting out of the
 live lock. If one thread might block, for a little bit waiting on the lock, then the other thread might be able to move
 closer to success. I'll kill this again. I'm going to revert the last change, where I commented out the synchronization
 code. Live locks can be difficult to debug and fix. For this reason, there are a few general things you can do to avoid
 them:

    * Avoid having threads that are constantly checking each other's states. If possible, design your code so that threads
    can make progress independently of each other.
    * Use timeouts to prevent threads from waiting indefinitely for each other.
    * Use randomization to break the symmetry between threads. For example, if two threads are both trying to acquire the
    same resource, you can have them randomly decide which thread should acquire the resource first.

 As I demonstrated, the problem in this code isn't due to the synchronization code, or locks. One solution which might
 help, is to execute the search, but randomize the search time a little bit. In this code, our partners are sort of in
 lock step, both moving forward at the exact same rate. Let's have each thread wait a different amount of time. You wouldn't
 hard code this in a real game, but I'll just do it here, to make the result more consistent, than trying a purely random
 solution.

                 Thread.sleep(500);
                        to
                 Thread.sleep(participant.name().equals("Grace") ? 2900 : 500);

 I'll use a ternary operator, if the name is grace, I'll wait 2900 milliseconds, otherwise, it will only wait 500 milliseconds.
 I'll run the code with this change.

             [[, , , ], [, G, , ], [, , , ], [, , , A]]
             Adam searching [[A, , , ], [, G, , ], [, , , ], [, , , !G]]
             Adam searching [[!G, A, , ], [, G, , ], [, , , ], [, , , !G]]
             Adam searching [[!G, !G, A, ], [, G, , ], [, , , ], [, , , !G]]
             Adam searching [[!G, !G, !G, A], [, G, , ], [, , , ], [, , , !G]]
             Adam searching [[!G, !G, !G, !G], [A, G, , ], [, , , ], [, , , !G]]
             Grace searching [[, , , ], [A, !A, G, ], [, , , ], [, , , ]]
             Adam searching [[, , , ], [!G, A, G, ], [, , , ], [, , , ]]
             Adam found Grace at [1, 2]!

 Since Grace is waiting almost all the way up to the 3 second max, and Adam is moving every half a second, he's able to
 catch her. Obviously, in an application maze, this code isn't the right solution. You could even argue a live lock here,
 makes the game more challenging. Still, I hope you were able to see from this example, that one thread's actions, can
 effect another thread in such a way, that completing the tasks is nearly impossible. That's a live lock in a nutshell.
 Next, I'll talk about the third common threading problem, starvation.
 **/
//End-Part-15
    }
}
