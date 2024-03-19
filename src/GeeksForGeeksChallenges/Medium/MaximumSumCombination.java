package GeeksForGeeksChallenges.Medium;

import java.io.IOException;
import java.util.*;

/*
        Given two integer array A and B of size N each. A sum combination is made by adding one element from array A and
    another element of array B. Return the maximum K valid sum combinations from all the possible sum combinations.

    Note: An output array must be sorted in non-increasing order.

    Example 1:
    Input:
    N = 2
    K = 2
    A [ ] = {3, 2}
    B [ ] = {1, 4}
    Output: {7, 6}
    Explanation: 7 -> (A : 3) + (B : 4)
                 6 -> (A : 2) + (B : 4)

    Example 2:
    Input:
    N = 4
    K = 3
    A [ ] = {1, 4, 2, 3}
    B [ ] = {2, 5, 1, 6}
    Output: {10, 9, 9}
    Explanation: 10 -> (A : 4) + (B : 6)
                  9 -> (A : 4) + (B : 5)
                  9 -> (A : 3) + (B : 6)

    Your Task: You don't need to read input or print anything. Your task is to complete the function maxCombinations()
    which takes the interger N, integer K and two integer arrays A [ ] and B [ ] as parameters and returns the maximum K
    valid distinct sum combinations.

    Expected Time Complexity: O(Nlog(N))
    Expected Auxiliary Space: O(N)

    Constraints:
    1 ≤ N ≤ 105
    1 ≤ K ≤ N
    1 ≤ A [ i ] , B [ i ] ≤ 104
*/
public class MaximumSumCombination {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter size of arrays");
        int n = sc.nextInt();
        System.out.println("Enter size of output array");
        int k = sc.nextInt();

        System.out.println("Enter elements of array A");
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        System.out.println("Enter elements of array B");
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            b[i] = sc.nextInt();
        }

        List<Integer> ans = FindMaximumSumCombination.maxCombinations(n, k, a, b);
        for (int e : ans) System.out.print(e + " ");
        System.out.println();
    }
}

class FindMaximumSumCombination {
    static List<Integer> maxCombinations(int N, int K, int[] A, int[] B) {
        // code here
        List<Integer> result = new ArrayList<>();

        Arrays.sort(A);
        Arrays.sort(B);

        int sum = 0, remember = 0;
        for (int i = N - 1; i >= 0 && result.size() < K; i--) {
            for (int j = N - 1; j >= 0 && result.size() < K; j--) {
                sum = A[i] + B[j];
                if (i != 0 && sum < A[i - 1] + B[N - 1]) {
                    if (remember > sum && remember > A[i - 1] + B[N - 1]) {
                        result.add(remember);
                        result.add(A[i - 1] + B[N - 1]);
                    } else if (remember > sum && remember < A[i - 1] + B[N - 1]) {
                        result.add(A[i - 1] + B[N - 1]);
                        result.add(remember);
                    }
                    remember = sum;
                    break;
                }
                if (sum > remember) {
                    result.add(sum);
                } else {
                    result.add(remember);
                    remember = sum;
                }
            }
        }

        return result;
    }
}