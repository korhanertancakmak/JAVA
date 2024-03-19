package GeeksForGeeksChallenges.Basic;

import java.util.*;

/*
        Given an array consisting of N positive integers, and an integer k. You have to find the maximum product of k
    contiguous elements in the array. Your task is to complete the function which takes three arguments .The first argument
    is the array A[ ] and the second argument is an integer N denoting the size of the array A[ ] and the third argument
    is an integer k. The function will return and value denoting the largest product of sub-array of size k.

    Input: The first line of each test case is two integer N and K separated by space. The next line contains N space
    separated values of the array A[].

    Output: Output of each test case will be an integer denoting the largest product of subArray of size k.

    Example:
    Input
    4 2
    1 2 3 4
    Output
    12

    Explanation: The sub-array of size 2 will be 3 4 and the product is 12.

    Note: The Input/Output format and Example given are used for the system's internal purpose, and should be used by a
    user for Expected Output only. As it is a function problem, hence a user should not read any input from stdin/console.
    The task is to complete the function specified, and not to write the full code.

    Constraints:
    1 <=T<= 100
    1 <=N<= 10
    1 <=K<= N
    1<=A[]<=10
*/
public class LargestProduct {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter size(n) and key(k) values");
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        System.out.println("Enter the elements of the array");
        for(int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        FindLargestProduct g = new FindLargestProduct();
        System.out.println(g.findMaxProduct(arr, n , k));
    }
}

class FindLargestProduct {
    long findMaxProduct(int[] A, int n, int k) {
        // Your code here
        long result = 0;
        int sub = k;
        for (int i = 0; i <= n - k; i++) {
            long product = 1;
            while (sub-- != 0) {
                product *= A[i + sub];
            }
            result = Math.max(product, result);
            sub = k;
        }

        return result;
    }
}

//Problem successfully Solved
//Test Cases Passed:
//101 /101
//Total Points Scored:
//1 /1
//Your Total Score:
//111
//Total Time Taken:
//0.24
//Your Accuracy:
//50%
//Attempts No.:
//2
