/* Calculating perimeter and area of a circle 

Write a program in Java that calculates the area and perimeter of a circle whose radius you receive from the user.
Area Formula: "pi" * r * r;
perimeter Formula: 2 * "pi" * r;

Homework
Write a program to find the area of ​​a circle whose radius is r and whose central angle is "alpha".
Take "pi" = 3,14.
Formula: ("pi" * (r * r) * "alpha") / 360
*/

import java.util.Scanner;

public class Project08 {
    
    public static void main(String[] args) {
        
        Scanner kb = new Scanner(System.in);
        System.out.println("Enter the radius of the circle:");
        Double r = kb.nextDouble();

        Double area = Math.PI * r * r;
        Double per = 2 * Math.PI * r;

        System.out.printf("Area: %.2f Perimeter: %.2f", area, per);
        kb.close();
    }
}

