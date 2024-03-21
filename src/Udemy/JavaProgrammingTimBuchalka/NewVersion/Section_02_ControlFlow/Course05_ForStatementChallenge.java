package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_02_ControlFlow;

public class Course05_ForStatementChallenge {

    public static void main(String[] args) {
        //System.out.println(isPrime(99));
        int counter = 0;

        for(int i = 10; i <= 50; i++) {
            if (counter == 3) {
                System.out.println("We have already found 3 prime numbers!");
                break;
            }

            if (isPrime(i)) {
                counter++;
                System.out.println(i + " is a prime number. We have found " + counter + " prime numbers, so far.");
            }
        }
    }

    public static boolean isPrime(int number) {

        if (number <= 2) {
            return (number == 2);
        }

        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
