package GeeksForGeeksChallenges.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
public class MinimizeHeights {

    public static void main(String[] args) {

        int k = 5;
        int[] arr = {8, 1, 5, 4, 7, 5, 7, 9, 4, 6};
        int n = arr.length;

        GetMinimizeHeights gmh = new GetMinimizeHeights();
        System.out.println(gmh.getMinDiff(arr, n, k));
    }
}

class GetMinimizeHeights {

    private boolean[] IsDone;

    void setBoolean(int i) {
        this.IsDone[i] = true;
    }

    int getMinDiff(int[] arr, int n, int k) {
        // code here
        this.IsDone = new boolean[n];
        arr = mandatory(arr, k);

        List<Integer> indexToBeChanged = new ArrayList<>();
        for (int j = 0; j < n; j++) {
            if (!this.IsDone[j]) {
                indexToBeChanged.add(j);
            }
        }

        return solver(arr, indexToBeChanged, k);
    }

    int[] mandatory(int[] arr, int k) {

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] - k <= 0) {
                arr[i] += k;
                setBoolean(i);
            }
        }
        return arr;
    }

    int solver(int[] arr, List<Integer> ind1, int k) {

        int[] arr1 = Arrays.copyOf(arr, arr.length);
        int index = ind1.get(0);
        arr1[index] += k;
        List<Integer> ind2 = new ArrayList<>(ind1);
        ind1.remove(0);

        int diff = Integer.MAX_VALUE;
        if (ind1.size() == 0) {
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (int j : arr1) {
                min = Math.min(min, j);
                max = Math.max(max, j);
                diff = max - min;
            }
        } else {
            diff = solver(arr1, ind1, k);
        }

        int[] arr2 = Arrays.copyOf(arr, arr1.length);
        int index2 = ind2.get(0);
        arr2[index2] -= k;
        ind2.remove(0);

        int diff2 = Integer.MAX_VALUE;
        if (ind2.size() == 0) {
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (int j : arr2) {
                min = Math.min(min, j);
                max = Math.max(max, j);
                diff2 = max - min;
            }
        } else {
            diff2 = solver(arr2, ind2, k);
        }
        return Math.min(diff, diff2);
    }
}