/*Evaluating average of Grades

Write a simple code in Java that takes the user's grades in Math, Physics, Chemistry, English, History, Music classes from the keyboard, and valuates the averages.

Homework:
In the same code, using conditional statements print:            
    1. "Passed" if the user's average is higher than 60,
    2. "Failed" if the user's average is lower than 60. 

Not: Use Ternary Operator or Switch Case.
*/

import java.util.Scanner;

public class Project04 {

    public static void main(String[] args) {

        int Math, Physics, Chemistry, English, History, Music;

        Scanner kb = new Scanner(System.in);

        System.out.println("Enter Math Grade:");
        Math = kb.nextInt();

        System.out.println("Enter Physics Grade:");
        Physics = kb.nextInt();

        System.out.println("Enter Chemistry Grade:");
        Chemistry = kb.nextInt();

        System.out.println("Enter English Grade:");
        English = kb.nextInt();

        System.out.println("Enter History Grade:");
        History = kb.nextInt();

        System.out.println("Enter Music Grade:");
        Music = kb.nextInt();

        double average = (Math + Physics + Chemistry + English + History + Music) / 6;
        System.out.println(average);

        kb.close();
    }
}

