package GeeksForGeeksChallenges.Basic;

import java.io.IOException;
import java.util.Scanner;

/*
        Given a non-negative integer N. The task is to check if N is a power of 2. More formally, check if N can be expressed
    as 2^x for some integer x.

    Example 1:
    Input:
    N = 8
    Output:
    YES
    Explanation: 8 is equal to 2 raised to 3 (2^3 = 8).

    Example 2:

    Input:
    N = 98
    Output:
    NO
    Explanation: 98 cannot be obtained by any power of 2.

    Your Task: Your task is to complete the function isPowerofTwo() which takes n as a parameter and returns true or false
    by checking if the given number can be represented as a power of two or not.

    Expected Time Complexity:O(log N).
    Expected Auxiliary Space:O(1).

    Constraints:
    0 ≤ N ≤1018
*/
public class PowerOf2 {
    public static void main(String args[])throws IOException {

        Scanner sb = new Scanner(System.in);
        //input a number n
        long n = Long.parseLong(sb.nextLine());
        // if n is less than equal to zero then
        //it can't be a power of 2 so we print No
        if (FindPowerOf2.isPowerOfTwo(n)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}

class FindPowerOf2{

    // Function to check if given number n is a power of two.
    public static boolean isPowerOfTwo(long n) {
        // Your code here
        if (n < 1 ) {
            return false;
        } else if (n == 1) {
            return true;
        } else if (n % 2 != 0) {
            return false;
        }

        long k = n / 2;
        while (k % 2 == 0) {
            k /= 2;
        }

        return k == 1;
    }

}

//Problem successfully Solved
//Test Cases Passed:
//1183 /1183
//Total Points Scored:
//1 /1
//Your Total Score:
//91
//Total Time Taken:
//0.17
//Your Accuracy:
//50%
//Attempts No.:
//2