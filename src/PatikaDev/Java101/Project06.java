/* Code to find hypotenuse in a right triangle

Write a program in Java that takes the length of the base and altitude sides from the user and calculates the hypotenuse.

Homework
Write a program that calculates the area of ​​the triangle whose three side lengths are given by the user.

Formula
Perimeter of triangle = 2u

u = (a + b + c) / 2

Area * Area = u * (u − a)* (u − b) * (u − c)
*/

import java.util.Scanner;

public class Project06 {

    public static void main(String[] args) {
     
        System.out.println("Enter the base and altitude:");
        Scanner kb = new Scanner(System.in);

        Double base = kb.nextDouble();
        Double altitute = kb.nextDouble();

        Double hypo = Math.sqrt((base * base) + (altitute * altitute));
        System.out.printf("Hypotenuse: %.2f%n", hypo);

        Double perimeter = (base + altitute + hypo) / 2;
        Double area = Math.sqrt(perimeter * (perimeter - base) *
                                            (perimeter - altitute) *
                                            (perimeter - hypo));

        System.out.printf("Area: %.2f", area);
        kb.close();
    }
}
