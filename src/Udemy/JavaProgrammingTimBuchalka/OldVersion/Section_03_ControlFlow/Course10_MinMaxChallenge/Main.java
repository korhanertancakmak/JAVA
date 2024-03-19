package CourseCodes.OldSections.Section_03_ControlFlow.Course10_MinMaxChallenge;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        while(true) {                                                                   // Infinite loop

            System.out.println("Enter number:");
            boolean isAnInt = scanner.hasNextInt();
            if(isAnInt) {

                int number = scanner.nextInt();
                if(number > max) {
                    max = number;
                }

                if(number < min) {
                    min = number;
                }

            } else {
                break;
            }

            scanner.nextLine();                                                         // handle input
        }

        System.out.println("min= "+ min + ", max= " + max);
        scanner.close();
    }
}
