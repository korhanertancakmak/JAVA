package GeeksForGeeksChallenges.Easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

/*
        Given an unsorted array A of size N that contains only positive integers, find a continuous sub-array that adds
    to a given number S and return the left and right index(1-based indexing) of that subArray.

    In the case of multiple subArrays, return the subArray indexes which come first on moving from left to right.

    NOTE : You have to return an ArrayList consisting of two elements left and right. In case no such subArray exists,
    return an array consisting of element -1.

    Example 1:
    Input:
    N = 5, S = 12
    A[] = {1,2,3,7,5}
    Output: 2 4
    Explanation: The sum of elements from 2nd position to 4th position is 12.

    Example 2:
    Input:
    N = 10, S = 15
    A[] = {1,2,3,4,5,6,7,8,9,10}
    Output: 1 5
    Explanation: The sum of elements from 1st position to 5th position is 15.

    Your Task:
    You don't need to read input or print anything. The task is to complete the function subArraySum() which takes arr,
    N, and S as input parameters and returns an ArrayList containing the starting and ending positions of the first such
    occurring subArray from the left where sum equals to S. The two indexes in the array should be according to 1-based
    indexing. If no such subArray is found, return an array consisting of only one element that is -1.

    Expected Time Complexity: O(N)
    Expected Auxiliary Space: O(1)

    Constraints:
    1 <= N <= 105
    1 <= Ai <= 109
    0<= S <= 109
*/
public class SubArrayWithGivenSum {

    static BufferedReader br;
    static PrintWriter ot;
    public static <Solution> void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        ot = new PrintWriter(System.out);

        System.out.println("Enter the input as '1' for default");
        int t = Integer.parseInt(br.readLine());

        while(t-->0){

            System.out.println("Enter the input as 'N S'");
            String[] s = br.readLine().trim().split(" ");

            int n = Integer.parseInt(s[0]);
            int k = Integer.parseInt(s[1]);
            int[] a = new int[n];
            System.out.println("Enter the elements of the array");
            s = br.readLine().trim().split(" ");
            for(int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(s[i]);
            }

            ArrayList<Integer> res = FindSubArraySum.subArraySum(a, n, k);
            for (Integer re : res) ot.print(re + " ");

            ot.println();
        }
        ot.close();
    }
}

class FindSubArraySum {
    static ArrayList<Integer> subArraySum(int[] arr, int n, int s) {

        int starting = 0, ending = 1, sum = arr[0];
        while (sum != s & starting != n) {
            sum += arr[ending];
            if (sum < s) {
                ending++;
            } else if (sum > s) {
                starting++;
                ending = starting + 1;
                sum = arr[starting];
            }
        }
        ArrayList<Integer> res = new ArrayList<>();
        res.add(starting + 1);
        res.add(ending + 1);
        return res;
    }
}
