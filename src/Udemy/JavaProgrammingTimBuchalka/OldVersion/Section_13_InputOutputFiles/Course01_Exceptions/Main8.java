package CourseCodes.OldSections.Section_13_InputOutputFiles.Course01_Exceptions;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main8 {

    public static void main(String[] args) {
        int result = divide();
        System.out.println(result);
    }

    private static int divide() {
        int x, y;
        try {
            x = getInt();
            y = getInt();
        } catch(NoSuchElementException e) {
            throw new ArithmeticException("no suitable input");
        }
        System.out.println("x is " + x + ", y is " + y);
        try {
            return x / y;
        } catch(ArithmeticException e) {
            throw new ArithmeticException ("attempt to divide by zero");
        }
    }

    private static int getInt() {
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter an integer ");
        while(true) {
            try {
                return s.nextInt();
            } catch(InputMismatchException e) {
                // go round again.  Read past the end of line in the input first
                s.nextLine();
                System.out.println("Please enter a number using only the digits 0 to 9 ");
            }
        }
    }
}
