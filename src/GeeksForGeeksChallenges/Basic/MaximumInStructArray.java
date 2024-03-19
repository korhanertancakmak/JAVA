package GeeksForGeeksChallenges.Basic;
/*
        Given a struct array of type Height, having two elements feet and inches. Find the maximum height among n heights,
    where height is calculated sum of feet and inches after converting feet into inches.

    NOTE: 1 feet = 12 inches.

    Example 1:
    Input:
    n = 2
    h1 -> 1 2
    h2 -> 2 1
    Output:
    25
    Explanation: (1, 2) and (2, 1) are respective given heights. After converting both heights into inches, we get 14 and
    25 as respective heights. So, 25 is the maximum height.

    Your Task: Your task is to only complete the function of find maximum height from a given array.
*/

import java.util.Arrays;
import java.util.Scanner;

class Height {
    int feet;
    int inches;

    public Height(int ft, int inc) {
        feet = ft;
        inches = inc;
    }
}
public class MaximumInStructArray {

    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter size of heights");
        int n = sc.nextInt();
        Height[] arr = new Height[n];
        System.out.println("Enter the heights as feet and inch");
        for (int i = 0; i < n; i++) {
            int temp1 = sc.nextInt();
            int temp2 = sc.nextInt();
            arr[i] = new Height(temp1, temp2);
        }

        int res = FindMaximumInStructArray.findMax(arr, n);
        System.out.println(res);
    }
}

class FindMaximumInStructArray {
    public static int findMax(Height[] arr, int n) {
        // your code here
        int[] totalHeights = new int[n];
        int i = 0;
        for (var el : arr) {
            totalHeights[i] = el.feet * 12 + el.inches;
            i++;
        }
        Arrays.sort(totalHeights);
        return totalHeights[arr.length - 1];
    }
}

//Problem successfully Solved
//Test Cases Passed:
//84 /84
//Total Points Scored:
//1 /1
//Your Total Score:
//118
//Total Time Taken:
//0.71
//Your Accuracy:
//100%
//Attempts No.:
//1