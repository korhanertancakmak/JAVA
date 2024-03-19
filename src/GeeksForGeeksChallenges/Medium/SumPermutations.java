package GeeksForGeeksChallenges.Medium;

import java.util.ArrayList;
import java.util.Scanner;

/*
        Given N distinct digits in an array A (from 1 to 9), Your task is to complete the function getSum which finds
    a sum of all n digit numbers that can be formed using these digits.

    Note: Since the output can be large, take modulo 1000000007

    Input: The first line of input contains an integer T denoting the no of test cases. Then T test cases follow. The
    first line of each test case contains an integer N. In the next line are N space separated digits.

    Output: For each test case in a new line output will be the sum of all n digit numbers that can be formed using these
    digits.

    Constraints:
    1<=T<=100
    1<=N<=9

    Example(To be used only for expected output):
    Input:
    2
    3
    1 2 3
    2
    1 2
    Output:
    1332
    33

    Explanation:
    For a first test case the numbers formed will be 123, 132, 312, 213, 231, 321
    sum = 123 + 132 + 312 + 213 + 231 + 321 = 1332

    For the second test case, the numbers formed will be 12, 21
    sum = 12 + 21 = 33
*/
public class SumPermutations {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int[] a = new int[10];
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }
            FindSumPermutations g = new FindSumPermutations();

            System.out.println(g.getSum(a,n));
        }
    }
}

class FindSumPermutations{
    public long getSum(int[] A,int n) {

        ArrayList<String> arr = new ArrayList<>();
        String S = "";
        for (int i = 0; i < n; i++){
            S += A[i];
        }
        perm(S.substring(0,1), S.substring(1), arr);
        long val = 0L;
        for (String s : arr) {
            val += Long.parseLong(s);
        }
        return val%1000000007;
    }
    public static void perm(String P , String S, ArrayList<String> arr){
        if (S.length() == 0) {
            arr.add(P);
            return ;
        }
        char ch = S.charAt(0);
        for (int i = 0; i < P.length()+1; i++) {
            String p = P.substring(0,i) + ch + P.substring(i);
            perm(p, S.substring(1), arr);
        }
    }
}

//Problem Solved Successfully
//Test Cases Passed:
//100 /100
//Total Points Scored:
//4 /4
//Your Total Score:
//63
//Total Time Taken:
//0.51
//Your Accuracy:
//100%
//Attempts No.:
//1