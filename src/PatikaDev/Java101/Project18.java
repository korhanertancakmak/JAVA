/* Pratice18 - Chinese Zodiac Codes.InterfaceJava.Calculator

Write a program in Java that takes the date of birth from the user and calculates the Chinese Zodiac value.

What is the Chinese Zodiac?
Chinese astrology is a type of astrology that has been used for 4000 thousand years and 
identifies people with 12 different zodiac signs and symbols. The Chinese Zodiac is an animal 
ring in which these 12 signs are arranged at equal intervals (10 degrees wide) and has little 
to do with stars.

How to calculate Chinese Zodiac?
When calculating the Chinese zodiac, it is calculated by dividing the person's birth year by 12.

Birth year % 12 = 0 ➜ Monkey
Birth year % 12 = 1 ➜ Rooster
Birth year % 12 = 2 ➜ Dog
Birth year % 12 = 3 ➜ Pig
Birth year % 12 = 4 ➜ Rat
Birth year % 12 = 5 ➜ Ox
Birth year % 12 = 6 ➜ Tiger
Birth year % 12 = 7 ➜ Rabbit
Birth year % 12 = 8 ➜ Dragon
Birth year % 12 = 9 ➜ Snake
Birth year % 12 = 10 ➜ Horse
Birth year % 12 = 11 ➜ Sheep

Scenario
Enter your Year of Birth: 1990
Your Chinese Zodiac Sign: Horse

*/

import java.util.Scanner;

public class Project18 {

    public static void main(String[] args) {
                
        Scanner kb = new Scanner(System.in);
        System.out.print("Enter your year of birth: ");
        int birthYear = kb.nextInt();

        int remainder = birthYear % 12;
        String zodiac = switch (remainder) {
            case 0 -> "Monkey";
            case 1 -> "Rooster";
            case 2 -> "Dog";
            case 3 -> "Pig";
            case 4 -> "Rat";
            case 5 -> "Ox";
            case 6 -> "Tiger";
            case 7 -> "Rabbit";
            case 8 -> "Dragon";
            case 9 -> "Snake";
            case 10 -> "Horse";
            case 11 -> "Sheep";
            default -> "";
        };

        System.out.print("Your Chinese Zodiac Sign: " + zodiac);                                              

        kb.close();
    }
}

