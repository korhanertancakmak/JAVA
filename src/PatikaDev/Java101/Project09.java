/* Body Mass Index Calculation

Get height and weight values ​​from the user and assign them to a variable with Java. 
Calculate the user's "Body Mass Index" value according to the formula below and print it on the screen.

Formula
Weight (kg) / Height (m) * Height (m)

Output
Please enter your height (in meters): 1.72
Please enter your weight: 105
Your Body Mass Index: 35.49215792320173

*/

import java.util.Scanner;

public class Project09 {
    
    public static void main(String[] args) {
        
        Scanner kb = new Scanner(System.in);
        System.out.printf("Please enter your height: ");
        Double height = kb.nextDouble();
        System.out.printf("Please enter your weight: ");
        Double weight = kb.nextDouble();

        Double bmi = weight / (height * height);

        System.out.printf("Your body mass index: %.14f", bmi);
        kb.close();
    }
}


