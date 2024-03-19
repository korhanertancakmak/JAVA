package GeeksForGeeksChallenges.Easy;

/*
        Given an array a of size N which contains elements from 0 to N-1, you need to find all the elements occurring more
    than once in the given array. Return the answer in ascending order. If no such element is found, return list containing
    [-1].

    Note : The extra space is only for the array to be returned. Try and perform all operations within the provided array.

    Example 1:
    Input:
    N = 4
    a[] = {0,3,1,2}
    Output:
    -1
    Explanation:
    There is no repeating element in the array. Therefore output is -1.

    Example 2:
    Input:
    N = 5
    a[] = {2,3,1,2,3}
    Output:
    2 3
    Explanation:
    2 and 3 occur more than once in the given array.

    Your Task:
    Complete the function duplicates() which takes array a[] and n as input as parameters and returns a list of elements
    that occur more than once in the given array in a sorted manner.

    Expected Time Complexity: O(n).
    Expected Auxiliary Space: O(n).

    Constraints:
    1 <= N <= 105
    0 <= A[i] <= N-1, for each valid i
*/

import java.util.*;

public class FindDuplicateInArray {

    public static void main(String[] args) {

        int[] a = {0, 3, 1, 2};
        int N = a.length;

        ArrayList<Integer> output = FindDuplicate.duplicates(a, N);
        System.out.println("Input:");
        System.out.println(Arrays.toString(a));
        System.out.println("Output:");
        System.out.println(output);
    }
}

class FindDuplicate {
    public static ArrayList<Integer> duplicates(int[] arr, int n) {

        if (!noDuplicates(arr)) {
            ArrayList<Integer> myList = new ArrayList<>();
            myList.add(-1);
            return myList;
        }

        ArrayList<Integer> myList = new ArrayList<>();
        int[] sortedArray = sortedArray(arr);
        int count = 1;

        for (int i = 1; i < sortedArray.length; i++) {

            if (sortedArray[i - 1] == sortedArray[i]) {
                count++;
            }

            if (sortedArray[i - 1] == sortedArray[i] && i != sortedArray.length - 1) {
                continue;
            } else if (count > 1){
                myList.add(sortedArray[i - 1]);
                count = 1;
            }
        }

        return  myList;
    }

    public static int[] sortedArray(int[] a) {
        int[] b = new int[a.length];
        b = Arrays.copyOf(a, a.length);
        Arrays.sort(b);
        return b;
    }

    public static boolean noDuplicates(int[] a) {

        int[] b = sortedArray(a);

        for (int i = 0; i < b.length - 1; i++) {
            if (b[i] == b[i + 1]) {
                return true;
            }
        }
        return false;
    }
}

//Problem Solved Successfully
//You get marks only for the first correct submission if you solve the problem without viewing the full solution.
//Test Cases Passed:
//1220 /1220
//Your Total Score:
//114
//Total Time Taken:
//3.6
//Correct Submission Count: 2
//Attempts No.: 3