/*Evaluating VAT price

Write a program in Java that calculates the total price with VAT and VAT amount of the money received from the user and prints it on the screen.

Note: Take 18% as the VAT ratio.

Price without VAT(Input) = 10;

Price with VAT = 11.8;

VAT amount = 1.8;

Homework
Take the VAT ratio:
    1. 18% if the entry is between 0-1000 TRY
    2. 8% if the entry is higher than 1000 TRY
*/

import java.util.Scanner;

public class Project05 {
    
    public static void main(String[] args) {
        
        System.out.println("Enter the price: ");
        Scanner kb = new Scanner(System.in);

        double price = kb.nextDouble();
        double VatAmount = (price * 18) / 100;

        System.out.println("Price with VAT: " + (price + VatAmount)); 
        System.out.println("VAT amount: " + VatAmount); 

        kb.close();
    }
}

