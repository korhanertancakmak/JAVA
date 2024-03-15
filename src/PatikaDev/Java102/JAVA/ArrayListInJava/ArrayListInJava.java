package JAVA.ArrayListInJava;

import java.util.ArrayList;
import java.util.Scanner;

/** 
Sometimes it's better to use dynamic size arrays. 
Java's Arraylist can provide you this feature. Try to solve this problem using Arraylist.

You are given n lines. 
In each line there are zero or more integers. 
You need to answer a few queries where you need to tell the number located in yth position 
of zth line.

Take your input from System.in.

Input Format
The first line has an integer n. 
In each of the next n lines there will be an integer d denoting number of integers on that 
line and then there will be d space-separated integers. 
In the next line there will be an integer q denoting number of queries. 
Each query will consist of two integers x and y.

Constraints
* 1 <= n <= 20000
* 0 <= d <= 50000
* 1 <= q <= 1000
* 1 <= x <= n

Each number will fit in signed integer.
Total number of integers in n lines will not cross 10^5.

Output Format
In each line, output the number located in yth position of zth line. 
If there is no such position, just print "ERROR!"

Sample Input:
5
5 41 77 74 22 44
1 12
4 37 34 36 52
0
3 20 22 33
5
1 3
3 4
3 1
4 3
5 5

Sample Output:
74
52
37
ERROR!
ERROR!

Explanation
The diagram below explains the queries:
<img src="https://s3.amazonaws.com/hr-assets/0/1489168616-b25dd38013-arraylist.png" />
**/


public class ArrayListInJava {
    
    public static void main(String[] args) {
        // Enter your code here. Read input from STDIN. Print output to STDOUT.

        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        ArrayList<int[]> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int d = kb.nextInt();
            int[] arr = new int[d];
            for (int j = 0; j < d; j++) {
                arr[j] = kb.nextInt();
            }
            list.add(arr);
        }
        int q = kb.nextInt();
        for (int k = 0; k < q; k++) {
            int x = kb.nextInt() - 1;
            int y = kb.nextInt() - 1;
            if (list.get(x).length > y) {
                System.out.println(list.get(x)[y]);
            } else {
                System.out.println("ERROR!");
            }
        }
        kb.close();
    }
}
