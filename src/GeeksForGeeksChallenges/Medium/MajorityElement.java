package GeeksForGeeksChallenges.Medium;

/*
    Given an array A of N elements. Find the majority element in the array. A majority element in an array A of size N is
    an element that appears more than N/2 times in the array.

    Example 1:
    Input:
    N = 3
    A[] = {1,2,3}
    Output:
    -1
    Explanation: Since, each element in {1,2,3} appears only once so there is no majority element.

    Example 2:
    Input:
    N = 5
    A[] = {3,1,3,3,2}
    Output:
    3
    Explanation: Since, 3 is present more than N/2 times, so it is the majority element.

    Your Task:
    The task is to complete the function majorityElement() which returns the majority element in the array. If no majority
    exists, return -1.

    Expected Time Complexity: O(N).
    Expected Auxiliary Space: O(1).

    Constraints:
    1 ≤ N ≤ 107
    0 ≤ Ai ≤ 106
*/
public class MajorityElement {

    public static void main(String[] args) {

        int[] arr = {1,2,3};
        int n = arr.length;
        System.out.println(FindMajority.majorityElement(arr, n));
    }
}

class FindMajority
{
    static int majorityElement(int[] a, int size) {
        int max = a[0];
        for (int el : a) {
            max = Math.max(max, el);
        }
        int[] freq = new int[max];
        freq = frequencyCount(a, max);

        for (int i = 0; i < freq.length; i++) {
            if (freq[i] > size/2) {
                return i + 1;
            }
        }
        return -1;
    }

    public static int[] frequencyCount(int arr[], int max) {

        int[] freq = new int[max];
        int i = 0;
        for (int el :arr) {
            i = el;
            freq[i - 1]++;
        }

        return freq;
    }
}