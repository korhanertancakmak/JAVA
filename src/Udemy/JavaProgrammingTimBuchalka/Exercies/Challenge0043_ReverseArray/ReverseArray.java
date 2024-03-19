package Udemy.JavaProgrammingTimBuchalka.Exercies.Challenge0043_ReverseArray;

import java.util.Arrays;

public class ReverseArray {

    private static void reverse(int[] anArray) {
        System.out.println("Array = " + Arrays.toString(anArray));
        int[] reversedArray = new int[anArray.length];
        int j = anArray.length - 1;

        for (int i = 0; i < anArray.length; i++) {
            reversedArray[i] = anArray[j];
            j--;
        }
        System.out.println("Reversed Array = " + Arrays.toString(reversedArray));
    }

    public void run(int[] anArray) {
        reverse(anArray);
    }
}
