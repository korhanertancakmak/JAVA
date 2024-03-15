/* Pratice47 - Sorting the array elements

Write a program in Java language that sorts the elements in the array from smallest to largest. 
Get the size of the array and the elements of the array from the user.

Scenario
Length of the array n : 5
Enter the elements :
1. Element : 99
2. Elemant : -2
3. Elemant : -2121
4. Elemant : 1
5. Elemant : 0
The order  : -2121 -2 0 1 99

Length of the array n : 6
Enter the elements :
1. Element : 1000221
2. Element : 22
3. Element : -1
4. Element : 999
5. Element : 0
6. Element : 254
The order : -1 0 22 254 999 1000221 
*/

import java.util.Arrays;
import java.util.Scanner;

public class Project47 {
    
    public static void main(String[] args) {

        Scanner kb = new Scanner(System.in);
        System.out.println("Length of the array: ");
        int n = kb.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.println(i + ". Element : ");
            arr[i] = kb.nextInt();
        }
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        kb.close();
    }
}


