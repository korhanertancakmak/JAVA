package GeeksForGeeksChallenges.Basic;

import java.util.Scanner;

/*
    Given a number N, find the first N Fibonacci numbers. The first two number of the series are 1 and 1.

    Example 1:
    Input:
    N = 5
    Output: 1 1 2 3 5

    Example 2:
    Input:
    N = 7
    Output: 1 1 2 3 5 8 13

    Your Task: Your task is to complete printFibb() which takes single argument N and returns a list of first N Fibonacci
    numbers.

    Expected Time Complexity: O(N).
    Expected Auxiliary Space: O(N).
    Note: This space is used to store and return the answer for printing purpose.

    Constraints:
    1<= N <=84
*/
public class PrintFirstFibonacciNumbers {

    public static void main (String[] args) {

        //taking input using Scanner class
        Scanner sc=new Scanner(System.in);

        //taking total number of elements
        int n=sc.nextInt();

        //calling printFibb() method
        long[] res = FindFirstFibonacciNumbers.printFibb(n);

        //printing the elements of the array
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + " ");
        }
        System.out.println();
    }
}

class FindFirstFibonacciNumbers {
    //Function to return list containing first n fibonacci numbers.
    public static long[] printFibb(int n) {
        long[] res = new long[n];
        if (n == 1) {
            res[0] = 1;
            return res;
        } else if (n == 2) {
            res[0] = 1;
            res[1] = 1;
            return res;
        }

        res[0] = 1;
        res[1] = 1;
        for (int i = 2; i < n; i++) {
            res[i] = res[i - 1] + res [i - 2];
        }

        return res;
    }
}

//Problem successfully Solved
//Test Cases Passed:
//100 /100
//Total Points Scored:
//1 /1
//Your Total Score:
//74
//Total Time Taken:
//0.34
//Your Accuracy:
//50%
//Attempts No.:
//2