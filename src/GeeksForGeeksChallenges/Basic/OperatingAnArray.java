package GeeksForGeeksChallenges.Basic;

import java.util.Arrays;
import java.util.Scanner;

/*
        Given an array A of size N, your task is to do some operations, i.e., search an element x, insert an element y at
    index yi, and delete an element z by completing the functions. Also, all functions should return a boolean value.

    Note: In delete operation return true even if element is not present. N is never greater than 10000.

    Input Format:
    N
    A1 A2 . . . An
    x y yi z

    Example:
    Input:
    5
    2 4 1 0 6
    1 2 2 0

    Output:
    1 1 1

    Your Task: Since this is a function problem, you only need to complete the provided functions.

    Constraints:
    1 <= N <= 100
    0 <= Ai <= 1000
*/
public class OperatingAnArray {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter size of array");
        int n = sc.nextInt();
        int[] a = new int[10000];
        Arrays.fill(a,-1);
        System.out.println("Enter elements of array");
        for(int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        System.out.println("Enter search element x");
        int x = sc.nextInt();
        System.out.println("Enter insert element y");
        int y = sc.nextInt();
        System.out.println("Enter index of insert element yi");
        int yi = sc.nextInt();
        System.out.println("Enter the element to be deleted");
        int z = sc.nextInt();

        Operation g = new Operation();
        boolean b = g.searchEle(a, x);
        if (b) {
            System.out.print("1 ");
        }
        else {
            System.out.print("0 ");
        }

        b = g.insertEle(a, y, yi);
        if (b) {
            if (a[yi] == y) {
                System.out.print("1 ");
            } else {
                System.out.print("0 ");
            }
        } else {
            System.out.print("0 ");
        }

        b = g.deleteEle(a, z);
        if (b) {
            if (!g.searchEle(a, z)) {
                System.out.println("1 ");
            } else {
                System.out.println("0 ");
            }
        } else {
            System.out.println("0 ");
        }
    }
}

class Operation {
    public boolean searchEle(int[] a, int x) {
        //add code here.
        for (int el : a) {
            if (el == x) {
                return true;
            }
        }
        return false;
    }
    public boolean insertEle(int[] a, int y, int yi) {
        //add code here.
        a[yi] = y;
        return true;
    }
    public boolean deleteEle(int[] a, int z) {
        //add code here.
        for (int i = 0; i < a.length; i++) {
            if (a[i] == z) {
                a[i] = -1;
            }
        }
        return true;
    }
}

//Problem successfully Solved
//Test Cases Passed:
//100 /100
//Total Points Scored:
//1 /1
//Your Total Score:
//116
//Total Time Taken:
//0.55
//Your Accuracy:
//100%
//Attempts No.:
//1