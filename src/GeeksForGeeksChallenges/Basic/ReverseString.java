package GeeksForGeeksChallenges.Basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
    You are given a string s. You need to reverse the string.

    Example 1:
    Input:
    s = Geeks
    Output: skeeG

    Example 2:
    Input:
    s = for
    Output: rof

    Your Task: You only need to complete the function reverseWord() that takes s as parameter and returns the reversed
    string.

    Expected Time Complexity: O(|S|).
    Expected Auxiliary Space: O(1).

    Constraints:
    1 <= |s| <= 10000
*/
public class ReverseString {

    public static void main(String[] args)throws IOException
    {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());

        while(t-- >0)
        {
            String str = read.readLine();
            System.out.println(Reverse.reverseWord(str));
        }
    }
}

class Reverse {

    // Complete the function
    // str: input string
    public static String reverseWord(String str)
    {
        // Reverse the string str
        StringBuilder strbldr = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            strbldr.append(str.charAt(i));
        }
        strbldr.reverse();
        return strbldr.toString();
    }
}