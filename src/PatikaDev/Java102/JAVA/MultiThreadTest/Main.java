package PatikaDev.Java102.JAVA.MultiThreadTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 Create an ArrayList consisting of numbers from 1 to 10000 (10 thousand).
 Then, divide the 10 thousand elements in this ArrayList into 4 equal parts of 2500 elements.
 Design 4 separate Threads that find odd and even numbers in these 4 separate ArrayLists of 2500 elements.

 * 4 Threads will add the even numbers that they find to a common ArrayList.
 * 4 Threads will add the odd numbers that they find to a common ArrayList.
 * ArrayLists holding odd and even numbers will be empty when they are first created.
   And there will be two ArrayLists.
 * When the 4 Threads start processing their own ArrayList of 2500 elements,
   it will fill the odd and even number ArrayLists.
**/

public class Main {
    public static void main(String[] args) {
        // Create a list with numbers from 1 to 10000
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 10000; i++) {
            numbers.add(i);
        }

        // Create two separate ArrayLists for odd and even numbers
        List<Integer> oddNumbers = new ArrayList<>();
        List<Integer> evenNumbers = new ArrayList<>();

        // Divide the numbers into 4 equal parts
        List<List<Integer>> subLists = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int fromIndex = i * 2500;
            int toIndex = fromIndex + 2500;
            subLists.add(numbers.subList(fromIndex, toIndex));
        }

        // Create a thread pool
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        // Create OddEvenWorker instances for each sublist
        for (List<Integer> sublist : subLists) {
            executorService.execute(new OddEvenWorker(sublist, oddNumbers, evenNumbers));
        }

        // Shutdown the executor service
        executorService.shutdown();

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Print the results
        System.out.println("Odd numbers: " + oddNumbers);
        System.out.println("Even numbers: " + evenNumbers);
    }
}
