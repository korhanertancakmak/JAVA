package GeeksForGeeksChallenges.Medium;

import java.io.IOException;
import java.util.Scanner;

/*
        You are given array A of size n. You need to find the maximum-sum sub-array with the condition that you are allowed
    to skip at most one element.

    Example 1:
    Input:
    n = 5
    A[] = {1,2,3,-4,5}
    Output: 11
    Explanation: We can get maximum sum subarray by skipping -4.

    Example 2:
    Input:
    n = 8
    A[] = {-2,-3,4,-1,-2,1,5,-3}
    Output: 9
    Explanation: We can get maximum sum subarray by skipping -2 as [4,-1,1,5] sums to 9, which is the maximum achievable
    sum.

    Your Task: Your task is to complete the function maxSumSubarray that take array and size as parameters and returns
    the maximum sum.

    Expected Time Complexity: O(N).
    Expected Auxiliary Space: O(N).

    Constraints:
       1 <= n  <= 100
    -103 <= Ai <= 103
*/
public class MaxSumSubarray {

    public static void main(String[] args)throws IOException {

        Scanner sc = new Scanner(System.in);

        //taking number of elements
        int n = Integer.parseInt(sc.nextLine());
        int[] a = new int[n];
        String[] str = sc.nextLine().trim().split(",");
        //inserting elements to the array
        for(int i = 0;i < n; i++) {
            a[i] = Integer.parseInt(str[i]);
        }

        System.out.println(FindMaxSumSubarray.maxSumSubarray(a , n));
    }
}

class FindMaxSumSubarray {
    //Function to return maximum sum subarray by removing at most one element.
    public static int maxSumSubarray(int[] A, int n) {
        //add code here.
        if (n == 1) {
            return A[0];
        }

        int[] maxEndingHere = new int[n];
        int[] maxStartingHere = new int[n];

        maxEndingHere[0] = A[0];
        maxStartingHere[n - 1] = A[n - 1];

        int maxSoFar = A[0];

        for (int i = 1; i < n; i++) {
            maxEndingHere[i] = Math.max(A[i], maxEndingHere[i - 1] + A[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere[i]);
        }

        for (int i = n - 2; i >= 0; i--) {
            maxStartingHere[i] = Math.max(A[i], maxStartingHere[i + 1] + A[i]);
        }

        int maxWithSkip = maxSoFar;

        for (int i = 1; i < n - 1; i++) {
            maxWithSkip = Math.max(maxWithSkip, maxEndingHere[i - 1] + maxStartingHere[i + 1]);
        }

        return maxWithSkip;
    }
}

//Problem successfully Solved
//Test Cases Passed:
//105 /105
//Total Points Scored:
//4 /4
//Your Total Score:
//90
//Total Time Taken:
//0.15
//Your Accuracy:
//100%
//Attempts No.:
//1