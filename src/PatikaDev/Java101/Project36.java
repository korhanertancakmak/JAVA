/* Pratice36 - Enhanced Codes.InterfaceJava.Calculator

Write a calculator which calculates what the user enters.

Functions of the calculator: 

1- Summation
2- Subtraction
3- Multiplication
4- Division
5- Powers
6- Factorial
7- Mod 
8- The area and perimeter of a rectangular.

*/

import java.util.Scanner;

public class Project36 {
    
    public static void main(String[] args) {

        Scanner kb = new Scanner(System.in);
        System.out.println("Enter the first number: ");
        int n1 = kb.nextInt();
        System.out.println("Enter the second number: ");
        int n2 = kb.nextInt();

        String options = """
            Functions of the calculator:
            1- Summation
            2- Subtraction
            3- Multiplication
            4- Division
            5- Powers
            6- Factorial
            7- Mod
            8- The area and perimeter of a rectangular.
                """; 
        System.out.println(options);
        int option = kb.nextInt();
        
        switch (option) {
            case 1:
                System.out.println("Sum of " + n1 + " and " + n2 + ": " + sum(n1, n2));
                break;
            case 2:
                System.out.println("Subraction of " + n1 + " from " + n2 + ": " + subtract(n1, n2));
                break;
            case 3:
                System.out.println("Multiply " + n1 + " with " + n2 + ": " + multiply(n1, n2));
                break;
            case 4:
                System.out.printf("Division of %d by %d : %.2f%n", n1, n2, divide(n1, n2));
                break;
            case 5:
                System.out.println("Power of " + n1 + " the base " + n2 + ": " + pow(n1, n2));
                break;
            case 6:
                System.out.println("Factorial of " + n1 + " : " + fact(n1));
                System.out.println("Factorial of " + n2 + " : " + fact(n2));
                break;
            case 7:
                System.out.println("Mod of " + n1 + " with " + n2 + ": " + mod(n1, n2));
                break;
            case 8:
                System.out.println("Area of the rectangle with side " + n1 + " and " + n2 + ": " + area(n1, n2));
                System.out.println("Perimeter of the rectangle with side " + n1 + " and " + n2 + ": " + peri(n1, n2));
                break;
        }

        kb.close();
    }

    public static int sum(int n1, int n2) {       
        return n1 + n2;
    }
    
    public static int subtract(int n1, int n2) {
        return n1 - n2;
    }

    public static int multiply(int n1, int n2) {
        return n1 * n2;
    }

    public static double divide(int n1, int n2) {
        if (n2 == 0) {
            System.out.println("Wrong data!");
        }
        return (double) n1 / n2;
    }
    
    public static int pow(int n1, int n2) {
        int result = 1;
        for (int i = 1; i <= n2; i++) {
            result *= n1;
        }
        return result;
    }

    public static int fact(int n) {
        int result = 1;

        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
    
    public static int mod(int n1, int n2) {
        return n1 % n2;
    }

    public static int area(int n1, int n2) {
        return multiply(n1, n2);
    }

    public static int peri(int n1, int n2) {
        return sum(n1, n2);
    }
}
