package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_02_ControlFlow;

import java.util.Scanner;

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
