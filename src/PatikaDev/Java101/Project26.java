/* Pratice26 - Program to Find Harmonic Numbers

We will write a program in Java to find the harmonic series of the entered number.

1 + 1/2 + 1/3 + 1/4 + 1/5 + .... = sum(n=0 to infinite) {1/n}
*/

import java.util.Scanner;

public class Project26 {
    
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.print("Enter the number :");
        int num = input.nextInt();
        System.out.printf("Harmonic number of %d is %.2f", num, harmonic(num));

        input.close();
    }

    public static double harmonic(int n) {
        double res = 0.0;

        for(int i = 1; i <= n; i++) {
            res += (double) 1/i;
            System.out.println(res);
        }

        return res;
    }
}


