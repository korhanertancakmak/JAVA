package CourseCodes.NewSections.Exercises0To0029.Challenge0019_SharedDigit;

//Challenge-Extra-19
/*
                                          Shared Digit

        Write a method named hasSharedDigit with two parameters of type int. Each number should be within the range of
    10 (inclusive) - 99 (inclusive). If one of the numbers is not within the range, the method should return false. The
    method should return true if there is a digit that appears in both numbers, such as 2 in 12 and 23; otherwise, the
    method should return false.

    EXAMPLE INPUT/OUTPUT:
        hasSharedDigit(12, 23); → should return true since the digit 2 appears in both numbers
        hasSharedDigit(9, 99);  → should return false since 9 is not within the range of 10-99
        hasSharedDigit(15, 55); → should return true since the digit 5 appears in both numbers

    NOTE: The method hasSharedDigit should be defined as public static like we have been doing so far in the course.
*/

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        //System.out.println();
        System.out.println(hasSharedDigit(12, 23));
        System.out.println(hasSharedDigit(9, 99));
        System.out.println(hasSharedDigit(15, 55));

    }

    public static boolean hasSharedDigit(int a, int b) {

        if (a < 10 || a > 99 && b < 10 || b > 99) {
            return false;
        }

        int alength = Integer.toString(a).length();
        int blength = Integer.toString(b).length();
        int[] aDigits = new int[alength];
        int[] bDigits = new int[blength];

        for (int i = 0; i < alength; i++) {
            aDigits[i] = a % 10;
            a /= 10;
        }
        Arrays.sort(aDigits);

        for (int i = 0; i < blength; i++) {
            bDigits[i] = b % 10;
            b /= 10;
        }
        Arrays.sort(bDigits);

        if (alength == blength) {
            for (int elA : aDigits) {
                for (int elB : bDigits) {
                    if (elA == elB) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}

