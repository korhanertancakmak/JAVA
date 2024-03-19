package GeeksForGeeksChallenges.Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

/*
        You are given an array arr of n integers. For each index i, you have to find the sum of all integers present in
    the array with a value less than arr[i].

    Example 1:
    Input:
    n = 3
    arr = {1, 2, 3}
    Output:
    0 1 3
    Explanation: For 1, there are no elements lesser than itself. For 2, only 1 is lesser than 2. And for 3, 1 and 2 are
    lesser than 3, so the sum is 3.

    Example 2:
    Input:
    n = 2
    arr = {4, 4}
    Output:
    0 0
    Explanation: For 4, there are no elements lesser than itself. For 4, there are no elements lesser than itself. There
    are no smaller elements than 4.

    Your Task: You don't need to read input or print anything. Your task is to complete the function smallerSum() which
    takes an integer n and an array arr and returns an array of length n, the answer for every index.

    Expected Time Complexity:O(n log n)
    Expected Space Complexity:O(n)

    Constraints:
    1 <= n <= 105
    0 <= arr[i] <= 109
*/
public class SmallerSumV2 {

    public static void main(String[] args)throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        System.out.println("Enter '1' for default");
        int t = Integer.parseInt(in.readLine());
        while(t-->0){
            System.out.println("Enter the size of the array");
            int n = Integer.parseInt(in.readLine());
            System.out.println("Enter the array");
            String[] s = in.readLine().trim().split(" ");
            int[] a = new int[n];
            for(int i=0;i<n;i++){
                a[i] = Integer.parseInt(s[i]);
            }
            FindSmallerSumV2 ob = new FindSmallerSumV2();
            long[] ans = ob.smallerSum(n, a);
            for(long i : ans){
                out.print(i+" ");
            }
            out.println();
        }
        out.close();
    }
}

class FindSmallerSumV2 {
    public long[] smallerSum(int n, int[] arr) {

        long[] ans = new long[n];
        long[] temp = new long[n];
        for (int i = 0; i < n; i++) {
            temp[i] = arr[i];
        }

        Arrays.sort(temp);

        long[] pre = new long[n];
        pre[0] = temp[0];
        for (int i = 1; i < n; i++){
            pre[i] = temp[i] + pre[i-1];
        }

        for (int i = 0; i < n; i++) {
            if (temp[0] >= arr[i]) {
                ans[i] = 0;
                continue;
            }

            int l = 0, r = n-1;
            while(l < r) {

                int mid = (l + r + 1) / 2;
                if (temp[mid] >= arr[i]) {
                    r = mid - 1;
                } else {
                    l = mid;
                }
            }
            ans[i] = pre[l];
        }
        return ans;
    }

}

// This is the version of full answer in geeksforgeeks.org