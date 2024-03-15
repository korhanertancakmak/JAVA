/* Grocery Cashier Program

Write a program in Java that prints the total amount of the products purchased by users from the grocery store 
according to their kilogram values.

Fruits Prices in kg
Pear: 2.14 TRY
Apple: 3.67 TRY
Tomato: 1.11 TRY
Banana: 0.95 TRY
Eggplant: 5.00 TRY

Sample Output;
How Many Kilos of Pears? :       0
How Many Kilos of Apple? :       1
How Many Kilos of Tomatoes? :    1
How Many Kilograms of Banana? :  2
How Many Kilos of Eggplant? :    3
Total Amount:                   21.68 TL

*/

import java.util.Scanner;

public class Project10 {
    
    public static void main(String[] args) {
        
        Scanner kb = new Scanner(System.in);
        Double pearP = 2.14;
        Double appleP = 3.67;
        Double tomatoP = 1.11;
        Double bananaP = 0.95;
        Double eggplantP = 5.0;
        System.out.println("How many kilos of Pears?: ");
        int pears = kb.nextInt();
        System.out.println("How many kilos of Apple?: ");
        int apple = kb.nextInt();
        System.out.println("How many kilos of Tomatoes?: ");
        int tomato = kb.nextInt();
        System.out.println("How many kilos of Banana?: ");
        int banana = kb.nextInt();
        System.out.println("How many kilos of Eggplant?: ");
        int eggplant = kb.nextInt();

        Double price = (pears * pearP) + (apple * appleP)+ (tomato * tomatoP)+ (banana * bananaP)+ (eggplant * eggplantP);

        System.out.printf("Your body mass index: %.2f%n", price);
        kb.close();

    }
}
