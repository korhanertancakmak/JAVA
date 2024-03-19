package GeeksForGeeksChallenges.Basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/*
        Given an array A of integers. Find three numbers such that sum of two elements equals the third element and return
    the triplet in a container result, if no such triplet is found return the container as empty.

    Input: First line of input contains number of testcases. For each testcases there will two lines. First line contains
    size of array and next line contains array elements.

    Output: For each test case output the triplets, if any triplet found from the array, if no such triplet is found,
    output -1.

    Your Task: Your task is to complete the function to find triplet and return container containing result.

    Example:
    Input:
    3
    5
    1 2 3 4 5
    3
    3 3 3
    6
    8 10 16 6 15 25

    Output:
    1
    -1
    1

    Explanation: Testcase 1: Triplet Formed: {2, 1, 3}    Hence  1
                 Testcase 2: Triplet Formed: {}           Hence -1
                 Testcase 3: Triplet Formed: {10, 15, 25} Hence  1

    Constraints:
    1 <= N <= 103
    0 <= Ai <= 105
*/
public class TripletFamily {

    public static void main (String[] args) {
        Scanner sc= new Scanner(System.in);

        System.out.println("Enter size of array");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.println("Enter elements of array");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        ArrayList<Integer> res = new ArrayList<Integer>();
        res = FindTripletFamily.findTriplet(arr, n);
        if (res.size() != 3) {
            System.out.println("-1");
        } else {
            Collections.sort(res);
            if (res.get(0) + res.get(1) == res.get(2)) {
                System.out.println("1");
            } else {
                System.out.println("0");
            }
        }
    }
}
class FindTripletFamily {
    public static ArrayList<Integer> findTriplet(int[] arr, int n) {
        // your code here
        ArrayList<Integer> elements = new ArrayList<>();
        for (int el : arr) {
            elements.add(el);
        }
        Collections.sort(elements);
        ArrayList<Pair> family = new ArrayList<>();
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                Pair p = new Pair(arr[i], arr[j]);
                family.add(p);
                if (elements.contains(p.sum)) {
                    result.add(p.firstElement);
                    result.add(p.secondElement);
                    result.add(p.sum);
                    return result;
                }
            }
        }
        return result;
    }
}

class Pair {
    int firstElement;
    int secondElement;
    int sum;

    public Pair(int firstElement, int secondElement) {
        this.firstElement = firstElement;
        this.secondElement = secondElement;
        this.sum = firstElement + secondElement;
    }
}

//Problem successfully Solved
//Test Cases Passed:
//100 /100
//Total Points Scored:
//1 /1
//Your Total Score:
//119
//Total Time Taken:
//0.65
//Your Accuracy:
//100%
//Attempts No.:
//1