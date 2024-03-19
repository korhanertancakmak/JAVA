package GeeksForGeeksChallenges.Easy;

import java.io.IOException;
import java.util.Scanner;

/*
        Given an array A of n positive numbers. The task is to find the first equilibrium point in an array. Equilibrium
    point in an array is a position such that the sum of elements before it is equal to the sum of elements after it.

    Note: Return equilibrium point in 1-based indexing. Return -1 if no such point exists.

    Example 1:
    Input:
    n = 5
    A[] = {1,3,5,2,2}
    Output:
    3
    Explanation: equilibrium point is at position 3 as a sum of elements before it (1+3) = sum of elements after it (2+2).

    Example 2:
    Input:
    n = 1
    A[] = {1}
    Output:
    1
    Explanation: Since there's only one element, hence it's only the equilibrium point.

    Your Task: The task is to complete the function equilibriumPoint() which takes the array and n as input parameters
    and returns the point of equilibrium.

    Expected Time Complexity: O(n)
    Expected Auxiliary Space: O(1)

    Constraints:
    1 <= n <= 105
    1 <= A[i] <= 109
*/
public class EquilibriumPoint {

    public static void main(String[] args) throws IOException {

        Scanner kb = new Scanner(System.in);

        //taking input n
        System.out.println("Enter size of array");
        int n = Integer.parseInt(kb.nextLine().trim());
        long[] arr = new long[n];
        System.out.println("Enter elements of array as A1 A2 A3 ..");
        String[] inputLine = kb.nextLine().trim().split(" ");

        //adding elements to the array
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(inputLine[i]);
        }

        //calling equilibriumPoint() function
        System.out.println(FindEquilibriumPoint.equilibriumPoint(arr, n));
    }
}

class FindEquilibriumPoint {
    // a: input array
    // n: size of array
    // Function to find equilibrium point in the array.
    public static int equilibriumPoint(long[] arr, int n) {
        // Your code here

        long totalSum = 0;
        for (long el : arr) {
            totalSum += el;
        }

        long leftSum = 0;
        for (int i = 0; i < n; i++) {
            totalSum -= arr[i];
            if (totalSum == leftSum) {
                return i + 1;
            }
            leftSum += arr[i];
        }
        return -1;
    }
}

//Problem successfully Solved
//Test Cases Passed:
//1121 /1121
//Total Points Scored:
//2 /2
//Your Total Score:
//114
//Total Time Taken:
//0.79
//Your Accuracy:
//100%
//Attempts No.:
//1
