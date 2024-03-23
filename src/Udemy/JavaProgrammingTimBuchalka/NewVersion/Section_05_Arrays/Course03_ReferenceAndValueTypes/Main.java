package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_05_Arrays.Course03_ReferenceAndValueTypes;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {


        int[] myIntArray = new int[5];
        int[] anotherArray = myIntArray;


        System.out.println("myIntArray = " + Arrays.toString(myIntArray));
        System.out.println("anotherArray = " + Arrays.toString(anotherArray));

        anotherArray[0] = 1;

        System.out.println("after change myIntArray = "
                + Arrays.toString(myIntArray));
        System.out.println("after change anotherArray = " +
                Arrays.toString(anotherArray));

    }
}
