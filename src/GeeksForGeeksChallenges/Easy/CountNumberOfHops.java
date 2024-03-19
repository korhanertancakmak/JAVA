package GeeksForGeeksChallenges.Easy;

import java.util.Scanner;

/*
        A frog jumps either 1, 2, or 3 steps to go to the top. In how many ways can it reach the top of Nth step. As the
    answer will be large find the answer modulo 1000000007.

    Example 1:
    Input:
    N = 1
    Output: 1

    Example 2:
    Input:
    N = 4
    Output: 7
    Explanation: Below are the 7 ways to reach 4
    1 step + 1 step + 1 step + 1 step
    1 step + 2 step + 1 step
    2 step + 1 step + 1 step
    1 step + 1 step + 2 step
    2 step + 2 step
    3 step + 1 step
    1 step + 3 step

    Your Task: Your task is to complete the function countWays() which takes 1 argument(N) and returns the answer %
    (10^9 + 7).

    Expected Time Complexity: O(N).
    Expected Auxiliary Space: O(1).

    Constraints:
    1 ≤ N ≤ 105
*/
public class CountNumberOfHops {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        System.out.println("Enter the step number");
        int n = Integer.parseInt(kb.nextLine().trim());
        System.out.println(FindCountNumberOfHops.countWays(n));
    }
}

class FindCountNumberOfHops {
    //Function to count the number of ways in which frog can reach the top.
    static long countWays(int n) {
        // add your code here
        long[] res = new long[n + 1];

        if (n < 2) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else {
            res[0] = 1;
            res[1] = 1;
            res[2] = 2;
        }

        for (int i = 3; i <= n; i++) {
            res[i] = (res[i - 3] + res[i - 2] + res[i - 1]) % 1000000007;
        }

        return res[n] ;
    }

}

//Problem successfully Solved
//Test Cases Passed:
//1120 /1120
//Total Points Scored:
//2 /2
//Your Total Score:
//93
//Total Time Taken:
//0.17
//Your Accuracy:
//33%
//Attempts No.:
//3