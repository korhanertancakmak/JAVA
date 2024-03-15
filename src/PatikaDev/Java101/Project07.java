/* Taximeter Program 

Write a program in Java that prints the taximeter price according to the distance(KM) traveled.
Taximeter costs 2.20 TRY per KM. 
The minimum payable amount is 20 TRY. For fares under 20 TRY, 20 TRY will still be charged.
Taximeter opening fee is 10 TRY.
*/

import java.util.Scanner;

public class Project07 {
    
    public static void main(String[] args) {
        
        Scanner kb = new Scanner(System.in);
        System.out.println("Enter the distance traveled in km:");
        Double dist = kb.nextDouble();
        Double totalPrice = 10 + (2.20 * dist);

        if (totalPrice < 20.0) {
            totalPrice = 20.0;
        } 
        System.out.printf("Taximeter price: %.2f%n", totalPrice);

        kb.close();
    }
}
