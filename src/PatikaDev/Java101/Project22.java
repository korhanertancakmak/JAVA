/* Pratice22 - Program to Find Powers of 2 Less than the Entered Number

We write a program that prints the powers of 2 up to the entered number on the screen using Java loops.

Homework
We write a program that prints the powers of 4 and 5 up to the entered number on the screen using Java loops.

*/

import java.util.Scanner;

public class Project22 {
    
    public static void main(String[] args) {

        Scanner kb = new Scanner(System.in);
        System.out.println("Enter the number: ");
        int num = kb.nextInt();
        if (Integer.signum(num) == 0) {
            System.out.println("You have entered negative data!");
            kb.close();
            return; 
        }

        System.out.print("Powers of 2: ");
        for (int i = 1; i < num; i *= 2) {
            System.out.print(i + " ");
        }

        kb.close();
    }
}
