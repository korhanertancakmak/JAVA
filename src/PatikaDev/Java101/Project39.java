/* Pratice39 -  Creating recursive method based on pattern

Write a "Recursive" method in Java language without using a loop that obeys the following rules according to the n value received from the user.

Rules: Subtract 5 from the entered number until the entered number is 0 or negative. 
       Print the last value on the screen during each subtraction. 
       After the number becomes negative or 0, add 5 again. 
       Again, print the last value of the number to the screen with each addition.

Scenarios
N Number: 16
Output: 16 11 6 1 -4 1 6 11 16

N Number: 10
Output: 10 5 0 5 10

N Number: 25
Output: 25 20 15 10 5 0 5 10 15 20 25

N Number: 5
Output: 5 0 5

*/

import java.util.Scanner;

public class Project39 {
    
    public static void main(String[] args) {       

        Scanner kb = new Scanner(System.in);
        System.out.print("N : ");
        int n = kb.nextInt();
        
        System.out.println("Output: " + pattern(n, -5, n));
        

        kb.close(); 
    }
    
    public static String pattern(int n, int m, int check) {
        String result = Integer.toString(n) + " ";
        if (check == n && m > 0) {
            return result;
        }
        if (n <= 0) {
            m *= -1;
        }
        n += m;
        result += pattern(n, m, check);
        return result;
    }
}

