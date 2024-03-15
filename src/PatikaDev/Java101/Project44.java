/* Pratice44 -  Max & Min values in an array

Homework
Write a program that finds the nearest number smaller than the entered number and the nearest number greater than the entered number of the elements in the array.

Array                                : {15,12,788,1,-1,-778,2,0}
The entered number                   : 5
The nearest number smaller than this : 2
The nearest number greater than this : 12

*/

import java.util.Arrays;
import java.util.Scanner;

public class Project44 {
    
    public static void main(String[] args) {
        int[] list = {15,12,788,1,-1,-778,2,0};
      
        int min = list[0];
        int max = list[0];

        for (int i : list) {
            if (i < min) {
                min = i;
            }
            if (i > max) {
                max = i;
            }
        }

        System.out.println("Min value: " + min);
        System.out.println("Max value: " + max);

        Scanner kb = new Scanner(System.in);
        System.out.println("Enter a number: ");
        int n = kb.nextInt();

        int[] list2 = new int[list.length + 1];
        list2[list2.length - 1] = n;
        for (int i = 0; i < list.length; i++) {
            list2[i] = list[i];
        }
        Arrays.sort(list2);
        System.out.println(Arrays.toString(list2));
        for (int i = 0; i < list2.length; i++) {
            if (list2[i] == n) {
                System.out.println("Nearest smaller value: " + list2[i - 1]);
                System.out.println("Nearest greater value: " + list2[i + 1]);
            }
        }
        kb.close();
    }
}

