package CourseCodes.OldSections.Section_13_InputOutputFiles.Course01_Exceptions;

import java.util.Scanner;

public class Main5 {

    public static void main(String[] args) {
        int result = divide();
        System.out.println(result);
    }

    // divide() gives InputMismatchException when the input is string
    private static int divide() {
        int x = getInt();
        int y = getInt();
        System.out.println("x is " + x + ", y is " + y);
        return x / y;
    }

    private static int getInt() {
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter an integer ");
        return s.nextInt();
    }
}
