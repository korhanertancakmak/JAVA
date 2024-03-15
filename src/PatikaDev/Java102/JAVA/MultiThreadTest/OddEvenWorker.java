package JAVA.MultiThreadTest;

import java.util.List;

public class OddEvenWorker implements Runnable {
    private final List<Integer> numbers;
    private final List<Integer> oddNumbers;
    private final List<Integer> evenNumbers;

    public OddEvenWorker(List<Integer> numbers, List<Integer> oddNumbers, List<Integer> evenNumbers) {
        this.numbers = numbers;
        this.oddNumbers = oddNumbers;
        this.evenNumbers = evenNumbers;
    }

    @Override
    public void run() {
        // Process the sublist and add odd and even numbers to the respective lists
        for (int number : numbers) {
            if (number % 2 == 0) {
                evenNumbers.add(number);
            } else {
                oddNumbers.add(number);
            }
        }
    }
}