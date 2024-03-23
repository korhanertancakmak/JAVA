package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_05_Arrays.Course02_JavaUtilArraysSortFillCopyOf;

import java.util.Arrays;
import java.util.Random;

public class TheArrayChallenge {

    public static void main(String[] args) {
        Random random = new Random();
        int len = 5;
        int[] intArray = new int[len];
        for (int i = 0; i < len - 1; i++) {
            intArray[i] = random.nextInt(100);
        }
        System.out.println("This is the random array = " + Arrays.toString(intArray));
        Arrays.sort(intArray);
        System.out.println("This is the sorted array = " + Arrays.toString(intArray));

        int i = 0;
        int[] intArrayDescending = new int[len];
        for (int j = len - 1 ; j > -1; j--) {
            intArrayDescending[j] = intArray[i];
            i++;
        }
        System.out.println("This is the descending sorted array = " + Arrays.toString(intArrayDescending));
    }
}
