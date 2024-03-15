/* Pratice20 - Program to find even numbers up to the entered number

We write a program using Java loops to find even numbers up to the number entered by the user.

Homework
Write a program that calculates the average of numbers divisible by 3 and 4 from 0 to the entered number using Java loops.

*/

import java.util.Scanner;

public class Project20 {
    
    public static void main(String[] args) {

        Scanner kb = new Scanner(System.in);
        System.out.println("Enter the number:");
        int limit = kb.nextInt();

        int total = 0, j = 0;
        System.out.print("Even numbers up to the number entered: ");
        for (int i = 0; i < limit; i++) {
            if (i % 2 == 0) {
                System.out.print(i + " ");
            }
            if (i % 12 == 0 && i != 0) {
                total += i;
                j++;
            }
        }
        Double average = (double) total / j;
        System.out.printf("%nAverage of numbers divisible by 3 and 4 from 0 to the entered number: %.2f", average);
        kb.close();
    }
}
