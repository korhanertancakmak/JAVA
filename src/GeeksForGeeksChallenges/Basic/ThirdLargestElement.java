package GeeksForGeeksChallenges.Basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/*
        Given an array of distinct elements. Find the third largest element in it.

    Suppose you have A[] = {1, 2, 3, 4, 5, 6, 7}, its output will be 5 because it is the 3 largest element in the array A.

    Example 1:
    Input:
    N = 5
    A[] = {2,4,1,3,5}
    Output: 3

    Example 2:

    Input:
    N = 2
    A[] = {10,2}
    Output: -1

    Your Task: Complete the function thirdLargest() which takes the array a[] and the size of the array, n, as input
    parameters and returns the third largest element in the array. It return -1 if there are less than 3 elements in the
    given array.

    Expected Time Complexity: O(N)
    Expected Auxiliary Space: O(1)

    Constraints:
    1 ≤ N ≤ 105
    1 ≤ A[i] ≤ 105
*/
public class ThirdLargestElement {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter size of the array");
        int n =sc.nextInt();
        int[] arr = new int[n];
        System.out.println("Enter elements of the array");
        for(int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        FindThirdLargestElement g = new FindThirdLargestElement();
        System.out.println(g.thirdLargest(arr,n));
    }
}

class FindThirdLargestElement {
    int thirdLargest(int[] a, int n) {
        // Your code here
        if (n < 3) {
            return -1;
        }

        ArrayList<Integer> aList = new ArrayList<>();
        for (int el : a) {
            aList.add(el);
        }
        Collections.sort(aList);
        return aList.get(n - 3);
    }
}

//Problem successfully Solved
//Test Cases Passed:
//110 /110
//Total Points Scored:
//1 /1
//Your Total Score:
//112
//Total Time Taken:
//0.77
//Your Accuracy:
//100%
//Attempts No.:
//1