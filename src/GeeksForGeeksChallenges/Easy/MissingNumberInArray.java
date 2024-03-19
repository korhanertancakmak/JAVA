package GeeksForGeeksChallenges.Easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
        Given an array of size N-1 such that it only contains distinct integers in the range of 1 to N. Find the missing
    element.

    Example 1:
    Input:
    N = 5
    A[] = {1,2,3,5}
    Output: 4

    Example 2:
    Input:
    N = 10
    A[] = {6,1,2,8,3,4,7,10,5}
    Output: 9

    Your Task: You don't need to read input or print anything. Complete the function MissingNumber() that takes array
    and N as input parameters and returns the value of the missing number.

    Expected Time Complexity: O(N)
    Expected Auxiliary Space: O(1)

    Constraints:
    1 ≤ N ≤ 106
    1 ≤ A[i] ≤ 106
*/
public class MissingNumberInArray {

    public static void main(String[] args) throws IOException {

        System.out.println("Enter '1' for default!");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());

        while (t-- > 0) {
            System.out.println("Enter number of the elements in the array");
            int n = Integer.parseInt(br.readLine().trim());
            System.out.println("Enter the elements");
            String[] str = br.readLine().trim().split(" ");
            int[] array = new int[n - 1];
            for (int i = 0; i < n - 1; i++) {
                array[i] = Integer.parseInt(str[i]);
            }
            FindMissingNumberInArray sln = new FindMissingNumberInArray();
            System.out.println(sln.missingNumber(array, n));
        }
    }
}

class FindMissingNumberInArray {
    int missingNumber(int[] array, int n) {

        List<Boolean> index = new ArrayList<>(Arrays.asList(new Boolean[n]));
        Collections.fill(index, false);
        for (int el : array) {
            index.set(el - 1, true);
        }

        return index.indexOf(false) + 1;
    }
}