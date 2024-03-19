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
import java.util.Stack;

public class NearestSmallerTowerV2 {

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

            FindNearestSmallerTowerV2 ob = new FindNearestSmallerTowerV2();
            int [] ans = ob.nearestSmallestTower(arr);
            for(int i = 0; i < n; i++)
                System.out.print(ans[i]+" ");
            System.out.println();
        }

    }
}

class FindNearestSmallerTowerV2{

    int[] nearestSmallestTower(int[] arr){

        Stack<Integer> stack = new Stack<>();
        int[] leftNearest = new int[arr.length];
        int[] rightNearest = new int[arr.length];

        //Finding left nearest
        for(int i=0;i<arr.length;i++){
            if(stack.empty()){
                leftNearest[i] = -1;
            }
            else {
                if(arr[stack.peek()]<arr[i]){
                    leftNearest[i] = stack.peek();
                }
                else {
                    while(!stack.empty() && arr[stack.peek()]>=arr[i]){
                        stack.pop();
                    }

                    if(stack.empty()){
                        leftNearest[i] = -1;
                    }
                    else {
                        leftNearest[i] = stack.peek();
                    }
                }

            }
            stack.push(i);
        }
        stack.clear();
        //Finding right nearest
        for(int i=arr.length-1;i>=0;i--){
            if(stack.empty()){
                rightNearest[i] = -1;
            }
            else {
                if(arr[stack.peek()]<arr[i]){
                    rightNearest[i] = stack.peek();
                }
                else {
                    while(!stack.empty() && arr[stack.peek()]>=arr[i]){
                        stack.pop();
                    }

                    if(stack.empty()){
                        rightNearest[i] = -1;
                    }
                    else {
                        rightNearest[i] = stack.peek();
                    }
                }

            }
            stack.push(i);
        }

        int[] ans = new int[arr.length];
        for(int i=0;i<arr.length;i++){
            if(rightNearest[i]==-1 && leftNearest[i]==-1){
                ans[i] = -1;
            }
            else if(rightNearest[i]==-1 || leftNearest[i]==-1){
                ans[i] = (rightNearest[i]==-1) ? leftNearest[i] : rightNearest[i];
            }
            else {
                //Differences are the same
                int absR = Math.abs(i - rightNearest[i]);
                int absL = Math.abs(i - leftNearest[i]);
                if(absR == absL){
                    //Go for small value
                    if(arr[rightNearest[i]] < arr[leftNearest[i]]){
                        ans[i] = rightNearest[i];
                    }
                    else if(arr[rightNearest[i]] > arr[leftNearest[i]]){
                        ans[i] = leftNearest[i];
                    }
                    else {
                        //Both values are also same
                        ans[i] = Math.min(leftNearest[i], rightNearest[i]);
                    }
                }
                else if(absR < absL){
                    ans[i] = rightNearest[i];
                }
                else {
                    ans[i] = leftNearest[i];
                }
            }
        }
        return ans;
    }
}