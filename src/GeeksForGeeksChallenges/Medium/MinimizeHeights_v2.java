package GeeksForGeeksChallenges.Medium;

import java.util.Arrays;

/*
    Given an array arr[] denoting heights of N towers and a positive integer K. For each tower, you must perform "exactly
    one" of the following operations "exactly once".

        Increase the height of the tower by K
        Decrease the height of the tower by K

    Find out the minimum possible difference between the height of the shortest and tallest towers after you have modified
    each tower.

    Note: It is compulsory to increase or decrease the height by K for each tower. After the operation, the resultant array
    should not contain any negative integers.

    Example 1:
    Input:
    K = 2, N = 4
    Arr[] = {1, 5, 8, 10}
    Output:
    5
    Explanation:
    The array can be modified as
    {1+k, 5-k, 8-k, 10-k} = {3, 3, 6, 8}.
    The difference between
    the largest and the smallest is 8-3 = 5.

    Example 2:
    Input:
    K = 3, N = 5
    Arr[] = {3, 9, 12, 16, 20}
    Output:
    11
    Explanation:
    The array can be modified as
    {3+k, 9+k, 12-k, 16-k, 20-k} -> {6, 12, 9, 13, 17}.
    The difference between
    the largest and the smallest is 17-6 = 11.

    Your Task:
    You don't need to read input or print anything. Your task is to complete the function getMinDiff() which takes the
    arr[], n, and k as input parameters and returns an integer denoting the minimum difference.

    Expected Time Complexity: O(N*logN)
    Expected Auxiliary Space: O(N)

    Constraints
    1 ≤ K ≤ 104
    1 ≤ N ≤ 105
    1 ≤ Arr[i] ≤ 105
*/
public class MinimizeHeights_v2 {

    public static void main(String[] args) {

        int k = 5;
        int[] arr = {8, 1, 5, 4, 7, 5, 7, 9, 4, 6};
        int n = arr.length;

        GetMinimizeHeights_v2 gmh = new GetMinimizeHeights_v2();
        System.out.println(gmh.getMinDiff(arr, n, k));
    }
}

class GetMinimizeHeights_v2 {

    int getMinDiff(int[] arr, int n, int k) {
        // code here
        // so first sort the array
        Arrays.sort(arr);                                // O(NLogN) time complexity

        // now array is sort so the difference between max and min is
        int diff = arr[n - 1] - arr[0];                  // we have to minimize this difference

        // logic
        // so on the left side the lowest value and on the right-side highest value
        // so increase the left side value and decrease the right side value to minimize the difference
        int mini = arr[n - 1] - k;                       // go on left-side
        int maxi = arr[0] + k;                           // go on right-side
        int curr_max, curr_min;
        for(int i = 0; i < n - 1; i++) {                 // not include n-1 because we have taken it already on the left-side
            curr_max = Math.max(arr[i] + k, mini);       // so going increasing arr[i]+k and going to right-side

            // And finding the max element until it become greater than "arr[n-1]-k" because we have to go on the left-side,
            // also we don't take arr[n-1]+k. Because if we take then it become greater than arr[n-1]-k, and we have to
            // go on the right-side to make it small not greater

            curr_min = Math.min(maxi, arr[i + 1] - k);   // we have to go on the right-side, so we not take arr[0]+k
            // why?because we have to go on righ side and arr[0]+k going to the right-side, so we take one greater than
            // it arr[1]-k to arr[n-1]-k and compare it with arr[0]+k our minimum like we have find minimum then this or
            // not if so then update it

            diff = Math.min(diff, curr_max - curr_min);

            // Note that this will include negative diff because arr[i+1]-k could be negative so if question says height
            // can't be negative than arr[i+1]-k should be positive
        }
        return diff;

    }

}