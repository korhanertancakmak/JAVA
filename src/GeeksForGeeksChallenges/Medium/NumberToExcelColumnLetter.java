package GeeksForGeeksChallenges.Medium;

/*
        Given a positive integer, return its corresponding column title as appear in an Excel sheet.
    Excel columns has a pattern like A, B, C, … ,Z, AA, AB, AC,…. ,AZ, BA, BB, … ZZ, AAA, AAB ….. etc. In other words,
    column 1 is named as “A”, column 2 as “B”, column 27 as “AA” and so on.

    Example 1:
    Input:
    N = 28
    Output: AB
    Explanation: 1 to 26 are A to Z. Then, 27 is AA and 28 = AB.

    Example 2:
    Input:
    N = 13
    Output: M
    Explanation: M is the 13th character of alphabet.

    Example 3:
    Input:
    N = 546468486503029
    Output: CVPUZOPLGPY

    Your Task:
    You don't need to read input or print anything. Your task is to complete the function colName() which takes the column
    number N as input and returns the column name represented as a string.
    Expected Time Complexity: O(LogN).
    Expected Auxiliary Space: O(1).

    Constraints:
    1 <= N <= 1018


*/

import java.util.Scanner;

public class NumberToExcelColumnLetter {
    public static void main (String[] args)
    {

        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while(t-- > 0)
        {
            long n = sc.nextLong();
            System.out.println (new ColomnName().colName(n));
        }

    }
}

class ColomnName {

    String colName (long n) {
        String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
                "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        int len = alphabet.length;
        long res = 0, rem = 0, m = n - 1;
        double rootN = Math.log(m) / Math.log(len);
        StringBuilder result = new StringBuilder();
        int count = 0;
        do  {
            if (count == 0) {
                res =  m / len;
                rem =  m % len;
            } else {
                rem = (res - 1) % len;
                res = (res - 1) / len;
            }
            if (rootN < 1) {
                result.append(alphabet[(int) rem]);
            } else {
                result.append(alphabet[(int) rem]);
                if (res <= len) {
                    result.append(res == 0 ? "" : alphabet[(int)res - 1]);
                }
            }
            count++;
        } while (res > len);
        result.reverse();
        return result.toString();
    }
}