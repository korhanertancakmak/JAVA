package GeeksForGeeksChallenges.Medium;

import java.util.*;

/*
        Given K sorted arrays arranged in the form of a matrix of size K x K. The task is to merge them into one sorted
    array.

    Example 1:
    Input:
    K = 3
    arr[][] = {{1,2,3},{4,5,6},{7,8,9}}
    Output: 1 2 3 4 5 6 7 8 9
    Explanation:Above test case has 3 sorted arrays of size 3, 3, 3. arr[][] = [[1, 2, 3], [4, 5, 6], [7, 8, 9]]. The merged
    list will be [1, 2, 3, 4, 5, 6, 7, 8, 9].

    Example 2:
    Input:
    K = 4
    arr[][]={{1,2,3,4}, {2,2,3,4}, {5,5,6,6}, {7,8,9,9}}
    Output:
    1 2 2 2 3 3 4 4 5 5 6 6 7 8 9 9
    Explanation: Above test case has 4 sorted arrays of size 4, 4, 4, 4. arr[][] = [[1, 2, 2, 2], [3, 3, 4, 4], [5, 5, 6, 6],
    [7, 8, 9, 9 ]]. The merged list will be [1, 2, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 8, 9, 9].

    Your Task: You do not need to read input or print anything. Your task is to complete mergeKArrays() function which
    takes 2 arguments, an arr[K][K] 2D Matrix containing K sorted arrays and an integer K denoting the number of sorted
    arrays, as input and returns the merged sorted array ( as a pointer to the merged sorted arrays in cpp, as an ArrayList
    in java, and list in python)

    Expected Time Complexity: O(K2*Log(K))
    Expected Auxiliary Space: O(K2)

    Constraints:
    1 <= K <= 100
*/
public class MergeKSortedArrays {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of the array");
        int n = sc.nextInt();

        int[][] a = new int[n][n];
        System.out.println("Enter the elements of the array");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = sc.nextInt();
            }
        }

        ArrayList<Integer> arr = FindTheMerge.mergeKArrays(a, n);
        for (int i = 0; i < n * n; i++) {
            System.out.print(arr.get(i) + " ");
        }
        System.out.println();
    }
}

class FindTheMerge {
    //Function to merge k sorted arrays.
    public static ArrayList<Integer> mergeKArrays(int[][] arr, int K) {
        // Write your code here.

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < K; j++) {
                list.add(arr[i][j]);
            }
        }
        Collections.sort(list);
        return list;
    }
}

//Problem successfully Solved
//Test Cases Passed:
//74 /74
//Total Points Scored:
//4 /4
//Your Total Score:
//78
//Total Time Taken:
//2.75
//Your Accuracy:
//100%
//Attempts No.:
//1