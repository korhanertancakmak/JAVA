package Udemy.JavaProgrammingTimBuchalka.Exercies.Challenge0042_MinimumElement;

import java.util.Arrays;
import java.util.Scanner;

public class MinimumElement {

    private static int readInteger() {
        Scanner kb = new Scanner(System.in);
        System.out.println("Enter the size of the array : ");
        return kb.nextInt();
    }

    private static int[] readElements(int size) {
        Scanner kb = new Scanner(System.in);
        int[] myIntArray = new int[size];

        System.out.println("Enter the elements:");
        for (int i = 0; i < size; i++) {
            myIntArray[i] = kb.nextInt();
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

    public void run() {
        int[] myInt = MinimumElement.readElements(MinimumElement.readInteger());
        System.out.println("The array you entered : " + Arrays.toString(myInt));
        System.out.println("The min number of it : " + findMin(myInt));
    }
}
