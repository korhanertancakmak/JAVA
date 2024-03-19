package GeeksForGeeksChallenges.Medium;
/*
        Given a sorted array arr containing n elements with possibly duplicate is to find indexes of first elements, the
    task is to find the first and last occurrences of an element x in the given array.

    Note: If the number x is not found in the array then return both the indices as -1.

    Example 1:
    Input:
    n=9, x=5
    arr[] = { 1, 3, 5, 5, 5, 5, 67, 123, 125 }
    Output:
    2 5
    Explanation: First occurrence of 5 is at index 2 and last occurrence of 5 is at index 5.

    Example 2:
    Input:
    n=9, x=7
    arr[] = { 1, 3, 5, 5, 5, 5, 7, 123, 125 }
    Output:
    6 6
    Explanation: First and last occurrence of 7 is at index 6.

    Your Task: Since, this is a function problem. You don't need to take any input, as it is already accomplished by the
    driver code. You just need to complete the function find() that takes array arr, integer n and integer x as parameters
    and returns the required answer.

    Expected Time Complexity: O(logN)
    Expected Auxiliary Space: O(1).

    Constraints:
    1 ≤ N ≤ 106
    1 ≤ arr[i],x ≤ 109
*/

import java.io.*;
import java.util.*;
public class FirstAndLastOccurrencesOfX {

    public static void main(String[] args) throws IOException {

        Scanner kb = new Scanner(System.in);
        System.out.println("Enter size of the array");
        int n = kb.nextInt();
        System.out.println("Enter the element you're looking for");
        int x = kb.nextInt();

        int[] arr = new int[n];
        System.out.println("Enter elements of the array");
        for (int i = 0; i < n; i++) {
            arr[i] = kb.nextInt();
        }
        FindFirstAndLastOccurrencesOfX ob = new FindFirstAndLastOccurrencesOfX();
        ArrayList<Integer> ans = ob.find(arr, n, x);
        System.out.println(ans.get(0) + " " + ans.get(1));
    }
}

class FindFirstAndLastOccurrencesOfX {
    ArrayList<Integer> find(int[] arr, int n, int x) {
        // code here

        List<Integer> arrList = new ArrayList<>();
        for (int el : arr) {
            arrList.add(el);
        }
        ArrayList<Integer> result = new ArrayList<>();
        result.add(arrList.indexOf(x));
        result.add(arrList.lastIndexOf(x));
        return result;
    }
}


//Problem successfully Solved
//Test Cases Passed:
//1120 /1120
//Total Points Scored:
//4 /4
//Your Total Score:
//108
//Total Time Taken:
//1.58
//Your Accuracy:
//100%
//Attempts No.:
//1