/* Pratice25 - Program to Find Armstrong Numbers

We write a program to find out whether a number is an Armstrong number using Java loops.

What is Armstrong Number?
If the sum of the nth powers of the digits of an n-digit number is equal to the number itself, such numbers are called Armstrong numbers.

Take the number 407 for example. Returns (4^3)+ (0^3)+(7^3) = 64+0+343 = 407. 
This shows that the number 407 is an Armstrong number.

Let's also look at the number 1342. Since the number 
(1^4)+(3^4)+(4^4)+(2^4) =1+81+256+16=354 is not equal to 1342, 
it is not an Armstrong number.

1634=1^4+6^4+3^4+4^4=1+1296+81+256=1634

54748=5^5+4^5+7^5+4^5+8^5=3125+1024+16807+1024+32768=54748

Homework
Write a program that calculates the sum of the digits of a number.

Example: 1643 = 1 + 6 + 4 + 3 = 14
*/

import java.util.Scanner;

public class Project25 {
    
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.print("Enter the number :");
        int number = input.nextInt();
        int tempNumber = number;
        int basValue, basPow;
        int basNumber = 0;
        int result = 0;

        while (tempNumber != 0) {
            tempNumber /= 10;
            basNumber++;
        }

        tempNumber = number;
        while (tempNumber != 0) {
            basValue = tempNumber % 10;
            // 1*1*1*1 = 1^4
            basPow = 1;
            for (int i = 1; i <= basNumber; i++) {
                basPow *= basValue;
            }
            result += basPow;
            tempNumber /= 10;
        }

        if (result == number) {
            System.out.println(number + " is an Armstrong number.");
        } else {
            System.out.println(number + " is not an Armstrong number.");
        }

        System.out.println("Enter the number that you want its digits'sum: ");
        int num = input.nextInt();
        int tempNum = num;
        int digit = 0, digitSum = 0;      
        while (tempNum != 0) { 
            digit = tempNum % 10;
            if (tempNum / 10 != 0) {
                System.out.print(digit + " + ");
            } else {
                System.out.print(digit + " = ");
            } 
            digitSum += digit;
            tempNum /= 10;
        }
        System.out.print(digitSum);

        input.close();
    }
}

