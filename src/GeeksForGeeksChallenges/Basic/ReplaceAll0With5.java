package GeeksForGeeksChallenges.Basic;

import java.util.Scanner;

/*
    You are given an integer N. You need to convert all zeroes of N to 5.

    Example 1:
    Input:
    N = 1004
    Output: 1554
    Explanation: There are two zeroes in 1004 on replacing all zeroes with "5", the new number will be "1554".

    Example 2:
    Input:
    N = 121
    Output: 121
    Explanation: Since there are no zeroes in "121", the number remains as "121".

    Your Task: Your task is to complete the function convertFive() which takes an integer N as an argument and replaces
    all zeros in the number N with 5. Your function should return the converted number.

    Expected Time Complexity: O(K) where K is the number of digits in N
    Expected Auxiliary Space: O(1)

    Constraints:
    1 <= n <= 10000
*/
public class ReplaceAll0With5 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number");
        int N = sc.nextInt();
        System.out.println(new FindReplaceAll0With5().convertFive(N));
    }
}

class FindReplaceAll0With5 {
    int convertFive(int num) {
        // Your code here
        String asStr = Integer.toString(num);
        String asNewStr = asStr.replace('0','5');
        return Integer.parseInt(asNewStr);
    }
}

//Problem successfully Solved
//Test Cases Passed:
//300 /300
//Total Points Scored:
//1 /1
//Your Total Score:
//110
//Total Time Taken:
//0.2
//Your Accuracy:
//100%
//Attempts No.:
//1