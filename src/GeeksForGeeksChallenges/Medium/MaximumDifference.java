package GeeksForGeeksChallenges.Medium;

import java.util.Scanner;

/*
        Given array A[] of integers, the task is to complete the function findMaxDiff which finds the maximum absolute
    difference between the nearest left and right smaller element of every element in array.If the element is the leftmost
    element, nearest smaller element on left side is considered as 0. Similarly, if the element is the rightmost elements,
    smaller element on right side is considered as 0.

    Examples:
    Input : arr[] = {2, 1, 8}
    Output : 1
    Left smaller  LS[] {0, 0, 1}
    Right smaller RS[] {1, 0, 0}
    Maximum Diff of abs(LS[i] - RS[i]) = 1

    Input  : arr[] = {2, 4, 8, 7, 7, 9, 3}
    Output : 4
    Left smaller   LS[] = {0, 2, 4, 4, 4, 7, 2}
    Right smaller  RS[] = {0, 3, 7, 3, 3, 3, 0}
    Maximum Diff of abs(LS[i] - RS[i]) = 7 - 3 = 4

    Input : arr[] = {5, 1, 9, 2, 5, 1, 7}
    Output : 1

    Input: The first line of input contains an integer T denoting the no of test cases. Then T test cases follow .Each
    test case contains an integer N denoting the size of the array A. In the next line are N space separated values of
    the array A.

    Output: For each test case output will be in a new line denoting the the maximum absolute difference between the nearest
    left and right smaller element of every element in array.

    Constraints:
    1<=T<=100
    1<=N<=100
    1<=A[ ]<=100

    Example(To be used only for expected output) :
    Input:
    2
    3
    2 1 8
    7
    2 4 8 7 7 9 3
    Output
    1
    4
*/
public class MaximumDifference {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter size of the array");
        int n = sc.nextInt();

        System.out.println("Enter elements of the array");
        int[] a = new int[n];
        for(int i=0; i<n; i++) {
            a[i] = sc.nextInt();
        }
        FindMaximumDifference ob = new FindMaximumDifference();
        System.out.println(ob.findMaxDiff(a,n));
    }
}

class FindMaximumDifference {
    int findMaxDiff(int[] a, int n) {

        int[] leftSmaller = new int[n];
        int[] rightSmaller = new int[n];

        // Finding the smaller element on left sides
        for (int i = 0; i < n; i++) {
            leftSmaller[i] = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (a[j] < a[i]) {
                    leftSmaller[i] = a[j];
                    break;
                }
            }
        }

        // Finding the smaller element on right sides
        for (int i = n - 1; i >= 0; i--) {
            rightSmaller[i] = 0;
            for (int j = i + 1; j < n; j++) {
                if (a[j] < a[i]) {
                    rightSmaller[i] = a[j];
                    break;
                }
            }
        }

        // Finding the max abs diff between left and right sides
        int maxDiff = 0;
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(leftSmaller[i] - rightSmaller[i]);
            maxDiff = Math.max(maxDiff, diff);
        }

        return maxDiff;
    }
}

//Problem successfully Solved
//Test Cases Passed:
//100 /100
//Total Points Scored:
//4 /4
//Your Total Score:
//82
//Total Time Taken:
//0.35
//Your Accuracy:
//100%
//Attempts No.:
//1