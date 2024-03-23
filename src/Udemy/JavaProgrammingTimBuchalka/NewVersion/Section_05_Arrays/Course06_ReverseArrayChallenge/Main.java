package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_05_Arrays.Course06_ReverseArrayChallenge;


import java.util.Arrays;

public class Main {
    public static void main(String[] args) {


    int[] myArray = new int[] {1, 2, 3, 4, 5};
    System.out.println(Arrays.toString(myArray));
    System.out.println(Arrays.toString(reverse(myArray)));

    }

    private static int[] reverse(int[] anArray) {
        int[] reversedArray = new int[anArray.length];
        int j = anArray.length - 1;

        for (int i = 0; i < anArray.length; i++) {
            reversedArray[i] = anArray[j];
            j--;
        }
        return reversedArray;
    }
}
