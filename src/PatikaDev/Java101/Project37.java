/* Pratice37 -  Calculation exponents with recursive methods

Write a program in Java language that uses the Recursive methods to perform the exponentiation process whose base and exponent values ​​are received from the user.

Scenario:
Enter the base value: 2 
Enter the exponent value: 0
Result: 1
Enter the base value: 2
Enter the exponent value: 3
Result: 8
Enter the base value: 5
Enter the exponent value: 3
Result: 125

*/

import java.util.Scanner;

public class Project37 {
    
    public static void main(String[] args) {

        Scanner kb = new Scanner(System.in);
        System.out.println("Enter the base value: ");
        int n1 = kb.nextInt();
        System.out.println("Enter the exponent value: ");
        int n2 = kb.nextInt();

        System.out.println("Result: " + pow(n1, n2));
        kb.close();
    }
    
    public static int pow(int base, int exp) {
        if (exp == 0) {
            return 1;
        }
        return base * pow(base, exp - 1);
    }
    
}

