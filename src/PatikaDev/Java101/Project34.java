/* Pratice34 - Fibonacci sequence

Write a program that finds fibonacci sequence with loops in Java. Take the number of Fibonacci sequence's element as input. 

What is Fibonacci sequence?
Fibonacci sequence is a series of numbers formed by adding each number to the previous one.
When the numbers are compared to each other in this series, the golden ratio emerges.
That is, when a number is divided by the number before it, a series that gets closer and closer to the golden ratio is obtained.

The Fibonacci sequence starts from 0 and goes to infinity. Each digit is added to the previous digit. The result obtained is written on the right side of the number.
The first ten numbers of the Fibonacci sequence are as follows:

Fibonacci sequence with 9 elements : 0 1 1 2 3 5 8 13 21 34

 0 + 1 = 1
 1 + 1 = 2
 1 + 2 = 3
 2 + 3 = 5
 3 + 5 = 8
 5 + 8 = 13
 13 + 8 = 21
 21 + 13 = 34

*/

import java.util.Scanner;

public class Project34 {
    
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of elements");
        int n = input.nextInt();

        System.out.print("Fibonacci sequence : ");
        int last = 0, first = 1, mid;
        System.out.print(last + " ");
        for (int i = 1; i < n - 1; i++) {  
            mid = first; 
            first += last;
            last = mid;
            System.out.print(first + " ");
        }
        input.close();
    }
}
