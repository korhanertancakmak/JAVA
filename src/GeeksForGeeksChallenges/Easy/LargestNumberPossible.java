package GeeksForGeeksChallenges.Easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
        Given two numbers 'N' and 'S', find the largest number that can be formed with 'N' digits and whose sum of digits
    should be equals to 'S'. Return -1 if it is not possible.

    Example 1:
    Input: N = 2, S = 9
    Output: 90
    Explaination: It is the biggest number with sum of digits equals to 9.

    Example 2:
    Input: N = 3, S = 20
    Output: 992
    Explaination: It is the biggest number with sum of digits equals to 20.

    Your Task: You do not need to read input or print anything. Your task is to complete the function findLargest() which takes N and S as input parameters and returns the largest possible number. Return -1 if no such number is possible.

    Expected Time Complexity: O(N)
    Exepcted Auxiliary Space: O(1)

    Constraints:
    1 ≤ N ≤ 104
    0 ≤ S ≤ 105
*/
public class LargestNumberPossible {

    public static void main(String[] args)throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter test no");
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0){
            System.out.println("Enter 'N S'. N is no of digits of the largest no. S is the sum of its digits");
            String[] a = in.readLine().trim().split("\\s+");
            int N = Integer.parseInt(a[0]);
            int S = Integer.parseInt(a[1]);

            System.out.println(FindLargestNumberPossible.findLargest(N, S));
        }
    }
}

class FindLargestNumberPossible{
    static String findLargest(int N, int S) {

        StringBuilder ans = new StringBuilder();
        if (S == 0 && N > 1) {
            return "-1";
        }

        for(int i = 0; i < N; i++){
            int temp = Math.min(S, 9);
            ans.append(temp);
            S -= temp;
        }

        if (S != 0) {
            return "-1";
        }

        return ans.toString();
    }
}

//Problem successfully Solved
//Test Cases Passed:
//1120 /1120
//Total Points Scored:
//2 /2
//Your Total Score:
//73
//Total Time Taken:
//0.31
//Your Accuracy:
//33%
//Attempts No.:
//3