package GeeksForGeeksChallenges.Easy;

import java.util.Arrays;

/*
        Given an array A[] of N positive integers which can contain integers from 1 to P where elements can be repeated
    or can be absent from the array. Your task is to count the frequency of all elements from 1 to N.

    Note: The elements greater than N in the array can be ignored for counting and do modify the array in-place.

    Example 1:
    Input:
    N = 5
    arr[] = {2, 3, 2, 3, 5}
    P = 5
    Output:
    0 2 2 0 1
    Explanation:
    Counting frequencies of each array element
    We have:
    1 occurring 0 times.
    2 occurring 2 times.
    3 occurring 2 times.
    4 occurring 0 times.
    5 occurring 1 time.

    Example 2:
    Input:
    N = 4
    arr[] = {3,3,3,3}
    P = 3
    Output:
    0 0 4 0
    Explanation:
    Counting frequencies of each array element
    We have:
    1 occurring 0 times.
    2 occurring 0 times.
    3 occurring 4 times.
    4 occurring 0 times.

    Your Task:
    You don't need to read input or print anything. Complete the function frequencycount() that takes the array and n as
    input parameters and modify the array itself in place to denote the frequency count of each element from 1 to N. i,e
    element at index 0 should represent the frequency of 1, and so on.

    Can you solve this problem without using extra space (O(1) Space)?

    Constraints:
    1 ≤ N ≤ 105
    1 ≤ P ≤ 4*104
    1 <= A[i] <= P

*/
public class FrequenciesOfLimitedRange {

    public static void main(String[] args) {

        int N = 4;
        int[] arr = {3,3,3,3};

        FindFrequencies.frequencyCount(arr, N);
    }
}

class FindFrequencies{
    //Function to count the frequency of all elements from 1 to N in the array.
    public static void frequencyCount(int arr[], int N) {

        for(int i = 0; i < N; i++){
            if(arr[i] > N){
                arr[i] = 0;
            }
        }

        int i = 0;
        while(i < N){
            if(arr[i] <= 0){
                i++;
                continue;
            }
            else{
                int j = arr[i] - 1;
                if(arr[j] <= 0){
                    arr[j]--;
                    arr[i] = 0;
                    i++;
                }
                else{
                    arr[i] = arr[j];
                    arr[j] = -1;
                }
            }
        }
        for(int k = 0; k < N; k++){
            arr[k] = -arr[k];
        }

        System.out.println("Output: " + Arrays.toString(arr));
    }
}