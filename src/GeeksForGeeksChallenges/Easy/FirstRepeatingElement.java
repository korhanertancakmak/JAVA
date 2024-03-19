package GeeksForGeeksChallenges.Easy;

import java.util.Arrays;

/*
        Given an array arr[] of size n, find the first repeating element. The element should occur more than once, and the
    index of its first occurrence should be the smallest.

    Note: The position you return should be according to 1-based indexing.

    Example 1:
    Input:
    n = 7
    arr[] = {1, 5, 3, 4, 3, 5, 6}
    Output: 2
    Explanation:
    5 is appearing twice and its first appearence is at index 2 which is less than 3 whose first occuring index is 3.

    Example 2:
    Input:
    n = 4
    arr[] = {1, 2, 3, 4}
    Output: -1
    Explanation:
    All elements appear only once so answer is -1.

    Your Task:
    You don't need to read input or print anything. Complete the function firstRepeated() which takes arr and n as input
    parameters and returns the position of the first repeating element. If there is no such element, return -1.

    Expected Time Complexity: O(n)
    Expected Auxilliary Space: O(n)

    Constraints:
    1 <= n <= 106
    0 <= Ai<= 106
*/
public class FirstRepeatingElement {

    public static void main(String[] args) {

        int n = 12;
        int[] arr = {7, 4, 0, 9, 4, 8, 8, 2, 4, 5, 5, 1};

        System.out.println("Input: " + Arrays.toString(arr));
        System.out.println("Output: " + FindFirstRepeatingElement.firstRepeated(arr, n));
    }
}


class FindFirstRepeatingElement {
    // Function to return the position of the first repeating element.
    public static int firstRepeated(int[] arr, int n) {
        // Your code here

        // Find the max element in the arr
        int maxEl = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (arr[i] > maxEl) {
                maxEl = arr[i];
            }
        }

        // Create a (maxEl + 1)-sized array, which has [positions][frequencies]
        int[][] myarr = new int[maxEl + 1][2];

        int index = 0;
        while(index < n) {
            // el is the element in that index of arr
            int el = arr[index];
            // increase the freq of element index by 1.
            myarr[el][1]++;
            // if its position value has not processed yet
            if (myarr[el][0] == 0) {
                // set it to the position in the arr array.
                myarr[el][0] = index + 1;
                // and increase the index for the next loop
                index++;
            } else {
            // otherwise, it means we already processed this location, and it must already have a position mark,
            // which is the smallest value. But this doesn't mean second position of the same element won't take place.
                index++;
                continue;
            }
        }

        // Since we took all the elements in myarr, we need to sort "myarr" array to find the smallest position.
        Arrays.sort(myarr, (a, b) -> Integer.compare(a[0], b[0]));

        // Find the first element has the value of 2 in the array myarr[position][frequencies=2]
        // and break-return its position value
        for (int i = 0; i < maxEl; i++) {
            if (myarr[i][1] > 1) {
                return myarr[i][0];
            }
        }
        // If all the frequencies are lower than 2, means no repeated element, return -1
        return -1;
    }
}