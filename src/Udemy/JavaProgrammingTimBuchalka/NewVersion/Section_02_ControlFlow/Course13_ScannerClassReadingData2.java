package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_02_ControlFlow;

import java.util.Scanner;

public class Course13_ScannerClassReadingData2 {
    public static void main(String[] args) {

        int currentYear = 2023;

        System.out.println(getInputFromScanner(currentYear));
    }

    public static String getInputFromScanner(int currentYear) {

        Scanner cs = new Scanner(System.in);

        System.out.println("Hi, What's your name? ");
        String name = cs.nextLine();

        System.out.println("Hi " + name + ", Thanks for taking the course!");

        System.out.println("What year were you born? ");

        boolean validDOB = false;
        int age = 0;
        do {
            System.out.println("Enter a year of birth >= " +
                    (currentYear - 125) + " and <= " + currentYear);
            try {
                age = CheckData(currentYear, cs.nextLine());
                validDOB = age < 0 ? false : true;
            } catch (NumberFormatException badUserData) {
                System.out.println("Characters not allowed!!! Try again.");
            }
        } while (!validDOB);


        return "So you are " + age + " years old";
    }

    public static int CheckData(int currentYear, String dateOfBirth) {
        int dob = Integer.parseInt(dateOfBirth);
        int minimumYear = currentYear - 125;

        if ((dob < minimumYear) || (dob > currentYear)) {
            return -1;
        }

        return (currentYear - dob);
    }
}
