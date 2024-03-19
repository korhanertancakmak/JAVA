package CourseCodes.OldSections.Section_13_InputOutputFiles.Course01_Exceptions;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main6 {

    public static void main(String[] args) {
        int result = divide();
        System.out.println(result);
    }

    private static int divide() {
        int x = getInt();
        int y = getInt();
        System.out.println("x is " + x + ", y is " + y);
        return x / y;
    }

    // Update in getInt() removes InputMismatchException that's in Main5
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
