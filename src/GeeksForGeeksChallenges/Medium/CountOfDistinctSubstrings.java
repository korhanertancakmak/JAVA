package GeeksForGeeksChallenges.Medium;
/*
        Given a string of length N of lowercase alphabet characters. The task is to complete the function countDistinctSubstring(),
    which returns the count of total number of distinct substrings of this string.

    Input: The first line of input contains an integer T, denoting the number of test cases. Then T test cases follow.
    Each test case contains a string str.

    Output: For each test case in a new line, output will be an integer denoting count of total number of distinct substrings of
    this string.

    User Task: Since this is a functional problem you don't have to worry about input, you just have to complete the function
    countDistinctSubstring().

    Constraints:
    1 ≤ T ≤ 10
    1 ≤ N ≤ 1000

    Example (To be used only for expected output):
    Input:
    2
    ab
    ababa

    Output:
    4
    10

    Exaplanation:
    Testcase 1: For the given string "ab" the total distinct substrings are: "", "a", "b", "ab".
    Testcase 1: For the given string "ab" the total distinct substrings are: "", "a", "b", "ab", "ba", "aba", "bab", "abab",
    "baba", "ababa".
*/

import java.util.*;
public class CountOfDistinctSubstrings {

    public static void main (String[] args) {
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter no of test cases");
        int t = sc.nextInt();

        while(t-- > 0) {
            System.out.println("Enter the string");
            String st = sc.next();
            System.out.println(FindCountOfDistinctSubstrings.countDistinctSubstring(st));
        }
    }
}

class FindCountOfDistinctSubstrings {
    public static int countDistinctSubstring(String st) {

        int n = st.length();
        ArrayList<String> comb = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = i; j <= n; j++) {
                String str = st.substring(i, j);
                if (!comb.contains(str)) {
                    comb.add(str);
                }
            }
        }

        return comb.size();
    }
}

//Compilation Completed
//For Input:
//3
//ab
//ababa
//kxiksxjecwmkwabhsl
//Your Output:
//4
//10
//167
//Expected Output:
//4
//10
//167