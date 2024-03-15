/* Pratice35 - Number Palindrome

Write a program that finds whether the number is a Palindrome or not in Java. 

What is Palindrome?

A palindrome number is a number which when reversed is equal to the original number.
For example: 121, 12321, 1001 etc.

*/

import java.util.Scanner;

public class Project35 {
    
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("Enter the test number for being Palindrome: ");
        int n = input.nextInt();
        if (isPalindrome(n)) {
            System.out.println(n + " is a Palindrome.");
        } else {
            System.out.println(n + " is not a Palindrome.");
        }
        input.close();
    }

    public static boolean isPalindrome(int number) {
        if (number < 0) {
            number = Math.abs(number);
        }

        int length = Integer.toString(number).length();
        int reverse = 0;
        int lastDigit;
        int store = number;
        StringBuilder reverseStr = new StringBuilder();
        for (int i = 0; i < length; i++) {
            lastDigit = number % 10;
            reverseStr.append(Integer.toString(lastDigit));
            number = number / 10;
        }

        reverse = Integer.parseInt(reverseStr.toString());

        return store == reverse;
    }
}

