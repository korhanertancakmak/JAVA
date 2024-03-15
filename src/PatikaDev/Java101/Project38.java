/* Pratice38 -  isPrime with Recursive methods

Write a program in Java language that finds out whether the number received from the user is a "Prime" number, using the "Recursive" method.

Scenario
Enter Number: 22
The number 22 is not PRIMARY!

Enter Number: 2
The number 2 is PRIMARY!

Enter Number: 39
The number 39 is not PRIMARY!

Enter Number: 17
The number 17 is Prime!

*/

import java.util.Scanner;

public class Project38 {
    
    public static void main(String[] args) {        
        isPrime();
    }
    
    public static void isPrime() {
        Scanner kb = new Scanner(System.in);
        System.out.println("Enter the number: ");
        int n = kb.nextInt();
        boolean prime = true;
        for (int i = 2; i <= n; i++) {
            if (n % i == 0 && i != n) {
                prime = false;
                break;
            }
        }
        if (prime) {
            System.out.println(n + " is a prime number.");   
            isPrime();         
        } else {
            System.out.println(n + " is not a prime number.");
            isPrime();
        }
        kb.close();
    }
}

