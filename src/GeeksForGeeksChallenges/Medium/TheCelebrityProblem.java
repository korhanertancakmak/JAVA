package GeeksForGeeksChallenges.Medium;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*
        A celebrity is a person who is known to all but does not know anyone at a party. If you go to a party of N people,
    find if there is a celebrity in the party or not. A square NxN matrix M[][] is used to represent people at the party
    such that if an element of row i and column j is set to "1" it means ith person knows jth person. Here M[i][i] will
    always be 0.

    Note: Follow 0-based indexing.
    Follow Up: Can you optimize it to O(N)

    Example 1:
    Input:
    N = 3
    M[][] = {{0 1 0},
             {0 0 0},
             {0 1 0}}
    Output: 1
    Explanation: 0th and 2nd persons both know 1. Therefore, 1 is the celebrity.

    Example 2:
    Input:
    N = 2
    M[][] = {{0 1},
             {1 0}}
    Output: -1
    Explanation: The two people at the party both know each other. None of them is a celebrity.

    Your Task: You don't need to read input or print anything. Complete the function celebrity() which takes the matrix
    M and its size N as input parameters and returns the index of the celebrity. If no such celebrity is present, return
    -1.

    Expected Time Complexity: O(N^2)
    Expected Auxiliary Space: O(1)

    Constraints:
    2 <= N <= 3000
    0 <= M[][] <= 1
*/
public class TheCelebrityProblem {


    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter '1' for default");
        int t = sc.nextInt();

        while(t>0) {
            System.out.println("Enter the size of the NxN matrix");
            int N = sc.nextInt();
            int M[][] = new int[N][N];
            System.out.println("Enter the matrix all rows elements in 1 row");
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    M[i][j] = sc.nextInt();
                }
            }
            System.out.println(new FindTheCelebrity().celebrity(M,N));
            t--;
        }
    }
}

class FindTheCelebrity
{
    //Function to find if there is a celebrity in the party or not.
    int celebrity(int[][] M, int n) {

        ArrayList<Integer> MList = new ArrayList<>();
        ArrayList<Integer> MListCheck = new ArrayList<>();
        int j = 0, res = -1;
        while (j < n) {
            for (int i = 0; i < n; i++) {
                MList.add(i, M[i][j]);
            }
            int firstIdx = MList.indexOf(0);
            int lastIdx = MList.lastIndexOf(0);
            if (firstIdx == lastIdx) {
                for (int k = 0; k < n; k++) {
                    MListCheck.add(k, M[firstIdx][k]);
                }
                int firstIdxCheck = MListCheck.indexOf(1);
                int lastIdxCheck = MListCheck.lastIndexOf(1);
                if (firstIdxCheck == -1 && lastIdxCheck == -1) {
                    res = firstIdx;
                    break;
                }
                MListCheck.clear();
            }
            j++;
            MList.clear();
        }
        return res;
    }
}

//Problem successfully Solved
//Test Cases Passed:
//66 /69
//Total Points Scored:
//4 /4
//Your Total Score:
//55
//Total Time Taken:
//3.55
//Your Accuracy:
//16%
//Attempts No.:
//6