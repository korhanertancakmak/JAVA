package GeeksForGeeksChallenges.Basic;

import java.util.*;

/*
        Given an array A[] of n elements. Your task is to complete the Function num which returns an integer denoting the
    total number of times digit k appears in the whole array.

    For Example:
    Input:
    A[] = {11,12,13,14,15}, k=1
    Output:
    6
    Explanation: Here digit 1 appears in the whole array 6 times.

    Your Task: You don't need to read input or print anything. Complete the function Num() which accepts an integer N and
    array A as input parameter and return the desired output.

    Expected Time Complexity: O(N)
    Expected Auxiliary Space: O(1)

    Constraints:
    1<=n<=20
    1<=A[]<=1000
*/
public class FindNumberOfNumbers {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter size of array");
        int n = sc.nextInt();
        int[] a = new int[n];
        System.out.println("Enter elements of array");
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        System.out.println("Enter the digit you're looking for");
        int k = sc.nextInt();
        System.out.println(NumberOfNumbers.num(a, n, k));
    }
}

class NumberOfNumbers {
    public static int num(int[] a, int n, int k) {
        //Your code here

        int count = 0;
        for (int i = 0; i < n; i++) {
            String str = Integer.toString(a[i]);
            int strLength = str.length();
            int j = 0;
            while (j < strLength) {
                String str2 = str.substring(j, j + 1);
                if (Objects.equals(str2, Integer.toString(k))) {
                    count++;
                }
                j++;
            }
        }

        return count;
    }
}

//Problem successfully Solved
//Test Cases Passed:
//100 /100
//Total Points Scored:
//1 /1
//Your Total Score:
//115
//Total Time Taken:
//0.29
//Your Accuracy:
//100%
//Attempts No.:
//1