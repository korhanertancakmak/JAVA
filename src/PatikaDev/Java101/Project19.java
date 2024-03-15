/* Pratice19 - Leap Year Calcultor

Write a program in Java to find out whether the year entered by the user is a leap year.

What is Leap Year?
A leap year is a year with 366 days instead of 365 in the Gregorian calendar. This extra day (leap day) is 
being added to February, which normally has 28 days.

How to Calculate Leap Year?
As a general rule, leap years are years that are multiples of 4. Such as:
    * 1988, 1992, 1996, 2000, 2004, 2008, 2012, 2016, 2020, 2024

Among the years that are multiples of 100, only those that are divisible by 400 without remainder are leap years. Such as:
    * 1200, 1600, and 2000 are leap years, but 1700, 1800, and 1900 are not leap years.

The reason why only those that are divisible by 400 are considered leap years is to eliminate the error caused by the fact 
that an astronomical year is approximately 365.242 days, not 365.25 days.

Scenarios:

Enter Year: 2020
2020 is a leap year!

Enter Year: 2021
2021 is not a leap year!

*/

import java.util.Scanner;

public class Project19 {
    
    public static void main(String[] args) {

        Scanner kb = new Scanner(System.in);
        System.out.println("Enter the year: ");
        int year = kb.nextInt();

        if (isLeapYear(year)) {
            System.out.printf("%d is a leap year!", year);
        } else {
            System.out.printf("%d is not a leap year!", year);
        }
        kb.close();
    }

    public static boolean isLeapYear(int year) {
        if (year < 1 || year > 9999) {
            return false;
        }

        boolean divisibleBy4 = year % 4 == 0;
        boolean divisibleBy100 = year % 100 == 0;
        boolean divisibleBy400 = year % 400 == 0;

        if (divisibleBy4) {
            if (divisibleBy100) {
                return divisibleBy400;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
}

