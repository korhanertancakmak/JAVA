package GeeksForGeeksChallenges.Medium;

import java.util.ArrayList;
import java.util.Arrays;

/*
    Given an array A of N integers, find any 3 elements in it such that A[i] < A[j] < A[k] and i < j < k.

    Example 1:
    Input:
    N = 5
    A[] = {1,2,1,1,3}
    Output: 1
    Explanation: a sub-sequence 1 2 3 exist.

    Example 2:
    Input:
    N = 3
    A[] = {1,1,3}
    Output: 0
    Explanation: no such sub-sequence exist.

    Your Task:
    Your task is to complete the function find3Numbers which finds any 3 elements in it such that A[i] < A[j] < A[k] and
    i < j < k. You need to return them as a vector/ArrayList/array (depending on the language cpp/Java/Python), if no such
    element is present then return the empty vector of size 0.

    Note: The output will be 1 if the sub-sequence returned by the function is present in array A or else 0. If the sub-sequence
    returned by the function is not in the format as mentioned then the output will be -1.

    Expected Time Complexity: O(N)
    Expected Auxiliary Space: O(N)

    Constraints:
    1 <= N <= 105
    1 <= A[i] <= 106, for each valid i
*/

public class SortedSubsequenceOfSize3 {

    public static void main(String[] args) {

        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(75);
        arr.add(34);
        arr.add(54);
        arr.add(58);
        arr.add(46);
        arr.add(60);
        arr.add(86);
        arr.add(82);

        int n = 8;
        int output = (MySolution.find3Numbers(arr, n).size() > 0) ? 1 : 0;

        System.out.println("The sequence= " + arr);
        System.out.println("Output= " + output);
        System.out.println("Sub-sequence= " + MySolution.find3Numbers(arr, n));
    }
}

class MySolution {
    public static ArrayList<Integer> find3Numbers(ArrayList<Integer> arr, int n) {
        int[] leftMin = new int[n];
        int[] rightMax = new int[n];

        int minSoFar = Integer.MAX_VALUE;
        int maxSoFar = Integer.MIN_VALUE;

        for(int i=0; i< n; i++) {
            if(arr.get(i) < minSoFar) {
                minSoFar = arr.get(i);
            }
            leftMin[i] = minSoFar;

            if(arr.get(n-i-1) > maxSoFar) {
                maxSoFar = arr.get(n-i-1);
            }
            rightMax[n-i-1] = maxSoFar;
        }


        for(int i=1; i< n; i++) {
            if(arr.get(i) > leftMin[i] && arr.get(i) < rightMax[i]) {
                return new ArrayList<>(Arrays.asList(leftMin[i] , arr.get(i), rightMax[i]));
            }
        }

        return new ArrayList<>();
    }
}