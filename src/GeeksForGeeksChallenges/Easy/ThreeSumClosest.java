package GeeksForGeeksChallenges.Easy;

import java.util.Arrays;

/*
        Given an array, Arr of N numbers, and another number target, find three integers in the array such that the sum
    is closest to the target. Return the sum of the three integers.

    Note: If there are multiple solutions, print the maximum one.

    Example 1:
    Input:
    N = 6, target = 2
    A[] = {-7,9,8,3,1,1}
    Output: 2
    Explanation: There is one triplet with sum 2 in the array. Triplet elements are -7,8,1 whose sum is 2.

    Example 2:
    Input:
    N = 4, target = 13
    A[] = {5,2,7,5}
    Output: 14
    Explanation: There is one triplet with sum 12 and other with sum 14 in the array. Triplet elements are 5, 2, 5 and
    2, 7, 5 respectively. Since abs(13-12) == abs(13-14) maximum triplet sum will be preferred i.e 14.

    Your Task:
    Complete threeSumClosest() function and return the expected answer.

    Expected Time Complexity: O(N*N).
    Expected Auxiliary Space: O(1).

    Constraints:
    3 ≤ N ≤ 103
    -105 ≤ A[i] ≤ 105
    1 ≤ target ≤ 105
*/
public class ThreeSumClosest {
    public static void main(String[] args) {

        int N = 6, target = 2;
        int[] A = {-7, 9, 8, 3, 1, 1};

        System.out.println("Input: ");
        System.out.println("N = " + N + " " + "target = " + target);
        System.out.println("Output: ");
        System.out.println(threeSumClosest(A, target));
    }

    static int threeSumClosest(int[] array, int target) {
        // code here

        int flag = 0;
        int n = array.length;
        int result = Integer.MAX_VALUE;
        Arrays.sort(array);

        for (int i = 0; i < n - 2; ++i) {
            int j = i + 1, k = n - 1;
            while (j < k) {
                int sum = array[i] + array[j] + array[k];

                if (flag != 1) {
                    result = sum;
                    flag = 1;
                }


                if (Math.abs(sum - target) < Math.abs(result - target))
                    result = sum;

                else if (Math.abs(sum - target) == Math.abs(result - target)) {
                    if (sum > result)
                        result = sum;
                }

                if (target > sum)
                    j++;
                else
                    k--;
            }
        }
        return result;
    }
}