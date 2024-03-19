package CourseCodes.NewSections.Section_09_Arrays.Course05_MinElementChallenge;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

/*
Part-1
                                             Minimum Element Challenge

        Write a method called readIntegers, that reads a comma delimited list of numbers, entered by the user from the
    console, and then returns an array, containing those numbers that were entered. Next, write a method called findMin,
    that takes the array as an argument, and returns the minimum value found in that array.

        In the main method :
  - Call the method readIntegers, to get the array of integers from the user, and print these out, using a method found
    in java.util.Arrays.
  - Next, call the findMin method, passing the array, returned from the call to the readIntegers method.
  - Print the minimum element in the array, which should be returned from the findMin method.

    A tip here. Assume that the user will only enter numbers, so you don't need to do any validation for the console input.
End-Part-1
*/


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
