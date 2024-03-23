package CourseCodes.NewSections.Section_09_Arrays.Course06_ReverseArrayChallenge;


import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

/*
Part-1
                                             Reverse Array Challenge

        The challenge is to write a method called reverse, that takes an int array as a parameter. In the main method,
    call the reverse method, and print the array both before and after the reverse method is called. To reverse the array,
    you have to swap the elements, so that the first element is swapped with the last element, and so on. So for example,
    if the array contains the numbers 1,2,3,4,5, then the reversed array should be, 5,4,3,2,1.

End-Part-1
*/

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
