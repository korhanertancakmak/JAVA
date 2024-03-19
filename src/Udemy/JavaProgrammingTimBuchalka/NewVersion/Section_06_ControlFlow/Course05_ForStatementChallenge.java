package CourseCodes.NewSections.Section_06_ControlFlow;

/*
Course-21

                                       The for Statement Challenge Exercise

        In the previous course, we talked about the for statement, and in this one, i want to give you another challenge
    to work out on your own first, then we'll get back together and work through the solution.

        This challenge will use prime numbers, which is not a composite number, and which are the numbers we canNOT make
    them by multiplying other whole numbers. Another way to think of it, that a prime number is only divisible by itself,
    and one.

        Create a prime number counter variable, that will keep count of how many prime numbers were found. Create a for
    statement, using any range of numbers, where the maximum number is <= 1000. For each number in the range.

      * Check to see if it's a prime number using the isPrime method.
      * If the number is prime, print it out and increment the prime number counter variable.
      * Once the prime number counter equals three, exit the loop (Hint, use the break statement to exit).

        Your challenge is to create a for statement, using any range of numbers, to determine if the numbers, are prime
    numbers. You'll use the isPrime method. If it's a prime number, print it out, and increment a count of the number of
    prime numbers found. If you get to the stage where 3 or more prime numbers are found, end the loop. In other words,
    you'll be iterating through the loop, but you've found three prime numbers before the range is fully processed. I want
    you to exit the for loop, and as a hint, use the break statement to exit. So you need to use an if statement to check
    the count, and use break to get out of the loop, even before it completes processing all the numbers in the range you
    picked.


*/
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
