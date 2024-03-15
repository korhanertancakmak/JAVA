/* Pratice32 - Printing inverse triangle with "*"

Write a program in Java that takes the number of digits from the user and draws an inverted triangle on the screen with asterisks (*) using loops.

Example
Digit number : 10

*******************
*****************
***************
*************
*********** 
********* 
******* 
***** 
*** 
*

*/

import java.util.Scanner;

public class Project32 {
    
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("Enter digit number: ");
        int n = input.nextInt();

        for (int i = n; i >= 1; i--) {
            for (int j = 2*i - 1; j >= 1; j--) {
                System.out.print("*");
            }
            System.out.println(" ");
        }
        
        input.close();
    }
}
