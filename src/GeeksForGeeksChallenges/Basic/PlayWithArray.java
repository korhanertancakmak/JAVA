package GeeksForGeeksChallenges.Basic;

import java.util.Arrays;
import java.util.Scanner;

/*
        Given an unsorted array arr of size N, rearrange the elements of array such that number at the odd index is greater
    than the number at the previous even index. The task is to complete the function formatArray() which will return
    formatted array.

    NOTE: If the returned array formed is according to rules, 1 will be printed else 0 will be printed.

    Example 1:
    Input:
    n = 5
    arr[] = {5, 4, 3, 2, 1}

    Output:
    1
    Explanation: The given array after modification will be as such: 4 5 2 3 1.

    Example 2:
    Input:
    n = 4
    arr[] = {4, 3, 2, 1}

    Output:
    1

    User task: Since this is a functional problem you don't have to worry about the input, you just have to complete the
    function formatArray(), which accepts array arr and its size

    Constraints:
    1 <= N <= 100
    1 <= arr[i] <= 100
*/
public class PlayWithArray {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter size of array");
        int n = sc.nextInt();
        int[] a = new int[n];

        System.out.println("Enter elements of array");
        for(int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        PlayArray g = new PlayArray();
        int[] b = g.formatArray(a, n);
        int flag = 1;
        if(b.length == a.length){
            for (int i = 1; i < n; i += 2) {
                if (b[i] < b[i - 1]) {
                    flag = 0;
                }
                if (flag == 0) {
                    System.out.println("0");
                } else {
                    Arrays.sort(a);
                    Arrays.sort(b);
                }
            }
            for (int i = 0; i < n ; i++) {
                if (a[i] != b[i]) {
                    flag = 0;
                }
                System.out.println(flag);
            }
        } else {
            System.out.println("0");
        }
    }
}

class PlayArray {
    public int[] formatArray(int[] a,int n) {
        // add code here.
        int temp = 0;
        for (int i = 0; i < n - 1; i += 2) {
            if (a[i] < a[i + 1]) {
                continue;
            } else {
                temp = a[i];
                a[i] = a[i + 1];
                a[i + 1] = temp;
            }
        }
        return a;
    }
}

//Problem successfully Solved
//Test Cases Passed:
//100 /100
//Total Points Scored:
//1 /1
//Your Total Score:
//117
//Total Time Taken:
//0.35
//Your Accuracy:
//100%
//Attempts No.:
//1
