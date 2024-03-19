package GeeksForGeeksChallenges.Easy;

import java.util.Scanner;

/*
        Given an integer N. The task is to return the position of first set bit found from the right side in the binary
    representation of the number. If there is no set bit in the integer N, then return 0 from the function.

    Example 1:
    Input:
    N = 18
    Output:
    2
    Explanation: Binary representation of 18 is 010010,the first set bit from the right side is at position 2.

    Example 2:
    Input:
    N = 12
    Output:
    3
    Explanation: Binary representation of  12 is 1100, the first set bit from the right side is at position 3.

    Your Task: The task is to complete the function getFirstSetBit() that takes an integer n as a parameter and returns
    the position of first set bit.

    Expected Time Complexity: O(log N).
    Expected Auxiliary Space: O(1).

    Constraints:
    0 <= N <= 108
*/
public class FirstSetBit {

    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);

        System.out.println("Enter n");
        int n=sc.nextInt();                                         //input n
        System.out.println(FindFirstSetBit.getFirstSetBit(n));      //calling method
    }
}

//User function Template for Java

class FindFirstSetBit {
    //Function to find position of first set bit in the given number.
    public static int getFirstSetBit(int n) {
        // Your code here
        if (n == 0)
            return 0;

        String binaryRes = Integer.toBinaryString(n);
        int length = binaryRes.length();
        int count = 1;
        for (int i = length; i >= 0; i--) {
            String res = binaryRes.substring(i - 1, i);
            if (Integer.parseInt(res) == 1) {
                return count;
            }
            count++;
        }
        return count;
    }
}

//Problem successfully Solved
//Test Cases Passed:
//1120 /1120
//Total Points Scored:
//2 /2
//Your Total Score:
//95
//Total Time Taken:
//0.34
//Your Accuracy:
//50%
//Attempts No.:
//2