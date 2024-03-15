/* Pratice23 - Program to Calculate Factorial

We are writing a program to calculate factorial in Java.

Homework
The number of different sets with r elements which will be created by a set with N element is named by N's combination with r.
N's combination with r is shown as C(n,r).

Write a program that calculates combination in Java.

Combination formula
C(n,r) = n! / (r! * (n-r)!)

*/

import java.util.Scanner;

public class Project23 {
    
    public static void main(String[] args) {

        Scanner kb = new Scanner(System.in);
        System.out.print("Enter the number: ");
        int num = kb.nextInt();
        System.out.printf("Factorial of %d: %d%n", num, factorial(num));
        System.out.println("Enter the inputs for C(N, r): ");
        int N = kb.nextInt();
        int r = kb.nextInt();
        System.out.printf("C(%d, %d) : %.2f", N, r, combination(N, r));
        kb.close();
    }

    public static int factorial(int a) {

        int b = 1;
        for (int i = 2; i <= a; i++) {
            b *= i;
        } 
        return b;
    }

    public static double combination (int n, int r) {
        double c = 0.0;
        int numerator = factorial(n);
        int denumerator = factorial(r) * factorial(n - r);
        c = numerator / denumerator;
        return c;
    }
}
