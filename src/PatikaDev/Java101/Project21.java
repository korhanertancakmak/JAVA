/* Pratice21 - Program to find the sum of odd numbers 

We write a program that is accepting the input numbers until a negative value is entered with java loops and 
prints the odd numbers from the entered values on the screen.

Homework
We write a program that is accepting the input numbers until an odd value is entered with java loops and 
prints the sum of even numbers and multiples of 4 from the entered values on the screen.

*/

import java.util.Scanner;

public class Project21 {
    
    public static void main(String[] args) {

        Scanner kb = new Scanner(System.in);
        System.out.print("Enter the numbers(stop = negative input): ");
        int num = kb.nextInt();
        int sum = num;
        while (Integer.signum(num) == 1) {
            num = kb.nextInt();
            if (num % 2 != 0 && num > 0) {
                sum += num;
            }
        }
        System.out.printf("%nSum of the odd numbers up to now: %d%n", sum);
        
        System.out.print("Enter the new numbers(stop = odd input): ");
        num = kb.nextInt();
        sum = num;
        while (num % 2 == 0) {
            num = kb.nextInt();
            if (num % 4 != 0 && num % 2 == 0) {
                sum += num;
            }
        }
        System.out.printf("%nSum of the desired numbers up to now: %d%n", sum);

        kb.close();
    }
}
