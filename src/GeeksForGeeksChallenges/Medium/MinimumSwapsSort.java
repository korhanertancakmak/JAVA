package GeeksForGeeksChallenges.Medium;

import java.util.*;
import java.lang.*;
import java.io.*;

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
public class MinimumSwapsSort {

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
            FindMinimumSwapsSort obj = new FindMinimumSwapsSort();
            int ans = obj.minSwaps(nums);
            System.out.println(ans);
        }
    }
}

class FindMinimumSwapsSort {
    //Function to find the minimum number of swaps required to sort the array.
    public int minSwaps(int[] nums) {

        int n = nums.length;
        ArrayList<Integer> AList = new ArrayList<>();
        for (int el : nums) {
            AList.add(el);
        }

        Arrays.sort(nums);

        int nSwap = 0, i = 0, j = i, numsEl, listEl, listIdx;
        while(i < n) {

            if (AList.get(i) != nums[i]) {
                numsEl = nums[i];
                listIdx = AList.indexOf(numsEl);
                listEl = AList.get(i);
                AList.set(i, numsEl);
                AList.set(listIdx, listEl);
                nSwap++;
                if (AList.get(j + 1) == nums[j + 1]) {
                    j += 2;
                } else {
                    j++;
                }
                i = j;
            } else {
                i++;
            }
        }

        return nSwap;
    }
}

//Runtime Error
//Test Cases Passed: 10 /50
//
//Time Limit Exceeded
//Your program took more time than expected.Expected Time Limit : 3.2sec