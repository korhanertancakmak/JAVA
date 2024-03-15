/* Pratice48 - Frequency of the elemnts in an array

Write a program in Java language that finds how many times the elements in the array repeats, so its frequency.

Scenario
Array : [10, 20, 20, 10, 10, 20, 5, 20]
Repeating values
Number-10 3 times repeated.
Number-20 4 times repeated. 
Number-5 1 times repeated. 
*/

import java.util.Arrays;

public class Project48 {
    
    public static void main(String[] args) {

        int[] arr = {10, 20, 20, 10, 10, 20, 5, 20};
        Arrays.sort(arr);

        int first = arr[0], counter = 1; 
        for(int i = 1; i < arr.length; i++) {
            if (arr[i] == first) {
                counter++;
            }
            
            if (arr[i] != first || i == arr.length - 1) {
                System.out.println("Number-" + first + " " + counter + " times repeated.");
                counter = 1;
            } 

            first = arr[i];
        }
    }
}

