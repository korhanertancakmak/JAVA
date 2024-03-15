/* Pratice16 - Zodiac Codes.InterfaceJava.Calculator Program

Capricorn: December 22 - January 21
Aquarius: January 22 - February 19
Pisces: February 20 - March 20
Aries: March 21 - April 20
Taurus: April 21 - May 21
Gemini: May 22 - June 22
Cancer: June 23 - July 22
Leo: July 23 - August 22
Virgo: August 23 - September 22
Libra: September 23 - October 22
Scorpio: October 23 - November 21
Sagittarius: November 22 - December 21

Homework
Do the same example without using switch-case.

*/

import java.util.Scanner;

record Birth(int day, int month) {
}

public class Project16 {

    public static void main(String[] args) {
                
        Scanner kb = new Scanner(System.in);
        System.out.println("Day(1-31):");
        int day = kb.nextInt();
        System.out.println("Month(1-12):");
        int month = kb.nextInt();
        Birth birth = new Birth(day, month);

        String zodiac = "";
        
        if (birth.month() == 12 && birth.day() > 21 || birth.month() == 1 && birth.day() <= 21) {
            zodiac = "Capricorn";
        } else if (birth.month() == 1 || birth.month() == 2 && birth.day() <= 19) {
            zodiac = "Aquarius";
        } else if (birth.month() == 2 || birth.month() == 3 && birth.day() <= 20) {
            zodiac = "Pisces";
        } else if (birth.month() == 3 || birth.month() == 4 && birth.day() <= 20) {
            zodiac = "Aries";
        } else if (birth.month() == 4 || birth.month() == 5 && birth.day() <= 21) {
            zodiac = "Taurus";
        } else if (birth.month() == 5 || birth.month() == 6 && birth.day() <= 22) {
            zodiac = "Gemini";
        } else if (birth.month() == 6 || birth.month() == 7 && birth.day() <= 22) {
            zodiac = "Cancer";
        } else if (birth.month() == 7 || birth.month() == 8 && birth.day() <= 22) {
            zodiac = "Leo";
        } else if (birth.month() == 8 || birth.month() == 9 && birth.day() <= 22) {
            zodiac = "Virgo";
        } else if (birth.month() == 9 || birth.month() == 10 && birth.day() <= 22) {
            zodiac = "Libra";
        } else if (birth.month() == 10 || birth.month() == 11 && birth.day() <= 21) {
            zodiac = "Scorpio";
        } else if (birth.month() == 11 || birth.month() == 12) {
            zodiac = "Sagittarius";
        }

        System.out.println("Your Zodiac Sign: " + zodiac);
        kb.close();
    }
}

