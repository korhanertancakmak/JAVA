package CourseCodes.OldSections.Section_13_InputOutputFiles.Course01_Exceptions;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main3 {

    public static void main(String[] args) {

        int x = getIntLBYL();
        System.out.println("x is " + x);
    }

    private static int getIntLBYL() {
        Scanner s = new Scanner(System.in);
        boolean isValid = true;
        System.out.println("Please enter an integer ");
        String input = s.next();
        for(int i=0; i<input.length(); i++) {
            if(!Character.isDigit(input.charAt(i))) {
                isValid = false;
                break;
            }
        }
        if(isValid) {
            return Integer.parseInt(input);
        }
        return 0;
    }

}
