package GeeksForGeeksChallenges.Basic;

import java.io.IOException;
import java.util.Scanner;

/*
        Given a sorted array of size N and an integer K, find the position(0-based indexing) at which K is present in the
    array using binary search.

    Example 1:
    Input:
    N = 5
    arr[] = {1 2 3 4 5}
    K = 4
    Output: 3
    Explanation: 4 appears at index 3.

    Example 2:
    Input:
    N = 5
    arr[] = {11 22 33 44 55}
    K = 445
    Output: -1
    Explanation: 445 is not present.

    Your Task: You don't need to read input or print anything. Complete the function binarySearch() which takes arr[], N
    and K as input parameters and returns the index of K in the array. If K is not present in the array, return -1.

    Expected Time Complexity: O(LogN)
    Expected Auxiliary Space: O(LogN) if solving recursively and O(1) otherwise.

    Constraints:
        1 <= N <= 105
        1 <= arr[i] <= 106
        1 <= K <= 106
*/
public class BinarySearch {

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter size of the array");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.println("Enter elements of the array");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println("Enter the key element");
        int key = sc.nextInt();
        FindBinarySearch g = new FindBinarySearch();
        System.out.println(g.binarysearch(arr, n, key));
    }
}

class FindBinarySearch {
    int binarysearch(int[] arr, int n, int k) {
        // code here
        int l = 0;
        int r = n - 1;
        int result = -1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (arr[mid] < k) {
                l = mid + 1;
            } else if (arr[mid] > k) {
                r = mid - 1;
            } else {
                result = mid;
                break;
            }
        }
        return result;
    }
}

//Problem successfully Solved
//Test Cases Passed:
//152 /152
//Total Points Scored:
//1 /1
//Your Total Score:
//109
//Total Time Taken:
//2.08
//Your Accuracy:
//100%
//Attempts No.:
//1