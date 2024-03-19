package GeeksForGeeksChallenges.Medium;
/*
        Given two unsorted arrays arr1[] and arr2[]. They may contain duplicates. For each element in arr1[] count elements
    less than or equal to it in array arr2[].

    Example 1:

    Input:
    m = 6, n = 6
    arr1[] = {1,2,3,4,7,9}
    arr2[] = {0,1,2,1,1,4}
    Output: 4 5 5 6 6 6
    Explanation: Number of elements less than or equal to 1, 2, 3, 4, 7, and 9 in the second array are respectively 4,5,
    5,6,6,6

    Example 2:
    Input:
    m = 5, n = 7
    arr1[] = {4,8,7,5,1}
    arr2[] = {4,48,3,0,1,1,5}
    Output: 5 6 6 6 3
    Explanation: Number of elements less than or equal to 4, 8, 7, 5, and 1 in the second array are respectively 5,6,6,6
    ,3

    Your Task : Complete the function countEleLessThanOrEqual() that takes two array arr1[], arr2[], m, and n as input
    and returns an array containing the required results(the count of elements less than or equal to it in arr2 for each
    element in arr1 where ith output represents the count for ith element in arr1.)

    Expected Time Complexity: O(m + n).
    Expected Auxiliary Space: O(m).

    Constraints:
    1<=m,n<=10^5
    0<=arr1[i],arr2[j]<=10^5
*/
import java.util.*;
public class CountingElementsInTwoArrays {

    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter sizes of the array m and n");
        int m = sc.nextInt();
        int n = sc.nextInt();

        int[] arr1 = new int[m];
        int[] arr2 = new int[n];

        System.out.println("Enter elements of the first array");
        for(int i = 0; i < m; i++)
            arr1[i] = sc.nextInt();

        System.out.println("Enter elements of the second array");
        for(int i = 0; i < n; i++)
            arr2[i] = sc.nextInt();

        ArrayList<Integer> res = FindCountingElementsInTwoArrays.countEleLessThanOrEqual(arr1, arr2, m, n);
        for (Integer re : res) System.out.print(re + " ");
        System.out.println();
    }
}

class FindCountingElementsInTwoArrays {

    static int binarySearch(int[] arr, int x) {
        int l = 0, r = arr.length - 1;
        int ans = -1;
        while (l <= r) {
            int mid = (l + r) / 2;

            if (arr[mid] <= x) {                        // If x is greater, ignore left half
                ans = mid;
                l = mid + 1;
            } else                                      // If x is smaller, ignore right half
                r = mid - 1;
        }
        return ans;                                     // Return index of the last occurrence of x
    }
    public static ArrayList<Integer> countEleLessThanOrEqual(int[] arr1, int[] arr2, int m, int n) {

        // Sort the second array
        Arrays.sort(arr2);

        // For each element in the first array, count elements less than or equal to it in the second array
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int index = binarySearch(arr2, arr1[i]);
            if (index != -1) {
                result.add(index + 1);
            } else {
                result.add(0);
            }
        }

        return result;
    }
}

//Problem successfully Solved
//Test Cases Passed:
//432 /432
//Total Points Scored:
//4 /4
//Your Total Score:
//104
//Total Time Taken:
//4.24
//Your Accuracy:
//100%
//Attempts No.:
//1