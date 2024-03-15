/* Practice - Codes.InterfaceJava.Calculator

Homework
Write a code for calculator by using switch-case method with the following options: 
1.Sum
2.Subtract
3.Multiply
4.Divide

*/

import java.util.Scanner;

public class Project11 {
    
    public static void main(String[] args) {
        
        int n1, n2, select;
        Double result = 0.0;
        Scanner kb = new Scanner(System.in);
        
        System.out.println("Enter first number:");
        n1 = kb.nextInt();
        System.out.println("Enter second number:");
        n2 = kb.nextInt();

        System.out.println("1-Sum\n2-Subtract\n3-Multiply\n4-Divide");
        System.out.println("Enter your choice:");
        select = kb.nextInt();

        switch(select) {
            case 1:
                result = (double) n1 + n2;
                System.out.print("Sum: ");
                break;
            case 2:
                result = (double) n1 - n2;
                System.out.print("Subtract: ");
                break;
            case 3:
                result = (double) n1 * n2;
                System.out.print("Multiply: ");
                break;
            case 4:
                if (n2 != 0) {
                    result = (double) n1 / n2;
                    System.out.print("Divide: ");
                }
                break;
        }
        System.out.println(result);
        kb.close();

    }
}
