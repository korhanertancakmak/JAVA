package CourseCodes.OldSections.Section_13_InputOutputFiles.Course01_Exceptions;

import java.util.Scanner;

public class Main2 {

    public static void main(String[] args) {

        int x = getInt();
        System.out.println("x is " + x);
    }

    // Try input a string to getInt will give exception InputMismatchException
    private static int getInt() {
        Scanner s = new Scanner(System.in);
        return s.nextInt();
    }
}
