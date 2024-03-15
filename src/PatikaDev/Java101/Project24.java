/* Pratice24 - Program to Calculate Exponents

We write a program in Java that calculates exponential numbers with the values ​​entered by the user and by using "For Loop".

*/

import java.util.Scanner;

public class Project24 {
    
    public static void main(String[] args) {

        Scanner kb = new Scanner(System.in);
        System.out.print("Enter the base number: ");
        int base = kb.nextInt();
        System.out.print("Enter the exponent number: ");
        int exp = kb.nextInt();

        System.out.printf("pow(base, exp) = %d", pow(base, exp));
        
        kb.close();
    }

    public static int pow(int a, int b) {

        int res = 1;
        for (int i = 1; i <= b; i++) {
            res *= a;
        } 
        return res;
    }
}
