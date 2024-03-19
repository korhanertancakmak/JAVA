package CourseCodes.NewSections.Section_19_Concurrency.Course24_LivelockExample;

import java.io.File;

//Part-1
/**                                                 Livelock

        In the last lecture, we looked at a deadlock. In this lecture, I'll be showing you an example of a livelock. A
 livelock is when two or more threads are continuously reacting, each responding to the other's actions, and they never
 can successfully complete. You can find different examples of this problem on the internet. Let's say you and your friend
 have gone to an escape room, these are rooms where you have to solve problems to get out. In this case though, it's simply
 going to contain a maze you have to navigate through. You and your partner will start at different positions, and have
 to find each other, and aren't allowed to communicate with each other. Should you both look for each other? Definitely
 not, but the rules say each person must move within 3 seconds.

                    _________________________________________________________________________________
                    |                   |                   |                   |                   |
          →→→→→→→→→ |                   |                   |                   |                   |  →→→→
          ↑         |               →→→→→→→→            →→→→→→→→            →→→→→→→→                |     ↓
          ↑         |                   |                   |                   |                   |     ↓
          ↑     ←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←
          ↑     ↓   |                   |                   |                   |                   |
          ↑     →→→ |                   |       GRACE       |                   |                   |  →→→→
          ↑         |               →→→→→→→→            →→→→→→→→            →→→→→→→→                |     ↓
          ↑         |                   |                   |                   |                   |     ↓
          ↑     ←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←
          ↑     ↓   |                   |                   |                   |                   |
          ↑     →→→ |                   |                   |                   |                   |  →→→→
          ↑         |               →→→→→→→→            →→→→→→→→            →→→→→→→→                |     ↓
          ↑         |                   |                   |                   |                   |     ↓
          ↑     ←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←
          ↑     ↓   |                   |                   |                   |                   |
          ↑     →→→ |                   |                   |                   |        ADAM       |  →→→→
          ↑         |               →→→→→→→→            →→→→→→→→            →→→→→→→→                |     ↓
          ↑         |___________________|___________________|___________________|___________________|     ↓
          ↑                                                                                               ↓
          ↑←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←

 In this lecture, I'll use a simple maze to demonstrate how you and your friend, in the escape room's maze, might be caught
 in a live lock scenario. I'll be creating a 4 by 4 maze, and the paths are shown by the arrows. I'll have two people in
 the maze searching for each other, Grace and Adam. In this case, Grace is at position (1 1), and Adam is at position (3 3).
 Adam, if he moves to the next position, will go to position (0 0), or the top left corner. For the purposes of this code,
 the players must only move forward as shown. Getting back to the code, I'll create a new class, called Maze.
 **/
//End-Part-1

public class Main {

    public static void main(String[] args) {

        File resourceA = new File("inputData.csv");
        File resourceB = new File("outputData.json");

        Thread threadA = new Thread(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " attempting to lock resourceA (csv)");
            synchronized (resourceA) {
                System.out.println(threadName + " has lock on resourceA (csv)");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(threadName +
                        " NEXT attempting to lock resourceB (json), " +
                        "still has lock on resource A (csv)");
                synchronized (resourceB) {
                    System.out.println(threadName + " has lock on resourceB (json)");
                }
                System.out.println(threadName + " has released lock on resourceB (json)");
            }
            System.out.println(threadName + " has released lock on resourceA (csv)");
        }, "THREAD-A");

        Thread threadB = new Thread(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " attempting to lock resourceA (csv)");
            synchronized (resourceA) {
                System.out.println(threadName + " has lock on resourceA (csv)");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(threadName +
                        " NEXT attempting to lock resourceB (json), " +
                        "still has lock on resource A (csv)");
                synchronized (resourceB) {
                    System.out.println(threadName + " has lock on resourceB (json)");
                }
                System.out.println(threadName + " has released lock on resourceB (json)");
            }
            System.out.println(threadName + " has released lock on resourceA (csv)");
        }, "THREAD-B");

        threadA.start();
        threadB.start();

        try {
            threadA.join();
            threadB.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}