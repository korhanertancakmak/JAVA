package GeeksForGeeksChallenges.Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
        Given an array of n distinct elements. Find the minimum number of swaps required to sort the array in strictly
    increasing order.

    Example 1:
    Input:
    nums = {2, 8, 5, 4}
    Output:
    1
    Explaination: swap 8 with 4.

    Example 2:
    Input:
    nums = {10, 19, 6, 3, 5}
    Output:
    2
    Explaination: swap 10 with 3 and swap 19 with 5.

    Your Task: You do not need to read input or print anything. Your task is to complete the function minSwaps() which
    takes the nums as input parameter and returns an integer denoting the minimum number of swaps required to sort the
    array. If the array is already sorted, return 0.

    Expected Time Complexity: O(nlogn)
    Expected Auxiliary Space: O(n)

    Constraints:
    1 ≤ n ≤ 105
    1 ≤ numsi ≤ 106
*/
public class MinimumSwapsSortV2 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        while(T-->0)
        {
            int n = Integer.parseInt(br.readLine().trim());
            int[] nums = new int[n];
            String[] S = br.readLine().trim().split(" ");
            for(int i = 0; i < n; i++){
                nums[i] = Integer.parseInt(S[i]);
            }
            FindMinimumSwapsSortV2 obj = new FindMinimumSwapsSortV2();
            int ans = obj.minSwaps(nums);
            System.out.println(ans);
        }
    }
}

class FindMinimumSwapsSortV2 {

    static class pair{
        int a = 0, b = 0;
        pair (int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
    public void swap(pair p1, pair p2, ArrayList<pair> al) {
        pair temp = al.get(p1.b);
        al.set(p1.b, al.get(p2.b));
        al.set(p2.b, temp);
    }
    //Function to find the minimum number of swaps required to sort the array.
    public int minSwaps(int[] arr) {
        // Code here
        int n = arr.length;
        ArrayList<pair> al = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            al.add(new pair(arr[i], i));
        }

        al.sort((first, second) -> (first.a - second.a));
        int count =0;
        for (int i = 0; i < n; i++) {
            pair p = al.get(i);
            if (p.b != i) {
                swap(p, al.get(p.b), al);
                count++;
                i--;
            }

        }
        return count;
    }
}

//Problem successfully Solved
//Test Cases Passed:
//50 /50
//Total Points Scored:
//4 /4
//Your Total Score:
//67
//Total Time Taken:
//1.18
//Your Accuracy:
//7%
//Attempts No.:
//14