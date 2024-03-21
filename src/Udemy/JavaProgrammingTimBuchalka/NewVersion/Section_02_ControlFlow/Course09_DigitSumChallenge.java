package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_02_ControlFlow;

public class Course09_DigitSumChallenge {
    public static void main(String[] args) {
        System.out.println(sumDigits(1234));
        System.out.println(sumDigitsNewVersion(1234));
    }

    // This is my solution and it works
    public static int sumDigits(int number) {
        if (number < 0) {
            return -1;
        }

        int digitsCount = 1, number2 = number;
        do {
            number2 /= 10;
            digitsCount++;
        } while (number2 / 10 != 0);

        int i = 0, sum = 0;
        while (i != digitsCount) {
            i++;
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }
    /*
        At the start of the process:

            number = 1234, and our variable, sum = 0.

        If we use the remainder operator, getting the remainder of the number divided by 10, this will give us the most
     right digit in the number:

            1234 % 10 = 4

        And 4 is the last digit, or most right digit. Since we'll be working through the digits, from right to left, we'll
     add this to sum:

            sum = 4
        Next, we want to drop the most right digit, 4, and just have the other 3 digits to process. We can do this by
     dividing by 10:

            1234 / 10 = 123

        So in the second iteration of the loop:

            number = 123, and sum = 4

        And again we use the remainder operator, getting the remainder of the number divided by 10, which gives us the right
     most digit:

            123 % 10 = 3

        And we'll add 3 to sum, which was 4:

            sum = 7

        And now we divide 123 by 10:

            123 / 10 = 12

        So in the third iteration of the loop:

            number = 12, and sum = 7

        And we again take number mod 10:

            12 % 10 = 2

        And 2 gets added to sum:

            sum = 9

        And we again divide now by 10:

            12 / 10 = 1

        Now our number is a single digit, (number < 10) and here we'll break out of the loop. So, in this final step, after
    we've broken out of the loop:

            number = 1, and sum = 9.

        Now we can just add this last single digit to sum, and we'll have a final sum of 10.
     */

    public static int sumDigitsNewVersion(int number) {

        if (number < 0) {
            return -1;
        }

        int sum = 0;

        while (number > 9) {            // This is valid only up to 9 digits!!!
            sum += (number % 10);
            number = number / 10;
        }

        sum += number;

        return sum;
    }
}
