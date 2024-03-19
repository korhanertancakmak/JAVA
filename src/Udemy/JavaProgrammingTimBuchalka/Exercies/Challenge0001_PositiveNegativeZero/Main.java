package CourseCodes.NewSections.Exercises0To0029.Challenge0001_PositiveNegativeZero;

import java.util.Scanner;

/*
Challenge-Extra-1

                                                Positive, Negative or Zero

        Write a method called checkNumber with an int parameter named number. The method should not return any value and
    it needs to print out:

    - "positive" if the parameter number is > 0
    - "negative" if the parameter number is < 0
    - "zero" if the parameter number is equal to 0

    NOTE : The checkNumber method needs to be defined as public static like we have been doing so far in the course.
    NOTE : Do not add a main method to your solution code!


*/
public class Main {

    public static void main(String[] args) {

        Scanner kb = new Scanner(System.in);
        System.out.println("Enter a number that you want to check...");
        int number = kb.nextInt();
        PositiveNegativeZero.checkNumber(number);
    }
}

class PositiveNegativeZero {
    // write code here
    public static void checkNumber(int number) {
        if (number > 0) {
            System.out.println("positive");
        } else if (number < 0) {
            System.out.println("negative");
        } else if (number == 0) {
            System.out.println("zero");
        }
    }
}