package CourseCodes.NewSections.Exercises0030To0049.Challenge0041_SortedArray;

import java.util.Arrays;
import java.util.Scanner;

public class SortedArray {

    public static void printArray(int[] anArray) {
        int i = 0;
        for (int elements : anArray) {
            System.out.printf("Element %d contents %d%n", i, elements);
            i++;
        }
    }

    public static int[] sortIntegers(int[] unsortedArray) {
        int[] temp = Arrays.copyOf(unsortedArray, unsortedArray.length);
        Arrays.sort(temp);
        int dummy, j = temp.length;
        for (int i = 0; i < temp.length / 2; i++) {
            dummy = temp[i];
            temp[i] = temp[j - 1];
            temp[j - 1] = dummy;
            j--;
        }
        int[] sortedArray = Arrays.copyOf(temp, temp.length);
        return sortedArray;
    }
    public static int[] getIntegers(int size) {
        Scanner kb = new Scanner(System.in);
        int[] intArray = new int[size];
        for (int i = 0; i < size; i++) {
            intArray[i] = kb.nextInt();
        }
        return intArray;
    }
}
