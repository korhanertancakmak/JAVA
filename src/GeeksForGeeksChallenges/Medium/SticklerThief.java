package GeeksForGeeksChallenges.Medium;

import java.util.Scanner;

/*
        Stickler the thief wants to loot money from a society having n houses in a single line. He is a weird person and
    follows a certain rule when looting the houses. According to the rule, he will never loot two consecutive houses. At
    the same time, he wants to maximize the amount he loots. The thief knows which house has what amount of money but is
    unable to come up with an optimal looting strategy. He asks for your help to find the maximum money he can get if he
    strictly follows the rule. ith house has a[i] amount of money present in it.

    Example 1:
    Input:
    n = 5
    a[] = {6,5,5,7,4}
    Output:
    15
    Explanation: Maximum amount he can get by looting 1st, 3rd and 5th house. Which is 6+5+4=15.

    Example 2:
    Input:
    n = 3
    a[] = {1,5,3}
    Output:
    5
    Explanation: Loot only 2nd house and get maximum amount of 5.

    Your Task: Complete the functionFindMaxSum() which takes an array arr[] and n as input which returns the maximum money
    he can get following the rules.

    Expected Time Complexity:O(N).
    Expected Space Complexity:O(N).

    Constraints:
    1 ≤ n ≤ 105
    1 ≤ a[i] ≤ 104
*/
public class SticklerThief {

    public static void main (String[] args) {
        //taking input using Scanner class
        Scanner sc = new Scanner(System.in);

        //taking count of houses
        System.out.println("Enter the number of houses");
        int n = sc.nextInt();
        int[] arr = new int[n];

        //inserting money present in
        //each house in the array
        System.out.println("Enter the money in each house");
        for (int i = 0; i < n; ++i) {
            arr[i] = sc.nextInt();
        }

        //calling method FindMaxSum() of class solve
        System.out.println(new FindSticklerThief().FindMaxSum(arr, n));
    }
}

class FindSticklerThief {
    //Function to find the maximum money the thief can get.
    public int FindMaxSum(int[] arr, int n) {
        // Your code here
        if (n == 0) {
            return 0;
        }

        int val1 = arr[0];
        if(n == 1) {
            return val1;
        }

        int val2 = Math.max(arr[0], arr[1]);
        if (n == 2) {
            return val2;
        }

        int sum = 0;
        for(int i = 2; i < n; i++) {
            sum = Math.max(arr[i] + val1, val2);
            val1 = val2;
            val2 = sum;
        }
        return sum;
    }
}

//Problem successfully Solved
//Test Cases Passed:
//1120 /1120
//Total Points Scored:
//4 /4
//Your Total Score:
//100
//Total Time Taken:
//1.65
//Your Accuracy:
//50%
//Attempts No.:
//2