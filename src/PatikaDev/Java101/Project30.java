/* Pratice30 - Program to find the greatest and the lowest values


Write a program in Java that finds the greatest and smallest numbers among N counting numbers entered from the keyboard and writes these numbers to the screen.

Scenario
How many numbers you will enter: 4
1. number: 16
2. number: -22
3. number: -15
4. number: 100
The greatest number: 100
the lowest number: -22

*/

import java.util.Scanner;

public class Project30 {
    
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("How many numbers you will enter?: ");
        int n = input.nextInt();

        int newNum = 0, memGreat = 0, memLeast = 0;
        for (int i = 1; i <= n; i++) {
            System.out.print(i + ". number: ");
            newNum = input.nextInt();
            if (i != 1 && newNum > memGreat) {
                memGreat = newNum;
            }
            if (i != 1 && newNum < memLeast) {
                memLeast = newNum;
            }
        }

        System.out.println("The greatest number: " + memGreat);
        System.out.println("The lowest number: " + memLeast);
        input.close();
    }
}
