/* Pratice31 - Program to find whether the number entered is perfect number or not

Write a program in Java detects whether a number entered from the keyboard is a perfect number or not, and prints the following expressions on the screen:
if the number is a perfect number, "it is a perfect number",
If not, “it is not the perfect number”.

What is perfect number?
A perfect number is a positive integer that is equal to the sum of its positive divisors, excluding the number itself. 

Scenarios:
Enter a number: 28
28 is a perfect number
Enter a number: 1
1 is not a perfect number
Enter a number: 496
496 is a perfect number

*/

import java.util.Scanner;

public class Project31 {
    
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("Enter a number: ");
        int n = input.nextInt();

        int sum = 0;
        for (int i = 1; i <= n; i++) {
            if (n % i == 0 && n != i) {
                sum += i;
            }
        }
        if (sum == n) {
            System.out.println(n + " is a perfect number");
        } else {
            System.out.println(n + " is not a perfect number");
        }
        input.close();
    }
}
