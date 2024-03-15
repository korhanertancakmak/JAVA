/* Pratice13 - Grade status

Classes : Math, Physics, English, Chemistry, Music

Succeed Point : 55

Task
If the entry point is not between 0-100, ignore and do not include to the average. 
*/

import java.util.Scanner;

public class Project13 {
    
    public static void main(String[] args) {
        
        int math, physics, english, chemistry, music;
        int sloppy;
        double result = 0.0;
        Scanner kb = new Scanner(System.in);
        
        System.out.println("Math points: ");
        sloppy = kb.nextInt();
        math = (sloppy < 0 || sloppy > 100) ? 0 : sloppy;
        System.out.println("Physics points: ");
        sloppy = kb.nextInt();
        physics = (sloppy < 0 || sloppy > 100) ? 0 : sloppy;
        System.out.println("English points: ");
        sloppy = kb.nextInt();
        english = (sloppy < 0 || sloppy > 100) ? 0 : sloppy;
        System.out.println("Chemistry points: ");
        sloppy = kb.nextInt();
        chemistry = (sloppy < 0 || sloppy > 100) ? 0 : sloppy;
        System.out.println("Music points: ");
        sloppy = kb.nextInt();
        music = (sloppy < 0 || sloppy > 100) ? 0 : sloppy;

        result = (math + physics + english + chemistry + music) / 5;
        System.out.printf("Average: %.2f ", result);
        if (result < 55) {
            System.out.println("Failed");
        } else {
            System.out.println("Passed");
        }
        kb.close();
    }
}

