/* Pratice17 - Flight Ticket Price Codes.InterfaceJava.Calculator

Create a program in Java that calculates flight ticket prices according to distance and conditions. 
Get Distance (KM), age and trip type (One Way, Round Trip) from the user. 
The values ​​received from the user must be valid (distance and age values ​​must be positive numbers, trip type must be 1 or 2). 
Otherwise, the user will be told "You have entered incorrect data!" A warning should be given.
Get the price per distance as 0.10 TRY / km. 

First, calculate the total price of the flight and then apply the following discounts to the customer according to the conditions;

* If the person is under 12 years old, a 50% discount is applied to the ticket price.
* If the person is between the ages of 12-24, a 10% discount is applied to the ticket price.
* If the person is over 65 years old, a 30% discount is applied on the ticket price.
* If the person has selected "Trip Type" round trip, a 20% discount is applied on the ticket price.

Hint:
    Normal Amount = Distance * 0.10 = 1500 * 0.10 = 150 TL
    Age Discount = Normal Amount * Age Discount Rate = 150 * 0.10 = 15 TL
    Discounted Amount = Normal Amount – Age Discount = 150 – 15 = 135 TL
    Round Trip Ticket Discount = Discounted Amount * 0.20 = 135 * 0.20 = 27 TL
    Total Amount = (135-27) * 2 = 216 TL

Scenario-1:
    Enter the distance in km: 1500
    Enter your age: 20
    Enter the trip type (1 => One Way, 2 => Round Trip): 2
    Total Amount = 216 TL

Scenario-2:
    Enter the distance in km: -500
    Enter your age: 1
    Enter the trip type (1 => One Way, 2 => Round Trip): 77
    You Entered Incorrect Data!

Scenario 3
    Enter the distance in km: 200
    Enter your age: 35
    Enter the trip type (1 => One Way, 2 => Round Trip): 1
    Total Amount = 20.0 TL

*/

import java.util.Scanner;

public class Project17 {

    public static void main(String[] args) {
                
        Scanner kb = new Scanner(System.in);
        System.out.print("Enter the distance in km: ");
        int distance = kb.nextInt();
        System.out.print("Enter your age: ");
        int age = kb.nextInt();
        System.out.print("Enter the trip type (1 => One Way, 2 => Round Trip): ");
        int tripType = kb.nextInt();
        if (!(Integer.signum(distance) == 1 && Integer.signum(age) == 1 && tripType == 1 || tripType == 2)) {
            kb.close();
            System.out.println("You have entered incorrect data!");
            return;
        }

        double ageDiscountRate = 0.0, roundTripDiscountRate = 0.0;
        if (age < 12) {
            ageDiscountRate = 0.5;
        } else if (age < 24) {
            ageDiscountRate = 0.1;
        } else if (age > 65) {
            ageDiscountRate = 0.3;
        }

        if (tripType == 2) {
            roundTripDiscountRate = 0.2;
        }

        double unitPrice = 0.10;
        Double normalAmount = distance * unitPrice;
        System.out.printf("Normal Amount = %.2f%n", normalAmount);

        Double ageDiscount = normalAmount * ageDiscountRate;
        System.out.printf("Age Discount = %.2f%n", ageDiscount);
        
        Double discountedAmount = normalAmount - ageDiscount;
        System.out.printf("Discounted Amount = %.2f%n", discountedAmount);

        Double roundTripDiscount = discountedAmount * roundTripDiscountRate;
        System.out.printf("Round Trip Ticket Discount Amount = %.2f%n", roundTripDiscount);

        Double totalAmount = (tripType == 1) ? discountedAmount - roundTripDiscount : 
                                              (discountedAmount - roundTripDiscount) *2; 

        System.out.printf("Total Amount = %.2f%n", totalAmount);                                              

        kb.close();
    }
}

