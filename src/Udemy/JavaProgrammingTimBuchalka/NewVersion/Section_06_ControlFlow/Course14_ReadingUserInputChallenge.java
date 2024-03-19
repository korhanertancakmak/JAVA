package CourseCodes.NewSections.Section_06_ControlFlow;

import java.util.Scanner;

/*
Course-30

                                        Reading User Input Challenge

        In this challenge, you'll read 5 valid numbers from the console, entered by the user, and print the sum of those
    five numbers. You'll want to check that the numbers entered, are valid integers. If not, print out the message "Invalid
    number" to the console, but continue looping, until you do have 5 valid numbers.

        Before the user enters each number, prompt them with the message, "Enter number #x:", where x represents the count,
    1, 2, 3, etc. And as an example there, the first message would look something like,

                                        "Enter number #1:"

    the next,

                                        "Enter number #2:"

    and so on. The hints for doing this are,

      - Firstly, use a while loop, or a do while loop.
      - Use a Scanner object, and the nextLine method, to read input as a String.
      - Use Integer.parseInt, as we did in the previous courses.
      - You'll need some local variables, to keep track of the count of valid integers, as well as the sum of the integers.


                                        Min and Max Challenge

        This is the minimum and maximum challenge. This challenge is similar to the last one in some ways. You'll be using
an endless loop which:

      - Prompts the user to enter a number, or any character to quit.
      - Validates if the user-entered data really is a number, you can choose either an integer, or double validation method.
      - If the user-entered data is not a number, quit the loop.
      - Keep track of the minimum number entered.
      - Keep track of the maximum number entered.

        If the user has previously entered a set of numbers (or even just one), display the minimum, and maximum number,
    which the user entered. So, you wanna create a loop, that continues to process, until the user enters non-numeric data.
    You'll prompt the user to enter a number, or type a character to quit each iteration. After the user enters some data,
    you'll read the input as a String, and then test if it can be parsed to a number.

        You can decide if you want to solicit integers or decimal numbers, from the user. If the user entered a valid number,
    you'll want to see if it is less than what you have for a minimum number, and if it is, you'll set that to the current
    number. You'll do the same check for the maximum number. For example, after one valid numeric entry, minimum and maximum
    numbers, should be the same number.

*/
public class Course14_ReadingUserInputChallenge {
    public static void main(String[] args) {

        //System.out.println("The sum of the 5 numbers = " + sumOfNumbers());
        minMaxNumbers();
    }

    public static int sumOfNumbers() {

        int count = 0, number = 0, sum = 0;
        Scanner sc = new Scanner(System.in);
        while (count < 5) {
            count++;
            System.out.println("Enter number #" + count);
            String stringNumber = sc.nextLine();

            try {
                number = Integer.parseInt(stringNumber);
                sum += number;
            } catch (NumberFormatException badUserData) {
                System.out.println("Invalid Value!!! Try again.");
                count--;
            }
        }
        return sum;
    }

    public static void minMaxNumbers() {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, number;
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Enter number or type a character to quit");
            String stringNumber = sc.nextLine();

            try {
                number = Integer.parseInt(stringNumber);
                if (number < min) {
                    min = number;
                }
                if (number > max) {
                    max = number;
                }
            } catch (NumberFormatException badUserData) {
                System.out.println("No valid data entered!!! Process is ending.");
                System.out.println("Min and Max numbers from previously entered a set of numbers = " + min + " " + max);
                break;
            }

        }
    }
}
