/* Pratice29 - Program that finds the least common multiple(LCM) and greatest common divisor(GCD)

We write a program that finds the value of LCM and GCD for 2 numbers in Java.

GCD : The greatest common divisor of two or more natural numbers is called the greatest common divisor of these numbers, in short, GCF.

Example: Let's find greatest common divisor of 18 and 24 step by step.

Divisors of 18 : 1, 2, 3, 6, 9, 18
Divisors of 24 : 1, 2, 3, 4, 6, 8, 12, 24

The greatest of these common divisors, the number 6, is GCD. 


LCM : The smallest common multiple of two or more natural numbers is called the least common multiple of these numbers, in short, LCM.

Example: Let's find the least common multiple of 6 and 8 step by step.

Multiples of 6 : 6, 12, 18, 24, 30, 36, 42, 48, …
Multiples of 8 : 8, 16, 24, 32, 40, 48, 56, 64, …

The least value of these common multiples, the number 24, LCM. 

LCM = (n1 * n2) / GCD

Homework
Print LCM and GCD values by using while loop in Java. 

*/

import java.util.Scanner;

public class Project29 {
    
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("Enter the first number: ");
        int n1 = input.nextInt();
        System.out.println("Enter the second number: ");
        int n2 = input.nextInt();
        
        // Find GCD
        int gcd = 0;
        for (int i = 1; i <= n1; i++) {
            if (n1 % i == 0 && n2 % i == 0) {
                gcd = i;
            }
        }
        System.out.println("GCD of " + n1 + " and " + n2 + " is: " + gcd);

        // Find LCM
        int lcm = (n1 * n2) / gcd;
        System.out.println("LCM of " + n1 + " and " + n2 + " is: " + lcm);

        input.close();
    }
}
