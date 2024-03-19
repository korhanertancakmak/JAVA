package GeeksForGeeksChallenges.Medium;

import java.util.Arrays;
import java.util.Scanner;

/*
        Given an array arr[] of n integers. Check whether it contains a triplet that sums up to zero.

    Note: Return 1, if there is at least one triplet following the condition else return 0.

    Example 1:
    Input: n = 5, arr[] = {0, -1, 2, -3, 1}
    Output: 1
    Explanation: 0, -1 and 1 form a triplet with the sum equal to 0.

    Example 2:
    Input: n = 3, arr[] = {1, 2, 3}
    Output: 0
    Explanation: No triplet with the sum "0" exists.

    Your Task: You don't need to read input or print anything. Your task is to complete the boolean function findTriplets()
    which takes the array arr[] and the size of the array (n) as inputs and print 1 if the function returns true else
    print "0" if the function returns false.

    Expected Time Complexity: O(n2)
    Expected Auxiliary Space: O(1)

    Constraints:
    1 <= n <= 104
    -106 <= Ai <= 106
*/
public class FindTripletsWithZeroSum {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the test number '1' for default");
        int t = sc.nextInt();

        while (t-- > 0) {
            System.out.println("Enter the number of elements in the array");
            int n = sc.nextInt();
            int[] a = new int[n];
            System.out.println("Enter the elements of the array");
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }
            FindZeroSum g = new FindZeroSum();
            if (g.findTriplets(a,n))
                System.out.println("1");
            else
                System.out.println("0");

        }
    }
}

class FindZeroSum {
    public boolean findTriplets(int[] arr , int n) {

        // sort array elements
        Arrays.sort(arr);

        for (int i = 0; i < n - 1; i++) {
            // initialize left and right
            int l = i + 1;
            int r = n - 1;
            int x = arr[i];
            while (l < r) {
                if (x + arr[l] + arr[r] == 0) {
                    // print elements if its sum is given 0.
                    return true;
                } else if (x + arr[l] + arr[r] < 0) {
                    // If the sum of three elements is less than '0' then increment on the left
                    l++;
                } else {
                    // if the sum is greater than 0, then decrement on the right side
                    r--;
                }
            }
        }
        return false;
    }
}

//Problem successfully Solved
//Test Cases Passed:
//1120 /1120
//Total Points Scored:
//4 /4
//Your Total Score:
//71
//Total Time Taken:
//0.43
//Your Accuracy:
//100%
//Attempts No.:
//1