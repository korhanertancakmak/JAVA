/* Pratice14 - Events up to the weather

Conditions :

If the temperature is less than 5 degrees, Suggest the "Skiing" activity.
If the temperature is between 5 and 15 degrees, suggest the "Cinema" activity.
If the temperature is between 15 and 25 degrees, suggest the "Picnic" activity.
If the temperature is greater than 25 degrees, recommend the "Swimming" activity.

Homework
Find different solutions in other ways than if conditions by using the same example.


*/

import java.util.Scanner;

public class Project14 {
    
    public static void main(String[] args) {
        
        
        Scanner kb = new Scanner(System.in);
        System.out.println("Enter the temperature:");
        int temp = kb.nextInt();
        int condition = 0;

        condition = (temp <= 5) ? 1 : 
                    (temp > 5 && temp <= 15) ? 2 : 
                    (temp > 15 && temp <= 25) ? 3 : 
                    (temp > 25) ? 4 : 0;

        System.out.printf("The temperature entered is: %d%n", temp);
        switch(condition) {
            case 1:
                System.out.println("Skiing is suggested");
                break;
            case 2:
                System.out.println("Cinema is suggested");
                break;
            case 3:
                System.out.println("Picnic is suggested");
                break;
            case 4:
                System.out.println("Swimming is suggested");
                break;
        }
        
        kb.close();
    }
}

