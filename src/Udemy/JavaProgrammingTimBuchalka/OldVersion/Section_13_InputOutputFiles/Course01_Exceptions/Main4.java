package CourseCodes.OldSections.Section_13_InputOutputFiles.Course01_Exceptions;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main4 {

    public static void main(String[] args) {

        int x = divideEAFP();
        System.out.println("x is " + x);
    }
    private static int divideEAFP() {

        Scanner s = new Scanner(System.in);
        System.out.println("Please enter an integer");
        try {
            return s.nextInt();
        } catch(InputMismatchException e) {
            return 0;
        }
    }
}
