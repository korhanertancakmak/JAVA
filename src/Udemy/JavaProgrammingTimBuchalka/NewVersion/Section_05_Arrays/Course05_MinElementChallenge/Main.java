package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_05_Arrays.Course05_MinElementChallenge;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int[] redInt = readIntegers();
        System.out.println("The array you entered : " + Arrays.toString(redInt));
        System.out.println("The minimum integer of this array is " + findMin(redInt));
    }

    private static int[] readIntegers() {
        Scanner kb = new Scanner(System.in);
        System.out.println("Enter numbers you want with (,) delimiter : ");
        String myString = kb.nextLine();

        String[] myStringArray = myString.split(",");
        int[] myIntArray = new int[myStringArray.length];

        for (int i = 0; i < myStringArray.length; i++) {
            myIntArray[i] = Integer.parseInt(myStringArray[i].trim()); // trim() methods for just in case the user put spaces before or after commas
        }

        return myIntArray;
    }

    private static int findMin(int[] array) {
        int min = Integer.MAX_VALUE;
        for (int j : array) {
            if (j < min) {
                min = j;
            }
        }
        return min;
    }
}
