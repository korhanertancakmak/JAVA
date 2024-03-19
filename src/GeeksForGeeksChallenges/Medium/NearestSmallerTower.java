package GeeksForGeeksChallenges.Medium;
/*
        Given an array where each element (arr[i]) represents the height of the tower. Find for each tower, the nearest
    possible tower that is shorter than it. You can look left or right on both sides.

    ==> If two smaller towers are at the same distance, pick the smallest tower.
    ==> If two towers have the same height, then we choose the one with a smaller index.

    Example 1:
    Input:
    arr[] = {1,3,2}
    Output:
    {-1,0,0}
    Explanation:
    For 0th Index: no tower is smallest, so -1.
    For 1st Index: For 3, here 1 & 2 both are small & at a same distance, so we will pick 1, beacuse it has the smallest
    value, so 0(Index)
    For 2nd Index: here 1 is smaller, so 0(Index) So the final output will be which consists Indexes are {-1,0,0}.

    Example 2:
    Input:
    arr[] = {4,8,3,5,3}
    Output:
    {2,2,-1,2,-1}

    Explanation:
    For 0th Index : here 3 is the smaller, so 2(Index)
    For 1st Index : For 8, here 4 & 3 both are small & at a same distance, so we will pick 3, so 2(Index)
    For 2nd Index : no tower is smallest, so -1.
    For 3rd Index : For 5, here 3 & 3 both are small & at a same distance, so we will pick 3(2nd Index) because it is
    smaller Index, so 2(Index)
    For 4th Index : no tower is smallest, so -1. So the final output will be which consists Indexes are {2,2,-1,2,-1}.

    Your Task: You don't need to read input or print anything. Your task is to complete the function nearestSmallerTower()
    which takes an array of heights of the towers as input parameter and returns an array of indexes of the nearest
    smaller tower. If there is no smaller tower on both sides then return -1 for that tower.

    Expected Time Complexity: O(N)
    Expected Auxiliary Space: O(N)

    Constraints:
    1 <= N <= 105
    1 <= arr[i] <= 105
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NearestSmallerTower {

    public static void main(String [] args) throws IOException {

        System.out.println("Enter '1' for default");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.parseInt(br.readLine());

        while(test-- > 0) {

            System.out.println("Enter the size of the array");
            int n = Integer.parseInt(br.readLine());
            int [] arr = new int[n];

            System.out.println("Enter the array");
            String [] str = br.readLine().trim().split(" ");

            for(int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(str[i]);
            }

            FindNearestSmallerTower ob = new FindNearestSmallerTower();
            int [] ans = ob.nearestSmallestTower(arr);
            for(int i = 0; i < n; i++)
                System.out.print(ans[i]+" ");
            System.out.println();
        }

    }
}

class FindNearestSmallerTower{

    int[] nearestSmallestTower(int[] arr){

        int n = arr.length;
        int[][] resarr = new int[n][3];
        for (int m = 0; m < n; m++) {
            resarr[m][0] = m;
            resarr[m][1] = arr[m];
            resarr[m][2] = Integer.MAX_VALUE;
        }


        for (int i = 0; i < n; i++) {
            int right = i + 1, distright = 1;
            while (right < n) {
                if (arr[i] > arr[right]) {
                    resarr[i][0] = right;
                    resarr[i][1] = arr[right];
                    resarr[i][2] = distright;
                    break;
                }
                distright++;
                right = i + distright;
            }
            int left = i - 1, distleft = 1;
            while (left >= 0) {
                if (resarr[i][2] != Integer.MAX_VALUE && arr[i] > arr[left]) {
                    if (distleft < distright) {
                        resarr[i][0] = left;
                        resarr[i][1] = arr[left];
                        resarr[i][2] = distleft;
                        break;
                    } else if (distleft == distright && arr[left] < resarr[i][1]) {
                        resarr[i][0] = left;
                        resarr[i][1] = arr[left];
                        resarr[i][2] = distleft;
                        break;
                    } else if (distleft == distright && arr[left] == resarr[i][1] && left < resarr[i][0]) {
                        resarr[i][0] = left;
                        resarr[i][1] = arr[left];
                        resarr[i][2] = distleft;
                        break;
                    }
                } else {
                    if (arr[i] > arr[left]) {
                        resarr[i][0] = left;
                        resarr[i][1] = arr[left];
                        resarr[i][2] = distleft;
                        break;
                    }
                }
                distleft++;
                left = i - distleft;
            }
            if (resarr[i][2] == Integer.MAX_VALUE) {
                resarr[i][0] = -1;
            }
        }


        int[] result = new int[n];

        for (int k = 0; k < n; k++) {
            result[k] = resarr[k][0];
        }

        return result;
    }
}