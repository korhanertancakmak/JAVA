package GeeksForGeeksChallenges.Easy;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

/*
        Given a non-empty array of integers, find the top k elements which have the highest frequency in the array. If
    two numbers have the same frequency, then the larger number should be given preference.

    Note: Print the elements according to the frequency count (from highest to lowest) and if the frequency is equal, then
    larger number will be given preference.

    Example 1:
    Input:
    N = 6
    nums = {1,1,1,2,2,3}
    k = 2
    Output: {1, 2}

    Example 2:
    Input:
    N = 8
    nums = {1,1,2,2,3,3,3,4}
    k = 2
    Output: {3, 2}
    Explanation: Elements 1 and 2 have the same frequency ie. 2. Therefore, in this case, the answer includes element 2
    as 2 > 1.

    User Task:
    The task is to complete the function topK() that takes the array and integer k as input and returns a list of top k
    frequent elements.

    Expected Time Complexity: O(NlogN)
    Expected Auxiliary Space : O(N)

    Constraints:
    1 <= N <= 105
    1<=A[i]<=105
*/
public class TopKFrequentElements {

    public static void main(String[] args) throws IOException {

        Path fileName = Path.of("D:/JAVA_STUDY/JAVA_CLASSES/UDEMY_CLASSES/JAVA Programming MasterClass Tim Buchalka/PROGRAMS/Courses/src/Geeksforgeeks_Challenges/Input.txt");

        // Now calling Files.readString() method to
        // read the file
        String str = Files.readString(fileName);

        String[] string = str.split(" ");

        int[] nums = new int[string.length];

        for (int i = 0; i < string.length; i++) {
            nums[i] = Integer.parseInt(string[i]);
        }

        int N = nums.length - 2;
        int[] numbers = new int[N];
        if (N - 1 - 1 >= 0) System.arraycopy(nums, 1, numbers, 1, N - 1 - 1);
        int k = nums[nums.length - 1];


        System.out.println("Input:");
        System.out.println("N = " + N);
        System.out.println("nums = " + Arrays.toString(numbers));
        System.out.println("k = " + k);
        System.out.println("Output: " + Arrays.toString(FindTopKFrequentElements.topK(numbers, k)));
    }
}

class FindTopKFrequentElements {
    public static int[] topK(int[] nums, int k) {
        // Code here

        // Find the max element in the arr
        int maxEl = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > maxEl) {
                maxEl = nums[i];
            }
        }
        int[][] myarr = new int[maxEl + 1][2];

        int index = 0;
        while(index < nums.length) {
            // el is the element of nums array at that index
            int el = nums[index];
            // increase the freq of element index by 1.
            myarr[el][0]++;
            // Put the corresponding element in myarr, only if it's empty.
            if (myarr[el][1] == 0) {
                // set it to the position in the arr array.
                myarr[el][1] = el;
                // and increase the index for the next loop
                index++;
            } else {
                // otherwise, it means we already processed this index
                index++;
                continue;
            }
        }

        Arrays.sort(myarr, (a, b) -> Integer.compare(b[0], a[0]));

        int[] result = new int[k];
        int count2 = 0;
        for (int j = 0; j < k; j++) {
            if (myarr[count2][1] == 0) {
                j--;
                count2++;
            } else {
                if (myarr[count2][0] == myarr[count2 + 1][0]) {
                    do {
                        if (myarr[count2][1] > myarr[count2 + 1][1]) {
                            result[j] = myarr[count2][1];
                            count2++;
                        } else {
                            result[j] = myarr[count2 + 1][1];
                            if (j + 1 < k) {
                                result[j + 1] = myarr[count2][1];
                                j++;
                            }
                            count2++;
                        }
                    } while (myarr[count2][0] == myarr[count2 + 1][0]);
                } else {
                    result[j] = myarr[count2][1];
                    count2++;
                }
            }
        }

        return result;
    }
}