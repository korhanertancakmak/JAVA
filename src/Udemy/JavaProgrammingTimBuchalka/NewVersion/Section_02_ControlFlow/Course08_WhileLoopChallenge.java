package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_02_ControlFlow;

public class Course08_WhileLoopChallenge {
    public static void main(String[] args) {

        int i = 4, evenCount = 0, oddCount = 0;
        while (i <= 20) {
            i++;
            if (!isEvenNumber(i)) {
                oddCount++;
                continue;
            }
            System.out.println("Even number " + i);
            evenCount++;
        }
        System.out.println("Total odd numbers found = " + oddCount);
        System.out.println("Total even numbers found = " + evenCount);
    }

    public static boolean isEvenNumber(int number) {
        return (number % 2 == 0) ? true : false;
    }
}
